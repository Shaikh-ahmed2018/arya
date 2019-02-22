package com.centris.campus.forms;



import org.apache.struts.action.ActionForm;

/**
 * @author seshuma
 *
 */
public class AddDesignation extends ActionForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String designationid;
	String designation_name;
	String designation_description;
	String createdby;
	String createddate;
	
	
	
	
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getDesignationid() {
		return designationid;
	}
	public void setDesignationid(String designationid) {
		
		this.designationid = designationid;
	}
	public String getDesignation_name() {
		
		return designation_name;
	}
	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}
	public String getDesignation_description() {
		return designation_description;
	}
	public void setDesignation_description(String designation_description) {
		
		this.designation_description = designation_description;
	}
	
	
}
