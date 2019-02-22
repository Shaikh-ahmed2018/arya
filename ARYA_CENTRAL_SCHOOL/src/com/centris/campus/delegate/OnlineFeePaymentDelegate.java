package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.OnlineFeePaymentService;
import com.centris.campus.serviceImpl.OnlineFeePaymentServiceImpl;
import com.centris.campus.vo.OnlinePaymentVo;

public class OnlineFeePaymentDelegate {

	public List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(
			String userType, String userCode) {
		OnlineFeePaymentService obj=new OnlineFeePaymentServiceImpl();	
		return obj.getStudentOnlineFeePaymentDetails(userType,userCode);
	}

}
