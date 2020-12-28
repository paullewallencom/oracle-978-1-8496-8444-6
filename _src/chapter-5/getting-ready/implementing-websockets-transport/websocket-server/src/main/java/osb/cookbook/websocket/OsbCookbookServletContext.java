package osb.cookbook.websocket;

import org.apache.xmlbeans.XmlObject;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.LazyList;
import org.eclipse.jetty.websocket.WebSocketServlet;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class OsbCookbookServletContext
{
    private int mPort = 8080;
    private boolean mPortSet;
    private String mContextPath = "/";
    private boolean mContextPathSet;
    private String mStaticFilesDir = ".";
    private boolean mStaticFilesDirSet;

    private Thread mServerThread;
    private boolean mServerThreadStarted;

    private Map<String, Pair<ServletHolder, Object>> mServlets = new TreeMap<String, Pair<ServletHolder, Object>>();

    private ServletContextHandler mJettyContext;
    private Server mJettyServer;

    private static final OsbCookbookServletContext mInstance = new OsbCookbookServletContext();

    public static void main(String[] args) throws Exception
    {
        mInstance.mStaticFilesDir = "D:/ws";
        mInstance.createServer();
        mInstance.startServer();
        Thread.sleep(1000);

        try {
            mInstance.addServlet(Class.forName("osb.cookbook.websocket.servlet.PassThroughServlet").asSubclass(WebSocketServlet.class), "/pass", null);
        } catch (Exception e) {
            System.out.println("OsbCookbookServletContext.main " + e);
            e.printStackTrace();
        }
    }

    public static OsbCookbookServletContext getInstance() {
        return mInstance;
    }

    public void update(XmlObject pXml, String pServletName) {
        if (!mServlets.containsKey(pServletName))
            throw new IllegalArgumentException("Unknown servlet '" + pServletName + "'");

        Pair<ServletHolder, Object> meta = mServlets.get(pServletName);
        ServletHolder servletHolder = meta.left;

        try {
            Servlet servlet = servletHolder.getServlet();
            Method update = servlet.getClass().getMethod("update", XmlObject.class);
            update.invoke(servlet, pXml);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void notifyOSB(String pMessage, String pServletName)
    {
        if (!mServlets.containsKey(pServletName))
            throw new IllegalArgumentException("Unknown servlet '" + pServletName + "'");

        Pair<ServletHolder, Object> meta = mServlets.get(pServletName);
        Object callback = meta.right;

        if (callback == null) {
            System.out.println("OsbCookbookServletContext.notifyOSB(): no callback, ignoring message");
            return;
        }

        try {
            Method receive = callback.getClass().getMethod("receive", String.class, XmlObject.class);

            XmlObject xmlObject = XmlObject.Factory.parse(pMessage);

            receive.invoke(callback, pServletName, xmlObject);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public synchronized void startServer() {
        if (mServerThreadStarted || mServerThread == null || (mJettyServer != null && !mJettyServer.isStopped())) {
            return;
        }

        mServerThread.start();
        mServerThreadStarted = true;
    }

    public synchronized void stopServer() {
        if (mServerThread == null || mJettyServer == null || !mJettyServer.isStarted())
            return;

        try {
            mJettyServer.stop();
        } catch (Exception e) {
            System.out.println("OsbCookbookServletContext.stopServer(): Exception: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void stopAndReset()
    {
        stopServer();
        mPort = 8080;
        mPortSet = false;
        mContextPath = "/";
        mContextPathSet = false;
        mStaticFilesDir = ".";
        mStaticFilesDirSet = false;

        mServerThread = null;
        mServerThreadStarted = false;

        mServlets = new TreeMap<String, Pair<ServletHolder, Object>>();

        mJettyContext = null;
        mJettyServer = null;
    }

    /**
     * @return the effective servlet name which has to be used for future reference
     */
    public String addServlet(Class<? extends WebSocketServlet> pServletClass, String pPath, Object pCallback)
    {
        System.out.println("OsbCookbookServletContext.addServlet(): called with " + pServletClass.getName() + " and path " + pPath);


        try {
            String servletName = pServletClass.getName() + pPath;

            if (mServlets.containsKey(servletName))
            {
                // Check if the stored callback is null and the provided one is not.
                if (mServlets.get(servletName).right == null) {
                    mServlets.get(servletName).right = pCallback;
                } else if (pCallback != null) {
                    throw new IllegalStateException("For the servlet " + servletName + ", there is already a callback registered. " +
                            "It is not allowed to define more than one Proxy Service on the same web socket servlet instance");
                }

                // Servlet already added, doing nothing
                return servletName;
            }

            HttpServlet servlet = pServletClass.getConstructor(OsbCookbookServletContext.class).newInstance(this);
            ServletHolder servletHolder = new ServletHolder(servlet);
            servletHolder.setName(servletName);

            while (mJettyServer == null || !mJettyServer.isStarted()) {
                Thread.sleep(10); // Wait until the server is started
            }

            mJettyContext.addServlet(servletHolder, pPath);

            mServlets.put(servletName, new Pair<ServletHolder, Object>(servletHolder, pCallback));
            return servletName;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public void removeServlet(String pServletName)
    {
        ServletHandler handler = mJettyContext.getServletHandler();

        ServletHolder servletHolder = handler.getServlet(pServletName);
        ServletHolder[] servlets = handler.getServlets();
        ServletHolder[] removedList = (ServletHolder[])LazyList.removeFromArray(servlets, servletHolder);
        handler.setServlets(removedList);
    }

    public synchronized void createServer() {
        if (mServerThread != null)
            return;

        mServerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("OsbCookbookServletContext.createServer().run() start of method");
                    Server server = new Server(mPort);

                    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                    context.setContextPath(mContextPath);
                    mJettyContext = context;
                    mJettyServer = server;


                    try {
                        ResourceHandler resource_handler = new ResourceHandler();
                        resource_handler.setDirectoriesListed(true);
                        resource_handler.setWelcomeFiles(new String[]{ "index.html" });

                        resource_handler.setResourceBase(mStaticFilesDir);

                        HandlerList handlers = new HandlerList();
                        handlers.setHandlers(new Handler[] { resource_handler, context, new DefaultHandler() });
                        server.setHandler(handlers);

                        server.start();
                        System.out.println("OsbCookbookServletContext.run start requested");
                        server.join();
                        System.out.println("OsbCookbookServletContext.run server stopped");
                    } catch (InterruptedException e) {
                        System.out.println("OsbCookbookServletContext.run(): Stopping Jetty server");
                    } catch (Exception e) {
                        System.err.println("OsbCookbookServletContext.run(): Error while executing Jetty server");
                        e.printStackTrace();
                    }
                } finally {
                    synchronized (mInstance) {
                        mServerThread = null;
                        mServerThreadStarted = false;
                    }
                }
            }
        });
    }

    public synchronized void setPort(int pPort) {
        if (pPort == 0)
            return; // ignore, leave default

        if (mPortSet && mPort != pPort)
            throw new IllegalStateException("Port set to incompatible values. Current: " + mPort + ", new: " + pPort);

        mPort = pPort;
        mPortSet = true;
    }

    public int getPort() {
        return mPort;
    }

    public synchronized void setContextPath(String pContextPath) {
        if (pContextPath == null || pContextPath.length() == 0)
            return; // ignore, leave default

        if (mContextPathSet && !mContextPath.equals(pContextPath))
            throw new IllegalStateException("ContextPath set to incompatible values. Current: " + mContextPath + ", new: " + pContextPath);


        mContextPath = pContextPath;
        mContextPathSet = true;
    }

    public String getContextPath() {
        return mContextPath;
    }

    public String getStaticFilesDir() {
        return mStaticFilesDir;
    }

    public void setStaticFilesDir(String pStaticFilesDir) {
        if (pStaticFilesDir == null || pStaticFilesDir.length() == 0)
            return; // ignore, leave default

        if (mStaticFilesDirSet && !mStaticFilesDir.equals(pStaticFilesDir))
            throw new IllegalStateException("StaticFilesDir set to incompatible values. Current: " + mStaticFilesDir + ", new: " + pStaticFilesDir);


        mStaticFilesDir = pStaticFilesDir;
        mStaticFilesDirSet = true;
    }

    private class Pair<T1, T2> {
        public T1 left;
        public T2 right;

        public Pair(T1 pLeft, T2 pRight) {
            left = pLeft;
            right = pRight;
        }
    }
}
