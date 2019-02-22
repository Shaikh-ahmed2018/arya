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

import com.centris.campus.delegate.PhoneDirectoryReportBD;
import com.centris.campus.delegate.StaffAttendanceReportBD;
import com.centris.campus.forms.PhoneDirectoryForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PhoneDirectoryVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class PhoneDirectoryAction extends DispatchAction
{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	private static final Logger logger = Logger
			.getLogger(PhoneDirectoryAction.class);


	public ActionForward phonedirectoryAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: phonedirectoryAction Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PHONEDIRECTORY);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: phonedirectoryAction Ending");

		return mapping.findForward(MessageConstants.PHONE_DIRECTORY);
	}


	public ActionForward getPersonNameListAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: getPersonNameListAction Starting");
		
		try {
			PhoneDirectoryVo vo = new PhoneDirectoryVo();
			
			String category = "";
			
			 category = request.getParameter("category").trim();
			
		
			vo.setCategory(category);
			
			List<PhoneDirectoryVo> phonedirectorylist=new PhoneDirectoryReportBD().getPhoneDirectoryNamesBD(vo);
			
              
			
			 JSONObject object=new JSONObject();
			 
			 object.put("phonedirectorylist", phonedirectorylist);
			 
			 response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: getPersonNameListAction Ending");
		return null;
	
	}
	
	
	public ActionForward getPhoneDirectoryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: getPhoneDirectoryList Starting");
		
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, LeftMenusHighlightMessageConstant.MODULE_REPORTS_PHONEDIRECTORY);
		
		
		try {
			PhoneDirectoryForm phoneform = (PhoneDirectoryForm)form;
			PhoneDirectoryVo vo = new PhoneDirectoryVo();
			
			vo.setCategory(phoneform.getCategory());
			
			System.out.println("phoneform.getCategory()-->"+phoneform.getCategory());
			System.out.println("phoneform.getCategory()-->"+phoneform.getName());
			
			if(phoneform.getName().equalsIgnoreCase("all")){
				
				
				
				vo.setId("%%");
				
				
			
				
			}
			else{
				
				vo.setId(phoneform.getName());
			}
			
			
			
			PhoneDirectoryVo selectedlist=new PhoneDirectoryReportBD().getSelectedValueNameBD(vo);
			
			
			List<PhoneDirectoryVo> phonedirectorylist=new PhoneDirectoryReportBD().getPhoneDirectoryListBD(vo);
			
			
			
			request.setAttribute("phonedirectorylist", phonedirectorylist);
			
			request.setAttribute("selectedvalue", phoneform);
			
			
			request.setAttribute("selectedlist", selectedlist);
			
			request.setAttribute("success", "success");
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: getPhoneDirectoryList Ending");
		
		return mapping.findForward(MessageConstants.PHONE_DIRECTORY);
	
	}
	
	
	public ActionForward phonedirectoryExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: phonedirectoryExcelReport Starting");
		
			
		
		String filePath = null;
		
		try {
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			 String selectname = "";
			 String category=request.getParameter("category");
			 selectname=request.getParameter("selectname");
			
		
			String sourceFileName = request.getRealPath("Reports/PhoneDirectoryXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			PhoneDirectoryVo vo = new PhoneDirectoryVo();
			
			vo.setCategory(category);
			vo.setId(selectname);
			 
			 if(selectname.equalsIgnoreCase("all")){
				 
				 vo.setId("%%");
				 
			 }
			

			List<PhoneDirectoryVo> phonedirectorylist=new PhoneDirectoryReportBD().getPhoneDirectoryListBD(vo);
			
		
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					phonedirectorylist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/PhoneDirectoryXLSReport.xls"));
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
					request.getRealPath("Reports/PhoneDirectoryXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=PhoneDirectoryXLSReport.xls");
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
				+ " Control in PhoneDirectoryAction: phonedirectoryExcelReport Ending");
		
		return null;
	
	}
	

	public ActionForward phonedirectoryPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryAction: phonedirectoryPdfReport Starting");
		
		
try {


	 String selectname = "";
	 String category=request.getParameter("category");
	 selectname=request.getParameter("selectname");
		

		PhoneDirectoryVo vo = new PhoneDirectoryVo();
		
		vo.setCategory(category);
		vo.setId(selectname);
		
		
		
		
		 
		 if(selectname.equalsIgnoreCase("all")){
			 
			 vo.setId("%%");
			 
		 }
			

			List<PhoneDirectoryVo> phonedirectorylist=new PhoneDirectoryReportBD().getPhoneDirectoryListBD(vo);
			
		
			
			String sourceFileName = request
					.getRealPath("Reports/PhoneDirectoryPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					phonedirectorylist);

			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "PhoneDirectoryPDFReport - " + ".pdf\"");

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
				+ " Control in PhoneDirectoryAction: phonedirectoryPdfReport Ending");
		
		return null;
	
	}
	
	
}
