package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListDao {
	
	public ArrayList<SuddenHolidaySMSVO> getAccYears();
	
	public ArrayList<SuddenHolidaySMSVO> getStream();
	
	public ArrayList<SuddenHolidaySMSVO> getStudentClass(String location);
	
	public ArrayList<SuddenHolidaySMSVO>getlocationList();
	
	
	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList();

	String AddSuddenHoliday(SuddenHolidaysPojo upojo);

	String storeSuudenHolidaysSections(SuddenHolidaysPojo upojo);

	boolean validateSuddenHolidaysSms(String date, String smstext);

}
