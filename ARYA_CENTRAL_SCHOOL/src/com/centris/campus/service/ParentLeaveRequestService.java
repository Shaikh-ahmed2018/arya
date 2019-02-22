package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.RemainderMasterVO;

public interface ParentLeaveRequestService {

	String leaveRequestEntryService(LeaveRequestVo leavevo);

	LeaveRequestVo getRequestLeaveService(int sno);

	List<RemainderMasterVO> getRemainderlistService();

}
