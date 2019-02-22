package com.centris.campus.forms;

import org.apache.struts.upload.FormFile;

/**
 * 
 * @author mahesh
 */
public class AddSubjectForm extends org.apache.struts.action.ActionForm {

	private FormFile file;

	private String category;
	private String classname;
	private String subjtname;
	private String description;
	private String createdUserId;
	private String filename;
	private String hiddensubjectId;
	private String hiddensubject;
	private String hiddenfile;
	private int totalMarks;
	private int passMarks;
	private String locationId;
	private String locationName;
	private String specialization;
	private String subjectCode;
	private String isLanguage;
	private String isLang;
	private String syllabus;
	private String lab_id;
	private String hiddenlab_id;
	private String lab_name;
	private String specId;
	private String isExam;
	
	
	public String getIsExam() {
		return isExam;
	}

	public void setIsExam(String isExam) {
		this.isExam = isExam;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getLab_name() {
		return lab_name;
	}

	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}

	public String getHiddenlab_id() {
		return hiddenlab_id;
	}

	public void setHiddenlab_id(String hiddenlab_id) {
		this.hiddenlab_id = hiddenlab_id;
	}

	public String getLab_id() {
		return lab_id;
	}

	public void setLab_id(String lab_id) {
		this.lab_id = lab_id;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public String getIsLang() {
		return isLang;
	}

	public void setIsLang(String isLang) {
		this.isLang = isLang;
	}

	public String getIsLanguage() {
		return isLanguage;
	}

	public void setIsLanguage(String isLanguage) {
		this.isLanguage = isLanguage;
	}



	

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSubjtname() {
		return subjtname;
	}

	public void setSubjtname(String subjtname) {
		this.subjtname = subjtname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getHiddensubjectId() {
		return hiddensubjectId;
	}

	public void setHiddensubjectId(String hiddensubjectId) {
		this.hiddensubjectId = hiddensubjectId;
	}

	public String getHiddensubject() {
		return hiddensubject;
	}

	public void setHiddensubject(String hiddensubject) {
		this.hiddensubject = hiddensubject;
	}

	public String getHiddenfile() {
		return hiddenfile;
	}

	public void setHiddenfile(String hiddenfile) {
		this.hiddenfile = hiddenfile;
	}


}