package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.RemainderMasterDelegate;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;


public class RemainderMasterAction extends DispatchAction 

{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName"); 
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public ActionForward addremainderdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : addremainderdetails Starting");
		
		String status = request.getParameter("result"); 
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccessremainMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.SuccessremainMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.SuccessremainderUpMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.SuccessremainderUpMsg);
			}
		}
		
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : addremainderdetails Ending");
		
		return mapping.findForward(MessageConstants.ADD_REMAINDER);
		
		
	}
	
	public ActionForward getnamecount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : getnamecount Starting");
		
		String name= request.getParameter("name");
		
		String id= request.getParameter("id");
		
		boolean status = false;
		
		
		try {
			
			RemainderMasterVO vo=new RemainderMasterVO();
			
			vo.setName(name);
			
			vo.setId(id);
			
			RemainderMasterDelegate delegate=new RemainderMasterDelegate();
			
			status=delegate.getnamecount(vo);
			
			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("message", status);
			
			response.getWriter().println(jsonObject);
					
			
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : getnamecount Ending");
		
		
		
		return null;
		
		
	}
	
	
	public ActionForward addremainderdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : addremainderdata Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
				
				
			RemainderMasterVO vo=new RemainderMasterVO();
			vo.setId(request.getParameter("id"));
			vo.setName(request.getParameter("name"));
			vo.setDescription(request.getParameter("description"));
			vo.setRemtype(request.getParameter("remainderto"));
			vo.setCreateuser(HelperClass.getCurrentUserID(request));
				
			RemainderMasterDelegate delegate=new RemainderMasterDelegate();
			
			String result=delegate.addremainderdata(vo);
			
			JSONObject jsonobj = new JSONObject();
			
			jsonobj.put("jsonResponse", result);
			
			response.getWriter().print(jsonobj);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : addremainderdata Ending");

		
		return null;
		
		
	
	
	}
	
	public ActionForward editremainderDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{

		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : editremainderDetails Starting");
		
		
		String id=request.getParameter("id");
		
		try {
			
			RemainderMasterVO vo=new RemainderMasterVO();
			
			vo.setId(id);
			
			RemainderMasterVO editremainderDetails = new RemainderMasterDelegate().editremainderDetails(vo);
			
			request.setAttribute("editremainderDetails", editremainderDetails);
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : editremainderDetails Ending");

		

		return mapping.findForward(MessageConstants.ADD_REMAINDER);
	
	}
	
	
	public ActionForward deleteRemainderDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : deleteRemainderDetails Starting");
		
		String result = "";
		
		
		String name=request.getParameter("name");
		
		try {
			
			RemainderMasterVO vo=new RemainderMasterVO();
			
			vo.setId(name);
			
			result = new RemainderMasterDelegate()
			.deleteRemainderDetails(vo);
			

			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("jsonResponse", result);
			
			response.getWriter().println(jsonObject);
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : deleteRemainderDetails Ending");

		

		return null;
		
	}
	
	
	
	public ActionForward remainderdetailsXLSDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : remainderdetailsXLSDownload Starting");
		
		
try {
			

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/RemainderDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
		
			String searchvalue = request.getParameter("searchvalue");
			
			
			
			RemainderMasterVO vo = new RemainderMasterVO();

			vo.setName(searchvalue);

			ArrayList<RemainderMasterVO> remainderlist = new RemainderMasterDelegate().remainderdetails(vo);
			
		
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					remainderlist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/RemainderDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Remainder Details Excel Report" };
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
					request.getRealPath("Reports/RemainderDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=RemainderDetailsXLSReport.xls");
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
				+ " Control in RemainderMasterAction : remainderdetailsXLSDownload Ending");

		

		return null;
		
	}
	
	public ActionForward remainderdetailsPDFDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterAction : remainderdetailsPDFDownload Starting");
		
		try {
			
			String searchvalue = request.getParameter("searchvalue");
			
			RemainderMasterVO vo = new RemainderMasterVO();

			vo.setName(searchvalue);

			ArrayList<RemainderMasterVO> remainderlist = new RemainderMasterDelegate().remainderdetails(vo);
			
			String sourceFileName = request
					.getRealPath("Reports/RemainderDetailsPDFReport.jrxml");
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


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "RemainderDetailsPDFReport - " + ".pdf\"");

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
				+ " Control in RemainderMasterAction : remainderdetailsPDFDownload Ending");

		

		return null;
		
	}
	
	
}
