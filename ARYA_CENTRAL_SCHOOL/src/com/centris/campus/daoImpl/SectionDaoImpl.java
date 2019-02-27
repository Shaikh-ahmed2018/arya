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

import com.centris.campus.dao.SectionDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.util.ClassUtilsConstants;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SectionUtilsConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StudentRegistrationVo;

public class SectionDaoImpl implements SectionDao{
	
	private static final Logger logger = Logger
			.getLogger(SectionDaoImpl.class);
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	java.util.Date today = new java.util.Date();
	java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());

	@Override
	public synchronized String insertCampusClassSectionDao(
			SectionPojo campusClassSectionPojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:insertCampusClassSectionDao  Starting");

		boolean secNameCheck = false;
		String status="";
		String secDetailsId = campusClassSectionPojo.getSecDetailsId();
		String sectionName = campusClassSectionPojo.getSectionName();
		String USER_NAME = campusClassSectionPojo.getCreateUser();
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		SectionDaoImpl impl = new SectionDaoImpl();
		secNameCheck = impl.checkSectionName(sectionName, secDetailsId);
		
		int count=0;
		
			Connection conn = null;
			
			try {
				
				System.out.println("Inside the DAO");
				conn = JDBCConnection.getSeparateConnection();

				pstmt=conn.prepareStatement(SectionUtilsConstants.INSERT_DETAILS_CAMPUS_CLASS_SECTION);
								if(campusClassSectionPojo.getLocationId().equalsIgnoreCase("all")){
					locationList = new  LocationBD().getLocationDetails();
					System.out.println(locationList.size());
				}
				if(locationList.size()!=0){
					for(int j=0;j<locationList.size();j++){
				pstmt.setString(1,
						IDGenerator.getPrimaryKeyID("campus_classsection"));
				pstmt.setString(2, secDetailsId);
				pstmt.setString(3, sectionName);
				pstmt.setString(4, campusClassSectionPojo.getSectionStrength());
				pstmt.setString(5, USER_NAME);
				pstmt.setTimestamp(6, time_stamp);
				pstmt.setString(7, null);
				pstmt.setString(8, null);
				pstmt.setString(9, locationList.get(j).getLocation_id());
				System.out.println(pstmt);

				count=pstmt.executeUpdate();
				
					}
				}
				else{
					pstmt.setString(1,
							IDGenerator.getPrimaryKeyID("campus_classsection"));
					pstmt.setString(2, secDetailsId);
					pstmt.setString(3, campusClassSectionPojo.getSectionName());
					pstmt.setString(4, campusClassSectionPojo.getSectionStrength());
					pstmt.setString(5, USER_NAME);
					pstmt.setTimestamp(6, time_stamp);
					pstmt.setString(7, null);
					pstmt.setString(8, null);
					pstmt.setString(9, campusClassSectionPojo.getLocationId());
					System.out.println(pstmt);

					count=pstmt.executeUpdate();
				}
				if(count > 0){
					status="Section Details Added Successfully";
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
				+ " Control in CampusClassSectionDaoImpl:insertCampusClassSectionDao Ending");
		System.out.println("status"+status);
		return status;
	}

	@Override
	public synchronized String updateCampusClassSectionDao(
			SectionPojo campusClassSectionPojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:updateCampusClassSectionDao  Starting");
		// TODO Auto-generated method stub
		String sectionId = campusClassSectionPojo.getSecId();

		String secNameCheck = "";
		int count = 0;

		String secDetailsId = campusClassSectionPojo.getSecDetailsId();
		String sectionName = campusClassSectionPojo.getSectionName();
		String sectionStrength = campusClassSectionPojo.getSectionStrength();
		String secId = campusClassSectionPojo.getSecId();
		String USER_NAME = campusClassSectionPojo.getCreateUser();
		String locationId=campusClassSectionPojo.getLocationId();
		
		

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt=conn.prepareStatement(SectionUtilsConstants.UPDATE_DETAILS_CAMPUS_CLASS_SECTION);
			pstmt.setString(1, secDetailsId);
			pstmt.setString(2, sectionName);
			pstmt.setString(3, sectionStrength);
			pstmt.setString(4, USER_NAME);
			pstmt.setTimestamp(5, time_stamp);
			pstmt.setString(6, locationId);
			pstmt.setString(7, secId);
			
			
			
			count = pstmt.executeUpdate();
			

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

		if (count > 0) {

			secNameCheck = "Section Details Updated Successfully";
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:updateCampusClassSectionDao  Ending");
		return secNameCheck;
	}

	@Override
	public synchronized boolean deleteCampusClassSectionDao(String sectionid[],String[] locationList) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:deleteCampusClassSectionDao  Starting");

		boolean flag = false;
		
		int sectioncount = 0;
		int sectionCount = 0;
		int count = 0;
		PreparedStatement pstmtcountinmarksupload = null;
		PreparedStatement pstmtcountinMeeting = null;
		PreparedStatement pstmtcountinstudent = null;
		PreparedStatement pstmtcountinStudentPromotion = null;
		PreparedStatement pstmtcountinTimeTable = null;
		PreparedStatement pstmtcountinTeacherSettings = null;

		ResultSet rscountinmarksupload = null;
		ResultSet rscountinMeeting = null;
		ResultSet rscountinstudent = null;
		ResultSet rscountinStudentPromotion = null;
		ResultSet rscountinTimeTable = null;
		ResultSet rscountinTeacherSettings = null;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			for(int i=0;i<sectionid.length;i++){
			// check section in assignment table
			PreparedStatement pstmtcountinassignment = conn
					.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_CAMPUS_ASSIGNMENT);
			pstmtcountinassignment.setString(1, sectionid[i]);
			pstmtcountinassignment.setString(2, locationList[i]);
			System.out.println("check section in in assignment table  Delete:::"+pstmtcountinassignment);
			
			
			
			ResultSet rscountinassignment = pstmtcountinassignment
					.executeQuery();
			while (rscountinassignment.next()) {
				sectioncount = rscountinassignment.getInt(1);
				if(sectioncount > 0){
					sectionCount ++;
				}
				
			}
			rscountinassignment.close();
			pstmtcountinassignment.close();
			if (sectioncount == 0) {
				// check section in campus classteacher table
				PreparedStatement pstmtcountinclassTeacher = conn
						.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_CAMPUS_CLASSTEACHER);
				pstmtcountinclassTeacher.setString(1, sectionid[i]);
				pstmtcountinclassTeacher.setString(2, locationList[i]);
				System.out.println("check section in campus classteacher  Delete:::"+pstmtcountinclassTeacher);
				
				ResultSet rscountinclassTeacher = pstmtcountinclassTeacher
						.executeQuery();
				while (rscountinclassTeacher.next()) {
					sectioncount = rscountinclassTeacher.getInt(1);
					if(sectioncount > 0){
						sectionCount ++;
					}
					
					
				}
				rscountinclassTeacher.close();
				pstmtcountinclassTeacher.close();

				if (sectioncount == 0) {

					// check SECTION in marks upload

					pstmtcountinmarksupload = conn
							.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_MARKS_UPLOAD);
					pstmtcountinmarksupload.setString(1, sectionid[i]);
					pstmtcountinmarksupload.setString(2, locationList[i]);
					System.out.println("check section in marks upload  Delete:::"+pstmtcountinmarksupload);
					
					rscountinmarksupload = pstmtcountinmarksupload
							.executeQuery();
					while (rscountinmarksupload.next()) {
						sectioncount = rscountinmarksupload.getInt(1);
						if(sectioncount > 0){
							sectionCount ++;
						}
					}
					rscountinmarksupload.close();
					pstmtcountinmarksupload.close();
					

						// check SECTION in meeting upload

						
						if (sectioncount == 0) {

							// check section in student
							// table
							
							
							
							pstmtcountinstudent = conn
									.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_CAMPUS_STUDENT);
							pstmtcountinstudent.setString(1, sectionid[i]);
							pstmtcountinstudent.setString(2, locationList[i]);
							
							System.out.println("check section in student  Delete:::"+pstmtcountinstudent);
							rscountinstudent = pstmtcountinstudent
									.executeQuery();
							while (rscountinstudent.next()) {
								sectioncount = rscountinstudent.getInt(1);
								if(sectioncount > 0){
									sectionCount ++;
								}

							}
							rscountinstudent.close();
							pstmtcountinstudent.close();
							
							if (sectioncount == 0) {

								// check section in
								// student promotion
								// table
								pstmtcountinStudentPromotion = conn
										.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_CAMPUS_STUDENT_PROMOTION);
								pstmtcountinStudentPromotion.setString(1,sectionid[i]);
								pstmtcountinStudentPromotion.setString(2,sectionid[i]);
								pstmtcountinStudentPromotion.setString(3, locationList[i]);
								System.out.println("check section in student promotion Delete:::"+pstmtcountinStudentPromotion);
								
								
								rscountinStudentPromotion = pstmtcountinStudentPromotion
										.executeQuery();

								while (rscountinStudentPromotion.next()) {
									sectioncount = rscountinStudentPromotion
											.getInt(1);
									if(sectioncount > 0){
										sectionCount ++;
									}
								}
								rscountinStudentPromotion.close();
								pstmtcountinStudentPromotion.close();
								if (sectioncount == 0) {
									// check section in
									// time table
									pstmtcountinTimeTable = conn
											.prepareStatement(SectionUtilsConstants.SECTION_COUNT_IN_CAMPUS_TIME_TABLE);
									pstmtcountinTimeTable.setString(1,sectionid[i]);
									pstmtcountinTimeTable.setString(2, locationList[i]);
									
									System.out.println("check section in Time table Delete:::"+pstmtcountinTimeTable);
									rscountinTimeTable = pstmtcountinTimeTable
											.executeQuery();
									while (rscountinTimeTable.next()) {
										sectioncount = rscountinTimeTable
												.getInt(1);
										if(sectioncount > 0){
											sectionCount ++;
										}
									}
									rscountinTimeTable.close();
									pstmtcountinTimeTable.close();

									

										if (sectioncount == 0) {
											pstmt = conn.prepareStatement(SectionUtilsConstants.DELETE_DETAILS_CAMPUS_CLASS_SECTION);

											pstmt.setString(1, sectionid[i]);
											pstmt.setString(2, locationList[i]);
											System.out.println("location Delete:::"+pstmt);
											count = pstmt.executeUpdate();

										}

									}// if end for time
										// table

								}// if end for student
									// promotion table

							}// if end for campus
								// student table

						}// if end for meeting

					}// if end for marks upload
						// table
			//}// if end for campus_classteacher table

			//	} if end for assignment table

			/*if (count > 0) {
				flag = true;
			} else {
				flag = false;
			}*/
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rscountinmarksupload != null
						&& !rscountinmarksupload.isClosed()) {
					rscountinmarksupload.close();
				}
				if (rscountinMeeting != null && !rscountinMeeting.isClosed()) {
					rscountinMeeting.close();
				}
				if (rscountinstudent != null && !rscountinstudent.isClosed()) {
					rscountinstudent.close();
				}
				if (rscountinStudentPromotion != null
						&& !rscountinStudentPromotion.isClosed()) {
					rscountinStudentPromotion.close();
				}
				if (rscountinTimeTable != null
						&& !rscountinTimeTable.isClosed()) {
					rscountinTimeTable.close();
				}
				if (rscountinTeacherSettings != null
						&& !rscountinTeacherSettings.isClosed()) {
					rscountinTeacherSettings.close();
				}
				if (pstmtcountinmarksupload != null
						&& !pstmtcountinmarksupload.isClosed()) {
					pstmtcountinmarksupload.close();
				}
				if (pstmtcountinMeeting != null
						&& !pstmtcountinMeeting.isClosed()) {
					pstmtcountinMeeting.close();
				}
				if (pstmtcountinstudent != null
						&& !pstmtcountinstudent.isClosed()) {
					pstmtcountinstudent.close();
				}
				if (pstmtcountinStudentPromotion != null
						&& !pstmtcountinStudentPromotion.isClosed()) {
					pstmtcountinStudentPromotion.close();
				}
				if (pstmtcountinTimeTable != null
						&& !pstmtcountinTimeTable.isClosed()) {
					pstmtcountinTimeTable.close();
				}
				if (pstmtcountinTeacherSettings != null
						&& !pstmtcountinTeacherSettings.isClosed()) {
					pstmtcountinTeacherSettings.close();
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
				+ " Control in CampusClassSectionDaoImpl:deleteCampusClassSectionDao  Ending");
		
		if(sectionCount > 0){
			
			System.out.println(flag);
			
			flag = false;
			return flag;
		}
		else{
			
			System.out.println(flag);
			flag = true;
			return flag;
		}
		
		
	}

	@Override
	public synchronized List<SectionPojo> getCampusClassSectionAndClassDetailsDao(String schoolLocation)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Starting");
		ResultSet rs = null;
		
		List<SectionPojo> campusClassSectionPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS);
			pstmt.setString(1, schoolLocation);
			System.out.println("section:::"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs.getString("Location_Name"));
				campusClassSectionPojo.setLocationId(rs.getString("locationId"));
				campusClassSectionPojoList.add(campusClassSectionPojo);
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
				+ " Control in CampusClassSectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Ending");
		return campusClassSectionPojoList;

	}

	@Override
	public synchronized List<SectionPojo> getCampusClassDetailsIDandClassDetailsNameDao(String locationId)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:getCampusClassDetailsIDandClassDetailsNameDao  Starting");
		// TODO Auto-generated method stub
		List<SectionPojo> campusClassDetailsPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_DETAILS_ID_AND_CLASS_DETAILS_NAME);
			if(!locationId.equalsIgnoreCase("all"))
			pstmt.setString(1, locationId);
			else
			pstmt.setString(1, "%%");
			
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSecDetailsId(rs
						.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs
						.getString("classdetails_name_var"));
				campusClassDetailsPojoList.add(campusClassSectionPojo);
			}
			
			System.out.println("class detail query>>>"+pstmt);
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
				+ " Control in CampusClassSectionDaoImpl:getCampusClassDetailsIDandClassDetailsNameDao  Ending");
		return campusClassDetailsPojoList;

	}

	@Override
	public synchronized boolean checkSectionName(String sectionName, String secDetailId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:checkSectionName  Starting");
		// TODO Auto-generated method stub
		boolean secNameCheck = false;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SectionUtilsConstants.CHECK_SECTION);
			pstmt.setString(1, secDetailId);
			pstmt.setString(2, sectionName);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt("usercount") > 0) {
				secNameCheck = true;
			} else {
				secNameCheck = false;
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
				+ " Control in CampusClassSectionDaoImpl:checkSectionName  Ending");
		return secNameCheck;
	}

	public synchronized boolean checkSectionNameForUpdate(String sectionId,
			String sectionName, String className) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:checkSectionNameForUpdate  Starting");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String classId = null;
		boolean secNameCheck = false;
		Connection conn = null;
		PreparedStatement pstmtclassid = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmtclassid = conn
					.prepareStatement(SectionUtilsConstants.GET_CLASS_ID_BY_NAME);
			pstmtclassid.setString(1, className);
			ResultSet rsclassid = pstmtclassid.executeQuery();

			while (rsclassid.next()) {
				classId = rsclassid.getString("classdetail_id_int");
				pstmt = (PreparedStatement) JDBCConnection
						.getStatement(SectionUtilsConstants.CHECK_SECTION_FOR_UPDATE);
				pstmt.setString(1, classId);
				pstmt.setString(2, sectionName);
				pstmt.setString(3, sectionId);
				ResultSet rs = pstmt.executeQuery();

				rs.next();
				if (rs.getInt("usercount") > 0) {
					secNameCheck = true;
				} else {
					secNameCheck = false;
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
				+ " Control in CampusClassSectionDaoImpl:checkSectionNameForUpdate Ending");
		return secNameCheck;
	}
	
	@Override
	public synchronized List<SectionForm> searchSection(SectionForm searchText) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :searchClassDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int siNo = 0;
		List<SectionForm> getClassDetailsList = new ArrayList<SectionForm>();
		try {
			String searchName=searchText.getSectionName();
			String locationId=searchText.getLocationId();
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SectionUtilsConstants.GET_SECTION_DETAILS_BY_SEARCH_ID);
			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, locationId);
			System.out.println("section Search "+pstmt);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionForm campusClassSectionPojo = new SectionForm();
				siNo++;
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs
						.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs
						.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs
						.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs
						.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs
						.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs
						.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs
						.getString("Location_Name"));
				
			
				
				
				getClassDetailsList.add(campusClassSectionPojo);

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
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :searchClassDetails  Starting");

		return getClassDetailsList;
	}
	
	public synchronized SectionForm editSection(SectionForm secCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : editClass  Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SectionForm campusClassSectionPojo = new SectionForm();
		try {
			String secId=secCode.getSectionId();
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SectionUtilsConstants.EDIT_SECTION);
			pstmt.setString(1, secId);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setLocationId(rs.getString("locationId"));
			
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setStatus("update");
			
				
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
				+ " Control in ClassDaoImpl : editClass  Starting");
		return campusClassSectionPojo;
	}

	@Override
	public List<SectionPojo> sectiondetailsdownload() throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Starting");
		ResultSet rs = null;
		// TODO Auto-generated method stub
		List<SectionPojo> campusClassSectionPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs.getString("Location_Name"));
				
				
				
				campusClassSectionPojoList.add(campusClassSectionPojo);
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
				+ " Control in CampusClassSectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Ending");
		return campusClassSectionPojoList;

	
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sectionNameCheck(SectionForm sectionForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : classNameCheck  Starting");
		boolean status = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;
		int count1 = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SectionUtilsConstants.CHECK_SEC_NAME);

			pstmt.setString(1, sectionForm.getClassId().trim());
			pstmt.setString(2, sectionForm.getSectionName());
			pstmt.setString(3, sectionForm.getLocationId());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("secname");
			}

			if (count > 0) {
				status = true;
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
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : classNameCheck  Starting");

		System.out.println(status);
		return status;

	}

	public List<SectionForm> getstreamdetailsforOnchange(String locationid,String classname, String streamId) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
	//List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	int sno=0;
	List<SectionForm> list = new ArrayList<SectionForm>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		pst = conn.prepareStatement(" SELECT ccs.classsection_id_int,cl.Location_Id,cl.Location_Name,cstr.classstream_name_var,cstr.classstream_id_int,ccd.classdetails_name_var,ccd.classdetail_id_int,ccs.classsection_id_int,ccs.classsection_name_var,ccs.classsection_strength_int FROM campus_classsection ccs LEFT JOIN campus_classdetail ccd ON ccs.classdetail_id_int=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId  LEFT JOIN campus_classstream cstr  ON ccd.classstream_id_int=cstr.classstream_id_int  LEFT JOIN campus_location cl ON ccs.locationId=cl.Location_Id WHERE ccs.locationId LIKE ? AND ccd.classstream_id_int LIKE ? AND ccd.classdetail_id_int LIKE ? ORDER BY CAST(SUBSTR(ccs.locationId,4) AS UNSIGNED),CAST(SUBSTR(ccd.classstream_id_int,4) AS UNSIGNED),CAST(SUBSTR(ccd.classdetail_id_int,4) AS UNSIGNED)");
		pst.setString(1, locationid);
		pst.setString(2, streamId);
		pst.setString(3, classname);
		
		System.out.println(pst);
		rs = pst.executeQuery();
		while (rs.next()) {
			sno++;
			
			SectionForm stdvo = new SectionForm();
			
			stdvo.setLocationId(rs.getString("Location_Id"));
			stdvo.setSecId(rs.getString("classsection_id_int"));
			stdvo.setLocationName(rs.getString("Location_Name"));
			stdvo.setStreamName(rs.getString("classstream_name_var"));
			stdvo.setClassName(rs.getString("classdetails_name_var"));
			stdvo.setSectionName(rs.getString("classsection_name_var"));
			stdvo.setStrength(rs.getString("classsection_strength_int"));
			//stdvo.setStrength(rs.getString(""));
			//stdvo.set
			/*stdvo.setStreamName("");
			stdvo.setClassName("");
			stdvo.setSectionName("");
			stdvo.setStrength("");
	*/
			list.add(stdvo);
			System.out.println("*********************************************************************");
		list.size();
		}
	}
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

	return list;

	}
	
}
