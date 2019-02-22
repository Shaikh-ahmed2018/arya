package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AddStageVO;

public interface StageDAO

{
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo);
	public String insertstage(AddStagePojo apojo);
	public String updatestage(AddStagePojo apojo);
	public AddStageForm EditStageDetails(AddStageForm aform);
	public String deleteStage(String[] stageid);
	public boolean getstagecount(AddStageVO vo);


}
