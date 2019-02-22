package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class StudentPromotionForm2 extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acadamicyear;
	private String category;
	private String classname;
	private String section;
	
	
	public String getAcadamicyear() {
		return acadamicyear;
	}
	public void setAcadamicyear(String acadamicyear) {
		this.acadamicyear = acadamicyear;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
	
}
