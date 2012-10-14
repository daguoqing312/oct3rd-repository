package rt.controller;


import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import rt.model.entity.CustomerInfo;
import rt.service.api.ICustomerDatabaseService;


@Model
public class CustomerInfoCtrl {

	@Inject
	private FacesContext facesContext;	
		
	@Inject
	private Logger log;
	
	private CustomerInfo newCustomerInfo;
	private CustomerInfo searchCustomerInfo;
	private List<CustomerInfo> customerInfoList;
	
	private static int id=1;
	
	@Inject 
	private ICustomerDatabaseService customerDBService;
	
	public void submit() throws Exception{
		try{
			customerDBService.saveCustomer(newCustomerInfo);
		}catch (Exception e){			
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Submit failed.", "Database problem."));
		}		
		finally{
			id++;
			initCustomerInfo();			
		}		
		
		log.info("Saved:"+newCustomerInfo.getId());
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer info submitted successfully.", "No detailed info about customer."));
	}	
	
	@PostConstruct
	public void initCustomerInfo(){
		newCustomerInfo = new CustomerInfo();
		newCustomerInfo.setId(id);
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
	public CustomerInfo getSearchCustomerInfo() {
		return searchCustomerInfo;
	}
	
	public void search() throws Exception {
		try{
			
		}catch(Exception e){
			
		}
	}
	
	public List<CustomerInfo> getAllCustomerInfo() throws Exception {
		List<CustomerInfo> rtnList = null;
		try{
			rtnList = customerDBService.findAllCustomers();			
		}catch(Exception e){
			facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database error, cannot fetch all customer info.","No detail info provided."));
		}		
		return rtnList;
	}
	
	//Find id in some type list.
	public  List<CustomerInfo> searchElementId(List<CustomerInfo>someList, int id){
		List<CustomerInfo> rtnList = null;		
		Iterator<CustomerInfo>iterator = someList.iterator();
		while (iterator.hasNext()){
			
		}	
		
		return rtnList;
	}
}
