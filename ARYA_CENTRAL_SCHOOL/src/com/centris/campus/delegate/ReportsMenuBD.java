package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.centris.campus.forms.FeeReportform;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.service.LibraryService;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.serviceImpl.FeeReportServiceImpl;
import com.centris.campus.serviceImpl.LibraryServiceImpl;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.feeReportVO;


public class ReportsMenuBD {
	
	static ReportsMenuService reportservice;
	static {
		reportservice=new ReportsMenuServiceImpl();
	}
	
	public ArrayList<ReportMenuVo> getAccYears(){
		return reportservice.getAccYears();
	}
	
	public ArrayList<ReportMenuVo> getStream(){
		
		return reportservice.getStream();
	}

	public ArrayList<ReportMenuVo> getStudentClass(String schoolLocation){
		
		return reportservice.getStudentClass(schoolLocation);
	}
	
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId){
		
		
		
		return reportservice.getClassesByStream(streamId);
		
	}
	
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location){
		
		
		
		return reportservice.getSectionsByClass(classId,location);
		
	}
	
	
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform){
		
		
		
		return reportservice.getStudentDetailsReport(reform);
		
	}
	
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform){
		
		
		
		return reportservice.getSelectedItems(reform);
		
	}
	
	public HashMap<String, ArrayList<FeeReportDetailsVo>>  getStdFeeStatusReportDetails(ReportMenuForm reform){
			
			
			
			return reportservice.getStdFeeStatusReportDetails(reform);
			
		}
	
	public ArrayList<FeeReportDetailsVo>  getStdFeeStatusReportDownload(FeeStatusReportPojo reform){
		
		
		
		return reportservice.getStdFeeStatusReportDownload(reform);
		
	}
	
	public HashMap<String, ArrayList<MarksUploadVO>>  getStdMarksDetails(ReportMenuForm reform){
		
		
		
		return reportservice.getStdMarksDetails(reform);
		
	}
	
	public ArrayList<MarksUploadVO>  getStdMarksDetailsDownload(MarksPOJO reform){
		
		
		
		return reportservice.getStdMarksDetailsDownload(reform);
		
	}
	
	public ArrayList<ExaminationDetailsVo>  examReportClassWiseDetails(ReportMenuForm reform){
		
		
		
		return reportservice.examReportClassWiseDetails(reform);
		
	}
	
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuForm reform){
		
		
		
		return reportservice.geInactivetStudentDetailsReport(reform);
		
	}
	
	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform){
		
		
		
		return reportservice.geInactivetStudentFeeDetailsReport(reform);
		
	}
	
	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform){
		
		
		
		return reportservice.getSelectedoneItems(reform);
		
	}
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId) {
		
		
		return reportservice.getSingleStdFeeStatusReportDetails(stdId);
	}

	public String gettempregid() {
		
		return reportservice.gettempregid();

       }

	

	public String getthirdRegNo() {
		return  reportservice.getthirdRegNo();

}
	
	public ArrayList<ReportMenuVo> getlocationList() {
		
		return reportservice.getlocationList();
	}

	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid) {
		return reportservice.insertadmissionDetailsAction(vo,enquriyid);
	}


	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo) {
		return reportservice.getstudentDOBWise(vo);
	}

	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo) {
		return reportservice.getstudentFatherOccuWise(vo);
	}

	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo) {
		return reportservice.getclasssectionDetails(vo);
	}

	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo) {
		return reportservice.getstudentMotherOccuWise(vo);
	}

	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo) {
		return reportservice.getstudentDetailsReligionWise(vo);
	}


	public ArrayList<ReportMenuVo> getClassDetails() {
		return reportservice.getClassDetails();
	}

	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo) {
		return reportservice.getstudentCategoryWise(vo);
	}

	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo) {
		return reportservice.getstudentParentWise(vo);
	}

	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo) {
		return reportservice.getstudentList(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		return reportservice.getstudentDetailsList(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo) {
		return reportservice.getstudentContactDetails(vo);
	}

	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo) {
		return reportservice.getstudentStandardWise(vo);
	}


	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear, String locid) {
		return reportservice.getAccYearsSubject(accyear,locid);
	}
	
	public ArrayList<ExaminationDetailsVo> accYearListStatus() {
		return reportservice.accYearListStatus();
	}

	public ArrayList<ReportMenuVo> getAllLocationName() {
		return reportservice.getAllLocationName();
	}

	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear) {
		return reportservice.accYearhouseSettings(locid,accyear);
	}

	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear) {
		return reportservice.accYeargeneratehouseSettings(locid,accyear);
	}

	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo) {
		return reportservice.getstudentDepartmentList(vo);
	}

	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo) {
		return reportservice.getstudentBusRouteWise(vo);
	}

	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo) {
		return reportservice.getstudentOptionalSubjectDetails(vo);
	}

	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo) {
		return reportservice.getstudentWithPhoneNumber(vo);
	}

	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo) {
		return reportservice.getOldStudentsList(vo);
	}

	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo) {
		return reportservice.getStudentsStrength(vo);
	}

	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo) {
		return reportservice.getStudentsNewAdmissionList(vo);
	}

	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo) {
		return reportservice.getStudentPromotionList(vo);
	}

	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo) {
		return reportservice.getStudentListGenderWise(vo);
	}

	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location) {
		return reportservice.accYearListStatusGrade(accyear,location);
	}


	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId) {
		return reportservice.getSubjectByClass(classId,locationId);
	}

	/*public List<PageFilterVo> getstaffDeatails() {
		ReportsMenuService list=new getstaffDeatailsServiceImpl();
		return list.accYeargeneratehouseSettings(locid);
	}*/

	public ArrayList<ReportMenuVo> getaccessionNo() {
		return reportservice.getaccessionNo();
	}


	public static List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode) {
		return reportservice.getSubjectOnClass(classId,studentId,accYear,locationId,examCode);
	}

	public static ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId) {
		return reportservice.getExam(studentId,accyear,locationId,classDetailId,sectionId);
	}

	public static ArrayList<ExaminationDetailsVo> getExamDependencides(
			String examCode, String studentId, String accYear, String locationId, String classId, String sectionId, int scored) {
		return reportservice.getExamDependencides(examCode,studentId,accYear,locationId,classId,sectionId,scored);
	}
	
	public static String getGradeBasedOnMarks(int grandtotal) {
		return reportservice.getGradeBasedOnMarks(grandtotal);
	}

	public ArrayList<ReportMenuVo> getAccessionNo() {
		return reportservice.getAccessionNo();
	}
	
	public static List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId) {
			return reportservice.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,examCode,sectionId);
			}

	public ArrayList<ReportMenuVo> getTerm(String accyear, String location) {
		return reportservice.getTerm(accyear,location);
	}

	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj) {
		return reportservice.gettransportfeeDetails(obj);
	}

	public static ArrayList<ExaminationDetailsVo> reportservice() {
		
		return reportservice.reportservice();
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId) {
		
		return reportservice.getFeeCollectionReport(locationid,accyear,termId);
	}

	public static ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid, String termId) {
		
		return reportservice.getfeecollectionclasslist(locationid,accyear,classid,termId);
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid, String termId) {
		
		return reportservice.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId);
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid, String paymenttype, String termId) {
			return reportservice.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}


	public ArrayList<ReportMenuVo> getterms(String location) {
		return reportservice.getterms(location);
	}

	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid) {
		return reportservice.DDReportList(termid,academic_year,locationid);
	}


	public static ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype, String termId) {
		return reportservice.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}


	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location) {
		return reportservice.getSectionsByClassLoc(classId,location);
	}
	public static ITFeeVo getITFee(String studentId, String accyer,
			String locationId) {
		return reportservice.getITFee(studentId,accyer,locationId);
	}

	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo) {
		return reportservice.getStudentListAdmiWise(vo);
	}

	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo) {
		return reportservice.getstudentRollNoWise(vo);
	}

	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo) {
		return reportservice.getstudentAlphaWise(vo);
	}

	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo) {
		return reportservice.getTermWiseReportCard(vo);
	}

	public ReportMenuVo getTerm1Exams(String accyear,String classId, String locationid) {
		return reportservice.getTerm1Exams(accyear,classId,locationid);
	}

	public ReportMenuVo getTerm2Exams(String accyear,String classId,String locationid) {
		return reportservice.getTerm2Exams(accyear,classId,locationid);
	}

	public ReportMenuVo getFinalExams(String accyear, String classId,String locationid) {
		return reportservice.getFinalExams(accyear,classId,locationid);
	}

	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo) {
		return reportservice.getAcademicYearWiseReportCard(vo);
	}

	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo) {
		return reportservice.getStudentClassSectionWiseListForReport(vo);
	}
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo) {
		return reportservice.getStudentClassSectionWiseListForReportByAll(vo);
	}
}
