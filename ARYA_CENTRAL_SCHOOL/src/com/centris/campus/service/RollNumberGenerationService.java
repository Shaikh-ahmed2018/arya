package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.RollnumberGenerationVo;

public interface RollNumberGenerationService {

	List<RollnumberGenerationVo> getlanguagedetails(String acy_yearid,
			String location_id);

	List<RollnumberGenerationVo> getSecondlanguageofclass(String classidVal,
			String sectionidVal, String schoolLocation);

}
