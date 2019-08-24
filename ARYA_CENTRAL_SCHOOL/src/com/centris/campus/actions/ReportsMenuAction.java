package com.centris.campus.actions;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.Session;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRGenericPrintElementParameterFactory.Parameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.bsf.debug.jsdi.JsObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.Request;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.daoImpl.createFeeReportDaoImpl;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.FeeCollectionSummaryReportBD;
import com.centris.campus.delegate.FeeReportsBD;
import com.centris.campus.delegate.LibraryDelegate;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TimeTableBD;
import com.centris.campus.forms.FeeCollectionSummaryReportForm;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.util.CustomizableStudentReportExcell;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;
import com.itextpdf.text.log.SysoLogger;

public class ReportsMenuAction extends DispatchAction {

	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	private static String BoardLogo = res.getString("boardlogo");
	private static String SchoolName = res.getString("SchoolName");
	private static final Logger logger = Logger
			.getLogger(ReportsMenuAction.class);
	
	
	public ActionForward studentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			
			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
 
			request.setAttribute("AccYearList", accYearList);

			

			request.setAttribute("StreamList", streamList);
			request.setAttribute("locationList", locationList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_REPORT);
	}

	public ActionForward getClassesByStream(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStream Starting");
		try {

		

			String streamId = request.getParameter("streamId");

			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD()
					.getClassesByStream(streamId);
			
			JSONObject object = new JSONObject();
			object.put("ClassesList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStream Ending");

		return null;
	}

	public ActionForward getSectionByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClass Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
		
			String classId = request.getParameter("classId");
			String location=request.getParameter("location");
			
		
			
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD().getSectionsByClass(classId,location);

			JSONObject object = new JSONObject();
			object.put("SectionList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClass Ending");

		return null;
	}

	public ActionForward getStudentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD().getStudentDetailsReport(reform);
			request.setAttribute("StudentInfoList", studentInfoList);
			
			
			ReportMenuVo selectedItems = new ReportsMenuBD().getSelectedItems(reform);
			request.setAttribute("CurrentForm", selectedItems);

	

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_REPORT);
	}

	public ActionForward studentDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : certificateReport Starting");

		String accYear = request.getParameter("AccId");
		String stream = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		
		try {

			String PropfilePath =request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("accYear", accYear);
			mapdata.put("stream", stream);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			String filepath = request.getRealPath("Reports/StudentDetailsPDFReport.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection());

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentDetails" + ".pdf\"");

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
				+ " Control in ReportsAction : certificateReport Ending");
	
		return null;

	}

	public ActionForward studentDetailsExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentAttendanceReportDisplay Starting");

		String accYear = request.getParameter("AccId");
		String streamId = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		String filePath = null;

		try {

			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("stream", streamId);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentDetailsExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, JDBCConnection.getConnection());
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
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

			pdfxls = new File(request.getRealPath("Reports/StudentDetails.xlsx"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentDetails.xlsx");
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
				+ " Control in ReportsAction : getNonBoardSalaryNonAccountReportExcel Ending");

		return null;
	}

	public ActionForward studentFeeStatusReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentFeeStatusReport Starting");
		try {

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_CURRENTSTUDENTFEEPAYMENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%");

			TermMasterVo vo = new TermMasterVo();

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
					.termList(vo);

			request.setAttribute("TermList", termlist);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentFeeStatusReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_FEE_STATUS_REPORT);
	}
	
	public ActionForward defaultFeeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : defenderFeeReport Starting");
		try{
			
	/*		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEE_DEFAULTER_LIST);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);*/
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEE_DEFAULTER_LIST);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accyear = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accyear);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : defenderFeeReport Ending");

		return mapping.findForward(MessageConstants.defenderFeeReport);
		
	}

	public ActionForward getStdFeeStatusReportDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

/*			System.out.println("accyear :: " + reportForm.getAccyear() + " -- "
					+ reportForm.getHaccyear());
			System.out.println("class :: " + reportForm.getClassname() + " -- "
					+ reportForm.getHclass());
			System.out.println("section :: " + reportForm.getSection() + " -- "
					+ reportForm.getHsection());
			System.out.println("Term :: " + reportForm.getTerm() + " -- "
					+ reportForm.getHterm());
			System.out.println("Status :: " + reportForm.getStatus());
*/	
			
			
			reportForm.setStatus("ALL");
			request.setAttribute("reportForm", reportForm);

			HashMap<String, ArrayList<FeeReportDetailsVo>> feeStatusReport = new ReportsMenuBD()
					.getStdFeeStatusReportDetails(reportForm);

			request.setAttribute("feeStatusReport", feeStatusReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			TermMasterVo vo = new TermMasterVo();

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
					.termList(vo);

			request.setAttribute("TermList", termlist);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_FEE_STATUS_REPORT);
	}

	public ActionForward feeStatusExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : feeStatusPdfReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String TermId = request.getParameter("TermId");
		String TermName = request.getParameter("TermName");
		String Status = request.getParameter("Status");
        String student=request.getParameter("studentname");
        if(student==null){
        	student="%%";
        }
		
		String filePath = null;

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			FeeStatusReportPojo feeStatusPojo = new FeeStatusReportPojo();

			feeStatusPojo.setAccyear(accYear);
			feeStatusPojo.setClassname(Classid);
			feeStatusPojo.setSection(SectionId);
			feeStatusPojo.setTerm(TermId);
			feeStatusPojo.setStatus(Status);
			feeStatusPojo.setStudent(student);
			
			ArrayList<FeeReportDetailsVo> feeStatusList = new ReportsMenuBD()
					.getStdFeeStatusReportDownload(feeStatusPojo);

			String sourceFileName = request
					.getRealPath("Reports/StudentFeeStatusReportExcel.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("termName", TermName);
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusList);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TransportDetailsReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { TermName + " Class FeeSummary  Report" };
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
				+ " Control in FeeCollectionSummaryReportAction : feeStatusPdfReport Ending");

		return null;
	}

	public ActionForward feeStatusPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : feeStatusExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String TermId = request.getParameter("TermId");
		String TermName = request.getParameter("TermName");
		String Status = request.getParameter("Status");
		 String student=request.getParameter("studentname");
		 if(student==null){
			 student="%%";
		 }
		 
		try {

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("termName", TermName);

			
			String SchoolName = res.getString("SchoolName");
			String AddressLine1 = res.getString("AddressLine1");


			mapdata.put("SchoolName", SchoolName);
			mapdata.put("AddressLine1", AddressLine1);
			FeeStatusReportPojo feeStatusPojo = new FeeStatusReportPojo();

			feeStatusPojo.setAccyear(accYear);
			feeStatusPojo.setClassname(Classid);
			feeStatusPojo.setSection(SectionId);
			feeStatusPojo.setTerm(TermId);
			feeStatusPojo.setStatus(Status);
			feeStatusPojo.setStudent(student);

			ArrayList<FeeReportDetailsVo> feeStatusList = new ReportsMenuBD()
					.getStdFeeStatusReportDownload(feeStatusPojo);

			String sourceFileName = request
					.getRealPath("Reports/StudentFeeStatusReportPdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusList);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);

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
				+ " Control in ReportsAction : feeStatusExcelReport Ending");

		return null;

	}

	public ActionForward marksDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : marksDetailsReport Starting");
		try {

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_MARKSDETAILS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD()
					.getexamdeligate();

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : marksDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_MARKS_DETAILS);
	}

	public ActionForward getStudentBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Starting");
		try {

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			List<ParentVO> studentList = new StudentAttendanceBD()
					.getAllStudent(classId, sectionId);

			JSONObject object = new JSONObject();
			object.put("studentList", studentList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Ending");

		return null;
	}

	public ActionForward getStdMarksDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdMarksDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

			request.setAttribute("reportForm", reportForm);

			HashMap<String, ArrayList<MarksUploadVO>> marksDetailsReport = new ReportsMenuBD()
					.getStdMarksDetails(reportForm);

			request.setAttribute("marksDetailsReport", marksDetailsReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD()
					.getexamdeligate();

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdMarksDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_MARKS_DETAILS);
	}

	public ActionForward studentMarksExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : feeStatusPdfReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		String filePath = null;

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);

			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo);

			String sourceFileName = request
					.getRealPath("Reports/StudentMarksExcel.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					marksList);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentMarksReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { "Student Marks Report" };
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
					request.getRealPath("Reports/StudentMarksReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Student Marks Report.xls");
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
				+ " Control in FeeCollectionSummaryReportAction : feeStatusPdfReport Ending");

		return null;
	}

	public ActionForward studentMarksPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : feeStatusExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		try {

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);

			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo);
			String sourceFileName = request
					.getRealPath("Reports/StudentMarksPdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					marksList);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentMarksDetails - " + ".pdf\"");

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
				+ " Control in ReportsAction : feeStatusExcelReport Ending");

		return null;

	}

	public ActionForward studentMarksGraph(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentMarksGraph Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		try {

			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);

			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo);

			JSONObject object = new JSONObject();
			object.put("MARKSDETAILS", marksList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentMarksGraph Ending");

		return null;

	}

	public ActionForward examReportClassWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWise Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_EXAMREPORTCLASSWISE);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWise Ending");
		return mapping.findForward(MessageConstants.EXAM_CLASSWISE_REPORT);
	}

	public ActionForward examReportClassWiseDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWiseDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

			request.setAttribute("reportForm", reportForm);

			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(reportForm);

			request.setAttribute("examDetailsReport", examDetailsReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWiseDetails Ending");

		return mapping.findForward(MessageConstants.EXAM_CLASSWISE_REPORT);
	}

	public ActionForward classWiseExamExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionSummaryReportAction : classWiseExamExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String ClassName = request.getParameter("ClassName");

		String filePath = null;

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			ReportMenuForm rform = new ReportMenuForm();
			rform.setAccyear(accYear);
			rform.setClassname(Classid);

			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(rform);

			String sourceFileName = request
					.getRealPath("Reports/ClassWiseExamExcelReport.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ClassName", ClassName);
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examDetailsReport);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ClassWiseExamExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { "Class Wise Exam Report" };
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
					request.getRealPath("Reports/ClassWiseExamExcelReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Student Marks Report.xls");
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
				+ " Control in FeeCollectionSummaryReportAction : classWiseExamExcelReport Ending");

		return null;
	}

	public ActionForward classWiseExamPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : feeStatusExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String ClassName = request.getParameter("ClassName");

		try {

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("ClassName", ClassName);

			ReportMenuForm rform = new ReportMenuForm();
			rform.setAccyear(accYear);
			rform.setClassname(Classid);

			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(rform);
			String sourceFileName = request
					.getRealPath("Reports/ClassWiseExamPdfReport.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examDetailsReport);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ClassExamDetails - " + ".pdf\"");

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
				+ " Control in ReportsAction : feeStatusExcelReport Ending");

		return null;

	}

	public ActionForward InactivatedstudentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_INACTIVATESTUDENTS);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD().getexamdeligate();

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);
			request.setAttribute("locationList", locationList);

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream();

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("StreamList", streamList);

			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentDetailsReport(reform);

			ReportMenuVo selectedItems = new ReportsMenuBD()
					.getSelectedItems(reform);

			request.setAttribute("StudentInfoList", studentInfoList);
			request.setAttribute("CurrentForm", selectedItems);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentDetailExcelsReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : geInactivetStudentDetailExcelsReport Starting");

		String accYear = request.getParameter("AccId");
		String streamId = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		String filePath = null;

		try {

			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("stream", streamId);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InActiveStudentDetailsExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, JDBCConnection.getConnection());
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/InActivateStudentDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
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

			pdfxls = new File(request.getRealPath("Reports/StudentDetails.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=In-ActiveStudentDetails.xls");
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
				+ " Control in ReportsAction : getNonBoardSalaryNonAccountReportExcel Ending");

		return null;
	}

	public ActionForward geInactivetStudentDetailPDFReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : geInactivetStudentDetailPDFReport Starting");

		String accYear = request.getParameter("AccId");
		String stream = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		try {

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("accYear", accYear);
			mapdata.put("stream", stream);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			String filepath = request
					.getRealPath("Reports/InActiveStudentDetailsPDFReport.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection());

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "In-Active StudentDetails" + ".pdf\"");

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
				+ " Control in ReportsAction : geInactivetStudentDetailPDFReport Ending");

		return null;

	}

	public ActionForward InActivestudentFeeReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream();

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("StreamList", streamList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_FEE_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentFeeDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();


			request.setAttribute("AccYearList", accYearList);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentFeeDetailsReport(reform);

			ReportMenuVo selectedItems = new ReportsMenuBD()
					.getSelectedoneItems(reform);

			request.setAttribute("StudentInfoList", studentInfoList);
			request.setAttribute("CurrentForm", selectedItems);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_FEE_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentFeeDetailExcelsReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentAttendanceReportDisplay Starting");

		

		String filePath = null;

			try {

				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				String sourceFileName = request
						.getRealPath("Reports/InActiveStudentFeeReport.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				
				String accYear = request.getParameter("AccId");

				System.out.println("accYear " + accYear);
				
				ReportMenuForm reform = (ReportMenuForm) form;
				
				reform.setAccyear(accYear);


				ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
						.geInactivetStudentFeeDetailsReport(reform);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentInfoList);
				Map parameters = new HashMap();

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						parameters, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(
						request.getRealPath("Reports/InActiveStudentFeeReport.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "InActive Student Fee Report" };
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
						request.getRealPath("Reports/InActiveStudentFeeReport.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=InActiveStudentFeeReport.xls");
				response.setContentLength((int) pdfxls.length());
				input = new FileInputStream(pdfxls);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				} 
				
			}
			catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : getNonBoardSalaryNonAccountReportExcel Ending");

		return null;
	}

	public ActionForward geInactivetStudentFeeDetailPDFReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Starting");
	
	
		try {
			

		

			String accYear = request.getParameter("AccId");

			System.out.println("accYear " + accYear);
			
			ReportMenuForm reform = (ReportMenuForm) form;
			
			reform.setAccyear(accYear);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentFeeDetailsReport(reform);
		
			
			String sourceFileName = request
					.getRealPath("Reports/InActiveStudentFeePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentInfoList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "/images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("Image", PropfilePath);

			
			

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Inactivated Student Fee Details - " + ".pdf\"");

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
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Ending");
		return null;

	}


	public ActionForward getSingleStdFeeStatusReportDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);


		String stdId=request.getParameter("idString");
		System.out.println("stdIdAction"+stdId);
		String section=request.getParameter("section");
		String term=request.getParameter("term");
		String classname=request.getParameter("classname");
		String accyear=request.getParameter("accyear");
       
		 ArrayList<FeeReportDetailsVo> feeStatusReport = new ReportsMenuBD()
		.getSingleStdFeeStatusReportDetails(stdId);
		 System.out.println(feeStatusReport);
		request.setAttribute("feeStatusReport", feeStatusReport);
		 String studentName=feeStatusReport.get(0).getStudentName();
		String totalAmount=feeStatusReport.get(0).getTotalAmount();
		double paidAmount=feeStatusReport.get(0).getPaidAmount();
		double balanceAmount=feeStatusReport.get(0).getBalanceAmount();
		
		 ArrayList<FeeReportDetailsVo> report=new ArrayList<FeeReportDetailsVo>();
		
		for(int i=1;i<feeStatusReport.size();i++){
			FeeReportDetailsVo feeStatusReportDownload=new FeeReportDetailsVo();
			feeStatusReportDownload.setOpeningfeeAmount(feeStatusReport.get(i).getOpeningfeeAmount());
		System.out.println("Fee Amount"+feeStatusReport.get(i).getOpeningfeeAmount());
			feeStatusReportDownload.setFeeName(feeStatusReport.get(i).getFeeName());
			feeStatusReportDownload.setOpeningfeeAmount(feeStatusReport.get(i).getOpeningfeeAmount());
			feeStatusReportDownload.setFeeAmountCollected(feeStatusReport.get(i).getFeeAmountCollected());
			feeStatusReportDownload.setBlancefeeAmount(feeStatusReport.get(i).getBlancefeeAmount());
			feeStatusReportDownload.setPaidDate(feeStatusReport.get(i).getPaidDate());
			report.add(feeStatusReportDownload);
		}
		
		
		request.getSession(false).setAttribute("feeStatusReport1", report);
		request.getSession(false).setAttribute("sectionName", section);	
		request.getSession(false).setAttribute("term", term);
		request.getSession(false).setAttribute("classname", classname);
		request.getSession(false).setAttribute("accyear", accyear);
		request.getSession(false).setAttribute("studentName", studentName);	
		request.getSession(false).setAttribute("totalAmount", totalAmount);
		request.getSession(false).setAttribute("paidAmount", paidAmount);
		request.getSession(false).setAttribute("balanceAmount", balanceAmount);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_SINGLE_FEE_STATUS_REPORT);
	}

	public ActionForward downloadPDFfeeStatusReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadPDFfeeStatusReport  Starting");
		try{
			
			 ArrayList<FeeReportDetailsVo> feeStatusReport = ( ArrayList<FeeReportDetailsVo>)request.getSession(false).getAttribute("feeStatusReport1");
				
			 String sourceFileName = request
						.getRealPath("Reports/FeeStatusPDFReport.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						feeStatusReport);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("section",request.getSession(false).getAttribute("sectionName"));
				parameters.put("term",request.getSession(false).getAttribute("term"));
				parameters.put("class",request.getSession(false).getAttribute("classname"));
				parameters.put("accyear",request.getSession(false).getAttribute("accyear"));
				parameters.put("studentName",request.getSession(false).getAttribute("studentName"));
				parameters.put("totalAmount",request.getSession(false).getAttribute("totalAmount"));
				parameters.put("paidAmount",request.getSession(false).getAttribute("paidAmount"));
				parameters.put("balanceAmount",request.getSession(false).getAttribute("balanceAmount"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentFeeStatusPDFReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentFeeStatusPDFReport   Ending");
		
		return null;
	}
	
	public ActionForward downloadXLfeeStatusReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadXLfeeStatusReport  Starting");
	
	try{

		//System.out.println("DOWNLOADING EXCEL");
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/FeeStatusXLReport (2).jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);

		 ArrayList<FeeReportDetailsVo> feeStatusReport = ( ArrayList<FeeReportDetailsVo>)request.getSession(false).getAttribute("feeStatusReport1");
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				feeStatusReport);
		Map parameters = new HashMap();
		
		parameters.put("section",request.getSession(false).getAttribute("sectionName"));
		parameters.put("term",request.getSession(false).getAttribute("term"));
		parameters.put("class",request.getSession(false).getAttribute("classname"));
		parameters.put("accyear",request.getSession(false).getAttribute("accyear"));
		parameters.put("studentName",request.getSession(false).getAttribute("studentName"));
		parameters.put("totalAmount",request.getSession(false).getAttribute("totalAmount"));
		parameters.put("paidAmount",request.getSession(false).getAttribute("paidAmount"));
		parameters.put("balanceAmount",request.getSession(false).getAttribute("balanceAmount"));
		
		
		
		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				parameters, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/FeeStatusXLReport (2).xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "FeeStatus Detail Excel Report" };
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
				request.getRealPath("Reports/FeeStatusXLReport (2).xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=FeeStatusXLReport.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}

	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadXLfeeStatusReport   Ending");
	
	return null;
}
	
	
	public ActionForward CustomizableStudentReportsExcell(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Starting");
		try {
			
			
			String formValueArray=request.getParameter("formValueArray");
			String labelValueArray=request.getParameter("labelValueArray");
			String[] fielldArray=formValueArray.split(",");
			String[] labelArray=labelValueArray.split(",");
			String location=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String className=request.getParameter("class");
			String section=request.getParameter("section");
			String orderbyArray=request.getParameter("orderbyArray");
			
			ArrayList<HashMap<String, String>> fullstudentList=new ReportsMenuDaoImpl().getCustomizableStudentReportsExcell(formValueArray,location,accyear,className,section,orderbyArray);
		
			
			request.getSession(false).setAttribute("fullstudentList", fullstudentList);
			request.getSession(false).setAttribute("fielldArray", fielldArray);
			request.getSession(false).setAttribute("labelArray", labelArray);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Ending");

		return null;
	}
	public ActionForward CustomizableStudentReportsExcellDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Starting");
		try {
			
			
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			
			String filePath = request.getRealPath("/")+ "FIles/CUSTOMIZABLEREPORT/CustomizableStudentRepots.xls";
			
			String[] fielldArray=(String[]) request.getSession(false).getAttribute("fielldArray");
			ArrayList<HashMap<String, String>> fullstudentList=(ArrayList<HashMap<String, String>>) request.getSession(false).getAttribute("fullstudentList");
			String[] labelArray=(String[]) request.getSession(false).getAttribute("labelArray");
			
			CustomizableStudentReportExcell excelFile= new CustomizableStudentReportExcell(); 
			excelFile.download(fullstudentList,fielldArray,filePath,labelArray);
			
			pdfxls = new File(request.getRealPath("FIles/CUSTOMIZABLEREPORT/CustomizableStudentRepots.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/CustomizableStudentRepots.xls");
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
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Ending");

		return null;
	}
	public ActionForward CustomizableStudentReports(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReports Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, LeftMenusHighlightMessageConstant.MODULE_REPORTS_CUSTOMIZABLE_STUDENT);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    	   }
			

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream();
			
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
 
			request.setAttribute("AccYearList", accYearList);

			/*System.out.println("streamList ::: " + streamList.size());*/

			request.setAttribute("StreamList", streamList);
			
			request.setAttribute("classList", classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReports Ending");

		return mapping.findForward(MessageConstants.CUSTOMIZABLE_STUDENT_REPORT);
	}	
	
	public ActionForward StudentReports(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentReports Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STUDENT);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    	   }
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
			/*System.out.println("streamList ::: " + streamList.size());*/
			request.setAttribute("StreamList", streamList);
			
			request.setAttribute("classList", classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentReports Ending");

		return mapping.findForward(MessageConstants.STUDENT_REPORT);
	}	
	
	public ActionForward StudentCountReports(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentCountReports Starting");
		try {
			

			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STUDENT);
			
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    	   }

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentCountReports Ending");

		return mapping.findForward("studentcountreport");
	}

	public ActionForward getstudentDOBWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentDOBWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
				ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
			
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentDOBWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("studentdobList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentdobList",arr);
				
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}
				else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentDOBWise Ending");

		return null;

	}
	
	public ActionForward getstudentDOBWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWiseXL  Starting");
		
		try{

			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentWithDOBXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentdobList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentWithDOBXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentDOB Wise Excel Report" };
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
					request.getRealPath("Reports/StudentWithDOBXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentDOBExcelReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWiseXL   Ending");
		
		return null;
}
	
	public ActionForward studentDOBWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDOBWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentdobList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentWithDOB.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentDOBWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentFatherOccuWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentFatherOccuWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentfatheroccuList",arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentfatheroccuList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}
				else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWise Ending");

		return null;

	}
	
	// pdf report
	
public ActionForward getstulisthousewisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("stuListHousewise");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentListHouseWise.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("Schoollogo", PropfilePath);
				parameters.put("SchoolName", schName);
				parameters.put("SchoolAdd", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classname",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("location",request.getSession(false).getAttribute("LocationName"));
				parameters.put("Reporttype","Student House Wise Report");
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentHouseWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentFatherOccuWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentfatheroccuList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentFatherOccupation1.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentFatherOccuWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentHouiseWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentHouiseWiseXL  Starting");
		
		try{

			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListHouseWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("stuListHousewise");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("Schoollogo", PropfilePath);
			parameters.put("SchoolName", schName);
			parameters.put("SchoolAdd", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classname",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("location",request.getSession(false).getAttribute("LocationName"));
			parameters.put("Reporttype","Student House Wise Report");
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListHouseWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student House Wise Excel Report" };
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
					request.getRealPath("Reports/StudentListHouseWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentListHouseWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentHouiseWiseXL   Ending");
		
		return null;
}
	
	public ActionForward getstudentFatherOccuWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWiseXL  Starting");
		
		try{

			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentFatherOccupation1XL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentfatheroccuList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentFatherOccupation1XL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentFatherOccu Wise Excel Report" };
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
					request.getRealPath("Reports/StudentFatherOccupation1XL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentFatherOccupation1XL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWiseXL   Ending");
		
		return null;
}
	// for mother occupation
	public ActionForward getstudentMotherOccuWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentMotherOccuWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
	
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentMotherOccuWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentmotheroccuList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentmotheroccuList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
				
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentMotherOccuWise Ending");

		return null;

	}
	
	public ActionForward getstudentMotherOccuWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWiseXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsMotherOccupationXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentmotheroccuList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsMotherOccupationXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentMotherOccu Wise Excel Report" };
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
					request.getRealPath("Reports/StudentsMotherOccupationXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentMotherOccupation1XL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWiseXL   Ending");
		
		return null;
}
	
	public ActionForward getstudentMotherOccuWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentmotheroccuList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentsMotherOccupation.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentMotherOccuWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentDetailsReligionWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentDetailsReligionWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
			
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentDetailsReligionWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentReligionWiseList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentReligionWiseList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());


		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentDetailsReligionWise Ending");

		return null;

	}
	
	public ActionForward studentReligionWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWiseXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentReligionWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentReligionWiseList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentReligionWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentDOB Wise Excel Report" };
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
					request.getRealPath("Reports/StudentReligionWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentReligionWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWiseXL   Ending");
		
		return null;
}
	
	public ActionForward studentReligionWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentReligionWiseList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentReligionWisePDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentsReligionWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}

	public ActionForward getStudentByTransport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Starting");
		try {

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			List<ParentVO> studentList = new StudentAttendanceBD()
					.getStudentByTransport(classId, sectionId);

			JSONObject object = new JSONObject();
			object.put("studentList", studentList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Ending");

		return null;
	}
	
	public ActionForward getTermDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Starting");
		try{
			
			String location = request.getParameter("loc");
			String academic_year = request.getParameter("accyear");
			List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(academic_year,location);
			
			JSONObject object = new JSONObject();
			object.put("termList",termList);
			response.getWriter().print(object);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Ending");

	return null;
	}
	
	public ActionForward getClassDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Starting");
		
		try{
			
			ArrayList<ReportMenuVo> list = new ReportsMenuBD().getClassDetails();
			
			JSONObject object = new JSONObject();
			object.put("ClassList",list);
			response.getWriter().print(object);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Ending");
		return null;
	}

	public ActionForward getstudentCategoryWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentCategoryWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		

			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
					.getstudentCategoryWise(vo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentCategoryWiseList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentCategoryWiseList",arr);
			
			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}
			
			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentCategoryWise Ending");

		return null;

	}
	
	public ActionForward getstudentCategoryWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWiseXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentCategoryWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentCategoryWiseList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));


			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentCategoryWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentCategory Wise Excel Report" };
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
					request.getRealPath("Reports/StudentCategoryWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentCategoryWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWiseXL   Ending");
		
		return null;
}
	
	public ActionForward getstudentCategoryPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentCategoryWiseList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentCategoryWisePDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));


				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentCategoryWiseReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentParentWise(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportMenuAction : getstudentParentWise Starting");

	try {
		ReportMenuVo vo = new ReportMenuVo();

		String selection = request.getParameter("selection");
		String sectionId = request.getParameter("section");
		String accyearid = request.getParameter("accyear");
		String classId = request.getParameter("classId");
		String location = request.getParameter("location");
		

		if(location.equalsIgnoreCase("all"))
		{
			location="%%";
			
		}
		if(classId.equalsIgnoreCase("all"))
		{
			classId="%%";
			
		}
		if(sectionId.equalsIgnoreCase("all"))
		{
			sectionId="%%";
		
		}
		
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
	

		ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);

		ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
				.getstudentParentWise(vo);

		JSONObject jsonobj = new JSONObject();
		jsonobj.accumulate("studentParentList", arr);
		response.getWriter().print(jsonobj);
		request.getSession(false).setAttribute("studentParentList",arr);


		if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
		{
			request.getSession(false).setAttribute("classanddiv","All");
		}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
			request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
		}
		else{
			request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
		}
		
		request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
		request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
		request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());


	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportMenuAction : getstudentParentWise Ending");

	return null;

}

	public ActionForward getstudentParentWiseXL(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentParentWiseXL  Starting");
	
	try{
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/StudentParentWiseListXL.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		String PropfilePath = request.getRealPath(
				"/")
				+ "images/" + ImageName.trim();

		String schName = res.getString("SchoolName");
		String schAddLine1 = res.getString("AddressLine1");

		 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentParentList");
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				studentList);
		Map parameters = new HashMap();
		parameters.put("schoollogo", PropfilePath);
		parameters.put("schoolName", schName);
		parameters.put("Address1", schAddLine1);
		parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
		parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
		parameters.put("strength",request.getSession(false).getAttribute("Strength"));
		parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
		
		
		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				parameters, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/StudentParentWiseListXL.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "StudentParent Wise Excel Report" };
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
				request.getRealPath("Reports/StudentParentWiseListXL.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=StudentParentWiseListXL.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}

	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentParentWiseXL   Ending");
	
	return null;
}

	public ActionForward getstudentParentWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentParentWisePDFReport  Starting");
	try{
		 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentParentList");
		
		
		
		 String sourceFileName = request
					.getRealPath("Reports/StudentParentWiseListPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentParentWiseListPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
	}
	catch(Exception e){
		
	}
	return null;
}
	
	public ActionForward getstudentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentList Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getstudentList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("getstudentDetailsList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("getstudentDetailsList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentList Ending");

		return null;

	}
	
	public ActionForward getstudentListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListXL  Starting");
		
		try{
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("getstudentDetailsList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentList Excel Report" };
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
					request.getRealPath("Reports/StudentListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListXL   Ending");
		
		return null;
}
	
	public ActionForward getstudentListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("getstudentDetailsList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentList.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentListReport - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}

	public ActionForward getstudentStandardWise(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getstudentStandardWise Starting");

	try {
		ReportMenuVo vo = new ReportMenuVo();

		String selection = request.getParameter("selection");
		String sectionId = request.getParameter("section");
		String accyearid = request.getParameter("accyear");
		String classId = request.getParameter("classId");
		String location = request.getParameter("location");
		

		if(location.equalsIgnoreCase("all"))
		{
			location="%%";
			
		}
		if(classId.equalsIgnoreCase("all"))
		{
			classId="%%";
			
		}
		if(sectionId.equalsIgnoreCase("all"))
		{
			sectionId="%%";
		
		}
		
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
	
		
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
		
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
					.getstudentStandardWise(vo);
		
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentStandardList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentStandardList",arr);
			
			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}
			
			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getstudentStandardWise Ending");

	return null;

}

	public ActionForward getstudentStandardWiseXL(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentStandardWiseXL  Starting");
	
	try{

		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/StudentsStandardWiseXL.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		String PropfilePath = request.getRealPath(
				"")
				+ "images/" + ImageName.trim();

		String schName = res.getString("SchoolName");
		String schAddLine1 = res.getString("AddressLine1");

		 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentStandardList");
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				studentList);
		Map parameters = new HashMap();
		parameters.put("schoollogo", PropfilePath);
		parameters.put("schoolName", schName);
		parameters.put("Address1", schAddLine1);
		parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
		parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
		parameters.put("strength",request.getSession(false).getAttribute("Strength"));
		parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
		
		
		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				parameters, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/StudentsStandardWiseXL.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "StudentStandard Wise Excel Report" };
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
				request.getRealPath("Reports/StudentsStandardWiseXL.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=StudentsStandardWiseXL.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}

	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentStandardWiseXL   Ending");
	
	return null;
}

	public ActionForward getstudentStandardWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentStandardWisePDFReport  Starting");
	try{
		 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentStandardList");
		
		
		
		 String sourceFileName = request
					.getRealPath("Reports/StudentsStandardWisePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentSatandaradWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
	}
	catch(Exception e){
		
	}
	return null;
}

//student contact wise

	public ActionForward getstudentContactDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportMenuAction : getstudentContactDetails Starting");

	try {
		ReportMenuVo vo = new ReportMenuVo();

		String selection = request.getParameter("selection");
		String sectionId = request.getParameter("section");
		String accyearid = request.getParameter("accyear");
		String classId = request.getParameter("classId");
		String location = request.getParameter("location");
		

		if(location.equalsIgnoreCase("all"))
		{
			location="%%";
			
		}
		if(classId.equalsIgnoreCase("all"))
		{
			classId="%%";
			
		}
		if(sectionId.equalsIgnoreCase("all"))
		{
			sectionId="%%";
		
		}
		
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
	
		
		ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
					.getstudentContactDetails(vo);
		
			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentContactDetails", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentContactDetails",arr);
			
			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}
			
			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportMenuAction : getstudentContactDetails Ending");

	return null;
}
	
	public ActionForward getstudentContactDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsContactDetailXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentContactDetails");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsContactDetailXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Contact Details Excel Report" };
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
					request.getRealPath("Reports/StudentsContactDetailXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsContactDetailXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsXL   Ending");
		
		return null;
	}
	
	public ActionForward getstudentContactDetailsPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentContactDetails");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentsContactDetailPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentsContactDetailPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentDepartmentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentDepartmentList Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getstudentDepartmentList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentDepartmentList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentDepartmentList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}
				else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentDepartmentList Ending");

		return null;
	}
	
	public ActionForward getstudentDepartmentListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentDepartmentListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentDepartmentList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentDepartmentListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Department List Excel Report" };
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
					request.getRealPath("Reports/StudentDepartmentListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentDepartmentListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListXL   Ending");
		
		return null;
	}
	
	public ActionForward getstudentDepartmentListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentDepartmentList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentDepartmentListPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentDepartmentListPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentBusRouteWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentBusRouteWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getstudentBusRouteWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentBusRouteWise", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentBusRouteWise",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentBusRouteWise Ending");

		return null;
	}
	
	public ActionForward getstudentBusRouteWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWiseXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentBusRouteXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentBusRouteWise");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentBusRouteXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Bus Route Excel Report" };
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
					request.getRealPath("Reports/StudentBusRouteXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentBusRouteXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWiseXL   Ending");
		
		return null;
	}
	
	public ActionForward getstudentBusRouteWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentBusRouteWise");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentBusRoutePDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentBusRoutePDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentOptionalSubjectDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentOptionalSubjectDetails Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getstudentOptionalSubjectDetails(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentOptionalSubject", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentOptionalSubject",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentOptionalSubjectDetails Ending");

		return null;
	}
	
	public ActionForward getstudentOptionalSubjectDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentOptionalSubjectDetailsXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentOptionalSubject");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentOptionalSubjectDetailsXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Optional Subject Excel Report" };
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
					request.getRealPath("Reports/StudentOptionalSubjectDetailsXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentOptionalSubjectDetailsXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsXL   Ending");
		
		return null;
	}
	
	public ActionForward getstudentOptionalSubjectDetailsPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentOptionalSubject");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentOptionalSubjectDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentOptionalSubjectPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	
	public ActionForward getstudentsWithheld(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentsWithheld Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuDaoImpl().getstudentsWithheld(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentWithheld", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentWithheld",arr);
				System.out.println("sectionId="+sectionId);
				if(sectionId.equalsIgnoreCase("%%") || sectionId.equalsIgnoreCase("all"))
				{
					if(classId.equalsIgnoreCase("%%") || classId.equalsIgnoreCase("all")) {
						request.getSession(false).setAttribute("classanddiv","All");
					}
					else {
						request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
					}
					
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
				
				
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentsWithheld Ending");

		return null;
	}
	
	
	public ActionForward getstudentsWithheldXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsWithheldXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsWithheldXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithheld");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsWithheldXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Withheld Excel Report" };
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
					request.getRealPath("Reports/StudentsWithheldXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsWithheldXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsWithheldXL   Ending");
		
		return null;
	}
public ActionForward getstudentWithheldPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithheldPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithheld");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentsWithheldXL.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentsWithheldPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getstudentsWithPhoneNumbers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentsWithPhoneNumbers Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
			
			}
			
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
		
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentWithPhoneNumber(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentWithPhoneNumber", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentWithPhoneNumber",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getstudentsWithPhoneNumbers Ending");

		return null;
	}
	
	public ActionForward getstudentWithPhoneNumbersXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsWithPhoneNumbersXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithPhoneNumber");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsWithPhoneNumbersXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student With Phone Number Excel Report" };
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
					request.getRealPath("Reports/StudentsWithPhoneNumbersXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsWithPhoneNumbersXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersXL   Ending");
		
		return null;
	}
	
	public ActionForward getstudentWithPhoneNumbersPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithPhoneNumber");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentsWithPhoneNumbersPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentWithPhoneNUmberPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getOldStudentsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getOldStudentsList Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getOldStudentsList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("oldStudentList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("oldStudentList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getOldStudentsList Ending");

		return null;
	}
	
	public ActionForward getStudentsStrengthXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsStrengthXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentsStrength");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsStrengthXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students Strength Excel Report" };
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
					request.getRealPath("Reports/StudentsStrengthXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsStrengthXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthXL   Ending");
		
		return null;
	}
	
	public ActionForward getStudentsStrengthPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentsStrength");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentsStrengthPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentsStrengthPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getStudentsStrength(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsStrength Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getStudentsStrength(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentsStrength", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentsStrength",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsStrength Ending");

		return null;
	}
	
	public ActionForward getStudentsNewAdmissionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsNewAdmissionList Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getStudentsNewAdmissionList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentNewAdmissionList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentNewAdmissionList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsNewAdmissionList Ending");

		return null;
	}
	
	public ActionForward getStudentsNewAdmissionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentNewAdmissionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewAdmissionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentNewAdmissionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "New Admission List Excel Report" };
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
					request.getRealPath("Reports/StudentNewAdmissionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentNewAdmissionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListXL   Ending");
		
		return null;
}
	
	public ActionForward getStudentsNewAdmissionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewAdmissionList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentNewAdmissionListPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentNewAdmissionList - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getStudentPromotionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentPromotionList Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD()
						.getStudentPromotionList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentPromotionList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentPromotionList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentPromotionList Ending");

		return null;
	}
	
	public ActionForward getStudentPromotionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentPromotionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentPromotionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentPromotionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students Promotion List Excel Report" };
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
					request.getRealPath("Reports/StudentPromotionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentPromotionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListXL   Ending");
		
		return null;
}
	
	public ActionForward getStudentPromotionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentPromotionList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentPromotionListPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentPromotionList - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ActionForward getStudentListGenderWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentListGenderWise Starting");

		try {
			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String gender = request.getParameter("gender");
			
			if(gender.equalsIgnoreCase("all")){
				gender="%%";
			}else
			{
				gender=gender;
			}
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
				vo.setGender(gender);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setGender(gender);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getStudentListGenderWise(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentListGenderWise", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentListGenderWise",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentListGenderWise Ending");

		return null;

	}
	
	public ActionForward getStudentListGenderWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWiseXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListGenderWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentListGenderWise");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListGenderWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students List Gender Wise Excel Report" };
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
					request.getRealPath("Reports/StudentListGenderWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentListGenderWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWiseXL   Ending");
		
		return null;
}
	
	public ActionForward getStudentListGenderWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWisePDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentListGenderWise");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentListGenderWisePDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentListGenderWise - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		return null;
	}
	public ActionForward progressReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

	       
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.PROGRESS_REPORT);
	}
	public ActionForward individualStudentProgress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {

			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			request.setAttribute("studentSearchList",list);

			List<ExaminationDetailsVo> list1=new ArrayList<ExaminationDetailsVo>();
			list1 =	ReportsMenuBD.getSubjectOnClass(classId,studentId,accYear,locationId,ExamCode);
			request.setAttribute("subjectList",list1);
			double total=0;
			int pass=0;
			double scored=0;
			for (int i = 0; i < list1.size(); i++) {
				
				if(list1.get(i).getTot_marks() !=null && !list1.get(i).getTot_marks().equalsIgnoreCase("")) {
					 total=total+ (Double.parseDouble(list1.get(i).getTot_marks()));
				}
		     
			}
			for (int i = 0; i < list1.size(); i++) {
				pass=pass+Integer.parseInt(list1.get(i).getPassmarks());
				
				}
			for (int i = 0; i < list1.size(); i++) {
				if(list1.get(i).getScoredmarks() !=null && !list1.get(i).getScoredmarks().equalsIgnoreCase("")){
					scored=scored+(Double.parseDouble(list1.get(i).getScoredmarks()));
				}else{
					scored=scored;
				}
			}
			request.setAttribute("scored",scored);
			String result=null;
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getScoredmarks() != null) {
					if (Double.parseDouble(list1.get(i).getPassmarks()) > Double.parseDouble(list1.get(i).getScoredmarks())) {
						result = "FAIL";
						break;
					} else {
						result = "PASS";
					}
				} else {
					result = "";
				}
			}
			request.setAttribute("result",result);
			request.setAttribute("total",total);
			request.setAttribute("pass",pass);
			
			ArrayList<ExaminationDetailsVo> list2=new ArrayList<ExaminationDetailsVo>();
			list2=ReportsMenuBD.getExamDependencides(ExamCode,studentId,accYear,locationId,classId,SectionId,(int) scored);
			request.setAttribute("Dependency",list2);
			
			int Grandtotal=0;
			int OutOf=0;
			for(int i=0; i<list2.size(); i++){
				if(list2.get(i).getMainexammark()!=0){
				Grandtotal=Grandtotal+list2.get(0).getTotalDepScoredMarks()+list2.get(i).getMainexammark();
				OutOf=list2.get(0).getOutOffG()+list2.get(i).getMaintotal();
				}
				else{
					Grandtotal=0;
				}	
			}
			
			double grademrk1=(Grandtotal/(double)OutOf)*100;
			String GrandGrade=null;
			int grademrk=(int)grademrk1;
			         if(Grandtotal!=0){
				 GrandGrade=ReportsMenuBD.getGradeBasedOnMarks(grademrk);
			             }else{
				GrandGrade=" ";
			          }
			request.setAttribute("GrandGrade",GrandGrade);
			request.setAttribute("Grandtotal",Grandtotal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_PROGRESS_REPORT);
	}
	
	public ActionForward getExam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			String studentId=request.getParameter("studentId");
			String accyear=request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			String classDetailId=request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			
			ArrayList<ExaminationDetailsVo> list=new ArrayList<ExaminationDetailsVo>();
			list=ReportsMenuBD.getExam(studentId,accyear,locationId,classDetailId,SectionId);
			JSONObject json = new JSONObject();
			json.put("ExamList", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return null;
	}
	
	public ActionForward classPerformance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_CLASSPERFORMANCE);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

	        if(location.equalsIgnoreCase("all")){
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
			else{
				
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.CLASS_PERFORMANCE);
	}
	
	public ActionForward individualStudentClassPerformance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_CLASSPERFORMANCE);
		try {

			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");
			
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			request.setAttribute("studentSearchList",list);

			List<ExaminationDetailsVo> list1=new ArrayList<ExaminationDetailsVo>();
			list1 =	ReportsMenuBD.getSubjectOnClass(classId,studentId,accYear,locationId,ExamCode);
			request.setAttribute("subjectList",list1);
			
			request.setAttribute("studentId", studentId);
			request.setAttribute("accYear", accYear);
			request.setAttribute("locationId", locationId);
			request.setAttribute("classId", classId);
			request.setAttribute("SectionId", SectionId);
			request.setAttribute("ExamCode", ExamCode);
			
			} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_CLASS_PERFORMANCE);
	}
	
	public ActionForward getChartDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accYear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");
			
			List<ExaminationDetailsVo> list2=new ArrayList<ExaminationDetailsVo>();
			list2 =	ReportsMenuBD.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,ExamCode,SectionId);
			JSONObject json = new JSONObject();
			json.put("detail",list2);
			response.getWriter().print(json);
			
			} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return null;
	}
	public ActionForward getTerm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTerm Starting");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			try{
				String locId=request.getParameter("locId");
				String accId=request.getParameter("accId");
				
				System.out.println(locId);
				ArrayList<ReportMenuVo> list = new ReportsMenuDaoImpl().getTermBaseOnLocation(locId,accId);
				JSONObject obj = new JSONObject();
				obj.put("data", list);
				response.getWriter().print(obj);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getTerm Ending");

			return null;
			}
	

	public ActionForward FeeCollectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollection Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEECOLLECTION);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	  if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    	   }

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream();
			
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
 
			request.setAttribute("AccYearList", accYearList);

		

			request.setAttribute("StreamList", streamList);
			
			request.setAttribute("classList", classList);
			
					
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollection Ending");
      
		return mapping.findForward(MessageConstants.FeeCollectionReport);
	}
	
	public ActionForward getFeeCollectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String termId=request.getParameter("termId");
			
			if(termId.equalsIgnoreCase("all")){
				termId="%%";
	    		   
	    	   }
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
	    	   }
			 
			 
			 
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionReport(locationid,accyear,termId);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionReport Ending");

		return null;
	}
	
	public ActionForward getFeeCollectionAdmissionWiseReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionAdmissionWiseReport Starting");
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String termId=request.getParameter("termId");
			String sectionId=request.getParameter("sectionid");
			String PaymentType=request.getParameter("PaymentType");
			String paymode=request.getParameter("paymode");
			if(PaymentType.equalsIgnoreCase("all")){
				 PaymentType="%%";
		    		   
		    	   }
			 if(paymode.equalsIgnoreCase("all")){
				 paymode="%%";
		    		   
		    	   }
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			 if(sectionId.equalsIgnoreCase("all")){
				 sectionId="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=new ReportsMenuDaoImpl().getFeeCollectionAdmissionWiseReport(locationid,accyear,classid,termId,sectionId,PaymentType,paymode);
			
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		return null;
	}
	public ActionForward getFeeCollectionBillWiseReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionBillWiseReport Starting");
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String termId=request.getParameter("termId");
			String sectionId=request.getParameter("sectionid");
			String PaymentType=request.getParameter("PaymentType");
			String paymode=request.getParameter("paymode");
			if(PaymentType.equalsIgnoreCase("all")){
				 PaymentType="%%";
		    		   
		    	   }
			 if(paymode.equalsIgnoreCase("all")){
				 paymode="%%";
		    		   
		    	   }
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			 if(sectionId.equalsIgnoreCase("all")){
				 sectionId="%%";
	    		   
			 }
			 
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=new ReportsMenuDaoImpl().getFeeCollectionBillWiseReport(locationid,accyear,classid,termId,sectionId,PaymentType,paymode);
			
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		return null;
	}
	public ActionForward getFeeCollectionMonthWiseReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionMonthWiseReport Starting");
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String termId=request.getParameter("termId");
			String monthName=request.getParameter("monthName");	
			String sectionId=request.getParameter("sectionid");
			String PaymentType=request.getParameter("PaymentType");
			String paymode=request.getParameter("paymode");
			if(PaymentType.equalsIgnoreCase("all")){
				 PaymentType="%%";
		    		   
		    	   }
			 if(paymode.equalsIgnoreCase("all")){
				 paymode="%%";
		    		   
		    	   }
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			 if(sectionId.equalsIgnoreCase("all")){
				 sectionId="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=new ReportsMenuDaoImpl().getFeeCollectionMonthWiseReport(locationid,accyear,classid,termId,monthName,sectionId,paymode);
			
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		return null;
	}
	public ActionForward getfeecollectiondatelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String termId=request.getParameter("termId");
			String startdate=request.getParameter("startdate");	
			String enddate=request.getParameter("enddate");	
			String PaymentType=request.getParameter("PaymentType");
			String paymode=request.getParameter("paymode");
			if(PaymentType.equalsIgnoreCase("all")){
				 PaymentType="%%";
		    		   
		    	   }
			 if(paymode.equalsIgnoreCase("all")){
				 paymode="%%";
		    		   
		    	   }
			
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=new ReportsMenuDaoImpl().getfeecollectiondatelist(locationid,accyear,classid,termId,startdate,enddate,paymode);
			
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Ending");

		return null;
	}
	
	public ActionForward getFeeCollectionSectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionSectionReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String setionid=request.getParameter("sectionid");
			 String termId=request.getParameter("termId");
				
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
			 }
			 if(setionid.equalsIgnoreCase("all")){
				 setionid="%%";
	    		   
			 }
			 
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionSectionReport Ending");

		return null;
	}
	
	
	public ActionForward getFeeCollectionPaymodeReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String setionid=request.getParameter("sectionid");
			 String paymodeid=request.getParameter("paymodid");
			 String paymenttype =request.getParameter("paymenttype");
			 String termId=request.getParameter("termId");
				
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
	    	   }
			 if(setionid.equalsIgnoreCase("all")){
				 setionid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
			 }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Ending");

		return null;
	}
	
	public ActionForward DDReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_CURRENTSTUDENTFEEPAYMENTS);
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

	        if(location.equalsIgnoreCase("all")){
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
			else{
				
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.DD_REPORT);
	}
	
	public ActionForward TermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			

			String location = request.getParameter("loc");
	        if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			
			ArrayList<ReportMenuVo> termList = new ReportsMenuBD().getterms(location);
			
			JSONObject json = new JSONObject();
			json.put("TermList",termList);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return null;
	}
	
	public ActionForward DDReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			String Termid = request.getParameter("Termid");
			String academic_year = request.getParameter("Acyearid");
			String locationid = request.getParameter("locationname");

			if(academic_year == null || academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			if(Termid == null || Termid.equalsIgnoreCase("") || Termid.equalsIgnoreCase("all")) {
				Termid ="%%";
			}
		
			ArrayList<ReportMenuVo> termList = new ReportsMenuBD().DDReportList(Termid,academic_year,locationid);
			request.getSession(false).setAttribute("DDLIST", termList);
			JSONObject json = new JSONObject();
			json.put("SearchList",termList);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Ending");
		return null;
	}
	public ActionForward DDReportListDateWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			String Termid = request.getParameter("Termid");
			String locationid = request.getParameter("locationname");
			String startDate= request.getParameter("startDate");
			String endDate= request.getParameter("endDate");

			if(locationid == null || locationid.equalsIgnoreCase("") || locationid.equalsIgnoreCase("all")) {
				locationid = "%%";
			}
			if(Termid == null || Termid.equalsIgnoreCase("") || Termid.equalsIgnoreCase("all")) {
				Termid ="%%";
			}
		
			ArrayList<ReportMenuVo> termList = new ReportsMenuDaoImpl().DDReportListDateWise(locationid,Termid,startDate,endDate);
			request.getSession(false).setAttribute("DDLIST", termList);
			JSONObject json = new JSONObject();
			json.put("SearchList",termList);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Ending");
		return null;
	}
	public ActionForward DetailsOfDDPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : DetailsOfDDPdfReport Starting");

		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		try {

			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");
			
			ArrayList<ReportMenuVo> list=(ArrayList<ReportMenuVo>) request.getSession(false).getAttribute("DDLIST");
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			Map mapdata=new HashMap();

			mapdata.put("Image",PropfilePath);
			mapdata.put("accYear",accYear);
			mapdata.put("Termid","ALL");
			mapdata.put("schName","ARYA CENTRAL SCHOOL");
			mapdata.put("schAddLine1",schAddLine1);
			mapdata.put("locationid","ARYA CENTRAL SCHOOL");

			/*examReportClassWiseDetails*/
			
			String sourceFileName=request.getRealPath("Reports/DDReportPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "DetailsOfDD" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes,0,bytes.length);
			outstream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : DetailsOfDDPdfReport Ending");

		return null;
	}
	
	public ActionForward DetailsOfDDExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : DetailsOfDDExcelReport Starting");

		
		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		String filePath = null;

		try {
			
			
	ArrayList<ReportMenuVo> list=(ArrayList<ReportMenuVo>) request.getSession(false).getAttribute("DDLIST");
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");			
			
			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("Termid", "ALL");
			mapdata.put("schName", "ARYA CENTRAL SCHOOL");
			mapdata.put("schAddLine1", schAddLine1);
			mapdata.put("locationid", "ARYA CENTRAL SCHOOL");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/DDReportxlsx.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/DDDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "DD Details" };
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

			pdfxls = new File(request.getRealPath("Reports/DDDetails.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=DDDetails.xls");
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
				+ " Control in ReportsAction : DetailsOfDDExcelReport Ending");

		return null;
	}


	public ActionForward getonlinelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String setionid=request.getParameter("sectionid");
			 String paymodeid=request.getParameter("paymodid");
			 String paymenttype =request.getParameter("paymenttype");
			 String termId=request.getParameter("termId");
				
				if(termId==null || termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 if(locationid==null || locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
			 }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			 if(setionid.equalsIgnoreCase("all")){
				 setionid="%%";
	    		   
			 }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Ending");

		return null;
	}
	
	public ActionForward FeeCollectionPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeCollectionPdfReport Starting");
		try {
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();

		String accyear=request.getParameter("AccId");
		String classid=request.getParameter("ClassId");
		String setionid=request.getParameter("SectionId");
		String paymodeid=request.getParameter("Paymode");
		String paymenttype=request.getParameter("PaymentType");
		String locationid=request.getParameter("locationname");
		String locid = request.getParameter("locid");
		String classname=request.getParameter("Classname");
		String Sectionname=request.getParameter("Sectionname");
		String paymode=request.getParameter("paymode");
		String PaymentType=request.getParameter("PaymentType");
		String acyear=request.getParameter("accyear");
		String termId=request.getParameter("termId");
		String termName=request.getParameter("termName");
		
	
		if(termId.equalsIgnoreCase("all")){
			termId="%%";
			 
    	   }
		
		
	      	if(classid.equalsIgnoreCase("all")){
			classid="%%";
   	     	 }
	    	if(setionid.equalsIgnoreCase("all")){
	  		setionid="%%";
   		   
		   }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);		
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
		    mapdata.put("class", classid);
		    mapdata.put("setionid", setionid);
		    mapdata.put("paymenttype", paymenttype);
		    mapdata.put("sclname",locationid);
		    mapdata.put("classname", classname);
		    mapdata.put("scenm", Sectionname);
		    mapdata.put("paymode", paymode);
		    mapdata.put("paytype", PaymentType);
		    mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId",termName);
			
	
			String sourceFileName=request.getRealPath("Reports/Feecollectionreportpdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeCollectionReport" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes,0,bytes.length);
			outstream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeCollectionPdfReport Ending");

		return null;
	}

	
	
	public ActionForward FeeCollectionExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeCollectionExcelReport Starting");

		


		String filePath = null;

		try {

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();
			System.out.println("//////////////"+userName);

		String accyear=request.getParameter("AccId");
		String classid=request.getParameter("ClassId");
		String setionid=request.getParameter("SectionId");
		String paymodeid=request.getParameter("Paymode");
		String paymenttype=request.getParameter("PaymentType");
		String locationid=request.getParameter("locationname");
		String locid = request.getParameter("locid");
		String classname=request.getParameter("Classname");
		String Sectionname=request.getParameter("Sectionname");
		String paymode=request.getParameter("paymode");
		String PaymentType=request.getParameter("PaymentType");
		String acyear=request.getParameter("accyear");
		String termId=request.getParameter("termId");
		String termName=request.getParameter("termName");
		
		if(termId.equalsIgnoreCase("all")){
			termId="%%";
    		   
    	   }
		
		
	      	if(classid.equalsIgnoreCase("all")){
			classid="%%";
   	     	 }
	    	if(setionid.equalsIgnoreCase("all")){
	  		setionid="%%";
   		   
		   }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);		
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
		    mapdata.put("class", classid);
		    mapdata.put("setionid", setionid);
		    mapdata.put("paymenttype", paymenttype);
		    mapdata.put("sclname",locationid);
		    mapdata.put("classname", classname);
		    mapdata.put("scenm", Sectionname);
		    mapdata.put("paymode", paymode);
		    mapdata.put("paytype", PaymentType);
		    mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId", termName);
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/FeecollectionreportXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeecollectionExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Fee Collection Details" };
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

			pdfxls = new File(request.getRealPath("Reports/FeecollectionExcelReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeecollectionExcelReport.xls");
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
				+ " Control in ReportsAction : FeeCollectionExcelReport Ending");

		return null;
	}
	
	

	public ActionForward getSectionByClassLoc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClassLoc Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
		
			String classId = request.getParameter("classId");
			String location=request.getParameter("location");
			
			

			if(classId=="all"){
				classId="%%";
			}
			
			System.out.println("class id value>>>"+classId);
			ArrayList<ReportMenuVo> sectionlist = new ReportsMenuBD()
					.getSectionsByClassLoc(classId,location);


			JSONObject object = new JSONObject();
			object.put("SectionList", sectionlist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClassLoc Ending");

		return null;
	}

	
	public ActionForward PrintDDDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : DetailsOfDDPdfReport Starting");

		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		try {

			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("Image",PropfilePath);
			mapdata.put("accYear",accYear);
			mapdata.put("Termid",Termid);
			mapdata.put("schName",schName);
			mapdata.put("schAddLine1",schAddLine1);
			mapdata.put("locationid",locationid);

			
			/*Printing Details Of DD*/
			
			String sourceFileName=request.getRealPath("Reports/DDReportPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,JDBCConnection.getConnection());
			//JasperViewer.viewReport(jasperPrint, false);
			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			/*   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
			   printRequestAttributeSet.add(mediaSizeName);*/
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : DetailsOfDDPdfReport Ending");


		return null;
	}

	
	
	public ActionForward FeeCollectionPrintDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeCollectionPrintDetails Starting");

		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		try {

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();

		String accyear=request.getParameter("AccId");
		String classid=request.getParameter("ClassId");
		String setionid=request.getParameter("SectionId");
		String paymodeid=request.getParameter("Paymode");
		String paymenttype=request.getParameter("PaymentType");
		String locationid=request.getParameter("locationname");
		String locid = request.getParameter("locid");
		String classname=request.getParameter("Classname");
		String Sectionname=request.getParameter("Sectionname");
		String paymode=request.getParameter("paymode");
		String PaymentType=request.getParameter("PaymentType");
		String acyear=request.getParameter("accyear");
		String termId=request.getParameter("termId");
		String termName=request.getParameter("termName");
		
		if(termId.equalsIgnoreCase("all")){
			termId="%%";
    		   
    	   }
		
		
	      	if(classid.equalsIgnoreCase("all")){
			classid="%%";
   	     	 }
	    	if(setionid.equalsIgnoreCase("all")){
	  		setionid="%%";
   		   
		   }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);		
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
		    mapdata.put("class", classid);
		    mapdata.put("setionid", setionid);
		    mapdata.put("paymenttype", paymenttype);
		    mapdata.put("sclname",locationid);
		    mapdata.put("classname", classname);
		    mapdata.put("scenm", Sectionname);
		    mapdata.put("paymode", paymode);
		    mapdata.put("paytype", PaymentType);
		    mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId", termName);
			
			/*examReportClassWiseDetails*/
			
			String sourceFileName=request.getRealPath("Reports/Feecollectionreportpdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
			//JasperViewer.viewReport(jasperPrint, false);
			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			/*   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
			   printRequestAttributeSet.add(mediaSizeName);*/
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : FeeCollectionPrintDetails Ending");


		return null;
	}
	
	public ActionForward ITFeeCollectionReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_IT_FEE_COLLECTION);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation));

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			

			if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation = "%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location);
			}
		     else{
				
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location);
				System.out.println("Location From Action:" +list.get(0).getLocation());
			}
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				System.out.println("HelperClass.getCurrentYearID()"
						+ HelperClass.getCurrentYearID());
				academic_year = HelperClass.getCurrentYearID();
			}

			List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("stuId");
			if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate()
				.getStudentDetails(searchTerm,schoolLocation);
				request.setAttribute("searchTerm",searchTerm);

			} else {
				List = new StudentRegistrationDelegate().getStudentDetails1(
						userType, userCode, academic_year,schoolLocation);
				
			     }		
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Ending");

		return mapping.findForward(MessageConstants.IT_FEE_COLLECTION);

	}
	public ActionForward printITFeeCollectionReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			String StudentId=request.getParameter("StuId");
			String accyer=request.getParameter("accyear");
			String locationId=request.getParameter("locid");
			System.out.println(StudentId+" "+accyer+" "+locationId);
			ArrayList<ITFeeVo> list=new ArrayList<ITFeeVo>();
			ITFeeVo ITF=new ITFeeVo();
			ITF.setMaj("1");
			ITF=ReportsMenuBD.getITFee(StudentId,accyer,locationId);
			System.out.println("OUT DIO impl");
			String PropfilePath = request.getRealPath("/")+"images/"+ImageName.trim();
			String sourceFileName = request.getRealPath("Reports/ITFeeCollection.jrxml");
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			String Bracket = res.getString("Bracket");
			String Bracket1 = res.getString("Bracket1");
			String Bracket2 = res.getString("Bracket2");
			String Bracket3 = res.getString("Bracket3");
			String Bracket4 = res.getString("Bracket4");
			String[] acy=ITF.getAccyear().split("-");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			Map parameters = new HashMap();
			parameters.put("locname", schName);
			parameters.put("Bracket", Bracket);
			parameters.put("Bracket1", Bracket1);
			parameters.put("Bracket2", Bracket2);
			parameters.put("Bracket3", Bracket3);
			parameters.put("Bracket4", Bracket4);
			
			System.out.println(ITF.getClassSec());
			parameters.put("StudentName", ITF.getStuName());
			parameters.put("ParentName", ITF.getParentname());
			parameters.put("Class", ITF.getClassSec());
			parameters.put("TutionFee", ITF.getTutorial());
			parameters.put("TutionFeeInN", ITF.getStringtution());
			parameters.put("LabFee", ITF.getClab());
			parameters.put("LabFeeInN", ITF.getStringclab());
			parameters.put("Sacy", acy[0]);
			parameters.put("Lacy", acy[1]);
			System.out.println("Sacy "+acy[0]);
			System.out.println("Sacy "+acy[1]);
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			System.out.println("JASPER VIVER");
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			System.out.println("AFTER JASPER VIVER");
			viewer.setVisible(true);
			PrinterJob job = PrinterJob.getPrinterJob();
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Ending");
		return null;

	}
	public ActionForward getStudentsTempNewAdmissionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsTempNewAdmissionList Starting");

		try {

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
		if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				
			}
		 if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			
			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
				ArrayList<ReportMenuVo> arr = new ReportsMenuDaoImpl().getStudentsTempNewAdmissionList(vo);
			
				JSONObject jsonobj = new JSONObject();
				jsonobj.accumulate("studentNewAdmissionList", arr);
				response.getWriter().print(jsonobj);
				request.getSession(false).setAttribute("studentNewTempAdmissionList",arr);
				
				if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
				{
					request.getSession(false).setAttribute("classanddiv","All");
				}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
					request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
				}
				else{
					request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
				}
				
				request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
				request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
				request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportMenuAction : getStudentsTempNewAdmissionList Ending");

		return null;
	}
	
	public ActionForward getStudentsTempNewAdmissionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListXL  Starting");
		
		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentNewTempAdmissionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath("/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewTempAdmissionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentNewTempAdmissionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "New Admission List Excel Report" };
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
					request.getRealPath("Reports/StudentNewTempAdmissionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentNewTempAdmissionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListXL   Ending");
		
		return null;
}
	
	public ActionForward getStudentsTempNewAdmissionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListPDFReport  Starting");
		try{
			 ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewTempAdmissionList");
			
			
			
			 String sourceFileName = request
						.getRealPath("Reports/StudentNewTempAdmissionListXL.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentList);

				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schoollogo", PropfilePath);
				parameters.put("schoolName", schName);
				parameters.put("Address1", schAddLine1);
				parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
				parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
				parameters.put("strength",request.getSession(false).getAttribute("Strength"));
				parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentNewTempAdmissionListPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
		}
		catch(Exception e){
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListPDFReport   Ending");
		
		return null;
	}
	
	public ActionForward getStudentListAdmiWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListAdmiWise  Starting");

		try{
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			
			List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentListAdmiWise(vo);
			
			JSONObject obj = new JSONObject();
			obj.put("stuList", stuList);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
public ActionForward getstudentRollNoWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentListAdmiWise  Starting");

	try{
		ReportMenuVo vo = new ReportMenuVo();
		vo.setLocationId(request.getParameter("location"));
		vo.setAccyearId(request.getParameter("accyear"));
		vo.setClassId(request.getParameter("classId"));
		vo.setSectionId(request.getParameter("section"));
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentRollNoWise(vo);
		
		JSONObject obj = new JSONObject();
		obj.put("stuList", stuList);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}
	public ActionForward getstudentHouseWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentHouseWise  Starting");

		try{
			
			String classId=request.getParameter("location");
			String sectionId=request.getParameter("section");
			
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			
			List<ReportMenuVo> stuList = new ReportsMenuDaoImpl().getstudentHouseWise(vo);
			
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo);
			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}
			
			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(stuList.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
			
			request.getSession(false).setAttribute("stuListHousewise", stuList);
			
			JSONObject obj = new JSONObject();
			obj.put("stuList", stuList);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
public ActionForward getstudentAlphaWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentListAdmiWise  Starting");

	try{
		ReportMenuVo vo = new ReportMenuVo();
		vo.setLocationId(request.getParameter("location"));
		vo.setAccyearId(request.getParameter("accyear"));
		vo.setClassId(request.getParameter("classId"));
		vo.setSectionId(request.getParameter("section"));
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentAlphaWise(vo);
		
		JSONObject obj = new JSONObject();
		obj.put("stuList", stuList);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}

public ActionForward getstudentListRollNoWise(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : studentListRollNoWise Starting");

	try {
		
		String locName = request.getParameter("locName");
		String className = request.getParameter("classname");
		String sectionName = request.getParameter("sectionnname");
		String accyear = request.getParameter("name");	
		String selection = request.getParameter("report");

		ReportMenuVo vo = new ReportMenuVo();
		vo.setLocationId(request.getParameter("location"));
		vo.setAccyearId(request.getParameter("accyear"));
		vo.setClassId(request.getParameter("classId"));
		vo.setSectionId(request.getParameter("section"));
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentRollNoWise(vo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				stuList);		
		String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName=res.getString("SchoolName");
		String schAddLine1=res.getString("AddressLine1");

		Map mapdata=new HashMap();

		mapdata.put("SchoolName",schName);
		mapdata.put("SchoolAdd",schAddLine1);
		mapdata.put("accyear",accyear);
		mapdata.put("location",locName);
		mapdata.put("classname",className);
		mapdata.put("section",sectionName);
		mapdata.put("Reporttype","Student List - Roll No. Wise"); 
		if(selection.equalsIgnoreCase("pdf")) {
			
		String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""
			+ "StudentListRollNoWise"+".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();
		outstream.write(bytes,0,bytes.length);
		outstream.flush();
	 }else if(selection.equalsIgnoreCase("excel")){
			
		 	File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Roll No Wise Excel Report" };
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

			pdfxls = new File(request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentListRollNoWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
	 }
	}	
		catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentListRollNoWisePdf Ending");

	return null;
}  

public ActionForward getstudentListAdmisNoWise(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : studentListRollNoWise Starting");

	try {
		
		String locName = request.getParameter("locName");
		String className = request.getParameter("classname");
		String sectionName = request.getParameter("sectionnname");
		String accyear = request.getParameter("name");	
		String selection = request.getParameter("report");

		ReportMenuVo vo = new ReportMenuVo();
		vo.setLocationId(request.getParameter("location"));
		vo.setAccyearId(request.getParameter("accyear"));
		vo.setClassId(request.getParameter("classId"));
		vo.setSectionId(request.getParameter("section"));
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentListAdmiWise(vo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				stuList);		
		String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName=res.getString("SchoolName");
		String schAddLine1=res.getString("AddressLine1");

		Map mapdata=new HashMap();

		mapdata.put("SchoolName",schName);
		mapdata.put("SchoolAdd",schAddLine1);
		mapdata.put("accyear",accyear);
		mapdata.put("location",locName);
		mapdata.put("classname",className);
		mapdata.put("section",sectionName);
		mapdata.put("Reporttype","Student List - Admission No. Wise"); 
		
		if(selection.equalsIgnoreCase("pdf")) {
			
		String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""
			+ "StudentListRollNoWise"+".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();
		outstream.write(bytes,0,bytes.length);
		outstream.flush();
	 }else if(selection.equalsIgnoreCase("excel")){
			
		 	File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Roll No Wise Excel Report" };
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
					request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentListRollNoWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
	 }
	}	
		catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentListRollNoWisePdf Ending");

	return null;
} 

public ActionForward getstudentListAlphaWise(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : studentListRollNoWise Starting");

	try {
		
		String locName = request.getParameter("locName");
		String className = request.getParameter("classname");
		String sectionName = request.getParameter("sectionnname");
		String accyear = request.getParameter("name");	
		String selection = request.getParameter("report");

		ReportMenuVo vo = new ReportMenuVo();
		vo.setLocationId(request.getParameter("location"));
		vo.setAccyearId(request.getParameter("accyear"));
		vo.setClassId(request.getParameter("classId"));
		vo.setSectionId(request.getParameter("section"));
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentAlphaWise(vo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				stuList);		
		String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName=res.getString("SchoolName");
		String schAddLine1=res.getString("AddressLine1");

		Map mapdata=new HashMap();

		mapdata.put("SchoolName",schName);
		mapdata.put("SchoolAdd",schAddLine1);
		mapdata.put("accyear",accyear);
		mapdata.put("location",locName);
		mapdata.put("classname",className);
		mapdata.put("section",sectionName);
		mapdata.put("Reporttype","Student List - Alphabetic Wise"); 
		if(selection.equalsIgnoreCase("pdf")) {
			
		String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""
			+ "StudentListRollNoWise"+".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();
		outstream.write(bytes,0,bytes.length);
		outstream.flush();
	 }else if(selection.equalsIgnoreCase("excel")){
			
		 	File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Roll No Wise Excel Report" };
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
					request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentListRollNoWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
	 }
	}	
		catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getstudentListRollNoWisePdf Ending");

	return null;
} 

public ActionForward reportCardDownload(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentListAdmiWise  Starting");

	try{
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_REPORTCARD);
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear");
		
		request.setAttribute("accYear", accYear);
		
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);
		request.setAttribute("AccYearList", accYearList);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return mapping.findForward(MessageConstants.ReportCardDownload);
}

public ActionForward downloadReportCard(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadReportCard Starting");

	frontPageDownload(request,response);
	//backPageDownload(request,response);
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadReportCard Ending");

	return null;
}

public static void frontPageDownload(HttpServletRequest request,
		HttpServletResponse response) {
	try {
		String classId = request.getParameter("classId");
		String locationId = request.getParameter("locationId");
		String accyearId = request.getParameter("accyearId");
		String term1 = request.getParameter("terms1");
		String term2 = request.getParameter("terms2");
		String examstypeid = request.getParameter("examstypeid");
		String examstypeidterm2 = request.getParameter("examstypeidterm2");
		String stdId = request.getParameter("stdId");
		String sectionId = request.getParameter("sectionId");

		ReportMenuVo vo=new ReportMenuVo();
		vo.setAccYear(accyearId);
		vo.setClassId(classId);
		vo.setLocationId(locationId);
		//vo.setTermname(checkedTermValue);
		vo.setExamtypeid(examstypeid);
		vo.setExamstypeidterm2(examstypeidterm2);
		vo.setStudentId(stdId);
		vo.setSectionId(sectionId);
		vo.setTerm1(term1);
		vo.setTerm2(term2);

		List<ReportMenuVo> stuList = new ReportsMenuBD().getTermWiseReportCard(vo);
		System.out.println("size is "+stuList.size());
		List<ReportMenuVo> marksheet=null;
		ArrayList<ReportMenuVo> singlelist=null;
		String sourceFileName = null;		
		FileOutputStream outputFile=null;
		File secondDir = null;
		File firstDir = null;
		JRBeanCollectionDataSource beanColDataSource = null;
		
			sourceFileName=request.getRealPath("Reports/ReportCardFrontPage.jrxml");
		

		
		/*ReportCard_Dir*/

		/*firstDir = new File(ReportCard_Dir);
		if (firstDir.exists()) {
			secondDir = new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId));
			secondDir.mkdir();
		} else {
			new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId)).mkdirs();
		}
		 */
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		String imageFilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
		String boardFilePath=request.getRealPath("/")+ "images/" + BoardLogo.trim();
		ServletOutputStream outstream =null;
		byte[] bytes=null;
		Map parameters = new HashMap();
		parameters.put("schoollogo",imageFilePath);
		parameters.put("boardlogo",boardFilePath);

		beanColDataSource = new JRBeanCollectionDataSource(stuList);

		bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""+HelperClass.getAcademicYearFace(accyearId)+"-"+HelperClass.getClassName(classId, locationId)+".pdf\"");
		outstream = response.getOutputStream();
		outstream.write(bytes, 0, bytes.length);


		/*singlelist = new ArrayList<ReportMenuVo>();
			System.out.println("STUDENT "+i);
			singlelist.add(stuList.get(i));
			beanColDataSource = new JRBeanCollectionDataSource(singlelist);

			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			outputFile = new FileOutputStream(new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId) + "/" + stuList.get(i).getStudentnamelabel().replaceAll(" ", "_")+".pdf"));
			JasperExportManager.exportReportToPdfStream(print, outputFile);
			outputFile.close();*/

	}	
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
}
public byte[] generateReport(JasperPrint jasperPrint1, JasperPrint jasperPrint2) {
	  //throw the JasperPrint Objects in a list
	  List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	  jasperPrintList.add(jasperPrint1);
	  jasperPrintList.add(jasperPrint2);


	  ByteArrayOutputStream baos = new ByteArrayOutputStream();     
	  JRPdfExporter exporter = new JRPdfExporter();     
	  //Add the list as a Parameter
	  exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	  //this will make a bookmark in the exported PDF for each of the reports
	  exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
	  exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);       
	  try {
		exporter.exportReport();
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}      
	  return baos.toByteArray();
	}

public static void backPageDownload(HttpServletRequest request,
		HttpServletResponse response) {
	try {
		String classId = request.getParameter("classId");
		String locationId = request.getParameter("locationId");
		String accyearId = request.getParameter("accyearId");
		String term1 = request.getParameter("terms1");
		String term2 = request.getParameter("terms2");
		String examstypeid = request.getParameter("examstypeid");
		String examstypeidterm2 = request.getParameter("examstypeidterm2");
		String stdId = request.getParameter("stdId");
		String sectionId = request.getParameter("sectionId");

		ReportMenuVo vo=new ReportMenuVo();
		vo.setAccYear(accyearId);
		vo.setClassId(classId);
		vo.setLocationId(locationId);
		//vo.setTermname(checkedTermValue);
		vo.setExamtypeid(examstypeid);
		vo.setExamstypeidterm2(examstypeidterm2);
		vo.setStudentId(stdId);
		vo.setSectionId(sectionId);
		vo.setTerm1(term1);
		vo.setTerm2(term2);

		List<ReportMenuVo> stuList = new ReportsMenuBD().getTermWiseReportCard(vo);
		System.out.println("size is "+stuList.size());
		List<ReportMenuVo> marksheet=null;
		ArrayList<ReportMenuVo> singlelist=null;
		String sourceFileName = null;		
		FileOutputStream outputFile=null;
		File secondDir = null;
		File firstDir = null;
		JRBeanCollectionDataSource beanColDataSource = null;
		if(!term2.equals("")){
			sourceFileName=request.getRealPath("Reports/ReportCardByClassTerms2.jrxml");
		}else{
			sourceFileName=request.getRealPath("Reports/ReportCardByClassTerms.jrxml");
		}

		
		/*ReportCard_Dir*/

		/*firstDir = new File(ReportCard_Dir);
		if (firstDir.exists()) {
			secondDir = new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId));
			secondDir.mkdir();
		} else {
			new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId)).mkdirs();
		}
		 */
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		String imageFilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
		String boardFilePath=request.getRealPath("/")+ "images/" + BoardLogo.trim();
		ServletOutputStream outstream =null;
		byte[] bytes=null;
		Map parameters = new HashMap();
		parameters.put("schoollogo",imageFilePath);
		parameters.put("boardlogo",boardFilePath);

		beanColDataSource = new JRBeanCollectionDataSource(stuList);

		bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""+HelperClass.getAcademicYearFace(accyearId)+"-"+HelperClass.getClassName(classId, locationId)+".pdf\"");
		outstream = response.getOutputStream();
		outstream.write(bytes, 0, bytes.length);


		/*singlelist = new ArrayList<ReportMenuVo>();
			System.out.println("STUDENT "+i);
			singlelist.add(stuList.get(i));
			beanColDataSource = new JRBeanCollectionDataSource(singlelist);

			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			outputFile = new FileOutputStream(new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId) + "/" + stuList.get(i).getStudentnamelabel().replaceAll(" ", "_")+".pdf"));
			JasperExportManager.exportReportToPdfStream(print, outputFile);
			outputFile.close();*/

	}	
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
}

//getTerm1Exams
public ActionForward getTerm1Exams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Starting");
	

	try{
		String accyear=request.getParameter("accyear");
		String classId=request.getParameter("classId");
		String locationid=request.getParameter("locationid");
		
		ReportMenuVo examid = new ReportsMenuBD().getTerm1Exams(accyear,classId,locationid);
		
		JSONObject obj = new JSONObject();
		obj.put("examsid", examid.getExamname());
		obj.put("examstypeid", examid.getExamtypeid());
		obj.put("examstypeprefix", examid.getExamtypeprefix());
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Ends");

	
	return null;
}

public ActionForward getTerm2Exams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Starting");

	try{
		String accyear=request.getParameter("accyear");
		String classId=request.getParameter("classId");
		String locationid=request.getParameter("locationid");
		
		ReportMenuVo examid = new ReportsMenuBD().getTerm2Exams(accyear,classId,locationid);
		
		JSONObject obj = new JSONObject();
		obj.put("examsid", examid.getExamname());
		obj.put("examstypeid", examid.getExamtypeid());
		obj.put("examstypeprefix", examid.getExamtypeprefix());
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Ends");

	
	return null;
}

public ActionForward getFinalExams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Starting");

	try{
		String accyear=request.getParameter("accyear");
		String classId=request.getParameter("classId");
		String locationid=request.getParameter("locationid");
		
		ReportMenuVo examid = new ReportsMenuBD().getFinalExams(accyear,classId,locationid);
		
		JSONObject obj = new JSONObject();
		obj.put("examsid", examid.getExamname());
		obj.put("examstypeid", examid.getExamtypeid());
		obj.put("examstypeprefix", examid.getExamtypeprefix());
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getTerm1Exams  Ends");

	
	return null;
}

public ActionForward downloadAcademicYearReportCard(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadReportCard Starting");

	try {
		String classId = request.getParameter("classId");
		String locationId = request.getParameter("locationId");
		String accyearId = request.getParameter("accyearId");
		String checkedTermValue = request.getParameter("terms");
		String examstypeid = request.getParameter("examstypeid");
		String stdId = request.getParameter("stdId");
		String sectionId = request.getParameter("sectionId");
		System.out.println("stdId is "+ stdId);

		String[] arrStringVal=checkedTermValue.split(",");

		ReportMenuVo vo=new ReportMenuVo();
		vo.setAccYear(accyearId);
		vo.setClassId(classId);
		vo.setLocationId(locationId);
		vo.setTermname(checkedTermValue);
		vo.setExamtypeid(examstypeid);
		vo.setStudentId(stdId);
		vo.setSectionId(sectionId);

		List<ReportMenuVo> stuList = new ReportsMenuBD().getAcademicYearWiseReportCard(vo);
		System.out.println("size is "+stuList.size());
		List<ReportMenuVo> marksheet=null;
		ArrayList<ReportMenuVo> singlelist=null;
		String sourceFileName = null;		
		FileOutputStream outputFile=null;
		File secondDir = null;
		File firstDir = null;
		JRBeanCollectionDataSource beanColDataSource = null;
		sourceFileName=request.getRealPath("Reports/ReportCardByClassAcademicYear.jrxml");



		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		String imageFilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
		String boardFilePath=request.getRealPath("/")+ "images/" + BoardLogo.trim();
		ServletOutputStream outstream =null;
		byte[] bytes=null;
		Map parameters = new HashMap();
		parameters.put("schoollogo",imageFilePath);
		parameters.put("boardlogo",boardFilePath);

		beanColDataSource = new JRBeanCollectionDataSource(stuList);

		bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""+HelperClass.getAcademicYearFace(accyearId)+"-"+HelperClass.getClassName(classId, locationId)+".pdf\"");
		outstream = response.getOutputStream();
		outstream.write(bytes, 0, bytes.length);


		
	}	
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : downloadReportCard Ending");

	return null;
}
public ActionForward religionDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : religionDetails Starting");
	try {
		

		ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
		List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			list = obj.getReligionDetails();
		
			JSONObject objr = new JSONObject();
			objr.put("data", list);
			response.getWriter().print(objr);
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : religionDetails Ending");

	return null;
}

public ActionForward categoryName(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : categoryName Starting");
	try {
		

		
		List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			list = new ReportsMenuDaoImpl().categoryName();
		
			JSONObject objr = new JSONObject();
			objr.put("data", list);
			response.getWriter().print(objr);
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : categoryName Ending");

	return null;
}
public ActionForward getStudentCountSelectionWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentCountSelectionWise  Starting");

	try{
		String accyear=request.getParameter("accyear");
		String selection=request.getParameter("selection");
		String locationid=request.getParameter("locationid");
		
		List<ReportMenuVo> data = new ReportsMenuDaoImpl().getStudentCountSelectionWise(accyear,selection,locationid);
		
		JSONObject obj = new JSONObject();
		obj.put("data", data);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentCountSelectionWise  Ends");

	
	return null;
}
public ActionForward getClassListSelectionWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentCountSelectionWise  Starting");

	try{
		String accyear=request.getParameter("accyear");
		String selection=request.getParameter("selection");
		String locationid=request.getParameter("locationid");
		
		List<ReportMenuVo> data = new ReportsMenuDaoImpl().getClassListSelectionWise(accyear,selection,locationid);
		
		JSONObject obj = new JSONObject();
		obj.put("data", data);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentCountSelectionWise  Ends");

	
	return null;
}
}
	
	
	

