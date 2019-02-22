package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;

public interface FeeCollectionService {
	
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear);
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails);
	public String saveFeeCollection(FeeCollectionVo collectionVo);
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,String sectionId, String termId, String stuId);
	public List<ParentVO> getAllStudentNamesReportService(String sectionid, String classname, String accyear);
	public ArrayList<ClassFeeSetupVo> getAllFeesService(ClassFeeSetupPojo feeSetupPojo);
	public ClassFeeSetupVo getStudentValService(ClassFeeSetupPojo feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getPaymentTypeService(ClassFeeSetupPojo feeSetupPojo);
	public boolean inserfeecollection(ClassFeeSetupForm form1);
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails);
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo);
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails);


}
