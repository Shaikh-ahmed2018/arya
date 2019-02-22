package com.centris.campus.util;

public class StaffAttendanceSqlUtil {

	public static final String GET_STAFF_ATTENDANCE = "select t.TeacherID,t.registerId,concat(t.FirstName,' ',t.LastName) teacherName,des.designationName,dept.DEPT_NAME,case when a.AttendenceStatus is null then 'Present' else a.AttendenceStatus end status from  campus_teachers t left outer join campus_teacher_attendence a on a.TeacherID=t.TeacherID and a.AttendenceDate=?,campus_designation des,campus_department dept where t.designation=des.DesignationCode and t.department=dept.DEPT_ID and t.department like ? and t.isActive='Y' order by length(t.TeacherID),t.TeacherID"; 
	public static final String GET_ATTENDANCE_COUNT = "select count(*) from campus_teacher_attendence where TeacherID=? and AttendenceDate=?";
	public static final String UPDATE_ATTENDANCE = "update campus_teacher_attendence set AttendenceStatus=?,modifyuser=?,modifydate=? where  TeacherID=? and AttendenceDate=?";
	public static final String INSERT_ATTENDANCE = "insert into campus_teacher_attendence(TeacherID,AttendenceDate,AttendenceStatus,createuser,createdate) values(?,?,?,?,?)";
	
	public static final String GET_ATTENDANCE_LIST = "select ta.AttendenceDate,count(t.TeacherID) as total_streangth,sum(case when ta.AttendenceStatus='Present' then 1 else 0 end) TotalPresent,sum(case when ta.AttendenceStatus='Absent' then 1 else 0 end) TotalAbsent,sum(case when ta.AttendenceStatus='Holiday' then 1 else 0 end) TotalHoliday,sum(case when ta.AttendenceStatus='CL' then 1 else 0 end) Totalleave from campus_teacher_attendence ta,campus_teachers t where ta.AttendenceDate between ?  and ? and t.TeacherID=ta.TeacherID group by ta.AttendenceDate";
	
}
