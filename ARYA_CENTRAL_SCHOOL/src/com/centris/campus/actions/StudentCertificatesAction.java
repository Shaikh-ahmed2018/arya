package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import org.apache.log4j.Level;

import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentCertificatesBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentCertificatesVo;
import com.centris.campus.vo.StudentRegistrationVo;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StudentCertificatesAction extends DispatchAction{

	private static final Logger logger = Logger
			.getLogger(StudentCertificatesAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward studentCertificates(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : studentCertificates Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			
			   String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			   request.setAttribute("academic_year", academic_year);
			   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    	   }
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation);
	    		   request.setAttribute("currentlocation", currentlocation);
	    	   }
	    	   request.setAttribute("locationId",schoolLocation);
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails();
				request.setAttribute("locationDetailsList", list);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
						.getAccYears();
				request.setAttribute("accYearList", accYearList);
				ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation);
				request.setAttribute("classList", classList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentCertificates Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_CERTIFICATES);
	}
	
	public ActionForward ageCertificate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : AgeCertificate Starting");
		
		try {
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			
			String locationid = request.getParameter("locationid");
			if(locationid == null){
				locationid = "%%";
			}
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuid");
			
			request.setAttribute("stuid",stuid);
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			pojo.setLocid(locationid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			
			StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo);
			request.setAttribute("stuList",list);
			request.setAttribute("locationid",list.getLocid());
			request.setAttribute("accyearid",list.getAccyear());
			request.setAttribute("sectionid",list.getSectionid());
			request.setAttribute("classid",list.getClassid());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AgeCertificate Ending");
		
		return mapping.findForward(MessageConstants.AGE_CERTIFICATE);
	}
public ActionForward AgeCertificatePDF(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : findStudent Starting");
			try {	
				
				/*String locationid = request.getParameter("locationid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");*/
				/*String stuid = request.getParameter("stuname");*/
				ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
				/*System.out.println(stuid);*/
				String sourceFileName = request.getRealPath("Reports/age&bonafiedCertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						list);
				String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\"+ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);
/*				parameters.put("stuname",stuid);*/
				
				/*String result = "true";
	        	JSONObject jsonobj = new JSONObject();
				jsonobj.put("status",result);
				response.getWriter().print(jsonobj);*/
				
				JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		        JasperViewer.viewReport(jasperPrint, false);
		           
		        
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
		return null;
	}
	public ActionForward getStudentsDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : AgeCertificate Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String loc = request.getParameter("location");
			String acyear = request.getParameter("acyear");
			ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().getStudentsDetails(loc,acyear);
			
			
			JSONObject obj=new JSONObject();
			obj.put("stuList",stuList);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AgeCertificate Ending");
		return null;
	}
	public ActionForward getStudentsDetailsByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : AgeCertificate Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			String loc = request.getParameter("location");
			String acyear = request.getParameter("acyear");
			String classname = request.getParameter("classid");
			System.out.println(classname);
			
			list = new StudentRegistrationDelegate().getStudentDetailsLByFilter(loc,acyear,classname);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getClassWiseList", list);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AgeCertificate Ending");
		return null;
	}
	
	public ActionForward getClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : AgeCertificate Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String loc = request.getParameter("location");
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(loc);
			JSONObject obj=new JSONObject();
			obj.put("classList",classList);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AgeCertificate Ending");
		return null;
	}
	public ActionForward getStudentListBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : gradeList Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus();
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;
	}
	
	public ActionForward findStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : findStudent Starting");
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudent Ending");
		
		return mapping.findForward(MessageConstants.FIND_STUDENT);
	}
	public ActionForward saveAgeCertificateData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : saveAgeCertificateData Starting");
		try {
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuname = request.getParameter("stuname");
			String admissionno = request.getParameter("admissionno");
			String fathername = request.getParameter("fathername");
			String dob = request.getParameter("dob");
			String purpose = request.getParameter("purpose");
			String otherinfo = request.getParameter("otherinfo");
			String studentstatus = request.getParameter("studentstatus");
			String motherName = request.getParameter("motherName");
			String dobwords = request.getParameter("dobwords");
			
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			
			pojo.setAccyear(accyear);
			pojo.setLocid(locid);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setAdmissionno(admissionno);
			pojo.setStuname(stuname);
			pojo.setDob(dob);
			pojo.setDobwords(dobwords);
			pojo.setMothername(motherName);
			pojo.setOtherinfo(otherinfo);
			pojo.setPurpose(purpose);
			pojo.setFatherName(fathername);
			pojo.setStudentstatus(studentstatus);
			
			String result = new StudentCertificatesBD().saveAgeCertificateData(pojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status",result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudent Ending");
		
		return null;
	}
	
	public ActionForward getStudentSearchByConfReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByConfReport Starting");
		
		List<StudentRegistrationVo> list = null;
		
		try {
			
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			
			String searchTerm = request.getParameter("searchname".trim());

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getStudentSearchByConfReport(searchTerm);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getConfSearchByFilter(searchTerm,locationid,accyear);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getConfSearchByClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}
			
			request.setAttribute("SearchList", list);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByConfReport Ending");

		return null;
	}

	public ActionForward getissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
						
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : findStudent Starting");
			try {	
				String locid = request.getParameter("locid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuname");
				
				StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
				pojo.setLocid(locid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
				ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getissueddetails(pojo);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("stuList",list);
				response.getWriter().print(jsonobj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
		return null;
	}
	
	public ActionForward bonafideCertificate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : bonafideCertificate Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction  : AgeCertificate Starting");
				
				String locationid = request.getParameter("locationid");
				if(locationid == null){
					locationid = "%%";
				}
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuid");
				
				request.setAttribute("locationid",locationid);
				request.setAttribute("accyearid",accyear);
				request.setAttribute("sectionid",sectionid);
				request.setAttribute("classid",classid);
				request.setAttribute("stuid",stuid);
				StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
				pojo.setLocid(locationid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
				
				StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo);
				request.setAttribute("stuList",list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : bonafideCertificate Ending");
		
		return mapping.findForward(MessageConstants.BONAFIDE_CERTIFICATE);
	}
	public ActionForward saveBonafiedCertificateData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction  : saveAgeCertificateData Starting");
		try {
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuname = request.getParameter("stuname");
			String admissionno = request.getParameter("admissionno");
			String fathername = request.getParameter("fathername");
			String dob = request.getParameter("dob");
			String purpose = request.getParameter("purpose");
			String otherinfo = request.getParameter("otherinfo");
			String studentstatus = request.getParameter("studentstatus");
			String motherName = request.getParameter("motherName");
			String dobwords = request.getParameter("dobwords");
			
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			
			pojo.setAccyear(accyear);
			pojo.setLocid(locid);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setAdmissionno(admissionno);
			pojo.setStuname(stuname);
			pojo.setDob(dob);
			pojo.setDobwords(dobwords);
			pojo.setMothername(motherName);
			pojo.setOtherinfo(otherinfo);
			pojo.setPurpose(purpose);
			pojo.setFatherName(fathername);
			pojo.setStudentstatus(studentstatus);
			
			String result = new StudentCertificatesBD().saveBonafiedCertificateData(pojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status",result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudent Ending");
		
		return null;
	}
	public ActionForward getbonafiedissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : findStudent Starting");
			try {	
				String locid = request.getParameter("locid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuname");
				
				StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
				pojo.setLocid(locid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
				ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getbonafiedissueddetails(pojo);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("stuList",list);
				response.getWriter().print(jsonobj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
		return null;
	}
public ActionForward BonafiedCertificatePDF(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : findStudent Starting");
			try {	
				
				String locationid = request.getParameter("locationid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuid");
				
				System.out.println(locationid);
				System.out.println(stuid);
				System.out.println(accyear);
				System.out.println(classid);
				System.out.println(sectionid);
				
				String sourceFileName = request
						.getRealPath("Reports/bonafiedCertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				
				ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
				
				String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\"+ImageName.trim();
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("Image", PropfilePath);
				
		        JasperPrint jp = JasperFillManager.fillReport(jasperreport,null,beanColDataSource);
		        JasperViewer.viewReport(jp, false);
		        
		        	/*JSONObject jsonobj = new JSONObject();
					jsonobj.put("status","true");
					response.getWriter().print(jsonobj);*/
		        
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
		return null;
	}

public ActionForward conductCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : conductCertificate Starting");
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		String locationid = request.getParameter("locationid");
		if(locationid == null){
			locationid = "%%";
		}
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);
		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo);
		
		
		request.setAttribute("stuList",list);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : conductCertificate Ending");
	
	return mapping.findForward(MessageConstants.CONDUCT_CERTIFICATE);
}
public ActionForward course_conductCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : course_conductCertificate Starting");
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		String locationid = request.getParameter("locationid");
		if(locationid == null){
			locationid = "%%";
		}
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);
		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo);
		request.setAttribute("stuList",list);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : course_conductCertificate Ending");
	
	return mapping.findForward(MessageConstants.COURSE_CONDUCT_CERTIFICATE);
}

public ActionForward studentVisaCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : studentVisaCertificate Starting");
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		String locationid = request.getParameter("locationid");
		if(locationid == null){
			locationid = "%%";
		}
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);
		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo);
		request.setAttribute("stuList",list);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : studentVisaCertificate Ending");
	
	return mapping.findForward(MessageConstants.STUDENT_VISA_CERTIFICATE);
}
public ActionForward saveCounductedCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : saveAgeCertificateData Starting");
	try {
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String conductNo = request.getParameter("conductNo");
		String purpose = request.getParameter("purpose");
		String otherinfo = request.getParameter("otherinfo");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String conduct = request.getParameter("conduct");
		
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setConductno(conductNo);
		pojo.setConduct(conduct);
		pojo.setMothername(motherName);
		pojo.setOtherinfo(otherinfo);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		
		String result = new StudentCertificatesBD().saveCounductedCertificateData(pojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : findStudent Ending");
	
	return null;
}
public ActionForward getconductissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : findStudent Starting");
		try {	
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getconductissueddetails(pojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
	return null;
}
public ActionForward saveCourseCounductedCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : saveCourseCounductedCertificateData Starting");
	try {
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String conductNo = request.getParameter("conductNo");
		String purpose = request.getParameter("purpose");
		String otherinfo = request.getParameter("otherinfo");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String conduct = request.getParameter("conduct");
		
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setConductno(conductNo);
		pojo.setConduct(conduct);
		pojo.setMothername(motherName);
		pojo.setOtherinfo(otherinfo);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		
		String result = new StudentCertificatesBD().saveCourseCounductedCertificateData(pojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : findStudent Ending");
	
	return null;
}
public ActionForward getcourseconductissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : getcourseconductissueddetails Starting");
		try {	
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getcourseconductissueddetails(pojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : findStudent Ending");
	return null;
}
public ActionForward saveStudentVisaCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : saveCourseCounductedCertificateData Starting");
	try {
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String purpose = request.getParameter("purpose");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String pasportno = request.getParameter("passportno");
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setMothername(motherName);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		pojo.setPassportno(pasportno);
		String result = new StudentCertificatesBD().saveStudentVisaCertificateData(pojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : findStudent Ending");
	
	return null;
}
public ActionForward getstudentvisaissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction  : getstudentvisaissueddetails Starting");
		try {	
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getstudentvisaissueddetails(pojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : getstudentvisaissueddetails Ending");
	return null;
}
public ActionForward displayageissueddetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : AgeCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
		String selection = request.getParameter("selection");
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displayageissueddetails(stuid,agecetiid,selection);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : AgeCertificate Ending");
	return null;
}
public ActionForward displayconductdetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : AgeCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
		String selection = request.getParameter("selection");
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displayconductdetails(stuid,agecetiid,selection);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : AgeCertificate Ending");
	return null;
}
public ActionForward displaystudentvisadetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : AgeCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
	
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displaystudentvisadetails(stuid,agecetiid);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : AgeCertificate Ending");
	return null;
}

public ActionForward printCCCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction  : AgeCertificate Starting");
	
	try {
		
		String locationId=request.getParameter("locationId");
		String accyear=request.getParameter("accyear");
		String studentid=request.getParameter("studentid");
		String admid=request.getParameter("admid");
		String classid=request.getParameter("classid");
		
		String sourceFileName =null;
		String academicYear=HelperClass.getAcademicYearFace(accyear.split(",")[0]);
		String lastacademicYear=Integer.toString(Integer.parseInt(HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[0]));
		ServletOutputStream outstream =null;
		byte[] bytes=null;
		
		
		sourceFileName = request.getRealPath("Reports/CourceAndConductCertificateofXII.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		
		List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
		Map<String, Object> parameters = new HashMap<String, Object>();
		String courseConductTemplate=null;
		List<StudentRegistrationVo> CpurseConductdetails=new ArrayList<StudentRegistrationVo>();
		
		for(int i=0;i<classid.split(",").length;i++) {
			StudentRegistrationVo coursevo=new StudentRegistrationVo();
			coursevo.setStudentFullName(TCdetails.get(i).getStudentFullName());
			coursevo.setFatherName(TCdetails.get(i).getFatherName());
			coursevo.setDateofBirth(TCdetails.get(i).getDateofBirth());
			coursevo.setDateofBirthInWords(TCdetails.get(i).getDateofBirthInWords());
			coursevo.setAccyear(academicYear);
			coursevo.setLaccyear(lastacademicYear);
			coursevo.setConduct("Good");
		if(!classid.split(",")[i].equalsIgnoreCase("CCD15")){
			courseConductTemplate= request.getRealPath("/")+"images/ReportTemplate/courseConduct1.png";
		}else{
			courseConductTemplate= request.getRealPath("/")+"images/ReportTemplate/courseConduct.png";
		}
		coursevo.setTemplate(courseConductTemplate);
		CpurseConductdetails.add(coursevo);
		}
		JRBeanCollectionDataSource beanColDataSource = null;
		beanColDataSource = new JRBeanCollectionDataSource(CpurseConductdetails);
		bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\"Course_and_conduct_certificate_"+academicYear+".pdf\"");
		outstream = response.getOutputStream();
		outstream.write(bytes, 0, bytes.length);
		

	
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : AgeCertificate Ending");
	return null;
}
}
