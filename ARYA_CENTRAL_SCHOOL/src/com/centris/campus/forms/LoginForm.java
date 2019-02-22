package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	
	private String username;
	private String password;
	private String academicYear;
	private String internal_ip;
	
	
	public String getInternal_ip() {
		return internal_ip;
	}
	public void setInternal_ip(String internal_ip) {
		this.internal_ip = internal_ip;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
