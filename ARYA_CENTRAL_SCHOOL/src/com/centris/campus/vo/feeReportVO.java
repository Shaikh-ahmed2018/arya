package com.centris.campus.vo;

import java.util.ArrayList;

public class feeReportVO {
	private String location;
	private String classname;
	private String examdate;
	private String accyear;
	private String section;
	private String enddate;
	private String studentName;
	private String termname;
	private Double totalAmount;
	private Double paidAmount;
	private String paidDate;
	private String status;
	private int  sno;
	private String stream;
	private int days;
	private double fine;
	private String isApplicable;
	private String termId;
	private ArrayList<FeeNameVo> feeNameVo;
	private String locationid;
	private String classnameid;
	private String sectionid;
	private String accyearid;
	private String streamid;
	private String fineDate;
	
	

	public String getFineDate() {
		return fineDate;
	}
	public void setFineDate(String fineDate) {
		this.fineDate = fineDate;
	}
	public ArrayList<FeeNameVo> getFeeNameVo() {
		return feeNameVo;
	}
	public void setFeeNameVo(ArrayList<FeeNameVo> feeNameVo) {
		this.feeNameVo = feeNameVo;
	}
	public String getIsApplicable() {
		return isApplicable;
	}
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getClassnameid() {
		return classnameid;
	}
	public void setClassnameid(String classnameid) {
		this.classnameid = classnameid;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getAccyearid() {
		return accyearid;
	}
	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}
	public String getStreamid() {
		return streamid;
	}
	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}


	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
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
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}

}
