package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.controllers.LoginUser;
import akkis.types.Role;
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
		
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.ADMIN);
		
		user1.setUsername("jorma");
		user1.setName("Jorma");
		user1.setPassword("salasana");
		user1.setRoles((List<Role>) roles);
		
		em.persist(user1);
		
		
		User user2 = new User();
		
		ArrayList<Role> user2roles = new ArrayList<Role>();
		user2roles.add(Role.VIEW_COMPANIES);
		user2roles.add(Role.VIEW_CUSTOMERS);
		user2roles.add(Role.VIEW_DELIVERIES);
		user2roles.add(Role.VIEW_INVOICES);
		user2roles.add(Role.VIEW_PRODUCTS);
		user2roles.add(Role.VIEW_USERS);
		
		user2.setUsername("boss");
		user2.setName("Boss");
		user2.setPassword("salasana");
		user2.setRoles((List<Role>) user2roles);
		
		em.persist(user2);
		
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
		
		
		Delivery delivery1 = new Delivery();
		delivery1.setName("Toimitus 1");
		em.persist(delivery1);
		
		Delivery delivery2 = new Delivery();
		delivery2.setName("Toimitus 2");
		em.persist(delivery2);
		
		Date tanaan = new Date();
		SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
		fdate.format(tanaan);
			
		Invoice invoice = new Invoice();
		invoice.setDelivery(delivery1);
		invoice.setSum(100);
		invoice.setDate(tanaan);
		invoice.setDuePeriod(14);
		invoice.setInfoText("InfoText");
		
		
		Invoice invoice1 = new Invoice();
		invoice1.setDelivery(delivery2);
		invoice1.setSum(120);
		invoice1.setDate(tanaan);
		invoice1.setDuePeriod(10);
		invoice1.setInfoText("InfoText2");
		
		em.persist(invoice);
		em.persist(invoice1);

		List<Invoice> invoices1 = new ArrayList<Invoice>();
		invoices1.add(invoice);
	//	List<Invoice> invoices2 = new ArrayList<Invoice>();
	//	invoices2.add(invoice1);
		
	//	Delivery deliverylist1 = new Delivery();
	//	Delivery deliverylist2 = new Delivery();
	//	deliverylist1.setInvoices(invoices1);
	//	deliverylist2.setInvoices(invoices2);
		
	//	em.persist(deliverylist1);
	//	em.persist(deliverylist2);
				
		Product product = new Product();
		product.setName("Tuote1");
		product.setPrice(20.0);
		
		em.persist(product);	
	}
	
	public void save(Object book) {
		em.persist(book);
	}
	
	public void saveChanges(Object book) {
		em.merge(book);
	}
	
	public List<User> getUsers() {
		List<User> users = em.createNamedQuery("searchAllUsers").getResultList();
		return users;
	}
	
	public User getUser(Long id) {
		
		try {
			User user = (User) em.createNamedQuery("userById")
				.setParameter("id", id).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
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
		return customers;
	}
	

	public List<Company> getCompanies() {
		List<Company> companies = null;
		companies = em.createNamedQuery("searchAllCompanies").getResultList();

		return companies;
	}

	public List<Invoice> getInvoices() {
		List<Invoice> invoices = null;
		invoices = em.createNamedQuery("searchAllInvoices").getResultList();

		return invoices;
	}
	
	public List<Invoice> getInvoices(Delivery delivery) {
		List<Invoice> invoices = null;
		invoices = em.createNamedQuery("invoicesForDelivery")
			.setParameter("delivery", delivery).getResultList();
		return invoices;
	}
	
	public Invoice getInvoice(Long id) {
		
		try {
			Invoice user = (Invoice) em.createNamedQuery("invoiceById")
				.setParameter("id", id).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
	}
	
	public Delivery getDelivery(Long id) {
		
		try {
			Delivery user = (Delivery) em.createNamedQuery("deliveryById")
				.setParameter("id", id).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
	}

	public Company getCompany(Long id) {
		
		try {
			Company user = (Company) em.createNamedQuery("companyById")
				.setParameter("id", id).getSingleResult();
			
			return user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
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

	public List<Product> getproducts() {
		List<Product> products = null;
		products = em.createNamedQuery("searchAllProducts").getResultList();
		return products;
	}
	
	public Product getProduct(Long id) {
		
		try {
			Object user = em.createNamedQuery("productByID")
				.setParameter("id", id).getSingleResult();
			
			System.out.println(user);
			
			return (Product) user;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}
		
	}

	public List<Delivery> getDeliveries() {
		List<Delivery> deliveries = null;
		deliveries = em.createNamedQuery("searchAllDeliveries").getResultList();
		return deliveries;
	}

		
}