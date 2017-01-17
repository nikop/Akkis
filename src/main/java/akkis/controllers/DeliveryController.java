package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Delivery;
import akkis.Invoice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class DeliveryController {

	@EJB
	private AkkisEjb tuoteEjb;

	@ManagedProperty(value = "#{delivery}")
	private Delivery delivery;
	// private Invoice invoice;

	public DeliveryController() {

	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String saveDelivery() {

		tuoteEjb.save(delivery);

		FacesMessages.info("Successfully saved.");

		return null;
	}
	
	public String saveDelivery(Delivery delivery) {
		tuoteEjb.saveChanges(delivery);
		
		FacesMessages.info("Successfully saved.");
		
		return "delivery?faces-redirect=true";
	}

	public List<Delivery> getDeliveries() {
		return tuoteEjb.getDeliveries();
	}

	public String initDelivery() {
		tuoteEjb.init();
		return null;
	}

}
