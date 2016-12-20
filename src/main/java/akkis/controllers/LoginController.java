package akkis.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import akkis.AkkisEjb;

@ManagedBean
public class LoginController {

	// EJB-komponentti sisältää datan tallennuksen ja haun tietokannasta JPA:lla
	@EJB
	private AkkisEjb tuoteEjb;
	
	
}
