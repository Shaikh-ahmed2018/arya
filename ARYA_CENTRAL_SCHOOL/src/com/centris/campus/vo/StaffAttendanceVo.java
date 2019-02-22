package com.centris.campus.vo;

public class StaffAttendanceVo {
	
	private String teacherId;
	private String regid;
	private String teacherName;
	private String designation;
	private String department;
	private String status;
	private int count;
	
	
	private String date;
	private String tot_count;
	private String present_count;
	private String absent_count;
	private String holiday_count;
	private String leave_count;
	private String inTime;
	private String outTime;
	private String late;
	private String early;
	
	
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	public String getEarly() {
		return early;
	}
	public void setEarly(String early) {
		this.early = early;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTot_count() {
		return tot_count;
	}
	public void setTot_count(String tot_count) {
		this.tot_count = tot_count;
	}
	public String getPresent_count() {
		return present_count;
	}
	public void setPresent_count(String present_count) {
		this.present_count = present_count;
	}
	public String getAbsent_count() {
		return absent_count;
	}
	public void setAbsent_count(String absent_count) {
		this.absent_count = absent_count;
	}
	public String getHoliday_count() {
		return holiday_count;
	}
	public void setHoliday_count(String holiday_count) {
		this.holiday_count = holiday_count;
	}
	public String getLeave_count() {
		return leave_count;
	}
	public void setLeave_count(String leave_count) {
		this.leave_count = leave_count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	

}
