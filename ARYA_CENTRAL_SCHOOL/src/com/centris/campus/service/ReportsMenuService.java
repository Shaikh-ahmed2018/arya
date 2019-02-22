package com.centris.campus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.ViewallSubjectsVo;

public interface ReportsMenuService {
	
	public ArrayList<ReportMenuVo> getAccYears();
	public ArrayList<ReportMenuVo> getStream();
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId);
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location);
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform);
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform);
	public HashMap<String, ArrayList<FeeReportDetailsVo>>  getStdFeeStatusReportDetails(ReportMenuForm reform);
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo reform);
	public HashMap<String, ArrayList<MarksUploadVO>>  getStdMarksDetails(ReportMenuForm reform);
	public ArrayList<MarksUploadVO>  getStdMarksDetailsDownload(MarksPOJO reform);
	public ArrayList<ExaminationDetailsVo>  examReportClassWiseDetails(ReportMenuForm reform);
   public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuForm reform);
   public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform);

	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform);
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId);
	public String gettempregid();
	public String getthirdRegNo();
	public ArrayList<ReportMenuVo> getlocationList();
	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid);

	public ArrayList<ReportMenuVo> getStudentClass(String schoolLocation);
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getClassDetails();
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo);
	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String schoolLocation, String locid);
	public ArrayList<ExaminationDetailsVo> accYearListStatus();
	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear);
	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear);
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getAllLocationName();
	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo);
	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location);
	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId);
	public ArrayList<ReportMenuVo> getaccessionNo();
	public List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode);
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId);
	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode, String studentId, String accYear, String locationId, String classId, String sectionId, int scored);
	public String getGradeBasedOnMarks(int grandtotal);

	public ArrayList<ReportMenuVo> getAccessionNo();

	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId);
	public ArrayList<ReportMenuVo> getTerm();
	
	ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj);
	ArrayList<ReportMenuVo> getTerm(String accyear);

	public ArrayList<ExaminationDetailsVo> reportservice();
	public ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId);
	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(String locationid, String accyear, String classid, String termId);
	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid, String termId);
	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(String locationid, String accyear, String classid, String setionid,String paymodeid, String paymenttype, String termId);

	public ArrayList<ReportMenuVo> getterms(String location);
	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid);

	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,String accyear, String classid, String setionid, String paymodeid,String paymenttype, String termId);

	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location);
	ArrayList<ReportMenuVo> getTerm(String accyear, String location);
	public ITFeeVo getITFee(String studentId, String accyer, String locationId);
	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo);
	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo);
	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo);
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo);
	public ReportMenuVo getTerm1Exams(String accyear, String classId, String locationid);
	public ReportMenuVo getTerm2Exams(String accyear,String classId, String locationid);
	public ReportMenuVo getFinalExams(String accyear,String classId, String locationid);
	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo);
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo);
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo);
}
