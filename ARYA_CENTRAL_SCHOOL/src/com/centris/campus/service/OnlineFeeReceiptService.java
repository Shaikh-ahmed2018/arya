package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;

public interface OnlineFeeReceiptService {

	List<ParentVO> getParentChildService(String sectionid, String huserid);

	ArrayList<OnlineFeeReceiptVo> getSearchStudentFeeReceiptService(
			OnlineFeeReceiptVo feevo);

}
