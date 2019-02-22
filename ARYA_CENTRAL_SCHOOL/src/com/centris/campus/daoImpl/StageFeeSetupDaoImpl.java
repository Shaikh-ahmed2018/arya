package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StageFeeSetupDao;
import com.centris.campus.pojo.StageFeeSetupPojo;
import com.centris.campus.util.StageFeeSetupSqlUtilConstants;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StageFeeSetupVo;

public class StageFeeSetupDaoImpl implements StageFeeSetupDao{

	private static final Logger logger = Logger.getLogger(StageFeeSetupDaoImpl.class);
	
	@Override
	public ArrayList<StageFeeSetupVo> getStageFeeSetupDetails(
			String currentaccyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl : getStageFeeSetupDetails Starting");
		
		ArrayList<StageFeeSetupVo> feeList = new ArrayList<StageFeeSetupVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		Connection conn = null;
		int sno=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.GET_CLASS_DETAILS);
			pstmt.setString(1,currentaccyear);

			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				
				StageFeeSetupVo feevo = new StageFeeSetupVo();
				sno++;
				feevo.setSno(sno);
				feevo.setAccyear(resultSet.getString("acadamic_year"));
				feevo.setAccyearid(resultSet.getString("acadamic_id"));
				feevo.setClassid(resultSet.getString("classdetail_id_int"));
				feevo.setClassname(resultSet.getString("classdetails_name_var"));
				feevo.setTermid(resultSet.getString("termid"));
				feevo.setTermname(resultSet.getString("termname"));
				
				pstmt1 = conn.prepareStatement(StageFeeSetupSqlUtilConstants.GET_STAGE_COUNT);
				pstmt1.setString(1, resultSet.getString("classdetail_id_int"));
				pstmt1.setString(2, resultSet.getString("acadamic_id"));
				pstmt1.setString(3, resultSet.getString("termid"));
				
				resultSet1=pstmt1.executeQuery();
				
				while(resultSet1.next()){
					
					feevo.setStagecount(resultSet1.getInt("stagecount"));
				}
				
				feeList.add(feevo);
				
				
				}
			
			
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl : getStageFeeSetupDetails Ending");
		
		return feeList;
	}

	@Override
	public ArrayList<StageFeeSetupVo> getSearchStageFeeSetupDetails(
			String searchterm, String currentaccyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl : getSearchStageFeeSetupDetails Starting");
		
		ArrayList<StageFeeSetupVo> feeList = new ArrayList<StageFeeSetupVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		Connection conn = null;
		int sno=0;
		int feecount=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.SERCH_FEE_DETAILS);
			pstmt.setString(1, "%"+searchterm+"%");
			pstmt.setString(2, "%"+searchterm+"%");
			pstmt.setString(3, "%"+searchterm+"%");
			pstmt.setString(4, currentaccyear);
			
			resultSet = pstmt.executeQuery();
			
			
			
			while (resultSet.next()) {
				
				StageFeeSetupVo feevo = new StageFeeSetupVo();
				sno++;
				feevo.setSno(sno);
				feevo.setStagecount(feecount);
				feevo.setAccyear(resultSet.getString("acadamic_year"));
				feevo.setAccyearid(resultSet.getString("acadamic_id"));
				feevo.setClassid(resultSet.getString("classdetail_id_int"));
				feevo.setClassname(resultSet.getString("classdetails_name_var"));
				feevo.setTermid(resultSet.getString("termid"));
				feevo.setTermname(resultSet.getString("termname"));
				
				pstmt1 = conn.prepareStatement(StageFeeSetupSqlUtilConstants.GET_STAGE_COUNT);
				pstmt1.setString(1, resultSet.getString("classdetail_id_int"));
				pstmt1.setString(2, resultSet.getString("acadamic_id"));
				pstmt1.setString(3, resultSet.getString("termid"));
				
				resultSet1=pstmt1.executeQuery();
				
				while(resultSet1.next()){
					
					feevo.setStagecount(resultSet1.getInt("stagecount"));
				}
				
				feeList.add(feevo);
				
				
				}
			
			
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl : getSearchStageFeeSetupDetails Ending");
		
		return feeList;
	}

	@Override
	public ArrayList<StageFeeSetupVo> getApprovedStages(
			StageFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: getApprovedStages Starting");

		PreparedStatement pstmt = null;
		ArrayList<StageFeeSetupVo> approvedFees = new ArrayList<StageFeeSetupVo>();
		ResultSet resultSet = null;
		Connection conn = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.GET_APPROVED_STAGES);
			
			pstmt.setString(1, feeSetupPojo.getClassId());
			pstmt.setString(2, feeSetupPojo.getAcadamicYear());
			pstmt.setString(3, feeSetupPojo.getTerm());
			

			if (pstmt != null) {
				resultSet = pstmt.executeQuery();

				while (resultSet.next()) {
					count++;
					StageFeeSetupVo FeeSetupVo = new StageFeeSetupVo();

					FeeSetupVo.setSno(count);
					FeeSetupVo.setFeecode(resultSet.getString("stage_id"));
					FeeSetupVo.setFeename(resultSet.getString("stage_name"));
					FeeSetupVo.setFeesettingcode(resultSet
							.getString("stageSettingCode"));
					FeeSetupVo.setFeeamount(resultSet.getString("feeAmount"));
					FeeSetupVo.setAccyearid(feeSetupPojo.getAcadamicYear());
					FeeSetupVo.setClassid(feeSetupPojo.getClassId());
					FeeSetupVo.setTermid(feeSetupPojo.getTerm());

					approvedFees.add(FeeSetupVo);
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: getApprovedStages  Ending");

		return approvedFees;

	}

	@Override
	public ArrayList<StageFeeSetupVo> getAllStages(
			StageFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: getAllStages Starting");

		ArrayList<StageFeeSetupVo> allfeeslist = new ArrayList<StageFeeSetupVo>();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.GET_ALL_STAGES);

			pstmt.setString(1, feeSetupPojo.getClassId());
			pstmt.setString(2, feeSetupPojo.getAcadamicYear());
			pstmt.setString(3, feeSetupPojo.getTerm());
			
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				count++;
				StageFeeSetupVo feeSetupVo = new StageFeeSetupVo();
				feeSetupVo.setSno(count);
				feeSetupVo.setFeecode(resultSet.getString("stage_id"));
				feeSetupVo.setFeename(resultSet.getString("stage_name"));

				allfeeslist.add(feeSetupVo);
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: getAllStages  Ending");

		return allfeeslist;
	}
	
	public synchronized int insertApproveFees(ArrayList<StageFeeSetupPojo> approvefeelist) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: insertApproveFees Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		PreparedStatement pstmt1=null;
		ResultSet rs1=null;
		Connection conn = null;
		String setupcode=null;
		int setupcount=0;
		int fee_count = 0;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			for (int i=0;i<approvefeelist.size();i++) {

				pstmt1=conn.prepareStatement(StageFeeSetupSqlUtilConstants.FEE_SETUP_COUNT);
				pstmt1.setString(1, approvefeelist.get(i).getClassId());
				pstmt1.setString(2, approvefeelist.get(i).getAcadamicYear());
				pstmt1.setString(3, approvefeelist.get(i).getTerm());
				rs1=pstmt1.executeQuery();
				
				while(rs1.next()){
					
					setupcount=rs1.getInt(1);
				}
				
				
				if(setupcount==0){
					
					setupcode=IDGenerator.getPrimaryKeyID("campus_fee_stagesetup");
					
					pst = conn.prepareStatement(StageFeeSetupSqlUtilConstants.INSERT_APPROVED_FEES_IN_FEE_SETUP);

					pst.setString(1, setupcode);
					pst.setString(2, approvefeelist.get(i).getClassId());
					pst.setString(3, approvefeelist.get(i).getAcadamicYear());
					pst.setString(4, approvefeelist.get(i).getTerm());
					pst.setString(5, approvefeelist.get(i).getCreatedby());
					
					count = pst.executeUpdate();
				}
				
				if(setupcode!=null){
					if (count > 0) {
						PreparedStatement insert_pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.INSERT_APPROVED_FEES);
						insert_pstmt.setString(1,setupcode);
						insert_pstmt.setString(2, approvefeelist.get(i).getFeeId());
						insert_pstmt.setDouble(3, 0);
						
						
						fee_count = insert_pstmt.executeUpdate();
					}
					}else{
						PreparedStatement insert_pstmt = conn.prepareStatement(StageFeeSetupSqlUtilConstants.INSERT_APPROVED_FEES_AMT);
						
						insert_pstmt.setString(1, approvefeelist.get(i).getClassId());
						insert_pstmt.setString(2, approvefeelist.get(i).getAcadamicYear());
						insert_pstmt.setString(3, approvefeelist.get(i).getTerm());
						insert_pstmt.setString(4, approvefeelist.get(i).getFeeId());
						insert_pstmt.setDouble(5, 0);
						
						
						fee_count = insert_pstmt.executeUpdate();
						
					}
			}
			
		} catch (SQLException sqlExp) {
			
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pst != null && !pst.isClosed()) {
					pst.close();
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
				+ " Control in StageFeeSetupDaoImpl: insertApproveFees  Ending");

		return fee_count;
	}

	@Override
	public boolean deleteFees(StageFeeSetupPojo feeSetupPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: deleteFees Starting");
		int count = 0;
		boolean flag = false;
		PreparedStatement count_pstmt = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			count_pstmt = conn
					.prepareStatement(StageFeeSetupSqlUtilConstants.DELETE_FEE_CODE);

			count_pstmt.setString(1, feeSetupPojo.getFeesettingCode());
			count_pstmt.setString(2, feeSetupPojo.getFeeId());

			count = count_pstmt.executeUpdate();
			if (count > 0) {

				flag = true;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (count_pstmt != null && !count_pstmt.isClosed()) {
					count_pstmt.close();
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
				+ " Control in StageFeeSetupDaoImpl: deleteFees  Ending");
		return flag;
	}

	public synchronized boolean insertFeeAmount(ArrayList<StageFeeSetupPojo> feeSetupList) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: insertFeeAmount Starting");

		int count = 0;
		boolean flag = false;
		PreparedStatement count_pstmt = null;
		Connection connection = null;
		try {

			connection = JDBCConnection.getSeparateConnection();
			count_pstmt = connection.prepareStatement(StageFeeSetupSqlUtilConstants.UPDATE_FEE_AMOUNT);

			for(int i=0; i<feeSetupList.size();i++){
				
			count_pstmt.setString(1, feeSetupList.get(i).getFeeamount());
			count_pstmt.setString(2, feeSetupList.get(i).getClassId());
			count_pstmt.setString(3, feeSetupList.get(i).getAccyearId());
			count_pstmt.setString(4, feeSetupList.get(i).getTermId());
			count_pstmt.setString(5, feeSetupList.get(i).getFeeId());
			

			count = count_pstmt.executeUpdate();
			}

			if (count > 0) {

				flag = true;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (count_pstmt != null && !count_pstmt.isClosed()) {
					count_pstmt.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageFeeSetupDaoImpl: insertFeeAmount  Ending");
		return flag;
	}
	


}
