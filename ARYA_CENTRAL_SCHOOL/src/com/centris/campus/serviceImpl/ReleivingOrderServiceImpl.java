package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ReleivingOrderDao;
import com.centris.campus.daoImpl.ReleivingOrderDaoImpl;
import com.centris.campus.service.ReleivingOrderService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class ReleivingOrderServiceImpl implements ReleivingOrderService {
	
	
	
	private static final Logger logger = Logger
			.getLogger(ReleivingOrderServiceImpl.class);
	
	
	ReleivingOrderDao dao = new ReleivingOrderDaoImpl();


	
	public List<ReportMenuVo> getTeacherListService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherListService Starting");
		
		List<ReportMenuVo> teacxherlist = null;
		try {
			
			teacxherlist=dao.getTeacherListDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getTeacherListService  Ending");
		
		return teacxherlist;
	}	
	
	
	
	

}
