package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Delivery;
import akkis.DeliveryProduct;
import akkis.Invoice;
import akkis.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class DeliveryController {

	@EJB
	private AkkisEjb ejb;

	@ManagedProperty(value = "#{delivery}")
	private Delivery delivery;

	public DeliveryController() {

	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String saveDelivery(Delivery delivery) {
		ejb.save(delivery);
		
		Akkis.info("Delivery created");
		
		return "/deliveries/show?id=" + delivery.getId() + "&faces-redirect=true";
	}
	
	public String updateDelivery(Delivery delivery) {
		ejb.update(delivery);
		
		Akkis.info("Delivery updated");
		
		return "/deliveries/show?id=" + delivery.getId() + "&faces-redirect=true";
	}
	
	public String addProductToDelivery(Product p) {
		return addProductToDelivery(delivery, new DeliveryProduct(p));
	}
	
	public String addProductToDelivery(DeliveryProduct p) {
		return addProductToDelivery(delivery, p);
	}
	
	public String addProductToDelivery(Delivery delivery, Product p) {
		return addProductToDelivery(delivery, new DeliveryProduct(p));
	}
	
	public String addProductToDelivery(Delivery delivery, DeliveryProduct dp) {
		
		delivery.addProduct(dp);
		
		ejb.update(delivery);
		
		Akkis.info("Product added to delivery");
		
		return "/deliveries/show?id=" + delivery.getId() + "&faces-redirect=true";
	}
	
	public String createInvoice()
	{
		return createInvoice(delivery);
	}

	public String createInvoice(Delivery delivery)
	{
	
		Invoice invoice = delivery.createInvoice();
		ejb.update(delivery);
		
		Akkis.info("Invoice created!");
		
		return "/invoices/show?id=" + invoice.getId() + "&faces-redirect=true";
	}

	public List<Delivery> getDeliveries() {
		return ejb.getDeliveries();
	}

}
