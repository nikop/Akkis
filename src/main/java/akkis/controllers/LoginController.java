package akkis.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import akkis.AkkisEjb;
import akkis.Product;
import akkis.User;

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
			return "login-error";
		
		loginUser.setUser(user);	
		
		return "index";
	}
	
	public String logout()
	{
		loginUser.setUser(null);
		return "logout-ok";
	}
}
