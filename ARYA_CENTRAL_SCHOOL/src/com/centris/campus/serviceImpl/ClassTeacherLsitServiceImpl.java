package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassTeacherLsitDao;
import com.centris.campus.daoImpl.ClassTeacherLsitDaoImpl;
import com.centris.campus.daoImpl.StreamDetailsDaoImpl;
import com.centris.campus.service.ClassTeacherLsitService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherVo;

public class ClassTeacherLsitServiceImpl implements ClassTeacherLsitService{

	private static final Logger logger = Logger.getLogger(ClassTeacherLsitServiceImpl.class);
	
	ClassTeacherLsitDao dao = new ClassTeacherLsitDaoImpl();
	
	public ArrayList<ClassTeacherVo> getClassTeacherListService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getClassTeacherListService Starting");
		
		ArrayList<ClassTeacherVo> list = null;
		try {
			
			list = dao.getClassTeacherListDao();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getClassTeacherListService Ending");
		
		return list;
	}

	
	
	
	public ClassTeacherVo editClassTeacherService(ClassTeacherVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : editClassTeacherService Starting");
		
		
		ClassTeacherVo teaval=null;
		
		try {
			
			teaval = dao.editClassTeacherDao(vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : editClassTeacherService Ending");
		
		return teaval;
	}




	
	public String saveClassTeacherService(ClassTeacherVo vo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : saveClassTeacherService Starting");
		
		String check = "";
		try {
			
			
			
			check = dao.saveClassTeacherDao(vo);
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : saveClassTeacherService Ending");
		
		return check;
	}




	public boolean validateClassTeacherService(ClassTeacherVo vo) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : validateClassTeacherService Starting");
		
		

		boolean classteacher_validate = false;
		
		try {
			classteacher_validate = dao.validateClassTeacherDao(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : validateClassTeacherService Ending");
		
		
		return classteacher_validate;
	}




	
	public ArrayList<ClassTeacherVo> getSearchClassTeacherListService(
			String searchTerm) {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getSearchClassTeacherListService Starting");
		
		
		ArrayList<ClassTeacherVo> list = null;
		try {
			
			list = dao.getSearchClassTeacherListDao(searchTerm);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getSearchClassTeacherListService Ending");
		
		
		
		return list;
	}
	
	
	

}
