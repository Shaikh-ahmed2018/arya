package com.centris.campus.vo;

import java.util.ArrayList;

/**
 * @author Vedavathi
 *
 */
public class ElectionVo {

	private String accyear;
	private String accid;
	private String schoolName;
	private String schoolId;
	private int sno;
	private String groupName;
	private String groupid;
	private String grp;
	
	private String fromDate;
	private String toDate;
	private String startTime;
	private String endTime;
	private String electionTitle;
	private String electionTitleId;
	private String electionCategoryId;
	private String categoryName;
	private String participateClass[];
	private String genderWise;
	private String houseWise;
	private String priority;
	private String nominLevel[];
	private String status;
	
	private String studentName;
	private String studentId;
	private String className;
	private String classId;
	private String sectionName;
	private String sectionId;
	private String imgUrl;
	private String admissionNo;
	private String rollNumber;
	

	private String staffId;
	private String staffName;
	
	private String boothName;
	private String boothNameId;
	private String staffIncharge;
	private String centralSystem;
	private String centralSystemIp;
	private String voterClass[];
	private String voterClassInt;
	
	private String pollingMachineId;
	private String pollingMachineName;
	private String pollingSystemName;
	private String pollingSystemIp;
	
	private String locationName;
	private ArrayList<StudentRegistrationVo> candidateList;
	private String houseName;
	private String houseId;
	private String isLegal;
	private String voteCount;
	
	public String getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}
	public String getIsLegal() {
		return isLegal;
	}
	public void setIsLegal(String isLegal) {
		this.isLegal = isLegal;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public ArrayList<StudentRegistrationVo> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(ArrayList<StudentRegistrationVo> candidateList) {
		this.candidateList = candidateList;
	}
	public String getPollingMachineName() {
		return pollingMachineName;
	}
	public void setPollingMachineName(String pollingMachineName) {
		this.pollingMachineName = pollingMachineName;
	}
	public String getPollingSystemName() {
		return pollingSystemName;
	}
	public void setPollingSystemName(String pollingSystemName) {
		this.pollingSystemName = pollingSystemName;
	}
	public String getPollingSystemIp() {
		return pollingSystemIp;
	}
	public void setPollingSystemIp(String pollingSystemIp) {
		this.pollingSystemIp = pollingSystemIp;
	}


	
	public String getVoterClassInt() {
		return voterClassInt;
	}
	public void setVoterClassInt(String voterClassInt) {
		this.voterClassInt = voterClassInt;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public String getBoothNameId() {
		return boothNameId;
	}
	public void setBoothNameId(String boothNameId) {
		this.boothNameId = boothNameId;
	}
	public String getStaffIncharge() {
		return staffIncharge;
	}
	public void setStaffIncharge(String staffIncharge) {
		this.staffIncharge = staffIncharge;
	}
	public String getCentralSystem() {
		return centralSystem;
	}
	public void setCentralSystem(String centralSystem) {
		this.centralSystem = centralSystem;
	}
	public String getCentralSystemIp() {
		return centralSystemIp;
	}
	public void setCentralSystemIp(String centralSystemIp) {
		this.centralSystemIp = centralSystemIp;
	}
	public String[] getVoterClass() {
		return voterClass;
	}
	public void setVoterClass(String[] voterClass) {
		this.voterClass = voterClass;
	}





	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	
	
	private String electionCategory;
	
	
	public String getElectionCategory() {
		return electionCategory;
	}
	public void setElectionCategory(String electionCategory) {
		this.electionCategory = electionCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getElectionCategoryId() {
		return electionCategoryId;
	}
	public void setElectionCategoryId(String electionCategoryId) {
		this.electionCategoryId = electionCategoryId;
	}
	public String[] getNominLevel() {
		return nominLevel;
	}
	public void setNominLevel(String[] nominLevel) {
		this.nominLevel = nominLevel;
	}
	private String nominFor;
	private String classWiseName;
	private String classWiseId;
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String[] getParticipateClass() {
		return participateClass;
	}
	public void setParticipateClass(String[] participateClass) {
		this.participateClass = participateClass;
	}
	public String getGenderWise() {
		return genderWise;
	}
	public void setGenderWise(String genderWise) {
		this.genderWise = genderWise;
	}
	public String getHouseWise() {
		return houseWise;
	}
	public void setHouseWise(String houseWise) {
		this.houseWise = houseWise;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getNominFor() {
		return nominFor;
	}
	public void setNominFor(String nominFor) {
		this.nominFor = nominFor;
	}
	public String getClassWiseName() {
		return classWiseName;
	}
	public void setClassWiseName(String classWiseName) {
		this.classWiseName = classWiseName;
	}
	public String getClassWiseId() {
		return classWiseId;
	}
	public void setClassWiseId(String classWiseId) {
		this.classWiseId = classWiseId;
	}

	
	
	public String getElectionTitleId() {
		return electionTitleId;
	}
	public void setElectionTitleId(String electionTitleId) {
		this.electionTitleId = electionTitleId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getElectionTitle() {
		return electionTitle;
	}
	public void setElectionTitle(String electionTitle) {
		this.electionTitle = electionTitle;
	}
	
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAccid() {
		return accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	/*public void setVoterClass(String[] classNameList) {
		// TODO Auto-generated method stub
		
	}*/
	public String getPollingMachineId() {
		return pollingMachineId;
	}
	public void setPollingMachineId(String pollingMachineId) {
		this.pollingMachineId = pollingMachineId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
