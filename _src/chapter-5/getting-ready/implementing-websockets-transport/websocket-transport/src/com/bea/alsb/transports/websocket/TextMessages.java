package com.bea.alsb.transports.websocket;

import weblogic.i18n.Localizer;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.HashMap;

/**
 * It contains messages used by {@link WebSocketTransportUIBinding}. It has an API
 * to get localized messages.
 * <p/>
 * These are mapped in /l10n/WebSocketTransportTextMessages.xml
 * <p/>
 * Messages can be added here and map it in the above mapping xml.
 * More information can be found at http://edocs.bea.com/wls/docs91/i18n/MssgCats.html
 */
public class TextMessages {
    public static final String SERVER_PORT = "SERVER_PORT";
    public static final String SERVER_PORT_INFO = "SERVER_PORT_INFO";
    public static final String SERVER_CONTEXT_PATH = "SERVER_CONTEXT_PATH";
    public static final String SERVER_CONTEXT_PATH_INFO = "SERVER_CONTEXT_PATH_INFO";
    public static final String STATIC_FILES_DIR = "STATIC_FILES_DIR";
    public static final String STATIC_FILES_DIR_INFO = "STATIC_FILES_DIR_INFO";
    public static final String SERVLET_CLASS = "SERVLET_CLASS";
    public static final String SERVLET_CLASS_INFO = "SERVLET_CLASS_INFO";
    public static final String DISPATCH_POLICY = "DISPATCH_POLICY";
    public static final String DISPATCH_POLICY_INFO = "DISPATCH_POLICY_INFO";

    private static final String propsClazz =
            "com.bea.alsb.transports.websocket.WebSocketTransportTextMessagesTextLocalizer";
    static HashMap<Locale, Localizer> resourceBundleMap =
            new HashMap<Locale, Localizer>();

    public static String getMessage(String id, Locale locale) {
        Localizer localizer = resourceBundleMap.get(locale);
        if (localizer == null) {
            ResourceBundle resourceBundle =
                    ResourceBundle.getBundle(propsClazz, locale);
            localizer = new Localizer(resourceBundle);
            resourceBundleMap.put(locale, localizer);
        }
        return localizer.get(id);
    }

    public static String getMessage(String id) {
        return getMessage(id, Locale.getDefault());
    }
}
