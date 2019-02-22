package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class StdentAttendanceReportForm extends ActionForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accyearId;
	private String accyrid;
	private String accyear;
	private String studentid;
	private String studentname;
	private String studentRegNum;
	private String streamid;
	private String streamname;
	private String classid;
	private String classname;
	private String sectionid;
	private String sectionname;
	private String fromdate;
	private String todate;
	
	private String attdancedate;
	private String attendancestatus;
	public String getAccyrid() {
		return accyrid;
	}
	public void setAccyrid(String accyrid) {
		this.accyrid = accyrid;
	}
	
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentRegNum() {
		return studentRegNum;
	}
	public void setStudentRegNum(String studentRegNum) {
		this.studentRegNum = studentRegNum;
	}
	public String getStreamid() {
		return streamid;
	}
	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}
	public String getStreamname() {
		return streamname;
	}
	public void setStreamname(String streamname) {
		this.streamname = streamname;
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
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getAttdancedate() {
		return attdancedate;
	}
	public void setAttdancedate(String attdancedate) {
		this.attdancedate = attdancedate;
	}
	public String getAttendancestatus() {
		return attendancestatus;
	}
	public void setAttendancestatus(String attendancestatus) {
		this.attendancestatus = attendancestatus;
	}
	
	
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	
	
	
	
	

}
