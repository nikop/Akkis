package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Customer;
import akkis.Product;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class ProductController {

	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{product}")
	private Product product;

	
	public ProductController() {
		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String saveProduct() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Product pd = (Product) facesContext.getExternalContext().getRequestMap().get("product");
		System.out.println("Product:" + pd);
		tuoteEjb.save(pd);
		
		FacesMessages.info("Successfully saved.");
				
		return null;
	}
	
	public String saveProduct(Product product) {
		//tuoteEjb.save(customer);
		
		tuoteEjb.saveChanges(product);
		
		FacesMessages.info("Successfully saved.");
		
		return "product?faces-redirect=true";
	}

	public List<Product> getProducts() {
		return tuoteEjb.getProducts();
	}

}
