package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentEnquiryDao;
import com.centris.campus.forms.StudentEnquiryForm;
import com.centris.campus.pojo.StudentEnquiryPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentEnquirySqlUtil;
import com.centris.campus.vo.StudentEnquiryVo;
import com.mysql.jdbc.PreparedStatement;

public class StudentEnquiryDaoImpl implements StudentEnquiryDao {
	private static final Logger logger = Logger
			.getLogger(StudentEnquiryDaoImpl.class);

	@Override
	public List<StudentEnquiryVo> getAllEnquiryDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : getAllEnquiryDetails Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.GET_INACTIVE_ENQUIRY_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentEnquiryVo vo = new StudentEnquiryVo();
				i = i + 1;
				vo.setEnq_sno(String.valueOf(i));
				vo.setEnq_Id(rs.getString("enquiry_id").trim());
				vo.setEnq_fullname(rs.getString("StudentName").trim());
				vo.setEnq_age(rs.getString("age").trim());

				vo.setEnq_classname(rs.getString("ClassName").trim());

				vo.setEnq_conname(rs.getString("contact_name").trim());
				vo.setEnq_conPhno(rs.getString("contact_mobileno").trim());

				vo.setEnq_reg_status(rs.getString("registration_Status").trim());

				vo.setEnq_int_status_id(rs.getString("interaction_Status")
						.trim());

				vo.setEnq_int_status(rs.getString("interaction_Status").trim());

				String isJoin = rs.getString("isJoined").trim();

				if (isJoin.equalsIgnoreCase("Y")) {
					vo.setEnq_join_status("Joined");
				} else if (isJoin.equalsIgnoreCase("N")) {
					vo.setEnq_join_status("Yet To Join");
				}
				vo.setEnq_address(rs.getString("address").trim());

				allDetails.add(vo);
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
				+ " Control in StudentEnquiryDaoImpl : getAllEnquiryDetails Ending");
		return allDetails;
	}

	@Override
	public List<StudentEnquiryVo> getSelectedEnquiryDetails(String Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : getSelectedEnquiryDetails Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.GET_SELECTED_ENQUIRY_DETAILS);
			pstmt.setString(1, Id);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentEnquiryVo vo = new StudentEnquiryVo();
				vo.setEnq_Id(rs.getString("enquiry_id").trim());
				vo.setEnq_fname(rs.getString("fname").trim());
				vo.setEnq_app_no(rs.getString("application_no"));
				vo.setEnq_lname(rs.getString("lname").trim());
				vo.setEnq_dob(HelperClass.convertDatabaseToUI(rs.getString(
						"dob").trim()));
				vo.setEnq_doj(HelperClass.convertDatabaseToUI(rs.getString(
						"expec_doj").trim()));
				vo.setEnq_age(rs.getString("age").trim());
				vo.setEnq_classId(rs.getString("classdetail_id").trim());
				vo.setEnq_accId(rs.getString("acadamicyear_id").trim());
				vo.setEnq_gender(rs.getString("gender").trim());
				vo.setEnq_religion(rs.getString("religion"));
				vo.setEnq_phyhand(rs.getString("physically_challenged").trim());
				vo.setEnq_conname(rs.getString("contact_name").trim());
				vo.setEnq_conPhno(rs.getString("contact_mobileno").trim());
				vo.setEnq_conemailId(rs.getString("contact_mailid"));
				vo.setEnq_address(rs.getString("address").trim());
				vo.setEnq_contype(rs.getString("contact_type").trim());
				vo.setEnq_enquiredby(rs.getString("enquiredby").trim());

				vo.setEnq_int_status(rs.getString("interaction_Status").trim());
				vo.setEnq_reg_status(rs.getString("registration_Status").trim());

				if (rs.getString("interaction_date") == null
						|| rs.getString("interaction_date") == "") {
					vo.setEnq_int_date("");
				} else {
					vo.setEnq_int_date(HelperClass.convertDatabaseToUI(rs
							.getString("interaction_date")));
				}
				if (rs.getString("registration_date") == null
						|| rs.getString("registration_date") == "") {
					vo.setEnq_adm_date("");
				} else {
					vo.setEnq_adm_date(HelperClass.convertDatabaseToUI(rs
							.getString("registration_date")));
				}
				vo.setEnq_join_status(rs.getString("isJoined").trim());

				vo.setStatus("update");
				allDetails.add(vo);
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
				+ " Control in StudentEnquiryDaoImpl : getSelectedEnquiryDetails Ending");
		return allDetails;
	}

	public String saveStudentEnquiry(StudentEnquiryForm studentEnquiryForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : saveStudentEnquiry Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		boolean status1 = false;

		String status = "failure";
		int count = 0;
		int count1 = 0;

		String int_date = null;
		String Adm_date = null;
		Connection conn = null;
		String doj = null;
		String comdate = null;
		String Adddate = null;

		try {
			conn = JDBCConnection.getSeparateConnection();

			if ("update".equals(studentEnquiryForm.getStatus()) != true) {
				if (!(validationMobileno(studentEnquiryForm.getEnq_MobileNo()) || applicationNoValidate(studentEnquiryForm
						.getAppno()))) {

					if (studentEnquiryForm.getEnq_dateofJoin().trim()
							.equalsIgnoreCase("")) {
						doj = null;
					} else {
						doj = HelperClass
								.convertUIToDatabase(studentEnquiryForm
										.getEnq_dateofJoin());
					}

					if (studentEnquiryForm.getCompletiondate().trim()
							.equalsIgnoreCase("")) {
						comdate = null;
					} else {
						comdate = HelperClass
								.convertUIToDatabase(studentEnquiryForm
										.getCompletiondate());
					}

					if (studentEnquiryForm.getAdmissiondate().trim()
							.equalsIgnoreCase("")) {
						Adddate = null;
					} else {
						Adddate = HelperClass
								.convertUIToDatabase(studentEnquiryForm
										.getAdmissiondate());
					}

					pstmt = (PreparedStatement) conn
							.prepareStatement(StudentEnquirySqlUtil.INSERTING_ENQUIRY_VALUES);
					pstmt.setString(1, studentEnquiryForm.getEnquiryid());
					pstmt.setString(2, studentEnquiryForm.getAppno());
					pstmt.setString(3, studentEnquiryForm.getEnq_academicYear());
					pstmt.setString(4, studentEnquiryForm.getEnq_studClassId());
					pstmt.setString(5, studentEnquiryForm.getEnq_FirstName());
					pstmt.setString(6, studentEnquiryForm.getEnq_LastName());
					pstmt.setString(7, HelperClass
							.convertUIToDatabase(studentEnquiryForm
									.getEnq_dateofBirth()));
					pstmt.setString(8, studentEnquiryForm.getEnq_gender());
					pstmt.setString(9, studentEnquiryForm.getEnq_age());
					pstmt.setString(10, doj);
					pstmt.setString(11, studentEnquiryForm.getEnq_religion());
					pstmt.setString(12,
							studentEnquiryForm.getEnq_physicallyChallenged());
					pstmt.setString(13, studentEnquiryForm.getEnq_contacttype());
					pstmt.setString(14, studentEnquiryForm.getEnq_contactname());
					pstmt.setString(15, studentEnquiryForm.getEnq_MobileNo());
					pstmt.setString(16,
							studentEnquiryForm.getEnq_contactmailId());
					pstmt.setString(17, studentEnquiryForm.getEnq_source());
					pstmt.setString(18,
							studentEnquiryForm.getEnq_addressstreet1());
					pstmt.setString(19, studentEnquiryForm.getCreate_username());

					pstmt.setString(20, studentEnquiryForm.getInteraction());
					pstmt.setString(21, comdate);
					pstmt.setString(22, studentEnquiryForm.getAdmissionstatus());
					pstmt.setString(23, Adddate);
					System.out.println(pstmt);
					int i = pstmt.executeUpdate();

					if (i > 0) {

						status = "success";

					}
				} else {
					status = "failure";
				}
			} else {

				if (studentEnquiryForm.getEnq_dateofJoin().trim()
						.equalsIgnoreCase("")) {
					doj = null;
				} else {
					doj = HelperClass.convertUIToDatabase(studentEnquiryForm
							.getEnq_dateofJoin().trim());
				}
				if (studentEnquiryForm.getCompletiondate() == null
						|| studentEnquiryForm.getCompletiondate() == "") {
					int_date = null;
				} else {
					int_date = HelperClass
							.convertUIToDatabase(studentEnquiryForm
									.getCompletiondate());
				}

				if (studentEnquiryForm.getAdmissiondate() == null
						|| studentEnquiryForm.getAdmissiondate() == "") {
					Adm_date = null;
				} else {
					Adm_date = HelperClass
							.convertUIToDatabase(studentEnquiryForm
									.getAdmissiondate());
				}
				pstmt1 = (PreparedStatement) conn
						.prepareStatement(StudentEnquirySqlUtil.CHECKING_STUDENT_VALIDATION);

				pstmt1.setString(1, studentEnquiryForm.getEnq_FirstName());
				pstmt1.setString(2, studentEnquiryForm.getEnq_dateofBirth());
				pstmt1.setString(3, studentEnquiryForm.getEnq_gender());
				pstmt1.setString(4,
						studentEnquiryForm.getEnq_physicallyChallenged());
				pstmt1.setString(5, studentEnquiryForm.getEnq_studClassId());
				pstmt1.setString(6, studentEnquiryForm.getEnq_academicYear());
				pstmt1.setString(7, studentEnquiryForm.getEnq_contacttype());
				pstmt1.setString(8, studentEnquiryForm.getEnq_contactname());
				pstmt1.setString(9, studentEnquiryForm.getEnq_MobileNo());
				pstmt1.setString(10, studentEnquiryForm.getAppno());
				pstmt1.setString(11, studentEnquiryForm.getEnquiryid());
				System.out.println(pstmt1);
				rs = pstmt1.executeQuery();

				while (rs.next()) {
					count1 = rs.getInt(1);
				}
				if (count1 > 0) {
					status1 = true;
				}

				if (status1 != true) {

					pstmt = (PreparedStatement) conn
							.prepareStatement(StudentEnquirySqlUtil.UPDATE_ENQUIRY_STUDENT_DETAILS);
					pstmt.setString(1, studentEnquiryForm.getEnq_academicYear());
					pstmt.setString(2, studentEnquiryForm.getEnq_studClassId());
					pstmt.setString(3, studentEnquiryForm.getEnq_FirstName());
					pstmt.setString(4, studentEnquiryForm.getEnq_LastName());
					pstmt.setString(5, HelperClass
							.convertUIToDatabase(studentEnquiryForm
									.getEnq_dateofBirth()));
					pstmt.setString(6, studentEnquiryForm.getEnq_gender());
					pstmt.setString(7, studentEnquiryForm.getEnq_age());
					pstmt.setString(8, doj);
					pstmt.setString(9, studentEnquiryForm.getEnq_religion());
					pstmt.setString(10,
							studentEnquiryForm.getEnq_physicallyChallenged());
					pstmt.setString(11, studentEnquiryForm.getEnq_contacttype());
					pstmt.setString(12, studentEnquiryForm.getEnq_contactname());
					pstmt.setString(13, studentEnquiryForm.getEnq_MobileNo());
					pstmt.setString(14,
							studentEnquiryForm.getEnq_contactmailId());
					pstmt.setString(15, studentEnquiryForm.getCreate_username());
					pstmt.setString(16, studentEnquiryForm.getInteraction());
					pstmt.setString(17, studentEnquiryForm.getAdmissionstatus());
					pstmt.setString(18, studentEnquiryForm.getAppno());
					pstmt.setString(19, int_date);
					pstmt.setString(20, Adm_date);
					pstmt.setString(21,
							studentEnquiryForm.getEnq_addressstreet1());
					pstmt.setString(22, studentEnquiryForm.getEnquiryid());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();

					if (count > 0) {
						status = "success";
					}
				} else {
					status = "failure";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : saveStudentEnquiry Ending");

		return status;
	}

	public boolean duplicateStudentChecking(
			StudentEnquiryPojo studentenquiryPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl: duplicateStudentChecking  Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		boolean status = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.DUBNLICATE_CHECKING);

			pstmt.setString(1, studentenquiryPojo.getEnq_FirstName());
			pstmt.setString(2, studentenquiryPojo.getEnq_dateofBirth());
			pstmt.setString(3, studentenquiryPojo.getEnq_gender());
			pstmt.setString(4, studentenquiryPojo.getEnq_physicallyChallenged());
			pstmt.setString(5, studentenquiryPojo.getEnq_category());
			pstmt.setString(6, studentenquiryPojo.getEnq_studClassId());
			pstmt.setString(7, studentenquiryPojo.getEnq_academicYear());
			pstmt.setString(8, studentenquiryPojo.getEnq_contacttype());
			pstmt.setString(9, studentenquiryPojo.getEnq_contactname());
			pstmt.setString(10, studentenquiryPojo.getEnq_MobileNo());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				+ " Control in StudentEnquiryDaoImpl: duplicateStudentChecking  Ending");
		return status;
	}

	public boolean validationMobileno(String mobileno) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl: validationMobileno  Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		boolean status = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.VALIDATION_MOBILE_NO);
			pstmt.setString(1, mobileno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				+ " Control in StudentEnquiryDaoImpl: validationMobileno  Ending");
		return status;
	}

	public List<StudentEnquiryVo> getEnquiryDetailsBySearch(String date,
			String name, String interactionStatus, String AdmissionStatus) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : getEnquiryDetailsBySearch Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.GET_ENQUIRY_DETAILS_BYSEARCH);
			pstmt.setString(1, date);
			pstmt.setString(2, name);
			pstmt.setString(3, interactionStatus);
			pstmt.setString(4, AdmissionStatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentEnquiryVo vo = new StudentEnquiryVo();
				i = i + 1;
				vo.setEnq_sno(String.valueOf(i));
				vo.setEnq_Id(rs.getString("enquiry_id").trim());
				vo.setEnq_fullname(rs.getString("StudentName").trim());
				vo.setEnq_fname(rs.getString("fname").trim());
				vo.setEnq_lname(rs.getString("lname").trim());

				vo.setEnq_dob(HelperClass.convertDatabaseToUI(rs.getString(
						"dob").trim()));
				vo.setEnq_age(rs.getString("age").trim());
				vo.setEnq_gender(rs.getString("gender").trim());

				vo.setEnq_doj(HelperClass.convertDatabaseToUI(rs.getString(
						"expec_doj").trim()));
				vo.setEnq_religion(rs.getString("religion").trim());
				vo.setEnq_phyhand(rs.getString("physically_challenged").trim());

				vo.setEnq_classname(rs.getString("ClassName").trim());
				vo.setEnq_classId(rs.getString("classdetail_id").trim());
				vo.setEnq_streamId(rs.getString("classstream_id").trim());
				vo.setEnq_accId(rs.getString("acadamicyear_id").trim());

				vo.setEnq_contype(rs.getString("contact_type").trim());
				vo.setEnq_conname(rs.getString("contact_name").trim());
				vo.setEnq_conPhno(rs.getString("contact_mobileno").trim());
				vo.setEnq_conemailId(rs.getString("contact_mailid").trim());

				String enq_address = rs.getString("street1").trim() + ","
						+ rs.getString("location").trim() + ","
						+ rs.getString("towncity").trim() + ","
						+ rs.getString("state").trim() + ","
						+ rs.getString("country").trim();
				vo.setEnq_address(enq_address);

				vo.setEnq_street1(rs.getString("street1").trim());
				vo.setEnq_street2(rs.getString("street2"));
				vo.setEnq_location(rs.getString("location").trim());
				vo.setEnq_city(rs.getString("towncity").trim());

				vo.setEnq_state(rs.getString("state").trim());
				vo.setEnq_pincode(rs.getString("zippostcode").trim());
				vo.setEnq_country(rs.getString("country").trim());

				vo.setEnq_reg_status_id(rs.getString("registration_Status")
						.trim());

				String regStatus = rs.getString("registration_Status").trim();
				if (regStatus.equalsIgnoreCase("completed")) {
					vo.setEnq_reg_status("Completed");
				} else if (regStatus.equalsIgnoreCase("yettovisit")) {
					vo.setEnq_reg_status("Yet To Visit");

				} else if (regStatus.equalsIgnoreCase("rejected")) {
					vo.setEnq_reg_status("Rejected");
				}
				vo.setEnq_int_status_id(rs.getString("interaction_Status")
						.trim());

				String enqStatus = rs.getString("interaction_Status").trim();
				if (enqStatus.equalsIgnoreCase("completed")) {
					vo.setEnq_int_status("Completed");
				} else if (enqStatus.equalsIgnoreCase("yettovisit")) {
					vo.setEnq_int_status("Yet To Visit");

				} else if (enqStatus.equalsIgnoreCase("rejected")) {
					vo.setEnq_int_status("Rejected");
				}
				String isJoin = rs.getString("isJoined").trim();

				if (isJoin.equalsIgnoreCase("Y")) {
					vo.setEnq_join_status("Joined");
				} else if (isJoin.equalsIgnoreCase("N")) {
					vo.setEnq_join_status("Yet To Join");
				}
				vo.setEnq_enquiredby(rs.getString("enquiredby").trim());
				allDetails.add(vo);
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
				+ " Control in StudentEnquiryDaoImpl : getEnquiryDetailsBySearch Ending");
		return allDetails;
	}

	public boolean updateStudentEnquiry(StudentEnquiryVo studentenquiry) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : updateStudentEnquiry Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		boolean status = false;
		boolean status1 = false;
		int count = 0;
		int count1 = 0;
		String doj = null;
		String int_date = null;
		String Adm_date = null;

		Connection conn = null;

		if (studentenquiry.getEnq_doj().trim().equalsIgnoreCase("")) {
			doj = null;
		} else {
			doj = studentenquiry.getEnq_doj().trim();
		}
		if (studentenquiry.getEnq_int_date() == null
				|| studentenquiry.getEnq_int_date() == "") {
			int_date = null;
		} else {
			int_date = HelperClass.convertUIToDatabase(studentenquiry
					.getEnq_int_date());
		}

		if (studentenquiry.getEnq_adm_date() == null
				|| studentenquiry.getEnq_adm_date() == "") {
			Adm_date = null;
		} else {
			Adm_date = HelperClass.convertUIToDatabase(studentenquiry
					.getEnq_adm_date());
		}
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.CHECKING_STUDENT_VALIDATION);

			pstmt1.setString(1, studentenquiry.getEnq_fname());
			pstmt1.setString(2, studentenquiry.getEnq_dob());
			pstmt1.setString(3, studentenquiry.getEnq_gender());
			pstmt1.setString(4, studentenquiry.getEnq_phyhand());
			pstmt1.setString(5, studentenquiry.getEnq_streamId());
			pstmt1.setString(6, studentenquiry.getEnq_classId());
			pstmt1.setInt(7, Integer.parseInt(studentenquiry.getEnq_accId()));
			pstmt1.setString(8, studentenquiry.getEnq_contype());
			pstmt1.setString(9, studentenquiry.getEnq_conname());
			pstmt1.setString(10, studentenquiry.getEnq_conPhno());
			pstmt1.setString(11, studentenquiry.getEnq_app_no());
			pstmt1.setString(12, studentenquiry.getEnq_sno());

			rs = pstmt1.executeQuery();

			while (rs.next()) {
				count1 = rs.getInt(1);
			}
			if (count1 > 0) {
				status1 = true;
			}

			if (status1 != true) {

				pstmt = (PreparedStatement) conn
						.prepareStatement(StudentEnquirySqlUtil.UPDATE_ENQUIRY_STUDENT_DETAILS);
				pstmt.setString(1, studentenquiry.getEnq_accId());
				pstmt.setString(2, studentenquiry.getEnq_classId());
				pstmt.setString(3, studentenquiry.getEnq_fname());
				pstmt.setString(4, studentenquiry.getEnq_lname());
				pstmt.setString(5, studentenquiry.getEnq_dob());
				pstmt.setString(6, studentenquiry.getEnq_gender());
				pstmt.setString(7, studentenquiry.getEnq_age());
				pstmt.setString(8, doj);
				pstmt.setString(9, studentenquiry.getEnq_religion());
				pstmt.setString(10, studentenquiry.getEnq_phyhand());
				pstmt.setString(11, studentenquiry.getEnq_contype());
				pstmt.setString(12, studentenquiry.getEnq_conname());
				pstmt.setString(13, studentenquiry.getEnq_conPhno());
				pstmt.setString(14, studentenquiry.getEnq_conemailId());
				pstmt.setString(15, studentenquiry.getUsercode());
				pstmt.setString(16, studentenquiry.getEnq_int_status());
				pstmt.setString(17, studentenquiry.getEnq_reg_status());
				pstmt.setString(18, studentenquiry.getEnq_app_no());
				pstmt.setString(19, int_date);
				pstmt.setString(20, Adm_date);
				pstmt.setString(21, studentenquiry.getEnq_sno());
				count = pstmt.executeUpdate();

				if (count > 0) {
					status = true;
				}
			} else {
				status = false;
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : updateStudentEnquiry Ending");
		return status;
	}

	public boolean applicationNoValidate(String appnumber) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl: applicationNoValidate  Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		boolean status = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.VALIDATION_APPLICATION_NO);
			pstmt.setString(1, appnumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				+ " Control in StudentEnquiryDaoImpl: applicationNoValidate  Ending");

		return status;
	}

	public boolean validationPhoneno(String mobileno, String enq_Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl: validationPhoneno  Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		boolean status = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.VALIDATION_PHONE_NO);
			pstmt.setString(1, mobileno);
			pstmt.setString(2, enq_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				+ " Control in StudentEnquiryDaoImpl: validationPhoneno  Ending");
		return status;
	}

	public boolean deleteEnquiryDetails(String Id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryDaoImpl : deleteEnquiryDetails Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		boolean status = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.DELETE_STUDENT_ENQUIRY);
			pstmt.setString(1, Id);

			count = pstmt.executeUpdate();

			if (count > 0) {
				status = true;
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				+ " Control in StudentEnquiryDaoImpl : deleteEnquiryDetails Ending");
		return status;
	}

	public List<StudentEnquiryVo> getSearchEnquiryDetails(String text) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentEnquiryServiceImpl : getSearchEnquiryDetails Starting");
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(StudentEnquirySqlUtil.GET_INACTIVE_ENQUIRY_DETAILS_SEARCH);
			pstmt.setString(1, "%" + text + "%");
			pstmt.setString(2, "%" + text + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentEnquiryVo vo = new StudentEnquiryVo();
				vo.setEnq_Id(rs.getString("enquiry_id").trim());
				vo.setEnq_fullname(rs.getString("StudentName").trim());
				vo.setEnq_age(rs.getString("age").trim());

				vo.setEnq_classname(rs.getString("ClassName").trim());

				vo.setEnq_conname(rs.getString("contact_name").trim());
				vo.setEnq_conPhno(rs.getString("contact_mobileno").trim());

				vo.setEnq_reg_status(rs.getString("registration_Status").trim());

				vo.setEnq_int_status_id(rs.getString("interaction_Status")
						.trim());

				vo.setEnq_int_status(rs.getString("interaction_Status").trim());

				String isJoin = rs.getString("isJoined").trim();

				if (isJoin.equalsIgnoreCase("Y")) {
					vo.setEnq_join_status("Joined");
				} else if (isJoin.equalsIgnoreCase("N")) {
					vo.setEnq_join_status("Yet To Join");
				}
				vo.setEnq_address(rs.getString("address").trim());

				allDetails.add(vo);
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
				+ " Control in StudentEnquiryServiceImpl : getSearchEnquiryDetails Ending");
		return allDetails;
	}
	
	public static void main(String[] args) {
		
		new StudentEnquiryDaoImpl().getSearchEnquiryDetails("fghfg");
	}
}
