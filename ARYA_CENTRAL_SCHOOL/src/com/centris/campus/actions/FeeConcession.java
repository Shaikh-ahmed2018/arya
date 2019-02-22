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

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.FeeSetupBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.FeeConcessionVO;

public class FeeConcession extends DispatchAction {
	private static final Logger logger = Logger.getLogger(FeeConcession.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward Delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Starting");
		try {

			System.out.println("Delete fee Action Is Working");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			String concessionid = request.getParameter("concession_name");

			FeeConcessionVO vo = new FeeConcessionVO();

			vo.setConcessionId(concessionid);

			System.out.println("concessionid" + concessionid);

			String status = new FeeSetupBD().deleteconcession(vo);

			System.out.println("Ststus:::" + status);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", status);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

		/*
		 * if (MessageConstants.PRINCIPLE_CHAR_SEQUENCE.contains(username
		 * .substring(0, 3))) { return null; return mapping
		 * .findForward(MessageConstants.PRINCIPLE_DESIGNATION_MASTER); } else {
		 * return null; return
		 * mapping.findForward(MessageConstants.DESIGNATION_MASTER); }
		 */

		return null;

	}

	public ActionForward getnamecount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception

	{

		System.out.println("getnamecount Concession  Action class working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : getnamecount Starting");

		boolean status = false;
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String percentage = request.getParameter("percentage");
		System.out.println(name);
		System.out.println(id);
		System.out.println(percentage);

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			FeeConcessionVO vo = new FeeConcessionVO();

			vo.setConcessionId(id);
			vo.setConcesionName(name);
			vo.setPercentage(percentage);

			FeeSetupBD delegate = new FeeSetupBD();

			status = delegate.getnamecount(vo);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("message", status);

			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : getnamecount Ending");

		return null;

	}

	public ActionForward EditConcesssionFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : EditConcesssionDetails  Starting");
		try {

			System.out.println("Edit Action for concession fee Is Working");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			ConcessionForm detailsForm = new ConcessionForm();

			detailsForm.setCreatedby(HelperClass.getCurrentUserID(request));

			String name = request.getParameter("name");

			detailsForm.setConcessionId(name);

			System.out.println(name);

			String createUser = HelperClass.getCurrentUserID(request);

			System.out.println(createUser);

			ConcessionForm detailsBD = new FeeSetupBD()
					.EditConcesssionDetails(detailsForm);

			request.setAttribute("ConcessionList", detailsBD);

			/*
			 * 
			 * 
			 * System.out.println("Edit fee concession is working");
			 * 
			 * ConcessionForm detailsForm = new ConcessionForm();
			 * 
			 * detailsForm.setCreatedby(HelperClass.getCurrentUserID(request));
			 * 
			 * String concessionName = request.getParameter("name");
			 * 
			 * detailsForm.setConcessionId(concessionName);
			 * 
			 * System.out.println("id"+concessionName);
			 * 
			 * String modifyUser = HelperClass.getCurrentUserID(request);
			 * 
			 * 
			 * String description = request.getParameter("description");
			 * 
			 * detailsForm.setDescription(description);
			 * 
			 * System.out.println(description);
			 * 
			 * String percentage = request.getParameter("percentage");
			 * 
			 * detailsForm.setPercentage(percentage);
			 * 
			 * System.out.println(percentage);
			 * 
			 * 
			 * System.out.println("modifyUser"+modifyUser);
			 * 
			 * detailsForm.setModifyUser(modifyUser);
			 * 
			 * 
			 * ConcessionForm detailsBD = new
			 * FeeSetupBD().EditConcesssionDetails(detailsForm);
			 * 
			 * 
			 * boolean check = detailsBD. request.setAttribute("ConcessionList",
			 * detailsBD);
			 * 
			 * JSONObject jsonObject = new JSONObject();
			 * 
			 * if (check) {
			 * 
			 * request.getSession().setAttribute("error",
			 * "Concession  Already Exists"); jsonObject.accumulate("status",
			 * false);
			 * 
			 * } else {
			 * 
			 * request.getSession().setAttribute("message",
			 * "Concession  Updated Successfully");
			 * jsonObject.accumulate("status", true);
			 * 
			 * } response.getWriter().print(jsonObject);
			 */} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : EditConcesssionDetails Ending");

		return mapping.findForward(MessageConstants.ADD_FEE_CONCESSION);

	}

	public ActionForward ConcessionDetailsExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : ConcessionDetailsExcelReport Starting");

		System.out.println("Excel Downloading");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FeeConcessionDetailsExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			ConcessionDetailsPojo vo = new ConcessionDetailsPojo();

			vo.setConcessionName(request.getParameter("searchvalue"));

			List<ConcessionDetailsPojo> list = new FeeSetupBD()
					.getconcessiondetails(vo);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeeConcessionDetailsExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Fee Concession Details Excel Report" };
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
					request.getRealPath("Reports/FeeConcessionDetailsExcelReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeConcessionDetailsExcelReport.xls");
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
				+ " Control in FeeConcession : ConcessionDetailsExcelReport  Ending");
		return null;

	}

	public ActionForward ConcessionDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

		try {

			System.out.println("downloading pdf");

			ConcessionDetailsPojo vo = new ConcessionDetailsPojo();

			vo.setConcessionName(request.getParameter("searchvalue"));

			List<ConcessionDetailsPojo> list = new FeeSetupBD()
					.getconcessiondetails(vo);


			String sourceFileName = request
					.getRealPath("Reports/FeeConcessionDetailsPDF.jrxml");
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

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeConcessionDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
		return null;

	}

}
