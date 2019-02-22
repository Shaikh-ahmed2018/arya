package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.NewRegistrationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.ReportMenuVo;

public class RegistrationOfUser extends DispatchAction

{
	private static final Logger logger = Logger
			.getLogger(RegistrationOfUser.class);

	public ActionForward NewRegistrationEntryPage(ActionMapping mapping,
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

	System.out.println("Stream:" +streamList);

	request.setAttribute("StreamList", streamList);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.newregistration);

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

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String streamId = request.getParameter("streamId");

			System.out.println("streamId"+streamId);
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD()
					.getClassesByStream(streamId);
			System.out.println("classesList"+classesList.size());
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

	public ActionForward InsertNewRegistrationUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {
			
            System.out.println("Insertion Action:");
			String parentfirstName = request.getParameter("parentfirstName");
			String parent_LastName = request.getParameter("parent_LastName");
			String mobile_number = request.getParameter("mobile_number");
			String parentEmailId = request.getParameter("parentEmailId");
			String address = request.getParameter("address");
			String is_relat = request.getParameter("stu_par_relation");
			String parents_name = request.getParameter("parents_name");
			String schoolId=request.getParameter("schoolId");
			String stream_name = request.getParameter("stream");
			String class_name = request.getParameter("classid");
			String accyearId=request.getParameter("accyearId");
			NewUserRegistrationForm registrationform = new NewUserRegistrationForm();

			registrationform.setParentfirstName(parentfirstName);
			registrationform.setParent_LastName(parent_LastName);
			registrationform.setMobile_number(mobile_number);
			registrationform.setParentEmailId(parentEmailId); 
			registrationform.setAddress(address);
			registrationform.setStu_parrelation(is_relat);
			registrationform.setParents_name(parents_name);
			registrationform.setStreamname(stream_name);
			registrationform.setClassid(class_name);
			registrationform.setLocation(schoolId);
			registrationform.setAccyearId(accyearId);
			String registration = new NewRegistrationBD()    
					.InsertNewRegistrationUser(registrationform);
			
			System.out.println("status:" +registration);
			JSONObject object = new JSONObject();
			object.put("status", registration);
			response.getWriter().print(object);

			System.out.println("registration From DAOimplL" +registration);

		
			
			
			//For Listinng
			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
			.getStream();

	   System.out.println("Stream:" +streamList);

	   request.setAttribute("StreamList", streamList);
	   
		ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD()
		.getissuedforms();


           request.setAttribute("issuedList", issuedList);
           ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD()
			.getApprovedForms();

	         request.setAttribute("getapprvedlist", getapprvedlist);
	         ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD()
				.getRejectedlist();

		
		         request.setAttribute("rejectlist", rejectlist);

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

	public ActionForward saveparentsubmittingdetailstoschool(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		try {

			System.out.println("Submitting The Records");

			String studentfirstName = request.getParameter("studentfirstName");
			String dateofBirthId = request.getParameter("dateofBirthId");
			String classname = request.getParameter("classname");
			String group_name = request.getParameter("group_name");
			String accyear = request.getParameter("accyear");
			String parentId = request.getParameter("parentId");
			String mobile_number = request.getParameter("mobile_number");
			String emailId = request.getParameter("emailId");
			String address = request.getParameter("address");
			String relationship = request.getParameter("relationship");
			String alternateMobileNo = request
					.getParameter("alternateMobileNo");
			String alternateemailId = request.getParameter("alternateemailId");
			String advertisement = request.getParameter("advertisement");
			String paper = request.getParameter("paper");
			String websites = request.getParameter("websites");
			String channels = request.getParameter("channels");
			String others = request.getParameter("others");
			String parents = request.getParameter("parents");
			
			String school_name = request.getParameter("school_name");
			String percentage = request.getParameter("percentage");
			String previous_classname = request.getParameter("previous_classname");
			String group_name1 = request.getParameter("group_name1");

			ParentRequiresAppointmentForm registrationform = new ParentRequiresAppointmentForm();
			
			
			
			registrationform.setStudentfirstName(studentfirstName);
			registrationform.setDateofBirthId(dateofBirthId);
			registrationform.setClassname(classname);
			registrationform.setGroup_name(group_name);
			registrationform.setAccyear(accyear);
			registrationform.setParentId(parentId);
			registrationform.setMobile_number(mobile_number);
			registrationform.setEmailId(emailId);
			registrationform.setAddress(address);
			registrationform.setRelationship(relationship);
			registrationform.setAlternateMobileNo(alternateMobileNo);
			registrationform.setAlternateemailId(alternateemailId);
			registrationform.setAdvertisement(advertisement);
			registrationform.setPaper(paper);
			registrationform.setWebsites(websites);
			registrationform.setChannels(channels);
			registrationform.setOthers(others);
			registrationform.setParents(parents);
			registrationform.setSchool_name(school_name);
			registrationform.setPercentage(percentage);
			registrationform.setPrevious_classname(previous_classname);
			registrationform.setGroup_name1(group_name1);
			

			String registration = new NewRegistrationBD()
					.saveparentsubmittingdetailstoschool(registrationform);

			System.out.println(registration);

			if (registration == "true") {
				request.setAttribute(
						"successMessage",
						"Thanks for your Admission Enquiry on our school portal and we will get back soon"+
						"please quote your admission enquiry number in all your further correspondance");
			} else {
				request.setAttribute("errorMessage",
						"Submitting Application Failed ,Please Try Again ");
			}

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
				+ " Control in LoginAction : login Ending");

		return mapping.findForward(MessageConstants.enquirylogin);

	}
	
	
}
