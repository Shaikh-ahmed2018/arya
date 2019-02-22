package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class AbsentsSMSForm extends ActionForm{
	
	private String category;
	private String classList;
	private String sectionList;
	private String date;
	private String smstext;
	private String student;
	private String[] classListArray;
	private String createdUser;
	private String[] sectionListArray;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getClassList() {
		return classList;
	}
	public void setClassList(String classList) {
		this.classList = classList;
	}
	public String getSectionList() {
		return sectionList;
	}
	public void setSectionList(String sectionList) {
		this.sectionList = sectionList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String[] getClassListArray() {
		return classListArray;
	}
	public void setClassListArray(String[] classListArray) {
		this.classListArray = classListArray;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String[] getSectionListArray() {
		return sectionListArray;
	}
	public void setSectionListArray(String[] sectionListArray) {
		this.sectionListArray = sectionListArray;
	}
	
	
	
	
	
	

}
