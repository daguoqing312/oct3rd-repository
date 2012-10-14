package rt.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
//import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import rt.model.entity.User;
import rt.service.api.IUserLoginService;


@Named
@Alternative
@Stateless
public class UserLoginService implements IUserLoginService{
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;	
	
	public void saveUser(User user) throws Exception {
		log.info("User registed: " + user.getEmail());
	    em.persist(user);	    
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(){
		Query query = em.createQuery("Select u From User u");		
		List<User> result = query.getResultList();
		return result;
	}
	
	public boolean findTheUser(User user){		
		String queryStatement = "Select e From User e Where e.email= :email and e.password = :password";
		Query query = em.createQuery(queryStatement);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		int resultInt = query.getResultList().size();
		log.info("Line40 Results:" + resultInt);
		return resultInt>0;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findUser(User user) throws Exception {
	      String email= user.getEmail();
	      String password = user.getPassword();
	      String queryStatement = "Select e From User e Where e.email= :email and e.password = :password";
	      Query query = em.createQuery(queryStatement);
	      query.setParameter("email", email);
		  query.setParameter("password", password);
			
	      List<User> results = query.getResultList();
	      
	      if (results.isEmpty()) {	    	  
	         return false;
	      } else  {
	    	  log.info("You are logged in.");
	    	 return true;	         
	      } 
	 }
	
	
}
