package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.RollNumberGenerationService;
import com.centris.campus.serviceImpl.RollNumberGenerationServiceImpl;
import com.centris.campus.vo.RollnumberGenerationVo;

public class RollnumberGenerationBD {
	 
	RollNumberGenerationService rollnumber = new RollNumberGenerationServiceImpl();

	public List<RollnumberGenerationVo> getlanguagedetails(String acy_yearid,
			String location_id) {
		 return rollnumber.getlanguagedetails(acy_yearid,location_id);
	}

	public java.util.List<RollnumberGenerationVo> getSecondlanguageofclass(
			String classidVal, String sectionidVal, String schoolLocation) {
		return rollnumber.getSecondlanguageofclass(classidVal,sectionidVal,schoolLocation);
	}

}
