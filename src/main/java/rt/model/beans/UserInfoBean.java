package rt.model.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import rt.model.entity.UserInfo;

@RequestScoped
public class UserInfoBean {

	public static int id = 1;
	
	private UserInfo newUserInfo;

	@Named
	@Produces
	public UserInfo getNewUserInfo() {
		return newUserInfo;
	}


	public void setNewUserInfo(UserInfo newUserInfo) {
		this.newUserInfo = newUserInfo;
	}


	@PostConstruct
	public void init(){
		newUserInfo = new UserInfo();
		newUserInfo.setId(String.valueOf(id));
	}
}
