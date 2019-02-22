package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.MarksUploadDao;
import com.centris.campus.daoImpl.MarksUploadDaoImpl;
import com.centris.campus.service.MarksUploadService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDisplayVO;

public class MarksUploadServiceImpl implements MarksUploadService {
	private static final Logger logger = Logger
			.getLogger(MarksUploadServiceImpl.class);
	public String getMarksUpload(MarksUploadVO uploadmarks){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:getMarksUpload Starting");
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:getMarksUpload  Ending");
		return new MarksUploadDaoImpl().getMarksUpload(uploadmarks);
	}
	
	public List<ScoredMarksEntryVO> showStudentMarks(StudentMarksDisplayVO studentmarksdisplay){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:showStudentMarks Starting");
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:showStudentMarks  Ending");
		return new MarksUploadDaoImpl().showStudentMarks(studentmarksdisplay);
	}

	@Override
	public List<ScoredMarksEntryVO> getStudentMarks(StudentMarksDisplayVO studentmarksdisplay) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:getStudentMarks Starting");
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl:getStudentMarks  Ending");
		
		return new MarksUploadDaoImpl().getStudentMarks(studentmarksdisplay);
	}
	
	public ArrayList<MarksUploadVO>  getMarksUploadList(String serchterm){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl : getMarksUploadList Starting");
		
		ArrayList<MarksUploadVO> MarksUploadList=new  ArrayList<MarksUploadVO>();
		MarksUploadDao marksDao=new MarksUploadDaoImpl();
		try{
			
			MarksUploadList=marksDao.getMarksUploadList(serchterm);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MarksUploadServiceImpl : getMarksUploadList  Ending");
		
		return MarksUploadList;
	}
}
