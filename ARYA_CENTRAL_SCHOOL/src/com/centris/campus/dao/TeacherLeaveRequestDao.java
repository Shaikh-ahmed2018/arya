package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public interface TeacherLeaveRequestDao {

	List<UserDetailVO> getRequestUserListDao(String user);

	ArrayList<TimeTableVo> getTeacherTimetableListDao(String userid);

	String leaveRequestEntryDao(LeaveRequestVo leavevo);

	String updateleaveRequestEntryDao(LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveDao(int sno);

	List<LeaveBalanceVo> viewLeaveBalListDetailsDao(String userid,String accyear,
			LeaveRequestVo leavevo);

	ArrayList<ParentVO> getTeacherListDao(ParentVO vo);

	ArrayList<LstmsUpcomingMeetingVO> getteachermeetinglistDao(
			LstmsUpcomingMeetingVO meetvo);

	ArrayList<LeaveRequestVo> getleaveRequestDetailsDao(String parentid, LeaveRequestVo leavevo);

	ArrayList<RemainderMasterVO> getRemainderlistDao();

}
