package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;

public interface OnlineFeeReceiptDao {

	List<ParentVO> getParentChildDao(String sectionid, String huserid);

	ArrayList<OnlineFeeReceiptVo> getSearchStudentFeeReceiptDao(OnlineFeeReceiptVo feevo);

}
