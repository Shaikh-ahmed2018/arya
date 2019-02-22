package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceService {
	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department);
	public String updateAttendanceStatus(StaffAttendancePojo attPojo);
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate);

}
