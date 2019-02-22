package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.dao.ElectionDao;
import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.service.ElectionService;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.ReportMenuVo;

public class ElectionServiceImpl implements ElectionService {

	static ElectionDao dao;
	static{
		dao = new ElectionDaoImpl();
	}
	@Override
	public String saveGroupdetails(String accyear, String locid,String groupname) {

		return dao.saveGroupdetails(accyear,locid,groupname);
	}
	public List<ElectionVo> getGroupdetails() {
		return dao.getGroupdetails();
	}
	public static List<ElectionVo> getAccGrpName(ElectionPojo detailspojo) {
		// TODO Auto-generated method stub
		return dao.getAccGrpName(detailspojo);
	}
	public static String updateGroupdetails(ElectionPojo pojo,String[] schoolHiddenval) {
		// TODO Auto-generated method stub
		return dao.updateGroupdetails(pojo,schoolHiddenval);
	}

	public static ArrayList<ElectionVo> getGroupNamebyAcademicYear(ElectionPojo pojo) {
		
		return new ElectionDaoImpl().getGroupNamebyAcademicYear(pojo);
	}
	public static ArrayList<ElectionVo> getAccYearByGroup() {
		return new ElectionDaoImpl().getAccYearByGroup();
	}
	
	
	/*Election setting*/
	
	public static String saveElectionDetails(ElectionPojo pojo){
		return new ElectionDaoImpl().saveElectionDetails(pojo);
	}
	public static ArrayList<ElectionVo> getElectionDetails(String academicYearID, String groupNameID) {
		return new ElectionDaoImpl().getElectionDetails(academicYearID,groupNameID);
	}
	public static List<ElectionVo> getElectionDetailsFUpdate(ElectionPojo pojo) {
		return new ElectionDaoImpl().getElectionDetailsFUpdate(pojo);
	}
	public static String UpdateElectionDetails(ElectionPojo pojo) {
		return new ElectionDaoImpl().UpdateElectionDetails(pojo);
	
	}
	public static String checkDateOverLap(String date, ElectionPojo pojo) {
		return new ElectionDaoImpl().checkDateOverLap(date,pojo);
	}
	
	
/*--------------------election Category setting-------------------------------------------------------------------*/	
	public static ArrayList<ElectionVo> getElectionCategoryList(String academicYearID, String groupNameID) {
		return new ElectionDaoImpl().getElectionCategoryList(academicYearID,groupNameID);
	}

	public static String electionCategoryAddPopUp(ElectionPojo pojo) {
		return new  ElectionDaoImpl().electionCategoryAddPopUp(pojo);
	}
	public static ArrayList<ElectionVo> getElectionCategoryList(String accyear,String groupnameHidden, String titleHidden) {
		return new ElectionDaoImpl().getElectionCategoryList(accyear,groupnameHidden,titleHidden);
	}
	public static String DeleteElectionCategoryList(String id) {
		return new ElectionDaoImpl().DeleteElectionCategoryList(id);
	}
	
/*------------------Nomination Registration :::: Module - 4--------------------------------------------------------*/	
	public static ArrayList<ElectionVo> getElectionTitleByGroupName(ElectionPojo pojo) {
		return new ElectionDaoImpl().getElectionTitleByGroupName(pojo);
	}
	public static ArrayList<ElectionVo> getNominationRegistrationList(String academicYear, String groupName, String electionTitle) {
		return new ElectionDaoImpl().getNominationRegistrationList(academicYear,groupName,electionTitle);
	}
	public static ArrayList<ElectionVo> getElectionCategoryByTitle(ElectionPojo pojo) {
		return new ElectionDaoImpl().getElectionCategoryByTitle(pojo);
	}
	public static ArrayList<ElectionVo> getSingleNomineeDetails(ElectionPojo pojo) {
		return new ElectionDaoImpl().getSingleNomineeDetails(pojo);
	}
	public static String saveNewNomineeDetails(ElectionPojo pojo) {
		return new ElectionDaoImpl().saveNewNomineeDetails(pojo);
	}
/*--------------------Nomination approval module-5------------------*/
	
	public static ArrayList<ElectionVo> getNominationApprovalList(String academicYear, String groupName, String electionTitle,String electionCategory) {
		return new ElectionDaoImpl().getNominationApprovalList( academicYear,  groupName,  electionTitle, electionCategory);
	}
	public static String saveApproval(ElectionPojo pojo) {
		return new ElectionDaoImpl().saveApproval(pojo);
	}
	public static ArrayList<ElectionVo> getUpdateElectionCategory(String categoryId) {
		return new ElectionDaoImpl().getUpdateElectionCategory( categoryId);
	}
	public static String rejectApproval(ElectionPojo pojo) {
		return new ElectionDaoImpl().rejectApproval(pojo);
	}
/*--------------------booth setting module-6------------------*/
	public static ArrayList<ElectionVo> getStaffNameList() {
		return new ElectionDaoImpl().getStaffNameList();
	}
	public static String saveBoothDetails(ElectionPojo pojo) {
		return new ElectionDaoImpl().saveBoothDetails( pojo);
	}
	public static ArrayList<ElectionVo> getBoothDetailsList(ElectionPojo pojo) {
		return new ElectionDaoImpl().getBoothDetailsList(pojo);
	}
	public static String deleteBoothSelectedRow(String id) {
		return new ElectionDaoImpl().deleteBoothSelectedRow(id);
	}
	public static ArrayList<ElectionVo> getUpdateBoothSetting(String boothNameId) {
		return new ElectionDaoImpl().getUpdateBoothSetting(boothNameId);
	}
	
//polling machine details---------------
	public static ArrayList<ElectionVo> getBoothNameDropdown(ElectionPojo pojo) {
		return new ElectionDaoImpl().getBoothNameDropdown(pojo);
	}
	public static String savePollingMachineDetails(ElectionPojo pojo) {
		return new ElectionDaoImpl().savePollingMachineDetails( pojo);
	}
	public static ArrayList<ElectionVo> getPollingMachineList(ElectionPojo pojo) {
		return new ElectionDaoImpl().getPollingMachineList( pojo);
	}
	public static String deletePoleSelectedRow(String id) {
		return new ElectionDaoImpl().deletePoleSelectedRow(id);
	}
	public static ArrayList<ElectionVo> getUpdatePollingDetails(String boothNameId, String status) {
		return new ElectionDaoImpl().getUpdatePollingDetails(boothNameId,status);
	}


	public static String updatePollingData(ElectionPojo pojo) throws SQLException {
		return new ElectionDaoImpl().updatePollingData(pojo);
	}
	public static String checkDuplicateStaff(ElectionPojo pojo) {
		return new ElectionDaoImpl().checkDuplicateStaff(pojo);
	}
	
//----------------------voter search list:::module-7-----------------------------
	public static ArrayList<ElectionVo> getElectionTitleByAccYear(ElectionPojo pojo) {
		return new  ElectionDaoImpl().getElectionTitleByAccYear(pojo);
	}
	public static ArrayList<ElectionVo> getClassByAccyearTitle(ElectionPojo pojo) {
		return new ElectionDaoImpl().getClassByAccyearTitle(pojo);
	}
	public static ArrayList<ElectionVo> getSectionByAccyearTitle(ElectionPojo pojo) {
		return new ElectionDaoImpl().getSectionByAccyearTitle(pojo);
	}
	public static ArrayList<ElectionVo> getVoterSearchList(String academicYear,String className, String electionTitle, String sectionName) {
		return new ElectionDaoImpl().getVoterSearchList( academicYear, className,  electionTitle,  sectionName);
	}
	public static List<ElectionVo> getgetvoterDetailsViewUpdate(ElectionPojo pojo) {
		return new ElectionDaoImpl(). getgetvoterDetailsViewUpdate( pojo);
	}
	public static ArrayList<ElectionVo> getVotingMachine(String accid) {
		return new ElectionDaoImpl().getVotingMachine(accid);
	}



}
