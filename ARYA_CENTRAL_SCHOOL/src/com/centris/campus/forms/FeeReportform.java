package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class FeeReportform extends ActionForm{
	

	private String location;
	private String classname;
	private String examdate;
	private String accyear;
	private String section;
	private String enddate;
	private String stream;
	
	private String locationid;
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	
	
	

}
