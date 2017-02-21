package akkis.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import akkis.AkkisEjb;
import akkis.Company;
import akkis.Customer;
import akkis.Product;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import net.bootsfaces.utils.FacesMessages;

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
	
	public String newCompany(Company company) {
		
		tuoteEjb.save(company);
		
		Akkis.info("Successfully saved.");
		
		return "/companies/edit?id=" + company.getId() + "&faces-redirect=true";	
	}
	
	public String updateCompany(Company company) {		
		tuoteEjb.update(company);
		
		Akkis.info("Successfully saved.");
		
		return "/companies/index?faces-redirect=true";
	}

	public List<Company> getCompanies() {
		return tuoteEjb.getCompanies();
	}
	
}
