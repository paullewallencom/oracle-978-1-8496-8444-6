package osb.cookbook.websocket.servlet;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends WebSocketServlet
    {

        public HelloServlet() {
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello world</h1>");
            response.getWriter().println("session="+request.getSession(true).getId());
        }

        @Override
        public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
            return null;
        }
    }
