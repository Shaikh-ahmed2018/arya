package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ReportsMenuDao;
import com.centris.campus.daoImpl.FormsManagementdaoImpl;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.ViewallSubjectsVo;

public class ReportsMenuServiceImpl implements ReportsMenuService {

	private static final Logger logger = Logger
			.getLogger(ReportsMenuServiceImpl.class);

	static ReportsMenuDaoImpl daoimpl;
	static{
		daoimpl = new ReportsMenuDaoImpl();
	}
	
	@Override
	public ArrayList<ReportMenuVo> getAccYears() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getAccYears Starting");
		ArrayList<ReportMenuVo> yearList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			yearList = dao.getAccYears();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getAccYears Ending");

		return yearList;
	}

	@Override
	public ArrayList<ReportMenuVo> getStream() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStream Starting");
		ArrayList<ReportMenuVo> streamList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			streamList = dao.getStream();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStream Ending");

		return streamList;
	}
	
	public ArrayList<ReportMenuVo> getStudentClass(String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentClass Starting");
		ArrayList<ReportMenuVo> classList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			classList = dao.getStudentClass(location);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentClass Ending");

		return classList;
	}

	@Override
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getClassesByStream Starting");
		ArrayList<ReportMenuVo> classList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			classList = dao.getClassesByStream(streamId);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getClassesByStream Ending");

		return classList;
	}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId,String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClass Starting");
		ArrayList<ReportMenuVo> sectionList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			sectionList = dao.getSectionsByClass(classId,location);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClass Ending");

		return sectionList;
	}

	@Override
	public ArrayList<StudentInfoVO> getStudentDetailsReport(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.getStudentDetailsReport(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}

	public ReportMenuVo getSelectedItems(ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Starting");

		ReportMenuVo selected = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			selected = dao.getSelectedItems(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Ending");

		return selected;
	}

	@Override
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Starting");

		HashMap<String, ArrayList<FeeReportDetailsVo>> feeStatusList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			feeStatusList = dao.getStdFeeStatusReportDetails(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Ending");

		return feeStatusList;
	}

	@Override
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(
			FeeStatusReportPojo reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Starting");

		ArrayList<FeeReportDetailsVo> feeStatusList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			feeStatusList = dao.getStdFeeStatusReportDownload(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Ending");

		return feeStatusList;
	}

	@Override
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetails Starting");

		HashMap<String, ArrayList<MarksUploadVO>> marksList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			marksList = dao.getStdMarksDetails(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetails Ending");

		return marksList;
	}

	@Override
	public ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetailsDownload Starting");

		ArrayList<MarksUploadVO> marksList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			marksList = dao.getStdMarksDetailsDownload(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetailsDownload Ending");

		return marksList;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : examReportClassWiseDetails Starting");

		ArrayList<ExaminationDetailsVo> examList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			examList = dao.examReportClassWiseDetails(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : examReportClassWiseDetails Ending");

		return examList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.geInactivetStudentDetailsReport(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(
			ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.geInactivetStudentFeeDetailsReport(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}
	
	public ReportMenuVo getSelectedoneItems(ReportMenuForm reform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Starting");

		ReportMenuVo selected = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			selected = dao.getSelectedoneItems(reform);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Ending");

		return selected;
	}
	
	@Override
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId) {
		return daoimpl.getSingleStdFeeStatusReportDetails(stdId);
	}

	@Override
	public String gettempregid() {
		return daoimpl.gettempregid();
	}

	@Override
	public String getthirdRegNo() {
		return daoimpl.getthirdRegNo();
	}

	@Override
	public ArrayList<ReportMenuVo> getlocationList() {
		return daoimpl.getlocationList();
	}

	@Override
	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid) {
		FormsManagementdaoImpl daoimpl=new FormsManagementdaoImpl();
		return daoimpl.insertadmissionDetailsAction(vo,enquriyid);
	}


	@Override
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo) {
		return daoimpl.getstudentDOBWise(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo) {
		return daoimpl.getstudentFatherOccuWise(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo) {
		return daoimpl.getstudentMotherOccuWise(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo) {
		return daoimpl.getstudentDetailsReligionWise(vo);
	}
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo) {
		return daoimpl.getstudentCategoryWise(vo);
	}
	
	@Override
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo) {
		return daoimpl.getclasssectionDetails(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getClassDetails() {
		return daoimpl.getClassDetails();
	}
	
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo) {
		return daoimpl.getstudentParentWise(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo) {
		return daoimpl.getstudentList(vo);
	}

	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		return daoimpl.getstudentDetailsList(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo) {
		return daoimpl.getstudentContactDetails(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo) {
		return daoimpl.getstudentStandardWise(vo);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear,String locid) {
		return daoimpl.getAccYearsSubject(accyear,locid);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> accYearListStatus() {
		return daoimpl.accYearListStatus();
	}

	@Override
	public ArrayList<ReportMenuVo> getAllLocationName() {
		return daoimpl.getAllLocationName();
	}

	@Override
	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid,String accyear) {
		return daoimpl.accYearhouseSettings(locid,accyear);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid,String accyear) {
		return daoimpl.accYeargeneratehouseSettings(locid,accyear);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo) {
		return daoimpl.getstudentDepartmentList(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo) {
		return daoimpl.getstudentBusRouteWise(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo) {
		return daoimpl.getstudentOptionalSubjectDetails(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo) {
		return daoimpl.getstudentWithPhoneNumber(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo) {
		return daoimpl.getOldStudentsList(vo);	
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo) {
		return daoimpl.getStudentsStrength(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo) {
		return daoimpl.getStudentsNewAdmissionList(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo) {
		return daoimpl.getStudentPromotionList(vo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo) {
		return daoimpl.getStudentListGenderWise(vo);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location) {
		return daoimpl.accYearListStatusGrade(accyear,location);
	}
	@Override
	public ArrayList<SubjectPojo> getSubjectByClass(String classId,String locationId) {
		return daoimpl.getSubjectByClass(classId,locationId);
	}
	@Override
	public ArrayList<ReportMenuVo> getaccessionNo() {
		return daoimpl.getaccessionNo();
	}
	@Override
	public List<ExaminationDetailsVo> getSubjectOnClass(String classId,String studentId,String accYear,String locationId,String examCode) {
		return daoimpl.getSubjectOnClass(classId,studentId,accYear,locationId,examCode);
		}

	@Override
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,
			String accyear, String locationId, String classDetailId,
			String sectionId) {
		return daoimpl.getExam(studentId,accyear,locationId,classDetailId,sectionId);
		}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode,String studentId, String accYear, String locationId, String classId, String sectionId,int scored) {
		return daoimpl.getExamDependencides(examCode,studentId,accYear,locationId,classId,sectionId,scored);
		}

	@Override
	public String getGradeBasedOnMarks(int grandtotal) {
		return daoimpl.getGradeBasedOnMarks(grandtotal);
		}


	@Override
	public ArrayList<ReportMenuVo> getAccessionNo() {
		return daoimpl.getAccessionNo();
		}


	@Override
	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId) {
		return daoimpl.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,examCode,sectionId);
		}


	@Override
	public ArrayList<ReportMenuVo> getTerm(String accyear,String location) {
		return daoimpl.getTerm(accyear,location);
	}

	@Override
	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj) {
		return daoimpl.gettransportfeeDetails(obj);
	}

	@Override
	public ArrayList<ReportMenuVo> getTerm() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public ArrayList<ExaminationDetailsVo> reportservice() {
		
		return null;
	}

	@Override
	public ArrayList<FeeCollectionVo>getFeeCollectionReport(String locationid, String accyear,String termId) {
		
		return daoimpl.getFeeCollectionReport(locationid,accyear,termId);
	}

	@Override
	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid,String termId) {
		return daoimpl.getfeecollectionclasslist(locationid,accyear,classid,termId);
	}

	@Override
	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid,String termId) {
	
		return daoimpl.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId);
	}

	@Override
	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid,String paymenttype,String termId) {
		
		return daoimpl.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}


	@Override
	public ArrayList<ReportMenuVo> getterms(String location) {
		return daoimpl.getterms(location);
	}

	@Override
	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid) {
		return daoimpl.DDReportList(termid,academic_year,locationid);
	}


	@Override
	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype,String termId) {
		
		return daoimpl.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
	}


	@Override
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClassLoc Starting");
		ArrayList<ReportMenuVo> sectionList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {


			sectionList = dao.getSectionsByClassLoc(classId,location);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClassLoc Ending");

		return sectionList;
	}

	@Override
	public ArrayList<ReportMenuVo> getTerm(String accyear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITFeeVo getITFee(String studentId, String accyer,String locationId) {
		return daoimpl.gettgetITFeeerms(studentId,accyer,locationId);
		}

	@Override
	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo) {
		return daoimpl.getStudentListAdmiWise(vo);
	}

	@Override
	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo) {
		return daoimpl.getstudentRollNoWise(vo);
	}

	@Override
	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo) {
		return daoimpl.getstudentAlphaWise(vo);
	}
	
	@Override
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo) {
		return daoimpl.getTermWiseReportCard(vo);
	}

	@Override
	public ReportMenuVo getTerm1Exams(String accyear,String classId, String locationid) {
		return daoimpl.getTerm1Exams(accyear,classId,locationid);
	}

	@Override
	public ReportMenuVo getTerm2Exams(String accyear,String classId, String locationid) {
		return daoimpl.getTerm2Exams(accyear,classId,locationid);
	}

	@Override
	public ReportMenuVo getFinalExams(String accyear,String classId, String locationid) {
		return daoimpl.getFinalExams(accyear,classId,locationid);
	}

	@Override
	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo) {
		return daoimpl.getAcademicYearWiseReportCard(vo);
	}

	@Override
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo) {
		return daoimpl.getStudentClassSectionWiseListForReport(vo);
	}

	@Override
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo) {
		return daoimpl.getStudentClassSectionWiseListForReportByAll(vo);
	}
}
