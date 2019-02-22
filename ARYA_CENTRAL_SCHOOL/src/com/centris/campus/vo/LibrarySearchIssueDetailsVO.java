package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LibrarySearchIssueDetailsVO implements Comparable {

	private String schoolName;
	private String academicYear;
	private String standard;
	private String division;
	private String search;
	
	private String stuname;
	private String stuid;
	private String classid;
	private String classname;
	private String secid;
	private String secname;
	private String rollno;
	private String admission;
	private String stuSubNo;
	private int slno;
	private String status;
	
	private String department;
	private String desigantion;
	
	private String staffName;
	private String staffDepartment;
	private String staffDesignation;
	private String locid;
	private String searchText;

	private String othersName;
	private String contactNo;
	private String emailId;
	private String bookname;
	private String bookauthor;
	private String issuedate;
	private String accyrid;
	private String sectionid;
	private String subscriberno;
	private String designationname;
	private String departmentname;
	private String othersNameText;
	private String othersGender;
	private String othersAdress;
	private String othersMailId;
	private String othersContact;
	
	private String subid;
	private String accessionNo;
	private String fromdate;
	private String todate;
	private String staffid;
	private String returneddate;
	private String usertype;
	private String subname;
	
	
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getReturneddate() {
		return returneddate;
	}
	public void setReturneddate(String returneddate) {
		this.returneddate = returneddate;
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
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getSubid() {
		return subid;
	}
	public void setSubid(String subid) {
		this.subid = subid;
	}
	public String getOthersGender() {
		return othersGender;
	}
	public void setOthersGender(String othersGender) {
		this.othersGender = othersGender;
	}
	public String getOthersAdress() {
		return othersAdress;
	}
	public void setOthersAdress(String othersAdress) {
		this.othersAdress = othersAdress;
	}
	public String getOthersMailId() {
		return othersMailId;
	}
	public void setOthersMailId(String othersMailId) {
		this.othersMailId = othersMailId;
	}
	public String getOthersContact() {
		return othersContact;
	}
	public void setOthersContact(String othersContact) {
		this.othersContact = othersContact;
	}
	public String getOthersNameText() {
		return othersNameText;
	}
	public void setOthersNameText(String othersNameText) {
		this.othersNameText = othersNameText;
	}
	public String getDesignationname() {
		return designationname;
	}
	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getSubscriberno() {
		return subscriberno;
	}
	public void setSubscriberno(String subscriberno) {
		this.subscriberno = subscriberno;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	private String locationid;
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getAccyrid() {
		return accyrid;
	}
	public void setAccyrid(String accyrid) {
		this.accyrid = accyrid;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesigantion() {
		return desigantion;
	}
	public void setDesigantion(String desigantion) {
		this.desigantion = desigantion;
	}


	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOthersName() {
		return othersName;
	}
	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getLocid() {
		return locid;
	}
	public void setLocid(String locid) {
		this.locid = locid;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffDepartment() {
		return staffDepartment;
	}
	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}
	public String getStaffDesignation() {
		return staffDesignation;
	}
	public void setStaffDesignation(String staffDesignation) {
		this.staffDesignation = staffDesignation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
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
	public String getSecid() {
		return secid;
	}
	public void setSecid(String secid) {
		this.secid = secid;
	}
	public String getSecname() {
		return secname;
	}
	public void setSecname(String secname) {
		this.secname = secname;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getAdmission() {
		return admission;
	}
	public void setAdmission(String admission) {
		this.admission = admission;
	}
	public String getStuSubNo() {
		return stuSubNo;
	}
	public void setStuSubNo(String stuSubNo) {
		this.stuSubNo = stuSubNo;
	}
	@Override
	public int compareTo(Object o) {
		LibrarySearchIssueDetailsVO person = (LibrarySearchIssueDetailsVO) o;
		return subname.compareTo(person.subname);
	}
	
	
}
