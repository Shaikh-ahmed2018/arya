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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.LocationVO;

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

public class LocationDetailsAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(StreamDetailsAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward insertLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertLocationDetailsAction Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			LocationMasterForm form1= new LocationMasterForm();
			
			String createCode = HelperClass.getCurrentUserID(request);
			
			String locationName=request.getParameter("locationname");
			String desc=request.getParameter("description");
			String locationId=request.getParameter("update_loc");
			System.out.println(locationId);
			
			form1.setLocationname(locationName.trim());
			form1.setDesc(desc);
			
			form1.setLoc_id(locationId);
			form1.setCreatedBy(createCode);
			
			LocationBD details=new LocationBD();
			
			String result = details.insertLocationDetailsAction(form1,locationId);
			
			
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StreamDetailsAction : insertStreamDetailsAction  Ending");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertLocationDetailsAction Ending");
		
		return null;
	}
	
	public ActionForward validatelocationname(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			String locname = request.getParameter("locname");
			String locid = request.getParameter("locid");

			LocationMasterForm form1= new LocationMasterForm();

			form1.setLocationname(locname);
			form1.setLocation_id(locid);

			boolean locname_available = new LocationBD()
					.validateLocName(form1);

			JSONObject object = new JSONObject();
			object.put("des_available", locname_available);
			response.getWriter().print(locname_available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}
	
	public ActionForward validateLocNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction: validateDeptNameUpdate: Starting");

		try {
			String locname = request.getParameter("locname");
			String locid = request.getParameter("locid");

			LocationVO validateUpdate = new LocationVO();
			validateUpdate.setLocationname(locname);
			validateUpdate.setLocation_id(locid);
			boolean desname_available = new LocationBD()
					.validateLocNameUpdate(validateUpdate);

			JSONObject object = new JSONObject();
			object.put("des_available", desname_available);
			response.getWriter().print(desname_available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction: validateDeptNameUpdate: Ending");
		return null;
	}
	
	public ActionForward editLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION);
			
			String title = "Modify Location";
			request.setAttribute("Location", title);
			
			String edit = request.getParameter("locid");

			LocationVO edit_list = new LocationBD().editLocation(edit);

			request.setAttribute("editlist",edit_list);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(MessageConstants.ADD_LOCATION);

	}
	
	public ActionForward deleteLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteDepartment Starting");
		@SuppressWarnings("unused")
		String username = null;

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String locid[] = request.getParameterValues("locid[]");
			
			
			System.out.println("locid"+locid.length);
			String status =new LocationBD().deleteDepartmentDetails(locid);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteDepartment Ending");

		return null;

	}
	
	public ActionForward downloadLocationDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadLocationDetailsXL  Starting");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/LocationDetailsXLReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			
			String SearchName = request.getParameter("searchTerm");
			
			if(SearchName != null){
				
				list =obj.searchLocationDetails(SearchName);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				list = obj.getLocationDetails();
				
			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/LocationDetailsXLReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Location Details Excel Report" };
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
					request.getRealPath("Reports/LocationDetailsXLReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LocationDetailsXLReport.xls");
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
				+ " Control in AddDepartmentDetailsAction : downloadLocationDetailsXL  Ending");
		return null;

	}
	
	
	
	
	public ActionForward downloadLocationDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Starting");
		
		
		try {
			

			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			
			String SearchName = request.getParameter("searchTerm");
			
			if(SearchName != null){
				
				list =obj.searchLocationDetails(SearchName);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				list = obj.getLocationDetails();
				
			}
			
			
			
			String sourceFileName = request
					.getRealPath("Reports/LocationDetailsPDFReport.jrxml");
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
					+ "LocationDetailsPDFReport - " + ".pdf\"");

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
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Ending");
	
		return null;

	}
	
	
	
}
