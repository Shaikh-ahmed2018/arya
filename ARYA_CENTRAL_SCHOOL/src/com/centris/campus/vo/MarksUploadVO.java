package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.List;

public class MarksUploadVO {
	
	private String stuId;
	private String admissionno;
	private String studentname;
	
	private String categoryid;
	private String categoryname;
	
	private String classid;
	private String classname;
	
	private String sectionid;
	private String sectionname;
	
	private String subjectId;
	private String subjectname;
	
	private String examid;
	private String examname;
	
	private String examdate;
	private String examstarttime;
	private String examendtime;
	
	private String scoredmarks;
	private String maxmarks;
	private String reqmarks ;
	private String studSubId;
	private String examination;
	private double markspercent;
	
	private int sno;
	
	
	public double getMarkspercent() {
		return markspercent;
	}
	public void setMarkspercent(double markspercent) {
		this.markspercent = markspercent;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	private List<String> studentIdArray;
	private List<String> scoredmarksArray;
	
	
	
	
	public List<String> getStudentIdArray() {
		return studentIdArray;
	}
	public void setStudentIdArray(List<String> studentIdArray) {
		this.studentIdArray = studentIdArray;
	}
	public List<String> getScoredmarksArray() {
		return scoredmarksArray;
	}
	public void setScoredmarksArray(List<String> scoredmarksArray) {
		this.scoredmarksArray = scoredmarksArray;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	public String getExamstarttime() {
		return examstarttime;
	}
	public void setExamstarttime(String examstarttime) {
		this.examstarttime = examstarttime;
	}
	public String getExamendtime() {
		return examendtime;
	}
	public void setExamendtime(String examendtime) {
		this.examendtime = examendtime;
	}
	public String getStudSubId() {
		return studSubId;
	}
	public void setStudSubId(String studSubId) {
		this.studSubId = studSubId;
	}
	public String getExamination() {
		return examination;
	}
	public void setExamination(String examination) {
		this.examination = examination;
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
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getScoredmarks() {
		return scoredmarks;
	}
	public void setScoredmarks(String scoredmarks) {
		this.scoredmarks = scoredmarks;
	}
	public String getMaxmarks() {
		return maxmarks;
	}
	public void setMaxmarks(String maxmarks) {
		this.maxmarks = maxmarks;
	}
	public String getReqmarks() {
		return reqmarks;
	}
	public void setReqmarks(String reqmarks) {
		this.reqmarks = reqmarks;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
}