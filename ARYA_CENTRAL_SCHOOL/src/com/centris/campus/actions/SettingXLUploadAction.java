package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.centris.campus.forms.SettingExcelUploadForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SettingsFileUploadUtil;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class SettingXLUploadAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(SettingXLUploadAction.class);
	
	

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
	
	
	public ActionForward downloadClassWiseExcelUploadFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Starting");
		try {

			String locationid = request.getParameter("locationId");
			String accyear = request.getParameter("accyearId");
			String classid = request.getParameter("classId");
			String sectionid = request.getParameter("sectionId");
			String examid = request.getParameter("examid");
			String subjectid = request.getParameter("subjectid");
			
		 	File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			ReportMenuVo vo=new ReportMenuVo();
			vo.setLocationId(locationid);
			vo.setAccyearId(accyear);
			vo.setClassId(classid);
			vo.setSectionId(sectionid);
			vo.setExamId(examid);
			vo.setSubjectid(subjectid);

			List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentClassSectionWiseListForReport(vo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stuList);	
			String sourceFileName = request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,null, beanColDataSource);
			JRXlsxExporter exporter = new JRXlsxExporter();
			File outputFile = new File(request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.xlsx"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,new Boolean(true));
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,new Boolean(true));

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.xlsx"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename="+stuList.get(0).getClassname()+"-"+stuList.get(0).getSectionname()+"_"+stuList.get(0).getSubjectName()+"_"+stuList.get(0).getExamname()+".xlsx");
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Ending");
		return null;
	}
	
	
	
	
	
	
	
	
//subject excel upload::setting	
	
	public ActionForward ExcelUpload_subjectSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExcelUpload_subjectSetting : excelUploadForStudentsMarks Starting");
		try {
			
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExcelUpload_subjectSetting : excelUploadForStudentsMarks Ending");
		
		return mapping.findForward("ExcelUpload_subjectSetting");

	}
	
	public ActionForward SubjectExcelUploadFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectExcelUploadFormat Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);

			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Subjectwise_excel_upload_format.xlsx";
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "Subjectwise_excel_upload_format.xlsx");
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
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectExcelUploadFormat Ending");

		return null;
	}
	
	
	public ActionForward SubjectDetailsExcelInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : HolidayExcelInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
		Set<classVo> subjectList = new HashSet<classVo>();
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String userId = user.getUserId();
			String accyear = user.getAcademicyear();
			
			if (file != null) {
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> SubjectMap = new SettingsFileUploadUtil().SubjectDetailsSetting(fileURL,file);

				List<ClassPojo> alList = (List<ClassPojo>) SubjectMap.get("List");
			
				if(alList != null){
				beforeInsert = alList.size();
				System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) SubjectMap.get("Error");
		
				System.out.println("Going To Delegate");
				
				subjectList = ClassBD.insertSubjectDetails((List<ClassPojo>) SubjectMap.get("List"),userId,accyear,log_audit_session);
				System.out.println("libXLSList::::::::::::::::::"+subjectList.size());
				notInsertCount = subjectList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (subjectList.size()!=0) {
					request.setAttribute("failedSubjectList", subjectList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "ExcelUpload_subjectSetting" ;
					System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "ExcelUpload_subjectSetting";

					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Holiday Detail(s) Uploaded SuccessFully");
						forward = "ExcelUpload_subjectSetting";
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
					forward = "ExcelUpload_subjectSetting";
				}
			}
		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "ExcelUpload_subjectSetting" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectDetailsExcelInsert Ending");
		return mapping.findForward(forward);
	}
	
	
	
	
	
//holiday list
	
	public ActionForward HolodaylistExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in AdminMenuAction : HolodaylistExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in AdminMenuAction : HolodaylistExcelUpload Ending");

		return mapping.findForward("HolidaylistExcelUpload");
	}

	
	
	
public ActionForward HolidayExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelFormat Starting");
	try {
		
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

		String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Holiday-instructions.xlsx";
		System.out.println("FILEPATH:::" + filePath);
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("content-disposition", "attachment; filename=" + "Holiday-instructions.xlsx");
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
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelFormat Ending");

	return null;
}
	
public ActionForward HolidaySampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidaySampleData Starting");
	try {

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);
		
		String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Holiday-sample-format.xlsx";
		System.out.println("FILEPATH:::" + filePath);
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("content-disposition", "attachment; filename=" + "Holiday-sample-format.xlsx");
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
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidaySampleData Ending");
	return null;
}

	public ActionForward HolidayExcelInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : HolidayExcelInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
	
		SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
		Set<classVo> holidayList = new HashSet<classVo>();
		try {
	
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);
	
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
	
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String userId = user.getUserId();
			String accyear = user.getAcademicyear();
	
			if (file != null) {
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
	
				Map<String, Object> holdiayMap = new SettingsFileUploadUtil().holidaySetting(fileURL,file);
	
				List<ClassPojo> alList = (List<ClassPojo>) holdiayMap.get("List");
	
				if(alList != null){
					beforeInsert = alList.size();
					System.out.println("beforeInsert:::" + beforeInsert);
					String demo = (String) holdiayMap.get("Error");
					System.out.println("Going To Delegate");
	
					holidayList = ClassBD.insertHolidayXSL((List<ClassPojo>) holdiayMap.get("List"),userId,accyear,log_audit_session);
					System.out.println("libXLSList::::::::::::::::::"+holidayList.size());
					notInsertCount = holidayList.size();
					successInsert = beforeInsert - notInsertCount;
	
					if (holidayList.size()!=0) {
						request.setAttribute("FailedHolidayList", holidayList);
						request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
								+ ": Duplicate or Invalid record(s) found.");
						forward = "HolidaylistExcelUpload" ;
						System.out.println(forward);
					}
					else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "HolidaylistExcelUpload";
	
					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Holiday Detail(s) Uploaded SuccessFully");
						forward = "HolidaylistExcelUpload";
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
					forward = "HolidaylistExcelUpload";
				}
			}
		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "HolidaylistExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelInsert Ending");
		return mapping.findForward(forward);
	}
	
	public ActionForward downloadClassWiseCoScholasticAreaExcelUploadFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Starting");
		try {

			String locationid = request.getParameter("locationId");
			String accyear = request.getParameter("accyearId");
			String classid = request.getParameter("classId");
			String sectionid = request.getParameter("sectionId");
			String examid = request.getParameter("examid");
			
		 	File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			ReportMenuVo vo=new ReportMenuVo();
			vo.setLocationId(locationid);
			vo.setAccyearId(accyear);
			vo.setClassId(classid);
			vo.setSectionId(sectionid);
			vo.setExamId(examid);

			List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentClassSectionWiseListForReportByAll(vo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stuList);	
			String sourceFileName = request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,null, beanColDataSource);
			JRXlsxExporter exporter = new JRXlsxExporter();
			File outputFile = new File(request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.xlsx"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,new Boolean(true));
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,new Boolean(true));

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.xlsx"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename="+stuList.get(0).getClassname()+"-"+stuList.get(0).getSectionname()+"_StudentCo-Scholastic AreasUploadExcelFormat.xlsx");
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Ending");
		return null;
	}
}
