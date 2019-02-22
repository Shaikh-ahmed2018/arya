package com.centris.campus.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayRollVo {
	
	private int sno;
	private String empId;
	private String regId;
	private String empName;
	private String deptName;
	
	private int month;
	private String monthName;
	private int year;
	private int monthDays;
	private String accountnumber;
	private int bo_of_absents;
	private int no_of_leaves;
	private int payabledays;
	
	private double ctc;
	private double basic;
	private double da;
	private double hra;
	private double ca;
	private double medicalallowances;
	private double convieance;
	private double others;
	private double grosssalary;
	
	
	private double employee_pf;
	private double employer_pf;
	private double pt;
	private double incomeTax;
	private String advanceTaken;
	private double leavededuction;
	private double otherdeduction;
	private double totaldeductions;
	
	private double netsalary;
	
	private String userId;
	
	private String pfnumber;
	private String esinumber;
	private String pannumber;
	
	private String teacherId;
	private String staffname;
	private double taxableTotalAmount;
	private double taxworkTaxableIncome;
	private double taxworkNetTax;
	private double taxworkDedTillDate;
	private double taxworkToBeDeducted;
	private double taxworkPerMonth;
	private String locationId;
	private String[] location;
	private String[] teacher;
	private String[] taxPerMonth;
	private String designationname;
	private double performanceIncentive;
	private double specialAllowance;
	private double foodAllowance;
	private double childEdu;
	private double arrears;
	private double reimbursement;
	private double internetExpense;
	private double driverSalary;
	private double leaveEncash;
	private double medicalReimbursement;
	private double totalsalary;
	
	private String currmonth;
	private String curryear;
	private String yearval;
	private String createdby;
	private String createddate;
	private String monthvalcode;
	private String yearvalcode;
	private String createdtime;
	private String monthval;
	private String status;
	private int monthcount;
	private String locationName;
	private String yearName;
	private String doj;
	private String accyearId;
	
	private double basic_earned;
	private double da_earned;
	private double Convinience_earned;
	private double hra_earned;
	private double medical_earned;
	private double performanceIncentive_earned;
	private double specialAllowance_earned;
	private double foodAllowance_earned;
	private double childEdu_earned;
	private double arrears_earned;
	private double reimbursement_earned;
	private double internetExpense_earned;
	private double driverSalary_earned;
	private double leaveEncash_earned;
	private double medicalReimbursement_earned;
	private double totalsalary_earned;
	private double others_earned;
	private double pfempr_earned;
	private double pfempy_earned;
	private double esiempr_earned;
	private double esiempy_earned;
	private double incometax_earned;
	private double pf;
	private double esi;
	private double ptax_earned;
	private double takehome;
	private double netpay;
	
	private String[] monthdays;
	private String[] payableday;
	private String[] earnbasic;
	private String[] earnda;
	private String[] earnhra;
	private String[] earnconvience;
	private String[] earnperform;
	private String[] earnfood;
	private String[] earnspecial;
	private String[] earnchild;
	private String[] earnarrears;
	private String[] earnreimburse;
	private String[] earninternet;
	private String[] earndriver;
	private String[] earnleave;
	private String[] earnmedical;
	private String[] earntotal;
	private String[] earnpfempr;
	private String[] earnpfempy;
	private String[] earnesiempr;
	private String[] earnesiempy;
	private String[] monthlyptax;
	private String[] monthlyincometax;
	private String[] monthlytotaldeduction;
	private String[] monthlytakehome;
	private String[] monthlysalpending;
	private String[] monthlyadvance;
	private String[] monthlynetpay;
	private String locationAddress;
	private String locationPhone;
	private String paymentType;
	private String bankname;
	private String bankaccNo;
	private String monthYear;
	private HashMap<String,String> monthYearSet;
	
	
	public HashMap<String, String> getMonthYearSet() {
		return monthYearSet;
	}

	public void setMonthYearSet(HashMap<String, String> monthYearSet) {
		this.monthYearSet = monthYearSet;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankaccNo() {
		return bankaccNo;
	}

	public void setBankaccNo(String bankaccNo) {
		this.bankaccNo = bankaccNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getLocationPhone() {
		return locationPhone;
	}

	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	private List<PayRollVo> yearcode;
	
	
	public List<PayRollVo> getYearcode() {
		return yearcode;
	}

	public void setYearcode(List<PayRollVo> yearcode) {
		this.yearcode = yearcode;
	}

	public String[] getPayableday() {
		return payableday;
	}

	public void setPayableday(String[] payableday) {
		this.payableday = payableday;
	}

	public String[] getMonthdays() {
		return monthdays;
	}

	public void setMonthdays(String[] monthdays) {
		this.monthdays = monthdays;
	}

	public String[] getEarnbasic() {
		return earnbasic;
	}

	public void setEarnbasic(String[] earnbasic) {
		this.earnbasic = earnbasic;
	}

	public String[] getEarnda() {
		return earnda;
	}

	public void setEarnda(String[] earnda) {
		this.earnda = earnda;
	}

	public String[] getEarnhra() {
		return earnhra;
	}

	public void setEarnhra(String[] earnhra) {
		this.earnhra = earnhra;
	}

	public String[] getEarnconvience() {
		return earnconvience;
	}

	public void setEarnconvience(String[] earnconvience) {
		this.earnconvience = earnconvience;
	}

	public String[] getEarnperform() {
		return earnperform;
	}

	public void setEarnperform(String[] earnperform) {
		this.earnperform = earnperform;
	}

	public String[] getEarnfood() {
		return earnfood;
	}

	public void setEarnfood(String[] earnfood) {
		this.earnfood = earnfood;
	}

	public String[] getEarnspecial() {
		return earnspecial;
	}

	public void setEarnspecial(String[] earnspecial) {
		this.earnspecial = earnspecial;
	}

	public String[] getEarnchild() {
		return earnchild;
	}

	public void setEarnchild(String[] earnchild) {
		this.earnchild = earnchild;
	}

	public String[] getEarnarrears() {
		return earnarrears;
	}

	public void setEarnarrears(String[] earnarrears) {
		this.earnarrears = earnarrears;
	}

	public String[] getEarnreimburse() {
		return earnreimburse;
	}

	public void setEarnreimburse(String[] earnreimburse) {
		this.earnreimburse = earnreimburse;
	}

	public String[] getEarninternet() {
		return earninternet;
	}

	public void setEarninternet(String[] earninternet) {
		this.earninternet = earninternet;
	}

	public String[] getEarndriver() {
		return earndriver;
	}

	public void setEarndriver(String[] earndriver) {
		this.earndriver = earndriver;
	}

	public String[] getEarnleave() {
		return earnleave;
	}

	public void setEarnleave(String[] earnleave) {
		this.earnleave = earnleave;
	}

	public String[] getEarnmedical() {
		return earnmedical;
	}

	public void setEarnmedical(String[] earnmedical) {
		this.earnmedical = earnmedical;
	}

	public String[] getEarntotal() {
		return earntotal;
	}

	public void setEarntotal(String[] earntotal) {
		this.earntotal = earntotal;
	}

	public String[] getEarnpfempr() {
		return earnpfempr;
	}

	public void setEarnpfempr(String[] earnpfempr) {
		this.earnpfempr = earnpfempr;
	}

	public String[] getEarnpfempy() {
		return earnpfempy;
	}

	public void setEarnpfempy(String[] earnpfempy) {
		this.earnpfempy = earnpfempy;
	}

	public String[] getEarnesiempr() {
		return earnesiempr;
	}

	public void setEarnesiempr(String[] earnesiempr) {
		this.earnesiempr = earnesiempr;
	}

	public String[] getEarnesiempy() {
		return earnesiempy;
	}

	public void setEarnesiempy(String[] earnesiempy) {
		this.earnesiempy = earnesiempy;
	}

	public String[] getMonthlyptax() {
		return monthlyptax;
	}

	public void setMonthlyptax(String[] monthlyptax) {
		this.monthlyptax = monthlyptax;
	}

	public String[] getMonthlyincometax() {
		return monthlyincometax;
	}

	public void setMonthlyincometax(String[] monthlyincometax) {
		this.monthlyincometax = monthlyincometax;
	}

	public String[] getMonthlytotaldeduction() {
		return monthlytotaldeduction;
	}

	public void setMonthlytotaldeduction(String[] monthlytotaldeduction) {
		this.monthlytotaldeduction = monthlytotaldeduction;
	}

	public String[] getMonthlytakehome() {
		return monthlytakehome;
	}

	public void setMonthlytakehome(String[] monthlytakehome) {
		this.monthlytakehome = monthlytakehome;
	}

	public String[] getMonthlysalpending() {
		return monthlysalpending;
	}

	public void setMonthlysalpending(String[] monthlysalpending) {
		this.monthlysalpending = monthlysalpending;
	}

	public String[] getMonthlyadvance() {
		return monthlyadvance;
	}

	public void setMonthlyadvance(String[] monthlyadvance) {
		this.monthlyadvance = monthlyadvance;
	}

	public String[] getMonthlynetpay() {
		return monthlynetpay;
	}

	public void setMonthlynetpay(String[] monthlynetpay) {
		this.monthlynetpay = monthlynetpay;
	}

	public double getTakehome() {
		return takehome;
	}

	public void setTakehome(double takehome) {
		this.takehome = takehome;
	}

	public double getNetpay() {
		return netpay;
	}

	public void setNetpay(double netpay) {
		this.netpay = netpay;
	}

	public double getPtax_earned() {
		return ptax_earned;
	}

	public void setPtax_earned(double ptax_earned) {
		this.ptax_earned = ptax_earned;
	}

	public String getAccyearId() {
		return accyearId;
	}

	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}

	public double getPf() {
		return pf;
	}

	public void setPf(double pf) {
		this.pf = pf;
	}

	public double getEsi() {
		return esi;
	}

	public void setEsi(double esi) {
		this.esi = esi;
	}

	public double getPfempy_earned() {
		return pfempy_earned;
	}

	public void setPfempy_earned(double pfempy_earned) {
		this.pfempy_earned = pfempy_earned;
	}

	public double getEsiempr_earned() {
		return esiempr_earned;
	}

	public void setEsiempr_earned(double esiempr_earned) {
		this.esiempr_earned = esiempr_earned;
	}

	public double getEsiempy_earned() {
		return esiempy_earned;
	}

	public void setEsiempy_earned(double esiempy_earned) {
		this.esiempy_earned = esiempy_earned;
	}

	public double getIncometax_earned() {
		return incometax_earned;
	}

	public void setIncometax_earned(double incometax_earned) {
		this.incometax_earned = incometax_earned;
	}

	public double getPfempr_earned() {
		return pfempr_earned;
	}

	public void setPfempr_earned(double pfempr_earned) {
		this.pfempr_earned = pfempr_earned;
	}

	public double getBasic_earned() {
		return basic_earned;
	}

	public void setBasic_earned(double basic_earned) {
		this.basic_earned = basic_earned;
	}

	public double getDa_earned() {
		return da_earned;
	}

	public void setDa_earned(double da_earned) {
		this.da_earned = da_earned;
	}

	public double getConvinience_earned() {
		return Convinience_earned;
	}

	public void setConvinience_earned(double convinience_earned) {
		Convinience_earned = convinience_earned;
	}

	public double getHra_earned() {
		return hra_earned;
	}

	public void setHra_earned(double hra_earned) {
		this.hra_earned = hra_earned;
	}

	public double getMedical_earned() {
		return medical_earned;
	}

	public void setMedical_earned(double medical_earned) {
		this.medical_earned = medical_earned;
	}

	public double getPerformanceIncentive_earned() {
		return performanceIncentive_earned;
	}

	public void setPerformanceIncentive_earned(double performanceIncentive_earned) {
		this.performanceIncentive_earned = performanceIncentive_earned;
	}

	public double getSpecialAllowance_earned() {
		return specialAllowance_earned;
	}

	public void setSpecialAllowance_earned(double specialAllowance_earned) {
		this.specialAllowance_earned = specialAllowance_earned;
	}

	public double getFoodAllowance_earned() {
		return foodAllowance_earned;
	}

	public void setFoodAllowance_earned(double foodAllowance_earned) {
		this.foodAllowance_earned = foodAllowance_earned;
	}

	public double getChildEdu_earned() {
		return childEdu_earned;
	}

	public void setChildEdu_earned(double childEdu_earned) {
		this.childEdu_earned = childEdu_earned;
	}

	public double getArrears_earned() {
		return arrears_earned;
	}

	public void setArrears_earned(double arrears_earned) {
		this.arrears_earned = arrears_earned;
	}

	public double getReimbursement_earned() {
		return reimbursement_earned;
	}

	public void setReimbursement_earned(double reimbursement_earned) {
		this.reimbursement_earned = reimbursement_earned;
	}

	public double getInternetExpense_earned() {
		return internetExpense_earned;
	}

	public void setInternetExpense_earned(double internetExpense_earned) {
		this.internetExpense_earned = internetExpense_earned;
	}

	public double getDriverSalary_earned() {
		return driverSalary_earned;
	}

	public void setDriverSalary_earned(double driverSalary_earned) {
		this.driverSalary_earned = driverSalary_earned;
	}

	public double getLeaveEncash_earned() {
		return leaveEncash_earned;
	}

	public void setLeaveEncash_earned(double leaveEncash_earned) {
		this.leaveEncash_earned = leaveEncash_earned;
	}

	public double getMedicalReimbursement_earned() {
		return medicalReimbursement_earned;
	}

	public void setMedicalReimbursement_earned(double medicalReimbursement_earned) {
		this.medicalReimbursement_earned = medicalReimbursement_earned;
	}

	public double getTotalsalary_earned() {
		return totalsalary_earned;
	}

	public void setTotalsalary_earned(double totalsalary_earned) {
		this.totalsalary_earned = totalsalary_earned;
	}

	public double getOthers_earned() {
		return others_earned;
	}

	public void setOthers_earned(double others_earned) {
		this.others_earned = others_earned;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public double getTotalsalary() {
		return totalsalary;
	}

	public void setTotalsalary(double totalsalary) {
		this.totalsalary = totalsalary;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getMonthcount() {
		return monthcount;
	}

	public void setMonthcount(int monthcount) {
		this.monthcount = monthcount;
	}

	public String getMonthval() {
		return monthval;
	}

	public void setMonthval(String monthval) {
		this.monthval = monthval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYearval() {
		return yearval;
	}

	public void setYearval(String yearval) {
		this.yearval = yearval;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getMonthvalcode() {
		return monthvalcode;
	}

	public void setMonthvalcode(String monthvalcode) {
		this.monthvalcode = monthvalcode;
	}

	public String getYearvalcode() {
		return yearvalcode;
	}

	public void setYearvalcode(String yearvalcode) {
		this.yearvalcode = yearvalcode;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getCurrmonth() {
		return currmonth;
	}

	public void setCurrmonth(String currmonth) {
		this.currmonth = currmonth;
	}

	public String getCurryear() {
		return curryear;
	}

	public void setCurryear(String curryear) {
		this.curryear = curryear;
	}

	public double getPerformanceIncentive() {
		return performanceIncentive;
	}

	public void setPerformanceIncentive(double performanceIncentive) {
		this.performanceIncentive = performanceIncentive;
	}

	public double getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public double getFoodAllowance() {
		return foodAllowance;
	}

	public void setFoodAllowance(double foodAllowance) {
		this.foodAllowance = foodAllowance;
	}

	public double getChildEdu() {
		return childEdu;
	}

	public void setChildEdu(double childEdu) {
		this.childEdu = childEdu;
	}

	public double getArrears() {
		return arrears;
	}

	public void setArrears(double arrears) {
		this.arrears = arrears;
	}

	public double getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(double reimbursement) {
		this.reimbursement = reimbursement;
	}

	public double getInternetExpense() {
		return internetExpense;
	}

	public void setInternetExpense(double internetExpense) {
		this.internetExpense = internetExpense;
	}

	public double getDriverSalary() {
		return driverSalary;
	}

	public void setDriverSalary(double driverSalary) {
		this.driverSalary = driverSalary;
	}

	public double getLeaveEncash() {
		return leaveEncash;
	}

	public void setLeaveEncash(double leaveEncash) {
		this.leaveEncash = leaveEncash;
	}

	public double getMedicalReimbursement() {
		return medicalReimbursement;
	}

	public void setMedicalReimbursement(double medicalReimbursement) {
		this.medicalReimbursement = medicalReimbursement;
	}

	public String getDesignationname() {
		return designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	public String[] getTaxPerMonth() {
		return taxPerMonth;
	}

	public void setTaxPerMonth(String[] taxPerMonth) {
		this.taxPerMonth = taxPerMonth;
	}

	public String[] getLocation() {
		return location;
	}

	public void setLocation(String[] location) {
		this.location = location;
	}

	public String[] getTeacher() {
		return teacher;
	}

	public void setTeacher(String[] teacher) {
		this.teacher = teacher;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public double getTaxableTotalAmount() {
		return taxableTotalAmount;
	}

	public void setTaxableTotalAmount(double taxableTotalAmount) {
		this.taxableTotalAmount = taxableTotalAmount;
	}

	public double getTaxworkTaxableIncome() {
		return taxworkTaxableIncome;
	}

	public void setTaxworkTaxableIncome(double taxworkTaxableIncome) {
		this.taxworkTaxableIncome = taxworkTaxableIncome;
	}

	public double getTaxworkNetTax() {
		return taxworkNetTax;
	}

	public void setTaxworkNetTax(double taxworkNetTax) {
		this.taxworkNetTax = taxworkNetTax;
	}

	public double getTaxworkDedTillDate() {
		return taxworkDedTillDate;
	}

	public void setTaxworkDedTillDate(double taxworkDedTillDate) {
		this.taxworkDedTillDate = taxworkDedTillDate;
	}

	public double getTaxworkToBeDeducted() {
		return taxworkToBeDeducted;
	}

	public void setTaxworkToBeDeducted(double taxworkToBeDeducted) {
		this.taxworkToBeDeducted = taxworkToBeDeducted;
	}

	public double getTaxworkPerMonth() {
		return taxworkPerMonth;
	}

	public void setTaxworkPerMonth(double taxworkPerMonth) {
		this.taxworkPerMonth = taxworkPerMonth;
	}

	public String getPfnumber() {
		return pfnumber;
	}

	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}

	public String getEsinumber() {
		return esinumber;
	}

	public void setEsinumber(String esinumber) {
		this.esinumber = esinumber;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonthDays() {
		return monthDays;
	}

	public void setMonthDays(int monthDays) {
		this.monthDays = monthDays;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getBo_of_absents() {
		return bo_of_absents;
	}

	public void setBo_of_absents(int bo_of_absents) {
		this.bo_of_absents = bo_of_absents;
	}

	public int getNo_of_leaves() {
		return no_of_leaves;
	}

	public void setNo_of_leaves(int no_of_leaves) {
		this.no_of_leaves = no_of_leaves;
	}

	public int getPayabledays() {
		return payabledays;
	}

	public void setPayabledays(int payabledays) {
		this.payabledays = payabledays;
	}

	public double getCtc() {
		return ctc;
	}

	public void setCtc(double ctc) {
		this.ctc = ctc;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getCa() {
		return ca;
	}

	public void setCa(double ca) {
		this.ca = ca;
	}

	public double getMedicalallowances() {
		return medicalallowances;
	}

	public void setMedicalallowances(double medicalallowances) {
		this.medicalallowances = medicalallowances;
	}

	public double getConvieance() {
		return convieance;
	}

	public void setConvieance(double convieance) {
		this.convieance = convieance;
	}

	public double getOthers() {
		return others;
	}

	public void setOthers(double others) {
		this.others = others;
	}

	public double getGrosssalary() {
		return grosssalary;
	}

	public void setGrosssalary(double grosssalary) {
		this.grosssalary = grosssalary;
	}

	public double getEmployee_pf() {
		return employee_pf;
	}

	public void setEmployee_pf(double employee_pf) {
		this.employee_pf = employee_pf;
	}

	public double getEmployer_pf() {
		return employer_pf;
	}

	public void setEmployer_pf(double employer_pf) {
		this.employer_pf = employer_pf;
	}

	public double getPt() {
		return pt;
	}

	public void setPt(double pt) {
		this.pt = pt;
	}

	public double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public String getAdvanceTaken() {
		return advanceTaken;
	}

	public void setAdvanceTaken(String advanceTaken) {
		this.advanceTaken = advanceTaken;
	}

	public double getLeavededuction() {
		return leavededuction;
	}

	public void setLeavededuction(double leavededuction) {
		this.leavededuction = leavededuction;
	}

	public double getOtherdeduction() {
		return otherdeduction;
	}

	public void setOtherdeduction(double otherdeduction) {
		this.otherdeduction = otherdeduction;
	}

	public double getTotaldeductions() {
		return totaldeductions;
	}

	public void setTotaldeductions(double totaldeductions) {
		this.totaldeductions = totaldeductions;
	}

	public double getNetsalary() {
		return netsalary;
	}

	public void setNetsalary(double netsalary) {
		this.netsalary = netsalary;
	}
	
	
	

	
}
