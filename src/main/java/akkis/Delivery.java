package akkis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
@Entity
@NamedQuery(name = "searchAllDeliveries", query = "SELECT d from Delivery d") 
public class Delivery implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_delivery", sequenceName = "Delivery_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_delivery")

	private long id;
	
	//@OneToMany(fetch = FetchType.EAGER)
	private List<Invoice> invoices;
//	private Invoice invoice = new Invoice();
//	private boolean edit;
	
	
	public Delivery() {
		super();
	//	invoices = new ArrayList<Invoice>();
	//	invoices.add(invoice);
	//	invoice = new Invoice();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	public void addRow() {
		invoices.add(new Invoice());
	}
	
	
		
	public void addRow() {
		invoice.setId(invoices.isEmpty() ? 1 : invoices.get(invoices.size() - 1).getId() + 1);
		invoices.add(new Invoice());
	//	invoice = new Invoice();
	}
	
	/*
	public void edit(Invoice invoice) {
		this.invoice = invoice;
		edit = true;
	}
	
	public void save() {
		invoice = new Invoice();
		edit = false;
	}
	
	public void delete(Invoice invoice) {
		deliveries.remove(invoice);
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public boolean isEdit() {
		return edit;
	}
	*/
	

	public List<Invoice> getInvoices() {
		return invoices;
	}


	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	
	@Override
	public String toString() {
		return "Delivery [id=" + id + ", invoices=" + invoices + "]";
	}


	
}
