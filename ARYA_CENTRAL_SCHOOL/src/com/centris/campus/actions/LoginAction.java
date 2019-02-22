package com.centris.campus.actions;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.admin.LstmsParents;
import com.centris.campus.admin.LstmsPrinciple;
import com.centris.campus.admin.LstmsTeachers;
import com.centris.campus.admin.lstmsUser;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.LoginBD;
import com.centris.campus.delegate.ParentRequiresAppointmentDELEGATE;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.LoginForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;
import com.itextpdf.text.log.SysoLogger;

public class LoginAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	public ActionForward checkValidateuser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkValidateuser  Starting");

		try {

			String userName = request.getParameter("UserName");
			String Password = request.getParameter("password");

			System.out.println("user name:: " + userName + "  Password ::: "
					+ Password);

			LoginBD loginBD = new LoginBD();
			LoginVo loginVo = loginBD.validateUserBD(userName, Password);

			JSONObject object = new JSONObject();
			object.put("Status", loginVo.getUsercode());

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkValidateuser Ending");

		return null;

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		String userdefined = null;

		try {
			HttpSession session = request.getSession(true);
			LoginForm lform = (LoginForm) form;

			String userName = lform.getUsername();
			String Password = lform.getPassword();

			LoginBD loginBD = new LoginBD();

			LoginVo loginVo = loginBD.validateUserBD(userName,Password);
			
			System.out.println("IP:::"+lform.getInternal_ip());
			session.setAttribute("IP", lform.getInternal_ip());
			System.out.println(loginVo.getUsercode());
			String usercode =null;
			
			if(loginVo.getUsercode()=="" || loginVo.getUsercode()==null)
			{
				System.out.println("Inside If Loop");
				usercode = HelperClass.getCurrentUserID(request);
				loginVo.setUsertype(HelperClass.getCurrentUser(request));
				loginVo.setUsercode(usercode);
				request.getSession(false).setAttribute("Priveliges", HelperClass.getPrivigeValue(request));
			}
			else
			{
				System.out.println("Inside Else Loop");
				usercode = loginVo.getUsercode();
				request.getSession(false).setAttribute("Priveliges", loginVo.getIsAdministrator());
			}
			
			
			if (usercode.contains("TEA")) {

				List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();

				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_HOMENAME);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, null);

				session = request.getSession(false);
				String selected = request.getParameter("accYear");
				String schoolCode=request.getParameter("school");

				accYear = HelperClass.getAllAcademicYear();
				session.setAttribute(MessageConstants.ACADAMICYEAR, accYear);

				if (selected == null || selected.equalsIgnoreCase("") || selected.equalsIgnoreCase("null")) {
				
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,	HelperClass.getCurrentYearID());
					
				} else {
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,
							selected.trim());
				}
				String accYears=(String) request.getSession(false).getAttribute("current_academicYear");
				String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYears));
				String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYears));
				
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);

				UserDetailVO usrVo = loginBD.loadUserBD(loginVo);

				usrVo.setAcademicyear(selected);
				session.setAttribute("TEACHERNAMEDETAILS", usrVo.getFirstName());
				session.setAttribute(MessageConstants.USER_DETAILS, usrVo);
				session.setAttribute("employeeType", usrVo.getEmployeeType());
				System.out.println("locationId "+usrVo.getLocationid());
				if (schoolCode == null || schoolCode.equalsIgnoreCase("") || schoolCode.equalsIgnoreCase("null")) {
					if(loginVo.getIsAdministrator().equalsIgnoreCase("Y")){
						session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,"all");
					}
					else{
					session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,usrVo.getLocationid());
					}
				} else {
					if(schoolCode=="all"){
						session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,"%%");
					}
					else{
					session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,
							schoolCode.trim());
						}
					}
				
				session.setAttribute("user", usrVo.getUserNametype());
				userdefined = "TEACHER_AND_PARENT";

			}
			else if(usercode.contains("PAR")){
				
				List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_HOMENAME);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, null);

				session = request.getSession(false);
				String selected = request.getParameter("accYear");
				if (selected == null || selected.equalsIgnoreCase("") || selected.equalsIgnoreCase("null")) {
					
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,	HelperClass.getCurrentYearID());
					
				} else {

					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,
							selected.trim());
				}
				accYear = HelperClass.getAllAcademicYear();
				session.setAttribute(MessageConstants.ACADAMICYEAR,accYear);

				if (selected == null) {

					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,
							HelperClass.getCurrentYearID());

				} else {

					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,
							selected.trim());
				}
				
				String accYears=(String) request.getSession(false).getAttribute("current_academicYear");
				String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYears));
				String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYears));
				
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);

				UserDetailVO usrVo = loginBD.loadUserBD(loginVo);
				usrVo.setAcademicyear(selected);
				System.out.println(usrVo.getFirstName());
				session.setAttribute("TEACHERNAMEDETAILS", usrVo.getFirstName());
				session.setAttribute(MessageConstants.USER_DETAILS, usrVo);
				session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,"all");
				
				System.out.println(usrVo.getPermissionmap().size());

				session.setAttribute("user", usrVo.getUserNametype());
				
				System.out.println("usrVo.getUserNametype() "+usrVo.getUserNametype());
				userdefined = "PARENT";
			}
			else if(usercode.contains("PNQ")) {
				
				ParentRequiresAppointmentVO appointmentVo = new ParentRequiresAppointmentVO();
				String enquiryid=loginVo.getUsercode();
				System.out.println("enquiryid is "+ enquiryid);
				
				UserDetailVO usrVo = loginBD.loadUserBD(loginVo);
				
				session.setAttribute("user", "registration");
				appointmentVo.setEnquiryId(enquiryid);
				
				
				session.setAttribute(MessageConstants.USER_DETAILS, usrVo);
				
				List<ParentRequiresAppointmentVO> admissionList = new ParentRequiresAppointmentDELEGATE().getAdmissionRegDetails(appointmentVo);
				session.setAttribute("user", "ENQUIRY");
				session.setAttribute("enquiryid", enquiryid);
				session.setAttribute("adaccyear", admissionList.get(0).getAccyearid());
				if(usrVo.getStream().equalsIgnoreCase("NURSERY")) {
					userdefined = "ENQUIRY";
				}
				else if(usrVo.getStream().equalsIgnoreCase("PRIMARY") || usrVo.getStream().equalsIgnoreCase("SECONDARY")) {
					userdefined = "ENQUIRY1_10";
				}
				else {
					userdefined = "ENQUIRY11_12";
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		if (userdefined == "TEACHER_AND_PARENT"
				|| userdefined.equalsIgnoreCase("TEACHER_AND_PARENT")) {
			return mapping.findForward(MessageConstants.adminLogin);
		}
		
		else if(userdefined=="PARENT" || userdefined.equalsIgnoreCase("PARENT")){
			return mapping.findForward(MessageConstants.parentLogin);
		}
		else if(userdefined=="ENQUIRY" || userdefined.equalsIgnoreCase("ENQUIRY")){
			
			
			return mapping.findForward(MessageConstants.enquirylogin);
		}
		else if(userdefined=="ENQUIRY1_10" || userdefined.equalsIgnoreCase("ENQUIRY1_10")){
			
			
			return mapping.findForward(MessageConstants.SECONDADMISSIONFORMAT);
		}
		else {
			return mapping.findForward(MessageConstants.THIRDADMISSIONFORMAT);
		}

	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : logout  Starting");

		try {
			String user = (String) request.getParameter("usernamehidden");

			HttpSession session = request.getSession(false);
			session.invalidate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : logout Ending");

		return mapping.findForward("Home");
	}

	public ActionForward changePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : changePassword  Starting");

		try {

			String newPassword = (String) request.getParameter("newPassword");
			String currentuser = (String) request.getSession(false)
					.getAttribute("user");
			String currentuserId = HelperClass.getCurrentUserID(request);

			int count = new LoginBD().changePassword(currentuser, newPassword,
					currentuserId);

			JSONObject object = new JSONObject();

			if (count > 0) {

				object.put("status", "true");

			} else {

				object.put("status", "false");
			}

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : changePassword Ending");

		return null;
	}

	public ActionForward checkCurrentPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkCurrentPassword  Starting");

		try {

			String currentuser = (String) request.getSession(false)
					.getAttribute("user");
			String currentPassword = (String) request
					.getParameter("oldPassword");
			String currentuserId = HelperClass.getCurrentUserID(request);

			int count = new LoginBD().checkCurrentPassword(currentuser,
					currentPassword, currentuserId);

			JSONObject object = new JSONObject();
			if (count > 0) {
				object.put("status", "true");
			} else {
				object.put("status", "false");
			}
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkCurrentPassword Ending");

		return null;
	}

	public ActionForward userValidCase(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginCheckAction:checkLoginUserDetails: Starting");
		
		try{
		
		String username=(String)request.getParameter("uname");
		String password=(String)request.getParameter("pword");
		
		
		System.out.println(username+"   "+password);
		LoginBD loginservice = new LoginBD();
		if (loginservice.userValidCase(username.trim(),
				password.trim()).equals(MessageConstants.TRUE)) {
			
			JSONObject object=new JSONObject();
			object.put("status", true);
			
			response.getWriter().print(object);
		}else{
			
			JSONObject object=new JSONObject();
			object.put("status", false);
			
			response.getWriter().print(object);
		}
	
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginCheckAction:checkLoginUserDetails: Ending");
		
		return null;
	}
	public ActionForward Dairy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginCheckAction:checkLoginUserDetails: Starting");
		UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=user.getUserId();
		String userType=user.getUserNametype();
		try{
		
		String content=request.getParameter("content");
		String rowid=request.getParameter("rowid");
		String date=request.getParameter("commentdate");
		
		System.out.println(date);
		LoginBD loginservice = new LoginBD();
		String status=loginservice.saveDairy(content,rowid,date,userId,userType);
	
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginCheckAction:checkLoginUserDetails: Ending");
		
		return null;
	}
	


}