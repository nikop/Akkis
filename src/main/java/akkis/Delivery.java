package akkis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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
	
	@EJB
    private AkkisEjb ejb;
	
	@Id
	@SequenceGenerator(name = "id_seq_delivery", sequenceName = "Delivery_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_delivery")

	private long id;
	
	//@OneToMany(fetch = FetchType.EAGER)
	private List<Invoice> invoices;
//	private Invoice invoice = new Invoice();
//	private boolean edit;
	
	private List<DeliveryProduct> products;	
	
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
		DeliveryProduct dp = new DeliveryProduct();
		dp.setProduct(product);
		addProduct(dp);
	}
	
	public void addProduct(DeliveryProduct dp)
	{
		dp.setDelivery(this);
		products.add(dp);
		
		ejb.save(dp);
		ejb.saveChanges(this);
	}


	public void setProducts(List<DeliveryProduct> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Delivery [id=" + id + ", invoices=" + invoices + "]";
	}


	
}
