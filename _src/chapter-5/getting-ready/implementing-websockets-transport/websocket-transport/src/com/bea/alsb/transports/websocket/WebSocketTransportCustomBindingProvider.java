package com.bea.alsb.transports.websocket;

import com.bea.wli.sb.transports.AbstractTransportCustomBindingProvider;
import com.bea.wli.sb.transports.TransportProvider;
import com.bea.wli.sb.transports.URIType;
import com.bea.wli.sb.transports.EndPointConfiguration;
import com.bea.wli.config.Ref;

import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class WebSocketTransportCustomBindingProvider
        extends AbstractTransportCustomBindingProvider {

    protected WebSocketTransportCustomBindingProvider(TransportProvider tp) {
        super(tp);
    }

    protected String evaluateBusinessURI(URIType uriType)
            throws URISyntaxException {
        return WebSocketTransportUtil.toPublicServiceURI(uriType.getValue());
    }

    protected List<String> toProxyEndPointAddress(EndPointConfiguration epc,
                                                  Ref service) {
        List<String> addresses = new ArrayList<String>();
        URIType[] uriArray = epc.getURIArray();
        if (uriArray == null || uriArray.length != 1 || uriArray[0] == null)
            throw new IllegalArgumentException("No URI in endpoint configuration");

        String uri = uriArray[0].getValue();
        if (uri.trim().equals("")) {
            throw new IllegalArgumentException("The URI cannot be empty");
        }
        try {
            addresses.add(WebSocketTransportUtil.toPublicServiceURI(uri));
        } catch (URISyntaxException e) {
            WebSocketTransportUtil.logger.error(e.getMessage(), e);
        }

        return addresses;
    }

}
