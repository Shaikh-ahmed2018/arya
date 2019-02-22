package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.PayCertificateReportVO;

public interface StaffServiceReportDao {

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsDao(String locationid, String classid);

	ArrayList<TeacherRegistrationPojo> getTeacherDetailReportDao(
			TeacherRegistrationPojo teacherpojo);

	
	ArrayList<PayCertificateReportVO> getTeacherPayDetailsReportDao(
			String accyear, String month, String teachername);

}
