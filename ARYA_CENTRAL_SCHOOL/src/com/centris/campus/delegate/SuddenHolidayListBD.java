package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.service.SuddenHolidayListService;
import com.centris.campus.serviceImpl.SuddenHolidayListServiceImpl;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListBD {
	static SuddenHolidayListService holidayListService;
	static {
		holidayListService=new SuddenHolidayListServiceImpl();
	}
	
	//SuddenHolidayListService holidayListService = new SuddenHolidayListServiceImpl();

	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList() {
		
		return holidayListService.SuddenHolidayList();
	}

	public String AddSuddenHoliday(SuddenHolidayForm form2) {
		
		return holidayListService.AddSuddenHoliday(form2);
	}

	public boolean validateSuddenHolidaysSms(String date, String smstext) {
		
		return holidayListService.validateSuddenHolidaysSms(date,smstext);
	}
	public ArrayList<SuddenHolidaySMSVO> getAccYears(){
		return holidayListService.getAccYears();
	}
	
public ArrayList<SuddenHolidaySMSVO> getStream(){
		
		return holidayListService.getStream();
	}
public ArrayList<SuddenHolidaySMSVO> getStudentClass(String schoolLocation){
	
	return holidayListService.getStudentClass(schoolLocation);
}
public ArrayList<SuddenHolidaySMSVO> getClassesByStream(String streamId){
	
	
	 
	return holidayListService.getClassesByStream(streamId);
	
}

public ArrayList<SuddenHolidaySMSVO> getSectionsByClass(String classId, String location){
	
	
	
	return holidayListService.getSectionsByClass(classId,location);
	
}
public ArrayList<SuddenHolidaySMSVO> getlocationList() {
	
	return holidayListService.getlocationList();
}
/*public ArrayList<SuddenHolidaySMSVO> getClassDetails() {
	return holidayListService.getClassDetails();
}*/
}
