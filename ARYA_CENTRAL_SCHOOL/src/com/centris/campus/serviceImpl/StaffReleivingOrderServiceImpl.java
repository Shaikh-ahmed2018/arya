package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffReleivingOrderDao;
import com.centris.campus.daoImpl.StaffReleivingOrderDaoImpl;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.service.StaffReleivingOrderService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public class StaffReleivingOrderServiceImpl implements StaffReleivingOrderService{
	
	private static final Logger logger = Logger.getLogger(StaffReleivingOrderServiceImpl.class);
	
	StaffReleivingOrderDao dao = new StaffReleivingOrderDaoImpl();

	
	public List<AllTeacherDetailsVo> getTeachingListService(
			AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getTeachingListService Starting");
		
		 List<AllTeacherDetailsVo> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getTeachingListDaoImpl(vo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getTeachingListService Ending");
		
		
		return teachinglist;
	}


	
	public List<AllTeacherDetailsVo> getNonTeachingListService(
			AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getNonTeachingListService Starting");
		
		 List<AllTeacherDetailsVo> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getNonTeachingListDaoImpl(vo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getNonTeachingListService Ending");
		
		
		return teachinglist;
	}



	
	public List<UserDetailVO> getUsersListService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getUsersListService Starting");
		
		 List<UserDetailVO> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getUsersListDaoImpl();
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getUsersListService Ending");
		
		
		return teachinglist;
	}



	
	public List<ReleivingOrderVo> getReleivingDetailsService(
			RelievingOrderPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getReleivingDetailsService Starting");
		
       List<ReleivingOrderVo> list = null;
		 
		 try {
			
			 
			 list=dao.getReleivingDetailsDaoImpl(pojo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getReleivingDetailsService Ending");
		
		return list;
	}
	
	
	

}
