package com.centris.campus.vo;

public class InventoryVO {
	
	private String item_type_id;
	private String item_type_name;
	private String item_type_description;
	private String inventoryCheckBox;
	private String department;
	
	public String[] getGetDataArray() {
		return GetDataArray;
	}
	public void setGetDataArray(String[] getDataArray) {
		GetDataArray = getDataArray;
	}
	private String GetDataArray[];
	
		

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getInventoryCheckBox() {
		return inventoryCheckBox;
	}
	public void setInventoryCheckBox(String inventoryCheckBox) {
		this.inventoryCheckBox = inventoryCheckBox;
	}
	public String getItem_type_id() {
		return item_type_id;
	}
	public void setItem_type_id(String item_type_id) {
		this.item_type_id = item_type_id;
	}
	public String getItem_type_name() {
		return item_type_name;
	}
	public void setItem_type_name(String item_type_name) {
		this.item_type_name = item_type_name;
	}
	public String getItem_type_description() {
		return item_type_description;
	}
	public void setItem_type_description(String item_type_description) {
		this.item_type_description = item_type_description;
	}
	
	
	
	
	
}
