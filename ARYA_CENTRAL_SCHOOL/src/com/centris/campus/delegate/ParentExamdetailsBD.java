package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.service.ParentSettingsService;
import com.centris.campus.serviceImpl.ParentSettingsServiceServiceImpl;
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

public class ParentExamdetailsBD {

	ParentSettingsService service = new ParentSettingsServiceServiceImpl();

	public ArrayList<ExaminationDetailsVo> getExamListDetails(ParentVO vo) {

		return service.getExamListDetailsService(vo);
	}

	public ArrayList<ParentVO> getStudentlist(ParentVO vo) {

		return service.getStudentService(vo);
	}

	public ArrayList<ExaminationDetailsVo> getMoreChildExamListDetails(
			ExaminationDetailsVo exmvo) {

		return service.getMoreChildExamListService(exmvo);
	}

	public ArrayList<AssignmentViewVO> getAssignmentListBD(ParentVO vo) {

		return service.getAssignmentListBDService(vo);
	}

	public ArrayList<AssignmentViewVO> getmoreAssidnmentListBD(
			AssignmentViewVO assvo) {

		return service.getmoreAssidnmentListService(assvo);
	}

	public AssignmentViewVO getviewAssignmentBD(AssignmentViewVO assvo) {

		return service.getviewAssignmentService(assvo);
	}

	public StudentRegistrationVo getStudentInfoBD(ParentVO vo) {

		return service.getStudentInfoService(vo);
	}

	public String saveFeedBackDetails(ParentFeedbackVo fbvo) {

		return service.saveFeedBackDetailsService(fbvo);
	}

	public ArrayList<ParentFeedbackVo> getFeedBackDetailsBD(
			ParentFeedbackVo fbvo) {

		return service.getFeedBackDetailsService(fbvo);
	}

	public StudentRegistrationVo getnextStudentInfoBD(
			StudentRegistrationVo studentinfo) {

		return service.getnextStudentInfoService(studentinfo);
	}

	public ArrayList<StudentAttendanceVo> getAttendanceMonthList(String year,
			String month, ParentVO vo) {

		return service.getAttendanceMonthListService(year, month, vo);
	}

	public ArrayList<StudentAttendanceVo> getAttendanceDayList(
			StudentAttendanceVo attvo) {

		return service.getAttendanceDayListService(attvo);
	}

	public ArrayList<StudentAttendanceVo> getNextChildAttendanceMonthList(
			String year, String month, StudentAttendanceVo studvo) {

		return service.getNextChildAttendanceMonthListService(year, month,
				studvo);
	}

	public List<StreamDetailsVO> getStreamListDetails() {

		return service.getStreamListDetailsService();
	}

	public List<ClassPojo> getClassListDetails(ClassPojo pojo) {

		return service.getClassListDetailsService(pojo);
	}

	public List<ViewallSubjectsVo> getSubjectDetails(ViewallSubjectsVo subvo) {

		return service.getSubjectDetailsService(subvo);
	}

	public ArrayList<StudentAttendanceVo> getmoreAttendanceDayList(
			StudentAttendanceVo attvo) {

		return service.getmoreAttendanceDayListService(attvo);
	}

	public String getfeedbackfilepath(String fbid) {

		return service.getfeedbackfilepathService(fbid);
	}

	public String getsubjectfilepath(String subid) {

		return service.getsubjectfilepathService(subid);
	}

	public List<LstmsUpcomingMeetingVO> getMeetingListDetails() {

		return service.getMeetingListDetailsService();
	}

	public List<LstmsUpcomingMeetingVO> getstudentmeetinglistBD(
			LstmsUpcomingMeetingVO meetvo) {

		return service.getstudentmeetinglistService(meetvo);
	}

	public String leaveRequestEntryBD(LeaveRequestVo leavevo) {

		return service.leaveRequestEntryService(leavevo);
	}

	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(
			LeaveRequestVo leavevo) {

		return service.getleaveRequestDetailService(leavevo);
	}

	public LeaveRequestVo getRequestLeaveBD(int sno) {

		return service.getRequestLeaveService(sno);
	}

	public String deleteRequestLeaveBD(String[] list, String userid, String academic_year) {

		return service.deleteRequestLeaveBDService(list,userid,academic_year);
	}

	public ArrayList<TimeTableVo> getStudentTimetable(String studentid) {

		return service.getStudentTimetableService(studentid);
	}

	public ArrayList<TimeTableVo> getFirstStudentTimeTableBD(ParentVO vo) {

		return service.getFirstStudentTimeTableService(vo);
	}

	public List<UserDetailVO> getRequestUserListDetails(String user) {

		return service.getRequestUserListService(user);
	}

	public ArrayList<LeaveRequestVo> getleaveApprovalDetailsBD(
			LeaveRequestVo leavevo) {

		return service.getleaveApprovalDetailService(leavevo);
	}

	public LeaveRequestVo getLeaveApprovalBD(int sno) {

		return service.getLeaveApproval(sno);
	}

	public ArrayList<LeaveRequestVo> ApprovingLeaveforleaveRequestBD(
			LeaveRequestVo leavevo) {

		return service.ApprovingLeaveforleaveRequestService(leavevo);
	}

	public ArrayList<LeaveRequestVo> searchleave(String searchTerm) {
		// TODO Auto-generated method stub
		 return service.searchleave(searchTerm);
	}

	public ArrayList<LeaveRequestVo> searchleaverequest( String userid,String searchTerm) {
		// TODO Auto-generated method stub
		return service.searchleaverequest(userid,searchTerm);
	}

	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(
			LeaveRequestVo leavevo, String empId,String usertype) {
		return service.getleaveRequestDetailService(leavevo,empId,usertype);
	}

	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(String empId) {
			return service.getleaveRequestDetailsBD(empId);
	}

	public ArrayList<ParentVO> viewExamdetails(ParentVO obj) {
		return service.viewExamdetails(obj);
	}

	public ParentVO getexamname(ParentVO obj) {
		return service.getexamname(obj);
	}

	public ParentVO getStudentDetails(ParentVO obj) {
		return service.getStudentDetails(obj);
	}

	


}
