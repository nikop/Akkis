package akkis.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import akkis.AkkisEjb;
import akkis.Product;
import akkis.User;
import akkis.beans.ChangePassword;
import akkis.beans.LoginUser;
import akkis.beans.UserInfo;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
public class LoginController {

	@EJB
	private AkkisEjb ejb;
	
	@ManagedProperty(value = "#{userInfo}")
	private UserInfo userInfo;
	
	@ManagedProperty(value = "#{loginUser}")
	private LoginUser loginUser;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public String login()
	{
		User user = ejb.getUser(loginUser.getUsername());
		
		try {
			if (!user.checkPasswordForLogin(loginUser.getPassword()))
				user = null;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			
			user = null;
		}
		
		loginUser.setPassword(null);
		
		if (user == null)
		{
			Akkis.error("Wrong username or password!");
			return null;
		}
		else
		{
			userInfo.login(user);	
		}
		
		Akkis.info("Successfully logged in.");
		
		return null;
	}
	
	public String logout()
	{
		userInfo.logout();

		Akkis.info("Successfully logged out.");
		
		return "/index";
	}
	
	public String init()
	{
		ejb.init();
		
		return "/index";
	}
	
	public String changePassword(ChangePassword ch) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if (!userInfo.getUser().checkPasswordForLogin(ch.getOld_password()))
		{
			Akkis.info("Old Password is Wrong");
			
			return null;
		}
		
		if (!ch.getNew_password().equals(ch.getNew_password2()))
		{
			Akkis.info("New Password don't match.");
			
			return null;
		}
		
		userInfo.getUser().setPassword(ch.getNew_password());
		
		ejb.update(userInfo.getUser());
		
		userInfo.login(userInfo.getUser());
		
		return null;
	}
}
