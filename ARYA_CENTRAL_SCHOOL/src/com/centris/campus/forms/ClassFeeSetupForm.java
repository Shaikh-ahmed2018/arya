package com.centris.campus.forms;

import java.io.File;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ClassFeeSetupForm extends ActionForm {

	private String stud_academicYear;
	private String stud_category;
	private String stud_classname;
	private String stud_section;
	private String stud_studentname;
	private String date;
	private String accyear;
	private String teachertId;
	private String term;
	private String searchTerm;
	private String searchType;
	private String searchBy;
	private String feeCollectionCode;
	private String lastdatetopay;
	
	
	private String hfmsname;
	private String studentid;
	private String admissionNum;
	private String payment_mode;
	private String paymentType;
	private double paidamount;
	private double totalfeeamt;
	private double dueamount;
	private String payment_date_id;
	private String hclassname;
	private String paymentMonth;
	private String monthlist;
	private String currentuserid;
	private double payingamount;
	private String cheque_no;
	private String classname;
	private String studentname; 
	
	private FormFile formfile;
	
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStud_academicYear() {
		return stud_academicYear;
	}
	public void setStud_academicYear(String stud_academicYear) {
		this.stud_academicYear = stud_academicYear;
	}
	public String getStud_category() {
		return stud_category;
	}
	public void setStud_category(String stud_category) {
		this.stud_category = stud_category;
	}
	public String getStud_classname() {
		return stud_classname;
	}
	public void setStud_classname(String stud_classname) {
		this.stud_classname = stud_classname;
	}
	public String getStud_section() {
		return stud_section;
	}
	public void setStud_section(String stud_section) {
		this.stud_section = stud_section;
	}
	public String getStud_studentname() {
		return stud_studentname;
	}
	public void setStud_studentname(String stud_studentname) {
		this.stud_studentname = stud_studentname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getTeachertId() {
		return teachertId;
	}
	public void setTeachertId(String teachertId) {
		this.teachertId = teachertId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getFeeCollectionCode() {
		return feeCollectionCode;
	}
	public void setFeeCollectionCode(String feeCollectionCode) {
		this.feeCollectionCode = feeCollectionCode;
	}
	public String getLastdatetopay() {
		return lastdatetopay;
	}
	public void setLastdatetopay(String lastdatetopay) {
		this.lastdatetopay = lastdatetopay;
	}
	public String getHfmsname() {
		return hfmsname;
	}
	public void setHfmsname(String hfmsname) {
		this.hfmsname = hfmsname;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getAdmissionNum() {
		return admissionNum;
	}
	public void setAdmissionNum(String admissionNum) {
		this.admissionNum = admissionNum;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}
	public double getTotalfeeamt() {
		return totalfeeamt;
	}
	public void setTotalfeeamt(double totalfeeamt) {
		this.totalfeeamt = totalfeeamt;
	}
	public double getDueamount() {
		return dueamount;
	}
	public void setDueamount(double dueamount) {
		this.dueamount = dueamount;
	}
	public String getPayment_date_id() {
		return payment_date_id;
	}
	public void setPayment_date_id(String payment_date_id) {
		this.payment_date_id = payment_date_id;
	}
	public String getHclassname() {
		return hclassname;
	}
	public void setHclassname(String hclassname) {
		this.hclassname = hclassname;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getMonthlist() {
		return monthlist;
	}
	public void setMonthlist(String monthlist) {
		this.monthlist = monthlist;
	}
	public String getCurrentuserid() {
		return currentuserid;
	}
	public void setCurrentuserid(String currentuserid) {
		this.currentuserid = currentuserid;
	}
	public double getPayingamount() {
		return payingamount;
	}
	public void setPayingamount(double payingamount) {
		this.payingamount = payingamount;
	}
	public String getCheque_no() {
		return cheque_no;
	}
	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
	}
	
	
	
	

}