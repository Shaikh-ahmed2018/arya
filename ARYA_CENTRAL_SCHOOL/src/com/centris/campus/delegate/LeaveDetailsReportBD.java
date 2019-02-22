package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.LeaveDetailsReportForm;
import com.centris.campus.service.LeaveDetailsReportService;
import com.centris.campus.serviceImpl.LeaveDetailsReportServiceImpl;
import com.centris.campus.vo.LeaveDetailsReportVo;
import com.centris.campus.vo.TeacherVo;

public class LeaveDetailsReportBD {
	
	public  ArrayList<LeaveDetailsReportVo> getLeaveDetails(LeaveDetailsReportForm leaveDetailsReportVo){
		
			LeaveDetailsReportService leaveReport=new LeaveDetailsReportServiceImpl();
		
		return leaveReport.getLeaveDetails(leaveDetailsReportVo);
	}
	
	public  ArrayList<TeacherVo> getTeachername(String teachingtype,String dept){
		
		LeaveDetailsReportService leaveReport=new LeaveDetailsReportServiceImpl();
	
		return leaveReport.getTeachername(teachingtype,dept);
	}
	

}
