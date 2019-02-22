package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.service.StaffAttendanceReportService;
import com.centris.campus.serviceImpl.StaffAttendanceReportServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceReportBD {
	
	
	StaffAttendanceReportService service = new StaffAttendanceReportServiceImpl();

	public ReportMenuVo getSelectedItems(String acc) {
		
		
		return service.getSelectedItemsService(acc);
	}

	public ArrayList<StaffAttendanceVo> getStaffAttendanceReportBD(ReportMenuVo vo) {
		
		return service.getStaffAttendanceReportService(vo);
	}

	public List<AllTeacherDetailsVo> getTeachingListBD(AllTeacherDetailsVo vo) {
		
		return service.getTeachingListService(vo);
	}

	public List<AllTeacherDetailsVo> getNonTeachingListBD(AllTeacherDetailsVo vo) {
		return service.getNonTeachingListService(vo);
	}

	public StaffAttendanceVo getSelectedTeacherNameReportBD(ReportMenuVo vo) {
	
		return service.getSelectedTeacherNameReportService(vo);
	}
	
	
	
	
	
	

}
