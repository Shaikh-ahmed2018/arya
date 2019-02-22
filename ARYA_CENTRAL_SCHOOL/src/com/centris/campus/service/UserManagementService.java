package com.centris.campus.service;

import java.util.List;

import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.vo.UserRecordVO;

public interface UserManagementService {

	List<UserRecordVO> getUserRecordsService();

	List<UserRecordVO> getSearchUserDetailsService(UserManagementPojo userManagementPojo);

	UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo);

	String changePassword(UserManagementPojo userManagementPojo);

	String blockUser(UserManagementPojo userManagementPojo);

}
