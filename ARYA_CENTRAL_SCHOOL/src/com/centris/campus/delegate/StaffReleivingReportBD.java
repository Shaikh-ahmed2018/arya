package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.service.StaffReleivingOrderService;
import com.centris.campus.serviceImpl.StaffReleivingOrderServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public class StaffReleivingReportBD {
	

	public List<AllTeacherDetailsVo> getTeachingListBD(AllTeacherDetailsVo vo) {
		
		
		StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		
		return service.getTeachingListService(vo);
	}

	public List<AllTeacherDetailsVo> getNonTeachingListBD(AllTeacherDetailsVo vo) {
		
    StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		
		return service.getNonTeachingListService(vo);
	}

	public List<UserDetailVO> getUsersList() {
	
		   StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
			
			return service.getUsersListService();
	}

	public List<ReleivingOrderVo> getReleivingDetailsBD(RelievingOrderPojo pojo) {
		
		  StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
			
			return service.getReleivingDetailsService(pojo);
	}

}
