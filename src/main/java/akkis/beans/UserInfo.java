package akkis.beans;

import java.beans.Transient;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;

import akkis.AkkisEjb;
import akkis.User;
import akkis.types.Permission;
import akkis.types.Role;

@ManagedBean
@RequestScoped
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AkkisEjb ejb;
	
	@ManagedProperty(value="#{akkisSession}")
	private AkkisSession session;	
	
	private User user;
	
	public User getUser() {
		
		if (session == null)
			return null;
		
		if (user != null)
			return user;
		
		if (session.getUsername() != null && session.getPassword_hash() != null)
		{
			user = ejb.getUser(session.getUsername());
				
			if (user != null && !session.getPassword_hash().equals(user.getPasswordHashed()))
			{
				user = null;
				session.setPassword_hash(null);
			}
			
			return user;
		}
		
		return user;
	}

	public AkkisSession getSession() {
		return session;
	}



	public void setSession(AkkisSession session) {
		this.session = session;
	}

	public boolean isLogged() {
		return getUser() != null;
	}
	
	public boolean can(String role)
	{
		return can(Role.valueOf(role));
	}
	
	public boolean can(Role role)
	{
		if (!isLogged())
			return false;
		
		return user.hasRole(Role.ADMIN) || user.hasRole(role);
	}

	public void login(User newUser) {
	
		session.setUsername(newUser.getUsername());
		session.setPassword_hash(newUser.getPasswordHashed());
		
	}
	
	public void logout()
	{
		session.setUsername(null);
		session.setPassword_hash(null);
		user = null;
	}
}
