package akkis.controllers;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Delivery;
import akkis.Invoice;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class DeliveryController {

	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{delivery}")
	private Delivery delivery;
	private Invoice invoice;
	
	
	
	public DeliveryController() {
		
	}



	public Delivery getDelivery() {
		return delivery;
	}



	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
public String saveDelivery() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Delivery de = (Delivery) facesContext.getExternalContext().getRequestMap().get("delivery");
		System.out.println("Delivery:" + de);
		tuoteEjb.save(de);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
		
			}

	public List<Delivery> getDeliveries() {
		return tuoteEjb.getDeliveries();
	}

	
	public String initDelivery() {
		tuoteEjb.init();
		return null;
	}


	
	
	
}
