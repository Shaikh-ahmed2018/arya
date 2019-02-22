package com.centris.campus.pojo;

import java.sql.Timestamp;

public class QuotaMasterPojo {

	String quotaid;
	String quotaName;
	String quotaDescription;
	Timestamp create_date;
	String created_by;
	Timestamp updated_date;
	String updated_by;

	public Timestamp getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = updated_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getQuotaid() {
		return quotaid;
	}

	public void setQuotaid(String quotaid) {
		this.quotaid = quotaid;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getQuotaDescription() {
		return quotaDescription;
	}

	public void setQuotaDescription(String quotaDescription) {
		this.quotaDescription = quotaDescription;
	}

}
