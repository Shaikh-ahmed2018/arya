package com.centris.campus.vo;

import org.apache.struts.upload.FormFile;

@SuppressWarnings("unused")
public class ParentFeedbackVo {

	
	private String feedbackid;
	private String feedbackto;
	private String description;
	private String addfile;
	private String createUser;
	private String accyear;
	private int sno;
	
	
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
	public String getAddfile() {
		return addfile;
	}
	public void setAddfile(String addfile) {
		this.addfile = addfile;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	
	
	
	
	
}
