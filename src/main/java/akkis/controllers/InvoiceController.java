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
public class InvoiceController {

	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{invoice}")
	private Invoice invoice;

	public InvoiceController() {
		
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String saveInvoice() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		tuoteEjb.save(in);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
	}
	
	public String saveInvoice(Delivery delivery) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		
		in.setDelivery(delivery);
		
		tuoteEjb.save(in);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
	}
	
	public String saveInvoice(Invoice invoice) {
		tuoteEjb.saveChanges(invoice);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
	}

	public List<Invoice> getInvoices() {
		return tuoteEjb.getInvoices();
	}
	
	public List<Invoice> getInvoices(Delivery delivery) {
		return tuoteEjb.getInvoices(delivery);
	}
	
}
