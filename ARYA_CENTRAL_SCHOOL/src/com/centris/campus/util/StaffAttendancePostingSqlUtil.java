package com.centris.campus.util;

public class StaffAttendancePostingSqlUtil {
	public static final String CHECK_TEACHER_ATTENDANCE = "select count(*) from campus_teacher_attendence where AttendenceDate=? and TeacherID=?";
	public static final String UPDATE_TEACHER_ATTENDANCE = "update campus_teacher_attendence set AttendenceStatus=?,UpdateDate=?,UpdatedBy=?,isApproved='Y' where AttendenceDate=? and TeacherID=?";
	public static final String INSERT_TEACHER_ATTENDANCE = "insert into campus_teacher_attendence (TeacherID,AttendenceDate,AttendenceStatus,CreateDate,CreatedBy,isApproved) values (?,?,?,?,?,?)";
	public static final String CHECK_TEACHER_ATTENDANCEDATE = "select count(*) from campus_teacher_attendence where AttendenceDate=?";
	public static final String GET_TEACHER_ATTENDANCE_DETAILS = "select distinct cta.TeacherID,ct.FirstName,ct.LastName,cta.AttendenceStatus from campus_teacher_attendence cta,campus_teachers ct,campus_classdetail ccd,campus_classstream ccs,campus_teachersettings cts where cta.AttendenceDate=? and  ct.TeacherID=cta.TeacherID and ct.isActive ='Y' order by length(cta.TeacherID),cta.TeacherID";
	public static final String GET_ATTENDANCE_DATES = "select AttendenceDate from campus_teacher_attendence";
	public static final String GET_CREATEDBY = "select UserName from campus_teachers where TeacherID=?";
	public static final String POST_ATTENDANCE = "insert into campus_teacher_attendence(TeacherID,AttendenceDate,AttendenceStatus,isApproved,CreateDate,UpdateDate,CreatedBy,UpdatedBy) values(?,?,?,?,?,?,?,?)";
	public static final String TEACHER_ATTENDANCE_STATUS = "select AttendenceDate,sum(case when AttendenceStatus='Present' then 1 else 0 end)totalpresent, sum(case when AttendenceStatus='Holiday' then 1 else 0 end)totalHoliday,sum(case when AttendenceStatus='Absent' then 1 else 0 end)totalAbsent,sum(case when AttendenceStatus='Leave' then 1 else 0 end)totalleave from campus_teacher_attendence where AttendenceDate between (select subdate(now(), Interval 30 day)) and now()";
	public static final String GET_TEACHER_ATTENDANCE_BY_DATE = "select ta.AttendenceDate,ta.AttendenceStatus,ta.TeacherID,td.FirstName from  campus_teachers td,campus_teacher_attendence ta where td.TeacherID=ta.TeacherID and ta.isApproved='N' and ta.AttendenceDate=?";
	public static final String TEACHER_ATTENDANCE_APPROVED_BY_PRINCIPAL = "update campus_teacher_attendence set AttendenceDate=?,AttendenceStatus=?,isApproved=? where TeacherID=? and AttendenceDate=?";
	public static final String GET_TEACHERS_NAMES_WITH_STATUS = "select att.TeacherID,case when LastName is null then tea.FirstName else concat(tea.FirstName,' ',tea.LastName) end TeacherName,att.AttendenceStatus from campus_teacher_attendence att, campus_teachers tea where att.TeacherID = tea.TeacherID and AttendenceDate=? order by TeacherName";
	public static final String GET_TEACHERS_NAMES = "select TeacherID, case when LastName is null then FirstName else concat(FirstName,' ',LastName) end TeacherName from campus_teachers order by TeacherName";
	public static final String GET_TEACHERS_BASED_ON_DATE = "select TeacherID from campus_teacher_attendence where AttendenceDate=?";
	public static final String UPDATE_TEACHER_ATTENDANCE_DETAILS = "update campus_teacher_attendence set AttendenceStatus = ?, UpdateDate=?, UpdatedBy=? where TeacherID=? and AttendenceDate=?";
	public static final String INSERT_TEACHER_ATTENDANCE_DETAILS = "insert into campus_teacher_attendence(TeacherID,AttendenceDate,AttendenceStatus,isApproved,CreateDate,CreatedBy)values(?,?,?,'N',?,?);";
	public static final String GET_ALL_TEACHER_ATTENDANCE_DETAILS = "select distinct ct.TeacherID,ct.FirstName,ct.LastName from campus_teachers ct where ct.isActive ='Y' order by length(ct.TeacherID),ct.TeacherID";
}
