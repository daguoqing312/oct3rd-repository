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

import rt.model.entity.User;
import rt.service.api.IUserLoginService;

@Model
public class UserController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private IUserLoginService userService;
	
	@Inject
	private Logger log;
	
	
	private User newUser;
	private boolean registerResult = false;
	
	
	@Produces
	@Named
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser){
		this.newUser = newUser;
	}
	
	
	public String register(){
		log.info("UserController-register:"+ newUser.toString());
		
		try{
			setRegisterResult(userService.findUser(newUser));
			if (isRegisterResult()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
			}else{
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "failed to Register!", "Registration failed"));
			}
			initNewUser();
		}catch (Exception e){
			log.severe("Error:"+e.getMessage());
		}
		return "registerResult";
	}
	
	@PostConstruct
	public void initNewUser()
	{
		newUser = new User();
	}

	public boolean isRegisterResult() {
		return registerResult;
	}

	public void setRegisterResult(boolean registerResult) {
		this.registerResult = registerResult;
	}
}
