package com.centris.campus.vo;

public class StageFeeSetupVo {

	private String classid;
	private String classname;
	private String termid;
	private String termname;
	private String accyear;
	private String accyearid;
	private int sno;
	private int stagecount;
	
	private String feecode;
	private String feename;
	private String feesettingcode;
	private String feeamount;
	private String AllfeescheckBox;
	
	
	
	
	public String getAllfeescheckBox() {
		return AllfeescheckBox;
	}
	public void setAllfeescheckBox(String allfeescheckBox) {
		AllfeescheckBox = allfeescheckBox;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAccyearid() {
		return accyearid;
	}
	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getStagecount() {
		return stagecount;
	}
	public void setStagecount(int stagecount) {
		this.stagecount = stagecount;
	}
	public String getFeecode() {
		return feecode;
	}
	public void setFeecode(String feecode) {
		this.feecode = feecode;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public String getFeesettingcode() {
		return feesettingcode;
	}
	public void setFeesettingcode(String feesettingcode) {
		this.feesettingcode = feesettingcode;
	}
	public String getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(String feeamount) {
		this.feeamount = feeamount;
	}
	
	
}
