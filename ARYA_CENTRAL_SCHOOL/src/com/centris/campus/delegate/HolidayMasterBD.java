package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;
import com.centris.campus.service.HolidayMasterService;
import com.centris.campus.serviceImpl.HolidayMasterServiceImpl;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.vo.HolidayMasterVo;


public class HolidayMasterBD {

	HolidayMasterService service;
	public String addMultipleHoliday(HolidayMasterForm formObj, String usercode) {
		return new HolidayMasterServiceImpl().addMultipleHoliday(formObj,usercode);
	}

	public ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation) {
		service = new HolidayMasterServiceImpl();
		return service.getHolidayDetails(academic_year,schoolLocation);
	}

	public HolidayMasterVo editHolidayDetail(String deptId, String date) {
		service = new HolidayMasterServiceImpl();
		return service.editHolidayDetail(deptId,date);
	}

	public String addSingleHoliday(HolidayMasterPojo hpojo) {
		return new HolidayMasterServiceImpl().addSingleHoliday(hpojo);
	}

	public Boolean deleteSingleHoliday(String holidaydate, String deptid) {
		return new HolidayMasterServiceImpl().deleteSingleHoliday(holidaydate,deptid);
	}

	public String dateValidate(String dateval, String location, String accYear) {
		return new HolidayMasterServiceImpl().dateValidate(dateval,location,accYear);
	}

	public ArrayList<HolidayMasterVo> searchLocationDetails(String searchterm, String academic_year) {
		return new HolidayMasterServiceImpl().searchLocationDetails(searchterm,academic_year);
	}

	public Boolean deleteSingleHoliday(String[] list) {
		return new HolidayMasterServiceImpl().deleteSingleHoliday(list);
	}

	public HolidayMasterVo editHolidayDetail(String deptId) {
		service = new HolidayMasterServiceImpl();
		return service.editHolidayDetail(deptId);
	}

}
