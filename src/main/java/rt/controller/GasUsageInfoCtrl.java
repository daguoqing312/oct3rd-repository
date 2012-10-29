package rt.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import rt.model.entity.GasUsageInfo;
import rt.service.api.IGasUsageService;

@Model
public class GasUsageInfoCtrl {

	@Inject
	private FacesContext facesContext;

	@Inject
	private Logger log;

	private GasUsageInfo newGasUsageInfo;
	private static int id = 1;

	@Inject
	private IGasUsageService unitDBService;

	public void submit() throws Exception {
		try {
			unitDBService.saveGasUsageInfo(newGasUsageInfo);
			log.info("Saved:" + newGasUsageInfo.getId());
			
		} catch (Exception e) {
			log.info("Gas usage failed");
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Submit failed.",
					"Database problem."));
		}
		

		id++;
		initGasUsageInfo();
		log.info("Gas usage saved.");
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO,
				"GasUsage info submitted successfully.",
				"No detailed info about unit."));
	}

	@PostConstruct
	public void initGasUsageInfo() {
		newGasUsageInfo = new GasUsageInfo();
		newGasUsageInfo.setId(id);
	}

	@Produces
	@Named
	public GasUsageInfo getNewGasUsageInfo() {
		return newGasUsageInfo;
	}

	public void setNewGasUsageInfo(GasUsageInfo newGasUsageInfo) {
		this.newGasUsageInfo = newGasUsageInfo;
	}

}
