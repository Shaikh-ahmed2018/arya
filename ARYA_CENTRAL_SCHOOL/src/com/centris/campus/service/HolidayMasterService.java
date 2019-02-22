package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.vo.HolidayMasterVo;

public interface HolidayMasterService {

	ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation);

	HolidayMasterVo editHolidayDetail(String deptId, String date);

	HolidayMasterVo editHolidayDetail(String deptId);

}
