package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class TransportFeeReceiptForm extends ActionForm 
{
	public String location;
	public String accademic_year;
	public String stu_class;
	public String section;
	public String start_date;
	public String end_date;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAccademic_year() {
		return accademic_year;
	}
	public void setAccademic_year(String accademic_year) {
		this.accademic_year = accademic_year;
	}
	public String getStu_class() {
		return stu_class;
	}
	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
}
