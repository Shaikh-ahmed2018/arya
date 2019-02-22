package com.centris.campus.vo;

import java.util.List;
import java.util.Map;

public class ReportMenuVo {
	
	private String accyearId;
	private String accyearname;
	private String streamId;
	private String streamname;
	private String classId;
	private String classname;
	private String sectionId;
	private String sectionname;
	private String class_and_section;
	
	private String fromdate;
	private String todate;
	private String teachertype;
	
	private String locationId;
	private String locationName;
	
	private String studentId;
	private String admissionNo;
	private String teachertId;
	private String teacherName;
	private String accYear;
	private String value;
	private String searchTerm;
	private String studentnamelabel;
	private String studentidlabel;
	private String description;
	private String attendenceDate;
	private String attendenceStatus;
	private String teachername;
	
	private String startDate;
	private String endDate;
	private double total_amt;
	private String status;
	private String termname;
	private String termId;
	private String termStatus;
	private String termStatusId;
	private int sno;
	
	private int count;
	private int rollNo;
	private String dob;
	private String occupationId;
	private String fatherName;
	private String motherName;
	private String guardianName;
	private String occupation;
	private String religion;
	private String caste;
	private String casteCategory;
	private String mobileno;
	private String fatherMobileNo;
	private String student_mothermobileno_var;
	private String guardianMobileNo;
	private String address;
	private String student_mothername_var;
	private String total_strength;
	private String student_gender_var;
	private String residenceNo;
	private String location;
	private String accyear;
	private String section;
	private String studentRollNo;
	private String fatherResidenceNo;
	private String motherResidenceNo;
	private String specializationName;
	private String specializationId;
	private String busStageName;
	private String stageAmount;
	private String secondLanguage;
	private String thirdLanguage;
	private String subjectName;
	private int girls;
	private int boys;
	private String dateOfJoining;
	private String tempAdmnNo;
	private String promotionStatus;
	private String gender;
	private int totalStudentsInDiv;
	private int totalStudentsInCls;
	private int totalStudentsInStrm;
	private String accessionNo;
	private String fatherOccupation;
	private String motherOccupation;
	private String fatherIncome;
	private String motherIncome;
	private String fatherOfficeAddress;
	private String motherOfficeAddress;
	private String nationality;
	private String motherTounge;
	private String permanentaddress;
	private String aadharNo;
	private String term1;
	private String term2;
	private String finalterms;
	private String cbse;
	private String subjectid;
	private float scored_marks;
	private float notebook_marks;
	private float subject_enrich_marks;
	private float periodictest;
	private float obtainedmarks;
	private List<ReportMenuVo> examMarkList;
	private List<ReportMenuVo> examMarkList1;
	private String examname;
	private String examtypeid;
	private String examstypeidterm2;
	private String work_edu_grade;
	private String art_edu_grade;
	private String heath_edu_grade;
	private String discipline_grade;
	private String examtypeprefix;
	private Map<String,String> subMarks;
	private Map<String,String> periodicsubMarks;
	private Map<String,String> notebookMarks;
	private Map<String,String> enrichmentMarks;
	private String remarks;
	private String result;
	private String regNo;
	private String work_edu_grade1;
	private String art_edu_grade1;
	private String health_edu_grade1;
	private String discipline_grade1;
	private String grade1;
	private String grade2;
	private String studentIdNo;
	private String examId;
	private String examcode;
	private String examTypeName;
	private String examStartDate;
	private String examEndDate;
	private String grade;
	private String designation;
	private String studentHouse;
	
	
	public String getStudentHouse() {
		return studentHouse;
	}
	public void setStudentHouse(String studentHouse) {
		this.studentHouse = studentHouse;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTerm1() {
		return term1;
	}
	public void setTerm1(String term1) {
		this.term1 = term1;
	}
	public String getTerm2() {
		return term2;
	}
	public void setTerm2(String term2) {
		this.term2 = term2;
	}
	public String getFinalterms() {
		return finalterms;
	}
	public void setFinalterms(String finalterms) {
		this.finalterms = finalterms;
	}
	public String getCbse() {
		return cbse;
	}
	public void setCbse(String cbse) {
		this.cbse = cbse;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public float getScored_marks() {
		return scored_marks;
	}
	public void setScored_marks(float scored_marks) {
		this.scored_marks = scored_marks;
	}
	public float getNotebook_marks() {
		return notebook_marks;
	}
	public void setNotebook_marks(float notebook_marks) {
		this.notebook_marks = notebook_marks;
	}
	public float getSubject_enrich_marks() {
		return subject_enrich_marks;
	}
	public void setSubject_enrich_marks(float subject_enrich_marks) {
		this.subject_enrich_marks = subject_enrich_marks;
	}
	public float getPeriodictest() {
		return periodictest;
	}
	public void setPeriodictest(float periodictest) {
		this.periodictest = periodictest;
	}
	public float getObtainedmarks() {
		return obtainedmarks;
	}
	public void setObtainedmarks(float obtainedmarks) {
		this.obtainedmarks = obtainedmarks;
	}
	public List<ReportMenuVo> getExamMarkList() {
		return examMarkList;
	}
	public void setExamMarkList(List<ReportMenuVo> examMarkList) {
		this.examMarkList = examMarkList;
	}
	public List<ReportMenuVo> getExamMarkList1() {
		return examMarkList1;
	}
	public void setExamMarkList1(List<ReportMenuVo> examMarkList1) {
		this.examMarkList1 = examMarkList1;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public String getExamtypeid() {
		return examtypeid;
	}
	public void setExamtypeid(String examtypeid) {
		this.examtypeid = examtypeid;
	}
	public String getExamstypeidterm2() {
		return examstypeidterm2;
	}
	public void setExamstypeidterm2(String examstypeidterm2) {
		this.examstypeidterm2 = examstypeidterm2;
	}
	public String getWork_edu_grade() {
		return work_edu_grade;
	}
	public void setWork_edu_grade(String work_edu_grade) {
		this.work_edu_grade = work_edu_grade;
	}
	public String getArt_edu_grade() {
		return art_edu_grade;
	}
	public void setArt_edu_grade(String art_edu_grade) {
		this.art_edu_grade = art_edu_grade;
	}
	public String getHeath_edu_grade() {
		return heath_edu_grade;
	}
	public void setHeath_edu_grade(String heath_edu_grade) {
		this.heath_edu_grade = heath_edu_grade;
	}
	public String getDiscipline_grade() {
		return discipline_grade;
	}
	public void setDiscipline_grade(String discipline_grade) {
		this.discipline_grade = discipline_grade;
	}
	public String getExamtypeprefix() {
		return examtypeprefix;
	}
	public void setExamtypeprefix(String examtypeprefix) {
		this.examtypeprefix = examtypeprefix;
	}
	public Map<String, String> getSubMarks() {
		return subMarks;
	}
	public void setSubMarks(Map<String, String> subMarks) {
		this.subMarks = subMarks;
	}
	public Map<String, String> getPeriodicsubMarks() {
		return periodicsubMarks;
	}
	public void setPeriodicsubMarks(Map<String, String> periodicsubMarks) {
		this.periodicsubMarks = periodicsubMarks;
	}
	public Map<String, String> getNotebookMarks() {
		return notebookMarks;
	}
	public void setNotebookMarks(Map<String, String> notebookMarks) {
		this.notebookMarks = notebookMarks;
	}
	public Map<String, String> getEnrichmentMarks() {
		return enrichmentMarks;
	}
	public void setEnrichmentMarks(Map<String, String> enrichmentMarks) {
		this.enrichmentMarks = enrichmentMarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getWork_edu_grade1() {
		return work_edu_grade1;
	}
	public void setWork_edu_grade1(String work_edu_grade1) {
		this.work_edu_grade1 = work_edu_grade1;
	}
	public String getArt_edu_grade1() {
		return art_edu_grade1;
	}
	public void setArt_edu_grade1(String art_edu_grade1) {
		this.art_edu_grade1 = art_edu_grade1;
	}
	public String getHealth_edu_grade1() {
		return health_edu_grade1;
	}
	public void setHealth_edu_grade1(String health_edu_grade1) {
		this.health_edu_grade1 = health_edu_grade1;
	}
	public String getDiscipline_grade1() {
		return discipline_grade1;
	}
	public void setDiscipline_grade1(String discipline_grade1) {
		this.discipline_grade1 = discipline_grade1;
	}
	public String getGrade1() {
		return grade1;
	}
	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}
	public String getGrade2() {
		return grade2;
	}
	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}
	public String getStudentIdNo() {
		return studentIdNo;
	}
	public void setStudentIdNo(String studentIdNo) {
		this.studentIdNo = studentIdNo;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getExamcode() {
		return examcode;
	}
	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}
	public String getExamTypeName() {
		return examTypeName;
	}
	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}
	public String getExamStartDate() {
		return examStartDate;
	}
	public void setExamStartDate(String examStartDate) {
		this.examStartDate = examStartDate;
	}
	public String getExamEndDate() {
		return examEndDate;
	}
	public void setExamEndDate(String examEndDate) {
		this.examEndDate = examEndDate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMotherTounge() {
		return motherTounge;
	}
	public void setMotherTounge(String motherTounge) {
		this.motherTounge = motherTounge;
	}
	public String getPermanentaddress() {
		return permanentaddress;
	}
	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getFatherIncome() {
		return fatherIncome;
	}
	public void setFatherIncome(String fatherIncome) {
		this.fatherIncome = fatherIncome;
	}
	public String getMotherIncome() {
		return motherIncome;
	}
	public void setMotherIncome(String motherIncome) {
		this.motherIncome = motherIncome;
	}
	public String getFatherOfficeAddress() {
		return fatherOfficeAddress;
	}
	public void setFatherOfficeAddress(String fatherOfficeAddress) {
		this.fatherOfficeAddress = fatherOfficeAddress;
	}
	public String getMotherOfficeAddress() {
		return motherOfficeAddress;
	}
	public void setMotherOfficeAddress(String motherOfficeAddress) {
		this.motherOfficeAddress = motherOfficeAddress;
	}
	private String bankName;
	private String ddNo;
	private String ddDate;
	private String paidDate;
	private String amount_paid;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getDdNo() {
		return ddNo;
	}
	public void setDdNo(String ddNo) {
		this.ddNo = ddNo;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(String amount_paid) {
		this.amount_paid = amount_paid;
	}
	public int getTotalStudentsInCls() {
		return totalStudentsInCls;
	}
	public void setTotalStudentsInCls(int totalStudentsInCls) {
		this.totalStudentsInCls = totalStudentsInCls;
	}
	public int getTotalStudentsInStrm() {
		return totalStudentsInStrm;
	}
	public void setTotalStudentsInStrm(int totalStudentsInStrm) {
		this.totalStudentsInStrm = totalStudentsInStrm;
	}
	public int getTotalStudentsInDiv() {
		return totalStudentsInDiv;
	}
	public void setTotalStudentsInDiv(int totalStudentsInDiv) {
		this.totalStudentsInDiv = totalStudentsInDiv;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPromotionStatus() {
		return promotionStatus;
	}
	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}
	public String getTempAdmnNo() {
		return tempAdmnNo;
	}
	public void setTempAdmnNo(String tempAdmnNo) {
		this.tempAdmnNo = tempAdmnNo;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public int getGirls() {
		return girls;
	}
	public void setGirls(int girls) {
		this.girls = girls;
	}
	public int getBoys() {
		return boys;
	}
	public void setBoys(int boys) {
		this.boys = boys;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianMobileNo() {
		return guardianMobileNo;
	}
	public void setGuardianMobileNo(String guardianMobileNo) {
		this.guardianMobileNo = guardianMobileNo;
	}
	public String getFatherMobileNo() {
		return fatherMobileNo;
	}
	public void setFatherMobileNo(String fatherMobileNo) {
		this.fatherMobileNo = fatherMobileNo;
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
	public String getBusStageName() {
		return busStageName;
	}
	public void setBusStageName(String busStageName) {
		this.busStageName = busStageName;
	}
	public String getStageAmount() {
		return stageAmount;
	}
	public void setStageAmount(String stageAmount) {
		this.stageAmount = stageAmount;
	}
	public String getSpecializationId() {
		return specializationId;
	}
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}
	public String getSpecializationName() {
		return specializationName;
	}
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	public String getFatherResidenceNo() {
		return fatherResidenceNo;
	}
	public void setFatherResidenceNo(String fatherResidenceNo) {
		this.fatherResidenceNo = fatherResidenceNo;
	}
	public String getMotherResidenceNo() {
		return motherResidenceNo;
	}
	public void setMotherResidenceNo(String motherResidenceNo) {
		this.motherResidenceNo = motherResidenceNo;
	}
	public String getStudentRollNo() {
		return studentRollNo;
	}
	public void setStudentRollNo(String studentRollNo) {
		this.studentRollNo = studentRollNo;
	}
	public String getTotal_strength() {
		return total_strength;
	}
	public void setTotal_strength(String total_strength) {
		this.total_strength = total_strength;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getDob() {
		return dob;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getTermStatus() {
		return termStatus;
	}
	public void setTermStatus(String termStatus) {
		this.termStatus = termStatus;
	}
	public String getTermStatusId() {
		return termStatusId;
	}
	public void setTermStatusId(String termStatusId) {
		this.termStatusId = termStatusId;
	}
	public String getClass_and_section() {
		return class_and_section;
	}
	public void setClass_and_section(String class_and_section) {
		this.class_and_section = class_and_section;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getStudent_gender_var() {
		return student_gender_var;
	}
	public void setStudent_gender_var(String student_gender_var) {
		this.student_gender_var = student_gender_var;
	}
	public String getTeachertId() {
		return teachertId;
	}
	public void setTeachertId(String teachertId) {
		this.teachertId = teachertId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getAccYear() {
		return accYear;
	}
	public void setAccYear(String accYear) {
		this.accYear = accYear;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
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
	public String getAttendenceDate() {
		return attendenceDate;
	}
	public void setAttendenceDate(String attendenceDate) {
		this.attendenceDate = attendenceDate;
	}
	public String getAttendenceStatus() {
		return attendenceStatus;
	}
	public void setAttendenceStatus(String attendenceStatus) {
		this.attendenceStatus = attendenceStatus;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getTeachertype() {
		return teachertype;
	}
	public void setTeachertype(String teachertype) {
		this.teachertype = teachertype;
	}
	public String getAccyearname() {
		return accyearname;
	}
	public void setAccyearname(String accyearname) {
		this.accyearname = accyearname;
	}
	public String getStreamname() {
		return streamname;
	}
	public void setStreamname(String streamname) {
		this.streamname = streamname;
	}
	public String getClassname() {
		return classname;
	}
	public String getStudent_mothername_var() {
		return student_mothername_var;
	}
	public void setStudent_mothername_var(String student_mothername_var) {
		this.student_mothername_var = student_mothername_var;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getAccyearId() {
		return accyearId;
	}
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getDescription() {
		return description;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getStudent_mothermobileno_var() {
		return student_mothermobileno_var;
	}
	public void setStudent_mothermobileno_var(String student_mothermobileno_var) {
		this.student_mothermobileno_var = student_mothermobileno_var;
	}
	public String getAddress() {
		return address;
	}
	public String getResidenceNo() {
		return residenceNo;
	}
	public void setResidenceNo(String residenceNo) {
		this.residenceNo = residenceNo;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accesionNo) {
		this.accessionNo = accesionNo;
	}
	public String getDdDate() {
		return ddDate;
	}
	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}
	

}
