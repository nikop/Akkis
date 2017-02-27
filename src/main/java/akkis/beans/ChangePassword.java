package akkis.beans;

import java.beans.Transient;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;

import akkis.AkkisEjb;
import akkis.User;
import akkis.types.Permission;
import akkis.types.Role;

@ManagedBean
@RequestScoped
public class ChangePassword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String old_password;

	private String new_password;
	
	private String new_password2;

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password2() {
		return new_password2;
	}

	public void setNew_password2(String new_password2) {
		this.new_password2 = new_password2;
	}	
	
}
