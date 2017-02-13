package akkis;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Entity
@NamedQueries({
	@NamedQuery(name = "companyById", query = "SELECT c from Company c WHERE c.id = :id"),
	@NamedQuery(name = "searchAllCompanies", query = "SELECT c from Company c") 
})
public class Company  implements Serializable {

	@Id
	@SequenceGenerator(name = "id_seq_company", sequenceName = "Company_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_company")
    
	private Long id;
	
	@Size(min = 3, max=25, message = "Write Company name, at least 3 characters")
	private String name;
	@Size(min = 10, max = 13, message = "Write Company Phone Number, at least 10 numbers")
	private String phoneNumber;
	@Size(min = 5, max = 33, message = "Write Company address, at least 5 characters")
	private String address;
	@Size(min = 5, max = 33, message = "Write Company web page, at least 5 characters")
	private String webPage;
	@Size(min = 8, max = 33, message = "Write Company business id")
	private String yTunnus;
	
	public Company () {
		
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getyTunnus() {
		return yTunnus;
	}

	public void setyTunnus(String yTunnus) {
		this.yTunnus = yTunnus;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", webPage=" + webPage
				+ ", yTunnus=" + yTunnus + "]";
	}
	
	
	
}
