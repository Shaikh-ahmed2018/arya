package com.centris.campus.vo;

import java.util.ArrayList;

public class FeeCollectionVo {

	private String addmissionno;
	private String studentname;
	private String term;
	private String termid;
	private String classId;
	private String classname;
	private String sectionId;
	private String sectionname;
	private String accYear;
	private String accYearname;
	private String amount;
	private String status;
	private int sno;
	private double concession;
	private double tot_actual_amt;
	private double tot_concession_amt;
	private double tot_paid_amt;
	private String tot_opning_amt;
	private String userID;
	private double amount_paid_so_far;
	private double opening_balance;
	private double current_payment;
	private double outstanding_balance;
	private String studentid;
	private String specialization;
	private double fineAmount;
	private String[] termIdArray;
	private String[] monthName;
	private String[] monthlyAmount;
	private String refundstatus;
	private double advanceCarry;
	private double duesCarry;
	private String paymentMode;
	private String paymentPatriculars;
	private String dd_cheque_date;
	private String dd_cheque_bank;
	private String imgurl;
	private String billdate;
	private String chlnno;
	private String permanentaddress;
	private String termName;
	private String refrecieptNo;
	private String singleMonthName;

	
	
	public String getSingleMonthName() {
		return singleMonthName;
	}
	public void setSingleMonthName(String singleMonthName) {
		this.singleMonthName = singleMonthName;
	}
	public String getRefrecieptNo() {
		return refrecieptNo;
	}
	public void setRefrecieptNo(String refrecieptNo) {
		this.refrecieptNo = refrecieptNo;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getPermanentaddress() {
		return permanentaddress;
	}
	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}
	public String getChlnno() {
		return chlnno;
	}
	public void setChlnno(String chlnno) {
		this.chlnno = chlnno;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public String getDd_cheque_date() {
		return dd_cheque_date;
	}
	public void setDd_cheque_date(String dd_cheque_date) {
		this.dd_cheque_date = dd_cheque_date;
	}
	public String getDd_cheque_bank() {
		return dd_cheque_bank;
	}
	public void setDd_cheque_bank(String dd_cheque_bank) {
		this.dd_cheque_bank = dd_cheque_bank;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentPatriculars() {
		return paymentPatriculars;
	}
	public void setPaymentPatriculars(String paymentPatriculars) {
		this.paymentPatriculars = paymentPatriculars;
	}
	public double getAdvanceCarry() {
		return advanceCarry;
	}
	public void setAdvanceCarry(double advanceCarry) {
		this.advanceCarry = advanceCarry;
	}
	public double getDuesCarry() {
		return duesCarry;
	}
	public void setDuesCarry(double duesCarry) {
		this.duesCarry = duesCarry;
	}
	private ArrayList<FeeNameVo> feeNamelist;
	private String feeSettingList; 
	
	public String getFeeSettingList() {
		return feeSettingList;
	}
	public void setFeeSettingList(String feeSettingList) {
		this.feeSettingList = feeSettingList;
	}
	
	
	public String getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	public String[] getTermIdArray() {
		return termIdArray;
	}
	public void setTermIdArray(String[] termIdArray) {
		this.termIdArray = termIdArray;
	}
	public String[] getMonthName() {
		return monthName;
	}
	public void setMonthName(String[] monthName) {
		this.monthName = monthName;
	}
	public String[] getMonthlyAmount() {
		return monthlyAmount;
	}
	public void setMonthlyAmount(String[] monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getTot_opning_amt() {
		return tot_opning_amt;
	}
	public void setTot_opning_amt(String tot_opning_amt) {
		this.tot_opning_amt = tot_opning_amt;
	}
	public double getAmount_paid_so_far() {
		return amount_paid_so_far;
	}
	public void setAmount_paid_so_far(double amount_paid_so_far) {
		this.amount_paid_so_far = amount_paid_so_far;
	}
	public double getOpening_balance() {
		return opening_balance;
	}
	public void setOpening_balance(double opening_balance) {
		this.opening_balance = opening_balance;
	}
	public double getCurrent_payment() {
		return current_payment;
	}
	public void setCurrent_payment(double current_payment) {
		this.current_payment = current_payment;
	}
	public double getOutstanding_balance() {
		return outstanding_balance;
	}
	public void setOutstanding_balance(double outstanding_balance) {
		this.outstanding_balance = outstanding_balance;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getClassname() {
		return classname;
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
	public String getAccYearname() {
		return accYearname;
	}
	public void setAccYearname(String accYearname) {
		this.accYearname = accYearname;
	}
	
	public double getConcession() {
		return concession;
	}
	public void setConcession(double concession) {
		this.concession = concession;
	}
	public double getTot_actual_amt() {
		return tot_actual_amt;
	}
	public void setTot_actual_amt(double tot_actual_amt) {
		this.tot_actual_amt = tot_actual_amt;
	}
	public double getTot_concession_amt() {
		return tot_concession_amt;
	}
	public void setTot_concession_amt(double tot_concession_amt) {
		this.tot_concession_amt = tot_concession_amt;
	}
	public double getTot_paid_amt() {
		return tot_paid_amt;
	}
	public void setTot_paid_amt(double tot_paid_amt) {
		this.tot_paid_amt = tot_paid_amt;
	}
	public ArrayList<FeeNameVo> getFeeNamelist() {
		return feeNamelist;
	}
	public void setFeeNamelist(ArrayList<FeeNameVo> feeNamelist) {
		this.feeNamelist = feeNamelist;
	}
	public String getAccYear() {
		return accYear;
	}
	public void setAccYear(String accYear) {
		this.accYear = accYear;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getAddmissionno() {
		return addmissionno;
	}
	public void setAddmissionno(String addmissionno) {
		this.addmissionno = addmissionno;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
