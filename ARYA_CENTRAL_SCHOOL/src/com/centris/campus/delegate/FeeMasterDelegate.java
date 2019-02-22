package com.centris.campus.delegate;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.addfeedetailsform;
import com.centris.campus.service.FeeMasterService;
import com.centris.campus.serviceImpl.FeeMasterServiceImpl;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;
public class FeeMasterDelegate
{
	FeeMasterService obj_Fee = new FeeMasterServiceImpl();
public String insertFeeDetails(AddFeeVO vo){
		return obj_Fee.insertFeeDetails(vo);
	}
public boolean getnamecount(AddFeeVO vo){
		return obj_Fee.getnamecount(vo);
	}
public ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo) {
		return obj_Fee.getfeedetails(vo);
	}
public AddFeeVO editFeeDetails(AddFeeVO vo) {
		return obj_Fee.editFeeDetails(vo);
	}
public boolean deleteFeeDetails(AddFeeVO vo) {
		return obj_Fee.deleteFeeDetails(vo);
	}
public ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo) {
		return obj_Fee.searchFeeDetails(vo);
	}
public List<ReportMenuVo> getclasslist() {
	return obj_Fee.getclasslistService();
}
public ArrayList<AddFeeVO> feeTypeListBD() {
	return obj_Fee.feeTypeListService();
}
public boolean getFeeTypeCount(AddFeeVO vo) {
	return obj_Fee.getFeeTypeCount(vo);
}
}
