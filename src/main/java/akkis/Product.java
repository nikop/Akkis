package akkis;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Entity
@NamedQueries({
	@NamedQuery(name = "searchAllProducts", query = "SELECT b from Product b"),
	@NamedQuery(name = "productByID", query = "SELECT c from Product c WHERE c.id = :id") 	
})
public class Product implements Serializable {

	@Id
	@SequenceGenerator(name = "id_seq_product", sequenceName = "Product_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_product")
    private Long id;
    
    @Size(min = 2, message = "Write product name")
    private String name; 

    //@Min(1)
    @DecimalMin("0.01")
	@DecimalMax("9999.99")
    private double price;

	public Product() {

    } 
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
  		return price;
  	}

  	public void setPrice(double price) {
  		this.price = price;
  	}    
    
    @Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}

}


