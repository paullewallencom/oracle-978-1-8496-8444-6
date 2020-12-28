package osb.cookbook.websocket.servlet;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Html5Servlet extends WebSocketServlet {


    private AtomicInteger index = new AtomicInteger();


    private static final List<String> tickers = new ArrayList<String>();

    static {
        tickers.add("ajeesh");
        tickers.add("peeyu");
        tickers.add("kidillan");
        tickers.add("entammo");
    }


    private static final long serialVersionUID = 1L;


    public WebSocket doWebSocketConnect(HttpServletRequest req, String resp) {
        System.out.println("On server");
        return new StockTickerSocket();
    }

    protected String getMyJsonTicker() {
        StringBuilder start = new StringBuilder("{");
        start.append("\"stocks\":[");
        int counter = 0;
        for (String aTicker : tickers) {
            counter++;

            start.append("{ \"ticker\":\"" + aTicker + "\"" + "," + "\"price\":\"" + index.incrementAndGet() + "\" }");
            if (counter < tickers.size()) {
                start.append(",");
            }
        }
        start.append("]");
        start.append("}");
        return start.toString();
    }

    public class StockTickerSocket implements WebSocket, WebSocket.OnControl, WebSocket.OnTextMessage {

        private Connection outbound;
        Timer timer;

        public void onOpen(Connection outbound) {
            this.outbound = outbound;
            timer = new Timer();
        }


        public void onClose(int i, String s) {
            timer.cancel();
        }


        @Override
        public boolean onControl(byte controlCode, byte[] data, int offset, int length) {
            return false;
        }

        @Override
        public void onMessage(String data) {
            if (data.indexOf("disconnect") >= 0) {
                outbound.disconnect();
            } else {
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            System.out.println("Running task");
                            outbound.sendMessage(getMyJsonTicker());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Date(), 5000);
            }
        }
    }


}
