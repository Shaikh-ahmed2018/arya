package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.ParentLeaveRequestService;
import com.centris.campus.serviceImpl.ParentLeaveRequestServiceImpl;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.RemainderMasterVO;

public class ParentLeaveRequestBD {

	
	ParentLeaveRequestService service = new ParentLeaveRequestServiceImpl();

	public String leaveRequestEntryBD(LeaveRequestVo leavevo) {
		
		return service.leaveRequestEntryService(leavevo);
	}

	public LeaveRequestVo getRequestLeaveBD(int sno) {
		
		return service.getRequestLeaveService(sno);
	}

	public List<RemainderMasterVO> getRemainderlistBD() {
		
		return service. getRemainderlistService();
		
	}
	
}
