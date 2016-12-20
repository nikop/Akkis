package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AkkisEjb {
	
	
	@PersistenceContext(unitName = "jpa_akkis") // Name in persistence.xml
	private EntityManager em;

	public AkkisEjb() {

	}
	
	public void init() {
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