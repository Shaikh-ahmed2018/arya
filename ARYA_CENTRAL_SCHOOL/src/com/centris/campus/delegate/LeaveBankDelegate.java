package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.serviceImpl.LeaveBankServiceImpl;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveViewDetailsVo;


public class LeaveBankDelegate {
	
	
	public  ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo){
		
		System.out.println("DesignationDetailsTable delegate is Working");

			return new LeaveBankServiceImpl().leavebanklist(vo);
		
	}
     public String insertLeaveBankDelegate(LeaveBankForm aform){

			return new LeaveBankServiceImpl().insertLeaveBankservice(aform);
	}

	public LeaveBankForm editleavebankdelegate(LeaveBankForm aform){

			return new LeaveBankServiceImpl().editleavebank(aform);
	}
		
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal)

	{
		return new LeaveBankServiceImpl().getSearchDetails(searchTextVal);
		}
	
	public Boolean deleteLeave(String[] deletelist)
	{
		return new LeaveBankServiceImpl().deleteLeave(deletelist);
	}
	
	
	public  ArrayList<LeaveRequestVo> getLeaveAprrovedDetails(LeaveRequestVo leavevo){
		
		System.out.println("DesignationDetailsTable delegate is Working");

			return new LeaveBankServiceImpl().getLeaveAprrovedDetails(leavevo);
		
	}
	public Boolean validAddLeave(String year) {
		return new LeaveBankServiceImpl().validAddLeave(year);
	}
	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {
		return new LeaveBankServiceImpl().getviewLeaveDetails(userId);
	}
	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankServiceImpl().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location);
	}
	public ArrayList<LeaveBankVO> getleaveCatList() {
		return new LeaveBankServiceImpl().getleaveCatList();
	}
	public ArrayList<LeaveBankVO> getleavetypeDetails(String academic_year) {
		return new LeaveBankServiceImpl().getleavetypeDetails(academic_year);
		
	}
	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		return new LeaveBankServiceImpl().editleavetypes(accyear,loc);
	}
	public String checkLeaveType(String accyear, String loc, String category) {
		return new LeaveBankServiceImpl().checkLeaveType(accyear,loc,category);
	}
	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year) {
		return new LeaveBankServiceImpl().getSearchleavetypeDetails(searchTearm,academic_year);
	}
	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankServiceImpl().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
	}
	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		
		return new LeaveBankServiceImpl().getaccLocCatList(accyear,loc);
	}
	public String checkDuplicacy(String accyear, String loc) {
		return new LeaveBankServiceImpl().checkDuplicacy(accyear,loc);
	}
	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		return new LeaveBankServiceImpl().getleavenamesList(academic_year);
	}
	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getleaveusertype(parentid,academic_year);
	}
	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getleaveBalance(parentid,academic_year);
	}
	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getLeaveCalculation(parentid,academic_year);
	}
	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getNewLeaveCalculation(parentid,academic_year);
	}
	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		return new LeaveBankServiceImpl().checkLeaveCount(obj);
	}
	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		return new LeaveBankServiceImpl().checkDateDuplicacy(startDate,toDate,leavetype,parentid);
	}
	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year) {
		return new LeaveBankServiceImpl().getviewNewLeaveDetails(trim,academic_year);
	}

	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location) {
		return new LeaveBankServiceImpl().getLeaveTypes(academicYear,location);
	}

	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		return new LeaveBankServiceImpl().checkMonthleave(academic_year,parentid,fromDate,leavetype);
	}
	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType) {
		return new LeaveBankServiceImpl().getNoOfLeave(academicYear,location,leaveType);
	}

	
	
}
