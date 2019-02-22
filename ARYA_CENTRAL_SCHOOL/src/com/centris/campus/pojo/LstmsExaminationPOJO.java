package com.centris.campus.pojo;

import java.sql.Date;

public class LstmsExaminationPOJO {

	String examId;  
    String examName; 
    String examdate;   
    String description;
    String accadamicyear;
    String createuser;
    String enddate;
    
    
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccadamicyear() {
		return accadamicyear;
	}
	public void setAccadamicyear(String accadamicyear) {
		this.accadamicyear = accadamicyear;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
    
    
    
	
}
