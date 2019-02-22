package com.centris.campus.dao;


import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;

public interface FeeMasterDAO 

{

	String insertFeeDetails(AddFeeVO vo);

	int getFeeNameCheckDao(String feename);

	boolean getnamecount(AddFeeVO vo);

	ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo);

	AddFeeVO editFeeDetails(AddFeeVO vo);

	boolean deleteFeeDetails(AddFeeVO vo);

	ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo);

	String updateFeeDetails(AddFeeVO vo);
	List<ReportMenuVo> getclasslistDao();

	ArrayList<AddFeeVO> feeTypeListDao();

	boolean getFeeTypeCount(AddFeeVO vo);
}
