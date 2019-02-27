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

import com.centris.campus.dao.ReligionCasteCasteCategoryDao;
import com.centris.campus.dao.StreamDetailsDao;
import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReligionCasteCasteCategoryUtils;
import com.centris.campus.util.StreamSqlUtils;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.itextpdf.text.log.SysoLogger;

public class ReligionCasteCasteCategoryDaoImpl implements ReligionCasteCasteCategoryDao {
	private static final Logger logger = Logger
			.getLogger(ReligionCasteCasteCategoryDaoImpl.class);

	
	public int insertReligion(ReligionCasteCasteCategoryPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertReligion  Starting");

		
		String religion = detailsPojo.getReligion();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_RELIGION);

			pstmt.setString(1,
					IDGenerator.getPrimaryKeyID("campus_religion"));

			pstmt.setString(2, religion.trim());
			
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			

			rs = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		finally {

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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertReligion  Ending");

		
		return result;

	}

	
	public int updateReligion(ReligionCasteCasteCategoryPojo detailsPojo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Starting");
		
		
		String religionId = detailsPojo.getReligionId();
		String religion = detailsPojo.getReligion();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_RELIGION);

			pstmt.setString(1, religion.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, religionId.trim());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Ending");
		
		
		
		return result;
	}
	
	
	public int validateReligion(ReligionCasteCasteCategoryPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateReligion Starting");

		int religionName_validate = 0;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_RELIGION_NAME);

			psCheckReligion.setString(1, detailsPojo.getReligion().trim());

			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {

				count = rsCheckReligion.getInt(1);

			}
			if (count > 0) {

				religionName_validate = 1;

			} else {

				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateReligion  Ending");

		return religionName_validate;
	}


	
	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchReligion Starting");

		ArrayList<ReligionCasteCasteCategoryVo> religionList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_RELIGION_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setSno(String.valueOf(sno));
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));

				religionList.add(religionVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchReligion  Ending");

		return religionList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getReligionDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails Starting");
		List<ReligionCasteCasteCategoryVo> religionList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.RELGION_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				System.out.println("DIOMPL: "+rs.getString("religionId"));
				System.out.println("DIOMPL: relgion: "+rs.getString("religion"));
				religionList.add(religionVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails  Ending");
		return religionList;
	
	}


	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_RELIGION);

			pstmt.setString(1, detailsPojo.getReligionId());

			rs = pstmt.executeQuery();
			System.out.println("DIOMPL: GET_SINGLE_RELIGION: "+pstmt);
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				System.out.println(""+rs.getString("religionId"));
				System.out.println(""+rs.getString("religion"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion  Ending");

		return religionVo;
	
	}


	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteReligion Starting");

		String result = null;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			
			for(int i=0;i<detailsPojo.getReligionIdArray().length;i++){
				conn = JDBCConnection.getSeparateConnection();
				
				psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.DELETE_RELIGION);
				psCheckReligion.setString(1, detailsPojo.getReligionIdArray()[i].trim());
				
				System.out.println("delete religion: "+psCheckReligion);
				count = psCheckReligion.executeUpdate();
				
				if (count > 0) {

					result = "Selected Religion Deleted";
					System.out.println("Deleted");

				} else {

					result = "Selected Religion Not Deleted";
				}
				
				
				
			}
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteReligion  Ending");

		return result;
	
	}


	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste Starting");

		ArrayList<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_CASTE_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setSno(String.valueOf(sno));
				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));		
				religionVo.setReligion(rs.getString("religion"));

				casteList.add(religionVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste  Ending");

		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTCASTE_DETAILS);
			//pstmt.setString(1, religionId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteId(rs.getString("casteId"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				casteList.add(casteVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails  Ending");
		return casteList;
	
	}


	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCaste Starting");

		String result = null;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			for(int i=0;i<detailsPojo.getCasteIdArray().length;i++){
				conn = JDBCConnection.getSeparateConnection();
				
				psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.DELETE_CASTE);

				psCheckReligion.setString(1, detailsPojo.getCasteIdArray()[i].trim());

				count = psCheckReligion.executeUpdate();
				
				System.out.println("delete religion: "+psCheckReligion);
				
				if (count > 0) {

					result = "Selected Caste Deleted";
					System.out.println("Deleted");

				} else {

					result = "Selected Caste Not Deleted";
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCaste  Ending");

		return result;
	
	
	}


	public int validateCaste(ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCaste Starting");

		int religionName_validate = 0;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_CASTE_NAME);

			psCheckReligion.setString(1, detailsPojo.getCaste().trim());
			psCheckReligion.setString(2, detailsPojo.getMain_religion().trim());
			System.out.println("duplicate checking is "+psCheckReligion);
			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {

				count = rsCheckReligion.getInt(1);

			}
			if (count > 0) {

				religionName_validate = 1;

			} else {

				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCaste  Ending");

		return religionName_validate;
	
	}


	public int insertCaste(ReligionCasteCasteCategoryPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCaste  Starting");

		
		String caste = detailsPojo.getCaste();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_CASTE);

			pstmt.setString(1,
					IDGenerator.getPrimaryKeyID("campus_caste"));

			pstmt.setString(2, caste.trim());
			
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			pstmt.setString(5,detailsPojo.getMain_religion());
			

			rs = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCaste  Ending");
		
		return result;

	}


	public int updateCaste(ReligionCasteCasteCategoryPojo detailsPojo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Starting");
		
		
		String casteId = detailsPojo.getCasteId();
		String caste = detailsPojo.getCaste();
		System.out.println("DIompl casteId: "+casteId);
		System.out.println("DIompl caste: "+caste);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_CASTE);

			pstmt.setString(1, caste.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, detailsPojo.getMain_religion());
			pstmt.setString(5, casteId.trim());
			
			
			System.out.println(pstmt);

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Ending");
		
		
		
		return result;
	}


	public ReligionCasteCasteCategoryVo getSingleCaste(
			ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCaste Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_CASTE);

			pstmt.setString(1, detailsPojo.getCasteId());

			rs = pstmt.executeQuery();
			System.out.println("DIOMPL: getSingleCaste: "+pstmt);
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religionId"));
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCaste  Ending");

		return religionVo;
	
	}


	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(
			String searchName) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste Starting");

		ArrayList<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_CASTE_CATEGORY_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setSno(String.valueOf(sno));
				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religion"));

				casteList.add(religionVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste  Ending");

		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.CASTE_CATEGORY_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//a.castCatId,a.casteCategory,b.caste,c.religion
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteCategoryId(rs.getString("castCatId"));
				casteVo.setCasteCategory(rs.getString("casteCategory"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				
				casteList.add(casteVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails  Ending");
		return casteList;
	
	
	}


	public int validateCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCasteCategory Starting");

		int religionName_validate = 0;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_CASTE_CATEGORY_NAME);

			psCheckReligion.setString(1, detailsPojo.getCasteCategory().trim());
			psCheckReligion.setString(2, detailsPojo.getReligionId().trim());
			psCheckReligion.setString(3, detailsPojo.getCasteId().trim());
			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {

				count = rsCheckReligion.getInt(1);

			}
			if (count > 0) {

				religionName_validate = 1;

			} else {

				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCasteCategory  Ending");

		return religionName_validate;
	
	
		
	}


	public int insertCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCasteCategory  Starting");

		
		//String casteCategory = detailsPojo.getCasteCategory();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_CASTE_CATEGORY);
//castCatId,casteCategory,religionId,casteId,createdBy,createdTime
			pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_caste_category"));

			pstmt.setString(2, detailsPojo.getCasteCategory());
			pstmt.setString(3, detailsPojo.getReligionId());
			pstmt.setString(4, detailsPojo.getCasteId());
			pstmt.setString(5, detailsPojo.getCreateUser());
			pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
			

			rs = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCasteCategory  Ending");
		
		return result;

	
	}


	public int updateCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCasteCategory  Starting");
		
		
		String casteCategoryId = detailsPojo.getCasteCategoryId();
		String casteCategory = detailsPojo.getCasteCategory();
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_CASTE_CATEGORY);
			pstmt.setString(1, casteCategory.trim());
			pstmt.setString(2, detailsPojo.getReligionId());
			pstmt.setString(3, detailsPojo.getCasteId());
			pstmt.setString(4, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(5,  HelperClass.getCurrentTimestamp());
			pstmt.setString(6, casteCategoryId.trim());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCasteCategory  Ending");
		
		
		
		return result;
	
	}


	public String delteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {




		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCasteCategory Starting");

		String result = null;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			
			for(int i=0;i<detailsPojo.getCasteCategoryIdArray().length;i++){
				conn = JDBCConnection.getSeparateConnection();
				
				psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.DELETE_CASTE_CATEGORY);

				psCheckReligion.setString(1, detailsPojo.getCasteCategoryIdArray()[i].trim());

				count = psCheckReligion.executeUpdate();
				
				
				
				if (count > 0) {

					result = "Selected Caste Category Deleted";
					System.out.println("Deleted");

				} else {

					result = "Selected Caste Category Not Deleted";
				}
			}
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {
			try {

				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCasteCategory  Ending");

		return result;
	
	
	
	}


	public ReligionCasteCasteCategoryVo getSingleCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCasteCategory Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_CASTE_CATEGORY);

			pstmt.setString(1, detailsPojo.getCasteCategoryId());

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religion"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCaste  Ending");

		return religionVo;
	
	
	}


	public int insertOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertOccupation  Starting");

		
		String occupation = detailsPojo.getOccupation();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_OCCUPATION);

			pstmt.setString(1,
					IDGenerator.getPrimaryKeyID("campus_occupation"));

			pstmt.setString(2, occupation.trim());
			
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			

			rs = pstmt.executeUpdate();
			System.out.println("insert Occupation: "+pstmt);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		 finally {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertOccupation  Ending");

		
		return result;

	}


	public int validateOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateOccupation Starting");

		int occupationName_validate = 0;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_OCCUPATION_NAME);

			psCheckReligion.setString(1, detailsPojo.getOccupation().trim());

			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {

				count = rsCheckReligion.getInt(1);

			}
			if (count > 0) {

				occupationName_validate = 1;

			} else {

				occupationName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateOccupation  Ending");

		return occupationName_validate;
	
	}


	
	public int updateOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {

		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateOccupation  Starting");
		
		
		String occupationId = detailsPojo.getOccupationId();
		String occupation = detailsPojo.getOccupation();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_OCCUPATION);

			pstmt.setString(1, occupation.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, occupationId.trim());
			
			result = pstmt.executeUpdate();
			System.out.println("update Occupation: "+pstmt);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateOccupation  Ending");
		
		
		
		return result;
	
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationDetails() {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails Starting");
		List<ReligionCasteCasteCategoryVo> occupationList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.OCCUPATION_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo occupationVo = new ReligionCasteCasteCategoryVo();
				occupationVo.setOccupationId(rs.getString("occupationId"));
				occupationVo.setOccupation(rs.getString("occupation"));
				occupationList.add(occupationVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails  Ending");
		return occupationList;
	
	
	}


	public ReligionCasteCasteCategoryVo getSingleOccupation(
			ReligionCasteCasteCategoryPojo detailsPojo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_OCCUPATION);

			pstmt.setString(1, detailsPojo.getOccupationId());

			rs = pstmt.executeQuery();
			System.out.println("DIOMPL: getOccupationId: "+pstmt);
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setOccupationId(rs.getString("occupationId"));
				religionVo.setOccupation(rs.getString("occupation"));
				System.out.println(""+rs.getString("occupationId"));
				System.out.println(""+rs.getString("occupation"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion  Ending");

		return religionVo;
	
	
	}


	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteOccupation Starting");

		String result = null;

		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			
		  for(int i=0;i<detailsPojo.getOccupationIdArray().length;i++){
			  conn = JDBCConnection.getSeparateConnection();
				
				psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.DELETE_OCCUPATION);

				psCheckReligion.setString(1, detailsPojo.getOccupationIdArray()[i].trim());

				count = psCheckReligion.executeUpdate();
				
				System.out.println("delete religion: "+psCheckReligion);
				
				if (count > 0) {

					result = "Selected Occupation Deleted";
					System.out.println("Deleted");

				} else {

					result = "Selected Occupation Not Deleted";
				}
		  }
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rsCheckReligion != null && !rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && !psCheckReligion.isClosed()) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteOccupation  Ending");

		return result;
	
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.CASTE_DETAILS);
			pstmt.setString(1, religionId.trim());
			System.out.println("caste details is "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteId(rs.getString("casteId"));
				casteVo.setCaste(rs.getString("caste"));
				casteList.add(casteVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails  Ending");
		return casteList;
	
	}
	
	public List<ReligionCasteCasteCategoryVo> getListOfCaste() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTOF_CASTE_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setCasteId("casteId");
				religionVo.setCaste(rs.getString("caste"));
				
				casteList.add(religionVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails  Ending");
		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteCategoryList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SINGLE_CASTECATOGRY_DETAILS);
			pstmt.setString(1, casteId.trim());
			System.out.println("caste category query "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				
				casteCategoryList.add(religionVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails  Ending");
		return casteCategoryList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
			String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails Starting");
		List<ReligionCasteCasteCategoryVo> occupationList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_OCCUPATION_DETAILS);
			pstmt.setString(1, "%"+searchName+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo occupationVo = new ReligionCasteCasteCategoryVo();
				occupationVo.setOccupationId(rs.getString("occupationId"));
				occupationVo.setOccupation(rs.getString("occupation"));
				System.out.println("DIOMPL: "+rs.getString("occupationId"));
				System.out.println("DIOMPL: occupation: "+rs.getString("occupation"));
				occupationList.add(occupationVo);

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails  Ending");
		return occupationList;
	}
}