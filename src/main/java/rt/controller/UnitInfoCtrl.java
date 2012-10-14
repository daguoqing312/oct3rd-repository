package rt.controller;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import rt.model.entity.UnitInfo;
import rt.service.api.IUnitDatabaseService;


@Model
public class UnitInfoCtrl {

	@Inject
	private FacesContext facesContext;	
		
	@Inject
	private Logger log;
	
	private UnitInfo newUnitInfo;
	private static int id=1;
	
	@Inject 
	private IUnitDatabaseService unitDBService;
	
	public void submit() throws Exception{
		try{
			unitDBService.saveUnit(newUnitInfo);
		}catch (Exception e){			
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Submit failed.", "Database problem."));
		}		
		finally{
			id++;
			initUnitInfo();			
		}		
		
		log.info("Saved:"+newUnitInfo.getId());
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Unit info submitted successfully.", "No detailed info about unit."));
	}	
	
	@PostConstruct
	public void initUnitInfo(){
		newUnitInfo = new UnitInfo();
		newUnitInfo.setId(id);
	}

	@Produces
	@Named
	public UnitInfo getNewUnitInfo() {
		return newUnitInfo;
	}

	public void setNewUnitInfo(UnitInfo newUnitInfo) {
		this.newUnitInfo = newUnitInfo;
	}

	
}
