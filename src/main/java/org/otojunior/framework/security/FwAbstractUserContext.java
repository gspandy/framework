/**
 * 
 */
package org.otojunior.framework.security;

import java.security.Principal;

/**
 * @author otojunior
 *
 */
public abstract class FwAbstractUserContext implements UserContext {
	private static final long serialVersionUID = -3608046049154304943L;

	private Principal userPrincipal;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Principal getUserPrincipal() {
		return userPrincipal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUserPrincipal(Principal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getUserLogin() {
		return userPrincipal.getName();
	}
	
	
}
