package com.centris.campus.vo;

public class StaffEmployementVo {
	
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
	private double medicalallowances;
	private double telephonereim;
	private double perfincentive;
	private double shiftallowance;
	private double specialallowance;
	private double otherallowance;
	private double foodallowance;
	private double washingallowance;
	private double da;
	private double cca;
	private double tdsctc;
	private double standarddedu;
	private double taxbleincomestatury;
	private double tdspf;
	private double tdspt;
	private double taxpayble;
	private double taxableincome;
	
	
	private double d_emp_esi;
	private double d_empr_esi;
	private double d_emp_pf;
	private double d_empr_pf;
	
	private double total;
	
	
	private String teacherId;
	private String name;
	private String doj;
	private String pannumber;
	private double medicalbill;
	private double mediclaim;
	
	private double elssLicHousingLoans;
	private double housingLoanInterest;
	private double savingsAccountIntrest;
	private double rgessExempted;
	
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
	
	private double incomeOtherHousingLoanLetout;
	private double incomeOtherHousingLoanSelf;
	private double exemptHra;
	private double exemptChildEdu;
	private double exemptMedReimb;
	private double exemptTransAllowance;
	private double exemptLTA;
	private double exemptFoodCoupon;
	private double exemptTelephoneReimb;
	private double exemptCarExpReimb;
	private double exemptInternetExp;
	private double exemptDriverSal;
	private double exemptOtherExp;
	private double exemptTotal;
	private double section80CEmpPF;
	private double section80CTutionFee;
	private double section80CFixedDeposit;
	private double section80CLIC;
	private double section80CMutualFund;
	private double section80CNationalSaveCerti;
	private double section80CAccruNSC;
	private double section80CPublicPF;
	private double section80CRepayHousingLoan;
	private double section80CULIP;
	private double section80CSeniorCitizenSaving;
	private double section80CPostSaving;
	private double section80COther;
	private double section80CTotal;
	private double section80DMediClaim1;
	private double section80DMediClaim2;
	private double section80DMediClaim3;
	private double section80DDBMedTreatment1;
	private double section80DDBMedTreatment2;
	private double section80EEduLoanInterest;
	private double section80CCDNPS;
	private double section80UDeduction1;
	private double section80UDeduction2;
	private double section80CCGRajivEquitySaving;
	private double section80TTASavingBankInterest;
	private double section80DDMedTreatment1;
	private double section80DDMedTreatment2;
	private double section80D80UTotal;
	private double incomeOtherHousingLoanLetoutMax;
	private double incomeOtherHousingLoanSelfMax;
	private double exemptMedReimbMax;
	private double exemptTransAllowanceMax;
	private double section80CMax;
	private double section80DMediClaim1Max;
	private double section80DMediClaim2Max;
	private double section80DMediClaim3Max;
	private double section80DDBMedTreatment1Max;
	private double section80DDBMedTreatment2Max;
	private double section80CCDNPSMax;
	private double section80UDeduction1Max;
	private double section80UDeduction2Max;
	private double section80CCGRajivEquitySavingMax;
	private double section80TTASavingBankInterestMax;
	private double section80DDMedTreatment1Max;
	private double section80DDMedTreatment2Max;
	private double pfMaxAmount;
	private double esiMaxAmount;
	private double pfPercentage;
	private double esiPercentage;
	private double otherIncome;
	private double otherIncomeTotal;
	
	private String housefromdate;
	private String housetodate;
	private String housecity;
	private String landlordname;
	private String houseaddress;
	private String pancardno;
	private double houseamount;
	private double monthlyhraamount;
	private double totalExemptions;
	private double tdspayabletax;
	private double tdstaxableincome;
	private double totaltdsundervia;
	private double TotalTDSIncomeFromOther;
	
	public double getTotalTDSIncomeFromOther() {
		return TotalTDSIncomeFromOther;
	}

	public void setTotalTDSIncomeFromOther(double totalTDSIncomeFromOther) {
		TotalTDSIncomeFromOther = totalTDSIncomeFromOther;
	}
	
	
	public double getTotaltdsundervia() {
		return totaltdsundervia;
	}

	public void setTotaltdsundervia(double totaltdsundervia) {
		this.totaltdsundervia = totaltdsundervia;
	}

	public double getTdstaxableincome() {
		return tdstaxableincome;
	}

	public void setTdstaxableincome(double tdstaxableincome) {
		this.tdstaxableincome = tdstaxableincome;
	}

	public double getTdspayabletax() {
		return tdspayabletax;
	}

	public void setTdspayabletax(double tdspayabletax) {
		this.tdspayabletax = tdspayabletax;
	}

	public double getTotalExemptions() {
		return totalExemptions;
	}

	public void setTotalExemptions(double totalExemptions) {
		this.totalExemptions = totalExemptions;
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

	public double getTaxworkProfTax() {
		return taxworkProfTax;
	}

	public void setTaxworkProfTax(double taxworkProfTax) {
		this.taxworkProfTax = taxworkProfTax;
	}

	public double getTaxworkNetTax() {
		return taxworkNetTax;
	}

	public void setTaxworkNetTax(double taxworkNetTax) {
		this.taxworkNetTax = taxworkNetTax;
	}

	public double getTaxworkTaxableIncome() {
		return taxworkTaxableIncome;
	}

	public void setTaxworkTaxableIncome(double taxworkTaxableIncome) {
		this.taxworkTaxableIncome = taxworkTaxableIncome;
	}

	public double getTaxableTotalAmount() {
		return taxableTotalAmount;
	}

	public void setTaxableTotalAmount(double taxableTotalAmount) {
		this.taxableTotalAmount = taxableTotalAmount;
	}
	private double taxworkProfTax;
	private double taxworkNetTax;
	private double taxworkTaxableIncome;
	private double taxableTotalAmount;
	
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

	public double getIncomeOtherHousingLoanLetout() {
		return incomeOtherHousingLoanLetout;
	}

	public void setIncomeOtherHousingLoanLetout(double incomeOtherHousingLoanLetout) {
		this.incomeOtherHousingLoanLetout = incomeOtherHousingLoanLetout;
	}

	public double getIncomeOtherHousingLoanSelf() {
		return incomeOtherHousingLoanSelf;
	}

	public void setIncomeOtherHousingLoanSelf(double incomeOtherHousingLoanSelf) {
		this.incomeOtherHousingLoanSelf = incomeOtherHousingLoanSelf;
	}

	public double getExemptHra() {
		return exemptHra;
	}

	public void setExemptHra(double exemptHra) {
		this.exemptHra = exemptHra;
	}

	public double getExemptChildEdu() {
		return exemptChildEdu;
	}

	public void setExemptChildEdu(double exemptChildEdu) {
		this.exemptChildEdu = exemptChildEdu;
	}

	public double getExemptMedReimb() {
		return exemptMedReimb;
	}

	public void setExemptMedReimb(double exemptMedReimb) {
		this.exemptMedReimb = exemptMedReimb;
	}

	public double getExemptTransAllowance() {
		return exemptTransAllowance;
	}

	public void setExemptTransAllowance(double exemptTransAllowance) {
		this.exemptTransAllowance = exemptTransAllowance;
	}

	public double getExemptLTA() {
		return exemptLTA;
	}

	public void setExemptLTA(double exemptLTA) {
		this.exemptLTA = exemptLTA;
	}

	public double getExemptFoodCoupon() {
		return exemptFoodCoupon;
	}

	public void setExemptFoodCoupon(double exemptFoodCoupon) {
		this.exemptFoodCoupon = exemptFoodCoupon;
	}

	public double getExemptTelephoneReimb() {
		return exemptTelephoneReimb;
	}

	public void setExemptTelephoneReimb(double exemptTelephoneReimb) {
		this.exemptTelephoneReimb = exemptTelephoneReimb;
	}

	public double getExemptCarExpReimb() {
		return exemptCarExpReimb;
	}

	public void setExemptCarExpReimb(double exemptCarExpReimb) {
		this.exemptCarExpReimb = exemptCarExpReimb;
	}

	public double getExemptInternetExp() {
		return exemptInternetExp;
	}

	public void setExemptInternetExp(double exemptInternetExp) {
		this.exemptInternetExp = exemptInternetExp;
	}

	public double getExemptDriverSal() {
		return exemptDriverSal;
	}

	public void setExemptDriverSal(double exemptDriverSal) {
		this.exemptDriverSal = exemptDriverSal;
	}

	public double getExemptOtherExp() {
		return exemptOtherExp;
	}

	public void setExemptOtherExp(double exemptOtherExp) {
		this.exemptOtherExp = exemptOtherExp;
	}

	public double getExemptTotal() {
		return exemptTotal;
	}

	public void setExemptTotal(double exemptTotal) {
		this.exemptTotal = exemptTotal;
	}

	public double getSection80CEmpPF() {
		return section80CEmpPF;
	}

	public void setSection80CEmpPF(double section80cEmpPF) {
		section80CEmpPF = section80cEmpPF;
	}

	public double getSection80CTutionFee() {
		return section80CTutionFee;
	}

	public void setSection80CTutionFee(double section80cTutionFee) {
		section80CTutionFee = section80cTutionFee;
	}

	public double getSection80CFixedDeposit() {
		return section80CFixedDeposit;
	}

	public void setSection80CFixedDeposit(double section80cFixedDeposit) {
		section80CFixedDeposit = section80cFixedDeposit;
	}

	public double getSection80CLIC() {
		return section80CLIC;
	}

	public void setSection80CLIC(double section80clic) {
		section80CLIC = section80clic;
	}

	public double getSection80CMutualFund() {
		return section80CMutualFund;
	}

	public void setSection80CMutualFund(double section80cMutualFund) {
		section80CMutualFund = section80cMutualFund;
	}

	public double getSection80CNationalSaveCerti() {
		return section80CNationalSaveCerti;
	}

	public void setSection80CNationalSaveCerti(double section80cNationalSaveCerti) {
		section80CNationalSaveCerti = section80cNationalSaveCerti;
	}

	public double getSection80CAccruNSC() {
		return section80CAccruNSC;
	}

	public void setSection80CAccruNSC(double section80cAccruNSC) {
		section80CAccruNSC = section80cAccruNSC;
	}

	public double getSection80CPublicPF() {
		return section80CPublicPF;
	}

	public void setSection80CPublicPF(double section80cPublicPF) {
		section80CPublicPF = section80cPublicPF;
	}

	public double getSection80CRepayHousingLoan() {
		return section80CRepayHousingLoan;
	}

	public void setSection80CRepayHousingLoan(double section80cRepayHousingLoan) {
		section80CRepayHousingLoan = section80cRepayHousingLoan;
	}

	public double getSection80CULIP() {
		return section80CULIP;
	}

	public void setSection80CULIP(double section80culip) {
		section80CULIP = section80culip;
	}

	public double getSection80CSeniorCitizenSaving() {
		return section80CSeniorCitizenSaving;
	}

	public void setSection80CSeniorCitizenSaving(double section80cSeniorCitizenSaving) {
		section80CSeniorCitizenSaving = section80cSeniorCitizenSaving;
	}

	public double getSection80CPostSaving() {
		return section80CPostSaving;
	}

	public void setSection80CPostSaving(double section80cPostSaving) {
		section80CPostSaving = section80cPostSaving;
	}

	public double getSection80COther() {
		return section80COther;
	}

	public void setSection80COther(double section80cOther) {
		section80COther = section80cOther;
	}

	public double getSection80CTotal() {
		return section80CTotal;
	}

	public void setSection80CTotal(double section80cTotal) {
		section80CTotal = section80cTotal;
	}

	public double getSection80DMediClaim1() {
		return section80DMediClaim1;
	}

	public void setSection80DMediClaim1(double section80dMediClaim1) {
		section80DMediClaim1 = section80dMediClaim1;
	}

	public double getSection80DMediClaim2() {
		return section80DMediClaim2;
	}

	public void setSection80DMediClaim2(double section80dMediClaim2) {
		section80DMediClaim2 = section80dMediClaim2;
	}

	public double getSection80DMediClaim3() {
		return section80DMediClaim3;
	}

	public void setSection80DMediClaim3(double section80dMediClaim3) {
		section80DMediClaim3 = section80dMediClaim3;
	}

	public double getSection80DDBMedTreatment1() {
		return section80DDBMedTreatment1;
	}

	public void setSection80DDBMedTreatment1(double section80ddbMedTreatment1) {
		section80DDBMedTreatment1 = section80ddbMedTreatment1;
	}

	public double getSection80DDBMedTreatment2() {
		return section80DDBMedTreatment2;
	}

	public void setSection80DDBMedTreatment2(double section80ddbMedTreatment2) {
		section80DDBMedTreatment2 = section80ddbMedTreatment2;
	}

	public double getSection80EEduLoanInterest() {
		return section80EEduLoanInterest;
	}

	public void setSection80EEduLoanInterest(double section80eEduLoanInterest) {
		section80EEduLoanInterest = section80eEduLoanInterest;
	}

	public double getSection80CCDNPS() {
		return section80CCDNPS;
	}

	public void setSection80CCDNPS(double section80ccdnps) {
		section80CCDNPS = section80ccdnps;
	}

	public double getSection80UDeduction1() {
		return section80UDeduction1;
	}

	public void setSection80UDeduction1(double section80uDeduction1) {
		section80UDeduction1 = section80uDeduction1;
	}

	public double getSection80UDeduction2() {
		return section80UDeduction2;
	}

	public void setSection80UDeduction2(double section80uDeduction2) {
		section80UDeduction2 = section80uDeduction2;
	}

	public double getSection80CCGRajivEquitySaving() {
		return section80CCGRajivEquitySaving;
	}

	public void setSection80CCGRajivEquitySaving(double section80ccgRajivEquitySaving) {
		section80CCGRajivEquitySaving = section80ccgRajivEquitySaving;
	}

	public double getSection80TTASavingBankInterest() {
		return section80TTASavingBankInterest;
	}

	public void setSection80TTASavingBankInterest(double section80ttaSavingBankInterest) {
		section80TTASavingBankInterest = section80ttaSavingBankInterest;
	}

	public double getSection80DDMedTreatment1() {
		return section80DDMedTreatment1;
	}

	public void setSection80DDMedTreatment1(double section80ddMedTreatment1) {
		section80DDMedTreatment1 = section80ddMedTreatment1;
	}

	public double getSection80DDMedTreatment2() {
		return section80DDMedTreatment2;
	}

	public void setSection80DDMedTreatment2(double section80ddMedTreatment2) {
		section80DDMedTreatment2 = section80ddMedTreatment2;
	}

	public double getSection80D80UTotal() {
		return section80D80UTotal;
	}

	public void setSection80D80UTotal(double section80d80uTotal) {
		section80D80UTotal = section80d80uTotal;
	}

	public double getOtherIncomeTotal() {
		return otherIncomeTotal;
	}

	public void setOtherIncomeTotal(double otherIncomeTotal) {
		this.otherIncomeTotal = otherIncomeTotal;
	}

	public double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public double getPfMaxAmount() {
		return pfMaxAmount;
	}

	public void setPfMaxAmount(double pfMaxAmount) {
		this.pfMaxAmount = pfMaxAmount;
	}

	public double getEsiMaxAmount() {
		return esiMaxAmount;
	}

	public void setEsiMaxAmount(double esiMaxAmount) {
		this.esiMaxAmount = esiMaxAmount;
	}

	public double getPfPercentage() {
		return pfPercentage;
	}

	public void setPfPercentage(double pfPercentage) {
		this.pfPercentage = pfPercentage;
	}

	public double getEsiPercentage() {
		return esiPercentage;
	}

	public void setEsiPercentage(double esiPercentage) {
		this.esiPercentage = esiPercentage;
	}

	public double getIncomeOtherHousingLoanLetoutMax() {
		return incomeOtherHousingLoanLetoutMax;
	}

	public void setIncomeOtherHousingLoanLetoutMax(double incomeOtherHousingLoanLetoutMax) {
		this.incomeOtherHousingLoanLetoutMax = incomeOtherHousingLoanLetoutMax;
	}

	public double getIncomeOtherHousingLoanSelfMax() {
		return incomeOtherHousingLoanSelfMax;
	}

	public void setIncomeOtherHousingLoanSelfMax(double incomeOtherHousingLoanSelfMax) {
		this.incomeOtherHousingLoanSelfMax = incomeOtherHousingLoanSelfMax;
	}

	public double getExemptMedReimbMax() {
		return exemptMedReimbMax;
	}

	public void setExemptMedReimbMax(double exemptMedReimbMax) {
		this.exemptMedReimbMax = exemptMedReimbMax;
	}

	public double getExemptTransAllowanceMax() {
		return exemptTransAllowanceMax;
	}

	public void setExemptTransAllowanceMax(double exemptTransAllowanceMax) {
		this.exemptTransAllowanceMax = exemptTransAllowanceMax;
	}

	public double getSection80CMax() {
		return section80CMax;
	}

	public void setSection80CMax(double section80cMax) {
		section80CMax = section80cMax;
	}

	public double getSection80DMediClaim1Max() {
		return section80DMediClaim1Max;
	}

	public void setSection80DMediClaim1Max(double section80dMediClaim1Max) {
		section80DMediClaim1Max = section80dMediClaim1Max;
	}

	public double getSection80DMediClaim2Max() {
		return section80DMediClaim2Max;
	}

	public void setSection80DMediClaim2Max(double section80dMediClaim2Max) {
		section80DMediClaim2Max = section80dMediClaim2Max;
	}

	public double getSection80DMediClaim3Max() {
		return section80DMediClaim3Max;
	}

	public void setSection80DMediClaim3Max(double section80dMediClaim3Max) {
		section80DMediClaim3Max = section80dMediClaim3Max;
	}

	public double getSection80DDBMedTreatment1Max() {
		return section80DDBMedTreatment1Max;
	}

	public void setSection80DDBMedTreatment1Max(double section80ddbMedTreatment1Max) {
		section80DDBMedTreatment1Max = section80ddbMedTreatment1Max;
	}

	public double getSection80DDBMedTreatment2Max() {
		return section80DDBMedTreatment2Max;
	}

	public void setSection80DDBMedTreatment2Max(double section80ddbMedTreatment2Max) {
		section80DDBMedTreatment2Max = section80ddbMedTreatment2Max;
	}

	public double getSection80CCDNPSMax() {
		return section80CCDNPSMax;
	}

	public void setSection80CCDNPSMax(double section80ccdnpsMax) {
		section80CCDNPSMax = section80ccdnpsMax;
	}

	public double getSection80UDeduction1Max() {
		return section80UDeduction1Max;
	}

	public void setSection80UDeduction1Max(double section80uDeduction1Max) {
		section80UDeduction1Max = section80uDeduction1Max;
	}

	public double getSection80UDeduction2Max() {
		return section80UDeduction2Max;
	}

	public void setSection80UDeduction2Max(double section80uDeduction2Max) {
		section80UDeduction2Max = section80uDeduction2Max;
	}

	public double getSection80CCGRajivEquitySavingMax() {
		return section80CCGRajivEquitySavingMax;
	}

	public void setSection80CCGRajivEquitySavingMax(double section80ccgRajivEquitySavingMax) {
		section80CCGRajivEquitySavingMax = section80ccgRajivEquitySavingMax;
	}

	public double getSection80TTASavingBankInterestMax() {
		return section80TTASavingBankInterestMax;
	}

	public void setSection80TTASavingBankInterestMax(double section80ttaSavingBankInterestMax) {
		section80TTASavingBankInterestMax = section80ttaSavingBankInterestMax;
	}

	public double getSection80DDMedTreatment1Max() {
		return section80DDMedTreatment1Max;
	}

	public void setSection80DDMedTreatment1Max(double section80ddMedTreatment1Max) {
		section80DDMedTreatment1Max = section80ddMedTreatment1Max;
	}

	public double getSection80DDMedTreatment2Max() {
		return section80DDMedTreatment2Max;
	}

	public void setSection80DDMedTreatment2Max(double section80ddMedTreatment2Max) {
		section80DDMedTreatment2Max = section80ddMedTreatment2Max;
	}

	public double getBasictotal() {
		return basictotal;
	}

	public void setBasictotal(double basictotal) {
		this.basictotal = basictotal;
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

	public double getElssLicHousingLoans() {
		return elssLicHousingLoans;
	}

	public void setElssLicHousingLoans(double elssLicHousingLoans) {
		this.elssLicHousingLoans = elssLicHousingLoans;
	}

	public double getHousingLoanInterest() {
		return housingLoanInterest;
	}

	public void setHousingLoanInterest(double housingLoanInterest) {
		this.housingLoanInterest = housingLoanInterest;
	}

	public double getSavingsAccountIntrest() {
		return savingsAccountIntrest;
	}

	public void setSavingsAccountIntrest(double savingsAccountIntrest) {
		this.savingsAccountIntrest = savingsAccountIntrest;
	}


	public double getRgessExempted() {
		return rgessExempted;
	}

	public void setRgessExempted(double rgessExempted) {
		this.rgessExempted = rgessExempted;
	}

	public double getMedicalbill() {
		return medicalbill;
	}

	public void setMedicalbill(double medicalbill) {
		this.medicalbill = medicalbill;
	}

	public double getMediclaim() {
		return mediclaim;
	}

	public void setMediclaim(double mediclaim) {
		this.mediclaim = mediclaim;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getD_emp_esi() {
		return d_emp_esi;
	}

	public void setD_emp_esi(double d_emp_esi) {
		this.d_emp_esi = d_emp_esi;
	}

	public double getD_empr_esi() {
		return d_empr_esi;
	}

	public void setD_empr_esi(double d_empr_esi) {
		this.d_empr_esi = d_empr_esi;
	}

	public double getD_emp_pf() {
		return d_emp_pf;
	}

	public void setD_emp_pf(double d_emp_pf) {
		this.d_emp_pf = d_emp_pf;
	}

	public double getD_empr_pf() {
		return d_empr_pf;
	}

	public void setD_empr_pf(double d_empr_pf) {
		this.d_empr_pf = d_empr_pf;
	}

	public double getCca() {
		return cca;
	}

	public void setCca(double cca) {
		this.cca = cca;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
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
