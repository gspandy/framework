package org.otojunior.framework.security;

import java.io.Serializable;
import java.security.Principal;


/**
 * User context. For user data information associed with application.
 * @author otojunior
 */
public interface UserContext extends Serializable {
	/**
	 * Get login of user.
	 * @return Login name.
	 */
	public String getUserLogin();

	/**
	 * Set user principal.
	 * @param userPrincipal User principal.
	 */
	public void setUserPrincipal(Principal userPrincipal);
	
	/**
	 * Set user principal.
	 * @param userPrincipal User principal.
	 */
	public Principal getUserPrincipal();

	/**
	 * Complete the context with other specific data.
	 */
	public void complete();
}

