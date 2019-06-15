/**
 * 
 */
package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.centris.campus.actions.registrationVo;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;

/**
 * @author sathish
 * 
 */
public class StudentRegistrationDelegate {
	public List<StudentRegistrationVo> getAcademicYear() throws Exception {
		return new StudentRegistrationServiceImpl().getAcademicYear();

	}

	public List<StudentRegistrationVo> getStudentquota() throws Exception {
		return new StudentRegistrationServiceImpl().getStudentquota();

	}

	public Map<String, String> saveStudentRegistration(StudentRegistrationForm studentRegistrationForm) throws Exception {
		return new StudentRegistrationServiceImpl().saveStudentRegistration(studentRegistrationForm);
	}

	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo studentSearch) {
		return new StudentRegistrationServiceImpl()
				.studentSearch(studentSearch);
	}

	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo) {
		return new StudentRegistrationServiceImpl()
				.searchStudentResult(registrationVo);
	}

	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo) {
		return new StudentRegistrationServiceImpl()
				.studentSearchByParent(registrationVo);
	}

	public List<StudentRegistrationVo> getStudentCaste() throws Exception {
		return new StudentRegistrationServiceImpl().getStudentCaste();

	}

	public String modifyStudentDetails(StudentRegistrationForm registrationVo) {
		return new StudentRegistrationServiceImpl().modifyStudentDetails(registrationVo);
	}

	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type) {
		return new StudentRegistrationServiceImpl().validateDuplicateData(
				formObj, type);
	}

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr) {

		return new StudentRegistrationServiceImpl().getAllStudentsDetails(
				classname, accodamicyr);
	}

	public String getTransportCategoryType(String trnsportTypeId) {

		return new StudentRegistrationServiceImpl()
				.getTransportCategoryType(trnsportTypeId);
	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo studentSearch) {
		return new StudentRegistrationServiceImpl()
				.studentSearchbyClass(studentSearch);
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation) {
		return new StudentRegistrationServiceImpl().getChildCategory(schoolLocation);
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory) {
		return new StudentRegistrationServiceImpl().getClassDetails(catecory);
	}

	public List<StudentRegistrationVo> getSection(String classval) {
		return new StudentRegistrationServiceImpl().getSection(classval);
	}

	public List<StudentRegistrationVo> getConcessionDetails() {
		return new StudentRegistrationServiceImpl().getConcessionDetails();
	}

	public List<TransportTypeVO> transportypedetails() {
		return new StudentRegistrationServiceImpl().transportypedetails();
	}

	public List<StageMasterVo> getStageDetails(String accyear) {
		return new StudentRegistrationServiceImpl().getStageDetails(accyear);
	}

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year,String schoolLocation) {
	
		return new StudentRegistrationServiceImpl().getStudentDetails1(userType,userCode,academic_year,schoolLocation);
	}

	public boolean deactivateStudent(StudentRegistrationVo registrationVo) {
		return new StudentRegistrationServiceImpl().deactivateStudent(registrationVo);
	}

	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID) {
		return new StudentRegistrationServiceImpl().getSubjectByClass(classID);
	}
	
	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo) {
		
		return new StudentRegistrationServiceImpl().editStudent(registrationVo);
	}
	public List<registrationvo> StudentDetails(String academic_year) {
		return new StudentRegistrationServiceImpl().StudentDetails(academic_year);
	}
	
	public java.util.List<StudentRegistrationVo> getStudentDetails(	String searchName,String location) {
		
		return new StudentRegistrationServiceImpl().getStudentDetails(searchName,location);
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String academic_year) {
		
		return new StudentRegistrationServiceImpl().getStudentDetails(userType,userCode,academic_year);
	}
	
	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		return new StudentRegistrationServiceImpl().getTranscationcategory(transloc) ;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location) {
		return new StudentRegistrationServiceImpl().getClassDetailWithOutStream(location);
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {
		return new StudentRegistrationServiceImpl().getClassDetailsByTemp();
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {
		return new StudentRegistrationServiceImpl().getClassDetailSrSecondary();
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo) {
		return new StudentRegistrationServiceImpl().getTempRegistrationDetails(registrationVo);
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {
		return new StudentRegistrationServiceImpl().getClassDetailMontessori();
	}

	public java.util.List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String academic_year) {
		return new StudentRegistrationServiceImpl().getTransportStudentDetails(userType,userCode,academic_year);
	}

	public java.util.List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		return new StudentRegistrationServiceImpl().getTransportStudentDetails(searchTerm);
	}
	
	public List<StudentRegistrationVo> getStudentList(String academic_year, String location) {
		
		return new StudentRegistrationServiceImpl().getStudentList(academic_year,location);
	}

	public List<StudentRegistrationVo> getClassByLocation(String locationid) {
		return new StudentRegistrationServiceImpl().getClassByLocation(locationid);
	}

	public java.util.List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location) {
		return new StudentRegistrationServiceImpl().getAllStudentDetails(academic_year,location);
	}

	public List<StudentRegistrationVo> studentFullList(String studentId,String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().studentFullList(studentId,accYear,locationId);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsList(String locationid ,String accyear ) {
		return new StudentRegistrationServiceImpl().getStudentDetailsList(locationid,accyear);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getStudentDetailsLByFilter(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname, String sectionid, String sortingby,String orderby){
			return new StudentRegistrationServiceImpl().getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	}
	
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname, String sectionid){
		return new StudentRegistrationServiceImpl().getStudentListBySection(locationid,accyear,classname,sectionid);
}

	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag) {
		return new StudentRegistrationServiceImpl().singleStudentDetailsList(studentId,accYear,locationId,flag);
	}

	public String saveStudentPromotion(StudentRegistrationVo registrationVo) {
		return new StudentRegistrationServiceImpl().saveStudentPromotion(registrationVo);
	}

	public String getNextAcademicYearId(String academicyearid) {
		return new StudentRegistrationServiceImpl().getNextAcademicYearId(academicyearid);
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getPromotedListDetails(regVo);
	}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location) {
		return new StudentRegistrationServiceImpl().getStudentLocationList(academic_year,location);
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear) {
		return new StudentRegistrationServiceImpl().searchAllAcademicYearDetails(locationId,accYear);
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return new StudentRegistrationServiceImpl().getStudentSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		return new StudentRegistrationServiceImpl().getStudentSearchListByLocation(searchTerm,locationid);
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		return new StudentRegistrationServiceImpl().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		return new StudentRegistrationServiceImpl().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}
	
	public List<StudentRegistrationVo> getIDCard(String studentId,String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().getIDCard(studentId,accYear,locationId);
	}
	
	
	
	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().getfeedetails(studentId,accYear,locationId);
	}

	public String AddConfidentialDetails(String entryDate,String comments, String studentId, String accyear, String locationid,String userName) {
		return new StudentRegistrationServiceImpl().AddConfidentialDetails(entryDate,comments,studentId,accyear,locationid,userName);
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location) {
		return new StudentRegistrationServiceImpl().getConfidentialReportStudents(academic_year,location);
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear) {
		return new StudentRegistrationServiceImpl().searchAllAccYearDetailsConfReport(locationId,accYear);
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getConfDetailsLByFilter(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(String locationid, String accyear, String classname,String sectionid) {
		return new StudentRegistrationServiceImpl().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid);
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentSearchByConfReport(searchTerm);
	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().singleStudentConfDetails(studentId,accYear,locationId);
	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear) {
		return new StudentRegistrationServiceImpl().getConfSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid) {
		return new StudentRegistrationServiceImpl().getConfSearchListByLocation(searchTerm,locationid);
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear) {
		return new StudentRegistrationServiceImpl().getConfSearchByFilter(searchTerm,locationid,accyear);
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getConfSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		return new StudentRegistrationServiceImpl().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);	
	}

	public String deleteConfidentialDetails(String deleteId) {
		return new StudentRegistrationServiceImpl().deleteConfidentialDetails(deleteId);	
	}

	public String editConfidentialDetails(String entryDate, String comments, String editId) {
		return new StudentRegistrationServiceImpl().editConfidentialDetails(entryDate,comments,editId);
	}

	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo) {
		return new StudentRegistrationServiceImpl().getIDCardStaff(filterpojo);
	}

	public List<ExaminationDetailsVo> getExaminationDetails(StudentRegistrationVo svo) {
		return new StudentRegistrationServiceImpl().getExaminationDetails(svo);
	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,String acyear_id) {
		return new StudentRegistrationServiceImpl().getExaminationCodes(loc_id,acyear_id);
	}

	public String getExamName(String examcode) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getExamName(examcode);
	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getStudentAttendance(stud_id);
	}

	public List<StudentAttendanceVo> getStudentAppraisal(StudentRegistrationVo spvo) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getStudentAppraisal(spvo);
	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(StudentRegistrationVo svo) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getExaminationDetailsBasedonExams(svo);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByList(String searchTerm, String accyear) {
		return new StudentRegistrationServiceImpl().getStudentFeeSearchByList(searchTerm,accyear);

	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(String searchTerm, String accyear) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getStudentFeeSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		return new StudentRegistrationServiceImpl().getStudentFeeSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(String searchTerm, String locationid, String accyear,	String classname) {
		return new StudentRegistrationServiceImpl().getStudentSearchByFeeClass(searchTerm,locationid,accyear,classname);

	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(String searchTerm, String locationid, String accyear) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getStudentSearchByFeeFilter(searchTerm,locationid,accyear);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getStudentDetailsLByFeeFilter(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentListByFeeSection(String locationid, String accyear, String classname,String sectionid) {
		// TODO Auto-generated method stub
		return new StudentRegistrationServiceImpl().getStudentListByFeeSection(locationid,accyear,classname,sectionid);
	}

	public java.util.List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(	String academic_year, String location, String classId,String sectionid) {
		return new StudentRegistrationServiceImpl().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid);
	}

	public String toCheckNextClassAvailable(String toclass, String locationId) {
		return new StudentRegistrationServiceImpl().toCheckNextClassAvailable(toclass,locationId);
	}

	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear) {
		return new StudentRegistrationServiceImpl().getAllAcademicYearDemotedDetails(locationId,accYear);
	}
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear) {
		return new StudentRegistrationServiceImpl().getAllAcademicYearPromotedDetails(locationId,accYear);
	}
	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentDemotedSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentPromotedChange(String studentId, String accYear, String locationId,String promotedId) {
		return new StudentRegistrationServiceImpl().getStudentPromotedChange(studentId,accYear,locationId,promotedId);
	}
	
	public String toCheckNextYearAvailable(String accyear) {
		return new StudentRegistrationServiceImpl().toCheckNextYearAvailable(accyear);
	}
	
	public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getPromotedClassSectionList(regVo);
	}
	
	public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getPromotedClassList(regVo);
	}
	
	public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getDemotedListDetails(regVo);
	}
	
	public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getDemotedClassSectionList(regVo);
	}
	
	public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo) {
		return new StudentRegistrationServiceImpl().getDemotedClassList(regVo);
	}
	
	public List<StudentRegistrationVo> getStudentWisePromotion(String studentId, String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().getStudentWisePromotion(studentId,accYear,locationId);
	}
	
	public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchListByLocation(searchTerm,locationid);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByFilter(searchTerm,locationid,accyear);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location,
			String currentaccyear) {
		return new StudentRegistrationServiceImpl().getStudentDetailsexcel(searchTerm,location,currentaccyear);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsByJs(
			StudentRegistrationVo data) {
		return new StudentRegistrationServiceImpl().getStudentDetailsByJs(data);
	}

	public String getlocationteacher(String userCode) {
		return new StudentRegistrationServiceImpl().getlocationteacher(userCode);
	}

	public String toGetStreamName(String toClass, String locationId) {
		return new StudentRegistrationServiceImpl().toGetStreamName(toClass,locationId);
	}

	public List<StudentRegistrationVo> individualStudentSearch(String studentId) {
		return new StudentRegistrationServiceImpl().individualStudentSearch(studentId);
	}

	public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location) {
		return new StudentRegistrationServiceImpl().getStudentWithheldList(academic_year,location);
	}

	public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().singleStudentWithHeld(studentId,accYear,locationId);
	}

	public String AddWithHeldDetails(StudentRegistrationVo studentvo) {
		return new StudentRegistrationServiceImpl().AddWithHeldDetails(studentvo);
	}

	public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
			String locationid, String flag, String status) {
		return new StudentRegistrationServiceImpl().singleStudentWithHeldDetailsList(studentId,accyear,locationid,flag,status);
	}

	public String EditWithHeldDetails(StudentRegistrationVo studentvo) {
		return new StudentRegistrationServiceImpl().EditWithHeldDetails(studentvo);
	}

	public String deleteWithHeldDetails(String deleteId) {
		return new StudentRegistrationServiceImpl().deleteWithHeldDetails(deleteId);
	}

	public String AddApparisalDetails(StudentRegistrationVo vo) {
		
		return new StudentRegistrationServiceImpl().AddApparisalDetails(vo);
	}

	public List<StudentRegistrationVo> singleStudentDetailReport(String studentId, String accyear, String locationid) {
		return new StudentRegistrationServiceImpl().singleStudentDetailReport(studentId,accyear,locationid);
	}

	public String deleteApparaisalDetails(String deleteId) {
		return new StudentRegistrationServiceImpl().deleteApparaisalDetails(deleteId);
	}

	public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details) {
		return new StudentRegistrationServiceImpl().getStudentListBySpecialization(details);
	}

	public String getAdmissionNo(String locationId) {
		return new StudentRegistrationServiceImpl().getAdmissionNo(locationId);
	}
	
	public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId,String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().getIDCardPhotoSheet(sectionId,classId,accYear,locationId);
	}

	public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
			String accYear, String locationId) {
		
		return new StudentRegistrationServiceImpl().ShowStudentAddress(studentId,accYear,locationId) ;
	}
	
	public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result) {
		return new StudentRegistrationServiceImpl().generateStudentTC(splitlocation,splitaccyear,splitstudentid,splitadmid,splitclassid,examdetails,reason,remarks,result);
	}
	
	public List<StudentRegistrationVo> TCGeneration(String academic_year, String location) {
		return new StudentRegistrationServiceImpl().TCGeneration(academic_year,location);
	}
	
public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {
		
		return new StudentRegistrationServiceImpl().TCGeneration1(academic_year,location);
	}

public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().getNotGenTC(vo);
}

public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname, String sectionid, String sortingby,String orderby){
	return new StudentRegistrationServiceImpl().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby);
}

public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid){
	return new StudentRegistrationServiceImpl().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
}

public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().GenTCListFilter(vo);
}

public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().GenTCListSearch(vo);
}

public ArrayList<StudentRegistrationVo> getStudentcontactDetails(String accaYear, String locId, String classid, String secId) {
	// TODO Auto-generated method stub
	return new StudentRegistrationServiceImpl().getStudentcontactDetails(accaYear,locId,classid,secId) ;
}
	
}
