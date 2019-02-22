package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.service.FeeCollectionService;
import com.centris.campus.serviceImpl.FeeCollectionServiceImpl;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;

public class FeeCollectionBD {

	
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear){
		
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getFeeCollectionDetails(currentYear);
	}
	
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,String sectionId, String termId, String stuId){
		
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getSearchFeeCollectionDetails(currentYear,classid,sectionId,termId,stuId);
	}
	
	
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails){
		
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getFeeCollectionAmount(FeeCodeDetails);
	}
	
	public String saveFeeCollection(FeeCollectionVo collectionVo){
		
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.saveFeeCollection(collectionVo);
	}
	
	public List<ParentVO> getAllStudentNamesReportBD(String sectionid, String classname, String accyear) {
		
        FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getAllStudentNamesReportService(sectionid,classname,accyear);
	}
	
	public static ArrayList<ClassFeeSetupVo> getAllFees(
			ClassFeeSetupPojo feeSetupPojo) {
		
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			
			return feeCollection.getAllFeesService(feeSetupPojo);
	}

	public static ClassFeeSetupVo getStudentValBd(ClassFeeSetupPojo feeSetupPojo) {
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		return feeCollection.getStudentValService(feeSetupPojo);
	}

	public static ArrayList<ClassFeeSetupVo> getPaymentTypeBD(
			ClassFeeSetupPojo feeSetupPojo) {
		
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			return feeCollection.getPaymentTypeService(feeSetupPojo);
	}

	public static boolean inserfeecollection(ClassFeeSetupForm form1) {
		// TODO Auto-generated method stub
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			return feeCollection.inserfeecollection(form1);	}

	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails) {
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getTranportFeeCollectionAmount(feeCodeDetails);
	}

	public String saveTransportFeeCollection(FeeCollectionVo collectionVo) {
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.saveTransportFeeCollection(collectionVo);
	}

	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails) {
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		return feeCollection.getFeePaidDetails(feeCodeDetails);
	}

	public ArrayList<AddFeeVO> getDefaulterFeeList(String locId,String classId, String divId, String termId, String accId) {
		return new FeeCollectionServiceImpl().getDefaulterFeeList(locId,classId,divId,termId,accId);
	}
	
}
