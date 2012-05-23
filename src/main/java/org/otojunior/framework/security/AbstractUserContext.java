package org.otojunior.framework.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author otojunior
 *
 */
public abstract class AbstractUserContext implements UserContext {
	private static final long serialVersionUID = -3608046049154304943L;

	private HttpServletRequest request;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Convenience method that obtains the user login.
	 * @return the user login.
	 */
	public String getUserLogin() {
		Principal userPrincipal = getRequest().getUserPrincipal();
		return (userPrincipal != null) ? userPrincipal.getName() : null;
	}
	
	/**
	 * Convenience method for verify if a user has the role permission.
	 * @param rolename Role name.
	 * @return <code>true</code> if user has the role, <code>false</code> otherwise.
	 */
	public boolean isUserInRole(String rolename) {
		return request.isUserInRole(rolename);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
