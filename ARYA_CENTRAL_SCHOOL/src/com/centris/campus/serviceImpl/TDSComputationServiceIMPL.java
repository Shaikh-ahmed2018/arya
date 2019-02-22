package com.centris.campus.serviceImpl;

import com.centris.campus.dao.TDSComputationDAO;
import com.centris.campus.daoImpl.TDSComputationDAOIMPL;
import com.centris.campus.service.TDSComputationService;
import com.centris.campus.vo.StaffEmployementVo;

public class TDSComputationServiceIMPL implements TDSComputationService {


	TDSComputationDAO dao = new TDSComputationDAOIMPL();
	
	public StaffEmployementVo getEmployeeDetails(String user,String currentUserId,String academic_year) {
		// TODO Auto-generated method stub
		return dao.getEmployeeDetails(user,currentUserId,academic_year);
	}

	@Override
	public StaffEmployementVo getStaffMaximumLimitDetails(String academic_year,String location) {
		// TODO Auto-generated method stub
		return dao.getStaffMaximumLimitDetails(academic_year,location);
	}

}
