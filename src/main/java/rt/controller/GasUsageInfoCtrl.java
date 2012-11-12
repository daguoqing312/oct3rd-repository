package rt.controller;

import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import rt.model.GasUsageInfoModel;


@Model
public class GasUsageInfoCtrl {	
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private Logger log;

	@Inject
	private GasUsageInfoModel gasUsageInfoModel;
	
	public void submit(){
		switch (gasUsageInfoModel.doSubject()){
		case Accepted:
			log.info("The form submitted.");
			break;
		case Refused:
			log.info("The form is refused.");
			break;		
		}
		
	}
}
