package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class InventoryForm extends ActionForm {
	
	private String inventory_type_name;
	private String description;
	private String current_user;
	private String currentdate;
	private String inventory_id;
	private String message;
	
	
	private String department ;
	private String item_name ;
	private String total_quantity ;
	private String manufacturer ;
	private String address ;
	private String item_type;
	private String item_id ;
	private String purchased_date ;
	private String purchased_by ;
	private String contact_number;
	private String purchase_id;
	public String getInventory_type_name() {
		return inventory_type_name;
	}
	public void setInventory_type_name(String inventory_type_name) {
		this.inventory_type_name = inventory_type_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrent_user() {
		return current_user;
	}
	public void setCurrent_user(String current_user) {
		this.current_user = current_user;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(String inventory_id) {
		this.inventory_id = inventory_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getTotal_quantity() {
		return total_quantity;
	}
	public void setTotal_quantity(String total_quantity) {
		this.total_quantity = total_quantity;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getPurchased_date() {
		return purchased_date;
	}
	public void setPurchased_date(String purchased_date) {
		this.purchased_date = purchased_date;
	}
	public String getPurchased_by() {
		return purchased_by;
	}
	public void setPurchased_by(String purchased_by) {
		this.purchased_by = purchased_by;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}
	
	
	
	

}
