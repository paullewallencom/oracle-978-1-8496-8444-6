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

import com.bea.wli.sb.transports.DefaultRequestMetaData;
import com.bea.wli.sb.transports.RequestHeaders;
import com.bea.wli.sb.transports.RequestHeadersXML;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.TransportProvider;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;

/**
 * POJO class for Request metadata of the socket transport.
 */
public class WebSocketRequestMetaData
  extends DefaultRequestMetaData<WebSocketRequestMetaDataXML> {
  private int port = Integer.MIN_VALUE;
  private String hostAddress;

  public WebSocketRequestMetaData(WebSocketRequestMetaDataXML rmdXML)
    throws TransportException {
    super(WebSocketTransportProvider.getInstance(), rmdXML);
    if(rmdXML != null) {
      if(rmdXML.isSetClientHost()) {
        setClientHost(rmdXML.getClientHost());
      }
      if(rmdXML.isSetClientPort()) {
        setClientPort(rmdXML.getClientPort());
      }
    }
  }

  public WebSocketRequestMetaData(String requestEncoding) throws TransportException {
    /*not calling super.(TransportProvider provider, RequestHeaders hdr,
    String enc) because it does not create new headers if hdr is null.*/
    super(WebSocketTransportProvider.getInstance());
    setCharacterEncoding(requestEncoding);
  }

  protected RequestHeaders createHeaders(TransportProvider provider,
                                         RequestHeadersXML hdrXML)
    throws TransportException {
    return new WebSocketRequestHeaders(hdrXML);
  }

  /**
   * Returns RequestMetaDataXML, XML bean which is created by setting the values
   * of this POJO instace.
   *
   * @return
   * @throws TransportException
   */
  public WebSocketRequestMetaDataXML toXML() throws TransportException {
    WebSocketRequestMetaDataXML requestMetaData = super.toXML();
    // set socket transport specific metadata.
    if (hostAddress != null) {
      requestMetaData.setClientHost(hostAddress);
    }
    if (port != Integer.MIN_VALUE) {
      requestMetaData.setClientPort(port);
    }
    return requestMetaData;
  }

  public void setClientHost(String hostAddress) {
    this.hostAddress = hostAddress;
  }

  public void setClientPort(int port) {
    this.port = port;
  }

  /**
   * Validates and Parses the given XmlObject to WebSocketRequestMetaDataXML.
   * @param xbean
   * @return WebSocketRequestMetaDataXML of the given XmlObject.
   * @throws TransportException
   */
  public static WebSocketRequestMetaDataXML getSocketRequestMetaData(
    XmlObject xbean) throws TransportException {
    if (xbean == null) {
      return null;
    } else if (xbean instanceof WebSocketRequestMetaDataXML) {
      return (WebSocketRequestMetaDataXML) xbean;
    } else {
      try {
        return WebSocketRequestMetaDataXML.Factory.parse(xbean.newInputStream());
      } catch (XmlException e) {
        throw new TransportException(e.getMessage(), e);
      } catch (IOException e) {
        throw new TransportException(e.getMessage(), e);
      }
    }
  }
}
