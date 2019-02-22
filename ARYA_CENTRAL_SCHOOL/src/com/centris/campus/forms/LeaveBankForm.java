package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class LeaveBankForm extends ActionForm {
	
	
	private String sno;
	private String accyear;
	private String totalleaves;
	private String permonth;
	private String  accyearcode;
	private String academicyear;
	//private String leavebankCheckBox;
	
	
	private double sickleave;
	private double paidleave;
	private double casualleave;
	private String createdby;
	
	
	
	
	
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getTotalleaves() {
		return totalleaves;
	}
	public void setTotalleaves(String totalleaves) {
		this.totalleaves = totalleaves;
	}
	public String getPermonth() {
		return permonth;
	}
	public void setPermonth(String permonth) {
		this.permonth = permonth;
	}
	public String getAccyearcode() {
		return accyearcode;
	}
	public void setAccyearcode(String accyearcode) {
		this.accyearcode = accyearcode;
	}
	public String getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	public double getSickleave() {
		return sickleave;
	}
	public void setSickleave(double sickleave) {
		this.sickleave = sickleave;
	}
	public double getPaidleave() {
		return paidleave;
	}
	public void setPaidleave(double paidleave) {
		this.paidleave = paidleave;
	}
	public double getCasualleave() {
		return casualleave;
	}
	public void setCasualleave(double casualleave) {
		this.casualleave = casualleave;
	}
	
	
	
	
	
	

}
