package com.centris.campus.service;

import java.util.ArrayList;
import com.centris.campus.pojo.StageFeeSetupPojo;
import com.centris.campus.vo.StageFeeSetupVo;

public interface StageFeeSetupService {

	public ArrayList<StageFeeSetupVo> getStageFeeSetupDetails(String currentaccyear);
	public ArrayList<StageFeeSetupVo> getSearchStageFeeSetupDetails(String searchterm,String currentaccyear);
	public ArrayList<StageFeeSetupVo> getApprovedStages(StageFeeSetupPojo feeSetupPojo);
	public ArrayList<StageFeeSetupVo> getAllStages(StageFeeSetupPojo feeSetupPojo);
	public int insertApproveFees(ArrayList<StageFeeSetupPojo> approvefeelist);
	public boolean deleteFees(StageFeeSetupPojo feeSetupPojo);
	public boolean insertFeeAmount(ArrayList<StageFeeSetupPojo> feeSetupList);
	
	
}
