package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class FeeCollectionSummaryReportForm extends ActionForm {
	
	private String accyear;
	private String classname;
	private String section;
	private String term;
	
	private String haccyear;
	private String hclass;
	private String hsection;
	private String hterm;
	
	
	public String getHaccyear() {
		return haccyear;
	}
	public void setHaccyear(String haccyear) {
		this.haccyear = haccyear;
	}
	public String getHclass() {
		return hclass;
	}
	public void setHclass(String hclass) {
		this.hclass = hclass;
	}
	public String getHsection() {
		return hsection;
	}
	public void setHsection(String hsection) {
		this.hsection = hsection;
	}
	public String getHterm() {
		return hterm;
	}
	public void setHterm(String hterm) {
		this.hterm = hterm;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	

}
