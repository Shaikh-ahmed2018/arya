package com.centris.campus.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.service.ElectionService;
import com.centris.campus.serviceImpl.ElectionServiceImpl;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.ReportMenuVo;

public class ElectionBD {

	static ElectionService service;
	static ElectionService details;
	
	static{
		service = new ElectionServiceImpl();
		details= new ElectionServiceImpl();

	}
	
	public String saveGroupdetails(String accyear, String locid,
			String groupname) {
		return service.saveGroupdetails(accyear,locid,groupname);
	}

	public List<ElectionVo> getGroupdetails() {
		
		return details.getGroupdetails();
	}

	public List<ElectionVo> getAccGrpName(ElectionPojo detailspojo) {
		
		return ElectionServiceImpl.getAccGrpName(detailspojo);

	}

	public String updateGroupdetails(ElectionPojo pojo, String[] schoolHiddenval) {
		return ElectionServiceImpl.updateGroupdetails(pojo,schoolHiddenval);
	}

	public ArrayList<ElectionVo> getGroupNamebyAcademicYear(ElectionPojo pojo) {
		
		return ElectionServiceImpl.getGroupNamebyAcademicYear(pojo);
	}

	public ArrayList<ElectionVo> getAccYearByGroup() {
		return ElectionServiceImpl.getAccYearByGroup();
	}
	
	
/*--------------------election setting------------------*/

	public String saveElectionDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.saveElectionDetails(pojo);
	}

	public ArrayList<ElectionVo> getElectionDetails(String academicYearID, String groupNameID) {
		return ElectionServiceImpl.getElectionDetails(academicYearID,groupNameID);
	}

	public List<ElectionVo> getElectionDetailsFUpdate(ElectionPojo pojo) {
		return ElectionServiceImpl.getElectionDetailsFUpdate(pojo);
	}

	public String UpdateElectionDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.UpdateElectionDetails(pojo);
	}

	public String checkDateOverLap(String date, ElectionPojo pojo) {
	return ElectionServiceImpl.checkDateOverLap(date,pojo);
	}


	
/*--------------------election Category setting------------------*/
	public ArrayList<ElectionVo> getElectionCategoryList(String academicYearID,String groupNameID) {
		return ElectionServiceImpl.getElectionCategoryList(academicYearID,groupNameID);
	}

	public String electionCategoryAddPopUp(ElectionPojo pojo) {
		return ElectionServiceImpl.electionCategoryAddPopUp(pojo);
	}

	public ArrayList<ElectionVo> getElectionCategoryList(String accyear,String groupnameHidden, String titleHidden) {
		return ElectionServiceImpl.getElectionCategoryList(accyear,groupnameHidden,titleHidden);
	}

	public String DeleteElectionCategoryList(String id) {
		return ElectionServiceImpl.DeleteElectionCategoryList(id);
	}

	
/*--------------------Nomination Registration module-4------------------*/
	public ArrayList<ElectionVo> getElectionTitleByGroupName(ElectionPojo pojo) {
		return ElectionServiceImpl.getElectionTitleByGroupName(pojo);
	}

	public ArrayList<ElectionVo> getNominationRegistrationList(String academicYear, String groupName, String electionTitle) {
		return ElectionServiceImpl.getNominationRegistrationList(academicYear,groupName,electionTitle);
	}

	public ArrayList<ElectionVo> getElectionCategoryByTitle(ElectionPojo pojo) {
		return ElectionServiceImpl.getElectionCategoryByTitle(pojo);
	}

	public ArrayList<ElectionVo> getSingleNomineeDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.getSingleNomineeDetails(pojo);
	}

	public String saveNewNomineeDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.saveNewNomineeDetails(pojo);
	}
	
/*--------------------Nomination approval module-5------------------*/

	public ArrayList<ElectionVo> getNominationApprovalList(String academicYear,String groupName, String electionTitle, String electionCategory) {
		return ElectionServiceImpl.getNominationApprovalList( academicYear, groupName,  electionTitle,  electionCategory);
	}

	public String saveApproval(ElectionPojo pojo) {
		return ElectionServiceImpl.saveApproval(pojo);
	}

	public ArrayList<ElectionVo> getUpdateElectionCategory(String categoryId) {
		return ElectionServiceImpl.getUpdateElectionCategory( categoryId);
	}

	public String rejectApproval(ElectionPojo pojo) {
		return ElectionServiceImpl.rejectApproval(pojo);
	}
/*--------------------Booth setting ------------------*/

	public ArrayList<ElectionVo> getStaffNameList() {
		return ElectionServiceImpl.getStaffNameList();
	}

	public String saveBoothDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.saveBoothDetails(pojo);
	}

	public ArrayList<ElectionVo> getBoothDetailsList(ElectionPojo pojo) {
		return ElectionServiceImpl.getBoothDetailsList(pojo);
	}

	public String deleteBoothSelectedRow(String id) {
		return ElectionServiceImpl.deleteBoothSelectedRow(id);
	}

	public ArrayList<ElectionVo> getUpdateBoothSetting(String boothNameId) {
		return ElectionServiceImpl.getUpdateBoothSetting(boothNameId);
	}

	
//polling machine-----------
	public ArrayList<ElectionVo> getBoothNameDropdown(ElectionPojo pojo) {
		return ElectionServiceImpl.getBoothNameDropdown(pojo);
	}

	public String savePollingMachineDetails(ElectionPojo pojo) {
		return ElectionServiceImpl.savePollingMachineDetails(pojo);
	}

	public ArrayList<ElectionVo> getPollingMachineList(ElectionPojo pojo) {
	return ElectionServiceImpl.getPollingMachineList( pojo);
	}

	public String deletePoleSelectedRow(String id) {
	return ElectionServiceImpl.deletePoleSelectedRow( id);
	}

	public ArrayList<ElectionVo> getUpdatePollingDetails(String boothNameId, String status) {
		return ElectionServiceImpl.getUpdatePollingDetails( boothNameId,status);
	}



	public String updatePollingData(ElectionPojo pojo) throws SQLException {
		return ElectionServiceImpl.updatePollingData( pojo);
	}

	public String checkDuplicateStaff(ElectionPojo pojo) {
	 return ElectionServiceImpl.checkDuplicateStaff(pojo);
	}
	
//----------------------voter search list:::module-7-----------------------------
	public ArrayList<ElectionVo> getElectionTitleByAccYear(ElectionPojo pojo) {
		return ElectionServiceImpl.getElectionTitleByAccYear(pojo);
	}

	public ArrayList<ElectionVo> getClassByAccyearTitle(ElectionPojo pojo) {
	return ElectionServiceImpl. getClassByAccyearTitle( pojo);
	}

	public ArrayList<ElectionVo> getSectionByAccyearTitle(ElectionPojo pojo) {
	return ElectionServiceImpl. getSectionByAccyearTitle( pojo);
	}

	public ArrayList<ElectionVo> getVoterSearchList(String academicYear,String className, String electionTitle, String sectionName) {
		return ElectionServiceImpl.getVoterSearchList( academicYear, className,  electionTitle,  sectionName);
	}

	public List<ElectionVo> getvoterDetailsViewUpdate(ElectionPojo pojo) {
		return ElectionServiceImpl.getgetvoterDetailsViewUpdate(pojo);
	}

	public ArrayList<ElectionVo> getVotingMachine(String accid) {
		return ElectionServiceImpl.getVotingMachine(accid);
	}


//	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(String academicYear, String locId, String classId,String sectionName, String suscriberType) {
	
	//}
	

	
	
}
