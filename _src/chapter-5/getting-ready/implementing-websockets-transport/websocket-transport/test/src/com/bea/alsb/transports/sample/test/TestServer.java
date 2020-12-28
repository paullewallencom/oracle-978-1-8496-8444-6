package com.bea.alsb.transports.sample.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * This class can be used as an external service to receive requests from ALSB
 * business service and send the response back.
 */
public class TestServer {
  static final String STD_MSG =
    "<?xml version=\"1.0\" ?> " +
      "<project name=\"sock-transport\" default=\"build-jar\" basedir=\".\"/>";
  private String message = STD_MSG;
  private String fileEnc;
  private int port;

  public TestServer(String msg, int port, String fileEnc) {
    if (msg != null) {
      this.message = msg;
    }
    this.port = port;
    this.fileEnc = fileEnc;
  }

  private void startServer() {
    try {
      ServerSocket serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress(port));
      log("Started listening on socket:" + serverSocket.getInetAddress()
        + " on thread:" + Thread.currentThread().getName());
      while (true) {
        Socket socket = serverSocket.accept();
        new WorkerThread(socket).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    if (args.length < 1) {
      log("Wrong Usage:");
      log(
        "Usage is: java -Dfile-encoding=<file-encoding> " +
          "-Drequest-encoding=<request-encoding> " +
          "com.bea.alsb.transports.sample.test.TestServer  " +
          "<port> <message-file-location> \n" +
          "port is the port number at which server socket is listening.\n" +
          "message-file-location (optional argument) is the location of the " +
          "message-file, " +
          "which will be sent as a response to the business service.\n" +
          "file-encoding is the encoding of the file. \n" +
          "request-encoding is is the encoding of the" +
          "request that is sent by the socket business service. " +
          "Both these properties have a default value of utf-8\n\n");
      System.exit(1);
    }

    int port = Integer.parseInt(args[0]);
    String location = null;
    String msg = null;
    String fileEnc = System.getProperty("file-encoding", "utf-8");
    if(args.length>1) {
      location = args[1];
      msg = TestUtil.getMessage(location, fileEnc);
      System.out.println("---------------------------------------------------");
      System.out.println("----> Given message is= " + msg);
      System.out.println("---------------------------------------------------");
    }
    TestServer testServer = new TestServer(msg, port, fileEnc);
    testServer.startServer();
  }

  private static void log(String msg) {
    System.out.println("<" + new Date() + ">" + msg);
  }

  /**
   * This class does the work of receiving and sending data in a separate
   * thread.
   */
  class WorkerThread extends Thread {
    private Socket socket;

    public WorkerThread(Socket socket) {
      this.socket = socket;
      log("Connection established for: " + socket.getInetAddress() +
        " on thread:" + Thread.currentThread().getName());
    }


    public void run() {
      try {
        InputStream inputStream = socket.getInputStream();
        String reqEnc = System.getProperty("request-encoding", "utf-8");
        InputStreamReader inputStreamReader =
          new InputStreamReader(inputStream, reqEnc);
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[512];
        int index = -1;
        String doubleCRLF = new String(TestUtil.D_CRLF.getBytes(reqEnc));
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
        if (index == -1) {
          log("Illegal request message format.");
        } else {
          String request = sb.toString().substring(0, index);
          log("Request is:" + request);
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(message.getBytes(fileEnc));
        outputStream.write(TestUtil.D_CRLF.getBytes(fileEnc));
        outputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
