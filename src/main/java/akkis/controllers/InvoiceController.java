package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Delivery;
import akkis.Invoice;
import akkis.InvoiceRow;

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
	
	@ManagedProperty(value = "#{invoiceRow}")
	private InvoiceRow invoiceRow;

	public InvoiceController() {
		
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceRow getInvoiceRow() {
		return invoiceRow;
	}

	public void setInvoiceRow(InvoiceRow invoiceRow) {
		this.invoiceRow = invoiceRow;
	}

	public String saveNewInvoice(Invoice invoice) {
		tuoteEjb.save(invoice);
		
		FacesMessages.info("Successfully saved.");
		
		return "/invoices/index";
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
		tuoteEjb.update(invoice);
		
		FacesMessages.info("Successfully saved.");
		
		return null;
	}
	
	public String save(Invoice invoice, InvoiceRow invoiceRow) {
		
		invoice.addRow(invoiceRow);
		
		tuoteEjb.update(invoice);
		
		FacesMessages.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String update(InvoiceRow invoiceRow) {
		tuoteEjb.update(invoiceRow);
		
		FacesMessages.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoiceRow.getInvoice().getId();
	}

	public List<Invoice> getInvoices() {
		return tuoteEjb.getInvoices();
	}
	
	public List<Invoice> getInvoices(Delivery delivery) {
		return tuoteEjb.getInvoices(delivery);
	}
	
}
