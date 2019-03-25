package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class SingleSMSForm extends ActionForm {
	private String mobileno;
	private String message;
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
