package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
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

public interface ParentSettingsDao {

	ArrayList<ExaminationDetailsVo> getExamListDetailsDao(ParentVO vo);

	ArrayList<ParentVO> getStudentDao(ParentVO vo);

	ArrayList<ExaminationDetailsVo> getMoreChildExamListDao(
			ExaminationDetailsVo exmvo);

	ArrayList<AssignmentViewVO> getAssignmentListDao(ParentVO vo);

	ArrayList<AssignmentViewVO> getmoreAssidnmentListDao(AssignmentViewVO assvo);

	AssignmentViewVO getviewAssignmentDao(AssignmentViewVO assvo);

	StudentRegistrationVo getStudentInfoDao(ParentVO vo);

	String saveFeedBackDetailsDao(ParentFeedbackVo fbvo);

	ArrayList<ParentFeedbackVo> getFeedBackDetailsDao(ParentFeedbackVo fbvo);

	StudentRegistrationVo getnextStudentInfoDao(
			StudentRegistrationVo studentinfo);

	ArrayList<StudentAttendanceVo> getAttendanceMonthListDao(String year,
			String month, ParentVO vo);

	ArrayList<StudentAttendanceVo> getAttendanceDayListDao(
			StudentAttendanceVo attvo);

	ArrayList<StudentAttendanceVo> getNextChildAttendanceMonthListDao(
			String year, String month, StudentAttendanceVo studvo);

	List<StreamDetailsVO> getStreamListDetailsDao();

	List<ClassPojo> getClassListDetailsDao(ClassPojo pojo);

	List<ViewallSubjectsVo> getSubjectDetailsDao(ViewallSubjectsVo subvo);

	ArrayList<StudentAttendanceVo> getmoreAttendanceDayListDao(
			StudentAttendanceVo attvo);

	String getfeedbackfilepathDao(String fbid);

	String getsubjectfilepathDao(String subid);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsDao();

	ArrayList<LstmsUpcomingMeetingVO> getstudentmeetinglistDao(
			LstmsUpcomingMeetingVO meetvo);

	String leaveRequestEntryDao(LeaveRequestVo leavevo);

	ArrayList<LeaveRequestVo> getleaveRequestDetailDao(LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveDao(int sno);

	String updateleaveRequestEntryDao(LeaveRequestVo leavevo);

	String deleteRequestLeaveDao(String[] sno, String userid, String academic_year);

	ArrayList<TimeTableVo> getStudentTimetableDao(String studentid);

	ArrayList<TimeTableVo> getFirstStudentTimeTableDao(ParentVO vo);

	List<UserDetailVO> getRequestUserListDao(String user);

	ArrayList<LeaveRequestVo> getleaveApprovalDetailDao(LeaveRequestVo leavevo);

	LeaveRequestVo getLeaveApprovalDao(int sno);

	ArrayList<LeaveRequestVo> ApprovingLeaveforleaveRequestDAO(
			LeaveRequestVo leavevo);

	ArrayList<LeaveRequestVo> searchleave(String searchTerm);

	ArrayList<LeaveRequestVo> searchleaverequest(String searchTerm, String userid);

	ArrayList<LeaveRequestVo> getleaveRequestDetailDao(LeaveRequestVo leavevo,String empId, String usertype);

	ArrayList<ParentVO> viewExamdetails(ParentVO obj);

	ParentVO getexamname(ParentVO obj);

	ParentVO getStudentDetails(ParentVO obj);

}
