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

import com.centris.campus.dao.ClassDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.AcademicYearSQLUtilConstants;
import com.centris.campus.util.ClassUtilsConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.LocationVO;

public class ClassDaoImpl implements ClassDao{
	private static final Logger logger = Logger.getLogger(ClassDaoImpl.class);
	

	public synchronized List<ClassPojo> getClassDetails(String schoolLocation) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :updateStreamCheck Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<ClassPojo> getClassDetailsList=new ArrayList<ClassPojo>();
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS);
				pstmt.setString(1, schoolLocation);
			System.out.println("classListing::"+pstmt);
			ResultSet getStreamDetailsRs=pstmt.executeQuery();
			
		while(getStreamDetailsRs.next()){
			ClassPojo masterPojo=new ClassPojo();
			masterPojo.setClassId(getStreamDetailsRs.getString("classdetail_id_int"));
			masterPojo.setClassName(getStreamDetailsRs.getString("classdetails_name_var"));
			masterPojo.setStreamName(getStreamDetailsRs.getString("classstream_name_var"));
			masterPojo.setLocationName(getStreamDetailsRs.getString("Location_Name"));
			masterPojo.setLocationId(getStreamDetailsRs.getString("locationId"));
			getClassDetailsList.add(masterPojo);
			 
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
			+ " Control in ClassDaoImpl :updateStreamCheck  Starting");
	return getClassDetailsList;

	}
	
	public synchronized String createClass(ClassPojo classPojo,String createUser, String classcode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : createClass  Starting");
		
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			  if(!classPojo.getStatus().equals("update")){
			
					
			pstmt = conn.prepareStatement(ClassUtilsConstants.CREATE_CLASS);
			if(classPojo.getLocationId().equalsIgnoreCase("all")){
				locationList = new  LocationBD().getLocationDetails();
				System.out.println(locationList.size());
			}
			if(locationList.size()!=0){
				for(int j=0;j<locationList.size();j++){
			pstmt.setString(1, "CCD"+classPojo.getClassId());
			pstmt.setString(2, classPojo.getStreamId());
			pstmt.setString(3, classPojo.getClassName());
			pstmt.setString(4, classPojo.getCreateUser());
			pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			pstmt.setString(6, locationList.get(j).getLocation_id());
			
			count = pstmt.executeUpdate();
			
				}
			
			
			
			}
			else{
				pstmt.setString(1, "CCD"+classPojo.getClassId());
				pstmt.setString(2, classPojo.getStreamId());
				pstmt.setString(3, classPojo.getClassName());
				pstmt.setString(4, classPojo.getCreateUser());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6, classPojo.getLocationId());
				
				count = pstmt.executeUpdate();
			}
			if (count > 0) {
				status = "Class Added Successfully";
			}
			
		
			  }else{
				  
				
				PreparedStatement	pstmt1 = conn.prepareStatement(ClassUtilsConstants.DELETE_CLASS_DETAIL);
					
					//pstmt.setString(1, classPojo.getClassId());
				
					pstmt1.setString(1, classPojo.getUpdateClassCode());
					pstmt1.setString(2, classPojo.getLocationId());
					
					
					
					int count1 = pstmt1.executeUpdate();
					if (count1 > 0) {
						
							
					pstmt = conn.prepareStatement(ClassUtilsConstants.CREATE_CLASS);
					if(classPojo.getLocationId().equalsIgnoreCase("all")){
						locationList = new  LocationBD().getLocationDetails();
						System.out.println(locationList.size());
					}
					if(locationList.size()!=0){
						for(int j=0;j<locationList.size();j++){
					pstmt.setString(1, "CCD"+classPojo.getClassId());
					pstmt.setString(2, classPojo.getStreamId());
					pstmt.setString(3, classPojo.getClassName());
					pstmt.setString(4, classPojo.getCreateUser());
					pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
					pstmt.setString(6, locationList.get(j).getLocation_id());
					
					
					
						}
					
					count = pstmt.executeUpdate();
					
					}
					else{
						pstmt.setString(1, "CCD"+classPojo.getClassId());
						pstmt.setString(2, classPojo.getStreamId());
						pstmt.setString(3, classPojo.getClassName());
						pstmt.setString(4, classPojo.getCreateUser());
						pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
						pstmt.setString(6, classPojo.getLocationId());
						
						count = pstmt.executeUpdate();
					}
					if (count > 0) {
						status = "Class Updated Successfully";
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
				+ " Control in ClassDaoImpl : createClass  Starting");
		return status;
	}
	
	@Override
	public synchronized List<ClassPojo> getStreamDetailDao(String school) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassStreamDaoImpl:getStreamDetailDao  Starting");
		// TODO Auto-generated method stub
		List<ClassPojo> streamList = new ArrayList<ClassPojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
				conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_STREAM);
			pstmt.setString(1, school);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ClassPojo streamPojo = new ClassPojo();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamList.add(streamPojo);
				
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
				+ " Control in ClassDaoImpl :getStreamDetailDao  Ending");
		return streamList;
	}
	
	public synchronized boolean classNameCheck(ClassPojo classPojo) {
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
			pstmt = conn.prepareStatement(ClassUtilsConstants.CHECK_CLASS_NAME);

			pstmt.setString(1, classPojo.getClassName().trim());
			pstmt.setString(2, classPojo.getStreamId());
			if(!classPojo.getLocationId().equalsIgnoreCase("all"))
			pstmt.setString(3, classPojo.getLocationId());
			else
			pstmt.setString(3,"%%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("Classname");
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
	
	public synchronized boolean updateclassNameCheck(ClassPojo classPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateclassNameCheck  Starting");
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
			pstmt = conn.prepareStatement(ClassUtilsConstants.CHECK_UPDATE_CLASS_NAME);

			pstmt.setString(1, classPojo.getSecDetailName());
			pstmt.setString(2, classPojo.getStreamId());
			pstmt.setString(3, classPojo.getUpdateClassCode());
			pstmt.setString(4, classPojo.getLocationId());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("Classname");
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
				+ " Control in ClassDaoImpl : updateclassNameCheck  Starting");

		return status;

	}
	
	public synchronized ClassPojo editClass(String classCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : editClass  Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClassPojo classPojo = new ClassPojo();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ClassUtilsConstants.EDIT_CLASS);
			pstmt.setString(1, classCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				
                classPojo.setClassId(rs.getString("classdetail_id_int").trim());
				classPojo.setStreamId(rs.getString("classstream_id_int").trim());
				classPojo.setClassName(rs.getString("classdetails_name_var"));
				classPojo.setLocationId(rs.getString("locationId"));
				classPojo.setStatus("update");
			
				
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
		return classPojo;
	}
	
	@Override
	public synchronized boolean deleteClass(String classid[],String locationCode[]) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassStreamDaoImpl:deleteClassDao  Starting");
		// TODO Auto-generated method stub
		int val = 0;
		PreparedStatement pstmtcountinClassSection = null;
		PreparedStatement pstmtcountinassignment = null;
		PreparedStatement pstmtcountinclassTeacher = null;
		PreparedStatement pstmtcountinexamtimetable = null;
		PreparedStatement pstmtcountinFeesettings = null;
		PreparedStatement pstmtcountinfeestructur = null;
		PreparedStatement pstmtcountinfeesettings = null;
		PreparedStatement pstmtcountinMeeting = null;
		PreparedStatement pstmtcountinmarksupload = null;
		PreparedStatement pstmtcountinstudent = null;
		PreparedStatement pstmtcountinStudentPromotion = null;
		PreparedStatement pstmtcountinTimeTable = null;
		PreparedStatement pstmtcountinCampusSubject = null;
		PreparedStatement pstmtcountinTeacherSettings = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean status = false;
		int statusCount=0;
		int classcount = 0;
		Connection conn = null;
		try { 
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<classid.length;i++){
			// check class in classsection table
			pstmtcountinClassSection = conn
					.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_CLASSSECTION);
			pstmtcountinClassSection.setString(1, classid[i]);
			pstmtcountinClassSection.setString(2, locationCode[i]);
			

			
			
			
			
			ResultSet rscountinClassSection = pstmtcountinClassSection
					.executeQuery();
			while (rscountinClassSection.next()) {
				classcount = rscountinClassSection.getInt(1);
				
				if(classcount > 0){
					statusCount++;
				}
				
			}
			rscountinClassSection.close();
			pstmtcountinClassSection.close();

			if (classcount == 0) {
				// check class in assignment table
				pstmtcountinassignment = conn
						.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_ASSIGNMENT);
				pstmtcountinassignment.setString(1, classid[i]);
				pstmtcountinassignment.setString(2, locationCode[i]);
				
				
				ResultSet rscountinassignment = pstmtcountinassignment
						.executeQuery();
				while (rscountinassignment.next()) {
					classcount = rscountinassignment.getInt(1);
					
					if(classcount>0){
						statusCount++;
					}
				}
				rscountinassignment.close();
				pstmtcountinassignment.close();
				if (classcount == 0) {
					// check class in campus classteacher table
					pstmtcountinclassTeacher = conn
							.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_CLASSTEACHER);
					pstmtcountinclassTeacher.setString(1, classid[i]);
					pstmtcountinclassTeacher.setString(2, locationCode[i]);
					
					
					ResultSet rscountinclassTeacher = pstmtcountinclassTeacher
							.executeQuery();
					while (rscountinclassTeacher.next()) {
						classcount = rscountinclassTeacher.getInt(1);
						if(classcount>0){
							statusCount++;
						}
					}
					rscountinclassTeacher.close();
					pstmtcountinclassTeacher.close();

					if (classcount == 0) {

						// check class in exam time table
						pstmtcountinexamtimetable = conn
								.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_EXAMINATION_TIME_TABLE);
						pstmtcountinexamtimetable.setString(1, classid[i]);
						pstmtcountinexamtimetable.setString(2, locationCode[i]);
						
						
						ResultSet rscountinexamtimetable = pstmtcountinexamtimetable
								.executeQuery();

						while (rscountinexamtimetable.next()) {
							classcount = rscountinexamtimetable.getInt(1);
							if(classcount>0){
								statusCount++;
							}
						}
						rscountinexamtimetable.close();
						pstmtcountinexamtimetable.close();

						if (classcount == 0) {
							// check class in fee_settings table

							pstmtcountinFeesettings = conn
									.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_FEE_SETUP);
							pstmtcountinFeesettings.setString(1, classid[i]);
							pstmtcountinFeesettings.setString(2, locationCode[i]);
							
							
							ResultSet rscountinFeeSettings = pstmtcountinFeesettings
									.executeQuery();

							while (rscountinFeeSettings.next()) {
								classcount = rscountinFeeSettings.getInt(1);
								if(classcount>0){
									statusCount++;
								}
							}
							rscountinFeeSettings.close();
							pstmtcountinFeesettings.close();

							

								if (classcount == 0) {

									// check stream in marks upload
									
									

									pstmtcountinmarksupload = conn
											.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_MARKS_UPLOAD);
									pstmtcountinmarksupload.setString(1,classid[i]);
									pstmtcountinmarksupload.setString(2, locationCode[i]);
									
									
									ResultSet rscountinmarksupload = pstmtcountinmarksupload.executeQuery();
									while (rscountinmarksupload.next()) {
										classcount = rscountinmarksupload
												.getInt(1);
										if(classcount>0){
											statusCount++;
										}
									}
									rscountinmarksupload.close();
									pstmtcountinmarksupload.close();

									if (classcount == 0) {

										// check CLASS in student
										// table
										pstmtcountinstudent = conn
												.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_STUDENT);
										pstmtcountinstudent.setString(1,classid[i]);
										pstmtcountinstudent.setString(2, locationCode[i]);

										
										
										ResultSet rscountinstudent = pstmtcountinstudent
												.executeQuery();
										while (rscountinstudent.next()) {
											classcount = rscountinstudent
													.getInt(1);
											if(classcount>0){
												statusCount++;
											}
										}
										rscountinstudent.close();
										pstmtcountinstudent.close();
										if (classcount == 0) {

											// check class in
											// student promotion
											// table
											pstmtcountinStudentPromotion = conn
													.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_STUDENT_PROMOTION);
											pstmtcountinStudentPromotion.setString(1, classid[i]);
											pstmtcountinStudentPromotion.setString(2, classid[i]);
											pstmtcountinStudentPromotion.setString(3, locationCode[i]);
											
											
											ResultSet rscountinStudentPromotion = pstmtcountinStudentPromotion
													.executeQuery();

											while (rscountinStudentPromotion
													.next()) {
												classcount = rscountinStudentPromotion
														.getInt(1);
												if(classcount>0){
													statusCount++;
												}
											}
											rscountinStudentPromotion.close();
											pstmtcountinStudentPromotion
													.close();
											if (classcount == 0) {
												// check class in
												// time table
												pstmtcountinTimeTable = conn
														.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_TIME_TABLE);
												pstmtcountinTimeTable.setString(1, classid[i]);
												pstmtcountinTimeTable.setString(2, locationCode[i]);

												
												
												ResultSet rscountinTimeTable = pstmtcountinTimeTable
														.executeQuery();
												while (rscountinTimeTable
														.next()) {
													classcount = rscountinTimeTable
															.getInt(1);
													if(classcount>0){
														statusCount++;
													}
												}
												rscountinTimeTable.close();
												pstmtcountinTimeTable.close();
												if (classcount == 0) {
													// check class
													// in subject
													// table
													pstmtcountinCampusSubject = conn
															.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_CAMPUS_SUBJECT);
													pstmtcountinCampusSubject.setString(1,classid[i]);
													pstmtcountinCampusSubject.setString(2, locationCode[i]);
													
													
													ResultSet rscountinCampusSubject = pstmtcountinCampusSubject
															.executeQuery();
													while (rscountinCampusSubject
															.next()) {
														classcount = rscountinCampusSubject
																.getInt(1);
														if(classcount>0){
															statusCount++;
														}
													}
													rscountinCampusSubject
															.close();
													pstmtcountinCampusSubject
															.close();

													if (classcount == 0) {
														// check
														// class in
														// time
														// table
														pstmtcountinTeacherSettings = conn.prepareStatement(ClassUtilsConstants.CLASS_COUNT_IN_TEACHERSETTINGS);
														pstmtcountinTeacherSettings.setString(1,classid[i]);
														pstmtcountinTeacherSettings.setString(2, locationCode[i]);
														
														
														ResultSet rscountinTeacherSettings = pstmtcountinTeacherSettings
																.executeQuery();
														while (rscountinTeacherSettings
																.next()) {
															classcount = rscountinTeacherSettings
																	.getInt(1);
															if(classcount>0){
																statusCount++;
															}
														}
														rscountinCampusSubject
																.close();
														pstmtcountinTeacherSettings
																.close();

														if (classcount == 0) {
															pstmt = conn
																	.prepareStatement(ClassUtilsConstants.DELETE_CLASS_DETAIL);

															pstmt.setString(1,classid[i]);
															pstmt.setString(2, locationCode[i]);
															val = pstmt.executeUpdate();
															/*if(val > 0){
																status=true;
															}*/

														}

													}// //if end for
														// campus
														// subject

												}// if end for time
													// table

											}// if end for student
												// promotion table

										}// if end for campus
											// student table

									}// if end for marks upload
										// table

								}// if end for campus meetng table
							}// if end for feesettings table

							/*
							 * }// if end for fee structure table
							 */
							/*
							 * }// if end for fee_settings table
							 */
						}// if end for examination time table

					}// if end for campus_classteacher table

				}// if end for assignment table

			//} if end for class section table
	
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
				if (pstmtcountinClassSection != null
						&& !pstmtcountinClassSection.isClosed()) {
					pstmtcountinClassSection.close();
				}
				if (pstmtcountinassignment != null
						&& !pstmtcountinassignment.isClosed()) {
					pstmtcountinassignment.close();
				}
				if (pstmtcountinclassTeacher != null
						&& !pstmtcountinclassTeacher.isClosed()) {
					pstmtcountinclassTeacher.close();
				}
				if (pstmtcountinexamtimetable != null
						&& !pstmtcountinexamtimetable.isClosed()) {
					pstmtcountinexamtimetable.close();
				}
				if (pstmtcountinFeesettings != null
						&& !pstmtcountinFeesettings.isClosed()) {
					pstmtcountinFeesettings.close();
				}
				if (pstmtcountinfeestructur != null
						&& !pstmtcountinfeestructur.isClosed()) {
					pstmtcountinfeestructur.close();
				}
				if (pstmtcountinfeesettings != null
						&& !pstmtcountinfeesettings.isClosed()) {
					pstmtcountinfeesettings.close();
				}
				if (pstmtcountinMeeting != null
						&& !pstmtcountinMeeting.isClosed()) {
					pstmtcountinMeeting.close();
				}
				if (pstmtcountinmarksupload != null
						&& !pstmtcountinmarksupload.isClosed()) {
					pstmtcountinmarksupload.close();
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
				if (pstmtcountinCampusSubject != null
						&& !pstmtcountinCampusSubject.isClosed()) {
					pstmtcountinCampusSubject.close();
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
				+ " Control in ClassDaoImpl :deleteClassDao  Ending");

		if(statusCount > 0)
			status=false;
		else{
			status=true;
		}
		
		
		System.out.println("status"+status);
		return status;
		
	}
	
	public synchronized boolean updateClass(ClassPojo classPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateClass  Starting");
		boolean status = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		int count = 0;
		int count1 = 0;
		ResultSet rs = null;
		try {

				conn1 = JDBCConnection.getSeparateConnection();
			

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(ClassUtilsConstants.UPDATE_CLASS_DETAIL);

					pstmt.setString(1, classPojo.getClassName());
					
					pstmt.setString(2, classPojo.getStreamId());
					

					count = pstmt.executeUpdate();

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
				+ " Control in ClassDaoImpl : updateClass  Starting");
		return status;

	}
	
	@Override
	public synchronized List<ClassPojo> searchClassDetails(String searchText) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :searchClassDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int siNo = 0;
		String searchterm=searchText.split(",")[0];
		String school=searchText.split(",")[1];
		if(school.equalsIgnoreCase("all")){
			school="%%";
		}
		List<ClassPojo> getClassDetailsList = new ArrayList<ClassPojo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS_BY_SEARCH_ID);
			pstmt.setString(1, "%" + searchterm + "%");
			pstmt.setString(2, "%" + searchterm + "%");
			pstmt.setString(3, "%" + searchterm + "%");
			pstmt.setString(4, school);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ClassPojo coursePojo = new ClassPojo();
				siNo++;
				coursePojo.setClassId(rs.getString("classdetail_id_int"));
				coursePojo.setClassName(rs.getString("classdetails_name_var"));
				coursePojo.setStreamName(rs.getString("classstream_name_var"));
				coursePojo.setLocationName(rs.getString("Location_Name"));
				
				
				getClassDetailsList.add(coursePojo);

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

	public static List<ClassPojo> getClassDetailsOnChangeFunction(String streamId, String locationid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :updateStreamCheck Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<ClassPojo> getClassDetailsList=new ArrayList<ClassPojo>();
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS_BY_JS);
				pstmt.setString(1, locationid);
			pstmt.setString(2, streamId);
			System.out.println("classListing::>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pstmt);
			ResultSet getStreamDetailsRs=pstmt.executeQuery();
			
		while(getStreamDetailsRs.next()){
			ClassPojo masterPojo=new ClassPojo();
			masterPojo.setClassId(getStreamDetailsRs.getString("classdetail_id_int"));
			masterPojo.setClassName(getStreamDetailsRs.getString("classdetails_name_var"));
			masterPojo.setStreamName(getStreamDetailsRs.getString("classstream_name_var"));
			masterPojo.setLocationName(getStreamDetailsRs.getString("Location_Name"));
			masterPojo.setLocationId(getStreamDetailsRs.getString("locationId"));
			getClassDetailsList.add(masterPojo);
			 
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
			+ " Control in ClassDaoImpl :updateStreamCheck  Starting");
	return getClassDetailsList;

	}

	

}
