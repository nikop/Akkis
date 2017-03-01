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
	private AkkisEjb ejb;
	
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

	public String saveProduct(Product product) {
		
		Akkis.info("Product created");
		
		ejb.save(product);
		
		return "/products/index?faces-redirect=true";
	}
	
	public String updateProduct(Product product) {		
		ejb.update(product);
		
		Akkis.info("Product updated");
		
		return "/products/index?faces-redirect=true";
	}

	public List<Product> getProducts() {
		return ejb.getProducts();
	}

}
