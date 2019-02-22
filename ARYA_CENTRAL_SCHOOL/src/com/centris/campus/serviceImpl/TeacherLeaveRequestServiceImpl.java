package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TeacherLeaveRequestDao;
import com.centris.campus.daoImpl.TeacherLeaveRequestDaoImpl;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.service.TeacherLeaveRequestService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public class TeacherLeaveRequestServiceImpl implements TeacherLeaveRequestService {

	
	
	TeacherLeaveRequestDao dao = new TeacherLeaveRequestDaoImpl();
	private static final Logger logger = Logger
			.getLogger(TeacherLeaveRequestServiceImpl.class);
	
	
	public List<UserDetailVO> getRequestUserListService(String user) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestUserListService Starting"); 
		
		
		List<UserDetailVO> uservo = null;
		try {
			
			uservo = dao.getRequestUserListDao(user);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestUserListService  Ending");
		return uservo;
	}



	public ArrayList<TimeTableVo> getTeacherTimetableListService(String userid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherTimetableListService Starting"); 
		

		ArrayList<TimeTableVo> uservo = null;
		try {
			
			uservo = dao. getTeacherTimetableListDao(userid);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherTimetableListService  Ending");
		
		return uservo;
	}




	public String leaveRequestEntryService(LeaveRequestVo leavevo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : leaveRequestEntryService Starting"); 
		
		String leavevo1 =null;
		
		
		if(leavevo.getSno()==0){
			
			
			
			try {
				
				leavevo1 = dao.leaveRequestEntryDao(leavevo);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
		
		else if(leavevo.getSno() != 0){
			
			
			try {
				
				leavevo1 = dao.updateleaveRequestEntryDao(leavevo);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : leaveRequestEntryService  Ending");
		
		return leavevo1;
	}



	
	public LeaveRequestVo getRequestLeaveService(int sno) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestLeaveService Starting"); 
		
		
		LeaveRequestVo asslist =null;
		try {
			
			
			asslist = dao.getRequestLeaveDao(sno);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestLeaveService  Ending");
		
		
		return asslist;
	}




	public List<LeaveBalanceVo> viewLeaveBalListDetailsService(String userid,String accyear,
			LeaveRequestForm leaveform) {
		
		LeaveRequestVo leavevo = new LeaveRequestVo();
		leavevo.setCheck(leaveform.getCreateuser());
		
		
		return dao.viewLeaveBalListDetailsDao(userid,accyear,leavevo);
	}




	public ArrayList<ParentVO> getTeacherlistService(ParentVO vo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherlistService Starting"); 
		
		ArrayList<ParentVO> teacher = null;
		try {
			
			
			teacher = dao.getTeacherListDao(vo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherlistService  Ending");
		
		return teacher;
	}




	public List<LstmsUpcomingMeetingVO> getteachermeetinglistService(
			LstmsUpcomingMeetingVO meetvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getstudentmeetinglistService Starting");
		ArrayList<LstmsUpcomingMeetingVO> meetinglist = null;
		try {
			
			
			meetinglist = dao.getteachermeetinglistDao(meetvo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getstudentmeetinglistService  Ending");
		
		return meetinglist;
	}



	
	public ArrayList<LeaveRequestVo> getleaveRequestDetailsService(String parentid,LeaveRequestVo leavevo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailsService Starting");
		
		ArrayList<LeaveRequestVo> leavelist = null;
		try {
			
			
			leavelist = dao.getleaveRequestDetailsDao(parentid,leavevo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailsService  Ending");
		return leavelist;
	}




	public List<RemainderMasterVO> getRemainderlistService() {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRemainderlistService Starting");
		
	 	ArrayList<RemainderMasterVO> remainderlist = null;
		try {
			
			
			remainderlist = dao. getRemainderlistDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRemainderlistService  Ending");
		
		return remainderlist;
	}

}
