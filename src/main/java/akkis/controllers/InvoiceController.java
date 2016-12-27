package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
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
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		System.out.println("Invoice:" + in);
		tuoteEjb.save(in);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
	}

	public List<Invoice> getInvoices() {
		return tuoteEjb.getInvoices();
	}
	
}
