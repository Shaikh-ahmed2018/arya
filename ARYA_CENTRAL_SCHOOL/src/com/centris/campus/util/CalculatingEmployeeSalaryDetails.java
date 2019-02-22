package com.centris.campus.util;

import java.sql.Connection;
import java.sql.ResultSet;

import com.centris.campus.pojo.PayrolDeductionsPojo;
import com.centris.campus.vo.CalculateTimeAmount;
import com.centris.campus.vo.StaffEmployementVo;
import com.mysql.jdbc.PreparedStatement;

public class CalculatingEmployeeSalaryDetails  {
	
	
	public PayrolDeductionsPojo calculateNetSalary(StaffEmployementVo empsalaryvo,double grosssalary,Connection connection){
		
		double netsalary=grosssalary;
		Connection conn=connection;
		PayrolDeductionsPojo payrolDeductionsPojo=new PayrolDeductionsPojo();
		
		double ot_bft_sft_ded=0;
		String ot_bft_sft_time="00:00:00";
		double ot_bft_aftr_ded=0;
		String ot_bft_aftr_time="00:00:00";
		
		double late_ded=0;
		String late_time="00:00:00";
		double early_ded=0;
		String early_time="00:00:00";
		
			
		if("Y".equalsIgnoreCase(empsalaryvo.getLatedeductions())){
			
			CalculateTimeAmount timeamount_late=new CalculatingEmployeeSalaryDetails().lateDeductions(empsalaryvo,grosssalary,conn);
			CalculateTimeAmount timeamount_early=new CalculatingEmployeeSalaryDetails().earlyLeaveDeductions(empsalaryvo,grosssalary,conn);
			
			late_ded=timeamount_late.getAmount();
			late_time=timeamount_late.getTime();
			early_ded=timeamount_early.getAmount();
			early_time=timeamount_early.getTime();
						
			netsalary=netsalary-(late_ded+early_ded);
			
		}
		
		if("Y".equalsIgnoreCase(empsalaryvo.getOt())){
			
			CalculateTimeAmount timeamount_bft_shft=new CalculatingEmployeeSalaryDetails().overTimeBeforeShift(empsalaryvo,grosssalary,conn);
			CalculateTimeAmount timeamount_aftr_shft=new CalculatingEmployeeSalaryDetails().overTimeAfterShift(empsalaryvo,grosssalary,conn);
			
			 ot_bft_sft_ded=timeamount_bft_shft.getAmount();
			 ot_bft_sft_time=timeamount_bft_shft.getTime();
			 
			 ot_bft_aftr_ded=timeamount_aftr_shft.getAmount();
			 ot_bft_aftr_time=timeamount_aftr_shft.getTime();
		
			netsalary=netsalary+(ot_bft_sft_ded+ot_bft_aftr_ded);
		}
		
		netsalary = Math.round(netsalary);
		
		payrolDeductionsPojo.setEarlydeductions(early_ded);
		payrolDeductionsPojo.setEarlytime(early_time);
		payrolDeductionsPojo.setLatedeductions(late_ded);
		payrolDeductionsPojo.setLatetime(late_time);
		payrolDeductionsPojo.setOt_aftrshift_ded(ot_bft_aftr_ded);
		payrolDeductionsPojo.setOt_aftrshift_time(ot_bft_aftr_time);
		payrolDeductionsPojo.setOt_beforeshift_ded(ot_bft_sft_ded);
		payrolDeductionsPojo.setOt_bfrshift_time(ot_bft_sft_time);
		payrolDeductionsPojo.setNetsalary(netsalary);
		
		
		return payrolDeductionsPojo;
		
	}
	
	
	
	public double calculateGrossSalary(double salary_aftr_leave_ded,StaffEmployementVo emp_salary_vo){
		
		CalculatingEmployeeSalaryDetails cal=new CalculatingEmployeeSalaryDetails();
		double grosssalary=0.0;
		
		/*double grosssalary=salary_aftr_leave_ded+
		(cal.calculateHRA(salary_aftr_leave_ded)+cal.conveyance(salary_aftr_leave_ded)+cal.medical(salary_aftr_leave_ded)+cal.da(salary_aftr_leave_ded)+emp_salary_vo.getAllowance()+emp_salary_vo.getTransportallowances()+emp_salary_vo.getCa())
		-(cal.employeePF(salary_aftr_leave_ded)+cal.employeRPF(salary_aftr_leave_ded)+cal.profissionalTax(salary_aftr_leave_ded)+emp_salary_vo.getIncometax()+emp_salary_vo.getOt());
		*/
		
		return grosssalary;
	}
	
	
	
	
	public  double calculateHRA(double basicsalary){
		
		double HRA=(basicsalary*MessageConstants.HRA_PERCENTAGE)/100;
		
		HRA = Math.round(HRA * 100);
		HRA = HRA/100;
		
		return HRA;
		
	}
	
	public int conveyance(double basic){
		
		int CONVEYANCE=0;
		
		if(basic!=0.0){
		
		 CONVEYANCE=MessageConstants.CONVEYANCE;
		}
		
		return CONVEYANCE;
		
	}
	
public int medical(double basic){
	
	int MEDICAL=0;
		
	if(basic!=0.0){
		
		 MEDICAL=MessageConstants.MEDICA_REIMUBURSEMENT;
	}
		
		return MEDICAL;
		
	}

public double da(double basicsalary){
	
	double DA=(basicsalary*MessageConstants.DA)/100;
	
	
	return DA;
	
}

public double employeePF(double basicsalary){
	
	double EMPLOYEEPF=(basicsalary*MessageConstants.EMPLOYEE_PF)/100;
	
	
	EMPLOYEEPF = Math.round(EMPLOYEEPF * 100);
	EMPLOYEEPF = EMPLOYEEPF/100;
	
	return EMPLOYEEPF;
	
}


public double employeRPF(double basicsalary){
	
	double employeRPF=(basicsalary*MessageConstants.EMPLOYER_PF)/100;
	
	
	employeRPF = Math.round(employeRPF * 100);
	employeRPF = employeRPF/100;
	
	return employeRPF;
	
}

public double profissionalTax(double basicsalary){
	
	
	
	  int salary1 = Double.compare(MessageConstants.salary1, basicsalary);
	  int salary2 =Double.compare(MessageConstants.salary2, basicsalary);
	  int salary3=Double.compare(MessageConstants.salary3, basicsalary);
	  int salary4=Double.compare(MessageConstants.salary4, basicsalary);
	  
	  double profissionalTax=0.0;
	
	if(salary1>=0){
		
		 profissionalTax=MessageConstants.PF_9999;
		 
		 
		
	}else if(salary2<=0 && salary3>=0){
		
		 profissionalTax=MessageConstants.PF_10000_14999;
	}else if(salary4<=0){
		
		 profissionalTax=MessageConstants.PF_15000;
	}
	
	

	profissionalTax = Math.round(profissionalTax * 100);
	profissionalTax = profissionalTax/100;
	
	return profissionalTax;
	
}


public CalculateTimeAmount lateDeductions(StaffEmployementVo vo,double grosssalary,Connection connection){
	
	PreparedStatement ps_latedeductions=null;
	ResultSet rs_latededuction=null;
	Connection conn=connection;
	String latetime=null;
	int no_lateexec=0;
	int year=vo.getYear();
	int month=vo.getMonth();
	
	 CalculatingEmployeeSalaryDetails cal=new CalculatingEmployeeSalaryDetails();
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


public CalculateTimeAmount earlyLeaveDeductions(StaffEmployementVo vo,double grosssalary,Connection connection){
	
	PreparedStatement ps_latedeductions=null;
	ResultSet rs_latededuction=null;
	String earlytime=null;
	int no_earlyexec=0;
	int year=vo.getYear();
	int month=vo.getMonth();
	Connection conn=connection;
	 
	 CalculatingEmployeeSalaryDetails cal=new CalculatingEmployeeSalaryDetails();
		CalculateTimeAmount cal_vo=new CalculateTimeAmount();
	 
	 	String startdate=year+"-"+month+"-01";
		String enddate= year+"-"+month+"-"+HelperClass.getDaysByMonthAndYear(month, year);
	
	try {
		ps_latedeductions = (PreparedStatement) conn.prepareStatement(SQLUtilConstants.CALCULATE_LATE_TIME);
		ps_latedeductions.setString(1, startdate);
		ps_latedeductions.setString(2, enddate);
		ps_latedeductions.setString(3, vo.getEmpid());
		
		rs_latededuction=ps_latedeductions.executeQuery();
		
		while(rs_latededuction.next()){
			
			earlytime=rs_latededuction.getString("EarlyTime");
			no_earlyexec=Integer.parseInt(rs_latededuction.getString("earlyexec"));
			
		}
		
		 cal_vo=cal.calculatingAmmountforLate(month, year, grosssalary, earlytime, no_earlyexec);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return cal_vo;
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
		// TODO Auto-generated catch block
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

public double leavededuction(int totaldays,double salary,int payabledays){
	
	double perdaysalary=salary/totaldays;
	double leavededuction=Math.round(perdaysalary*(totaldays-payabledays));
	
	return leavededuction;
}


public CalculateTimeAmount calculatingAmmountforOT(int month,int year,double salary,String time){
	
	 int totalDaysInMonth = HelperClass.getDaysByMonthAndYear(month, year);
	 double incentiveDays = salary/(totalDaysInMonth);
	 double incentiveHours = incentiveDays/9;
	 double incentivesMinutes = incentiveHours/60;
	 String tot_time="00:00:00";
	
	 CalculateTimeAmount vo=new CalculateTimeAmount();
	 
	
	if(time!=null){
		 String[] overtime_bfrsft_array=time.split(",");
		 
		 tot_time=HelperClass.addTimeArray(overtime_bfrsft_array);
		 
		 if(tot_time.equalsIgnoreCase(null)){
			 tot_time="00:00:00";
			}
		 
	
		
	String[] hourminSec = HelperClass.addTimeArray(overtime_bfrsft_array).split(":");
	vo.setAmount(Math.round((((Integer.parseInt(hourminSec[0]) * 60) +  Integer.parseInt(hourminSec[1])) * incentivesMinutes))) ;

	}
	
	 vo.setTime(tot_time);
	
	
	return vo;
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





public static void main(String[] args) {
	
	
	
	StaffEmployementVo empsalaryvo=new StaffEmployementVo();
	

	empsalaryvo.setMonth(9);
	empsalaryvo.setYear(2014);
	empsalaryvo.setBasic(3000);
	empsalaryvo.setLatedeductions("Y");
	empsalaryvo.setLeavedeductions("Y");
	empsalaryvo.setOt("Y");
	
	//c.calculateGrossSalary(empsalaryvo, "1");
}


	



}
