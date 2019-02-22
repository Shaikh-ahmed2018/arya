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

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.delegate.TransportTypeBD;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.TransportTypeVO;

public class TransportTypeAction extends DispatchAction {
	
	private static Logger logger = Logger.getLogger(TransportTypeAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public synchronized ActionForward getTransportTypeEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : getTransportTypeEntry Starting");

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : getTransportTypeEntry Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_TYPE_ENTRY);
	}

	public synchronized ActionForward validateTypeName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : validateTypeName Starting");

		try {
			
			String typeName = request.getParameter("typeName");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setTransptyname(typeName);
		
			String result = new TransportTypeBD().validateTypeName(transportTypePOJO);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : validateTypeName Ending");

		return null;
	}
	
	public synchronized ActionForward validateTypeNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : validateTypeNameUpdate Starting");

		try {
			
			String typeName = request.getParameter("typeName");
			String typeId = request.getParameter("typeId");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setTransptyname(typeName);
			transportTypePOJO.setTransptyId(typeId);
			
			String result = new TransportTypeBD().validateTypeName(transportTypePOJO);
			
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : validateTypeNameUpdate Ending");

		return null;
	}
	public synchronized ActionForward Add(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : Add Starting");

		try {
			
			String typeName = request.getParameter("typeName");
			String typeDescription = request.getParameter("typeDescription");
			String collectionType = request.getParameter("collectionType");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setTransptyname(typeName);
			transportTypePOJO.setTransptydes(typeDescription);
			transportTypePOJO.setType(collectionType);
			
			transportTypePOJO.setTransptyId(IDGenerator.getPrimaryKeyID("transport_typedetails"));
			transportTypePOJO.setCreatedby(HelperClass.getCurrentUserID(request));
		
			String result = new TransportTypeBD().AddTransportType(transportTypePOJO);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : Add Ending");

		return null;
	}
	public synchronized ActionForward Update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : Update Starting");

		try {
			
			String typeName = request.getParameter("typeName");
			String typeDescription = request.getParameter("typeDescription");
			String collectionType = request.getParameter("collectionType");
			String typeId = request.getParameter("typeId");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setTransptyname(typeName);
			transportTypePOJO.setTransptydes(typeDescription);
			transportTypePOJO.setType(collectionType);
			
			transportTypePOJO.setTransptyId(typeId);
			transportTypePOJO.setCreatedby(HelperClass.getCurrentUserID(request));
		
			String result = new TransportTypeBD().UpdateTransportType(transportTypePOJO);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : Update Ending");

		return null;
	}
	
	public synchronized ActionForward editType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : editType Starting");

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			
			String TypeCode = request.getParameter("TypeCode");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setTransptyId(TypeCode);
			
			TransportTypeVO transportTypeVO =new TransportTypeBD().editType(transportTypePOJO);
			
			request.setAttribute("transportTypeVO", transportTypeVO);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : editType Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_TYPE_ENTRY);
	}

	
	public synchronized ActionForward deleteType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : deleteType Starting");
		
		String name = request.getParameter("getDataArray");//Name should be according to js name4
		String getDataArray[]=name.split(",");

		try {
			
			String typeCode = request.getParameter("typeCode");
			
			TransportTypePOJO transportTypePOJO = new  TransportTypePOJO();
			transportTypePOJO.setGetDataArray(getDataArray);
			transportTypePOJO.setTransptyId(typeCode);
			
			String result = new TransportTypeBD().deleteType(transportTypePOJO);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : deleteType Ending");

		return null;
	}
	
	
	public synchronized ActionForward downloadTransportTypeXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : downloadTransportTypeXLS Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/TransportTypeXSLReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			ArrayList<TransportTypeVO> transporttypelist = new ArrayList<TransportTypeVO>();

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm != null) {

				TransportTypePOJO transportTypePOJO = new TransportTypePOJO();
				transportTypePOJO.setSearchtext(searchTerm);

				transporttypelist = new TransportTypeBD().getSearchDetails(transportTypePOJO);
				request.setAttribute("searchtransporttype", searchTerm);

			} else {

				transporttypelist = new TransportTypeBD().getAllTransportypeDetails();
			}
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					transporttypelist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TransportTypeXSLReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Transport Type Details" };
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
					request.getRealPath("Reports/TransportTypeXSLReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TransportTypeXSLReport.xls");
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeAction : downloadTransportTypeXLS Ending");

		return null;
	}

	public ActionForward downloadTransportTypePDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportTypeAction : downloadTransportTypePDF  Starting");

			try {

				System.out.println("downloading pdf");

				ArrayList<TransportTypeVO> transporttypelist = new ArrayList<TransportTypeVO>();

				String searchTerm = request.getParameter("searchTerm");

				if (searchTerm != null) {

					TransportTypePOJO transportTypePOJO = new TransportTypePOJO();
					transportTypePOJO.setSearchtext(searchTerm);

					transporttypelist = new TransportTypeBD().getSearchDetails(transportTypePOJO);
					request.setAttribute("searchtransporttype", searchTerm);

				} else {

					transporttypelist = new TransportTypeBD().getAllTransportypeDetails();
				}
					
				String sourceFileName = request
						.getRealPath("Reports/transporttypeDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						transporttypelist);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "TransportTypeDetailsPDF - " + ".pdf\"");

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
					+ " Control in TransportTypeAction : downloadTransportTypePDF  Ending");
			return null;

		}

	
}
