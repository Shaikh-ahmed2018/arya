package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.forms.UniformSmsForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public class SmsAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(SmsAction.class);
	
	public ActionForward getSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getSection Starting");
		
		try {
			
			String classid = request.getParameter("classid");
			
			
			ArrayList<SectionPojo> seclist = new SmsDeligate().getsectionlist(classid);
			
			JSONObject object=new JSONObject();
			 
			 object.put("seclist", seclist);
			 
			 response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  :getSection Ending");

		return null;

	}
	
	
	public ActionForward getSubject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getSubject Starting");
	
	try {
		
		String classid = request.getParameter("classid");
		
		
		
		ArrayList<SectionPojo> sublist = new SmsDeligate().getsubjectlist(classid);
		
		JSONObject object=new JSONObject();
		 
		 object.put("sublist", sublist);
		 
		 response.getWriter().print(object);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : getSubject Ending");

		return null;

	}
	
	
	
	/*public ActionForward insertsms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : insertsms Starting");
		
		try {
			
			String createuser = HelperClass.getCurrentUserID(request);
			
			String dateId = request.getParameter("dateId");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String subjectid = request.getParameter("subjectid");
			String description = request.getParameter("description");
			
			SmsVo vo = new SmsVo();
			
			vo.setDate(dateId);
			vo.setClassid(classid);
			vo.setSectionid(sectionid);
			vo.setSubjectid(subjectid);
			vo.setDescription(description);
			vo.setCreateuser(createuser);
			
			String result = new SmsDeligate().inserthomework(vo);
			
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", result);
				
				response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : insertsms Ending");

		return null;

	}*/
//single sms edited by anu
	
	public ActionForward insertsms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : insertsms Starting");
		
		try {
			
			String createuser = HelperClass.getCurrentUserID(request);
			
			//String dateId = request.getParameter("dateId");
			//String classid = request.getParameter("classid");
			//String sectionid = request.getParameter("sectionid");
			String phone = request.getParameter("mobileno");
			String message = request.getParameter("message");
			
			SmsVo vo = new SmsVo();
			
			//vo.setDate(dateId);
			//vo.setClassid(classid);
			//vo.setSectionid(sectionid);
			//vo.setSubjectid(subjectid);
			//vo.setDescription(description);
			vo.setCreateuser(createuser);
			vo.setMobileno(phone);
			vo.setMessage(message);
			
			String result = new SmsDeligate().inserthomework(vo);
			
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", result);
				
				response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : insertsms Ending");

		return null;

	}
	
	public ActionForward getSectionMeeting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getSectionMeeting Starting");
		
		try {
			SectionPojo secpojo = new SectionPojo();
			
			String classid=request.getParameter("classid");
			String[] categoryval=classid.split(",");
			
			ArrayList<SectionPojo> seclist=new CommunicationSettingsBD().getSectionListDetails(categoryval);

			JSONObject object=new JSONObject();
			 
			 object.put("seclist", seclist);
			 
			 response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : getSectionMeeting Ending");

		return null;
	}
	
	
	public ActionForward getStudentMeeting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getStudentMeeting Starting");
		
		try {
			
			String sectionid=request.getParameter("sectionid");
			
			String[] categoryval=sectionid.split(",");
			
			ArrayList<StudentInfoVO> studentlist=new SmsDeligate().getStudentListDetails(categoryval);
			
			JSONObject object=new JSONObject();
			 
			 object.put("studentlist", studentlist);
			 
			 response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : getStudentMeeting Ending");

		return null;
	}


	public ActionForward getSubjectMeeting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getSubjectMeeting Starting");
		
		try {
			
			
			SubjectPojo subpojo = new SubjectPojo();
			
			String classid=request.getParameter("classid");
			String sectionid=request.getParameter("sectionid");
			
			String[] categoryval=classid.split(",");
			
			ArrayList<SubjectPojo> subjectlist =new SmsDeligate().getSubjectListDetails(categoryval);

			JSONObject object=new JSONObject();
			 
			 object.put("subjectlist", subjectlist);
			 
			 response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : getSubjectMeeting Ending");

		return null;
	}
	
	
	public ActionForward insertmeeting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : insertmeeting Starting");
	
	
		try {
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			
			
			String createUser = HelperClass.getCurrentUser(request);
			String accyear = HelperClass.getCurrentYearID();
			
			String dateId = request.getParameter("dateId");
			String classid = request.getParameter("classid");
			String starttime = request.getParameter("starttime");
			String subjectid = request.getParameter("subjectid");
			String studentid [] = request.getParameterValues("studentid[]");
			String titleid = request.getParameter("titleid");
			
			
			
			String sectionid = request.getParameter("sectionid");
			String endtime = request.getParameter("endtime");
			
			
			
		/*	String venueid = request.getParameter("venueid");*/
			
			
			
			String description = request.getParameter("description");
			
			
			
			if(subjectid == "" || subjectid == null)
				
			{
				
				meetingvo.setSubjectid("-");
				
			}
			
			else
				
			{
				
				meetingvo.setSubjectid(subjectid);
				
			}
			
			meetingvo.setMeetingDate(dateId);
			meetingvo.setClassid(classid);
			meetingvo.setStartTime(starttime);
			
			meetingvo.setStudentname(studentid);
			meetingvo.setTitle(titleid);
			meetingvo.setSectionid(sectionid);
			meetingvo.setEndTime(endtime);
			/*meetingvo.setVenueid(venueid);*/
			meetingvo.setDescription(description);
			meetingvo.setCreatedby(createUser);
			
			meetingvo.setAccyearid(createUser);
			
			
			String result = new SmsDeligate().saveMeetingDetails(meetingvo);
			
			JSONObject jsonobj = new JSONObject();
			
			jsonobj.put("jsonResponse", result);
			
			response.getWriter().print(jsonobj);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SmsAction  : insertmeeting Ending");

		return null;
	}
	
	
	public ActionForward addlatecomers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : addlatecomers Starting");


			try {
				
				LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
				
				
				String createUser = HelperClass.getCurrentUser(request);
				String accyear = HelperClass.getCurrentYearID();
				
				String dateId = request.getParameter("dateId");
				String classid = request.getParameter("classid");
				String studentid [] = request.getParameterValues("studentid[]");
				/*String titleid = request.getParameter("titleid");*/
				String sectionid = request.getParameter("sectionid");
				String description = request.getParameter("description");
				
				meetingvo.setMeetingDate(dateId);
				meetingvo.setClassid(classid);
				meetingvo.setStudentname(studentid);
				/*meetingvo.setTitle(titleid);*/
				meetingvo.setSectionid(sectionid);
				meetingvo.setDescription(description);
				meetingvo.setCreatedby(createUser);
				
				meetingvo.setAccyearid(createUser);
				
				
				String result = new SmsDeligate().addlatecomers(meetingvo);
				
				JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", result);
				
				response.getWriter().print(jsonobj);
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				e.printStackTrace();
			}
			
		
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  SmsAction  : addlatecomers Ending");

			return null;
		}
	
	
	public ActionForward SendUniform(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : SendUniform Starting");
	
		try {
			
			ClassPojo pojo = new ClassPojo();
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo);
			
			
			UniformSmsForm uform = (UniformSmsForm)form;
			
			UniformSmsPojo upojo = new UniformSmsPojo();
			
			String[] classlist = uform.getClassname().split(",");
			String[] sectionlist = uform.getSection().split(","); 
			String[] studentlist = uform.getStudent().split(","); 
			
			upojo.setClassid(classlist);
			upojo.setSectionid(sectionlist);
			upojo.setStudentid(studentlist);
			
			upojo.setDate(HelperClass.convertUIToDatabase(uform.getDate()));
			upojo.setSmstext(uform.getSmstext());
			upojo.setCreatedby(HelperClass.getCurrentUserID(request));
			upojo.setCreatedate(HelperClass.getCurrentTimestamp());
			
			
			String status=new SmsDeligate().storeUniformDetails(upojo);
			
			request.setAttribute("message", status);
			request.setAttribute("classpojo", classpojo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.END_POINT);
logger.info(JDate.getTimeString(new Date())
		+ " Control in  SmsAction  : SendUniform Ending");

return mapping.findForward(MessageConstants.UNIFORM_ENTRY);
}

}
