package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListDao {

	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList();

	String AddSuddenHoliday(SuddenHolidaysPojo upojo);

	String storeSuudenHolidaysSections(SuddenHolidaysPojo upojo);

	boolean validateSuddenHolidaysSms(String date, String smstext);

}
