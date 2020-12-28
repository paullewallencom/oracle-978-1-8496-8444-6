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

import org.apache.xmlbeans.XmlObject;
import com.bea.wli.sb.transports.ResponseMetaData;
import com.bea.wli.sb.transports.ServiceTransportSender;
import com.bea.wli.sb.transports.TransportOptions;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.CoLocatedMessageContext;

/**
 * This class provides implementation for co-located out-bound  invocations.
 */
public class WebSocketCoLocatedMessageContext extends CoLocatedMessageContext {

    /**
     * Constructor.
     *
     * @param sender
     * @param options
     * @throws TransportException
     */
    protected WebSocketCoLocatedMessageContext(ServiceTransportSender sender,
                                               TransportOptions options)
            throws TransportException {
        super(sender, options);
    }

    /**
     * Returns a new {@link WebSocketResponseMetaData} instance, which is empty.
     *
     * @return returns a new empty SocketResponseMetaData object.
     */
    public ResponseMetaData createResponseMetaData() throws TransportException {
        WebSocketResponseMetaData responseMetaData = new WebSocketResponseMetaData("utf-8");
        return responseMetaData;
    }

    /**
     * Returns {@link WebSocketResponseMetaData} initialized with headers and metadata
     * retreived from the passed XMLObject.
     *
     * @param rmdXML
     * @return
     * @throws TransportException
     */
    public ResponseMetaData createResponseMetaData(XmlObject rmdXML)
            throws TransportException {
        WebSocketResponseMetaDataXML xmlObject =
                WebSocketResponseMetaData.getSocketResponseMetaData(rmdXML);
        if (xmlObject != null) {
            return new WebSocketResponseMetaData(xmlObject);
        }
        return null;
    }

}
