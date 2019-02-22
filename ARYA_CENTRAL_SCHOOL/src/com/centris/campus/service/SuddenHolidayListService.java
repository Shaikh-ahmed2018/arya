package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListService {

	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList();

	String AddSuddenHoliday(SuddenHolidayForm form2);

	boolean validateSuddenHolidaysSms(String date, String smstext);

}
