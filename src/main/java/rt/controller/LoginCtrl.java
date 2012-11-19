package rt.controller;



import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import rt.model.entity.UserInfo;
import rt.service.api.IUserInfoService;

@Model
public class LoginCtrl {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private IUserInfoService userInfoService;
	
	@Inject
	private Logger log;
	
	
	private UserInfo newUser;
	private boolean registerResult = false;
	
	@Produces
	@Named
	public UserInfo getNewUser() {
		return newUser;
	}

	public void setNewUser(UserInfo newUser){
		this.newUser = newUser;
	}
	
	
	public String login(){
		log.info("LoginCtrl-login:"+ newUser.toString());
		
		try{
			setRegisterResult(userInfoService.findUser(newUser));
			if (isRegisterResult()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
			}else{
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "failed to Register!", "Registration failed"));
			}
			initNewUser();
		}catch (Exception e){
			log.info("Error:"+e.getMessage());
		}
		return "registerResult";
	}
	
	@PostConstruct
	public void initNewUser()
	{
		newUser = new UserInfo();
	}

	public boolean isRegisterResult() {
		return registerResult;
	}

	public void setRegisterResult(boolean registerResult) {
		this.registerResult = registerResult;
	}
}
