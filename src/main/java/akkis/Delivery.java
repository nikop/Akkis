package akkis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
@Entity
@NamedQuery(name = "searchAllDeliveries", query = "SELECT d from Delivery d") 
public class Delivery implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_delivery", sequenceName = "Delivery_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_delivery")

	private long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Invoice> deliveries;
//private Invoice invoice;
	
	
	public Delivery() {
		super();
		deliveries = new ArrayList<Invoice>();
	//	deliveries.add(new Invoice());
	//	invoice = new Invoice();
	}

	



	public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public List<Invoice> getDeliveries() {
		return deliveries;
	}





	public void setDeliveries(List<Invoice> deliveries) {
		this.deliveries = deliveries;
	}





	@Override
	public String toString() {
		return "Delivery [id=" + id + ", deliveries=" + deliveries + "]";
	}
	
	
	
}
