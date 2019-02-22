package com.centris.campus.forms;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class StaffEmployementForm extends ActionForm {
	
	private int year;
	private int month;
	private double basic;
	private double hra;
	private double employerpf;
	private double employeepf;
	private double pt;
	private double ca;
	private long bankaccnumber;
	private String leavedeductions;
	private String latedeductions;
	private String ot;
	private String empid="";
	private double incometax;
	private String employeepfno;
	private double grosssalary;
	private double others;
	private double ctc;
	private String empname;
	private  double employerESI;
	private  double employeeESI;
	private  double dedmployerESI;
	
	private String paymenttype;
	private double demployerpf;
	private String bankname;
	private String IFSCCode;
	private int hrapercentag;
	
	private String email;
	private long payabledays;
	private String designation;
	private String department;
	private String pfnumber;
	private String hempid;
	
	private double medicalallowances;
	private double telephonereim;
	private double perfincentive;
	private double shiftallowance;
	private double specialallowance;
	private double otherallowance;
	private double foodallowance;
	private double washingallowance;
	private Timestamp createddate;
	private String createdby;
	private String hemloyeeid;
	private double da;
	private double basictotal;
	private double datotal;
	private double hratotal;
	private double allowance;
	private double allowancetotal;
	private double childedu;
	private double childedutotal;
	private double special;
	private double specialtotal;
	private double arrears;
	private double arrearstotal;
	private double performace;
	private double performacetotal;
	private double medical;
	private double medicaltotal;
	private double leave;
	private double leavetotal;
	private double food;
	private double foodtotal;
	private double reimburse;
	private double reimbursetotal;
	private double internet;
	private double internettotal;
	private double driver;
	private double drivertotal;
	private double other;
	private double othertotal;
	private double totalsalary;
	private double yearlytotalamount;
	private double pfemployer;
	private double pfamount;
	private double yearlytotalpfamount;
	private double esiemployer;
	private double esiamount;
	private double yearlytotalesiamount;
	private String accyearId;
	private String locationId;
	
	private double houseincomefromother;
	private double houseintrestloan;
	private double otherIncomeSelf;
	private double totalofOtherIncome;
	private double hraExemptionSection;
	private double childeducationexemption;
	private double medicalsectionexemption;
	private double transportsectionexemption;
	private double ltasectionexemption;
	private double foodsectionexemption;
	private double telephoneexemption;
	private double carreimburseexemption;
	private double internetsectionexemption;
	private double driversectionexemption;
	private double otherssectionexpense;
	private double totalSectionAmount;
	private double empprovidentfund;
	private double tutionfee;
	private double fixeddeposit;
	private double lifeinspremium;
	private double mutualfund;
	private double nationalsavecertificate;
	private double accruednsci;
	private double publicpf;
	private double repaymenthouseloan;
	private double unitLinkInsPlan;
	private double seniorcitizensave;
	private double postsavingbank;
	private double othersection;
	private double totalChapter;
	private double mediclaimself;
	private double mediclaimparents;
	private double meditreatbeloweighty;
	private double meditreataboveeighty;
	private double interesteduloan;
	private double additionalcontribute;
	private double deductionofphysicalbelow;
	private double deductionofphysicalabove;
	private double rajivgandhiequity;
	private double eightyttainterestsaving;
	private double eightyddmedicaltreatbelow;
	private double eightyddmedicaltreatabove;
	private double totalChapter80Up;
	private double mediclaimparentsabove;
	private String housefromdate;
	private String housetodate;
	private String housecity;
	private String landlordname;
	private String houseaddress;
	private String pancardno;
	private double houseamount;
	private double taxablebasicperYear;
	private double taxworkproftax;
	private double taxhrafoutyper;
	private double taxhratenper;
	private double excemptionhra;
	private double taxablehra;
	private double excemptiontransport;
	private double taxabletransport;
	private double totalInvest80c;
	private double totaldeductionunder;
	private double taxworkanyOtherIncome;
	private double taxablespecial;
	private double taxableother;
	private double taxableTotalAmount;
	private double taxableIncomeAmount;
	private double totalTaxAmount;
	private double educationcess;
	private double highereducationcess;
	private double totaleducationcess;
	private double netTax;
	private double hraperYear;
	private double taxworkPerMonth;
	private double taxworkToBeDeducted;
	private double monthlyhraamount;
	private double tdsexmption;
	private double demployeepf;
	private double demployeeESI;
	private double tdsundervia;
	private double taxableExemption;
	private double taxableAnyOtherIncome;
	private double taxworkUnderCapterVIA;
	
	
	public double getTaxableExemption() {
		return taxableExemption;
	}

	public void setTaxableExemption(double taxableExemption) {
		this.taxableExemption = taxableExemption;
	}

	public double getTaxableAnyOtherIncome() {
		return taxableAnyOtherIncome;
	}

	public void setTaxableAnyOtherIncome(double taxableAnyOtherIncome) {
		this.taxableAnyOtherIncome = taxableAnyOtherIncome;
	}

	public double getTaxworkUnderCapterVIA() {
		return taxworkUnderCapterVIA;
	}

	public void setTaxworkUnderCapterVIA(double taxworkUnderCapterVIA) {
		this.taxworkUnderCapterVIA = taxworkUnderCapterVIA;
	}

	public double getTdsundervia() {
		return tdsundervia;
	}

	public void setTdsundervia(double tdsundervia) {
		this.tdsundervia = tdsundervia;
	}

	public double getDemployeeESI() {
		return demployeeESI;
	}

	public void setDemployeeESI(double demployeeESI) {
		this.demployeeESI = demployeeESI;
	}

	public double getDemployeepf() {
		return demployeepf;
	}

	public void setDemployeepf(double demployeepf) {
		this.demployeepf = demployeepf;
	}

	public double getTdsexmption() {
		return tdsexmption;
	}

	public void setTdsexmption(double tdsexmption) {
		this.tdsexmption = tdsexmption;
	}
	private int hrapercentage;
	
	public double getMonthlyhraamount() {
		return monthlyhraamount;
	}

	public void setMonthlyhraamount(double monthlyhraamount) {
		this.monthlyhraamount = monthlyhraamount;
	}

	public int getHrapercentage() {
		return hrapercentage;
	}

	public void setHrapercentage(int hrapercentage) {
		this.hrapercentage = hrapercentage;
	}

	public double getTaxworkPerMonth() {
		return taxworkPerMonth;
	}

	public void setTaxworkPerMonth(double taxworkPerMonth) {
		this.taxworkPerMonth = taxworkPerMonth;
	}

	public double getTaxworkToBeDeducted() {
		return taxworkToBeDeducted;
	}

	public void setTaxworkToBeDeducted(double taxworkToBeDeducted) {
		this.taxworkToBeDeducted = taxworkToBeDeducted;
	}

	public double getTaxablebasicperYear() {
		return taxablebasicperYear;
	}

	public void setTaxablebasicperYear(double taxablebasicperYear) {
		this.taxablebasicperYear = taxablebasicperYear;
	}

	public double getTaxworkproftax() {
		return taxworkproftax;
	}

	public void setTaxworkproftax(double taxworkproftax) {
		this.taxworkproftax = taxworkproftax;
	}

	public double getTaxhrafoutyper() {
		return taxhrafoutyper;
	}

	public void setTaxhrafoutyper(double taxhrafoutyper) {
		this.taxhrafoutyper = taxhrafoutyper;
	}

	public double getTaxhratenper() {
		return taxhratenper;
	}

	public void setTaxhratenper(double taxhratenper) {
		this.taxhratenper = taxhratenper;
	}

	public double getExcemptionhra() {
		return excemptionhra;
	}

	public void setExcemptionhra(double excemptionhra) {
		this.excemptionhra = excemptionhra;
	}

	public double getTaxablehra() {
		return taxablehra;
	}

	public void setTaxablehra(double taxablehra) {
		this.taxablehra = taxablehra;
	}

	public double getExcemptiontransport() {
		return excemptiontransport;
	}

	public void setExcemptiontransport(double excemptiontransport) {
		this.excemptiontransport = excemptiontransport;
	}

	public double getTaxabletransport() {
		return taxabletransport;
	}

	public void setTaxabletransport(double taxabletransport) {
		this.taxabletransport = taxabletransport;
	}

	public double getTotalInvest80c() {
		return totalInvest80c;
	}

	public void setTotalInvest80c(double totalInvest80c) {
		this.totalInvest80c = totalInvest80c;
	}

	public double getTotaldeductionunder() {
		return totaldeductionunder;
	}

	public void setTotaldeductionunder(double totaldeductionunder) {
		this.totaldeductionunder = totaldeductionunder;
	}

	public double getTaxworkanyOtherIncome() {
		return taxworkanyOtherIncome;
	}

	public void setTaxworkanyOtherIncome(double taxworkanyOtherIncome) {
		this.taxworkanyOtherIncome = taxworkanyOtherIncome;
	}

	public double getTaxablespecial() {
		return taxablespecial;
	}

	public void setTaxablespecial(double taxablespecial) {
		this.taxablespecial = taxablespecial;
	}

	public double getTaxableother() {
		return taxableother;
	}

	public void setTaxableother(double taxableother) {
		this.taxableother = taxableother;
	}

	public double getTaxableTotalAmount() {
		return taxableTotalAmount;
	}

	public void setTaxableTotalAmount(double taxableTotalAmount) {
		this.taxableTotalAmount = taxableTotalAmount;
	}

	public double getTaxableIncomeAmount() {
		return taxableIncomeAmount;
	}

	public void setTaxableIncomeAmount(double taxableIncomeAmount) {
		this.taxableIncomeAmount = taxableIncomeAmount;
	}

	public double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public double getEducationcess() {
		return educationcess;
	}

	public void setEducationcess(double educationcess) {
		this.educationcess = educationcess;
	}

	public double getHighereducationcess() {
		return highereducationcess;
	}

	public void setHighereducationcess(double highereducationcess) {
		this.highereducationcess = highereducationcess;
	}

	public double getTotaleducationcess() {
		return totaleducationcess;
	}

	public void setTotaleducationcess(double totaleducationcess) {
		this.totaleducationcess = totaleducationcess;
	}

	public double getNetTax() {
		return netTax;
	}

	public void setNetTax(double netTax) {
		this.netTax = netTax;
	}

	public double getHraperYear() {
		return hraperYear;
	}

	public void setHraperYear(double hraperYear) {
		this.hraperYear = hraperYear;
	}

	public String getHousefromdate() {
		return housefromdate;
	}

	public void setHousefromdate(String housefromdate) {
		this.housefromdate = housefromdate;
	}

	public String getHousetodate() {
		return housetodate;
	}

	public void setHousetodate(String housetodate) {
		this.housetodate = housetodate;
	}

	public String getHousecity() {
		return housecity;
	}

	public void setHousecity(String housecity) {
		this.housecity = housecity;
	}

	public String getLandlordname() {
		return landlordname;
	}

	public void setLandlordname(String landlordname) {
		this.landlordname = landlordname;
	}

	public String getHouseaddress() {
		return houseaddress;
	}

	public void setHouseaddress(String houseaddress) {
		this.houseaddress = houseaddress;
	}

	public String getPancardno() {
		return pancardno;
	}

	public void setPancardno(String pancardno) {
		this.pancardno = pancardno;
	}

	public double getHouseamount() {
		return houseamount;
	}

	public void setHouseamount(double houseamount) {
		this.houseamount = houseamount;
	}

	public double getMediclaimparentsabove() {
		return mediclaimparentsabove;
	}

	public void setMediclaimparentsabove(double mediclaimparentsabove) {
		this.mediclaimparentsabove = mediclaimparentsabove;
	}

	public double getHouseincomefromother() {
		return houseincomefromother;
	}

	public void setHouseincomefromother(double houseincomefromother) {
		this.houseincomefromother = houseincomefromother;
	}

	public double getHouseintrestloan() {
		return houseintrestloan;
	}

	public void setHouseintrestloan(double houseintrestloan) {
		this.houseintrestloan = houseintrestloan;
	}

	public double getOtherIncomeSelf() {
		return otherIncomeSelf;
	}

	public void setOtherIncomeSelf(double otherIncomeSelf) {
		this.otherIncomeSelf = otherIncomeSelf;
	}

	public double getTotalofOtherIncome() {
		return totalofOtherIncome;
	}

	public void setTotalofOtherIncome(double totalofOtherIncome) {
		this.totalofOtherIncome = totalofOtherIncome;
	}

	public double getHraExemptionSection() {
		return hraExemptionSection;
	}

	public void setHraExemptionSection(double hraExemptionSection) {
		this.hraExemptionSection = hraExemptionSection;
	}

	public double getChildeducationexemption() {
		return childeducationexemption;
	}

	public void setChildeducationexemption(double childeducationexemption) {
		this.childeducationexemption = childeducationexemption;
	}

	public double getMedicalsectionexemption() {
		return medicalsectionexemption;
	}

	public void setMedicalsectionexemption(double medicalsectionexemption) {
		this.medicalsectionexemption = medicalsectionexemption;
	}

	public double getTransportsectionexemption() {
		return transportsectionexemption;
	}

	public void setTransportsectionexemption(double transportsectionexemption) {
		this.transportsectionexemption = transportsectionexemption;
	}

	public double getLtasectionexemption() {
		return ltasectionexemption;
	}

	public void setLtasectionexemption(double ltasectionexemption) {
		this.ltasectionexemption = ltasectionexemption;
	}

	public double getFoodsectionexemption() {
		return foodsectionexemption;
	}

	public void setFoodsectionexemption(double foodsectionexemption) {
		this.foodsectionexemption = foodsectionexemption;
	}

	public double getTelephoneexemption() {
		return telephoneexemption;
	}

	public void setTelephoneexemption(double telephoneexemption) {
		this.telephoneexemption = telephoneexemption;
	}

	public double getCarreimburseexemption() {
		return carreimburseexemption;
	}

	public void setCarreimburseexemption(double carreimburseexemption) {
		this.carreimburseexemption = carreimburseexemption;
	}

	public double getInternetsectionexemption() {
		return internetsectionexemption;
	}

	public void setInternetsectionexemption(double internetsectionexemption) {
		this.internetsectionexemption = internetsectionexemption;
	}

	public double getDriversectionexemption() {
		return driversectionexemption;
	}

	public void setDriversectionexemption(double driversectionexemption) {
		this.driversectionexemption = driversectionexemption;
	}

	public double getOtherssectionexpense() {
		return otherssectionexpense;
	}

	public void setOtherssectionexpense(double otherssectionexpense) {
		this.otherssectionexpense = otherssectionexpense;
	}

	public double getTotalSectionAmount() {
		return totalSectionAmount;
	}

	public void setTotalSectionAmount(double totalSectionAmount) {
		this.totalSectionAmount = totalSectionAmount;
	}

	public double getEmpprovidentfund() {
		return empprovidentfund;
	}

	public void setEmpprovidentfund(double empprovidentfund) {
		this.empprovidentfund = empprovidentfund;
	}

	public double getTutionfee() {
		return tutionfee;
	}

	public void setTutionfee(double tutionfee) {
		this.tutionfee = tutionfee;
	}

	public double getFixeddeposit() {
		return fixeddeposit;
	}

	public void setFixeddeposit(double fixeddeposit) {
		this.fixeddeposit = fixeddeposit;
	}

	public double getLifeinspremium() {
		return lifeinspremium;
	}

	public void setLifeinspremium(double lifeinspremium) {
		this.lifeinspremium = lifeinspremium;
	}

	public double getMutualfund() {
		return mutualfund;
	}

	public void setMutualfund(double mutualfund) {
		this.mutualfund = mutualfund;
	}

	public double getNationalsavecertificate() {
		return nationalsavecertificate;
	}

	public void setNationalsavecertificate(double nationalsavecertificate) {
		this.nationalsavecertificate = nationalsavecertificate;
	}

	public double getAccruednsci() {
		return accruednsci;
	}

	public void setAccruednsci(double accruednsci) {
		this.accruednsci = accruednsci;
	}

	public double getPublicpf() {
		return publicpf;
	}

	public void setPublicpf(double publicpf) {
		this.publicpf = publicpf;
	}

	public double getRepaymenthouseloan() {
		return repaymenthouseloan;
	}

	public void setRepaymenthouseloan(double repaymenthouseloan) {
		this.repaymenthouseloan = repaymenthouseloan;
	}

	public double getUnitLinkInsPlan() {
		return unitLinkInsPlan;
	}

	public void setUnitLinkInsPlan(double unitLinkInsPlan) {
		this.unitLinkInsPlan = unitLinkInsPlan;
	}

	public double getSeniorcitizensave() {
		return seniorcitizensave;
	}

	public void setSeniorcitizensave(double seniorcitizensave) {
		this.seniorcitizensave = seniorcitizensave;
	}

	public double getPostsavingbank() {
		return postsavingbank;
	}

	public void setPostsavingbank(double postsavingbank) {
		this.postsavingbank = postsavingbank;
	}

	public double getOthersection() {
		return othersection;
	}

	public void setOthersection(double othersection) {
		this.othersection = othersection;
	}

	public double getTotalChapter() {
		return totalChapter;
	}

	public void setTotalChapter(double totalChapter) {
		this.totalChapter = totalChapter;
	}

	public double getMediclaimself() {
		return mediclaimself;
	}

	public void setMediclaimself(double mediclaimself) {
		this.mediclaimself = mediclaimself;
	}

	public double getMediclaimparents() {
		return mediclaimparents;
	}

	public void setMediclaimparents(double mediclaimparents) {
		this.mediclaimparents = mediclaimparents;
	}

	public double getMeditreatbeloweighty() {
		return meditreatbeloweighty;
	}

	public void setMeditreatbeloweighty(double meditreatbeloweighty) {
		this.meditreatbeloweighty = meditreatbeloweighty;
	}

	public double getMeditreataboveeighty() {
		return meditreataboveeighty;
	}

	public void setMeditreataboveeighty(double meditreataboveeighty) {
		this.meditreataboveeighty = meditreataboveeighty;
	}

	public double getInteresteduloan() {
		return interesteduloan;
	}

	public void setInteresteduloan(double interesteduloan) {
		this.interesteduloan = interesteduloan;
	}

	public double getAdditionalcontribute() {
		return additionalcontribute;
	}

	public void setAdditionalcontribute(double additionalcontribute) {
		this.additionalcontribute = additionalcontribute;
	}

	public double getDeductionofphysicalbelow() {
		return deductionofphysicalbelow;
	}

	public void setDeductionofphysicalbelow(double deductionofphysicalbelow) {
		this.deductionofphysicalbelow = deductionofphysicalbelow;
	}

	public double getDeductionofphysicalabove() {
		return deductionofphysicalabove;
	}

	public void setDeductionofphysicalabove(double deductionofphysicalabove) {
		this.deductionofphysicalabove = deductionofphysicalabove;
	}

	public double getRajivgandhiequity() {
		return rajivgandhiequity;
	}

	public void setRajivgandhiequity(double rajivgandhiequity) {
		this.rajivgandhiequity = rajivgandhiequity;
	}

	public double getEightyttainterestsaving() {
		return eightyttainterestsaving;
	}

	public void setEightyttainterestsaving(double eightyttainterestsaving) {
		this.eightyttainterestsaving = eightyttainterestsaving;
	}

	public double getEightyddmedicaltreatbelow() {
		return eightyddmedicaltreatbelow;
	}

	public void setEightyddmedicaltreatbelow(double eightyddmedicaltreatbelow) {
		this.eightyddmedicaltreatbelow = eightyddmedicaltreatbelow;
	}

	public double getEightyddmedicaltreatabove() {
		return eightyddmedicaltreatabove;
	}

	public void setEightyddmedicaltreatabove(double eightyddmedicaltreatabove) {
		this.eightyddmedicaltreatabove = eightyddmedicaltreatabove;
	}

	public double getTotalChapter80Up() {
		return totalChapter80Up;
	}

	public void setTotalChapter80Up(double totalChapter80Up) {
		this.totalChapter80Up = totalChapter80Up;
	}

	public String getAccyearId() {
		return accyearId;
	}

	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getBasictotal() {
		return basictotal;
	}

	public void setBasictotal(double basictotal) {
		this.basictotal = basictotal;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getDatotal() {
		return datotal;
	}

	public void setDatotal(double datotal) {
		this.datotal = datotal;
	}

	public double getHratotal() {
		return hratotal;
	}

	public void setHratotal(double hratotal) {
		this.hratotal = hratotal;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public double getAllowancetotal() {
		return allowancetotal;
	}

	public void setAllowancetotal(double allowancetotal) {
		this.allowancetotal = allowancetotal;
	}

	public double getChildedu() {
		return childedu;
	}

	public void setChildedu(double childedu) {
		this.childedu = childedu;
	}

	public double getChildedutotal() {
		return childedutotal;
	}

	public void setChildedutotal(double childedutotal) {
		this.childedutotal = childedutotal;
	}

	public double getSpecial() {
		return special;
	}

	public void setSpecial(double special) {
		this.special = special;
	}

	public double getSpecialtotal() {
		return specialtotal;
	}

	public void setSpecialtotal(double specialtotal) {
		this.specialtotal = specialtotal;
	}

	public double getArrears() {
		return arrears;
	}

	public void setArrears(double arrears) {
		this.arrears = arrears;
	}

	public double getArrearstotal() {
		return arrearstotal;
	}

	public void setArrearstotal(double arrearstotal) {
		this.arrearstotal = arrearstotal;
	}

	public double getPerformace() {
		return performace;
	}

	public void setPerformace(double performace) {
		this.performace = performace;
	}

	public double getPerformacetotal() {
		return performacetotal;
	}

	public void setPerformacetotal(double performacetotal) {
		this.performacetotal = performacetotal;
	}

	public double getMedical() {
		return medical;
	}

	public void setMedical(double medical) {
		this.medical = medical;
	}

	public double getMedicaltotal() {
		return medicaltotal;
	}

	public void setMedicaltotal(double medicaltotal) {
		this.medicaltotal = medicaltotal;
	}

	public double getLeave() {
		return leave;
	}

	public void setLeave(double leave) {
		this.leave = leave;
	}

	public double getLeavetotal() {
		return leavetotal;
	}

	public void setLeavetotal(double leavetotal) {
		this.leavetotal = leavetotal;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public double getFoodtotal() {
		return foodtotal;
	}

	public void setFoodtotal(double foodtotal) {
		this.foodtotal = foodtotal;
	}

	public double getReimburse() {
		return reimburse;
	}

	public void setReimburse(double reimburse) {
		this.reimburse = reimburse;
	}

	public double getReimbursetotal() {
		return reimbursetotal;
	}

	public void setReimbursetotal(double reimbursetotal) {
		this.reimbursetotal = reimbursetotal;
	}

	public double getInternet() {
		return internet;
	}

	public void setInternet(double internet) {
		this.internet = internet;
	}

	public double getInternettotal() {
		return internettotal;
	}

	public void setInternettotal(double internettotal) {
		this.internettotal = internettotal;
	}

	public double getDriver() {
		return driver;
	}

	public void setDriver(double driver) {
		this.driver = driver;
	}

	public double getDrivertotal() {
		return drivertotal;
	}

	public void setDrivertotal(double drivertotal) {
		this.drivertotal = drivertotal;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public double getOthertotal() {
		return othertotal;
	}

	public void setOthertotal(double othertotal) {
		this.othertotal = othertotal;
	}

	public double getTotalsalary() {
		return totalsalary;
	}

	public void setTotalsalary(double totalsalary) {
		this.totalsalary = totalsalary;
	}

	public double getYearlytotalamount() {
		return yearlytotalamount;
	}

	public void setYearlytotalamount(double yearlytotalamount) {
		this.yearlytotalamount = yearlytotalamount;
	}

	public double getPfemployer() {
		return pfemployer;
	}

	public void setPfemployer(double pfemployer) {
		this.pfemployer = pfemployer;
	}

	public double getPfamount() {
		return pfamount;
	}

	public void setPfamount(double pfamount) {
		this.pfamount = pfamount;
	}

	public double getYearlytotalpfamount() {
		return yearlytotalpfamount;
	}

	public void setYearlytotalpfamount(double yearlytotalpfamount) {
		this.yearlytotalpfamount = yearlytotalpfamount;
	}

	public double getEsiemployer() {
		return esiemployer;
	}

	public void setEsiemployer(double esiemployer) {
		this.esiemployer = esiemployer;
	}

	public double getEsiamount() {
		return esiamount;
	}

	public void setEsiamount(double esiamount) {
		this.esiamount = esiamount;
	}

	public double getYearlytotalesiamount() {
		return yearlytotalesiamount;
	}

	public void setYearlytotalesiamount(double yearlytotalesiamount) {
		this.yearlytotalesiamount = yearlytotalesiamount;
	}

	public String getHemloyeeid() {
		return hemloyeeid;
	}

	public void setHemloyeeid(String hemloyeeid) {
		this.hemloyeeid = hemloyeeid;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp currentdate) {
		this.createddate = currentdate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getHempid() {
		return hempid;
	}

	public void setHempid(String hempid) {
		this.hempid = hempid;
	}

	public double getMedicalallowances() {
		return medicalallowances;
	}

	public void setMedicalallowances(double medicalallowances) {
		this.medicalallowances = medicalallowances;
	}

	public double getFoodallowance() {
		return foodallowance;
	}

	public void setFoodallowance(double foodallowance) {
		this.foodallowance = foodallowance;
	}

	public double getWashingallowance() {
		return washingallowance;
	}

	public void setWashingallowance(double washingallowance) {
		this.washingallowance = washingallowance;
	}
	private double tdsctc;
	private double standarddedu;
	private double taxbleincomestatury;
	private double tdspf;
	private double tdspt;
	private double taxpayble;
	private double taxableincome;
	
	
	
	
	
	
	public double getTaxableincome() {
		return taxableincome;
	}

	public void setTaxableincome(double taxableincome) {
		this.taxableincome = taxableincome;
	}
	public double getTdsctc() {
		return tdsctc;
	}
	public void setTdsctc(double tdsctc) {
		this.tdsctc = tdsctc;
	}
	public double getStandarddedu() {
		return standarddedu;
	}
	public void setStandarddedu(double standarddedu) {
		this.standarddedu = standarddedu;
	}
	
	public double getTaxbleincomestatury() {
		return taxbleincomestatury;
	}
	public void setTaxbleincomestatury(double taxbleincomestatury) {
		this.taxbleincomestatury = taxbleincomestatury;
	}
	public double getTdspf() {
		return tdspf;
	}
	public void setTdspf(double tdspf) {
		this.tdspf = tdspf;
	}
	public double getTdspt() {
		return tdspt;
	}
	public void setTdspt(double tdspt) {
		this.tdspt = tdspt;
	}
	public double getTaxpayble() {
		return taxpayble;
	}
	public void setTaxpayble(double taxpayble) {
		this.taxpayble = taxpayble;
	}
	public double getOtherallowance() {
		return otherallowance;
	}
	public void setOtherallowance(double otherallowance) {
		this.otherallowance = otherallowance;
	}
	public double getPerfincentive() {
		return perfincentive;
	}
	public void setPerfincentive(double perfincentive) {
		this.perfincentive = perfincentive;
	}
	public double getShiftallowance() {
		return shiftallowance;
	}
	public void setShiftallowance(double shiftallowance) {
		this.shiftallowance = shiftallowance;
	}
	public double getSpecialallowance() {
		return specialallowance;
	}
	public void setSpecialallowance(double specialallowance) {
		this.specialallowance = specialallowance;
	}
	
	public double getTelephonereim() {
		return telephonereim;
	}
	public void setTelephonereim(double telephonereim) {
		this.telephonereim = telephonereim;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPayabledays() {
		return payabledays;
	}
	public void setPayabledays(long payabledays) {
		this.payabledays = payabledays;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPfnumber() {
		return pfnumber;
	}
	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}
	public int getHrapercentag() {
		return hrapercentag;
	}
	public void setHrapercentag(int hrapercentag) {
		this.hrapercentag = hrapercentag;
	}
	public double getDemployerpf() {
		return demployerpf;
	}
	public void setDemployerpf(double demployerpf) {
		this.demployerpf = demployerpf;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getDedmployerESI() {
		return dedmployerESI;
	}
	public void setDedmployerESI(double dedmployerESI) {
		this.dedmployerESI = dedmployerESI;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(double hra) {
		this.hra = hra;
	}
	public double getEmployerpf() {
		return employerpf;
	}
	public void setEmployerpf(double employerpf) {
		this.employerpf = employerpf;
	}
	public double getEmployeepf() {
		return employeepf;
	}
	public void setEmployeepf(double employeepf) {
		this.employeepf = employeepf;
	}
	public double getPt() {
		return pt;
	}
	public void setPt(double pt) {
		this.pt = pt;
	}
	public double getCa() {
		return ca;
	}
	public void setCa(double ca) {
		this.ca = ca;
	}
	public long getBankaccnumber() {
		return bankaccnumber;
	}
	public void setBankaccnumber(long bankaccnumber) {
		this.bankaccnumber = bankaccnumber;
	}
	public String getLeavedeductions() {
		return leavedeductions;
	}
	public void setLeavedeductions(String leavedeductions) {
		this.leavedeductions = leavedeductions;
	}
	public String getLatedeductions() {
		return latedeductions;
	}
	public void setLatedeductions(String latedeductions) {
		this.latedeductions = latedeductions;
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public double getIncometax() {
		return incometax;
	}
	public void setIncometax(double incometax) {
		this.incometax = incometax;
	}
	
	
	public String getEmployeepfno() {
		return employeepfno;
	}
	public void setEmployeepfno(String employeepfno) {
		this.employeepfno = employeepfno;
	}
	public String getIFSCCode() {
		return IFSCCode;
	}
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}
	public int getYear() {
		return year;
	}
	public double getGrosssalary() {
		return grosssalary;
	}
	public void setGrosssalary(double grosssalary) {
		this.grosssalary = grosssalary;
	}
	
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}
	public double getCtc() {
		return ctc;
	}
	public void setCtc(double ctc) {
		this.ctc = ctc;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public double getEmployerESI() {
		return employerESI;
	}
	public void setEmployerESI(double employerESI) {
		this.employerESI = employerESI;
	}
	public double getEmployeeESI() {
		return employeeESI;
	}
	public void setEmployeeESI(double employeeESI) {
		this.employeeESI = employeeESI;
	}
	
	
	
	
	

	








}
