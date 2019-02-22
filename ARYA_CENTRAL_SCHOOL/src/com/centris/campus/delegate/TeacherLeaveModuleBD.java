package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.service.TeacherLeaveRequestService;
import com.centris.campus.serviceImpl.TeacherLeaveRequestServiceImpl;
import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public class TeacherLeaveModuleBD {
	
	
	
	TeacherLeaveRequestService service = new TeacherLeaveRequestServiceImpl();
	
	
	

	public List<UserDetailVO> getRequestUserListDetails(String user ) {
		
		return service.getRequestUserListService(user);
	}



	public ArrayList<TimeTableVo> getTeacherTimetable(String userid) {
	
		return service.getTeacherTimetableListService(userid);
	}



	public String leaveRequestEntryBD(LeaveRequestVo leavevo) {
		
		return service.leaveRequestEntryService(leavevo);
	}



	public LeaveRequestVo getRequestLeaveBD(int sno) {
	
		return service.getRequestLeaveService(sno);
	}



	public List<LeaveBalanceVo> viewLeaveBalListDetails(String userid,String accyear,
			LeaveRequestForm leaveform) {
		
		return service.viewLeaveBalListDetailsService(userid,accyear,leaveform);
	}



	public ArrayList<ParentVO> getTeacherlist(ParentVO vo) {
	
		return service.getTeacherlistService(vo);
	}



	public List<LstmsUpcomingMeetingVO> getteachermeetinglistBD(
			LstmsUpcomingMeetingVO meetvo) {
	
		return service.getteachermeetinglistService(meetvo);
	}



	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(
			String parentid, LeaveRequestVo leavevo) {
		
		return service. getleaveRequestDetailsService(parentid,leavevo);
	}



	public List<RemainderMasterVO> getRemainderlistBD() {
		
		return service.getRemainderlistService();
	}

	
	
	
	
	
	
	
	
	
	
}
