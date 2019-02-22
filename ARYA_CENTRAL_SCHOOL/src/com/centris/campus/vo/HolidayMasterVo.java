package com.centris.campus.vo;

import java.util.List;

public class HolidayMasterVo {

	
	private String locId;
	private String locName;
	private String desc;
	private String depLoc;
	private String locManagerMail;
	private String hrManagerMail;
	private String onsiteManagerMail;
	private String year;
	private String holidaysName;
	private String holidayType;
	private String accyname;
	private String holId;
	
	public String getHolId() {
		return holId;
	}
	public void setHolId(String holId) {
		this.holId = holId;
	}
	public String getAccyname() {
		return accyname;
	}
	public void setAccyname(String accyname) {
		this.accyname = accyname;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getHolidaysName() {
		return holidaysName;
	}
	public void setHolidaysName(String holidaysName) {
		this.holidaysName = holidaysName;
	}                                                           
	public String getDate() {
		return date;                                           
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	String date;
	String weekDay;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	List depRecords;
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDepLoc() {
		return depLoc;
	}
	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}
	public String getLocManagerMail() {
		return locManagerMail;
	}
	public void setLocManagerMail(String locManagerMail) {
		this.locManagerMail = locManagerMail;
	}
	public String getHrManagerMail() {
		return hrManagerMail;
	}
	public void setHrManagerMail(String hrManagerMail) {
		this.hrManagerMail = hrManagerMail;
	}
	
	public List getDepRecords() {
		return depRecords;
	}
	public void setDepRecords(List depRecords) {
		this.depRecords = depRecords;
	}
	public String getOnsiteManagerMail() {
		return onsiteManagerMail;
	}
	public void setOnsiteManagerMail(String onsiteManagerMail) {
		this.onsiteManagerMail = onsiteManagerMail;
	}

}
