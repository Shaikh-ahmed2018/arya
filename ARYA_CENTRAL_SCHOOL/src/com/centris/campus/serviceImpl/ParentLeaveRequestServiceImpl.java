package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ParentLeaveRequestDao;
import com.centris.campus.daoImpl.ParentLeaveRequestDaoImpl;
import com.centris.campus.service.ParentLeaveRequestService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.RemainderMasterVO;

public class ParentLeaveRequestServiceImpl implements ParentLeaveRequestService{

	ParentLeaveRequestDao parentdao = new ParentLeaveRequestDaoImpl();
	private static final Logger logger = Logger
			.getLogger(ParentLeaveRequestServiceImpl.class);
	
	public String leaveRequestEntryService(LeaveRequestVo leavevo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : leaveRequestEntryService Starting"); 
		
		String leavevo1 =null;
		
		
		if(leavevo.getSno()==0){
			
			
			
			try {
				
				leavevo1 = parentdao.leaveRequestEntryDao(leavevo);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
		
		else if(leavevo.getSno() != 0){
			
			
			try {
				
				leavevo1 = parentdao.updateleaveRequestEntryDao(leavevo);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : leaveRequestEntryService  Ending");
		
		return leavevo1;
	}


	public LeaveRequestVo getRequestLeaveService(int sno) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestLeaveService Starting"); 
		
		
		LeaveRequestVo asslist =null;
		try {
			
			
			asslist = parentdao.getRequestLeaveDao(sno);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRequestLeaveService  Ending");
		
		
		return asslist;
	}


	
	public List<RemainderMasterVO> getRemainderlistService() {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRemainderlistService Starting"); 
		
		
		ArrayList<RemainderMasterVO> remainderlist = null;
		try {
			
			
			remainderlist = parentdao. getRemainderlistDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRemainderlistService  Ending");
		
		
		return remainderlist;
	}

}
