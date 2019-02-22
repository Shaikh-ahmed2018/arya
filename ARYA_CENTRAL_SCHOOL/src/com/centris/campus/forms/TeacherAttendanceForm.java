package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class TeacherAttendanceForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teacherId;	
	private String status;
	private String date;
	private String createdBy;
	private String[] teacherIdArr;
	private String[] statusArr;
	
	public String[] getTeacherIdArr() {
		return teacherIdArr;
	}
	public void setTeacherIdArr(String[] teacherIdArr) {
		this.teacherIdArr = teacherIdArr;
	}
	public String[] getStatusArr() {
		return statusArr;
	}
	public void setStatusArr(String[] statusArr) {
		this.statusArr = statusArr;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
