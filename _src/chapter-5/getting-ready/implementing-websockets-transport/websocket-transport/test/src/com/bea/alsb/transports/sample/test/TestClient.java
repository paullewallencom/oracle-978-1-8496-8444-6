package com.bea.alsb.transports.sample.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * This class can be used to send request messages to the ALSB socket
 * proxyservice and receive the response.
 */
public class TestClient {

  private static final String MSG = "I am a Rimbaud with a leather jacket, " +
    "If my poetry aims to achieve anything, it's to deliver people from the limited ways in which they see and feel" +
    "-- the Lizard King -----JM";
  private String message = MSG;
  private String fileEnc;
  private String host;
  private int port;
  private int threadCt;

  public TestClient(String msg, String host, int port, int threadCt,
                    String fileEnc) {
    this.host = host;
    this.port = port;
    this.threadCt = threadCt;
    this.fileEnc = fileEnc;
    if(msg != null) {
      this.message = msg;
    }
  }

  private void sendToSocketTransport() {
    try {
      SendReceiveTask target = new SendReceiveTask(host, port);
      Thread[] threads = new Thread[threadCt];

      for (int i = 0; i < threadCt; i++) {
        threads[i] = new Thread(target);
      }

      for (int i = 0; i < threadCt; i++) {
        threads[i].start();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void debugSocket(Socket sock) {
    log("----> sock.getPort() = " + sock.getPort());
    log(
      "----> sock.getRemoteSocketAddress() = " + sock.getRemoteSocketAddress());
    log("----> sock.getLocalSocketAddress() = " + sock.getLocalSocketAddress());
    log("----> sock.getInetAddress() = " +
      sock.getInetAddress().getCanonicalHostName());
    log("----> sock.getLocalPort() = " + sock.getLocalPort());
  }

  private static void log(String msg) {
    System.out.println("<" + new Date() + "> " + msg);
  }

  public static void main(String[] args) {

    if (args.length < 3) {
      log("Wrong Usage.");
      log(
        "Usage is : java -Dfile-encoding=<file-encoding> " +
          "-Dresponse-encoding=<response-encoding> " +
          "com.bea.alsb.transports.sample.test.TestClient " +
          "<host-name> <port>  <thread-ct> <message-file-location> \n" +
          "where host-name is host name of the ALSB\n" +
          "port is the port number at which proxy service is listening to,\n" +
          "therad-ct is the number of threads/clients \n" +
          "message-file-location (optional argument)is the location of " +
          "the message-file, " +
          "which will be sent as a response to the business service.\n" +
          "file-encoding is is the encoding of the file. " +
          "response-encoding is a system property which is the encoding of the" +
          "response received from the socket proxy service. " +
          "Both these properties have a default value of utf-8\n\n");
      System.exit(1);
    }

    String host = args[0];
    int port = Integer.parseInt(args[1]);
    int threadCt = Integer.parseInt(args[2]);
    log("----> host = " + host);
    log("----> port = " + port);
    log("----> threadCt = " + threadCt);
    String msg = null;
    String fileEnc = System.getProperty("file-encoding", "utf-8");
    log("----> file-encoding = "+fileEnc);
    if (args.length > 3) {
      msg = TestUtil.getMessage(args[3], fileEnc);
    }
    TestClient testClient = new TestClient(msg, host, port, threadCt, fileEnc);
    testClient.sendToSocketTransport();
  }

  class SendReceiveTask implements Runnable {
    private String host;
    private int port;

    public SendReceiveTask(String host, int port) {
      this.host = host;
      this.port = port;
    }

    public void run() {
      try {
        Socket sock = new Socket(host, port);
        debugSocket(sock);
        OutputStream outputStream = sock.getOutputStream();
        InputStream inputStream = sock.getInputStream();
        InputStreamReader inputStreamReader;
        StringBuilder sb;
        int index;
        try {
          outputStream.write(message.getBytes(fileEnc));
          outputStream.write(TestUtil.D_CRLF.getBytes(fileEnc));
          outputStream.flush();
          log("Sent a message to the server on thread: " +
            Thread.currentThread().getName());
          String resEnc = System.getProperty("response-encoding", "utf-8");
          inputStreamReader = new InputStreamReader(inputStream, resEnc);
          sb = new StringBuilder();
          char[] buff = new char[512];
          String doubleCRLF = new String(TestUtil.D_CRLF.getBytes(resEnc));
          while (true) {
            index = inputStreamReader.read(buff);
            if (index == -1) {
              break;
            }
            sb.append(buff, 0, index);
            if ((index = sb.lastIndexOf(doubleCRLF)) != -1) {
              break;
            }
          }
        } finally {
          sock.close();
        }

        if (index == -1) {
          log("Illegal response message format or it might be one-way.");
          return;
        }
        String response = sb.toString().substring(0, index);
        log("----> response for thread: " + Thread.currentThread().getName() +
          "= " + response);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
