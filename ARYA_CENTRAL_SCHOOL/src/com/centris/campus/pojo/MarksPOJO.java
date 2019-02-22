package com.centris.campus.pojo;

public class MarksPOJO {
	
	private String accyear;
	private String classid;
	private String section;
	private String studentid;
	private String examid;
	
	
	public String getAccyear() {
		return accyear;
	}

	public void setAccyear(String accyear) {
		this.accyear = accyear;
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

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public MarksPOJO() {
	}

	public String getStudClassId() {
		return studClassId;
	}

	public void setStudClassId(String studClassId) {
		this.studClassId = studClassId;
	}

	public String getStudSubId() {
		return studSubId;
	}

	public void setStudSubId(String studSubId) {
		this.studSubId = studSubId;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getScoredMarks() {
		return ScoredMarks;
	}

	public void setScoredMarks(String scoredMarks) {
		ScoredMarks = scoredMarks;
	}

	String studClassId;
	String studSectionId;

	public String getStudSectionId() {
		return studSectionId;
	}

	public void setStudSectionId(String studSectionId) {
		this.studSectionId = studSectionId;
	}

	String studSubId;
	String admissionNo;
	String totalMarks;
	String ScoredMarks;
	String minMarks;

	public String getMinMarks() {
		return minMarks;
	}

	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}

}
