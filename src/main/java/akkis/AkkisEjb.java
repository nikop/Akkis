package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.controllers.LoginUser;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AkkisEjb {
	
	
	@PersistenceContext(unitName = "jpa_akkis") // Name in persistence.xml
	private EntityManager em;

	public AkkisEjb() {

	}
	
	public void init() {
		
		User user1 = new User();
		
		user1.setName("jorma");
		user1.setPassword("salasana");
		
		em.persist(user1);
	}

	public void save(Object book) {
		em.persist(book);
	}
	
	public User getUser(LoginUser loginUser) {
		
		User user = (User) em.createNamedQuery("userLogin")
			.setParameter("user",loginUser.getUser())
			.setParameter("password", loginUser.getPassword()).getSingleResult();
		
		return user;
	}
	
}