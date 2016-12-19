package book;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

@ManagedBean
@SessionScoped
@Entity
@NamedQuery(name = "searchAllOrders", query = "SELECT o from Tilaus o") 
public class Tilaus {

	@Id
	@SequenceGenerator(name = "id_seq", sequenceName = "TILAUS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Long id;
	
	@Size(min=1, message = "Anna nimesi")
    private String name;
    
    private String address;
    
    private java.sql.Timestamp submitTime; 
    
    @Size(min=1)
    @OneToMany(mappedBy = "tilaus", cascade = CascadeType.ALL)
    private List<TilausRivi> rivit = new ArrayList<TilausRivi>();
	
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<TilausRivi> getRivit() {
		return rivit;
	}
	public void setRivit(List<TilausRivi> rivit) {
		this.rivit = rivit;
	} 
	
	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	public double getTotal() {
		double total = 0;
		
		for (TilausRivi tilausRivi : rivit) {
			total += tilausRivi.getPrice();
		}
		
		return Math.round(total * 100) / 100.0;
	} 
    
    public void Add(Tuote t)
    {
    	rivit.add(new TilausRivi(this, t));
    }

}
