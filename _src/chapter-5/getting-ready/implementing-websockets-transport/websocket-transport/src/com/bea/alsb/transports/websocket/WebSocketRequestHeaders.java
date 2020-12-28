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

import com.bea.wli.sb.transports.DefaultRequestHeaders;
import com.bea.wli.sb.transports.RequestHeadersXML;
import com.bea.wli.sb.transports.TransportException;

/**
 * Request headers POJO for Socket transport.
 */
public class WebSocketRequestHeaders extends DefaultRequestHeaders<WebSocketRequestHeadersXML> {
  /*message-count element specified in the schema, whenever a new header
  element is added their get/set type methods can be added here. */
  private static final String MESSAGE_COUNT = "message-count";

  public WebSocketRequestHeaders(RequestHeadersXML headers) throws
    TransportException {
    super(WebSocketTransportProvider.getInstance(), headers);
  }

  /**
   * Returns message count attribute.
   *
   * @return
   */
  public long getMessageCount() {
    return (Long) getHeader(MESSAGE_COUNT);
  }

  /**
   * Sets the messagecount attribute.
   *
   * @param messageCount
   */
  public void setMessageCount(long messageCount) {
    setHeader(MESSAGE_COUNT, messageCount);
  }

}
