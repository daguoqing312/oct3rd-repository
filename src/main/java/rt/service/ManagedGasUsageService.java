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

import rt.model.entity.GasUsageInfo;
import rt.service.api.IGasUsageService;




@Named("unitDbService")
@RequestScoped
public class ManagedGasUsageService implements IGasUsageService{
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	@Inject
	private UserTransaction utx;		
	

	@Override
	public void saveGasUsageInfo(GasUsageInfo gasUsageInfo) throws Exception {
		try{
			try{
				utx.begin();
				log.info("Register a new gasUsageInfo: id-" + gasUsageInfo.getId());
			    em.persist(gasUsageInfo);
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
	public List<GasUsageInfo> findAllGasUsageInfo() throws Exception {
		try {
			try{
				utx.begin();
				Query query = em.createQuery("Select c From gasUsageInfo c");		
				List<GasUsageInfo> result = query.getResultList();
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
	public GasUsageInfo findGasUsageInfo(GasUsageInfo gasUsageInfo) throws Exception {
		GasUsageInfo result ;
	      try{
	    	  try{
	    		  utx.begin();	    		  
	    	      String id = gasUsageInfo.getId();
	    	      String queryStatement = "Select u From GasUsageInfo u Where u.id= :id";
	    	      Query query = em.createQuery(queryStatement);
	    	      query.setParameter("id", id);	    		  
	    			
	    	      List<GasUsageInfo> results = query.getResultList();
	    	      
	    	      if (results.isEmpty()) {
	    	    	  log.info("No such a gasUsageInfo with the id:" + id);
	    	    	  result = null;
	    	      } else if (results.size() > 1) {
	    	         log.info("More than one gasUsageInfo have the same id!");
	    	         result =results.get(0);
	    	      } else {
	    	    	  log.info("Find the gasUsageInfo with the id.");
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
