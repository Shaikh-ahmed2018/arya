/**
 * 
 */
package com.centris.campus.vo;

/**
 * @author chiranjeevi
 *
 */
public class TeacherManualAttendanceVO {
	
	private String sno;
	private String teacherid;
	private String teachername;
	private String status;
	private String date;
	private String updatedby;
	private int presentcount;
	private int absentcount;

	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
	public int getPresentcount() {
		return presentcount;
	}
	public void setPresentcount(int presentcount) {
		this.presentcount = presentcount;
	}
	public int getAbsentcount() {
		return absentcount;
	}
	public void setAbsentcount(int absentcount) {
		this.absentcount = absentcount;
	}
	
	
}
