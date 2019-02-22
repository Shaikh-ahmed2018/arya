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

import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.StaffAttendanceReportBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class CommunicationAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName"); 
	
	private static final Logger logger = Logger
			.getLogger(CommunicationAction.class);
	
	
	public ActionForward sendRemarkAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : sendRemark Starting");
		
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			
			List<StreamDetailsVO> streamvo =new CommunicationSettingsBD().getStreamListDetails();
			
			
			
			request.setAttribute("streamlist", streamvo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :sendRemark Ending");
		
		return mapping.findForward(MessageConstants.CREATE_REMARK);
		
	}
	
	public ActionForward getClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : getClass Starting");
		
		
		try {
			ClassPojo pojo = new ClassPojo();
			String streamname=request.getParameter("streamname");
			
			
			pojo.setStreamId(streamname);
			
			
			
		
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo);
			
			

			JSONObject object=new JSONObject();
			 
			 object.put("classlist", classpojo);
			 
			 response.getWriter().print(object);
			 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :getClass Ending");
		
		return null;
		
	}
	
	public ActionForward getSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : getSection Starting");
		
		
		try {
			SectionPojo secpojo = new SectionPojo();
			
			String classid=request.getParameter("classid");
			String[] categoryval=classid.split(",");
			
			//secpojo.setSecDetailsId(categoryval);
			
			
			
			
			
			ArrayList<SectionPojo> seclist=new CommunicationSettingsBD().getSectionListDetails(categoryval);
			
			
			

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
				+ " Control in CommunicationAction :getSection Ending");
		
		
		return null;
			
	}
	
	public ActionForward getStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : getStudent Starting");
		
		try {
			
			StudentInfoVO studentvo = new StudentInfoVO();
			String studentid=request.getParameter("student");
			
			studentvo.setStudentId(studentid);
			
			ArrayList<StudentInfoVO> studentlist=new CommunicationSettingsBD().getStudentListDetails(studentvo);

			JSONObject object=new JSONObject();
			 
			 object.put("studentlist", studentlist);
			 
			 response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();	
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :getStudent Ending");
		return null;
	}
	
	public ActionForward getTeacher(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : getTeacher Starting");
		
		try {
			
			TeacherRegistrationPojo teacherpojo = new TeacherRegistrationPojo();
			String teacher=request.getParameter("teacher");
			
			teacherpojo.setTeacherId(teacher);
	
			ArrayList<TeacherRegistrationPojo> teacherlist =new CommunicationSettingsBD().getTeacherListDetails(teacherpojo);
			
			
			

			JSONObject object=new JSONObject();
			 
			 object.put("teacherlist", teacherlist);
			 
			 response.getWriter().print(object);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();	
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :getTeacher Ending");
		return null;
	}
	
	public ActionForward getSubject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : getSubject Starting");
		
		try {
			
			SubjectPojo subpojo = new SubjectPojo();
			String subject=request.getParameter("subject");
			subpojo.setSubjectId(subject);
			
			
			
			ArrayList<SubjectPojo> subjectlist =new CommunicationSettingsBD().getSubjectListDetails(subpojo);
			
			

			JSONObject object=new JSONObject();
			 
			 object.put("subjectlist", subjectlist);
			 
			 response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :getSubject Ending");
		return null;	
		
	}
	
	public ActionForward saveRemarkAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : saveRemarkAction Starting");
		
		
		
		
		try {
			
			RemarksVo remarkform = new RemarksVo();
			
			String createUser = HelperClass.getCurrentUser(request);
			
			String accyear = HelperClass.getAcademicYear();
			
			String remarksto=request.getParameter("rematksto");
			String studentid=request.getParameter("studentid");
			String remarkstype=request.getParameter("remarkstype");
			String subjectid=request.getParameter("subjectid");
			String remarks=request.getParameter("remarks");
			String dateId=request.getParameter("dateId");
			
		
			
			
			remarkform.setStudentid(studentid);
			remarkform.setRemarkstype(remarkstype);
			remarkform.setSubjectid(subjectid);
			remarkform.setRemarks(remarks);
			remarkform.setDateId(dateId);
			remarkform.setCreateUser(createUser);
			remarkform.setAccyear(accyear);
			remarkform.setRemarksto(remarksto);
			
			String result = new CommunicationSettingsBD().saveRemarkDetails(remarkform);
			
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
				+ " Control in CommunicationAction :saveRemarkAction Ending");
		return null;
		
	}
	
	public ActionForward editRemarkAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : editRemarkAction Starting");
	
	
		try {
			
			RemarksVo remarkvo = new RemarksVo();
			String remarkid = request.getParameter("hremarhcode");
			
			
			remarkvo.setRemarkcode(remarkid);
			
			
			RemarksVo result = new CommunicationSettingsBD().editRemarkDetailsBD(remarkvo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
	
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :editRemarkAction Ending");
		
		return mapping.findForward(MessageConstants.CREATE_REMARK);
		
	}
	
	public ActionForward validateRemark(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : validateRemark Starting");
		
		
		try {
			
			String studentid = request.getParameter("studentid");
			String remarksto = request.getParameter("remarksto");
			String remarkstype = request.getParameter("remarkstype");
			
			String subjectid = request.getParameter("subjectid");
			String remarks = request.getParameter("remarks");
			String dateId = request.getParameter("dateId");
			
			
			RemarksVo remarkform = new RemarksVo();
			
			remarkform.setStudentid(studentid);
			remarkform.setRemarkstype(remarkstype);
			remarkform.setSubjectid(subjectid);
			remarkform.setRemarks(remarks);
			remarkform.setDateId(dateId);
			remarkform.setRemarksto(remarksto);
			
			
			boolean remark_Available=new CommunicationSettingsBD().validateRemarkBD(remarkform);
			
			JSONObject jsonobject= new JSONObject();
			
			if(remark_Available){
				
				jsonobject.put("status", "true");
			}else{
				 jsonobject.put("status", "false");
			}
			response.getWriter().print(jsonobject);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :validateRemark Ending");
		return null;
		
	}
	
	public ActionForward createMeetingAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : createMeetingAction Starting");
		
		
		try {
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			List<StreamDetailsVO> streamvo =new CommunicationSettingsBD().getStreamListDetails();
			
			request.setAttribute("streamlist", streamvo);
			
			
			
			
			
			
			SubjectPojo subpojo = new SubjectPojo();
	
			ArrayList<SubjectPojo> subjectlist =new CommunicationSettingsBD().getSubjectListDetails(subpojo);
			
			
			
			
			request.setAttribute("subjectlist", subjectlist);

		
			
			ClassPojo pojo = new ClassPojo();
			
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo);
			
			
			
			
			request.setAttribute("classpojo", classpojo);
			
			
			
			
			
			List<LstmsUpcomingMeetingVO> meetinglist =new CommunicationSettingsBD().getMeetingListDetails();
			
			request.setAttribute("meetinglist", meetinglist);
			
			

			/*JSONObject object=new JSONObject();
			 
			 object.put("classlist", classpojo);
			 
			 response.getWriter().print(object);*/
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :createMeetingAction Ending");
		
		return mapping.findForward(MessageConstants.CREATE_MEETING);
		
	}
	
	public ActionForward saveMeetingAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : saveMeetingAction Starting");
		
		
		
		
		
		try {
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			
			String createUser = HelperClass.getCurrentUser(request);
			
			String dateId = request.getParameter("dateId");
			String meetingfor = request.getParameter("meetingfor");
			String classname = request.getParameter("classname");
			String sectionname = request.getParameter("sectionname");
			String studentid = request.getParameter("studentid");
			String title = request.getParameter("title");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			String venueid = request.getParameter("venueid");
			String subjectid = request.getParameter("subjectid");
			String description = request.getParameter("description");
			
			
			
			
			meetingvo.setMeetingDate(dateId);
			meetingvo.setMeetingfor(meetingfor);
			meetingvo.setClassname(classname);
			meetingvo.setSectionname(sectionname);
			meetingvo.setMeetingwith(studentid);
			meetingvo.setTitle(title);
			meetingvo.setStartTime(starttime);
			meetingvo.setEndTime(endtime);
			meetingvo.setVenueid(venueid);
			meetingvo.setSubjectid(subjectid);
			meetingvo.setDescription(description);
			meetingvo.setCreatedby(createUser);
			
			
			String result = new CommunicationSettingsBD().saveMeetingDetails(meetingvo);
			
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
				+ " Control in CommunicationAction :saveMeetingAction Ending");
		
		
		return null;
		
		
		
	}
	
	public ActionForward createBdayWishAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : createBdayWishAction Starting");
		
		
		
		try {
			
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			List<StreamDetailsVO> streamvo =new CommunicationSettingsBD().getStreamListDetails();
			
			
			
			request.setAttribute("streamlist", streamvo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction :createBdayWishAction Ending");
		
		return mapping.findForward(MessageConstants.CREATE_BDAYWISH);
		
		
	}
	
	public ActionForward saveBdayAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : saveBdayAction Starting");
		
	
try {
			
			UpcomingBdayVo bdayvo = new UpcomingBdayVo();
			
			String createuser = HelperClass.getCurrentUser(request);
			
			String bdayto = request.getParameter("studentid");
			String classname = request.getParameter("classname");
			String sectionname = request.getParameter("sectionname");
			String content = request.getParameter("content");
			String bdaydate = request.getParameter("dateId");
			
			
			
			String bdayperson = request.getParameter("bdayto");
			
			
			
			bdayvo.setCreateuser(createuser);
			bdayvo.setBdayto(bdayto);
			bdayvo.setClassname(classname);
			bdayvo.setSectionname(sectionname);
			bdayvo.setContent(content);
			bdayvo.setBdayperson(bdayperson);
			bdayvo.setBdayDate(bdaydate);
			
		
			
			String result = new CommunicationSettingsBD().createBdayDetails(bdayvo);
			
		
			
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
				+ " Control in CommunicationAction :saveBdayAction Ending");
		return null;
		
		
		
	}

	public ActionForward remarksDownloadXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : remarksDownloadXLS Starting");
	
	
	try {
		

		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/RemarksListXLSReport.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		
		ArrayList<UpcomingRemarksVO> remarkslist = new ArrayList<UpcomingRemarksVO>();
		
		UpcomingRemarksVO remrakvo = new UpcomingRemarksVO();
		
		String hremarksid = request.getParameter("hremarksid");
		String hfromdateid = request.getParameter("hfromdateid");
		String htodateid = request.getParameter("htodateid");
		

		
		remrakvo.setRemarks(hremarksid);
		remrakvo.setFromdate(hfromdateid);
		remrakvo.setTodate(htodateid);
	
		if(hremarksid != "" && hfromdateid != "" && htodateid != ""){
			
			
			 remarkslist = new CommunicationSettingsBD().searchRemarkDetails(remrakvo);
			 
			 
			
		}
		else{
			
			remarkslist = new CommunicationSettingsBD().getRemarksListDetails();
		
		}
		
	
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				remarkslist);
		Map parameters = new HashMap();

		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				parameters, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/RemarksListXLSReport.xls"));
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
				request.getRealPath("Reports/RemarksListXLSReport.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=RemarksListXLSReport.xls");
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
				+ " Control in CommunicationAction :remarksDownloadXLS Ending");
		
		return null;
		
	}
	
	public ActionForward meetingDownloadXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : meetingDownloadXLS Starting");
	
		try {
			
            
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/MeetingListXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			
			String hmeetingid = request.getParameter("hremarksid");
			String hfromdateid = request.getParameter("hfromdateid");
			String htodateid = request.getParameter("htodateid");
			
			meetingvo.setTitle(hmeetingid);
			meetingvo.setFromdate(hfromdateid);
			meetingvo.setTodate(htodateid);
			
			if(hmeetingid != "" && hfromdateid != "" && htodateid != ""){
				
				 meetinglist = new CommunicationSettingsBD().searchMeetingDetails(meetingvo);
				
				
			}
			else{
				
				 meetinglist = new CommunicationSettingsBD().getMeetingListDetails();
			
			}
			
	
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					meetinglist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/MeetingListXLSReport.xls"));
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
					request.getRealPath("Reports/MeetingListXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=MeetingListXLSReport.xls");
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
				+ " Control in CommunicationAction :meetingDownloadXLS Ending");
		
		return null;
		
	}
	
	public ActionForward bdayWishDownloadXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : bdayWishDownloadXLS Starting");
	
		try {
			

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/BdayListXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
		
			
			ArrayList<UpcomingBdayVo> bdaylist = new ArrayList<UpcomingBdayVo>();
			
			UpcomingBdayVo bdayvo = new UpcomingBdayVo();
			String hbdayid = request.getParameter("hremarksid");
			String hfromdateid = request.getParameter("hfromdateid");
			String htodateid = request.getParameter("htodateid");
			

			bdayvo.setContent(hbdayid);
			bdayvo.setFromdate(hfromdateid);
			bdayvo.setTodate(htodateid);
			
			
			
	    	if(hbdayid !="" && hfromdateid !="" && htodateid !=""){
				
				bdaylist = new CommunicationSettingsBD().searchBdayDetails(bdayvo);
				
				
			}
			
			else{
				
				 bdaylist = new CommunicationSettingsBD().getBdayListDetails();
				 
				
			}
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					bdaylist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/BdayListXLSReport.xls"));
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
					request.getRealPath("Reports/BdayListXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=BdayListXLSReport.xls");
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
				+ " Control in CommunicationAction :bdayWishDownloadXLS Ending");
		
		return null;
		
	}
	
	public ActionForward remarksDownloadPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : remarksDownloadPDF Starting");
	
		try {
			
			ArrayList<UpcomingRemarksVO> remarkslist = new ArrayList<UpcomingRemarksVO>();
			
			UpcomingRemarksVO remrakvo = new UpcomingRemarksVO();
			
			String hremarksid = request.getParameter("hremarksid");
			String hfromdateid = request.getParameter("hfromdateid");
			String htodateid = request.getParameter("htodateid");
			

			
			remrakvo.setRemarks(hremarksid);
			remrakvo.setFromdate(hfromdateid);
			remrakvo.setTodate(htodateid);
		
			if(hremarksid != "" && hfromdateid != "" && htodateid != ""){
				
				
				 remarkslist = new CommunicationSettingsBD().searchRemarkDetails(remrakvo);
				 
				 
				
			}
			else{
				
				remarkslist = new CommunicationSettingsBD().getRemarksListDetails();
			
			}

			String sourceFileName = request
					.getRealPath("Reports/RemarksPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					remarkslist);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "RemarksPDFReport - " + ".pdf\"");

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
				+ " Control in CommunicationAction :remarksDownloadPDF Ending");
		
		return null;
		
	}
	
	public ActionForward meetingDownloadPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : meetingDownloadPDF Starting");
	
		
		try {
			
			
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			
			String hmeetingid = request.getParameter("hremarksid");
			String hfromdateid = request.getParameter("hfromdateid");
			String htodateid = request.getParameter("htodateid");
			
			meetingvo.setTitle(hmeetingid);
			meetingvo.setFromdate(hfromdateid);
			meetingvo.setTodate(htodateid);
			
			if(hmeetingid != "" && hfromdateid != "" && htodateid != ""){
				
				 meetinglist = new CommunicationSettingsBD().searchMeetingDetails(meetingvo);
				
				
			}
			else{
				
				 meetinglist = new CommunicationSettingsBD().getMeetingListDetails();
			
			}
			String sourceFileName = request
					.getRealPath("Reports/MeetingPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					meetinglist);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "MeetingPDFReport - " + ".pdf\"");

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
				+ " Control in CommunicationAction :meetingDownloadPDF Ending");
		
		return null;
		
	}
	
	public ActionForward bdayWishDownloadPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationAction : bdayWishDownloadPDF Starting");
	
		try {
			
			ArrayList<UpcomingBdayVo> bdaylist = new ArrayList<UpcomingBdayVo>();
			
			UpcomingBdayVo bdayvo = new UpcomingBdayVo();
			String hbdayid = request.getParameter("hremarksid");
			String hfromdateid = request.getParameter("hfromdateid");
			String htodateid = request.getParameter("htodateid");
			

			bdayvo.setContent(hbdayid);
			bdayvo.setFromdate(hfromdateid);
			bdayvo.setTodate(htodateid);
			
			
			
	    	if(hbdayid !="" && hfromdateid !="" && htodateid !=""){
				
				bdaylist = new CommunicationSettingsBD().searchBdayDetails(bdayvo);
				
				
			}
			
			else{
				
				 bdaylist = new CommunicationSettingsBD().getBdayListDetails();
				 
				
			}
			

			String sourceFileName = request
					.getRealPath("Reports/BdayListPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					bdaylist);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "BdayListPDFReport - " + ".pdf\"");

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
				+ " Control in CommunicationAction :bdayWishDownloadPDF Ending");
		
		return null;
		
	}
	
	
}
