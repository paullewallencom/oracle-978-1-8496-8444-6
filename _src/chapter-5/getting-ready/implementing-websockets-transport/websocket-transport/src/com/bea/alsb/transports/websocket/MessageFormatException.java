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

/**
 * This exception represents that the message format is wrong.
 */
public class MessageFormatException extends Exception {
  public MessageFormatException() {
  }

  public MessageFormatException(String msg) {
    super(msg);
  }

  public MessageFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public MessageFormatException(Throwable cause) {
    super(cause);
  }
}
