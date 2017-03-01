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
	private AkkisEjb ejb;
	
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
		ejb.save(invoice);
		
		Akkis.info("Invoie Created");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String saveInvoice(Delivery delivery) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Invoice in = (Invoice) facesContext.getExternalContext().getRequestMap().get("invoice");
		
		in.setDelivery(delivery);
		
		ejb.save(in);
		
		Akkis.info("Invoie Created");
		
		return null;
	}
	
	public String saveInvoice(Invoice invoice) {
		ejb.update(invoice);
		
		Akkis.info("Invoice Updated");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String addRowToInvoice(Invoice invoice, InvoiceRow invoiceRow) {
		
		invoice.addRow(invoiceRow);
		
		invoice.setSum(invoice.calculateSum());
		
		ejb.update(invoice);
		
		Akkis.info("Item added to Invoice");
		
		return "/invoices/show?faces-redirect=true&id=" + invoice.getId();
	}
	
	public String update(InvoiceRow invoiceRow) {
		ejb.update(invoiceRow);
		
		Akkis.info("Item Updated");
		
		return "/invoices/show?faces-redirect=true&id=" + invoiceRow.getInvoice().getId();
	}
	
	public String markOpen(Invoice invoice) {
		
		if (invoice.getStatus() == InvoiceStatus.OPEN)
		{
			Akkis.error("Can't change status");
			return null;
		}
				
		invoice.setStatus(InvoiceStatus.OPEN);
		
		ejb.update(invoice);
		
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
		
		ejb.update(invoice);
		
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
		
		ejb.update(invoice);
		
		Akkis.info("Invoice Voided");
		
		return null;
	}

	public List<Invoice> getInvoices() {
		return ejb.getInvoices();
	}
	
	public List<Invoice> getOpenInvoices() {
		return ejb.getOpenInvoices();
	}
	
	public List<Invoice> getInvoices(Delivery delivery) {
		return ejb.getInvoices(delivery);
	}
	
}
