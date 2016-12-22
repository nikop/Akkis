package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.controllers.LoginUser;
import akkis.types.Role;
import akkis.types.Status;

import java.util.ArrayList;
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
		
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.ADMIN);
		
		user1.setUsername("jorma");
		user1.setName("Jorma");
		user1.setPassword("salasana");
		user1.setRoles((List<Role>) roles);
		
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