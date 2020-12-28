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

import com.bea.alsb.transports.websocket.WebSocketTransportMessagesLogger;
import com.bea.wli.sb.transports.EndPointConfiguration;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.TransportManagerHelper;
import org.apache.xmlbeans.XmlObject;
import weblogic.logging.NonCatalogLogger;
import weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Set;

/**
 * This class provides utility methods that can be used in WebSocket Transport.
 */
public class WebSocketTransportUtil {
    public static final NonCatalogLogger logger = new NonCatalogLogger("OSBCookbook.WebSocketTransport");

    /**
     * Returns the WebSocketEndpointConfiguration of the passed configuration. If
     * the instance is of same type it will be casted and returned else
     * instance's stream will be parsed and returns WebSocketEndpointConfiguration.
     *
     * @param configuration
     * @return
     * @throws TransportException
     */
    public static WebSocketEndpointConfiguration getConfig(
            EndPointConfiguration configuration) throws TransportException {
        XmlObject xbean = configuration.getProviderSpecific();

        if (xbean instanceof WebSocketEndpointConfiguration) {
            return (WebSocketEndpointConfiguration) xbean;
        } else {
            try {
                return WebSocketEndpointConfiguration.Factory.parse(xbean.newInputStream());
            } catch (Exception e) {
                throw new TransportException(e.getMessage(), e);
            }
        }
    }

    /**
     * Establish and return a JMX Connector using the internal 'wlx' protocol
     * where wlx is a collocated protocol.
     *
     * @param jndiName the JNDI name of the mbean server to connect to.
     */
    public static JMXConnector getServerSideConnection(String jndiName)
            throws IOException {
        return getConnection(COLOCATED_PROTOCOL, jndiName, null, 0, null, null);
    }

    private static final String COLOCATED_PROTOCOL = "wlx";
    private static final String JNDI_ROOT = "/jndi/";

    private static JMXConnector getConnection(String protocol, String URI,
                                              String hostName, int portNumber,
                                              String userName, String password)
            throws IOException, MalformedURLException {
        JMXServiceURL serviceURL = null;
        if (protocol.equals(COLOCATED_PROTOCOL)) {
            serviceURL = new JMXServiceURL(protocol, null, 0, JNDI_ROOT + URI);
        } else {
            serviceURL =
                    new JMXServiceURL(protocol, hostName, portNumber, JNDI_ROOT + URI);
        }

        Hashtable<String, String> h = new Hashtable<String, String>();
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
                "weblogic.management.remote");
        if (userName != null && password != null) {
            h.put(Context.SECURITY_PRINCIPAL, userName);
            h.put(Context.SECURITY_CREDENTIALS, password);
        }

        JMXConnector connection = JMXConnectorFactory.connect(serviceURL, h);

        return connection;
    }

    public static String toPublicServiceURI(String uriVal)
            throws URISyntaxException {
        Set<String> servers = null;
        if (TransportManagerHelper.isRuntimeEnabled()) {
            servers = TransportManagerHelper.getRuntimeServerNames();
        }

        String host = "localhost";
        if (TransportManagerHelper.isRuntimeEnabled() &&
                servers != null && !servers.isEmpty()) {
            // give one of the runtime servers as host.
            String server = servers.iterator().next();
            try {
                JMXConnector jmxConnector = WebSocketTransportUtil.getServerSideConnection(
                        DomainRuntimeServiceMBean.MBEANSERVER_JNDI_NAME);
                host = TransportManagerHelper.getDomainRuntimeService(jmxConnector).
                        getDomainConfiguration().lookupServer(server).getListenAddress();
            } catch (Exception e) {
                WebSocketTransportUtil.logger
                        .error(WebSocketTransportMessagesLogger.noJmxConnectorAvailble(), e);
                host = "localhost";
            }
        }

        int port = Integer.parseInt(uriVal);
        URI uri = new URI("tcp", null, host, port, null, null, null);
        return uri.toString();
    }

}
