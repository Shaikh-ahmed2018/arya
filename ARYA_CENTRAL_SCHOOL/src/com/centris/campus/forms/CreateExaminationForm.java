package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class CreateExaminationForm extends ActionForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String examId;
	private String examname;
	private String examdate;
	private String enddate;
	private String accyear;
	private String description;
	private String accyearId;
	private String createUser;
	private String accadamicyear;
	
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccyearId() {
		return accyearId;
	}
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	public String getAccadamicyear() {
		return accadamicyear;
	}
	public void setAccadamicyear(String accadamicyear) {
		this.accadamicyear = accadamicyear;
	}
	

}
