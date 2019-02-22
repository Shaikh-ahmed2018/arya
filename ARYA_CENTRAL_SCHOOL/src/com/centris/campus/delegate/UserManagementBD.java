package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.service.UserManagementService;
import com.centris.campus.serviceImpl.UserManagementServiceImpl;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementBD {

	public List<UserRecordVO> getUserRecordsBD() {
		
		UserManagementService  	userManagementService = new  UserManagementServiceImpl();
		
		return userManagementService.getUserRecordsService();
	}


	public List<UserRecordVO> getSearchUserDetailsBD(UserManagementPojo userManagementPojo) {

	   UserManagementService  	userManagementService = new  UserManagementServiceImpl();
		
	 return userManagementService.getSearchUserDetailsService(userManagementPojo);

	}


	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo) {
		
		  UserManagementService  	userManagementService = new  UserManagementServiceImpl();
			
		return userManagementService.getUserDeatils(userManagementPojo);
	}


	public String changePassword(UserManagementPojo userManagementPojo) {
		
		  UserManagementService  	userManagementService = new  UserManagementServiceImpl();
			
		 return userManagementService.changePassword(userManagementPojo);
	}


	public String blockUser(UserManagementPojo userManagementPojo) {
		
		  UserManagementService  	userManagementService = new  UserManagementServiceImpl();
			
			 return userManagementService.blockUser(userManagementPojo);
	}

	
}
