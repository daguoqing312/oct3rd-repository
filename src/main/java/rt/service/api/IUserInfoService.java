package rt.service.api;

import java.util.List;


import rt.model.entity.UserInfo;


public interface IUserInfoService {
	public void saveUser(UserInfo userInfo) throws Exception ;
		
	public List<UserInfo> findAllUsers()throws Exception;
	
	public UserInfo findUserInfo(UserInfo user) throws Exception ;
	
	public boolean findTheUser(UserInfo usr)throws Exception;	
	
	public boolean findUser(UserInfo usr) throws Exception ;
	
}
