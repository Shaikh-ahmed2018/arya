package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class LeaveRequestForm extends ActionForm{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String leavecode;
	private String requestto;
	private String fromdate;
	private String todate;
	private String starttime;
	private String endtime;
	private String leavetype;
	private String totalleave;
	private String reason;
	private FormFile fileupload;
	private String userid;
	private String fileupload1;
	private String studentRegno;
	private String studentFnameVar;
	private String studentid;
	private String srudentname;
	private String createuser;
	private int sno;
	
	private String studentFname;
	private String createuserval;
	private String payrollid;
	
	//private int sno1;
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPayrollid() {
		return payrollid;
	}
	
	public void setPayrollid(String payrollid) {
		this.payrollid = payrollid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreateuserval() {
		return createuserval;
	}
	public void setCreateuserval(String createuserval) {
		this.createuserval = createuserval;
	}
	public String getLeavecode() {
		return leavecode;
	}
	public void setLeavecode(String leavecode) {
		this.leavecode = leavecode;
	}
	public String getRequestto() {
		return requestto;
	}
	public void setRequestto(String requestto) {
		this.requestto = requestto;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getTotalleave() {
		return totalleave;
	}
	public void setTotalleave(String totalleave) {
		this.totalleave = totalleave;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public FormFile getFileupload() {
		return fileupload;
	}
	public void setFileupload(FormFile fileupload) {
		this.fileupload = fileupload;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getFileupload1() {
		return fileupload1;
	}
	public void setFileupload1(String fileupload1) {
		this.fileupload1 = fileupload1;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSrudentname() {
		return srudentname;
	}
	public void setSrudentname(String srudentname) {
		this.srudentname = srudentname;
	}
	public String getStudentRegno() {
		return studentRegno;
	}
	public void setStudentRegno(String studentRegno) {
		this.studentRegno = studentRegno;
	}
	public String getStudentFnameVar() {
		return studentFnameVar;
	}
	public void setStudentFnameVar(String studentFnameVar) {
		this.studentFnameVar = studentFnameVar;
	}
	public String getStudentFname() {
		return studentFname;
	}
	public void setStudentFname(String studentFname) {
		this.studentFname = studentFname;
	}
	
	
	
	

}
