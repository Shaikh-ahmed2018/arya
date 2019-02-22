package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.StageFeeSetupDao;
import com.centris.campus.daoImpl.StageFeeSetupDaoImpl;
import com.centris.campus.pojo.StageFeeSetupPojo;
import com.centris.campus.service.StageFeeSetupService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StageFeeSetupVo;

public class StageFeeSetupServiceImpl implements StageFeeSetupService{
	
	private static final Logger logger = Logger.getLogger(StageFeeSetupServiceImpl.class);

	@Override
	public ArrayList<StageFeeSetupVo> getStageFeeSetupDetails(String currentaccyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getStageFeeSetupDetails Starting");
		ArrayList<StageFeeSetupVo> feelist = null;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		try {
			
			feelist = dao.getStageFeeSetupDetails(currentaccyear);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getStageFeeSetupDetails Ending");
		return feelist;
	}

	@Override
	public ArrayList<StageFeeSetupVo> getSearchStageFeeSetupDetails(String searchterm, String currentaccyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getSearchStageFeeSetupDetails Starting");
		ArrayList<StageFeeSetupVo> feelist = null;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		try {
			
			feelist = dao.getSearchStageFeeSetupDetails(searchterm,currentaccyear);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getSearchStageFeeSetupDetails Ending");
		return feelist;
	}

	@Override
	public ArrayList<StageFeeSetupVo> getApprovedStages(StageFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getApprovedStages Starting");
		
		ArrayList<StageFeeSetupVo> feelist = null;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getApprovedStages(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getApprovedStages Ending");
	
		return feelist;
	}

	@Override
	public ArrayList<StageFeeSetupVo> getAllStages(
			StageFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getAllFees Starting");
		
		ArrayList<StageFeeSetupVo> feelist = null;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getAllStages(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : getAllFees Ending");
	
		return feelist;
	}
	
	public int insertApproveFees(ArrayList<StageFeeSetupPojo> approvefeelist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : insertApproveFees Starting");
		
		int count=0;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		
		try {
			
			count = dao.insertApproveFees(approvefeelist);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : insertApproveFees Ending");
	
		return count;
	}


	@Override
	public boolean deleteFees(StageFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : deleteFees Starting");
		
		boolean flag=false;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		
		try {
			
			flag = dao.deleteFees(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : deleteFees Ending");
	
		return flag;
	}



	@Override
	public boolean insertFeeAmount(ArrayList<StageFeeSetupPojo> feeSetupList) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : insertFeeAmount Starting");
		
		boolean flag=false;
		StageFeeSetupDao dao = new StageFeeSetupDaoImpl();
		
		try {
			
			flag = dao.insertFeeAmount(feeSetupList);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupServiceImpl : insertFeeAmount Ending");
	
		return flag;
	}

}
