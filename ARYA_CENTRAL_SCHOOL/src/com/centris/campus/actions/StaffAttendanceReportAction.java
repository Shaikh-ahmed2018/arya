package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import org.json.JSONObject;

import com.centris.campus.daoImpl.StaffAttendanceReportDaoImpl;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StaffAttendanceReportBD;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceReportAction extends DispatchAction{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	private static final Logger logger = Logger.getLogger(AdminMenuAction.class);
	
	
	public ActionForward staffAttendanceReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendanceReport Starting");
		
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STAFFATTENDANCEDETAILS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
	      ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		  request.setAttribute("AccYearList", accYearList);
		  
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendanceReport Ending");
		
		
		return mapping.findForward(MessageConstants.STAFF_ATTENDANCE_REPORT);
	}
	
	
	
	
	public ActionForward getStaffAttendanceAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceAction Starting");
		
		
		try {
			
			ReportMenuForm reform=(ReportMenuForm)form;
			ReportMenuVo vo = new ReportMenuVo();
			vo.setAccyearname(reform.getAccyear());
			vo.setTeachertype(reform.getTeachertype());
			vo.setFromdate(HelperClass.convertUIToDatabase(reform.getFromdate()));
			vo.setTodate(HelperClass.convertUIToDatabase(reform.getTodate()));
			vo.setDesignation(reform.getDesignation());
			vo.setAttendenceStatus(reform.getAttendenceStatus());
	      if(reform.getTeachername().equalsIgnoreCase("all")){
				vo.setTeachertId("%%");
			}
			else{
				vo.setTeachertId(reform.getTeachername());
			}
	      
			String acc = reform.getAccyear();
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		   ReportMenuVo selectedItems=new StaffAttendanceReportBD().getSelectedItems(acc);
				 request.setAttribute("AccYearList", accYearList);
				request.setAttribute("selecteddetails", reform);
				request.setAttribute("accyearname", selectedItems);

				String teacher = reform.getTeachername();
				StaffAttendanceVo selectedteacher = new StaffAttendanceReportBD().getSelectedTeacherNameReportBD(vo);
				request.setAttribute("selectedteacher", selectedteacher);
				
				
				
				ArrayList<StaffAttendanceVo> staffattendanceList=new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo);
				request.setAttribute("success", "success");
				request.setAttribute("staffattendanceList", staffattendanceList);
				request.getSession(false).setAttribute("EXcel",staffattendanceList);				
				
				
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceAction Ending");
		
		return mapping.findForward(MessageConstants.STAFF_ATTENDANCE_REPORT);
	}
	
	
	
	
	public ActionForward staffAttendanceExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.START_POINT);
logger.info(JDate.getTimeString(new Date())
		+ " Control in StaffAttendanceReportAction : staffAttendanceExcelReport Starting");

String teachername = "";
String accyear=request.getParameter("accyear");
String fromdate=request.getParameter("fromdate");
String todate=request.getParameter("todate");
String teachertype=request.getParameter("teachertype");
teachername=request.getParameter("teachername");
String designation=request.getParameter("designation");
String attstatus=request.getParameter("attstatus");
String filePath = null;

try {
	


	File pdfxls = null;
	FileInputStream input = null;
	BufferedInputStream buf = null;
	ServletOutputStream stream = null;

	String sourceFileName = request.getRealPath("Reports/StaffAttendanceDetailsXLSReport.jrxml");
	JasperDesign design = JRXmlLoader.load(sourceFileName);
	JasperReport jasperreport = JasperCompileManager
			.compileReport(design);
	/*List<ReportMenuVo> MasterList = new ArrayList<ReportMenuVo>();
	MasterList = (List<ReportMenuVo>) request.getSession(false).getAttribute("EXcel");*/
	
	ReportMenuVo vo = new ReportMenuVo();
	
	vo.setTeachertype(teachertype);
	vo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
	vo.setTodate(HelperClass.convertUIToDatabase(todate));
	vo.setTeachertId(teachername);
	vo.setDesignation(designation);
	
	String desigantionName="ALL";
	if(teachername.equalsIgnoreCase("all")){
		 
		 vo.setTeachertId("%%");
		 
	 }
	if(designation.equalsIgnoreCase("all")){
		 
		 vo.setDesignation("%%");
		 
	 }
	else {
		desigantionName=HelperClass.getDesignationName(designation);
	}
	
	if(attstatus.equalsIgnoreCase("all")){
		 
		vo.setAttendenceStatus("%%");
		 
	 }
	else {
		vo.setAttendenceStatus(attstatus);
	}
	
	ArrayList<StaffAttendanceVo> MasterList = new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo);

	
	
	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
			MasterList);
	Map parameters = new HashMap();
	parameters.put("fromdate",fromdate);
	parameters.put("todate",todate);
	parameters.put("designationName",desigantionName);
	
	stream = response.getOutputStream();
	JasperPrint print = JasperFillManager.fillReport(jasperreport,
			parameters, beanColDataSource);
	JRXlsExporter exporter = new JRXlsExporter();
	File outputFile = new File(
			request.getRealPath("Reports/StaffAttendanceDetailsXLSReport.xls"));
	FileOutputStream fos = new FileOutputStream(outputFile);
	String[] sheetNames = { "Role Details Excel Report" };
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
			request.getRealPath("Reports/StaffAttendanceDetailsXLSReport.xls"));
	response.setContentType("application/octet-stream");
	response.addHeader("Content-Disposition",
			"attachment; filename=StaffAttendanceDetailsXLSReport.xls");
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
		+ " Control in ReportsAction : staffAttendanceExcelReport Ending");

return null;
}
	
	public ActionForward staffAttendancePDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendancePDFReport Starting");
		
		try {


			String teachername = "";
			String accyear = request.getParameter("accyear");
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");
			String teachertype = request.getParameter("teachertype");
			teachername=request.getParameter("teachername");
			String designation=request.getParameter("designation");
			String attstatus=request.getParameter("attstatus");
			ReportMenuVo vo = new ReportMenuVo();

			vo.setTeachertype(teachertype);
			vo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
			vo.setTodate(HelperClass.convertUIToDatabase(todate));
			vo.setTeachertId(teachername);
			vo.setDesignation(designation);
			vo.setAttendenceStatus(attstatus);
			
			String desigantionName="ALL";
			if(teachername.equalsIgnoreCase("all")){
				 
				 vo.setTeachertId("%%");
				 
			 }
			if(designation.equalsIgnoreCase("all")){
				 
				 vo.setDesignation("%%");
				 
			 }
			else {
				desigantionName=HelperClass.getDesignationName(designation);
			}
			
			if(attstatus.equalsIgnoreCase("all")){
				 
				 vo.setAttendenceStatus("%%");
				 
			 }
			
			
			
			
			
			List<StaffAttendanceVo> Details = new ArrayList<StaffAttendanceVo>();
			Details = new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo);

			String sourceFileName = request.getRealPath("Reports/StaffAttendancePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Details);

			String PropfilePath =request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			parameters.put("schName", schName);
			parameters.put("schAddLine1", schAddLine1);
			parameters.put("fromdate",fromdate);
			parameters.put("todate",todate);
			parameters.put("designationName",desigantionName);
			parameters.put("staffType",teachertype);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StaffAttendancePDFReport - " + ".pdf\"");

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
				+ " Control in StaffAttendanceReportAction : staffAttendancePDFReport Ending");
		
		return null;
	}
	
	
	public ActionForward getTeachernameAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getTeachernameAction Starting");
		
		
		try {
			
			
			String teachertype = "";
			teachertype = request.getParameter("teachertype");
			String designation=request.getParameter("designation");
			
			AllTeacherDetailsVo vo = new AllTeacherDetailsVo();
			vo.setTeacherType(teachertype);
			vo.setDesignation(designation);
			
				
				List<AllTeacherDetailsVo> teachinglist = new StaffAttendanceReportDaoImpl().getStaffList(vo);
				
				 JSONObject object=new JSONObject();
				 
				 object.put("teachinglist", teachinglist);
				 
				 response.getWriter().print(object);	
			
	
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getTeachernameAction Ending");
		
		return null;
	}
	
	
	public ActionForward getDesignationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getDesignationList Starting");
		
		
		try {
			
			
			String teachertype = "";
			teachertype = request.getParameter("teachertype");
			
			AllTeacherDetailsVo vo = new AllTeacherDetailsVo();
			vo.setTeacherType(teachertype);
			
				
				List<AllTeacherDetailsVo> designationList = new StaffAttendanceReportDaoImpl().getDesignationList(vo);
				
				 JSONObject object=new JSONObject();
				 
				 object.put("designationList", designationList);
				 
				 response.getWriter().print(object);	
			
	
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getDesignationList Ending");
		
		return null;
	}
	
	
	
	
}
