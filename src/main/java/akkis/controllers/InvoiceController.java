package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Company;
import akkis.Customer;
import akkis.Invoice;
import akkis.Product;
import akkis.Tilaus;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

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
		String message = "Saving was successful: " + invoice;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		System.out.println("Invoice:" + in);
		tuoteEjb.save(in);
		return message;
		/*
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);

		return "index";
		*/
	}

	public List<Invoice> getInvoices() {
		return tuoteEjb.getInvoices();
	}

	
	public String initInvoice() {
		tuoteEjb.init();
		return null;
	}

	
}
