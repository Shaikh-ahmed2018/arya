package com.centris.campus.vo;

public class ClassPromotionList {
private int sno;
private String className;
private String classId;
private String sectionName;
private String sectionId;
private int studentStrength;
private int promotedStudent;
private int demotedStudent;
private String status;
private String specializationName;
private String specializationId;


public String getSpecializationName() {
	return specializationName;
}
public void setSpecializationName(String specializationName) {
	this.specializationName = specializationName;
}
public String getSpecializationId() {
	return specializationId;
}
public void setSpecializationId(String specializationId) {
	this.specializationId = specializationId;
}
public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public String getClassId() {
	return classId;
}
public void setClassId(String classId) {
	this.classId = classId;
}
public String getSectionName() {
	return sectionName;
}
public void setSectionName(String sectionName) {
	this.sectionName = sectionName;
}
public String getSectionId() {
	return sectionId;
}
public void setSectionId(String sectionId) {
	this.sectionId = sectionId;
}
public int getStudentStrength() {
	return studentStrength;
}
public void setStudentStrength(int studentStrength) {
	this.studentStrength = studentStrength;
}
public int getPromotedStudent() {
	return promotedStudent;
}
public void setPromotedStudent(int promotedStudent) {
	this.promotedStudent = promotedStudent;
}
public int getDemotedStudent() {
	return demotedStudent;
}
public void setDemotedStudent(int demotedStudent) {
	this.demotedStudent = demotedStudent;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
