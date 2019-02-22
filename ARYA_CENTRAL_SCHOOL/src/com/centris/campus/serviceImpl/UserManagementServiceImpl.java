package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.UserManagementDao;
import com.centris.campus.daoImpl.UserManagementDaoImpl;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.service.UserManagementService;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementServiceImpl implements UserManagementService {


	public List<UserRecordVO> getUserRecordsService() {
		
	    UserManagementDao userManagementDao = new UserManagementDaoImpl();
		
		return userManagementDao.getUserRecordsDao();
	}


	public List<UserRecordVO> getSearchUserDetailsService(UserManagementPojo userManagementPojo) {
	
		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			return userManagementDao.getSearchUserDetailsDao(userManagementPojo);
	}



	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo) {

		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
	 return userManagementDao.getUserDeatils(userManagementPojo);
	
	}



	public String changePassword(UserManagementPojo userManagementPojo) {
		
		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			 return userManagementDao.changePassword(userManagementPojo);
	}



	public String blockUser(UserManagementPojo userManagementPojo) {
		
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			 return userManagementDao.blockUser(userManagementPojo);
	}

}
