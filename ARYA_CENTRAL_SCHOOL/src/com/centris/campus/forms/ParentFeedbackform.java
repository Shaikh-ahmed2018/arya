package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ParentFeedbackform  extends ActionForm{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedbackid;
	private String feedbackto;
	private String description;
	private FormFile addfile;
	private String createUser;
	private String accyear;
	private String addfile1;
	
	
	
	
	

	public FormFile getAddfile() {
		return addfile;
	}
	public void setAddfile(FormFile addfile) {
		this.addfile = addfile;
	}
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getFeedbackto() {
		return feedbackto;
	}
	public void setFeedbackto(String feedbackto) {
		this.feedbackto = feedbackto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAddfile1() {
		return addfile1;
	}
	public void setAddfile1(String addfile1) {
		this.addfile1 = addfile1;
	}
	
	
	
	

}
