package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;

public interface TeacherAttendanceService {

	public List<TeacherAttendanceVo> getTeacherAttendanceService(
			TeacherAttendanceForm attendanceForm);

	public boolean insertTeacherAttendanceService(
			TeacherAttendanceForm attendanceForm);

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceStatus();

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate(
			String attendanceDate);

	public int approvedByPrincipal(
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVos);

}
