package com.centris.campus.vo;

import org.apache.struts.action.ActionForm;

public class SmsVo extends ActionForm{
	
	private String homeworkid;
	private String date;
	private String classname;
	private String classid;
	private String sectionname;
	private String sectionid;
	private String subjectid;
	private String subjectname;
	private String description;
	
	
	private String meetingid;
	
	
	private String createuser;
	
	private String studentid [];

	public String getHomeworkid() {
		return homeworkid;
	}

	public void setHomeworkid(String homeworkid) {
		this.homeworkid = homeworkid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String[] getStudentid() {
		return studentid;
	}

	public void setStudentid(String[] studentid) {
		this.studentid = studentid;
	}
	
	
	
	
	
	
	
	

}
