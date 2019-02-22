package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffServiceReportDao;
import com.centris.campus.daoImpl.StaffServiceReportDaoImpl;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.service.StaffServiceReportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PayCertificateReportVO;

public class StaffServiceReportServiceImpl implements StaffServiceReportService{

	
	private static final Logger logger = Logger.getLogger(StaffServiceReportServiceImpl.class);
	
	StaffServiceReportDao dao = new StaffServiceReportDaoImpl();
	
	public ArrayList<TeacherRegistrationPojo> getTeacherListDetailsService(String locationid,String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherListDetailsService Starting");
		
		
		 ArrayList<TeacherRegistrationPojo> list = null;
		
		try {
			
			
			
			
			list = dao.getTeacherListDetailsDao(locationid,classid);
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherListDetailsService Ending");
		
		return list;
	}


	public ArrayList<TeacherRegistrationPojo> getTeacherDetailReportService(
			TeacherRegistrationPojo teacherpojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherDetailReportService Starting");
		
		ArrayList<TeacherRegistrationPojo> list = null;
		try {
			
			
			list = dao. getTeacherDetailReportDao(teacherpojo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherDetailReportService Ending");
		
		return list;
	}


	
	public ArrayList<PayCertificateReportVO> getTeacherPayDetailsReportService(
			String accyear, String month, String teachername) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherPayDetailsReportService Starting");
		
		
		String year = "";
		ArrayList<PayCertificateReportVO> list = null;
		try {
			if(Integer.parseInt(month) > 5){
				year = accyear.trim().split("-")[0];
	    }else{
	    	year = accyear.trim().split("-")[1];
	     }
	
			list = dao. getTeacherPayDetailsReportDao(year,month,teachername);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffServiceReportServiceImpl : getTeacherPayDetailsReportService Ending");
		
		return list;
	}

}
