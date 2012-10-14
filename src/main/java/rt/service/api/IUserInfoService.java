package rt.service.api;

import java.util.List;

import rt.model.entity.UserInfo;


public interface IUserInfoService {
	public void saveCustomer(UserInfo userInfo) throws Exception ;
		
	public List<UserInfo> findAllCustomers()throws Exception;
	
	public UserInfo findCustomer(UserInfo user) throws Exception ;
	
}
