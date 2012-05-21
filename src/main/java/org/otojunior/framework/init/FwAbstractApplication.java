package org.otojunior.framework.init;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		if (getUser() != null) {
			UserContext userContext = (UserContext)getUser();
			userContext.setRequest(null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		UserContext userContext = null;
		if (getUser() == null) {
			userContext = getNewUserContext();
			userContext.complete();
			userContext.setRequest(request);
			setUser(userContext);
		} else {
			userContext = (UserContext)getUser();
			userContext.setRequest(request);
		}
	}

	/**
	 * Creates a new User context. Must be overrided.
	 * @return {@link UserContext}
	 */
	protected abstract UserContext getNewUserContext();
}
