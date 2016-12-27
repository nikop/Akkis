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
@NamedQuery(name = "searchAllCompanies", query = "SELECT c from Company c") 
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
	
	//private ArrayList<Company> companies = new ArrayList<Company>();
	
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
	/*
	private boolean editable;

	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public String editCompany(Company company) {
		company.setEditable(true);
		return null;
	}
	
	// save companies which has been edited
	public String saveEditableCompanies(){
	      //set "canEdit" of all employees to false 
	      for (Company company : companies){
	         company.setEditable(false);
	      }		
	      return null;
	   }
	
	public String editAllCompanies() {
		//set "editable" to true for all fields
		for (Company ca : companies){
			ca.setEditable(true);
		}
		return null;
	}
*/
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", webPage=" + webPage
				+ ", yTunnus=" + yTunnus + "]";
	}
	
	
	
}
