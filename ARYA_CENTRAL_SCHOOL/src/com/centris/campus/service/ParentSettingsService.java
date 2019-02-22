package com.centris.campus.service;

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

public interface ParentSettingsService {

	ArrayList<ExaminationDetailsVo> getExamListDetailsService(ParentVO vo);

	ArrayList<ParentVO> getStudentService(ParentVO vo);

	ArrayList<ExaminationDetailsVo> getMoreChildExamListService(
			ExaminationDetailsVo exmvo);

	ArrayList<AssignmentViewVO> getAssignmentListBDService(ParentVO vo);

	ArrayList<AssignmentViewVO> getmoreAssidnmentListService(
			AssignmentViewVO assvo);

	AssignmentViewVO getviewAssignmentService(AssignmentViewVO assvo);

	StudentRegistrationVo getStudentInfoService(ParentVO vo);

	String saveFeedBackDetailsService(ParentFeedbackVo fbvo);

	ArrayList<ParentFeedbackVo> getFeedBackDetailsService(ParentFeedbackVo fbvo);

	StudentRegistrationVo getnextStudentInfoService(
			StudentRegistrationVo studentinfo);

	ArrayList<StudentAttendanceVo> getAttendanceMonthListService(String year,
			String month, ParentVO vo);

	ArrayList<StudentAttendanceVo> getAttendanceDayListService(
			StudentAttendanceVo attvo);

	ArrayList<StudentAttendanceVo> getNextChildAttendanceMonthListService(
			String year, String month, StudentAttendanceVo studvo);

	List<StreamDetailsVO> getStreamListDetailsService();

	List<ClassPojo> getClassListDetailsService(ClassPojo pojo);

	List<ViewallSubjectsVo> getSubjectDetailsService(ViewallSubjectsVo subvo);

	ArrayList<StudentAttendanceVo> getmoreAttendanceDayListService(
			StudentAttendanceVo attvo);

	String getfeedbackfilepathService(String fbid);

	String getsubjectfilepathService(String subid);

	List<LstmsUpcomingMeetingVO> getMeetingListDetailsService();

	List<LstmsUpcomingMeetingVO> getstudentmeetinglistService(
			LstmsUpcomingMeetingVO meetvo);

	String leaveRequestEntryService(LeaveRequestVo leavevo);

	ArrayList<LeaveRequestVo> getleaveRequestDetailService(
			LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveService(int sno);

	String deleteRequestLeaveBDService(String[] list, String userid, String academic_year);

	ArrayList<TimeTableVo> getStudentTimetableService(String studentid);

	ArrayList<TimeTableVo> getFirstStudentTimeTableService(ParentVO vo);

	List<UserDetailVO> getRequestUserListService(String user);

	ArrayList<LeaveRequestVo> getleaveApprovalDetailService(LeaveRequestVo leavevo);

	LeaveRequestVo getLeaveApproval(int sno);

	ArrayList<LeaveRequestVo> ApprovingLeaveforleaveRequestService(
			LeaveRequestVo leavevo);

	ArrayList<LeaveRequestVo> searchleave(String searchTerm);

	ArrayList<LeaveRequestVo> searchleaverequest(String userid,String searchTerm);

	ArrayList<LeaveRequestVo> getleaveRequestDetailService(
			LeaveRequestVo leavevo, String empId, String usertype);

	ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(String empId);

	ArrayList<ParentVO> viewExamdetails(ParentVO obj);

	ParentVO getexamname(ParentVO obj);

	ParentVO getStudentDetails(ParentVO obj);

}
