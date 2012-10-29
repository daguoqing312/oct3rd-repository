package rt.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;


import rt.model.entity.User;
import rt.service.api.IUserLoginService;


@Named("dbService")
@RequestScoped
public class ManagedUserLoginService implements IUserLoginService{
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	@Inject
	private UserTransaction utx;
	
	public void saveUser(User user) throws Exception {
		try{
			try{
				utx.begin();
				log.info("User registed.");
			    em.persist(user);
			}finally{
				utx.commit();
			}			
		}catch (Exception e){
			utx.rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<User> findAllUsers() throws Exception {
		try {
			try{
				utx.begin();
				Query query = em.createQuery("Select e From User e");		
				List<User> result = query.getResultList();
				return result;
			}finally {
				utx.commit();
			}
		}catch (Exception e){
			utx.rollback();
	        throw e;
		}		
	}	
	
	public boolean findTheUser(User user) throws Exception{
		try{
			try{
				utx.begin();
				String queryStatement = "Select u From User u Where u.email= :email and u.password = :password";
				Query query = em.createQuery(queryStatement);
				query.setParameter("email", user.getEmail());
				query.setParameter("password", user.getPassword());
				int resultInt = query.getResultList().size();
				log.info("Line40 Results:" + resultInt);
				return resultInt>0;
			}finally{
				utx.commit();
			}
		}catch (Exception e){
			utx.rollback();
			throw e;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public boolean findUser(User user) throws Exception {
		boolean result = false;
	      try{
	    	  try{
	    		  utx.begin();
	    		  String email= user.getEmail();
	    	      String password = user.getPassword();
	    	      String queryStatement = "Select u From User u Where u.email= :email and u.password = :password";
	    	      Query query = em.createQuery(queryStatement);
	    	      query.setParameter("email", email);
	    		  query.setParameter("password", password);
	    			
	    	      List<User> results = query.getResultList();
	    	      
	    	      if (results.isEmpty()) {
	    	    	  log.info("Line56: Email or Password may be wrong.");	    	         
	    	      } else if (results.size() > 1) {
	    	         throw new IllegalStateException("Cannot have more than one user with the same username!");
	    	      } else {
	    	    	  log.info("Line61: Your are logged in.");
	    	         result =true;
	    	      }
	    	      return result;
	    	  }finally {
	    		  utx.commit();
	    	   }
	     }catch (Exception e){
	    	 utx.rollback();
	    	 throw e;
	     }
	     
	 }
}
