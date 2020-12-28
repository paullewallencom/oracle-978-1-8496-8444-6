package com.bea.alsb.transports.sample.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is an Util class which is used by TestServer and TestClient.
 */
public class TestUtil {
  public static final String D_CRLF = "\r\n\r\n";

  /**
   * Returns a String of the contents in the given file location. It replaces
   * multiple \r\n to asignle \r\n.
   *
   * @param fileLocation
   * @return String of the content of the given file.
   */
  public static String getMessage(String fileLocation, String enc) {
    File file = new File(fileLocation);
    if (!file.exists()) {
      throw new IllegalArgumentException(
        "message-file-fileLocation :" + fileLocation + " does not exist.");
    }

    StringBuilder msg = new StringBuilder();
    InputStreamReader inputStreamReader = null;
    try {
      inputStreamReader = new InputStreamReader(new FileInputStream(file), enc);
      int ct = 0;
      char[] charBuffer = new char[512];
      while ((ct = inputStreamReader.read(charBuffer)) != -1) {
        msg.append(charBuffer, 0, ct);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if(inputStreamReader != null) {
          inputStreamReader.close();
        }
      } catch (IOException ignore) {
      }
    }
    String str = msg.toString();
    /** replacing multiple \r\n  to a signle \r\n */
    str=str.replaceAll("(\r\n)+", "\r\n");
    return str;
  }

}
