package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TuoteEjb {
	
	
	@PersistenceContext(unitName = "jpa_akkis") // Name in persistence.xml
	private EntityManager em;

	public TuoteEjb() {

	}
	
	public void init() {
		
		Tuote b1= new Tuote();
		b1.setName("Elements of Programming Interviews in Java");
		b1.setPrice(13.37);
				
		Tuote b2= new Tuote();
		b2.setName("Lada");
		b2.setPrice(500);
		
		Tuote b3= new Tuote();
		b3.setName("Tietokone");
		b3.setPrice(100);
				
		Tuote b4= new Tuote();
		b4.setName("Läppäri");
		b4.setPrice(200);

		Tuote b5= new Tuote();
		b5.setName("Kirves");
		b5.setPrice(50);

		Tuote b6= new Tuote();
		b6.setName("Leffapaketti");
		b6.setPrice(80);

		Tuote b7= new Tuote();
		b7.setName("Oma verkkokauppa");
		b7.setPrice(5000);

		Tuote b8= new Tuote();
		b8.setName("Sikasäkissä");
		b8.setPrice(50);

		em.persist(b1);
		em.persist(b2);
		em.persist(b3);
		em.persist(b4);
		em.persist(b5);
		em.persist(b6);
		em.persist(b7);
		em.persist(b8);
	}

	public void save(Object book) {
		em.persist(book);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tuote> getTuottet() {
		List<Tuote> books = null; 
		// get all books from the database
		books = em.createNamedQuery("searchAll").getResultList();
		System.out.println("*********** search all ********** => " + books);
		return books;
	}

	public List<Tilaus> getOrders() {
		List<Tilaus> orders = null; 
		// get all books from the database
		orders = em.createNamedQuery("searchAllOrders").getResultList();
		System.out.println("*********** search all ********** => " + orders);
		return orders;
	}
	
}