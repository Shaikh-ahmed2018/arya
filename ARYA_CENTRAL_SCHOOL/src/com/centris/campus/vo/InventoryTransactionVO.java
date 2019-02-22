package com.centris.campus.vo;

public class InventoryTransactionVO {

	private String transaction_id; 
	private String item_id; 
	private String item_type; 
	private String item_name; 
	private String requested_by; 
	private String issued_by; 
	private String issued_to; 
	private String issued_date; 
	private String returned_date; 
	private String status;
	private String transactionCheckBox;
	private String issued_quantity;
	private String purchase_item_name;
	private String purchase_item_id;
	private String remarks;
	private String returned_by;
	private String delayed_status;
	private int sno;
	private String startdate;
	private String todate;
	private String department;
	private String issuetime;
	private String returntime;
	private String item_type_id;

	
	public int getSno() {
		return sno;
	}
	public String getItem_type_id() {
		return item_type_id;
	}
	public void setItem_type_id(String item_type_id) {
		this.item_type_id = item_type_id;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getIssuetime() {
		return issuetime;
	}
	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDelayed_status() {
		return delayed_status;
	}
	public void setDelayed_status(String delayed_status) {
		this.delayed_status = delayed_status;
	}
	public String getReturned_by() {
		return returned_by;
	}
	public void setReturned_by(String returned_by) {
		this.returned_by = returned_by;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPurchase_item_id() {
		return purchase_item_id;
	}
	public void setPurchase_item_id(String purchase_item_id) {
		this.purchase_item_id = purchase_item_id;
	}
	public String getPurchase_item_name() {
		return purchase_item_name;
	}
	public void setPurchase_item_name(String purchase_item_name) {
		this.purchase_item_name = purchase_item_name;
	}
	public String getIssued_quantity() {
		return issued_quantity;
	}
	public void setIssued_quantity(String issued_quantity) {
		this.issued_quantity = issued_quantity;
	}
	public String getTransactionCheckBox() {
		return transactionCheckBox;
	}
	public void setTransactionCheckBox(String transactionCheckBox) {
		this.transactionCheckBox = transactionCheckBox;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getRequested_by() {
		return requested_by;
	}
	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}
	public String getIssued_by() {
		return issued_by;
	}
	public void setIssued_by(String issued_by) {
		this.issued_by = issued_by;
	}
	public String getIssued_to() {
		return issued_to;
	}
	public void setIssued_to(String issued_to) {
		this.issued_to = issued_to;
	}
	public String getIssued_date() {
		return issued_date;
	}
	public void setIssued_date(String issued_date) {
		this.issued_date = issued_date;
	}
	public String getReturned_date() {
		return returned_date;
	}
	public void setReturned_date(String returned_date) {
		this.returned_date = returned_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
