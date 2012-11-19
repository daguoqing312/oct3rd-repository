package rt.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import rt.model.beans.UserInfoBean;
import rt.model.data.SubmittedStatus;
import rt.model.entity.UserInfo;
import rt.service.api.IUserInfoService;

@ApplicationScoped
public class UserInfoModel {
	
	@Inject
	private UserInfoBean userInfoBean;
	
	@Inject
	private IUserInfoService userInfoService;
	
	public SubmittedStatus doSubmit(){
		UserInfo newUserInfo = userInfoBean.getNewUserInfo();
		try{
			userInfoService.saveUser(newUserInfo);
			UserInfoBean.id++;
			userInfoBean.init();
		}catch (Exception e){
			return SubmittedStatus.Refused;
		}
		return SubmittedStatus.Accepted;
	}
	
	
}
