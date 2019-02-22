package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.service.SuddenHolidayListService;
import com.centris.campus.serviceImpl.SuddenHolidayListServiceImpl;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListBD {
	
	SuddenHolidayListService holidayListService = new SuddenHolidayListServiceImpl();

	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList() {
		
		return holidayListService.SuddenHolidayList();
	}

	public String AddSuddenHoliday(SuddenHolidayForm form2) {
		
		return holidayListService.AddSuddenHoliday(form2);
	}

	public boolean validateSuddenHolidaysSms(String date, String smstext) {
		
		return holidayListService.validateSuddenHolidaysSms(date,smstext);
	}
	

}
