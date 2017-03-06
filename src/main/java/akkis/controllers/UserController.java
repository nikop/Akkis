package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Company;
import akkis.Customer;
import akkis.Product;
import akkis.StringUtilities;
import akkis.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class UserController {

	@EJB
	private AkkisEjb ejb;
	
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
		ejb.save(user);
		
		Akkis.info("New User created");
		
		return "/users/index?faces-redirect=true";		
	}
	
	public String updateUser(User user) {		
		ejb.update(user);
		
		Akkis.info("Successfully saved.");
		
		return "/users/index?faces-redirect=true";
	}
	
	public String resetPassword(User user) {
		
		String newPassword = StringUtilities.getRandomString(new Random(), 4, 12);
		
		user.setPassword(newPassword);
		
		ejb.update(user);
		
		Akkis.info(String.format("User %s new password is %s", user.getUsername(), newPassword));
		
		return "/users/index?faces-redirect=true";
	}

	public List<User> getUsers() {
		return ejb.getUsers();
	}

	public List<Customer> getCustomers() {
		return ejb.getCustomers(user);
	}
}
