package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;

public interface FeeCollectionDao {
	
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear);
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails);
	public String saveFeeCollection(FeeCollectionVo collectionVo);
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(
			String currentYear, String classid, String sectionId, String termId, String stuId);
	public List<ParentVO> getAllStudentNamesReportDao(String sectionid, String classname, String accyear);
	public ArrayList<ClassFeeSetupVo> getAllFeesDao(
			ClassFeeSetupPojo feeSetupPojo);
	public ClassFeeSetupVo getStudentValDao(ClassFeeSetupPojo feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getPaymentTypeDAO(ClassFeeSetupPojo feeSetupPojo);
	public int getstudentcount(String studentid);
	public boolean inserfeecollection(ClassFeeSetupVo vo);
	public boolean updatefeecollection(ClassFeeSetupVo vo);
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails);
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo);
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails);
}
