package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.controllers.LoginUser;
import akkis.types.Status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
		//List<Delivery> deliveries = new ArrayList<Delivery>();
		//em.persist(deliveries);
		
		Date tanaan = new Date();
		SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
		fdate.format(tanaan);
//			
		Invoice invoice = new Invoice();
		//invoice.setDeliveries(deliveries);
		invoice.setSum(100);
		invoice.setDate(tanaan);
		invoice.setDuePeriod(14);
		invoice.setInfoText("InfoText");
		
		em.persist(invoice);
		
	}

	public void save(Object book) {
		em.persist(book);
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

	public List<Invoice> getInvoices() {
		List<Invoice> invoices = null;
		invoices = em.createNamedQuery("searchAllInvoices").getResultList();
		System.out.println("**List of the Invoices**");
		// TODO Auto-generated method stub
		return invoices;
	}

	
	
}