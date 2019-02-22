package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.delegate.AssignmentUploadBD;
import com.centris.campus.forms.AssignmentUploadForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ProjectVO;

public class AssignmentUploadAction extends DispatchAction {
	
	
private static Logger logger = Logger.getLogger(AssignmentUploadAction.class);
	
	public synchronized ActionForward getStudentDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : getStudentDetails Starting");
		try {
			
			String section=request.getParameter("section");
		
			JSONObject object=new JSONObject();
			object.put("StudentList", new AssignmentUploadBD().getStudentDetails(section));
			
			response.getWriter().print(object);;
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : getStudentDetails Ending");

		return null;
	}
	
	public synchronized ActionForward insertAssignment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : insertAssignment Starting");
		try {
			
			String userId=HelperClass.getCurrentUserID(request);
			String accYearString=request.getSession(false).getAttribute("current_academicYear").toString();
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			
			AssignmentViewVO viewVo = new AssignmentViewVO();
			
			String studentId=request.getParameter("studentId");
			String rollNo=request.getParameter("rollNo");
			String admissionId=request.getParameter("admissionId");
			String classId=request.getParameter("classId");
			String sectionId=request.getParameter("sectionId");
			String specId=request.getParameter("specId");
			String ass_Name=request.getParameter("ass_Name");
			String ass_date=request.getParameter("ass_date");
			String comp_date=request.getParameter("comp_date");
			String max_mark=request.getParameter("max_mark");
			String sch_Name=request.getParameter("sch_Name");
			String cls_Name=request.getParameter("cls_Name");
			String division=request.getParameter("division");
			String sub_Name=request.getParameter("sub_Name");
			String desc=request.getParameter("desc");
			String special=request.getParameter("special");
			
			String[] stdid=studentId.split(",");
			String[] roll_No=rollNo.split(",");
			String[] adm_Id=admissionId.split(",");
			String[] cls_Id=classId.split(",");
			String[] sec_Id=sectionId.split(",");
			String[] spc_Id=specId.split(",");
			
			viewVo.setStudentId(stdid);
			viewVo.setRoll_No(roll_No);
			viewVo.setAdmission_Id(adm_Id);
			viewVo.setClass_Id(cls_Id);
			viewVo.setSection_Id(sec_Id);
			viewVo.setSpec_Id(spc_Id);
			
			viewVo.setAssignmentname(ass_Name);
			viewVo.setAssignmentdate(ass_date);
			viewVo.setCompletiondate(comp_date);
			viewVo.setMaxmarks(max_mark);
			viewVo.setLocation(sch_Name);
			viewVo.setSubjectname(sub_Name);
			viewVo.setDescription(desc);
			viewVo.setSpecializationId(special);
			viewVo.setClassid(cls_Name);
			viewVo.setSectionid(division);
			viewVo.setUserId(userId);
			viewVo.setAccYear(accYearString);
			
			String statuss=new AssignmentUploadBD().insertAssignment(viewVo);
		
			JSONObject object=new JSONObject();
			object.put("assignmentInsertion", statuss);
			response.getWriter().print(object);;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : insertAssignment Ending");

		return null;
	}
	
	public synchronized ActionForward editAssignment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : editAssignment Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADASSIGNMENT);
			
			String assignmentId=request.getParameter("assignmentId");
			
			ArrayList<AssignmentUploadVo> assignmentDetailsList=new AssignmentUploadBD().getAssignmentDetails(assignmentId);
			AssignmentUploadVo assignmentDetailsVo=new AssignmentUploadBD().getSingleAssignment(assignmentId); 
			
			request.setAttribute("AssignmentDetailsList", assignmentDetailsList);
			
			request.setAttribute("AssignmentDetailsVo", assignmentDetailsVo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : editAssignment Ending");

		return mapping.findForward(MessageConstants.UPDATE_ASSIGNMENT);
	}
	
	public synchronized ActionForward updateAssignmentDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : updateAssignmentDetails Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String userId=HelperClass.getCurrentUserID(request);
			
			String assignmentId=request.getParameter("assignmentId");
			String studentId=request.getParameter("studentIdArray");
			String actualCompletionDate=request.getParameter("actualcompletionArray"); 
			String aquiredmarks=request.getParameter("aquiredmarksArray");
			String remarks=request.getParameter("remarksArray");
			
			
			AssignmentUploadVo assignmentVo=new AssignmentUploadVo();
			
			 assignmentVo.setAssignmentId(assignmentId);
			 assignmentVo.setStudentId(studentId);
			 assignmentVo.setActualcompletedDate(actualCompletionDate);
			 assignmentVo.setAcquiredMarks(aquiredmarks);
			 assignmentVo.setRemarks(remarks);
			 assignmentVo.setUserID(userId);
			
			String status=new AssignmentUploadBD().updateAssignmentDetails(assignmentVo);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : updateAssignmentDetails Ending");

		return null;
	}
	
	public synchronized ActionForward deleteAssignment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : deleteAssignment Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String assCode=request.getParameter("assignmentCode");
			String[] assignCode=assCode.split(",");
			
			AssignmentViewVO assignmentCode = new AssignmentViewVO();
			assignmentCode.setAssignmentCode(assignCode);
			
			
			String status=new AssignmentUploadBD().deleteAssignment(assignmentCode);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : deleteAssignment Ending");

		return null;
	}
	
	public synchronized ActionForward addnewProject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : addnewProject Starting");
		try {
			
			String userId=HelperClass.getCurrentUserID(request);
			String accYearString=request.getSession(false).getAttribute("current_academicYear").toString();
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			
			ProjectVO viewVo = new ProjectVO();
			
			String studentId=request.getParameter("studentId");
			String rollNo=request.getParameter("rollNo");
			String admissionId=request.getParameter("admissionId");
			String classId=request.getParameter("classId");
			String sectionId=request.getParameter("sectionId");
			String specId=request.getParameter("specId");
			String projectname=request.getParameter("projectname");
			String assignedDate=request.getParameter("assignedDate");
			String submissionDate=request.getParameter("submissionDate");
			String max_mark=request.getParameter("max_mark");
			String sch_Name=request.getParameter("sch_Name");
			String cls_Name=request.getParameter("cls_Name");
			String division=request.getParameter("division");
			String desc=request.getParameter("desc");
			String special=request.getParameter("special");
			String subject=request.getParameter("subject");
			
			String[] stdid=studentId.split(",");
			String[] roll_No=rollNo.split(",");
			String[] adm_Id=admissionId.split(",");
			String[] cls_Id=classId.split(",");
			String[] sec_Id=sectionId.split(",");
			String[] spc_Id=specId.split(",");
			
			viewVo.setStudentId(stdid);
			viewVo.setRoll_No(roll_No);
			viewVo.setAdmission_Id(adm_Id);
			viewVo.setClass_Id(cls_Id);
			viewVo.setSection_Id(sec_Id);
			viewVo.setSpec_Id(spc_Id);
			viewVo.setSubjectid(subject);
			viewVo.setProjectname(projectname);
			viewVo.setAssigneddate(assignedDate);
			viewVo.setSubmissiondate(submissionDate);
			viewVo.setMaxmarks(max_mark);
			viewVo.setLocation(sch_Name);
			viewVo.setDescription(desc);
			viewVo.setSpecializationId(special);
			viewVo.setClassid(cls_Name);
			viewVo.setSectionid(division);
			viewVo.setUserId(userId);
			viewVo.setAccYear(accYearString);
			
			String statuss=new AssignmentUploadBD().addProject(viewVo);
		
			JSONObject object=new JSONObject();
			object.put("ProjectStatus", statuss);
			response.getWriter().print(object);;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : addnewProject Ending");

		return null;
	}
	
	public synchronized ActionForward editProject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : editProject Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADPROJECT);
			
			String projectId=request.getParameter("projectId");
			
			ArrayList<ProjectVO> projectDetailsList=new AssignmentUploadBD().getProjectDetails(projectId);
			ProjectVO projectDetailsVo=new AssignmentUploadBD().getSingleProject(projectId); 
			
			request.setAttribute("ProjectDetailsList", projectDetailsList);
			
			request.setAttribute("ProjectDetailsVo", projectDetailsVo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : editProject Ending");

		return mapping.findForward(MessageConstants.UPDATE_PROJECT);
	}
	
	public synchronized ActionForward updateProjectDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : updateProjectDetails Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String userId=HelperClass.getCurrentUserID(request);
			
			String projectId=request.getParameter("projectId");
			String studentId=request.getParameter("studentIdArray");
			String actualCompletionDate=request.getParameter("actualcompletionArray"); 
			String aquiredmarks=request.getParameter("aquiredmarksArray");
			String remarks=request.getParameter("remarksArray");
			
			
			ProjectVO projectvo = new ProjectVO();
			
			projectvo.setProjectId(projectId);
			projectvo.setStudentid(studentId);
			projectvo.setActualcompletedate(actualCompletionDate);
			projectvo.setAcquiredmarks(aquiredmarks);
			projectvo.setRemarks(remarks);
			projectvo.setUserId(userId);
			
			String status=new AssignmentUploadBD().updateProjectDetails(projectvo);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : updateProjectDetails Ending");

		return null;
	}
	
	public synchronized ActionForward deleteProject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : deleteProject Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String prjCode=request.getParameter("projectCode");
			String[] projectCode=prjCode.split(",");
			
			ProjectVO project = new ProjectVO();
			project.setProjectCode(projectCode);
			
			
			String status=new AssignmentUploadBD().deleteProject(project);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadAction : deleteProject Ending");

		return null;
	}
	
}
