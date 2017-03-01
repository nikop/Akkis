package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Customer;
import akkis.Product;
import net.bootsfaces.utils.FacesMessages;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

@ManagedBean
public class CustomerController {

	@EJB
	private AkkisEjb ejb;
	
	@ManagedProperty(value = "#{customer}")
	private Customer customer;
	
	public CustomerController() {
		
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String saveCustomer(Customer customer) {
		ejb.save(customer);
		
		Akkis.info("Customer created");
		
		return "/customers/index?faces-redirect=true";
	}
	
	public String updateCustomer(Customer customer) {
		ejb.update(customer);
		
		Akkis.info("Customer updated");
		
		return "/customers/index?faces-redirect=true";
	}

	public List<Customer> getCustomers() {
		return ejb.getCustomers();
	}

}
