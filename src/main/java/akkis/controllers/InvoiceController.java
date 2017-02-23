package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Delivery;
import akkis.Invoice;
import akkis.InvoiceRow;
import akkis.types.InvoiceStatus;

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
		
		Akkis.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String saveInvoice(Delivery delivery) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		
		in.setDelivery(delivery);
		
		tuoteEjb.save(in);
		
		Akkis.info("Successfully saved.");
		
		return null;
	}
	
	public String saveInvoice(Invoice invoice) {
		tuoteEjb.update(invoice);
		
		Akkis.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String addRowToInvoice(Invoice invoice, InvoiceRow invoiceRow) {
		
		invoice.addRow(invoiceRow);
		
		invoice.setSum(invoice.calculateSum());
		
		tuoteEjb.update(invoice);
		
		Akkis.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String update(InvoiceRow invoiceRow) {
		tuoteEjb.update(invoiceRow);
		
		Akkis.info("Successfully saved.");
		
		return "/invoices/show?faces-redirect=true&id=" + invoiceRow.getInvoice().getId();
	}
	
	public String markOpen(Invoice invoice) {
		
		if (invoice.getStatus() == InvoiceStatus.OPEN)
		{
			Akkis.error("Can't change status");
			return null;
		}
				
		invoice.setStatus(InvoiceStatus.OPEN);
		
		tuoteEjb.update(invoice);
		
		Akkis.info("Invoice Open");
		
		return null;
	}
	
	public String markPaid(Invoice invoice) {
		
		if (invoice.getStatus() != InvoiceStatus.OPEN)
		{
			Akkis.error("Can't change status");
			return null;
		}
			
		invoice.setStatus(InvoiceStatus.PAID);
		
		tuoteEjb.update(invoice);
		
		Akkis.info("Invoice Paid");
		
		return null;
	}
	
	public String markVoid(Invoice invoice) {
		
		if (invoice.getStatus() != InvoiceStatus.OPEN)
		{
			Akkis.error("Can't change status");
			return null;
		}		
		
		invoice.setStatus(InvoiceStatus.VOIDED);
		
		tuoteEjb.update(invoice);
		
		Akkis.info("Invoice Voided");
		
		return null;
	}

	public List<Invoice> getInvoices() {
		return tuoteEjb.getInvoices();
	}
	
	public List<Invoice> getOpenInvoices() {
		return tuoteEjb.getOpenInvoices();
	}
	
	public List<Invoice> getInvoices(Delivery delivery) {
		return tuoteEjb.getInvoices(delivery);
	}
	
}
