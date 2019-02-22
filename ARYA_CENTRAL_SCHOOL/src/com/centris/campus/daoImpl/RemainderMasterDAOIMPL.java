package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.RemainderMasterDAO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;

public class RemainderMasterDAOIMPL implements RemainderMasterDAO {
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);


	public synchronized boolean getnamecount(RemainderMasterVO vo) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : getnamecount Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.REMAINDER_COUNT);
			pstmt.setString(1, vo.getName().trim());
			
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
		
	
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : getnamecount Ending");
		
		return result;
	}


	
	public synchronized String addremainderdata(RemainderMasterVO vo) 
	
	{
		System.out.println("addremainderdata DAO Working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : addremainderdata Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		System.out.println("The Type is::::"+vo.getRemtype());
		
		
		try {
			
				String str = IDGenerator.getPrimaryKeyID("campus_remainder");
				
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_REMAINDER);
				
				pstmt.setString(1, str);
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getDescription());
				pstmt.setString(4, vo.getRemtype());
				
				if(vo.getRemtype().equalsIgnoreCase("All"))
				{
					
					pstmt.setString(5, "Y");
					pstmt.setString(6, "Y");
					pstmt.setString(7, "Y");
				}
				
				else if(vo.getRemtype().equalsIgnoreCase("Teachers"))
				{
					pstmt.setString(5, "Y");
					pstmt.setString(6, "N");
					pstmt.setString(7, "N");
				}
				else if(vo.getRemtype().equalsIgnoreCase("Parents"))
				{
					pstmt.setString(5, "N");
					pstmt.setString(6, "Y");
					pstmt.setString(7, "N");
				}
				else if(vo.getRemtype().equalsIgnoreCase("Backoffice"))
				{
					pstmt.setString(5, "N");
					pstmt.setString(6, "N");
					pstmt.setString(7, "Y");
				}
				
				pstmt.setString(8, vo.getCreateuser());
				pstmt.setTimestamp(9,HelperClass.getCurrentTimestamp());
				System.out.println("ADD_REMAINDER"+pstmt);
				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.SuccessremainMsg;
				} else {
					result_Status = MessageConstants.ErrorremainMsg;
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : addremainderdata Ending");

		
		return result_Status;

	
	}



	
	public synchronized ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : remainderdetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<RemainderMasterVO> remainderdetails = new ArrayList<RemainderMasterVO>();
		
		try {

		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(SQLUtilConstants.GET_REMAINDER);
		
		System.out.println("GET_REMAINDER "+pstmt);
		
		rs = pstmt.executeQuery();
		while (rs.next())

		{
			RemainderMasterVO val = new RemainderMasterVO();
			val.setId(rs.getString("remainder_id"));
			val.setName(rs.getString("remainder_title"));
			val.setDescription(rs.getString("description"));
			val.setRemtype(rs.getString("remainder_to"));
			
			System.out.println("remainder_title::"+rs.getString("remainder_title"));
			
			remainderdetails.add(val);
		
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
	
	
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : addremainderdata Ending");
		
		return remainderdetails;
	}



	
	public synchronized RemainderMasterVO editremainderDetails(RemainderMasterVO vo) 
	
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : editremainderDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		RemainderMasterVO remain = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_REMAINDER);
			pstmt.setString(1,vo.getId());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				remain = new RemainderMasterVO();
				remain.setId(rs.getString("remainder_id").trim());
				remain.setName(rs.getString("remainder_title").trim());
				remain.setDescription(rs.getString("description").trim());
				remain.setRemtype(rs.getString("remainder_to").trim());
				
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : editremainderDetails Ending");
		return remain;
	}



	
	public synchronized boolean geteditcount(RemainderMasterVO vo) 
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : geteditcount Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		try {

		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_REMAINDER_COUNT);
		pstmt.setString(1, vo.getId().trim());
		pstmt.setString(2, vo.getName().trim());
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


	

	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : geteditcount Ending");

	return result;

	
}



	
	public synchronized String updateremainderdata(RemainderMasterVO vo) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : updateremainderdata Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATE_REMAINDER_DETAILS);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getDescription());
			pstmt.setString(3, vo.getRemtype());
			
			if(vo.getRemtype().equalsIgnoreCase("All"))
			{
				
				pstmt.setString(4, "Y");
				pstmt.setString(5, "Y");
				pstmt.setString(6, "Y");
			}
			
			else if(vo.getRemtype().equalsIgnoreCase("Teachers"))
			{
				pstmt.setString(4, "Y");
				pstmt.setString(5, "N");
				pstmt.setString(6, "N");
			}
			else if(vo.getRemtype().equalsIgnoreCase("Parents"))
			{
				pstmt.setString(4, "N");
				pstmt.setString(5, "Y");
				pstmt.setString(6, "N");
			}
			else if(vo.getRemtype().equalsIgnoreCase("Backoffice"))
			{
				pstmt.setString(4, "N");
				pstmt.setString(5, "N");
				pstmt.setString(6, "Y");
			}
			
			pstmt.setString(7, vo.getCreateuser());
			pstmt.setTimestamp(8,HelperClass.getCurrentTimestamp());
			pstmt.setString(9, vo.getId());

			
			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				result_Status = MessageConstants.SuccessremainderUpMsg;
			} else {
				result_Status = MessageConstants.ErrorremainderUpMsg;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : updateremainderdata Ending");
		return result_Status;
		
		
	}



	
	public synchronized String deleteRemainderDetails(RemainderMasterVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : deleteRemainderDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String status="";
		int no=0;

		Connection conn = null;

		try
		
		{
		
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_REMAINDER);
			pstmt.setString(1, vo.getId());
			no = pstmt.executeUpdate();
			if(no>0)
			{
				
				status=MessageConstants.REM_SUCCESS;
				
			}
			
			else
			{
				status=MessageConstants.REM_ERROR;
			}
		
		}
		
		catch (SQLException sqlExp) {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : deleteRemainderDetails Ending");
		
		return status;
	}



	
	public synchronized ArrayList<RemainderMasterVO> searchdetails(RemainderMasterVO vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterDAOIMPL : searchdetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<RemainderMasterVO> remainderdetails = new ArrayList<RemainderMasterVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_REMAINDER_DETAILS);
			pstmt.setString(1, "%"+vo.getName()+"%");
			pstmt.setString(2, "%"+vo.getName()+"%");
			
			System.out.println("SEARCH_REMAINDER_DETAILS "+pstmt);
			
			rs = pstmt.executeQuery();
			while (rs.next())

			{

				RemainderMasterVO val = new RemainderMasterVO();
				val.setId(rs.getString("remainder_id"));
				val.setName(rs.getString("remainder_title"));
				val.setDescription(rs.getString("description"));
				val.setRemtype(rs.getString("remainder_to"));
				
				System.out.println("remainder_title "+rs.getString("remainder_title"));
				remainderdetails.add(val);

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
		
	
		return remainderdetails;
	}

}
