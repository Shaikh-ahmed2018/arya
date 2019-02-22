package com.centris.campus.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
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
import net.sf.jasperreports.olap.mapping.Mapping;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.Inventory_Delegate;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ParentRequiresAppointmentDELEGATE;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SecondAppointmentDELEGATE;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.InventoryVO;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;
import com.centris.campus.vo.secadmissionformformatVO;
import com.itextpdf.text.log.SysoLogger;

public class ParentRequiresAppointmentAction extends DispatchAction

{

	private static final Logger logger = Logger
			.getLogger(ParentRequiresAppointmentAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward EditingForAdmissionApproval(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {
			

			String temporary_id = request.getParameter("name");

			ParentRequiresAppointmentVO detailsVo = new ParentRequiresAppointmentVO();

			System.out.println("Temporary_id:::::" + temporary_id);

			detailsVo.setTemporary_id(temporary_id);

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();

			ParentRequiresAppointmentVO ckeck = detailsBD
					.EditingForAdmissionApproval(detailsVo);

			request.setAttribute("studentSearchList", ckeck);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping.findForward(MessageConstants.GETTING_SINGLE_ADMISSION);

	}

	public ActionForward UpdatingFirstLevelAdmissionApproval(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {

			String temporary_id = request.getParameter("temporary_id");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");

			String dateofAppointmentId = request
					.getParameter("dateofAppointmentId");
			String starttime = request.getParameter("starttime");

			ParentRequiresAppointmentVO detailsVo = new ParentRequiresAppointmentVO();

			System.out.println("Temporary_id:::::" + temporary_id);
			System.out.println("status:::::" + status);
			System.out.println("remarks:::::" + remarks);

			detailsVo.setTemporary_id(temporary_id);
			detailsVo.setRemarks(remarks);
			detailsVo.setStatus(status);
			detailsVo.setModifiedby(HelperClass.getCurrentUserID(request));
			detailsVo.setAppointment_date(dateofAppointmentId);
			detailsVo.setAppointment_time(starttime);

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();

			String ckeck = detailsBD
					.UpdatingFirstLevelAdmissionApproval(detailsVo);

			request.setAttribute("studentSearchList", ckeck);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");
			request.setAttribute("classList", classList);

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();
			list = obj.getAdmisssionProcessingListDetails();
			request.setAttribute("TemporaryAdmissionDetailsList", list);

			if (ckeck == "true" || ckeck.equalsIgnoreCase("true")) {
				request.setAttribute("successmessagediv",
						"Mail Sent Successfully For Further Process");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping.findForward(MessageConstants.ADMISSION_LIST);

	}
	public ActionForward printApplicationForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : printApplicationForm  Starting");
		try {
			
			ParentRequiresAppointmentForm parentform=(ParentRequiresAppointmentForm) request.getSession(false).getAttribute("hstudentinformation");
			request.setAttribute("studentinformation", parentform);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : printApplicationForm   Ending");

		return mapping.findForward(MessageConstants.SUCCESSPAGEDISPLAY);

	}
	public ActionForward InsertTemporaryStudentRegistration(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {
		
		UserDetailVO userd=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			System.out.println("INSERTING TEMPORARY REGISTRATION");

			ParentRequiresAppointmentForm parentform = (ParentRequiresAppointmentForm) form;
			
			parentform.setFatherOccupationName(HelperClass.getOccupationName(parentform.getFatherOccupation()));
			parentform.setMotherOccupationName(HelperClass.getOccupationName(parentform.getMotherOccupation()));
			parentform.setClassName(HelperClass.className(parentform.getClassname().split("D")[1]));
			if(parentform.getCaste().equalsIgnoreCase("Other"))	{
				parentform.setRelname(parentform.getOthercaste());
			}
			else {
				parentform.setCastename(HelperClass.getCasteName(parentform.getCaste()));
			}
			if(parentform.getReligion().equalsIgnoreCase("Other"))	{
				parentform.setRelname(parentform.getOtherreligion());
			}
			else {
				parentform.setRelname(HelperClass.getRelegionName(parentform.getReligion()));
			}
			
			
			parentform.setCastecatname(HelperClass.getCasteCategoryName(parentform.getCasteCategory()));
			
			if(parentform.getCastecatname().equalsIgnoreCase("ST") || parentform.getCastecatname().equalsIgnoreCase("SC") || parentform.getCastecatname().equalsIgnoreCase("OBC") ){
				parentform.setCastecatname("YES");
			}else{
				parentform.setCastecatname("NO");
			}
			com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();
			
			String datewords = null;
			String[] split=parentform.getDateofBirth().split("-");
			int day = Integer.parseInt(split[0]);
			int month = Integer.parseInt(split[1]);
			int year = Integer.parseInt(split[2]);
			datewords = (num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year));
			request.setAttribute("dateofbirthinwords",datewords);
			parentform.setDobinwords(datewords);
			String cuudate = HelperClass.getCurrentSqlDate().toString();
			List<String> datesize = HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(parentform.getDateofBirth()),cuudate);
			int years = (datesize.size()-1)/365;
			int months = ((datesize.size()-1)%365)/30;
			parentform.setStuage(years+" Years "+months+" Months ");
			
			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();
			FormFile imagePath = null;
			String realPath = "";
			String bearthRealPath="";
			FormFile bearthImagePath=null;
			FileOutputStream outputStream = null;
			String imagepath1 = "";
			String imagepath2 = "";
			String fileName = "";
			String birthfileName = "";
			String StudentIDGenerator = null;
			StudentIDGenerator = IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details");
			try {
				String extension="";
				imagePath = parentform.getInputfile();

				int dot =0;
				if (imagePath != null) {

					fileName = imagePath.getFileName();
					dot = imagePath.getFileName().lastIndexOf('.');
				}
 
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName != null && fileName.length() < 2) {

					parentform.setImageString("FIles/StudentImageTempStorage/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath1 =  request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath1);
					realPath = imagepath1.substring(imagepath1.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath1));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath1 =  request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath1.substring(imagepath1.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath1));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath1 = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath1.substring(imagepath1.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath1));
					outputStream.write(imagePath.getFileData());
				}
				parentform.setImageString(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} 
			
			try {
				String extension="";
				bearthImagePath = parentform.getBirthCertificateFile();

				if (bearthImagePath != null) {

					birthfileName = bearthImagePath.getFileName();
				}

				int dot = bearthImagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = bearthImagePath.getFileName().substring(dot+1);
				}

				if (birthfileName.length() < 2 && fileName != null) {

					parentform.setBirthCertificateFileString("FIles/StudentImageTempStorage/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath2 = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/BirthCertificateFile"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath2);
					bearthRealPath = imagepath2.substring(imagepath2.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath2));
					outputStream.write(bearthImagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath2 = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/BirthCertificateFile"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					bearthRealPath = imagepath1.substring(imagepath2.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath2));
					outputStream.write(bearthImagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath2 = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/BirthCertificateFile"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					bearthRealPath = imagepath2.substring(imagepath2.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath2));
					outputStream.write(bearthImagePath.getFileData());
				}
				else if(extension.equalsIgnoreCase("pdf")){
					imagepath2 = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/BirthCertificateFile"+parentform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					bearthRealPath = imagepath2.substring(imagepath2.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath2));
					outputStream.write(bearthImagePath.getFileData());
				}
				parentform.setBirthCertificateFileString(bearthRealPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}
			
			parentform.setSchoolLocation(userd.getLocationid());
			parentform.setStream(userd.getStreamId());
			String details = detailsBD.InsertTemporaryStudentRegistration(parentform);
			
			HttpSession session = request.getSession(true);
			if(details.equalsIgnoreCase("false")) {
				
			}
			else {
				session.setAttribute("hstudentinformation",parentform);
				request.setAttribute("admissionno",details);
				response.sendRedirect("parentrequiresappointment.html?method=printApplicationForm");
			}
			
		
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return null;

	}
	
	public ActionForward saveSecondAdmissionApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {
			
			UserDetailVO userd=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			

			ParentRequiresAppointmentForm secform = (ParentRequiresAppointmentForm) form;
			
			secform.setFatherOccupationName(HelperClass.getOccupationName(secform.getFatherOccupation()));
			secform.setMotherOccupationName(HelperClass.getOccupationName(secform.getMotherOccupation()));
			secform.setClassName(HelperClass.className(secform.getClassname().split("D")[1]));
			if(secform.getCaste().equalsIgnoreCase("Other"))	{
				secform.setRelname(secform.getOthercaste());
			}
			else {
				secform.setCastename(HelperClass.getCasteName(secform.getCaste()));
			}
			if(secform.getReligion().equalsIgnoreCase("Other"))	{
				secform.setRelname(secform.getOtherreligion());
			}
			else {
				secform.setRelname(HelperClass.getRelegionName(secform.getReligion()));
			}
			
			
			secform.setCastecatname(HelperClass.getCasteCategoryName(secform.getCasteCategory()));
			
			SecondAppointmentDELEGATE secformBD = new SecondAppointmentDELEGATE();
			FormFile imagePath = null;
			String realPath = "";
			FileOutputStream outputStream = null;
			String imagepath;
			String fileName = "";
			String StudentIDGenerator=null;

			StudentIDGenerator = IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details");
			try {
				String extension="";
				imagePath = secform.getInputfile();

				if (imagePath != null) {

					fileName = imagePath.getFileName();
				}

				int dot = imagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName.length() < 2 && fileName != null) {

					secform.setImageString("FIles/STUDENTIMAGES/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath);
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}
				secform.setImageString(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}
			secform.setLastKindergartenName(secform.getLastSchool());
			secform.setSchoolLocation(userd.getLocationid());
			secform.setStream(userd.getStreamId());
			String details = secformBD.InsertSecadmissionform(secform);
			
			
			HttpSession session = request.getSession(true);

			request.setAttribute("admissionno", details);
			session.setAttribute("hstudentinformation",secform);
			response.sendRedirect("parentrequiresappointment.html?method=printApplicationForm");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return null;

	}
		
		
	
	public ActionForward DeleteParentRequiresAppointment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		try {

			String id = request.getParameter("name");
			System.out.println(id);
			ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();

			vo.setTemporary_id(id);

			String status = detailsBD.DeleteParentRequiresAppointment(vo);

			if (status == "true") {
				request.setAttribute("successmessagediv",
						"Admission Details  Deleted Successfully");
			} else if (status == "false") {
				request.setAttribute("errorMessage",
						"Admission Details Not Deleted Successfully");
			}

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();
			list = detailsBD.getAdmisssionProcessingListDetails();
			request.setAttribute("TemporaryAdmissionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return mapping.findForward(MessageConstants.ADMISSION_LIST);

	}

	public ActionForward EditingForCalledAdmissionDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {
			/*
			 * request.setAttribute(MessageConstants.MODULE_NAME,
			 * MessageConstants.BACKOFFICE_SETTINGS);
			 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
			 * MessageConstants.MODULE_SETTINGS);
			 */

			String temporary_id = request.getParameter("name");
			ParentRequiresAppointmentVO detailsVo = new ParentRequiresAppointmentVO();
			System.out.println("Temporary_id:::::" + temporary_id);
			detailsVo.setTemporary_id(temporary_id);
			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();
			String userid = HelperClass.getCurrentUserID(request);

			System.out.println("userid::::" + userid);
			detailsVo.setCurrent_userid(userid);

			ParentRequiresAppointmentVO ckeck = detailsBD
					.EditingForCalledAdmissionDetails(detailsVo);

			request.setAttribute("studentSearchList", ckeck);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("AccYearList", accYearList);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%");
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping
				.findForward(MessageConstants.GETTING_SINGLE_CALLED_FOR_ADMISSION_ENTRY);

	}
	
	
	

	public ActionForward UpdatingCalledForEvaluationStatus(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {

			String temporary_id = request.getParameter("temporary_id");
			String test = request.getParameter("test");
			String max_marks = request.getParameter("max_marks");
			String status = request.getParameter("recommended_status");
			String evaluation_date = request.getParameter("evaluation_date");
			String marks_secured = request.getParameter("marks_secured");
			String remarks = request.getParameter("remarks");
			String userid = HelperClass.getCurrentUserID(request);
			String evaluated_by = request.getParameter("evaluated_by");

			ParentRequiresAppointmentVO details = new ParentRequiresAppointmentVO();

			System.out.println("Temporary_id:::::" + temporary_id);
			System.out.println("test:::::" + test);
			System.out.println("max_marks:::::" + max_marks);
			System.out.println("recommended_status:::::" + status);
			System.out.println("evaluation_date:::::" + evaluation_date);
			System.out.println("marks_secured" + marks_secured);
			System.out.println("remarks:::::" + remarks);
			System.out.println("evaluated_by:::::" + evaluated_by);
			System.out.println("userid:::::" + userid);

			details.setTemporary_id(temporary_id);
			details.setTest(test);
			details.setMax_marks(max_marks);
			details.setStatus(status);
			details.setEvaluation_date(evaluation_date);
			details.setMarks_secured(marks_secured);
			details.setRemarks(remarks);
			details.setEvaluated_by(evaluated_by);
			details.setCurrent_user(userid);

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();

			String ckeck = detailsBD.UpdatingCalledForEvaluationStatus(details);

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			list = obj.CalledForEvaluationList();
			request.setAttribute("TemporaryAdmissionDetailsList", list);

			if (ckeck == "true" || ckeck.equalsIgnoreCase("true")) {
				request.setAttribute("successmessagediv",
						"Evaluation Status Updated Successfully");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping.findForward(MessageConstants.CALLED_FOR_ADMISSION_LIST);
	}
	
	public ActionForward EditingForConfirmingAdmissionDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {
			/*
			 * request.setAttribute(MessageConstants.MODULE_NAME,
			 * MessageConstants.BACKOFFICE_SETTINGS);
			 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
			 * MessageConstants.MODULE_SETTINGS);
			 */

			String temporary_id = request.getParameter("name");
			ParentRequiresAppointmentVO detailsVo = new ParentRequiresAppointmentVO();
			detailsVo.setTemporary_id(temporary_id);
			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();
			String userid = HelperClass.getCurrentUserID(request);
			detailsVo.setCurrent_userid(userid);

			ParentRequiresAppointmentVO ckeck = detailsBD.EditingForConfirmingAdmissionDetails(detailsVo);
			
			System.out.println("Check:::"+ckeck.getEvaluated_by());

			request.setAttribute("studentSearchList", ckeck);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%");
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping
				.findForward(MessageConstants.GETTING_SINGLE_FINAL_ADMISSION_ENTRY);

	}
	
	public ActionForward UpdatingFinalApprovalAdmissionStatus(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {

			
			String temporary_id = request.getParameter("temporary_id");
			String principal_status = request.getParameter("approval_status");
			String principal_remarks = request.getParameter("principal_remarks");
			String userid = HelperClass.getCurrentUserID(request);
			

			ParentRequiresAppointmentVO details = new ParentRequiresAppointmentVO();

			System.out.println("Temporary_id:::::" + temporary_id);
			System.out.println("principal_remarks:::::" + principal_remarks);
			System.out.println("principal_status:::::" + principal_status);
			System.out.println("userid:::::" + userid);

			details.setTemporary_id(temporary_id);
			details.setCurrent_user(userid);
			details.setPrincipal_remarks(principal_remarks);
			details.setPrincipal_status(principal_status);
			
			details.setStudentPhoto(getServlet()
					.getServletContext().getRealPath("\\")
					+ "FIles/STUDENTIMAGES/noImage.png");

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();

			String ckeck = detailsBD.UpdatingFinalApprovalAdmissionStatus(details);

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			
			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();
		
			list = obj.FinalAdmisssionList();
			
			request.setAttribute("TemporaryAdmissionDetailsList", list);

			if (ckeck == "true" || ckeck.equalsIgnoreCase("true")) {
				request.setAttribute("successMessage",
						"Admission Status Updated Successfully and Confirmation mail sent");
			}
			else
			{
				request.setAttribute("errorMessage",
						"Admission Status Not Updated Successfully");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping.findForward(MessageConstants.FINAL_ADMISSION_LIST);
	}

	public ActionForward secondadmissionformat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String secregno=null;
		 
		secregno = new ParentRequiresAppointmentDELEGATE().getsecform();
		System.out.println("in Action SecRegNo is:" +secregno);
		request.setAttribute("SecAdmNo", secregno);
		

		return mapping.findForward(MessageConstants.SECONDADMISSIONFORMAT);

	}
	

	public ActionForward downloadLKGUKGForm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws  Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : downloadLKGUKGForm  Starting");
		try{
			
			System.out.println("Download form-------------");
			String sourceFileName = request
			.getRealPath("Reports/AryaSmartKids_registrationform.jrxml");
	JasperDesign design = JRXmlLoader.load(sourceFileName);

	JasperReport jasperreport = JasperCompileManager
			.compileReport(design);
	
	String PropfilePath = getServlet().getServletContext().getRealPath(
			"")
			+ "\\images\\" + ImageName.trim();

	System.out.println("Download form-------------"+request.getParameter("tempId"));
	String schName = res.getString("SchoolName");
	String schAddLine1 = res.getString("AddressLine1");
	
	String id=request.getParameter("tempId");
	ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();
	ArrayList<AryasmartschoolVo> list =new ArrayList<AryasmartschoolVo>();
	
	list=detailsBD.getimageName(id);
	
	System.out.println(list);
	
	System.out.println(list.get(0).getIsSibling());
	

	
	/*String filePath = getServlet().getServletContext()
			.getRealPath("/") + "FIles/STUDENTIMAGES\\"+imageName.trim();*/
	
	
	
	Map parameters = new HashMap();
	
	parameters.put("Image", PropfilePath);
	parameters.put("tempId",request.getParameter("tempId"));
	parameters.put("dob", list.get(0).getDateofBirthId());
	
	System.out.println("------------------>"+list.get(0).getIsSibling());
	
	if(list.get(0).getIsSibling().equalsIgnoreCase("Y")){
		
		parameters.put("siblingName", list.get(0).getSiblingname())	;
		parameters.put("siblingclass", list.get(0).getSiblingclass());
	}
	else{
		parameters.put("siblingName", "-");
		parameters.put("siblingclass", "-");
	}
	if(list.get(0).getIsaluminiFather().equalsIgnoreCase("Y")){
		parameters.put("fyear", list.get(0).getFaluminiyear());
		parameters.put("fname",list.get(0).getTemporary_fathername());
	}
	else{
		parameters.put("fyear", "-");
		parameters.put("fname","-");
	}
	if(list.get(0).getIsaluminiMother().equalsIgnoreCase("Y")){
		parameters.put("myear", list.get(0).getMaluminiyear());
		parameters.put("mname",list.get(0).getMothername());
	}
	else{
		parameters.put("myear", "-");
		parameters.put("mname","-");
	}
	
	
	/*parameters.put("studentImage", filePath);
	*/
	
	
	
	byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
			parameters, JDBCConnection.getConnection());

	response.setContentType("application/pdf");

	response.setContentLength(bytes.length);

	response.setHeader("Content-Disposition", "outline; filename=\""
			+ "Sample - " + ".pdf\"");

	ServletOutputStream outstream = response.getOutputStream();

	outstream.write(bytes, 0, bytes.length);

	outstream.flush();
	
			
		}catch(Exception e){
			
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : downloadLKGUKGForm   Ending");
		return null;
	}
	
	public ActionForward downloadLocationDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Starting");
		
		
		try {
			

			/*LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			
			String SearchName = request.getParameter("searchTerm");
			
			if(SearchName != null){
				
				list =obj.searchLocationDetails(SearchName);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				list = obj.getLocationDetails();
				
			}*/
			
			List<LocationVO> list = new ArrayList<LocationVO>();
			
			String sourceFileName = request
					.getRealPath("Reports/report1.jrxml");
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

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "report1 - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
			System.out.println("<--------------------->");
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Ending");
	
		return null;

	}	
	
	public ActionForward downloadsecadmissionapplication(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : downloadsecadmissionapplication  Starting");
		
		try{
			System.out.println("------------------");
			
			
			ParentRequiresAppointmentDELEGATE obj= new ParentRequiresAppointmentDELEGATE();
			ArrayList<SecondAdmissionformVo> list = new ArrayList<SecondAdmissionformVo>();
			list= obj.downloadsecadmissionapplication(request.getParameter("appId"));
			Map parameters = new HashMap();
			
			String sourceFileName = request
					.getRealPath("Reports/REGISTRATIONFORADMISSION(I-X).jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "REGISTRATIONFORADMISSION(I-X)" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
		return null;
	}
	
	
	


	public ActionForward Thirdadmissionformat(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
		try {

			System.out.println("INSERTING TEMPORARY REGISTRATION");

			ParentRequiresAppointmentForm parentform = (ParentRequiresAppointmentForm) form;

			ParentRequiresAppointmentDELEGATE detailsBD = new ParentRequiresAppointmentDELEGATE();
			
			String extension = "",base="",filepath=null,realpath=null;
			FormFile namefile=parentform.getInputfile();
			System.out.println("input file is "+namefile);
			if(namefile != null){
				if(namefile.getFileSize()>0 ){
					int j = namefile.getFileName().lastIndexOf('.');
					if (j >= 0) {
						base = (String) ((j == -1) ? namefile : namefile.getFileName().substring(0, j));
						extension = namefile.getFileName().substring(j+1);
					}

					if(extension.equalsIgnoreCase("jpg") )
					{
						realpath="/FIles/StudentImageTempStorage/"+base+"."+extension;
						filepath=request.getRealPath("/")+"/FIles/StudentImageTempStorage/"+base+"."+extension;
					}
					else if(extension.equalsIgnoreCase("jpeg"))
					{
						realpath="/FIles/StudentImageTempStorage/"+base+"."+extension;
						filepath=request.getRealPath("/")+"/FIles/StudentImageTempStorage/"+base+"."+extension;
					}

					byte[] btDataFile = namefile.getFileData();
					File of = new File(filepath);
					FileOutputStream osf = new FileOutputStream(of);
					osf.write(btDataFile);
					osf.flush();
				}else{
					realpath="";
				}
				parentform.setImageString(realpath);
			}
			
			String details = detailsBD.Insertthirdadmissionformat(parentform);
			
			

			List<ThirdformformatVO> list = new ArrayList<ThirdformformatVO>();
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			list = obj.getthirdadmissiondetailslist();
			request.setAttribute("ThirdAdmissionDetailsList", list);

			JSONObject object = new JSONObject();
			object.put("status", details);
			response.getWriter().print(object);
			
			
			/*if (details == "true" || details.equalsIgnoreCase("true")) {
				request.setAttribute("successmessagediv",
						"Application Submitted Successfully");
			} else {
				request.setAttribute("errorMessage",
						"Application Not Submitted Successfully,Please try again");
			}*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return mapping.findForward(MessageConstants.THIRDADMISSIONFORMATLIST);

	}
	
	public ActionForward ThirdadmissionformatList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(MessageConstants.THIRDADMISSIONFORMATLIST);

	}
	

	public ActionForward downloadthirdAppform(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequirment : downloadthirdAppform Starting");
		try{
			System.out.println(request.getParameter("formId"));
			ParentRequiresAppointmentDELEGATE obj= new ParentRequiresAppointmentDELEGATE();
			ArrayList<ThirdAddmissionApplicationVo> list = new ArrayList<ThirdAddmissionApplicationVo>();
			list= obj.downloadthirdAppform(request.getParameter("formId"));
			
			String imageUrl = list.get(0).getImageurl();
			
			String filepath = request.getRealPath("/")+imageUrl;
			
			String PropfilePath = request.getRealPath("/")+ "images\\" + ImageName.trim();
			
			Map parameters = new HashMap();
			parameters.put("photo", filepath);
			parameters.put("images", PropfilePath);
			
			String sourceFileName = request
					.getRealPath("Reports/REGISTRATIONFORADMISSIONXI.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "REGISTRATIONFORADMISSIONXI" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
		return null;
	}
	public ActionForward NewStudentadmRegistration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();
            request.setAttribute("StreamList", streamList);
            } 
		 catch (Exception e) 
		   {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		   }
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");
	
	return mapping.findForward(MessageConstants.NEW_STUDENT_ADM_REGISTRATION);
	}
	public ActionForward Issuedformlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
             ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
             request.setAttribute("issuedList", issuedList);
            } 
		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
}
	public ActionForward insertapprovedlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String mobile_number = request.getParameter("mobile_number");
			String idList = request.getParameter("enquiryIdlist");
			String reason=request.getParameter("resn");
			String othereason=request.getParameter("othereason");
					
	        String approvedList = new TemporaryRegBD().insertapprovedlist(idList,reason,othereason,mobile_number);
		    JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", approvedList);
			response.getWriter().print(jsonObject);
		    request.setAttribute("approvedList", approvedList);
	        ArrayList<Issuedmenuvo> sendmail = new TemporaryRegBD()
				.sendmailtoparents();
            ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
            request.setAttribute("issuedList", issuedList);
	     }
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return null;
}
	
	public ActionForward getApprovedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms();
               request.setAttribute("getapprvedlist", getapprvedlist);
	        } 
		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
}
	
	public ActionForward insertrejectedlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
            String idLists = request.getParameter("enquiryIdlist");
			String rejectreason=request.getParameter("resn");
			String otherrsn=request.getParameter("othereason");
			 String rejectedList = new TemporaryRegBD().insertrejectedList(idLists,rejectreason,otherrsn);
            request.setAttribute("rejectedList", rejectedList);
	         JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", rejectedList);
				response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return null;
}
	public ActionForward insertcancelledlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String idList = request.getParameter("enquiryIdlist");
			String reason=request.getParameter("resn");
			String canreason=request.getParameter("cancelreasn");
	        String cancelledlist = new TemporaryRegBD().insertcancelledlist(idList,reason,canreason);
		    JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", cancelledlist);
			response.getWriter().print(jsonObject);
		    request.setAttribute("approvedList", cancelledlist);
	        ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms();
            request.setAttribute("issuedList", issuedList);
	        } 
		 catch (Exception e) 
		   {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		  }
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return null;
   }
	
	
	public ActionForward getCancelledList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
                 ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD().getCancelledForms();
		         request.setAttribute("getcancelledlist", getcancelledlist);
	        }
		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
}
	
	public ActionForward getClassDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Starting");
		try {

			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate().getClassDetailsByTemp();

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Ending");
		return null;
	}
	
	
	public ActionForward saveThirdAdmissionApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+" Control in ParentRequiresAppointmentAction : editStreamDetailsAction  Starting");
	
		UserDetailVO userd=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		try {
			

			ParentRequiresAppointmentForm secform = (ParentRequiresAppointmentForm) form;
			secform.setFatherOccupationName(HelperClass.getOccupationName(secform.getFatherOccupation()));
			secform.setMotherOccupationName(HelperClass.getOccupationName(secform.getMotherOccupation()));
			secform.setClassName(HelperClass.className(secform.getClassname().split("D")[1]));
			if(secform.getCaste().equalsIgnoreCase("Other"))	{
				secform.setRelname(secform.getOthercaste());
			}
			else {
				secform.setCastename(HelperClass.getCasteName(secform.getCaste()));
			}
			if(secform.getReligion().equalsIgnoreCase("Other"))	{
				secform.setRelname(secform.getOtherreligion());
			}
			else {
				secform.setRelname(HelperClass.getRelegionName(secform.getReligion()));
			}
			
			
			secform.setCastecatname(HelperClass.getCasteCategoryName(secform.getCasteCategory()));
			if(secform.getCastecatname().equalsIgnoreCase("ST") || secform.getCastecatname().equalsIgnoreCase("SC") || secform.getCastecatname().equalsIgnoreCase("OBC") ){
				secform.setCastecatname("YES");
			}else{
				secform.setCastecatname("NO");
			}
			com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();
			
			String datewords = null;
			String[] split=secform.getDateofBirth().split("-");
			int day = Integer.parseInt(split[0]);
			int month = Integer.parseInt(split[1]);
			int year = Integer.parseInt(split[2]);
			datewords = (num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year));
			request.setAttribute("dateofbirthinwords",datewords);
			secform.setDobinwords(datewords);
			String cuudate = HelperClass.getCurrentSqlDate().toString();
			List<String> datesize = HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(secform.getDateofBirth()),cuudate);
			int years = (datesize.size()-1)/365;
			int months = ((datesize.size()-1)%365)/30;
			secform.setStuage(years+" Years "+months+" Months ");
			
			
			SecondAppointmentDELEGATE thirdformBD = new SecondAppointmentDELEGATE();

			FormFile imagePath = null;
			String realPath = "";
			FileOutputStream outputStream = null;
			String imagepath;
			String fileName = "";
			String StudentIDGenerator = null;


			StudentIDGenerator = IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details");

			/*student image file*/
			try {
				String extension="";
				imagePath = secform.getInputfile();

				if (imagePath != null) {

					fileName = imagePath.getFileName();
				}

				int dot = imagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName.length() < 2 && fileName != null) {

					secform.setImageString(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath);
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}
				secform.setImageString(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}

			/*student marksheet file*/
			try {
				String extension="";
				imagePath = secform.getMarksheet();

				if (imagePath != null) {

					fileName = imagePath.getFileName();
				}

				int dot = imagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName.length() < 2 && fileName != null) {

					secform.setMarkImageFile(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"marksheet"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath);
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath =request.getRealPath("/")+ "\\FIles/StudentImageTempStorage"+ "/"+"marksheet"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath =request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"marksheet"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("pdf")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"marksheet"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}
				secform.setMarkImageFile(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}
			
			
			/* TC Certificate */
			try {
				secform.setIstc("YES");
				String extension="";
				imagePath = secform.getTccertificate();

				if (imagePath != null) {

					fileName = imagePath.getFileName();
				}

				int dot = imagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName.length() < 2 && fileName != null) {
					secform.setIstc("NO");
					secform.setTccertificateString(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+"noImage.png");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"TC"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath);
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"TC"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"TC"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("pdf")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"TC"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}
				secform.setTccertificateString(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}
			
			
			/* Migration Certificate */
			try {
				secform.setIsMigration("YES");
				String extension="";
				imagePath = secform.getMigrationcertificate();

				if (imagePath != null) {

					fileName = imagePath.getFileName();
				}

				int dot = imagePath.getFileName().lastIndexOf('.');
				if (dot >= 0) {
					extension = imagePath.getFileName().substring(dot+1);
				}

				if (fileName.length() < 2 && fileName != null) {

					secform.setMigrationcertificateString(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+"noImage.png");
					secform.setIsMigration("NO");

				} else if(extension.equalsIgnoreCase("jpg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"Migration"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					System.out.println("imagepath is "+imagepath);
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					System.out.println("realPath is "+realPath);
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("jpeg")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"Migration"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("png")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"Migration"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}else if(extension.equalsIgnoreCase("pdf")){
					imagepath = request.getRealPath("/")+ "FIles/StudentImageTempStorage"+ "/"+"Migration"+secform.getStudentfirstName()+"_"+StudentIDGenerator + "." + extension;
					realPath = imagepath.substring(imagepath.indexOf("FIles/"));
					outputStream = new FileOutputStream(new File(imagepath));
					outputStream.write(imagePath.getFileData());
				}
				secform.setMigrationcertificateString(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				if (outputStream != null) {

					outputStream.close();
				}
			}
			
			secform.setLastKindergartenName(secform.getLastSchool());
			secform.setSchoolLocation(userd.getLocationid());
			secform.setStream(userd.getStreamId());

			String details = thirdformBD.InsertThirdadmissionform(secform);
			HttpSession session = request.getSession(true);

			request.setAttribute("admissionno", details);
			session.setAttribute("hstudentinformation",secform);
			response.sendRedirect("parentrequiresappointment.html?method=printApplicationForm");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRequiresAppointmentAction : editStreamDetailsAction   Ending");

		return null;
}
	public ActionForward getClassDetailSrSecondary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Starting");
		try {

			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate().getClassDetailSrSecondary();

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Ending");
		return null;
	}
	
	public ActionForward getAdmissionRegistrationDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Starting");
		try {
			ParentRequiresAppointmentVO appointmentVo = new ParentRequiresAppointmentVO();
			String enquiryid=request.getParameter("enquiryid");
			System.out.println("enquiryid is "+ enquiryid);
			appointmentVo.setEnquiryId(enquiryid);
			List<ParentRequiresAppointmentVO> admissionList = new ParentRequiresAppointmentDELEGATE().getAdmissionRegDetails(appointmentVo);

			JSONArray array = new JSONArray();
			array.put(admissionList);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, admissionList);
			response.getWriter().print(jsonObject);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Ending");
		return null;
	}
	
	public ActionForward getValidateAdmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Starting");
		try {
			ParentRequiresAppointmentVO appointmentVo = new ParentRequiresAppointmentVO();
			String enquiryid=request.getParameter("enquiryid");
			System.out.println("enquiryid is "+ enquiryid);
			appointmentVo.setEnquiryId(enquiryid);
			String validatenostatus = new ParentRequiresAppointmentDELEGATE().getValidateAdmissionNo(appointmentVo);

			JSONObject object = new JSONObject();
			object.put("validatenostatus", validatenostatus);
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Ending");
		return null;
	}
	
	public ActionForward getClassDetailMontessori(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Starting");
		try {

			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate().getClassDetailMontessori();

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Ending");
		return null;
	}
	
	public ActionForward Successmessagedisplay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		return mapping.findForward(MessageConstants.SUCCESSPAGEDISPLAY);
	
}
}
	
	
	

