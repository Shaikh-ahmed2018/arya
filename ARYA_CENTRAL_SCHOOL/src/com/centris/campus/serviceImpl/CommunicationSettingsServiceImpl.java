package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import com.centris.campus.dao.CommunicationSettingsDao;
import com.centris.campus.daoImpl.CommunicationSettingsDaoImpl;
import com.centris.campus.forms.ClassForm;
import com.centris.campus.forms.RemarksForm;
import com.centris.campus.pojo.BdayPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.MeetingPojo;
import com.centris.campus.pojo.RemarksPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.service.CommunicationSettingsService;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class CommunicationSettingsServiceImpl implements CommunicationSettingsService{

	private static final Logger logger = Logger
			.getLogger(CommunicationSettingsServiceImpl.class);
	
	
	CommunicationSettingsDao communicatedao = new CommunicationSettingsDaoImpl();
	
	public ArrayList<UpcomingRemarksVO> getRemarksListDetailsService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getRemarksListDetailsService Starting");
		
		
		ArrayList<UpcomingRemarksVO> remarkslist = null;
		
		try {
			
			
			remarkslist = communicatedao.getRemarksListDetailsDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getRemarksListDetailsService  Ending");
		
		return remarkslist;
	}

	
	public List<StreamDetailsVO> getStreamListDetailsService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getStreamListDetailsService Starting");
		
		
		List<StreamDetailsVO> streamvo = null;
		
		
		streamvo = communicatedao.getStreamListDetailsDao();
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getStreamListDetailsService  Ending");
		
		return streamvo;
	}


	
	public List<ClassPojo> getClassListDetailsService(ClassPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getClassListDetailsService Starting");
		
		List<ClassPojo> classpojo = null;
		
		classpojo = communicatedao.getClassListDetailsDao(pojo);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getClassListDetailsService  Ending");
		return classpojo;
	}


	
	public ArrayList<SectionPojo> getSectionListDetailsService(String[] categoryval) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getSectionListDetailsService Starting");
		
		
		ArrayList<SectionPojo> sec = null;
		
		
		sec = communicatedao.getSectionListDetailsDao(categoryval);
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getSectionListDetailsService  Ending");
		return sec;
	}


	
	public ArrayList<StudentInfoVO> getStudentListDetailsService(
			StudentInfoVO studentvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getStudentListDetailsService Starting");
		
		ArrayList<StudentInfoVO> student=null;
		student = communicatedao.getStudentListDetailsDao(studentvo);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getStudentListDetailsService  Ending");
		return student;
	}


	
	public ArrayList<TeacherRegistrationPojo> getTeacherListDetailsService(
			TeacherRegistrationPojo teacherpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getTeacherListDetailsService Starting");
		
		
		ArrayList<TeacherRegistrationPojo> teacherlist = null;
		
		teacherlist=communicatedao.getTeacherListDetailsDao(teacherpojo);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getTeacherListDetailsService  Ending");
		return teacherlist;
	}



	public ArrayList<SubjectPojo> getSubjectListDetailsService(
			SubjectPojo subpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getSubjectListDetailsService Starting");
		
		
		ArrayList<SubjectPojo> sublist = null;
		
		sublist=communicatedao.getSubjectListDetailsDao(subpojo);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getSubjectListDetailsService  Ending");
		return sublist;
	}



	/*public String saveRemarkDetailsService(RemarksVo remarkform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveRemarkDetailsService Starting");
		
		
		RemarksPojo remarkpojo = new RemarksPojo();
		String result = null;
		
		try {
			
			exam.setAccadamicyear(examform.getAccyear());
			
			
			remarkpojo.setRemarkcode(remarkform.getRemarkcode());
			remarkpojo.setStudentid(remarkform.getStudentid());
			remarkpojo.setTeacher(remarkform.getTeacher());
			remarkpojo.setRemarkstype(remarkform.getRemarkstype());
			remarkpojo.setSubjectid(remarkform.getSubjectid());
			remarkpojo.setRemarks(remarkform.getRemarks());
			remarkpojo.setDateId(HelperClass.convertUIToDatabase(remarkform.getDateId()));
			remarkpojo.setCreateUser(remarkform.getCreateUser());
			remarkpojo.setAccyear(remarkform.getAccyear());
			
			
			
			
			result =communicatedao.saveRemarkDetailsDao(remarkpojo);
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveRemarkDetailsService  Ending");
		
		return result;
	}*/


	@Override
	public String saveRemarkDetailsService(RemarksVo remarkform) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : saveRemarkDetailsService Starting");
		
		
		RemarksPojo remarkpojo = new RemarksPojo();
		String result = null;
		
		try {
			
	
			remarkpojo.setRemarkcode(remarkform.getRemarkcode());
			remarkpojo.setStudentid(remarkform.getStudentid());
		/*	remarkpojo.setTeacher(remarkform.getTeacher());*/
			remarkpojo.setRemarkstype(remarkform.getRemarkstype());
			remarkpojo.setSubjectid(remarkform.getSubjectid());
			remarkpojo.setRemarks(remarkform.getRemarks());
			remarkpojo.setDateId(HelperClass.convertUIToDatabase(remarkform.getDateId()));
			remarkpojo.setCreateUser(remarkform.getCreateUser());
			remarkpojo.setAccyear(remarkform.getAccyear());
			
			remarkpojo.setRemarksto(remarkform.getRemarksto());
			
			
			result =communicatedao.saveRemarkDetailsDao(remarkpojo);
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : saveRemarkDetailsService  Ending");
		
		return result;
	}


	
	/*public ArrayList<RemarksVo> searchRemarkDetailsService(RemarksVo remrakvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchRemarkDetailsService Starting");
		
		
		ArrayList<RemarksVo> remarklist = null;
		remarklist=communicatedao.getRemarkListDetailsDao(remrakvo);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchRemarkDetailsService  Ending");
		
		return remarklist;
	}*/


	
	public ArrayList<UpcomingRemarksVO> searchRemarkDetailsService(
			UpcomingRemarksVO remrakvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchRemarkDetailsService Starting");
		
		
		ArrayList<UpcomingRemarksVO> remarklist = null;
		remarklist=communicatedao.getRemarkListDetailsDao(remrakvo);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchRemarkDetailsService  Ending");
		
		return remarklist;
	}


	
	public boolean validateRemarkService(RemarksVo remarkform) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : validateRemarkService Starting");
		boolean remark_validate =false;
		
		
		remark_validate=communicatedao.validateRemarkDao(remarkform);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : validateRemarkService  Ending");
		return remark_validate;
	}


	
	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getMeetingListDetailsService Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = null;
		
		try {
			
			
			meetinglist = communicatedao.getMeetingListDetailsDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getMeetingListDetailsService  Ending");
		
		return meetinglist;
	}


	
	public String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : saveMeetingDetailsService Starting");
		
		
		
		
		MeetingPojo pojo = new MeetingPojo();
		String result=null;
		
		
		try {
			
			
			pojo.setMeetingid(meetingvo.getMeetingid());
			pojo.setMeetingDate(HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
			pojo.setDescription(meetingvo.getDescription());
			pojo.setMeetingtype(meetingvo.getMeetingtype());
			pojo.setMeetingwith(meetingvo.getMeetingwith());
			pojo.setStudentid(meetingvo.getStudentid());
			pojo.setSubjectid(meetingvo.getSubjectid());
			pojo.setTitle(meetingvo.getTitle());
			pojo.setStartTime(meetingvo.getStartTime());
			pojo.setEndTime(meetingvo.getEndTime());
			pojo.setCreatedby(meetingvo.getCreatedby());
			pojo.setClassid(meetingvo.getClassid());
			pojo.setSectionid(meetingvo.getSectionid());
			pojo.setVenueid(meetingvo.getVenueid());
			pojo.setMeetingfor(meetingvo.getMeetingfor());
			
			
			result =communicatedao.saveMeetingDetailsDao(pojo);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : saveMeetingDetailsService  Ending");
		
		return null;
	}


	
	public ArrayList<LstmsUpcomingMeetingVO> searchMeetingDetailsService(
			LstmsUpcomingMeetingVO meetingvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchMeetingDetailsService Starting");
		
		
		ArrayList<LstmsUpcomingMeetingVO> meetinglist = null;
		meetinglist=communicatedao.searchMeetingListDetailsDao(meetingvo);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchMeetingDetailsService  Ending");
		
		return meetinglist;
	}


	
	public String createBdayDetailsService(UpcomingBdayVo bdayvo) {
	

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : createBdayDetailsService Starting");
		
		BdayPojo bdaypojo = new BdayPojo();
		String result=null;
		try {
			
			bdaypojo.setBdayid(bdayvo.getBdayid());
			bdaypojo.setBdayDate(HelperClass.convertUIToDatabase(bdayvo.getBdayDate()));
			bdaypojo.setContent(bdayvo.getContent());
			bdaypojo.setClassname(bdayvo.getClassname());
			bdaypojo.setClassid(bdayvo.getClassid());
			bdaypojo.setSectionname(bdayvo.getSectionname());
			bdaypojo.setSectionid(bdayvo.getSectionid());
			bdaypojo.setBdayto(bdayvo.getBdayto());
			bdaypojo.setBdayperson(bdayvo.getBdayperson());
			bdaypojo.setCreateuser(bdayvo.getCreateuser());
			
			result =communicatedao.createBdayDetailsDao(bdaypojo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : createBdayDetailsService  Ending");
		return result;
	}


	
	public ArrayList<UpcomingBdayVo> getBdayDetailsService() {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getBdayDetailsService Starting");

		ArrayList<UpcomingBdayVo> bdaylist = null;
		
		try {
			
			
			bdaylist = communicatedao.getBdayListDetailsDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : getBdayDetailsService  Ending");
		
		return bdaylist;
	}


	
	public ArrayList<UpcomingBdayVo> searchBdayDetailsService(
			UpcomingBdayVo bdayvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchBdayDetailsService Starting");
		
		
		ArrayList<UpcomingBdayVo> bdaylist = null;
		bdaylist=communicatedao.searchBadyListDetailsDao(bdayvo);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : searchBdayDetailsService  Ending");
		
		
		return bdaylist;
	}


	
	public RemarksVo editRemarkDetailsService(RemarksVo remarkvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : editRemarkDetailsService Starting");
		
		RemarksVo result = null;
		try {
			
			result = communicatedao.editRemarkDetailsDao(remarkvo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsServiceImpl : editRemarkDetailsService  Ending");
		
		return result;
	}

}
