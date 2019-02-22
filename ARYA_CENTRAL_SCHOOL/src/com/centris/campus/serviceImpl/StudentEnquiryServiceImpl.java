package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentEnquiryDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentEnquiryDaoImpl;
import com.centris.campus.forms.StudentEnquiryForm;
import com.centris.campus.pojo.StudentEnquiryPojo;
import com.centris.campus.service.StudentEnquiryService;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StudentEnquiryVo;

public class StudentEnquiryServiceImpl implements StudentEnquiryService {
	private static final Logger logger = Logger
			.getLogger(StudentEnquiryServiceImpl.class);

	@Override
	public List<StudentEnquiryVo> getAllEnquiryDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getAllEnquiryDetails Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		try {
			allDetails = obj.getAllEnquiryDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getAllEnquiryDetails Ending");
		return allDetails;
	}

	@Override
	public List<StudentEnquiryVo> getSelectedEnquiryDetails(String Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getSelectedEnquiryDetails Starting");
		List<StudentEnquiryVo> selectedIdDetails = new ArrayList<StudentEnquiryVo>();
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		try {
			selectedIdDetails = obj.getSelectedEnquiryDetails(Id.trim());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getSelectedEnquiryDetails Ending");
		return selectedIdDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.centris.campus.service.StudentEnquiryService#saveStudentEnquiry(com
	 * .centris.campus.forms.StudentEnquiryForm)
	 */
	public String saveStudentEnquiry(StudentEnquiryForm studentEnquiryForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: saveStudentEnquiry  Starting");

		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		String status = "";
		try {

			status = obj.saveStudentEnquiry(studentEnquiryForm);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: saveStudentEnquiry  Ending");

		return status;
	}

	public boolean duplicateStudentChecking(
			StudentEnquiryPojo studentenquiryPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: duplicateStudentChecking  Starting");

		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		boolean status = false;
		try {

			status = obj.duplicateStudentChecking(studentenquiryPojo);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: duplicateStudentChecking  Ending");

		return status;
	}

	public boolean validationMobileno(String mobileno) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: validationMobileno  Starting");
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		boolean status = false;

		try {
			status = obj.validationMobileno(mobileno);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: validationMobileno  Ending");
		return status;
	}

	public List<StudentEnquiryVo> getEnquiryDetailsBySearch(String date,
			String name, String interactionStatus, String AdmissionStatus) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getEnquiryDetailsBySearch Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		try {

			if ((date.equalsIgnoreCase("")) || (date.equalsIgnoreCase(null))) {
				date = "%%";
			} else {
				date = HelperClass.convertUIToDatabase(date.trim()) + "%";
			}
			name = "%" + name.trim() + "%";
			interactionStatus = "%" + interactionStatus.trim() + "%";
			AdmissionStatus = "%" + AdmissionStatus.trim() + "%";

			allDetails = obj.getEnquiryDetailsBySearch(date, name,
					interactionStatus, AdmissionStatus);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getEnquiryDetailsBySearch Ending");
		return allDetails;
	}

	public boolean updateStudentEnquiry(StudentEnquiryVo studentenquiry) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : updateStudentEnquiry Starting");
		boolean status = false;
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		try {
			status = obj.updateStudentEnquiry(studentenquiry);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : updateStudentEnquiry Ending");
		return status;
	}

	public boolean applicationNoValidate(String appnumber) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: applicationNoValidate  Starting");
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		boolean status = false;

		try {
			status = obj.applicationNoValidate(appnumber);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: applicationNoValidate  Ending");

		return status;
	}

	public boolean validationPhoneno(String mobileno, String enq_Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: validationMobileno  Starting");
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();

		boolean status = false;

		try {
			status = obj.validationPhoneno(mobileno, enq_Id);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl: validationMobileno  Ending");
		return status;
	}

	public boolean deleteEnquiryDetails(String Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : deleteEnquiryDetails Starting");
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		boolean status = false;
		try {
			status = obj.deleteEnquiryDetails(Id.trim());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : deleteEnquiryDetails Ending");
		return status;
	}

	public List<StudentEnquiryVo> getSearchEnquiryDetails(String text) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getSearchEnquiryDetails Starting");
		StudentEnquiryDao obj = new StudentEnquiryDaoImpl();
		List<StudentEnquiryVo> list = null;
		try {
			list = obj.getSearchEnquiryDetails(text);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getSearchEnquiryDetails Ending");
		return list;
	}
}