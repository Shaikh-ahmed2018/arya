package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;

public interface TeacherAttendanceDao {

	public List<TeacherAttendanceVo> getTeachersAttendanceDetailsDao(
			TeacherAttendanceForm attendanceForm);

	public boolean insertTeacherAttendanceDao(
			TeacherAttendanceForm attendanceForm);

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceStatus();

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate(
			String attendanceDate);

	public int approvedByPrincipal(
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVos);

}
