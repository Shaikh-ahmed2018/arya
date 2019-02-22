package com.centris.campus.service;

import java.util.List;
import java.util.Map;

import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.registrationvo;

public interface StudentRegistrationService {
	public List<StudentRegistrationVo> getAcademicYear() throws Exception;

	public List<StudentRegistrationVo> getStudentquota() throws Exception;

	public Map<String, String> saveStudentRegistration(
			StudentRegistrationForm studentRegistrationForm) throws Exception;

	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo registrationVo);

	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo);

	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo);

	public String modifyStudentDetails(StudentRegistrationForm modifyform);

	public List<StudentRegistrationVo> getStudentCaste();

	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type);

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation);

	public List<StudentRegistrationVo> getClassDetails(String catecory);

	public List<StudentRegistrationVo> getSection(String classval);

	public List<StudentRegistrationVo> getConcessionDetails();

	public List<TransportTypeVO> transportypedetails();

	public List<StageMasterVo> getStageDetails(String accyear);

	public List<StudentRegistrationVo> getStudentDetails(String userType,String userCode,String acadamic_year);

	public boolean deactivateStudent(StudentRegistrationVo registrationVo);
	
	public List<registrationvo> StudentDetails(String academic_year) ;
	
	public List<StudentRegistrationVo> getStudentList(String academic_year, String location);
	
	public List<StudentRegistrationVo> studentFullList(String studentId,String accYear, String locationId);
	
	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear);
	
	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location);
	
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm);
	
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear);
	
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid);
	
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,String classname);
	
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname);
	
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid);
}
