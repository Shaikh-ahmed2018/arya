package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.vo.ClassFeeSetupVo;


public interface ClassFeeSetupService {
	
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear);
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear);
	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo, String location);
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist);
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo);
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupList);
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo);
	public String getSpecialization(String classId, String accYear);
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo);
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo);
	
	
}
