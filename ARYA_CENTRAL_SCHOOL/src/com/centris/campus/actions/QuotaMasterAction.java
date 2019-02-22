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

import com.centris.campus.delegate.QuotaMasterBD;
import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.QuotaMasterVO;

public class QuotaMasterAction extends DispatchAction {

	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	
	private static final Logger logger = Logger
			.getLogger(QuotaMasterAction.class);

	public ActionForward AddQuota(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : AddQuota Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		String title="Add New Quota";
		request.setAttribute("title", title);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : AddQuota  Ending");

		return mapping.findForward(MessageConstants.ADD_QUOTA);

	}

	public ActionForward insertQuotaDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : insertQuotaDetails Starting");
		String user = HelperClass.getCurrentUserID(request);

		try {

			String name = request.getParameter("quotaname");
			String desc = request.getParameter("quota_description");
			String quota_id = request.getParameter("quota_update_id");

			QuotaDetailsForms qForm = new QuotaDetailsForms();
			qForm.setQuotaname(name);
			qForm.setQuotadescription(desc);
			qForm.setQuotacode(quota_id);
			qForm.setCreated_by(user);
			qForm.setUpdated_by(user);

			String insert_quota = new QuotaMasterBD().insertQuotaDetails(qForm);

			request.setAttribute("status", insert_quota);

			JSONObject object = new JSONObject();
			object.put("status", insert_quota);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : insertQuotaDetails  Ending");

		return null;
	}

	public ActionForward deleteQuotaDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : deleteQuotaDetails   Starting");

		try {
			String quota_del_code = request.getParameter("quotaid").toString();

			QuotaMasterVO deletelist = new QuotaMasterVO();
			deletelist.setQuotaid(quota_del_code);

			String status = new QuotaMasterBD().deleteQuotaDetails(deletelist);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

			// request.setAttribute("QUOTA_DETAILS", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : deleteQuotaDetails  Ending");

		return null;

	}

	public ActionForward editQuotaDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : editQuotaDetails   Starting");

		try {
			
			String title="Modify Quota";
			request.setAttribute("title", title);
			
			String edit_code = request.getParameter("edit_name").toString();

			QuotaMasterPojo editlist = new QuotaMasterPojo();
			editlist.setQuotaid(edit_code);

			QuotaMasterPojo status = new QuotaMasterBD()
					.editQuotaDetails(editlist);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

			request.setAttribute("QUOTA_DETAILS", status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : editQuotaDetails  Ending");

		return mapping.findForward(MessageConstants.ADD_QUOTA);

	}

	public ActionForward searchQuota(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : searchQuota Starting");

		try {
			String Search_Name = request.getParameter("searchname");
			String SearchName = Search_Name.trim();

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			QuotaMasterVO searchvo = new QuotaMasterVO();
			searchvo.setSearch_name(SearchName);
			ArrayList<QuotaMasterVO> getquotalist = new QuotaMasterBD()
					.searchQuota(searchvo);

			request.setAttribute("Quotalist", getquotalist);

			request.setAttribute("searchdetails", SearchName);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : searchQuota Ending");

		return mapping.findForward(MessageConstants.QUOTA_DETAILS);

	}

	public ActionForward validateQuotaName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : validateQuotaName: Starting");

		try {
			String quota_name = request.getParameter("quotaname");
			String quota_id = request.getParameter("quotaid");

			QuotaDetailsForms quotaform = new QuotaDetailsForms();

			quotaform.setQuotaname(quota_name);
			quotaform.setQuotacode(quota_id);

			boolean quotaname_available = new QuotaMasterBD()
					.validateQuotaName(quotaform);

			JSONObject object = new JSONObject();
			object.put("des_available", quotaname_available);
			response.getWriter().print(quotaname_available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : validateQuotaName: Ending");
		return null;
	}

	public ActionForward validateQuotaNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction: validateQuotaNameUpdate: Starting");

		try {
			String quota_name = request.getParameter("quotaname");
			String quota_id = request.getParameter("quotaid");

			QuotaDetailsForms validateupdate = new QuotaDetailsForms();

			validateupdate.setQuotaname(quota_name);
			validateupdate.setQuotacode(quota_id);

			boolean desname_available = new QuotaMasterBD()
					.validateQuotaNameUpdate(validateupdate);

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
				+ " Control in QuotaMasterAction: validateQuotaNameUpdate: Ending");
		return null;
	}

	public ActionForward downloadQuotaDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : downloadQuotaDetailsXLS  Starting");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/QuotaDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			
		/*	List<QuotaMasterVO> QuotaMasterList = new ArrayList<QuotaMasterVO>();
			QuotaMasterList = (List<QuotaMasterVO>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/

			
            ArrayList<QuotaMasterVO> quota_list = new ArrayList<QuotaMasterVO>();
				

			
			QuotaMasterVO searchvo = new QuotaMasterVO();
			String SearchName = request.getParameter("searchTerm");
			
			
			
			searchvo.setSearch_name(SearchName);
			
			if(SearchName != null){
				
				quota_list = new QuotaMasterBD().searchQuota(searchvo);
				request.setAttribute("searchdetails", SearchName);
				
				request.setAttribute("searchnamelist", SearchName);
			}
			else{
				
				
				
				 quota_list = new QuotaMasterBD().getQuotaDetails();
			}
			
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					quota_list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/QuotaDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Quota details Excel Report" };
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
					request.getRealPath("Reports/QuotaDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=QuotaDetailsXLSReport.xls");
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
				+ " Control in QuotaMasterAction : downloadQuotaDetailsXLS  Ending");
		return null;

	}
	
	
	
	
	public ActionForward downloadQuotaDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterAction : downloadQuotaDetailsPDF  Starting");
		
		
		
		try {
			
			
			/*List<QuotaMasterVO> QuotaMasterList = new ArrayList<QuotaMasterVO>();
			
			
			QuotaMasterList = new QuotaMasterBD()
			.getQuotaDetails();*/
			
			/*List<QuotaMasterVO> QuotaMasterList = new ArrayList<QuotaMasterVO>();
			QuotaMasterList = (List<QuotaMasterVO>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/
			
			
			
			  ArrayList<QuotaMasterVO> quota_list = new ArrayList<QuotaMasterVO>();
				

				
				QuotaMasterVO searchvo = new QuotaMasterVO();
				String SearchName = request.getParameter("searchTerm");
				
				
				
				searchvo.setSearch_name(SearchName);
				
				if(SearchName != null){
					
					quota_list = new QuotaMasterBD().searchQuota(searchvo);
					request.setAttribute("searchdetails", SearchName);
					
					request.setAttribute("searchnamelist", SearchName);
				}
				else{
					
					
					
					 quota_list = new QuotaMasterBD().getQuotaDetails();
				}
				
				
			
			
			String sourceFileName = request
					.getRealPath("Reports/QuotaDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(quota_list);

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
					+ "QuotaDetailsPDFReport - " + ".pdf\"");

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
				+ " Control in QuotaMasterAction : downloadQuotaDetailsPDF  Ending");
		return null;

	}
	
	
	
	
	
}
