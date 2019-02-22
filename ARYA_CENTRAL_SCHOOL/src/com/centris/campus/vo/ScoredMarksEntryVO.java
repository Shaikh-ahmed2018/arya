package com.centris.campus.vo;

import java.util.ArrayList;

public class ScoredMarksEntryVO {
	
	private String studentAdmissionNo;
	 private String studentFirstName;
	 private String serialNumber;
	 private String enterMarks;
	 private String studentId;
	 private String maxMarks;
	 private String minMarks;
	 
	 private ArrayList<StudentMarksDetailsVo> studentmarkslist;

	 
	 
	 
	 
	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEnterMarks() {
		return enterMarks;
	}

	public void setEnterMarks(String enterMarks) {
		this.enterMarks = enterMarks;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getMinMarks() {
		return minMarks;
	}

	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}

	public ArrayList<StudentMarksDetailsVo> getStudentmarkslist() {
		return studentmarkslist;
	}

	public void setStudentmarkslist(
			ArrayList<StudentMarksDetailsVo> studentmarkslist) {
		this.studentmarkslist = studentmarkslist;
	}
	 
	 
	
}
