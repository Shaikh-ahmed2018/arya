package com.centris.campus.forms;

public class HolidayMasterForm {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String department; 
	String location; 
	 public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		
		this.department = department;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public String[] getWeekday() {
		return weekday;
	}
	public void setWeekday(String[] weekday) {
		this.weekday = weekday;
	}
	public String[] getHoliday() {
		return holiday;
	}
	public void setHoliday(String[] holiday) {
		this.holiday = holiday;
	}
	String year;
	 String[] date; 
	 String[] weekday; 
	 String[] holiday;
	 String[] holiday_type;
	 
	public String[] getHoliday_type() {
		return holiday_type;
	}
	public void setHoliday_type(String[] holiday_type) {
		this.holiday_type = holiday_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
