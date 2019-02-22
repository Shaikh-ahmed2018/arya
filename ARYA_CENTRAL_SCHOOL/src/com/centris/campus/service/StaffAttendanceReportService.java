package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceReportService {

	ReportMenuVo getSelectedItemsService(String acc);

	ArrayList<StaffAttendanceVo> getStaffAttendanceReportService(ReportMenuVo vo);

	List<AllTeacherDetailsVo> getTeachingListService(AllTeacherDetailsVo vo);

	List<AllTeacherDetailsVo> getNonTeachingListService(AllTeacherDetailsVo vo);

	StaffAttendanceVo getSelectedTeacherNameReportService(ReportMenuVo vo);

}
