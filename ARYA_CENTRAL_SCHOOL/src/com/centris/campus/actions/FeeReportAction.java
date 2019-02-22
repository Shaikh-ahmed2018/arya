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
import javax.xml.bind.ParseConversionEvent;

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
import com.centris.campus.delegate.FeeReportsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.forms.FeeReportform;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.feeReportVO;
import com.itextpdf.text.log.SysoLogger;

public class FeeReportAction extends DispatchAction{
	private static Logger logger = Logger.getLogger(ExamTimeTableAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward feeReportMainPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
				
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentsSMSAction: storeAbsentSms Starting");
			
			
			ReportMenuForm reform = (ReportMenuForm) form;
			FeeReportform f =  (FeeReportform) form;		//for displaying list
			
			
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%");
			request.setAttribute("classList", classList);


					
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in expensesDetailsReport : expensesDetailsReport Ending");
			return mapping.findForward(MessageConstants.Add_Fee_Report);
		
			}
	
	public ActionForward AccYearwiseDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editExam Starting");

		
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			
			String accyearid = request.getParameter("accyearid");
			 System.out.println("accyearid "+accyearid);
			JSONObject obj = new JSONObject();
			obj.put("enddate", HelperClass.getForDateofAcademicYear(accyearid));
			obj.put("startdate", HelperClass.getPastDateofAcademicYear(accyearid));
			
			response.getWriter().print(obj);
			
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : editExam Ending");

			return null;
			}
	
	
	public ActionForward getFeeReport(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getFeeReport Starting");
		
			String acyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String location = request.getParameter("location");
			String section = request.getParameter("section");
			String enddate = request.getParameter("enddate");
			String examdate = request.getParameter("examdate");
			
			feeReportVO obj = new feeReportVO();
			obj.setAccyearid(acyear);
			obj.setClassnameid(classid);
			obj.setLocationid(location);
			obj.setSectionid(section);
			obj.setEnddate(enddate);
			obj.setExamdate(examdate);
			
			
			ArrayList<feeReportVO> list = new FeeReportsBD().expandAll(obj);
		
			request.setAttribute("feelist",list);
			
			JSONObject object = new JSONObject();
			object.put("feelist",list);
			response.getWriter().print(object);
			
			request.getSession(false).setAttribute("feelist",list);
			

			request.getSession(false).setAttribute("AcademicYearID",list.get(0).getAccyear());
			request.getSession(false).setAttribute("ClassNameID",list.get(0).getClassname());	
			request.getSession(false).setAttribute("Strength",Integer.toString(list.size()));	
				
			
			

			System.out.println("academic year id action================ "+list.get(0).getAccyear());
			System.out.println("academic year id action ================ "+list.get(0).getClassname());


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getFeeReport Ending");

			return null;
	}	
	
	public ActionForward FeeExcelReport(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeExcelReport Starting");

		String acyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String location = request.getParameter("location");
		String section = request.getParameter("section");

		
		System.out.println("accyear----------->"+acyear);
		System.out.println("classid----------->"+classid);
		System.out.println("location----------->"+location);
		System.out.println("section----------->"+section);


		String filePath = null;

		try {
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FeeReportExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			

			Map parameters = new HashMap();
			
			parameters.put("acyear",request.getSession(false).getAttribute("AcademicYearID"));
			parameters.put("classid",request.getSession(false).getAttribute("ClassNameID"));
			parameters.put("Strength", request.getSession(false).getAttribute("Strength"));
			ArrayList<feeReportVO> list = (ArrayList<feeReportVO>)request.getSession(false).getAttribute("feelist");
			
		

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeeReport.xls"));
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
					request.getRealPath("Reports/FeeReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeReport.xls");
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
				+ " Control in ReportsAction : FeeExcelReport Ending");

		return null;  
	}
	
	public ActionForward FeePDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Starting");

			try {
				System.out.println("pdf download==================");

				ArrayList<feeReportVO> list = (ArrayList<feeReportVO>)request.getSession(false).getAttribute("feelist");
								
				String sourceFileName = request
						.getRealPath("Reports/FeeReportPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

				String PropfilePath = getServlet().getServletContext().getRealPath("")
						+ "\\images\\" + ImageName.trim();

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);
				
				parameters.put("acyear",request.getSession(false).getAttribute("AcademicYearID"));
				parameters.put("classid",request.getSession(false).getAttribute("ClassNameID"));
				parameters.put("strength", request.getSession(false).getAttribute("Strength"));
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "feeReport" + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}catch (Exception e){
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Ending");
			return null;
		}
	
	
	
}
		
