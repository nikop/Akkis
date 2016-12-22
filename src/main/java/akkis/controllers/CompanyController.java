package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Company;
import akkis.Customer;
import akkis.Product;
import akkis.Tilaus;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

@ManagedBean
public class CompanyController {

	@EJB
	private AkkisEjb tuoteEjb;
	
	@ManagedProperty(value = "#{company}")
	private Company company;
	
	public CompanyController() {
		
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String saveCompany() {
		String message = "Saving was successful: " + company;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Company co = (Company) facesContext.getExternalContext().getRequestMap().get("company");
		System.out.println("Company:" + co);
		tuoteEjb.save(co);
		return message;
		/*
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);

		return "index";
		*/
	}

	public List<Company> getCompanies() {
		return tuoteEjb.getCompanies();
	}

	
	public String initCompany() {
		tuoteEjb.init();
		return null;
	}

	
}
