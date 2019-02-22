package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceReportDao {

	ReportMenuVo getSelectedItemsDaoImpl(String acc);

	ArrayList<StaffAttendanceVo> getStaffAttendanceReportDaoImpl(ReportMenuVo vo);

	ArrayList<AllTeacherDetailsVo> getTeachingListDaoImpl(AllTeacherDetailsVo vo);

	ArrayList<AllTeacherDetailsVo> getNonTeachingListDaoImpl(
			AllTeacherDetailsVo vo);

	StaffAttendanceVo getSelectedTeacherNameReportDao(ReportMenuVo vo);

}
