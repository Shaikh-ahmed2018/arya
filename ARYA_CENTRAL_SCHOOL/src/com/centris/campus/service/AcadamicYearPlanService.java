package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.vo.AcadamicYearPlanVO;

public interface AcadamicYearPlanService {

	String insertAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	AcadamicYearPlanVO editAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	ArrayList<AcadamicYearPlanVO> getSearchDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	ArrayList<AcadamicYearPlanVO> getAllEventDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	String deleteAcadamicYearPlan(String[] event_code);

	String updateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	String validateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

	String validateAcadamicYearPlanUpdate(AcadamicYearPlanPOJO acadamicYearPlanPOJO);

}
