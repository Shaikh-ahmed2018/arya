package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.RemainderMasterVO;

public interface ParentLeaveRequestDao {

	String leaveRequestEntryDao(LeaveRequestVo leavevo);

	String updateleaveRequestEntryDao(LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveDao(int sno);

	ArrayList<RemainderMasterVO> getRemainderlistDao();

}
