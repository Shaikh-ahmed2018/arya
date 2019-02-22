package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class AddStageForm extends ActionForm {
	
	String stageid;
	String stage_name;
	String stage_description;
	String createdby;
	String createddate;
	String amount;
	String accyear;
	
	
	
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStageid() {
		return stageid;
	}
	public void setStageid(String stageid) {
		this.stageid = stageid;
	}
	public String getStage_name() {
		return stage_name;
	}
	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}
	public String getStage_description() {
		return stage_description;
	}
	public void setStage_description(String stage_description) {
		this.stage_description = stage_description;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	
	
	

}
