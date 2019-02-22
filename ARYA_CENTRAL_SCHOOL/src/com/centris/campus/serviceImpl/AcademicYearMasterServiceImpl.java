package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AcademicYearMasterDao;
import com.centris.campus.daoImpl.AcademicYearMasterDaoImpl;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.service.AcademicYearMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcademicYearVO;

public class AcademicYearMasterServiceImpl implements AcademicYearMasterService {
	private static final Logger logger = Logger
			.getLogger(AcademicYearMasterServiceImpl.class);

	AcademicYearMasterDao yearMasterdao = new AcademicYearMasterDaoImpl();

	public ArrayList<AcademicYearVO> getAcademicYearDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getSpecializationDetails  Starting");
		ArrayList<AcademicYearVO> getacademiclist = null;
		try {

			getacademiclist = yearMasterdao.getAcademicYearDetails();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getSpecializationDetails  Starting");

		return getacademiclist;
	}

	public String createAcademicYear(AcademicYearPojo ACY_pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : createAcademicYear  Starting");
		String status = null;
		try {

			status = yearMasterdao.createAcademicYear(ACY_pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : createAcademicYear  Starting");
		return status;
	}

	public boolean deleteAcademicYear(String ACY_code) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : deleteAcademicYear  Starting");
		boolean status = false;
		try {

			status = yearMasterdao.deleteAcademicYear(ACY_code);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : deleteAcademicYear  Starting");
		return status;
	}

	public ArrayList<AcademicYearVO> editAcademicYear(String ACY_code) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : editAcademicYear  Starting");
		ArrayList<AcademicYearVO> EditACCDetails = null;
		try {

			EditACCDetails = yearMasterdao.editAcademicYear(ACY_code);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : editAcademicYear  Starting");
		return EditACCDetails;
	}

	public String accyearNameCheck(AcademicYearPojo ACY_pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : accyearNameCheck  Starting");
		String status = null;
		try {

			status = yearMasterdao.accyearNameCheck(ACY_pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : accyearNameCheck  Starting");
		return status;

	}

	public ArrayList<AcademicYearVO> searchAcademicYearDetails(String searchname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : searchAcademicYearDetails  Starting");
		ArrayList<AcademicYearVO> getacademiclist = null;
		try {

			getacademiclist = yearMasterdao
					.searchAcademicYearDetails(searchname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : searchAcademicYearDetails  Starting");

		return getacademiclist;
	}

	@Override
	public synchronized List<AcademicYearVO> getAccYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserServiceImpl: getAllUsers Starting");
		List<AcademicYearVO> getAccyr = new ArrayList<AcademicYearVO>();
		try {
			getAccyr = yearMasterdao.getAccYear();
		} catch (Exception exception) {
			exception.printStackTrace();

			logger.error(exception.getMessage(), exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserServiceImpl: getAllUsers  Ending");
		return getAccyr;
	}

	public ArrayList<AcademicYearPojo> getAcademicYearList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearList  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearList  Starting");
		return yearMasterdao.getAcademicYearList();

	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearDetailsByBranchId  Starting");
		ArrayList<AcademicYearVO> getacademiclist = null;
		try {

			getacademiclist = yearMasterdao
					.getAcademicYearDetailsByBranchId(brancid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearDetailsByBranchId  Starting");

		return getacademiclist;
	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearDetailsbysearch  Starting");
		ArrayList<AcademicYearVO> getacademiclist = null;
		try {

			getacademiclist = yearMasterdao.getAcademicYearDetailsbysearch(
					brancid, searchname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterServiceImpl : getAcademicYearDetailsbysearch  Starting");

		return getacademiclist;

	}
}
