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
		
		Product b1= new Product();
		b1.setName("Elements of Programming Interviews in Java");
		b1.setPrice(13.37);
				
		Product b2= new Product();
		b2.setName("Lada");
		b2.setPrice(500);
		
		Product b3= new Product();
		b3.setName("Tietokone");
		b3.setPrice(100);
				
		Product b4= new Product();
		b4.setName("L�pp�ri");
		b4.setPrice(200);

		Product b5= new Product();
		b5.setName("Kirves");
		b5.setPrice(50);

		Product b6= new Product();
		b6.setName("Leffapaketti");
		b6.setPrice(80);

		Product b7= new Product();
		b7.setName("Oma verkkokauppa");
		b7.setPrice(5000);

		Product b8= new Product();
		b8.setName("Sikas�kiss�");
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
	public List<Product> getTuottet() {
		List<Product> books = null; 
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