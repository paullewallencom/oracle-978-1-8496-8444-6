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

import com.bea.wli.sb.transports.DefaultResponseMetaData;
import com.bea.wli.sb.transports.TransportException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;

/**
 * POJO class for ResponseMetaData of the socket transport, which has two
 * attrbutes called endPointHost and endPointIP.
 */
public class WebSocketResponseMetaData
  extends DefaultResponseMetaData<WebSocketResponseMetaDataXML> {
  /**
   * Host name of the endpoint of the external system.
   */
  private String endPointHost;
  /**
   * IP address of the endpoint of the external system.
   */
  private String endPointIP;

  public WebSocketResponseMetaData(WebSocketResponseMetaDataXML rmdXML)
    throws TransportException {
    super(WebSocketTransportProvider.getInstance(), rmdXML);
    if(rmdXML != null) {
      if(rmdXML.isSetServiceEndpointHost()) {
        setEndPointHost(rmdXML.getServiceEndpointHost());
      }
      if(rmdXML.isSetServiceEndpointIp()) {
        setEndPointIP(rmdXML.getServiceEndpointIp());
      }
    }
  }

  public WebSocketResponseMetaData(String encoding) throws TransportException {
    super(WebSocketTransportProvider.getInstance());
    setCharacterEncoding(encoding);
  }

  public String getEndPointHost() {
    return endPointHost;
  }

  public void setEndPointHost(String endPointHost) {
    this.endPointHost = endPointHost;
  }

  public String getEndPointIP() {
    return endPointIP;
  }

  public void setEndPointIP(String endPointIP) {
    this.endPointIP = endPointIP;
  }

  /**
   * Returns ResponseMetaDataXML, XML bean which is created by setting the
   * values of this POJO instace.
   *
   * @return
   * @throws TransportException
   */
  public WebSocketResponseMetaDataXML toXML() throws TransportException {
    WebSocketResponseMetaDataXML responseMetaData =super.toXML();

    if (endPointHost != null) {
      responseMetaData.setServiceEndpointHost(endPointHost);
    }
    if (endPointIP != null) {
      responseMetaData.setServiceEndpointIp(endPointIP);
    }
    return responseMetaData;
  }

  /**
   * Validates and Parses the given XmlObject to WebSocketResponseMetaDataXML.
   * @param xbean
   * @return WebSocketResponseMetaDataXML of given XmlObject.
   * @throws TransportException
   */
  public static WebSocketResponseMetaDataXML getSocketResponseMetaData(
    XmlObject xbean) throws TransportException {
    if (xbean == null) {
      return null;
    } else if (xbean instanceof WebSocketResponseMetaDataXML) {
      return (WebSocketResponseMetaDataXML) xbean;
    } else {
      try {
        return (WebSocketResponseMetaDataXML.Factory.parse(xbean.newInputStream()));
      } catch (XmlException e) {
        throw new TransportException(e.getMessage(), e);
      } catch (IOException e) {
        throw new TransportException(e.getMessage(), e);
      }
    }
  }
}
