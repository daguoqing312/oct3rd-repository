package rt.model.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import rt.model.entity.GasUsageInfo;

@RequestScoped
public class GasUsageInfoBean {
	private int id=1;
	
	private GasUsageInfo newGasUsageInfo;

	public GasUsageInfo getNewGasUsageInfo() {
		return newGasUsageInfo;
	}

	public void setNewGasUsageInfo(GasUsageInfo newGasUsageInfo) {
		this.newGasUsageInfo = newGasUsageInfo;
	}
	
	@PostConstruct
	public void init(){
		newGasUsageInfo = new GasUsageInfo();
		newGasUsageInfo.setId(String.valueOf(id));
	}
}
