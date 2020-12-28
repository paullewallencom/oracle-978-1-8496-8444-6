package com.bea.alsb.transports.websocket;

import com.bea.wli.sb.transports.TransportManager;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.TransportProviderFactory;

public class WebSocketTransportProviderFactory implements TransportProviderFactory {

    public void registerProvider(TransportManager tm) throws TransportException {
        WebSocketTransportProvider instance = WebSocketTransportProvider.getInstance();
        tm.registerProvider(instance, null);
    }

    public String getId() {
        return WebSocketTransportProvider.ID;
    }
}
