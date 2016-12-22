package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.controllers.LoginUser;
import akkis.types.Status;

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
		
		user1.setUsername("jorma");
		user1.setName("Jorma");
		user1.setPassword("salasana");
		
		em.persist(user1);
		
		Customer customer = new Customer();
		customer.setName("Pekka");
		customer.setAddress("Asemakatu 4");
		customer.setCountry("Finland");
		customer.setEmail("pekka@gmail.com");
		customer.setPhoneNumber("555 1234567");
		customer.setStatus(Status.CONTACT);
		
		em.persist(customer);
		
		Company company = new Company();
		company.setName("Nokia");
		company.setAddress("Visiokatu 4");
		company.setPhoneNumber("555 1234567");
		company.setWebPage("http://www.nokia.com");
		company.setyTunnus("1234567-8");
		
		em.persist(company);
	}

	public void save(Object book) {
		em.persist(book);
	}
	
	public void saveChanges(Object book) {
		em.merge(book);
	}
	
	public User getUser(LoginUser loginUser) {
		
		try {
			User user = (User) em.createNamedQuery("userLogin")
				.setParameter("user",loginUser.getUsername())
				.setParameter("password", loginUser.getPassword()).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}
	}

	public List<Customer> getCustomers() {
		List<Customer> customers = null;
		customers = em.createNamedQuery("searchAllCustomers").getResultList();
		System.out.println("**List of the Customers**");
		// TODO Auto-generated method stub
		return customers;
	}
	

	public List<Company> getCompanies() {
		List<Company> companies = null;
		companies = em.createNamedQuery("searchAllCompanies").getResultList();
		// TODO Auto-generated method stub
		System.out.println("**List of the Companies**");
		return companies;
	}

	public Customer getCustomer(Long id) {
		
		try {
			Customer user = (Customer) em.createNamedQuery("customerbyId")
				.setParameter("id", id).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}
		
	}
	
}