package org.otojunior.framework.security;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;


/**
 * User context. For user data information associed with application.
 * @author otojunior
 */
public interface UserContext extends Serializable {
	/**
	 * Complete the context with other specific data.
	 */
	public void complete();

	/**
	 * Obtains the request
	 * @param request
	 */
	public HttpServletRequest getRequest();
	
	/**
	 * Stores current request in context
	 * @param request
	 */
	public void setRequest(HttpServletRequest request);
}

