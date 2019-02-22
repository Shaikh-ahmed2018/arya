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

import com.centris.campus.daoImpl.StudentAttendanceDaoImpl;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StaffAttendanceReportBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.forms.StdentAttendanceReportForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;

public class StudentAttendanceReportAction extends DispatchAction{

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	private static final Logger logger = Logger.getLogger(StudentAttendanceReportAction.class);
	
	public ActionForward studentAttendanceReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : studentAttendanceReport Starting");
		
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STUDENTATTENDANCEDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			  
			  ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
				.getClassesByStream("%%");
			  request.setAttribute("classList", classList);

			  
			List<StreamDetailsVO> streamvo =new CommunicationSettingsBD().getStreamListDetails();
			request.setAttribute("streamlist", streamvo);
			
			  
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : studentAttendanceReport Ending");

		
		return  mapping.findForward(MessageConstants.studentattendancereport);
	}
	
	
	
	public ActionForward getAllStudentName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getAllStudentName Starting");
		
		
	
		
		try {
			
			String classVal = request.getParameter("classid");
			String sectionVal = request.getParameter("sectionid");
		List<ParentVO> parentVOList = new StudentAttendanceBD().getAllStudent(classVal, sectionVal);
		
		
			
	
		
		 JSONObject object=new JSONObject();
		 
		 object.put("parentVOList", parentVOList);
		 
		 response.getWriter().print(object);
		
		
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getAllStudentName Ending");

		return null;
		
	}
	
	
	public ActionForward getAllStudentListName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getAllStudentName Starting");
		
		
	
		
		try {
			
			String classVal = request.getParameter("classid");
			String sectionVal = request.getParameter("sectionid");
			String locationval=request.getParameter("locationid");
			String accyearVal=request.getParameter("accyearid");
			List<ParentVO> parentVOList = new StudentAttendanceDaoImpl().getAllStudentListName(locationval,accyearVal,classVal,sectionVal);
		
		
			
	
		
		 JSONObject object=new JSONObject();
		 
		 object.put("parentVOList", parentVOList);
		 
		 response.getWriter().print(object);
		
		
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getAllStudentName Ending");

		return null;
		
	}
	
	public ActionForward studentAttendanceExcelReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : studentAttendanceExcelReport Starting");
		
		
		
		String studentname = "";
		String accyear = request.getParameter("accyear").trim();
		String fromdate = request.getParameter("fromdate").trim();
		String todate = request.getParameter("todate").trim();
		String streamname = request.getParameter("stream").trim();
		String classname = request.getParameter("classname").trim();
		String section = request.getParameter("section").trim();
		studentname = request.getParameter("studentname");
		
		
		
		
		
		
		
		if(studentname.equalsIgnoreCase("all"))
			
		{
			
			studentname = "%%";
			
		}
		
		
		String filePath = null;
		
		try {
			


			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentAttendanceListXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			
			StudentAttendanceReportVo stuvo = new StudentAttendanceReportVo();
			
			stuvo.setAccyrid(accyear);
			stuvo.setFromdate(fromdate);
			stuvo.setTodate(todate);
			stuvo.setStreamid(streamname);
			stuvo.setClassid(classname);
			stuvo.setSectionid(section);
			stuvo.setStudentid(studentname);
			
			/*
			ArrayList<StudentAttendanceReportVo> MasterList = new StudentAttendanceBD().getStudentAttendanceReportBD(vo);
*/
			ArrayList<StudentAttendanceReportVo> MasterList = new StudentAttendanceBD().getStudentAttendanceListReportBD(stuvo);	
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					MasterList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentAttendanceListXLSReport.xls"));
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
					request.getRealPath("Reports/StudentAttendanceListXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentAttendanceListXLSReport.xls");
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
				+ " Control in StudentAttendanceReportAction : studentAttendanceExcelReport Ending");

		return null;
	}
	
	
	
	public ActionForward studentAttendancePDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : studentAttendancePDFReport Starting");
		
		try {

			
			String studentname = "";
			String accyear = request.getParameter("accyear").trim();
			String fromdate = request.getParameter("fromdate").trim();
			String todate = request.getParameter("todate").trim();
			String streamname = request.getParameter("stream").trim();
			String classname = request.getParameter("classname").trim();
			String section = request.getParameter("section").trim();
			studentname = request.getParameter("studentname");
			
			if(studentname.equalsIgnoreCase("all"))
				
			{
				
				studentname = "%%";
				
			}
			
			
			
			String filePath = null;
			
			

			StudentAttendanceReportVo stuvo = new StudentAttendanceReportVo();
			
			stuvo.setAccyrid(accyear);
			stuvo.setFromdate(fromdate);
			stuvo.setTodate(todate);
			stuvo.setStreamid(streamname);
			stuvo.setClassid(classname);
			stuvo.setSectionid(section);
			stuvo.setStudentid(studentname);
			
			
			/*ArrayList<StudentAttendanceReportVo> MasterList = new StudentAttendanceBD().getStudentAttendanceReportBD(vo);
*/
			
			ArrayList<StudentAttendanceReportVo> MasterList = new StudentAttendanceBD().getStudentAttendanceListReportBD(stuvo);
			String sourceFileName = request
					.getRealPath("Reports/StudentAttendancePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					MasterList);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentAttendancePDFReport - " + ".pdf\"");

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
				+ " Control in StudentAttendanceReportAction : studentAttendancePDFReport Ending");
		
		return null;
	}
	
	public ActionForward getStudentAttendanceAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getStudentAttendanceAction Starting");
	
		try {
			String studentname = "";
			StdentAttendanceReportForm stuform=(StdentAttendanceReportForm)form;
			
			StudentAttendanceReportVo stuvo = new StudentAttendanceReportVo();
			
			stuvo.setAccyrid(stuform.getAccyear());
			stuvo.setStreamid(stuform.getStreamname());
			stuvo.setClassid(stuform.getClassname());
			stuvo.setSectionid(stuform.getSectionname());
		 	
	    	stuvo.setFromdate(HelperClass.convertUIToDatabase(stuform.getFromdate()));
			stuvo.setTodate(HelperClass.convertUIToDatabase(stuform.getTodate()));
			
			/*stuvo.setFromdate(stuform.getFromdate());
			stuvo.setTodate(stuform.getTodate());*/
			
			if(stuform.getStudentname().equalsIgnoreCase("all"))
				
			{
				
				stuform.setStudentname("%%");
				
			}
			
			stuvo.setStudentid(stuform.getStudentname());
			
		
		 String acc = stuform.getAccyear();
		 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		 ReportMenuVo selectedItems=new StaffAttendanceReportBD().getSelectedItems(acc);
		 request.setAttribute("AccYearList", accYearList);
		 request.setAttribute("accyearitem", selectedItems);
			
			
		         String stream = stuform.getStreamname();
		         StreamDetailsVO streamitem = new StudentAttendanceBD().getStreamNameBD(stream);
				 List<StreamDetailsVO> streamvo =new CommunicationSettingsBD().getStreamListDetails();
				 request.setAttribute("streamlist", streamvo);
			     request.setAttribute("streamitem", streamitem);
			
			
			     String classname = stuform.getClassname();
			     ClassPojo classitem = new StudentAttendanceBD().getClassNameBD(classname);
			     request.setAttribute("classitem", classitem);
			     
			     
			     String sectionname = stuform.getSectionname();
			     SectionPojo sectionitem = new StudentAttendanceBD().getSectionNameBD(sectionname);
			     request.setAttribute("sectionitem", sectionitem);
			     
			     
			     String student = stuform.getStudentname();
			     System.out.println("studnt in action class: "+student);
			     ParentVO studentitem = new StudentAttendanceBD().getStudentNameBD(student);
			    
			     if(student.equalsIgnoreCase("%%"))
						
					{
					  
			    	  studentitem.setStudentFnameVar("All");
					 // request.setAttribute("Stu", "all");
						
					}
			    	
			    
			    	 
				     request.setAttribute("studentitem", studentitem);
			    	 
			   
		
			ArrayList<StudentAttendanceReportVo> MasterList = new StudentAttendanceBD().getStudentAttendanceListReportBD(stuvo);
			
			
			
			request.setAttribute("studentattendanceList", MasterList);
			request.setAttribute("success", "success");
			
			  request.setAttribute("selecteditems", stuvo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceReportAction : getStudentAttendanceAction Ending");
		
		return  mapping.findForward(MessageConstants.studentattendancereport);
	}
	
	
	
}
