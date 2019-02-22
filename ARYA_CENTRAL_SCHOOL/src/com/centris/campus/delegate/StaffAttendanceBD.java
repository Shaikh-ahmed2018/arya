package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.service.StaffAttendanceService;
import com.centris.campus.serviceImpl.StaffAttendanceServiceImpl;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceBD {
	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department) {
			
			StaffAttendanceService service=new StaffAttendanceServiceImpl();
			
			return service.getStaffAttendance(date, department);
	}
	
	public String updateAttendanceStatus(StaffAttendancePojo attPojo) {
		
		StaffAttendanceService service=new StaffAttendanceServiceImpl();
		
		return service.updateAttendanceStatus(attPojo);
	}
	
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate) {
		
		StaffAttendanceService service=new StaffAttendanceServiceImpl();
		
		return service.getStaffAttendanceList(startdate,enddate);
	}

}
