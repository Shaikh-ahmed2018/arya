package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.service.RemainderMasterService;
import com.centris.campus.serviceImpl.RemainderMasterServiceImpl;
import com.centris.campus.vo.RemainderMasterVO;

public class RemainderMasterDelegate 

{

	RemainderMasterService obj_Remain = new RemainderMasterServiceImpl();

	public boolean getnamecount(RemainderMasterVO vo) 
	
	{
		
		return obj_Remain.getnamecount(vo);
	}

	public String addremainderdata(RemainderMasterVO vo) {
		
		return obj_Remain.addremainderdata(vo);
	}

	public ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo) {
		
		return obj_Remain.remainderdetails(vo);
	}

	public RemainderMasterVO editremainderDetails(RemainderMasterVO vo) {
		
		return obj_Remain.editremainderDetails(vo);
	}

	public String deleteRemainderDetails(RemainderMasterVO vo) {
		
		return obj_Remain.deleteRemainderDetails(vo);
	}
	
}
