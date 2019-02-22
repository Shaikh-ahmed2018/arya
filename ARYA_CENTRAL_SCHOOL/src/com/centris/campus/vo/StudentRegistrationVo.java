/**
 * 
 */
package com.centris.campus.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import antlr.collections.List;


/**
 * @author Ashok G
 *
 */
public class StudentRegistrationVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tcno;
	private String studentPrimaryKey;
	private String parentId;
	private String admissionNo;
	private String academicYear;
	private String academicYearId;
	private String studentId;
	private String classStreamId;
	private String classDetailId;
	private String classSectionId;
	private String studentRegNo;
	private String studentFirstName;
	private String studentLastName;
	private String studentFullName;
	private String dateofBirth;
	private String gender;
	private String bloodGroup;
	private String age;
	private String studentPhoto;
	private String dateofJoin;
	private String religion;
	private String scholarShip;
	private String studentStatus;
	private String confidentialStatus;
	private String confidentialComments;
	private String confidentialEntryDate;
	private String confidentialId;
	private String physicalStatus;
	private String identificationMarks;
	private String perfomance;
	private String parentRelationship;
	private String parentName;
	private String parentMobileNo;
	private String fatherName;
	private String motherName;
	private String guardianName;
	private String fatherQualification;
	private String profession;
	private String fatherMobileNo;
	private String motherMobileNo;
	private String studentPhoneno;
	private String studentquotaid;
	private String studentquotaname;
	private String sibilingadminno;
	private String gaurdianName;
	private String previousParentId;
	private String studentSibilingRegNo;
	private String sibilingClassId;
	private String studentSibilingIdInt;
	private String studentSibilingName;
	private String studentrollno;
	private String studentcastename;
	private String studentcasteid;
	private String studentimage;
	private String imageFileName;
	private String quardianoccupation;
	private String physicalchalreason;
	private String houseId;
	private String houseName;
	private String rollNo;
	private String addressstreet1;
	private String addressstreet2;
	private String location;
	private String locationId;
	private String townandcity;
	private String zippostcode;

	private String applicationNo;
	
	private String hostel;
	private String transport;
	private String isconcession;
	private String rte;

	private String transporttypeId;
	private String transporttypeName;
	private String transportlocationId;
	private String transportlocationName;
	private String transportcollectType;

	private String streemname;
	private String classname;
	private String sectionnaem;
	private String streemcode;
	private String classcode;
	private String sectioncode;

	private String emisNo;
	
	private String transferfile;
	private String birthcertificate;

	private String fatheroccupation;
	private String motheroccupation;

	private String concession;
	private String concessionid;
	
	private String hiddenid;
	private String parenthidden;
	private String hiddenstudentid;
	private String sno;
	private String aadharno;
	private String parent_annulIncome;
	private String mothertongue;
	private String medium;
	private String stage_id;
    private String stage_name;
    
    private String route;
    private String schoolLocation;
    private String isWorkingTeacherGuardian;
    private String workingTeacherName;
    private String workingTeacherGuardianId;
    private String specilization;
    private String transferCertificateNo;
    private String identificationMarks1;
    private String casteCategory;
    
    private String fatherPanNo;
    private double fatherAnnualIncome;
    private String fatherPhoto;
    private String guardianOccupation;
    private String guardianPanNo;
    private double guardianAnnualIncome;
    private String guardianQualification;
    private String guardianPhoto;
    private String motherPanNo;
    private double motherAnnualIncome;
    private String motherPhoto;
    private String primaryPersondisables;
    private String presentaddress;
    private String certificate1;
    private String certificate2;
    private String certificate3;
    private String certificate4;
    private String certificate5;
    private String tempregid;
    private String caste;
    private String enquiryId;
    private int count;
    private String specilizationname;
    private String secondLanguage;
    private String thirdLanguage;

    private String locationnid;
    private String accyear;
    private String currentAccyearId;
    private int promotionId;
    private String sectionto;
    private String newsection;
    private String newspecilaization;
    private String promotedId;
    private String firstlang;
    private String secondlang;
    private String thirdlang;
    private String validaty;

    private String actiontaken;
    private String schedualdate;
    private String meetingdate;
    private String meetingon;
    private String recomendedby;
    private String meetingwith;
    private String remark;
    private String studentappraisalid;
    
    private String temp_stuId;
    private String parentNameLabel;
    private String parentMobileLabel;

    private String subscriberNumber;
    private String subscriberId;
    private String subscriberName;
    private String comments;
    private String entryDate;
    private String cancelcomment;
    private String cancelDate;
    private String withheldId;
    private String electionCategory;
    
    private String smsnumber;
	private String emergencynumber;
	private String fatherDepartment;
	private String fatherOfficeAddress;
	private String motherDepartment;
	private String motherOfficeAddress;
	private String guardianDepartment;
	private String guardianOfficeAddress;
	private String guardianDesignation;
	private String fatherDesignation;
	private String motherDesignation;
	private ArrayList<FeeNameVo> feeStatus;
	private String classsection;
	private String latestclass;
	private String compulsorySub;
	private String lastAttended;
	private String promotionDate;
	private String stuckOfRolls;
	private String certificateDateApply;
	private String reasonForTc;
	private String casteCategoryStatus ;
	private String dateofBirthInWords ;
	private String electiveSub;
	private String sortBy;
	private String orderBy;

	private String studoj;

	private String tccode;
	private String dateofjoin;
	private String result;
	private String resultstatus;
	private String feestat;
	private String paidupto;
	private int show_per_page;
	private int startPoint;
	private int totalCount;
	private String concessionType;
	private String occupation;
	private String occupationId;
	private String location_address;
	private String location_phone;
	private String tc_no;
	private String template;
	private String laccyear;
	private String conduct;
	private String lastKindergartenName;
	private String schemeofstudy;
	private String opt_subjects;
	private String isTc;
	private String isMigration;
	private String previousSchool;
	private String landLine;
	private String communication_landline;
	private String father_office_landline;
	private String mother_office_landline;
	private String emergencyNo;
	
	
	
	public String getCommunication_landline() {
		return communication_landline;
	}
	public void setCommunication_landline(String communication_landline) {
		this.communication_landline = communication_landline;
	}
	public String getFather_office_landline() {
		return father_office_landline;
	}
	public void setFather_office_landline(String father_office_landline) {
		this.father_office_landline = father_office_landline;
	}
	public String getMother_office_landline() {
		return mother_office_landline;
	}
	public void setMother_office_landline(String mother_office_landline) {
		this.mother_office_landline = mother_office_landline;
	}
	public String getEmergencyNo() {
		return emergencyNo;
	}
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}
	public String getPreviousSchool() {
		return previousSchool;
	}
	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getIsTc() {
		return isTc;
	}
	public void setIsTc(String isTc) {
		this.isTc = isTc;
	}
	public String getIsMigration() {
		return isMigration;
	}
	public void setIsMigration(String isMigration) {
		this.isMigration = isMigration;
	}
	public String getOpt_subjects() {
		return opt_subjects;
	}
	public void setOpt_subjects(String opt_subjects) {
		this.opt_subjects = opt_subjects;
	}
	public String getSchemeofstudy() {
		return schemeofstudy;
	}
	public void setSchemeofstudy(String schemeofstudy) {
		this.schemeofstudy = schemeofstudy;
	}
	public String getLastKindergartenName() {
		return lastKindergartenName;
	}
	public void setLastKindergartenName(String lastKindergartenName) {
		this.lastKindergartenName = lastKindergartenName;
	}
	public String getLaccyear() {
		return laccyear;
	}
	public void setLaccyear(String laccyear) {
		this.laccyear = laccyear;
	}
	public String getConduct() {
		return conduct;
	}
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getTc_no() {
		return tc_no;
	}
	public void setTc_no(String tc_no) {
		this.tc_no = tc_no;
	}
	public String getLocation_address() {
		return location_address;
	}
	public void setLocation_address(String location_address) {
		this.location_address = location_address;
	}
	public String getLocation_phone() {
		return location_phone;
	}
	public void setLocation_phone(String location_phone) {
		this.location_phone = location_phone;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public String getConcessionType() {
		return concessionType;
	}
	public void setConcessionType(String concessionType) {
		this.concessionType = concessionType;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getShow_per_page() {
		return show_per_page;
	}
	public void setShow_per_page(int show_per_page) {
		this.show_per_page = show_per_page;
	}
	public int getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}
	public String getStudoj() {
		return studoj;
	}
	public void setStudoj(String studoj) {
		this.studoj = studoj;
	}

	public String getPaidupto() {
		return paidupto;
	}
	public void setPaidupto(String paidupto) {
		this.paidupto = paidupto;
	}
	public String getFeestat() {
		return feestat;
	}
	public void setFeestat(String feestat) {
		this.feestat = feestat;
	}
	public String getResultstatus() {
		return resultstatus;
	}
	public void setResultstatus(String resultstatus) {
		this.resultstatus = resultstatus;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDateofjoin() {
		return dateofjoin;
	}
	public void setDateofjoin(String dateofjoin) {
		this.dateofjoin = dateofjoin;
	}
	public String getTccode() {
		return tccode;
	}
	public void setTccode(String tccode) {
		this.tccode = tccode;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getElectiveSub() {
		return electiveSub;
	}
	public void setElectiveSub(String electiveSub) {
		this.electiveSub = electiveSub;
	}
	public String getGetDateofBirthInWords() {
		return getDateofBirthInWords;
	}
	public void setGetDateofBirthInWords(String getDateofBirthInWords) {
		this.getDateofBirthInWords = getDateofBirthInWords;
	}
	public String getDateofBirthInWords() {
		return dateofBirthInWords;
	}
	public void setDateofBirthInWords(String dateofBirthInWords) {
		this.dateofBirthInWords = dateofBirthInWords;
	}
	public String getCasteCategoryStatus() {
		return casteCategoryStatus;
	}
	public void setCasteCategoryStatus(String casteCategoryStatus) {
		this.casteCategoryStatus = casteCategoryStatus;
	}
	public String getReasonForTc() {
		return reasonForTc;
	}
	public void setReasonForTc(String reasonForTc) {
		this.reasonForTc = reasonForTc;
	}
	public String getCertificateDateApply() {
		return certificateDateApply;
	}
	public void setCertificateDateApply(String certificateDateApply) {
		this.certificateDateApply = certificateDateApply;
	}

	private String certificateIssueDate;

	public String getCertificateIssueDate() {
		return certificateIssueDate;
	}
	public void setCertificateIssueDate(String certificateIssueDate) {
		this.certificateIssueDate = certificateIssueDate;
	}
	public String getStuckOfRolls() {
		return stuckOfRolls;
	}
	public void setStuckOfRolls(String stuckOfRolls) {
		this.stuckOfRolls = stuckOfRolls;
	}
	public String getPromotionDate() {
		return promotionDate;
	}
	public void setPromotionDate(String promotionDate) {
		this.promotionDate = promotionDate;
	}
	public String getLastAttended() {
		return lastAttended;
	}
	public void setLastAttended(String lastAttended) {
		this.lastAttended = lastAttended;
	}
	public String getCompulsorySub() {
		return compulsorySub;
	}
	public void setCompulsorySub(String compulsorySub) {
		this.compulsorySub = compulsorySub;
	}
	public String getLatestclass() {
		return latestclass;
	}
	public void setLatestclass(String latestclass) {
		this.latestclass = latestclass;
	}
	public ArrayList<FeeNameVo> getFeeStatus() {
		return feeStatus;
	}
	public void setFeeStatus(ArrayList<FeeNameVo> feeStatus) {
		this.feeStatus = feeStatus;
	}
	public String getClasssection() {
		return classsection;
	}
	public void setClasssection(String classsection) {
		this.classsection = classsection;
	}
	public String getFatherDesignation() {
		return fatherDesignation;
	}
	public void setFatherDesignation(String fatherDesignation) {
		this.fatherDesignation = fatherDesignation;
	}
	public String getMotherDesignation() {
		return motherDesignation;
	}
	public void setMotherDesignation(String motherDesignation) {
		this.motherDesignation = motherDesignation;
	}
	public String getSmsnumber() {
		return smsnumber;
	}
	public void setSmsnumber(String smsnumber) {
		this.smsnumber = smsnumber;
	}
	public String getEmergencynumber() {
		return emergencynumber;
	}
	public void setEmergencynumber(String emergencynumber) {
		this.emergencynumber = emergencynumber;
	}
	public String getFatherDepartment() {
		return fatherDepartment;
	}
	public void setFatherDepartment(String fatherDepartment) {
		this.fatherDepartment = fatherDepartment;
	}
	public String getFatherOfficeAddress() {
		return fatherOfficeAddress;
	}
	public void setFatherOfficeAddress(String fatherOfficeAddress) {
		this.fatherOfficeAddress = fatherOfficeAddress;
	}
	public String getMotherDepartment() {
		return motherDepartment;
	}
	public void setMotherDepartment(String motherDepartment) {
		this.motherDepartment = motherDepartment;
	}
	public String getMotherOfficeAddress() {
		return motherOfficeAddress;
	}
	public void setMotherOfficeAddress(String motherOfficeAddress) {
		this.motherOfficeAddress = motherOfficeAddress;
	}
	public String getGuardianDepartment() {
		return guardianDepartment;
	}
	public void setGuardianDepartment(String guardianDepartment) {
		this.guardianDepartment = guardianDepartment;
	}
	public String getGuardianOfficeAddress() {
		return guardianOfficeAddress;
	}
	public void setGuardianOfficeAddress(String guardianOfficeAddress) {
		this.guardianOfficeAddress = guardianOfficeAddress;
	}
	public String getGuardianDesignation() {
		return guardianDesignation;
	}
	public void setGuardianDesignation(String guardianDesignation) {
		this.guardianDesignation = guardianDesignation;
	}

    

    public String getElectionCategory() {
		return electionCategory;
	}

	public void setElectionCategory(String electionCategory) {
		this.electionCategory = electionCategory;
	}

	public String getStudentappraisalid() {
		return studentappraisalid;
	}

	public void setStudentappraisalid(String studentappraisalid) {
		this.studentappraisalid = studentappraisalid;
	}

	public String getActiontaken() {
		return actiontaken;
	}

	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
	}

	public String getSchedualdate() {
		return schedualdate;
	}

	public void setSchedualdate(String schedualdate) {
		this.schedualdate = schedualdate;
	}

	public String getMeetingdate() {
		return meetingdate;
	}

	public void setMeetingdate(String meetingdate) {
		this.meetingdate = meetingdate;
	}

	public String getMeetingon() {
		return meetingon;
	}

	public void setMeetingon(String meetingon) {
		this.meetingon = meetingon;
	}

	public String getRecomendedby() {
		return recomendedby;
	}

	public void setRecomendedby(String recomendedby) {
		this.recomendedby = recomendedby;
	}

	public String getMeetingwith() {
		return meetingwith;
	}

	public void setMeetingwith(String meetingwith) {
		this.meetingwith = meetingwith;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
    public String getParentMobileLabel() {
		return parentMobileLabel;
	}

	public void setParentMobileLabel(String parentMobileLabel) {
		this.parentMobileLabel = parentMobileLabel;
	}

	public String getParentNameLabel() {
		return parentNameLabel;
	}

	public void setParentNameLabel(String parentNameLabel) {
		this.parentNameLabel = parentNameLabel;
	}

	public String getTemp_stuId() {
		return temp_stuId;
	}

	public void setTemp_stuId(String temp_stuId) {
		this.temp_stuId = temp_stuId;
	}

	public String getRollNo() {

		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getValidaty() {
		return validaty;
	}

	public void setValidaty(String validaty) {
		this.validaty = validaty;
	}

	public String getFirstlang() {
		return firstlang;
	}

	public void setFirstlang(String firstlang) {
		this.firstlang = firstlang;
	}

	public String getSecondlang() {
		return secondlang;
	}

	public void setSecondlang(String secondlang) {
		this.secondlang = secondlang;
	}

	public String getThirdlang() {
		return thirdlang;
	}

	public void setThirdlang(String thirdlang) {
		this.thirdlang = thirdlang;
	}

	public String getPromotedId() {
		return promotedId;
	}

	public void setPromotedId(String promotedId) {
		this.promotedId = promotedId;
	}

	public String getNewsection() {
		return newsection;
	}

	public void setNewsection(String newsection) {
		this.newsection = newsection;
	}

	public String getNewspecilaization() {
		return newspecilaization;
	}

	public void setNewspecilaization(String newspecilaization) {
		this.newspecilaization = newspecilaization;
	}

	public String getSectionto() {
		return sectionto;
	}

	public void setSectionto(String sectionto) {
		this.sectionto = sectionto;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	private String[] studentIdArray;
	private String[] academicyear_fromArray;
	private String[] locationIdArray;
	private String[] admissionNoArray;
	private String[] rollNoArray;
	private String[] class_fromArray;
	private String[] section_fromArray;
	private String[] specilization_fromArray;
	private String[] class_toArray;
	private String[] statusArray;
	private String[] section_toArray;
	private String[] academicyear_toArray;
	private String[] specilization_toArray;
	private String exam_code;
	private String exam_id;
	private String status;
	private String section_id;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSection_id() {
		return section_id;
	}

	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

	private String firstLanguageName;
	private String secondLanguageName;
	private String thirdLanguageName;
	
	
	public String getFirstLanguageName() {
		return firstLanguageName;
	}

	public void setFirstLanguageName(String firstLanguageName) {
		this.firstLanguageName = firstLanguageName;
	}

	public String getSecondLanguageName() {
		return secondLanguageName;
	}

	public void setSecondLanguageName(String secondLanguageName) {
		this.secondLanguageName = secondLanguageName;
	}

	public String getThirdLanguageName() {
		return thirdLanguageName;
	}

	public void setThirdLanguageName(String thirdLanguageName) {
		this.thirdLanguageName = thirdLanguageName;
	}

	public String getExam_id() {
		return exam_id;
	}

	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}

	public String getExam_code() {
		return exam_code;
	}

	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	private String exam_name;
    

    public String[] getStudentIdArray() {
		return studentIdArray;
	}

	public void setStudentIdArray(String[] studentIdArray) {
		this.studentIdArray = studentIdArray;
	}
    
    public String getConfidentialId() {
		return confidentialId;
	}
	public String[] getAcademicyear_fromArray() {
		return academicyear_fromArray;
	}
	public void setConfidentialId(String confidentialId) {
		this.confidentialId = confidentialId;
	}

	public String getConfidentialComments() {
		return confidentialComments;
	}

	public void setConfidentialComments(String confidentialComments) {
		this.confidentialComments = confidentialComments;
	}

	public String getConfidentialEntryDate() {
		return confidentialEntryDate;
	}

	public void setConfidentialEntryDate(String confidentialEntryDate) {
		this.confidentialEntryDate = confidentialEntryDate;
	}

	
	public void setAcademicyear_fromArray(String[] academicyear_fromArray) {
		this.academicyear_fromArray = academicyear_fromArray;
	}

	public String[] getLocationIdArray() {
		return locationIdArray;
	}

	public void setLocationIdArray(String[] locationIdArray) {
		this.locationIdArray = locationIdArray;
	}

	public String[] getAdmissionNoArray() {
		return admissionNoArray;
	}

	public void setAdmissionNoArray(String[] admissionNoArray) {
		this.admissionNoArray = admissionNoArray;
	}

	public String[] getRollNoArray() {
		return rollNoArray;
	}

	public void setRollNoArray(String[] rollNoArray) {
		this.rollNoArray = rollNoArray;
	}

	public String[] getClass_fromArray() {
		return class_fromArray;
	}

	public void setClass_fromArray(String[] class_fromArray) {
		this.class_fromArray = class_fromArray;
	}

	public String[] getSection_fromArray() {
		return section_fromArray;
	}

	public void setSection_fromArray(String[] section_fromArray) {
		this.section_fromArray = section_fromArray;
	}

	public String[] getSpecilization_fromArray() {
		return specilization_fromArray;
	}

	public void setSpecilization_fromArray(String[] specilization_fromArray) {
		this.specilization_fromArray = specilization_fromArray;
	}

	public String[] getClass_toArray() {
		return class_toArray;
	}

	public void setClass_toArray(String[] class_toArray) {
		this.class_toArray = class_toArray;
	}

	public String[] getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}

	public String[] getSection_toArray() {
		return section_toArray;
	}

	public void setSection_toArray(String[] section_toArray) {
		this.section_toArray = section_toArray;
	}

	public String[] getAcademicyear_toArray() {
		return academicyear_toArray;
	}

	public void setAcademicyear_toArray(String[] academicyear_toArray) {
		this.academicyear_toArray = academicyear_toArray;
	}

	public String[] getSpecilization_toArray() {
		return specilization_toArray;
	}

	public void setSpecilization_toArray(String[] specilization_toArray) {
		this.specilization_toArray = specilization_toArray;
	}

	public String getCurrentAccyearId() {
		return currentAccyearId;
	}

	public void setCurrentAccyearId(String currentAccyearId) {
		this.currentAccyearId = currentAccyearId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobileNo() {
		return parentMobileNo;
	}

	public void setParentMobileNo(String parentMobileNo) {
		this.parentMobileNo = parentMobileNo;
	}

	public String getParentRelationship() {
		return parentRelationship;
	}

	public void setParentRelationship(String parentRelationship) {
		this.parentRelationship = parentRelationship;
	}

	public String getConfidentialStatus() {
		return confidentialStatus;
	}

	public void setConfidentialStatus(String confidentialStatus) {
		this.confidentialStatus = confidentialStatus;
	}

	public String getSecondLanguage() {
		return secondLanguage;
	}

	public void setSecondLanguage(String secondLanguage) {
		this.secondLanguage = secondLanguage;
	}

	public String getThirdLanguage() {
		return thirdLanguage;
	}

	public void setThirdLanguage(String thirdLanguage) {
		this.thirdLanguage = thirdLanguage;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSpecilizationname() {
		return specilizationname;
	}

	public void setSpecilizationname(String specilizationname) {
		this.specilizationname = specilizationname;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStudentPrimaryKey() {
		return studentPrimaryKey;
	}

	public void setStudentPrimaryKey(String studentPrimaryKey) {
		this.studentPrimaryKey = studentPrimaryKey;
	}
	public String getStudentFullName() {
		return studentFullName;
	}

	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}

	public String getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getTempregid() {
		return tempregid;
	}

	public void setTempregid(String tempregid) {
		this.tempregid = tempregid;
	}

	public String getCertificate1() {
		return certificate1;
	}

	public void setCertificate1(String certificate1) {
		this.certificate1 = certificate1;
	}

	public String getCertificate2() {
		return certificate2;
	}

	public void setCertificate2(String certificate2) {
		this.certificate2 = certificate2;
	}

	public String getCertificate3() {
		return certificate3;
	}

	public void setCertificate3(String certificate3) {
		this.certificate3 = certificate3;
	}

	public String getCertificate4() {
		return certificate4;
	}

	public void setCertificate4(String certificate4) {
		this.certificate4 = certificate4;
	}

	public String getCertificate5() {
		return certificate5;
	}

	public void setCertificate5(String certificate5) {
		this.certificate5 = certificate5;
	}

	public String getPresentaddress() {
		return presentaddress;
	}

	public void setPresentaddress(String presentaddress) {
		this.presentaddress = presentaddress;
	}

	public String getFatherPanNo() {
		return fatherPanNo;
	}

	public void setFatherPanNo(String fatherPanNo) {
		this.fatherPanNo = fatherPanNo;
	}

	public double getFatherAnnualIncome() {
		return fatherAnnualIncome;
	}

	public void setFatherAnnualIncome(double fatherAnnualIncome) {
		this.fatherAnnualIncome = fatherAnnualIncome;
	}

	public String getFatherPhoto() {
		return fatherPhoto;
	}

	public void setFatherPhoto(String fatherPhoto) {
		this.fatherPhoto = fatherPhoto;
	}

	public String getGuardianOccupation() {
		return guardianOccupation;
	}

	public void setGuardianOccupation(String guardianOccupation) {
		this.guardianOccupation = guardianOccupation;
	}

	public String getGuardianPanNo() {
		return guardianPanNo;
	}

	public void setGuardianPanNo(String guardianPanNo) {
		this.guardianPanNo = guardianPanNo;
	}

	public double getGuardianAnnualIncome() {
		return guardianAnnualIncome;
	}

	public void setGuardianAnnualIncome(double guardianAnnualIncome) {
		this.guardianAnnualIncome = guardianAnnualIncome;
	}

	public String getGuardianQualification() {
		return guardianQualification;
	}

	public void setGuardianQualification(String guardianQualification) {
		this.guardianQualification = guardianQualification;
	}

	public String getGuardianPhoto() {
		return guardianPhoto;
	}

	public void setGuardianPhoto(String guardianPhoto) {
		this.guardianPhoto = guardianPhoto;
	}

	public String getMotherPanNo() {
		return motherPanNo;
	}

	public void setMotherPanNo(String motherPanNo) {
		this.motherPanNo = motherPanNo;
	}

	public double getMotherAnnualIncome() {
		return motherAnnualIncome;
	}

	public void setMotherAnnualIncome(double motherAnnualIncome) {
		this.motherAnnualIncome = motherAnnualIncome;
	}

	public String getMotherPhoto() {
		return motherPhoto;
	}

	public void setMotherPhoto(String motherPhoto) {
		this.motherPhoto = motherPhoto;
	}

	public String getPrimaryPersondisables() {
		return primaryPersondisables;
	}

	public void setPrimaryPersondisables(String primaryPersondisables) {
		this.primaryPersondisables = primaryPersondisables;
	}

	public String getIdentificationMarks1() {
		return identificationMarks1;
	}

	public void setIdentificationMarks1(String identificationMarks1) {
		this.identificationMarks1 = identificationMarks1;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}

	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}

	public String getSpecilization() {
		return specilization;
	}

	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}

	public String getIsWorkingTeacherGuardian() {
		return isWorkingTeacherGuardian;
	}

	public void setIsWorkingTeacherGuardian(String isWorkingTeacherGuardian) {
		this.isWorkingTeacherGuardian = isWorkingTeacherGuardian;
	}

	public String getWorkingTeacherName() {
		return workingTeacherName;
	}

	public void setWorkingTeacherName(String workingTeacherName) {
		this.workingTeacherName = workingTeacherName;
	}

	public String getWorkingTeacherGuardianId() {
		return workingTeacherGuardianId;
	}

	public void setWorkingTeacherGuardianId(String workingTeacherGuardianId) {
		this.workingTeacherGuardianId = workingTeacherGuardianId;
	}

	public String getSchoolLocation() {
		return schoolLocation;
	}

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getStage_name()
	{
		return stage_name;
	}

	public void setStage_name(String stage_name)
	{
		this.stage_name = stage_name;
	}

	public String getStage_id()
	{
		return stage_id;
	}

	public void setStage_id(String stage_id)
	{
		this.stage_id = stage_id;
	}

	public String getMedium()
	{
		return medium;
	}

	public void setMedium(String medium)
	{
		this.medium = medium;
	}

	public String getMothertongue()
	{
		return mothertongue;
	}

	public void setMothertongue(String mothertongue)
	{
		this.mothertongue = mothertongue;
	}

	public String getParent_annulIncome()
	{
		return parent_annulIncome;
	}

	public void setParent_annulIncome(String parent_annulIncome)
	{
		this.parent_annulIncome = parent_annulIncome;
	}

	public String getAadharno()
	{
		return aadharno;
	}

	public void setAadharno(String aadharno)
	{
		this.aadharno = aadharno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getFatheroccupation() {
		return fatheroccupation;
	}

	public void setFatheroccupation(String fatheroccupation) {
		this.fatheroccupation = fatheroccupation;
	}

	public String getMotheroccupation() {
		return motheroccupation;
	}

	public void setMotheroccupation(String motheroccupation) {
		this.motheroccupation = motheroccupation;
	}

	public String getStreemname() {
		return streemname;
	}

	public void setStreemname(String streemname) {
		this.streemname = streemname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSectionnaem() {
		return sectionnaem;
	}

	public void setSectionnaem(String sectionnaem) {
		this.sectionnaem = sectionnaem;
	}

	public String getTransporttypeId() {
		return transporttypeId;
	}

	public void setTransporttypeId(String transporttypeId) {
		this.transporttypeId = transporttypeId;
	}

	public String getTransporttypeName() {
		return transporttypeName;
	}

	public void setTransporttypeName(String transporttypeName) {
		this.transporttypeName = transporttypeName;
	}

	public String getTransportlocationId() {
		return transportlocationId;
	}

	public void setTransportlocationId(String transportlocationId) {
		this.transportlocationId = transportlocationId;
	}

	public String getTransportlocationName() {
		return transportlocationName;
	}

	public void setTransportlocationName(String transportlocationName) {
		this.transportlocationName = transportlocationName;
	}

	public String getTransportcollectType() {
		return transportcollectType;
	}

	public void setTransportcollectType(String transportcollectType) {
		this.transportcollectType = transportcollectType;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public String getIsconcession() {
		return isconcession;
	}

	public void setIsconcession(String isconcession) {
		this.isconcession = isconcession;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getStudentSibilingIdInt() {
		return studentSibilingIdInt;
	}

	public void setStudentSibilingIdInt(String studentSibilingIdInt) {
		this.studentSibilingIdInt = studentSibilingIdInt;
	}

	private String primaryPerson;

	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public String getSibilingClassId() {
		return sibilingClassId;
	}

	public void setSibilingClassId(String sibilingClassId) {
		this.sibilingClassId = sibilingClassId;
	}

	public String getStudentSibilingRegNo() {
		return studentSibilingRegNo;
	}

	public void setStudentSibilingRegNo(String studentSibilingRegNo) {
		this.studentSibilingRegNo = studentSibilingRegNo;
	}

	public String getPreviousParentId() {
		return previousParentId;
	}

	public void setPreviousParentId(String previousParentId) {
		this.previousParentId = previousParentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGaurdianName() {
		return gaurdianName;
	}

	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
	}

	public String getSibilingadminno() {
		return sibilingadminno;
	}

	public void setSibilingadminno(String sibilingadminno) {
		this.sibilingadminno = sibilingadminno;
	}

	public String getStudentquotaid() {
		return studentquotaid;
	}

	public void setStudentquotaid(String studentquotaid) {
		this.studentquotaid = studentquotaid;
	}

	public String getStudentquotaname() {
		return studentquotaname;
	}

	public void setStudentquotaname(String studentquotaname) {
		this.studentquotaname = studentquotaname;
	}

	public String getStudentPhoneno() {
		return studentPhoneno;
	}

	public void setStudentPhoneno(String studentPhoneno) {
		this.studentPhoneno = studentPhoneno;
	}

	public String getMotherMobileNo() {
		return motherMobileNo;
	}

	public void setMotherMobileNo(String motherMobileNo) {
		this.motherMobileNo = motherMobileNo;
	}

	public String getConcession() {
		return concession;
	}

	public void setConcession(String concession) {
		this.concession = concession;
	}

	public String getConcessionid() {
		return concessionid;
	}

	public void setConcessionid(String concessionid) {
		this.concessionid = concessionid;
	}

	private String motherQualification;
	private String motherProfession;
	private String emailId;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String residenceAddress;
	private String SiblingName;
	private String siblingClass;
	private String activation;
	private String previousHistory;
	private FormFile studentImage;
	private String createUser;
	private String createDate;
	private String modifyUser;
	private String modifyDate;
	private String nationality;
	private String remarks;
	private String grade;
	private String promotionStatus;
	private String physicallyChallenged;
	private String category;
	private String studClassId;
	private String studSectionId;

	private String officePhone;
	private String sibilingName;
	private String sibilingClass;
	private String address;
	private String middleName;
	private String categoryVal;
	private String fatherProfession;
	private String searchTerm;
	private String studentAdmissionNo;
	private String officeAddress;
	private String studentIDgenerator;
	private String image;
	private String studentnamelabel;
	private String studentidlabel;
	private String guardianMobileNo;
	private String guardianemailId;
	private String fatheremailId;
	private String motheremailId;
	private String admissionno;
	public String getDateofBirthInWords;


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAdmissionno() {
		return admissionno;
	}

	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}

	public String getGuardianMobileNo() {
		return guardianMobileNo;
	}

	public void setGuardianMobileNo(String guardianMobileNo) {
		this.guardianMobileNo = guardianMobileNo;
	}

	public String getGuardianemailId() {
		return guardianemailId;
	}

	public void setGuardianemailId(String guardianemailId) {
		this.guardianemailId = guardianemailId;
	}

	public String getFatheremailId() {
		return fatheremailId;
	}

	public void setFatheremailId(String fatheremailId) {
		this.fatheremailId = fatheremailId;
	}

	public String getMotheremailId() {
		return motheremailId;
	}

	public void setMotheremailId(String motheremailId) {
		this.motheremailId = motheremailId;
	}

	public String getStudentnamelabel() {
		return studentnamelabel;
	}

	public void setStudentnamelabel(String studentnamelabel) {
		this.studentnamelabel = studentnamelabel;
	}

	public String getStudentidlabel() {
		return studentidlabel;
	}

	public void setStudentidlabel(String studentidlabel) {
		this.studentidlabel = studentidlabel;
	}

	public String getStudentIDgenerator() {
		return studentIDgenerator;
	}

	public void setStudentIDgenerator(String studentIDgenerator) {
		this.studentIDgenerator = studentIDgenerator;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassStreamId() {
		return classStreamId;
	}

	public void setClassStreamId(String classStreamId) {
		this.classStreamId = classStreamId;
	}

	public String getClassDetailId() {
		return classDetailId;
	}

	public void setClassDetailId(String classDetailId) {
		this.classDetailId = classDetailId;
	}

	public String getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(String classSectionId) {
		this.classSectionId = classSectionId;
	}

	public String getStudentRegNo() {
		return studentRegNo;
	}

	public void setStudentRegNo(String studentRegNo) {
		this.studentRegNo = studentRegNo;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}

	public String getDateofJoin() {
		return dateofJoin;
	}

	public void setDateofJoin(String dateofJoin) {
		this.dateofJoin = dateofJoin;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getScholarShip() {
		return scholarShip;
	}

	public void setScholarShip(String scholarShip) {
		this.scholarShip = scholarShip;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}

	public String getIdentificationMarks() {
		return identificationMarks;
	}

	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}

	public String getPerfomance() {
		return perfomance;
	}

	public void setPerfomance(String perfomance) {
		this.perfomance = perfomance;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getFatherQualification() {
		return fatherQualification;
	}

	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getFatherMobileNo() {
		return fatherMobileNo;
	}

	public void setFatherMobileNo(String fatherMobileNo) {
		this.fatherMobileNo = fatherMobileNo;
	}

	public String getMotherQualification() {
		return motherQualification;
	}

	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}

	public String getMotherProfession() {
		return motherProfession;
	}

	public void setMotherProfession(String motherProfession) {
		this.motherProfession = motherProfession;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getSiblingName() {
		return SiblingName;
	}

	public void setSiblingName(String siblingName) {
		SiblingName = siblingName;
	}

	public String getSiblingClass() {
		return siblingClass;
	}

	public void setSiblingClass(String siblingClass) {
		this.siblingClass = siblingClass;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getPreviousHistory() {
		return previousHistory;
	}

	public void setPreviousHistory(String previousHistory) {
		this.previousHistory = previousHistory;
	}

	public FormFile getStudentImage() {
		return studentImage;
	}

	public void setStudentImage(FormFile studentImage) {
		this.studentImage = studentImage;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPromotionStatus() {
		return promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}

	public String getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	public void setPhysicallyChallenged(String physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStudClassId() {
		return studClassId;
	}

	public void setStudClassId(String studClassId) {
		this.studClassId = studClassId;
	}

	public String getStudSectionId() {
		return studSectionId;
	}

	public void setStudSectionId(String studSectionId) {
		this.studSectionId = studSectionId;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getSibilingName() {
		return sibilingName;
	}

	public void setSibilingName(String sibilingName) {
		this.sibilingName = sibilingName;
	}

	public String getSibilingClass() {
		return sibilingClass;
	}

	public void setSibilingClass(String sibilingClass) {
		this.sibilingClass = sibilingClass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCategoryVal() {
		return categoryVal;
	}

	public void setCategoryVal(String categoryVal) {
		this.categoryVal = categoryVal;
	}

	public String getFatherProfession() {
		return fatherProfession;
	}

	public void setFatherProfession(String fatherProfession) {
		this.fatherProfession = fatherProfession;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getStudentrollno() {
		return studentrollno;
	}

	public void setStudentrollno(String studentrollno) {
		this.studentrollno = studentrollno;
	}

	public String getLocationnid() {
		return locationnid;
	}

	public void setLocationnid(String locationnid) {
		this.locationnid = locationnid;
	}

	public String getAccyear() {
		return accyear;
	}

	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}

	public String getStudentcastename() {
		return studentcastename;
	}

	public void setStudentcastename(String studentcastename) {
		this.studentcastename = studentcastename;
	}

	public String getStudentimage() {
		return studentimage;
	}

	public void setStudentimage(String studentimage) {
		this.studentimage = studentimage;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getQuardianoccupation() {
		return quardianoccupation;
	}

	public void setQuardianoccupation(String quardianoccupation) {
		this.quardianoccupation = quardianoccupation;
	}

	public String getAddressstreet1() {
		return addressstreet1;
	}

	public void setAddressstreet1(String addressstreet1) {
		this.addressstreet1 = addressstreet1;
	}

	public String getAddressstreet2() {
		return addressstreet2;
	}

	public void setAddressstreet2(String addressstreet2) {
		this.addressstreet2 = addressstreet2;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTownandcity() {
		return townandcity;
	}

	public void setTownandcity(String townandcity) {
		this.townandcity = townandcity;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getZippostcode() {
		return zippostcode;
	}

	public void setZippostcode(String zippostcode) {
		this.zippostcode = zippostcode;
	}

	public String getStudentSibilingName() {
		return studentSibilingName;
	}

	public void setStudentSibilingName(String studentSibilingName) {
		this.studentSibilingName = studentSibilingName;
	}

	public String getStudentcasteid() {
		return studentcasteid;
	}

	public void setStudentcasteid(String studentcasteid) {
		this.studentcasteid = studentcasteid;
	}

	public String getEmisNo() {
		return emisNo;
	}

	public void setEmisNo(String emisNo) {
		this.emisNo = emisNo;
	}

	public String getStreemcode() {
		return streemcode;
	}

	public void setStreemcode(String streemcode) {
		this.streemcode = streemcode;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getSectioncode() {
		return sectioncode;
	}

	public void setSectioncode(String sectioncode) {
		this.sectioncode = sectioncode;
	}

	public String getPhysicalchalreason() {
		return physicalchalreason;
	}

	public void setPhysicalchalreason(String physicalchalreason) {
		this.physicalchalreason = physicalchalreason;
	}

	public String getHiddenid() {
		return hiddenid;
	}

	public void setHiddenid(String hiddenid) {
		this.hiddenid = hiddenid;
	}

	public String getParenthidden() {
		return parenthidden;
	}

	public void setParenthidden(String parenthidden) {
		this.parenthidden = parenthidden;
	}

	public String getHiddenstudentid() {
		return hiddenstudentid;
	}

	public void setHiddenstudentid(String hiddenstudentid) {
		this.hiddenstudentid = hiddenstudentid;
	}

	public String getTransferfile() {
		return transferfile;
	}

	public void setTransferfile(String transferfile) {
		this.transferfile = transferfile;
	}

	public String getBirthcertificate() {
		return birthcertificate;
	}

	public void setBirthcertificate(String birthcertificate) {
		this.birthcertificate = birthcertificate;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getCancelcomment() {
		return cancelcomment;
	}

	public void setCancelcomment(String cancelcomment) {
		this.cancelcomment = cancelcomment;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getWithheldId() {
		return withheldId;
	}

	public void setWithheldId(String withheldId) {
		this.withheldId = withheldId;
	}

	
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	
	

}