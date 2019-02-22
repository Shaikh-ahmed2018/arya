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

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.FeeCollectionSummaryReportBD;
import com.centris.campus.delegate.FeeSetupBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TermMasterVo;

public class FeeCollectionSummaryReportAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(AcadamicYearPlanAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public synchronized ActionForward getFeeCollectionSummaryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : getFeeCollectionSummaryDetails Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			
			ArrayList<ReportMenuVo> classList=new ReportsMenuBD().getClassesByStream("%%");
			
			TermMasterVo vo = new TermMasterVo();
		
			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
			
			
			request.setAttribute("TermList", termlist);
			
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("classList", classList);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : getFeeCollectionSummaryDetails Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_SUMMARY_REPORT);
	}
	
	
	public synchronized ActionForward getFeecollectionSummaryReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : getFeecollectionSummaryReport Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			FeeCollectionSummaryReportForm feeReportForm=(FeeCollectionSummaryReportForm)form;
			
			System.out.println("feeReportForm acc id "+feeReportForm.getAccyear());
			System.out.println("feeReportForm acc name "+feeReportForm.getHaccyear());
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			
			ArrayList<ReportMenuVo> classList=new ReportsMenuBD().getClassesByStream("%%");
			
			TermMasterVo vo = new TermMasterVo();
		
			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
			
			
			request.setAttribute("TermList", termlist);
			
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("classList", classList);
			
			request.setAttribute("feeReportForm", feeReportForm);
			
			 ArrayList<FeeReportDetailsVo> feeCollectionReport=new FeeCollectionSummaryReportBD().getClassFeeSummaryReport(feeReportForm); 
			
			 request.setAttribute("feeCollectionReport", feeCollectionReport);
			
			 double tot_actual_amt=0;
			 double tot_paid_amt=0;
			 double tot_balance_amt=0;
			 
			 for(int i=0;i<feeCollectionReport.size();i++){
				 
				 tot_actual_amt=tot_actual_amt+feeCollectionReport.get(i).getActualAmount();
				 tot_paid_amt=tot_paid_amt+feeCollectionReport.get(i).getPaidAmount();
				 tot_balance_amt=tot_balance_amt+feeCollectionReport.get(i).getBalanceAmount();
			 }
			 
			 request.setAttribute("tot_actual_amt", tot_actual_amt);
			 request.setAttribute("tot_paid_amt", tot_paid_amt);
			 request.setAttribute("tot_balance_amt", tot_balance_amt);
			 
			 request.setAttribute("feeReportForm", feeReportForm);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : getFeecollectionSummaryReport Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_SUMMARY_REPORT);
	}
	
	
	
	public ActionForward feeCollectionExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : feeCollectionExcelReport Starting");
		
		String accYear=request.getParameter("AccId");
		String Classid=request.getParameter("ClassId");
		String SectionId=request.getParameter("SectionId");
		String TermId=request.getParameter("TermId");
		String TermName=request.getParameter("TermName");
		
		System.out.println("TermName :: "+TermName);
		
		String filePath = null;

		try {
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			
			FeeCollectionSummaryReportForm feeReportForm=new FeeCollectionSummaryReportForm();
			
			feeReportForm.setAccyear(accYear);
			feeReportForm.setClassname(Classid);
			feeReportForm.setSection(SectionId);
			feeReportForm.setTerm(TermId);
			
			 ArrayList<FeeReportDetailsVo> feeCollectionReport=new FeeCollectionSummaryReportBD().getClassFeeSummaryReport(feeReportForm);

			String sourceFileName = request
					.getRealPath("Reports/ClassFeeSummary.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("termName", TermName);
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(feeCollectionReport);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TransportDetailsReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(
					jasperreport, parameters, beanColDataSource);
			String[] sheetNames = { TermName
					+ " Class FeeSummary  Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
					print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					fos);
			exporter.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
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
			exporter.setParameter(
					JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/TransportDetailsReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Class Fee Summary Report.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : feeCollectionExcelReport Ending");

		return null;
	}
	
	public ActionForward feeCollectionPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : feeCollectionPdfReport Starting");

		String accYear=request.getParameter("AccId");
		String Classid=request.getParameter("ClassId");
		String SectionId=request.getParameter("SectionId");
		String TermId=request.getParameter("TermId");
		String TermName=request.getParameter("TermName");
		



		try {

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("termName", TermName);
			
			FeeCollectionSummaryReportForm feeReportForm=new FeeCollectionSummaryReportForm();
			
			feeReportForm.setAccyear(accYear);
			feeReportForm.setClassname(Classid);
			feeReportForm.setSection(SectionId);
			feeReportForm.setTerm(TermId);
			
			 ArrayList<FeeReportDetailsVo> feeCollectionReport=new FeeCollectionSummaryReportBD().getClassFeeSummaryReport(feeReportForm);
			
			String sourceFileName = request.getRealPath("Reports/ClassFeeSummaryPdf.jrxml");
			
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(feeCollectionReport);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport, mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeCollectionDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeCollectionSummaryReportAction : feeCollectionPdfReport Ending");

		return null;

	}
	
}
