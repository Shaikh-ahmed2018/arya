package com.centris.campus.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.serviceImpl.SettingsXLUploadServiceImpl;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;

public class SettingsXLUploadBD {

	public Set<studentExcelUploadVo> saveStudentExam(List<StudentExcelUploadPojo> list) throws SQLException {
	return new SettingsXLUploadServiceImpl().saveStudentExam(list);
	}

	public Set<classVo> insertSubjectDetails(List<ClassPojo> list, String userId, String accyear,
			String log_audit_session) {
		return new SettingsXLUploadServiceImpl().insertSubjectDetails(list,userId,accyear,log_audit_session);
	}

	public Set<classVo> insertHolidayXSL(List<ClassPojo> list, String userId, String accyear, String log_audit_session) {
		return new SettingsXLUploadServiceImpl().insertHolidayXSL(list,userId,accyear,log_audit_session);
	}
	
	
	

}
