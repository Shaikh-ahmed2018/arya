package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.serviceImpl.TeacherAttendanceServiceImpl;
import com.centris.campus.vo.PostAttendanceVO;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;
import com.centris.campus.vo.TeacherManualAttendanceVO;

public class TeacherAttendanceBD {

	public List<TeacherAttendanceVo> getTeacherAttendanceBD(
			TeacherAttendanceForm attendanceForm) {
		// TODO Auto-generated method stub
		return new TeacherAttendanceServiceImpl()
				.getTeacherAttendanceService(attendanceForm);
	}

	public boolean insertTeacherAttendanceBD(
			TeacherAttendanceForm attendanceForm) {
		// TODO Auto-generated method stub
		return new TeacherAttendanceServiceImpl()
				.insertTeacherAttendanceService(attendanceForm);
	}

	public String PostAttendance(PostAttendanceVO postattendance) {
		// TODO Auto-generated method stub
		return new TeacherAttendanceServiceImpl()
				.PostAttendance(postattendance);
	}

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceStatus() {
		return new TeacherAttendanceServiceImpl().getTeacherAttendanceStatus();
	}

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate(
			String attendanceDate) {
		return new TeacherAttendanceServiceImpl()
				.getTeacherAttendanceByDate(attendanceDate);
	}

	public int approvedByPrincipal(
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVo) {
		return new TeacherAttendanceServiceImpl()
				.approvedByPrincipal(attendanceStatusVo);
	}

	public ArrayList<TeacherManualAttendanceVO> GetTeacherAttendance(String user,String date) {
		return new TeacherAttendanceServiceImpl().GetTeacherAttendance(user,date);
	}
	
	public String updateTeacherAttendanceDetails(String teacherid[], String TeacherStatus[], String date, String updateby) {
		return new TeacherAttendanceServiceImpl().updateTeacherAttendanceDetails(teacherid,TeacherStatus,date,updateby);
	}

}
