/*
  Copyright (c) 2006 BEA Systems, Inc.
	All rights reserved

	THIS IS UNPUBLISHED PROPRIETARY
	SOURCE CODE OF BEA Systems, Inc.
	The copyright notice above does not
	evidence any actual or intended
	publication of such source code.
*/
package com.bea.alsb.transports.websocket;

import com.bea.alsb.transports.websocket.WebSocketTransportMessagesLogger;
import com.bea.wli.sb.transports.TransportEndPoint;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.TransportManagerHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedActionException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.xmlbeans.XmlObject;
import weblogic.security.SubjectUtils;
import weblogic.security.Security;

import javax.security.auth.Subject;

/**
 * This is a receiver thread of the socket transport end point which is
 * responsible for opening a server socket at the configured port and handles
 * start/stop/suspend/resume operations. It creates a WebSocketInboundMessageContext
 * and sends it to the transport SDK.
 */
public class WebSocketTransportReceiver implements Runnable
{
    private WebSocketTransportEndPoint endPoint;
    private String dispatchPolicy;
    private volatile boolean isStopping;
    private String mServletName;

    private final Map<String, LinkedList<XmlObject>> messages = new TreeMap<String, LinkedList<XmlObject>>();


    public WebSocketTransportReceiver(WebSocketTransportEndPoint endPoint) throws TransportException
    {
        this.endPoint = endPoint;
        init();
    }

    /**
     * Initializes the ServerSocket with the endpoint configuration.
     *
     * @throws TransportException
     */
    private void init() throws TransportException {
        WebSocketEndpointConfiguration epc = endPoint.getWebSocketEndpointConfiguration();
        /** Store endpoint configuration variables locally to avoid reading these
         * from xbean again and again.*/
        dispatchPolicy = epc.getDispatchPolicy();
    }

    /**
     * This is the method called by the Jetty webserver
     * @param pServletName
     * @param pMessage
     */
    public void receive(String pServletName, XmlObject pMessage)
    {
        synchronized (messages) {
            LinkedList<XmlObject> xmlObjects = messages.get(pServletName);
            if (xmlObjects == null) {
                xmlObjects = new LinkedList<XmlObject>();
                messages.put(pServletName, xmlObjects);
            }
            xmlObjects.add(pMessage);
            messages.notifyAll();
        }
    }

    public void run() {

        // deferred initialization (the servlet name is not yet ready at the time init() is being called)
        mServletName = endPoint.getServletName();

        while (true) {

            synchronized (messages) {
                while (!isStopping && (messages.get(mServletName) == null || messages.get(mServletName).isEmpty())) {
                    try {
                        messages.wait();
                    } catch (InterruptedException e) {}
                }
            }

            if (isStopping)
                return;

            XmlObject xmlObject;
            try {
                xmlObject = messages.get(mServletName).removeFirst();
            } catch (NoSuchElementException e) {
                continue;
            }

            if (xmlObject != null) {
                /** create a worker and schedule it */
                WorkerThread workerThread = new WorkerThread(xmlObject, endPoint);

                try {
                    TransportManagerHelper.schedule(workerThread, dispatchPolicy);
                } catch (TransportException e) {
                    WebSocketTransportUtil.logger.error(WebSocketTransportMessagesLogger.scheduleFailed(), e);
                }
            }
        }
    }

    public void stopAcceptor() {
        isStopping = true;
        synchronized (messages) {
            messages.notifyAll(); // handle the stop request
        }
    }

    /**
     * This class represents a single thread of execution of send the data to the
     * transport.
     */
    static class WorkerThread implements Runnable
    {
        private XmlObject mXmlObject;
        private WebSocketTransportEndPoint endPoint;

        public WorkerThread(XmlObject pXmlObject, WebSocketTransportEndPoint pEndPoint) {
            mXmlObject = pXmlObject;
            this.endPoint = pEndPoint;
        }

        public void run() {
            try {
                String msgId = new Random().nextInt() + "." + System.nanoTime();


                final WebSocketInboundMessageContext inboundMessageContext = new WebSocketInboundMessageContext(endPoint, msgId, mXmlObject);

                /** send inbound context to SDK which sends it to the pipeline,
                 * invoke the pipeline in anonymous subject.*/
                Subject subject = SubjectUtils.getAnonymousUser();
                if (subject != null) {
                    try {
                        Security.runAs(subject,
                                new PrivilegedExceptionAction<Void>() {
                                    public Void run() throws TransportException {
                                        TransportManagerHelper.getTransportManager().receiveMessage(inboundMessageContext, null);
                                        return null;
                                    }
                                });
                    } catch (PrivilegedActionException e) {
                        throw (TransportException) e.getException();
                    }
                } else {
                    TransportManagerHelper.getTransportManager().receiveMessage(inboundMessageContext, null);
                }

            } catch (TransportException e) {
                WebSocketTransportUtil.logger.error(getErrorMsg(), e);
            }
        }

        private String getErrorMsg() {
            return WebSocketTransportMessagesLogger.socketReceiverFailedLoggable().getMessage();
        }
    }

}
