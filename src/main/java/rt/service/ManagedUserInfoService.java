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



@Named("customerDbService")
@RequestScoped
public class ManagedUserInfoService implements IUserInfoService{
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	@Inject
	private UserTransaction utx;		
	

	@Override
	public void saveCustomer(UserInfo userInfo) throws Exception {
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
	public List<UserInfo> findAllCustomers() throws Exception {
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
	public UserInfo findCustomer(UserInfo customer) throws Exception {
		UserInfo result ;
	      try{
	    	  try{
	    		  utx.begin();	    		  
	    	      String id = customer.getId();
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
}
