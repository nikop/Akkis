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
public class LoginUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AkkisEjb ejb;
	
	@ManagedProperty(value="#{akkisSession}")
	private AkkisSession session;	
	
	private String username;
	
	private String password;

	public AkkisSession getSession() {
		return session;
	}

	public void setSession(AkkisSession session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
