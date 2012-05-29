package org.otojunior.framework.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.otojunior.framework.security.UserContext;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

/**
 * @author otojunior
 */
public abstract class AbstractApplication extends Application implements HttpServletRequestListener {
	private static final long serialVersionUID = 5706181071228373990L;

	/**
	 * {@inheritDoc}
	 * May be overrided in subclasses.
	 */
	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		// May be overrided in subclasses.
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
			userContext.setRequest(request);
			userContext.complete();
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
	
	@Override
	public void close() {
		super.close();
		UserContext userContext = (UserContext)getUser();
		HttpSession session = userContext.getRequest().getSession();
		if (!session.isNew())
			session.invalidate();
	}
}
