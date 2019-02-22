package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class LeaveDetailsReportForm extends ActionForm {
	
	private String accyear;
	private String teachingtype;
	private String department;
	private String teachername;
	private String location;
	private String haccyear;
	private String hteachertype;
	private String hdepartment;
	private String hteachername;
	
	
	
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHaccyear() {
		return haccyear;
	}
	public void setHaccyear(String haccyear) {
		this.haccyear = haccyear;
	}
	public String getHteachertype() {
		return hteachertype;
	}
	public void setHteachertype(String hteachertype) {
		this.hteachertype = hteachertype;
	}
	public String getHdepartment() {
		return hdepartment;
	}
	public void setHdepartment(String hdepartment) {
		this.hdepartment = hdepartment;
	}
	public String getHteachername() {
		return hteachername;
	}
	public void setHteachername(String hteachername) {
		this.hteachername = hteachername;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getTeachingtype() {
		return teachingtype;
	}
	public void setTeachingtype(String teachingtype) {
		this.teachingtype = teachingtype;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	
	

}
