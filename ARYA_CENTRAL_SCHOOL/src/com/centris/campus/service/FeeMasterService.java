package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.addfeedetailsform;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TermMasterVo;

public interface FeeMasterService 

{

	public String insertFeeDetails(AddFeeVO vo);

	public boolean getnamecount(AddFeeVO vo);

	public ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo);

	public AddFeeVO editFeeDetails(AddFeeVO vo);

	public boolean deleteFeeDetails(AddFeeVO vo);

	public ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo);
	public List<ReportMenuVo> getclasslistService();

	public ArrayList<AddFeeVO> feeTypeListService();

	public boolean getFeeTypeCount(AddFeeVO vo);
}
