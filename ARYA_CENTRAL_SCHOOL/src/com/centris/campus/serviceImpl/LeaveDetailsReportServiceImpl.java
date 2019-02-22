package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.LeaveDetailsReportDao;
import com.centris.campus.daoImpl.LeaveDetailsReportDaoImpl;
import com.centris.campus.forms.LeaveDetailsReportForm;
import com.centris.campus.service.LeaveDetailsReportService;
import com.centris.campus.vo.LeaveDetailsReportVo;
import com.centris.campus.vo.TeacherVo;

public class LeaveDetailsReportServiceImpl implements LeaveDetailsReportService{
	
	public  ArrayList<LeaveDetailsReportVo> getLeaveDetails(LeaveDetailsReportForm leaveDetailsReportVo){
		
		LeaveDetailsReportDao leaveReport=new LeaveDetailsReportDaoImpl();
	
		return leaveReport.getLeaveDetails(leaveDetailsReportVo);
	}
	
	public  ArrayList<TeacherVo> getTeachername(String teachingtype,String dept){
		
		LeaveDetailsReportDao leaveReport=new LeaveDetailsReportDaoImpl();
	
		return leaveReport.getTeachername(teachingtype,dept);
	}

}
