package akkis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import akkis.types.InvoiceStatus;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@RequestScoped
@ManagedBean
@Entity
@NamedQueries({
	@NamedQuery(name = "searchAllDeliveries", query = "SELECT d from Delivery d"),
	@NamedQuery(name = "deliveryById", query = "SELECT d from Delivery d WHERE d.id = :id")
})
public class Delivery implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_delivery", sequenceName = "Delivery_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_delivery")

	private long id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy="delivery")
	private List<Invoice> invoices;	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy="delivery")
	private List<DeliveryProduct> products;	
	
	public Delivery() {
		super();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	private String name;	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public List<DeliveryProduct> getProducts() {
		return products;
	}

	public void addProduct(Product product)
	{
		addProduct(new DeliveryProduct(product));
	}
	
	public void addProduct(DeliveryProduct dp)
	{
		dp.setDelivery(this);
		products.add(dp);
	}


	public void setProducts(List<DeliveryProduct> products) {
		this.products = products;
	}
	
	public Invoice createInvoice() {
		
		Invoice invoice = new Invoice();
		invoice.setDate(new Date());
		invoice.setDuePeriod(14);
		invoice.setDelivery(this);
		invoice.setInfoText(String.format("Invoice for %d products on %s", products.size(), invoice.getDate()));
		
		double sum = 0;
		
		for (Iterator<DeliveryProduct> iterator = products.iterator(); iterator.hasNext();) {
			DeliveryProduct dp = iterator.next();
			InvoiceRow r = new InvoiceRow();
			
			r.setInvoice(invoice);
			r.setText(dp.getProduct().getName());
			r.setAmount(1);
			r.setUnitPrice(dp.getProduct().getPrice());
			
			invoice.addRow(r);
			
			sum += r.getRowTotal();
		}
		
		invoice.setSum(sum);
		invoice.setStatus(InvoiceStatus.OPEN);
		
		invoices.add(invoice);
		
		return invoice;
	}


	@Override
	public String toString() {
		return "Delivery [id=" + id + ", invoices=" + invoices + "]";
	}


	
}
