package com.centris.campus.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class StudentRegistrationForm extends ActionForm {

	/*public StudentRegistrationForm() {

	}*/

	private String streamhiddenId;
	private String studentrollno;
	private String studentFirstName;
	private String studentLastName;
	private String dateofBirth;
	private String age;
	private String parenthiddenId;

	private String gender;
	private String physicallyChallenged;
	private String bloodGroup;
	private String identificationMarks;
	

	private String nationality;
	private int academicYearId;
	private String academicYear;
	private String dateofJoin;
	private String category;

	private String studClassId;
	private String studSectionId;
	private String studentquotaid;
	private String studentquotaname;
	private String grade;

	
	private String scholarShip;
	private String promotionStatus;
	private String previousHistory;
	private String remarks;

	private String studentimage;
	private String imageFileName;
	private String studentSibilingRegNo;
	private String sibilingClassId;
	private String studentSibilingIdInt;

	private String sibilingClass;
	private String sibilingadminno;
	private String primaryPerson;
	
	private String parentId;
	private String studentId;
	private String studentRegNo;
	private String studentAdmissionNo;
	private String studentPhoto;
	private String previousParentId;

	private String categoryVal;
	private String classVal;
	private String sibilingName;
	private String sectionVal;

	private String addressstreet1;
	private String addressstreet2;
	private String location;
	private String townandcity;
	private String zippostcode;
	private String state;
	private String country;

	private String createUser;
	private String createDate;
	private String modifyUser;
	private String modifyDate;
	private String studentIDgenerator;
	private FormFile studentImage;
	private String studentstatus;
	private String enquiryId;
	private String applicationNo;
	private String hostel;
	private String transport;
	private String cencession;
	private String rte;

	private String transcategory;
	private String translocation;
	private String emisNo;
	private String address;

	private String physicalchalreason;
	private String quardianoccupation;

	private String birthfile;
	
	private String previousImage;
	private String previousBirthCer;
	private String previousTransferCer;
	private String aadharno;
	private String parent_annulIncome;
	private String mothertongue;
	private String medium;
	private String stage_id;
	private String route;
	
	private String fatherName;
	private String fatherMobileNo;
	private String fatherOccupation;
	private String fatherPanNo;
	private double fatherAnnualIncome;
	private String fatherEmail;
	private String fatherQualification;
	
	private String motherName;
	private String motherMobileNo;
	private String motherOccupation;
	private String motherPanNo;
	private double motherAnnualIncome;
	private String motherEmail;
	private String motherQualification;
	
	private String guardianName;
	private String guardianMobileNo;
	private String guardianOccupation;
	private String guardianPanNo;
	private double guardianAnnualIncome;
	private String guardianEmail;
	private String guardianQualification;
	
	private String fatherPhotoUrl;
	private String motherPhotoUrl;
	private String guardianPhotoUrl;
	private FormFile fatherPhoto;
	private FormFile motherPhoto;
	private FormFile guardianPhoto;
	
	private String certificate1Url;
	private String certificate2Url;
	private String certificate3Url;
	private String certificate4Url;
	private String certificate5Url;
	
	private FormFile certificate1;
	private FormFile certificate2;
	private FormFile certificate3;
	private FormFile certificate4;
	private FormFile certificate5;
	private List<FormFile> photoUpload;
	private String specilization;
	private String transferCertificateNo;
	private String isWorkingTeacherGuardian;
	private String workingTeacherGuardianId;
	private String religion;	
	private String caste;
	private String casteCategory;
	private String guardianRelationship;
	private String schoolLocation;
	private String presentaddress;
	private String workingTeacherName;
	private String identificationMarks1;
	private String previousFatherImage;
	private String previousMotherImage;
	private String previousGuardImage;
	private String tempRegId;
	private String locationId;
	private String firstlang;
	private String secondlang;
	private String thirdlang;
	private String studHouseId;
	private String previousSchool;
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
	private String accyear;
	private ArrayList<String> photoRealPath;
	private ArrayList<String> photoAdmissionNoArray;
	private String landLine;
	private String classofJoinId;
	
	
	
	public String getClassofJoinId() {
		return classofJoinId;
	}
	public void setClassofJoinId(String classofJoinId) {
		this.classofJoinId = classofJoinId;
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
	public List<FormFile> getPhotoUpload() {
		return photoUpload;
	}
	public void setPhotoUpload(List<FormFile> photoUpload) {
		this.photoUpload = photoUpload;
	}
	public ArrayList<String> getPhotoAdmissionNoArray() {
		return photoAdmissionNoArray;
	}
	public void setPhotoAdmissionNoArray(ArrayList<String> photoAdmissionNoArray) {
		this.photoAdmissionNoArray = photoAdmissionNoArray;
	}
	public ArrayList<String> getPhotoRealPath() {
		return photoRealPath;
	}
	public void setPhotoRealPath(ArrayList<String> photoRealPath) {
		this.photoRealPath = photoRealPath;
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

	

	public String getStudHouseId() {
		return studHouseId;
	}

	public void setStudHouseId(String studHouseId) {
		this.studHouseId = studHouseId;
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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTempRegId() {
		return tempRegId;
	}

	public void setTempRegId(String tempRegId) {
		this.tempRegId = tempRegId;
	}

	public String getPreviousMotherImage() {
		return previousMotherImage;
	}

	public void setPreviousMotherImage(String previousMotherImage) {
		this.previousMotherImage = previousMotherImage;
	}

	public String getPreviousGuardImage() {
		return previousGuardImage;
	}

	public void setPreviousGuardImage(String previousGuardImage) {
		this.previousGuardImage = previousGuardImage;
	}

	public String getPreviousFatherImage() {
		return previousFatherImage;
	}

	public void setPreviousFatherImage(String previousFatherImage) {
		this.previousFatherImage = previousFatherImage;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getIdentificationMarks1() {
		return identificationMarks1;
	}

	public void setIdentificationMarks1(String identificationMarks1) {
		this.identificationMarks1 = identificationMarks1;
	}

	public String getWorkingTeacherName() {
		return workingTeacherName;
	}

	public void setWorkingTeacherName(String workingTeacherName) {
		this.workingTeacherName = workingTeacherName;
	}

	public String getPresentaddress() {
		return presentaddress;
	}

	public void setPresentaddress(String presentaddress) {
		this.presentaddress = presentaddress;
	}

	public String getFatherMobileNo() {
		return fatherMobileNo;
	}

	public void setFatherMobileNo(String fatherMobileNo) {
		this.fatherMobileNo = fatherMobileNo;
	}

	public String getMotherMobileNo() {
		return motherMobileNo;
	}

	public void setMotherMobileNo(String motherMobileNo) {
		this.motherMobileNo = motherMobileNo;
	}

	public String getGuardianMobileNo() {
		return guardianMobileNo;
	}

	public void setGuardianMobileNo(String guardianMobileNo) {
		this.guardianMobileNo = guardianMobileNo;
	}

	
	

	public String getCertificate1Url() {
		return certificate1Url;
	}

	public void setCertificate1Url(String certificate1Url) {
		this.certificate1Url = certificate1Url;
	}

	public String getCertificate2Url() {
		return certificate2Url;
	}

	public void setCertificate2Url(String certificate2Url) {
		this.certificate2Url = certificate2Url;
	}

	public String getCertificate3Url() {
		return certificate3Url;
	}

	public void setCertificate3Url(String certificate3Url) {
		this.certificate3Url = certificate3Url;
	}

	public String getCertificate4Url() {
		return certificate4Url;
	}

	public void setCertificate4Url(String certificate4Url) {
		this.certificate4Url = certificate4Url;
	}

	public String getCertificate5Url() {
		return certificate5Url;
	}

	public void setCertificate5Url(String certificate5Url) {
		this.certificate5Url = certificate5Url;
	}

	public FormFile getCertificate1() {
		return certificate1;
	}

	public void setCertificate1(FormFile certificate1) {
		this.certificate1 = certificate1;
	}

	public FormFile getCertificate2() {
		return certificate2;
	}

	public void setCertificate2(FormFile certificate2) {
		this.certificate2 = certificate2;
	}

	public FormFile getCertificate3() {
		return certificate3;
	}

	public void setCertificate3(FormFile certificate3) {
		this.certificate3 = certificate3;
	}

	public FormFile getCertificate4() {
		return certificate4;
	}

	public void setCertificate4(FormFile certificate4) {
		this.certificate4 = certificate4;
	}

	public FormFile getCertificate5() {
		return certificate5;
	}

	public void setCertificate5(FormFile certificate5) {
		this.certificate5 = certificate5;
	}

	public String getSpecilization() {
		return specilization;
	}

	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}

	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}

	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}


	public String getIsWorkingTeacherGuardian() {
		return isWorkingTeacherGuardian;
	}

	public void setIsWorkingTeacherGuardian(String isWorkingTeacherGuardian) {
		this.isWorkingTeacherGuardian = isWorkingTeacherGuardian;
	}

	public String getWorkingTeacherGuardianId() {
		return workingTeacherGuardianId;
	}

	public void setWorkingTeacherGuardianId(String workingTeacherGuardianId) {
		this.workingTeacherGuardianId = workingTeacherGuardianId;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getGuardianRelationship() {
		return guardianRelationship;
	}

	public void setGuardianRelationship(String guardianRelationship) {
		this.guardianRelationship = guardianRelationship;
	}

	public String getSchoolLocation() {
		return schoolLocation;
	}

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	

	public String getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
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

	public String getFatherEmail() {
		return fatherEmail;
	}

	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}

	public String getFatherQualification() {
		return fatherQualification;
	}

	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}



	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
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

	public String getMotherEmail() {
		return motherEmail;
	}

	public void setMotherEmail(String motherEmail) {
		this.motherEmail = motherEmail;
	}

	public String getMotherQualification() {
		return motherQualification;
	}

	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
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

	public String getGuardianEmail() {
		return guardianEmail;
	}

	public void setGuardianEmail(String guardianEmail) {
		this.guardianEmail = guardianEmail;
	}

	public String getGuardianQualification() {
		return guardianQualification;
	}

	public void setGuardianQualification(String guardianQualification) {
		this.guardianQualification = guardianQualification;
	}

	public String getFatherPhotoUrl() {
		return fatherPhotoUrl;
	}

	public void setFatherPhotoUrl(String fatherPhotoUrl) {
		this.fatherPhotoUrl = fatherPhotoUrl;
	}

	public String getMotherPhotoUrl() {
		return motherPhotoUrl;
	}

	public void setMotherPhotoUrl(String motherPhotoUrl) {
		this.motherPhotoUrl = motherPhotoUrl;
	}

	public String getGuardianPhotoUrl() {
		return guardianPhotoUrl;
	}

	public void setGuardianPhotoUrl(String guardianPhotoUrl) {
		this.guardianPhotoUrl = guardianPhotoUrl;
	}

	public FormFile getFatherPhoto() {
		return fatherPhoto;
	}

	public void setFatherPhoto(FormFile fatherPhoto) {
		this.fatherPhoto = fatherPhoto;
	}

	public FormFile getMotherPhoto() {
		return motherPhoto;
	}

	public void setMotherPhoto(FormFile motherPhoto) {
		this.motherPhoto = motherPhoto;
	}

	public FormFile getGuardianPhoto() {
		return guardianPhoto;
	}

	public void setGuardianPhoto(FormFile guardianPhoto) {
		this.guardianPhoto = guardianPhoto;
	}

	

	

	

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
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

	public String getParenthiddenId() {
		return parenthiddenId;
	}

	public void setParenthiddenId(String parenthiddenId) {
		this.parenthiddenId = parenthiddenId;
	}

	public String getPreviousImage() {
		return previousImage;
	}

	public void setPreviousImage(String previousImage) {
		this.previousImage = previousImage;
	}

	public String getPreviousBirthCer() {
		return previousBirthCer;
	}

	public void setPreviousBirthCer(String previousBirthCer) {
		this.previousBirthCer = previousBirthCer;
	}

	public String getPreviousTransferCer() {
		return previousTransferCer;
	}

	public void setPreviousTransferCer(String previousTransferCer) {
		this.previousTransferCer = previousTransferCer;
	}

	public String getBirthfile() {
		return birthfile;
	}

	public void setBirthfile(String birthfile) {
		this.birthfile = birthfile;
	}

	public String getTransferfile() {
		return transferfile;
	}

	public void setTransferfile(String transferfile) {
		this.transferfile = transferfile;
	}

	
	public String getStreamhiddenId() {
		return streamhiddenId;
	}

	public void setStreamhiddenId(String streamhiddenId) {
		this.streamhiddenId = streamhiddenId;
	}


	private String transferfile;

	private FormFile birthFile;

	public FormFile getBirthFile() {
		return birthFile;
	}

	public void setBirthFile(FormFile birthFile) {
		this.birthFile = birthFile;
	}

	public FormFile getTransferFile() {
		return transferFile;
	}

	public void setTransferFile(FormFile transferFile) {
		this.transferFile = transferFile;
	}

	private FormFile transferFile;

	public String getTranscategory() {
		return transcategory;
	}

	public void setTranscategory(String transcategory) {
		this.transcategory = transcategory;
	}

	public String getTranslocation() {
		return translocation;
	}

	public void setTranslocation(String translocation) {
		this.translocation = translocation;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public String getCencession() {
		return cencession;
	}

	public void setCencession(String cencession) {
		this.cencession = cencession;
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

	public String getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	public FormFile getStudentImage() {
		return studentImage;
	}

	public void setStudentImage(FormFile studentImage) {
		this.studentImage = studentImage;
	}

	public String getStudentrollno() {
		return studentrollno;
	}

	public void setStudentrollno(String studentrollno) {
		
		System.out.println("role no in form :::"+studentrollno);
		
		this.studentrollno = studentrollno;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	public void setPhysicallyChallenged(String physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getIdentificationMarks() {
		return identificationMarks;
	}

	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}

	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getDateofJoin() {
		return dateofJoin;
	}

	public void setDateofJoin(String dateofJoin) {
		this.dateofJoin = dateofJoin;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public String getScholarShip() {
		return scholarShip;         
	}

	public void setScholarShip(String scholarShip) {
		this.scholarShip = scholarShip;
	}

	public String getPromotionStatus() {
		return promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}

	public String getPreviousHistory() {
		return previousHistory;
	}

	public void setPreviousHistory(String previousHistory) {
		this.previousHistory = previousHistory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getStudentSibilingRegNo() {
		return studentSibilingRegNo;
	}

	public void setStudentSibilingRegNo(String studentSibilingRegNo) {
		this.studentSibilingRegNo = studentSibilingRegNo;
	}

	public String getSibilingClassId() {
		return sibilingClassId;
	}

	public void setSibilingClassId(String sibilingClassId) {
		this.sibilingClassId = sibilingClassId;
	}

	public String getStudentSibilingIdInt() {
		return studentSibilingIdInt;
	}

	public void setStudentSibilingIdInt(String studentSibilingIdInt) {
		this.studentSibilingIdInt = studentSibilingIdInt;
	}

	public String getSibilingClass() {
		return sibilingClass;
	}

	public void setSibilingClass(String sibilingClass) {
		this.sibilingClass = sibilingClass;
	}

	public String getSibilingadminno() {
		return sibilingadminno;
	}

	public void setSibilingadminno(String sibilingadminno) {
		this.sibilingadminno = sibilingadminno;
	}

	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentRegNo() {
		return studentRegNo;
	}

	public void setStudentRegNo(String studentRegNo) {
		this.studentRegNo = studentRegNo;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}

	public String getPreviousParentId() {
		return previousParentId;
	}

	public void setPreviousParentId(String previousParentId) {
		this.previousParentId = previousParentId;
	}

	public String getCategoryVal() {
		return categoryVal;
	}

	public void setCategoryVal(String categoryVal) {
		this.categoryVal = categoryVal;
	}

	public String getClassVal() {
		return classVal;
	}

	public void setClassVal(String classVal) {
		this.classVal = classVal;
	}

	public String getSibilingName() {
		return sibilingName;
	}

	public void setSibilingName(String sibilingName) {
		this.sibilingName = sibilingName;
	}

	public String getSectionVal() {
		return sectionVal;
	}

	public void setSectionVal(String sectionVal) {
		this.sectionVal = sectionVal;
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

	public String getTownandcity() {
		return townandcity;
	}

	public void setTownandcity(String townandcity) {
		this.townandcity = townandcity;
	}

	public String getZippostcode() {
		return zippostcode;
	}

	public void setZippostcode(String zippostcode) {
		this.zippostcode = zippostcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getStudentIDgenerator() {
		return studentIDgenerator;
	}

	public void setStudentIDgenerator(String studentIDgenerator) {
		this.studentIDgenerator = studentIDgenerator;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStudentstatus() {
		return studentstatus;
	}

	public void setStudentstatus(String studentstatus) {
		this.studentstatus = studentstatus;
	}

	public String getEmisNo() {
		return emisNo;
	}

	public void setEmisNo(String emisNo) {
		this.emisNo = emisNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhysicalchalreason() {
		return physicalchalreason;
	}

	public void setPhysicalchalreason(String physicalchalreason) {
		this.physicalchalreason = physicalchalreason;
	}

	

	

	public String getQuardianoccupation() {
		return quardianoccupation;
	}

	public void setQuardianoccupation(String quardianoccupation) {
		this.quardianoccupation = quardianoccupation;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}

}