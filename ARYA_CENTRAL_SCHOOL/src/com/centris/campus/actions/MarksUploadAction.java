package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.centris.campus.delegate.MarksUploadDelegate;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDisplayVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.registrationvo;

public class MarksUploadAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(MarksUploadAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward uploadMarks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : uploadMarks Starting");

		try {

			System.out.println("in upload marks");

			String hExamId = request.getParameter("hExamId");
			String hclass = request.getParameter("hclass");
			String hSectionId = request.getParameter("hSectionId");
			String hSubjectId = request.getParameter("hSubjectId");
			String maxmarks = request.getParameter("maxmarks");
			String minPassmarks = request.getParameter("minPassmarks");
			String[] studentIdArray = request.getParameter("studentIdArray")
					.split(",");
			String[] acuiredmarksArray = request.getParameter(
					"acuiredmarksArray").split(",");

			MarksUploadVO marksUpload = new MarksUploadVO();

			System.out.println("hExamId ::: " + hExamId);
			System.out.println("hSubjectId ::: " + hSubjectId);

			marksUpload.setClassid(hclass);
			marksUpload.setSectionid(hSectionId);
			marksUpload.setMaxmarks(maxmarks);
			marksUpload.setReqmarks(minPassmarks);
			marksUpload.setStudSubId(hSubjectId);
			marksUpload.setExamination(hExamId);
			marksUpload.setStudentIdArray(Arrays.asList(studentIdArray));
			marksUpload.setScoredmarksArray(Arrays.asList(acuiredmarksArray));

			String message = new MarksUploadDelegate()
					.getMarksUpload(marksUpload);

			JSONObject object = new JSONObject();
			object.put("Status", message);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : uploadMarks Ending");
		return null;
	}

	public ActionForward showUploadedMarks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : showUploadedMarks Starting");
		try {
			StudentMarksDisplayVO studentmarks = new StudentMarksDisplayVO();
			studentmarks
					.setStudSectionId(request.getParameter("studSectionId"));
			List<ScoredMarksEntryVO> ScoredMarks = new MarksUploadDelegate()
					.showStudentMarks(studentmarks);
			JSONObject jsonObject = new JSONObject(ScoredMarks);
			jsonObject.accumulate("ScoredMarks", ScoredMarks);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : showUploadedMarks Ending");
		return null;
	}

	public ActionForward getStudentMarks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : getStudentMarks Starting");
		try {

			String marksUploadId = request.getParameter("searchTerm");

			String[] marksUploadDetails = marksUploadId.split(",");

			StudentMarksDisplayVO studentmarks = new StudentMarksDisplayVO();

			studentmarks.setStudExamId(marksUploadDetails[0]);
			studentmarks.setStudClassId(marksUploadDetails[1]);
			studentmarks.setStudSectionId(marksUploadDetails[2]);
			studentmarks.setStudSubjectId(marksUploadDetails[3]);

			List<ScoredMarksEntryVO> ScoredMarks = new MarksUploadDelegate()
					.getStudentMarks(studentmarks);

			request.setAttribute("StudentMarksDetails", ScoredMarks);

			request.setAttribute("ExamId", marksUploadDetails[0]);
			request.setAttribute("ExamName", marksUploadDetails[4]);
			request.setAttribute("ClassId", marksUploadDetails[1]);
			request.setAttribute("ClassName", marksUploadDetails[5]);
			request.setAttribute("SectionId", marksUploadDetails[2]);
			request.setAttribute("SectionName", marksUploadDetails[6]);
			request.setAttribute("SubjectId", marksUploadDetails[3]);
			request.setAttribute("SubjectName", marksUploadDetails[7]);

			if (ScoredMarks.get(ScoredMarks.size() - 1) == null) {

				request.setAttribute("MaxMarks", "");
				request.setAttribute("MinMarks", "");
			} else {

				request.setAttribute("MaxMarks",
						ScoredMarks.get(ScoredMarks.size() - 1).getMaxMarks());
				request.setAttribute("MinMarks",
						ScoredMarks.get(ScoredMarks.size() - 1).getMinMarks());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : getStudentMarks Ending");
		return mapping.findForward("ViewStudentMarks");
	}

	public ActionForward downloadmarksDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : downloadmarksDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/exammarksdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			
			List<MarksUploadVO> streamList = new ArrayList<MarksUploadVO>();
			streamList = (List<MarksUploadVO>) request.getSession(false).getAttribute("EXcelDownLoad");
			
			

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/exammarksdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Exam Mark Details Excel Report" };
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
					request.getRealPath("Reports/Exammarksdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Exammarksdetailsxls.xls");
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
				+ " Control in MarksUploadAction : downloadmarksDetailsXLS   Ending");
		return null;

	}

	public ActionForward downloadmarksDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadAction : downloadmarksDetailsPDF  Starting");

		try {

			System.out.println("downloading pdf");

			List<MarksUploadVO> Details = new ArrayList<MarksUploadVO>();
			Details = (List<MarksUploadVO>) request.getSession(false).getAttribute("EXcelDownLoad");
			
				String sourceFileName = request
					.getRealPath("Reports/ExamMarkDetailsPDF.jrxml");
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
					+ "ExamMarkDetailsPDF - " + ".pdf\"");

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
				+ " Control in MarksUploadAction : downloadmarksDetailsPDF  Ending");
		return null;

	}

}