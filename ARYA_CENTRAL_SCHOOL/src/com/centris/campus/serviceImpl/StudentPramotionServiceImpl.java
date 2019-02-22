package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentPramotionDaoImpl;
import com.centris.campus.pojo.StudentPromotionPOJO;
import com.centris.campus.service.StudentPramotionService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.ClassPromotionList;
import com.centris.campus.vo.StudentPramotionVO;

public class StudentPramotionServiceImpl implements StudentPramotionService {
	private static final Logger logger = Logger
			.getLogger(StudentPramotionServiceImpl.class);
	String result;

	public List<AcadamicYearVO2> getAcadamicYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl:getAcadamicYear  Starting");

		List<AcadamicYearVO2> yeardetailslist = new ArrayList<AcadamicYearVO2>();
		try {
			yeardetailslist = new StudentPramotionDaoImpl().getAcadamicYear();
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception);
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl:getAcadamicYear  Ending");
		return yeardetailslist;
	}

	public List<StudentPramotionVO> getStudentData(String acadamicYear,
			String section) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl:getStudentData  Starting");
		List<StudentPramotionVO> studentlist = new ArrayList<StudentPramotionVO>();
		try {
			studentlist = new StudentPramotionDaoImpl().getStudentData(
					acadamicYear, section);
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception);
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: getStudentData Ending");
		return studentlist;
	}

	public StudentPramotionVO insertStudentPromotion(
			StudentPromotionPOJO studentPramotionPOJO) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: insertStudentPromotion  Starting");
		StudentPramotionVO studentList = new StudentPramotionVO();
		try {
			String studentPramotionid = IDGenerator
					.getPrimaryKeyID("campus_studentpromotion");

			studentPramotionPOJO.setStudentpramotionid(studentPramotionid);

			studentList = new StudentPramotionDaoImpl()
					.insertStudentPromotion(studentPramotionPOJO);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl:insertStudentPromotion   Ending");
		return studentList;
	}

	public ArrayList<StudentPramotionVO> getpromotionslist() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: getpromotionslist  Starting");
		ArrayList<StudentPramotionVO> yeardetailslist = null;
		try {
			yeardetailslist = new StudentPramotionDaoImpl().getpromotionslist();
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception);
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: getpromotionslist   Ending");

		return yeardetailslist;
	}

	public ArrayList<StudentPramotionVO> getpromotionssearchlist(String className, String sectionName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: getpromotionssearchlist  Starting");
		ArrayList<StudentPramotionVO> getpromotionssearchlist =null;
		try{
			getpromotionssearchlist =new StudentPramotionDaoImpl().getpromotionssearchlist(className,sectionName);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionServiceImpl: getpromotionssearchlist   Ending");

		
		return getpromotionssearchlist;
	}

	public List<ClassPromotionList> getClassListForPromotion(String currentYear) {
		return new StudentPramotionDaoImpl().getClassListForPromotion(currentYear);
	}
}
