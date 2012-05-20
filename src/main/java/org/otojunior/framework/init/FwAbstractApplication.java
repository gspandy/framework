package org.otojunior.framework.init;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.otojunior.framework.security.UserContext;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

/**
 * @author otojunior
 */
public abstract class FwAbstractApplication extends Application implements HttpServletRequestListener {
	private static final long serialVersionUID = 5706181071228373990L;

	private UserContext userContext;

	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		if (getUser() == null) {
			Principal userPrincipal = request.getUserPrincipal();
			userContext = getNewUserContext();
			userContext.setUserPrincipal(userPrincipal);
			userContext.complete();
			setUser(userContext);
		}
	}

	protected abstract UserContext getNewUserContext();


	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
	}
}
