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

import com.bea.alsb.presentation.CustomHelpProvider;

import com.bea.wli.sb.services.BindingTypeInfo;
import com.bea.wli.sb.transports.EndPointConfiguration;
import com.bea.wli.sb.transports.TransportException;
import com.bea.wli.sb.transports.TransportManagerHelper;
import com.bea.wli.sb.transports.TransportValidationContext;
import com.bea.wli.sb.transports.ui.TransportEditField;
import com.bea.wli.sb.transports.ui.TransportUIBinding;
import com.bea.wli.sb.transports.ui.TransportUIContext;
import com.bea.wli.sb.transports.ui.TransportUIError;
import com.bea.wli.sb.transports.ui.TransportUIFactory;

import static com.bea.alsb.transports.websocket.WebSocketTransportUtil.logger;
import static com.bea.wli.sb.transports.ui.TransportUIFactory.getIntValue;
import static com.bea.wli.sb.transports.ui.TransportUIFactory.getStringValue;
import static com.bea.wli.sb.transports.ui.TransportUIFactory.getStringValues;

import com.bea.wli.sb.transports.ui.TransportUIGenericInfo;
import com.bea.wli.sb.transports.ui.TransportViewField;
import org.apache.xmlbeans.XmlObject;

import javax.management.remote.JMXConnector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;

import weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean;

/**
 * This class represents the binding between UI and the metdata of the
 * transport. It provides UI validation and rendering of the transport provider
 * specific objects.
 */
public class WebSocketTransportUIBinding
        implements TransportUIBinding, CustomHelpProvider {
    private TransportUIContext uiContext;
    private static final String SERVER_PORT         = "WebSocket Server Port";
    private static final String SERVER_CONTEXT_PATH = "WebSocket Server Context Path";
    private static final String STATIC_FILES_DIR    = "WebApp Static Files Directory";
    private static final String SERVLET_CLASS       = "WebSocket Servlet Class Name";
    private static final String DISPATCH_POLICY     = "Dispatch policy";

    public static final String DEFAULT_WORK_MANAGER = "default";

    private Locale locale;

    public WebSocketTransportUIBinding(TransportUIContext uiContext) {
        this.uiContext = uiContext;
        locale = uiContext.getLocale();
    }

    /**
     * Returns true if the message type is either TEXT or XML. Socket transport
     * supports XML and TEXT message types only for both the request and the
     * response messages.
     *
     * @param bindingType
     * @return
     */
    public boolean isServiceTypeSupported(BindingTypeInfo bindingType) {
        try {
            BindingTypeInfo.BindingTypeEnum type = bindingType.getType();

            /**
             * If the binding is mixed, request type should exist and it should be
             * either TEXT or XML type and  if there is any response type,
             * it must be either TEXT or XML.
             */
            if (type.equals(BindingTypeInfo.BindingTypeEnum.MIXED)) {
                BindingTypeInfo.MessageTypeEnum responseMessageType =
                        bindingType.getResponseMessageType();
                if (responseMessageType != null) {
                    if (!(BindingTypeInfo.MessageTypeEnum.TEXT.equals(responseMessageType) ||
                            BindingTypeInfo.MessageTypeEnum.XML.equals(responseMessageType))) {
                        return false;
                    }
                }
                BindingTypeInfo.MessageTypeEnum requestMessageType =
                        bindingType.getRequestMessageType();
                return requestMessageType != null &&
                        (BindingTypeInfo.MessageTypeEnum.TEXT.equals(requestMessageType) ||
                         BindingTypeInfo.MessageTypeEnum.XML.equals(requestMessageType));
            }
            /**
             * Binding type must be either ABSTRACT_XML or XML.
             */
            return type.equals(BindingTypeInfo.BindingTypeEnum.ABSTRACT_XML)
                    || type.equals(BindingTypeInfo.BindingTypeEnum.XML);
        } catch (TransportException e) {
            logger.error(e.getLocalizedMessage(), e);
            return false;
        }
    }


    /**
     * Called at service definition time to provide information that is provider
     * specific for the main transport page. This includes information like URI
     * hints and autofill field data.
     *
     * @return
     */
    public TransportUIGenericInfo getGenericInfo() {
        TransportUIGenericInfo genInfo = new TransportUIGenericInfo();
        genInfo.setUriFormat("/servlet_path");
        genInfo.setUriAutofill("/servlet_path");
        return genInfo;
    }


    /**
     * Called at service definition time to get provider-specific contents of the
     * edit pane for endpoint configuration. This method is called when the
     * transport configuration page is first rendered.
     */
    public TransportEditField[] getEditPage(EndPointConfiguration config,
                                            BindingTypeInfo binding)
            throws TransportException {
        List<TransportEditField> fields = new ArrayList<TransportEditField>();
        WebSocketEndpointConfiguration sockConfig = null;
        if (config != null && config.isSetProviderSpecific()) {
            sockConfig = WebSocketTransportUtil.getConfig(config);
        }

        long serverPort = 8080;
        String contextPath = "/";
        String staticFilesDir = ".";
        String servletClass = "osb.cookbook.websocket.servlet.PassThroughServlet";


        if (uiContext.isProxy()) {
            if (sockConfig != null) {
                WebSocketInboundPropertiesType inboundProps = sockConfig.getInboundProperties();
                serverPort      = inboundProps.getServerPort();
                contextPath     = inboundProps.getServerContextPath();
                staticFilesDir  = inboundProps.getStaticFilesDir();
                servletClass    = inboundProps.getServletClass();
            }
        } else {
            if (sockConfig != null) {
                WebSocketOutboundPropertiesType outboundProps = sockConfig.getOutboundProperties();
                serverPort      = outboundProps.getServerPort();
                contextPath     = outboundProps.getServerContextPath();
                staticFilesDir  = outboundProps.getStaticFilesDir();
                servletClass    = outboundProps.getServletClass();
            }
        }

        TransportUIFactory.TextBoxObject textBox =
                TransportUIFactory.createTextBox(String.valueOf(serverPort), 20);
        TransportEditField editField = TransportUIFactory.createEditField(SERVER_PORT,
                TextMessages.getMessage(TextMessages.SERVER_PORT, locale),
                TextMessages.getMessage(TextMessages.SERVER_PORT_INFO, locale), false,
                textBox);
        fields.add(editField);

        textBox = TransportUIFactory.createTextBox(staticFilesDir, 20);
        editField = TransportUIFactory.createEditField(STATIC_FILES_DIR,
                TextMessages.getMessage(TextMessages.STATIC_FILES_DIR, locale),
                TextMessages.getMessage(TextMessages.STATIC_FILES_DIR_INFO, locale), false,
                textBox);
        fields.add(editField);

        textBox = TransportUIFactory.createTextBox(contextPath, 20);
        editField = TransportUIFactory.createEditField(SERVER_CONTEXT_PATH,
                        TextMessages.getMessage(TextMessages.SERVER_CONTEXT_PATH, locale),
                        TextMessages.getMessage(TextMessages.SERVER_CONTEXT_PATH_INFO, locale), false,
                        textBox);
        fields.add(editField);

        textBox = TransportUIFactory.createTextBox(servletClass, 20);
        editField = TransportUIFactory.createEditField(SERVLET_CLASS,
                TextMessages.getMessage(TextMessages.SERVLET_CLASS, locale),
                TextMessages.getMessage(TextMessages.SERVLET_CLASS_INFO, locale), false,
                textBox);
        fields.add(editField);



        String curDispatchPolicy = DEFAULT_WORK_MANAGER;
        if (sockConfig != null && sockConfig.getDispatchPolicy() != null) {
            curDispatchPolicy = sockConfig.getDispatchPolicy();
        }
        if (curDispatchPolicy == null) {
            curDispatchPolicy = DEFAULT_WORK_MANAGER;
        }
        TransportEditField field = getDispatchPolicyEditField(curDispatchPolicy);
        fields.add(field);

        return fields.toArray(new TransportEditField[fields.size()]);
    }


    /**
     * Called at service definition time to get contents of the edit pane for
     * endpoint configuration. This method is called each time the event for the
     * field of the given name is triggered. The set of field can be updated
     * accordingly.
     */
    public TransportEditField[] updateEditPage(TransportEditField[] fields,
                                               String name)
            throws TransportException {
        return fields;
    }


    /**
     * Called at the time the service details are viewed in read-only mode to get
     * the contents of the summary pane for endpoint configuration
     */
    public TransportViewField[] getViewPage(EndPointConfiguration config) throws TransportException
    {
        List<TransportViewField> fields = new ArrayList<TransportViewField>();
        WebSocketEndpointConfiguration socketEndpointConfiguration = WebSocketTransportUtil.getConfig(config);
        TransportViewField field;

        long serverPort;
        String contextPath;
        String staticFilesDir;
        String servletClass;

        if (uiContext.isProxy()) {
            WebSocketInboundPropertiesType inboundProps =
                    socketEndpointConfiguration.getInboundProperties();

            serverPort      = inboundProps.getServerPort();
            contextPath     = inboundProps.getServerContextPath();
            staticFilesDir  = inboundProps.getStaticFilesDir();
            servletClass    = inboundProps.getServletClass();

        } else {
            WebSocketOutboundPropertiesType outboundProps =
                    socketEndpointConfiguration.getOutboundProperties();

            serverPort      = outboundProps.getServerPort();
            contextPath     = outboundProps.getServerContextPath();
            staticFilesDir  = outboundProps.getStaticFilesDir();
            servletClass    = outboundProps.getServletClass();
        }

        field = new TransportViewField(SERVER_PORT,
                TextMessages.getMessage(TextMessages.SERVER_PORT, locale),
                serverPort);
        fields.add(field);

        field = new TransportViewField(SERVER_CONTEXT_PATH,
                TextMessages.getMessage(TextMessages.SERVER_CONTEXT_PATH, locale),
                contextPath);
        fields.add(field);

        field = new TransportViewField(STATIC_FILES_DIR,
                TextMessages.getMessage(TextMessages.STATIC_FILES_DIR, locale),
                staticFilesDir);
        fields.add(field);

        field = new TransportViewField(SERVLET_CLASS,
                TextMessages.getMessage(TextMessages.SERVLET_CLASS, locale),
                servletClass);
        fields.add(field);

        String dispatchPolicy = socketEndpointConfiguration.getDispatchPolicy();
        if (dispatchPolicy == null) {
            dispatchPolicy = DEFAULT_WORK_MANAGER;
        }
        field = new TransportViewField(DISPATCH_POLICY,
                TextMessages.getMessage(TextMessages.DISPATCH_POLICY, locale),
                dispatchPolicy
        );
        fields.add(field);

        return fields.toArray(new TransportViewField[fields.size()]);
    }



    /**
     * Builds the disptahc policies in the ui object.
     *
     * @param curDispatchPolicy
     * @return TransportEditField containing existing dispatch policies.
     */
    public TransportEditField getDispatchPolicyEditField(
            String curDispatchPolicy) {

        TransportUIFactory.TransportUIObject uiObject = null;
        Set<String> wmSet = null;
        JMXConnector jmxConnector = null;

        if (TransportManagerHelper.isOffline()) {
            jmxConnector =
                    (JMXConnector) uiContext.get(TransportValidationContext.JMXCONNECTOR);
        } else {
            try {
                jmxConnector = WebSocketTransportUtil.getServerSideConnection(
                        DomainRuntimeServiceMBean.MBEANSERVER_JNDI_NAME);
            } catch (Exception e) {
                logger
                        .error(WebSocketTransportMessagesLogger.noJmxConnectorAvailble(), e);
            }
        }
        try {
            if (jmxConnector != null) {
                wmSet = TransportManagerHelper.getDispatchPolicies(jmxConnector);
            } else {
                wmSet = TransportManagerHelper.getDispatchPolicies();
            }
        } catch (Exception ex) {
            wmSet = null; //continue
            logger
                    .error(WebSocketTransportMessagesLogger.noDispatchPolicies(), ex);
        }


        if (wmSet == null) {
            // if JMXConnector not available or impossible to connect provide a simple edit field
            uiObject = TransportUIFactory.createTextBox(curDispatchPolicy);
        } else {
            // create a drop down list
            // adding default work manager to the list.
            wmSet.add(DEFAULT_WORK_MANAGER);

            String[] values = wmSet.toArray(new String[wmSet.size()]);
            uiObject = TransportUIFactory.createSelectObject(
                    values,
                    values,
                    curDispatchPolicy,
                    TransportUIFactory.SelectObject.DISPLAY_LIST,
                    false);
        }

        return TransportUIFactory.createEditField(DISPATCH_POLICY,
                TextMessages.getMessage(TextMessages.DISPATCH_POLICY, locale),
                TextMessages.getMessage(TextMessages.DISPATCH_POLICY_INFO, locale),
                uiObject);
    }

    /**
     * Validates the main form of the transport by checking whether the configured
     * URIs are valid or not.
     *
     * @param fields
     * @return Returns an array of TransportUIError of the invalid URIs.
     */
    public TransportUIError[] validateMainForm(TransportEditField[] fields)
    {
        Map<String, TransportUIFactory.TransportUIObject> map = TransportEditField.getObjectMap(fields);

        List<TransportUIError> errors = new ArrayList<TransportUIError>();

        List<String[]> uris = getStringValues(map, TransportUIBinding.PARAM_URI);
        if (uris.size() > 1)
            errors.add(new TransportUIError(TransportUIBinding.PARAM_URI,"Only a single URI allowed"));
        else
            for (String[] uristr : uris) {
                String uri = uristr[0];
                if (uri == null || uri.length() == 0) {
                    errors.add(new TransportUIError(TransportUIBinding.PARAM_URI, "URI must not be empty"));
                }
            }

        return errors == null || errors.isEmpty() ? null :
                errors.toArray(new TransportUIError[errors.size()]);
    }

    /**
     * validate the provider-specific transport endpoint parameters in the
     * request.
     */
    public TransportUIError[] validateProviderSpecificForm(TransportEditField[] fields) {
        /** WebSocket transport configuration can be validated here. */
        Map<String, TransportUIFactory.TransportUIObject> map = TransportEditField.getObjectMap(fields);
        List<TransportUIError> errors = new ArrayList<TransportUIError>();

        int serverPort = getIntValue(map, SERVER_PORT);
        if (serverPort < 0 || serverPort > 65535) {
            errors.add(new TransportUIError(SERVER_PORT, "Invalid range"));
        }

        String contextPath = getStringValue(map, SERVER_CONTEXT_PATH);
        if (contextPath != null && contextPath.length() > 0 && !contextPath.matches("^/[\\w\\-]*$")) {
            errors.add(new TransportUIError(SERVER_CONTEXT_PATH, "Invalid format"));
        }

        String servletClass = getStringValue(map, SERVLET_CLASS);
        if (!servletClass.matches("^(([\\w])+\\.)*([\\w])+$")) {
            errors.add(new TransportUIError(SERVLET_CLASS, "Invalid class name format"));
        }

        return errors == null || errors.isEmpty() ? null :
                errors.toArray(new TransportUIError[errors.size()]);
    }


    /**
     * creates the Transport Provider Specific configuration from the UI form.
     * This method will be called only upon a successfull call to {@link
     * #validateMainForm} and {@link #validateProviderSpecificForm}
     */
    public XmlObject getProviderSpecificConfiguration(TransportEditField[] fields)
            throws TransportException {

        WebSocketEndpointConfiguration socketEndpointConfig = WebSocketEndpointConfiguration.Factory.newInstance();
        Map<String, TransportUIFactory.TransportUIObject> map = TransportEditField.getObjectMap(fields);

        if (uiContext.isProxy()) {
            WebSocketInboundPropertiesType inbPropsType = socketEndpointConfig.addNewInboundProperties();
            inbPropsType.setServerPort(TransportUIFactory.getIntValue(map, SERVER_PORT));
            inbPropsType.setServerContextPath(TransportUIFactory.getStringValue(map, SERVER_CONTEXT_PATH));
            inbPropsType.setStaticFilesDir(TransportUIFactory.getStringValue(map, STATIC_FILES_DIR));
            inbPropsType.setServletClass(TransportUIFactory.getStringValue(map, SERVLET_CLASS));
        } else {
            WebSocketOutboundPropertiesType outbPropsType = socketEndpointConfig.addNewOutboundProperties();
            outbPropsType.setServerPort(TransportUIFactory.getIntValue(map, SERVER_PORT));
            outbPropsType.setServerContextPath(TransportUIFactory.getStringValue(map, SERVER_CONTEXT_PATH));
            outbPropsType.setStaticFilesDir(TransportUIFactory.getStringValue(map, STATIC_FILES_DIR));
            outbPropsType.setServletClass(TransportUIFactory.getStringValue(map, SERVLET_CLASS));
        }

        String dispatchPolicy =TransportUIFactory.getStringValue(map, DISPATCH_POLICY);
        socketEndpointConfig.setDispatchPolicy(dispatchPolicy);

        return socketEndpointConfig;
    }

    public Reader getHelpPage() {
        String helpFile = "help/en/contexts_socketTransport.html";
        ClassLoader clLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = clLoader.getResourceAsStream(helpFile);
        InputStreamReader helpReader = null;
        if (is != null)
            helpReader = new InputStreamReader(is);
        else
            logger.warning(WebSocketTransportMessagesLogger.noHelpPageAvailableLoggable().
                    getMessage(uiContext.getLocale()));
        return helpReader;
    }

}
