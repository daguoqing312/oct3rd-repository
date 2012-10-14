package rt.service.api;

import java.util.List;

import rt.model.entity.GasUsageInfo;


public interface IGasUsageService {
	
	public void saveGasUsageInfo(GasUsageInfo gasUsageInfo) throws Exception ;
		
	public List<GasUsageInfo> findAllGasUsageInfo()throws Exception;
	
	public GasUsageInfo findGasUsageInfo(GasUsageInfo gasUsageInfo) throws Exception ;
	
}
