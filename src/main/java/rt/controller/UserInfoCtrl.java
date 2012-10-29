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


import rt.model.entity.UserInfo;
import rt.service.api.IUserInfoService;



@Model
public class UserInfoCtrl {

	@Inject
	private FacesContext facesContext;	
		
	@Inject
	private Logger log;
	
	private UserInfo newUserInfo;
	private UserInfo searchUserInfo;
	private List<UserInfo> UserInfoList;
	
	private static int id=1;
	
	@Inject 
	private IUserInfoService userInfoService;
	
	public void submit() throws Exception{
		try{
			userInfoService.saveCustomer(newUserInfo);
			log.info("The new user Saved");
		}catch (Exception e){
			log.info("Failed");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Submit failed.", "Database problem."));
		}		
		finally{
			id++;
			initUserInfo();			
		}		
		
		log.info("Saved:"+newUserInfo.getId());
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer info submitted successfully.", "No detailed info about customer."));
	}	
	
	@PostConstruct
	public void initUserInfo(){
		newUserInfo = new UserInfo();
		newUserInfo.setId(id);
	}

	@Produces
	@Named
	public UserInfo getNewUserInfo() {
		return newUserInfo;
	}

	public void setNewUserInfo(UserInfo newUserInfo) {
		this.newUserInfo = newUserInfo;
	}

	@Produces
	@Named
	public UserInfo getSearchUserInfo() {
		return searchUserInfo;
	}
	
	public void search() throws Exception {
		try{
			
		}catch(Exception e){
			
		}
	}
	
	public List<UserInfo> getAllUserInfo() throws Exception {
		List<UserInfo> rtnList = null;
		try{
			rtnList = userInfoService.findAllCustomers();			
		}catch(Exception e){
			facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database error, cannot fetch all customer info.","No detail info provided."));
		}		
		return rtnList;
	}
	
	//Find id in some type list.
	public  List<UserInfo> searchElementId(List<UserInfo>someList, int id){
		List<UserInfo> rtnList = null;		
		Iterator<UserInfo>iterator = someList.iterator();
		while (iterator.hasNext()){
			
		}	
		
		return rtnList;
	}
}
