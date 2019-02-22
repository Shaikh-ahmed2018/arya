package com.centris.campus.vo;

public class TeacherTimeTableVo {

	private String subjectName;
	private String teacher;
	private String className;
	private String section;

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public static void setSubjectID(String string) {
		// TODO Auto-generated method stub

	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private String dayname;
	private String dayid;
	private String period1;
	private String period2;

	private String period3;
	private String period4;
	private String period5;
	private String period6;

	private String period7;
	private String period8;
	
	private String teacherName;
	private String teacherId;

	public String getDayname() {
		return dayname;
	}

	public void setDayname(String dayname) {
		this.dayname = dayname;
	}

	public String getDayid() {
		return dayid;
	}

	public void setDayid(String dayid) {
		this.dayid = dayid;
	}

	public String getPeriod1() {
		return period1;
	}

	public void setPeriod1(String period1) {
		this.period1 = period1;
	}

	public String getPeriod2() {
		return period2;
	}

	public void setPeriod2(String period2) {
		this.period2 = period2;
	}

	public String getPeriod3() {
		return period3;
	}

	public void setPeriod3(String period3) {
		this.period3 = period3;
	}

	public String getPeriod4() {
		return period4;
	}

	public void setPeriod4(String period4) {
		this.period4 = period4;
	}

	public String getPeriod5() {
		return period5;
	}

	public void setPeriod5(String period5) {
		this.period5 = period5;
	}

	public String getPeriod6() {
		return period6;
	}

	public void setPeriod6(String period6) {
		this.period6 = period6;
	}

	public String getPeriod7() {
		return period7;
	}

	public void setPeriod7(String period7) {
		this.period7 = period7;
	}

	public String getPeriod8() {
		return period8;
	}

	public void setPeriod8(String period8) {
		this.period8 = period8;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
}