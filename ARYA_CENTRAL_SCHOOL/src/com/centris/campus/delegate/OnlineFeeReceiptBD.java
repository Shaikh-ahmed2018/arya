package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.service.OnlineFeeReceiptService;
import com.centris.campus.serviceImpl.OnlineFeeReceiptServiceImpl;
import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;


public class OnlineFeeReceiptBD {

	 OnlineFeeReceiptService service = new OnlineFeeReceiptServiceImpl();

	public List<ParentVO> getParentChildBD(String sectionid, String huserid) {
		
		return service.getParentChildService(sectionid,huserid);
	}

	public ArrayList<OnlineFeeReceiptVo> getSearchStudentFeeReceiptBD(
			OnlineFeeReceiptVo feevo) {
		
		return service. getSearchStudentFeeReceiptService(feevo);
	}

	
}
