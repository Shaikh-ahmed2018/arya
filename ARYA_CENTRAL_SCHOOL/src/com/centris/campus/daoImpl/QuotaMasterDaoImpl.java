package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.QuotaMasterDao;
import com.centris.campus.forms.QuotaDetailsForms;
import com.centris.campus.pojo.QuotaMasterPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.QuotaMasterVO;

public class QuotaMasterDaoImpl implements QuotaMasterDao {
	private static final Logger logger = Logger
			.getLogger(QuotaMasterDaoImpl.class);

	public synchronized ArrayList<QuotaMasterVO> getQuotaDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : getQuotaDetails Starting");

		ArrayList<QuotaMasterVO> list = new ArrayList<QuotaMasterVO>();
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_QUOTA_DETAILS);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				QuotaMasterVO pojo = new QuotaMasterVO();
				pojo.setQuotaid(rst.getString("Quota_Code"));
				pojo.setQuotaName(rst.getString("Quota_Name"));
				pojo.setQuotadescription(rst.getString("Quota_Description"));
				list.add(pojo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ "Control in QuotaMasterDaoImpl : getQuotaDetails Ending");
		return list;
	}

	public synchronized String addQuotaDetails(QuotaMasterPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : addQuotaDetails Starting");
		int count = 0;
		PreparedStatement pstmt = null;
		String status = null;

		// if (!checkQuotaCode(pojo.getQuotaName())) {

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.INSERT_QUOTA_DETAILS);

			pstmt.setString(1, pojo.getQuotaid());
			pstmt.setString(2, pojo.getQuotaName().trim());
			pstmt.setString(3, pojo.getQuotaDescription());
			pstmt.setTimestamp(4, pojo.getCreate_date());
			pstmt.setString(5, pojo.getCreated_by());

			count = pstmt.executeUpdate();

			if (count > 0) {
				status = MessageConstants.ADD_QUOTA_SUCCESS;
			} else {
				status = MessageConstants.ADD_QUOTA_FAIL;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		// }

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : addQuotaDetails Ending");

		return status;
	}

	public synchronized String updateQuotaDetails(QuotaMasterPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : updateQuotaDetails Starting");
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_QUOTA_COUNT_FOR_UPDATE);

			pstmt.setString(1, pojo.getQuotaName().trim());
			pstmt.setString(2, pojo.getQuotaid());
			
			
			
			
			rs = pstmt.executeQuery();

			rs.next();

			StringTokenizer st = new StringTokenizer(pojo.getQuotaDescription()
					.replaceAll("Â", ""));
			StringBuffer sb = new StringBuffer();
			while (st.hasMoreElements()) {
				sb.append(st.nextElement()).append(" ").toString()
						.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
			}

			if (rs.getInt(1) == 0) {
				pstmt = conn
						.prepareStatement(SQLUtilConstants.UPDATE_QUOTA_DETAILS);
				pstmt.setString(1, pojo.getQuotaName().trim());
				pstmt.setString(2, sb.toString());
				pstmt.setString(3, pojo.getUpdated_by());
				pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
				pstmt.setString(5, pojo.getQuotaid().trim());

				
				
				count = pstmt.executeUpdate();

			}
			if (count > 0) {

				status = MessageConstants.UPDATE_QUOTA_SUCCESS;
			} else {
				status = MessageConstants.UPDATE_QUOTA_FAIL;
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
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : updateQuotaDetails Ending");
		return status;
	}

	public synchronized String deleteQuotaDetails(QuotaMasterVO deletelist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : deleteQuotaDetails Starting");

		String status = null;
		int count = 0;
		PreparedStatement pstmt1 = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt1 = conn
					.prepareStatement(SQLUtilConstants.GET_QUOTA_COUNT_BEFOREDELETE);
			pstmt1.setString(1, deletelist.getQuotaid());

			rst = pstmt1.executeQuery();

			while (rst.next()) {
				count = rst.getInt(1);

			}

			if (count == 0) {
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_QUOTA);

				pstmt.setString(1, deletelist.getQuotaid());
				pstmt.executeUpdate();

				status = MessageConstants.QUOTA_DELETE_SUCCESS;
			} else {
				status = MessageConstants.QUOTA_IN_USE;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : deleteQuotaDetails Ending");
		return status;
	}

	public synchronized QuotaMasterPojo editQuotaDetails(
			QuotaMasterPojo editlist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : editQuotaDetails Starting");
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		QuotaMasterPojo editpojo = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_PARTICULAR_QUOTA);
			pstmt.setString(1, editlist.getQuotaid());
			rst = pstmt.executeQuery();

			while (rst.next()) {
				editpojo = new QuotaMasterPojo();
				editpojo.setQuotaid(editlist.getQuotaid());
				editpojo.setQuotaName(rst.getString("Quota_Name"));
				editpojo.setQuotaDescription(rst.getString("Quota_Description"));

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : editQuotaDetails Ending");
		return editpojo;

	}

	public synchronized ArrayList<QuotaMasterVO> searchQuota(
			QuotaMasterVO searchvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : searchQuota Starting");

		ArrayList<QuotaMasterVO> getquotalist = new ArrayList<QuotaMasterVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_QUOTA_DETAILS);

			pstmt.setString(1, "%" + searchvo.getSearch_name() + "%");
			pstmt.setString(2, "%" + searchvo.getSearch_name() + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sno++;
				QuotaMasterVO quotadetailsVO = new QuotaMasterVO();

				quotadetailsVO.setQuotaid(rs.getString("Quota_Code"));
				quotadetailsVO.setQuotaName(rs.getString("Quota_Name"));
				quotadetailsVO.setQuotadescription(rs
						.getString("Quota_Description"));
				getquotalist.add(quotadetailsVO);

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
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : searchQuota Ending");
		return getquotalist;

	}

	public synchronized boolean validateQuotaName(QuotaDetailsForms quotaform) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : validateQuotaName Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean quotaname_available = false;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_QUOTA_NAME);

			pstmt.setString(1, quotaform.getQuotaname());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt("quotaname");
			}

			if (count > 0) {

				quotaname_available = true;

			} else {

				quotaname_available = false;
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
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : validateQuotaName Ending");

		return quotaname_available;
	}

	public synchronized boolean validateQuotaNameUpdate(QuotaDetailsForms validateupdate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : validateQuotaNameUpdate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean quotaname_available = false;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.VALIDATE_QUOTA_UPDATE);
			pstmt.setString(1, validateupdate.getQuotaname());
			pstmt.setString(2, validateupdate.getQuotacode());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("quotaname");

			}

			if (count > 0) {

				quotaname_available = true;

			} else {

				quotaname_available = false;
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
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in QuotaMasterDaoImpl : validateQuotaNameUpdate Ending");

		return quotaname_available;
	}

}
