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
		
		
//		Delivery delivery1 = new Delivery();
//		em.persist(delivery1);
		
//		Delivery delivery2 = new Delivery();
//		em.persist(delivery2);
		
		Date tanaan = new Date();
		SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
		fdate.format(tanaan);
			
		Invoice invoice = new Invoice();
	//	invoice.setDelivery(delivery1);
		invoice.setSum(100);
		invoice.setDate(tanaan);
		invoice.setDuePeriod(14);
		invoice.setInfoText("InfoText");
		
/*		
		Invoice invoice1 = new Invoice();
//		invoice1.setDelivery(delivery2);
		invoice1.setSum(120);
		invoice1.setDate(tanaan);
		invoice1.setDuePeriod(10);
		invoice1.setInfoText("InfoText2");
	*/	
		
		em.persist(invoice);
	//	em.persist(invoice1);

		List<Invoice> invoices1 = new ArrayList<Invoice>();
		invoices1.add(invoice);
	//	List<Invoice> invoices2 = new ArrayList<Invoice>();
	//	invoices2.add(invoice1);
		
		Delivery deliverylist1 = new Delivery();
	//	Delivery deliverylist2 = new Delivery();
		deliverylist1.setInvoices(invoices1);
	//	deliverylist2.setInvoices(invoices2);
		
		em.persist(deliverylist1);
	//	em.persist(deliverylist2);
				
		Product product = new Product();
		product.setName("Tuote1");
		product.setPrice(20.0);
		
		em.persist(product);
		
		
	}

	public void saveInvoice(Invoice in) {
		
		List<Invoice> invoices3 = new ArrayList<Invoice>();
		invoices3.add(in);
		Delivery deliverylist3 = new Delivery();
		deliverylist3.setInvoices(invoices3);
		em.persist(in);
		em.persist(deliverylist3);
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

	public List<Invoice> getInvoices() {
		List<Invoice> invoices = null;
		invoices = em.createNamedQuery("searchAllInvoices").getResultList();
		System.out.println("**List of the Invoices**");
		// TODO Auto-generated method stub
		return invoices;
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
		System.out.println("**List of the Products**");
		// TODO Auto-generated method stub
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
		System.out.println("**List of the Deliveries**");
		// TODO Auto-generated method stub
		return deliveries;
	}

		
}