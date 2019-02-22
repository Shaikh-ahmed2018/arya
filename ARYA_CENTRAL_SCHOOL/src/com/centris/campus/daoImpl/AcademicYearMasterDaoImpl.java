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

import com.centris.campus.dao.AcademicYearMasterDao;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.util.AcademicYearSQLUtilConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcademicYearVO;

public class AcademicYearMasterDaoImpl implements AcademicYearMasterDao {
	private static final Logger logger = Logger
			.getLogger(AcademicYearMasterDaoImpl.class);

	public synchronized ArrayList<AcademicYearVO> getAcademicYearDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetails  Starting");
		ArrayList<AcademicYearVO> academicYearDetails = new ArrayList<AcademicYearVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sno = 0;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ALL_ACADEMICYEAR);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sno++;

				AcademicYearVO academicYearVO = new AcademicYearVO();

				academicYearVO.setSno(String.valueOf(sno));
				academicYearVO.setAcadamic_id(rs.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(rs.getString("acadamic_year"));
				academicYearVO.setStartDate(HelperClass.convertDatabaseToUI(rs
						.getString("startDate")));
				academicYearVO.setEndDate(HelperClass.convertDatabaseToUI(rs
						.getString("endDate")));
				academicYearVO.setDescription(rs.getString("description"));

				academicYearDetails.add(academicYearVO);
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
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetails  Starting");

		return academicYearDetails;
	}

	public synchronized String createAcademicYear(AcademicYearPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : createAcademicYear  Starting");
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		Connection conn1 = null;
		PreparedStatement pstmt1 = null;

		ResultSet rs = null;

		int count = 0;
		int count1 = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			if ("NULL".equalsIgnoreCase(pojo.getStatus())) {
				conn1 = JDBCConnection.getSeparateConnection();
				pstmt1 = conn1
						.prepareStatement(AcademicYearSQLUtilConstants.CHECK_ACADEMICYEAR_NAME);

				pstmt1.setString(1, pojo.getAcadamic_name());
				pstmt1.setString(2,
						HelperClass.convertUIToDatabase(pojo.getStartDate()));
				pstmt1.setString(3,
						HelperClass.convertUIToDatabase(pojo.getEndDate()));
				rs = pstmt1.executeQuery();

				while (rs.next()) {
					count1 = rs.getInt("Acdname");
				}
				if (count1 == 0) {
					pstmt = conn
							.prepareStatement(AcademicYearSQLUtilConstants.CREATE_ACADEMIC_YEAR);
					pstmt.setString(1, pojo.getAcadamic_id());
					pstmt.setString(2, pojo.getAcadamic_name());
					pstmt.setString(3, HelperClass.convertUIToDatabase(pojo
							.getStartDate()));
					pstmt.setString(4,
							HelperClass.convertUIToDatabase(pojo.getEndDate()));
					pstmt.setString(5, pojo.getDescription());
					pstmt.setString(6, pojo.getCreateuser());
					count = pstmt.executeUpdate();

					if (count > 0) {
						status = MessageConstants.SUCCESS;
					} else {
						status = MessageConstants.FAILD;
					}
				}

			} else {
				pstmt = conn
						.prepareStatement(AcademicYearSQLUtilConstants.UPDATE_ACADEMIC_YEAR);

				pstmt.setString(1, pojo.getAcadamic_name());
				pstmt.setString(2,
						HelperClass.convertUIToDatabase(pojo.getStartDate()));
				pstmt.setString(3,
						HelperClass.convertUIToDatabase(pojo.getEndDate()));
				pstmt.setString(4, pojo.getDescription());
				pstmt.setString(5, pojo.getCreateuser());
				pstmt.setString(6, pojo.getAcadamic_id());
				count = pstmt.executeUpdate();

				if (count > 0) {
					status = MessageConstants.SUCCESS;
				} else {
					status = MessageConstants.FAILD;
				}
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
				+ " Control in AcademicYearMasterDaoImpl : createAcademicYear  Starting");
		return status;
	}

	public synchronized boolean deleteAcademicYear(String ACY_code) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : deleteAcademicYear  Starting");
		boolean status = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		int count = 0;
		int count1 = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_STUDENT);
			pstmt.setString(1, ACY_code);
			
			
			
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				count1 = rs.getInt("academicyear");
				
				
			}
			if (count1 == 0) {
				pstmt = conn
						.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_ASSINGMENT);
				pstmt.setString(1, ACY_code);
				
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					count1 = rs.getInt("academicyear");
					
					
					
				}
				if (count1 == 0) {
					pstmt = conn
							.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_EXAMINATION);
					pstmt.setString(1, ACY_code);
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						count1 = rs.getInt("academicyear");
						
					}
					if (count1 == 0) {
						pstmt = conn
								.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_FEECOLLECTION);
						pstmt.setString(1, ACY_code);
						
						rs = pstmt.executeQuery();
						while (rs.next()) {
							count1 = rs.getInt("academicyear");
							
						}
						if (count1 == 0) {
							pstmt = conn
									.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_FEESETUP);
							pstmt.setString(1, ACY_code);
							
							rs = pstmt.executeQuery();
							while (rs.next()) {
								count1 = rs.getInt("academicyear");
								
							}
							if (count1 == 0) {
								pstmt = conn
										.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_STAGESETUP);
								pstmt.setString(1, ACY_code);
								
								
								rs = pstmt.executeQuery();
								while (rs.next()) {
									count1 = rs.getInt("academicyear");
									
								}
								if (count1 == 0) {
									pstmt = conn
											.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_TERMDETAILS);
									pstmt.setString(1, ACY_code);
									rs = pstmt.executeQuery();
									while (rs.next()) {
										count1 = rs.getInt("academicyear");
										
									}
									if (count1 == 0) {
										pstmt = conn
												.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_PRIMARY);
										pstmt.setString(1, ACY_code);
										rs = pstmt.executeQuery();
										while (rs.next()) {
											count1 = rs.getInt("academicyear");
											
										}
										if (count1 == 0) {
											pstmt = conn
													.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_SECONDARY);
											pstmt.setString(1, ACY_code);
											rs = pstmt.executeQuery();
											while (rs.next()) {
												count1 = rs
														.getInt("academicyear");
												
												
												
											}
											if (count1 == 0) {
												pstmt = conn
														.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_ENQUIRY);
												pstmt.setString(1, ACY_code);
												rs = pstmt.executeQuery();
												while (rs.next()) {
													count1 = rs
															.getInt("academicyear");
													
													
												}
												if (count1 == 0) {
													pstmt = conn
															.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_TIMETABLE_STUDENT);
													pstmt.setString(1, ACY_code);
													rs = pstmt.executeQuery();
													while (rs.next()) {
														count1 = rs
																.getInt("academicyear");
														
														
													}
													if (count1 == 0) {
														pstmt = conn
																.prepareStatement(AcademicYearSQLUtilConstants.ACADEMIC_YEAR_CHECK_TIMETABLE_TEACHER);
														pstmt.setString(1,
																ACY_code);
														rs = pstmt
																.executeQuery();
														while (rs.next()) {
															count1 = rs
																	.getInt("academicyear");
															
															
															
														}
													}
												}
											}

										}
									}
								}
							}
						}
					}
				}
			}

			if (count1 == 0) {
				pstmt = conn
						.prepareStatement(AcademicYearSQLUtilConstants.DELETE_ACADEMIC_YEAR);

				pstmt.setString(1, ACY_code);
				count = pstmt.executeUpdate();
				if (count > 0) {
					status = true;
				}

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
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in AcademicYearMasterDaoImpl : deleteAcademicYear  Starting");
		return status;
	}

	public synchronized ArrayList<AcademicYearVO> editAcademicYear(
			String ACY_code) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : editAcademicYear  Starting");
		ArrayList<AcademicYearVO> academicYearlist = new ArrayList<AcademicYearVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.EDIT_ACADEMICYEAR);
			pstmt.setString(1, ACY_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AcademicYearVO academicYearVO = new AcademicYearVO();
				academicYearVO.setAcadamic_id(rs.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(rs.getString("acadamic_year"));
				academicYearVO.setStartDate(HelperClass.convertDatabaseToUI(rs
						.getString("startDate")));
				academicYearVO.setEndDate(HelperClass.convertDatabaseToUI(rs
						.getString("endDate")));
				academicYearVO.setDescription(rs.getString("description"));
				academicYearlist.add(academicYearVO);
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
				+ " Control in AcademicYearMasterDaoImpl : editAcademicYear  Starting");
		return academicYearlist;
	}

	public synchronized String accyearNameCheck(AcademicYearPojo ACY_pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : accyearNameCheck  Starting");
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;
		int count1 = 0;
		try {

			if ("NULL".equalsIgnoreCase(ACY_pojo.getAcadamic_id())) {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(AcademicYearSQLUtilConstants.CHECK_ACADEMICYEAR_NAME);

				pstmt.setString(1, ACY_pojo.getAcadamic_name());
				pstmt.setString(2, HelperClass.convertUIToDatabase(ACY_pojo
						.getStartDate()));
				pstmt.setString(3, HelperClass.convertUIToDatabase(ACY_pojo.getEndDate()));
				
				
				
				
				
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					count = rs.getInt("Acdname");
				}

				if (count > 0) {
					status = "Academic Year already Exist";
				} else {
					conn1 = JDBCConnection.getSeparateConnection();

					pstmt1 = conn1
							.prepareStatement(AcademicYearSQLUtilConstants.CHECK_ACADEMICYEAR_DATES);
					pstmt1.setString(1, HelperClass
							.convertUIToDatabase(ACY_pojo.getStartDate()));
					pstmt1.setString(2, HelperClass
							.convertUIToDatabase(ACY_pojo.getEndDate()));
					
					
					 
					
					
					rs1 = pstmt1.executeQuery();

					while (rs1.next()) {
						count1 = rs1.getInt("datescount");
					}
					if (count1 > 0) {
						status = "Academic year already created using this dates";
					}
				}
			} else {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(AcademicYearSQLUtilConstants.CHECK_ACADEMICYEAR_NAME_WHILE_UPATE);

				pstmt.setString(1, ACY_pojo.getAcadamic_name());
				pstmt.setString(2, HelperClass.convertUIToDatabase(ACY_pojo
						.getStartDate()));
				pstmt.setString(3,
						HelperClass.convertUIToDatabase(ACY_pojo.getEndDate()));
				pstmt.setString(4, ACY_pojo.getAcadamic_id().trim());
				
				
				
				
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					count = rs.getInt("Acdname");
				}

				if (count > 0) {
					status = "Academic Year already Exist";
				} else {
					conn1 = JDBCConnection.getSeparateConnection();

					pstmt1 = conn1
							.prepareStatement(AcademicYearSQLUtilConstants.CHECK_ACADEMICYEAR_DATES_WHILE_UPATE);
					pstmt1.setString(1, HelperClass
							.convertUIToDatabase(ACY_pojo.getStartDate()));
					pstmt1.setString(2, HelperClass
							.convertUIToDatabase(ACY_pojo.getEndDate()));
					pstmt1.setString(3, ACY_pojo.getAcadamic_id().trim());
					
					
					
					
					rs1 = pstmt1.executeQuery();

					while (rs1.next()) {
						count1 = rs1.getInt("datescount");
					}
					if (count1 > 0) {
						status = "Academic year already created using this dates";
					}
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn1 != null && (!conn1.isClosed())) {
					conn1.close();
				}
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
				+ " Control in AcademicYearMasterDaoImpl : accyearNameCheck  Starting");

		return status;

	}

	public synchronized ArrayList<AcademicYearVO> searchAcademicYearDetails(
			String searchname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : searchAcademicYearDetails  Starting");
		ArrayList<AcademicYearVO> academicYearDetails = new ArrayList<AcademicYearVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sno = 0;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ALL_ACADEMICYEAR_SEARCH);
			pstmt.setString(1, "%" + searchname + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sno++;

				AcademicYearVO academicYearVO = new AcademicYearVO();

				academicYearVO.setSno(String.valueOf(sno));
				academicYearVO.setAcadamic_id(rs.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(rs.getString("acadamic_year"));
				academicYearVO.setStartDate(HelperClass.convertDatabaseToUI(rs
						.getString("startDate")));
				academicYearVO.setEndDate(HelperClass.convertDatabaseToUI(rs
						.getString("endDate")));
				academicYearVO.setDescription(rs.getString("description"));

				academicYearDetails.add(academicYearVO);
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
				+ " Control in AcademicYearMasterDaoImpl : searchAcademicYearDetails  Starting");

		return academicYearDetails;
	}

	@Override
	public synchronized List<AcademicYearVO> getAccYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserDaoImpl : getAcademicYear Starting");
		List<AcademicYearVO> accYearVos = new ArrayList<AcademicYearVO>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			preparedStatement = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ACTIVE_ACC_YEAR);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				AcademicYearVO academicYearVO = new AcademicYearVO();
				academicYearVO.setAcadamic_id(resultSet
						.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(resultSet
						.getString("acadamic_name"));
				accYearVos.add(academicYearVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
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
				+ " Control in UserDaoImpl : getAcademicYear Ending");
		return accYearVos;
	}

	public synchronized ArrayList<AcademicYearPojo> getAcademicYearList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearList  Starting");
		ArrayList<AcademicYearPojo> academicYearDetails = new ArrayList<AcademicYearPojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sno = 0;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ACADEMIC_YEAR_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				AcademicYearPojo obj = new AcademicYearPojo();
				obj.setYear(rs.getString("acadamic_name"));
				obj.setAcadamic_id(rs.getString("acadamic_id"));

				academicYearDetails.add(obj);

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
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearList  Starting");

		return academicYearDetails;

	}

	public synchronized ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetailsByBranchId  Starting");
		ArrayList<AcademicYearVO> academicYearDetails = new ArrayList<AcademicYearVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sno = 0;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ALL_ACADEMICYEAR_BY_BRANCHID);
			pstmt.setString(1, brancid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sno++;

				AcademicYearVO academicYearVO = new AcademicYearVO();

				academicYearVO.setSno(String.valueOf(sno));
				academicYearVO.setAcadamic_id(rs.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(rs.getString("acadamic_name"));
				academicYearVO.setStartDate(HelperClass.convertDatabaseToUI(rs
						.getString("startDate")));
				academicYearVO.setEndDate(HelperClass.convertDatabaseToUI(rs
						.getString("endDate")));
				academicYearVO.setDescription(rs.getString("description"));
				academicYearVO.setBranchname(rs.getString("BranchName"));

				if ("Y".equalsIgnoreCase(rs.getString("isActive"))) {
					academicYearVO.setStatus("Active");
				} else {
					academicYearVO.setStatus("Deactive");
				}

				academicYearDetails.add(academicYearVO);
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
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetailsByBranchId  Starting");

		return academicYearDetails;
	}

	public synchronized ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetailsbysearch  Starting");
		ArrayList<AcademicYearVO> academicYearDetails = new ArrayList<AcademicYearVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int sno = 0;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(AcademicYearSQLUtilConstants.GET_ALL_ACADEMICYEAR_BY_BRANCHID_SEARCH);
			pstmt.setString(1, "%" + searchname + "%");
			pstmt.setString(2, brancid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sno++;

				AcademicYearVO academicYearVO = new AcademicYearVO();

				academicYearVO.setSno(String.valueOf(sno));
				academicYearVO.setAcadamic_id(rs.getString("acadamic_id"));
				academicYearVO.setAcadamic_name(rs.getString("acadamic_name"));
				academicYearVO.setStartDate(HelperClass.convertDatabaseToUI(rs
						.getString("startDate")));
				academicYearVO.setEndDate(HelperClass.convertDatabaseToUI(rs
						.getString("endDate")));
				academicYearVO.setDescription(rs.getString("description"));

				if ("Y".equalsIgnoreCase(rs.getString("isActive"))) {
					academicYearVO.setStatus("Active");
				} else {
					academicYearVO.setStatus("Deactive");
				}

				academicYearDetails.add(academicYearVO);
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
				+ " Control in AcademicYearMasterDaoImpl : getAcademicYearDetailsbysearch  Starting");

		return academicYearDetails;

	}

}
