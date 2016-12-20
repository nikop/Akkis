package akkis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import akkis.types.Role;

@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllUsers", query = "SELECT u from User u") 
public class User implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_user", sequenceName = "User_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_user")
    
	private Long id;
	private String name;
	private String password;
	private List<Role> roles;
	
	public User () {
		
	}

	 public boolean hasRole(Role role) {
	        return roles.contains(role);
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}

	
	
	
	
}
