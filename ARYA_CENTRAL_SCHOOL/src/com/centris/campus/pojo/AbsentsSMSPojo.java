package com.centris.campus.pojo;

import java.sql.Timestamp;

public class AbsentsSMSPojo {

	private String categoryid;
	private String[] classid;
	private String[] sectionid;
	private String[] studentid;
	private String date;
	private String smstext;
	private String createdby;
	private Timestamp createdate;
	private String modifiedby;
	private Timestamp modifieddate;
	private String absentcode;
	private int issection;
	private int isstudent;
	private String smsstatus;
	
	private String studentName;
	private String section;
	private String classname;
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String[] getClassid() {
		return classid;
	}
	public void setClassid(String[] classid) {
		this.classid = classid;
	}
	public String[] getSectionid() {
		return sectionid;
	}
	public void setSectionid(String[] sectionid) {
		this.sectionid = sectionid;
	}
	public String[] getStudentid() {
		return studentid;
	}
	public void setStudentid(String[] studentid) {
		this.studentid = studentid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getAbsentcode() {
		return absentcode;
	}
	public void setAbsentcode(String absentcode) {
		this.absentcode = absentcode;
	}
	public int getIssection() {
		return issection;
	}
	public void setIssection(int issection) {
		this.issection = issection;
	}
	public int getIsstudent() {
		return isstudent;
	}
	public void setIsstudent(int isstudent) {
		this.isstudent = isstudent;
	}
	public String getSmsstatus() {
		return smsstatus;
	}
	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
	
	
	
	
	
	
	
	
	
}
