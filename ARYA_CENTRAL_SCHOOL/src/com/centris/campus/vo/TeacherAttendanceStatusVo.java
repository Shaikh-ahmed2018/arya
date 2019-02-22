/**
 * 
 */
package com.centris.campus.vo;

/**
 * @author satish
 *
 */
public class TeacherAttendanceStatusVo {
private int sno;
private String attendanceDate ;
private String totalPresent ;
private String totalAbsent ;
private String toatlLeave;
private String attendanceStatus;
private String teacherName;
private String teacherId;
private String totalHolidays;


public String getTotalHolidays() {
	return totalHolidays;
}
public void setTotalHolidays(String totalHolidays) {
	this.totalHolidays = totalHolidays;
}
public String getAttendanceStatus() {
	return attendanceStatus;
}
public void setAttendanceStatus(String attendanceStatus) {
	this.attendanceStatus = attendanceStatus;
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
public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getAttendanceDate() {
	return attendanceDate;
}
public void setAttendanceDate(String attendanceDate) {
	this.attendanceDate = attendanceDate;
}
public String getTotalPresent() {
	return totalPresent;
}
public void setTotalPresent(String totalPresent) {
	this.totalPresent = totalPresent;
}
public String getTotalAbsent() {
	return totalAbsent;
}
public void setTotalAbsent(String totalAbsent) {
	this.totalAbsent = totalAbsent;
}
public String getToatlLeave() {
	return toatlLeave;
}
public void setToatlLeave(String toatlLeave) {
	this.toatlLeave = toatlLeave;
}

}
