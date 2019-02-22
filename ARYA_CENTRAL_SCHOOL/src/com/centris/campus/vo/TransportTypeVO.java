package com.centris.campus.vo;

import java.io.Serializable;

public class TransportTypeVO implements Serializable{
	
	
	private String transptyId;
	private String transptyname;
	private String transptydes;
	private String type;
	private int sno;
	
	
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransptyId() {
		return transptyId;
	}
	public void setTransptyId(String transptyId) {
		this.transptyId = transptyId;
	}
	public String getTransptyname() {
		return transptyname;
	}
	public void setTransptyname(String transptyname) {
		this.transptyname = transptyname;
	}
	public String getTransptydes() {
		return transptydes;
	}
	public void setTransptydes(String transptydes) {
		this.transptydes = transptydes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
	
	
	
	
	
	
	
	
	
}
