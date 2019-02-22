package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SuddenHolidayListDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.SuddenHolidayListDaoImpl;
import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.service.SuddenHolidayListService;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListServiceImpl implements SuddenHolidayListService{
	private static Logger logger = Logger.getLogger(SuddenHolidayListServiceImpl.class);
	
	SuddenHolidayListDao holidayListDao = new SuddenHolidayListDaoImpl();

	
	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList() {
		
		return holidayListDao.SuddenHolidayList();
	}


	
	public String AddSuddenHoliday(SuddenHolidayForm form2) {

		
		String status = null;
		String suddenholidayscode = null;
		String count = null;

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListServiceImpl: AddSuddenHoliday : Starting");

		try {
			suddenholidayscode = IDGenerator
					.getPrimaryKeyID("sms_suddenholidays_details");
			
			
			SuddenHolidaysPojo upojo = new SuddenHolidaysPojo();
			
			upojo.setClassid(form2.getClassListArray());
			upojo.setSectionid(form2.getSectionListArray());
			upojo.setHdate(HelperClass.convertUIToDatabase(form2.getHdate()));
			//upojo.setHdate(form2.getHdate());
			upojo.setSmstext(form2.getSmstext());
			upojo.setCreatedby(form2.getCreatedUser());
			upojo.setCreatedate(HelperClass.getCurrentTimestamp());
			upojo.setIssection(1);
			upojo.setIsstudent(0);
			upojo.setSuddenholidayscode(suddenholidayscode);
			
			count=holidayListDao.AddSuddenHoliday(upojo);
		
			count = holidayListDao.storeSuudenHolidaysSections(upojo);


		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaysByPrinceService: storeSuddenHolidays Ending");
		return count;

	}



	@Override
	public boolean validateSuddenHolidaysSms(String date, String smstext) {

		return holidayListDao.validateSuddenHolidaysSms(date,smstext);
	}

}
