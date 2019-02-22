package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public interface TeacherLeaveRequestService {

	List<UserDetailVO> getRequestUserListService(String user);

	ArrayList<TimeTableVo> getTeacherTimetableListService(String userid);

	String leaveRequestEntryService(LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveService(int sno);

	List<LeaveBalanceVo> viewLeaveBalListDetailsService(String userid,String accyear,LeaveRequestForm leaveform);

	ArrayList<ParentVO> getTeacherlistService(ParentVO vo);

	List<LstmsUpcomingMeetingVO> getteachermeetinglistService(
			LstmsUpcomingMeetingVO meetvo);

	ArrayList<LeaveRequestVo> getleaveRequestDetailsService(
			String parentid, LeaveRequestVo leavevo);

	List<RemainderMasterVO> getRemainderlistService();
	
	
	

}
