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
	    	      String service_tag = gasUsageInfo.getService_tag();
	    	      String queryStatement = "Select u From GasUsageInfo u Where u.service_tag= :service_tag";
	    	      Query query = em.createQuery(queryStatement);
	    	      query.setParameter("service_tag", service_tag);	    		  
	    			
	    	      List<GasUsageInfo> results = query.getResultList();
	    	      
	    	      if (results.isEmpty()) {
	    	    	  log.info("No such a gasUsageInfo with the service_tag:" + service_tag);
	    	    	  result = null;
	    	      } else if (results.size() > 1) {
	    	         log.info("More than one gasUsageInfo have the same service_tag!");
	    	         result =results.get(0);
	    	      } else {
	    	    	  log.info("Find the gasUsageInfo with the service_tag.");
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
