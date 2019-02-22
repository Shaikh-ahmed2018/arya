package com.centris.campus.vo;

public class LeaveViewDetailsVo {
	
	private String accyear;
	
	private double openingbal;
	private double consumebal;
	private double closingbal;
	
	private String leavetype;
	
	private String startdate;
	private String enddate;
	private String startsession;
	private String endsession;
	private String current_user;
	private String locationid;
	private double totalleaves;
	private String total_leave_year;
	private String leaveID;
	
	
	public String getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
	}

	public String getTotal_leave_year() {
		return total_leave_year;
	}

	public void setTotal_leave_year(String total_leave_year) {
		this.total_leave_year = total_leave_year;
	}

	public String getAccyear() {
		return accyear;
	}

	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}

	public double getOpeningbal() {
		return openingbal;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStartsession() {
		return startsession;
	}

	public void setStartsession(String startsession) {
		this.startsession = startsession;
	}

	public String getEndsession() {
		return endsession;
	}

	public void setEndsession(String endsession) {
		this.endsession = endsession;
	}

	public String getCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(String current_user) {
		this.current_user = current_user;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public double getTotalleaves() {
		return totalleaves;
	}

	public void setTotalleaves(double d) {
		this.totalleaves = d;
	}

	public void setOpeningbal(double openingbal) {
		this.openingbal = openingbal;
	}

	public double getConsumebal() {
		return consumebal;
	}

	public void setConsumebal(double consumebal) {
		this.consumebal = consumebal;
	}

	public double getClosingbal() {
		return closingbal;
	}

	public void setClosingbal(double closingbal) {
		this.closingbal = closingbal;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
}
