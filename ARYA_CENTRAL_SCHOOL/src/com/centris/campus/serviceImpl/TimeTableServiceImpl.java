package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TimeTableDao;
import com.centris.campus.daoImpl.TimeTableDaoImpl;
import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.service.TimeTableService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;

public class TimeTableServiceImpl implements TimeTableService {
	private static final Logger logger = Logger
			.getLogger(TimeTableServiceImpl.class);

	TimeTableDao obj = new TimeTableDaoImpl();

	@Override
	public synchronized ArrayList<TimeTableVo> getTimeTableDetails(String timetableDetails,String details2) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		try {
			details = obj.getTimeTableDetails(timetableDetails,details2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTimeTableDetails Ending");
		return details;
	}

	@Override
	public synchronized ArrayList<TimeTableVo> getClassName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassName Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		try {
			details = obj.getClassName();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassName Ending");
		return details;
	}

	@Override
	public synchronized ArrayList<TimeTableVo> getSectionName(String classid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getSectionName Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		try {
			details = obj.getSectionName(classid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getSectionName Ending");
		return details;
	}

	@Override
	public String updateTimeTableDetails(TimeTablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : updateTimeTableDetails Starting");
		String result = null;
		try {
			result = obj.updateTimeTableDetails(pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : updateTimeTableDetails Ending");
		return result;
	}

	@Override
	public synchronized ArrayList<TimeTableVo> getTeacherTimeTableDetails(
			String teacherid, String accyearid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTeacherTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		try {
			details = obj.getTeacherTimeTableDetails(teacherid, accyearid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTeacherTimeTableDetails Ending");
		return details;
	}

	@Override
	public ArrayList<TeacherTimeTableVo> getTeacherName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTeacherName Starting");

		ArrayList<TeacherTimeTableVo> details = new ArrayList<TeacherTimeTableVo>();
		try {
			details = obj.getTeacherName();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getTeacherName Ending");
		return details;
	}
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : updateTeacherTimeTableDetails Starting");
		String result = null;
		try {
			result = obj.updateTeacherTimeTableDetails(pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : updateTeacherTimeTableDetails Ending");
		return result;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String viewBy) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
	
		try {
			
			details = obj.getClassTimeTableList(accyearid,viewBy);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Ending");
		
		return details;
	}
	
	public ArrayList<TimeTableVo> getClassSectionList(){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassSectionList Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
	
		try {
			
			details = obj.getClassSectionList();
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassSectionList Ending");
		
		return details;
	
	}

	
	public Object getClassNameDetailsService(String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassNameDetailsService Starting");
		
		String result = null;
		
		try {
			
			 result = obj.getClassNameDetailsDao(classid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassNameDetailsService Ending");
		return result;
	}

	
	public Object getSectionNameDetailsService(String sectionid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getSectionNameDetailsService Starting");
		
		String result = null;
		
		try {
			
			 result = obj.getSectionNameDetailsDao(sectionid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getSectionNameDetailsService Ending");
		return result;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy,	String classId,String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
	
		try {
			
			details = obj.getClassTimeTableListByClass(accyearid,viewBy,classId,locationId);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Ending");
		
		return details;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid, String classId, String sectionId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
	
		try {
			
			details = obj.getClassTimeTableListBySection(accyearid,classId,sectionId);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassTimeTableList Ending");
		
		return details;
	}

	@Override
	public Object getTeacherNameDetails(String classId, String sectionId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassNameDetailsService Starting");
		
		String result = null;
		
		try {
			
			 result = obj.getTeacherNameDetails(classId,sectionId);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableServiceImpl : getClassNameDetailsService Ending");
		return result;
	}
}