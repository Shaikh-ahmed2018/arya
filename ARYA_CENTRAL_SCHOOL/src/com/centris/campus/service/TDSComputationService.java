package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.vo.StaffEmployementVo;

public interface TDSComputationService {

	StaffEmployementVo getEmployeeDetails(String user,String currentUserId, String academic_year);

	StaffEmployementVo getStaffMaximumLimitDetails(String academic_year, String location);

}
