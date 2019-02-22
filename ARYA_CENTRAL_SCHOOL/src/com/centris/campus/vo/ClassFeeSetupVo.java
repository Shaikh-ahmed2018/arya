package com.centris.campus.vo;

import java.util.ArrayList;

public class ClassFeeSetupVo {

	
	private String classid;
	private String classname;
	private String termid;
	private String termname;
	private String accyear;
	private String accyearid;
	private int sno;
	private int feecount;
	private int specilazationCount;
	
	public int getSpecilazationCount() {
		return specilazationCount;
	}
	public void setSpecilazationCount(int specilazationCount) {
		this.specilazationCount = specilazationCount;
	}
	private String feecode;
	private String feename;
	private String feesettingcode;
	private String AllfeescheckBox;
	private String feeamount;
	private String acadamicyear;
	private String term;
	private String challangenarateDate;
	private String chalanNo;
	private String actualAmount;
	private String stdadmissionNo;
	private String termOrder;
	private String status;
	
	private String stuFname;
	private String stuLname;
	private String admissionNo;
	private Double fineamt;
	private String particularNo;
	private String reason;
	private String fineAmount;
	private String bankname;
	private String dddate;
	private String noofmnths;
	private String stmnth;
	private String endmnth;
	
	
	public String getStmnth() {
		return stmnth;
	}
	public void setStmnth(String stmnth) {
		this.stmnth = stmnth;
	}
	public String getEndmnth() {
		return endmnth;
	}
	public void setEndmnth(String endmnth) {
		this.endmnth = endmnth;
	}
	public String getNoofmnths() {
		return noofmnths;
	}
	public void setNoofmnths(String noofmnths) {
		this.noofmnths = noofmnths;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getDddate() {
		return dddate;
	}
	public void setDddate(String dddate) {
		this.dddate = dddate;
	}
	public String getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String studentid;
	private String studentname;
	private String paymenyid;
	private String paymentdate;
	private String january;
	private String february;
	private String march;
	private String april;
	private String may;
	private String june;
	private String july;
	private String august;
	private String september;
	private String october;
	private String november;
	private String december;
	private String totalfee;
	private double paidamount;
	private double dueamount;
	private String paymentmode;
	private String paymenttype;
	private double totalfeeamount;
	private String paymentmonth;
	private String monthlist;
	private String currentuser;
	private String cheque_no;
	private String specialization_name;
	private String specialization_id;
	private ArrayList<ClassFeeSetupVo> feeNamelist;
	private ArrayList<ClassFeeSetupVo> termName;
	private double feeAmount;
	private String locationId;
	private String locationName;
	
	public String getStuFname() {
		return stuFname;
	}
	public void setStuFname(String stuFname) {
		this.stuFname = stuFname;
	}
	public String getStuLname() {
		return stuLname;
	}
	public void setStuLname(String stuLname) {
		this.stuLname = stuLname;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public Double getFineamt() {
		return fineamt;
	}
	public void setFineamt(Double double1) {
		this.fineamt = double1;
	}
	public String getParticularNo() {
		return particularNo;
	}
	public void setParticularNo(String particularNo) {
		this.particularNo = particularNo;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public ArrayList<ClassFeeSetupVo> getTermName() {
		return termName;
	}
	public void setTermName(ArrayList<ClassFeeSetupVo> termName) {
		this.termName = termName;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public ArrayList<ClassFeeSetupVo> getFeeNamelist() {
		return feeNamelist;
	}
	public void setFeeNamelist(ArrayList<ClassFeeSetupVo> feeNamelist) {
		this.feeNamelist = feeNamelist;
	}
	public String getSpecialization_name() {
		return specialization_name;
	}
	public void setSpecialization_name(String specialization_name) {
		this.specialization_name = specialization_name;
	}
	public String getSpecialization_id() {
		return specialization_id;
	}
	public void setSpecialization_id(String specialization_id) {
		this.specialization_id = specialization_id;
	}
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
	public String getPaymenyid() {
		return paymenyid;
	}
	public void setPaymenyid(String paymenyid) {
		this.paymenyid = paymenyid;
	}
	public String getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}
	public String getJanuary() {
		return january;
	}
	public void setJanuary(String january) {
		this.january = january;
	}
	public String getFebruary() {
		return february;
	}
	public void setFebruary(String february) {
		this.february = february;
	}
	public String getMarch() {
		return march;
	}
	public void setMarch(String march) {
		this.march = march;
	}
	public String getApril() {
		return april;
	}
	public void setApril(String april) {
		this.april = april;
	}
	public String getMay() {
		return may;
	}
	public void setMay(String may) {
		this.may = may;
	}
	public String getJune() {
		return june;
	}
	public void setJune(String june) {
		this.june = june;
	}
	public String getJuly() {
		return july;
	}
	public void setJuly(String july) {
		this.july = july;
	}
	public String getAugust() {
		return august;
	}
	public void setAugust(String august) {
		this.august = august;
	}
	public String getSeptember() {
		return september;
	}
	public void setSeptember(String september) {
		this.september = september;
	}
	public String getOctober() {
		return october;
	}
	public void setOctober(String october) {
		this.october = october;
	}
	public String getNovember() {
		return november;
	}
	public void setNovember(String november) {
		this.november = november;
	}
	public String getDecember() {
		return december;
	}
	public void setDecember(String december) {
		this.december = december;
	}
	public String getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	public double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}
	public double getDueamount() {
		return dueamount;
	}
	public void setDueamount(double dueamount) {
		this.dueamount = dueamount;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public double getTotalfeeamount() {
		return totalfeeamount;
	}
	public void setTotalfeeamount(double totalfeeamount) {
		this.totalfeeamount = totalfeeamount;
	}
	public String getPaymentmonth() {
		return paymentmonth;
	}
	public void setPaymentmonth(String paymentmonth) {
		this.paymentmonth = paymentmonth;
	}
	public String getMonthlist() {
		return monthlist;
	}
	public void setMonthlist(String monthlist) {
		this.monthlist = monthlist;
	}
	public String getCurrentuser() {
		return currentuser;
	}
	public void setCurrentuser(String currentuser) {
		this.currentuser = currentuser;
	}
	public String getCheque_no() {
		return cheque_no;
	}
	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
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
	public String getFeesettingcode() {
		return feesettingcode;
	}
	public void setFeesettingcode(String feesettingcode) {
		this.feesettingcode = feesettingcode;
	}
	public String getAllfeescheckBox() {
		return AllfeescheckBox;
	}
	public void setAllfeescheckBox(String allfeescheckBox) {
		AllfeescheckBox = allfeescheckBox;
	}
	public String getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(String feeamount) {
		this.feeamount = feeamount;
	}
	public String getAcadamicyear() {
		return acadamicyear;
	}
	public void setAcadamicyear(String acadamicyear) {
		this.acadamicyear = acadamicyear;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getChallangenarateDate() {
		return challangenarateDate;
	}
	public void setChallangenarateDate(String challangenarateDate) {
		this.challangenarateDate = challangenarateDate;
	}
	public String getChalanNo() {
		return chalanNo;
	}
	public void setChalanNo(String chalanNo) {
		this.chalanNo = chalanNo;
	}
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getStdadmissionNo() {
		return stdadmissionNo;
	}
	public void setStdadmissionNo(String stdadmissionNo) {
		this.stdadmissionNo = stdadmissionNo;
	}
	public String getTermOrder() {
		return termOrder;
	}
	public void setTermOrder(String termOrder) {
		this.termOrder = termOrder;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	private String errorMessage;
	
	
	
	public int getFeecount() {
		return feecount;
	}
	public void setFeecount(int feecount) {
		this.feecount = feecount;
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
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAccyearid() {
		return accyearid;
	}
	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	
	
}