package com.centris.campus.vo;

import java.util.ArrayList;



public class LeaveDetailsReportVo {
	
	private int sno;
	private String teacherId;
	private String status;
	private String teachername;
	private ArrayList<LeaveStatusListVO> jan;
	private ArrayList<LeaveStatusListVO> feb;
	private ArrayList<LeaveStatusListVO> mar;
	private ArrayList<LeaveStatusListVO> aprl;
	private ArrayList<LeaveStatusListVO> may;
	private ArrayList<LeaveStatusListVO> june;
	private ArrayList<LeaveStatusListVO> july;
	private ArrayList<LeaveStatusListVO> aug;
	private ArrayList<LeaveStatusListVO> sep;
	private ArrayList<LeaveStatusListVO> oct;
	private ArrayList<LeaveStatusListVO> nov;
	private ArrayList<LeaveStatusListVO> dec;
	private String doj;
	private double balance;
	private String teachertype;
	private String departmentid;
	private String departmentname;
	private double total;
	private String startDate;
	private String endDate;
	private String approvedDate;
	private String approvedBy;
	private String designationId;
	private String designationName;
	private String noOfleave;
	private String accyearId;
	private String accyearName;
	private ArrayList<LeaveDetailsReportVo> leaveArrayList;
	private String leaveName;
	private String leaveValue;
	private String leaveConsumed;
	private String leaveAvailable;
	private ArrayList<LeaveDetailsReportVo> leaveList;
	private double lwa;
	private double haflDay;
	
	
	public double getLwa() {
		return lwa;
	}
	public void setLwa(double lwa) {
		this.lwa = lwa;
	}
	public double getHaflDay() {
		return haflDay;
	}
	public void setHaflDay(double haflDay) {
		this.haflDay = haflDay;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getNoOfleave() {
		return noOfleave;
	}
	public void setNoOfleave(String noOfleave) {
		this.noOfleave = noOfleave;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public ArrayList<LeaveDetailsReportVo> getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(ArrayList<LeaveDetailsReportVo> leaveList) {
		this.leaveList = leaveList;
	}
	public ArrayList<LeaveDetailsReportVo> getLeaveArrayList() {
		return leaveArrayList;
	}
	public void setLeaveArrayList(ArrayList<LeaveDetailsReportVo> leaveArrayList) {
		this.leaveArrayList = leaveArrayList;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeaveValue() {
		return leaveValue;
	}
	public void setLeaveValue(String leaveValue) {
		this.leaveValue = leaveValue;
	}
	public String getLeaveConsumed() {
		return leaveConsumed;
	}
	public void setLeaveConsumed(String leaveConsumed) {
		this.leaveConsumed = leaveConsumed;
	}
	public String getLeaveAvailable() {
		return leaveAvailable;
	}
	public void setLeaveAvailable(String leaveAvailable) {
		this.leaveAvailable = leaveAvailable;
	}
	public String getAccyearId() {
		return accyearId;
	}
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String getAccyearName() {
		return accyearName;
	}
	public void setAccyearName(String accyearName) {
		this.accyearName = accyearName;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public ArrayList<LeaveStatusListVO> getJan() {
		return jan;
	}
	public void setJan(ArrayList<LeaveStatusListVO> jan) {
		this.jan = jan;
	}
	public ArrayList<LeaveStatusListVO> getFeb() {
		return feb;
	}
	public void setFeb(ArrayList<LeaveStatusListVO> feb) {
		this.feb = feb;
	}
	public ArrayList<LeaveStatusListVO> getMar() {
		return mar;
	}
	public void setMar(ArrayList<LeaveStatusListVO> mar) {
		this.mar = mar;
	}
	public ArrayList<LeaveStatusListVO> getAprl() {
		return aprl;
	}
	public void setAprl(ArrayList<LeaveStatusListVO> aprl) {
		this.aprl = aprl;
	}
	public ArrayList<LeaveStatusListVO> getMay() {
		return may;
	}
	public void setMay(ArrayList<LeaveStatusListVO> may) {
		this.may = may;
	}
	public ArrayList<LeaveStatusListVO> getJune() {
		return june;
	}
	public void setJune(ArrayList<LeaveStatusListVO> june) {
		this.june = june;
	}
	public ArrayList<LeaveStatusListVO> getJuly() {
		return july;
	}
	public void setJuly(ArrayList<LeaveStatusListVO> july) {
		this.july = july;
	}
	public ArrayList<LeaveStatusListVO> getAug() {
		return aug;
	}
	public void setAug(ArrayList<LeaveStatusListVO> aug) {
		this.aug = aug;
	}
	public ArrayList<LeaveStatusListVO> getSep() {
		return sep;
	}
	public void setSep(ArrayList<LeaveStatusListVO> sep) {
		this.sep = sep;
	}
	public ArrayList<LeaveStatusListVO> getOct() {
		return oct;
	}
	public void setOct(ArrayList<LeaveStatusListVO> oct) {
		this.oct = oct;
	}
	public ArrayList<LeaveStatusListVO> getNov() {
		return nov;
	}
	public void setNov(ArrayList<LeaveStatusListVO> nov) {
		this.nov = nov;
	}
	public ArrayList<LeaveStatusListVO> getDec() {
		return dec;
	}
	public void setDec(ArrayList<LeaveStatusListVO> dec) {
		this.dec = dec;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getTeachertype() {
		return teachertype;
	}
	public void setTeachertype(String teachertype) {
		this.teachertype = teachertype;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	

}
