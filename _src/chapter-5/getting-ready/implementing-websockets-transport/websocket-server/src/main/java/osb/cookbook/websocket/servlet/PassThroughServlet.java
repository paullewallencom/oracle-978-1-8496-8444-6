package osb.cookbook.websocket.servlet;

import org.apache.xmlbeans.XmlObject;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import osb.cookbook.websocket.OsbCookbookServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class PassThroughServlet extends WebSocketServlet
{
    private Set<PassThroughSocket> sockets = new CopyOnWriteArraySet<PassThroughSocket>();

    private XmlObject mXml;
    private OsbCookbookServletContext mServer;

    public PassThroughServlet(OsbCookbookServletContext pServer) {
        mServer = pServer;
    }

    public void update(XmlObject pXml)
    {
        System.out.println("PassThroughServlet.update() recieved: " + pXml);
        mXml = pXml;
        for (PassThroughSocket socket : sockets) {
            socket.send(pXml);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Current XML</h1>");
        response.getWriter().println("<pre>"+(mXml!= null? mXml.toString().replace("<", "&lt;").replace(">", "&gt;") : "null")+"</pre>");
    }

    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
        return new PassThroughSocket();
    }

    public class PassThroughSocket implements WebSocket, WebSocket.OnTextMessage
    {
        private Connection outbound;

        public void onOpen(Connection outbound) {
            System.out.println("PassThroughServlet$PassThroughSocket.onOpen()");
            this.outbound = outbound;
            sockets.add(this);
        }


        public void onClose(int i, String s) {
            System.out.println("PassThroughServlet$PassThroughSocket.onClose()");
            sockets.remove(this);
        }

        public void send(final XmlObject pXml) {
            // send asynchronously to the WebSocket client to not block the OSB
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("PassThroughServlet$PassThroughSocket.send().run()");
                    try {
                        outbound.sendMessage("OSB:" + pXml.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onMessage(String data) {
            System.out.println("PassThroughServlet$PassThroughSocket.onMessage(): " + data);
            if (data.contains("disconnect")) {
                outbound.disconnect();
            } else {

                String msg = "<msg>" + data + "</msg>";

                mServer.notifyOSB(msg, PassThroughServlet.this.getServletName());
            }
        }
    }

}
