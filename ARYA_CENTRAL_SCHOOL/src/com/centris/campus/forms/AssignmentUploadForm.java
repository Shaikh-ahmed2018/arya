package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class AssignmentUploadForm extends ActionForm {
	
	private String assname;
	private String assdate;
	private String compdate;
	private String maxid;
	private String streamid;
	private String classid;
	private String section;
	private String subject;
	private String[] student;
	private String description;
	
	
	public String getAssname() {
		return assname;
	}
	public void setAssname(String assname) {
		this.assname = assname;
	}
	public String getAssdate() {
		return assdate;
	}
	public void setAssdate(String assdate) {
		this.assdate = assdate;
	}
	public String getCompdate() {
		return compdate;
	}
	public void setCompdate(String compdate) {
		this.compdate = compdate;
	}
	public String getMaxid() {
		return maxid;
	}
	public void setMaxid(String maxid) {
		this.maxid = maxid;
	}
	public String getStreamid() {
		return streamid;
	}
	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getStudent() {
		return student;
	}
	public void setStudent(String[] student) {
		this.student = student;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}
