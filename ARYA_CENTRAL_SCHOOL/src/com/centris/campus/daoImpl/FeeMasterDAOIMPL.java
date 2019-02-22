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

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.FeeMasterDAO;
import com.centris.campus.forms.addfeedetailsform;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.feeReportVO;

public class FeeMasterDAOIMPL implements FeeMasterDAO {

	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public synchronized String insertFeeDetails(AddFeeVO vo)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : insertFeeDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

	

		try {

				conn = JDBCConnection.getSeparateGodaddyConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_DETAILS);
				pstmt.setString(1,
						IDGenerator.getPrimaryKeyID("campus_fee_master",conn));
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getDescription());
				pstmt.setString(4, vo.getCreatedby());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6,vo.getConcessiontype());
				pstmt.setString(7,vo.getFeeTypeId().trim());
				pstmt.setString(8,vo.getAcademicYear().trim());
				pstmt.setString(9,vo.getLocationId().trim());
				
				System.out.println("pstmt"+pstmt);

				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.SuccessMsg;
				} else {
					result_Status = MessageConstants.ErrorMsg;
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
				+ " Control in FeeMasterDAOIMPL : insertFeeDetails Ending");

		
		return result_Status;

	}
	
	
	public synchronized String updateFeeDetails(AddFeeVO vo)
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : updateFeeDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_FEE_DETAILS);

			pstmt.setString(1, vo.getName().trim());
			pstmt.setString(2, vo.getDescription().trim());
			pstmt.setString(3, vo.getCreatedby().trim());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			pstmt.setString(5, vo.getConcessiontype());
			pstmt.setString(6, vo.getFeeTypeId());
			pstmt.setString(7, vo.getId().trim());

			System.out.println("pstmtupdate"+pstmt);

			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				result_Status = MessageConstants.SuccessUpMsg;
			} else {
				result_Status = MessageConstants.ErrorUpMsg;
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
				+ " Control in FeeMasterDAOIMPL : updateFeeDetails Ending");
		return result_Status;
		
		
	}
	
	

	public synchronized int getFeeNameCheckDao(String feeName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeNameCheckDao Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_COUNT);

			pstmt.setString(1, feeName);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
				+ " Control in FeeMasterDAOIMPL : getFeeNameCheckDao Ending");
		return count;
	}

	public synchronized boolean getnamecount(AddFeeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getId().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_COUNT);
				pstmt.setString(1, vo.getName().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
				
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

		if (!vo.getId().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_COUNT);
				pstmt.setString(1, vo.getId().trim());
				pstmt.setString(2, vo.getName().trim());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getAcademicYear());
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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");
		return result;

	}

	public synchronized ArrayList<AddFeeVO> getfeedetails(AddFeeVO val) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getfeedetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<AddFeeVO> feedetails = new ArrayList<AddFeeVO>();
		
		if (val.getName()==null || val.getName().equalsIgnoreCase("") || val.getName().equalsIgnoreCase("all") )
			
		{
		
			try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_DETAILS);
			pstmt.setString(1, val.getAcademicYear());
			pstmt.setString(2, val.getLocationId());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next())

			{

				AddFeeVO vo = new AddFeeVO();

				vo.setId(rs.getString("FeeCode").trim());
				vo.setName(rs.getString("FeeName").trim());
				vo.setConcession(rs.getString("IsConcession"));
				vo.setDescription(rs.getString("description").trim());
				vo.setFeeType(rs.getString("feeType").trim());
				vo.setFeeTypeId(rs.getString("feeTypeId").trim());
				vo.setAcademicYearName(rs.getString("acadamic_year"));
				vo.setLocationName(rs.getString("Location_Name"));
				feedetails.add(vo);

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
		
		else if (!val.getName().equalsIgnoreCase(""))
		
		{
			
			
			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_FEE_DETAILS);
				pstmt.setString(1, "%"+val.getName().trim()+"%");
				pstmt.setString(2, "%"+val.getName().trim()+"%");
				pstmt.setString(3, "%"+val.getName().trim()+"%");
				pstmt.setString(4, "%"+val.getName().trim()+"%");
				pstmt.setString(5, "%"+val.getName().trim()+"%");
				pstmt.setString(6, val.getAcademicYear());
				pstmt.setString(7, val.getLocationId());
				System.out.println("search fee type: "+pstmt);
				
				
				rs = pstmt.executeQuery();
				while (rs.next())

				{

					AddFeeVO vo = new AddFeeVO();

					vo.setId(rs.getString("FeeCode").trim());
					vo.setName(rs.getString("FeeName").trim());
					vo.setDescription(rs.getString("description").trim());
					vo.setConcession(rs.getString("IsConcession"));
					vo.setFeeType(rs.getString("feeType"));
					vo.setFeeTypeId(rs.getString("feeTypeId"));
					feedetails.add(vo);

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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");

		return feedetails;
	}

	public synchronized AddFeeVO editFeeDetails(AddFeeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : editFeeDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		AddFeeVO fee = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_EDIT_DETAILS);

			pstmt.setString(1, vo.getId());

			rs = pstmt.executeQuery();
			
			System.out.println("FeeEdit: ");
			while (rs.next())

			{
				fee = new AddFeeVO();
				fee.setId(rs.getString("FeeCode"));
				fee.setName(rs.getString("FeeName"));
				fee.setConcession(rs.getString("IsConcession"));
				fee.setDescription(rs.getString("description"));
				fee.setFeeTypeId(rs.getString("feeTypeId"));
				fee.setFeeType(rs.getString("feeType"));
				fee.setLocationId(rs.getString("locationId"));
				fee.setAcademicYear(rs.getString("academicyear"));
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
				+ " Control in FeeMasterDAOIMPL : editFeeDetails Ending");

		return fee;
	}

	public synchronized boolean deleteFeeDetails(AddFeeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : deleteFeeDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		int no=0;
		Connection conn = null;
		boolean result = false;

		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<vo.getGetDataArray().length;i++){//-------6
				PreparedStatement pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM campus_fee_setupdetails WHERE feecode=?");
				pstmt1.setString(1, vo.getGetDataArray()[i]);
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next()){
					no=rs1.getInt(1);
				}
				
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_FEE_DETAILS);
			pstmt.setString(1, vo.getGetDataArray()[i]);//---------7
			
			if(no==0)
			count = pstmt.executeUpdate();
			//-------8
			if (count > 0)

			{
				result = true;
			}
			else{
				result=false;
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
				+ " Control in FeeMasterDAOIMPL : editFeeDetails Ending");

		return result;

	}

	
	public synchronized ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		ArrayList<AddFeeVO> feedetails = new ArrayList<AddFeeVO>();
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_FEE_DETAILS);
			pstmt.setString(1, "%"+vo.getName()+"%");
			
			rs = pstmt.executeQuery();
			while (rs.next())

			{

				AddFeeVO val = new AddFeeVO();

				val.setId(rs.getString("FeeCode").trim());
				val.setName(rs.getString("FeeName").trim());
				val.setDescription(rs.getString("description").trim());
				feedetails.add(val);

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
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Ending");

		return feedetails;
	
		
	}
	public List<ReportMenuVo> getclasslistDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		
		List<ReportMenuVo> classlist = new ArrayList<ReportMenuVo>();
		
		
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_CLASS_DETAILS);
			System.out.println("pstmt "+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo  vo = new ReportMenuVo();
				
				vo.setClassId(rs.getString("classdetail_id_int").trim());
				vo.setClassname(rs.getString("classdetails_name_var").trim());
				classlist.add(vo);
			}
			
			
			
			
			
		}catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
	
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Ending");
		
		return classlist;
	}



	public ArrayList<AddFeeVO> feeTypeListDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getfeedetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<AddFeeVO> feeTypeList = new ArrayList<AddFeeVO>();
		

		
		try {

		conn = JDBCConnection.getSeparateGodaddyConnection();
		pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_TPYE_LIST);

		rs = pstmt.executeQuery();
		while (rs.next())

		{

			AddFeeVO vo = new AddFeeVO();

			vo.setFeeTypeId(rs.getString("feeTypeId").trim());
			vo.setFeeType(rs.getString("feeType").trim());
			System.out.println(vo.getFeeTypeId() +" "+ vo.getFeeType());
			feeTypeList.add(vo);

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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");

		return feeTypeList;
	}


	public boolean getFeeTypeCount(AddFeeVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeCount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getFeeTypeId().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_TYPE_COUNT);
				pstmt.setString(1, vo.getFeeType().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
				
				System.out.println("feeType:  "+pstmt);
				System.out.println(pstmt);
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

		if (!vo.getFeeTypeId().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_COUNT);
				pstmt.setString(1, vo.getFeeTypeId().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");
		return result;

	}


	public String saveFineConfiguration(String[] accyearArray, String[] termArray,String[] locationArray, String[] classArray,String[] days, String[] amountArray, String isApplicable, String userCode, String accyear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : saveFineConfiguration Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		int count1 = 0;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String result =null;
		
			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmt1=conn.prepareStatement("DELETE FROM campus_fineconfiguration where accyear=?");
				pstmt1.setString(1, accyear);
				count1=pstmt1.executeUpdate();
				
				
				for(int i=0;i<days.length;i++){
				pstmt = conn.prepareStatement("INSERT INTO campus_fineconfiguration (locationId,classId,date,amount,createdBy,IsApplicable,accyear,termid) VALUES(?,?,?,?,?,?,?,?)");
				pstmt.setString(1, locationArray[i]);
				pstmt.setString(2, classArray[i]);
				pstmt.setString(3, HelperClass.convertUIToDatabase(days[i]));
				pstmt.setString(4, amountArray[i]);
				pstmt.setString(5, userCode);
				pstmt.setString(6, isApplicable);
				pstmt.setString(7, accyearArray[i]);
				pstmt.setString(8, termArray[i]);
				System.out.println("feeType:  "+pstmt);
				count = pstmt.executeUpdate();
				

				if (count > 0)

				{
					result = "true";
				}
				else{
					result= "false";
				}
				}
				
				pstmt1.close();
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
				+ " Control in FeeMasterDAOIMPL : saveFineConfiguration Ending");
		return result;
	}


	public List<feeReportVO> getFineConfiguration(String acyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getfeedetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<feeReportVO> feeTypeList = new ArrayList<feeReportVO>();
		

		
		try {

		conn = JDBCConnection.getSeparateGodaddyConnection();
		pstmt = conn.prepareStatement("SELECT * FROM campus_fineconfiguration WHERE accyear=?");
		pstmt.setString(1, acyear);
		rs = pstmt.executeQuery();
		while (rs.next())

		{

			feeReportVO vo = new feeReportVO();
			vo.setAccyearid(rs.getString(2));
			vo.setLocationid(rs.getString(3));
			vo.setClassnameid(rs.getString(4));
			vo.setTermId(rs.getString(5));
			vo.setFineDate(HelperClass.convertDatabaseToUI(rs.getString(6)));
			vo.setFine(rs.getDouble(7));
			vo.setIsApplicable(rs.getString(10));
			feeTypeList.add(vo);

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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");

		return feeTypeList;
	}
}
