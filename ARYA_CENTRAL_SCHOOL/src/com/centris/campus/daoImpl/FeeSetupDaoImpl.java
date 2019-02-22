package com.centris.campus.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.FeeSetupDao;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;

import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.FeeConcessionVO;
import com.centris.campus.vo.StageMasterVo;

public class FeeSetupDaoImpl implements FeeSetupDao

{
	private static final Logger logger = Logger
			.getLogger(FeeSetupDaoImpl.class);

	public synchronized List<ConcessionDetailsPojo> getconcessiondetails(
			ConcessionDetailsPojo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getconcessiondetails Starting");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		List<ConcessionDetailsPojo> concessiondetailsList = new ArrayList<ConcessionDetailsPojo>();

		ResultSet rs = null;
		Connection conn = null;

		if (vo.getConcessionName() == null
				|| vo.getConcessionName().equalsIgnoreCase(""))

		{
			try {
				System.out.println("Feeconcession daoimpl");
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn
						.prepareStatement(SQLUtilConstants.GET_CONCESSION_DETAILS);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();
					pojo.setConcessionId(rs.getString("concessionid"));
					pojo.setConcessionName(rs.getString("concessionname"));
					pojo.setPercentage(rs.getString("percentage"));
					pojo.setDescription(rs.getString("description"));
					concessiondetailsList.add(pojo);
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
					+ " Control in FeeSetupDaoImpl : getconcessiondetails  Ending");

		} else if (!vo.getConcessionName().equalsIgnoreCase(""))

		{
			System.out.println("gfjhfghjgjh@@@@@@@@@@DAOIMPL");

			try {
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH);

				pstmt.setString(1, "%" + vo.getConcessionName() + "%");

				pstmt.setString(2, "%" + vo.getConcessionName() + "%");

				pstmt.setString(3, "%" + vo.getConcessionName() + "%");

				System.out.println(pstmt);

				rs = pstmt.executeQuery();
				System.out.println("designation_rs" + rs);

				while (rs.next())

				{
					ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();

					pojo.setConcessionId(rs.getString("concessionid"));
					pojo.setConcessionName(rs.getString("concessionname"));
					pojo.setPercentage(rs.getString("percentage"));
					pojo.setDescription(rs.getString("description"));
					concessiondetailsList.add(pojo);

				}

			} catch (SQLException e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
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
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getconcessiondetails : Ending");
		return concessiondetailsList;

	}

	public synchronized String insertConcesssionDetails(
			ConcessionDetailsPojo detailsPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : insertConcesssionDetails  Starting");
		// TODO Auto-generated method stub

		PreparedStatement concession_pstmt = null;

		String status = null;

		Connection conn = null;

		int result1 = 0;

		try {

			conn = JDBCConnection.getSeparateConnection();

			concession_pstmt = conn
					.prepareStatement(SQLUtilConstants.INSERT_CONCESSION_DETAILS);
			concession_pstmt
					.setString(1, IDGenerator
							.getPrimaryKeyID("campus_fee_concessiondetails"));
			concession_pstmt.setString(2, detailsPojo.getConcessionName()
					.trim());
			concession_pstmt.setString(3, detailsPojo.getPercentage().trim());
			concession_pstmt.setString(4, detailsPojo.getDescription().trim());
			concession_pstmt.setString(5, detailsPojo.getCreateUser());
			/*
			 * concession_pstmt.setTimestamp(6, time_stamp);
			 * concession_pstmt.setString(7, null);
			 * concession_pstmt.setString(8, null);
			 */

			result1 = concession_pstmt.executeUpdate();

			if (result1 > 0) {

				status = MessageConstants.ADD_FEE_CONCSEEION_SUCCESS;

			} else {

				status = MessageConstants.ADD_FEE_CONCSEEION_FAIL;
			}

		}

		catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(), sqle);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (concession_pstmt != null && (!concession_pstmt.isClosed())) {
					concession_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl :insertConcesssionDetails Ending");

		return status;
	}

	/*
	 * PreparedStatement pstmt = null; java.util.Date today = new
	 * java.util.Date(); java.sql.Timestamp time_stamp = new
	 * java.sql.Timestamp(today.getTime());
	 * 
	 * String USER_NAME = detailsPojo.getCreateUser(); FeeSetupDaoImpl impl =
	 * new FeeSetupDaoImpl(); String concesionName =
	 * detailsPojo.getConcessionName(); String desc =
	 * detailsPojo.getDescription(); String perc = detailsPojo.getPercentage();
	 * 
	 * Connection conn = null;
	 * 
	 * boolean check = impl.checkconcessionName(detailsPojo); if (!check) { try
	 * {
	 * 
	 * System.out.println("Daoimpl concession details"); conn =
	 * JDBCConnection.getSeparateConnection(); pstmt = conn
	 * .prepareStatement(SQLUtilConstants.INSERT_CONCESSION_DETAILS);
	 * pstmt.setString(1, IDGenerator
	 * .getPrimaryKeyID("campus_fee_concessiondetails")); pstmt.setString(2,
	 * concesionName.trim()); pstmt.setString(3, perc.trim());
	 * pstmt.setString(4, desc.trim()); pstmt.setString(5, USER_NAME);
	 * pstmt.setTimestamp(6, time_stamp); pstmt.setString(7, null);
	 * pstmt.setString(8, null);
	 * 
	 * pstmt.executeUpdate(); } catch (SQLException e) {
	 * logger.error(e.getMessage(), e); e.printStackTrace(); } catch (Exception
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } finally {
	 * try { if (pstmt != null && (!pstmt.isClosed())) { pstmt.close(); } if
	 * (conn != null && !conn.isClosed()) { conn.close(); } } catch (Exception
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } } }
	 * JLogger.log(0, JDate.getTimeString(new Date()) +
	 * MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	 * + " Control in FeeSetupDaoImpl : insertConcesssionDetails  Ending");
	 * return check; }
	 * 
	 * 
	 * public synchronized boolean checkconcessionName( ConcessionDetailsPojo
	 * detailsPojo) { logger.setLevel(Level.DEBUG); JLogger.log(0,
	 * JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	 * logger.info(JDate.getTimeString(new Date()) +
	 * " Control in FeeSetupDaoImpl : checkconcessionName Starting"); // TODO
	 * Auto-generated method stub
	 * 
	 * PreparedStatement pstmt = null;
	 * 
	 * boolean checkStream = false;
	 * 
	 * ResultSet rs = null; Connection conn = null;
	 * 
	 * try {
	 * 
	 * conn = JDBCConnection.getSeparateConnection(); pstmt = conn
	 * .prepareStatement(SQLUtilConstants.CHECK_CONCESSION_NAME);
	 * pstmt.setString(1, detailsPojo.getConcessionName());
	 * 
	 * rs = pstmt.executeQuery(); rs.next(); if (rs.getInt("usercount") > 0) {
	 * checkStream = true; } else { checkStream = false; } } catch (SQLException
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } catch
	 * (Exception e) { logger.error(e.getMessage(), e); e.printStackTrace(); }
	 * finally { try { if (rs != null && (!rs.isClosed())) { rs.close(); } if
	 * (pstmt != null && (!pstmt.isClosed())) { pstmt.close(); } if (conn !=
	 * null && !conn.isClosed()) { conn.close(); } } catch (Exception e) {
	 * logger.error(e.getMessage(), e); e.printStackTrace(); } } JLogger.log(0,
	 * JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	 * logger.info(JDate.getTimeString(new Date()) +
	 * " Control in FeeSetupDaoImpl : checkconcessionName  Ending"); return
	 * checkStream; }
	 */

	public synchronized ConcessionForm EditConcesssionDetails(
			ConcessionForm detailsForm) {

		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : updateConcessionDao Starting");

		PreparedStatement concession_pstmt = null;

		ResultSet concession_rs = null;

		Connection conn = null;

		ConcessionForm concessionVO = null;

		try {

			System.out.println("DAO conceession IMPl");

			conn = JDBCConnection.getSeparateConnection();
			concession_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_CONCESSION);

			concession_pstmt.setString(1, detailsForm.getConcessionId());

			concession_rs = concession_pstmt.executeQuery();
			System.out.println("pstmt" + concession_pstmt);

			while (concession_rs.next()) {
				concessionVO = new ConcessionForm();

				concessionVO.setConcessionId(concession_rs
						.getString("concessionid"));
				concessionVO.setConcesionName(concession_rs
						.getString("concessionname"));
				System.out.println("name:"
						+ concession_rs.getString("concessionname"));
				concessionVO.setPercentage(concession_rs
						.getString("percentage"));
				System.out.println("percentage"
						+ concession_rs.getString("percentage"));
				concessionVO.setDescription(concession_rs
						.getString("description"));
				concessionVO.setCreatedtime(concession_rs
						.getString("createdtime"));
				concessionVO.setCreatedby(concession_rs.getString("CreatedBy"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (concession_rs != null && (!concession_rs.isClosed())) {
					concession_rs.close();
				}
				if (concession_pstmt != null && (!concession_pstmt.isClosed())) {
					concession_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : updateStreamDetailsDao  Ending");
		return concessionVO;

	}

	@Override
	public String deleteconcession(FeeConcessionVO vo) {

		System.out.println("Delete fee dao impl concession is working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: deleteconcession : Starting");

		ResultSet concession_rs = null;
		ResultSet rs_deleteconcession = null;
		PreparedStatement deleteconcession_pstmt = null;
		PreparedStatement ps_deleteconcession = null;
		int no = 0;
		String status = null;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			deleteconcession_pstmt = conn
					.prepareStatement(SQLUtilConstants.CHECK_FEE_CONCESSION_MAP);

			deleteconcession_pstmt.setString(1, vo.getConcessionId());

			concession_rs = deleteconcession_pstmt.executeQuery();

			while (concession_rs.next()) {

				no = concession_rs.getInt(1);
			}

			if (no == 0) {

				ps_deleteconcession = conn
						.prepareStatement(SQLUtilConstants.DELETE_FEE_CONCESSION);

				ps_deleteconcession.setString(1, vo.getConcessionId());

				no = ps_deleteconcession.executeUpdate();

				if (no > 0) {

					status = MessageConstants.FEE_CONCSEEION_SUCCESS;

				}

				else {
					status = MessageConstants.FEE_CONCSEEION_ERROR;
				}
			}

			else

			{
				status = MessageConstants.FEE_CONCSEEION_WARNING;
			}

		}

		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {

			try {

				if (rs_deleteconcession != null
						&& (!rs_deleteconcession.isClosed())) {
					rs_deleteconcession.close();
				}
				if (concession_rs != null && (!concession_rs.isClosed())) {

					concession_rs.close();
				}
				if (ps_deleteconcession != null
						&& (!ps_deleteconcession.isClosed())) {

					ps_deleteconcession.close();
				}
				if (deleteconcession_pstmt != null
						&& (!deleteconcession_pstmt.isClosed())) {
					deleteconcession_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: deleteconcession : Ending");

		return status;

	}

	public boolean getnamecount(FeeConcessionVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getConcessionId().equalsIgnoreCase(""))

		{
			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(SQLUtilConstants.ADD_FEE_CONCESSION_COUNT);
				pstmt.setString(1, vo.getConcesionName().trim());

				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		}

		if (!vo.getConcessionId().equalsIgnoreCase(""))

		{

			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(SQLUtilConstants.EDIT_FEE_CONCESSION_COUNT);
				pstmt.setString(1, vo.getConcessionId().trim());
				pstmt.setString(2, vo.getConcesionName().trim());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getnamecount Ending");
		return result;

	}

	public String updateConcessionDao(ConcessionDetailsPojo detailsPojo)

	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl :updateConcessionDao");

		PreparedStatement concession_pstmt = null;

		
		
		String status = null;

		Connection conn = null;
		
		int result1=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			concession_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_FEE_CONCESSION_DETAILS);
			
			concession_pstmt.setString(1, detailsPojo.getConcessionName().trim());
			concession_pstmt.setString(2, detailsPojo.getDescription().trim());
			concession_pstmt.setString(3, detailsPojo.getPercentage().trim());
			concession_pstmt.setString(4, detailsPojo.getConcessionId());
			result1 = concession_pstmt.executeUpdate();

		
			if (result1 > 0) {

				status = MessageConstants.UPDATE_FEE_CONCSEEION_SUCCESS;
				
			} else {
				status = MessageConstants.UPDATE_FEE_CONCSEEION_FAIL;
				
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {

				if (concession_pstmt != null && !concession_pstmt.isClosed()) {
					concession_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : updateConcessionDao Ending");
			return status;
		}

}
