package akkis;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Random;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import akkis.types.Role;

@ManagedBean
@RequestScoped
@Entity
@NamedQueries({
	@NamedQuery(name = "searchAllUsers", query = "SELECT u from User u"),
	@NamedQuery(name = "userByLogin", query = "SELECT u from User u WHERE u.username = :user"),
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
	
	@Transient
	@Size(min = 3, message = "More than 3")
	private String password;
	
	private String passwordHashed;
	
	private String salt;
	
	@Size(min = 1, message = "Required")
	private String name;
	
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
	
	public String getPasswordHashed() {
		return passwordHashed;
	}

	public void setPasswordHashed(String passwordHashed) {
		this.passwordHashed = passwordHashed;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	@Transient
	public void setPassword(String password) {
		this.password = password;
		
		if (this.getSalt() == null) {
            this.setSalt(StringUtilities.getRandomString(new Random(), 16, 16));
        }

        try {
			this.setPasswordHashed(this.computeHash(password, this.getSalt()));
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			this.setPasswordHashed("");
		} catch (InvalidKeySpecException e) {
			System.out.println(e);
			this.setPasswordHashed("");
		}
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	// http://stackoverflow.com/questions/8959898/best-way-to-log-in-log-out-and-store-session-attributes-in-jsf
	
	@Transient
    public boolean checkPasswordForLogin(String passwordPlaintext) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (passwordPlaintext.trim().equals("")) {
            return false;
        }
        
        return passwordHashed.equals(this.computeHash(passwordPlaintext, this.getSalt()));
    }

    @Transient
    private String computeHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 2048, 160);
        SecretKeyFactory fact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return StringUtilities.encode(fact.generateSecret(spec).getEncoded());
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}
}
