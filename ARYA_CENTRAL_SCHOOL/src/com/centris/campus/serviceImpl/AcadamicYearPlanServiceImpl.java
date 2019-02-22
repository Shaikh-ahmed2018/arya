package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.AcadamicYearPlanDao;
import com.centris.campus.daoImpl.AcadamicYearPlanDaoImpl;
import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.service.AcadamicYearPlanService;
import com.centris.campus.vo.AcadamicYearPlanVO;

public class AcadamicYearPlanServiceImpl implements AcadamicYearPlanService{

	static AcadamicYearPlanDao  acadamicYearPlanDao;
	static{
		acadamicYearPlanDao = new AcadamicYearPlanDaoImpl();
	}
	
	public String insertAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanDao.insertAcadamicYearPlan(acadamicYearPlanPOJO);
	}

	public AcadamicYearPlanVO editAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanDao.editAcadamicYearPlan(acadamicYearPlanPOJO);
	}

	public ArrayList<AcadamicYearPlanVO> getSearchDetails(
			AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanDao.getSearchDetails(acadamicYearPlanPOJO);
	}

	public ArrayList<AcadamicYearPlanVO> getAllEventDetails(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanDao.getAllEventDetails(acadamicYearPlanPOJO);
	}
	
	public String deleteAcadamicYearPlan(String[] event_code) {
			return acadamicYearPlanDao.deleteAcadamicYearPlan(event_code);
	}
	
	public String updateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanDao.updateAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	
	public String validateAcadamicYearPlan(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
		return acadamicYearPlanDao.validateAcadamicYearPlan(acadamicYearPlanPOJO);
	}
	
	public String validateAcadamicYearPlanUpdate(AcadamicYearPlanPOJO acadamicYearPlanPOJO) {
			return acadamicYearPlanDao.validateAcadamicYearPlanUpdate(acadamicYearPlanPOJO);
	}

}
