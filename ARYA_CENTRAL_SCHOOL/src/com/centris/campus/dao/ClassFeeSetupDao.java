package com.centris.campus.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.vo.ClassFeeSetupVo;


public interface ClassFeeSetupDao {
	
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear);
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear);
	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo, String location);
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist);
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo);
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo);
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection);
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection);
	
}
