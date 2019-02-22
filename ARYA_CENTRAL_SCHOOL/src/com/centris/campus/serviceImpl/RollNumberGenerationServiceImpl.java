package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.RoleNumberGenerationDao;
import com.centris.campus.daoImpl.RoleNumberGenerationDaoImpl;
import com.centris.campus.service.RollNumberGenerationService;
import com.centris.campus.vo.RollnumberGenerationVo;

public class RollNumberGenerationServiceImpl implements RollNumberGenerationService{
	RoleNumberGenerationDao dao = new RoleNumberGenerationDaoImpl();
	@Override
	public List<RollnumberGenerationVo> getlanguagedetails(String acy_yearid,
			String location_id) {
		
		return dao.getlanguagedetails(acy_yearid, location_id);
	}
	@Override
	public List<RollnumberGenerationVo> getSecondlanguageofclass(
			String classidVal, String sectionidVal, String schoolLocation) {
		return dao.getSecondlanguageofclass(classidVal, sectionidVal,schoolLocation);
	}

}
