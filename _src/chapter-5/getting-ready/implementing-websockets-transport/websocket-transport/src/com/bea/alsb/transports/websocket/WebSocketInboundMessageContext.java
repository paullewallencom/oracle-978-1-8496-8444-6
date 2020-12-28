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

import com.bea.wli.sb.sources.Source;
import com.bea.wli.sb.sources.XmlObjectSource;
import com.bea.wli.sb.transports.*;
import org.apache.xmlbeans.XmlObject;

import java.net.URI;

/**
 * This class represents the message context at transport level for an incoming
 * message.
 */
public class WebSocketInboundMessageContext implements InboundTransportMessageContext
{
    private WebSocketTransportEndPoint endPoint;
    private String msgId;
    private XmlObject msg;
    private WebSocketRequestMetaData requestMetadata;
    private WebSocketResponseMetaData responseMetaData;
    private Source responsePayload;
    private static int count = 0;

    /**
     * Constructor of SocketInboundMessageContext. Initializes the field
     * variables, reads the message from the input stream and it is set.
     *
     * @param endPoint
     * @param msgId
     * @param msg
     */
    public WebSocketInboundMessageContext(WebSocketTransportEndPoint endPoint,
                                          String msgId,
                                          XmlObject msg) throws TransportException
    {
        this.endPoint = endPoint;
        this.msgId = msgId;
        this.msg = msg;

        requestMetadata = new WebSocketRequestMetaData("utf-8");
        ((WebSocketRequestHeaders) requestMetadata.getHeaders()).setMessageCount(++count);
    }

    /**
     * @return the service endpoint object which has received this incoming
     *         message
     */
    public TransportEndPoint getEndPoint() throws TransportException {
        return endPoint;
    }

    /**
     * @return the meta-data for the request part of the message, e.g. headers,
     *         etc. Returns null if there is no request meta-data
     */
    public RequestMetaData getRequestMetaData() throws TransportException {
        return requestMetadata;
    }

    /**
     * @return returns a source (e.g. input stream or a DOM object) for reading
     *         data in the body of the request of an inbound message or null if
     *         there is no body of the request. Note that the entire body of the
     *         payload is retrieved.
     */
    public Source getRequestPayload() throws TransportException {
        if (msg == null) {
            return null;
        }

        return new XmlObjectSource(msg);
    }

    /**
     * @return empty (new) meta-data for the response part of the message, e.g.
     *         headers, etc. Used for initializing the inbound response
     */
    public ResponseMetaData createResponseMetaData() throws TransportException {
        WebSocketResponseMetaData responseMetaData = new WebSocketResponseMetaData("utf-8");
        return responseMetaData;
    }


    /**
     * @return meta-data for the response part of the message, e.g. headers, etc
     *         initialized according to transport provider-specific XMLBean. Used
     *         for initializing the inbound response
     */
    public ResponseMetaData createResponseMetaData(XmlObject rmdXML) throws TransportException
    {
        WebSocketResponseMetaDataXML xmlObject = WebSocketResponseMetaData.getSocketResponseMetaData(rmdXML);
        if (xmlObject != null) {
            return new WebSocketResponseMetaData(xmlObject);
        }
        return null;
    }

    /**
     * sets the response metadata of the message.
     *
     * @param rmd
     * @throws TransportException when the passed metadata is not an instance of
     *                            SocketResponseMetaData.
     */
    public void setResponseMetaData(ResponseMetaData rmd) throws TransportException
    {
        if (!(rmd instanceof WebSocketResponseMetaData))
        {
            throw new TransportException(WebSocketTransportMessagesLogger.invalidResponseMetadataType(WebSocketResponseMetaData.class.getName()));
        }
        responseMetaData = (WebSocketResponseMetaData) rmd;
    }

    public void setResponsePayload(Source src) throws TransportException {
        responsePayload = src;
    }

    /**
     * Sends the response back to the client.
     */
    public void close(TransportOptions transportOptions) {

    }

    public URI getURI() {
        return endPoint.getURI()[0];
    }

    public String getMessageId() {
        return msgId;
    }

}
