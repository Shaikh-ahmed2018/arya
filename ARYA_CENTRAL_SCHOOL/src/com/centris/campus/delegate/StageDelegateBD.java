package com.centris.campus.delegate;
import java.util.ArrayList;
import java.util.List;
import com.centris.campus.forms.AddStageForm;

import com.centris.campus.serviceImpl.StageServiceIMPL;

import com.centris.campus.vo.AddStageVO;


public class StageDelegateBD {

	public  ArrayList<AddStageVO> StageDetails(String accyear){
		
		
			return new StageServiceIMPL().StageDetails(accyear);
		
	}
	
	public String insertStage(AddStageForm aform){
		
	

			return new StageServiceIMPL().insertStage(aform);
		
	}
	
	public AddStageForm EditStageDetails(AddStageForm aform){
		
		

			return new StageServiceIMPL().EditStageDetails(aform);
	}
	
	public String deleteStage(String[] stageid) 
	{
		

		return new StageServiceIMPL().deleteStage(stageid);
	}

public boolean getstagecount(AddStageVO vo) {
	
	return new StageServiceIMPL().getstagecount(vo);
}

public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo) {
	return new StageServiceIMPL().SelectAllSatges(vo);
	
}

public List<AddStageVO> searchStage(String searchName, String accyear) {
	return new StageServiceIMPL().searchStage(searchName,accyear);
}

	
}
