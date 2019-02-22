package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ParentSettingsDao;
import com.centris.campus.daoImpl.ParentSettingsDaoImpl;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.service.ParentSettingsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentFeedbackVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.ViewallSubjectsVo;

public class ParentSettingsServiceServiceImpl implements ParentSettingsService {

	ParentSettingsDao parentdao = new ParentSettingsDaoImpl();
	private static final Logger logger = Logger
			.getLogger(ParentSettingsServiceServiceImpl.class);

	public ArrayList<ExaminationDetailsVo> getExamListDetailsService(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getExamListDetailsService Starting");

		ArrayList<ExaminationDetailsVo> examlist = null;
		try {

			examlist = parentdao.getExamListDetailsDao(vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getExamListDetailsService  Ending");

		return examlist;
	}

	public ArrayList<ParentVO> getStudentService(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentService Starting");

		ArrayList<ParentVO> student = null;
		try {

			student = parentdao.getStudentDao(vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentService  Ending");

		return student;
	}

	public ArrayList<ExaminationDetailsVo> getMoreChildExamListService(
			ExaminationDetailsVo exmvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getMoreChildExamListService Starting");

		ArrayList<ExaminationDetailsVo> studentlist = null;
		try {

			studentlist = parentdao.getMoreChildExamListDao(exmvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getMoreChildExamListService  Ending");

		return studentlist;
	}

	public ArrayList<AssignmentViewVO> getAssignmentListBDService(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAssignmentListBDService Starting");

		ArrayList<AssignmentViewVO> assignmentlist = null;
		try {

			assignmentlist = parentdao.getAssignmentListDao(vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAssignmentListBDService  Ending");

		return assignmentlist;
	}

	public ArrayList<AssignmentViewVO> getmoreAssidnmentListService(
			AssignmentViewVO assvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getmoreAssidnmentListService Starting");

		ArrayList<AssignmentViewVO> assignmentlist = null;
		try {

			assignmentlist = parentdao.getmoreAssidnmentListDao(assvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getmoreAssidnmentListService  Ending");

		return assignmentlist;
	}

	public AssignmentViewVO getviewAssignmentService(AssignmentViewVO assvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getviewAssignmentService Starting");

		// ArrayList<AssignmentViewVO> asslist = null;

		AssignmentViewVO asslist = null;
		try {

			asslist = parentdao.getviewAssignmentDao(assvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getviewAssignmentService  Ending");
		return asslist;
	}

	public StudentRegistrationVo getStudentInfoService(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentInfoService Starting");

		StudentRegistrationVo studinfo = null;

		try {

			studinfo = parentdao.getStudentInfoDao(vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentInfoService  Ending");
		return studinfo;
	}

	public String saveFeedBackDetailsService(ParentFeedbackVo fbvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveFeedBackDetailsService Starting");

		String fbvo1 = null;

		try {

			fbvo1 = parentdao.saveFeedBackDetailsDao(fbvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveFeedBackDetailsService  Ending");

		return fbvo1;
	}

	public ArrayList<ParentFeedbackVo> getFeedBackDetailsService(
			ParentFeedbackVo fbvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getFeedBackDetailsService Starting");

		ArrayList<ParentFeedbackVo> fbvo1 = null;

		try {

			fbvo1 = parentdao.getFeedBackDetailsDao(fbvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getFeedBackDetailsService  Ending");

		return fbvo1;
	}

	public StudentRegistrationVo getnextStudentInfoService(
			StudentRegistrationVo studentinfo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getnextStudentInfoService Starting");

		StudentRegistrationVo stuinfo = null;

		try {

			stuinfo = parentdao.getnextStudentInfoDao(studentinfo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getnextStudentInfoService  Ending");
		return stuinfo;
	}

	public ArrayList<StudentAttendanceVo> getAttendanceMonthListService(
			String year, String month, ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAttendanceMonthListService Starting");

		ArrayList<StudentAttendanceVo> studattn = null;

		try {

			studattn = parentdao.getAttendanceMonthListDao(year, month, vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAttendanceMonthListService  Ending");

		return studattn;
	}

	public ArrayList<StudentAttendanceVo> getAttendanceDayListService(
			StudentAttendanceVo attvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAttendanceDayListService Starting");

		ArrayList<StudentAttendanceVo> list = null;
		try {

			list = parentdao.getAttendanceDayListDao(attvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAttendanceDayListService  Ending");

		return list;
	}

	public ArrayList<StudentAttendanceVo> getNextChildAttendanceMonthListService(
			String year, String month, StudentAttendanceVo studvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getNextChildAttendanceMonthListService Starting");

		ArrayList<StudentAttendanceVo> list = null;
		try {

			list = parentdao.getNextChildAttendanceMonthListDao(year, month,
					studvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getNextChildAttendanceMonthListService  Ending");

		return list;
	}

	public List<StreamDetailsVO> getStreamListDetailsService() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStreamListDetailsService Starting");

		List<StreamDetailsVO> streamvo = null;
		try {

			streamvo = parentdao.getStreamListDetailsDao();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStreamListDetailsService  Ending");

		return streamvo;
	}

	public List<ClassPojo> getClassListDetailsService(ClassPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getClassListDetailsService Starting");

		List<ClassPojo> clspojo = null;
		try {

			clspojo = parentdao.getClassListDetailsDao(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getClassListDetailsService  Ending");

		return clspojo;
	}

	public List<ViewallSubjectsVo> getSubjectDetailsService(
			ViewallSubjectsVo subvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSubjectDetailsService Starting");

		List<ViewallSubjectsVo> sublist = null;
		try {

			sublist = parentdao.getSubjectDetailsDao(subvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSubjectDetailsService  Ending");

		return sublist;
	}

	public ArrayList<StudentAttendanceVo> getmoreAttendanceDayListService(
			StudentAttendanceVo attvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getmoreAttendanceDayListService Starting");

		ArrayList<StudentAttendanceVo> list = null;
		try {

			list = parentdao.getmoreAttendanceDayListDao(attvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getmoreAttendanceDayListService  Ending");

		return list;
	}

	public String getfeedbackfilepathService(String fbid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getfeedbackfilepathService Starting");

		String path = null;
		try {

			path = parentdao.getfeedbackfilepathDao(fbid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getfeedbackfilepathService  Ending");

		return path;
	}

	public String getsubjectfilepathService(String subid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getsubjectfilepathService Starting");

		String path = null;
		try {

			path = parentdao.getsubjectfilepathDao(subid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getsubjectfilepathService  Ending");

		return path;
	}

	public List<LstmsUpcomingMeetingVO> getMeetingListDetailsService() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getMeetingListDetailsService Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = null;

		try {

			meetinglist = parentdao.getMeetingListDetailsDao();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getMeetingListDetailsService  Ending");

		return meetinglist;
	}

	public List<LstmsUpcomingMeetingVO> getstudentmeetinglistService(
			LstmsUpcomingMeetingVO meetvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getstudentmeetinglistService Starting");
		ArrayList<LstmsUpcomingMeetingVO> meetinglist = null;
		try {

			meetinglist = parentdao.getstudentmeetinglistDao(meetvo);

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

	public String leaveRequestEntryService(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : leaveRequestEntryService Starting");

		String leavevo1 = null;

		if (leavevo.getSno() == 0) {

			try {

				leavevo1 = parentdao.leaveRequestEntryDao(leavevo);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		else if (leavevo.getSno() != 0) {

			try {

				leavevo1 = parentdao.updateleaveRequestEntryDao(leavevo);

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

	public ArrayList<LeaveRequestVo> getleaveRequestDetailService(
			LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailService Starting");

		ArrayList<LeaveRequestVo> leavelist = null;
		try {

			leavelist = parentdao.getleaveRequestDetailDao(leavevo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailService  Ending");

		return leavelist;
	}

	public LeaveRequestVo getRequestLeaveService(int sno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestLeaveService Starting");

		LeaveRequestVo asslist = null;
		try {

			asslist = parentdao.getRequestLeaveDao(sno);

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

	public String deleteRequestLeaveBDService(String[] sno,String userid,String academic_year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteRequestLeaveBDService Starting");

		String asslist = null;
		try {

			asslist = parentdao.deleteRequestLeaveDao(sno,userid,academic_year);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteRequestLeaveBDService  Ending");
		return asslist;
	}

	public ArrayList<TimeTableVo> getStudentTimetableService(String studentid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentTimetableService Starting");

		ArrayList<TimeTableVo> timetablelist = null;

		try {

			timetablelist = parentdao.getStudentTimetableDao(studentid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStudentTimetableService  Ending");

		return timetablelist;
	}

	public ArrayList<TimeTableVo> getFirstStudentTimeTableService(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getFirstStudentTimeTableService Starting");

		ArrayList<TimeTableVo> timetablelist = null;

		try {
			timetablelist = parentdao.getFirstStudentTimeTableDao(vo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getFirstStudentTimeTableService  Ending");

		return timetablelist;
	}

	public ArrayList<LeaveRequestVo> getleaveApprovalDetailService(
			LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveApprovalDetailService Starting");

		ArrayList<LeaveRequestVo> leavelist = null;
		try {

			leavelist = parentdao.getleaveApprovalDetailDao(leavevo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveApprovalDetailService  Ending");

		return leavelist;
	}

	public List<UserDetailVO> getRequestUserListService(String user) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestUserListService Starting");

		List<UserDetailVO> uservo = null;
		try {

			uservo = parentdao.getRequestUserListDao(user);

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

	public LeaveRequestVo getLeaveApproval(int sno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getLeaveApproval Starting");

		LeaveRequestVo asslist = null;
		try {

			asslist = parentdao.getLeaveApprovalDao(sno);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getLeaveApproval  Ending");

		return asslist;
	}

	public ArrayList<LeaveRequestVo> ApprovingLeaveforleaveRequestService(
			LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveApprovalDetailService Starting");

		ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
		try {

			leavelist = parentdao.ApprovingLeaveforleaveRequestDAO(leavevo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveApprovalDetailService  Ending");

		return leavelist;
	}

	@Override
	public ArrayList<LeaveRequestVo> searchleave(String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchleave Starting");

		ArrayList<LeaveRequestVo> leavelist = null;
		try {

			leavelist = parentdao.searchleave(searchTerm);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchleave  Ending");

		return leavelist;
	}

	@Override
	public ArrayList<LeaveRequestVo> searchleaverequest(String userid,String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchleaverequest Starting");

		ArrayList<LeaveRequestVo> leavelist = null;
		try {

			leavelist = parentdao.searchleaverequest(searchTerm,userid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchleaverequest  Ending");

		return leavelist;
	}

	@Override
	public ArrayList<LeaveRequestVo> getleaveRequestDetailService(LeaveRequestVo leavevo, String empId,String usertype) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailService Starting");

		ArrayList<LeaveRequestVo> leavelist = null;
		try {

			leavelist = parentdao.getleaveRequestDetailDao(leavevo,empId,usertype);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getleaveRequestDetailService  Ending");

		return leavelist;
	}

	@Override
	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ParentVO> viewExamdetails(ParentVO obj) {
		return parentdao.viewExamdetails(obj);
	}

	@Override
	public ParentVO getexamname(ParentVO obj) {
		return parentdao.getexamname(obj);
	}

	@Override
	public ParentVO getStudentDetails(ParentVO obj) {
		return parentdao.getStudentDetails(obj);
	}

}
