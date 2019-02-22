package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.OnlineFeePaymentDAO;
import com.centris.campus.daoImpl.OnlineFeePaymentDaoImpl;
import com.centris.campus.service.OnlineFeePaymentService;
import com.centris.campus.vo.OnlinePaymentVo;

public class OnlineFeePaymentServiceImpl implements OnlineFeePaymentService {

	@Override
	public List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(
			String userType, String userCode) {
		OnlineFeePaymentDAO obj=new OnlineFeePaymentDaoImpl();
		return obj.getStudentOnlineFeePaymentDetails(userType,userCode);
	}

}
