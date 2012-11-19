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


import rt.model.entity.UserInfo;
import rt.service.api.IUserInfoService;



@Named("userDbService")
@RequestScoped
public class ManagedUserInfoService implements IUserInfoService{
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	@Inject
	private UserTransaction utx;		
	

	@Override
	public void saveUser(UserInfo userInfo) throws Exception {
		try{
			try{
				utx.begin();
				log.info("Register a new customer: id-" + userInfo.getId()+", name-"+userInfo.getName());
			    em.persist(userInfo);
			}finally{
				utx.commit();
			}			
		}catch (Exception e){
			utx.rollback();
			throw e;
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<UserInfo> findAllUsers() throws Exception {
		try {
			try{
				utx.begin();
				Query query = em.createQuery("Select u From userInfo u");		
				List<UserInfo> result = query.getResultList();
				return result;
			}finally {
				utx.commit();
			}
		}catch (Exception e){
			utx.rollback();
	        throw e;
		}	
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserInfo findUserInfo(UserInfo user) throws Exception {
		UserInfo result ;
	      try{
	    	  try{
	    		  utx.begin();	    		  
	    	      String id = user.getId();
	    	      String queryStatement = "Select u From UserInfo u Where u.id= :id";
	    	      Query query = em.createQuery(queryStatement);
	    	      query.setParameter("id", id);	    		  
	    			
	    	      List<UserInfo> results = query.getResultList();
	    	      
	    	      if (results.isEmpty()) {
	    	    	  log.info("No such a customer with the phone:" + id);
	    	    	  result = null;
	    	      } else if (results.size() > 1) {
	    	         log.info("More than one customer have the same phone!");
	    	         result =results.get(0);
	    	      } else {
	    	    	  log.info("Find the customer with the phone.");
	    	         result =results.get(0);
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
	
	public boolean findTheUser(UserInfo user){		
		String queryStatement = "Select u From UserInfo u Where  u.password = :password";
		Query query = em.createQuery(queryStatement);
		query.setParameter("password", user.getPassword());
		int resultInt = query.getResultList().size();
		log.info("Line40 Results:" + resultInt);
		return resultInt>0;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findUser(UserInfo user) throws Exception {
	      String email= user.getEmail();
	      String password = user.getPassword();
	      String queryStatement = "Select u From UserInfo u Where u.password = :password";
	      Query query = em.createQuery(queryStatement);
		  query.setParameter("password", password);
			
	      List<UserInfo> results = query.getResultList();
	      
	      if (results.isEmpty()) {
	    	  log.info("Line62: Email or Password may be wrong.");
	         return false;
	      } else if (results.size() > 1) {
	    	  log.info("Line65, Error"+results.size());
	         throw new IllegalStateException("Cannot have more than one user with the same username!");
	      } else {
	    	  log.info("Line68: Your are logged in.");
	         return true;
	      }
	 }
	
}
