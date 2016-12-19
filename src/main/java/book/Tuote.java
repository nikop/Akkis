package book;

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
@NamedQuery(name = "searchAll", query = "SELECT b from Tuote b") 
public class Tuote implements Serializable {

    @Id
	@SequenceGenerator(name = "id_seq_tuote", sequenceName = "TUOTE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_tuote")
    private Long id;
    
    @Size(min = 2, message = "Anna tuotteen nimi")
    private String name; 

    @Min(1)
    private double price;

	public Tuote() {

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
		return "Tuote [id=" + id + ", name=" + name + "]";
	}

}


