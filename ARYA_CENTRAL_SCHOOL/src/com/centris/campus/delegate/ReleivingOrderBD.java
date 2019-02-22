package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.ReleivingOrderService;
import com.centris.campus.serviceImpl.ReleivingOrderServiceImpl;
import com.centris.campus.vo.ReportMenuVo;

public class ReleivingOrderBD {

	
	ReleivingOrderService service = new ReleivingOrderServiceImpl();
	
	
	
	public List<ReportMenuVo> getTeacherListBD() {
		
		return service.getTeacherListService();
	}
	
	
	
	
	
	
	
	
	
	

}
