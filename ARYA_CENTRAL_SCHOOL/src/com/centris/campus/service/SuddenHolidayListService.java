package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListService {

	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList();

	String AddSuddenHoliday(SuddenHolidayForm form2);

	boolean validateSuddenHolidaysSms(String date, String smstext);

	public ArrayList<SuddenHolidaySMSVO> getAccYears();

	public ArrayList<SuddenHolidaySMSVO> getStream();
	
	public ArrayList<SuddenHolidaySMSVO> getStudentClass(String schoolLocation);

	public ArrayList<SuddenHolidaySMSVO> getClassesByStream(String streamId);

	public ArrayList<SuddenHolidaySMSVO> getSectionsByClass(String classId, String location);

	public ArrayList<SuddenHolidaySMSVO> getlocationList();

	//public ArrayList<SuddenHolidaySMSVO> getClassDetails();

	

	
	

}
