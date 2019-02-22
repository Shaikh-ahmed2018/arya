package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import org.json.JSONObject;

import com.centris.campus.vo.LeaveViewDetailsVo;
import com.centris.campus.daoImpl.LeaveBankDAOIMPL;
import com.centris.campus.daoImpl.TeacherDaoImpl;
import com.centris.campus.daoImpl.TeacherLeaveRequestDaoImpl;
import com.centris.campus.delegate.AssignmentUploadBD;
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.MarksUploadDelegate;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StaffPayrollBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TeacherLeaveModuleBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.ProjectVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public class TeacherMenuAction extends DispatchAction {

	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String EcampusPro_Payslip_Dir = res.getString("EcampusPro_Payslip_Dir");

	private static final Logger logger = Logger.getLogger(TeacherMenuAction.class);

	private static String ImageName = res.getString("ImageName");
	public ActionForward Home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : Home Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : Home Ending");

		return mapping.findForward(MessageConstants.TEACHER_HOME);
	}

	public ActionForward leaveRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequest Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LEAVE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVEREQUESTANDSTATUS);
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
			list = new LeaveBankDelegate().getleavenamesList(academic_year);
			request.setAttribute("LeaveList", list);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usertype=userDetailVO.getRoleCode();
			String empId=userDetailVO.getUserId();
			
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();

			ParentExamdetailsBD leavedeligate = new ParentExamdetailsBD();

			

			String parentid = HelperClass.getCurrentUserID(request);

			request.setAttribute("parentid", parentid);

			/*ParentVO vo = new ParentVO();
			request.setAttribute("parentid", vo);*/

			LeaveRequestVo leavevo = new LeaveRequestVo();

			if (searchTerm != null) {
				
				leavelist = leavedeligate.searchleaverequest(parentid,searchTerm.trim());
				request.setAttribute("searchterm", searchTerm);
			} else {

					leavelist = leavedeligate.getleaveRequestDetailsBD(leavevo,empId,usertype);	
			
			}

			request.setAttribute("leavelist", leavelist);
			request.getSession(false).setAttribute("excelDownLoad", leavelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequest Ending");

		return mapping.findForward(MessageConstants.LEAVEREQUEST);
	}

	/*
	 * public ActionForward requestLeavescreen(ActionMapping mapping, ActionForm
	 * form, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
	 * Date()) + MessageConstants.START_POINT);
	 * logger.info(JDate.getTimeString(new Date()) +
	 * " Control in TeacherMenuAction : requestLeavescreen Starting"); try {
	 * String status = "status";
	 * request.setAttribute(MessageConstants.MODULE_NAME,
	 * MessageConstants.BACKOFFICE_TEACHERS);
	 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
	 * MessageConstants.MODULE_TEACHERS); request.setAttribute("status",
	 * status);
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage(), e);
	 * e.printStackTrace(); }
	 * 
	 * JLogger.log(0, JDate.getTimeString(new Date()) +
	 * MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	 * + " Control in TeacherMenuAction : requestLeavescreen Ending");
	 * 
	 * return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN); }
	 */

	public ActionForward leaveApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveApproval Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_LEAVE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_APPROVEORREJECTLEAVE);
			
			String userid = HelperClass.getCurrentUserID(request);

			String usertype = HelperClass.getCurrentUser(request);

			LeaveRequestVo leavevo = new LeaveRequestVo();

			leavevo.setUserid(userid);
			leavevo.setUsertype(usertype);

			String searchTerm = request.getParameter("searchTerm");

			

			ArrayList<LeaveRequestVo> leaveapproval = null;

			if (searchTerm != null) {

				leaveapproval = new ParentExamdetailsBD().searchleave(searchTerm);

				request.setAttribute("search", searchTerm.trim());

			} else {

				leaveapproval = new ParentExamdetailsBD().getleaveApprovalDetailsBD(leavevo);

			}

			request.setAttribute("leaveapproval", leaveapproval);
			request.getSession(false).setAttribute("excel", leaveapproval);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveApproval Ending");

		return mapping.findForward(MessageConstants.LEAVEAPPROVAL);
	}

	public ActionForward LeaveApprovalScreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : LeaveApprovalScreen Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_APPROVEORREJECTLEAVE);

			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
			list = new LeaveBankDelegate().getleavenamesList(academic_year);
			request.setAttribute("LeaveList", list);
			
			/* String userid = HelperClass.getCurrentUserID(request); */

		/*	LeaveRequestVo leavevo = new LeaveRequestVo();*/

			String sno = request.getParameter("snoid");

			// leavevo.setUserid(sno);

			// leavevo.setSno1(sno);

			LeaveRequestVo result = new ParentExamdetailsBD().getLeaveApprovalBD(Integer.parseInt(sno));
			request.setAttribute("attnhidden", sno);
			request.setAttribute("leaveapprovelist", result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : LeaveApprovalScreen Ending");

		return mapping.findForward(MessageConstants.LEAVEAPPROVALSCREEN);
	}

	public ActionForward ApprovingLeaveforleaveRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : ApprovingLeaveforleaveRequest Starting");
		try {
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");

			String userid = HelperClass.getCurrentUserID(request);

			String usertype = HelperClass.getCurrentUser(request);

			LeaveRequestVo leavevo = new LeaveRequestVo();

			String sno = request.getParameter("snoid");

			String leavesapproved = request.getParameter("leavesapproved");

			String approvedstartdate = request
					.getParameter("approvedstartdate");

			String approvedleavestatus = request
					.getParameter("approvedleavestatus");

			String comments = request.getParameter("comments");

			String approvedenddate = request.getParameter("approvedenddate");

			String approvedendsessionDay = request
					.getParameter("approvedendsessionDay");

			String approvedstartsessionDay = request
					.getParameter("approvedstartsessionDay");
			
			
			if(approvedleavestatus.equalsIgnoreCase("Rejected")){
				
				leavevo.setSno(Integer.parseInt(sno));
				leavevo.setLeavesapproved("0.0");
				leavevo.setApprovedstartdate("-");
				leavevo.setApprovedenddate("-");
				leavevo.setApprovedleavestatus(approvedleavestatus);
				leavevo.setComments(comments);
				leavevo.setUserid(userid);
				leavevo.setUsertype(usertype);
				leavevo.setApprovedstartsessionDay("-");
				leavevo.setApprovedendsessionDay("-");
				
			}
			else{
			
				leavevo.setSno(Integer.parseInt(sno));

				leavevo.setLeavesapproved(leavesapproved);

				leavevo.setApprovedstartdate(approvedstartdate);

				leavevo.setApprovedenddate(approvedenddate);

				leavevo.setApprovedleavestatus(approvedleavestatus);

				leavevo.setComments(comments);

				leavevo.setUserid(userid);

				leavevo.setUsertype(usertype);

				leavevo.setApprovedstartsessionDay(approvedstartsessionDay);

				leavevo.setApprovedendsessionDay(approvedendsessionDay);
			}
			
			ArrayList<LeaveBankVO> getleaveDetails = new ArrayList<LeaveBankVO>();
			getleaveDetails = new LeaveBankDelegate().getleaveBalance(userid,academic_year);
			request.setAttribute("BalanceList", getleaveDetails);
			
			
			ArrayList<LeaveRequestVo> leaveapproval = new ParentExamdetailsBD().ApprovingLeaveforleaveRequestBD(leavevo);

			request.setAttribute("leaveapproval", leaveapproval);

			ArrayList<LeaveRequestVo> leaveapproval1 = new ParentExamdetailsBD().getleaveApprovalDetailsBD(leavevo);

			request.setAttribute("leaveapproval", leaveapproval1);

			request.setAttribute("success", "success");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : ApprovingLeaveforleaveRequest Ending");

		return mapping.findForward(MessageConstants.LEAVEAPPROVAL);
		
	}

	public ActionForward attendaceStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : attendaceStatus Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);

			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			ArrayList<StudentAttendanceVo> studentAttendance = new StudentAttendanceBD()
					.getStudentsAttendanceList(startdate, endDate);

			request.setAttribute("attendancelist", studentAttendance);

			request.setAttribute("stdid",startdate);
			request.setAttribute("StartDate", startdate);
			request.setAttribute("EndDate", endDate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : attendaceStatus Ending");

		return mapping.findForward(MessageConstants.ATTENDANCESTATUS);
	}

	public ActionForward attendaceUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : attendaceUpload Starting");
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
				+ " Control in TeacherMenuAction : attendaceUpload Ending");

		return mapping.findForward(MessageConstants.ATTENDANCEUPLOAD);
	}

	public ActionForward assignmentView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : assignmentView Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADASSIGNMENT);

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm == null) {

				searchTerm = "";
			}
			request.setAttribute("searchname", searchTerm);
			

			String userId = HelperClass.getCurrentUserID(request);
			String accYearString = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			
			ArrayList<AssignmentUploadVo> list = new AssignmentUploadBD().getAssignment(userId, accYearString, searchTerm);
			
			request.setAttribute("AssignmentList", list);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : assignmentView Ending");

		return mapping.findForward(MessageConstants.ASSIGNMENTVIEW);
	}

	public ActionForward addassignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : addassignment Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADASSIGNMENT);
/*			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();*/

			/*String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = request.getParameter("location");*/

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
	

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : addassignment Ending");

		return mapping.findForward(MessageConstants.ADDASSIGNMENT);
	}

	public ActionForward marksStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : marksStatus Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm == null) {

				searchTerm = "";
			}
			request.setAttribute("MarksUploadList",
					new MarksUploadDelegate().getMarksUploadList(searchTerm));

			request.getSession(false).setAttribute("EXcelDownLoad",
					new MarksUploadDelegate().getMarksUploadList(searchTerm));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : marksStatus Ending");

		return mapping.findForward(MessageConstants.MARKSUPLOAD);
	}

	public ActionForward uploadMarks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : uploadMarks Starting");
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
				+ " Control in TeacherMenuAction : uploadMarks Ending");

		return mapping.findForward(MessageConstants.MARKSUPLOADSCREEN);
	}

	public ActionForward requestLeavescreenadd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Starting");

		try {

			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
			
			list = new LeaveBankDelegate().getleavenamesList(academic_year);
			request.setAttribute("LeaveList", list);
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();

			ParentVO vo = new ParentVO();

			vo.setParentID(parentid);

			ArrayList<ParentVO> student = new ParentExamdetailsBD()
					.getStudentlist(vo);

			request.setAttribute("studentlist", student);

			request.setAttribute("parentid", vo);

			List<UserDetailVO> userlist = new ParentExamdetailsBD()
					.getRequestUserListDetails(parentid);
			request.setAttribute("userlist", userlist);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Ending");

		return mapping.findForward(MessageConstants.teacherLeaveRequest);
	}

	public ActionForward addassignmentedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : addassignment Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADASSIGNMENT);

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
				+ " Control in TeacherMenuAction : addassignment Ending");

		return mapping.findForward(MessageConstants.ADDASSIGNMENT);
	}

	public ActionForward uploadMarksedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : uploadMarksedit Starting");
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
				+ " Control in TeacherMenuAction : uploadMarksedit Ending");

		return mapping.findForward(MessageConstants.MARKSUPLOADSCREEN);
	}

	public ActionForward leaveRequestEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequestEntry Starting");

		String path = null;
		String extension = null;
		File fileURL = null;
		FileOutputStream fos = null;

		try {
			LeaveRequestForm leaveform = (LeaveRequestForm) form;
			LeaveRequestVo leavevo = new LeaveRequestVo();
			String parentid = HelperClass.getCurrentUserID(request);
			leaveform.setCreateuser(parentid);

			
			leaveform.setCreateuser(HelperClass.getCurrentUserID(request));

			ParentVO vo = new ParentVO();

			vo.setParentID(parentid);

			leaveform.setCreateuser(parentid);


			request.setAttribute("parentid", parentid);

			FormFile formFile = leaveform.getFileupload();

			if (formFile.getFileSize() > 0) {

				path = request.getRealPath("/")+ "FIles/LEAVEREQUEST";
				File files = new File(path);
				if (!files.exists()) {
					if (files.mkdirs()) {
					}
				}
				path = files.getAbsolutePath();

				int i = formFile.getFileName().lastIndexOf('.');
				if (i > 0) {
					extension = formFile.getFileName().substring(i + 1);
				}

				String fileName = HelperClass.getCurrentSqlDate() + "_"
						+ formFile;
				fileURL = new File(path, fileName);

				fos = new FileOutputStream(fileURL);
				fos.write(formFile.getFileData());

				String file1 = fileURL.getPath();

				file1 = file1.substring(file1.indexOf("FIles\\"));

				if (!fileURL.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(
							fileURL);

					fileURL.mkdir();
					fileOutStream.write(formFile.getFileData());

					fileOutStream.flush();

					fileOutStream.close();
				}

				leavevo.setFileupload(file1);

			} else {

				leavevo.setFileupload(leaveform.getFileupload1());

			}

			leavevo.setRequestto(leaveform.getRequestto());
			leavevo.setTotalleave(leaveform.getTotalleave());
			leavevo.setFromdate(HelperClass.convertUIToDatabase(leaveform
					.getFromdate()));
			leavevo.setTodate(HelperClass.convertUIToDatabase(leaveform
					.getTodate()));
			leavevo.setStarttime(leaveform.getStarttime());
			leavevo.setEndtime(leaveform.getEndtime());
			leavevo.setLeavetype(leaveform.getLeavetype());
			leavevo.setReason(leaveform.getReason());
			leavevo.setCreateuser(leaveform.getCreateuser());
			leavevo.setStudentFname(leaveform.getStudentFname());
			leavevo.setUserid(leaveform.getUserid());
			leavevo.setSno(leaveform.getSno());

			String result = new TeacherLeaveModuleBD().leaveRequestEntryBD(leavevo);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequestEntry Ending");

		return mapping.findForward(MessageConstants.teacherLeaveRequest);

		}

	public ActionForward editRequestLeaveAction(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Starting");

		try {
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String user = userDetailVO.getUserId();
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			String parentid = HelperClass.getCurrentUserID(request);
			ParentVO vo = new ParentVO();

			vo.setParentID(parentid);

			/*ArrayList<ParentVO> student = new ParentExamdetailsBD()
					.getStudentlist(vo);

			request.setAttribute("studentlist", student);*/
			
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
			list = new LeaveBankDelegate().getleavenamesList(academic_year);
			request.setAttribute("LeaveList", list);

			
			List<UserDetailVO> userlist = new TeacherLeaveModuleBD().getRequestUserListDetails(user);

			request.setAttribute("userlist", userlist);

			String sno = (String) request.getParameter("snoid");

			LeaveRequestVo result = new TeacherLeaveModuleBD().getRequestLeaveBD(Integer.parseInt(sno));

			request.setAttribute("leavedatails", result);
			
			ArrayList<LeaveBankVO> getleaveDetails = new ArrayList<LeaveBankVO>();
			getleaveDetails = new LeaveBankDelegate().getleaveBalance(parentid,academic_year);
			request.setAttribute("BalanceList", getleaveDetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Ending");

		return mapping.findForward(MessageConstants.teacherLeaveRequest);

	}

	public ActionForward deleteLeaveRequestAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : deleteLeaveRequestAction Starting");

		try {

			/*int count[]=new int[0]; */
			String userid = HelperClass.getCurrentUserID(request);
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			String sno = request.getParameter("requestList");
			
			String[] list = sno.split(",");
			
			String check = new ParentExamdetailsBD().deleteRequestLeaveBD(list,userid,academic_year);

			request.setAttribute("delete", check);
			JSONObject json = new JSONObject();

			json.put("status", check);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : deleteLeaveRequestAction Ending");

		return null;
	}

	public ActionForward viewTeacherTimeTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Starting");

		try {

			
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_VIEWTIMETABLE);
			
			
			String teacherid = request.getParameter("userId");

			TeacherRegistrationPojo obj = new TeacherRegistrationPojo();
			obj.setTeacherId(teacherid);

			obj = new TeacherRegistrationBD().getTeacherDetails(obj);


			request.setAttribute("teacherdetails", obj);

			
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Ending");

		return mapping.findForward(MessageConstants.teacherTimeTable);

	}
	
	
	public ActionForward viewTeacherTimeTableBYJs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Starting");

		try {

			
			String userid = request.getParameter("userId");
			

			ArrayList<TimeTableVo> timeTableDetails = new TeacherLeaveModuleBD().getTeacherTimetable(userid);
			
			List<TimeTableVo> timeTableList=new ArrayList<TimeTableVo>();
			for(int i=0;i<timeTableDetails.size();i++){
				TimeTableVo vo=new TimeTableVo();
				vo.setDayid(timeTableDetails.get(i).getDayid());
				vo.setDayname(timeTableDetails.get(i).getDayname());
				vo.setPeriod1(timeTableDetails.get(i).getPeriod().get("period1"));
				vo.setPeriod2(timeTableDetails.get(i).getPeriod().get("period2"));
				vo.setPeriod3(timeTableDetails.get(i).getPeriod().get("period3"));
				vo.setPeriod4(timeTableDetails.get(i).getPeriod().get("period4"));
				vo.setPeriod5(timeTableDetails.get(i).getPeriod().get("period5"));
				vo.setPeriod6(timeTableDetails.get(i).getPeriod().get("period6"));
				vo.setPeriod7(timeTableDetails.get(i).getPeriod().get("period7"));
				vo.setPeriod8(timeTableDetails.get(i).getPeriod().get("period8"));
				vo.setPeriod9(timeTableDetails.get(i).getPeriod().get("period9"));
				timeTableList.add(vo);
				 Collections.sort(timeTableList,HelperClass.TimeTableVoComparator);
				
			}
			
			
			
			JSONObject obj=new JSONObject();
			obj.put("timeTableDetails", timeTableList);
			response.getWriter().print(obj);
			
			
			
			request.setAttribute("timeTableDetails", timeTableList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Ending");

		return null;

	}
	
	public ActionForward viewTodayTeacherTimeTableBYJs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTodayTeacherTimeTableBYJs Starting");

		try {

			
			String userid = request.getParameter("userId");
			

			ArrayList<TimeTableVo> todaytimeTableDetails = new TeacherLeaveRequestDaoImpl().getTodaytimeTableDetails(userid);
			
			
			List<TimeTableVo> todaytimeTableDetailsList=new ArrayList<TimeTableVo>();
			for(int i=0;i<todaytimeTableDetails.size();i++){
				TimeTableVo vo=new TimeTableVo();
				vo.setDayid(todaytimeTableDetails.get(i).getDayid());
				vo.setDayname(todaytimeTableDetails.get(i).getDayname());
				vo.setPeriod1(todaytimeTableDetails.get(i).getPeriod().get("period1"));
				vo.setPeriod2(todaytimeTableDetails.get(i).getPeriod().get("period2"));
				vo.setPeriod3(todaytimeTableDetails.get(i).getPeriod().get("period3"));
				vo.setPeriod4(todaytimeTableDetails.get(i).getPeriod().get("period4"));
				vo.setPeriod5(todaytimeTableDetails.get(i).getPeriod().get("period5"));
				vo.setPeriod6(todaytimeTableDetails.get(i).getPeriod().get("period6"));
				vo.setPeriod7(todaytimeTableDetails.get(i).getPeriod().get("period7"));
				vo.setPeriod8(todaytimeTableDetails.get(i).getPeriod().get("period8"));
				vo.setPeriod9(todaytimeTableDetails.get(i).getPeriod().get("period9"));
				todaytimeTableDetailsList.add(vo);
				 
				
			}
			
			
			
			JSONObject obj1=new JSONObject();
			obj1.put("todaytimeTableDetails", todaytimeTableDetailsList);
			response.getWriter().print(obj1);
			
			request.setAttribute("todaytimeTableDetails", todaytimeTableDetailsList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTodayTeacherTimeTableBYJs Ending");

		return null;

	}
	
	
	public ActionForward viewTeacherListForTimeTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_VIEWTIMETABLE);

			String userid = HelperClass.getCurrentUserID(request);
			UserDetailVO userdetails=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userType=userdetails.getRoleName();
			
		System.out.println("USERTYPE="+userType);
			
			ArrayList<TeacherVo> timeTableDetailsList = new TeacherLeaveRequestDaoImpl().getTeacherListForTimeTable(userid,userType);
			
			request.setAttribute("timeTableDetailsList", timeTableDetailsList);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewTeacherTimeTable Ending");

		return mapping.findForward("timeTableDetailsList");

	}

	public ActionForward downloadPayslip(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_DOWNLOADPAYSLIP);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Ending");

		return mapping.findForward(MessageConstants.DOWNLOAD_PAYSLIP);

	}

	public ActionForward teacherLeaveRequestAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : teacherLeaveRequestAdd Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVEREQUESTANDSTATUS);
			
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			String parentid = userDetailVO.getUserId();
			String userType = userDetailVO.getUserNametype();
			
			ParentVO vo = new ParentVO();
			
			/* String parentid = HelperClass.getCurrentUserID(request); */

			vo.setParentID(parentid);

			/*ArrayList<ParentVO> student = new ParentExamdetailsBD()
					.getStudentlist(vo);

			request.setAttribute("studentlist", student);*/
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
			
			/*LeaveCalculation leavecalc =  new LeaveBankDelegate().getLeaveCalculation(parentid,academic_year);
			request.setAttribute("leaveCals", leavecalc);*/
			
			LeaveCalculation leaveCalculation = new LeaveBankDelegate().getNewLeaveCalculation(parentid, academic_year);
			request.setAttribute("leaveCalculation", leaveCalculation);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : teacherLeaveRequestAdd Ending");

		return mapping.findForward(MessageConstants.teacherLeaveRequest);
	}

	public ActionForward viewLeaveBalance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewLeaveBalance Starting");

		try {

			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");

			String userid = userDetailVO.getUserId();

			String accyear = HelperClass.getCurrentYearID();

			String username = request.getParameter("teachername");
			LeaveRequestForm leaveform = (LeaveRequestForm) form;
			leaveform.setCreateuser(username);

			List<LeaveBalanceVo> leavebalancelist = new TeacherLeaveModuleBD().viewLeaveBalListDetails(userid, accyear, leaveform);

			
			JSONObject leave_Object = new JSONObject();
			leave_Object.put("leavedetails", leavebalancelist);

			response.getWriter().print(leave_Object);

			request.setAttribute("viewbalance", leavebalancelist);

			request.setAttribute("success", "success");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewLeaveBalance Ending");

		return null;

	}

	public ActionForward viewMeetingdandEvents(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewMeetingdandEvents Starting");

		try {
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);

			vo.setParentID(userid);

			ArrayList<ParentVO> teacher = new TeacherLeaveModuleBD()
					.getTeacherlist(vo);

			request.setAttribute("teacherlist", teacher);

			List<LstmsUpcomingMeetingVO> meetinglist = new ParentExamdetailsBD()
					.getMeetingListDetails();
			request.setAttribute("meetinglist", meetinglist);
			request.setAttribute("parenthiddenid", userid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewMeetingdandEvents Ending");

		return mapping.findForward(MessageConstants.teachermeetingandevent);
	}

	public ActionForward getTeacherMeeting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewMeetingdandEvents Starting");

		try {

			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);

			vo.setParentID(userid);

			ArrayList<ParentVO> teacher = new TeacherLeaveModuleBD()
					.getTeacherlist(vo);

			request.setAttribute("teacherlist", teacher);

			LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();

			String teacherid = request.getParameter("hiddenid");

			meetvo.setTeacher(teacherid);

			List<LstmsUpcomingMeetingVO> meetinglist = new TeacherLeaveModuleBD()
					.getteachermeetinglistBD(meetvo);

			request.setAttribute("meetinglist", meetinglist);

			request.setAttribute("studentmeeting", userid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : viewMeetingdandEvents Ending");

		return mapping.findForward(MessageConstants.teachermeetingandevent);
	}

	public synchronized ActionForward downloadPayslipDocument(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: downloadDocument Starting");

		boolean flag = false;
		String month = null;
		String year = null;
		try {

			month = (String) request.getParameter("month");

			year = (String) request.getParameter("accyear");

			UserDetailVO userDetailsVo = (UserDetailVO) request.getSession(
					false).getAttribute(MessageConstants.USER_DETAILS);
			String userid = userDetailsVo.getUserId();
			String username = userDetailsVo.getFirstName().replace(" ", "_");

			String docPath = EcampusPro_Payslip_Dir + "/" + year + "-" + month
					+ "/" + userid + "-" + username + ".pdf";

			

			File docFile = new File(docPath);

			if (docFile.exists() && !docFile.isDirectory()) {

				flag = true;

				response.setContentType("application/octet-stream");
				String fileName = "FileName";
				fileName = docPath;

				response.addHeader("Content-Disposition",
						"attachment; filename=" + userid + "-" + username
								+ ".pdf");

				response.setContentLength((int) docFile.length());

				FileInputStream input = new FileInputStream(docFile);
				BufferedInputStream buf = new BufferedInputStream(input);
				int readBytes = 0;
				ServletOutputStream stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}

			} else {
				flag = false;

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: downloadDocument Ending");

		if (flag) {
			return null;
		} else {

			request.setAttribute("month", month);
			request.setAttribute("year", year);

			request.setAttribute("error",
					"For selected month and year payslip not generated");

			return mapping.findForward(MessageConstants.DOWNLOAD_PAYSLIP);
		}
	}

	public ActionForward downloadLeaveDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/LeaveApprovalDetailsXls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			

			
			String userid = HelperClass.getCurrentUserID(request);

			String usertype = HelperClass.getCurrentUser(request);

			LeaveRequestVo leavevo = new LeaveRequestVo();

			leavevo.setUserid(userid);
			leavevo.setUsertype(usertype);

			String searchTerm = request.getParameter("searchTerm");

			

			ArrayList<LeaveRequestVo> leaveapproval = null;

			if (searchTerm != null) {

				leaveapproval = new ParentExamdetailsBD()
						.searchleave(searchTerm);

				request.setAttribute("search", searchTerm);

			} else {

				leaveapproval = new ParentExamdetailsBD()
						.getleaveApprovalDetailsBD(leavevo);

			}

			/*
			 * List<LeaveRequestVo> List = new ArrayList<LeaveRequestVo>();
			 * ParentExamdetailsBD delegate = new ParentExamdetailsBD(); List =
			 * delegate.getleaveApprovalDetailsBD(leavevo);
			 */

		/*	
		 * 
		 * */
			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
			leavelist = (ArrayList<LeaveRequestVo>) request.getSession(false).getAttribute("excelDownLoad");
			
			request.getSession(false).setAttribute("excelDownLoad", leavelist);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					leaveapproval);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/LeaveApprovalDetailsXls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Leave Approval Details Excel Report" };
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
					request.getRealPath("Reports/LeaveApprovalDetailsXls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LeaveApprovalDetailsXls.xls");
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
				+ " Control in TeacherMenuAction : downloadLeaveDetailsXLS   Ending");
		return null;

	}

	public ActionForward downloadLeaveDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveDetailsPDF  Starting");

		try {

			
			/*
			 * List<LeaveRequestVo> Details = new ArrayList<LeaveRequestVo>();
			 * ParentExamdetailsBD delegate = new ParentExamdetailsBD(); Details
			 * = delegate.getleaveApprovalDetailsBD(leavevo);
			 */

		/*	ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
			leavelist = (ArrayList<LeaveRequestVo>) request.getSession(false)
					.getAttribute("excel");*/
			

			String userid = HelperClass.getCurrentUserID(request);

			String usertype = HelperClass.getCurrentUser(request);

			LeaveRequestVo leavevo = new LeaveRequestVo();

			leavevo.setUserid(userid);
			leavevo.setUsertype(usertype);

			String searchTerm = request.getParameter("searchTerm");

			

			ArrayList<LeaveRequestVo> leaveapproval = null;

			if (searchTerm != null) {
				
				leaveapproval = new ParentExamdetailsBD().searchleave(searchTerm);
				request.setAttribute("searchTerm", searchTerm);

			} else {
				leaveapproval = new ParentExamdetailsBD().getleaveApprovalDetailsBD(leavevo);
			}

			String sourceFileName = request.getRealPath("Reports/LeaveDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(leaveapproval);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "LeaveApprovalDetailsPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveDetailsPDF  Ending");
		return null;

	}

	public ActionForward downloadmeetingsandeventsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadmeetingsandeventsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/meetingsandeventxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			List<LstmsUpcomingMeetingVO> meetinglist = new ParentExamdetailsBD()
					.getMeetingListDetails();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					meetinglist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/meetingsandeventxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Meetings and Events Excel Report" };
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
					request.getRealPath("Reports/meetingsandeventxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=MeetingsandEventxls.xls");
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
				+ " Control in TeacherMenuAction : downloadmeetingsandeventsXLS   Ending");
		return null;

	}

	public ActionForward downloadmeetingsandeventsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadmeetingsandeventsPDF  Starting");

		try {

			

			List<LstmsUpcomingMeetingVO> Details = new ParentExamdetailsBD()
					.getMeetingListDetails();

			String sourceFileName = request
					.getRealPath("Reports/meetingandeventgDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					Details);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "MeetingandEventgDetailsPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadmeetingsandeventsPDF  Ending");
		return null;

	}

	public ActionForward downloadTeacherTimetableXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadTeacherTimetableXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/teachertimetablexls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String userid = HelperClass.getCurrentUserID(request);

			ArrayList<TimeTableVo> timeTableDetails = new TeacherLeaveModuleBD()
					.getTeacherTimetable(userid);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					timeTableDetails);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/teachertimetablexls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Teacher TimeTable Excel Report" };
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
					request.getRealPath("Reports/teachertimetablexls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TeacherTimetablexls.xls");
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
				+ " Control in TeacherMenuAction : downloadTeacherTimetableXLS   Ending");
		return null;

	}

	public ActionForward downloadTeacherTimetablePDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadTeacherTimetablePDF  Starting");

		try {

			

			String userid = HelperClass.getCurrentUserID(request);

			ArrayList<TimeTableVo> timeTableDetails = new TeacherLeaveModuleBD()
					.getTeacherTimetable(userid);

			String sourceFileName = request
					.getRealPath("Reports/teachertimetablePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					timeTableDetails);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "TeacherTimetablePDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadTeacherTimetablePDF  Ending");
		return null;

	}

	public ActionForward circularRemainder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : circularRemainder  Starting");

		try {

			List<RemainderMasterVO> remainderlist = new TeacherLeaveModuleBD()
					.getRemainderlistBD();

			request.setAttribute("remainderlist", remainderlist);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : circularRemainder  Ending");

		return mapping.findForward(MessageConstants.teacherRemainder);

	}

	public ActionForward downloadLeaveRequestXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveRequestXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/leaverequestdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			String searchTerm = request.getParameter("searchTerm");
			
			String userid = HelperClass.getCurrentUserID(request);
			
			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();

			ParentExamdetailsBD leavedeligate = new ParentExamdetailsBD();

			LeaveRequestVo leavevo = new LeaveRequestVo();

			if (searchTerm != null) {
				

				leavelist = leavedeligate.searchleaverequest(searchTerm,userid);

				request.setAttribute("searchterm", searchTerm);
			} else {

				leavelist = leavedeligate.getleaveRequestDetailsBD(leavevo);
				
			}

			/*
			 * ArrayList<LeaveRequestVo> leavelist = new
			 * ArrayList<LeaveRequestVo>(); leavelist =
			 * (ArrayList<LeaveRequestVo>) request.getSession(false)
			 * .getAttribute("excelDownLoad");
			 */

			/*
			 * LeaveRequestVo leavevo = new LeaveRequestVo();
			 * 
			 * ArrayList<LeaveRequestVo> leavelist = new ParentExamdetailsBD()
			 * .getleaveRequestDetailsBD(leavevo);
			 */
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					leavelist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/LeaveRequestDetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "LeaveRequest Details Excel Report" };
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
					request.getRealPath("Reports/LeaveRequestDetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LeaveRequestDetailsxls.xls");
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
				+ " Control in TeacherMenuAction : downloadLeaveRequestXLS   Ending");
		return null;

	}

	public ActionForward downloadLeaveRequestPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveRequestPDF  Starting");

		try {

			

			/*
			 * LeaveRequestVo leavevo = new LeaveRequestVo();
			 * 
			 * ArrayList<LeaveRequestVo> leavelist = new ParentExamdetailsBD()
			 * .getleaveRequestDetailsBD(leavevo);
			 */
			/*
			 * ArrayList<LeaveRequestVo> leavelist = new
			 * ArrayList<LeaveRequestVo>(); leavelist =
			 * (ArrayList<LeaveRequestVo>) request.getSession(false)
			 * .getAttribute("excelDownLoad");
			 */

			String searchTerm = request.getParameter("searchTerm");

			String userid = HelperClass.getCurrentUserID(request);

			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();

			ParentExamdetailsBD leavedeligate = new ParentExamdetailsBD();

			LeaveRequestVo leavevo = new LeaveRequestVo();

			if (searchTerm != null) {

				leavelist = leavedeligate.searchleaverequest(searchTerm.trim(),userid);
				
				request.setAttribute("searchterm", searchTerm);
			} else {

				leavelist = leavedeligate.getleaveRequestDetailsBD(leavevo);
			
			}
			String sourceFileName = request
					.getRealPath("Reports/leaverequestDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					leavelist);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "LeaveRequestDetailsPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadLeaveRequestPDF  Ending");
		return null;

	}

	public ActionForward downloadcircularandremainderXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadcircularandremainderXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/circularandRemainderXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			List<RemainderMasterVO> remainderlist = new TeacherLeaveModuleBD()
					.getRemainderlistBD();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					remainderlist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/circularandRemainderXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Circular And Remainder Details Excel Report" };
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
					request.getRealPath("Reports/CircularAndRemainderXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=CircularAndRemainderXLSReport.xls");
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
				+ " Control in TeacherMenuAction : downloadcircularandremainderXLS   Ending");
		return null;

	}

	public ActionForward downloadcircularandremainderPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadcircularandremainderPDF  Starting");

		try {

			

			List<RemainderMasterVO> remainderlist = new TeacherLeaveModuleBD()
					.getRemainderlistBD();
			String sourceFileName = request
					.getRealPath("Reports/circularandRemainderPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					remainderlist);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "CircularAndRemainderPDFReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadcircularandremainderPDF  Ending");
		return null;

	}

	public ActionForward getLeaveAprrovedDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : getLeaveAprrovedDetails Starting");
		try {

	

			String sno = request.getParameter("snoid");
			String userid = HelperClass.getCurrentUserID(request);
			String usertype = HelperClass.getCurrentUser(request);
			
			LeaveRequestVo  leavevo=new LeaveRequestVo();

			leavevo.setSno1(sno);
			leavevo.setUserid(userid);
			leavevo.setUsertype(usertype);

			ArrayList<LeaveRequestVo> leaveapproval = new ArrayList<LeaveRequestVo>();

			leaveapproval = new LeaveBankDelegate().getLeaveAprrovedDetails(leavevo);
			
			LeaveRequestVo approvedlist = new LeaveRequestVo();
			
			String approvedby = leaveapproval.get(0).getUserid();
			String approveddate = leaveapproval.get(0).getApproveddate();
			String approvedleaves = leaveapproval.get(0).getLeavesapproved();
			String approvedsdate = leaveapproval.get(0).getApprovedstartdate();
			String approvededate = leaveapproval.get(0).getApprovedenddate();
			String approvedcomments = leaveapproval.get(0).getComments();
			String approvedstrtsess = leaveapproval.get(0).getApprovedstartsessionDay();
			String approvedendsess = leaveapproval.get(0).getApprovedendsessionDay();
			
			
			
			approvedlist.setUserid(approvedby);
			approvedlist.setApproveddate(approveddate);
			approvedlist.setLeavesapproved(approvedleaves);
			approvedlist.setApprovedstartdate(approvedsdate);
			approvedlist.setApprovedenddate(approvededate);
			approvedlist.setComments(approvedcomments);
			approvedlist.setApprovedstartsessionDay(approvedstrtsess);
			approvedlist.setApprovedendsessionDay(approvedendsess);
			
			request.setAttribute("leaveapprovaldetails", approvedlist);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : getLeaveAprrovedDetails Ending");

		return mapping.findForward(MessageConstants.LEAVEAPPROVEDDETAILS);
	}

	public ActionForward downloadfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadfile Starting");
	
		
		
		try {
			
			String docPath = request.getParameter("Path");	
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
		
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			File docFile = new File(request.getRealPath("/") + docPath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in TeacherMenuAction : downloadfile Ending");

		return null;
	}
	
	public ActionForward getviewLeaveDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : getviewLeaveDetails Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_LEAVE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVEDETAILS);
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			/*ArrayList<LeaveViewDetailsVo> leaveViewDetailsVo = new LeaveBankDelegate().getviewLeaveDetails(userDetailVO.getUserId().trim());*/
			
			ArrayList<LeaveViewDetailsVo> leaveNewViewDetailsVo = new LeaveBankDelegate().getviewNewLeaveDetails(userDetailVO.getUserId().trim(),academic_year);
			
			request.setAttribute("ViewLeaveDetails", leaveNewViewDetailsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : getviewLeaveDetails Ending");

		return mapping.findForward("leaveteacherdetails");
	}
	
	public ActionForward checkLeaveCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : checkLeaveCount Starting");

		try{
		
			ArrayList<LeaveCalculation> tot_leaves=new ArrayList<LeaveCalculation>();
			LeaveCalculation obj = new LeaveCalculation();
			String totalLeaves = request.getParameter("total");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String diifDays = request.getParameter("daysDiff");
			String leavetype = request.getParameter("leavetype");
			String employeeType = request.getParameter("employeeType");
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			String parentid = userDetailVO.getUserId();
			
			
			obj.setAccyear(academic_year);
			obj.setUserid(parentid);
			obj.setLeaveStdate(startDate);
			obj.setLeaveEnddate(endDate);
			obj.setTotal_leave_req(Double.parseDouble(totalLeaves));
			obj.setTot_days(Double.parseDouble(diifDays));
			obj.setLeavetype(leavetype);
			
			if(employeeType.equalsIgnoreCase("TEMPORARY")) {
				tot_leaves = new LeaveBankDAOIMPL().checkLeaveCountForTemporary(obj);
				 
			}
			
			else {
				tot_leaves = new LeaveBankDelegate().checkLeaveCount(obj);
			}
			
			
		
			
			JSONObject leave_Object = new JSONObject();
			leave_Object.put("tot_leaves", tot_leaves);
			response.getWriter().print(leave_Object);
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : checkLeaveCount Ending");
		
		return null;
	}
	
	public ActionForward checkDateDuplicacy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : getviewLeaveDetails Starting");

		try{
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();
			
			String startDate = request.getParameter("Fromdate");
			String toDate = request.getParameter("todate");
			String leavetype = request.getParameter("leavetype");
			
			String result = new LeaveBankDelegate().checkDateDuplicacy(startDate,toDate,leavetype,parentid);
			
			JSONObject leave_Object = new JSONObject();
			leave_Object.put("status",result);
			response.getWriter().print(leave_Object);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public ActionForward checkLeaveEligibility(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeMenuAction : getviewLeaveDetails Starting");

		try{
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();

			String fromDate = request.getParameter("Fromdate");
			String leavetype = request.getParameter("leavetype");

			String result = new LeaveBankDelegate().checkMonthleave(academic_year,parentid,fromDate,leavetype);
			JSONObject obj=new JSONObject();
			obj.put("status", result);
			response.getWriter().print(obj);


		}catch(Exception e){

		}


		return null;
	}

	public ActionForward generatePayroll(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_GENERATEPAYROLL);

			String year = (String) request.getSession(false).getAttribute("current_academicYear");

			if(year==null){
				year= HelperClass.getCurrentYearID();
			}
			String locationId=null;
			if(locationId == null){
				locationId="all";
			}
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<PayRollVo> yearname=new StaffPayrollBD().getPayRorllMonthList(year);

			PayRollVo payrollvo=new PayRollVo();

			payrollvo.setCurryear(year);
			payrollvo.setLocationId(locationId);
			payrollvo.setYearcode(yearname);
			payrollvo.setMonthval("all");
			ArrayList<PayRollVo> payroll_list= new StaffPayrollBD().getAllPayrollList(payrollvo);


			request.setAttribute("payroll_list_status", payroll_list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Ending");

		return mapping.findForward(MessageConstants.GENERATE_PAYROLL);

	}
	
	public ActionForward getPayRorllMonthList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayRorllMonthList Starting");
		try {
			List<PayRollVo> list = new ArrayList<PayRollVo>();
			String accyearid = request.getParameter("accyearid");



			list = new StaffPayrollBD().getPayRorllMonthList(accyearid);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getMonthList", list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayRorllMonthList Ending");
		return null;
	} 
	
	public ActionForward GenerateMultiplePayroll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GenerateMultiplePayroll Starting");
		try {
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String empId=userDetailVO.getUserId();
			
			PayRollVo vo=new PayRollVo();
			//List<PayRollVo> list = new ArrayList<PayRollVo>();
			String locationId=request.getParameter("locationId");
			String yearId=request.getParameter("yearId");
			String monthId=request.getParameter("monthId");
			String accyearId=request.getParameter("accyearId");
			
			String teacherId[]=request.getParameter("teacherId").split(",");
			String monthdays[]=request.getParameter("monthdays").split(",");
			String payabledays[]=request.getParameter("payabledays").split(",");
			String earnbasic[]=request.getParameter("earnbasic").split(",");
			String earnda[]=request.getParameter("earnda").split(",");
			String earnhra[]=request.getParameter("earnhra").split(",");
			String earnconvience[]=request.getParameter("earnconvience").split(",");
			String earnperform[]=request.getParameter("earnperform").split(",");
			String earnfood[]=request.getParameter("earnfood").split(",");
			String earnspecial[]=request.getParameter("earnspecial").split(",");
			String earnchild[]=request.getParameter("earnchild").split(",");
			String earnarrears[]=request.getParameter("earnarrears").split(",");
			String earnreimburse[]=request.getParameter("earnreimburse").split(",");
			String earninternet[]=request.getParameter("earninternet").split(",");
			String earndriver[]=request.getParameter("earndriver").split(",");
			String earnleave[]=request.getParameter("earnleave").split(",");
			String earnmedical[]=request.getParameter("earnmedical").split(",");
			String earntotal[]=request.getParameter("earntotal").split(",");
			String earnpfempr[]=request.getParameter("earnpfempr").split(",");
			String earnpfempy[]=request.getParameter("earnpfempy").split(",");
			String earnesiempr[]=request.getParameter("earnesiempr").split(",");
			String earnesiempy[]=request.getParameter("earnesiempy").split(",");
			String monthlyptax[]=request.getParameter("monthlyptax").split(",");
			String monthlyincometax[]=request.getParameter("monthlyincometax").split(",");
			String monthlytotaldeduction[]=request.getParameter("monthlytotaldeduction").split(",");
			String monthlytakehome[]=request.getParameter("monthlytakehome").split(",");
			String monthlysalpending[]=request.getParameter("monthlysalpending").split(",");
			String monthlyadvance[]=request.getParameter("monthlyadvance").split(",");
			String monthlynetpay[]=request.getParameter("monthlynetpay").split(",");
			
			vo.setEmpId(empId);	
			vo.setMonthName(monthId);
			vo.setLocationId(locationId);
			vo.setAccyearId(accyearId);
			vo.setYearval(yearId);
			
			vo.setTeacher(teacherId);
			vo.setMonthdays(monthdays);
			vo.setPayableday(payabledays);
			vo.setEarnbasic(earnbasic);
			vo.setEarnda(earnda);
			vo.setEarnhra(earnhra);
			vo.setEarnconvience(earnconvience);
			vo.setEarnperform(earnperform);
			vo.setEarnfood(earnfood);
			vo.setEarnspecial(earnspecial);
			vo.setEarnchild(earnchild);
			vo.setEarnarrears(earnarrears);
			vo.setEarnreimburse(earnreimburse);
			vo.setEarninternet(earninternet);
			vo.setEarndriver(earndriver);
			vo.setEarnleave(earnleave);
			vo.setEarnmedical(earnmedical);
			vo.setEarntotal(earntotal);
			vo.setEarnpfempr(earnpfempr);
			vo.setEarnpfempy(earnpfempy);
			vo.setEarnesiempr(earnesiempr);
			vo.setEarnesiempy(earnesiempy);
			vo.setMonthlyptax(monthlyptax);
			vo.setMonthlyincometax(monthlyincometax);
			vo.setMonthlytotaldeduction(monthlytotaldeduction);
			vo.setMonthlytakehome(monthlytakehome);
			vo.setMonthlysalpending(monthlysalpending);
			vo.setMonthlyadvance(monthlyadvance);
			vo.setMonthlynetpay(monthlynetpay);
			
			String list = new StaffPayrollBD().GenerateMultiplePayroll(vo);
		
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getPayrollStatus", list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GenerateMultiplePayroll Ending");
		return null;
	}
	
	public ActionForward generatePayrollList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayroll : generatePayroll: Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String empId=userDetailVO.getUserId();
		try {
			LeaveRequestForm payrollform = (LeaveRequestForm) form;
			
			String generateId=payrollform.getPayrollid();
			String[] value=generateId.split(",");
			
			String yearvalcode=value[0].trim();
			String status=value[1].trim();
			String monthvalcode=value[2].trim();
			String locationId=value[3].trim();
			String yearname=value[4].trim();
			if(!locationId.equalsIgnoreCase("all")){
				String locationname=new StaffPayrollBD().getLoctionName(locationId);
				request.setAttribute("locationname", locationname);
				request.setAttribute("locationId", locationId);
			}else{
				request.setAttribute("locationname", "All");
				request.setAttribute("locationId", "all");
			}
			
			String accyear=new StaffPayrollBD().getAccYearName(yearvalcode);
			request.setAttribute("accyear", accyear);
			request.setAttribute("accyearId", yearvalcode);
			
			String monthname=new StaffPayrollBD().getMonthName(yearvalcode,monthvalcode);
			request.setAttribute("monthname", monthname);
			request.setAttribute("monthvalcode", monthvalcode);
			request.setAttribute("yearname", yearname);
			
			List<PayRollVo> list = new ArrayList<PayRollVo>();
			list = new StaffPayrollBD().getPayRollList(yearvalcode,locationId,monthvalcode,yearname);
			request.setAttribute("EmployeePayroll", list);


		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayroll : generatePayroll: Ending");
		
		return mapping.findForward(MessageConstants.GENERATE_PAYROLL_LIST);
	}
	
	public ActionForward generatePayrollListByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayroll : generatePayroll: Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_GENERATEPAYROLL);
			String yearvalcode=request.getParameter("accyearid");
			String status=request.getParameter("status");
			String monthvalcode=request.getParameter("monthId");
			String locationId=request.getParameter("locationId");
			String yearname=request.getParameter("yearId");
			
			List<PayRollVo> list = new ArrayList<PayRollVo>();
			if(!status.equalsIgnoreCase("Generated")){
				list = new StaffPayrollBD().getPayRollList(yearvalcode,locationId,monthvalcode,yearname);
			}else{
				list = new StaffPayrollBD().getGeneratedPayRollList(yearvalcode,locationId,monthvalcode,yearname);
			}
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("EmployeePayroll", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in GeneratePayroll : generatePayroll: Ending");
		
		return null;
	}
	
	public ActionForward getPayRollYearListByLocation(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_GENERATEPAYROLL);
			String year=request.getParameter("accyearid");
			String locationId=request.getParameter("locationId");
			/*if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}*/
			String monthId=request.getParameter("monthId");
			String monthYear=request.getParameter("monthYear");
			List<PayRollVo> yearname=new StaffPayrollBD().getPayRorllMonthList(year);

			PayRollVo payrollvo=new PayRollVo();

			payrollvo.setCurryear(year);
			payrollvo.setLocationId(locationId);
			payrollvo.setMonthval(monthId);
			payrollvo.setYearcode(yearname);
			payrollvo.setMonthYear(monthYear);
			
			ArrayList<PayRollVo> payroll_list= new StaffPayrollBD().getAllPayrollList(payrollvo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("payroll_list_status", payroll_list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadPayslip Ending");

		return null;

	}
	
	public ActionForward createPayslip(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreatepayslipAction : createPayslip: Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String empId=userDetailVO.getUserId();
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_GENERATEPAYROLL);
			String accyear=request.getParameter("accyearId").trim();
			String locationId=request.getParameter("locationId").trim();
			String monthId=request.getParameter("monthId").trim();
			String yearId=request.getParameter("yearId").trim();

			String sourceFileName = request.getRealPath("Reports/CreatePayslip.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			List<PayRollVo> payslipList = new StaffPayrollBD().getEmpMonthPayrollDetailsDownload(accyear,locationId,monthId,yearId);

			List<PayRollVo> payslip = null;
			FileOutputStream outputFile = null;
			File secondDir=null;
			File firstDir=null;

			firstDir=new File(EcampusPro_Payslip_Dir);
			if(firstDir.exists()){

				secondDir=new File(EcampusPro_Payslip_Dir+"/"+HelperClass.getMonthFullName(monthId)+"-"+yearId);
				secondDir.mkdir();
			}
			else{

				new File(EcampusPro_Payslip_Dir+"/"+HelperClass.getMonthFullName(monthId)+"-"+yearId).mkdirs();
			}

			JRBeanCollectionDataSource beanColDataSource = null;
			for (int i = 0; i < payslipList.size(); i++) {
				payslip = new ArrayList<PayRollVo>();
				payslip.add(payslipList.get(i));
				beanColDataSource = new JRBeanCollectionDataSource(payslip);
				
				Map parameters = new HashMap();
				parameters.put("monthName", HelperClass.getMonthFullName(monthId));
				parameters.put("year", yearId);
				JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
				outputFile = new FileOutputStream(new File(EcampusPro_Payslip_Dir + "/"
						+ HelperClass.getMonthFullName(monthId)
						+ "-"
						+ yearId.trim()
						+ "/"
						+ payslip.get(0).getEmpId()
						+ "-"
						+ payslip.get(0).getEmpName().trim().replaceAll(" ", "_") + ".pdf"));
				JasperExportManager.exportReportToPdfStream(print, outputFile);

				outputFile.close();
			}

			String year = (String) request.getSession(false).getAttribute("current_academicYear");

			if(year==null){
				year= HelperClass.getCurrentYearID();
			}
			String locationId1=null;
			if(locationId1 == null){
				locationId1="all";
			}
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<PayRollVo> yearname=new StaffPayrollBD().getPayRorllMonthList(year);

			PayRollVo payrollvo=new PayRollVo();

			payrollvo.setCurryear(year);
			payrollvo.setLocationId(locationId1);
			payrollvo.setYearcode(yearname);
			payrollvo.setMonthval("all");
			ArrayList<PayRollVo> payroll_list= new StaffPayrollBD().getAllPayrollList(payrollvo);


			request.setAttribute("payroll_list_status", payroll_list);

			if (payslipList.size() != 0) {
				request.setAttribute("serverMessage","Success: Payslip generated find "+"''"+EcampusPro_Payslip_Dir+"''"+ " for Payslip");
			} else {
				request.setAttribute("serverMessage","payroll not genarated for " + monthId + "please genarate payroll first");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreatepayslipAction : createPayslip: Ending");
		return mapping.findForward(MessageConstants.GENERATE_PAYROLL);
	}
	
	public ActionForward downloadPayrollExcel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in GenaratePayroll:  downloadPayrollExcel Starting");

		try {
			String accyear=request.getParameter("accyearId").trim();
			String locationId=request.getParameter("locationId").trim();
			String monthId=request.getParameter("monthId").trim();
			String yearId=request.getParameter("yearId").trim();

			List<PayRollVo> list = new ArrayList<PayRollVo>();
			list = new StaffPayrollBD().getGeneratedPayRollList(accyear,locationId,monthId,yearId);

			Map parameters = new HashMap();
			parameters.put("Month", HelperClass.getMonthFullName(monthId));
			parameters.put("Year", yearId);

			String	sourceFileName = request.getRealPath("Reports/PayrollExcelReport.jrxml");
			
			File pdfxls = null;
			FileInputStream input=null;
			BufferedInputStream buf =null;
			ServletOutputStream stream = null;

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport, parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/PayrollExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = {"Payroll"};
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos); 
	        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
	        exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames); 
	        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE );
	        exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE );
	 
	        exporter.exportReport();
	        
	        pdfxls = new File(request.getRealPath("Reports/PayrollExcelReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename=\""+ HelperClass.getMonthFullName(monthId)+"-"+yearId+"PayrollReport.xls\"");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream= response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreatepayslipAction : createPayslip: Ending");
		return null;
	}
	
	public ActionForward downloadPayrollPdf(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in GenaratePayroll:  downloadPayrollExcel Starting");

		try {
			String accyear=request.getParameter("accyearId").trim();
			String locationId=request.getParameter("locationId").trim();
			String monthId=request.getParameter("monthId").trim();
			String yearId=request.getParameter("yearId").trim();

			List<PayRollVo> list = new ArrayList<PayRollVo>();
			list = new StaffPayrollBD().getGeneratedPayRollList(accyear,locationId,monthId,yearId);

			Map parameters = new HashMap();
			parameters.put("Month", HelperClass.getMonthFullName(monthId));
			parameters.put("Year", yearId);

			String	sourceFileName = request.getRealPath("Reports/PayrollPdfReport.jrxml");
			
			File pdfxls = null;
			FileInputStream input=null;
			BufferedInputStream buf =null;
			ServletOutputStream stream = null;

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""+ HelperClass.getMonthFullName(monthId)+"-"+yearId+"PayrollReport.pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
			outstream.close();

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CreatepayslipAction : createPayslip: Ending");
		return null;
	}
	
	public ActionForward assignmentdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : assignmentdetails Starting");
		try {
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = request.getParameter("location").trim();
			String classname = request.getParameter("class").trim();
			String division = request.getParameter("division").trim();
			
			if(classname.equalsIgnoreCase("all")) {
				classname = "%%";
			}

			if(division.equalsIgnoreCase("all")) {
				division = "%%";
			}
			
			list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : assignmentdetails Ending");

		return null;
	}
	
	public ActionForward projectAssign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : projectAssign Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADPROJECT);
			String userId = HelperClass.getCurrentUserID(request);
			
			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm == null) {

				searchTerm = "";
			}
			request.setAttribute("searchname", searchTerm);
			
			
			String accYearString = request.getSession(false).getAttribute("current_academicYear").toString();
			ArrayList<ProjectVO> list=new ArrayList<ProjectVO>();
			list=new AssignmentUploadBD().getProjectList(userId, accYearString,searchTerm);

			request.setAttribute("ProjectList",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : projectAssign Ending");

		return mapping.findForward(MessageConstants.ASSIGNPROJECT);
	}
	
	public ActionForward addProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : addProject Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADPROJECT);
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = request.getParameter("location");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
	

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : addProject Ending");

		return mapping.findForward(MessageConstants.ADDPROJECT);
	}
	
	public ActionForward personalDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TeacherRegistrationAction: getTeacherDetails Starting");

	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STAFF_PERSONALDETAILS);
		String academicYear = HelperClass.getCurrentYearID();
		String teacherid = HelperClass.getCurrentUserID(request);

		TeacherRegistrationPojo obj = new TeacherRegistrationPojo();
		obj.setTeacherId(teacherid);

		obj = new TeacherRegistrationBD().getTeacherDetails(obj);

		request.setAttribute("RolePermission",new TeacherDaoImpl().getRoles());
		request.setAttribute("reportingToList", new TeacherRegistrationBD().reportingToList());
		request.setAttribute("StudentAdmissionNumber", new TeacherRegistrationBD().StudentAdmissionNumber(academicYear));

		request.setAttribute("teacherdetails", obj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TeacherRegistrationAction : getTeacherDetails Ending");
	return mapping.findForward("personalDetails");
	}

}





