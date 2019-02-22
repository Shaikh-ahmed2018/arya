package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.CareersViewDao;
import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.InternalJobPostSQLUtilConstants;
import com.centris.campus.vo.CareerUpdatePopuVO;
import com.centris.campus.vo.CareersViewVo;

public class CareersViewDaoImpl implements CareersViewDao {
	private static final Logger logger = Logger
			.getLogger(CareersViewDaoImpl.class);
	PreparedStatement pstmt = null;
	PreparedStatement pstmtview = null;
	List<CareerUpdatePopuVO> listcareview;
	boolean flag = true;

	@Override
	public synchronized List<CareersViewVo> getCareersViewDao() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getCareersViewDao Starting");

		List<CareersViewVo> carvo = new ArrayList<CareersViewVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(InternalJobPostSQLUtilConstants.CARREER_DATA);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				CareersViewVo car = new CareersViewVo();
				car.setJobcode(rs.getString("jobcode"));
				car.setTitle(rs.getString("title"));
				car.setQualification(rs.getString("qualification"));
				car.setNoofposition(rs.getString("noofpositions"));
				car.setExperience(rs.getString("experience"));
				car.setDescription(rs.getString("description"));
				car.setStatus(rs.getString("status"));
				carvo.add(car);
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getCareersView Ending");
		return carvo;
	}

	@Override
	public synchronized CareersViewVo getValues(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getValues Starting");

		CareersViewVo car = new CareersViewVo();
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(InternalJobPostSQLUtilConstants.VIEW_DATA);

			pstmt.setString(1, pojo.getJobcode());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				car.setJobcode(rs.getString("jobcode"));
				car.setTitle(rs.getString("title"));
				car.setQualification(rs.getString("qualification"));
				car.setNoofposition(rs.getString("noofpositions"));
				car.setExperience(rs.getString("experience"));
				car.setDescription(rs.getString("description"));
				car.setStatus(rs.getString("status"));

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getValues Ending");
		return car;

	}

	public synchronized boolean getDelete(CareersViewPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getDelete Starting");
		boolean status = false;
		Connection conn = null;
		int count = 0;
		int count1 = 0;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(InternalJobPostSQLUtilConstants.JOB_CHECK_APPLIEDJOBS);
			pstmt.setString(1, pojo.getJobcode());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count1 = rs.getInt("job");
			}

			if (count1 == 0) {
				pstmt = conn
						.prepareStatement(InternalJobPostSQLUtilConstants.DELETE_CAREER);

				pstmt.setString(1, pojo.getJobcode());
				count = pstmt.executeUpdate();

				if (count > 0) {
					status = true;
				}
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl:getDelete Ending");
		return status;

	}

	public synchronized List<CareersViewVo> getAllCareersView() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl: getAllCareersViewDao Starting");
		ResultSet rs = null;
		List<CareersViewVo> carvo = new ArrayList<CareersViewVo>();

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(InternalJobPostSQLUtilConstants.ALL_CARREER_DATA);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CareersViewVo car = new CareersViewVo();

				car.setJobcode(rs.getString("jobcode"));
				car.setTitle(rs.getString("title"));
				car.setQualification(rs.getString("qualification"));
				car.setNoofposition(rs.getString("noofpositions"));
				car.setExperience(rs.getString("experience"));
				car.setDescription(rs.getString("description"));
				car.setStatus(rs.getString("status"));

				carvo.add(car);

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl: getAllCareersViewDao Ending");
		return carvo;
	}

	public synchronized String addJobs(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareersViewDaoImpl:addJobs Starting");
		String status = null;
		int count = 0;
		int count1 = 0;
		Connection conn = null;
		// PreparedStatement pstmt1 = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			if ("NULL".equalsIgnoreCase(pojo.getCheck())) {
				/*
				 * pstmt1 = conn
				 * .prepareStatement(InternalJobPostSQLUtilConstants
				 * .CHECK_JOB_TITLE); pstmt1.setString(1, pojo.getTitle()); rs =
				 * pstmt1.executeQuery();
				 * 
				 * while (rs.next()) { count = rs.getInt(1); }
				 * 
				 * if (count == 0) {
				 */
				pstmt = conn
						.prepareStatement(InternalJobPostSQLUtilConstants.ADD_JOB_DETAILS);
				pstmt.setString(1, pojo.getJobcode());
				pstmt.setString(2, pojo.getTitle());
				pstmt.setString(3, pojo.getQualification());
				pstmt.setString(4, pojo.getNoofposition());
				pstmt.setString(5, pojo.getExperience());
				pstmt.setString(6, pojo.getDescription());
				pstmt.setString(7, pojo.getCreatedby());
				count = pstmt.executeUpdate();
				if (count > 0) {
					status = MessageConstants.SUCCESS;
				} else {
					status = MessageConstants.FAILD;
				}
				/* } */

			} else {

				if ("deactive".equalsIgnoreCase(pojo.getStatus())) {

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(InternalJobPostSQLUtilConstants.JOB_CHECK_APPLIEDJOBS);
					pstmt.setString(1, pojo.getJobcode());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						count1 = rs.getInt("job");
					}
					if (count1 == 0) {
						pstmt = conn
								.prepareStatement(InternalJobPostSQLUtilConstants.UPDATE_JOB_DETAILS);
						pstmt.setString(1, pojo.getTitle());
						pstmt.setString(2, pojo.getQualification());
						pstmt.setString(3, pojo.getNoofposition());
						pstmt.setString(4, pojo.getExperience());
						pstmt.setString(5, pojo.getDescription());
						pstmt.setString(6, pojo.getStatus());
						pstmt.setString(7, pojo.getCreatedby());
						pstmt.setString(8, pojo.getJobcode());
						count = pstmt.executeUpdate();
						if (count > 0) {
							status = MessageConstants.SUCCESS;
						} else {
							status = MessageConstants.FAILD;
						}
					} else {
						status = MessageConstants.DUPLICATE;
					}

				} else {
					pstmt = conn
							.prepareStatement(InternalJobPostSQLUtilConstants.UPDATE_JOB_DETAILS);
					pstmt.setString(1, pojo.getTitle());
					pstmt.setString(2, pojo.getQualification());
					pstmt.setString(3, pojo.getNoofposition());
					pstmt.setString(4, pojo.getExperience());
					pstmt.setString(5, pojo.getDescription());
					pstmt.setString(6, pojo.getStatus());
					pstmt.setString(7, pojo.getCreatedby());
					pstmt.setString(8, pojo.getJobcode());
					count = pstmt.executeUpdate();
					if (count > 0) {
						status = MessageConstants.SUCCESS;
					} else {
						status = MessageConstants.FAILD;
					}
				}

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareersViewDaoImpl:addJobs Ending");
		return status;
	}

	public synchronized boolean checkTitle(CareersViewPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareersViewDaoImpl:checkTitle Starting");

		boolean status = false;
		int count = 0;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			if ("NULL".equalsIgnoreCase(pojo.getJobcode())) {
				pstmt = conn
						.prepareStatement(InternalJobPostSQLUtilConstants.CHECK_JOB_TITLE);

				pstmt.setString(1, pojo.getTitle());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0) {
					status = true;
				}
			} else {

				pstmt = conn
						.prepareStatement(InternalJobPostSQLUtilConstants.CHECK_JOB_TITLE_WHILE_UPDATING);

				pstmt.setString(1, pojo.getTitle());
				pstmt.setString(2, pojo.getJobcode());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0) {
					status = true;
				}

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CareersViewDaoImpl:checkTitle Ending");
		return status;
	}

	public synchronized List<CareersViewVo> searchDetails(String name) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl : searchDetails  Starting");
		List<CareersViewVo> carvo = new ArrayList<CareersViewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(InternalJobPostSQLUtilConstants.GET_SEARCH_LIST);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CareersViewVo car = new CareersViewVo();
				car.setJobcode(rs.getString("jobcode"));
				car.setTitle(rs.getString("title"));
				car.setQualification(rs.getString("qualification"));
				car.setNoofposition(rs.getString("noofpositions"));
				car.setExperience(rs.getString("experience"));
				car.setDescription(rs.getString("description"));
				car.setStatus(rs.getString("status"));
				carvo.add(car);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CareersViewDaoImpl : searchDetails  Starting");

		return carvo;
	}
}
