package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.vo.UserRecordVO;

public interface UserManagementDao {

	List<UserRecordVO> getUserRecordsDao();

	List<UserRecordVO> getSearchUserDetailsDao(UserManagementPojo userManagementPojo);

	UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo);

	String changePassword(UserManagementPojo userManagementPojo);

	String blockUser(UserManagementPojo userManagementPojo);

}
