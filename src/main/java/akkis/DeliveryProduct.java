package akkis;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 * Entity JPA toteututs.
 * Managed Bean tarjotaan JSF:lle. VOidaan käyttää JSF:n kontrollerista
 */

@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllDeliveryProducts", query = "SELECT dP from DeliveryProduct dP") 
public class DeliveryProduct {

	@Id
	@SequenceGenerator(name = "id_seq_deliveryproduct", sequenceName = "DeliveryProduct_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_deliveryproduct")
    
	private Long id;
    private Product product;
    private Delivery delivery;
	
	public DeliveryProduct() {
		
	}

	public DeliveryProduct(Product p) {
		product = p;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "DeliveryProduct [id=" + id + ", product=" + product
				+ ", delivery=" + delivery + "]";
	}

}
