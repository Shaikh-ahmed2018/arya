package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffAttendanceDao;
import com.centris.campus.daoImpl.StaffAttendanceDaoImpl;
import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.service.StaffAttendanceService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceServiceImpl implements StaffAttendanceService{
	
private static final Logger logger = Logger.getLogger(StaffAttendanceServiceImpl.class);

	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: getStaffAttendance : Starting");
			
			StaffAttendanceDao dao=new StaffAttendanceDaoImpl();
			ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getStaffAttendance(date, department);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: getStaffAttendance: Ending");
			
			return staffAttendanceList;
		}


	@Override
	public String updateAttendanceStatus(StaffAttendancePojo attPojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: updateAttendanceStatus : Starting");
			
			StaffAttendanceDao dao=new StaffAttendanceDaoImpl();
			String status=null;
			
			try {
				
				status =	dao.updateAttendanceStatus(attPojo);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: updateAttendanceStatus: Ending");
			
			return status;
		}


	@Override
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: getStaffAttendance : Starting");
			
			StaffAttendanceDao dao=new StaffAttendanceDaoImpl();
			ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getStaffAttendanceList(startdate,enddate);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceServiceImpl: getStaffAttendance: Ending");
			
			return staffAttendanceList;
		}


}
