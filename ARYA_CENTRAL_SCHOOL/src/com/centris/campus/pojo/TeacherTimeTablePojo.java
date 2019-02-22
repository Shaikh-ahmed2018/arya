package com.centris.campus.pojo;

public class TeacherTimeTablePojo {
	String subjectID;
	String classid;
	String className;
	String subjectName;
	String teacher;
	String section;

	private String sessionCode;

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	private String[] dayid;
	private String[] period1;
	private String[] period2;

	private String[] period3;
	private String[] period4;
	private String[] period5;

	private String[] period6;
	private String[] period7;
	private String[] period8;
	private String[] period9;

	private String teacherid;
	private String accyearid;
	private String userid;

	public String[] getPeriod9() {
		return period9;
	}

	public void setPeriod9(String[] period9) {
		this.period9 = period9;
	}

	public String[] getDayid() {
		return dayid;
	}

	public void setDayid(String[] dayid) {
		this.dayid = dayid;
	}

	public String[] getPeriod1() {
		return period1;
	}

	public void setPeriod1(String[] period1) {
		this.period1 = period1;
	}

	public String[] getPeriod2() {
		return period2;
	}

	public void setPeriod2(String[] period2) {
		this.period2 = period2;
	}

	public String[] getPeriod3() {
		return period3;
	}

	public void setPeriod3(String[] period3) {
		this.period3 = period3;
	}

	public String[] getPeriod4() {
		return period4;
	}

	public void setPeriod4(String[] period4) {
		this.period4 = period4;
	}

	public String[] getPeriod5() {
		return period5;
	}

	public void setPeriod5(String[] period5) {
		this.period5 = period5;
	}

	public String[] getPeriod6() {
		return period6;
	}

	public void setPeriod6(String[] period6) {
		this.period6 = period6;
	}

	public String[] getPeriod7() {
		return period7;
	}

	public void setPeriod7(String[] period7) {
		this.period7 = period7;
	}

	public String[] getPeriod8() {
		return period8;
	}

	public void setPeriod8(String[] period8) {
		this.period8 = period8;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getAccyearid() {
		return accyearid;
	}

	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}