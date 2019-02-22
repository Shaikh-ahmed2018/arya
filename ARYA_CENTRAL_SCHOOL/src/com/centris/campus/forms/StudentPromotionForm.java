package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class StudentPromotionForm extends ActionForm{

	
	
	public StudentPromotionForm() {
		super();
	}
	private static final long serialVersionUID = 1L;
	private String studentid;
	private String admissionNumber;
	private String admissionno;
	private String studentname;
	private String stream;
	private String classname;
	private String year;
	private String section;
	private String performanceGrade;
	private String performanceStatus;
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getAdmissionno() {
		return admissionno;
	}
	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getPerformanceGrade() {
		return performanceGrade;
	}
	public void setPerformanceGrade(String performanceGrade) {
		this.performanceGrade = performanceGrade;
	}
	public String getPerformanceStatus() {
		return performanceStatus;
	}
	public void setPerformanceStatus(String performanceStatus) {
		this.performanceStatus = performanceStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}



}
