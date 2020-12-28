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

import com.bea.wli.config.Ref;
import com.bea.wli.config.env.NonQualifiedEnvValue;
import com.bea.wli.config.resource.Diagnostic;
import com.bea.wli.config.resource.Diagnostics;
import com.bea.wli.sb.management.query.ProxyServiceQuery;
import com.bea.wli.sb.services.ServiceInfo;
import com.bea.wli.sb.services.BindingTypeInfo;
import com.bea.wli.sb.transports.*;
import com.bea.wli.sb.transports.ui.TransportUIBinding;
import com.bea.wli.sb.transports.ui.TransportUIContext;
import com.bea.wli.sb.util.Refs;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlError;
import osb.cookbook.websocket.OsbCookbookServletContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Locale;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class implements {@link com.bea.wli.sb.transports.TransportProvider} and
 * provides functionality for websocket  transport.
 */
public final class WebSocketTransportProvider implements TransportProvider, TransportCustomBindingProvider
{
    private static WebSocketTransportProvider instance = new WebSocketTransportProvider();

    private WebSocketTransportCustomBindingProvider bindingProvider;

    /**
     * ID of the socket transport.
     */
    public static final String ID = "websocket";
    private static final String URI = "URI";
    private Hashtable<Ref, WebSocketTransportEndPoint> endPoints = new Hashtable<Ref, WebSocketTransportEndPoint>();
    private static final String ENABLED = "#websocket_ep_enabled";
    private static final String DELETE_ENDPOINT = "#_delete_websocket_ep";
    private static final String UPDATE_OLD_ENDPOINT = "#_update_old_websocket_ep";

    private WebSocketTransportProvider() {
        bindingProvider = new WebSocketTransportCustomBindingProvider(this);
    }

    /**
     * @return singleton object of this class.
     */
    public static WebSocketTransportProvider getInstance() {
        return instance;
    }

    /**
     * @return Returns {@link #ID}.
     */
    public String getId() {
        return ID;
    }

    /**
     * Validates the EndPointConfiguration and updates Diagnostics object if there
     * are any validation errors/messages.
     *
     * @param context
     */
    public void validateEndPointConfiguration(TransportValidationContext context)
    {
        if (context == null)
            throw new IllegalArgumentException("context is null");

        ServiceInfo serviceInfo = context.getServiceInfo();
        Diagnostics diags = context.getDiagnostics();
        Locale locale = context.getLocale();

        if (serviceInfo == null)
            throw new IllegalArgumentException("service info is null");
        if (diags == null)
            throw new IllegalArgumentException("diagnostics is null");

        EndPointConfiguration endPoint = serviceInfo.getEndPointConfiguration();
        String invalidURI = WebSocketTransportMessagesLogger.invalidURILoggable().getMessageText(locale);

        // checking whether the configured URI is valid.
        URIType[] uriArray = endPoint.getURIArray();
        if (uriArray.length > 1)
            diags.add(Diagnostic.mkCannotCommit(0, null, invalidURI, null));
        else
            for (URIType uri : uriArray) {
                if (uri == null || uri.getValue().length() == 0) {
                    diags.add(Diagnostic.mkCannotCommit(0, null, invalidURI, null));
                }
            }

        if (endPoint.getInbound()) {
            String uri = endPoint.getURIArray()[0].getValue();

            try {
                List<Ref> refs = TransportManagerHelper.searchInstanceIds(Refs.PROXY_SERVICE_TYPE, ProxyServiceQuery.KEY_PROXYURI, uri);
                refs.remove(serviceInfo.getRef());
                for (Ref ref : refs) {
                    // URIs should not have conflicts with socket transport only.
                    // (If your customized transport can have a conflict with other transports
                    // then it should be validated accordingly.)
                    if (ID.equals(TransportManagerHelper.getServiceInfo(ref).getEndPointConfiguration().getProviderId()))
                    {
                        String message = WebSocketTransportMessagesLogger.uriConflictLoggable(ref.getFullName()).getMessageText(locale);
                        diags.add(Diagnostic.mkCannotCommit(0, null, message, null));
                        break;
                    }
                }
            } catch (TransportException e) {
                WebSocketTransportUtil.logger.error(e.getLocalizedMessage(), e);
            }
        }

        // Check if servlet class exists and is instantiatable
        if (!TransportManagerHelper.isOffline()) // can't check that in the IDE
        {
            try {
                WebSocketEndpointConfiguration config = WebSocketTransportUtil.getConfig(endPoint);

                String className;
                if (endPoint.getInbound()) {
                    className = config.getInboundProperties().getServletClass();
                } else {
                    className = config.getOutboundProperties().getServletClass();
                }

                try {
                    Class servletClass = Class.forName(className);
                    String superClassName = servletClass.getSuperclass().getName();
                    if (!superClassName.equals("org.eclipse.jetty.websocket.WebSocketServlet")) {
                        diags.add(Diagnostic.mkCannotCommit(0, null, "Servlet class is not a subclass of org.eclipse.jetty.websocket.WebSocketServlet, but of: " + superClassName, null));
                    }
                    int modifiers = servletClass.getModifiers();
                    if (!Modifier.isPublic(modifiers)) {
                        diags.add(Diagnostic.mkCannotCommit(0, null, "Class "+className+" is not public", null));
                    } else if (Modifier.isAbstract(modifiers)) {
                        diags.add(Diagnostic.mkCannotCommit(0, null, "Class "+className+" is abstract", null));
                    }

                    Constructor constructor = servletClass.getConstructor(OsbCookbookServletContext.class);
                    if (!Modifier.isPublic(constructor.getModifiers())) {
                        diags.add(Diagnostic.mkCannotCommit(0, null, "Constructor "+className+"(OsbCookbookServletContext) is not public", null));
                    }
                } catch (ClassNotFoundException e) {
                    diags.add(Diagnostic.mkCannotCommit(0, null, "Servlet class not found: " + className, null));
                } catch (NoSuchMethodException e) {
                    diags.add(Diagnostic.mkCannotCommit(0, null, "Servlet has no constructor matching (OsbCookbookServletContext)", null));
                }

            } catch (TransportException e) {
                diags.add(Diagnostic.mkCannotCommit(0, null, e.getMessage(), null));
            }
        }
    }


    /**
     * Creates and deploys an outbound or inbound endpoint on this server
     * <p/>
     * The semantics of this operation is as follows: prepare whatever is
     * necessary for this endpoint to be operational, but it cannot process
     * messages on this until a activationComplete() call has been received.
     */
    public TransportEndPoint createEndPoint(EndPointOperations.Create createContext) throws TransportException
    {
        if (!TransportManagerHelper.isRuntimeEnabled()) {
            return null;
        }

        Ref ref = createContext.getRef();
        createContext.getScratchPad().put(ref.getFullName() + ENABLED, createContext.isEnabled());
        WebSocketTransportEndPoint socketTransportEndPoint = new WebSocketTransportEndPoint(ref, createContext.getEndPointConfiguration(), this);
        endPoints.put(ref, socketTransportEndPoint);
        WebSocketTransportMessagesLogger.refCreated(ref.getFullName());
        return socketTransportEndPoint;
    }


    /**
     * Updates the existing endpoint with new configuration. The return value from
     * this method has to be a difference instance of TransportEndPoint object
     * than that which previously existed. The semantics are: prepare for update,
     * but do not update until activationComplete call has been received.
     *
     * @throws TransportException
     */
    public TransportEndPoint updateEndPoint(EndPointOperations.Update update) throws TransportException
    {
        if (!TransportManagerHelper.isRuntimeEnabled()) {
            return null;
        }

        Ref ref = update.getRef();
        WebSocketTransportEndPoint oldEp = endPoints.get(ref);
        /** oldEP can be null, when the socket transport is restarted and existing
         * configuration is updated.
         */
        if (oldEp != null) {
            update.getScratchPad().put(ref.getFullName() + UPDATE_OLD_ENDPOINT, oldEp);
        }

        endPoints.remove(ref);
        update.getScratchPad().put(ref.getFullName() + ENABLED, update.isEnabled());
        WebSocketTransportEndPoint endPoint = new WebSocketTransportEndPoint(ref, update.getEndPointConfiguration(), this);
        endPoints.put(ref, endPoint);
        return endPoint;
    }

    /**
     * Suspends (disables) the endpoint with the given service reference
     */
    public void suspendEndPoint(EndPointOperations.Suspend suspend)
            throws TransportException {
    }

    /**
     * Resumes (Re-enables) a previously suspended endpoint with the given service
     * reference
     */
    public void resumeEndPoint(EndPointOperations.Resume resume)
            throws TransportException {
    }


    /**
     * Delete an endpoint associated with the given service reference
     */
    public void deleteEndPoint(EndPointOperations.Delete delete) throws TransportException
    {
        if (!TransportManagerHelper.isRuntimeEnabled()) {
            return;
        }
        Ref ref = delete.getRef();
        WebSocketTransportEndPoint transportEndPoint = endPoints.remove(ref);
        delete.getScratchPad().put(ref.getFullName() + DELETE_ENDPOINT, transportEndPoint);
    }

    /**
     * called once per every create/update/delete/suspend/resume call to signal
     * that the activate action has completed with respect to the corresponding
     * endpoint object Does not imply success or failure of the overall session
     * activation! The provider is not allowed to throw exceptions as there is no
     * way to recover at this point.
     *
     * @param context
     */
    public void activationComplete(EndPointOperations.CommonOperation context)
    {
        Ref ref = context.getRef();
        EndPointOperations.EndPointOperationTypeEnum type = context.getType();
        WebSocketTransportEndPoint endPoint = endPoints.get(ref);

        if (!TransportManagerHelper.isRuntimeEnabled()) {
            return;
        }

        try {
            if (EndPointOperations.EndPointOperationTypeEnum.CREATE.equals(type)) {
                if ((Boolean) context.getScratchPad().get(ref.getFullName() + ENABLED)) {
                    endPoint.start();
                }
            } else if (EndPointOperations.EndPointOperationTypeEnum.UPDATE.equals(type)) {
                WebSocketTransportEndPoint oldEP = (WebSocketTransportEndPoint)context.getScratchPad().get(ref.getFullName() + UPDATE_OLD_ENDPOINT);
                if (oldEP != null) {
                    oldEP.stop();
                }
                if ((Boolean) context.getScratchPad().get(ref.getFullName() + ENABLED)) {
                    endPoint.start();
                }
            } else if (EndPointOperations.EndPointOperationTypeEnum.DELETE.equals(type)) {
                WebSocketTransportEndPoint oldEP = (WebSocketTransportEndPoint)context.getScratchPad().get(ref.getFullName() + DELETE_ENDPOINT);
                if (oldEP != null) {
                    oldEP.stop();
                    WebSocketTransportMessagesLogger.refDeleted(ref.getFullName());
                } else {
                    WebSocketTransportMessagesLogger.deleteFailed(ref.getFullName());
                }
            } else if (EndPointOperations.EndPointOperationTypeEnum.RESUME.equals(type)) {
                if (endPoint != null) {
                    endPoint.resume();
                    WebSocketTransportMessagesLogger.refResumed(ref.getFullName());
                } else {
                    WebSocketTransportMessagesLogger.resumeFailed(ref.getFullName());
                }
            } else if (EndPointOperations.EndPointOperationTypeEnum.SUSPEND.equals(type)) {
                if (endPoint != null) {
                    endPoint.suspend();
                    WebSocketTransportMessagesLogger.refSuspended(ref.getFullName());
                } else {
                    WebSocketTransportMessagesLogger.suspendFailed(ref.getFullName());
                }
            }
            WebSocketTransportMessagesLogger.refActivated(ref.getFullName());
        } catch (Exception e) {
            String msg = WebSocketTransportMessagesLogger.activationFailedLoggable(ref.getFullName()).getMessage();
            WebSocketTransportUtil.logger.error(msg, e);
        }
    }

    /**
     * Return the list of all inbound and outbound endpoints for this provider
     */
    public Collection<? extends TransportEndPoint> getEndPoints() {
        return Collections.unmodifiableCollection(endPoints.values());
    }


    /**
     * return an endpoint with a given service reference
     */
    public TransportEndPoint getEndPoint(Ref ref) {
        return endPoints.get(ref);
    }

    /**
     * Sends an outbound message to an external service. The caller provides a
     * callback with is called when the response is received from an external
     * service. The semantics of the send operation are specific to the transport
     * implementation.
     *
     * @param sender   an instance of either ServiceTransportSender or
     *                 NoServiceTransportSender interface will be provided
     * @param listener a callback object the transport provider needs to invoke
     *                 asynchronously when the send operation is completed (for
     *                 one-way requests) or when the response has been received
     *                 (for request-response requests)
     * @param options  various options having to do with desired quality of
     *                 service, the mode, etc on the outbound request
     */
    public void sendMessageAsync(TransportSender sender,
                                 TransportSendListener listener,
                                 TransportOptions options) throws TransportException
    {
        /** whether the the other endpoint is inbound */
        boolean isInbound = false;

        if (sender instanceof ServiceTransportSender) {
            isInbound = ((ServiceTransportSender) sender).getEndPoint().isInbound();
        }

        if (!isInbound) {//other end point is an out-bound or none(NoServiceTransportSender).
            WebSocketOutboundMessageContext ctx = new WebSocketOutboundMessageContext(sender, options);
            ctx.send(listener);
        } else { // other endpoint is an inbound.
            WebSocketCoLocatedMessageContext ctx = new WebSocketCoLocatedMessageContext((ServiceTransportSender) sender, options);
            ctx.send(listener);
        }
    }


    /**
     * @return the XML schema type for the endpoint configuration for this
     *         provider
     */
    public SchemaType getEndPointConfigurationSchemaType() {
        return WebSocketEndpointConfiguration.type;
    }


    /**
     * @return the XML schema type of the request message for this provider
     */
    public SchemaType getRequestMetaDataSchemaType() {
        return WebSocketRequestMetaDataXML.type;
    }

    /**
     * @return the XML schema type of the request headers for this provider. If
     *         provider does not support request headers, return null.
     */
    public SchemaType getRequestHeadersSchemaType() {
        return WebSocketRequestHeadersXML.type;
    }

    /**
     * @return the XML schema type of the response message for this provider
     */
    public SchemaType getResponseMetaDataSchemaType() {
        return WebSocketResponseMetaDataXML.type;
    }


    /**
     * @return the XML schema type of the response headers for this provider. If
     *         provider does not support response headers, return null.
     */
    public SchemaType getResponseHeadersSchemaType() {
        return WebSocketResponseHeadersXML.type;
    }

    /**
     * @return the XML document for the static properties for this provider
     * @throws TransportException
     */
    public TransportProviderConfiguration getProviderConfiguration()
            throws TransportException {
        try {
            URL configUrl =
                    this.getClass().getClassLoader().getResource("WebSocketConfig.xml");
            XmlOptions options = new XmlOptions().setLoadLineNumbers();
            TransportProviderConfiguration providerConfiguration =
                    ProviderConfigurationDocument.Factory.parse(configUrl, options)
                            .getProviderConfiguration();

            XmlOptions validateOptions = new XmlOptions();
            ArrayList<XmlError> errorList = new ArrayList<XmlError>();
            validateOptions.setErrorListener(errorList);

            boolean valid = providerConfiguration.validate(validateOptions);
            if (!valid) {
                StringBuilder sb =
                        new StringBuilder(WebSocketTransportMessagesLogger
                                .invalidConfigMsgLoggable().getMessage());
                sb.append("\n");
                for (XmlError error : errorList) {
                    sb.append(WebSocketTransportMessagesLogger.buildErrorMsgLoggable(
                            error.getLine() + "", error.getColumn() + "",
                            error.getMessage()).getMessage()).append("\n");
                }
                throw new TransportException(sb.toString());
            }

            return providerConfiguration;
        } catch (Exception e) {
            WebSocketTransportUtil.logger.error(e.getLocalizedMessage(), e);
            if (e instanceof TransportException) {
                throw (TransportException) e;
            } else {
                throw new TransportException(e);
            }
        }
    }


    /**
     * Called at service definition time to get the provider-specific binding
     * object that validates provider-specific properties are present in the UI
     * context. The user interface will pass in a brand new instance of
     * TransportUIContext object for every time the user navigates the wizard. A
     * typical pattern for the provider is create a new instance of the
     * TransportUIBinding object and save the reference to the context and refer
     * to it as needed.
     */
    public TransportUIBinding getUIBinding(TransportUIContext context) {
        return new WebSocketTransportUIBinding(context);
    }

    /**
     * Called by the TransportManager when the server is shutting down
     */
    public void shutdown() {
        for (WebSocketTransportEndPoint endPoint : endPoints.values()) {
            endPoint.stop();
        }

        OsbCookbookServletContext.getInstance().stopAndReset();
    }


    /**
     * @return an empty List.
     */
    public Collection<NonQualifiedEnvValue> getEnvValues(Ref ref,
                                                         EndPointConfiguration epConfig) {
        return Collections.emptyList();
    }

    public void setEnvValues(Ref ref, EndPointConfiguration epConfig,
                             Collection<NonQualifiedEnvValue> envValues) {
    }

    /**
     * @return an empty List.
     */
    public Collection<Ref> getExternalReferences(EndPointConfiguration epConfig) {
        return Collections.emptyList();
    }

    public void setExternalReferences(Map<Ref, Ref> mapRefs,
                                      EndPointConfiguration epConfig) {
    }


    /**
     * Given a proxy service reference returns a map of string properties that
     * contains name/value pairs which are all the necessary provider-specific
     * attributes for a business service object to be instantiated (on a different
     * ALSB domain) that can invoke this proxy service. All the fields that are
     * exposed will be externalized by the transport provider (for e.g. replace
     * occurrences of localhost with actual server name etc.). If the transport
     * provider needs a specific business service URI which is different from a
     * proxy URI,  the properties object they return should contain a property
     * with a key "URI" and a string value to be used when creating the business
     * service. Only transport providers that support both proxy AND business
     * services (i.e. inbound AND outbound directions) need to support this.
     * Otherwise they can throw an UnsupportedOperationException. This is used in
     * UDDI import/export feature of ASLB.
     *
     * @param ref
     * @return a map of string properties that
     *         contains name/value pairs which are all the necessary provider-specific
     *         attributes for a business service object to be instantiated.
     */
    public Map<String, String> getBusinessServicePropertiesForProxy(Ref ref)
            throws TransportException {
        Map<String, String> props = new HashMap<String, String>();
        WebSocketTransportEndPoint endPoint = endPoints.get(ref);
        String uriVal = endPoint.getURI()[0].toString();
        try {
            props.put(URI, WebSocketTransportUtil.toPublicServiceURI(uriVal));
        } catch (URISyntaxException e) {
            throw new TransportException(e, e.getMessage());
        }
        return props;
    }

    /**
     * Given a map of properties object from one ALSB domain returns a transport
     * endpoint configuration that can be used to instantiate a business service
     * on another ALSB domain. Only transport providers that support both proxy
     * AND business services (i.e. inbound AND outbound directions) need to
     * support this. Otherwise they can throw an UnsupportedOperationException.
     * This is used in UDDI import/export feature of ASLB.
     *
     * @param ref   if not null, it is assumed that there already exists a service
     *              endpoint with a given ref, and the result of the method will
     *              be a merge of existing configuration and passed in
     *              properties.
     * @param props
     * @return returns a transport endpoint configuration
     */

    public XmlObject getProviderSpecificConfiguration(Ref ref,
                                                      Map<String, String> props)
            throws TransportException {
        WebSocketEndpointConfiguration sockEPConfig = null;
        if (ref != null) {
            WebSocketTransportEndPoint endPoint =
                    (WebSocketTransportEndPoint) getEndPoint(ref);
            if (endPoint == null) {
                throw new TransportException(
                        WebSocketTransportMessagesLogger.noEndPoint(ref.getFullName()));
            }
            sockEPConfig = endPoint.getWebSocketEndpointConfiguration();
        } else {
            sockEPConfig = WebSocketEndpointConfiguration.Factory.newInstance();
        }

        String uri = props.get(URI);
        String newUri = null;
        try {
            newUri = new URI(uri).getPort() + "";
        } catch (URISyntaxException e) {
            throw TransportException.newInstance(e);
        }
        props.put(URI, newUri);
        return sockEPConfig;
    }

    public String getIdentifierURI(EndPointConfiguration endpoint,
                                   BindingTypeInfo.BindingTypeEnum bindingType,
                                   Ref serviceRef) {
        return bindingProvider.getIdentifierURI(endpoint, bindingType, serviceRef);
    }

    public List<String> getEndPointAddress(EndPointConfiguration endpoint,
                                           BindingTypeInfo.BindingTypeEnum bindingType,
                                           Ref serviceRef) {
        return bindingProvider.getEndPointAddress(endpoint, bindingType, serviceRef);
    }

}
