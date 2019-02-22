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
import com.centris.campus.delegate.StudentEnquiryBD;
import com.centris.campus.forms.StudentEnquiryForm;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentEnquiryPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StudentEnquiryVo;

public class StudentEnquiryAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(StudentEnquiryAction.class);


	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward getAllEnquiryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getAllEnquiryDetails Starting");
		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		try {
			String text = request.getParameter("status");
			request.setAttribute("success", text);

			allDetails = delegateObj.getAllEnquiryDetails();
			request.setAttribute("EnquiryDetails", allDetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getAllEnquiryDetails Ending");
		return mapping.findForward("listOfStudents");
	}

	public ActionForward getSelectedEnquiryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getSelectedEnquiryDetails Starting");
		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		String enqId = request.getParameter("Code").trim();
		try {
			allDetails = delegateObj.getSelectedEnquiryDetails(enqId);

			StudentEnquiryVo details = new StudentEnquiryVo();
			details = (StudentEnquiryVo) allDetails.get(0);

			request.setAttribute("EnquiryDetails", details);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getSelectedEnquiryDetails Ending");
		return mapping.findForward(MessageConstants.STUDENTENQUIRYADD);
	}

	public ActionForward deleteEnquiryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : deleteEnquiryDetails Starting");

		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		String enqId = request.getParameter("Code").trim();
		try {
			boolean status = delegateObj.deleteEnquiryDetails(enqId);
			request.setAttribute("EnquiryDetails", status);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : deleteEnquiryDetails Ending");
		return null;
	}

	public ActionForward saveStudentEnquiry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: saveStudentEnquiry  Starting");
		try {

			StudentEnquiryForm studentEnquiryForm = new StudentEnquiryForm();

			studentEnquiryForm.setEnq_FirstName(request
					.getParameter("firstname"));
			studentEnquiryForm
					.setEnq_LastName(request.getParameter("LastName"));
			studentEnquiryForm.setEnq_studClassId(request
					.getParameter("studClassId"));

			studentEnquiryForm.setAppno(request.getParameter("appno"));
			studentEnquiryForm.setEnq_source(request.getParameter("source"));
			studentEnquiryForm.setEnq_dateofJoin(request
					.getParameter("dateofjoining"));
			studentEnquiryForm.setEnq_dateofBirth(request
					.getParameter("dateofBirth"));
			studentEnquiryForm.setEnq_academicYear(request
					.getParameter("academicYear"));
			studentEnquiryForm.setEnq_age(request.getParameter("age"));
			studentEnquiryForm
					.setEnq_religion(request.getParameter("religion"));
			studentEnquiryForm.setEnq_gender(request.getParameter("gen"));
			studentEnquiryForm.setEnq_physicallyChallenged(request
					.getParameter("phyChallenged"));
			studentEnquiryForm.setEnq_contacttype(request
					.getParameter("cantacttype"));
			studentEnquiryForm.setEnq_contactname(request
					.getParameter("cantactname"));
			studentEnquiryForm
					.setEnq_MobileNo(request.getParameter("mobileno"));
			studentEnquiryForm.setEnq_contactmailId(request
					.getParameter("contactmailId"));
			studentEnquiryForm.setEnq_addressstreet1(request
					.getParameter("paddrs"));
			studentEnquiryForm.setInteraction(request
					.getParameter("interaction"));
			studentEnquiryForm.setAdmissionstatus(request
					.getParameter("admissionstatus"));
			studentEnquiryForm.setCompletiondate(request
					.getParameter("completiondate"));
			studentEnquiryForm.setAdmissiondate(request
					.getParameter("admissiondate"));
			studentEnquiryForm.setJoiningId(request.getParameter("joiningId"));

			studentEnquiryForm.setStatus(request.getParameter("hidsatatus")
					.trim());

			if ("update".equals(studentEnquiryForm.getStatus())) {
				studentEnquiryForm.setEnquiryid(request
						.getParameter("enquiryid"));
			} else {
				studentEnquiryForm.setEnquiryid(IDGenerator
						.getPrimaryKeyID("campus_student_enquiry"));
			}

			studentEnquiryForm.setCreate_date(String.valueOf(HelperClass
					.getCurrentSqlDate()));

			studentEnquiryForm.setCreate_username(HelperClass
					.getCurrentUserID(request));

			String status = new StudentEnquiryBD()
					.saveStudentEnquiry(studentEnquiryForm);

			request.setAttribute("status", status);

			JSONObject ob = new JSONObject();
			ob.put("status", status);
			response.getWriter().print(ob);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: saveStudentEnquiry  Ending");

		return null;
	}

	public ActionForward duplicateStudentChecking(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: duplicateStudentChecking  Starting");
		try {

			StudentEnquiryPojo studentenquiryPojo = new StudentEnquiryPojo();

			studentenquiryPojo.setEnq_FirstName(request
					.getParameter("firstname"));
			studentenquiryPojo.setEnq_dateofBirth(HelperClass
					.convertUIToDatabase(request.getParameter("dateofBirth")));
			studentenquiryPojo.setEnq_gender(request.getParameter("gen"));
			studentenquiryPojo.setEnq_physicallyChallenged(request
					.getParameter("phyChallenged"));
			studentenquiryPojo
					.setEnq_category(request.getParameter("category"));
			studentenquiryPojo.setEnq_studClassId(request
					.getParameter("studClassId"));
			studentenquiryPojo.setEnq_academicYear(request
					.getParameter("academicYear"));
			studentenquiryPojo.setEnq_contacttype(request
					.getParameter("cantacttype"));
			studentenquiryPojo.setEnq_contactname(request
					.getParameter("cantactname"));
			studentenquiryPojo
					.setEnq_MobileNo(request.getParameter("mobileno"));

			boolean status = new StudentEnquiryBD()
					.duplicateStudentChecking(studentenquiryPojo);
			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: duplicateStudentChecking  Ending");

		return null;
	}

	public ActionForward validationMobileno(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: validationMobileno  Starting");
		try {

			String mobileno = request.getParameter("mobileno");

			boolean status = new StudentEnquiryBD()
					.validationMobileno(mobileno);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: validationMobileno  Ending");

		return null;
	}

	public ActionForward getEnquiryDetailsBySearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getEnquiryDetailsBySearch Starting");
		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		try {
			String date = request.getParameter("ser_Date").trim();
			String name = request.getParameter("ser_Name").trim();
			String interaction = request.getParameter("ser_Interaction").trim();
			String admission = request.getParameter("ser_Admission").trim();

			allDetails = delegateObj.getEnquiryDetailsBySearch(date, name,
					interaction, admission);
			request.setAttribute("EnquiryDetails", allDetails);

			request.setAttribute("sel_date", date);
			request.setAttribute("sel_name", name);
			request.setAttribute("sel_interaction", interaction);
			request.setAttribute("sel_admission", admission);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : getEnquiryDetailsBySearch Ending");
		return mapping.findForward("listOfStudents");
	}

	public ActionForward updateStudentEnquiry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : updateStudentEnquiry Starting");
		String usercode = HelperClass.getCurrentUser(request);
		try {

			/*
			 * "int_date": int_date, "adm_date":adm_date,
			 */

			StudentEnquiryVo studentenquiry = new StudentEnquiryVo();

			studentenquiry.setEnq_fname(request.getParameter("fname"));
			studentenquiry.setEnq_app_no(request.getParameter("App_no"));
			studentenquiry.setEnq_lname(request.getParameter("lname"));
			studentenquiry.setEnq_dob(HelperClass.convertUIToDatabase(request
					.getParameter("dob")));
			studentenquiry.setEnq_age(request.getParameter("age"));
			studentenquiry.setEnq_gender(request.getParameter("gender"));
			studentenquiry.setEnq_phyhand(request
					.getParameter("physicallyChallenged"));
			studentenquiry.setEnq_religion(request.getParameter("religion"));
			studentenquiry.setEnq_doj(HelperClass.convertUIToDatabase(request
					.getParameter("dateofJoin")));
			studentenquiry.setEnq_streamId(request.getParameter("category"));
			studentenquiry.setEnq_classId(request.getParameter("studClassId"));
			studentenquiry.setEnq_accId(request.getParameter("academicYear"));
			studentenquiry.setEnq_contype(request.getParameter("contacttype"));
			studentenquiry.setEnq_conname(request.getParameter("contactname"));
			studentenquiry.setEnq_conPhno(request.getParameter("MobileNo"));
			studentenquiry.setEnq_conemailId(request
					.getParameter("contactmailId"));
			studentenquiry.setEnq_street1(request
					.getParameter("addressstreet1"));
			studentenquiry.setEnq_street2(request
					.getParameter("addressstreet2"));
			studentenquiry.setEnq_location(request.getParameter("locat"));
			studentenquiry.setEnq_city(request.getParameter("townandcity"));
			studentenquiry.setEnq_pincode(request.getParameter("zippostcode"));
			studentenquiry.setEnq_state(request.getParameter("state"));
			studentenquiry.setEnq_country(request.getParameter("country"));
			studentenquiry
					.setEnq_int_status(request.getParameter("int_status"));
			studentenquiry
					.setEnq_reg_status(request.getParameter("adm_status"));
			studentenquiry.setEnq_sno(request.getParameter("enqId"));
			studentenquiry.setEnq_int_date(request.getParameter("int_date"));
			studentenquiry.setEnq_adm_date(request.getParameter("adm_date"));
			studentenquiry.setUsercode(usercode);

			boolean status = new StudentEnquiryBD()
					.updateStudentEnquiry(studentenquiry);

			JSONObject object = new JSONObject();
			object.put("message", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : updateStudentEnquiry Ending");
		return null;
	}

	public ActionForward applicationNoValidate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: applicationNoValidate  Starting");
		try {

			String appnumber = request.getParameter("appnumber");

			boolean status = new StudentEnquiryBD()
					.applicationNoValidate(appnumber);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: applicationNoValidate  Ending");

		return null;
	}

	public ActionForward validationPhoneno(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: validationMobileno  Starting");
		try {

			String mobileno = request.getParameter("mobileno");
			String enq_Id = request.getParameter("enqId");

			boolean status = new StudentEnquiryBD().validationPhoneno(mobileno,
					enq_Id);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction: validationMobileno  Ending");

		return null;
	}

	public ActionForward downloadenquiryDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryAction : downloadenquiryDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/studentenquiryDetails.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
					
			
			List<StudentEnquiryVo> details = new ArrayList<StudentEnquiryVo>();
			StudentEnquiryBD delegateObj = new StudentEnquiryBD();
			details = delegateObj.getAllEnquiryDetails();

			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					details);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/studentenquiryDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Enquiry Details Excel Report" };
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
					request.getRealPath("Reports/studentenquiryDetails.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=studentenquiryDetails.xls");
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
				+ " Control in StudentEnquiryAction : downloadenquiryDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadenquiryDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentEnquiryAction : downloadenquiryDetailsPDF  Starting");

			try {

				System.out.println("downloading pdf");
								
				List<StudentEnquiryVo> Details = new ArrayList<StudentEnquiryVo>();
				StudentEnquiryBD delegateObj = new StudentEnquiryBD();
				Details = delegateObj.getAllEnquiryDetails();
				
				String sourceFileName = request
						.getRealPath("Reports/studentenquirrDetailsPDF.jrxml");
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


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "studentenquirrDetailsPDF - " + ".pdf\"");

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
					+ " Control in StudentEnquiryAction : downloadenquiryDetailsPDF  Ending");
			return null;

		}




}