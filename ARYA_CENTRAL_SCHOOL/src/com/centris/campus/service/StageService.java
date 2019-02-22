package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.vo.AddStageVO;

public interface StageService
{
	
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo);

	public String insertStage(AddStageForm aform);

	public AddStageForm EditStageDetails(AddStageForm aform);

	public String deleteStage(String[] stageid);
	
	public boolean getstagecount(AddStageVO vo);

}
