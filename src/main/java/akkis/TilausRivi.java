package akkis;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@ManagedBean
@RequestScoped
@Entity
public class TilausRivi {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "id_seq_tilausrivi", sequenceName = "TILAUSRIVI_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_tilausrivi")
    private Long id;
    
	private Tuote tuote;
	
	private double price;
	
	private Tilaus tilaus;

	public TilausRivi(Tilaus tilaus, Tuote tuote) {
		super();
		this.tilaus = tilaus;
		this.tuote = tuote;
		this.price = tuote.getPrice();
	}
	
	public TilausRivi()
	{		
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Tilaus getTilaus() {
		return tilaus;
	}

	public void setTilaus(Tilaus tilaus) {
		this.tilaus = tilaus;
	}

	public Tuote getTuote() {
		return tuote;
	}

	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
	
}
