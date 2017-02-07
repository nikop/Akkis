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
@NamedQueries({
	@NamedQuery(name = "searchAllUsers", query = "SELECT u from User u"),
	@NamedQuery(name = "userLogin", query = "SELECT u from User u WHERE u.username = :user AND u.password = :password"),
	@NamedQuery(name = "userById", query = "SELECT u from User u WHERE u.id = :id") 
})
public class User implements Serializable {
	
	@Id
	@SequenceGenerator(name = "id_seq_user", sequenceName = "User_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_user")
    
	private Long id;
	
	@Column(unique=true)
	@Size(min = 3, max=25, message = "Must be between 3 and 25 characters")
	private String username;
	
	@Size(min = 1, message = "Required")
	private String password;
	
	@Size(min = 1, message = "Required")
	private String name;
	
	private List<Role> roles;
	
	public User () {
		
	}

	public boolean hasRole(Role role) {
		 
		 System.out.println(roles);
		 System.out.println(role);
		 System.out.println(roles.contains(role));
		 
		 return roles.contains(role);
	 }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}

}
