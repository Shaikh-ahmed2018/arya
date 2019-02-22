package com.centris.campus.vo;

public class AddFeeVO 
{

	private String name;
	private String id;
	private String description;
	private String createdby;
	private String currenttime;
	private String concession;
	private String concessiontype;
	private String getDataArray[];
	private String accYearArray[];
	private String feeTypeId;
	private String feeType;
	private String academicYear;
	private String locationId;
	private String locationName;
	private String academicYearName;
	private String admissionNo;
	//defaulter list
	private String studentName;
	private String className;
	private String divisionName;
	private String termName;
	private String dueAmount;
	private double dueAmt;
	private double advanceAmt;
	private double paidAmt;
	
	
	public double getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(double paidAmt) {
		this.paidAmt = paidAmt;
	}
	public double getAdvanceAmt() {
		return advanceAmt;
	}
	public void setAdvanceAmt(double advanceAmt) {
		this.advanceAmt = advanceAmt;
	}
	public double getDueAmt() {
		return dueAmt;
	}
	public void setDueAmt(double dueAmt) {
		this.dueAmt = dueAmt;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	
	public String[] getAccYearArray() {
		return accYearArray;
	}
	public void setAccYearArray(String[] accYearArray) {
		this.accYearArray = accYearArray;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getAcademicYearName() {
		return academicYearName;
	}
	public void setAcademicYearName(String academicYearName) {
		this.academicYearName = academicYearName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String[] getGetDataArray() {
		return getDataArray;
	}
	public void setGetDataArray(String[] getDataArray) {
		this.getDataArray = getDataArray;
	}
	public String getConcessiontype() {
		return concessiontype;
	}
	public void setConcessiontype(String concessiontype) {
		this.concessiontype = concessiontype;
	}
	public String getConcession() {
		return concession;
	}
	public void setConcession(String concession) {
		this.concession = concession;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCurrenttime() {
		return currenttime;
	}
	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
