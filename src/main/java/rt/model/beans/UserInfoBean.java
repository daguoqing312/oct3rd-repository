package rt.model.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import rt.model.entity.UserInfo;

@RequestScoped
public class UserInfoBean {

	static int id = 1;
	
	private UserInfo newUserInfo;

	
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
