package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hsqldb.SetFunction;
import org.json.JSONArray;

import com.centris.campus.dao.ClassFeeSetupDao;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.util.ClassFeeSetupDetails;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.FeeExcelUploadUtil;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;

public class ClassFeeSetupDaoImpl implements ClassFeeSetupDao {

	private static final Logger logger = Logger.getLogger(ClassFeeSetupDaoImpl.class);

	public synchronized ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String serachTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl:getFeeSetupDetails Starting");

		String currentaccyear=serachTerm.split(",")[0];
		String locationId=serachTerm.split(",")[1];
		ArrayList<ClassFeeSetupVo> feeList = new ArrayList<ClassFeeSetupVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		Connection conn = null;
		int sno = 0;
		int feecount = 0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(ClassFeeSetupDetails.GET_CLASS_DETAILS);
			pstmt.setString(1, currentaccyear);
			pstmt.setString(2, locationId);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				ClassFeeSetupVo feevo = new ClassFeeSetupVo();
				sno++;
				feevo.setSno(sno);
				feevo.setFeecount(feecount);
				feevo.setAccyear(resultSet.getString("acadamic_year"));
				feevo.setAccyearid(resultSet.getString("acadamic_id"));
				feevo.setClassid(resultSet.getString("classdetail_id_int"));
				feevo.setClassname(resultSet.getString("classdetails_name_var"));
				feevo.setLocationId(resultSet.getString("locationId"));
				feevo.setLocationName(resultSet.getString("Location_Name"));
				pstmt1 = conn.prepareStatement(ClassFeeSetupDetails.GET_FEE_COUNT);
				pstmt1.setString(1, resultSet.getString("classdetail_id_int"));
				pstmt1.setString(2, resultSet.getString("acadamic_id"));
				pstmt1.setString(3, resultSet.getString("locationId"));

				resultSet1 = pstmt1.executeQuery();

				while (resultSet1.next()) {

					feevo.setFeecount(resultSet1.getInt("feecount"));
					if (resultSet1.getInt("feecount") > 0) {
						feevo.setStatus("Fee Set");
					} else {
						feevo.setStatus("Fee Not Set");
					}
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
				+ " Control in ClassFeeSetupDaoImpl:getFeeSetupDetails Ending");

		return feeList;
	}

	@Override
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm, String currentaccyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl:getSearchFeeSetupDetails Starting");

		ArrayList<ClassFeeSetupVo> feeList = new ArrayList<ClassFeeSetupVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		Connection conn = null;
		int sno = 0;
		int feecount = 0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn
					.prepareStatement(ClassFeeSetupDetails.SERCH_FEE_DETAILS);
			pstmt.setString(1, "%" + searchTerm + "%");
			pstmt.setString(2, "%" + searchTerm + "%");
			pstmt.setString(3, "%" + searchTerm + "%");
			pstmt.setString(4, currentaccyear);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				ClassFeeSetupVo feevo = new ClassFeeSetupVo();
				sno++;
				feevo.setSno(sno);
				feevo.setFeecount(feecount);
				feevo.setAccyear(resultSet.getString("acadamic_year"));
				feevo.setAccyearid(resultSet.getString("acadamic_id"));
				feevo.setClassid(resultSet.getString("classdetail_id_int"));
				feevo.setClassname(resultSet.getString("classdetails_name_var"));
				feevo.setTermid(resultSet.getString("termid"));
				feevo.setTermname(resultSet.getString("termname"));
				feevo.setSpecialization_name(resultSet
						.getString("Specialization_name"));
				feevo.setSpecialization_id(resultSet
						.getString("Specialization_Id"));

				pstmt1 = conn
						.prepareStatement(ClassFeeSetupDetails.GET_FEE_COUNT);
				pstmt1.setString(1, resultSet.getString("classdetail_id_int"));
				pstmt1.setString(2, resultSet.getString("acadamic_id"));
				pstmt1.setString(3, resultSet.getString("termid"));
				pstmt1.setString(4, resultSet.getString("Specialization_Id"));

				resultSet1 = pstmt1.executeQuery();

				while (resultSet1.next()) {

					feevo.setFeecount(resultSet1.getInt("feecount"));
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
				+ " Control in ClassFeeSetupDaoImpl:getSearchFeeSetupDetails Ending");

		return feeList;
	}

	public synchronized ArrayList<ClassFeeSetupVo> getApprovedFees(
			ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: getAllFees Starting");

		ArrayList<ClassFeeSetupVo> allfeeslist = new ArrayList<ClassFeeSetupVo>();
		PreparedStatement pstmt0=null;
		ResultSet rs0=null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet resultSet3 = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		String feeSettingcode = null;
		ClassFeeSetupVo FeeSetupVo = null;
		ClassFeeSetupVo feeSetupVo = null;
		ClassFeeSetupVo specializationSetUp = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt0 = conn.prepareStatement(ClassFeeSetupDetails.GET_SPECIALIZATION);
			pstmt0.setString(1, feeSetupPojo.getClassId());
			pstmt0.setString(2, feeSetupPojo.getLocation_id());
			System.out.println("spaecialization "+pstmt0);
			 rs0 = pstmt0.executeQuery();
			while (rs0.next()) {
				String specId=rs0.getString(1);
				String specName=rs0.getString(2);

				pstmt = conn.prepareStatement(ClassFeeSetupDetails.GET_TERM);
				pstmt.setString(1, feeSetupPojo.getAcadamicYear());
				pstmt.setString(2, feeSetupPojo.getLocation_id());

				System.out.println("GET_TERM "+pstmt);
				resultSet = pstmt.executeQuery();

				while (resultSet.next()) {

					ArrayList<ClassFeeSetupVo> feeNamelist = new ArrayList<ClassFeeSetupVo>();
					count++;
					feeSetupVo = new ClassFeeSetupVo();
					feeSetupVo.setSno(count);
					feeSetupVo.setTermid(resultSet.getString("termid"));
					feeSetupVo.setTermname(resultSet.getString("termname"));
					feeSetupVo.setSpecialization_id(specId);
					feeSetupVo.setSpecialization_name(specName);
					pstmt2 = conn.prepareStatement(ClassFeeSetupDetails.CHECK_FEE_SETUP_SPECIALIZATION);
					pstmt2.setString(1, feeSetupPojo.getClassId());
					pstmt2.setString(2, feeSetupPojo.getAcadamicYear());
					pstmt2.setString(3, resultSet.getString("termid"));
					pstmt2.setString(4, specId);
					pstmt2.setString(5, feeSetupPojo.getLocation_id());

					System.out.println("pstmt2" + pstmt2);
					resultSet2 = pstmt2.executeQuery();
					while (resultSet2.next()) {
						count2 = resultSet2.getInt(1);
						feeSettingcode = resultSet2.getString("feeSettingcode");
					}

					pstmt1 = conn.prepareStatement(ClassFeeSetupDetails.GET_ALL_FEE);
					pstmt1.setString(1, feeSetupPojo.getLocation_id());
					pstmt1.setString(2, feeSetupPojo.getAcadamicYear());

					System.out.println("getActiveFee" + pstmt1);
					if (pstmt1 != null) {
						resultSet1 = pstmt1.executeQuery();

						while (resultSet1.next()) {
							count1++;
							FeeSetupVo = new ClassFeeSetupVo();

							FeeSetupVo.setFeecode(resultSet1.getString("FeeCode"));
							FeeSetupVo.setFeename(resultSet1.getString("FeeName"));

							if (count2 > 0) {
								pstmt3 = conn.prepareStatement(ClassFeeSetupDetails.GET_ALL_FEE_AMOUNT);
								pstmt3.setString(1, feeSettingcode);
								pstmt3.setString(2, resultSet1.getString("FeeCode"));
								resultSet3 = pstmt3.executeQuery();
								if (resultSet3.next()) {
									FeeSetupVo.setFeeAmount(resultSet3.getDouble("feeAmount"));
									FeeSetupVo.setStatus("Y");
									FeeSetupVo.setFeesettingcode(feeSettingcode);
								} else {
									FeeSetupVo.setFeeAmount(0.00);
									FeeSetupVo.setStatus("N");
								}
							}
							else{
								FeeSetupVo.setFeeAmount(0.00);
								FeeSetupVo.setStatus("N");
							}
							feeNamelist.add(FeeSetupVo);
						}
					}
					feeSetupVo.setFeeNamelist(feeNamelist);
					allfeeslist.add(feeSetupVo);
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
				if (resultSet1 != null && !resultSet1.isClosed()) {
					resultSet1.close();
				}
				if (resultSet2 != null && !resultSet2.isClosed()) {
					resultSet2.close();
				}
				if (rs0 != null && !rs0.isClosed()) {
					rs0.close();
				}
				if (pstmt0 != null && !pstmt0.isClosed()) {
					pstmt0.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
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
				+ " Control in ClassFeeSetupDaoImpl: getAllFees  Ending");

		return allfeeslist;

	}

	@Override
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo,String location) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: getAllFees Starting");

		ArrayList<ClassFeeSetupVo> allfeeslist = new ArrayList<ClassFeeSetupVo>();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet resultSet3 = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		String feeSettingcode = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ClassFeeSetupDetails.GET_TERM);
			pstmt.setString(1, feeSetupPojo.getAcadamicYear());
			pstmt.setString(2, location);
			System.out.println("TERM  "+pstmt);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				ArrayList<ClassFeeSetupVo> feeNamelist = new ArrayList<ClassFeeSetupVo>();
				count++;
				ClassFeeSetupVo feeSetupVo = new ClassFeeSetupVo();
				feeSetupVo.setSno(count);
				feeSetupVo.setTermid(resultSet.getString("termid"));
				feeSetupVo.setTermname(resultSet.getString("termname"));

				pstmt2 = conn.prepareStatement(ClassFeeSetupDetails.CHECK_FEE_SETUP);
				pstmt2.setString(1, feeSetupPojo.getClassId());
				pstmt2.setString(2, feeSetupPojo.getAcadamicYear());
				pstmt2.setString(3, resultSet.getString("termid"));
				pstmt2.setString(4,feeSetupPojo.getLocation_id());
				System.out.println("pstmt2" + pstmt2);
				resultSet2 = pstmt2.executeQuery();
				while (resultSet2.next()) {
					count2 = resultSet2.getInt(1);
					feeSettingcode = resultSet2.getString("feeSettingcode");
				}

				pstmt1 = conn.prepareStatement(ClassFeeSetupDetails.GET_ALL_FEE);
				pstmt1.setString(1, location);
				pstmt1.setString(2, feeSetupPojo.getAcadamicYear());
				System.out.println("getActiveFee" + pstmt1);
				if (pstmt1 != null) {
					resultSet1 = pstmt1.executeQuery();

					while (resultSet1.next()) {
						count1++;
						ClassFeeSetupVo FeeSetupVo = new ClassFeeSetupVo();

						FeeSetupVo.setFeecode(resultSet1.getString("FeeCode"));
						FeeSetupVo.setFeename(resultSet1.getString("FeeName"));

						if (count2 > 0) {
							pstmt3 = conn
									.prepareStatement(ClassFeeSetupDetails.GET_ALL_FEE_AMOUNT);
							pstmt3.setString(1, feeSettingcode);
							pstmt3.setString(2, resultSet1.getString("FeeCode"));
							resultSet3 = pstmt3.executeQuery();
							if (resultSet3.next()) {
								FeeSetupVo.setFeeAmount(resultSet3
										.getDouble("feeAmount"));
								FeeSetupVo.setStatus("Y");
								FeeSetupVo.setFeesettingcode(feeSettingcode);
							} else {
								FeeSetupVo.setFeeAmount(0.00);
								FeeSetupVo.setStatus("N");
							}
						}
						feeNamelist.add(FeeSetupVo);
					}
				}
				feeSetupVo.setFeeNamelist(feeNamelist);
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
				+ " Control in ClassFeeSetupDaoImpl: getAllFees  Ending");

		return allfeeslist;
	}

	public synchronized int insertApproveFees(
			ArrayList<ClassFeeSetupPojo> approvefeelist) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: insertApproveFees Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		String setupcode = null;
		int setupcount = 0;
		int fee_count = 0;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			System.out.println("list size ::: " + approvefeelist.size());

			for (int i = 0; i < approvefeelist.size(); i++) {

				pstmt1 = conn
						.prepareStatement(ClassFeeSetupDetails.FEE_SETUP_COUNT);
				pstmt1.setString(1, approvefeelist.get(i).getClassId());
				pstmt1.setString(2, approvefeelist.get(i).getAcadamicYear());
				pstmt1.setString(3, approvefeelist.get(i).getTerm());
				pstmt1.setString(4, approvefeelist.get(i).getSpecialization());
				rs1 = pstmt1.executeQuery();

				while (rs1.next()) {

					setupcount = rs1.getInt(1);
				}

				System.out.println("setupcount ::: " + setupcount);

				if (setupcount == 0) {

					setupcode = IDGenerator.getPrimaryKeyID("campus_fee_setup",conn);

					pst = conn
							.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES_IN_FEE_SETUP);

					pst.setString(1, setupcode);
					pst.setString(2, approvefeelist.get(i).getClassId());
					pst.setString(3, approvefeelist.get(i).getAcadamicYear());
					pst.setString(4, approvefeelist.get(i).getTerm());
					pst.setString(5, approvefeelist.get(i).getSpecialization());
					pst.setString(6, approvefeelist.get(i).getCreatedby());

					System.out.println("insert into fee setup ::: " + pst);

					count = pst.executeUpdate();
				}

				System.out.println("setupcode ::: " + setupcode);
				if (setupcode != null) {
					if (count > 0) {
						PreparedStatement insert_pstmt = conn
								.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES);
						insert_pstmt.setString(1, setupcode);
						insert_pstmt.setString(2, approvefeelist.get(i)
								.getFeeId());
						insert_pstmt.setDouble(3, 0);

						System.out.println("insert into fee setupDetails ::: "
								+ insert_pstmt);

						fee_count = insert_pstmt.executeUpdate();
					}
				} else {
					PreparedStatement insert_pstmt = conn
							.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES_AMT);

					insert_pstmt.setString(1, approvefeelist.get(i)
							.getClassId());
					insert_pstmt.setString(2, approvefeelist.get(i)
							.getAcadamicYear());
					insert_pstmt.setString(3, approvefeelist.get(i).getTerm());
					insert_pstmt.setString(4, approvefeelist.get(i)
							.getSpecialization());
					insert_pstmt.setString(5, approvefeelist.get(i).getFeeId());
					insert_pstmt.setDouble(6, 0);

					System.out.println("insert into fee setupDetails ::: "
							+ insert_pstmt);

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
				+ " Control in ClassFeeSetupDaoImpl: insertApproveFees  Ending");

		return fee_count;
	}

	@Override
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: deleteFees Starting");
		int count = 0;
		boolean flag = false;
		PreparedStatement count_pstmt = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			count_pstmt = conn
					.prepareStatement(ClassFeeSetupDetails.DELETE_FEE_CODE);

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
				+ " Control in ClassFeeSetupDaoImpl: deleteFees  Ending");
		return flag;
	}

	public synchronized int insertFeeAmount(
			ArrayList<ClassFeeSetupPojo> feeSetupList) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: insertApproveFees Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		String setupcode = null;
		int setupcount = 0;
		int fee_count = 0;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			System.out.println("list size ::: " + feeSetupList.size());

			for (int i = 0; i < feeSetupList.size(); i++) {

				pstmt1 = conn.prepareStatement(ClassFeeSetupDetails.FEE_SETUP_COUNT);
				pstmt1.setString(1, feeSetupList.get(i).getClassId());
				pstmt1.setString(2, feeSetupList.get(i).getAcadamicYear());
				pstmt1.setString(3, feeSetupList.get(i).getTerm());
				pstmt1.setString(4, feeSetupList.get(i).getSpecialization());
				pstmt1.setString(5,feeSetupList.get(i).getLocation_id());
				rs1 = pstmt1.executeQuery();

				while (rs1.next()) {

					setupcount = rs1.getInt(1);
					setupcode = rs1.getString("feeSettingcode");
				}

				System.out.println("setupcount ::: " + setupcount);

				if (setupcount == 0) {

					setupcode = IDGenerator.getPrimaryKeyID("campus_fee_setup",conn);

					pst = conn
							.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES_IN_FEE_SETUP);

					pst.setString(1, setupcode);
					pst.setString(2, feeSetupList.get(i).getClassId());
					pst.setString(3, feeSetupList.get(i).getAcadamicYear());
					pst.setString(4, feeSetupList.get(i).getLocation_id());
					pst.setString(5, feeSetupList.get(i).getTerm());
					pst.setString(6, feeSetupList.get(i).getSpecialization());
					pst.setString(7, feeSetupList.get(i).getCreatedby());

					System.out.println("insert into fee setup ::: " + pst);

					count = pst.executeUpdate();
				}

				System.out.println("setupcode ::: " + setupcode);
				if (setupcode != null) {
					if (count > 0) {
						PreparedStatement insert_pstmt = conn
								.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES);
						insert_pstmt.setString(1, setupcode);
						insert_pstmt.setString(2, feeSetupList.get(i)
								.getFeeId());
						insert_pstmt.setString(3, feeSetupList.get(i)
								.getFeeamount());

						System.out.println("insert into fee setupDetails ::: "
								+ insert_pstmt);

						fee_count = insert_pstmt.executeUpdate();
					} else {
						PreparedStatement update_pstmt = conn.prepareStatement(ClassFeeSetupDetails.DELETE_FEE_AMOUNT);

						update_pstmt.setString(1, feeSetupList.get(i).getClassId());
						update_pstmt.setString(2, feeSetupList.get(i).getAcadamicYear());
						update_pstmt.setString(3, feeSetupList.get(i).getTerm());
						update_pstmt.setString(4, feeSetupList.get(i).getSpecialization());
						update_pstmt.setString(5, feeSetupList.get(i).getFeeId());
						System.out.println("Delete fee setupDetails ::: "+ update_pstmt);

						fee_count = update_pstmt.executeUpdate();

						PreparedStatement insert_pstmt = conn.prepareStatement(ClassFeeSetupDetails.INSERT_APPROVED_FEES);
						insert_pstmt.setString(1, setupcode);
						insert_pstmt.setString(2, feeSetupList.get(i).getFeeId());
						insert_pstmt.setString(3, feeSetupList.get(i).getFeeamount());

						System.out.println("insert into fee setupDetails ::: "
								+ insert_pstmt);

						int fee_count1 = insert_pstmt.executeUpdate();
					}

				} else {
					PreparedStatement update_pstmt = conn
							.prepareStatement(ClassFeeSetupDetails.UPDATE_FEE_AMOUNT);
					update_pstmt.setString(1, feeSetupList.get(i)
							.getFeeamount());
					update_pstmt.setString(2, feeSetupList.get(i).getClassId());
					update_pstmt.setString(3, feeSetupList.get(i)
							.getAcadamicYear());
					update_pstmt.setString(4, feeSetupList.get(i).getTerm());
					update_pstmt.setString(5, feeSetupList.get(i)
							.getSpecialization());
					update_pstmt.setString(6, feeSetupList.get(i).getFeeId());
					System.out.println("update fee setupDetails ::: "
							+ update_pstmt);

					fee_count = update_pstmt.executeUpdate();

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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pst != null && !pst.isClosed()) {
					pst.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in ClassFeeSetupDaoImpl: insertApproveFees  Ending");

		return fee_count;
	}

	public int validateFeeCount(String currentaccyear, String sec,
			String classId) {

		int count = 0;
		PreparedStatement count_pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {

			connection = JDBCConnection.getSeparateGodaddyConnection();
			count_pstmt = connection
					.prepareStatement(ClassFeeSetupDetails.GET_FEE_COUNT);
			count_pstmt.setString(1, classId);
			count_pstmt.setString(2, currentaccyear);

			System.out.println("GET_FEE_COUNT " + count_pstmt);

			rs = count_pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);

				System.out.println("Ccc" + count);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

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
				+ " Control in ClassFeeSetupDaoImpl: insertFeeAmount  Ending");
		return count;
	}

	@Override
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: getAllFees Starting");

		ArrayList<ClassFeeSetupVo> allfeeslist = new ArrayList<ClassFeeSetupVo>();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ClassFeeSetupDetails.GET_FEE_HEADING);
			pstmt.setString(1, feeSetupPojo.getClassId());
			pstmt.setString(2, feeSetupPojo.getLocation_id());
			pstmt.setString(3, feeSetupPojo.getAcadamicYear());
			pstmt.setString(4, feeSetupPojo.getClassId());
			pstmt.setString(5, feeSetupPojo.getLocation_id());
			System.out.println("classfeesetupdetails:" +pstmt);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				count++;
				ClassFeeSetupVo feeSetupVo = new ClassFeeSetupVo();
				feeSetupVo.setSno(count);
				feeSetupVo.setClassname(resultSet.getString("className"));
				feeSetupVo.setAccyear(resultSet.getString("year"));
				feeSetupVo.setSpecilazationCount(resultSet.getInt("SpecializationCount"));

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
				+ " Control in ClassFeeSetupDaoImpl: getAllFees  Ending");

		return allfeeslist;
	}

	@Override
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in ClassFeeSetupDaoImpl: insertStudentXSL Starting");

		Set<ClassFeeSetupVo> failurelistOnDiompl = new LinkedHashSet<ClassFeeSetupVo>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		Connection conn =null;
		PreparedStatement ps_collection_count = null;
		ResultSet rs_collection_count = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps3 = null;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			for(int i=0;i<successlist.size();i++){
				
				int result = 0;
				String termid=null;
				String stDate[] = null;
				String endDate[] = null;
				String accyear = successlist.get(i).getAcadamicYear();
				String stuid = successlist.get(i).getStudentId();
				String classId=successlist.get(i).getClassId();
				double totamtpaid =successlist.get(i).getTotFeeAmt();
				PreparedStatement gettermdetails = conn.prepareStatement("SELECT termname,termid,startdate,enddate FROM campus_fee_termdetails WHERE accyear = ? AND locationId = ? ORDER BY startdate");
				gettermdetails.setString(1,accyear);
				gettermdetails.setString(2,successlist.get(i).getLocation_id().split(",")[0]);
				ResultSet gettermdetailsrs = gettermdetails.executeQuery();
				
				while(gettermdetailsrs.next()){
					if(totamtpaid!=0) {
					double termwisetot = 0.0;
					double fineAmount =0.0;
					double totaltermamount = 0.0;
					double balance_amount=0.0;
					double advance_amount=0.0;
					double prepaidamount=0.0;
					double checkamt=0.0;
					termid = gettermdetailsrs.getString("termid");
					PreparedStatement pstmtpaids = conn.prepareStatement("SELECT amount_paid,balance_amount,advance_amount,termcode,paidDate FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ? ORDER BY createdtime desc LIMIT 1");
					pstmtpaids.setString(1, stuid.trim());
					pstmtpaids.setString(2, accyear.trim());
					ResultSet rspaids = pstmtpaids.executeQuery();
					if(rspaids.next()){
						balance_amount=rspaids.getDouble("balance_amount");
						advance_amount=rspaids.getDouble("advance_amount");
						prepaidamount=rspaids.getDouble("amount_paid");
					}
					rspaids.close();
					pstmtpaids.close();
				
					stDate = gettermdetailsrs.getString("startdate").split("-");
					endDate = gettermdetailsrs.getString("enddate").split("-");
					
					String getfeesettingcode1 = null;
					getfeesettingcode1 = FeeExcelUploadUtil.getfeesettingCode(gettermdetailsrs.getString("termid"),accyear,successlist.get(i).getLocation_id());
					
					if(getfeesettingcode1!=null && !getfeesettingcode1.equalsIgnoreCase("NotSet")){
						
					
					
					        PreparedStatement getfineamt = conn.prepareStatement("SELECT * FROM campus_fineconfiguration where classId=? and termid=? ORDER BY date");
					        getfineamt.setString(1, classId);
					        getfineamt.setString(2, termid);
					        ResultSet getfineamtrs = getfineamt.executeQuery();
					        while(getfineamtrs.next()){
								String isApp=getfineamtrs.getString("IsApplicable");
								if(isApp.equalsIgnoreCase("Y")){
									
									
									if(java.sql.Date.valueOf(HelperClass.convertUIToDatabase(successlist.get(i).getPaidDate())).compareTo(getfineamtrs.getDate("date")) > 0){
										fineAmount=getfineamtrs.getDouble("amount");
									}
									
									
								}
								else{
									fineAmount=0.0;
								}
							
					       }
						
						
						PreparedStatement getfeeamt = conn.prepareStatement("SELECT SUM(feeAmount) FROM campus_fee_setupdetails WHERE feeSettingCode ="+"'"+getfeesettingcode1+"'");
						ResultSet getfeeamtrs = getfeeamt.executeQuery();
						while(getfeeamtrs.next()){
							termwisetot = getfeeamtrs.getDouble(1)+balance_amount-advance_amount;
						}

						if(totamtpaid > (termwisetot+fineAmount)){
							
						totaltermamount = fineAmount+termwisetot;
						ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.GET_COLLECTION_UPDATE_CNT);
						ps_collection_count.setString(1, stuid.trim());
						ps_collection_count.setString(2, accyear.trim());
						ps_collection_count.setString(3, gettermdetailsrs.getString("termid"));

						rs_collection_count=ps_collection_count.executeQuery();

						String feeCollectionCount=null;

						while(rs_collection_count.next()){
							feeCollectionCount=rs_collection_count.getString(1);
						}
						String paidDate = HelperClass.convertUIToDatabase(successlist.get(i).getPaidDate());
						if(feeCollectionCount==null){
							conn.setAutoCommit(false);
							double totalamt = 0;
							double dueamt =0.0;
							double advance=0.0;
							double paidamount= successlist.get(i).getTotFeeAmt();
							
							String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",conn);
							//Inserting Into campus_fee_collection table
							ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);

							ps_insertPlan.setString(1,primaryKey);
							ps_insertPlan.setString(2,stuid);
							ps_insertPlan.setString(3,accyear);
							ps_insertPlan.setString(4,gettermdetailsrs.getString("termid"));
							ps_insertPlan.setString(5,"Y");
							ps_insertPlan.setDouble(6,totaltermamount);
							ps_insertPlan.setDouble(7,termwisetot);
							ps_insertPlan.setDouble(8,0.0);//duesCarry
							ps_insertPlan.setDouble(9,0.0);//advance
							ps_insertPlan.setString(10,paidDate);
							ps_insertPlan.setString(11,successlist.get(i).getCreatedBy());
							ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
							ps_insertPlan.setDouble(13,termwisetot+fineAmount);//paid
							ps_insertPlan.setDouble(14,0.0);//duesCarry
							ps_insertPlan.setDouble(15,fineAmount);
							ps_insertPlan.setString(16,successlist.get(i).getParticularNo());
							ps_insertPlan.setString(17,successlist.get(i).getPaymentmode());
							ps_insertPlan.setString(18,successlist.get(i).getDdDate());
							ps_insertPlan.setString(19,successlist.get(i).getBankName());
							ps_insertPlan.setDouble(20,0.0);
							ps_insertPlan.setDouble(21,successlist.get(i).getTotFeeAmt());
							
							int count = ps_insertPlan.executeUpdate();
							
							// Inserting into campus_fee_indetail
							ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
									"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

							ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",conn));
							ps2.setString(2,stuid);
							ps2.setString(3,accyear);
							ps2.setString(4,gettermdetailsrs.getString("termid"));
							ps2.setDouble(5,0.0);//getTot_paid_amt
							ps2.setDouble(6,totaltermamount);//getTot_actual_amt
							ps2.setDouble(7,0.0);//getOutstanding_balance
							ps2.setDouble(8,totaltermamount);//getCurrent_payment
							ps2.setDouble(9,0.0);//getTot_concession_amt
							ps2.setDouble(10,0.0);//getConcession
							ps2.setDate(11,HelperClass.getCurrentSqlDate());
							ps2.setString(12,successlist.get(i).getCreatedBy());
							ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
							ps2.executeUpdate();

							if(count > 0){
								// Inserting into campus_fee_collectiondetails table
								PreparedStatement getfeecode = conn.prepareStatement("SELECT fm.FeeCode,FeeName,feeAmount FROM campus_fee_master fm JOIN campus_fee_setupdetails fs ON fs.feecode = fm.FeeCode WHERE fs.feeSettingcode = ?");
								getfeecode.setString(1,getfeesettingcode1);
								ResultSet getfeecoders = getfeecode.executeQuery();
								while(getfeecoders.next()){
									ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
									ps1.setString(1,primaryKey);
									ps1.setString(2,getfeecoders.getString("FeeCode"));
									ps1.setDouble(3,getfeecoders.getDouble("feeAmount"));
									ps1.setDouble(4,getfeecoders.getDouble("feeAmount"));
									ps1.setDouble(5,0.0);
									ps1.setDouble(6,0.0);
									ps1.setDouble(7,0.0);
								
									result=ps1.executeUpdate();
									// Inserting into campus_fee_reciept table
									PreparedStatement ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
									ps_insertReciept.setString(1,primaryKey);
									ps_insertReciept.setString(2,getfeecoders.getString("FeeCode"));
									ps_insertReciept.setDouble(3,getfeecoders.getDouble("feeAmount"));
									ps_insertReciept.setDouble(4,getfeecoders.getDouble("feeAmount"));
									ps_insertReciept.setDouble(5,0.0);
									ps_insertReciept.setDouble(6,0.0);
									ps_insertReciept.setDouble(7,0.0);
									ps_insertReciept.executeUpdate();
								}
							}
							if(result>0){
								//Inserting into campus_fee_collection_details
								PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);

								ps_insert_detail.setString(1,stuid);
								ps_insert_detail.setString(2,accyear);
								ps_insert_detail.setString(3,gettermdetailsrs.getString("termid"));
								ps_insert_detail.setString(4,"Y");
								ps_insert_detail.setDouble(8,0.0);
								ps_insert_detail.setDouble(6,totaltermamount); //actual amt
								ps_insert_detail.setDouble(7,0.0);
								ps_insert_detail.setDouble(5,totaltermamount);
								ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
								ps_insert_detail.setString(10,successlist.get(i).getCreatedBy());
								ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
								ps_insert_detail.setDouble(12,totaltermamount);//amt paid
								ps_insert_detail.setDouble(13,0.0);
								ps_insert_detail.executeUpdate();
								checkamt=termwisetot+fineAmount;
								conn.commit();
							}
							
						}else{


							String termcode= null;
							List <Double> prevPaidAmountList=new ArrayList<Double>();
							double payingAmount=successlist.get(i).getTotFeeAmt();
							 List<String> prepaiddatelist = new ArrayList<String>();
							 String flag="true";
							PreparedStatement pstmtpaid = conn.prepareStatement("SELECT paidByExcel,balance_amount,advance_amount,termcode,paidDate FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ?");
							pstmtpaid.setString(1, stuid.trim());
							pstmtpaid.setString(2, accyear.trim());
							ResultSet rspaid = pstmtpaid.executeQuery();
							while(rspaid.next()){
								termcode = rspaid.getString("termcode");
							String	prepaiddate = rspaid.getString("paidDate");
							double prevPaidAmount=rspaid.getDouble("paidByExcel");
								prepaiddatelist.add(prepaiddate);
								prevPaidAmountList.add(prevPaidAmount);
							}
							for (int m = 0; m < prepaiddatelist.size(); m++) {
					            if (prepaiddatelist.get(m).equalsIgnoreCase(paidDate) && prevPaidAmountList.get(m)==payingAmount) {
					            	flag="false";
					            	System.out.println(prepaiddatelist.get(m) +"   "+flag);
					            	System.out.println(successlist.get(i).getTotFeeAmt() +"   "+prepaidamount);
					            }
					        }
							if(flag.equalsIgnoreCase("false")){
								ClassFeeSetupVo uploadFeeXSLVo = new ClassFeeSetupVo();
								if(successlist.get(i).getPaymentmode().equalsIgnoreCase("cash")){
									uploadFeeXSLVo.setPaymentdate(successlist.get(i).getPaidDate());
								}else if(successlist.get(i).getPaymentmode().equalsIgnoreCase("dd") || successlist.get(i).getPaymentmode().equalsIgnoreCase("cheque")){
									uploadFeeXSLVo.setPaymentdate(successlist.get(i).getDdDate());
								}
								uploadFeeXSLVo.setStuFname(successlist.get(i).getStuFname());
								uploadFeeXSLVo.setStuLname(successlist.get(i).getStuLname());
								uploadFeeXSLVo.setAdmissionNo(successlist.get(i).getAdmissionNo());
								uploadFeeXSLVo.setAcadamicyear(successlist.get(i).getAccYear());
								uploadFeeXSLVo.setClassname(successlist.get(i).getClassName());
								uploadFeeXSLVo.setLocationName(successlist.get(i).getLocationName());
								uploadFeeXSLVo.setTermid(successlist.get(i).getTermName());
								uploadFeeXSLVo.setFeeamount(successlist.get(i).getTotFeeAmt()+"");
								uploadFeeXSLVo.setFineAmount(successlist.get(i).getFineamt()+"");
								uploadFeeXSLVo.setPaymentmode(successlist.get(i).getPaymentmode());
								uploadFeeXSLVo.setReason(gettermdetailsrs.getString("termname")+" "+"Alraedy Paid else change the paid date");
								failurelistOnDiompl.add(uploadFeeXSLVo);
								break;
							}else{
								
								System.out.println("we will check again gr "+stuid.trim());
								
						}
						}
					}
						else if(totamtpaid > 0 && totamtpaid < (termwisetot+fineAmount)){
							String paidDate = HelperClass.convertUIToDatabase(successlist.get(i).getPaidDate());
							totaltermamount = fineAmount+termwisetot;
							System.out.println("totaltermamount>="+totaltermamount);
							ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.GET_COLLECTION_UPDATE_CNT);
							ps_collection_count.setString(1, stuid.trim());
							ps_collection_count.setString(2, accyear.trim());
							ps_collection_count.setString(3, gettermdetailsrs.getString("termid"));

						
							rs_collection_count=ps_collection_count.executeQuery();

							String feeCollectionCount=null;

							while(rs_collection_count.next()){
								feeCollectionCount=rs_collection_count.getString(1);
							}
							
							if(feeCollectionCount==null){
								conn.setAutoCommit(false);
								double totalamt = 0;
								double dueamt =0.0;
								double advance=0.0;
								double paidamount= successlist.get(i).getTotFeeAmt();
								
								String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",conn);
								//Inserting Into campus_fee_collection table
								ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);

								ps_insertPlan.setString(1,primaryKey);
								ps_insertPlan.setString(2,stuid);
								ps_insertPlan.setString(3,accyear);
								ps_insertPlan.setString(4,gettermdetailsrs.getString("termid"));
								ps_insertPlan.setString(5,"Y");
								ps_insertPlan.setDouble(6,totaltermamount);
								ps_insertPlan.setDouble(7,termwisetot);
								ps_insertPlan.setDouble(8,totaltermamount -totamtpaid );//duesCarry
								ps_insertPlan.setDouble(9,0.0);//advance
								ps_insertPlan.setString(10,paidDate);
								ps_insertPlan.setString(11,successlist.get(i).getCreatedBy());
								ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
								ps_insertPlan.setDouble(13,totamtpaid);//paid
								ps_insertPlan.setDouble(14,totaltermamount -totamtpaid);//duesCarry
								ps_insertPlan.setDouble(15,fineAmount);
								ps_insertPlan.setString(16,successlist.get(i).getParticularNo());
								ps_insertPlan.setString(17,successlist.get(i).getPaymentmode());
								ps_insertPlan.setString(18,successlist.get(i).getDdDate());
								ps_insertPlan.setString(19,successlist.get(i).getBankName());
								ps_insertPlan.setDouble(20,0.0);
								ps_insertPlan.setDouble(21,successlist.get(i).getTotFeeAmt());
								int count = ps_insertPlan.executeUpdate();
								
								// Inserting into campus_fee_indetail
								ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
										"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

								ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",conn));
								ps2.setString(2,stuid);
								ps2.setString(3,accyear);
								ps2.setString(4,gettermdetailsrs.getString("termid"));
								ps2.setDouble(5,0.0);//getTot_paid_amt
								ps2.setDouble(6,termwisetot);//getTot_actual_amt
								ps2.setDouble(7,0.0);//getOutstanding_balance
								ps2.setDouble(8,totamtpaid);//getCurrent_payment
								ps2.setDouble(9,0.0);//getTot_concession_amt
								ps2.setDouble(10,0.0);//getConcession
								ps2.setDate(11,HelperClass.getCurrentSqlDate());
								ps2.setString(12,successlist.get(i).getCreatedBy());
								ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
								ps2.executeUpdate();

								if(count>0){
									// Inserting into campus_fee_collectiondetails table
									PreparedStatement getfeecode = conn.prepareStatement("SELECT fm.FeeCode,FeeName,feeAmount FROM campus_fee_master fm JOIN campus_fee_setupdetails fs ON fs.feecode = fm.FeeCode WHERE fs.feeSettingcode = ?");
									//getfeecode.setString(1,successlist.get(i).getFeeSettingCode());
									getfeecode.setString(1,getfeesettingcode1);
									ResultSet getfeecoders = getfeecode.executeQuery();
									while(getfeecoders.next()){
										ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
										ps1.setString(1,primaryKey);
										ps1.setString(2,getfeecoders.getString("FeeCode"));
										ps1.setDouble(3,getfeecoders.getDouble("feeAmount"));
										ps1.setDouble(4,getfeecoders.getDouble("feeAmount"));
										ps1.setDouble(5,0.0);
										ps1.setDouble(6,0.0);
										ps1.setDouble(7,0.0);
									
										result=ps1.executeUpdate();
										// Inserting into campus_fee_reciept table
										PreparedStatement ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
										ps_insertReciept.setString(1,primaryKey);
										ps_insertReciept.setString(2,getfeecoders.getString("FeeCode"));
										ps_insertReciept.setDouble(3,getfeecoders.getDouble("feeAmount"));
										ps_insertReciept.setDouble(4,getfeecoders.getDouble("feeAmount"));
										ps_insertReciept.setDouble(5,0.0);
										ps_insertReciept.setDouble(6,0.0);
										ps_insertReciept.setDouble(7,0.0);
										ps_insertReciept.executeUpdate();
									}
								}
								if(result>0){
									//Inserting into campus_fee_collection_details
									PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);

									ps_insert_detail.setString(1,stuid);
									ps_insert_detail.setString(2,accyear);
									ps_insert_detail.setString(3,gettermdetailsrs.getString("termid"));
									ps_insert_detail.setString(4,"Y");
									ps_insert_detail.setDouble(8,0.0);
									ps_insert_detail.setDouble(6,termwisetot); //actual amt
									ps_insert_detail.setDouble(7,0.0);
									ps_insert_detail.setDouble(5,0.0);
									ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
									ps_insert_detail.setString(10,successlist.get(i).getCreatedBy());
									ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
									ps_insert_detail.setDouble(12,totamtpaid);//amt paid
									ps_insert_detail.setDouble(13,0.0);
									ps_insert_detail.executeUpdate();
									checkamt=totamtpaid;
									conn.commit();
								}
								
							}else{


								String termcode= null;
								List <Double> prevPaidAmountList=new ArrayList<Double>();
								double payingAmount=successlist.get(i).getTotFeeAmt();
								 List<String> prepaiddatelist = new ArrayList<String>();
								 String flag="true";
								PreparedStatement pstmtpaid = conn.prepareStatement("SELECT paidByExcel,balance_amount,advance_amount,termcode,paidDate FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ?");
								pstmtpaid.setString(1, stuid.trim());
								pstmtpaid.setString(2, accyear.trim());
								ResultSet rspaid = pstmtpaid.executeQuery();
								while(rspaid.next()){
									termcode = rspaid.getString("termcode");
								String	prepaiddate = rspaid.getString("paidDate");
								double prevPaidAmount=rspaid.getDouble("paidByExcel");
									prepaiddatelist.add(prepaiddate);
									prevPaidAmountList.add(prevPaidAmount);
								}
								for (int m = 0; m < prepaiddatelist.size(); m++) {
						            if (prepaiddatelist.get(m).equalsIgnoreCase(paidDate) && prevPaidAmountList.get(m)==payingAmount) {
						            	flag="false";
						            	System.out.println(prepaiddatelist.get(m) +"   "+flag);
						            	System.out.println(successlist.get(i).getTotFeeAmt() +"   "+prepaidamount);
						            }
						        }
								if(flag.equalsIgnoreCase("false")){
									ClassFeeSetupVo uploadFeeXSLVo = new ClassFeeSetupVo();
									if(successlist.get(i).getPaymentmode().equalsIgnoreCase("cash")){
										uploadFeeXSLVo.setPaymentdate(successlist.get(i).getPaidDate());
									}else if(successlist.get(i).getPaymentmode().equalsIgnoreCase("dd") || successlist.get(i).getPaymentmode().equalsIgnoreCase("cheque")){
										uploadFeeXSLVo.setPaymentdate(successlist.get(i).getDdDate());
									}
									uploadFeeXSLVo.setStuFname(successlist.get(i).getStuFname());
									uploadFeeXSLVo.setStuLname(successlist.get(i).getStuLname());
									uploadFeeXSLVo.setAdmissionNo(successlist.get(i).getAdmissionNo());
									uploadFeeXSLVo.setAcadamicyear(successlist.get(i).getAccYear());
									uploadFeeXSLVo.setClassname(successlist.get(i).getClassName());
									uploadFeeXSLVo.setLocationName(successlist.get(i).getLocationName());
									uploadFeeXSLVo.setTermid(successlist.get(i).getTermName());
									uploadFeeXSLVo.setFeeamount(successlist.get(i).getTotFeeAmt()+"");
									uploadFeeXSLVo.setFineAmount(successlist.get(i).getFineamt()+"");
									uploadFeeXSLVo.setPaymentmode(successlist.get(i).getPaymentmode());
									uploadFeeXSLVo.setReason(gettermdetailsrs.getString("termname")+" "+"Alraedy Paid else change the paid date");
									failurelistOnDiompl.add(uploadFeeXSLVo);
									break;
								}else{
									System.out.println("we will chceck again "+stuid.trim());
									
									/*
									int count = 0;
									String startdate=null;
									String nextTerm="TRM"+(Integer.parseInt(termcode.split("M")[1])+1);
									PreparedStatement checkcount = conn.prepareStatement("SELECT COUNT(*) FROM campus_fee_termdetails WHERE termid ="+"'"+nextTerm+"'");
									ResultSet checkcountrs = checkcount.executeQuery();
									while(checkcountrs.next()){
										count = checkcountrs.getInt(1);
									}
									if(count >0){
										getfeesettingcode1 = FeeExcelUploadUtil.getfeesettingCode(nextTerm,accyear,successlist.get(i).getLocation_id());
										PreparedStatement getfeeamt1 = conn.prepareStatement("SELECT SUM(feeAmount) FROM campus_fee_setupdetails WHERE feeSettingCode ="+"'"+getfeesettingcode1+"'");
										ResultSet getfeeamtrs1 = getfeeamt1.executeQuery();
										while(getfeeamtrs1.next()){
											termwisetot = getfeeamtrs1.getDouble(1);
										}
										getfeeamtrs1.close();
										getfeeamt1.close();
										getfeeamt1=conn.prepareStatement("SELECT startdate FROM campus_fee_termdetails WHERE termid=?");
										getfeeamt1.setString(1, nextTerm);
										getfeeamtrs1 = getfeeamt1.executeQuery();
										while(getfeeamtrs1.next()){
											startdate = getfeeamtrs1.getString("startdate");
										}
										stDate=startdate.split("-");
										if(successlist.get(i).getPaidDate().compareTo(stDate[2]+"-"+stDate[1]+"-"+stDate[0]) >0){
										
											Calendar cal = Calendar.getInstance();
									        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[0]));
									        cal.set(Calendar.MONTH, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[1]));
									        cal.set(Calendar.YEAR, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[2]));
									        Date firstDate = cal.getTime();

									        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate[2]));
									        cal.set(Calendar.MONTH, Integer.parseInt(endDate[1]));
									        cal.set(Calendar.YEAR, Integer.parseInt(endDate[0]));
									        Date secondDate = cal.getTime();
									       
									        long diff =  firstDate.getTime() - secondDate.getTime();
									        diff = diff / 1000 / 60 / 60 / 24;
									    
									        PreparedStatement getfineamt = conn.prepareStatement("SELECT amount FROM campus_fineconfiguration WHERE"+" "+diff+">=days");
									        ResultSet getfineamtrs = getfineamt.executeQuery();
									        while(getfineamtrs.next()){
									        	fineAmount = getfineamtrs.getDouble("amount");
									       }
										}
										totaltermamount=termwisetot+fineAmount+balance_amount-advance_amount;
										insertFee(getfeesettingcode1,result,totaltermamount,stuid,accyear,nextTerm,paidDate,termwisetot,fineAmount,successlist.get(i),totamtpaid,ps_collection_count,ps_insertPlan,ps2,rs_collection_count,ps1,conn);
									}
							*/}
							}
						}
						else if(totamtpaid > 0 && totamtpaid == (termwisetot+fineAmount)){
							
							String paidDate = HelperClass.convertUIToDatabase(successlist.get(i).getPaidDate());
							totaltermamount = fineAmount+termwisetot;
							System.out.println("totaltermamount=="+totaltermamount);
							ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.GET_COLLECTION_UPDATE_CNT);
							ps_collection_count.setString(1, stuid.trim());
							ps_collection_count.setString(2, accyear.trim());
							ps_collection_count.setString(3, gettermdetailsrs.getString("termid"));

						
							rs_collection_count=ps_collection_count.executeQuery();

							String feeCollectionCount=null;

							while(rs_collection_count.next()){
								feeCollectionCount=rs_collection_count.getString(1);
							}
						
							if(feeCollectionCount==null){
								conn.setAutoCommit(false);
								//insertFee(getfeesettingcode1,result,totaltermamount,stuid,accyear,gettermdetailsrs.getString("termid"),paidDate,termwisetot,fineAmount,successlist.get(i),totamtpaid,ps_collection_count,ps_insertPlan,ps2,rs_collection_count,ps1,conn);
								double totalamt = 0;
								double dueamt =0.0;
								double advance=0.0;
								double paidamount= successlist.get(i).getTotFeeAmt();
								
								String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",conn);
								//Inserting Into campus_fee_collection table
								ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);

								ps_insertPlan.setString(1,primaryKey);
								ps_insertPlan.setString(2,stuid);
								ps_insertPlan.setString(3,accyear);
								ps_insertPlan.setString(4,gettermdetailsrs.getString("termid"));
								ps_insertPlan.setString(5,"Y");
								ps_insertPlan.setDouble(6,totaltermamount);
								ps_insertPlan.setDouble(7,termwisetot);
								ps_insertPlan.setDouble(8,totaltermamount-totamtpaid );//duesCarry
								ps_insertPlan.setDouble(9,0.0);//advance
								ps_insertPlan.setString(10,paidDate);
								ps_insertPlan.setString(11,successlist.get(i).getCreatedBy());
								ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
								ps_insertPlan.setDouble(13,totamtpaid);//paid
								ps_insertPlan.setDouble(14,totaltermamount-totamtpaid);//duesCarry
								ps_insertPlan.setDouble(15,fineAmount);
								ps_insertPlan.setString(16,successlist.get(i).getParticularNo());
								ps_insertPlan.setString(17,successlist.get(i).getPaymentmode());
								ps_insertPlan.setString(18,successlist.get(i).getDdDate());
								ps_insertPlan.setString(19,successlist.get(i).getBankName());
								ps_insertPlan.setDouble(20,0.0);
								ps_insertPlan.setDouble(21,successlist.get(i).getTotFeeAmt());
								int count = ps_insertPlan.executeUpdate();
								
								// Inserting into campus_fee_indetail
								ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
										"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

								ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",conn));
								ps2.setString(2,stuid);
								ps2.setString(3,accyear);
								ps2.setString(4,gettermdetailsrs.getString("termid"));
								ps2.setDouble(5,0.0);//getTot_paid_amt
								ps2.setDouble(6,termwisetot);//getTot_actual_amt
								ps2.setDouble(7,0.0);//getOutstanding_balance
								ps2.setDouble(8,totamtpaid);//getCurrent_payment
								ps2.setDouble(9,0.0);//getTot_concession_amt
								ps2.setDouble(10,0.0);//getConcession
								ps2.setDate(11,HelperClass.getCurrentSqlDate());
								ps2.setString(12,successlist.get(i).getCreatedBy());
								ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
								ps2.executeUpdate();

								if(count>0){
									// Inserting into campus_fee_collectiondetails table
									PreparedStatement getfeecode = conn.prepareStatement("SELECT fm.FeeCode,FeeName,feeAmount FROM campus_fee_master fm JOIN campus_fee_setupdetails fs ON fs.feecode = fm.FeeCode WHERE fs.feeSettingcode = ?");
									//getfeecode.setString(1,successlist.get(i).getFeeSettingCode());
									getfeecode.setString(1,getfeesettingcode1);
									ResultSet getfeecoders = getfeecode.executeQuery();
									while(getfeecoders.next()){
										ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
										ps1.setString(1,primaryKey);
										ps1.setString(2,getfeecoders.getString("FeeCode"));
										ps1.setDouble(3,getfeecoders.getDouble("feeAmount"));
										ps1.setDouble(4,getfeecoders.getDouble("feeAmount"));
										ps1.setDouble(5,0.0);
										ps1.setDouble(6,0.0);
										ps1.setDouble(7,0.0);
									
										result=ps1.executeUpdate();
										// Inserting into campus_fee_reciept table
										PreparedStatement ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
										ps_insertReciept.setString(1,primaryKey);
										ps_insertReciept.setString(2,getfeecoders.getString("FeeCode"));
										ps_insertReciept.setDouble(3,getfeecoders.getDouble("feeAmount"));
										ps_insertReciept.setDouble(4,getfeecoders.getDouble("feeAmount"));
										ps_insertReciept.setDouble(5,0.0);
										ps_insertReciept.setDouble(6,0.0);
										ps_insertReciept.setDouble(7,0.0);
										ps_insertReciept.executeUpdate();
									}
								}
								if(result>0){
									//Inserting into campus_fee_collection_details
									PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);

									ps_insert_detail.setString(1,stuid);
									ps_insert_detail.setString(2,accyear);
									ps_insert_detail.setString(3,gettermdetailsrs.getString("termid"));
									ps_insert_detail.setString(4,"Y");
									ps_insert_detail.setDouble(8,0.0);
									ps_insert_detail.setDouble(6,termwisetot); //actual amt
									ps_insert_detail.setDouble(7,0.0);
									ps_insert_detail.setDouble(5,0.0);
									ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
									ps_insert_detail.setString(10,successlist.get(i).getCreatedBy());
									ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
									ps_insert_detail.setDouble(12,totamtpaid);//amt paid
									ps_insert_detail.setDouble(13,0.0);
									ps_insert_detail.executeUpdate();
									checkamt=totamtpaid;
									conn.commit();
								}
								
							}else{

								String termcode= null;
								List <Double> prevPaidAmountList=new ArrayList<Double>();
								double payingAmount=successlist.get(i).getTotFeeAmt();
								 List<String> prepaiddatelist = new ArrayList<String>();
								 String flag="true";
								PreparedStatement pstmtpaid = conn.prepareStatement("SELECT paidByExcel,balance_amount,advance_amount,termcode,paidDate FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ?");
								pstmtpaid.setString(1, stuid.trim());
								pstmtpaid.setString(2, accyear.trim());
								ResultSet rspaid = pstmtpaid.executeQuery();
								while(rspaid.next()){
									termcode = rspaid.getString("termcode");
								String	prepaiddate = rspaid.getString("paidDate");
								double prevPaidAmount=rspaid.getDouble("paidByExcel");
									prepaiddatelist.add(prepaiddate);
									prevPaidAmountList.add(prevPaidAmount);
								}
								for (int m = 0; m < prepaiddatelist.size(); m++) {
						            if (prepaiddatelist.get(m).equalsIgnoreCase(paidDate) && prevPaidAmountList.get(m)==payingAmount) {
						            	flag="false";
						            	System.out.println(prepaiddatelist.get(m) +"   "+flag);
						            	System.out.println(successlist.get(i).getTotFeeAmt() +"   "+prepaidamount);
						            }
						        }
								if(flag.equalsIgnoreCase("false")){
									ClassFeeSetupVo uploadFeeXSLVo = new ClassFeeSetupVo();
									if(successlist.get(i).getPaymentmode().equalsIgnoreCase("cash")){
										uploadFeeXSLVo.setPaymentdate(successlist.get(i).getPaidDate());
									}else if(successlist.get(i).getPaymentmode().equalsIgnoreCase("dd") || successlist.get(i).getPaymentmode().equalsIgnoreCase("cheque")){
										uploadFeeXSLVo.setPaymentdate(successlist.get(i).getDdDate());
									}
									uploadFeeXSLVo.setStuFname(successlist.get(i).getStuFname());
									uploadFeeXSLVo.setStuLname(successlist.get(i).getStuLname());
									uploadFeeXSLVo.setAdmissionNo(successlist.get(i).getAdmissionNo());
									uploadFeeXSLVo.setAcadamicyear(successlist.get(i).getAccYear());
									uploadFeeXSLVo.setClassname(successlist.get(i).getClassName());
									uploadFeeXSLVo.setLocationName(successlist.get(i).getLocationName());
									uploadFeeXSLVo.setTermid(successlist.get(i).getTermName());
									uploadFeeXSLVo.setFeeamount(successlist.get(i).getTotFeeAmt()+"");
									uploadFeeXSLVo.setFineAmount(successlist.get(i).getFineamt()+"");
									uploadFeeXSLVo.setPaymentmode(successlist.get(i).getPaymentmode());
									uploadFeeXSLVo.setReason(gettermdetailsrs.getString("termname")+" "+"Alraedy Paid else change the paid date");
									failurelistOnDiompl.add(uploadFeeXSLVo);
									break;
								}else{
									System.out.println("we will chceck again eq "+stuid.trim());
									/*
									int count = 0;
									String startdate=null;
									String nextTerm="TRM"+(Integer.parseInt(termcode.split("M")[1])+1);
									PreparedStatement checkcount = conn.prepareStatement("SELECT COUNT(*) FROM campus_fee_termdetails WHERE termid ="+"'"+nextTerm+"'");
									ResultSet checkcountrs = checkcount.executeQuery();
									while(checkcountrs.next()){
										count = checkcountrs.getInt(1);
									}
									if(count > 0){
										getfeesettingcode1 = FeeExcelUploadUtil.getfeesettingCode(nextTerm,accyear,successlist.get(i).getLocation_id());
										PreparedStatement getfeeamt1 = conn.prepareStatement("SELECT SUM(feeAmount) FROM campus_fee_setupdetails WHERE feeSettingCode ="+"'"+getfeesettingcode1+"'");
										ResultSet getfeeamtrs1 = getfeeamt1.executeQuery();
										while(getfeeamtrs1.next()){
											termwisetot = getfeeamtrs1.getDouble(1);
										}
										getfeeamtrs1.close();
										getfeeamt1.close();
										getfeeamt1=conn.prepareStatement("SELECT startdate FROM campus_fee_termdetails WHERE termid=?");
										getfeeamt1.setString(1, nextTerm);
										getfeeamtrs1 = getfeeamt1.executeQuery();
										while(getfeeamtrs1.next()){
											startdate = getfeeamtrs1.getString("startdate");
										}
										stDate=startdate.split("-");
										if(successlist.get(i).getPaidDate().compareTo(stDate[2]+"-"+stDate[1]+"-"+stDate[0]) >0){
										
											Calendar cal = Calendar.getInstance();
									        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[0]));
									        cal.set(Calendar.MONTH, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[1]));
									        cal.set(Calendar.YEAR, Integer.parseInt(successlist.get(i).getPaidDate().split("-")[2]));
									        Date firstDate = cal.getTime();

									        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate[2]));
									        cal.set(Calendar.MONTH, Integer.parseInt(endDate[1]));
									        cal.set(Calendar.YEAR, Integer.parseInt(endDate[0]));
									        Date secondDate = cal.getTime();
									       
									        long diff =  firstDate.getTime() - secondDate.getTime();
									        diff = diff / 1000 / 60 / 60 / 24;
									    
									        PreparedStatement getfineamt = conn.prepareStatement("SELECT amount FROM campus_fineconfiguration WHERE"+" "+diff+">=days");
									        ResultSet getfineamtrs = getfineamt.executeQuery();
									        while(getfineamtrs.next()){
									        	fineAmount = getfineamtrs.getDouble("amount");
									       }
										}
										
										totaltermamount=termwisetot+fineAmount+balance_amount-advance_amount;
										insertFee(getfeesettingcode1,result,totaltermamount,stuid,accyear,nextTerm,paidDate,termwisetot,fineAmount,successlist.get(i),totamtpaid,ps_collection_count,ps_insertPlan,ps2,rs_collection_count,ps1,conn);
									}
								*/}
							}
						}
						
					}//end of feesettingcode IF
					else{
						ClassFeeSetupVo uploadFeeXSLVo = new ClassFeeSetupVo();
						if(successlist.get(i).getPaymentmode().equalsIgnoreCase("cash")){
							uploadFeeXSLVo.setPaymentdate(successlist.get(i).getPaidDate());
						}else if(successlist.get(i).getPaymentmode().equalsIgnoreCase("dd") || successlist.get(i).getPaymentmode().equalsIgnoreCase("cheque")){
							uploadFeeXSLVo.setPaymentdate(successlist.get(i).getDdDate());
						}
						
						uploadFeeXSLVo.setStuFname(successlist.get(i).getStuFname());
						uploadFeeXSLVo.setStuLname(successlist.get(i).getStuLname());
						uploadFeeXSLVo.setAdmissionNo(successlist.get(i).getAdmissionNo());
						uploadFeeXSLVo.setAcadamicyear(successlist.get(i).getAccYear());
						uploadFeeXSLVo.setClassname(successlist.get(i).getClassName());
						uploadFeeXSLVo.setLocationName(successlist.get(i).getLocationName());
						uploadFeeXSLVo.setTermid(successlist.get(i).getTermName());
						uploadFeeXSLVo.setFeeamount(successlist.get(i).getTotFeeAmt()+"");
						uploadFeeXSLVo.setFineAmount(successlist.get(i).getFineamt()+"");
						uploadFeeXSLVo.setPaymentmode(successlist.get(i).getPaymentmode());
						uploadFeeXSLVo.setReason(gettermdetailsrs.getString("termname")+" "+"Class Fee Set Up Not Found");
						failurelistOnDiompl.add(uploadFeeXSLVo);
					}//end of Else feesetting Code
					
					totamtpaid = totamtpaid - checkamt;
				}
				}
				
			}
		}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(ps_insertPlan !=null && !ps_insertPlan.isClosed()){
						ps_insertPlan.close();
					}
					if(ps1 !=null && !ps1.isClosed()){
						ps1.close();
					}
					if(ps2 !=null && !ps2.isClosed()){
						ps2.close();
					}
					if(ps3 !=null && !ps3.isClosed()){
						ps2.close();
					}
					if(conn!=null && !conn.isClosed()){
						conn.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupDaoImpl: insertStudentXSL  Ending");
		return failurelistOnDiompl;
	}

	@Override
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in ClassFeeSetupDaoImpl: insertStudentXSL Starting");

		Set<ClassFeeSetupVo> failurelistOnDiompl = new LinkedHashSet<ClassFeeSetupVo>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		Connection conn =null;
		PreparedStatement getReiptPs=null;
		PreparedStatement transportdetails=null;
		ResultSet gettermRs=null;
		PreparedStatement gettermpstmt=null;
		ResultSet getReiptRs=null;
		int recieptNo=0;
		PreparedStatement ps_insertPlan = null;
		String stmonth[] = null;
		String endmnth[] = null;
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<successlist.size();i++){
				
				getReiptPs=conn.prepareStatement("SELECT CASE WHEN  MAX(reciept_no) IS NULL THEN '1001' ELSE MAX(reciept_no) END reciept_no FROM campus_tranport_fee_collection_details");
				 getReiptRs=getReiptPs.executeQuery();
				while(getReiptRs.next()){
					recieptNo=getReiptRs.getInt("reciept_no")+1;
				}
				transportdetails=conn.prepareStatement("INSERT INTO campus_transport_fees_payments(receiptno,totalamt,paidAmount,balance,advance) VALUES(?,?,?,?,?)");
				transportdetails.setInt(1, recieptNo);
				transportdetails.setDouble(2, Double.parseDouble(successlist.get(i).getTotalfee()));
				
				transportdetails.setDouble(3, Double.parseDouble(successlist.get(i).getTotalfee()));
				transportdetails.setDouble(4, 0.0);
				transportdetails.setDouble(5, 0.0);
				int countTrans=transportdetails.executeUpdate();
				 transportdetails.close();
				PreparedStatement getmonths = conn.prepareStatement(" SELECT startdate,enddate FROM campus_fee_transport_termdetails WHERE termid = ?");
				getmonths.setString(1,successlist.get(i).getTermId());
				ResultSet monthrs = getmonths.executeQuery();
				while(monthrs.next()){
					stmonth = monthrs.getString(1).split("-");
					endmnth = monthrs.getString(2).split("-");
				}
				double amt = 0.0;
				int count = 0;
				String name = HelperClass.getMothNumberByShortName(successlist.get(i).getStmnth());
				count = Integer.parseInt(name);
				for(int j=0;j<Integer.parseInt(successlist.get(i).getNoofmnths());j++){
					 gettermpstmt=conn.prepareStatement("SELECT * FROM campus_fee_transport_termdetails WHERE CAST(SUBSTRING(startdate,6,2) AS UNSIGNED)<=? AND CAST(SUBSTRING(enddate,6,2) AS UNSIGNED)>=? AND accyear=? AND locationId=?");
					gettermpstmt.setInt(1, count);
					gettermpstmt.setInt(2, count);
					gettermpstmt.setString(3, successlist.get(i).getAcadamicYear());
					gettermpstmt.setString(4, successlist.get(i).getLocation_id());
					 gettermRs=gettermpstmt.executeQuery();
					 String term="";
					 if(gettermRs.next()){
						 term=gettermRs.getString("termid");
					 }
					String stageid="";
					 PreparedStatement stg=conn.prepareStatement("SELECT stage_id FROM campus_student_route_stage_mapping WHERE mapped_id=? AND accyear=? AND month=?");
						stg.setString(1, successlist.get(i).getStudentId());
						stg.setString(2, successlist.get(i).getAcadamicYear());
						stg.setString(3, HelperClass.getMonthFullName(name));
						ResultSet srs=stg.executeQuery();
						if(srs.next()) {
							stageid=srs.getString("stage_id");
						}
						srs.close();
						stg.close();
						
						
						stg=conn.prepareStatement("SELECT amount FROM campus_fee_stage WHERE stage_id=? AND accyear=?");
						stg.setString(1, stageid);
						stg.setString(2, successlist.get(i).getAcadamicYear());
						 srs=stg.executeQuery();
						if(srs.next()) {
							amt=srs.getDouble("amount");
						}
						srs.close();
						stg.close();
					 
					 
					ps_insertPlan = conn.prepareStatement("INSERT INTO campus_tranport_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,amount_paid,balance_amount,paidDate,createdby,createdtime,MonthName,reciept_no,bankname,dd_cheque_no,dd_cheque_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps_insertPlan.setString(1,successlist.get(i).getStudentId());
					ps_insertPlan.setString(2,successlist.get(i).getAcadamicYear());
					ps_insertPlan.setString(3,term);
					ps_insertPlan.setString(4,"Y");
					ps_insertPlan.setDouble(5,amt);
					ps_insertPlan.setDouble(6,amt);
					ps_insertPlan.setDouble(7,0.0);	
					ps_insertPlan.setString(8,HelperClass.convertUIToDatabase(successlist.get(i).getPaidDate()));
					ps_insertPlan.setString(9,successlist.get(i).getCreatedby());
					ps_insertPlan.setTimestamp(10,HelperClass.getCurrentTimestamp());
					ps_insertPlan.setString(11,HelperClass.getMonthFullName(name));
					ps_insertPlan.setInt(12,recieptNo);
					ps_insertPlan.setString(13,successlist.get(i).getBankName());
					ps_insertPlan.setString(15,successlist.get(i).getDdDate());
					ps_insertPlan.setString(14,successlist.get(i).getParticularNo());
					System.out.println("ps_insertPlan:::::"+ps_insertPlan);
					ps_insertPlan.executeUpdate();
					
					count++;
					if(count < 10){
						name = "0"+count;
					}else{
						name=count+"";
					}
					
				}
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (gettermRs != null && !gettermRs.isClosed()) {
					gettermRs.close();
				}
				if (gettermpstmt != null && !gettermpstmt.isClosed()) {
					gettermpstmt.close();
				}
				if (getReiptPs != null && !getReiptPs.isClosed()) {
					getReiptPs.close();
				}
				if (ps_insertPlan != null && !ps_insertPlan.isClosed()) {
					ps_insertPlan.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return failurelistOnDiompl;
	}
public void insertFee(String getfeesettingcode1, int result, double totaltermamount, String stuid, String accyear, String termid, String paidDate, double termwisetot, double fineAmount, ClassFeeSetupPojo classFeeSetupPojo, double totamtpaid, PreparedStatement ps_collection_count, PreparedStatement ps_insertPlan, PreparedStatement ps2, ResultSet rs_collection_count, PreparedStatement ps1, Connection conn) throws Exception{

	
	
	
		
		String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",conn);
		//Inserting Into campus_fee_collection table
		ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);

		ps_insertPlan.setString(1,primaryKey);
		ps_insertPlan.setString(2,stuid);
		ps_insertPlan.setString(3,accyear);
		ps_insertPlan.setString(4,termid);
		ps_insertPlan.setString(5,"Y");
		ps_insertPlan.setDouble(6,termwisetot+fineAmount);
		ps_insertPlan.setDouble(7,termwisetot);
		ps_insertPlan.setDouble(8,totaltermamount-totamtpaid );//duesCarry
		ps_insertPlan.setDouble(9,totamtpaid-totaltermamount);//advance
		ps_insertPlan.setString(10,paidDate);
		ps_insertPlan.setString(11,classFeeSetupPojo.getCreatedBy());
		ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
		ps_insertPlan.setDouble(13,totamtpaid);//paid
		ps_insertPlan.setDouble(14,totaltermamount-totamtpaid);//duesCarry
		ps_insertPlan.setDouble(15,fineAmount);
		ps_insertPlan.setString(16,classFeeSetupPojo.getParticularNo());
		ps_insertPlan.setString(17,classFeeSetupPojo.getPaymentmode());
		ps_insertPlan.setString(18,classFeeSetupPojo.getDdDate());
		ps_insertPlan.setString(19,classFeeSetupPojo.getBankName());
		ps_insertPlan.setDouble(20,0.0);
		int count = ps_insertPlan.executeUpdate();
		
		// Inserting into campus_fee_indetail
		ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
				"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",conn));
		ps2.setString(2,stuid);
		ps2.setString(3,accyear);
		ps2.setString(4,termid);
		ps2.setDouble(5,0.0);//getTot_paid_amt
		ps2.setDouble(6,termwisetot);//getTot_actual_amt
		ps2.setDouble(7,0.0);//getOutstanding_balance
		ps2.setDouble(8,totamtpaid);//getCurrent_payment
		ps2.setDouble(9,0.0);//getTot_concession_amt
		ps2.setDouble(10,0.0);//getConcession
		ps2.setDate(11,HelperClass.getCurrentSqlDate());
		ps2.setString(12,classFeeSetupPojo.getCreatedBy());
		ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
		ps2.executeUpdate();

		if(count>0){
			// Inserting into campus_fee_collectiondetails table
			PreparedStatement getfeecode = conn.prepareStatement("SELECT fm.FeeCode,FeeName,feeAmount FROM campus_fee_master fm JOIN campus_fee_setupdetails fs ON fs.feecode = fm.FeeCode WHERE fs.feeSettingcode = ?");
			//getfeecode.setString(1,successlist.get(i).getFeeSettingCode());
			getfeecode.setString(1,getfeesettingcode1);
			ResultSet getfeecoders = getfeecode.executeQuery();
			while(getfeecoders.next()){
				ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);
				ps1.setString(1,primaryKey);
				ps1.setString(2,getfeecoders.getString("FeeCode"));
				ps1.setDouble(3,getfeecoders.getDouble("feeAmount"));
				ps1.setDouble(4,getfeecoders.getDouble("feeAmount"));
				ps1.setDouble(5,0.0);
				ps1.setDouble(6,0.0);
				ps1.setDouble(7,0.0);
			
				result=ps1.executeUpdate();
				// Inserting into campus_fee_reciept table
				PreparedStatement ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);
				ps_insertReciept.setString(1,primaryKey);
				ps_insertReciept.setString(2,getfeecoders.getString("FeeCode"));
				ps_insertReciept.setDouble(3,getfeecoders.getDouble("feeAmount"));
				ps_insertReciept.setDouble(4,getfeecoders.getDouble("feeAmount"));
				ps_insertReciept.setDouble(5,0.0);
				ps_insertReciept.setDouble(6,0.0);
				ps_insertReciept.setDouble(7,0.0);
				ps_insertReciept.executeUpdate();
			}
		}
		if(result>0){
			//Inserting into campus_fee_collection_details
			PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);

			ps_insert_detail.setString(1,stuid);
			ps_insert_detail.setString(2,accyear);
			ps_insert_detail.setString(3,termid);
			ps_insert_detail.setString(4,"Y");
			ps_insert_detail.setDouble(8,0.0);
			ps_insert_detail.setDouble(6,termwisetot); //actual amt
			ps_insert_detail.setDouble(7,0.0);
			ps_insert_detail.setDouble(5,0.0);
			ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
			ps_insert_detail.setString(10,classFeeSetupPojo.getCreatedBy());
			ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
			ps_insert_detail.setDouble(12,totamtpaid);//amt paid
			ps_insert_detail.setDouble(13,0.0);
			ps_insert_detail.executeUpdate();
		}
		
	


}
}