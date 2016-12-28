package akkis.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;

import akkis.User;
import akkis.types.Permission;
import akkis.types.Role;

@ManagedBean
@SessionScoped
public class LoginUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private String username;
	
	private String password;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String user) {
		this.username = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLogged() {
		return user != null;
	}
	
	public boolean can(Permission permission)
	{
		if (!isLogged())
			return false;
		
		if (user.hasRole(Role.ADMIN))
			return true;
		
		switch (permission) {
			case VIEW_CUSTOMERS:
				return user.hasRole(Role.SELLER);
			default:
				return false;
		}
	}
}
