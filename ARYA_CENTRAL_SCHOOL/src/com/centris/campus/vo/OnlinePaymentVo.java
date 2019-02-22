package com.centris.campus.vo;

import java.util.List;

public class OnlinePaymentVo {
private String studentName;
private String studentId;
private String studentRollNo;
private double dueFee;
private String term;
private String accYear;
private String classDetail;
private String termid;
private String feeCode;
private String imgurl;
private String admissionNo;
private String classId;
private String spec;
private double fineAmt;
private double feeAmt;
private List<OnlinePaymentVo> termlist;
private List<OnlinePaymentVo> transporttermlist;
private String strageid;
private String stragename;
private String routeid;
private String routename;


public String getStrageid() {
	return strageid;
}
public void setStrageid(String strageid) {
	this.strageid = strageid;
}
public String getStragename() {
	return stragename;
}
public void setStragename(String stragename) {
	this.stragename = stragename;
}
public String getRouteid() {
	return routeid;
}
public void setRouteid(String routeid) {
	this.routeid = routeid;
}
public String getRoutename() {
	return routename;
}
public void setRoutename(String routename) {
	this.routename = routename;
}
public List<OnlinePaymentVo> getTermlist() {
	return termlist;
}
public void setTermlist(List<OnlinePaymentVo> termlist) {
	this.termlist = termlist;
}
public List<OnlinePaymentVo> getTransporttermlist() {
	return transporttermlist;
}
public void setTransporttermlist(List<OnlinePaymentVo> transporttermlist) {
	this.transporttermlist = transporttermlist;
}
public String getClassId() {
	return classId;
}
public void setClassId(String classId) {
	this.classId = classId;
}
public String getSpec() {
	return spec;
}
public void setSpec(String spec) {
	this.spec = spec;
}
public double getFineAmt() {
	return fineAmt;
}
public void setFineAmt(double fineAmt) {
	this.fineAmt = fineAmt;
}
public double getFeeAmt() {
	return feeAmt;
}
public void setFeeAmt(double feeAmt) {
	this.feeAmt = feeAmt;
}
public String getAdmissionNo() {
	return admissionNo;
}
public void setAdmissionNo(String admissionNo) {
	this.admissionNo = admissionNo;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
public String getFeeCode() {
	return feeCode;
}
public void setFeeCode(String feeCode) {
	this.feeCode = feeCode;
}
public String getTermid() {
	return termid;
}
public void setTermid(String termid) {
	this.termid = termid;
}
public String getClassDetail() {
	return classDetail;
}
public void setClassDetail(String classDetail) {
	this.classDetail = classDetail;
}
public String getAccYear() {
	return accYear;
}
public void setAccYear(String accYear) {
	this.accYear = accYear;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getStudentId() {
	return studentId;
}
public void setStudentId(String studentId) {
	this.studentId = studentId;
}
public String getStudentRollNo() {
	return studentRollNo;
}
public void setStudentRollNo(String studentRollNo) {
	this.studentRollNo = studentRollNo;
}
public double getDueFee() {
	return dueFee;
}
public void setDueFee(double dueFee) {
	this.dueFee = dueFee;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}

}
