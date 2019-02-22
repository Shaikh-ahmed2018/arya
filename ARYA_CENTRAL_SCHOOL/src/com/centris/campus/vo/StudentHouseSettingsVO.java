package com.centris.campus.vo;

public class StudentHouseSettingsVO implements  Comparable<StudentHouseSettingsVO> {

	private String houseid;
	private String housename;
	private String locid;
	private String locname;
	private String accid;
	private String accname;
	private String userid;
	private String usrname;
	private int slno;
	private String classid;
	private String classname;
	private String sectionid;
	private String sectionname;
	private int totalclassStrength;
	private int allocatedstudents;
	private String status;
	private String statusid;
	private String stuid;
	public String stuname;
	private String admissionno;
	private String genhouid;
	
	
	public String getGenhouid() {
		return genhouid;
	}
	public void setGenhouid(String genhouid) {
		this.genhouid = genhouid;
	}
	public String getAdmissionno() {
		return admissionno;
	}
	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAllocatedstudents() {
		return allocatedstudents;
	}
	public void setAllocatedstudents(int allocatedstudents) {
		this.allocatedstudents = allocatedstudents;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public int getTotalclassStrength() {
		return totalclassStrength;
	}
	public void setTotalclassStrength(int totalclassStrength) {
		this.totalclassStrength = totalclassStrength;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno2) {
		this.slno = slno2;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getHouseid() {
		return houseid;
	}
	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}
	public String getHousename() {
		return housename;
	}
	public void setHousename(String housename) {
		this.housename = housename;
	}
	public String getLocid() {
		return locid;
	}
	public void setLocid(String locid) {
		this.locid = locid;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	public String getAccid() {
		return accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	@Override
	public int compareTo(StudentHouseSettingsVO name) {
		 return  name.stuname.compareTo(name.stuname);
	}

	
	
}
