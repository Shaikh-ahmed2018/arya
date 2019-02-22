package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.OnlineFeePaymentDaoImpl;
import com.centris.campus.delegate.OnlineFeePaymentDelegate;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.delegate.ParentLeaveRequestBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TeacherLeaveModuleBD;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.IFAESUtil;
import com.centris.campus.util.IFHMACUtil;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.sha1;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.OnlinePaymentVo;
import com.centris.campus.vo.ParentFeedbackVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.feeReportVO;

import sun.misc.BASE64Encoder;

public class ParentsMenuAction extends DispatchAction {
	
	private static final Logger logger = Logger.getLogger(ParentsMenuAction.class);

	
	
	
	public ActionForward Home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : Home Starting");
		String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
		String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYear));
		String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYear));
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		
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
				+ " Control in ParentsMenuAction : Home Ending");

		return mapping.findForward(MessageConstants.parentLogin);
	}
	
	
	
	public ActionForward studentinformation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : studentinformation Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);

			studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
			
			request.setAttribute("studentdetails", studentinfo);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("studentexam", studentid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : studentinformation Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
	}
	
	public ActionForward getnextchildInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : getnextchildInfo Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		
		
		try {
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			
			
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
			
		
			
			
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			
			
			studentinfo.setStudentId(studentid);
			studentinfo.setHiddenid(hiddenid);
			
			//studentinfo = new ParentExamdetailsBD().getStudentInfoBD1(vo);
			
			
			studentinfo = new ParentExamdetailsBD()
			.getnextStudentInfoBD(studentinfo);
			
			
			
			
			request.setAttribute("studentdetails", studentinfo);
			
			
			
			
			
			request.setAttribute("studentexam", studentid);
			
			request.setAttribute("studentlist", student);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : getnextchildInfo Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
		
		
		
	}
	
	public ActionForward attendancedetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : attendancedetails Starting");
		
		String academicYearCode=HelperClass.getCurrentAcadamicYear();
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);

			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
				
			request.setAttribute("studentlist", student);
			request.setAttribute("attnstudentid", studentid);
			request.setAttribute("parenthiddenid", vo);
			
			String month = null;
			String year = null;
			ArrayList<StudentAttendanceVo> list = new ParentExamdetailsBD().getAttendanceMonthList(year,month,vo);
			
			
			for(int i=1; i<list.size(); i++)
				
			{
				System.out.println("Month Details"+list.get(i).getMonth());
			}
			
			request.setAttribute("attendancelist",list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : attendancedetails Ending");
		return mapping.findForward(MessageConstants.attendancedetails);
		
		
	}
	
	public ActionForward getNextChildAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getNextChildAttendance Starting");
		
		String academicYearCode=HelperClass.getCurrentAcadamicYear();
		
		try {
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			StudentAttendanceVo studvo = new StudentAttendanceVo();
			
			String studentid = request.getParameter("studentid");
			String parentid = request.getParameter("parentid");
			
			studvo.setStudentid(studentid);
			studvo.setParentid(parentid);
			studvo.setAccYear(accyear);
			
			String month = null;
			String year = null;
			
			ParentVO vo = new ParentVO();
			vo.setParentID(parentid);
			vo.setAccyear(accyear);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			ArrayList<StudentAttendanceVo> attnlist = new ParentExamdetailsBD().getNextChildAttendanceMonthList(year, month,studvo);
			
			request.setAttribute("hiddenstudentid", studentid);
			request.setAttribute("attendancelist", attnlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid",vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getNextChildAttendance Ending");
		
		return mapping.findForward(MessageConstants.attendancedetails);
	}
	
	public ActionForward getAttendanceView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceView Starting");
		
		String academicYearCode=HelperClass.getCurrentAcadamicYear();
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			
		/*	String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);*/
			
			String studentid = request.getParameter("studentid");
			String monthid = request.getParameter("monthid");
			String currentyearid = request.getParameter("currentyearid");
			String studentid1 = request.getParameter("studentid1");
			
			StudentAttendanceVo attvo = new StudentAttendanceVo();
		
			attvo.setStudentid(studentid);
			attvo.setMonth(monthid);
			attvo.setYear(currentyearid);
			attvo.setStudentid1(studentid1);
			attvo.setAccYear(academicYearCode);
			
			if(studentid != null){
				ArrayList<StudentAttendanceVo> attendancelist = new ParentExamdetailsBD().getAttendanceDayList(attvo);
				request.setAttribute("attendancelist", attendancelist);
			}
			
			
			if(studentid1 != null){
				ArrayList<StudentAttendanceVo> attendancelist1 = new ParentExamdetailsBD().getmoreAttendanceDayList(attvo);
				request.setAttribute("attendancelist1", attendancelist1);
			}
			
			request.setAttribute("studentlist", student);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : getAttendanceView Ending");
		
		return mapping.findForward(MessageConstants.attendanceview);
		
	}
	public ActionForward assignmentdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : assignmentdetails Starting");
		
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
					
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			
			ArrayList<AssignmentViewVO> assignmentlist = new ParentExamdetailsBD().getAssignmentListBD(vo);
			
			request.setAttribute("studentlist", student);
			request.setAttribute("accyearid",accyear);
			request.setAttribute("assignmentlist",assignmentlist);
			request.setAttribute("parenthiddenid",vo);
			request.setAttribute("assstudentid",studentid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : assignmentdetails Ending");
		return mapping.findForward(MessageConstants.assignmentdetails);
	}
	
	
	public ActionForward viewAssignmentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : viewAssignmentDetails Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String studentid = request.getParameter("studentid");
			String assgnmentid = request.getParameter("assgnmentid");
			String accyear = request.getParameter("hiddenaccyear");
			AssignmentViewVO assvo = new AssignmentViewVO();
			assvo.setStudentid(studentid);
			assvo.setAssignmentid(assgnmentid);
			assvo.setAccid(accyear);
			
			AssignmentViewVO result = new ParentExamdetailsBD().getviewAssignmentBD(assvo);
			
			request.setAttribute("assdetails", result);
			request.setAttribute("studentid", studentid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : viewAssignmentDetails+" +
				"- Ending");
		return mapping.findForward(MessageConstants.viewassignment);
	}
	
	public ActionForward getmoreassimentlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmoreassimentlist Starting");
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			AssignmentViewVO assvo = new AssignmentViewVO();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
			
		//	String assgnmentid = request.getParameter("assgnmentid");
		
			assvo.setStudentid(studentid);
			assvo.setHiddenid(hiddenid);
			assvo.setAccid(accyear);
			//assvo.setAssignmentid(assgnmentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			ArrayList<AssignmentViewVO> assignmentlist = new ParentExamdetailsBD().getmoreAssidnmentListBD(assvo);
			
			request.setAttribute("studentexam", studentid);
			request.setAttribute("assignmentlist", assignmentlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("assstudentid", studentid);
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("accyearid",accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmoreassimentlist Ending");
		return mapping.findForward(MessageConstants.assignmentdetails);
	}
	
	
	public ActionForward syllabusdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusdetails Starting");
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			
			vo.setStudentid(student.get(0).getStdAdmisiionNo());
			vo.setClassDetailId(student.get(0).getClassDetailId());
			vo.setLocid(student.get(0).getLocid());
			
			//List<StreamDetailsVO> streamvo =new ParentExamdetailsBD().getStreamListDetails();
			//request.setAttribute("streamlist", streamvo);
			request.setAttribute("stuList",student);
			request.setAttribute("studentData",vo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusdetails Ending");
		
		
		return mapping.findForward(MessageConstants.syllabusdetails);
		
		
		
	}

	
	public ActionForward sendfeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Starting");
		
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentFeedbackVo fbvo = new ParentFeedbackVo();
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);	
			request.setAttribute("studentlist", student);
			
			
			ArrayList<ParentFeedbackVo> fdlist = new ParentExamdetailsBD()
			.getFeedBackDetailsBD(fbvo);
			
			
			
			
			
			request.setAttribute("parentid", vo);
			request.setAttribute("feedbacklist", fdlist);
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Ending");
		
		
		return mapping.findForward(MessageConstants.sendfeedback);
		
	}
	

	/*

	
	
	public ActionForward getsyllabus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Starting");
		
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Ending");
		
		
		
		return mapping.findForward(MessageConstants.getsyllabus);
		
	}
	*/


	public ActionForward examdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examdetails Starting");
		
		
		try {
			
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			vo.setLocid(student.get(0).getLocid());
			vo.setClassDetailId( student.get(0).getClassDetailId());
			vo.setSectionid( student.get(0).getSectionid());
			
			ArrayList<ExaminationDetailsVo> examlist = new ParentExamdetailsBD().getExamListDetails(vo);
			
			request.setAttribute("examlist", examlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examdetails Ending");
		
		return mapping.findForward(MessageConstants.examdetails);
	}
	
	public ActionForward getexamlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getexamlist Starting");
		try {
			
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ExaminationDetailsVo exmvo = new ExaminationDetailsVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
		
			exmvo.setStudentid(studentid);
			exmvo.setHiddenid(hiddenid);
		
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			exmvo.setLocationid(student.get(0).getLocid());
			exmvo.setClassId( student.get(0).getClassDetailId());
			exmvo.setSectionid( student.get(0).getSectionid());
			exmvo.setAccyear(accyear);
			ArrayList<ExaminationDetailsVo> examslist = new ParentExamdetailsBD().getMoreChildExamListDetails(exmvo);
			
			request.setAttribute("studentexam", studentid);
			request.setAttribute("examlist", examslist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getexamlist Ending");
		
		return mapping.findForward(MessageConstants.examdetails);
	}
	
	
	public ActionForward getClassDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassDetails Starting");
		
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ClassPojo pojo = new ClassPojo();
			String streamname=request.getParameter("streamname");
			
			pojo.setStreamId(streamname);
			
			List<ClassPojo> classpojo =new ParentExamdetailsBD().getClassListDetails(pojo);

			JSONObject object=new JSONObject();
			 
			 object.put("classlist", classpojo);
			 
			 response.getWriter().print(object);
			 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassDetails Ending");
		
		return null;
	}
	
	public ActionForward getSubjectDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSubjectDetails Starting");
		
		try {
			ClassPojo pojo = new ClassPojo();
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ViewallSubjectsVo subvo = new ViewallSubjectsVo();
			
			String classid=request.getParameter("classid");
			
			subvo.setClassid(classid);
			subvo.setLocationId(request.getParameter("locid"));
			List<ViewallSubjectsVo> subjectlist =new ParentExamdetailsBD().getSubjectDetails(subvo);
			//List<StreamDetailsVO> streamvo =new ParentExamdetailsBD().getStreamListDetails();
			//List<ClassPojo> classpojo =new ParentExamdetailsBD().getClassListDetails(pojo);

			request.setAttribute("classid", classid);
			request.setAttribute("subjectid", subvo);
			request.setAttribute("subjectlist", subjectlist);
		
			JSONObject obj =new JSONObject();
			obj.accumulate("dataList",subjectlist);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getSubjectDetails Ending");
		
		///return mapping.findForward(MessageConstants.syllabusdetails);
		return null;
	}
	
	
	
	public ActionForward downloadSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadSubject Starting");
		
		
		try {
			
			String subid = request.getParameter("subjectid");
			
			String filepath = new ParentExamdetailsBD().getsubjectfilepath(subid);
			
			
			
			String fileName = "FileName";
			fileName=filepath;
			
			
			
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + filepath);
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
				+ " Control in AdminMenuAction : downloadSubject Ending");
		
		
		return null;
			
	}
	
	
	public ActionForward downloadFeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadFeedback Starting");
		
		try {
			
			String fbid = request.getParameter("feedbackId");
			
			
			
			String filepath = new ParentExamdetailsBD().getfeedbackfilepath(fbid);
			
			
			
			String fileName = "FileName";
			fileName=filepath;
			
			
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + filepath);
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
				+ " Control in AdminMenuAction : downloadFeedback Ending");
		
		
		return null;
	
	}
	
	
	public ActionForward downloadTCfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadTCfile Starting");
		
		try {
			String docPath = request.getParameter("Path");
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
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
				+ " Control in AdminMenuAction : downloadTCfile Ending");
		
		return null;
			
	}
	
	
	
	public ActionForward meetingandeventsdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingandeventsdetails Starting");
		
		try {
			
			

			
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);	
			request.setAttribute("studentlist", student);
		
	List<LstmsUpcomingMeetingVO> meetinglist =new ParentExamdetailsBD().getMeetingListDetails();
	
	
	
			
			request.setAttribute("meetinglist", meetinglist);
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingandeventsdetails Ending");
		
		return mapping.findForward(MessageConstants.getmeetingandevent);
		
	}
	
	
	
	public ActionForward getstudentmeetinglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentmeetinglist Starting");
		
		try {
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);	
			request.setAttribute("studentlist", student);
		   
			LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
			
			String studentid=request.getParameter("studentid");
			String parentid1=request.getParameter("hiddenid");
			
			
			meetvo.setStudentid(studentid);
			meetvo.setParentid(parentid1);
			
			List<LstmsUpcomingMeetingVO> meetinglist =new ParentExamdetailsBD().getstudentmeetinglistBD(meetvo);	
		
			request.setAttribute("studentmeeting", studentid);
			request.setAttribute("meetinglist", meetinglist);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentmeetinglist Ending");
		
		return mapping.findForward(MessageConstants.getmeetingandevent);
		
	}
	
	public ActionForward studentTimeTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : studentTimeTable Starting");
		
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			//String accyearid= HelperClass.getAcademicYear();
			
	      	vo.setParentID(userid);
			vo.setAccyear(accyear);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);	
			request.setAttribute("studentlist", student);
			request.setAttribute("classid", student.get(0).getClassDetailId());
			request.setAttribute("secid", student.get(0).getSectionid());
			request.setAttribute("locid", student.get(0).getLocid());
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	    
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : studentTimeTable Ending");
		
		return mapping.findForward(MessageConstants.studentTimeTable);

	
	}
	
	
	public ActionForward getStudentTimetable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentTimetable Starting");
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		
			ParentVO vom = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vom.setParentID(userid);
			vom.setAccyear(accyear);
			vom.setStudentid(request.getParameter("studentid"));
			vom.setClassDetailId(request.getParameter("classid"));
			vom.setSectionid(request.getParameter("secid"));
			vom.setLocid(request.getParameter("locid"));
			
			ArrayList<TimeTableVo> timeTableDetails= new ParentExamdetailsBD().getFirstStudentTimeTableBD(vom);
			
			
			
			List<TimeTableVo> todaytimeTableDetailsList=new ArrayList<TimeTableVo>();
			for(int i=0;i<timeTableDetails.size();i++){
				TimeTableVo vo=new TimeTableVo();
				vo.setDayid(timeTableDetails.get(i).getDayid().toUpperCase());
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
				todaytimeTableDetailsList.add(vo);
				 Collections.sort(todaytimeTableDetailsList,HelperClass.TimeTableVoComparator);
				
			}
			request.setAttribute("timeTableDetails", todaytimeTableDetailsList);
		    
			JSONObject obj = new JSONObject();
			obj.put("List", todaytimeTableDetailsList);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentTimetable Ending");
		return null;
	}
	
	
	public ActionForward requestLeavescreenadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Starting");
		
		
		try {
		
				
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();
			
			
			ParentVO vo = new ParentVO();
			/*String parentid = HelperClass.getCurrentUserID(request);*/
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			request.setAttribute("studentlist", student);
			
			request.setAttribute("parentid", vo);
			
			/*UserDetailVO vo = new UserDetailVO();
			
			String userid = request.getParameter("hiddenid");
			vo.setUserId(userid);*/
			
		
			
         List<UserDetailVO> userlist =new ParentExamdetailsBD().getRequestUserListDetails(parentid);
			

         request.setAttribute("userlist", userlist);
          
          
          
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Ending");

		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
		
	}
	
	
	
	public ActionForward leaveRequestEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
			LeaveRequestForm leaveform =(LeaveRequestForm)form;
			LeaveRequestVo leavevo = new LeaveRequestVo();
			String parentid = HelperClass.getCurrentUserID(request);
			leaveform.setCreateuser(parentid);
			
			/*UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();*/
			
			/*leaveform.setCreateuser(HelperClass.getCurrentUserID(request));*/
			
			ParentVO vo = new ParentVO();
			
			vo.setParentID(parentid);
			
		/*	leaveform.setCreateuser(parentid);*/
		/*	
			String createuser=HelperClass.getCurrentUserID(request);*/
			
			
			
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
		   List<UserDetailVO> userlist =new ParentExamdetailsBD().getRequestUserListDetails(HelperClass.getCurrentUserID(request));
			

           request.setAttribute("userlist", userlist);
			
           request.setAttribute("studentlist", student);
			
			request.setAttribute("parentid", vo);
			
			
		
			
		/*	
		    leaveform.setCreateuser(HelperClass.getCurrentUserID(request));
			
			
         	ParentVO vo = new ParentVO();
			//String parentid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			leaveform.setStudentid(studentid);*/
         
			
			
			
			
			
			FormFile formFile = leaveform.getFileupload();
			
			
			if(formFile.getFileSize()>0){
				
				path = getServlet().getServletContext().getRealPath("/")+ "FIles\\LEAVEREQUEST";
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
				
				String fileName = HelperClass.getCurrentSqlDate() + "_"	+ formFile ;
				fileURL = new File(path, fileName);
	
				fos = new FileOutputStream(fileURL);
				fos.write(formFile.getFileData());
	
				String file1 = fileURL.getPath();
	
				file1 = file1.substring(file1.indexOf("FIles\\"));
	
				if (!fileURL.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(fileURL);
	
					fileURL.mkdir();
					fileOutStream.write(formFile.getFileData());
	
					fileOutStream.flush();
	
					fileOutStream.close();
				}
				
				leavevo.setFileupload(file1);
				
				
			}
			else{
				
				leavevo.setFileupload(leaveform.getFileupload1());
				
			
				
			}
			
			
			leavevo.setRequestto(leaveform.getRequestto());
			leavevo.setTotalleave(leaveform.getTotalleave());
			leavevo.setFromdate(HelperClass.convertUIToDatabase(leaveform.getFromdate()));
			leavevo.setTodate(HelperClass.convertUIToDatabase(leaveform.getTodate()));
			leavevo.setStarttime(leaveform.getStarttime());
			leavevo.setEndtime(leaveform.getEndtime());
			leavevo.setLeavetype(leaveform.getLeavetype());
			leavevo.setReason(leaveform.getReason());
			leavevo.setCreateuser(leaveform.getCreateuser());
			leavevo.setStudentFname(leaveform.getStudentFname());
			
			leavevo.setSno(leaveform.getSno());
			
			
		
			
			String result =  new ParentLeaveRequestBD()
			.leaveRequestEntryBD(leavevo);
			
			
			
			/*  List<UserDetailVO> userlist =new TeacherLeaveModuleBD().getRequestUserListDetails(HelperClass.getCurrentUserID(request));
		         request.setAttribute("userlist", userlist);*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequestEntry Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
	}
	
	
	
	public ActionForward editRequestLeaveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Starting");
		
		try {
			
			
			String parentid = HelperClass.getCurrentUserID(request);
			ParentVO vo = new ParentVO();
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			  request.setAttribute("studentlist", student);
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String user = userDetailVO.getUserId();
			
		
         List<UserDetailVO> userlist =new TeacherLeaveModuleBD().getRequestUserListDetails(user);
			

         request.setAttribute("userlist", userlist);
		
			String sno =(String) request.getParameter("snoid");
		
			LeaveRequestVo result = new ParentLeaveRequestBD().getRequestLeaveBD(Integer.parseInt(sno));
			
			
			request.setAttribute("leavedatails", result);
		
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
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
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			
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
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadfile Ending");
		return null;
	}
		
		
		
	public ActionForward circularRemainderParentAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : circularRemainderParentAction Starting");
		
		
		try {
			

			
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT); 
			
	      List<RemainderMasterVO> remainderlist =new ParentLeaveRequestBD().getRemainderlistBD();	
			
			
			
			request.setAttribute("remainderlist", remainderlist);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : circularRemainderParentAction Ending");
		
		
		
		return mapping.findForward(MessageConstants.parentRemainder);
	}
	
	public ActionForward studentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		try {
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			System.out.println(student);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			
			studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
			
			request.setAttribute("studentdetails", studentinfo);
			request.setAttribute("studentlist",student);
		
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("studentexam", studentid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return mapping.findForward(MessageConstants.PARENT_STUDENT_LIST);
	}
	
	
	public ActionForward onlinefeePayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		try {
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userType=userDetailVO.getUserNametype();
			String userCode=userDetailVO.getUserId();
			System.out.println("userType:::"+userType);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_PARENT_ONLINE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TEACHERS);
			request.setAttribute("bankdetails", HelperClass.getBankDetails());
			List<OnlinePaymentVo> List = new OnlineFeePaymentDelegate().getStudentOnlineFeePaymentDetails(userType,userCode);
			request.setAttribute("OnlineFeePayment", List);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return mapping.findForward(MessageConstants.PARENT_ONLINE_PAYMENT);
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

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usertype=userDetailVO.getRoleCode();
			System.out.println(usertype);
			String empId=userDetailVO.getUserId();
			System.out.println("Leave::::"+empId);
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();

			ParentExamdetailsBD leavedeligate = new ParentExamdetailsBD();

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			String parentid = HelperClass.getCurrentUserID(request);

			request.setAttribute("parentid", parentid);

			

			LeaveRequestVo leavevo = new LeaveRequestVo();

			if (searchTerm != null) {
				

				leavelist = leavedeligate.searchleaverequest(parentid,searchTerm);

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
	
	public ActionForward viewExamdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Starting");
		try{
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			String examid = request.getParameter("examid");
			String classid= request.getParameter("classid");
			String sectionid = request.getParameter("secid");
			String locid= request.getParameter("locid");
			
			ParentVO obj = new ParentVO();
			obj.setAccyear(accyear);
			obj.setLocid(locid);
			obj.setExamid(examid);
			obj.setSectionid(sectionid);
			obj.setClassDetailId(classid);
			request.setAttribute("dataList",obj);
			
			obj = new ParentExamdetailsBD().getexamname(obj);
			request.setAttribute("examlist", obj);
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Ending");
		return mapping.findForward(MessageConstants.VIEWEXAMDETAILS);
	}
	public ActionForward displayExamdetail(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : viewExamdetails Starting");
				try{
					String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
					String examid = request.getParameter("examid");
					String classid= request.getParameter("classid");
					String sectionid = request.getParameter("secid");
					String locid= request.getParameter("locid");
					
					ParentVO obj = new ParentVO();
					obj.setAccyear(accyear);
					obj.setLocid(locid);
					obj.setExamid(examid);
					obj.setSectionid(sectionid);
					obj.setClassDetailId(classid);
					
					ArrayList<ParentVO> list = new ArrayList<ParentVO>();
					list = new ParentExamdetailsBD().viewExamdetails(obj);
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("dataList",list);
					response.getWriter().print(jsonobj);
					
				}catch(Exception e){
					e.printStackTrace();
				}

				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : viewExamdetails Ending");
				return null;
			}
	
	public ActionForward getStudentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Starting");
		try{
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			String stuid = request.getParameter("studid");
			
			ParentVO obj =new ParentVO();
			obj.setAccyear(accyear);
			obj.setStudentid(stuid);
			
			obj = new ParentExamdetailsBD().getStudentDetails(obj);
			
			JSONObject json = new JSONObject();
			json.put("details", obj.getClassDetailId()+" "+obj.getLocid()+" "+obj.getSectionid());
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Ending");
		return null;
	}
	
	public ActionForward getStuDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStuDetails Starting");
		
		String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
		StudentRegistrationVo studentinfo = new StudentRegistrationVo();
		ParentVO vo = new ParentVO();
		String userid = HelperClass.getCurrentUserID(request);
		vo.setParentID(userid);
		vo.setAccyear(accyear);
		ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
		
		String studentid = student.get(0).getStdAdmisiionNo();
		vo.setStudentid(studentid);
		
		vo.setStudentid(request.getParameter("studentid"));
		vo.setAccyear(accyear);
		studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
		request.setAttribute("studentdetails", studentinfo);
		request.setAttribute("studentlist", student);
		request.setAttribute("studentid", request.getParameter("studentid"));
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStuDetails Ending");
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
	}
	
	
	public ActionForward onlinefeetransactionId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse hresponse)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Starting");
		try{
			String studentId = request.getParameter("studentId");
			String feeCode= request.getParameter("feeCode");
			String termcode=request.getParameter("termcode");
			String bank=request.getParameter("bank");
			String grandtotal=request.getParameter("grandtotal");
			String fineAmt=request.getParameter("fineAmt");
			String bankcode=request.getParameter("bankcode");
			
			
			request.getSession(false).setAttribute("hstudentId", studentId);
			request.getSession(false).setAttribute("hfeeCode", feeCode);
			request.getSession(false).setAttribute("htermcode", termcode);
			request.getSession(false).setAttribute("hbank",bank);
			request.getSession(false).setAttribute("hgrandtotal", grandtotal);
			request.getSession(false).setAttribute("hfineAmt", fineAmt);
			request.getSession(false).setAttribute("hbankcode", bankcode);
			String tranID="";
			try {
				tranID = IDGenerator.getPrimaryKeyID("online_transactionid_table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("transactionId", tranID);
		if(bankcode.equalsIgnoreCase("IOB")) {	
			IFAESUtil aesutil=new IFAESUtil();
			IFHMACUtil hmacutil=new IFHMACUtil();
			
			//variable initialisations
			String encryptionkey="DCC4F202B207AD247437087C2D8A8247";
			String encryptioniv="JXEqSJJUa8P7QA2K";
			String signkey="HBsORxEEiO6UPsmjvye8DMd2OgUgraiq";
			String merchantid="APIMER";
			String merchantsubid="ACSTRI";
			String tokenaction="GENTOK";
			String txninitaction="TXNINIT";
			String feetype="ALL FEES";
			String totalamt="1.0";
			String replyurl="http://202.88.237.254:6789/ARYA_CENTRAL_SCHOOL/parentMenu.html?method=onlinefeeReceipt"; // reply url of merchant where txn init response has to be posted
			String iobpayapiurl="https://www.iobnet.co.in:8080/iobpay/iobpayRESTService/apitokenservice/generatenewtoken/";
			String iobpaytxniniturl="https://www.iobnet.co.in:8080/iobpay/apitxninit.do";
			String merchanttxnid=tranID;// UNIQUE ID from merchant end
			
			//generating a new token
			JSONObject tokenjsonobj=new JSONObject();
			tokenjsonobj.put("merchantid",merchantid);
			tokenjsonobj.put("merchantsubid",merchantsubid);
			tokenjsonobj.put("action",tokenaction);
			tokenjsonobj.put("feetype",feetype);
			tokenjsonobj.put("totalamt",totalamt);
			tokenjsonobj.put("replyurl",replyurl);
			
			System.out.println("Token Generation : Before Encryption : "+tokenjsonobj.toString());
			String encryptedtokendata=aesutil.encrypt(tokenjsonobj.toString(), encryptioniv, encryptionkey);
			String signedtokenhmac=hmacutil.HmacSHA256(encryptedtokendata, signkey);
			JSONObject tokenrequestjson=new JSONObject();
			tokenrequestjson.put("merchantid", merchantid);
			tokenrequestjson.put("merchantsubid", merchantsubid);
			tokenrequestjson.put("action", tokenaction);
			tokenrequestjson.put("data", encryptedtokendata);
			tokenrequestjson.put("hmac", signedtokenhmac);
			System.out.println("Token Generation : Request Message : "+tokenrequestjson.toString());
			
			URL obj = new URL(iobpayapiurl);
			URLConnection con = (URLConnection) obj.openConnection();
			con.setReadTimeout(300000);
			con.setConnectTimeout(300000);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(tokenrequestjson.toString());
			wr.flush();
			wr.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();
			System.out.println("response from token service : "+response.toString());
			
			JSONObject tokenjsonobjresponse=new JSONObject(response.toString());
			String decryptedtokendata=aesutil.decrypt(tokenjsonobjresponse.getString("data"), encryptioniv, encryptionkey);
			System.out.println("decrypted data from token service : "+decryptedtokendata);
			
			//getting tokenJSON from decryptedtokendata
			JSONObject tokenresponsejson=new JSONObject(decryptedtokendata);
			
			//generating request for txninit service
			JSONObject txninitjson=new JSONObject();
			txninitjson.put("merchantid", merchantid);
			txninitjson.put("merchantsubid", merchantsubid);
			txninitjson.put("action", txninitaction);
			txninitjson.put("feetype", feetype);
			txninitjson.put("totalamt", totalamt);
			txninitjson.put("tokenid",tokenresponsejson.get("tokenid"));
			txninitjson.put("merchanttxnid", merchanttxnid);
			txninitjson.put("udf1", "udf1test");
			txninitjson.put("udf2", "udf2test");
			txninitjson.put("udf3", "udf3test");
			
			//generate txninitrequestdataexchangeformat
			System.out.println("Txn Init : Before Encryption : "+txninitjson.toString());
			String encryptedtxninitreqdata=aesutil.encrypt(txninitjson.toString(), encryptioniv, encryptionkey);
			String signedtxninitreqhmac=hmacutil.HmacSHA256(encryptedtxninitreqdata, signkey);
			JSONObject txninitequestjson=new JSONObject();
			txninitequestjson.put("merchantid", merchantid);
			txninitequestjson.put("merchantsubid", merchantsubid);
			txninitequestjson.put("action", txninitaction);
			txninitequestjson.put("data", encryptedtxninitreqdata);
			txninitequestjson.put("hmac", signedtxninitreqhmac);
			
			System.out.println("Txn Init : after Encryption : "+txninitequestjson.toString());
			
			String tokenIdFor=(String) tokenresponsejson.get("tokenid");
			String status = new OnlineFeePaymentDaoImpl().onlinefeetransactionId(studentId,feeCode,termcode,bank,tranID,grandtotal,tokenIdFor,fineAmt);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status",status);
			
			request.setAttribute("redirecturl", iobpaytxniniturl);
			request.setAttribute("requestjson", txninitequestjson.toString());
			jsonobj.put("redirecturl",iobpaytxniniturl);
			jsonobj.put("requestjson", txninitequestjson.toString());
			hresponse.getWriter().print(jsonobj);
		}
		
		else if(bankcode.equalsIgnoreCase("KVB")) {
			
			Date date = new Date();
		    String strDateFormat ="dd/MM/yyyy hh:mm:ss a";
		    String tDateFormat ="dd/MM/yyyy";
		    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		    DateFormat dateformat1=new SimpleDateFormat(tDateFormat);
		    String formattedDate1= dateformat1.format(date);
		    String formattedDate= dateFormat.format(date);
		    System.out.println("Current time of the day using Date - 12 hour format: " +formattedDate);
		    JSONObject jsonobj = new JSONObject();
		
		    jsonobj.put("fldTxnId", "PUR");
		    jsonobj.put("fldClientCode", "101");
		    jsonobj.put("fldClientAccount", "");
		    jsonobj.put("fldMerchCode", "ARYASCHOOL");
		    jsonobj.put("fldTxnCurr", "INR");
		    jsonobj.put("fldTxnAmt", "10.0");
		    jsonobj.put("fldTxnScAmt", "0");
		    jsonobj.put("fldMerchRefNbr", tranID);
			jsonobj.put("fldSucStatFlg", "N");
			jsonobj.put("fldFailStatFlg", "N");
			jsonobj.put("fldDatTimeTxn", formattedDate);
			jsonobj.put("fldRef1", "1");
			jsonobj.put("fldRef2", "2");
			jsonobj.put("fldRef3", "3");
			jsonobj.put("fldRef4", "4");
			jsonobj.put("fldRef5", "5");
			jsonobj.put("fldRef6", "6");
			jsonobj.put("fldRef7", "7");
			jsonobj.put("fldRef8", "8");
			jsonobj.put("fldRef9", "9");
			jsonobj.put("fldRef10", "10");
			jsonobj.put("fldRef11", "11");
			jsonobj.put("fldDate1", "");
			jsonobj.put("fldDate2", "");
			jsonobj.put("fldOrgDatTimeTxn", formattedDate1);
			jsonobj.put("redirecturl", "http://14.142.112.23/GeneralMerchant/GeneralMRequest");
			
			jsonobj.put("status","KVB");

			
		}
else if(bankcode.equalsIgnoreCase("FDB")) {
			
		    JSONObject jsonobj = new JSONObject();
		    jsonobj.put("user_code", "ARCST");
		    jsonobj.put("pass_code", "ARCS1234#");
		    jsonobj.put("tran_id", tranID);
			jsonobj.put("amount", "100");
			jsonobj.put("charge_code", "A");
			jsonobj.put("hash_value", sha1.FDBSHAHASING("ARCST|ARCS1234#|"+tranID+"|100|A|A44R7893C989S78E"));
			jsonobj.put("redirecturl", "https://epay.federalbank.co.in/FedPaymentsV1/EasyPayments.ashx");
			
			jsonobj.put("status","FDB");
			hresponse.getWriter().print(jsonobj);
			
		} 
		
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Ending");
		return null;
	}
	
	
	public ActionForward onlinefeeReceipt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Starting");
		
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String txnstatus="";
		String userType=userDetailVO.getUserNametype();
		String userCode=userDetailVO.getUserId();
		String BankRefNo="";
		String tranID=(String) request.getSession(false).getAttribute("transactionId");
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_PARENT_ONLINE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TEACHERS);
		
		try{
			ArrayList<feeReportVO> feereceipt=new ArrayList<feeReportVO>();
			ArrayList<feeReportVO> tfeereceipt=new ArrayList<feeReportVO>();
			
			
			String studentId=(String) request.getSession(false).getAttribute("hstudentId");
			String feeCode=(String) request.getSession(false).getAttribute("hfeeCode");
			String termcode=(String) request.getSession(false).getAttribute("htermcode");
			String bank=(String) request.getSession(false).getAttribute("hbank");
			String grandtotal=(String) request.getSession(false).getAttribute("hgrandtotal");
			String fineAmt=(String) request.getSession(false).getAttribute("hfineAmt");
			
			String  bankcode=(String) request.getSession(false).getAttribute("hbankcode");
			
			if(bankcode.equalsIgnoreCase("KVB")) {
				
				 BankRefNo=request.getParameter("BankRefNo");
				
				String inserrt = new OnlineFeePaymentDaoImpl().onlinefeetransactionId(studentId,feeCode,termcode,bank,tranID,grandtotal,BankRefNo,fineAmt);
			}
			
		
			
			String tokenid=new OnlineFeePaymentDaoImpl().getonlinefeetokenId(tranID);
			if(tokenid.equalsIgnoreCase("false")) {
				txnstatus="RELOAD";
			}
			else {
				
				if(bankcode.equalsIgnoreCase("KVB")) {
					txnstatus=request.getParameter("Message");
					System.out.println("I M IN KVB txstatus="+txnstatus);
					
					String update=new OnlineFeePaymentDaoImpl().updateOnlineTable(BankRefNo,txnstatus,BankRefNo);
				}
				else {
					 txnstatus=testtxnstatusservice(tokenid);
				}
			
			if(txnstatus.equalsIgnoreCase("SUCCESS")) {
				List<FeeCollectionVo> status = new OnlineFeePaymentDaoImpl().saveonlinefeetransactionId(tranID,userCode);
				ParentVO stovo = new ParentVO();
				stovo.setParentID(userCode);
				stovo.setAccyear(HelperClass.getCurrentYearID());
				
				ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(stovo);
				
				String studentid = student.get(0).getStdAdmisiionNo();
				stovo.setStudentid(studentid);
				
				StudentRegistrationVo studentinfo = new ParentExamdetailsBD().getStudentInfoBD(stovo);
				System.out.println("STUDENT="+studentinfo.getStudentFirstName());
				request.setAttribute("studentInfo", studentinfo);
				for(int i=0;i<status.size();i++) {
					
					if(status.get(i).getStatus().equalsIgnoreCase("schoolfee")) {
						feeReportVO  vo=new feeReportVO();
						vo.setTermname(HelperClass.gettermName(status.get(i).getTermid()));
						vo.setFeeNameVo(new OnlineFeePaymentDaoImpl().getFeePaidDetails(status.get(i).getStudentid(),status.get(i).getTermid()));
						vo.setFine(status.get(i).getFineAmount());
						feereceipt.add(vo);
					}
					if(status.get(i).getStatus().equalsIgnoreCase("transport")) {
						feeReportVO  vo=new feeReportVO();
						vo.setTermname(HelperClass.getTransporttermName(status.get(i).getTermid()));
						vo.setTotalAmount(Double.parseDouble(new OnlineFeePaymentDaoImpl().transportFeepaid(status.get(i).getRefrecieptNo())));
						vo.setSno(1);
						vo.setStream("Transport Fee");
						tfeereceipt.add(vo);
					}
				}
			request.setAttribute("totalPaid",status.get(0).getTot_actual_amt());
			if(feereceipt.size()>0) {
				System.out.println("I Will Generate Receipt");
				request.setAttribute("SchoolFee",feereceipt);	
			}	
			if(tfeereceipt.size()>0) {
				request.setAttribute("TransportFee",tfeereceipt);	
			}	
				
			}
			
		}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Ending");
		return mapping.findForward(MessageConstants.ONLINE_FEE_RECEIPT);
	}
	public String testtxnstatusservice(String mytoken) 
	{
		String txnstatuus="";
		
		try
		{
			// class initialisations
			IFAESUtil aesutil=new IFAESUtil();
			IFHMACUtil hmacutil=new IFHMACUtil();
			
			//variable initialisations
			String encryptionkey="DCC4F202B207AD247437087C2D8A8247";
			String encryptioniv="JXEqSJJUa8P7QA2K";
			String signkey="HBsORxEEiO6UPsmjvye8DMd2OgUgraiq";
			String merchantid="APIMER";
			String merchantsubid="ACSTRI";
			String txnstatusaction="TXNSTATUS";
			String feetype="ALL FEES";
			String totalamt="1.0";
			
			String iobpaytxnstatusurl="https://www.iobnet.co.in:8080/iobpay/iobpayRESTService/apitxnstatusservice/gettxnstatus/";
			
			//generating a new token
			JSONObject txnstatusjsonobj=new JSONObject();
			txnstatusjsonobj.put("merchantid",merchantid);
			txnstatusjsonobj.put("merchantsubid",merchantsubid);
			txnstatusjsonobj.put("action",txnstatusaction);
			txnstatusjsonobj.put("feetype",feetype);
			txnstatusjsonobj.put("totalamt",totalamt);
			//txnstatusjsonobj.put("trackid","");
			txnstatusjsonobj.put("tokenid",mytoken);
			System.out.println("my===="+mytoken);
			System.out.println("Txn Status : Before Encryption : "+txnstatusjsonobj.toString());
			String encryptedtokendata=aesutil.encrypt(txnstatusjsonobj.toString(), encryptioniv, encryptionkey);
			String signedtokenhmac=hmacutil.HmacSHA256(encryptedtokendata, signkey);
			JSONObject txnstatusrequestjson=new JSONObject();
			txnstatusrequestjson.put("merchantid", merchantid);
			txnstatusrequestjson.put("merchantsubid", merchantsubid);
			txnstatusrequestjson.put("action", txnstatusaction);
			txnstatusrequestjson.put("data", encryptedtokendata);
			txnstatusrequestjson.put("hmac", signedtokenhmac);
			System.out.println("Txn Status : Request Message : "+txnstatusrequestjson.toString());
			
			URL obj = new URL(iobpaytxnstatusurl);
			URLConnection con = (URLConnection) obj.openConnection();
			con.setReadTimeout(300000);
			con.setConnectTimeout(300000);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(txnstatusrequestjson.toString());
			wr.flush();
			wr.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();
			System.out.println("response from Txn Status service : "+response.toString());
			
			JSONObject txnstatusjsonobjresponse=new JSONObject(response.toString());
			String decryptedtxnstatusdata=aesutil.decrypt(txnstatusjsonobjresponse.getString("data"), encryptioniv, encryptionkey);
			System.out.println("decrypted data from Txn Status service : "+decryptedtxnstatusdata);
			
			JSONObject decryptedtxnstatusdatajsonobjresponse=new JSONObject(decryptedtxnstatusdata);
			
			txnstatuus=(String) decryptedtxnstatusdatajsonobjresponse.get("txnstatus");
			String trackID=(String) decryptedtxnstatusdatajsonobjresponse.get("trackid");
			
			String update=new OnlineFeePaymentDaoImpl().updateOnlineTable(mytoken,txnstatuus,trackID);
			if(update.equalsIgnoreCase("false")) {
				txnstatuus="false";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return txnstatuus;
	}
    

}
