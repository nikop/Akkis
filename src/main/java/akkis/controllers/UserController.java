package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Company;
import akkis.Customer;
import akkis.Product;
import akkis.User;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class UserController {

	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{user}")
	private User user;
	
	public UserController() {
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String saveUser(User user) {
		tuoteEjb.save(user);
		
		FacesMessages.info("New User created");
		
		return "users?faces-redirect=true";		
	}
	
	public String updateUser(User user) {		
		tuoteEjb.saveChanges(user);
		
		FacesMessages.info("Successfully saved.");
		
		return "users?faces-redirect=true";
	}

	public List<User> getUsers() {
		return tuoteEjb.getUsers();
	}

}
