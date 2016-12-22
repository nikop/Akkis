package akkis.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import akkis.AkkisEjb;
import akkis.Product;
import akkis.User;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class LoginController {

	// EJB-komponentti sisältää datan tallennuksen ja haun tietokannasta JPA:lla
	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{loginUser}")
	private LoginUser loginUser;
	
	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public String init()
	{
		tuoteEjb.init();
		
		return "index";
	}
	
	public String login()
	{
		User user = tuoteEjb.getUser(loginUser);
		loginUser.setPassword("");
		
		if (user == null)
		{
			FacesMessages.error("Wrong username or password!");
			return null;
		}
		loginUser.setUser(user);	
		
		FacesMessages.info("Successfully logged in.");
		
		return null;
	}
	
	public String logout()
	{
		FacesMessages.info("Successfully logged out.");
		
		loginUser.setUser(null);
		return "index";
	}
}
