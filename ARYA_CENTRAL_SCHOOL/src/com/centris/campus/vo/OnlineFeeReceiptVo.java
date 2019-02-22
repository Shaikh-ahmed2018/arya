package com.centris.campus.vo;

public class OnlineFeeReceiptVo {
	
	
	private String studentid;
	private String studentname;
	private String accyearid;
	private String accyearname;
	private String classid;
	private String classname;
	private String sectionid;
	private String sectionname;
	
	private String feecode;
	private String feename;
	private String paiddate;
	private String addmissionnum;
	private double feeamount;
	private String termname;
	private double totalamount;
	private double feeAfterconcs;
	private String conscper;
	
	public String getConscper() {
		return conscper;
	}
	public void setConscper(String conscper) {
		this.conscper = conscper;
	}
	public double getFeeAfterconcs() {
		return feeAfterconcs;
	}
	public void setFeeAfterconcs(double feeAfterconcs) {
		this.feeAfterconcs = feeAfterconcs;
	}
	private double amt_paid;
	public double getAmt_paid() {
		return amt_paid;
	}
	public void setAmt_paid(double amt_paid) {
		this.amt_paid = amt_paid;
	}
	public double getBalance_amt() {
		return balance_amt;
	}
	public void setBalance_amt(double balance_amt) {
		this.balance_amt = balance_amt;
	}
	private double balance_amt;
	private int count;
	
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getAccyearid() {
		return accyearid;
	}
	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}
	public String getAccyearname() {
		return accyearname;
	}
	public void setAccyearname(String accyearname) {
		this.accyearname = accyearname;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAddmissionnum() {
		return addmissionnum;
	}
	public void setAddmissionnum(String addmissionnum) {
		this.addmissionnum = addmissionnum;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public String getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(String paiddate) {
		this.paiddate = paiddate;
	}
	public String getFeecode() {
		return feecode;
	}
	public void setFeecode(String feecode) {
		this.feecode = feecode;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public double getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(double feeamount) {
		this.feeamount = feeamount;
	}
	

	
	
	
}
