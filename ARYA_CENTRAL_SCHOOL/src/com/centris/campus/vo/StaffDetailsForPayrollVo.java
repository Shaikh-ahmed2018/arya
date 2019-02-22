package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.List;

public class StaffDetailsForPayrollVo {

	private String empId;
	private String empName;
	private String accountNumber;
	private String regId;
	private String totalDaysInMonth;
	private String totalPresent;
	private String totalAbsent;
	private List<String> totalPresentDate;
	private List<String> totalAbsentDate;
	private String totalLeave;
	private String totalHoliday;
	private StaffEmployementVo staffSalaryDetails;
	private String totalWeeklyOff;
	private String departmentname;
	private String designation;
	private String doj;
	private ArrayList<String> leavedates;
	private ArrayList<String> holidayDates;
	private ArrayList<String> absentdates;
	private ArrayList<String> presentdates;
	private ArrayList<String> holidaydates;
	private ArrayList<String> weekoffdates;
	
	public ArrayList<String> getAbsentdates() {
		return absentdates;
	}
	public void setAbsentdates(ArrayList<String> absentdates) {
		this.absentdates = absentdates;
	}
	public ArrayList<String> getPresentdates() {
		return presentdates;
	}
	public void setPresentdates(ArrayList<String> presentdates) {
		this.presentdates = presentdates;
	}
	public ArrayList<String> getHolidaydates() {
		return holidaydates;
	}
	public void setHolidaydates(ArrayList<String> holidaydates) {
		this.holidaydates = holidaydates;
	}
	public ArrayList<String> getWeekoffdates() {
		return weekoffdates;
	}
	public void setWeekoffdates(ArrayList<String> weekoffdates) {
		this.weekoffdates = weekoffdates;
	}
	public String getTotalWeeklyOff() {
		return totalWeeklyOff;
	}
	public void setTotalWeeklyOff(String totalWeeklyOff) {
		this.totalWeeklyOff = totalWeeklyOff;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	public List<String> getTotalPresentDate() {
		return totalPresentDate;
	}
	public void setTotalPresentDate(List<String> totalPresentDate) {
		this.totalPresentDate = totalPresentDate;
	}
	public String getTotalAbsent() {
		return totalAbsent;
	}
	public void setTotalAbsent(String totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	public List<String> getTotalAbsentDate() {
		return totalAbsentDate;
	}
	public void setTotalAbsentDate(List<String> totalAbsentDate) {
		this.totalAbsentDate = totalAbsentDate;
	}
	public String getTotalLeave() {
		return totalLeave;
	}
	public void setTotalLeave(String totalLeave) {
		this.totalLeave = totalLeave;
	}
	public ArrayList<String> getLeavedates() {
		return leavedates;
	}
	public void setLeavedates(ArrayList<String> leavedates) {
		this.leavedates = leavedates;
	}
	public String getTotalHoliday() {
		return totalHoliday;
	}
	public void setTotalHoliday(String totalHoliday) {
		this.totalHoliday = totalHoliday;
	}
	public ArrayList<String> getHolidayDates() {
		return holidayDates;
	}
	public void setHolidayDates(ArrayList<String> holidayDates) {
		this.holidayDates = holidayDates;
	}
	public StaffEmployementVo getStaffSalaryDetails() {
		return staffSalaryDetails;
	}
	public void setStaffSalaryDetails(StaffEmployementVo staffSalaryDetails) {
		this.staffSalaryDetails = staffSalaryDetails;
	}


}
