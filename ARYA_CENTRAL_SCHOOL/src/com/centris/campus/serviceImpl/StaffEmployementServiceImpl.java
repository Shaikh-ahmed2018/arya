package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffEmployementDao;
import com.centris.campus.dao.StageFeeSetupDao;
import com.centris.campus.daoImpl.StaffEmployementDaoImpl;
import com.centris.campus.daoImpl.StageFeeSetupDaoImpl;
import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.service.StaffEmployementService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffSearchVo;

public class StaffEmployementServiceImpl implements StaffEmployementService{
	
	private static final Logger logger = Logger.getLogger(StaffEmployementServiceImpl.class);



	@Override
	public StaffEmployementVo getStaffEmployementEntry(String teachercode,String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementServiceImpl : getStaffEmployementEntry Starting");
		StaffEmployementVo staffEmployemnetList = null;
		StaffEmployementDao dao = new StaffEmployementDaoImpl();
		try {
			
			staffEmployemnetList = dao.getStaffEmployementEntry(teachercode,academic_year);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementServiceImpl : getStaffEmployementEntry Ending");
		return staffEmployemnetList;
	}


	@Override
	public String saveStaffSalaryDetails(StaffEmployementForm staffForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementServiceImpl : saveStaffSalaryDetails Starting");
		String status = null;
		StaffEmployementDao dao = new StaffEmployementDaoImpl();
		try {
			
			status = dao.saveStaffSalaryDetails(staffForm);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffEmployementServiceImpl : saveStaffSalaryDetails Ending");
		return status;
	}


	
	
public boolean validateEmployeePfNumber(String emppfno,String empid){
	
	StaffEmployementDao dao = new StaffEmployementDaoImpl();

		
		return new StaffEmployementDaoImpl().validateEmployeePfNumber(emppfno,empid);
	}
	
public boolean validateBankAccNumber(String accnumber,String empid){
	StaffEmployementDao dao = new StaffEmployementDaoImpl();

	
	return new StaffEmployementDaoImpl().validateBankAccNumber(accnumber,empid);
}


@Override
public String saveStaffIncomeTaxTDSDeductionDetails(StaffEmployementForm staffForm) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StaffEmployementServiceImpl : saveStaffSalaryDetails Starting");
	String status = null;
	StaffEmployementDao dao = new StaffEmployementDaoImpl();
	try {
		
		status = dao.saveStaffIncomeTaxTDSDeductionDetails(staffForm);
		
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StaffEmployementServiceImpl : saveStaffSalaryDetails Ending");
	return status;
}
}
