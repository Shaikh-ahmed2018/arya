package com.centris.campus.delegate;

import java.util.ArrayList;
import com.centris.campus.pojo.StageFeeSetupPojo;
import com.centris.campus.service.StageFeeSetupService;
import com.centris.campus.serviceImpl.StageFeeSetupServiceImpl;
import com.centris.campus.vo.StageFeeSetupVo;

public class StageFeeSetupBD {
	
	
	public ArrayList<StageFeeSetupVo> getStageFeeSetupDetails(String currentaccyear) {
		StageFeeSetupService service = new StageFeeSetupServiceImpl();
		return service.getStageFeeSetupDetails(currentaccyear);
	}
	
	public ArrayList<StageFeeSetupVo> getSearchStageFeeSetupDetails(String searchterm,String currentaccyear) {
		StageFeeSetupService service = new StageFeeSetupServiceImpl();
		return service.getSearchStageFeeSetupDetails(searchterm,currentaccyear);
	}
	
	public ArrayList<StageFeeSetupVo> getApprovedStages(StageFeeSetupPojo feeSetupPojo) {

		StageFeeSetupService service = new StageFeeSetupServiceImpl();

		return service.getApprovedStages(feeSetupPojo);
	}
	
	public ArrayList<StageFeeSetupVo> getAllStages(StageFeeSetupPojo feeSetupPojo) {

		StageFeeSetupService service = new StageFeeSetupServiceImpl();

		return service.getAllStages(feeSetupPojo);
	}
	
	public int insertApproveFees(ArrayList<StageFeeSetupPojo> approvefeelist) {

		StageFeeSetupService service = new StageFeeSetupServiceImpl();

		return service.insertApproveFees(approvefeelist);
	}
	
	public boolean deleteFees(StageFeeSetupPojo feeSetupPojo) {

		StageFeeSetupService feeSetupService = new StageFeeSetupServiceImpl();

		return feeSetupService.deleteFees(feeSetupPojo);
	}

	public boolean insertFeeAmount(ArrayList<StageFeeSetupPojo> feeSetupList) {

		StageFeeSetupService feeSetupService = new StageFeeSetupServiceImpl();

		return feeSetupService.insertFeeAmount(feeSetupList);
	}

}
