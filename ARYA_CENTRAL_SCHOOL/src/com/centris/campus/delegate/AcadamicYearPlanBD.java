package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.service.AcadamicYearPlanService;
import com.centris.campus.serviceImpl.AcadamicYearPlanServiceImpl;
import com.centris.campus.vo.AcadamicYearPlanVO;

public class AcadamicYearPlanBD {

	static AcadamicYearPlanService acadamicYearPlanService;
	static{
		acadamicYearPlanService = new AcadamicYearPlanServiceImpl();
	}
	
	public String insertAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanService.insertAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	public AcadamicYearPlanVO editAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanService.editAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	public ArrayList<AcadamicYearPlanVO> getSearchDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanService.getSearchDetails(acadamicYearPlanPOJO);
	}
	public ArrayList<AcadamicYearPlanVO> getAllEventDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanService.getAllEventDetails(acadamicYearPlanPOJO);
	}
	public String deleteAcadamicYearPlan(String[] event_code) {
		return acadamicYearPlanService.deleteAcadamicYearPlan(event_code);
	}
	public String updateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanService.updateAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	public String validateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanService.validateAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	public String validateAcadamicYearPlanUpdate(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanService.validateAcadamicYearPlanUpdate(acadamicYearPlanPOJO);
	}

}
