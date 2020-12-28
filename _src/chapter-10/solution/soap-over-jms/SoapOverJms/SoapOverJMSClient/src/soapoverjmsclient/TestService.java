package soapoverjmsclient;

import java.net.URISyntaxException;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

import osb.book.soap.jms.HelloWorldService;
import osb.book.soap.jms.HelloWorldServiceSoapHttpPortBindingQSService;
import osb.book.soap.jms.HelloWorldServiceSoapHttpPortBindingQSService_Impl;

import weblogic.wsee.connection.transport.jms.JmsTransportInfo;
import weblogic.wsee.jaxrpc.WLStub;

public class TestService {
  public TestService() {
    super();
  }

  public static void main(String[] args) throws ServiceException,
                                                URISyntaxException,
                                                RemoteException {

    HelloWorldServiceSoapHttpPortBindingQSService service =
      new HelloWorldServiceSoapHttpPortBindingQSService_Impl();
    HelloWorldService port =
      service.getHelloWorldServiceSoapHttpPortBindingQSPort();

    Stub stub = (Stub)port;  
    String uri = "jms://localhost:7001?URI=SourceQueue";
    JmsTransportInfo ti = new JmsTransportInfo(uri);
    stub._setProperty("weblogic.wsee.connection.transportinfo", ti);

    try {
      String result = null;
      System.out.println("start");
      result = port.sayHello();
      System.out.println("Got JMS result: " + result);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
