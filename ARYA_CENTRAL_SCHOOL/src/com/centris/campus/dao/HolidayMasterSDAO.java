package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.HolidayMasterVo;

public interface HolidayMasterSDAO {

	ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation);

	HolidayMasterVo editHolidayDetail(String deptId, String date);

}
