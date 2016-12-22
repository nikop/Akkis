package akkis;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import akkis.types.Role;
import akkis.types.Status;

@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllCustomers", query = "SELECT c from Customer c") 
public class Customer implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_customer", sequenceName = "Customer_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_customer")
    
	private Long id;
	private String name;
	private String phoneNumber;
	private String email; 
	private String country;
	private String address;
	private Status status;
	private User responsibilitySeller;
	
	public Customer() {
	
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public User getResponsibilitySeller() {
		return responsibilitySeller;
	}


	public void setResponsibilitySeller(User responsibilitySeller) {
		this.responsibilitySeller = responsibilitySeller;
	}
	
	


}
