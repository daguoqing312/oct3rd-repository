package org.jboss.tools.example.richfaces.control;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.tools.example.richfaces.entity.CustomerInfo;

@Model
public class CustomerInfoCtrl {

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	private CustomerInfo newCustomerInfo;
	private CustomerInfo customerInfo;
	

	
	public void submit(){
		submitForm(newCustomerInfo);
		initCustomerInfo();
		facesContext.addMessage("submitForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Info Submitted", "submitted"));
	}
	
	public void submitForm(CustomerInfo customerInfo){
		try{
			em.persist(customerInfo);
		}catch (Exception e){
			log.severe("Save in database failed.");
			log.severe(e.getMessage());
		}
	}
	
	@PostConstruct
	public void initCustomerInfo(){
		newCustomerInfo = new CustomerInfo();
	}

	@Produces
	@Named
	public CustomerInfo getNewCustomerInfo() {
		return newCustomerInfo;
	}

	public void setNewCustomerInfo(CustomerInfo newCustomerInfo) {
		this.newCustomerInfo = newCustomerInfo;
	}

	@Produces
	@Named	
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
}
