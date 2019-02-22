package com.centris.campus.util;

import java.sql.Connection;
import java.sql.ResultSet;

import com.centris.campus.pojo.PayrolDeductionsPojo;
import com.centris.campus.vo.CalculateTimeAmount;
import com.centris.campus.vo.StaffDetailsForPayrollVo;
import com.centris.campus.vo.StaffEmployementVo;
import com.mysql.jdbc.PreparedStatement;



public class CalculateStaffSalaryDetails  {
	
	// ************* calculations for vspl start ******************//
	
	public double cal_Basic(double ctc){
		
		double Basic=(ctc*MessageConstants.BASIC_PERCENTAGE)/100;
		
		Basic = Math.round(Basic);
		
		return Basic;
	}
	
	public double cal_Hra(double basic, double hrapercentag){
		
		double HRA=(basic*hrapercentag)/100;
		
		HRA = Math.round(HRA);
		
		return HRA;
	}
	public double cal_Ca(double basic){
		
		double CA=(basic*MessageConstants.CA_PER)/100;
		
		CA = Math.round(CA);
		
		return CA;
	}
	public double cal_Da(double ctc){
		
		double DA=(ctc*MessageConstants.DA_PER)/100;
		
		DA = Math.round(DA);
		
		return DA;
	}
	
	public double cal_Others(double ctc){
		
		double OTHERS=(ctc*MessageConstants.OTHERS_PER)/100;
		
		OTHERS = Math.round(OTHERS);
		
		return OTHERS;
	}
	
public double cal_Emp_Pf(double basic){
	
	double EMPLOYEE_PF=0.0;
	
	if(basic >= 15000){
		
		EMPLOYEE_PF=(15000*MessageConstants.EMPLYEE_PF_PER)/100;
	
     }else{
    	 EMPLOYEE_PF=(basic*MessageConstants.EMPLYEE_PF_PER)/100;
     }
		
		
		EMPLOYEE_PF = Math.round(EMPLOYEE_PF);	
		
		return EMPLOYEE_PF;
	}


public double cal_Empr_Pf(double basic){
	
	double	EMPLYER_PF;
	
	if(basic >= 15000){
		
			EMPLYER_PF=Math.round((15000*MessageConstants.EMPLYER_PF_PER)/100);
		
	}else{
		
			EMPLYER_PF=Math.round((basic*MessageConstants.EMPLYER_PF_PER)/100);
	}
	
	
			
	return EMPLYER_PF;
}

public double cal_adminchrg(double basic){


double admin_charge=0.0;

if(basic<15000){
	
	admin_charge=(basic*MessageConstants.ADMIN_CHARGE)/100;
	
	admin_charge = Math.round(admin_charge);	
	
}else{
	
	admin_charge=(15000*MessageConstants.ADMIN_CHARGE)/100;
		
	admin_charge = Math.round(admin_charge);
	
}
	
	return admin_charge;
}

public double cal_Pt(double gross){
	
	double PT;
	
	if(gross<MessageConstants.pt_salary){
		
		PT=0.0;
	}else{
		
		PT=200;
	}
	
	return PT;
}
	
public double cal_Netsalary(double ctc,double netsalary){
	
	double PT;
	
	if(ctc<MessageConstants.pt_salary){
		
		PT=150;
	}else{
		
		PT=200;
	}
	
	return PT;
}

public PayrolDeductionsPojo calculateNetSalary(StaffEmployementVo empsalaryvo,Connection connection){
	
	double netsalary=empsalaryvo.getGrosssalary();
	Connection conn=connection;
	PayrolDeductionsPojo payrolDeductionsPojo=new PayrolDeductionsPojo();
	
	double late_ded=0;
	String late_time="00:00:00";
		
	if("Y".equalsIgnoreCase(empsalaryvo.getLatedeductions())){
		
		CalculateTimeAmount timeamount_late=new CalculateStaffSalaryDetails().lateDeductions(empsalaryvo,empsalaryvo.getBasic(),conn);
		
		late_ded=timeamount_late.getAmount();
		late_time=timeamount_late.getTime();
					
		netsalary=netsalary-(late_ded);
		
	}
	

	
	netsalary = Math.round(netsalary);
	
	payrolDeductionsPojo.setLatedeductions(late_ded);
	payrolDeductionsPojo.setLatetime(late_time);
	payrolDeductionsPojo.setNetsalary(netsalary);
	
	return payrolDeductionsPojo;
	
}

public CalculateTimeAmount lateDeductions(StaffEmployementVo vo,double grosssalary,Connection connection){

PreparedStatement ps_latedeductions=null;
ResultSet rs_latededuction=null;
Connection conn=connection;
String latetime=null;
int no_lateexec=0;
int year=vo.getYear();
int month=vo.getMonth();

CalculateStaffSalaryDetails cal=new CalculateStaffSalaryDetails();
	CalculateTimeAmount cal_vo=new CalculateTimeAmount();
 
 	String startdate=year+"-"+month+"-01";
	String enddate= year+"-"+month+"-"+HelperClass.getDaysByMonthAndYear(month, year);

try {
	ps_latedeductions = (PreparedStatement)conn.prepareStatement(SQLUtilConstants.CALCULATE_LATE_TIME);
	ps_latedeductions.setString(1, startdate);
	ps_latedeductions.setString(2, enddate);
	ps_latedeductions.setString(3, vo.getEmpid());
	
	rs_latededuction=ps_latedeductions.executeQuery();
	
	while(rs_latededuction.next()){
		
		latetime=rs_latededuction.getString("LateTime");
		no_lateexec=Integer.parseInt(rs_latededuction.getString("lateexex"));
		
	}
	
	
	 cal_vo=cal.calculatingAmmountforLate(month, year, grosssalary, latetime, no_lateexec);
	 
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return cal_vo;
}

public CalculateTimeAmount calculatingAmmountforLate(int month,int year,double salary,String time,int exec){

String Tot_EarlyTime="00:00:00";

 int totalDaysInMonth = HelperClass.getDaysByMonthAndYear(month, year);
 double incentiveDays = salary/(totalDaysInMonth);
 double incentiveHours = incentiveDays/9;
 double incentivesMinutes = incentiveHours/60;

 CalculateTimeAmount vo=new CalculateTimeAmount();
 
 if(time!=null){
		
	 String[] earlyArray=time.split(",");
	if(earlyArray.length<= exec){
		Tot_EarlyTime="00:00:00";
	}
	 else{
		 String[] earlyTimeArray = new String[earlyArray.length-exec];
		 System.arraycopy(earlyArray, exec, earlyTimeArray, 0, earlyTimeArray.length);
		 Tot_EarlyTime= HelperClass.addTimeArray(earlyTimeArray);
	 }
String[] hourminSec = Tot_EarlyTime.split(":");

if(Tot_EarlyTime.equalsIgnoreCase(null)){
	Tot_EarlyTime="00:00:00";
}


double tot_amount=(((Integer.parseInt(hourminSec[0]) * 60) +  Integer.parseInt(hourminSec[1])) * incentivesMinutes);

vo.setAmount(Math.round(tot_amount)) ;

}
 vo.setTime(Tot_EarlyTime);

return vo;
}



public CalculateTimeAmount overTimeBeforeShift(StaffEmployementVo vo,double grosssalary,Connection connection){



PreparedStatement ps_overtime=null;
ResultSet rs_overtime=null;
String overtimebeforeshift=null;
CalculatingEmployeeSalaryDetails cal=new CalculatingEmployeeSalaryDetails();
CalculateTimeAmount cal_vo=new CalculateTimeAmount();
int year=vo.getYear();
int month=vo.getMonth();
Connection conn=connection;

 	String startdate=year+"-"+month+"-01";
	String enddate= year+"-"+month+"-"+HelperClass.getDaysByMonthAndYear(month, year);

try {
	ps_overtime = (PreparedStatement) conn.prepareStatement(SQLUtilConstants.CALCULATE_OVERTIME);
	ps_overtime.setString(1, startdate);
	ps_overtime.setString(2, enddate);
	ps_overtime.setString(3, vo.getEmpid());
	
	
	rs_overtime=ps_overtime.executeQuery();
	
	while(rs_overtime.next()){
		
		overtimebeforeshift=rs_overtime.getString("otbeforeshift");
		
	}
	 cal_vo=cal.calculatingAmmountforOT(month, year, grosssalary, overtimebeforeshift);

} catch (Exception e) {
	
	e.printStackTrace();
}


return cal_vo;
}


public CalculateTimeAmount overTimeAfterShift(StaffEmployementVo vo,double grosssalary,Connection connection){


PreparedStatement ps_overtime=null;
ResultSet rs_overtime=null;
String overtimeaftershift=null;
CalculatingEmployeeSalaryDetails cal=new CalculatingEmployeeSalaryDetails();
CalculateTimeAmount cal_vo=new CalculateTimeAmount();
int year=vo.getYear();
int month=vo.getMonth();
Connection conn=connection;
 
 	String startdate=year+"-"+month+"-01";
	String enddate= year+"-"+month+"-"+HelperClass.getDaysByMonthAndYear(month, year);

try {
	ps_overtime = (PreparedStatement)conn.prepareStatement(SQLUtilConstants.CALCULATE_OVERTIME);
	ps_overtime.setString(1, startdate);
	ps_overtime.setString(2, enddate);
	ps_overtime.setString(3, vo.getEmpid());
	
	rs_overtime=ps_overtime.executeQuery();
	
	while(rs_overtime.next()){
		
		overtimeaftershift=rs_overtime.getString("otaftershift");
		
	}
	
	 cal_vo=cal.calculatingAmmountforOT(month, year, grosssalary, overtimeaftershift);

	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


return cal_vo;
}




public double cal_Hra_Prg(double basic){

double HRA=(basic*MessageConstants.HRA_PER_PRG)/100;

HRA = Math.round(HRA);

return HRA;
}
public double cal_Ca_PRG(double BASIC){

double CA=(BASIC*MessageConstants.CA_PER_PRG)/100;

CA = Math.round(CA);

return CA;
}
public double cal_Lta_Prg(double basic){

double LTA=(basic*MessageConstants.LTA_PER_PRG)/100;

LTA = Math.round(LTA);

return LTA;
}

public double cal_Others_Prg(double basic){

double OTHERS=(basic*MessageConstants.OTHERS_PER_PRG)/100;

OTHERS = Math.round(OTHERS);

return OTHERS;
}



public double cal_Empr_Pf_Prg(double basic){

double EMPLYER_PF=0.0;

if(basic<15000){

EMPLYER_PF=(basic*MessageConstants.EMPLYER_PF_PER)/100;

EMPLYER_PF = Math.round(EMPLYER_PF);	

}else{

EMPLYER_PF=(15000*MessageConstants.EMPLYER_PF_PER)/100;
	
EMPLYER_PF = Math.round(EMPLYER_PF);

}


return EMPLYER_PF;
}

public double cal_adminchrg_Prg(double basic){


double admin_charge=0.0;

if(basic<15000){

admin_charge=(basic*MessageConstants.ADMIN_CHARGE)/100;

admin_charge = Math.round(admin_charge);	

}else{

admin_charge=(15000*MessageConstants.ADMIN_CHARGE)/100;

admin_charge = Math.round(admin_charge);

}

return admin_charge;
}

public double cal_Pt_Prg(double basic){

double PT;

if(basic<MessageConstants.pt_salary_PRG){

PT=0;
}else if(basic==MessageConstants.pt_salary_PRG && basic< MessageConstants.pt_MAX_salary_PRG){


PT=150;
}else{

PT=200;
}

return PT;
}



public double cal_Empr_ESI(double basic, double gross) {

double EMPLYER_ESI=0.0;

if(gross<15000){
	
	EMPLYER_ESI=(gross*MessageConstants.EMPLYER_ESI_PER)/100;
}
	
EMPLYER_ESI = Math.round(EMPLYER_ESI);


return EMPLYER_ESI;
}



public double cal_Emp_ESI(double basic, double gross) {

 double EMPLYEE_ESI=0.0;
	
	if(gross<15000){
		
		EMPLYEE_ESI=(gross*MessageConstants.EMPLYEE_ESI_PER)/100;
	}
		
	EMPLYEE_ESI = Math.round(EMPLYEE_ESI);


return EMPLYEE_ESI;
}

public double cal_tax_payable(double taxableincome) {

double tax_payble=0.0;

if(taxableincome <= 250000){
	
	tax_payble = Math.round((taxableincome * 10)/100);
	
}else if(taxableincome >= 250001 && taxableincome <= 500000 ){
	
	tax_payble = Math.round((taxableincome * 10)/100);

}else if(taxableincome >= 500001 &&  taxableincome <= 1000000){

	double amount= taxableincome- 500000.0;

	double  one = Math.round((500000.0 * 10)/100);
	double  two = Math.round((amount * 20)/100);
	
	tax_payble = one + two;

	
}else{
	
	double amount= taxableincome- 500000.0;
	
	double  one = Math.round((500000.0 * 10)/100);
	double  two = Math.round((amount * 30)/100);
	
	tax_payble = one + two;;
}



return tax_payble;
}



public double cal_earnedgross(double gross,int payabledays,int totaldays){

 double earned_gross=0.0;

earned_gross=(gross/totaldays)*payabledays;


	return Math.round(earned_gross);

}


 public double cal_EarnedBasic(double basic_actual, long paybledays, int totalDaysInMonth){
			
			double Basic=(basic_actual/totalDaysInMonth)*paybledays;
			
			Basic = Math.round(Basic);
		
			return Basic;
}
		


public double cal_EarnedHra(double hra_actual, long paybledays, int totalDaysInMonth){
			
			double HRA=(hra_actual/totalDaysInMonth)*paybledays;
			
			HRA = Math.round(HRA);
			
			return HRA;
		}
	

public double cal_EarnedCa(double ca_actual, long payabledays,int totalDaysInMonth) {
  
     double CA=(ca_actual/totalDaysInMonth)*payabledays;
	
     CA = Math.round(CA);
	
	return CA;
	
}

public double cal_EarnedMedical(double medical_allowances,long payabledays, int totalDaysInMonth) {

	double Mediacle=(medical_allowances/totalDaysInMonth)*payabledays;
	
	Mediacle = Math.round(Mediacle);
	
	return Mediacle;
}

public double cal_EarnedTeleph(double telephone_allowances,long payabledays, int totalDaysInMonth) {

	double telephone=(telephone_allowances/totalDaysInMonth)*payabledays;
	
	telephone = Math.round(telephone);
	
	return telephone;
}

public double cal_EarnedPerfIncen(double perfincent_allowances,long payabledays, int totalDaysInMonth) {

	double PerformanceInnce=(perfincent_allowances/totalDaysInMonth)*payabledays;
	
	PerformanceInnce = Math.round(PerformanceInnce);
	
	return PerformanceInnce;
}


public double cal_EarnedShifAllowance(double shift_allowance,long payabledays, int totalDaysInMonth) {

	double ShiftAllowance=(shift_allowance/totalDaysInMonth)*payabledays;
	
	ShiftAllowance = Math.round(ShiftAllowance);
	
	return ShiftAllowance;
}

public double cal_EarnedSpecialAllowance(double special_allowance,long payabledays, int totalDaysInMonth) {
	
	double SpecialAllowance=(special_allowance/totalDaysInMonth)*payabledays;
	
	SpecialAllowance = Math.round(SpecialAllowance);
	
	return SpecialAllowance;
}


public double cal_EarnedFoodAllowance(double food_allowance,long payabledays, int totalDaysInMonth) {

	double FoodAllowance=(food_allowance/totalDaysInMonth)*payabledays;
	
	FoodAllowance = Math.round(FoodAllowance);
	
	return FoodAllowance;
}



public double cal_EarnedWashingAllowance(double washing_allowance,long payabledays, int totalDaysInMonth) {
	
	double WashingAllowance=(washing_allowance/totalDaysInMonth)*payabledays;
	
	WashingAllowance = Math.round(WashingAllowance);
	
	return WashingAllowance;
}



public double cal_EarnedOther(double other,long payabledays, int totalDaysInMonth) {
	
	double other_allowance=(other/totalDaysInMonth)*payabledays;
	
	other_allowance = Math.round(other_allowance);
	
	return other_allowance;
}



public double cal_EarnedEmpyerESI(double employer_esi_acutal,long payabledays, int totalDaysInMonth) {
	
	double employer_esi = (employer_esi_acutal/totalDaysInMonth)*payabledays;
	
	employer_esi = Math.round(employer_esi);
	
	return employer_esi;
}



public double cal_EarnedEmpyerPF(double employer_pf_acutal,long payabledays, int totalDaysInMonth) {
	
	double employer_pf = (employer_pf_acutal/totalDaysInMonth)*payabledays;
	
	employer_pf = Math.round(employer_pf);
	
	return employer_pf;
}



public double cal_EarnedEmp_Pf(double earned_basic){

	double EMPLOYEE_PF=0.0;
	
	if(earned_basic >= 15000){
		
		EMPLOYEE_PF=(15000*MessageConstants.EMPLYEE_PF_PER)/100;
	
     }else{
    	 EMPLOYEE_PF=(earned_basic*MessageConstants.EMPLYEE_PF_PER)/100;
     }
		
		
		EMPLOYEE_PF = Math.round(EMPLOYEE_PF);	
		
 return EMPLOYEE_PF;
	
}
	


public double cal_Earnedesi(double earnedgross, long payabledays, int totalDaysInMonth){
	
	
	 double EMPLYEE_ESI=0.0;
		
		if(earnedgross<15000){
			
			EMPLYEE_ESI=(earnedgross*MessageConstants.EMPLYEE_ESI_PER)/100;
		}
			
		EMPLYEE_ESI = Math.round(EMPLYEE_ESI);


	return EMPLYEE_ESI;
	
}




	public double cal_EarnedPt(double earnedgross){
		
		double PT=0.0;
		
		
		if(earnedgross<MessageConstants.pt_salary){
			
			PT=0.0;
		}else{
			
			PT=200;
		}
		
		
		
		return PT;
	}
		
	
	
	
	
		/*public static void main(String[] args) {
			
		    System.out.println(new Calculate_emp_payroll().cal_tax_payable(900000));
			
		}*/

		

		
	

		

		

		
//************* calculations for prg end ******************//


	
	
	
	


	//Calculations for salary details
	
	
	public double cal_Hra(double ctc){
		
		double HRA=(ctc*MessageConstants.HRA_PER)/100;
		
		HRA = Math.round(HRA);
		
		return HRA;
	}
	
	
	
	
	
	
	//For Monthly Payroll 
	
	public double leavededuction(int tot_days,StaffDetailsForPayrollVo vo){
		
		double deduction_amt= (vo.getStaffSalaryDetails().getBasic()/tot_days)*Integer.parseInt(vo.getTotalLeave());
		
		return Math.round(deduction_amt);
		
	}
	
	
	/*public double otherDeductions(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double deduction_amt= Math.round((vo.getStaffSalaryDetails().getOtherdeductions()/totdays)*payabledays);
		
		return deduction_amt;
		
	}*/
	
	
	
	public double hraMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double HRA=Math.round((vo.getStaffSalaryDetails().getHra()/totdays)*payabledays);
		
		return HRA;
	}
	
	public double caMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double CA=Math.round((vo.getStaffSalaryDetails().getCa()/totdays)*payabledays);
		
		return CA;
	}
	
	/*public double daMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double DA=Math.round((vo.getStaffSalaryDetails().getDa()/totdays)*payabledays);
		
		return DA;
	}*/
	
	public double othersMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double OTHERS=Math.round((vo.getStaffSalaryDetails().getOthers()/totdays)*payabledays);
		
		return OTHERS;
	}
	
	public double convieanceMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
				
				double conviance=Math.round((vo.getStaffSalaryDetails().getOthers()/totdays)*payabledays);
				
				return conviance;
	}
	
	public double medicalAllowMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double medicalaloow=Math.round((vo.getStaffSalaryDetails().getMedicalallowances()/totdays)*payabledays);
		
		return medicalaloow;
	}
	

	public double empPfMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double EMPLOYEE_PF=Math.round((vo.getStaffSalaryDetails().getEmployeepf()/totdays)*payabledays);
			
		return EMPLOYEE_PF;
	}

	public double emprPfMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double EMPLYER_PF=Math.round((vo.getStaffSalaryDetails().getEmployerpf()/totdays)*payabledays);
		
		return EMPLYER_PF;
	}
	
	public double ptMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double pt=Math.round((vo.getStaffSalaryDetails().getPt()/totdays)*payabledays);
		
		return pt;
		
	}
	
	public double incomeTaxMonth(StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		double incometax=Math.round((vo.getStaffSalaryDetails().getIncometax()/totdays)*payabledays);
		
		return incometax;
		
	}
	
	/*public double calGross(double basic,StaffDetailsForPayrollVo vo,int payabledays,int totdays){
		
		CalculateStaffSalaryDetails cal=new CalculateStaffSalaryDetails();
		
		double grosssalary=basic+(cal.hraMonth(vo,payabledays,totdays)+cal.daMonth(vo,payabledays,totdays)+cal.caMonth(vo,payabledays,totdays)+cal.medicalAllowMonth(vo,payabledays,totdays)+cal.convieanceMonth(vo,payabledays,totdays)+cal.othersMonth(vo, payabledays, totdays))
				-(cal.empPfMonth(vo,payabledays,totdays)+cal.emprPfMonth(vo,payabledays,totdays)+cal.ptMonth(vo,payabledays,totdays)+cal.incomeTaxMonth(vo,payabledays,totdays)+cal.otherDeductions(vo,payabledays,totdays));
				
				return grosssalary;	
	}*/

	

}
