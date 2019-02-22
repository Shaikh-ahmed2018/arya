package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class UploadMarksForm extends ActionForm  {
	private static final long serialVersionUID = 1L;
	
	private String hExamId;
	private String hclass;
	private String hSectionId;
	private String hSubjectId;
	private String maxmarks;
	private String minPassmarks;
	private String reqmarks;
	private String[] studentIdArray;
	private String[] acuiredmarksArray;
	
	
	public String gethExamId() {
		return hExamId;
	}
	public void sethExamId(String hExamId) {
		this.hExamId = hExamId;
	}
	public String getHclass() {
		return hclass;
	}
	public void setHclass(String hclass) {
		this.hclass = hclass;
	}
	public String gethSectionId() {
		return hSectionId;
	}
	public void sethSectionId(String hSectionId) {
		this.hSectionId = hSectionId;
	}
	public String gethSubjectId() {
		return hSubjectId;
	}
	public void sethSubjectId(String hSubjectId) {
		this.hSubjectId = hSubjectId;
	}
	public String getMaxmarks() {
		return maxmarks;
	}
	public void setMaxmarks(String maxmarks) {
		this.maxmarks = maxmarks;
	}
	public String getMinPassmarks() {
		return minPassmarks;
	}
	public void setMinPassmarks(String minPassmarks) {
		this.minPassmarks = minPassmarks;
	}
	public String getReqmarks() {
		return reqmarks;
	}
	public void setReqmarks(String reqmarks) {
		this.reqmarks = reqmarks;
	}
	public String[] getStudentIdArray() {
		return studentIdArray;
	}
	public void setStudentIdArray(String[] studentIdArray) {
		this.studentIdArray = studentIdArray;
	}
	public String[] getAcuiredmarksArray() {
		return acuiredmarksArray;
	}
	public void setAcuiredmarksArray(String[] acuiredmarksArray) {
		this.acuiredmarksArray = acuiredmarksArray;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
