package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class TransportCategoryForm extends ActionForm 
{
	public String vehicleType;
	public String description;
	public String vehicle_id;
	private String status;
	private String updateid;
	
	public String getUpdateid() {
		return updateid;
	}
	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
