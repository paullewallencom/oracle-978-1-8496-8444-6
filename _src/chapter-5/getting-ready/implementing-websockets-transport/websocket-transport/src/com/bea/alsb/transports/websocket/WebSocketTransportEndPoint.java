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

import com.bea.wli.config.Ref;
import com.bea.wli.sb.transports.*;
import org.apache.xmlbeans.XmlObject;
import osb.cookbook.websocket.OsbCookbookServletContext;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class represents End point abstraction for websocket transport.
 */
public class WebSocketTransportEndPoint implements TransportEndPoint
{
    private Ref ref;
    private EndPointConfiguration config;
    private WebSocketTransportProvider socketTransportProvider;
    private volatile WebSocketTransportReceiver socketTransportReceiver;
    private String dispatchPolicy;
    private TransportEndPoint.MessagePatternEnum messagePattern;
    private URI[] uri;
    private WebSocketEndpointConfiguration epc;

    private String servletName;

    public WebSocketTransportEndPoint(Ref ref,
                                      EndPointConfiguration config,
                                      WebSocketTransportProvider socketTransportProvider)
            throws TransportException {
        this.ref = ref;
        this.config = config;
        this.socketTransportProvider = socketTransportProvider;
        epc = WebSocketTransportUtil.getConfig(config);
        dispatchPolicy = epc.getDispatchPolicy();
        if (config.getInbound()) {
            socketTransportReceiver = new WebSocketTransportReceiver(this);
        }

        // Get start parameters for Jetty server
        int serverPort;
        String contextPath;
        String staticFilesDir;

        if (config.getInbound()) {
            WebSocketInboundPropertiesType inboundProps = epc.getInboundProperties();
            serverPort      = inboundProps.getServerPort();
            contextPath     = inboundProps.getServerContextPath();
            staticFilesDir  = inboundProps.getStaticFilesDir();
        } else {
            WebSocketOutboundPropertiesType outboundProps = epc.getOutboundProperties();
            serverPort      = outboundProps.getServerPort();
            contextPath     = outboundProps.getServerContextPath();
            staticFilesDir  = outboundProps.getStaticFilesDir();
        }

        // Start the Jetty Server if this is not done yet
        OsbCookbookServletContext server = OsbCookbookServletContext.getInstance();
        server.setPort(serverPort);
        server.setContextPath(contextPath);
        server.setStaticFilesDir(staticFilesDir);
        server.createServer();
        server.startServer();
    }


    /**
     * @return the reference to the service representing this endpoint
     */
    public Ref getServiceRef() {
        return ref;
    }

    public String getServletName() {
        return servletName;
    }

    /**
     * @return the array of URIs for this endpoint. Inbound endpoints will have a
     *         single URI, whereas outbound endpoints will be associated with
     *         multiple URIs
     */
    public URI[] getURI() {
        if (uri == null) {
            try {
                URIType[] uristr = config.getURIArray();
                uri = new URI[uristr.length];
                for (int i = 0; i < uristr.length; i++) {
                    uri[i] = new URI(uristr[i].getValue());
                }
                return uri;
            } catch (URISyntaxException e) {
                WebSocketTransportUtil.logger.error(e.getLocalizedMessage(), e);
                return null;
            }
        }

        return uri;
    }

    /**
     * @return true if this is an inbound endpoint
     */
    public boolean isInbound() {
        return config.getInbound();
    }


    /**
     * @return an XML Bean that describes the configuration of this endpoint.
     *         Configuration properties are specific to the provider type for this
     *         endpoint.
     */
    public EndPointConfiguration getConfiguration() {
        return config;
    }


    /**
     * @return the transport provider for this endpoint
     */
    public TransportProvider getProvider() {
        return socketTransportProvider;
    }


    /**
     * @return empty (new) meta-data for the request part of the outbound message,
     *         e.g. headers, etc.
     */
    public RequestMetaData createRequestMetaData() throws TransportException {
        WebSocketRequestMetaData requestMetaDataPojo = new WebSocketRequestMetaData("utf-8");
        return requestMetaDataPojo;
    }

    /**
     * @return return meta-data for the request part of the outbound message
     *         initialized according to provider-defined XmlBean representation.
     */
    public RequestMetaData createRequestMetaData(XmlObject rmdXML)
            throws TransportException {
        WebSocketRequestMetaDataXML socketRequestMetaDataXML =
                WebSocketRequestMetaData.getSocketRequestMetaData(rmdXML);
        if (socketRequestMetaDataXML != null) {
            return new WebSocketRequestMetaData(socketRequestMetaDataXML);
        }
        return null;
    }

    /**
     * @return returns false always becuase this transport does not support
     *         transactions.
     * @throws TransportException
     */
    public boolean isTransactional() throws TransportException {
        return false;
    }


    /**
     * @return returns the type of messaging pattern for this endpoint
     * @throws TransportException
     */
    public MessagePatternEnum getMessagePattern() throws TransportException {
        if (messagePattern == null) {
            messagePattern = MessagePatternEnum.ONE_WAY;
//                    epc.getRequestResponse() ?
//                            MessagePatternEnum.SYNCHRONOUS :
//                            MessagePatternEnum.ONE_WAY;
        }

        return messagePattern;
    }

    public WebSocketEndpointConfiguration getWebSocketEndpointConfiguration() {
        return epc;
    }

    public void start() {

        String servletClassName;
        String servletPath = config.getURIArray(0).getValue();
        if (isInbound()) {
            WebSocketInboundPropertiesType inbProps = epc.getInboundProperties();
            servletClassName = inbProps.getServletClass();
        } else {
            WebSocketOutboundPropertiesType outbProps = epc.getOutboundProperties();
            servletClassName = outbProps.getServletClass();
        }

        Class servletClass;
        try {
            servletClass = Class.forName(servletClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }

        servletName = OsbCookbookServletContext.getInstance().addServlet(servletClass, servletPath, socketTransportReceiver);

        if (isInbound()) {

            /** Schedule a receiver to receive requests from clients. */
            /** This is a long running thread it exists for the life time of
             * this endpoint. Generally thread work should be assigned to work-
             * managers of WL Server. */
            Thread thread = new Thread(socketTransportReceiver);
            thread.start();
        }
    }

    public void stop() {

        OsbCookbookServletContext.getInstance().removeServlet(servletName);

        if (isInbound() && socketTransportReceiver != null) {
            socketTransportReceiver.stopAcceptor();
        }
    }

    public void suspend() {
        stop();
    }

    public void resume() {
        start();
    }

}
