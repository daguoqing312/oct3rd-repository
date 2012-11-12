package rt.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import rt.model.beans.GasUsageInfoBean;
import rt.model.data.SubmittedStatus;
import rt.model.entity.GasUsageInfo;
import rt.service.api.IGasUsageService;

@ApplicationScoped
public class GasUsageInfoModel {
	
	@Inject
	private IGasUsageService gasUsageService;
	
	@Inject 
	private GasUsageInfoBean gasUsageInfoBean;
	
	public SubmittedStatus doSubject(){
		GasUsageInfo newGasUsageInfo = gasUsageInfoBean.getNewGasUsageInfo();
		try{
			gasUsageService.saveGasUsageInfo(newGasUsageInfo);
			GasUsageInfoBean.id++;
		}catch (Exception e){
			return SubmittedStatus.Refused;
		}
		return SubmittedStatus.Accepted;
	}
}
