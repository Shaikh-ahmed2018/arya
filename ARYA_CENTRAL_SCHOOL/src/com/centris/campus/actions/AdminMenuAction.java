package com.centris.campus.actions;


import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.converter.FileChooserDemo;
import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.FeeMasterDAOIMPL;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.StudentIDDaoImpl;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.daoImpl.TimeTableDaoImpl;
import com.centris.campus.delegate.AbsentSMSBD;
import com.centris.campus.delegate.AcadamicYearPlanBD;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.ClassFeeSetupBD;
import com.centris.campus.delegate.ClassTeacherLsisBD;
import com.centris.campus.delegate.ClassTeacherMappingDelegate;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.CreateExaminationBD;
import com.centris.campus.delegate.CareersViewdelegate;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.AcademicYearMasterBD;
import com.centris.campus.delegate.ElectionBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.AddSubjectDelegate;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.FeeSetupBD;
import com.centris.campus.delegate.FuelMasterBD;
import com.centris.campus.delegate.HolidayMasterBD;
import com.centris.campus.delegate.Inventory_Delegate;
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.LoginBD;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.delegate.ParentRequiresAppointmentDELEGATE;
import com.centris.campus.delegate.QuotaMasterBD;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.RemainderMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.RoleMasterBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.SpecializationBD;
import com.centris.campus.delegate.StaffAttendanceBD;
import com.centris.campus.delegate.StaffPayrollBD;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.StageFeeSetupBD;
import com.centris.campus.delegate.StreamDetailsBD;
import com.centris.campus.delegate.StudentEnquiryBD;
import com.centris.campus.delegate.StudentIDBD;
import com.centris.campus.delegate.StudentPramotionBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.SuddenHolidayListBD;
import com.centris.campus.delegate.TDSComputationBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TimeTableBD;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.TransportTypeBD;
import com.centris.campus.delegate.UserManagementBD;
import com.centris.campus.delegate.UserRolePermissionBD;
import com.centris.campus.delegate.VehicleDriverMappingBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.util.ETL;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearPlanVO;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.CareersViewVo;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.ClassPromotionList;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.ClassTeacherVo;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.HolidayMasterVo;
import com.centris.campus.vo.IDcardVo;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.QuotaMasterVO;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.SpecializationVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffPayrollListVo;
import com.centris.campus.vo.StageFeeSetupVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentEnquiryVo;
import com.centris.campus.vo.StudentIDVo;
import com.centris.campus.vo.StudentPramotionVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.UserRecordVO;
import com.centris.campus.vo.UserRolePermissionVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleDriverMappingVo;
import com.centris.campus.vo.VehicleTypeVo;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.feeReportVO;
import com.centris.campus.vo.secadmissionformformatVO;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class AdminMenuAction extends DispatchAction {
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String EcampusPro_TC_Dir = res.getString("EcampusPro_TC_Dir");
/*	private static String EcampusPro_TC_Dir_Excel = res.getString("EcampusPro_TC_Dir_Excel");
*/	private static final Logger logger = Logger.getLogger(AdminMenuAction.class);

	private static String ImageName = res.getString("ImageName");
	private static String SchoolName = res.getString("SchoolName");
	//private String schoolLocation;

	public ActionForward Home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in streamListAction : Home Starting");
		String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
		String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYear));
		String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYear));
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Home Ending");

		return mapping.findForward(MessageConstants.adminLogin);
	}

	public ActionForward studentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_REGISTRATION);
			
			/*UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");*/

			/*String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();*/
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}

			List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("searchname".trim());
			if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate().getStudentDetails(searchTerm,academic_year+","+location);

				request.setAttribute("searchTerm", searchTerm);

			}/* else {
				List = new StudentRegistrationDelegate().getStudentDetails(userType, userCode, academic_year+","+location);
			}*/

			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return mapping.findForward(MessageConstants.STUDENT_LIST);
	}
	
	
	public ActionForward StaffListForLeave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffListForLeave Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_LEAVE);

			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails();
			request.setAttribute("allTeacherDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffListForLeave Ending");

		return mapping.findForward("StaffListForLeave");
	}
	
	public ActionForward teacherLeaveRequestAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : teacherLeaveRequestAdd Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_LEAVE);
			
			
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			String parentid = request.getParameter("userid");
			
			ParentVO vo = new ParentVO();
			

			vo.setParentID(parentid);


			request.setAttribute("parentid", parentid);
			
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
			list = new LeaveBankDelegate().getleavenamesList(academic_year);
			request.setAttribute("LeaveTypesList", list);
			
			ArrayList<LeaveBankVO> leaveType = new ArrayList<LeaveBankVO>();
		    leaveType = new LeaveBankDelegate().getleaveusertype(parentid,academic_year);
		    request.setAttribute("LeaveList", leaveType);
		    
			List<UserDetailVO> userlist = new ParentExamdetailsBD().getRequestUserListDetails(parentid.trim());
			request.setAttribute("userlist", userlist);
			
			ArrayList<LeaveBankVO> getleaveDetails = new ArrayList<LeaveBankVO>();
			getleaveDetails = new LeaveBankDelegate().getleaveBalance(parentid,academic_year);
			request.setAttribute("BalanceList", getleaveDetails);
			
			
			
			LeaveCalculation leaveCalculation = new LeaveBankDelegate().getNewLeaveCalculation(parentid, academic_year);
			request.setAttribute("leaveCalculation", leaveCalculation);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : teacherLeaveRequestAdd Ending");

		return mapping.findForward(MessageConstants.teacherLeaveRequest);
	}
	
	public ActionForward staffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFREGISTRATION);

			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails();
			request.setAttribute("allTeacherDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffList Ending");

		return mapping.findForward(MessageConstants.STAFF_LIST);
	}

	public ActionForward examList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.EXAM_LIST);
	}

	public ActionForward streamList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_STREAMDETAILS);

			StreamDetailsBD obj = new StreamDetailsBD();
			List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();

			String SearchName = request.getParameter("searchname");
			String school=request.getParameter("school");
			if (SearchName != null) {
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

				list = obj.searchStreamDetailsBD(SearchName.trim()+","+school.trim());

			} else {
				list = obj.getStreamDetailsBD(schoolLocation);
			}

			request.setAttribute("streamDetailsList", list);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.STREAM_LIST);
	}

	public ActionForward termList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Starting");

		String status = request.getParameter("result");
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.TERM_SUCCESS);
			}
		}
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		String name = request.getParameter("searchvalue");
		request.setAttribute("searchvalue", name);

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TERMSETUP);

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(academic_year);

			request.setAttribute("searchterm", name);

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
			request.setAttribute("termlist", termlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Ending");

		return mapping.findForward(MessageConstants.TERM_LIST);
	}

	public ActionForward addStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStudent Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_REGISTRATION);
			StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStudent Ending");

		return mapping.findForward(MessageConstants.ADD_STUDENT);
	}

	public ActionForward departmentDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : departmentDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DEPARTMENTDETAILS);

			ArrayList<DepartmentMasterVO> deplist = new ArrayList<DepartmentMasterVO>();

			String SearchName = request.getParameter("searchname");

			/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
			searchvo.setSearch_name(SearchName);*/

			if (SearchName != null) {

				deplist = new DepartmentMasterBD().searchDepartment(SearchName.trim());
				request.setAttribute("DepartmentDetails", deplist);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchexamid", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				deplist = new DepartmentMasterBD().getDepartmentDetails();

			}

			request.setAttribute("DepartmentDetails", deplist);
			request.getSession(false).setAttribute("EXcelDownLoad", deplist);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : departmentDetails Ending");

		return mapping.findForward(MessageConstants.DEPARTMENT_DETAILS);

	}

	public ActionForward classList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			String Message = request.getParameter("msg");

			List<ClassPojo> classList = new ArrayList<ClassPojo>();

			ClassBD delegate = new ClassBD();

			String searchTextVal = request.getParameter("searchname");
			String school=request.getParameter("school");
			if (searchTextVal != null) {

				classList = delegate.searchClassDetails(searchTextVal.trim()+","+school);
				request.setAttribute("searchname", searchTextVal);
				request.setAttribute("searchnamelist", searchTextVal);

			} else {

				classList = delegate.getClassDetails(schoolLocation);

			}

			request.setAttribute("classList", classList);
			request.setAttribute("successmessagediv", Message);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Ending");

		return mapping.findForward(MessageConstants.CLASS_LIST);
	}

	public ActionForward academicyear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : academicyear Starting");
		try {

			ArrayList<AcademicYearVO> editacademicyearlist = null;

			String searchName = request.getParameter("searchText");

			if (searchName != null) {

				editacademicyearlist = new AcademicYearMasterBD()
				.searchAcademicYearDetails(searchName);

				request.setAttribute("searchname", searchName);
				request.setAttribute("searchnamelist", searchName);

			} else {
				editacademicyearlist = new AcademicYearMasterBD()
				.getAcademicYearDetails();

			}

			request.setAttribute("academicyearlist", editacademicyearlist);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACADEMICYEARDETAILS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : academicyear Ending");

		return mapping.findForward(MessageConstants.ACADEMIC_YEAR);
	}

	public ActionForward vehicleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : vehicleList Starting");
		try {

			String Message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);

			TransportBD obj = new TransportBD();
			List<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();


			String SearchName = request.getParameter("searchname");
			if(SearchName != null){
				getvehiclelist = obj.searchvehicledetails(SearchName.trim());
				request.setAttribute("searchname", SearchName );
				request.setAttribute("searchnamelist", SearchName);
			}
			else {
				getvehiclelist= obj.getAllvehicleDetails();
			}
			request.setAttribute("getvehiclelist", getvehiclelist);


			/*	String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				getvehiclelist = obj.searchvehicledetails(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				getvehiclelist = obj.getAllvehicleDetails();
			}
			request.setAttribute("getvehiclelist", getvehiclelist);
			 */

			request.getSession(false).setAttribute("vehiclelistdownload",
					getvehiclelist);

			request.setAttribute("successmessagediv", Message);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : vehicleList Ending");

		return mapping.findForward(MessageConstants.VEHICLE_LIST);

	}

	public ActionForward roleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : roleList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ROLEMASTER);


			List<RoleMasterPojo> roleMasterList = new ArrayList<RoleMasterPojo>();

			String searchTerm = request.getParameter("searchTerm");
			RoleMasterBD masterBD = new RoleMasterBD();

			if (searchTerm != null) {

				roleMasterList = masterBD.searchRole(searchTerm);

				request.setAttribute("searchnamelist", searchTerm);

			} else {

				roleMasterList = masterBD.getRoles();

			}

			request.setAttribute("roleMasterList", roleMasterList);
			/*
			 * request.getSession(false).setAttribute("EXcelDownLoad",
			 * roleMasterList);
			 */

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : roleList Ending");

		return mapping.findForward(MessageConstants.ROLE_LIST);
	}

	public ActionForward getUserRecords(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRecords Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PASSWORDMAINTAINANCE);

			List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

			String searchText = request.getParameter("searchText");
			String type = request.getParameter("type");

			if (searchText != null && type != null) {

				UserManagementPojo userManagementPojo = new UserManagementPojo();
				userManagementPojo.setType(type);
				userManagementPojo.setSearchtext(searchText);

				userRecords = new UserManagementBD().getSearchUserDetailsBD(userManagementPojo);

				request.setAttribute("SearchText", searchText);
				request.setAttribute("Type", type);

			} else {

				userRecords = new UserManagementBD().getUserRecordsBD();

			}
			
			RoleMasterBD masterBD = new RoleMasterBD();

		

			List<RoleMasterPojo>roleList = masterBD.getRoles();

				request.setAttribute("roleList", roleList);
			request.setAttribute("userRecords", userRecords);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRecords Ending");

		return mapping.findForward(MessageConstants.USERRECORD);

	}
	
/*	temporary
	*/
	
	
	public ActionForward sectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
	/*	String user = HelperClass.getCurrentUser(request);*/

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DIVISIONDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			List<SectionForm> ClassSectionAndClassDetailsList = new ArrayList<SectionForm>();

			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			String Message = request.getParameter("msg");

			SectionBD sectionDelegate = new SectionBD();

			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation);

			SectionForm secForm = new SectionForm();
			SectionBD bd = new SectionBD();

			String searchTextVal = request.getParameter("searchText");
			String locationId=request.getParameter("school");
			secForm.setSectionName(searchTextVal);
			secForm.setLocationId(locationId);

			if (searchTextVal != null) {

				ClassSectionAndClassDetailsList = bd.searchSection(secForm);
				request.setAttribute("searchnamelist", searchTextVal);
				request.setAttribute("searchname", searchTextVal);

			} else {

				ClassSectionAndClassDetailsList = sectionDelegate.getCampusClassSectionAndClassDetailsBD(schoolLocation);
			}

			request.setAttribute("classDetailsIDandClassDetailsNameList",
					classDetailsIDandClassDetailsNameList);

			// for list
			request.setAttribute("ClassSectionAndClassDetailsList",
					ClassSectionAndClassDetailsList);

			request.setAttribute("successmessagediv", Message);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Ending");

		return mapping.findForward(MessageConstants.SECTION_LIST);
	}

	
	public ActionForward feeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeList Ending");

		return mapping.findForward(MessageConstants.FEE_LIST);
	}

	// Download need to do
	public ActionForward feeDetailsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String status = request.getParameter("result");
		if (status != null) {
			if (status.equalsIgnoreCase("true")) {
				request.setAttribute("successmessagediv",
						MessageConstants.DeleteMsg);
			} else {

				request.setAttribute("errormessagediv",
						MessageConstants.DeleteErrorMsg);
			}
		} else {
			request.setAttribute("errormessagediv", "Fee Alraedy Mapped");
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			String academicYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String locationId=(String) request.getSession(false).getAttribute("current_schoolLocation");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			AddFeeVO vo = new AddFeeVO();

			vo.setName(request.getParameter("searchvalue"));
			vo.setAcademicYear(academicYear);
			vo.setLocationId(locationId);
			String search =request.getParameter("searchvalue");
			System.out.println(search);
			request.setAttribute("searchfee",search);

			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate().getfeedetails(vo);
			request.setAttribute("feelist", feelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Ending");

		return mapping.findForward(MessageConstants.FEE_DETAILS_LIST);
	}

	public ActionForward transportTypeHome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTypeHome Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			ArrayList<TransportTypeVO> typeList = new ArrayList<TransportTypeVO>();

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm != null) {

				TransportTypePOJO transportTypePOJO = new TransportTypePOJO();
				transportTypePOJO.setSearchtext(searchTerm);

				typeList = new TransportTypeBD()
				.getSearchDetails(transportTypePOJO);
				request.setAttribute("searchtransporttype", searchTerm);

			} else {

				typeList = new TransportTypeBD().getAllTransportypeDetails();
			}

			request.setAttribute("typelist", typeList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTypeHome Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_TYPELIST);
	}

	public ActionForward addStream(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStream Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_STREAMDETAILS);
			String title = "Add New Stream";
			request.setAttribute("title", title);


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStream Ending");

		return mapping.findForward(MessageConstants.ADD_STREAM);
	}

	public ActionForward createExam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExam Starting");

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase("Exam Created Successfully")) {

				request.setAttribute("successmessagediv",
						"Exam Created Successfully");
			}
		}

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			CreateExaminationForm examform = new CreateExaminationForm();

			List<Object> examnameslist = null;
			/*String accyear = examform.getAccyear();*/

			examnameslist = new CreateExaminationBD().getAllExamNames(examform);

			Map<String, String> map = (Map<String, String>) new CreateExaminationBD()
			.getAccadamicYearsBD();

			request.setAttribute("ALLACCYEARS", map);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward QuotaDetails(ActionMapping mapping, ActionForm form,

			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : QuotaDetails Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ArrayList<QuotaMasterVO> quota_list = new ArrayList<QuotaMasterVO>();

			QuotaMasterVO searchvo = new QuotaMasterVO();
			String SearchName = request.getParameter("searchname");

			searchvo.setSearch_name(SearchName);

			if (SearchName != null) {

				quota_list = new QuotaMasterBD().searchQuota(searchvo);
				request.setAttribute("searchdetails", SearchName.trim());

				request.setAttribute("searchnamelist", SearchName.trim());
			} else {

				quota_list = new QuotaMasterBD().getQuotaDetails();
			}

			request.setAttribute("Quotalist", quota_list);
			/*
			 * request.getSession(false).setAttribute("EXcelDownLoad",
			 * quota_list);
			 */
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : QuotaDetails Ending");

		return mapping.findForward(MessageConstants.QUOTA_DETAILS);

	}

	public ActionForward designationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : designationList Starting");

		String result = request.getParameter("value");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.DESIGNATION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.DESIGNATION_SUCCESS);
			}

		}

		String result1 = request.getParameter("result");

		if (result1 != null) {

			if (result1
					.equalsIgnoreCase(MessageConstants.ADD_DESIGNATION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_DESIGNATION_SUCCESS);
			} else if ((result1
					.equalsIgnoreCase(MessageConstants.ADD_DESIGNATION_FAIL))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_DESIGNATION_FAIL);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_DESIGNATION_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_DESIGNATION_SUCCESS);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_DESIGNATION_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_DESIGNATION_FAIL);
			}

		}
		String username = null;

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNATIONDETAILS);

			username = HelperClass.getCurrentUserID(request);

			AddDesignationVO vo = new AddDesignationVO();

			String searchvalue = request.getParameter("searchvalue");

			vo.setDesgname(searchvalue);

			request.setAttribute("searchvalue", searchvalue);

			ArrayList<AddDesignationVO> list = new AddDesignationBD()
			.DesignationDetails(vo);

			request.setAttribute("DesignationDetails", list);

			request.getSession(false).setAttribute("EXcel", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : designationList Ending");

		return mapping.findForward(MessageConstants.DESIGNATION_LIST);
	}

	public ActionForward adddesignation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		try {

			String title = "Add New Designation";
			request.setAttribute("title", title);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNATIONDETAILS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		return mapping.findForward(MessageConstants.ADD_DESIGNATION);
	}

	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			AddDesignation aform = new AddDesignation();

			aform.setDesignation_name(request.getParameter("name"));
			aform.setDesignation_description(request
					.getParameter("description"));
			aform.setDesignationid(request.getParameter("id"));
			aform.setCreatedby(HelperClass.getCurrentUserID(request));

			String name = request.getParameter("name");

			String description = request.getParameter("description");

			String createUser = HelperClass.getCurrentUserID(request);

			String id = request.getParameter("id");

			String result = new AddDesignationBD()
			.insertDesignationDetails(aform);

			System.out.println("delegate:::" + result);

			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return null;

	}

	public ActionForward careerupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: careerupdate Starting");

		CareersViewdelegate careerview;
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_INTERNALJOBPOSTING);

			List<CareersViewVo> career = new ArrayList<CareersViewVo>();

			careerview = new CareersViewdelegate();

			String searchName = request.getParameter("searchText");

			if (searchName != null) {

				career = careerview.searchDetails(searchName);
				request.setAttribute("searchname", searchName);
				request.setAttribute("searchnamelist", searchName);

			} else {
				career = careerview.getAllcareerdetails();
			}
			request.setAttribute("career", career);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: careerupdate Ending");

		return mapping.findForward(MessageConstants.UPDATE_CAREERS);

	}

	public ActionForward addJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: addJob Starting");

		try {

			String title = "Add New Internal Job Posting";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_INTERNALJOBPOSTING);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: addJob Ending");

		return mapping.findForward(MessageConstants.ADDJOB);

	}

	public ActionForward remainderdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: remainderdetails Starting");

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.REM_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.REM_SUCCESS);
			}
		}

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			RemainderMasterVO vo = new RemainderMasterVO();

			vo.setName(request.getParameter("searchvalue"));

			ArrayList<RemainderMasterVO> remainderlist = new RemainderMasterDelegate()
			.remainderdetails(vo);
			request.setAttribute("remainderlist", remainderlist);

			request.setAttribute("searchremainder",
					request.getParameter("searchvalue"));

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: remainderdetails Ending");

		return mapping.findForward(MessageConstants.REMAINDER_DETAILS);

	}

	public ActionForward acdamicYearPlanList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : acdamicYearPlanList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACTIVITIES);

			String schoolName=request.getSession(false).getAttribute("current_schoolLocation").toString();
			if(schoolName.equalsIgnoreCase("all")){
				schoolName="%%";
			}
			ArrayList<AcadamicYearPlanVO> eventlist = new ArrayList<AcadamicYearPlanVO>();

			String searchTerm = request.getParameter("searchTerm");
			String school=request.getParameter("school");
			if (searchTerm != null) {

				AcadamicYearPlanPOJO acadamicYearPlanPOJO = new AcadamicYearPlanPOJO();
				acadamicYearPlanPOJO.setSerachText(searchTerm);
				acadamicYearPlanPOJO.setLocationId(school);
				acadamicYearPlanPOJO.setAcadamicYear(request.getSession(false)
						.getAttribute("current_academicYear").toString());

				eventlist = new AcadamicYearPlanBD()
				.getSearchDetails(acadamicYearPlanPOJO);
				request.setAttribute("searchTerm", searchTerm);

				request.setAttribute("searchnamelist", searchTerm);

			} else {

				AcadamicYearPlanPOJO acadamicYearPlanPOJO = new AcadamicYearPlanPOJO();
				acadamicYearPlanPOJO.setAcadamicYear(request.getSession(false).getAttribute("current_academicYear").toString());
				acadamicYearPlanPOJO.setLocationId(schoolName);
				eventlist = new AcadamicYearPlanBD()
				.getAllEventDetails(acadamicYearPlanPOJO);

			}

			request.setAttribute("AcadamicYearPlanList", eventlist);
			request.getSession(false).setAttribute("EXcel1", eventlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : acdamicYearPlanList Ending");

		return mapping.findForward(MessageConstants.ACADAMICYEAR_PLANlIST);
	}

	public ActionForward changeBackground(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : changeBackground Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CHANGEBACKGROUND);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : changeBackground Ending");

		return mapping.findForward(MessageConstants.CHANGE_BACKGROUND);
	}

	public ActionForward fuelMaintenance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: fuelMaintenance Starting");

		String status = request.getParameter("result");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);

			List<FuelMaintenanceVO> fuelList = new FuelMasterBD()
			.getfuelMaintenanceList();
			request.setAttribute("fuelList", fuelList);

			if (status != null) {

				if (status
						.equalsIgnoreCase(MessageConstants.DELETE_FUEL_MAINTENANCE_SUCCESS)) {

					request.setAttribute("successmessagediv",
							MessageConstants.DELETE_FUEL_MAINTENANCE_SUCCESS);
				}

			}

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: fuelMaintenance Ending");

		return mapping.findForward(MessageConstants.FUEL_MAINTENANCE);

	}

	public ActionForward routeMasterSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: routeMasterSettings settings");

		try

		{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);

			request.setAttribute("AccyearId", HelperClass.getAllAcademicYear());
			
			TransportBD obj = new TransportBD();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String SearchName = request.getParameter("routeIdlist");
			String searchYear=request.getParameter("accyear");
			
			if (SearchName != null) {
				list = obj.searchDetails(SearchName.trim(),searchYear);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
				request.setAttribute("stageAccyear",searchYear);
				
			} else {
				list = obj.getTransportMasterDetails(HelperClass.getCurrentYearID());
				request.setAttribute("stageAccyear", HelperClass.getCurrentYearID());
			}
			request.setAttribute("getTpMasterDetails", list);


		

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: routeMasterSettings Ending");

		return mapping.findForward(MessageConstants.TRANSPORTMASTER);
	}

	public ActionForward StageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StageList Starting");

		String result = request.getParameter("value");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.STAGE_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.STAGE_SUCCESS);
			}

		}

		String username = null;
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			username = HelperClass.getCurrentUserID(request);
			request.setAttribute("AccyearId", HelperClass.getAllAcademicYear());	
			StageDelegateBD obj = new StageDelegateBD();
			List<AddStageVO> list = new ArrayList<AddStageVO>();
			String SearchName = request.getParameter("searchvalue");
			String searchYear=request.getParameter("accyear");
			
			
			if (SearchName != null) {
				list = obj.searchStage(SearchName.trim(),searchYear);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
				request.setAttribute("stageAccyear",searchYear);

			} else {
				list = obj.StageDetails(HelperClass.getCurrentYearID());
				request.setAttribute("stageAccyear", HelperClass.getCurrentYearID());
				

			}
			request.setAttribute("StageDetails", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StageList Ending");

		return mapping.findForward(MessageConstants.STAGE_LIST);
	}

	public ActionForward addstage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addstage:Starting");

		String title = "Add New Stage";
		request.setAttribute("Stage", title);

		String result = request.getParameter("result");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.ADD_STAGE_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_STAGE_SUCCESS);
			} else if ((result
					.equalsIgnoreCase(MessageConstants.ADD_STAGE_FAIL))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_STAGE_FAIL);
			} else if (result
					.equalsIgnoreCase(MessageConstants.UPDATE_STAGE_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_STAGE_SUCCESS);
			} else if (result
					.equalsIgnoreCase(MessageConstants.UPDATE_STAGE_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_STAGE_FAIL);
			}

		}

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute("AccyearId", HelperClass.getAllAcademicYear());	
			
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addstage:Ending");

		return mapping.findForward(MessageConstants.ADD_STAGE);
	}

	public ActionForward savestage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageAction : submit Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			AddStageForm aform = new AddStageForm();

			aform.setStage_name(request.getParameter("name"));
			aform.setAmount(request.getParameter("amount"));

			aform.setStage_description(request.getParameter("description"));
			aform.setStageid(request.getParameter("id"));
			aform.setCreatedby(HelperClass.getCurrentUserID(request));
			aform.setAccyear(request.getParameter("accyear"));
			String name = request.getParameter("name");
			String amount = request.getParameter("amount");

			String description = request.getParameter("description");

			String createUser = HelperClass.getCurrentUserID(request);

			String id = request.getParameter("id");

			String delegate = new StageDelegateBD().insertStage(aform);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", delegate);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageAction : submit Ending");

		return null;

	}

	public ActionForward getUserRolePermission(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getUserRolePermission Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ASSIGNPERMISSION);

			request.getSession(false).setAttribute("RolePermission",
					new UserRolePermissionBD().getUserRolePermission());
			UserRolePermissionVO VO = new UserRolePermissionBD()
			.getUserRolePermission();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRolePermission Ending");

		return mapping.findForward(MessageConstants.USER_ROLE_PERMISSIONS);
	}

	public ActionForward subjectdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :subjectdetails   Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			String school=request.getParameter("school");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setLocationId(school);
			obj.setSearchName(searchTerm);

			if (searchTerm != null) {

				subjectlist = new AddSubjectDelegate().searchsubjectdetails(obj);

				request.setAttribute("searchTerm", searchTerm);
				request.setAttribute("searchnamelist", searchTerm);

			} else {

				subjectlist = new AddSubjectDelegate().subjectdetails(schoolLocation);

			}

			request.setAttribute("allsubjects", subjectlist);

			request.setAttribute("successmessagediv", message);

			request.getSession(false)
			.setAttribute("EXcelDownLoad", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	public ActionForward teachermapping(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception

					{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: teachermapping Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_ADMIN);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_ADMIN);

		String status = request.getParameter("result");
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.DEL_MAP_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.DEL_MAP_SUCCESS);
			}
		}

		ClassTeacherMappingVO vo = new ClassTeacherMappingVO();

		ArrayList<ClassTeacherMappingVO> getDownloadDetails = new ClassTeacherMappingDelegate()
		.getDownloadDetails(vo);

		LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> mappinglist = new ClassTeacherMappingDelegate()
		.getclassdetails(vo);

		JSONArray arralist = new JSONArray();
		arralist.put(mappinglist);

		request.setAttribute("mappinglist", mappinglist);

		request.getSession(false).setAttribute("Exceldownload",
				getDownloadDetails);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: teachermapping Ending");

		return mapping.findForward(MessageConstants.TEACHERMAPPING);

					}

	public ActionForward getVehicleDriverMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getVehicleDriverMapping Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			String searchTerm = request.getParameter("searchTerm");

			ArrayList<VehicleDriverMappingVo> vehicleDriverMapList = new ArrayList<VehicleDriverMappingVo>();

			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				vehicleDriverMapList = new VehicleDriverMappingBD()
				.getVehicleDriverMappingList();

			} else {

				vehicleDriverMapList = new VehicleDriverMappingBD()
				.getSearchVehicleDriverMappingList(searchTerm);

			}

			request.setAttribute("SerchTerm", searchTerm);

			request.setAttribute("VehicleDriverMapList", vehicleDriverMapList);

			JSONArray array = new JSONArray();
			array.put(vehicleDriverMapList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getVehicleDriverMapping Ending");

		return mapping.findForward(MessageConstants.vehicle_driver_mapping);
	}

	public ActionForward driverList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);

			List<DriverMsaterListVo> driverList = new TransportBD().getdriverList();

			request.setAttribute("driverList", driverList);

			request.getSession(false).setAttribute("DownLoad", driverList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverList Ending");

		return mapping.findForward(MessageConstants.driver_list);

	}

	public ActionForward driverget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverget Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverget Ending");

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);

	}

	public ActionForward getClassFeeSetup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_CLASSFEESETUP);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			System.out.println(request.getSession(false)
					.getAttribute("current_academicYear").toString());
			String currentaccyear = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			String searchTerm = request.getParameter("searchTerm");

			String locationId=(String) request.getSession(false).getAttribute("current_schoolLocation");
			ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();

			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				classSetupList = new ClassFeeSetupBD()
				.getFeeSetupDetails(currentaccyear+","+locationId);

			} else {

				classSetupList = new ClassFeeSetupBD()
				.getSearchFeeSetupDetails(searchTerm, currentaccyear);

			}

			request.setAttribute("classfeesetupSerchTerm", searchTerm);

			request.setAttribute("classSetupList", classSetupList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Ending");

		return mapping.findForward(MessageConstants.CLASS_FEE_SETUP);
	}

	public ActionForward getStageFeeSetup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStageFeeSetup Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			String currentaccyear = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<StageFeeSetupVo> classSetupList = new ArrayList<StageFeeSetupVo>();

			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				classSetupList = new StageFeeSetupBD()
				.getStageFeeSetupDetails(currentaccyear);

			} else {

				classSetupList = new StageFeeSetupBD()
				.getSearchStageFeeSetupDetails(searchTerm,
						currentaccyear);

			}
			request.setAttribute("SerchTermstagesetup", searchTerm);
			request.setAttribute("classSetupList", classSetupList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStageFeeSetup Ending");

		return mapping.findForward(MessageConstants.STAGE_FEE_SETUP);
	}

	public ActionForward communicationRemarksList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : communicationRemarksList Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		ArrayList<UpcomingRemarksVO> remarkslist = new CommunicationSettingsBD()
		.getRemarksListDetails();

		request.setAttribute("remarkslist", remarkslist);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : communicationRemarksList Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);

	}

	public ActionForward feeCollection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEECOLLECTION);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation));

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			

			
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				System.out.println("HelperClass.getCurrentYearID()"
						+ HelperClass.getCurrentYearID());
				academic_year = HelperClass.getCurrentYearID();
			}

				
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			request.setAttribute("AccYearList", accYearList);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_LIST);

	}

	public ActionForward payRollList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : payRollList Starting");

		/*
		 * request.setAttribute(MessageConstants.MODULE_NAME,
		 * MessageConstants.BACKOFFICE_FEE);
		 * 
		 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
		 * MessageConstants.MODULE_FEE);
		 */
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : payRollList Ending");

		return mapping.findForward(MessageConstants.PAYROLL);

	}

	public ActionForward getStaffAttendance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffAttendance Starting");

		try {
 
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFATTENDANCE);
			
			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD()
			.getStaffAttendanceList(startdate, endDate);

			request.setAttribute("attendancelist", staffAttendanceList);
			request.setAttribute("StartDate", startdate);
			request.setAttribute("EndDate", endDate);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffAttendance Ending");
		return mapping.findForward(MessageConstants.StaffAttendance);

	}

	public ActionForward gettimetable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_TIMETABLEMANAGEMNET);


			/* String viewBy=request.getParameter("viewBy"); */
			String classId = request.getParameter("classId");
			request.setAttribute("SchoolLocation", HelperClass.getAllLocation());
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */
			request.setAttribute("ClassTimeTableList",
					new TimeTableBD().getClassTimeTableList(accyearid, classId));

			/*
			 * if (viewBy != null) { if (viewBy.equalsIgnoreCase("class")) {
			 * 
			 * request.setAttribute("ClassTimeTableList",new
			 * TimeTableBD().getClassTimeTableList(accyearid,viewBy));
			 * 
			 * } else {
			 * 
			 * request.setAttribute("TeacherTimeTableList",new
			 * TimeTableBD().getClassTimeTableList(accyearid,viewBy)); }
			 * 
			 * request.setAttribute("ViewBy", viewBy);
			 * 
			 * } else {
			 * 
			 * viewBy = "class"; request.setAttribute("ClassTimeTableList", new
			 * TimeTableBD().getClassTimeTableList(accyearid, viewBy)); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Ending");

		return mapping.findForward(MessageConstants.timetable);

	}

	public ActionForward studentPromotionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Starting");
		try {

			ArrayList<StudentPramotionVO> StudentPramotionlist = new StudentPramotionBD()
			.getpromotionslist();

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			request.setAttribute("StudentPramotionlist", StudentPramotionlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTION);
	}

	public ActionForward studentPromotionscreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTIONSCREEN);
	}

	public ActionForward studentPromotionscreenedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreenedit Starting");
		try {
			String status = "status";
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute("status", status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreenedit Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTIONSCREEN);
	}

	public ActionForward studentEnquiryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentEnquiryList Starting");
		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		try {
			String text = request.getParameter("Code");
			if (text == "" || text == null) {
				allDetails = delegateObj.getAllEnquiryDetails();
			} else {
				allDetails = delegateObj.getSearchEnquiryDetails(text);
			}

			request.setAttribute("EnquiryDetails", allDetails);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentEnquiryList Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRY);
	}

	public ActionForward enquiryCreateScreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreen Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreen Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward enquiryCreateScreenedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Starting");
		try {
			String status = "status";
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute("status", status);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward FeeconcessionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Starting");
		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward FeeConcessionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeConcessionDetails Starting");

		String result = request.getParameter("value");

		if (result != null) {

			if (result
					.equalsIgnoreCase(MessageConstants.FEE_CONCSEEION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.FEE_CONCSEEION_SUCCESS);
			}

		}

		String result1 = request.getParameter("result");

		if (result1 != null) {

			if (result1
					.equalsIgnoreCase(MessageConstants.ADD_FEE_CONCSEEION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_FEE_CONCSEEION_SUCCESS);
			} else if ((result1
					.equalsIgnoreCase(MessageConstants.ADD_FEE_CONCSEEION_FAIL))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_FEE_CONCSEEION_FAIL);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_FEE_CONCSEEION_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_FEE_CONCSEEION_SUCCESS);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_FEE_CONCSEEION_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_FEE_CONCSEEION_FAIL);
			}

		}
		String username = null;

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			username = HelperClass.getCurrentUserID(request);

			ConcessionDetailsPojo vo = new ConcessionDetailsPojo();

			vo.setConcessionName(request.getParameter("searchvalue"));

			List<ConcessionDetailsPojo> list = new FeeSetupBD()
			.getconcessiondetails(vo);

			request.setAttribute("concessiondetailsearch",
					request.getParameter("searchvalue"));

			request.setAttribute("concessiondetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeConcessionDetails Ending");

		return mapping.findForward(MessageConstants.FEE_CONCESSION_LIST);
	}

	public ActionForward addfeeconcession(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addfeeconcession");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addfeeconcession");

		return mapping.findForward(MessageConstants.ADD_FEE_CONCESSION);
	}

	public ActionForward insertConcesssionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupAction : insertConcesssionDetails  Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			ConcessionForm detailsForm = new ConcessionForm();

			String createCode = HelperClass.getCurrentUserID(request);

			String concessionName = request.getParameter("concessionname");
			String percentage = request.getParameter("percentage");
			String description = request.getParameter("description");
			String concessionId = request.getParameter("concessionId");

			detailsForm.setConcesionName(concessionName);
			detailsForm.setDescription(description);
			detailsForm.setCreateUser(createCode);
			detailsForm.setPercentage(percentage);
			detailsForm.setConcessionId(concessionId);

			String check = new FeeSetupBD()
			.insertConcesssionDetails(detailsForm);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", check);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupAction : insertConcesssionDetails  Ending");
		return null;

	}

	public ActionForward staffEmployementList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffEmployementList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFRENUMERATION);

			String searhname = request.getParameter("searhname");

			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();

			if (searhname == null) {

				list = new TeacherRegistrationBD().getAllTeacherDetails();
			} else {

				list = new TeacherRegistrationBD()
				.getSearchTeacherDetails(searhname);
			}

			request.setAttribute("allTeacherDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffEmployementList Ending");

		return mapping.findForward(MessageConstants.STAFF_EMPLOYEMENT);
	}

	public ActionForward getPayrollList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayrollList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_SALARYDETAILS);

			String month = null;
			String year = null;
			ArrayList<StaffPayrollListVo> list = new StaffPayrollBD()
			.getPayrollList(year, month);

			request.setAttribute("PayrollList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayrollList Ending");

		return mapping.findForward(MessageConstants.STAFF_PAYROLL_LIST);
	}

	public ActionForward studPromotion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: studPromotion Starting");

		try {

			ArrayList<StudentPramotionVO> notptomotedStudentList = (ArrayList<StudentPramotionVO>) request
					.getSession(false).getAttribute("notpromotedStud");

			if (notptomotedStudentList != null) {

				if (notptomotedStudentList.size() != 0) {

					request.setAttribute("error",
							"Displayed students are not promoted,Addmission number already exist");

					request.setAttribute("notptomotedStudentList",
							notptomotedStudentList);
				} else {

					request.setAttribute("success",
							"Selected Students Promoted Succeessfully");
				}
			}

			request.getSession(false).setAttribute("notpromotedStud", null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: studPromotion Ending");

		return mapping.findForward(MessageConstants.STUD_PROMOTION);
	}

	public ActionForward getmeeting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmeeting Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new CommunicationSettingsBD()
		.getMeetingListDetails();

		String meet = "meeting";

		request.setAttribute("meetinglist", meetinglist);
		request.setAttribute("meeting", meet);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmeeting Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);

	}

	public ActionForward getbdaylist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getbdaylist Starting");

		String bday = "bday";

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD()
		.getBdayListDetails();

		request.setAttribute("bdaylist", bdaylist);

		request.setAttribute("birthday", bday);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :getbdaylist Ending");
		return mapping.findForward(MessageConstants.REMARKS_LIST);
	}

	public ActionForward searchremark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchremark Starting");

		try {

			UpcomingRemarksVO remrakvo = new UpcomingRemarksVO();

			String remarks = request.getParameter("remarks");

			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			remrakvo.setRemarks(remarks);
			remrakvo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
			remrakvo.setTodate(HelperClass.convertUIToDatabase(todate));

			if (remrakvo.getRemarks().equalsIgnoreCase("remarks")) {

				ArrayList<UpcomingRemarksVO> remarkslist = new CommunicationSettingsBD()
				.searchRemarkDetails(remrakvo);

				request.setAttribute("remarkslist", remarkslist);
				request.setAttribute("searchlist", remrakvo);

				request.setAttribute("communicatelist", remrakvo);

			}

			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			String meeting = request.getParameter("remarks");

			String fromdate1 = request.getParameter("fromdate");
			String todate1 = request.getParameter("todate");

			meetingvo.setTitle(meeting);
			meetingvo.setFromdate(HelperClass.convertUIToDatabase(fromdate1));
			meetingvo.setTodate(HelperClass.convertUIToDatabase(todate1));

			if (meetingvo.getTitle().equalsIgnoreCase("meeting")) {

				ArrayList<LstmsUpcomingMeetingVO> meetinglist = new CommunicationSettingsBD()
				.searchMeetingDetails(meetingvo);

				String meet = "meeting";

				request.setAttribute("meetinglist", meetinglist);
				request.setAttribute("searchlist", meetingvo);
				request.setAttribute("meeting", meet);

				request.setAttribute("meetingitems", meetingvo);

			}

			UpcomingBdayVo bdayvo = new UpcomingBdayVo();
			String content = request.getParameter("remarks");

			String fromdate2 = request.getParameter("fromdate");
			String todate2 = request.getParameter("todate");

			bdayvo.setContent(content);
			bdayvo.setFromdate(HelperClass.convertUIToDatabase(fromdate2));
			bdayvo.setTodate(HelperClass.convertUIToDatabase(todate2));

			if (bdayvo.getContent().equalsIgnoreCase("bday")) {

				ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD()
				.searchBdayDetails(bdayvo);

				String bday = "bday";

				request.setAttribute("bdaylist", bdaylist);
				request.setAttribute("searchlist", bdayvo);
				request.setAttribute("birthday", bday);

				request.setAttribute("bdayitems", bdayvo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :searchremark Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);
	}

	public ActionForward LeaveBankList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TEACHERS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TEACHERS);

		String result = request.getParameter("deletekey");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.LEAVEBANK_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.LEAVEBANK_SUCCESS);
			} else if (result
					.equalsIgnoreCase(MessageConstants.LEAVEBANK_ERROR)) {

				request.setAttribute("errormessagediv",
						MessageConstants.LEAVEBANK_ERROR);
			}

		}

		String result1 = request.getParameter("result");

		if (result1 != null) {

			if (result1
					.equalsIgnoreCase(MessageConstants.ADD_LEAVEBANK_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_LEAVEBANK_SUCCESS);
			} else if ((result1
					.equalsIgnoreCase(MessageConstants.ADD_LEAVEBANK_FAILURE))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_LEAVEBANK_FAILURE);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_LEAVEBANK_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_LEAVEBANK_SUCCESS);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_LEAVEBANK_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_LEAVEBANK_FAIL);
			}

		}

		/*String username=null;*/

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);

	/*		username = HelperClass.getCurrentUserID(request);*/

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			LeaveBankVO vo = new LeaveBankVO();

			vo.setAccyearcode(request.getParameter("searchvalue"));

			ArrayList<LeaveBankVO> list = new LeaveBankDelegate().leavebanklist(vo);

			request.setAttribute("leaveBank", list);

			request.setAttribute("attnhidden", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Ending");

		return mapping.findForward(MessageConstants.LEAVEBANK_LIST);
	}

	public ActionForward booksmasterlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LIBRARY);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Ending");

		return mapping.findForward(MessageConstants.BOOKSLIST_LIST);

	}

	public ActionForward getclassandteacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getclassandteacherList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHERMAPPING);

			ArrayList<ClassTeacherVo> list = new ArrayList<ClassTeacherVo>();

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm != null) {

				list = new ClassTeacherLsisBD().getSearchClassTeacherListBD(searchTerm);

				request.setAttribute("searchTerm", searchTerm);

			}

			else {

				list = new ClassTeacherLsisBD().getClassTeacherListBD();
			}

			request.setAttribute("classteacherlist", list);

			request.getSession(false).setAttribute("EXcelDownLoad", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getclassandteacherList Ending");

		return mapping.findForward(MessageConstants.ClassTeacherMapping);
	}

	// support Work

	public ActionForward Support(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {/*
		 * request.setAttribute(MessageConstants.MODULE_NAME,
		 * MessageConstants.BACKOFFICE_SETTINGS);
		 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
		 * MessageConstants.MODULE_SETTINGS);
		 * 
		 * System.out.println("Support Action is Working");
		 * 
		 * StreamDetailsBD obj = new StreamDetailsBD();
		 * List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();
		 * 
		 * String SearchName = request.getParameter("searchname");
		 * 
		 * 
		 * if(SearchName != null){
		 * 
		 * 
		 * 
		 * list=obj.searchStreamDetailsBD(SearchName);
		 * request.setAttribute("searchname", SearchName);
		 * request.setAttribute("searchnamelist", SearchName);
		 * 
		 * 
		 * 
		 * } else{
		 * 
		 * list =obj.getStreamDetailsBD();
		 * 
		 * }
		 * 
		 * request.setAttribute("streamDetailsList", list);
		 * 
		 * 
		 * 
		 * request.getSession(false).setAttribute("EXcelDownLoad",list);
		 */
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.SUPPORT_LIST);
	}

	// excel file upload

	public ActionForward studentfileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : EmpExcelUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_UPLOADSTUDENTEXCELDATAFILE);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EmpExcelUpload : Ending");

		return mapping.findForward(MessageConstants.STUDENT_EXCEL_UPLOAD);
	}

	

	public ActionForward homeworklist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : homeworklist Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_HOMEWORKS);

			List<SmsVo> homeworklist = new ArrayList<SmsVo>();
			String searchTerm = request.getParameter("searchvalue");

			if (searchTerm == null) {

				homeworklist = new SmsDeligate().getHomeWorklist();

				request.setAttribute("homeworklist", homeworklist);

			} else {

				homeworklist = new SmsDeligate().getHomeWorkSearchlist(searchTerm);

				request.setAttribute("homeworklist", homeworklist);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : homeworklist Ending");

		return mapping.findForward(MessageConstants.HOMEWORKS_LIST);
	}

	public ActionForward addHomeWork(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHomeWork Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_HOMEWORKS);

			/* List<ClassPojo> classList = new ArrayList<ClassPojo>(); */

			List<ClassPojo> classList = new SmsDeligate().getclasslist();

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHomeWork Ending");

		return mapping.findForward(MessageConstants.HOMEWORKS_ENTRY);
	}

	public ActionForward meetingslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_MEETINGSOREVENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
			String searchTerm = request.getParameter("searchvalue");

			if (searchTerm == null) {

				meetinglist = new SmsDeligate().getMeetingListDetails();

				request.setAttribute("meetinglist", meetinglist);

			} else {

				meetinglist = new SmsDeligate()
				.getMeetingSearchListDetails(searchTerm);

				request.setAttribute("meetinglist", meetinglist);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Ending");

		return mapping.findForward(MessageConstants.MEETING_LIST);
	}

	public ActionForward addMeeting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_MEETINGSOREVENTS);

			List<ClassPojo> classList = new SmsDeligate().getclasslist();

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Ending");

		return mapping.findForward(MessageConstants.MEETING_ENTRY);
	}
	public ActionForward suddenholiodayslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Starting");
	

			

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			try {
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
			
			//StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
			//ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			

			//request.setAttribute("studentSearchList", registrationVo1);

			
			//request.setAttribute("studentList", list);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Ending");
		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_LIST);

		//return mapping.findForward(MessageConstants.STUDENTSAPPRAISAL);
		
				
		
	}
	

	/*public ActionForward suddenholiodayslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : suddenholiodayslist : Starting");
		//String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_SUDDENHOLIDAYS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			//ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			//SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();

			//ArrayList<SuddenHolidaySMSVO> arrayList = new ArrayList<SuddenHolidaySMSVO>();
			//ArrayList<SuddenHolidaySMSVO> accYearList = new ArrayList<SuddenHolidaySMSVO>();
			ArrayList<SuddenHolidaySMSVO> accYearList = new SuddenHolidayListBD()
					. getAccYears();
			ArrayList<SuddenHolidaySMSVO> streamList = new SuddenHolidayListBD()
					.getStream();
		String schoolLocation = null;
			
			ArrayList<SuddenHolidaySMSVO> classList = new SuddenHolidayListBD().getStudentClass(schoolLocation);
			
			
			ArrayList<SuddenHolidaySMSVO> locationList = new SuddenHolidayListBD().getlocationList();
			request.setAttribute("locationList", locationList);
			  
			request.setAttribute("AccYearList", accYearList);

		

			request.setAttribute("StreamList", streamList);
			
			request.setAttribute("classList", classList);

		//	arrayList = holidayListBD.SuddenHolidayList();

		//	request.setAttribute("holidayList", arrayList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : suddenholiodayslist : Ending");

		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_LIST);
	}*/

	public ActionForward latecomingstudentslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_LATECOMINGSTUDENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();

			meetinglist = new SmsDeligate().getlatecomersListDetails();

			request.setAttribute("meetinglist", meetinglist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Ending");

		return mapping.findForward(MessageConstants.LATE_LIST);
	}

	public ActionForward addlatecomers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addlatecomers Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_LATECOMINGSTUDENTS);

			List<ClassPojo> classList = new SmsDeligate().getclasslist();

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addlatecomers Ending");

		return mapping.findForward(MessageConstants.LATECOMERSENTRYADD);
	}

	public ActionForward uniformlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : uniformlist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_UNIFORM);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();

			uniformlist = new SmsDeligate().getUniformListDetails();

			request.setAttribute("meetinglist", uniformlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : uniformlist Ending");

		return mapping.findForward(MessageConstants.UNIFORM_LIST);
	}

	public ActionForward addUniform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addUniform Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_UNIFORM);

			ClassPojo pojo = new ClassPojo();
			List<ClassPojo> classpojo = new CommunicationSettingsBD()
			.getClassListDetails(pojo);
			request.setAttribute("classpojo", classpojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addUniform Ending");

		return mapping.findForward(MessageConstants.UNIFORM_ENTRY);
	}

	public ActionForward absentlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_ABSENT);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			List<ClassPojo> classList = new SmsDeligate().getclasslist();
			request.setAttribute("classList", classList);

			ArrayList<AbsentsSMSPojo> list = new ArrayList<AbsentsSMSPojo>();
			list = new AbsentSMSBD().absentlist();

			request.setAttribute("absentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Ending");

		return mapping.findForward(MessageConstants.SMS_ABSENT);
	}

	public ActionForward absentlistFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlistFilter Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_ABSENT);

			ClassPojo pojo = new ClassPojo();
			List<ClassPojo> classpojo = new CommunicationSettingsBD()
			.getClassListDetails(pojo);
			request.setAttribute("classpojo", classpojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlistFilter Ending");

		return mapping.findForward(MessageConstants.SMS_ABSENT_FILTER);
	}

	public ActionForward expenseslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.EXPENSES_LIST);
	}

	public ActionForward inflowslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");
		System.out.println("inflows");
		return mapping.findForward(MessageConstants.INFLOWS_LIST);
	}

	public ActionForward revelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.REVENUE_LIST);
	}

	public ActionForward createExpenses(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.ADD_EXPENSES_LIST);
	}

	public ActionForward getTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSACTIONS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Ending");

		return mapping.findForward(MessageConstants.GET_TRANSACTION_LIST);
	}

	public ActionForward createTransaction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createTransaction Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSACTIONS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createTransaction Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION_LIST);
	}

	// <-----------------------------------INVENTORY
	// MODULE--------------------------------------------->

	public ActionForward InventoryTypeList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryVO> list = new ArrayList<InventoryVO>();

			String SearchName = request.getParameter("searchname");

			if (SearchName != null) {

				list = obj.searchInventoryTypeList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = obj.InventoryTypesList();

			}

			request.setAttribute("InventoryTypesList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.INVENTORY_TYPE_LIST);
	}

	public ActionForward createinventorytype(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails();
			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.INVENTORY_ADD);
	}

	public ActionForward InventoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			System.out.println("SEarch Name:::" + SearchName);

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList();

			}

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.INVENTORY_LIST);
	}

	public ActionForward InventoryListExcelDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InventoryDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList();

			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/InventoryDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Inventory  Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/InventoryDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=InventoryDetailsXLSReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Ending");
		return null;

	}

	public ActionForward InventoryListPDFDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Starting");

		try {

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList();

			}

			String sourceFileName = request
					.getRealPath("Reports/InventoryDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Image", PropfilePath);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Inventory Details PDF Report - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Ending");
		return null;

	}

	public ActionForward AddorModifyorDeleteList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchname");

			if (SearchName != null) {

				list = obj.SearchAddorModifyorDeleteList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().AddorModifyorDeleteList();

			}

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping
				.findForward(MessageConstants.ADD_or_MODIFY_or_DELETE_LIST);
	}

	public ActionForward AddPurchaseDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails();

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList();

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.ADD_PURCHASE_DETAILS);
	}

	public ActionForward TransactionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */

			list = obj.InventoryTransactionList();

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.TRANSACTION_LIST);
	}

	public ActionForward usageReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : usageReport Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			System.out.println("usage report action");

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails();

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList();

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : usageReport Ending");

		return mapping.findForward(MessageConstants.USAGE_REPORT);
	}

	// usage report of inventory items //

	public ActionForward getUsageReportAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			System.out.println("<<<<<<<<<<< Hello >>>>>>>>>>>>>>");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			InventoryTransactionForm invenTranForm = (InventoryTransactionForm) form;

			System.out.println("getDepartment Type: "
					+ invenTranForm.getDepartment());

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */

			list = obj.usageReportList(invenTranForm);

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward("usageReportList");
	}

	public ActionForward notReturnedReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			System.out.println("notReturnedReport report action");

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails();

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList();

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Ending");

		return mapping.findForward(MessageConstants.NOTRETURNED_REPORT);
	}

	// for not returned item report page//

	public ActionForward getNotReturnedReportAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			System.out.println("<<<<<<<<<<< Hello >>>>>>>>>>>>>>");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			InventoryTransactionForm invenTranForm = (InventoryTransactionForm) form;

			System.out.println("getDepartment Type: "
					+ invenTranForm.getDepartment());

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */

			list = obj.getNotReturnedReportAction(invenTranForm);

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Ending");

		return mapping.findForward(MessageConstants.NOTRETURNED_REPORT_LIST);
	}

	public ActionForward admissionsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			/*
			 * request.setAttribute(MessageConstants.MODULE_NAME,
			 * MessageConstants.BACKOFFICE_SETTINGS);
			 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
			 * MessageConstants.MODULE_SETTINGS);
			 */

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchadmissionsList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.getAdmisssionProcessingListDetails();
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.ADMISSION_LIST);
	}

	public ActionForward CalledForEvaluationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchCalledForEvaluationList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.CalledForEvaluationList();
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.CALLED_FOR_ADMISSION_LIST);
	}

	public ActionForward FinalAdmisssionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchCalledForEvaluationList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.FinalAdmisssionList();
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.FINAL_ADMISSION_LIST);
	}

	public ActionForward AddTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			/*
			 * List<InventoryVO> list = new Inventory_Delegate()
			 * .InventoryTypesList();
			 * 
			 * request.setAttribute("InventoryTypesList", list);
			 */
			System.out.println("AddTransaction Details  :::");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate obj = new Inventory_Delegate();

			list = obj.InventoryList();

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);
	}

	public ActionForward EditTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id = request.getParameter("transactionItemId");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate obj = new Inventory_Delegate();

			list = obj.InventoryList();

			request.setAttribute("list", list);

			List<AddorModifyorDeleteVO> lists = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			lists = objs.singleItemDetails(id);
			request.setAttribute("lists", lists);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);
	}

	public ActionForward singleItemDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id = request.getParameter("transactionItemId");

			List<AddorModifyorDeleteVO> lists = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			lists = objs.singleItemDetails(id);

			JSONObject object = new JSONObject();
			object.put("status", lists);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return null;
	}

	// for get available quantity//

	public ActionForward getAvailableQuantity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id = request.getParameter("item_id");

			String issued = request.getParameter("issued_quantity");
			System.out.println(issued);

			Inventory_Delegate objs = new Inventory_Delegate();

			String lists = objs.getAvailableQuantity(id, issued);

			JSONObject object = new JSONObject();
			object.put("status", lists);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return null;
	}

	// for getting department by item type//

	public ActionForward getItemtypeByDepartmnet(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			String department = request.getParameter("department");
			System.out.println(department);

			List<InventoryVO> list = new ArrayList<InventoryVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			list = objs.getItemtypeByDepartmnet(department);

			JSONObject object = new JSONObject();
			object.put("status", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return null;
	}

	public ActionForward stafffileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : StaffExcelUpload : Starting");

		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffExcelUpload : Ending");

		return mapping.findForward(MessageConstants.STAFF_EXCEL_UPLOAD);
	}

	public ActionForward destWiseStudentdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTREPORTDESTINATIONDETAILS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);


		return mapping.findForward(MessageConstants.DESTINATIONWISESTUDENT);

	}

	public ActionForward transportFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTFEE);
			
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}
			
			
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation));

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			

			

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Ending");
		return mapping.findForward(MessageConstants.TRANSPORTFEELIST);




	}

	public ActionForward studentDestList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTREPORTDESTINATIONDETAILS);
		List<StudentRegistrationVo> desstudentlist = null;
		String transid = request.getParameter("transcategory");
		String transloc = request.getParameter("translocation");
		System.out.println("what transaction id is printing:" + transid);
		System.out.println("what transport location is printing:" + transloc);
		if (transid != null && transloc != null) {

			desstudentlist = new StudentRegistrationDelegate()
			.getTranscationcategory(transloc);
		}

		request.setAttribute("desstudentlist", desstudentlist);
		request.getSession(false).setAttribute("transloc", transloc);
		System.out.println("What it is printing:" + desstudentlist);
		return mapping.findForward(MessageConstants.DESTINATIONWISESTUDENT);

	}

	public ActionForward destinationWiseStudentDetailsPDF(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : destinationWiseStudentDetailsPDF Starting");

		// String transloc=request.getSession().getAttribute("transloc");
		System.out.println("transloc"
				+ request.getSession().getAttribute("transloc"));
		try {

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("SchoolName", schName);
			mapdata.put("address", schAddLine1);
			mapdata.put("Image", PropfilePath);
			mapdata.put("transloc",
					request.getSession().getAttribute("transloc"));

			String filepath = request
					.getRealPath("Reports/destinationWiseStudentPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection());
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "destinationWiseStudentList" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public ActionForward destinationWiseStudentXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : destinationWiseStudentXL Starting");
		try {

			Map mapdata = new HashMap();
			mapdata.put("transloc",
					request.getSession().getAttribute("transloc"));

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/destinationWiseStudentXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, JDBCConnection.getConnection());
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/destinationWiseStudentXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "DestinationWiseStudent Details" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/destinationWiseStudentXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=destinationWiseStudentXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}

	public ActionForward studentIdCreation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");


		try {



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return mapping.findForward("studentIdCreate");

	}

	public ActionForward locationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : locationList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION);
			
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();

			String SearchName = request.getParameter("searchText");
			if (SearchName != null) {
				list = obj.searchLocationDetails(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				list = obj.getLocationDetails();
			}

			request.setAttribute("locationDetailsList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.LOCATIONLIST);
	}

	public ActionForward addLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Starting");
		try {
			String arg = "Add New Location";
			request.setAttribute("Location", arg);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Ending");

		return mapping.findForward(MessageConstants.ADD_LOCATION);

	}

	public ActionForward casteDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();
			String religionId = "%%";
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchCaste(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				System.out.println("else Part");
				list = obj.getCasteDetails(religionId);
			}

			request.setAttribute("religionList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteDetails Ending");

		return mapping.findForward(MessageConstants.CASTE_LIST);
	}

	public ActionForward casteCategoryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteCategoryDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchCasteCategory(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				list = obj.getCasteCategoryDetails();
			}

			request.setAttribute("religionList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteCategoryDetails Ending");

		return mapping.findForward(MessageConstants.CASTE_CATEGORY_LIST);
	}

	public ActionForward religionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : religionDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchReligion(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				System.out.println("Action Class 	 ReligionDetails ");
				list = obj.getReligionDetails();
			}

			request.setAttribute("religionList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : religionDetails Ending");

		return mapping.findForward(MessageConstants.RELIGION_LIST);
	}

	public ActionForward SpecializationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : specializationList Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SPECIALIZATIONDETAILS);
			

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			SpecializationBD obj = new SpecializationBD();
			ArrayList<SpecializationVo> list = new ArrayList<SpecializationVo>();
			String searchterm = request.getParameter("searchText");

			if (searchterm != null) {
				list = obj.getSearchSpecializationList(searchterm.trim(),request.getParameter("SchoolName"));
				request.setAttribute("searchname", searchterm);
				request.setAttribute("searchnamelist", searchterm);

			} else {
				list = obj.getspecializationList(schoolLocation);
			}

			request.setAttribute("SpecializationList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.SPECIALIZATION_LIST);
	}

	public ActionForward addSpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addSpecialization Starting");

		try {

			String title = "Add New Specialization";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SPECIALIZATIONDETAILS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.ADD_SPECIALIZATION);
	}

	public ActionForward occupationDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : occupationDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.getOccupationDetailsSearch(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				System.out.println("Action Class adminMenu occupationDetails ");
				list = obj.getOccupationDetails();
			}

			request.setAttribute("religionList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : occupationDetails Ending");

		return mapping.findForward(MessageConstants.OCCUPATION_LIST);
	}

	public ActionForward saveChangeImage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Starting");

		try {
			System.out.println("Inside the changeImage");
			InventoryTransactionForm formObj = (InventoryTransactionForm) form;
			System.out.println("formObj :" + formObj);
			FormFile file = formObj.getInputfile();
			String filepath = null, base = null, filecuurentpath = null;
			BufferedImage bufferedImage = null;
			filecuurentpath = request.getRealPath("/")+ "CSS/images/aryawatermark.jpg";
			System.out.println(filecuurentpath);
			File f1 = new File(filecuurentpath);
			if (f1 != null) {
				f1.delete();
			}

			String extension = "";
			int j = (file).getFileName().lastIndexOf('.');
			if (j >= 0) {
				base = (String) ((j == -1) ? file : (file).getFileName()
						.substring(0, j));
				extension = (file).getFileName().substring(j + 1);
				base = "aryawatermark";
			}
			System.out.println("extension is " + extension);
			if (extension.equalsIgnoreCase("jpg")) {

				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;

				System.out.println(filepath);
				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("jpeg")) {

				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;
				System.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("png")) {
				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;
				System.out.println(filepath);

				// read image file
				bufferedImage = ImageIO.read(new File(filepath));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(
						bufferedImage.getWidth(), bufferedImage.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0,
						0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(
						newBufferedImage,
						"jpg",
						new File(request.getRealPath("/")+"CSS/images/aryawatermark.jpg"));

			}
			// byte[] btDataFile = (file).getFileData();

			// ImageIO.write(image, "png",new File("C:\\out.png"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Ending");

		return null;
	}

	public ActionForward holidaymaster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : holidaymaster Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");

			HolidayMasterBD obj = new HolidayMasterBD();
			ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();

			String searchterm = request.getParameter("location");
			
			if (searchterm != null) {
				list = obj.searchLocationDetails(searchterm, academic_year);
				request.setAttribute("searchnamelist", searchterm);
			} else {
				list = obj.getHolidayDetails(academic_year,schoolLocation);
			}
			request.setAttribute("HolidayList", list);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD()
			.getlocationList();
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.HOLIDAY_LIST);
	}

	public ActionForward addHoliday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHoliday Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD()
			.getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
			.getAccYears();
			
			request.setAttribute("AccYearList", accYearList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHoliday Ending");

		return mapping.findForward(MessageConstants.ADD_HOLIDAY);
	}

	public ActionForward transportTermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Starting");

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.TERM_SUCCESS);
			}
		}

		String name = request.getParameter("searchvalue");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);

			request.setAttribute("searchterm",
					request.getParameter("searchvalue"));

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
			.termList(vo);
			request.setAttribute("termlist", termlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Ending");

		return mapping.findForward(MessageConstants.TERM_LIST);

	}

	public ActionForward TdsSlabDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Ending");

		return mapping.findForward(MessageConstants.TDS_SLAB_DETAILS);
	}

	public ActionForward TDSComputationDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TDSComputationDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_ITDECLARATION);


			UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userId=user.getUserId();
			String userType=user.getUserNametype();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			
			int count=new TeacherRegistrationBD().checkStaffInTDS(userId);
			System.out.println("count is "+count);
			if(count == 0){
				String str="IT Declaration Is Not Declarated";
				request.setAttribute("itdeclaraton", str);
			}else{
				StaffEmployementVo list = new StaffEmployementVo();
				StaffEmployementVo list1 = new StaffEmployementVo();
				
				list = new TDSComputationBD().getEmployeeDetails(userType,userId,academic_year);
				
				request.setAttribute("list", list);
				
				list1 = new TDSComputationBD().getStaffMaximumLimitDetails(academic_year,location);
				
				request.setAttribute("maximumlist", list1);
			}
			String year = new StudentRegistrationDaoImpl().getSingleAcademicYear(academic_year);
			
			request.setAttribute("academic_year", year);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TDSComputationDetails Ending");

		return mapping.findForward(MessageConstants.TDS_COMPUTATION_DETAILS);
	}

	public ActionForward stagefileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : EmpExcelUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADSTAGEEXCELDATAFILE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EmpExcelUpload : Ending");

		return mapping.findForward(MessageConstants.STAGE_EXCEL_UPLOAD);
	}

	public ActionForward gettimetablelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Starting");

		try {

			String classId = request.getParameter("classId");
			String viewBy = request.getParameter("viewBy");
			String locationId=request.getParameter("locationId");
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */

			System.out.println("viewBy::::::" + viewBy);
			ArrayList<TimeTableVo> arr =null;
			if (classId != null) {
				
				arr= new TimeTableBD().getClassTimeTableListByClass(accyearid, viewBy,classId,locationId);
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("ClassTimeTableList", arr);
				response.getWriter().print(jsonobj);
			}
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Ending");

		return null;

	}

	public ActionForward gettimetablelistbysection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Starting");

		try {

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String accyearid = request.getSession(false)
					.getAttribute("current_academicYear").toString();

			if (classId != null) {
				ArrayList<TimeTableVo> arr = new TimeTableBD()
				.getClassTimeTableListBySection(accyearid, classId,
						sectionId);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("ClassTimeTableList", arr);

				response.getWriter().print(jsonobj);
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTimeTable Ending");

		return null;

	}

	public ActionForward tempadmissionMenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminAction : tempadmissionMenu  Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			

			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			System.out.println("SearchName From Action:" +SearchName);
			if (SearchName != null) {
				list = obj.searchadmformDetails(SearchName.trim());
				request.setAttribute("searchname", SearchName);

			} else {
				list = new TemporaryRegBD().getissuedforms(); 

			}
			request.setAttribute("issuedList", list);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();
			System.out.println("Stream:" + streamList);

			request.setAttribute("StreamList", streamList);


			ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD()
			.getApprovedForms();

			request.setAttribute("getapprvedlist", getapprvedlist);

			/*ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
			request.setAttribute("issuedList", issuedList);*/
			ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD()
			.getRejectedlist();

			request.setAttribute("rejectlist", rejectlist);
			ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
			.getCancelledForms();
			request.setAttribute("getcancelledlist", getcancelledlist);

			ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
			.getSubmittedForms();
			request.setAttribute("getsubmittedlist", getsubmittedlist);

			ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
			.getProcessedForms();
			request.setAttribute("getprocessedlist", getprocessedlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminAction : tempadmissionMenu Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward staffExcelFileUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : staffExcelFileUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADSTAFFEXCELDATAFILE);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffExcelFileUpload : Ending");

		return mapping.findForward(MessageConstants.STAFF_EXCEL_FILE_UPLOAD);
	}

	public ActionForward studentIDGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Starting");


		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Ending");

		return mapping.findForward(MessageConstants.STUDENTIDGENERATION);
	}

	public ActionForward studentBusIDGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Starting");


		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_PRINTBUSIDCARD);




		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Ending");

		return mapping.findForward(MessageConstants.STUDENTBUSIDGENERATION);
	}
	public ActionForward searchStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Starting");

		try {

			List<StudentIDVo> studentlist = new ArrayList<StudentIDVo>();

			String acadamicYear = request.getParameter("acadamicyear");
			String section = request.getParameter("section");
			String classVal = request.getParameter("class");
			studentlist = new StudentIDDaoImpl().getStudentData(acadamicYear,
					section, classVal);

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", studentlist);
			response.getWriter().print(jsonobject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Ending");

		return null;
	}

	public ActionForward studentIdCreationPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		String accyear = request.getParameter("AccId");
		String section = request.getParameter("Section");
		String className = request.getParameter("Class");
		String student = request.getParameter("studentname");


		try {

			List<StudentIDVo> studentList = new StudentIDBD().getstudentIDPDFReport(accyear, section, className, student);
			List<StudentIDVo> list = new ArrayList<StudentIDVo>();

			for (int i = 0; i < studentList.size(); i++) {

				StudentIDVo vo = new StudentIDVo();
				String fileName = studentList.get(i).getImage();
				String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
				vo.setImages(".\\FIles\\STUDENTIMAGES\\" + fileName.trim());

				vo.setStuName(studentList.get(i).getStuName());
				vo.setClassName(studentList.get(i).getClassName() + "&"
						+ studentList.get(i).getSection());
				vo.setSection(studentList.get(i).getSection());
				vo.setFatherName(studentList.get(i).getFatherName());
				vo.setAdress(studentList.get(i).getAdress());
				vo.setPhone(studentList.get(i).getPhone());
				vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
				vo.setValidity(studentList.get(i).getValidity());
				vo.setAdmissionno(studentList.get(i).getAdmissionno());
				vo.setMotherName(studentList.get(i).getMotherName());
				list.add(vo);

			}

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", list);
			response.getWriter().print(jsonobject);
			request.setAttribute("StudentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return null;

	}

	public ActionForward Issuedsearchdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Issuedsearchdetails Starting");

		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			if (searchTerm != null) {

				subjectlist = new AddSubjectDelegate()
				.searchsubjectdetails(obj);

				request.setAttribute("searchTerm", searchTerm);
				request.setAttribute("searchnamelist", searchTerm);

			} else {

				subjectlist = new AddSubjectDelegate().subjectdetails(schoolLocation);

			}

			request.setAttribute("allsubjects", subjectlist);

			request.setAttribute("successmessagediv", message);

			request.getSession(false)
			.setAttribute("EXcelDownLoad", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Issuedsearchdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	public ActionForward issuedformEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD()
			.editIssuedForm(edit);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping
				.findForward(MessageConstants.DETAILS_ISSUED_FORM_DETAILS);

	}

	public ActionForward EditissuedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		try {
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();
			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("enquiryid");
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD()
			.editIssuedForm(edit);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping.findForward(MessageConstants.EDIT_ISSUED_FORM_DETAILS);
	}

	public ActionForward admissionaddmenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
		ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
		request.setAttribute("StreamList", streamList);
		
		request.setAttribute("locationList", HelperClass.getAllLocation());
		request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
		
		
		
		return mapping.findForward(MessageConstants.ADD_ADMISSION_FORM_DETAILS);
		

	}

	public ActionForward updateissuelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String enquiryid = request.getParameter("enq_code");
		String firstname = request.getParameter("parentfirstName");
		String lastname = request.getParameter("parent_LastName");
		String parname = request.getParameter("parents_name");
		String paremailid = request.getParameter("parentEmailId");
		String address = request.getParameter("address");
		String stu_parrelation = request.getParameter("stu_parrelation");
		String mobile_no = request.getParameter("mobile_number");
		String stream = request.getParameter("stream");
		String classid = request.getParameter("classid");
		String enquriyid = request.getParameter("enq_code");
		
		Issuedmenuvo vo = new Issuedmenuvo();
		vo.setParentfirstName(firstname);
		vo.setParent_LastName(lastname);
		vo.setParents_name(parname);
		vo.setParentEmailId(paremailid);
		vo.setStu_parrelation(stu_parrelation);
		vo.setMobile_number(mobile_no);
		vo.setStreamId(stream);
		vo.setClassid(classid);
		vo.setAddress(address);
		ReportsMenuBD details = new ReportsMenuBD();
		String result = details.insertadmissionDetailsAction(vo, enquriyid);
		JSONObject object = new JSONObject();
		object.put("status", result);
		response.getWriter().print(object);

		return null;

	}

	public ActionForward IdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		String mainCss = request.getParameter("mainCss");
		String layout=request.getParameter("layout");
		String imgUrl=request.getParameter("imgUrl");
		System.out.println("imgurl"+imgUrl);
		
		System.out.println("realimgurl"+request.getRealPath("/")+imgUrl);
		if(!imgUrl.equalsIgnoreCase("noImage")){
			
			 FileInputStream is = null;
			         FileOutputStream os = null;
			         try {
			        	 File sourceFile=new File(request.getRealPath("/")+imgUrl);
			        	 File desFile=new File(request.getRealPath("/")+"images/IdCard/"+layout+".jpg");
			             is = new FileInputStream(sourceFile);
			             os = new FileOutputStream(desFile);
			             byte[] buffer = new byte[1024];
			             int length;
			             while ((length = is.read(buffer)) > 0) {
			                 os.write(buffer, 0, length);
			             }
			         }catch(Exception ex) {
			             System.out.println("Unable to copy file:"+ex.getMessage());
			         }   
			         finally {
			             try {
			                 is.close();
			                 os.close();
			             }catch(Exception ex) {}
			         }
			     }
		

		
		String newCssArray[] = mainCss.split("}");
		
		File file = new File(request.getRealPath("/")+ "CSS/IdCard/"+layout+".css");
		if(file.exists()){
			
		}
		else{
			file.createNewFile();
		}
		
		for (String css : newCssArray) {
			System.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME =request.getRealPath("/")+ "CSS/IdCard/"+layout+".css";
		File temp=null;
		temp = new File("IdCard.css");
		String absolutePath = temp.getAbsolutePath();
		System.out.println("absolutePath  "+absolutePath);
		try {

			System.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					System.out.println("each css" + css);

				}

			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			System.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return null;

	}
	
	public ActionForward StaffIdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffIdDesign Starting");
		String mainCss = request.getParameter("mainCss");
		String layout=request.getParameter("layout");
		String imgUrl=request.getParameter("imgUrl");
		System.out.println("imgurl"+imgUrl);
		
		System.out.println("realimgurl"+request.getRealPath("/")+imgUrl);
		if(!imgUrl.equalsIgnoreCase("noImage")){
			
			 FileInputStream is = null;
			         FileOutputStream os = null;
			         try {
			        	 File sourceFile=new File(request.getRealPath("/")+imgUrl);
			        	 File desFile=new File(request.getRealPath("/")+"images/IdCard/"+layout+".jpg");
			             is = new FileInputStream(sourceFile);
			             os = new FileOutputStream(desFile);
			             byte[] buffer = new byte[1024];
			             int length;
			             while ((length = is.read(buffer)) > 0) {
			                 os.write(buffer, 0, length);
			             }
			         }catch(Exception ex) {
			             System.out.println("Unable to copy file:"+ex.getMessage());
			         }   
			         finally {
			             try {
			                 is.close();
			                 os.close();
			             }catch(Exception ex) {}
			         }
			     }
		

		
		String newCssArray[] = mainCss.split("}");
		
		File file = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css");
		if(file.exists()){
			
		}
		else{
			file.createNewFile();
		}
		
		for (String css : newCssArray) {
			System.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME =request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css";
		File temp=null;
		temp = new File("StaffIdCard.css");
		String absolutePath = temp.getAbsolutePath();
		System.out.println("absolutePath  "+absolutePath);
		System.out.println("hello="+request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css");
		try {

			System.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					System.out.println("each css" + css);

				}

			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			System.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffIdDesign Ending");
		return null;

	}

	public ActionForward LeaveCategoryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveCategoryList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_LEAVE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVETYPES);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			String searchTearm = request.getParameter("searchvalue");
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();

			if (searchTearm != null) {
				list = new LeaveBankDelegate().getSearchleavetypeDetails(
						searchTearm, academic_year);
				request.setAttribute("searchname", searchTearm);
				request.setAttribute("searchnamelist", searchTearm);
			} else {
				list = new LeaveBankDelegate().getleavetypeDetails(academic_year);
			}
			request.setAttribute("LeaveListDetails", list);

		} catch (Exception e) {

			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : LeaveCategoryList Ending");
		return mapping.findForward(MessageConstants.LEAVECAT_LIST);
	}

	public ActionForward ApprformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();
			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD()
			.apprIssuedForm(edit);

			request.setAttribute("edit_issuedlist", edit_issuedlist);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_APPR_FORM_DETAILS);
	}

	public ActionForward RejectformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();
			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD()
			.rejectFormdetails(edit);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping
				.findForward(MessageConstants.DETAILS_REJECT_FORM_DETAILS);
	}

	public ActionForward searchapprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchapproveformDetails(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD()
				.getRejectedlist();
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
				.getCancelledForms();
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
				.getSubmittedForms();
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
				.getProcessedForms();
				request.setAttribute("getprocessedlist", getprocessedlist);


			} else {
				list = obj.getApprovedForms();
				request.setAttribute("getapprvedlist", list);
			}
			request.setAttribute("getapprvedlist",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchreject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchrejectformDetails(SearchName.trim());
				request.setAttribute("SearchName", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD()
				.getApprovedForms();

				request.setAttribute("getapprvedlist", getapprvedlist);		

				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
				.getCancelledForms();
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
				.getSubmittedForms();
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
				.getProcessedForms();
				request.setAttribute("getprocessedlist", getprocessedlist);
			} else {
				list = new TemporaryRegBD().getRejectedlist();
			}
			request.setAttribute("rejectlist", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward secondadmissionformat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");

		return mapping.findForward(MessageConstants.SECONDADMISSIONFORMAT);

	}

	public ActionForward thirdadmissionformat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");
		
		

		return mapping.findForward(MessageConstants.THIRDADMISSIONFORMAT);

	}

	public ActionForward AddNewAdmissionApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//
		// logger.setLevel(Level.DEBUG);
		// JLogger.log(0, JDate.getTimeString(new Date())
		// + MessageConstants.START_POINT);
		// logger.info(JDate.getTimeString(new Date())
		// + " Control in AdminMenuAction : streamList Starting");
		// try {
		//
		// ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
		// .getAccYears();
		// request.setAttribute("AccYearList", accYearList);
		//
		// ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
		// .getClassesByStream("%%");
		//
		// ArrayList<ReportMenuVo> List = new ArrayList<ReportMenuVo>();
		// for (int i = 0; i < classList.size(); i++) {
		//
		// ReportMenuVo list = new ReportMenuVo();
		//
		// String className = classList.get(i).getClassname();
		// if (!(className.equalsIgnoreCase("X")
		// || className.equalsIgnoreCase("XI") || className
		// .equalsIgnoreCase("XII"))) {
		//
		// list.setClassname(className);
		// List.add(list);
		// }
		// }
		//
		// request.setAttribute("classList", List);
		//
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// e.printStackTrace();
		// }
		//
		// JLogger.log(0, JDate.getTimeString(new Date())
		// + MessageConstants.END_POINT);
		// logger.info(JDate.getTimeString(new Date())
		// + " Control in AdminMenuAction : streamList Ending");

		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");

		return mapping.findForward(MessageConstants.ADD_NEW_APPLICATION);
	}

	public ActionForward CancelformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo cancel_list = new TemporaryRegBD()
			.cancelFormdetails(edit);

			request.setAttribute("edit_issuedlist", cancel_list);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_CANCELLED_FORM_DETAILS);
	}

	public ActionForward separateTransportTermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : separateTransportTermList Starting");
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

				request.setAttribute("successmessagediv",MessageConstants.TERM_SUCCESS);
			}
		}
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		String name = request.getParameter("searchvalue".trim());

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TERMSETUP);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(academic_year);
			request.setAttribute("searchvalue",request.getParameter("searchvalue".trim()));

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().separateTransportTermList(vo);
			request.setAttribute("termlist", termlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : separateTransportTermList Ending");

		return mapping.findForward(MessageConstants.SEPARATE_TRANSPORT_TERM_LIST);
	}

	public ActionForward submitformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo cancel_list = new TemporaryRegBD()
			.submitFormdetails(edit);

			request.setAttribute("edit_issuedlist", cancel_list);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping
				.findForward(MessageConstants.DETAILS_SUBMITTED_FORM_DETAILS);
	}

	public ActionForward processformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

			request.setAttribute("StreamList", streamList);
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo process_list = new TemporaryRegBD()
			.processFormdetails(edit);

			request.setAttribute("edit_issuedlist", process_list);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editissueddetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_PROCESSED_FORM_DETAILS);
	}

	public ActionForward OnlinereturnFormPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward(MessageConstants.ONLINE_REGISTRATION_RORM_RETURNS);

	}

	public ActionForward searchcancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchcancelformDetails(SearchName.trim());
				request.setAttribute("Searchcancel", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms();
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist();
				request.setAttribute("rejectlist", rejectlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
				.getSubmittedForms();
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
				.getProcessedForms();
				request.setAttribute("getprocessedlist", getprocessedlist);

			} else {
				list = new TemporaryRegBD().getCancelledForms();
			}
			request.setAttribute("getcancelledlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchsubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchsubmitformDetails(SearchName.trim());
				request.setAttribute("Searchsubmit", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms();
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist();
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
				.getCancelledForms();
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
				.getProcessedForms();
				request.setAttribute("getprocessedlist", getprocessedlist);
			} else {
				list = new TemporaryRegBD().getSubmittedForms();
			}
			request.setAttribute("getsubmittedlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchprocess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchprocessformDetails(SearchName.trim());
				request.setAttribute("Searchprocess", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms();
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist();
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
				.getSubmittedForms();
				request.setAttribute("getsubmittedlist", getsubmittedlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
				.getCancelledForms();
				request.setAttribute("getcancelledlist", getcancelledlist);



			} else {
				list = new TemporaryRegBD().getProcessedForms();
			}
			request.setAttribute("getprocessedlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward transportCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportCategory Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			TransportBD obj = new TransportBD();
			List<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();

			String SearchName = request.getParameter("searchText".trim());
			if (SearchName != null) {
				getvehiclelist = obj.searchVehicletypeDetails(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				getvehiclelist= obj.getAllvehicletypeDetails();
			}
			request.setAttribute("vehicleTypeList", getvehiclelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportCategory Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_CATEGORY);
	}

	

	public ActionForward driverfileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : driverfileupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADDRIVEREXCELDATAFILE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverfileupload : Ending");

		return mapping.findForward(MessageConstants.DRIVER_EXCEL_UPLOAD);
	}

	public ActionForward studentBusCard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentBusCard Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTBUSCARD);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("Acyearid",academic_year);
			
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			

			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentBusCard Ending");
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
}



	public ActionForward getAcademicNextYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getAcademicNextYear : Starting");

		String currentYear=request.getParameter("currentYear");
		try {
			ArrayList<String> nextYearList=HelperClass.getNextAccYearDetails(currentYear);


			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", nextYearList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAcademicNextYear : Ending");

		return null;
	}


	public ActionForward getClassListForPromotion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getClassListForPromotion : Starting");
		List<ClassPromotionList> classList=new ArrayList<ClassPromotionList>();
		String currentYear=request.getParameter("currentYear");
		try {

			StudentPramotionBD promotedList=new StudentPramotionBD();
			classList=promotedList.getClassListForPromotion(currentYear);

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", classList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassListForPromotion : Ending");

		return null;
	}

	public ActionForward busIdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsReport Starting");
		String mainCss = request.getParameter("mainCss");
		String newCssArray[] = mainCss.split("}");

		for (String css : newCssArray) {
			System.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME = request.getRealPath("/")+ "CSS/BusIdCard.css";
		try {

			System.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					System.out.println("each css" + css);

				}

			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			System.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIDPDFReport Ending");
		return null;

	}


	//--------------------------student ID card Design and print----------------------------

	public ActionForward NewstudentIdCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");


		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return mapping.findForward(MessageConstants.New_StdIDCard_Design);
	}

	public ActionForward NewstudentIdCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardPrint Starting");

		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);
			

		
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);

			
			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("locationAddress", list.get(0).getLocation_address());
			request.setAttribute("locationPhone", list.get(0).getLocation_phone());
			request.setAttribute("templateClass", accyear+schoolId+streamId);

		

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardPrint Ending");

		return mapping.findForward(MessageConstants.New_StdIDCard_Print);
	}

	public ActionForward StaffSingleIDCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardPrint Starting");
		
		String teacherID = request.getParameter("teacherID");
		String accyear=request.getParameter("accyear");
		String location=request.getParameter("locationId");
		String desigantion=request.getParameter("designation");
		String department=request.getParameter("departmentId");
		System.out.println("department------------------------ di +"+department);

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDSINGLE);

			PageFilterpojo filterpojo=new PageFilterpojo();
			
			filterpojo.setTeacherID(teacherID);
			filterpojo.setLocationId(location);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setDesignationId(desigantion);
			filterpojo.setDepartmentId(department);

			list=new  IDGenerator().getstaffIdCardprintList(filterpojo);
			
			request.setAttribute("staffDetails", list);
			request.setAttribute("template", accyear+location+list.get(0).getDesignationId());
			System.out.println(list.size());
					
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardPrint Ending");

		return mapping.findForward(MessageConstants.StaffSingleIDCardPrint);
	}

	public ActionForward DesignStaffIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStaffIDCard Starting");
		try{
	
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTAFFIDCARD);


	
	ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
	request.setAttribute("locationList", locationList);
	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
	request.setAttribute("AccYearList", accYearList);
	
	

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStaffIDCard Ending");

		return mapping.findForward(MessageConstants.DesignStaffIDCard);
	}
	public ActionForward PrintStaffIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String departmentId=request.getParameter("departmentId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTAFFIDCARD);

			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setDepartmentId(departmentId);
			list=new  IDGenerator().getstaffIdCardDesignList(filterpojo);

			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("departmentName", list.get(0).getDepartmentName());
			request.setAttribute("locationAddress", list.get(0).getLocation_address());
			request.setAttribute("locationPhone", list.get(0).getLocation_phone());
			request.setAttribute("templateClass", accyear+schoolId+departmentId);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.PrintStaffIDCard);
	}


	public ActionForward StaffSingleIDCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StaffSingleIDCardDesign Starting");
		
		
	
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDSINGLE);

			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : StaffSingleIDCardDesign Ending");
		
				

		return mapping.findForward(MessageConstants.StaffSingleIDCardDesign);
	}

	public ActionForward StaffSingleIDCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StaffSingleIDCardDesign Starting");
		
		
		
		
	/*	String accyear=request.getParameter("academicYear");
		String locationId11=request.getParameter("locationId");
		String designation=request.getParameter("designation");
		String department=request.getParameter("department");
		System.out.println("acc year========"+accyear);
		System.out.println("acc year========"+locationId11);
		System.out.println("acc year========"+designation);
		System.out.println("acc year========"+department);
		*/
		
		
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			String designationId=request.getParameter("designationId");
			if(designationId.equalsIgnoreCase("all")){
				designationId="%%";
			}
			String departmentId=request.getParameter("departmentId");
			if(departmentId.equalsIgnoreCase("all") ){
				departmentId="%%";
			}
		
			/*String departmentId=request.getParameter("departmentId");
			System.out.println("departmentid=============="+departmentId);*/
			
			PageFilterpojo filterpojo=new PageFilterpojo();
	
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setLocationId(locationId);
			filterpojo.setDesignationId(designationId);
			filterpojo.setDepartmentId(departmentId);
			
		
			//list=new IDGenerator().getSingleStaffCardDesignList(filterpojo);
			
			JSONObject obj=new JSONObject();
			obj.put("streamList", list);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : StaffSingleIDCardDesign Ending");
		
		return null;
	}

	
	public ActionForward StaffMultipleIDCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.StaffMultipleIDCardPrint);
	}


	public ActionForward printMultiStaffIDCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		


		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDMULTIPLE);


			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.printMultiStaffIDCard);
	}

	
	

/*renamed*/
	public ActionForward StaffMultipleIDCardPrint1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.StaffMultipleIDCardPrint);
	}


	public ActionForward DesignStudentSingleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDSINGLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList=new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
			else{
				
				stuList=new StudentRegistrationDelegate().getStudentList(academic_year,location);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		
		
		/*String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
			
			request.setAttribute("template", list);
			request.setAttribute("locationName", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		*/

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.DesignStudentSingleIDCard);
	}

	public ActionForward PrintStudentSingleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintStudentSingleIDCard Starting");


		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCard(studentId,accYear,locationId);

			request.setAttribute("studentSearchList", list);
			request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : PrintStudentSingleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintStudentSingleIDCard);
	}
	public ActionForward PrintStudentMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintStudentMultipleIDCard Starting");

		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDMULTIPLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList=new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
			else{
				
				stuList=new StudentRegistrationDelegate().getStudentList(academic_year,location);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
			JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : PrintStudentMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintStudentMultipleIDCard);
	}
	public ActionForward PrintPreviewStudentMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		List<StudentIDVo> studentList = new StudentIDDaoImpl().getstudentIDPDFReport(accyear,locationId,studentId);
		List<StudentIDVo> list = new ArrayList<StudentIDVo>();

		for (int i = 0; i < studentList.size(); i++) {

			StudentIDVo vo = new StudentIDVo();
			String fileName = studentList.get(i).getImage();
			vo.setImages("./"+fileName);

			vo.setStuName(studentList.get(i).getStuName());
			vo.setClassName(studentList.get(i).getClassName() + " - "
					+ studentList.get(i).getSection());
			vo.setSection(studentList.get(i).getSection());
			vo.setFatherName(studentList.get(i).getFatherName());
			vo.setAdress(studentList.get(i).getAdress());
			vo.setPhone(studentList.get(i).getPhone());
			vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
			vo.setValidity(studentList.get(i).getValidity());
			vo.setAdmissionno(studentList.get(i).getAdmissionno());
			vo.setMotherName(studentList.get(i).getMotherName());
			vo.setHouseName(studentList.get(i).getHouseName());
			vo.setEmergencyNo(studentList.get(i).getEmergencyNo());
			vo.setSchoolName(studentList.get(i).getSchoolName());
			vo.setLocation_address(studentList.get(i).getLocation_address());
			vo.setLocation_phone(studentList.get(i).getLocation_phone());
			vo.setDob(studentList.get(i).getDob());
			vo.setAdharNo(studentList.get(i).getAdharNo());
			vo.setBgroup(studentList.get(i).getBgroup());
			list.add(vo);

		}
		
		request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]);
		request.setAttribute("StudentList", list);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.PrintPreviewStudentMultipleIDCard);
	}
	public ActionForward DesignTransportIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNTRANSPORTIDCARD);

		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.DesignTransportIDCard);
	}
	public ActionForward PrintTransportIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintTransportIDCard Starting");
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNTRANSPORTIDCARD);

			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);

			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId+"transport");

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : PrintTransportIDCard Ending");

		return mapping.findForward(MessageConstants.PrintTransportIDCard);
	}



	public ActionForward DesignTransportIDCardSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DesignTransportIDCardSingle Starting");


		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDSINGLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
	/*		
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
			
			request.setAttribute("template", list);
			request.setAttribute("locationName", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId);*/
			
			
			
			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList = new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year);
			}
			else{
				
				stuList= new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : DesignTransportIDCardSingle Ending");

		return mapping.findForward(MessageConstants.DesignTransportIDCardSingle);
	}



	public ActionForward PrintTransportIDCardSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintTransportIDCardSingle Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDSINGLE);
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
		List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCard(studentId,accYear,locationId);

			request.setAttribute("studentSearchList", list);
			request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode()+"transport");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : PrintTransportIDCardSingle Ending");

		return mapping.findForward(MessageConstants.PrintTransportIDCardSingle);
	}

	public ActionForward PrintTransportMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDMULTIPLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("Acyearid",academic_year);
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.PrintTransportMultipleIDCard);
	}

	public ActionForward PrintPreviewTransportMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDMULTIPLE);
			
			List<StudentIDVo> studentList = new StudentIDBD().getstudentBusIDPDFReport(accyear, locationId, streamId, studentId);
			List<StudentIDVo> list = new ArrayList<StudentIDVo>();

			for (int i = 0; i < studentList.size(); i++) {

				StudentIDVo vo = new StudentIDVo();
				String fileName = studentList.get(i).getImage();
				String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
				vo.setImages("./"+fileName.trim());

				vo.setStuName(studentList.get(i).getStuName());
				vo.setClassName(studentList.get(i).getClassName() + " - "
						+ studentList.get(i).getSection());
				vo.setSection(studentList.get(i).getSection());
				vo.setFatherName(studentList.get(i).getFatherName());
				vo.setAdress(studentList.get(i).getAdress());
				vo.setPhone(studentList.get(i).getPhone());
				vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
				vo.setValidity(studentList.get(i).getValidity());
				vo.setAdmissionno(studentList.get(i).getAdmissionno());
				vo.setMotherName(studentList.get(i).getMotherName());
				vo.setRoute_no(studentList.get(i).getRoute_no());
				vo.setPoint_name(studentList.get(i).getPoint_name());
				vo.setSchoolName(studentList.get(i).getSchoolName());
				list.add(vo);

			}

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", list);
			response.getWriter().print(jsonobject);
			
			
			
			request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]+"transport");
			request.setAttribute("StudentList", list);
			request.setAttribute("templateClass", accyear[0]+locationId[0]+streamId[0]+"transport");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.PrintPreviewTransportMultipleIDCard);
	}
	
	

public ActionForward NewTransIdCardFilterList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");
	
	List<PageFilterVo> list=new ArrayList<PageFilterVo>();
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);
		
		
		String streamId=request.getParameter("streamId");
		if(streamId.equalsIgnoreCase("all")){
			streamId="%%";
		}
		PageFilterpojo filterpojo=new PageFilterpojo();
		filterpojo.setLocationId(request.getParameter("locationId"));
		filterpojo.setAcademicYear(request.getParameter("academicYear"));
		filterpojo.setStreamId(streamId);
		list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", list);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");
	
	return null;
}
	
/*	Election module actions--------------------BEGIN-------------------------------*/
	
	
	




/*	Election module actions--------------------BEGIN-------------------------------*/
public ActionForward electionList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : electionList Starting");

	try{
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONSETTING);
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		
		String academicYear = "%%";
		String groupId = "%%";
		ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
		dataList = new ElectionBD().getElectionDetails(academicYear,groupId);
		request.setAttribute("DataList", dataList);	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : electionList Ending");

	return mapping.findForward(MessageConstants.electionList);
}

	
	public ActionForward GroupList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : GroupList Starting");

				
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_GROUPSETTING);
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
			
			
			List<ElectionVo> Data = new ArrayList<ElectionVo>();
			
			Data = new ElectionBD().getGroupdetails();
			request.setAttribute("DataList", Data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : electionList Ending");

		return mapping.findForward(MessageConstants.GroupList);
	}

	public ActionForward addNewElection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : addNewElection Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONSETTING);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : addNewElection Ending");

		return mapping.findForward(MessageConstants.addNewElection);
	}



	public ActionForward nominationApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_NOMINATIONAPPROVAL);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.nominationApproval);
	}

	public ActionForward addBoothDetais(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_BOOTHSETTING);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.addBoothDetais);
	}

	public ActionForward voterSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.voterSearchList);
	}

	/*public ActionForward voterDetailsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.voterDetailsView);
	}*/


	public ActionForward voterMachineStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : campus_teachers Starting");
		String forward=null;
		
		try {
			String systemIp=(String) request.getSession(false).getAttribute("IP");
			String accyear=request.getParameter("accyear");
			String group=request.getParameter("group");
			String titleID=request.getParameter("titleID");
			
			String status=new ElectionDaoImpl().checkIp(systemIp);
			
			request.setAttribute("accyear", accyear);
			request.setAttribute("group", group);
			request.setAttribute("titleID", titleID);
			if(status.equalsIgnoreCase("true")){
				ArrayList<ElectionVo> boothDetails=new ElectionDaoImpl().getBoothDetails(systemIp,accyear,group,titleID);
				request.setAttribute("boothDetails", boothDetails);
				
				
				forward=MessageConstants.voterMachineActivate;
			}
			else{
				forward=MessageConstants.voterMachineStart;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : campus_teachers Ending");

		return mapping.findForward(forward);
	}

	public ActionForward showCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		String forward=null;
		try {
			
			String systemIp=(String) request.getSession(false).getAttribute("IP");
			String status=new ElectionDaoImpl().checkMachicneActivation(systemIp);
			if(status.equalsIgnoreCase("false")){

				String abc=new ElectionDaoImpl().getMachicneDeActivation(systemIp);
				forward=MessageConstants.voterMachineStart;
				
			}
			else{
					ElectionPojo obj=new ElectionDaoImpl().getFilterationForCandidateList(systemIp);
				
					ArrayList<ElectionVo> collectionVo = null;

				 collectionVo = new ElectionDaoImpl().getCandidateList(obj.getAccid(),obj.getGroupId(),obj.getElectionTitleId(),obj.getActivationFor());
				
				request.setAttribute("CollectionVo", collectionVo);
				request.setAttribute("accyear", obj.getAccid());
				request.setAttribute("group", obj.getGroupId());
				request.setAttribute("titleID", obj.getElectionTitleId());
				forward=MessageConstants.showCategory;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(forward);
	}


	public ActionForward electionCategoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : electionCategoryList Starting");

		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_CATEGORYSETTING);
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.electionCategoryList);
	}


	public ActionForward nominationRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : nominationRegister Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_NOMINATIONREGISTRATION);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.nominationRegister);
	}




	public ActionForward getSchool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getSchool : Starting");

		LocationBD objl = new LocationBD();
		List<LocationVO> schoolList = new ArrayList<LocationVO>();
		try {

			schoolList = objl.getLocationDetails();

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", schoolList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSchool Ending");

		return null;

	}

	public ActionForward studentConfidentialReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentConfidentialReport Starting");
	


			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			try {
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_DISCIPLINARYACTION);
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

		

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.STUDENTCONFIDENTIAL);
	}

	public ActionForward AddStudentConfidentialReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentConfidentialReport Starting");
		
		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
				
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accYear,locationId);			
			
			request.setAttribute("studentSearchList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_DISCIPLINARYACTION);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTCONFIDENTIAL);
	}

	public ActionForward AddStudentWithHeld1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentWithHeld1 Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTWITHHELDLIST);
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
				
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentWithHeld(studentId,accYear,locationId);			
			/*List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accYear,locationId);*/
			request.setAttribute("studentSearchList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentWithHeld1 Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTWITHHELD);
	}


	public ActionForward studentWithheldList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Starting");
	

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTWITHHELDLIST);
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
		
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

		

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Ending");

		return mapping.findForward(MessageConstants.WITHHELDLIST);
	}

	
	/*public ActionForward getSudetnWithheldtList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Starting");


		try {
			ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String searchTerm = request.getParameter("searchname".trim());

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location ="%%";
			}
			
			list = new StudentRegistrationDelegate().getSudetnWithheldtList(academic_year,location);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Ending");

		return null;
	}*/

	/*public ActionForward studentWithheld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Ending");

		return mapping.findForward(MessageConstants.STUDENTWITHHELD);
	}*/

	public ActionForward withheldCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Ending");

		return mapping.findForward(MessageConstants.STUDENTWITHHELD);
	}

	public ActionForward withheldFindStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldFindStudent Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldFindStudent Ending");

		return mapping.findForward(MessageConstants.FINDSTUDENT);
	}

	public ActionForward NewstudentPromotionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			
			list = delegateObj.getPromotedListDetails(regVo);

			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Ending");

		return mapping.findForward(MessageConstants.STUDENTPROM);
	}

	public ActionForward getStudentForPromotion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentForPromotion Starting");
	
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("Acyearid",accYearList.get(0).getAccyearId());

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);

			
		
		



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentForPromotion Ending");

		return mapping.findForward(MessageConstants.GETSTUDENT);
	}





	public ActionForward studentPromotionPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionPage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getStudentWisePromotion(studentId,accYear,locationId);

			request.setAttribute("studentPromotionSetting", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionPage Ending");

		return mapping.findForward(MessageConstants.PROMOTIONPAGE);
	}

	public ActionForward studentAppraisalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalList Starting");

		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalList Ending");

		return mapping.findForward(MessageConstants.APPRAISALLIST);
	}

	public ActionForward findStudentForAppraisal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForAppraisal Starting");

		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForAppraisal Ending");

		return mapping.findForward(MessageConstants.STUDENTAPPRAISAL);
	}

	public ActionForward studentAppraisalPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalPage Starting");

		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalPage Ending");

		return mapping.findForward(MessageConstants.APPRAISALPAGE);
	}

	public ActionForward studentTransferCertificateList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentTransferCertificateList Starting");

		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentTransferCertificateList Ending");

		return mapping.findForward(MessageConstants.TRANSFERCERTIFICATELIST);
	}

	public ActionForward findStudentForTransferCertificate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForTransferCertificate Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForTransferCertificate Ending");

		return mapping.findForward(MessageConstants.FINDSTUDENTLIST);
	}


	public ActionForward transferCertificatePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transferCertificatePage Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transferCertificatePage Ending");

		return mapping.findForward(MessageConstants.TRANSCERTIFICATEPAGE);
	}


	public ActionForward printTransferCertificate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printTransferCertificate Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printTransferCertificate Ending");

		return mapping.findForward(MessageConstants.PRINTTRANSCERTIFICATE);
	}

	public ActionForward gradeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gradeList Starting");

		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADESETTINGS);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			   request.setAttribute("academic_year", academic_year);
			   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation);
	    		   request.setAttribute("currentlocation", currentlocation);
	    	   }
	    	   request.setAttribute("locationId",schoolLocation);
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails();
				request.setAttribute("locationDetailsList", list);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
						.getAccYears();
				request.setAttribute("accYearList", accYearList);




		}catch(Exception e){
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.STUDENTGRADELIST);
	}


	public ActionForward getStudentConfidentialReport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getStudentConfidentialReport Starting");

		try {
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("Acyearid",accYearList.get(0).getAccyearId());

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);

			String accyear  = (String) request.getSession(false).getAttribute("current_academicYear");

			String locationid  = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if (locationid == null || locationid == "" || locationid.equalsIgnoreCase("")) {
				locationid = HelperClass.getCurrentYearID();
			}

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentDetailsList(locationid,accyear);

			request.setAttribute("studentList", list);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.GETSTUDENTCONFIDENTIAL);
	}	

	public ActionForward miscellaneousReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_MISREPORT);
			
		if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
			academic_year = HelperClass.getCurrentYearID();
		}
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);


		List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
		request.setAttribute("classlist", classlist);
			



		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return mapping.findForward(MessageConstants.MISCELLANEOUS_REPORT);
	}

	
	public ActionForward singleStudentDetailsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : singleStudentDetailsList Starting");
		
		

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationid");
			
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accyear,locationid);
			
			request.setAttribute("studentSearchList", list);
			
			JSONObject obj=new JSONObject();
			obj.put("studentSearchList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : singleStudentDetailsList Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTCONFIDENTIAL);
	}

	public ActionForward NewstudentIdCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			String streamId=request.getParameter("streamId");
			if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);

			JSONObject obj=new JSONObject();
			obj.put("streamList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return null;
	}
	
	public ActionForward saveLayoutImage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Starting");


		try {
			System.out.println("Inside the changeImage");
			InventoryTransactionForm formObj = (InventoryTransactionForm) form;
			System.out.println("formObj :" + formObj);
			
			FormFile file = formObj.getInputfile();
			String layout=formObj.getLayoutDetails();
			
			File filename = new File(request.getRealPath("/")+ "images/IdCard/"+layout+".jpg");
			if(filename.exists()){
				
			}
			else{
				filename.createNewFile();
			}
			
			String filepath = null, base = null, filecuurentpath = null;
			BufferedImage bufferedImage = null;
			filecuurentpath = request.getRealPath("/")+ "images/IdCard/"+layout+".jpg";
			System.out.println(filecuurentpath);
			File f1 = new File(filecuurentpath);
			if (f1 != null) {
				f1.delete();
			}

			String extension = "";
			int j = (file).getFileName().lastIndexOf('.');
			if (j >= 0) {
				base = (String) ((j == -1) ? file : (file).getFileName()
						.substring(0, j));
				extension = (file).getFileName().substring(j + 1);
				base = layout;
			}
			System.out.println("extension is " + extension);
			if (extension.equalsIgnoreCase("jpg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;

				System.out.println(filepath);
				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("jpeg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;
				System.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("png")) {
				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;
				System.out.println(filepath);

				// read image file
				bufferedImage = ImageIO.read(new File(filepath));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(
						bufferedImage.getWidth(), bufferedImage.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0,
						0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(
						newBufferedImage,
						"jpg",
						new File(request.getRealPath("/")+ "images/IdCard/"+layout+".jpg"));

			}
			// byte[] btDataFile = (file).getFileData();

			// ImageIO.write(image, "png",new File("C:\\out.png"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Ending");

		return null;
	}
	
	public ActionForward getStudentDetailsLByFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Starting");

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list =  new StudentRegistrationDelegate().getStudentDetailsLByFilter(locationid,accyear,classname);
		
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getClassWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Ending");

		return null;

	}
	

	public ActionForward TransportIDCardDesignList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");
	
	boolean bool = false;
	List<PageFilterVo> list=new ArrayList<PageFilterVo>();
	List<PageFilterVo> newlist=new ArrayList<PageFilterVo>();
	
	try {
		String streamId=request.getParameter("streamId");
		if(streamId.equalsIgnoreCase("all")){
			streamId="%%";
		}
		String locationId=request.getParameter("locationId");
		if(locationId.equalsIgnoreCase("all")){
			locationId="%%";
		}
		String academicYear=request.getParameter("academicYear");
		if(academicYear.equalsIgnoreCase("all")){
			academicYear="%%";
		}
		
		
		PageFilterpojo filterpojo=new PageFilterpojo();
		filterpojo.setLocationId(locationId);
		filterpojo.setAcademicYear(academicYear);
		filterpojo.setStreamId(streamId);
		list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
		for(int i=0;i<list.size();i++){
			PageFilterVo filterVo = new PageFilterVo();
			filterVo.setSno(list.get(i).getSno());
			filterVo.setAcademicYear(list.get(i).getAcademicYear());
			filterVo.setAcademicYearCode(list.get(i).getAcademicYearCode());
			filterVo.setLocationName(list.get(i).getLocationName());
			filterVo.setLocationId(list.get(i).getLocationId());
			filterVo.setStreamId(list.get(i).getStreamId());
			filterVo.setStreamName(list.get(i).getStreamName());
		
			filterVo.setTemplateId(list.get(i).getTemplateId());
	
		
			
			String template=list.get(i).getTemplateId();
			System.out.println("template="+template);
			File file = new File(request.getRealPath("/")+ "CSS/IdCard/"+template+".css");
			bool = file.exists();
			if(bool == true) {
				
				System.out.println("SET");
				filterVo.setStatus("SET");
				filterVo.setTemplateName(list.get(i).getTemplateName());
				
				
			}
			else{
				
				System.out.println("NOT SET");
				filterVo.setStatus("NOT SET");
				filterVo.setTemplateName("-");
				
				
			}
			newlist.add(filterVo);
			
		}
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", newlist);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}

	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");
	
	return null;
}
	
	public ActionForward rollNoGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : rollNoGeneration Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String order1 = request.getParameter("order1");
			String order2 = request.getParameter("order2");
			String order3 = request.getParameter("order3");
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDaoImpl().rollNoGeneration(locationid,accyear,classname,sectionid,order1,order2,order3);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : rollNoGeneration Ending");

		return null;

	}
	
	
	public ActionForward getStudentListBySectionForROllNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySection Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			
			if(locationid == "all"){
				locationid = "%%";
			}
			if(classname == "all"){
				classname = "%%";
			}
			if(sectionid == "all"){
				sectionid = "%%";
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDaoImpl().getStudentListBySectionForRollNo(locationid,accyear,classname,sectionid);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;

	}
	
	public ActionForward getStudentListBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySection Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			
			if(locationid == "all"){
				locationid = "%%";
			}
			if(classname == "all"){
				classname = "%%";
			}
			if(sectionid == "all"){
				sectionid = "%%";
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;

	}

	public ActionForward individualStudentSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : individualStudentSearch Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);

			request.setAttribute("studentSearchList", list);

			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : individualStudentSearch Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_STUDENT_SEARCH);
	}
	
	
	public ActionForward individualStudentSearchPopUp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : individualStudentSearch Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);

		JSONObject obj=new JSONObject();
		obj.put("studentSearchList", list);
		response.getWriter().print(obj);

			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : individualStudentSearch Ending");

		return null;
	}
	
	
	public ActionForward individualMisreport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : individualStudentSearch Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_MISREPORT);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);

			request.setAttribute("studentSearchList", list);
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String studentid = request.getParameter("studentid");
			String loc_id = request.getParameter("locid");
			String acyyear = request.getParameter("acyyear");
			
			StudentRegistrationVo svo = new StudentRegistrationVo();
			svo.setClassDetailId(classid);
			svo.setClassSectionId(sectionid);
			svo.setStudentId(studentid);
			svo.setLocationId(loc_id);
			svo.setAcademicYearId(acyyear);
			
			List<ExaminationDetailsVo> list1 = new StudentRegistrationDelegate().getExaminationDetails(svo);
			request.setAttribute("examDetailsList", list1);
			

			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : individualStudentSearch Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_MIS_REPORT);
	}
	
	public ActionForward studentSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
			/*
			if(location.equalsIgnoreCase("all")){
			
			location="%%";
			list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
		}
		else{
			
			list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
		}*/
		if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
			academic_year = HelperClass.getCurrentYearID();
		}
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);


		List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
		request.setAttribute("classlist", classlist);
			

		request.setAttribute("studentList", list);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return mapping.findForward(MessageConstants.STUDENT_SEARCH);
	}
	
	public ActionForward getConfDetailsLByFilter(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getConfDetailsLByFilter Starting");
			try {
				List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
				String locationid = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classname = request.getParameter("classId");
				
				if(classname.equalsIgnoreCase("all")){
					
					classname="%%";
					list = new StudentRegistrationDelegate().getConfDetailsLByFilter(locationid,accyear,classname);
				}
				else{
					
					list = new StudentRegistrationDelegate().getConfDetailsLByFilter(locationid,accyear,classname);
				}
				
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("getClassWiseList", list);
					response.getWriter().print(jsonobj);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getConfDetailsLByFilter Ending");
			return null;
		}
		
	public ActionForward getStudentBySectionForConfReport(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getStudentBySectionForConfReport Starting");
			try {
				List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
				String locationid = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classname = request.getParameter("classId");
				String sectionid = request.getParameter("sectionid");
				
				if(sectionid.equalsIgnoreCase("all")){
					
					sectionid="%%";
					list = new StudentRegistrationDelegate().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid);
				}
				else{
					
					list = new StudentRegistrationDelegate().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid);
				}
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("getSectionWiseList", list);
					response.getWriter().print(jsonobj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getStudentBySectionForConfReport Ending");
			return null;
		} 
	 
	public ActionForward StudentListforPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Starting");

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String streamId=request.getParameter("streamId");
			String flag=request.getParameter("flag");
			

			if(flag==null){
				flag="classCard";
			}
			if(locationid==null){
				locationid="%%";
			}
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if(accyear==null){
				accyear="%%";
			}
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
			}
			if(streamId==null){
				streamId="%%";
			}

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new IDGenerator().getStudentListforPrint(locationid,accyear,classname,sectionid,streamId,flag);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("getSectionWiseList", list);
				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;

	}
	
	
	
	public ActionForward getAccYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Starting");
		
		

		
		String academic_year = (String) request.getSession(false).getAttribute("academicYear");

		try {
			
		
		if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
			academic_year = HelperClass.getCurrentYearID();
		}
		
		

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

		
		
		JSONObject jsonobj = new JSONObject();

		jsonobj.put("AccYearList", accYearList);

		response.getWriter().print(jsonobj);



		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Ending");

		return null;
	}
	
	public ActionForward generateRollNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : generateRollNo Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEROLLNO);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : generateRollNo Ending");
		
		return mapping.findForward(MessageConstants.GENERATE_ROLL_NO);
		}
	
	
	public ActionForward getStudentDetailsLByFeeFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Starting");

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
               if(classname.equalsIgnoreCase("all")){
				
				classname="%%";
				System.out.println("academic year for all classname:" +accyear);
				list = new StudentRegistrationDelegate().getStudentDetailsLByFeeFilter(locationid,accyear,classname);
			  }
               else{
   				
   				list = new StudentRegistrationDelegate().getStudentDetailsLByFeeFilter(locationid,accyear,classname);
   				System.out.println("class detail id::" +list.get(0).getClassDetailId());
   			     }


				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getClassWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Ending");

		return null;

	}
	
	public ActionForward getStudentListByFeeSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListByFeeSection Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

//raji
			   if(sectionid.equalsIgnoreCase("all"))
			   {
			      sectionid="%%";
			      list = new StudentRegistrationDelegate().getStudentListByFeeSection(locationid,accyear,classname,sectionid);
			   }
			   else
			   {
				      list = new StudentRegistrationDelegate().getStudentListByFeeSection(locationid,accyear,classname,sectionid);
			   }
				JSONObject jsonobj = new JSONObject();
                jsonobj.put("getSectionWiseList", list);
                response.getWriter().print(jsonobj);
			
			

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListByFeeSection Ending");

		return null;

	}
	public ActionForward PrintPreviewStaffMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintPreviewStaffMultipleIDCard Starting");

	/*	String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		List<StudentIDVo> studentList = new StudentIDDaoImpl().getstudentIDPDFReport(accyear,locationId,studentId);
		List<StudentIDVo> list = new ArrayList<StudentIDVo>();

		for (int i = 0; i < studentList.size(); i++) {

			StudentIDVo vo = new StudentIDVo();
			String fileName = studentList.get(i).getImage();
			String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
			vo.setImages(".\\FIles\\STUDENTIMAGES\\" + fileName.trim());

			vo.setStuName(studentList.get(i).getStuName());
			vo.setClassName(studentList.get(i).getClassName() + "&"
					+ studentList.get(i).getSection());
			vo.setSection(studentList.get(i).getSection());
			vo.setFatherName(studentList.get(i).getFatherName());
			vo.setAdress(studentList.get(i).getAdress());
			vo.setPhone(studentList.get(i).getPhone());
			vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
			vo.setValidity(studentList.get(i).getValidity());
			vo.setAdmissionno(studentList.get(i).getAdmissionno());
			vo.setMotherName(studentList.get(i).getMotherName());
			list.add(vo);

	
		request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]);
		request.setAttribute("StudentList", list);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
*/
		return mapping.findForward(MessageConstants.PrintPreviewStaffMultipleIDCard);
	}
	public ActionForward getStudentPromotedClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			

			if(classid.equalsIgnoreCase("all")){
				classid="%%";
				regVo.setClasscode(classid);
				list = delegateObj.getPromotedClassList(regVo);
			}
			else{
				regVo.setClasscode(classid);
				list = delegateObj.getPromotedClassList(regVo);
			}
			
			//list = delegateObj.getPromotedClassList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getPromotedClassWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassList Ending");

		return null;
	}
	public ActionForward getStudentPromotedClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassSectionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setClasscode(classid);
			
			
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getPromotedClassSectionList(regVo);
			}
			else{
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getPromotedClassSectionList(regVo);
			}
			
			//list = delegateObj.getPromotedClassSectionList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getPromotedSectionWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassSectionList Ending");

		return null;
	}
	
	public ActionForward studentDemotedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			
			list = delegateObj.getDemotedListDetails(regVo);

			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getStudentDemotedList", list);

			response.getWriter().print(jsonobj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedList Ending");

		return null;
	}
	
	public ActionForward getStudentDemotedClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			

			if(classid.equalsIgnoreCase("all")){
				classid="%%";
				regVo.setClasscode(classid);
				list = delegateObj.getDemotedClassList(regVo);
			}
			else{
				regVo.setClasscode(classid);
				list = delegateObj.getDemotedClassList(regVo);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getDemotedClassWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Ending");

		return null;
	}
	
	public ActionForward getStudentDemotedClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassSectionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setClasscode(classid);
			
			
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getDemotedClassSectionList(regVo);
			}
			else{
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getDemotedClassSectionList(regVo);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getDemotedSectionWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassSectionList Ending");

		return null;
	}
	
	public ActionForward studentPromotedPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotedPage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String promotedId = request.getParameter("promotedId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getStudentPromotedChange(studentId,accYear,locationId,promotedId);

			request.setAttribute("studentPromotedSetting", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotedPage Ending");

		return mapping.findForward(MessageConstants.PROMOTEDPAGE);
	}
	public ActionForward GroupListbyJS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");

		//String accyear = request.getParameter("accyear");
		List<ElectionVo> Data = new ArrayList<ElectionVo>();
		try{
			
			String accyear=request.getParameter("accyear");
			System.out.println("check acc year=========="+accyear);
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			System.out.println("check acc year after null=========="+accyear);
			Data = new ElectionDaoImpl().getGroupdetailsByJS(accyear);
			JSONObject obj = new JSONObject();
			obj.put("DataList", Data);
		response.getWriter().print(obj);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return null;
	}
	
	public ActionForward studentDemotedToPromotingPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedToPromotingPage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String promotedId = request.getParameter("promotedId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getStudentPromotedChange(studentId,accYear,locationId,promotedId);

			request.setAttribute("studentPromotedSetting", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedToPromotingPage Ending");

		return mapping.findForward(MessageConstants.PROMOTINGPAGE);
	}
	
	public ActionForward getDairy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction:getDairy Starting");
		UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=user.getUserId();
		String userType=user.getUserNametype();
		List<DairyDetailsVo> commentlist=new ArrayList<DairyDetailsVo>();
		try{
		
		LoginBD loginservice = new LoginBD();
		commentlist=loginservice.getDairy(userId);
		
		JSONObject obj=new JSONObject();
		obj.put("commentlist", commentlist);
		response.getWriter().print(obj);
	
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction:getDairy: Ending");
		
		return null;
	}
	
	public ActionForward getElectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Starting");

		try{
			String academicYearID = request.getParameter("academicYear");
			
			System.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			
	
			String groupNameID = request.getParameter("groupName");
			System.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
		
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Ending");

		return null;
	}
	
	public ActionForward searchByLocationOnly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : searchByLocationOnly Starting");
		
		String locationId = request.getParameter("locationId");
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear");
		//String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		StreamDetailsBD obj = new StreamDetailsBD();
		List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();
		
		try {
 			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){
				
				locationId="%%";
				list = new StreamDetailsBD().searchByLocationOnly(locationId,accYear);
			}
			else{
				list = new StreamDetailsBD().searchByLocationOnly(locationId,accYear);
			}
			
			request.setAttribute("SearchList", list);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : searchByLocationOnly Ending");

		return null;
	}
	
	
	
/*Election Category Setting MODEL-3------------------------------------------------------*/
	
	public ActionForward getElectionCategoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Starting");

		try{
			String academicYearID = request.getParameter("academicYear");
			
			System.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			
	
			String groupNameID = request.getParameter("groupName");
			System.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
		
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
		
	/*	try{
			String academicYearID = request.getParameter("academicYear");
			
			System.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			String groupNameID = request.getParameter("groupName");
			System.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			//list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			list = new ElectionBD().getElectionCategoryList(academicYearID,groupNameID);
			
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
			*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Ending");

		return null;
	}	
	
	
	public ActionForward sectionListcheckTemporary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		String user = HelperClass.getCurrentUser(request);

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String streamId=request.getParameter("streamId");
			
			System.out.println("locationid is :-"+locationid);
			
			if(locationid==null || locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase("")){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
		
			List<SectionForm> list = new ArrayList<SectionForm>();

			SectionBD sectionDelegate = new SectionBD();

			list = sectionDelegate.getstreamdetailsforOnchange(locationid,classname,streamId);

			JSONObject obj= new JSONObject();
			obj.put("list", list);
			response.getWriter().print(obj);
			
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Ending");

		return null;
	}
	
	public ActionForward SpecializationListforListOnchangeMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : specializationList Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			SpecializationBD obj = new SpecializationBD();

			List<SpecializationVo> list = new ArrayList<SpecializationVo>();

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String streamId=request.getParameter("streamId");
			
			if(locationid==null || locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			
			list = obj.getstreamdetailsforOnchange(locationid,classname,streamId);
			
			//request.setAttribute("SpecializationList", list);

			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return null;
	}
	
	public ActionForward SubjectListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : SubjectListforListOnchangeMethod Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			
			System.out.println("locationid "+locationid);
			System.out.println("classname "+classname);
			System.out.println("specialization "+specialization);
			
			if(locationid==null){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(specialization.equalsIgnoreCase("all")){
				specialization="%%";
			}

			subjectlist = new AddSubjectDelegate().subjectdetailsOnchangeListingPage(locationid,classname,specialization);



			JSONObject obj1= new JSONObject();
			obj1.put("list", subjectlist);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : SubjectListforListOnchangeMethod Ending");

		return null;
	}
	
	public ActionForward classListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Starting");
	
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			String locationid = request.getParameter("location");
			String streamId = request.getParameter("streamId");

			
			if(locationid==null || locationid.equalsIgnoreCase("") || locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if(streamId==null || streamId.equalsIgnoreCase("") || streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			/*if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}*/
		
			List<ClassPojo> list = new ArrayList<ClassPojo>();

			ClassBD delegate = new ClassBD();
			list= delegate.getClassDetailsOnChangeFunction(streamId,locationid);

			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Ending");

		return null;
	}
	
	
	public ActionForward fineConfiguration(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfiguration Starting");
		List<feeReportVO> fineList=new ArrayList<feeReportVO>();
		
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_FEE);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_FEE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_FEE_FINECONFIGURATION);
			
		
		
		
		request.setAttribute("schoolName", HelperClass.getAllLocation());
		fineList=new FeeMasterDAOIMPL().getFineConfiguration(HelperClass.getCurrentYearID());
		request.setAttribute("fineList", fineList);
		request.setAttribute("AccyearId", HelperClass.getAllAcademicYear());	
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfiguration Ending");
		return mapping.findForward("fineConfiguration");
		
	}
	
	public ActionForward fineConfigurationByAccyear(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfigurationByAccyear Starting");
		List<feeReportVO> fineList=new ArrayList<feeReportVO>();
		
	try {
		
		String accyear=request.getParameter("accyear");
		
		JSONObject obj=new JSONObject();
		
		obj.put("schoolName", HelperClass.getAllLocation());
		fineList=new FeeMasterDAOIMPL().getFineConfiguration(accyear);
		obj.put("fineList", fineList);
		obj.put("AccyearId", HelperClass.getAllAcademicYear());	
		response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfigurationByAccyear Ending");
		return null;
		
	}
	
	public ActionForward electionListForVoting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionListForVoting Starting");

		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_VOTERMACHINE);
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
			
			String academicYear = "%%";
			String groupId = "%%";
			ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
			dataList = new ElectionBD().getElectionDetails(academicYear,groupId);
			request.setAttribute("DataList", dataList);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionListForVoting Ending");

		return mapping.findForward(MessageConstants.electionList);
	}
	public ActionForward countingListForVoting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : countingListForVoting Starting");

		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONREPORT);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : countingListForVoting Ending");

		return mapping.findForward(MessageConstants.countingListForVoting);
	}
	
	
	public ActionForward winnerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Starting");

		try{
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = request.getParameter("accyear");
			String group = request.getParameter("group");
			String titleID=request.getParameter("titleID");
			ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
			dataList = new ElectionDaoImpl().getWinnerList(accyear,group,titleID);
			request.setAttribute("DataList", dataList);
			if(dataList.size()>0){
			request.setAttribute("academicYear", dataList.get(0).getAccyear());	
			request.setAttribute("groupName", dataList.get(0).getGroupName());	
			request.setAttribute("electionName", dataList.get(0).getElectionTitle());
			
			request.setAttribute("academicYearId", accyear);	
			request.setAttribute("groupId", group);	
			request.setAttribute("electionId",titleID);
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Ending");

		return mapping.findForward("winnerList");
	}
	
	public ActionForward CandidateList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Starting");

		try{
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = request.getParameter("accyear");
			String group = request.getParameter("group");
			String titleID=request.getParameter("titleID");
			ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
			dataList = new ElectionDaoImpl().getCandidateList(accyear,group,titleID);
			
			JSONObject obj=new JSONObject();
			obj.put("DataList", dataList);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Ending");

		return null;
	}
	public ActionForward transportFeeCollectionForFilteration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String accyear=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchby=request.getParameter("searchby");
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if(classId.equalsIgnoreCase("all")){
				classId="%%";
			}
			if(divisionId.equalsIgnoreCase("all")){
				divisionId="%%";
			}
			if(searchby.equalsIgnoreCase("all")){
				searchby="%%";
			}
			
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			
				list = new StudentRegistrationDaoImpl().getStudentListForTransportFilteration(accyear,location,classId,divisionId,searchby);
			
				JSONObject obj=new JSONObject();
				obj.put("List", list);
				response.getWriter().print(obj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Ending");
		return null;




	}
	
	
	public ActionForward getStaffIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffIdDesignList Starting");
		
		
		try {
			
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			
			
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  
	    		  PageFilterpojo filterpojo=new PageFilterpojo();
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setDepartmentId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstafftIdCardDesignList(filterpojo);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getDepartmentName());
	  					list.add(template);
	  				}
	    	  
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffIdDesignList Ending");
		return null;

	}
	public ActionForward getStudentIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Starting");
		
		
		try {
			
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			
			
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if(!listOfFiles[i].getName().contains("transport") && listOfFiles[i].getName().contains("ACY")){
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  
	    		  PageFilterpojo filterpojo=new PageFilterpojo();
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setStreamId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getStreamName());
	  					list.add(template);
	  				}
	    	  }
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Ending");
		return null;

	}
	public ActionForward getTemplateIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Starting");
		
		
		try {
			String templateVal=request.getParameter("templateVal");
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			
			
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if(!listOfFiles[i].getName().contains("transport") && listOfFiles[i].getName().contains("ACY")){
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  
	    		  PageFilterpojo filterpojo=new PageFilterpojo();
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setStreamId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getStreamName());
	  					list.add(template);
	  				}
	    	  }
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Ending");
		return null;

	}
	

	
	public ActionForward StudentAppraisalReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Starting");
	

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			try {
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Ending");

		return mapping.findForward(MessageConstants.STUDENTSAPPRAISAL);
	}
	//quickadmission
	
	public ActionForward quickAdmission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Starting");
	

			

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			try {
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
			
			//StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
			//ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			

			//request.setAttribute("studentSearchList", registrationVo1);

			
			//request.setAttribute("studentList", list);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Ending");
		return mapping.findForward(MessageConstants.QUICK_ADMISSION);

		//return mapping.findForward(MessageConstants.STUDENTSAPPRAISAL);
	}
	
	
	
	public ActionForward AddStudentAppraisalReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :AddStudentAppraisalReport Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
				
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accYear,locationId);			
			
			request.setAttribute("studentSearchList", list);
			

			/*JSONObject obj=new JSONObject();
			obj.put("studentSearchList", list);
			response.getWriter().print(obj);*/

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :AddStudentAppraisalReport Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTAPPRAISAL);
	}

	public ActionForward laboratory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : laboratory Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			
			List<ViewallSubjectsVo> lablist = new ArrayList<ViewallSubjectsVo>();
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			
            List<AddSubjectForm> list = new ArrayList<AddSubjectForm>();
            AddSubjectDelegate obj = new AddSubjectDelegate();
			list = obj.getLaboratoryDetails();
			request.setAttribute("laboratoryDetails", list);
			
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			request.setAttribute("classList", classList);
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId);
			lablist = new AddSubjectDelegate().labdetails(schoolLocation);
		
			System.out.println("asdafadfafadfa"+list.size());
			//lablist = new AddSubjectDelegate().subjectdetails(schoolLocation);

		

		request.setAttribute("allsubjects", lablist);
			}
			catch (Exception e) {e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : laboratory Ending");

		return mapping.findForward(MessageConstants.LABORATORY);

}
	
	public ActionForward addLaboratory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Starting");
            String result=null;
            String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			String title="Add New laboratory";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			request.setAttribute("classList", classList);
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId);
			
			
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			
			String specialization=request.getParameter("specialization");
			String locationname=request.getParameter("locationname");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationName(locationname);
          
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Ending");

		return mapping.findForward(MessageConstants.ADD_LAB);
	}
	
	public ActionForward getSpecialization(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			System.out.println("Specilization Action classId: "+request.getParameter("classId"));
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", specializationList);
				
				response.getWriter().print(jsonobj);
			
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Ending");

		 return null;   
	}
	
	public ActionForward LabListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LabListforListOnchangeMethod Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			List<AddSubjectForm> lablist = new ArrayList<AddSubjectForm>();
			
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase("")){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname="%%";
			}
			if(specialization.equalsIgnoreCase("all") || specialization.equalsIgnoreCase("") || specialization.equalsIgnoreCase("-")){
				specialization="%%";
			}

			lablist = new AddSubjectDelegate().labdetailsOnchangeListingPage(locationid,classname,specialization);



			JSONObject obj1= new JSONObject();
			obj1.put("list", lablist);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LabListforListOnchangeMethod Ending");

		return null;
	}
	
	public ActionForward getStudentDetailsLByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByClass Starting");

		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");

			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list =  new StudentRegistrationDelegate().getStudentDetailsLByFilter(locationid,accyear,classname);
		
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getClassWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByClass Ending");

		return null;

	}
	
	public ActionForward getStudentListByDivision(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListByDivision Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListByDivision Ending");

		return null;

	}
	
	public ActionForward getStudentListBySpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySpecialization Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String specName = request.getParameter("spec_Name");
			
			ExaminationDetailsVo details = new ExaminationDetailsVo();
			details.setAccyear(accyear);
			details.setLocationid(locationid);
			details.setClassid(classname);
			details.setSectionid(sectionid);
			details.setSpecialization(specName);
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySpecialization(details);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getSpecializationWiseList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySpecialization Ending");

		return null;

	}
	
	//---------Student Photosheet Start--------
	
	public ActionForward studentPhotosheet(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentPhotosheet : Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_PHOTOSHEET);
			
			
			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
			else{
				
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			
			
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		System.out.println("hekkkkoo");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPhotosheet : Ending");

		return mapping.findForward(MessageConstants.studentphotosheet);
	}
	
	public ActionForward studentPhotosheetAccList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentPhotosheetAccList Starting");


		try {
			String locationId = request.getParameter("locationId");
			String accYear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			
			
			
		
			/*if(classId.equalsIgnoreCase("all")){
				classId="%%";
			}*/
			if(sectionId.equalsIgnoreCase("ALL")){
				sectionId="%%";
			}
			

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCardPhotoSheet(sectionId,classId,accYear,locationId);
			
			request.setAttribute("", list);
			
			/*request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode());*/
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("studentSearchList", list);

			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentPhotosheetAccList Ending");

		return null;
	}
	//---------Student Photosheet End--------	
	
	public ActionForward tcgeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
		
		if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
			academic_year = HelperClass.getCurrentYearID();
		}
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);

		List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
		request.setAttribute("classlist", classlist);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return mapping.findForward(MessageConstants.TC_GENERATION);
	}
	
	
	public ActionForward NewCancelStudentTC(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : NewCancelStudentTC Starting");
				
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String studentid = request.getParameter("studentid");
		
		
		String[] splitlocation = location.split(",");
		String[] splitaccyear =accyear.split(",");
		String[] splitstudentid =studentid.split(",");
		
		String status=null;
		
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			status = new StudentRegistrationDaoImpl().NewCancelStudentTC(splitlocation,splitaccyear,splitstudentid);
			
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("status", status);

			response.getWriter().print(jsonobj);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewCancelStudentTC Ending");

		return null;
	}
	
	
	
	public ActionForward newTcGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : newTcGeneration Starting");
				
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String studentid = request.getParameter("studentid");
		String admid = request.getParameter("admid");
		String classid = request.getParameter("classid");
		
		String examdetails = request.getParameter("examdetails");
		String reason = request.getParameter("reason");
		String remarks = request.getParameter("remarks");
		String result = request.getParameter("result");
		String appdate = request.getParameter("appdate");
		String ladate = request.getParameter("ladate");
		String csub = request.getParameter("csub");
		String esub = request.getParameter("esub");
		String admclass=request.getParameter("admclass");
		
		
		String[] splitlocation = location.split(",");
		String[] splitaccyear =accyear.split(",");
		String[] splitstudentid =studentid.split(",");
		String[] splitadmid =admid.split(",");
		String[] splitclassid =classid.split(",");
		String status=null;
		
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			status = new StudentRegistrationDaoImpl().NewGenerateStudentTC(splitlocation,splitaccyear,splitstudentid,splitadmid,splitclassid,examdetails,reason,remarks,result,appdate,ladate,csub,esub,admclass);
			
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("status", status);

			response.getWriter().print(jsonobj);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : newTcGeneration Ending");

		return null;
	}
	
	public ActionForward notGenTClist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			
			list = new StudentRegistrationDelegate().getNotGenTC(vo);
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchList", list);

			response.getWriter().print(jsonobj);
			
		
		

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	
	
	public ActionForward getStudentListByTC(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : gradeList Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			
			if(locationid == "all"){
				locationid = "%%";
			}
			if(classname == "all"){
				classname = "%%";
			}
			if(sectionid == "all"){
				sectionid = "%%";
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;

	}
	
	public ActionForward TransferCertificateDownload(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransferCertificateDownload: Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String empId=userDetailVO.getUserId();
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			
			String PropfilePath =request.getRealPath("/")+ "images/arya_logo.png";
			String template=request.getRealPath("/")+"images/ReportTemplate/TCTemplate.jpg"; 
			String locationId=request.getParameter("locationId");
			String accyear=request.getParameter("accyear");
			String studentid=request.getParameter("studentid");
			String admid=request.getParameter("admid");
			String classid=request.getParameter("classid");
			
			
			String academicYear=HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[0].substring(2)+"-"+HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[1].substring(2);
			ServletOutputStream outstream =null;
			byte[] bytes=null;
			
		
			
			String sourceFileName = request.getRealPath("Reports/transfercertificate.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
			List<StudentRegistrationVo> TCdetailsList = null;
			Map parameters = new HashMap();
			parameters.put("Image", PropfilePath);
			parameters.put("admNo", admid);
			parameters.put("accyear", academicYear);
			parameters.put("template", template);
			
			JRBeanCollectionDataSource beanColDataSource = null;
			
			
			
				
				beanColDataSource = new JRBeanCollectionDataSource(TCdetails);
				
				bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\"Transfer_certificate_"+academicYear+".pdf\"");
				outstream = response.getOutputStream();
				outstream.write(bytes, 0, bytes.length);
			
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransferCertificateDownload: Ending");
		return null;
	}
	
	
	public ActionForward GenTCListFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			
			if(locationid.equals("all")){
				locationid = "%%";
			}
			if(classname.equals("all")){
				classname = "%%";
			}
			if(sectionid.equals("all")){
				sectionid = "%%";
			}
			
			System.out.println(orderby + "orderby");
			
			vo.setLocationId(locationid);
			vo.setAccyear(accyear);
			vo.setClassname(classname);
			vo.setSection_id(sectionid);
			vo.setSortBy(sortingby);
			vo.setOrderBy(orderby);
			
			
			list = new StudentRegistrationDelegate().GenTCListFilter(vo);
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchList", list);

			response.getWriter().print(jsonobj);
			
		
		

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	
	
	public ActionForward studentDemotedListSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String searchname = request.getParameter("searchname");
			
			if(locationid.equals("all")){
				locationid = "%%";
			}
			if(classname.equals("all")){
				classname = "%%";
			}
			if(sectionid.equals("all")){
				sectionid = "%%";
			}
			
			
			vo.setLocationId(locationid);
			vo.setAccyear(accyear);
			vo.setClassname(classname);
			vo.setSection_id(sectionid);
			vo.setSearchTerm(searchname);
			
			list = new StudentRegistrationDelegate().GenTCListSearch(vo);
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchList", list);

			response.getWriter().print(jsonobj);
			
		
		

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	

	public ActionForward tcDownloadPdf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList Starting");

			try {
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
				
				System.out.println("vvvvvvvvvv");
				String locationId=request.getParameter("locationId");
				String accyear=request.getParameter("accyear");
				String studentid=request.getParameter("studentid");
				String admid=request.getParameter("admid");
				String classid=request.getParameter("classid");
				
				System.out.println("locationId  =  =  = " + locationId);
				List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
				
				String sourceFileName = request.getRealPath("Reports/transfercertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(TCdetails);

				String PropfilePath = request.getRealPath("/")+"images/ReportTemplate/TCTemplate";

				String schName = res.getString("SchoolName");
				
				System.out.println("SCHOOL NAME "+schName);
				String EventAddress=res.getString("EventAdd");
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				for (int i = 0; i < TCdetails.size(); i++) {
				parameters.put("admNo", HelperClass.getMonthFullName(admid));
				parameters.put("year", accyear);
				parameters.put("PropfilePath", PropfilePath);
				}
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "Event Result - " + ".pdf\"");
				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes, 0, bytes.length);
				outstream.flush();
				
				
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsAction : getEventStudentRegList ending");

			return null;
	}
	
	public ActionForward getTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
	
		
		List<TeacherVo> list = new ArrayList<TeacherVo>();

		
		try {
			
			list=HelperClass.getAllTeacherList();
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	
	public ActionForward getTodayTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
	
		
		List<TeacherVo> list = new ArrayList<TeacherVo>();

		
		try {
			
			list=HelperClass.getTodayForSubstitutionTeacherList(request.getParameter("tperiod"),request.getParameter("timetableId"));
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	
	public ActionForward getTodayTeacherListToday(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
	
		
		List<TeacherVo> list = new ArrayList<TeacherVo>();

		
		try {
			
			list=HelperClass.getTodayTeacherListToday();
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return null;
	}
	
	public ActionForward gettodaytimetablelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettodaytimetablelist Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_TIMETABLESUB);
			
			
			String classId = request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			String locationId=request.getParameter("locationId");
			if(classId==null){
				classId="ALL";
			}
			if(sectionid==null){
				sectionid="ALL";
			}
			if(locationId==null){
				locationId="ALL";
			}
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false).getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */

			ArrayList<TimeTableVo> arr =null;
			if (classId != null) {
				
				arr= new TimeTableDaoImpl().getClassTimeTableTodayListByClass(accyearid, sectionid,classId,locationId);
				
				request.setAttribute("ClassTodayTimeTableList", arr);
				
			}
			ArrayList<TimeTableVo> teacherSustituteLis=new TimeTableDaoImpl().getTeacherTimeTableTodayListByClass();
			request.setAttribute("teacherSustituteLis", new TimeTableDaoImpl().getTeacherTimeTableTodayListByClass());
			if(teacherSustituteLis.get(0).getStatus().equalsIgnoreCase("notSubstituted")) {
				request.setAttribute("notSubstituted", "Teacher List For Substitution");
				request.setAttribute("heading", "Teacher List For Substitution");
				request.setAttribute("periodT", "Vacant");
				
			}
			else {
				request.setAttribute("Substituted", "Substituted Teacher List");
				request.setAttribute("heading", "Substituted Teacher List");
				request.setAttribute("periodT", "Substituted");
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettodaytimetablelist Ending");

		return mapping.findForward("ClassTodayTimeTableList");

	}
	public ActionForward studentphotoupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentphotoupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_PHOTOUPLOAD);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentphotoupload : Ending");

		return mapping.findForward(MessageConstants.STUDENT_PHOTO_UPLOAD);
	}
	public ActionForward studentPhotosheetSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentPhotosheetAccList Starting");


		try {
			String admission = request.getParameter("admission");
			String accYear = request.getParameter("accyear");
			
			
			

			List<StudentRegistrationVo> list = new StudentRegistrationDaoImpl().getPhotoSheetForSearch(admission,accYear);
			
			request.setAttribute("studentSearchList", list);
			
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("studentSearchList", list);

			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentPhotosheetAccList Ending");

		return null;
	}	
	public ActionForward substitution(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : substitution Starting");


		try {
			
			UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userId=user.getUserId();
			
			

			String status =new TimeTableDaoImpl().getSubstitute(userId);
			
			
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("status", status);

			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : substitution Ending");

		return null;
	}
	public ActionForward getAllSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Starting");


		try {
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("schoolName", HelperClass.getAllLocation());
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Ending");

		return null;
	}
	public ActionForward getClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Starting");


		try {
			String schoolLocation=request.getParameter("location");
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("classList", HelperClass.getAllClassList(schoolLocation));
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Ending");

		return null;
	}
	public ActionForward getTermList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Starting");


		try {
			String schoolLocation=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("TermList", HelperClass.getTermList(schoolLocation, accyear));
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Ending");

		return null;
	}
	public ActionForward getAccyearList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Starting");


		try {
	
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Accyear", HelperClass.getAllAcademicYear());
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAllSchool Ending");

		return null;
	}
	
	
	
	public ActionForward ETLM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : ETL Starting");


		try {
	
			ETL.backendDataexchange();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : ETL Ending");

		return null;
	}
}

