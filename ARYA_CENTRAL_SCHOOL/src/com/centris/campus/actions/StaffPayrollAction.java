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
import net.sf.jasperreports.engine.JasperExportManager;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.delegate.StaffPayrollBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;

public class StaffPayrollAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(StaffPayrollAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static String Client_ImageName = res.getString("Client_Image");

	private static String ClientName = res.getString("ClientName");
	private static String ClientAddress_l1 = res
			.getString("ClientAddress_Line_1");
	private static String ClientAddress_l2 = res
			.getString("ClientAddress_Line_2");
	private static String EcampusPro_Payslip_Dir = res
			.getString("EcampusPro_Payslip_Dir");

	public ActionForward getPayrolEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : getPayrolEntry Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_SALARYDETAILS);

			String payroldetails = request.getParameter("teachercode");

			String month = payroldetails.split(",")[0].trim();
			String year = payroldetails.split(",")[1].trim();

			String userId = HelperClass.getCurrentUserID(request);

			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("monthname",
					HelperClass.getMonthFullName(month));

			ArrayList<PayRollVo> payroll_list = new StaffPayrollBD()
					.getFlatpayRollProcess(year, month, userId);

			request.setAttribute("payrollList", payroll_list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : getPayrolEntry Ending");

		return mapping.findForward(MessageConstants.STAFF_PAYROLL_ENTRY);
	}

	public ActionForward payrollExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : payrollExcelReport Starting");

		int month = Integer.parseInt(request.getParameter("month").trim());
		int year = Integer.parseInt(request.getParameter("year").trim());

		ArrayList<PayRollVo> payroll_list = new StaffPayrollBD()
				.getPayrollDetails(month, year);

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/PayrollExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					payroll_list);

			Map parameters = new HashMap();
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/PayrollExcelReport.jrxml"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StaffPayroll" };
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
					request.getRealPath("Reports/PayrollExcelReport.jrxml"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffPayroll.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : payrollExcelReport Ending");

		return null;

	}

	public ActionForward payrollPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : payrollPDFReport Starting");

		try {

			int month = Integer.parseInt(request.getParameter("month").trim());
			int year = Integer.parseInt(request.getParameter("year").trim());

			List<PayRollVo> payrollList = new StaffPayrollBD()
					.getPayrollDetails(month, year);

			JSONArray array = new JSONArray();
			array.put(payrollList);

			System.out.println("array :: " + array);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("month", month);
			mapdata.put("year", year);

			String filepath = request
					.getRealPath("Reports/PayrollPdfReport.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					payrollList);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "PayrollReport - " + ".pdf\"");
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
				+ " Control in StaffPayrollAction : payrollPDFReport Ending");

		return null;
	}

	public ActionForward createPayslip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in payrollPDFReport : createPayslip: Starting");

		System.out.println("create pay slip calling");

		String payslipDetails = request.getParameter("psyslipId");

		String empId = payslipDetails.split(",")[0];
		String month = payslipDetails.split(",")[1];
		String year = payslipDetails.split(",")[2];

		System.out.println("employee arrray :: " + payslipDetails);

		try {
			ServletOutputStream stream = null;
			String sourceFileName = request
					.getRealPath("Reports/CreatePayslip.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			ArrayList<PayRollVo> payslipList = new StaffPayrollBD()
					.getEmpMonthPayrollDetails(month, year, empId);
			FileOutputStream outputFile = null;
			File secondDir = null;
			File firstDir = null;
			JRBeanCollectionDataSource beanColDataSource = null;

			firstDir = new File(EcampusPro_Payslip_Dir);
			if (firstDir.exists()) {
				secondDir = new File(EcampusPro_Payslip_Dir + "/" + year + "-"
						+ HelperClass.getMonthName(month));
				secondDir.mkdir();
			} else {
				new File(EcampusPro_Payslip_Dir + "/" + year + "-"
						+ HelperClass.getMonthName(month)).mkdirs();
			}

			for (int i = 0; i < payslipList.size(); i++) {

				beanColDataSource = new JRBeanCollectionDataSource(payslipList);

				String imageFilePath = null;

				imageFilePath = getServlet().getServletContext().getRealPath(
						"/images/" + Client_ImageName);

				System.out.println("image path :: " + imageFilePath);
				Map parameters = new HashMap();
				parameters.put("GhatakImage", imageFilePath);
				parameters.put("monthName", HelperClass.getMonthName(month));
				parameters.put("year", year);
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						parameters, beanColDataSource);
				outputFile = new FileOutputStream(new File(
						EcampusPro_Payslip_Dir
								+ "/"
								+ year
								+ "-"
								+ HelperClass.getMonthName(month)
								+ "/"
								+ payslipList.get(0).getEmpId()
								+ "-"
								+ payslipList.get(0).getEmpName().trim()
										.replaceAll(" ", "_") + ".pdf"));
				JasperExportManager.exportReportToPdfStream(print, outputFile);
				outputFile.close();
			}

			JSONObject object = new JSONObject();

			if (payslipList.size() != 0) {

				object.put("SuccessMessage", "Payroll generated for "
						+ payslipList.get(0).getEmpName()
						+ ", Check in D:\\EcampusPro_Payslip folder");
				response.getWriter().print(object);

			} else {

				object.put("errorMessage", "Payroll not generated for "
						+ payslipList.get(0).getEmpName() + ",Try later");
				response.getWriter().print(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in payrollPDFReport : createPayslip: Ending");

		return null;
	}

	public ActionForward downloadpayrolllistingXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : downloadpayrolllistingXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/staffpayrolllistingdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String month = null;
			String year = null;
			ArrayList<StaffPayrollListVo> list = new StaffPayrollBD().getPayrollList(year, month);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StaffPayrollListingDetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Staff Payroll Listing Details Excel Report" };
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
					request.getRealPath("Reports/StaffPayrollListingDetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffPayrollListingDetailsxls.xls");
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
				+ " Control in StaffPayrollAction : downloadpayrolllistingXLS   Ending");
		return null;

	}

	public ActionForward downloadpayrolllistingPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayrollAction : downloadpayrolllistingPDF  Starting");

		try {

			System.out.println("downloading pdf");

			String month = null;
			String year = null;
			ArrayList<StaffPayrollListVo> Details = new StaffPayrollBD().getPayrollList(year, month);


			String sourceFileName = request
					.getRealPath("Reports/staffpayrolllistingDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					Details);

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
					+ "StaffPayrollListingDetailsPDF - " + ".pdf\"");

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
				+ " Control in StaffPayrollAction : downloadpayrolllistingPDF  Ending");
		return null;

	}

}
