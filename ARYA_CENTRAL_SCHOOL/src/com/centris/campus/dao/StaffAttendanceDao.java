package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceDao {
	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department);
	public String updateAttendanceStatus(StaffAttendancePojo attPojo);
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate);

}
