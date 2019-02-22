package com.centris.campus.util;

import com.centris.campus.vo.SalaryDetailsVo;

public class PayrollCalculations {
	public static double calEarnedGross(SalaryDetailsVo salaryDetailsVo,int payabledays,int totaldays){
		double earned_gross=0.0;
		earned_gross=(salaryDetailsVo.getTotalsalary()/totaldays)*payabledays;
		return Math.round(earned_gross);
	}

	public static double calculateBasic(SalaryDetailsVo salary_vo, int payabledays,int totaldays) {
		double basic=(salary_vo.getBasic()/totaldays)*payabledays;
		return Math.round(basic);
	}
	
	public static double calEarnedConveyance(SalaryDetailsVo salary_vo,int payabledays,int totaldays){
		double earned_conveyence=0.0;
		earned_conveyence=(salary_vo.getConvieance()/totaldays)*payabledays;
		return Math.round(earned_conveyence);
	}

	public static double calculateDa(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_da=0.0;
		earned_da=(salary_vo.getConvieance()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_da);
	}

	public static double calEarnedHra(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_hra=0.0;
		earned_hra=(salary_vo.getHra()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_hra);
	}

	public static double calEarnedPerformenceIncentive(SalaryDetailsVo salary_vo, int payabledayscount,
			int totalDaysInMonth) {
		double earned_pi=0.0;
		earned_pi=(salary_vo.getPerformanceIncentive()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_pi);
	}

	public static double calEarnedSpecial(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_spl=0.0;
		earned_spl=(salary_vo.getSpecialAllowance()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_spl);
	}

	public static double calEarnedFood(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_food=0.0;
		earned_food=(salary_vo.getFoodAllowance()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_food);
	}

	public static double calEarnedChildEdu(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_child=0.0;
		earned_child=(salary_vo.getChildEdu()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_child);
	}

	public static double calEarnedArrear(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_arrear=0.0;
		earned_arrear=(salary_vo.getArrears()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_arrear);
	}

	public static double calEarnedReimburse(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_reimburse=0.0;
		earned_reimburse=(salary_vo.getReimbursement()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_reimburse);
	}

	public static double calEarnedInternet(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_internet=0.0;
		earned_internet=(salary_vo.getInternetExpense()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_internet);
	}

	public static double calEarnedDriver(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_driver=0.0;
		earned_driver=(salary_vo.getDriverSalary()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_driver);
	}

	public static double calEarnedLeave(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_leave=0.0;
		earned_leave=(salary_vo.getLeaveEncash()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_leave);
	}

	public static double calEarnedMedical(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_med=0.0;
		earned_med=(salary_vo.getMedicalReimbursement()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_med);
	}

	public static double calEarnedOthers(SalaryDetailsVo salary_vo, int payabledayscount, int totalDaysInMonth) {
		double earned_other=0.0;
		earned_other=(salary_vo.getOthers()/totalDaysInMonth)*payabledayscount;
		return Math.round(earned_other);
	}

	public static double calculatePfEmpr(SalaryDetailsVo salaryDetailsVo,double basicda){
		double pf_empr=0.0;
		if(basicda>=salaryDetailsVo.getPfEmployerAmount()){
			pf_empr=(basicda*12)/100;
		}else{
			pf_empr=(basicda*12)/100;
		}
		return Math.round(pf_empr);
	}

	public static double calculatePfEmpy(SalaryDetailsVo salary_vo, double basicda) {
		double pf_empr=0.0;
		
		if(basicda>=salary_vo.getPfEmployerAmount()){
			pf_empr=(basicda*12)/100;
		}else{
			pf_empr=0.0;
		}
		return Math.round(pf_empr);
	}

	public static double calculateEsiEmpr(SalaryDetailsVo salary_vo, double earned_grossSalary) {
		double esi_empr=0.0;
		if(earned_grossSalary<=20000){
			esi_empr=(earned_grossSalary*4.75)/100;
		}
		return Math.round(esi_empr);
	}

	public static double calculateEsiEmpy(SalaryDetailsVo salary_vo, double earned_grossSalary) {
		double esi_empy=0.0;
		if(earned_grossSalary<=20000){
			esi_empy=(earned_grossSalary*1.75)/100;
		}
		return Math.round(esi_empy);
	}
	
	public static double calculateProfTax(SalaryDetailsVo salary_vo, double earnedBasicDa) {
		double pt=0.0;
		if(earnedBasicDa>0){
			pt=(salary_vo.getTaxworkProfTax()/12);
		}else{
			pt=pt;
		}
		
		return Math.round(pt);
	}

	public static double calculateIncomeTax(SalaryDetailsVo salary_vo, double earnedBasicDa) {
		double tax=0.0;
		if(earnedBasicDa>0){
			tax=(salary_vo.getTaxworkPerMonth());
		}else{
			tax=tax;
		}
		
		return Math.round(tax);
	}
}
