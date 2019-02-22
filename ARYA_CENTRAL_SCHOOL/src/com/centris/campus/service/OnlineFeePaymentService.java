package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.OnlinePaymentVo;

public interface OnlineFeePaymentService {

	

	List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(String userType, String userCode);

}
