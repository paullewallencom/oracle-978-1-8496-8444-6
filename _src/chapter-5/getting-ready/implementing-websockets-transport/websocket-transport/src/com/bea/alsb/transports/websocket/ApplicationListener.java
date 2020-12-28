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
import com.bea.wli.sb.transports.TransportManager;
import com.bea.wli.sb.transports.TransportManagerHelper;
import weblogic.application.ApplicationLifecycleEvent;
import weblogic.application.ApplicationLifecycleListener;
import weblogic.application.ApplicationException;
import weblogic.security.Security;
import weblogic.security.SubjectUtils;

import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedActionException;

/**
 * This class provides callbacks for deployment events.
 */
public class ApplicationListener extends ApplicationLifecycleListener {

    /**
     * After an application is initialized, this method is invoked by the Deploy
     * framework. Socket transport is registered with TransportManager.
     *
     * @param evt
     */
    public void preStart(ApplicationLifecycleEvent evt) throws ApplicationException {
        try {
            Security.runAs(SubjectUtils.getAnonymousUser(),
                    new PrivilegedExceptionAction() {
                        public Object run() throws Exception {
                            TransportManager man = TransportManagerHelper.getTransportManager();
                            WebSocketTransportProvider instance = WebSocketTransportProvider.getInstance();
                            man.registerProvider(instance, null);
                            WebSocketTransportMessagesLogger.registeredSuccessfully();
                            return null;
                        }
                    });
        } catch (PrivilegedActionException pae) {
            Exception e = pae.getException();
            WebSocketTransportUtil.logger.error(WebSocketTransportMessagesLogger.registrationFailedLoggable().getMessage(), e);
        }
    }

    /**
     * This method is invoked by Deploy framework when an application is commenced
     * to shutdown.
     *
     * @param evt
     */
    public void preStop(ApplicationLifecycleEvent evt) {
        /** TransportManager does not have unregisterProvider method.
         * Whenever a transport needs to be refreshed,
         * server should get offline and the transport can be deployed.
         * This restrictioin is made by the Transport SDK.
         */
    }
}
