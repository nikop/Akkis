package akkis;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllCompanies", query = "SELECT c from Company c") 
public class Company  implements Serializable {

	@Id
	@SequenceGenerator(name = "id_seq_company", sequenceName = "Company_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_company")
    
	private Long id;
	private String name;
	private String phoneNumber;
	private String address;
	private String webPage;
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
