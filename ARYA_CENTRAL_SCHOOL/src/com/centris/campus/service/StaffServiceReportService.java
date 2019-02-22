package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.PayCertificateReportVO;

public interface StaffServiceReportService {

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsService(String locationid, String classid);

	ArrayList<TeacherRegistrationPojo> getTeacherDetailReportService(
			TeacherRegistrationPojo teacherpojo);

	ArrayList<PayCertificateReportVO> getTeacherPayDetailsReportService(
			String accyear, String month, String teachername);
	
	
	

}
