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
import com.centris.campus.delegate.AcademicYearMasterBD;
import com.centris.campus.delegate.CareersViewdelegate;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.CareersViewVo;

public class CareerPopulateAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(CareerPopulateAction.class);

	public CareerPopulateAction() {
		super();

	}

	CareersViewdelegate careerview;
	String result;
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");

	public ActionForward addJobs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction : addJobs Starting");

		try {

			CareersViewPojo pojo = new CareersViewPojo();

			pojo.setTitle(request.getParameter("title"));
			pojo.setQualification(request.getParameter("qualification"));
			pojo.setExperience(request.getParameter("experience"));
			pojo.setDescription(request.getParameter("description"));
			pojo.setNoofposition(request.getParameter("noofposition"));
			pojo.setStatus(request.getParameter("status"));
			pojo.setCreatedby(HelperClass.getCurrentUserID(request));

			if ("NULL".equalsIgnoreCase(request.getParameter("jobid"))) {
				pojo.setJobcode(IDGenerator.getPrimaryKeyID("campus_careers"));
				pojo.setCheck("NULL");
			} else {
				pojo.setJobcode(request.getParameter("jobid"));
			}
			careerview = new CareersViewdelegate();
			String status = new CareersViewdelegate().addJobs(pojo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction : addJobs Ending");

		return null;
	}

	public ActionForward careerView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction:careerView Starting");
		try {
			
			
			String title = "Modify Internal Job Posting";
			request.setAttribute("title", title);
			
			CareersViewPojo pojo = new CareersViewPojo();

			pojo.setJobcode(request.getParameter("Code"));

			careerview = new CareersViewdelegate();
			CareersViewVo listcareview = careerview.getValues(pojo);

			request.setAttribute("listcareview", listcareview);
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_INTERNALJOBPOSTING);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction:careerView Ending");
		return mapping.findForward(MessageConstants.ADDJOB);
	}

	public ActionForward careerDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction:careerDelete Starting");
		try {
			CareersViewPojo pojo = new CareersViewPojo();

			pojo.setJobcode(request.getParameter("Code"));

			careerview = new CareersViewdelegate();
			boolean status = careerview.getDelete(pojo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareerPopulateAction:careerDelete Ending");
		return null;
	}

	public ActionForward checkTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareerPopulateAction:checkTitle Starting");
		try {
			CareersViewPojo pojo = new CareersViewPojo();

			pojo.setJobcode(request.getParameter("jobid"));
			pojo.setTitle(request.getParameter("title"));

			careerview = new CareersViewdelegate();
			boolean status = careerview.checkTitle(pojo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareerPopulateAction:checkTitle Ending");
		return null;
	}

	
	public ActionForward InternaljobXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareerPopulateAction : InternaljobXLS  Starting");
		
		try {
		
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/JobDetailsXLSReports.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			CareersViewdelegate careerview;
			
				List<CareersViewVo> career = new ArrayList<CareersViewVo>();

				careerview = new CareersViewdelegate();

				String searchTerm = request.getParameter("searchTerm");
				
				
				if (searchTerm != null) {

					career = careerview.searchDetails(searchTerm);
					request.setAttribute("searchnamelist", searchTerm);
					
				} else {
					career = careerview.getAllcareerdetails();
				}
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					career);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/JobDetailsXLSReports.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Job Details Excel Report" };
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
					request.getRealPath("Reports/JobDetailsXLSReports.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=JobDetailsXLSReports.xls");
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
				+ " Control in  CareerPopulateAction : InternaljobXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward InternaljobPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  CareerPopulateAction : InternaljobPDFReport  Starting");

			try {

				CareersViewdelegate careerview;
				
					List<CareersViewVo> career = new ArrayList<CareersViewVo>();

					careerview = new CareersViewdelegate();

					String searchTerm = request.getParameter("searchTerm");
					
					
					

					if (searchTerm != null) {

						career = careerview.searchDetails(searchTerm);
						request.setAttribute("searchnamelist", searchTerm);
						
					} else {
						career = careerview.getAllcareerdetails();
					}
			
				String sourceFileName = request
						.getRealPath("Reports/JobDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						career);

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
						+ "JobDetailsPDF - " + ".pdf\"");

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
					+ " Control in  CareerPopulateAction : InternaljobPDFReport  Ending");
			return null;

		}
}