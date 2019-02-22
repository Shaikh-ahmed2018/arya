package com.centris.campus.actions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SettingsXLUploadBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.UploadStudentXSLForm;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SettingsFileUploadUtil;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.studentExcelUploadVo;

public class UploadStudentXSLAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(UploadStudentXSLAction.class);

	public ActionForward insertStudentXSL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXSLAction : insertEmpXSL : Starting");

		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward = null;
		File fileURL=null;
		try {


			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String username = userDetailVO.getUserId();


			UploadStudentXSLForm uploadEmpXSLForm = (UploadStudentXSLForm) form;

			FormFile file = uploadEmpXSLForm.getFormFile();

			String filePath = request.getRealPath("/");

			if (file != null) {
				fileName = file.getFileName();
				 fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				Map<String, Object> employeeMap = new UploadStudentXSLUtility().getExcelData(fileURL, file);
				List<UploadStudentXlsPOJO> alList = (List<UploadStudentXlsPOJO>) employeeMap.get("List");
				beforeInsert = alList.size();
				UploadStudentXSLBD empXSLBD = new UploadStudentXSLBD();
				Set<UploadStudentXlsVO> empXLSList = new HashSet<UploadStudentXlsVO>();
				String demo = (String) employeeMap.get("Error");
				empXLSList = empXSLBD.insertEmpXSL((List<UploadStudentXlsPOJO>) employeeMap.get("List"), username, demo,
						schoolLocation);
				notInsertCount = empXLSList.size();
				successInsert = beforeInsert - notInsertCount;
				// new code
				if (empXLSList.size() != 0) {
					request.setAttribute("FailEmployeeList", empXLSList);
					// request.setAttribute("failedStaffList",
					// request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

				} else {
					// successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= " + successInsert);
					request.setAttribute("successmessagediv",
							+successInsert + ":Student(s) Rocords Registered SuccessFully");

					String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();

					if (schoolLocation.equalsIgnoreCase("all")) {
						schoolLocation = "%%";
					}

					String searchTerm = "%%";
					List<StudentRegistrationVo> List = null;
					List = new StudentRegistrationDelegate().getStudentDetailsexcel(searchTerm, schoolLocation,
							currentaccyear);
					request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;
				}

			} else {
				forward = MessageConstants.STUDENT_EXCEL_UPLOAD;
			}

		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "File is Corrupted or Empty");
			forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

			e.printStackTrace();

			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		finally{
			if(fileURL!=null){
				fileURL.delete();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : insertEmpXSL : Ending");
		return mapping.findForward(forward);

	}

	public ActionForward downloadfileformat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadfileformat : Starting");
		System.out.println("downloadfileformat");
		try {
			String filePath = request.getRealPath("/")+ "FIles/StudentRegistrationFileUpload/StudentRegistrationFormate.xlsx";
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "StudentRegistrationFormate.xlsx");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadfileformat : Ending");
		return null;
	}
	

	public ActionForward uploadStudentExamDetails_byExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : uploadStudentExamDetails_byExcel Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
				
			
			
			
			UploadStudentXSLForm lf =(UploadStudentXSLForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
		/*	UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String userId = user.getUserId();*/

				if (file != null) {
					fileName = file.getFileName();
					File fileURL = new File(filePath, fileName);
					request.setAttribute("fileAttribute", fileName);

					Map<String, Object> locationMap = new SettingsFileUploadUtil().StudentExamExcelUpload(fileURL,file);

					List<StudentExcelUploadPojo> alList = (List<StudentExcelUploadPojo>) locationMap.get("List");

				/*	System.out.println("Action class >> size of list after utility class: " + alList.size());*/
				
					beforeInsert = alList.size();
					System.out.println("beforeInsert:::" + beforeInsert);
					
					Set<studentExcelUploadVo> locationXLSList = new HashSet<studentExcelUploadVo>();
					
					String demo = (String) locationMap.get("Error");
					System.out.println("Going To Delegate");
				locationXLSList = new SettingsXLUploadBD().saveStudentExam((List<StudentExcelUploadPojo>) locationMap.get("List"));
				System.out.println("libXLSList::::::::::::::::::"+locationXLSList.size());
				notInsertCount = locationXLSList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (locationXLSList.size()!=0) {
					System.out.println("I m Mix");
					request.setAttribute("FailedExamUploadList", locationXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "ExcelUploadforstudentMarks" ;
				}
				 else {
						System.out.println("I m Success");
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Subscriber Detail(s) Rocords Registered SuccessFully");
					}
			}
			
			forward = "ExcelUploadforstudentMarks";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : uploadStudentExamDetails_byExcel Ending");

		return mapping.findForward(forward);
	}
	
	public ActionForward studentExcelUpload_instructions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_instructions Starting");
		try {

			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/studentExcelUpload_instructions.xlsx";
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "studentExcelUpload_instructions.xlsx");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_instructions Ending");

		return null;
	}
	
	public ActionForward excelUploadForStudentsMarks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : excelUploadForStudentsMarks Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : excelUploadForStudentsMarks Ending");
		
		return mapping.findForward("ExcelUploadforstudentMarks");

	}

}
