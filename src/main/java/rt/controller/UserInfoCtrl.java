package rt.controller;


import java.util.logging.Logger;


import javax.enterprise.inject.Model;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import rt.model.UserInfoModel;



@Model
public class UserInfoCtrl {

	@Inject
	private FacesContext facesContext;	
		
	@Inject
	private Logger log;
	
	@Inject
	private UserInfoModel userInfoModel;
	
	public void submit(){
		switch (userInfoModel.doSubmit()){
		case Accepted:
			log.info("The form has been submitted.");		
			break;
		case Refused:
			log.info("The form is refused.");
		}
	}
}
