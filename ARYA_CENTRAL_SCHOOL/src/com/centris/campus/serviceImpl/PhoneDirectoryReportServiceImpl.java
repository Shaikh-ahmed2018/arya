package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.PhoneDirectoryReportDao;
import com.centris.campus.daoImpl.PhoneDirectoryReportDaoImpl;
import com.centris.campus.service.PhoneDirectoryReportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PhoneDirectoryVo;

public class PhoneDirectoryReportServiceImpl implements PhoneDirectoryReportService{

	
	private static final Logger logger = Logger
			.getLogger(PhoneDirectoryReportServiceImpl.class);

	PhoneDirectoryReportDao dao = new PhoneDirectoryReportDaoImpl();

	
	public List<PhoneDirectoryVo> getPhoneDirectoryNamesService(
			PhoneDirectoryVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getPhoneDirectoryNamesService Starting");
		
		 List<PhoneDirectoryVo> list = null;
		try {
			
			
			list = dao.getPhoneDirectoryNamesDao(vo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getPhoneDirectoryNamesService Ending");
		
		
		return list;
	}



	public List<PhoneDirectoryVo> getPhoneDirectoryListService(
			PhoneDirectoryVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getPhoneDirectoryListService Starting");
		
		 List<PhoneDirectoryVo> list = null;
			try {
				
				
				list = dao.getPhoneDirectoryListDao(vo);
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getPhoneDirectoryListService Ending");
		
		
		return list;
	}



	
	public PhoneDirectoryVo getSelectedValueNameService(PhoneDirectoryVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getSelectedValueNameService Starting");
		
		
		PhoneDirectoryVo list = null;
		try {
			
			
			list = dao.getSelectedValueNameDao(vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportServiceImpl : getSelectedValueNameService Ending");
		
		return list;
	}

}
