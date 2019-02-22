package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class OnlineFeeReceiptForn extends ActionForm{
	
	private String accyear;
	private String sectionid;
	private String classname;
	private String studentname;
	
	
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	
	
	
	
	

}
