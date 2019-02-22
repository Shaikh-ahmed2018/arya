package com.centris.campus.forms;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class TeacherForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String tfastname;
	private String tlastname;
	private String tqualification;
	private String teacherpermanentadd;
	private String teachermobno;
	private String teacheremail;
	private String subjectprimary;
	private String subjectsecondary;
	private FormFile teacherImage;
	private FormFile teacherprofile;
	private FormFile idproof;
	private String username;
	private String dateofbirth;
	private String joiningdate;
	private String designation;
	private String teacherid;
	private String defaultimage;
	private String defaultidproof;
	private String defaultprofile;
	private String teachingtype;
	private String department;
	private String roleId;
	private String gender;
	private String transport;
	private String location;
	private String imagestring;
	private String createdBy;
	private String fathername;
	private String regno;
	private String usertype;
	private String accountNumber;
	private String bankName;
	private String panNumber;
	private FormFile filaattachment1;
	private FormFile filaattachment2;
	private FormFile filaattachment3;
	private FormFile filaattachment4;
	private FormFile filaattachment5;
	private String attachment1string;
	private String attachment2string;
	private String attachment3string;
	private String attachment4string;
	private String attachment5string;
	private String reportingTo;
	private String defaultattachment1;
	private String defaultattachment2;
	private String defaultattachment3;
	private String defaultattachment4;
	private String defaultattachment5;
	private String is_student_studying;
	private String studentName;
	private String student_admission_id;
	private String fatherMobile;
	private String motherMobile;
	private String abbreviate;
	private String aadhaarnumber;
	private String maritalstatus;
	private String spousename;
	private String spouseMobile;
	private String schoolName;
	private String academicYear;
	private String payrollid;
	private String hpl;
	
	
	public String getHpl() {
		return hpl;
	}

	public void setHpl(String hpl) {
		this.hpl = hpl;
	}

	public String getPayrollid() {
		return payrollid;
	}

	public void setPayrollid(String payrollid) {
		this.payrollid = payrollid;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSpousename() {
		return spousename;
	}

	public void setSpousename(String spousename) {
		this.spousename = spousename;
	}

	public String getSpouseMobile() {
		return spouseMobile;
	}

	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}

	public String getAbbreviate() {
		return abbreviate;
	}

	public void setAbbreviate(String abbreviate) {
		this.abbreviate = abbreviate;
	}

	public String getAadhaarnumber() {
		return aadhaarnumber;
	}

	public void setAadhaarnumber(String aadhaarnumber) {
		this.aadhaarnumber = aadhaarnumber;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getFatherMobile() {
		return fatherMobile;
	}

	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}

	public String getMotherMobile() {
		return motherMobile;
	}

	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}

	public String getIs_student_studying() {
		return is_student_studying;
	}

	public void setIs_student_studying(String is_student_studying) {
		this.is_student_studying = is_student_studying;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudent_admission_id() {
		return student_admission_id;
	}

	public void setStudent_admission_id(String student_admission_id) {
		this.student_admission_id = student_admission_id;
	}

	public String getDefaultattachment1() {
		return defaultattachment1;
	}

	public void setDefaultattachment1(String defaultattachment1) {
		this.defaultattachment1 = defaultattachment1;
	}

	public String getDefaultattachment2() {
		return defaultattachment2;
	}

	public void setDefaultattachment2(String defaultattachment2) {
		this.defaultattachment2 = defaultattachment2;
	}

	public String getDefaultattachment3() {
		return defaultattachment3;
	}

	public void setDefaultattachment3(String defaultattachment3) {
		this.defaultattachment3 = defaultattachment3;
	}

	public String getDefaultattachment4() {
		return defaultattachment4;
	}

	public void setDefaultattachment4(String defaultattachment4) {
		this.defaultattachment4 = defaultattachment4;
	}

	public String getDefaultattachment5() {
		return defaultattachment5;
	}

	public void setDefaultattachment5(String defaultattachment5) {
		this.defaultattachment5 = defaultattachment5;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getAttachment1string() {
		return attachment1string;
	}

	public void setAttachment1string(String attachment1string) {
		this.attachment1string = attachment1string;
	}

	public String getAttachment2string() {
		return attachment2string;
	}

	public void setAttachment2string(String attachment2string) {
		this.attachment2string = attachment2string;
	}

	public String getAttachment3string() {
		return attachment3string;
	}

	public void setAttachment3string(String attachment3string) {
		this.attachment3string = attachment3string;
	}

	public String getAttachment4string() {
		return attachment4string;
	}

	public void setAttachment4string(String attachment4string) {
		this.attachment4string = attachment4string;
	}

	public String getAttachment5string() {
		return attachment5string;
	}

	public void setAttachment5string(String attachment5string) {
		this.attachment5string = attachment5string;
	}

	public FormFile getFilaattachment1() {
		return filaattachment1;
	}

	public void setFilaattachment1(FormFile filaattachment1) {
		this.filaattachment1 = filaattachment1;
	}

	public FormFile getFilaattachment2() {
		return filaattachment2;
	}

	public void setFilaattachment2(FormFile filaattachment2) {
		this.filaattachment2 = filaattachment2;
	}

	public FormFile getFilaattachment3() {
		return filaattachment3;
	}

	public void setFilaattachment3(FormFile filaattachment3) {
		this.filaattachment3 = filaattachment3;
	}

	public FormFile getFilaattachment4() {
		return filaattachment4;
	}

	public void setFilaattachment4(FormFile filaattachment4) {
		this.filaattachment4 = filaattachment4;
	}

	public FormFile getFilaattachment5() {
		return filaattachment5;
	}

	public void setFilaattachment5(FormFile filaattachment5) {
		this.filaattachment5 = filaattachment5;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	private final Map values = new HashMap();
	private int count;

	private final Map subjects = new HashMap();
	private int subjectcount;
	
	private final Map leaves=new HashMap();
	private int leavecount;

	
	
	public int getLeavecount() {
		return leavecount;
	}

	public void setLeavecount(int leavecount) {
		this.leavecount = leavecount;
	}

	public Map getLeaves() {
		return leaves;
	}

	public int getSubjectcount() {
		return subjectcount;
	}

	public void setSubjectcount(int subjectcount) {
		this.subjectcount = subjectcount;
	}

	public Map getSubjects() {
		return subjects;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		System.out.println("count in form ::: " + count);
		this.count = count;
	}

	public Map getValues() {
		return values;
	}

	public void setTalue(String key, Object value) {
		subjects.put(key, value);
	}

	public Object getTalue(String key) {
		return subjects.get(key);
	}

	public void setValue(String key, Object value) {
		values.put(key, value);
	}

	public Object getValue(String key) {
		return values.get(key);
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getPresentadd() {
		return presentadd;
	}

	public void setPresentadd(String presentadd) {
		this.presentadd = presentadd;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getPermanentadd() {
		return permanentadd;
	}

	public void setPermanentadd(String permanentadd) {
		this.permanentadd = permanentadd;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	private String presentadd;
	private String mothername;
	private String permanentadd;
	private String blood;

	public String getImagestring() {
		return imagestring;
	}

	public void setImagestring(String imagestring) {
		this.imagestring = imagestring;
	}

	public String getProfilestring() {
		return profilestring;
	}

	public void setProfilestring(String profilestring) {
		this.profilestring = profilestring;
	}

	public String getIdproofstring() {
		return idproofstring;
	}

	public void setIdproofstring(String idproofstring) {
		this.idproofstring = idproofstring;
	}

	private String profilestring;
	private String idproofstring;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeachingtype() {
		return teachingtype;
	}

	public void setTeachingtype(String teachingtype) {
		this.teachingtype = teachingtype;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDefaultimage() {
		return defaultimage;
	}

	public void setDefaultimage(String defaultimage) {
		this.defaultimage = defaultimage;
	}

	public String getDefaultidproof() {
		return defaultidproof;
	}

	public void setDefaultidproof(String defaultidproof) {

		this.defaultidproof = defaultidproof;
	}

	public String getDefaultprofile() {
		return defaultprofile;
	}

	public void setDefaultprofile(String defaultprofile) {
		this.defaultprofile = defaultprofile;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public FormFile getIdproof() {
		return idproof;
	}

	public void setIdproof(FormFile idproof) {
		this.idproof = idproof;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDateofbirth() {

		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getTfastname() {
		return tfastname;
	}

	public void setTfastname(String tfastname) {
		this.tfastname = tfastname;
	}

	public String getTlastname() {
		return tlastname;
	}

	public void setTlastname(String tlastname) {
		this.tlastname = tlastname;
	}

	public String getTqualification() {
		return tqualification;
	}

	public void setTqualification(String tqualification) {
		this.tqualification = tqualification;
	}

	public String getTeacherpermanentadd() {
		return teacherpermanentadd;
	}

	public void setTeacherpermanentadd(String teacherpermanentadd) {
		this.teacherpermanentadd = teacherpermanentadd;
	}

	public String getTeachermobno() {
		return teachermobno;
	}

	public void setTeachermobno(String teachermobno) {
		this.teachermobno = teachermobno;
	}

	public String getTeacheremail() {
		return teacheremail;
	}

	public void setTeacheremail(String teacheremail) {
		this.teacheremail = teacheremail;
	}

	public String getSubjectprimary() {
		return subjectprimary;
	}

	public void setSubjectprimary(String subjectprimary) {
		this.subjectprimary = subjectprimary;
	}

	public String getSubjectsecondary() {
		return subjectsecondary;
	}

	public void setSubjectsecondary(String subjectsecondary) {
		this.subjectsecondary = subjectsecondary;
	}

	public FormFile getTeacherImage() {
		return teacherImage;
	}

	public void setTeacherImage(FormFile teacherImage) {
		this.teacherImage = teacherImage;
	}

	public FormFile getTeacherprofile() {
		return teacherprofile;
	}

	public void setTeacherprofile(FormFile teacherprofile) {
		this.teacherprofile = teacherprofile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

}