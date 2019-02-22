package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.service.StaffServiceReportService;
import com.centris.campus.serviceImpl.StaffServiceReportServiceImpl;
import com.centris.campus.vo.PayCertificateReportVO;

public class StaffServiceReportBD {

	
	StaffServiceReportService service = new StaffServiceReportServiceImpl();

	public ArrayList<TeacherRegistrationPojo> getTeacherListDetails(String locationid, String classid) {
	
		return service. getTeacherListDetailsService(locationid,classid);
	}

	public ArrayList<TeacherRegistrationPojo> getTeacherDetailReport(
			TeacherRegistrationPojo teacherpojo) {
		
		return service. getTeacherDetailReportService(teacherpojo);
	}

	public ArrayList<PayCertificateReportVO> getTeacherPayDetails(String accyear, String month, String teachername) {
		
		return service.getTeacherPayDetailsReportService(accyear,month,teachername);
	}
	
	
	
	
	
	
	
	
}
