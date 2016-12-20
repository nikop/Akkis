package akkis;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;

@ManagedBean
public class TuoteController {

	// EJB-komponentti sisältää datan tallennuksen ja haun tietokannasta JPA:lla
	@EJB
	private TuoteEjb tuoteEjb;

	@ManagedProperty(value = "#{tuote}")
	private Product tuote;
	
	@ManagedProperty(value = "#{tilaus}")
	private Tilaus tilaus;
	
	private Tilaus prevTilaus;


	public Tilaus getTilaus() {
		return tilaus;
	}

	public void setTilaus(Tilaus tilaus) {
		this.tilaus = tilaus;
	}

	public Tilaus getPrevTilaus() {
		return prevTilaus;
	}

	public void setPrevTilaus(Tilaus prevTilaus) {
		this.prevTilaus = prevTilaus;
	}

	public TuoteController() {
		// bookEjb.alustaBooket();

	}

	public Product getTuote() {
		return tuote;
	}

	public void setTuote(Product book) {
		this.tuote = book;
	}

	public String save() {


		return "index";
	}

	public List<Product> getTuotteet() {
		return tuoteEjb.getTuottet();
	}
	
	public String insertTuote()
	{
		tuoteEjb.save(tuote);
		
		return "index";
	}
	
	
	public String addToCart(Product t)
	{
		tilaus.Add(t);
		
		return "index";
	}

	public String submitOrder()
	{
		prevTilaus = tilaus;
		tilaus.setSubmitTime(new Timestamp(System.currentTimeMillis()));
		
		tuoteEjb.save(tilaus);
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tilaus");
		
		return "order-complete";
	}
	
	public String initialize() {
		tuoteEjb.init();
		return null;
	}
}
