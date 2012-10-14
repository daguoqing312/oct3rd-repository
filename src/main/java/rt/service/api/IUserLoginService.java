package rt.service.api;

import java.util.List;
import rt.model.entity.User;

public interface IUserLoginService {
	public void saveUser(User user) throws Exception ;
		
	public List<User> findAllUsers()throws Exception;
	
	public boolean findTheUser(User user)throws Exception;	
	
	public boolean findUser(User user) throws Exception ;
	
}
