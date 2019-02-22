package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class CareersViewForm extends ActionForm{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jobcode;
	 private String qualification;
	 private String noofposition;
	 private String experience;
	 private String description;
	 private String status;
	 
	public String getJobcode() {
		return jobcode;
	}
	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getNoofposition() {
		return noofposition;
	}
	public void setNoofposition(String noofposition) {
		this.noofposition = noofposition;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
