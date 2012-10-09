package org.jboss.tools.example.richfaces.control;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.tools.example.richfaces.entity.CustomerInfo;

@Model
@Stateful
public class CustomerInfoCtrl {

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	private CustomerInfo newCustomerInfo;
	private CustomerInfo customerInfo;
	

	
	public void submit() throws Exception{
		submitForm(newCustomerInfo);
		initCustomerInfo();
		facesContext.addMessage("submitForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Info Submitted", "submitted"));
		log.severe("Come to submit");
	}
	
	public void submitForm(CustomerInfo customerInfo)throws Exception{
		
			em.persist(customerInfo);
			log.severe("Come to submitForm");
			
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
