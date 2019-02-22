package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.List;

public class NonBoardSalaryvo {

	
	private String empId;
	private String empName;
	private String totalDaysInMonth;
	private String totalPresent;
	private String totalAbsent;
	private ArrayList<String> leavedates;
	private List<String> totalLeaveDate;
	private int totalLeaveCount;
	private List<String> totalAbsentDate;
	private String teacherType;
	private double nonBoardsalary;
	private double nonBoardPf;
	private double advance;
	private int sno;
	private double nbActualSalary;
	private double nbActualPf;
	private double noOfleave;
	private double festadvance;
	private double netPay;
	private double lop;
	private int month;
	private int year;
	private String updateuser;
	private double mainAdvance;
	private int absentcount;
	private int leavecount;
	private List<String> leaveRequestdate;
	private int requestcount;
	
	private String accountno;
	private String processedStatus;
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
	public String getTotalDaysInMonth() {
		return totalDaysInMonth;
	}
	public void setTotalDaysInMonth(String totalDaysInMonth) {
		this.totalDaysInMonth = totalDaysInMonth;
	}
	public String getTotalPresent() {
		return totalPresent;
	}
	public void setTotalPresent(String totalPresent) {
		this.totalPresent = totalPresent;
	}
	public String getTotalAbsent() {
		return totalAbsent;
	}
	public void setTotalAbsent(String totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	public ArrayList<String> getLeavedates() {
		return leavedates;
	}
	public void setLeavedates(ArrayList<String> leavedates) {
		this.leavedates = leavedates;
	}
	public List<String> getTotalLeaveDate() {
		return totalLeaveDate;
	}
	public void setTotalLeaveDate(List<String> totalLeaveDate) {
		this.totalLeaveDate = totalLeaveDate;
	}
	public int getTotalLeaveCount() {
		return totalLeaveCount;
	}
	public void setTotalLeaveCount(int totalLeaveCount) {
		this.totalLeaveCount = totalLeaveCount;
	}
	public List<String> getTotalAbsentDate() {
		return totalAbsentDate;
	}
	public void setTotalAbsentDate(List<String> totalAbsentDate) {
		this.totalAbsentDate = totalAbsentDate;
	}
	public String getTeacherType() {
		return teacherType;
	}
	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}
	public double getNonBoardsalary() {
		return nonBoardsalary;
	}
	public void setNonBoardsalary(double nonBoardsalary) {
		this.nonBoardsalary = nonBoardsalary;
	}
	public double getNonBoardPf() {
		return nonBoardPf;
	}
	public void setNonBoardPf(double nonBoardPf) {
		this.nonBoardPf = nonBoardPf;
	}
	public double getAdvance() {
		return advance;
	}
	public void setAdvance(double advance) {
		this.advance = advance;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public double getNbActualSalary() {
		return nbActualSalary;
	}
	public void setNbActualSalary(double nbActualSalary) {
		this.nbActualSalary = nbActualSalary;
	}
	public double getNbActualPf() {
		return nbActualPf;
	}
	public void setNbActualPf(double nbActualPf) {
		this.nbActualPf = nbActualPf;
	}
	public double getNoOfleave() {
		return noOfleave;
	}
	public void setNoOfleave(double noOfleave) {
		this.noOfleave = noOfleave;
	}
	public double getFestadvance() {
		return festadvance;
	}
	public void setFestadvance(double festadvance) {
		this.festadvance = festadvance;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public double getLop() {
		return lop;
	}
	public void setLop(double lop) {
		this.lop = lop;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public double getMainAdvance() {
		return mainAdvance;
	}
	public void setMainAdvance(double mainAdvance) {
		this.mainAdvance = mainAdvance;
	}
	public int getAbsentcount() {
		return absentcount;
	}
	public void setAbsentcount(int absentcount) {
		this.absentcount = absentcount;
	}
	public int getLeavecount() {
		return leavecount;
	}
	public void setLeavecount(int leavecount) {
		this.leavecount = leavecount;
	}
	public List<String> getLeaveRequestdate() {
		return leaveRequestdate;
	}
	public void setLeaveRequestdate(List<String> leaveRequestdate) {
		this.leaveRequestdate = leaveRequestdate;
	}
	public int getRequestcount() {
		return requestcount;
	}
	public void setRequestcount(int requestcount) {
		this.requestcount = requestcount;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getProcessedStatus() {
		return processedStatus;
	}
	public void setProcessedStatus(String processedStatus) {
		this.processedStatus = processedStatus;
	}
	
	
	
	
	private String SalaryArray;
	private String festadvanceArray;
	private String mainadvArray;
	private String lopArray;
	private String netSalaryarray;
	
	
	public String getSalaryArray() {
		return SalaryArray;
	}
	public void setSalaryArray(String salaryArray) {
		SalaryArray = salaryArray;
	}
	public String getFestadvanceArray() {
		return festadvanceArray;
	}
	public void setFestadvanceArray(String festadvanceArray) {
		this.festadvanceArray = festadvanceArray;
	}
	public String getMainadvArray() {
		return mainadvArray;
	}
	public void setMainadvArray(String mainadvArray) {
		this.mainadvArray = mainadvArray;
	}
	public String getLopArray() {
		return lopArray;
	}
	public void setLopArray(String lopArray) {
		this.lopArray = lopArray;
	}
	public String getNetSalaryarray() {
		return netSalaryarray;
	}
	public void setNetSalaryarray(String netSalaryarray) {
		this.netSalaryarray = netSalaryarray;
	}
	
	
	
	
	
	
	
}
