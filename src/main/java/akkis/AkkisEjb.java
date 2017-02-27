package akkis;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import akkis.beans.UserInfo;
import akkis.types.InvoiceStatus;
import akkis.types.Role;
import akkis.types.Status;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
			
		Invoice invoice = new Invoice();
		invoice.setDelivery(delivery1);
		invoice.setSum(100);
		invoice.setDate(tanaan);
		invoice.setDuePeriod(14);
		invoice.setInfoText("InfoText");
		invoice.addRow(
			new InvoiceRow("Example Item", 100)
		);
		
		
		Invoice invoice1 = new Invoice();
		invoice1.setDelivery(delivery2);
		invoice1.setSum(120);
		invoice1.setDate(tanaan);
		invoice1.setDuePeriod(10);
		invoice1.setInfoText("InfoText2");
		invoice1.addRow(
			new InvoiceRow("Example Item", 120)
		);
		
		em.persist(invoice);
		em.persist(invoice1);

		Product product = new Product();
		product.setName("Tuote1");
		product.setPrice(20.0);
		
		em.persist(product);	
	}
	
	public void save(Object book) {
		em.persist(book);
	}
	
	public void update(Object book) {
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
	
	public User getUser(String username) {
		try {
			User user = (User) em.createNamedQuery("userByLogin")
				.setParameter("user", username).getSingleResult();
				
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
	
	public List<Invoice> getOpenInvoices() {
		List<Invoice> invoices = null;
		invoices = em.createNamedQuery("invoicesByStatus").setParameter("status", InvoiceStatus.OPEN).getResultList();

		return invoices;
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
			Invoice invoice = (Invoice) em.createNamedQuery("invoiceById")
				.setParameter("id", id).getSingleResult();
			
			return invoice;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
	}
	
	public InvoiceRow getInvoiceRow(Long id) {
		
		try {
			InvoiceRow invoiceRow = (InvoiceRow) em.createNamedQuery("invoiceRowById")
				.setParameter("id", id).getSingleResult();
			
			return invoiceRow;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
	}
	
	public Delivery getDelivery(Long id) {
		
		try {
			Delivery delivery = (Delivery) em.createNamedQuery("deliveryById")
				.setParameter("id", id).getSingleResult();
			
			return delivery;
		}
		catch (javax.persistence.NoResultException ex)
		{
			return null;
		}	
	}

	public Company getCompany(Long id) {
		
		try {
			Company company = (Company) em.createNamedQuery("companyById")
				.setParameter("id", id).getSingleResult();
			
			return company;
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

	public List<Product> getProducts() {
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