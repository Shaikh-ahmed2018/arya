
package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentRegistrationDao;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;
import com.centris.campus.vo.secadmissionformformatVO;
   

public class StudentRegistrationDaoImpl implements StudentRegistrationDao {
	ArrayList<String> dobList = new ArrayList<String>();
	String dob = null;
	String duplicateStudentName = null;
	String stuAdmissionNo = null;
	String academicYear = "";
	String regno = null;

	/**
	 * Logger instance.
	 */
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationDaoImpl.class);


	// To Get student related dropdown values

	public List<StudentRegistrationVo> getAcademicYear() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Starting");
		List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				
				studentRegistrationVo.setAcademicYear(resultSet.getString(MessageConstants.AcademicYear));
				studentRegistrationVo.setAcademicYearId(resultSet.getString(MessageConstants.AcademicYearId));

				studentRegistrationVos.add(studentRegistrationVo);

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
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Ending");
		return studentRegistrationVos;
	}

	public int getStudentMaxId() throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentMaxId Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		int studentMaxid = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_MAXID);
			rs = pst.executeQuery();
			while (rs.next()) {
				studentMaxid = rs.getInt(1);
			}
			if (studentMaxid == 0) {
				studentMaxid = 1;
			}

		} catch (Exception e) {

			logger.error(e);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentMaxId Ending");
		return studentMaxid;
	}

	@Override
	public List<StudentRegistrationVo> getStudentquota() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentquota Starting");
		List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_QUOTA);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentquotaname(resultSet
						.getString("Quota_Name"));
				studentRegistrationVo.setStudentquotaid(resultSet
						.getString("Quota_Code"));

				studentRegistrationVos.add(studentRegistrationVo);

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
				+ " Control in StudentRegistrationDaoImpl : getStudentquota Ending");
		return studentRegistrationVos;

	}

	@Override
	public List<StudentRegistrationVo> getStudentCaste() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentCaste Starting");
		List<StudentRegistrationVo> studentReAdmissionVOList = new ArrayList<StudentRegistrationVo>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_CASTE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentReAdmissionVO = new StudentRegistrationVo();
				studentReAdmissionVO.setStudentcastename(resultSet
						.getString("caste_name"));
				studentReAdmissionVO.setStudentcasteid(resultSet
						.getString("caste_id"));

				studentReAdmissionVOList.add(studentReAdmissionVO);
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
				+ " Control in  StudentRegistrationDaoImpl : getStudentCaste Ending");
		return studentReAdmissionVOList;
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Starting");
		List<StudentRegistrationVo> categorylist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CATEGORY);
			pst.setString(1, schoolLocation);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo
				.setStreemcode(rs.getString("classstream_id_int"));
				registrationVo.setStreemname(rs
						.getString("classstream_name_var"));
				categorylist.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Ending");

		return categorylist;
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			System.out.println("catecory is "+catecory);
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS);
			pst.setString(1, catecory.trim());
			System.out.println("student class "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));


				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getSection(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getSection  Starting");
		String classval=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];
		
		if(classval.equalsIgnoreCase("all")){
			classval="%%";
		}

		if(location.equalsIgnoreCase("all")){
			location="%%";
		}

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SECTION);
			pst.setString(1, classval.trim());
			pst.setString(2, location);
			System.out.println("pst "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setSectioncode(rs
						.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs
						.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getSection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConcessionDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConcessionDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CONCESSION);

			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setConcessionid(rs.getString("concessionid"));
				registrationVo.setConcession(rs.getString("concessionname"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getConcessionDetails  Ending");

		return list;
	}

	public List<TransportTypeVO> transportypedetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : transportypedetails  Starting");
		List<TransportTypeVO> list = new ArrayList<TransportTypeVO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.TRANSPORT_TYPE);

			rs = pst.executeQuery();
			while (rs.next()) {
				TransportTypeVO registrationVo = new TransportTypeVO();
				registrationVo.setTransptyId(rs.getString("type_id"));
				registrationVo.setTransptyname(rs.getString("type_name"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : transportypedetails  Ending");


		return list;

	}

	public List<StageMasterVo> getStageDetails(String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageDetails  Starting");
		List<StageMasterVo> list = new ArrayList<StageMasterVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TRANSPORT_LOCATION);
			pst.setString(1, accyear);
			rs = pst.executeQuery();
			while (rs.next()) {
				StageMasterVo registrationVo = new StageMasterVo();
				registrationVo.setStageCode(rs.getString("stage_id"));
				registrationVo.setStageName(rs.getString("stage_name"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStageDetails  Ending");
		return list;

	}

	public String getTransportCategoryType(String trnsportTypeId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportCategoryType Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String transportTypeStatus = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TRANSPORTSTATUS_TYPE);
			preparedStatement.setString(1, trnsportTypeId);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				transportTypeStatus = rs.getString("type_collectFee");

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
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getTransportCategoryType Ending");
		return transportTypeStatus;
	}

	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl: getSubjectByClass Starting");
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ViewallSubjectsVo> subjectList = new ArrayList<ViewallSubjectsVo>();

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_SUBJECT_DETAILS);
			pstmObj.setString(1, classID);

			rs = pstmObj.executeQuery();

			while (rs.next()) {

				ViewallSubjectsVo vo = new ViewallSubjectsVo();
				vo.setSubjectid(rs.getString("subjectID"));
				vo.setSubjectname(rs.getString("subjectName"));

				subjectList.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl: getSubjectByClass  Ending");
		return subjectList;
	}

	//For validation

	@Override
	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateDuplicateData Starting");
		PreparedStatement pstmt = null;

		int count = 0;
		ResultSet rst = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if (type.equalsIgnoreCase("Add")) {

				pstmt = conn
						.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE_CHECK_ADDTIME);
				pstmt.setString(1, formObj.getStudentFirstName());
				pstmt.setString(2, formObj.getCategory());
				pstmt.setString(3, formObj.getStudClassId());
				pstmt.setString(4, formObj.getStudSectionId());
				
				if (formObj.getFatherName() != null) {
					pstmt.setString(5, formObj.getFatherName().trim());
				} else {
					pstmt.setString(5, "");
				}
				if (formObj.getMotherName() != null) {
					pstmt.setString(6, formObj.getMotherName().trim());
				} else {
					pstmt.setString(6, "");
				}
				/*if (formObj.getDateofBirth() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofBirth().trim()));
				} else {
					pstmt.setString(7, "");
				}*/
				if (formObj.getDateofJoin() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofJoin().trim()));
				} else {
					pstmt.setString(7, "");
				}

				pstmt.setString(8, formObj.getAcademicYear());
				System.out.println("pstmt is "+pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {
					count = rst.getInt(1);
				}
			} else {
				pstmt = conn
						.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE_CHECK_UPDATETIME);
				pstmt.setString(1, formObj.getStudentFirstName());
				pstmt.setString(2, formObj.getCategory());
				pstmt.setString(3, formObj.getStudClassId());
				pstmt.setString(4, formObj.getStudSectionId());

				if (formObj.getFatherName() != null) {
					pstmt.setString(5, formObj.getFatherName());
				} else {
					pstmt.setString(5, "");
				}
				if (formObj.getMotherName() != null) {
					pstmt.setString(6, formObj.getMotherName());
				} else {
					pstmt.setString(6, "");
				}
				/*if (formObj.getDateofBirth() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofBirth()));
				} else {
					pstmt.setString(7, "");
				}*/
				if (formObj.getDateofJoin() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofJoin()));
				} else {
					pstmt.setString(7, "");
				}
				pstmt.setString(8, formObj.getAcademicYear());
				pstmt.setString(9, formObj.getStudentIDgenerator());
				rst = pstmt.executeQuery();

				while (rst.next()) {
					count = rst.getInt(1);
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
				if (rst != null && (!rst.isClosed())) {
					rst.close();
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
				+ " Control in  StudentRegistrationDaoImpl : validateDuplicateData Ending");
		return count;
	}

	public String validateRollNumber(String searchTerm) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateRollNumber Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		String rollNumber=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_ROLL_NUMBER);
			preparedStatement.setString(1, rollNumber);
			preparedStatement.setString(2, location);
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "True";
			} else {
				successMessage = "False";
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
				+ " Control in  StudentRegistrationDaoImpl : validateRollNumber Ending");
		return successMessage;

	}

	@Override
	public String checkApplicationNo(String applicationNo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : checkApplicationNo Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_APPLICATION_NUMBER);
			preparedStatement.setString(1, applicationNo);
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "Application No already Exists";
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
				+ " Control in  StudentRegistrationDaoImpl : checkApplicationNo Ending");
		return successMessage;
	}

	@Override
	public String validatePhoneNo(String phoneNo) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validatePhoneNo Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_PARENT_PHONENO);

			preparedStatement.setString(1, phoneNo);
			preparedStatement.setString(2, phoneNo);
			preparedStatement.setString(3, phoneNo);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			}
			else{
				successMessage = "false";
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
				+ " Control in  StudentRegistrationDaoImpl : validatePhoneNo Ending");
		return successMessage;

	}

	@Override
	public String validateEmail(String email) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateEmail Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_PARENT_EMAILS);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, email);

			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "Email already Exists";
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
				+ " Control in  StudentRegistrationDaoImpl : validateEmail Ending");
		return successMessage;

	}

	//To save student

	@Override
	public Map<String, String> saveStudentRegistration(StudentRegistrationForm registration) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentRegistration Starting");


		int result = 0,result1=0;
		String relationship = "";
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		PreparedStatement precategoryName = null;
		Connection conn = null;
		Savepoint sp = null;
		String categoryName=null;
		PreparedStatement academicYearpre=null;
		String className = null;
		String sectionName = null;
		PreparedStatement prclassName = null;
		PreparedStatement prsectionName = null;
		PreparedStatement pstmcount = null;


		try {


			conn = JDBCConnection.getSeparateGodaddyConnection();
			String studentID = IDGenerator.getPrimaryKeyID("campus_student",conn);
			conn.setAutoCommit(false);

			sp = conn.setSavepoint("SavaStudent");

			precategoryName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_STREAM);
			precategoryName.setString(1, registration.getCategory());

			ResultSet resultSetcategoryName = precategoryName.executeQuery();

			while (resultSetcategoryName.next()) {

				categoryName = resultSetcategoryName.getString("classstream_name_var");
			}

			academicYearpre = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR_BYID);
			academicYearpre.setString(1, registration.getAcademicYear());

			ResultSet rsacademicYear = academicYearpre.executeQuery();

			String academicYear = "";

			while (rsacademicYear.next()) {
				academicYear = rsacademicYear.getString("acadamic_year");
			}
			academicYearpre.close();

			prclassName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_NAME);
			prclassName.setString(1, registration.getStudClassId());

			ResultSet resultSetclassName = prclassName.executeQuery();

			while (resultSetclassName.next()) {
				className = resultSetclassName.getString("classdetails_name_var");
			}

			prsectionName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SECTION_NAME);

			prsectionName.setString(1, registration.getStudSectionId());
			ResultSet resultSetsectionName = prsectionName.executeQuery();

			while (resultSetsectionName.next()) {

				sectionName = resultSetsectionName.getString("classsection_name_var");

			}

			pstmcount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_ACADEMIC_YEAR);
			ResultSet rs = pstmcount.executeQuery();

			rs.next();
			int count = rs.getInt(1);

			if (categoryName != null)

				stuAdmissionNo = StringUtilContants.STUDENT_ADMISSION_NO + "-";
			if (count < 9) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "000" + (++i) + "/"
						+ academicYear;
			} else if (count < 99) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "00" + (++i) + "/"
						+ academicYear;
			} else if (count < 999) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "0" + (++i) + "/"
						+ academicYear;
			} else {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + (++i) + "/" + academicYear;
			}

			PreparedStatement pstmclasscount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_CLASS);
			pstmclasscount.setString(1, registration.getStudClassId());
			ResultSet rsClass = pstmclasscount.executeQuery();
			rsClass.next();
			int classcount = rsClass.getInt(1);

			String studentRegNo = null;
			if (className != null)
				studentRegNo = StringUtilContants.STUDENT_REGISTRATION_NO + "-";

			if (classcount < 9) {
				int i = classcount;
				studentRegNo = studentRegNo + "000" + (++i);
			} else if (classcount < 99) {
				int i = classcount;
				studentRegNo = studentRegNo + "00" + (++i);
			} else if (classcount < 999) {
				int i = classcount;
				studentRegNo = studentRegNo + "0" + (++i);
			} else {
				int i = classcount;
				studentRegNo = studentRegNo + (++i);
			}

			rsacademicYear.close();

			int studentDuplicateCount = 0;


			//To check duplicate student

			PreparedStatement countDuplicate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE);
			countDuplicate.setString(1, registration.getStudentFirstName());
			countDuplicate.setString(2, registration.getCategory());
			countDuplicate.setString(3, registration.getStudClassId());
			countDuplicate.setString(4, registration.getStudSectionId());

			if (registration.getFatherName() != null) {
				countDuplicate.setString(5, registration.getFatherName());
			} else {
				countDuplicate.setString(5, "");
			}
			if (registration.getMotherName() != null) {
				countDuplicate.setString(6, registration.getMotherName());
			} else {
				countDuplicate.setString(6, "");
			}
			countDuplicate.setString(7, HelperClass.convertUIToDatabase(registration.getDateofBirth()));
			countDuplicate.setString(8, HelperClass.convertUIToDatabase(registration.getDateofJoin().trim()));

			ResultSet duplicateRs = countDuplicate.executeQuery();

			while (duplicateRs.next()) {

				studentDuplicateCount = duplicateRs.getInt(1);
			}

			if (studentDuplicateCount != 0) {

				studentIDAdmissionNOMap.put("duplicateMessage","Student Already Registered with these Details");
			} else {

				conn.setAutoCommit(false);

				String admission=registration.getStudentrollno();
				//To store student details

				PreparedStatement preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_REGISTRATION);

				Timestamp createdDate = HelperClass.getCurrentTimestamp();

				preparedStatement.setString(1, studentID);
				preparedStatement.setString(2, studentRegNo); //For roll number
				preparedStatement.setString(3, registration.getStudentrollno().trim()); //For addmission Number
				preparedStatement.setString(4, registration.getAcademicYear().trim());
				preparedStatement.setString(5, stuAdmissionNo); // For registartion number
				preparedStatement.setString(6, registration.getStudentFirstName().trim());
				preparedStatement.setString(7, registration.getStudentLastName().trim());
				preparedStatement.setString(8, registration.getStudentPhoto().trim());
				preparedStatement.setDate(9, HelperClass.getSqlDateFromDdMmYyFormat(registration.getDateofJoin().trim()));
				preparedStatement.setString(10, registration.getCreateUser().trim());
				preparedStatement.setTimestamp(11, createdDate);
				preparedStatement.setString(12, registration.getApplicationNo().trim());
				preparedStatement.setString(13, registration.getMedium());
				preparedStatement.setString(14, registration.getTransferCertificateNo());
				preparedStatement.setString(15, registration.getIsWorkingTeacherGuardian());
				preparedStatement.setString(16, registration.getWorkingTeacherGuardianId());
				preparedStatement.setString(17, registration.getWorkingTeacherName());
				preparedStatement.setString(18, HelperClass.convertUIToDatabase((registration.getDateofBirth().trim())));
				if(registration.getGender() != null && !registration.getGender().equalsIgnoreCase("")){
					preparedStatement.setString(19, registration.getGender().trim());
				}else{
					preparedStatement.setString(19, "");
				}
				preparedStatement.setString(20, registration.getAge().trim());
				preparedStatement.setString(21, registration.getBloodGroup().trim());
				preparedStatement.setString(22, registration.getNationality().trim());
				preparedStatement.setString(23, registration.getMothertongue());
				preparedStatement.setString(24, registration.getAadharno());
				preparedStatement.setString(25,	registration.getPhysicallyChallenged());
				preparedStatement.setString(26,	registration.getPhysicalchalreason());
				preparedStatement.setString(27, registration.getReligion().trim());
				preparedStatement.setString(28, registration.getCaste().trim());
				preparedStatement.setString(29, registration.getCasteCategory().trim());

				preparedStatement.setString(30, registration.getIdentificationMarks().trim());
				preparedStatement.setString(31, registration.getIdentificationMarks1().trim());
				preparedStatement.setString(32, registration.getPreviousHistory().trim());
				preparedStatement.setString(33, registration.getRemarks().trim());

				preparedStatement.setString(34,	registration.getStudentSibilingIdInt());

				if(registration.getPrimaryPerson().equalsIgnoreCase("father")){
					preparedStatement.setDouble(35, registration.getFatherAnnualIncome());
				}else if(registration.getPrimaryPerson().equalsIgnoreCase("mother")){
					preparedStatement.setDouble(35, registration.getMotherAnnualIncome());
				}else if(registration.getPrimaryPerson().equalsIgnoreCase("guardian")){
					preparedStatement.setDouble(35, registration.getGuardianAnnualIncome());
				}else{
					preparedStatement.setDouble(35, 0.0);
				}

				preparedStatement.setString(36, registration.getTransferfile());
				preparedStatement.setString(37, registration.getBirthfile());
				preparedStatement.setString(38, registration.getCertificate1Url());
				preparedStatement.setString(39, registration.getCertificate2Url());
				preparedStatement.setString(40, registration.getCertificate3Url());
				preparedStatement.setString(41, registration.getCertificate4Url());
				preparedStatement.setString(42, registration.getCertificate5Url());
				preparedStatement.setString(43, registration.getTempRegId());
				preparedStatement.setString(44, registration.getStudentstatus());
				preparedStatement.setString(45, registration.getSchoolLocation());
				preparedStatement.setString(46, registration.getStudClassId());
				preparedStatement.setString(47, registration.getPreviousSchool());
				preparedStatement.setString(48, registration.getState());
				preparedStatement.setString(49, registration.getLandLine());
				
				result = preparedStatement.executeUpdate();

				preparedStatement.close();

				//To store student class details

				PreparedStatement scpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_REGISTRATION);

				scpstmt.setString(1, studentID);
				scpstmt.setString(2, registration.getAcademicYear().trim());
				scpstmt.setString(3, registration.getCategory().trim());
				scpstmt.setString(4, registration.getStudClassId().trim());
				scpstmt.setString(5, registration.getStudSectionId().trim());
				if(registration.getSpecilization() != null && !registration.getSpecilization().equalsIgnoreCase("")){
					scpstmt.setString(6, registration.getSpecilization());
				}else{
					scpstmt.setString(6, "-");
				}
				scpstmt.setString(7, registration.getStudentPhoto().trim());
				scpstmt.setString(8, registration.getCreateUser().trim());
				
				scpstmt.setTimestamp(9, createdDate);
				scpstmt.setString(10, registration.getSchoolLocation());
				scpstmt.setString(11, registration.getSchoolLocation());
				if(registration.getFirstlang().equalsIgnoreCase("all")){
					scpstmt.setString(12, "");
				}else{
					scpstmt.setString(12, registration.getFirstlang().trim());
				}
				if(registration.getSecondlang().equalsIgnoreCase("all")){
					scpstmt.setString(13, "");
				}else{
					scpstmt.setString(13, registration.getSecondlang().trim());
				}
				if(registration.getThirdlang().equalsIgnoreCase("all")){
					scpstmt.setString(14, "");
				}else{
					scpstmt.setString(14, registration.getThirdlang().trim());
				}
				/*scpstmt.setString(13, registration.getSecondlang().trim());
				scpstmt.setString(14, registration.getThirdlang().trim());*/
				scpstmt.setString(15, registration.getStudHouseId().trim());
				
				scpstmt.executeUpdate();
				scpstmt.close();
				
				// To Store student contacts
				PreparedStatement contactpstmt=conn.prepareStatement("INSERT INTO campus_students_contacts (studentId,emergencyNo,smsNo) VALUES(?,?,?)");
				contactpstmt.setString(1, studentID);
				contactpstmt.setString(2, registration.getEmergencynumber());
				contactpstmt.setString(3, registration.getSmsnumber());
				
				contactpstmt.executeUpdate();
				contactpstmt.close();
				
				//To store student transport details
				

				PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION);

				stpstmt.setString(1, studentID);
				stpstmt.setString(2, registration.getAcademicYear().trim());
				if(registration.getTransport() !=null && !registration.getTransport().equalsIgnoreCase("")){
					stpstmt.setString(3, registration.getTransport().trim());
				}else{
					stpstmt.setString(3, "");
				}
				stpstmt.setString(4, registration.getTranscategory().trim());
				stpstmt.setString(5, registration.getTranslocation().trim());
				stpstmt.setString(6, registration.getRoute());
				stpstmt.setString(7, registration.getCreateUser().trim());
				stpstmt.setTimestamp(8, createdDate);
				stpstmt.setString(9, registration.getSchoolLocation());

				stpstmt.executeUpdate();
				stpstmt.close();

				//Parent Details 

				String parentId =null;

				System.out.println("registration.getParentId() "+ registration.getParentId());
				if (registration.getParentId()==null || "".equalsIgnoreCase(registration.getParentId())) {

					PreparedStatement preParentReg = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_REG);

					parentId = IDGenerator.getPrimaryKeyID("campus_parents",conn);

					String parentUserName = parentId.substring(3);

					if (registration.getPrimaryPerson().equals("father")) {

						relationship = "father";

						String fatherName = registration.getFatherName();
						fatherName = fatherName.replaceAll(" ", "");

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26, fatherName.concat(parentUserName));
						preParentReg.setString(27, fatherName.concat(parentUserName));

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

						Thread.sleep(1000);
						if (registration.getFatherEmail() == null) {
							new SendMail().sendMailtoChild(registration
									.getFatherEmail().trim(), fatherName
									.concat(parentUserName), fatherName
									.concat(parentUserName));
						}

					} else if (registration.getPrimaryPerson().equals("mother")) {

						relationship = "mother";
						String motherName = registration.getMotherName();
						motherName = motherName.replaceAll(" ", "");

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26, motherName.concat(parentUserName));
						preParentReg.setString(27, motherName.concat(parentUserName));

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

						Thread.sleep(1000);
						if (registration.getMotherEmail() == null) {
							new SendMail().sendMailtoChild(registration
									.getMotherEmail().trim(), motherName
									.concat(parentUserName), motherName
									.concat(parentUserName));
						}

					} else {
						relationship = "guardian";

						String guardianName = registration.getGuardianName();
						guardianName = guardianName.replaceAll(" ", " ");

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26, guardianName.concat(parentUserName));
						preParentReg.setString(27, guardianName.concat(parentUserName));

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

						Thread.sleep(1000);
						if (registration.getGuardianEmail() == null) {
							new SendMail().sendMailtoChild(registration
									.getGuardianEmail().trim(), guardianName
									.concat(parentUserName), guardianName
									.concat(parentUserName));
						}
					}	
					
					
						preParentReg.setString(33, registration.getFatherDepartment());
						preParentReg.setString(34, registration.getFatherDesignation());
						preParentReg.setString(35, registration.getFatherOfficeAddress());
						preParentReg.setString(36, registration.getMotherDepartment());
						preParentReg.setString(37, registration.getMotherDesignation());
						preParentReg.setString(38, registration.getMotherOfficeAddress());
						preParentReg.setString(39, registration.getGuardianDepartment());
						preParentReg.setString(40, registration.getGuardianDesignation());
						preParentReg.setString(41, registration.getGuardianOfficeAddress());

					preParentReg.execute();
					preParentReg.close();


					PreparedStatement preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);

					if (registration.getPrimaryPerson().equals("father")) {

						relationship = "father";

					} else if (registration.getPrimaryPerson().equals("mother")) {

						relationship = "mother";

					} else {

						relationship = "guardian";

					}

					preChildParentUpdate.setString(1, relationship);
					preChildParentUpdate.setString(2, parentId.trim());
					preChildParentUpdate.setString(3, studentID);

					System.out.println("Parent-Child Registration "+preChildParentUpdate);
					preChildParentUpdate.executeUpdate();

					/*temp admission status update*/
					if(registration.getTempRegId() != null || !registration.getTempRegId().equalsIgnoreCase("")){
						PreparedStatement updateTempRegStatus = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_TEMP_REGNO);
						updateTempRegStatus.setString(1, "processed");
						updateTempRegStatus.setString(2, registration.getTempRegId());
						updateTempRegStatus.executeUpdate();

						PreparedStatement updateEnquiryNoStatus = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_ENQUIRY_REGNO);
						updateEnquiryNoStatus.setString(1, "processed");
						updateEnquiryNoStatus.setString(2, registration.getEnquiryId());
						updateEnquiryNoStatus.executeUpdate();

					}


				}else{

					PreparedStatement preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);

					if (registration.getPrimaryPerson().equals("father")) {

						relationship = "father";

					} else if (registration.getPrimaryPerson().equals("mother")) {

						relationship = "mother";

					} else {

						relationship = "guardian";

					}

					preChildParentUpdate.setString(1, relationship);
					preChildParentUpdate.setString(2, registration.getParentId());
					preChildParentUpdate.setString(3, studentID);

					System.out.println("Parent-Child Registration "+preChildParentUpdate);
					preChildParentUpdate.executeUpdate();
				}
				
				if (result > 0) {
					
					
					
					
					PreparedStatement selectAutoGeneration=conn.prepareStatement("SELECT MAX(`student_admissionno_var`) maximcount FROM `campus_student` WHERE locationId=?");
					selectAutoGeneration.setString(1, registration.getSchoolLocation());
					rs=selectAutoGeneration.executeQuery();
					while(rs.next()){
						int mmaximcount = rs.getInt("maximcount");
						String incrementVal=(Integer.toString(mmaximcount).substring(1));
						int IncrementValue=Integer.parseInt(incrementVal);
						PreparedStatement updateAutomaicAdmissionGen = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_AUTO_GEN_ADMISSIONNO);
						updateAutomaicAdmissionGen.setInt(1, IncrementValue);
						updateAutomaicAdmissionGen.setString(2, registration.getSchoolLocation());
						result1=updateAutomaicAdmissionGen.executeUpdate();
					}
					
					if(result1 > 0){
						conn.commit();
						studentIDAdmissionNOMap.put("successMessage","Adding Record Progressing...");
					}else{
						conn.rollback();
						studentIDAdmissionNOMap.put("errorMessage","Student Not Registered");
					}
				} else {
					conn.rollback();
					studentIDAdmissionNOMap.put("errorMessage","Student Not Registered");
				}
			}

		} catch (Exception exception) {
			conn.rollback(sp);
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
			conn.rollback();
		} finally {
			try {

				if (precategoryName != null && (!precategoryName.isClosed())) {
					precategoryName.close();
				}

				if (prclassName  != null && (!prclassName .isClosed())) {
					prclassName .close();
				}

				if (prsectionName  != null && (!prsectionName .isClosed())) {
					prsectionName .close();
				}

				if (pstmcount  != null && (!pstmcount .isClosed())) {
					pstmcount .close();
				}
				if (academicYearpre  != null && (!academicYearpre .isClosed())) {
					academicYearpre .close();
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
				+ " Control in StudentRegistrationDaoImpl : saveStudentRegistration Ending");
		return studentIDAdmissionNOMap;
	}


	//TO modify Student

	//To modify student details

	@Override
	public String modifyStudentDetails(StudentRegistrationForm registration) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : modifyStudentDetails Starting");

		String successMsg = null;
		String relationship = "";
		int resultModify = 0;
		ResultSet rsClass = null;
		ResultSet rsImagePath = null;
		PreparedStatement preChildParentUpdate = null;
		PreparedStatement preParentRegUpdate = null;
		PreparedStatement getImagePath = null;
		PreparedStatement pstmclasscount = null;
		PreparedStatement academicYearpre = null;
		ResultSet rsacademicYear = null;
		ResultSet resultSetclassName = null;
		PreparedStatement prclassName = null;
		ResultSet rsacademicYearId = null;
		ResultSet duplicateRs = null;
		PreparedStatement pstmmodifyObj = null;
		PreparedStatement pstmacademicYearId = null;
		Connection conn = null;
		

		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmmodifyObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_REGISTRATION_MODIFY);

			pstmmodifyObj.setString(1, registration.getStudentFirstName().trim());
			pstmmodifyObj.setString(2, registration.getStudentLastName().trim());
			pstmmodifyObj.setString(3, registration.getStudentrollno());
			pstmmodifyObj.setString(4, registration.getApplicationNo().trim());
			pstmmodifyObj.setString(5, HelperClass.convertUIToDatabase(registration.getDateofJoin().trim()));
			pstmmodifyObj.setString(6, registration.getAcademicYear().trim());
			pstmmodifyObj.setString(7, registration.getSchoolLocation().trim());
			pstmmodifyObj.setString(8, registration.getTransferCertificateNo().trim());
			pstmmodifyObj.setString(9,registration.getIsWorkingTeacherGuardian().trim());
			pstmmodifyObj.setString(10,registration.getWorkingTeacherGuardianId().trim());
			pstmmodifyObj.setString(11,registration.getWorkingTeacherName().trim());
			pstmmodifyObj.setString(12,registration.getMedium().trim());
			pstmmodifyObj.setString(13,registration.getImageFileName().trim());
			pstmmodifyObj.setString(14, registration.getModifyUser().trim());
			pstmmodifyObj.setString(15, HelperClass.getCurrentTimestamp().toString());
			pstmmodifyObj.setString(16, HelperClass.convertUIToDatabase(registration.getDateofBirth().trim()));
			pstmmodifyObj.setString(17, registration.getGender().trim());
			pstmmodifyObj.setString(18, registration.getAge().trim());
			pstmmodifyObj.setString(19, registration.getBloodGroup().trim());
			pstmmodifyObj.setString(20, registration.getNationality().trim());
			pstmmodifyObj.setString(21, registration.getMothertongue().trim());
			pstmmodifyObj.setString(22,registration.getAadharno().trim());
			pstmmodifyObj.setString(23, registration.getPhysicallyChallenged().trim());
			pstmmodifyObj.setString(24, registration.getPhysicalchalreason().trim());
			pstmmodifyObj.setString(25, registration.getReligion().trim());
			pstmmodifyObj.setString(26, registration.getCaste().trim());
			pstmmodifyObj.setString(27, registration.getCasteCategory().trim());
			pstmmodifyObj.setString(28, registration.getStudentstatus().trim());
			pstmmodifyObj.setString(29, registration.getIdentificationMarks().trim());
			pstmmodifyObj.setString(30, registration.getIdentificationMarks1().trim());
			pstmmodifyObj.setString(31, registration.getPreviousHistory().trim());
			pstmmodifyObj.setString(32, registration.getRemarks().trim());

			pstmmodifyObj.setString(33,	registration.getStudentSibilingIdInt());

			if(registration.getPrimaryPerson().equalsIgnoreCase("father")){
				pstmmodifyObj.setDouble(34, registration.getFatherAnnualIncome());
			}else if(registration.getPrimaryPerson().equalsIgnoreCase("mother")){
				pstmmodifyObj.setDouble(34, registration.getMotherAnnualIncome());
			}else if(registration.getPrimaryPerson().equalsIgnoreCase("guardian")){
				pstmmodifyObj.setDouble(34, registration.getGuardianAnnualIncome());
			}else{
				pstmmodifyObj.setDouble(34, 0.0);
			}

			pstmmodifyObj.setString(35, registration.getTransferfile().trim());
			pstmmodifyObj.setString(36, registration.getBirthfile().trim());
			pstmmodifyObj.setString(37, registration.getCertificate1Url().trim());
			pstmmodifyObj.setString(38, registration.getCertificate2Url().trim());
			pstmmodifyObj.setString(39, registration.getCertificate3Url().trim());
			pstmmodifyObj.setString(40, registration.getCertificate4Url().trim());
			pstmmodifyObj.setString(41, registration.getCertificate5Url().trim());

			pstmmodifyObj.setString(42, registration.getPreviousSchool());
			pstmmodifyObj.setString(43, registration.getState());
			pstmmodifyObj.setString(44, registration.getLandLine());


			pstmmodifyObj.setString(45,registration.getStudentId().trim());

			System.out.println("what it is setting:" +pstmmodifyObj);
			resultModify = pstmmodifyObj.executeUpdate();


			PreparedStatement scpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_REGISTRATION_MODIFY);

			scpstmt.setString(1, registration.getAcademicYear().trim());
			scpstmt.setString(2, registration.getCategory().trim());
			scpstmt.setString(3, registration.getStudClassId().trim());
			scpstmt.setString(4, registration.getStudSectionId().trim());
			if(registration.getSpecilization() != null && !registration.getSpecilization().equalsIgnoreCase("")){
				scpstmt.setString(5, registration.getSpecilization());
			}else{
				scpstmt.setString(5, "-");
			}
			scpstmt.setString(6, registration.getImageFileName().trim());
			scpstmt.setString(7, registration.getModifyUser().trim());
			scpstmt.setString(8, HelperClass.getCurrentTimestamp().toString());
			scpstmt.setString(9, registration.getSchoolLocation());
			scpstmt.setString(10, registration.getSchoolLocation());
			
			scpstmt.setString(11,registration.getFirstlang().trim());
			scpstmt.setString(12,registration.getSecondlang().trim());
			scpstmt.setString(13,registration.getThirdlang().trim());
			scpstmt.setString(14,registration.getStudHouseId().trim());
			scpstmt.setString(15,registration.getStudentId().trim());
			scpstmt.setString(16,registration.getAcademicYear().trim());
			
		
			scpstmt.executeUpdate();
			scpstmt.close();
			
			PreparedStatement checkpstmt=conn.prepareStatement("SELECT COUNT(*) FROM campus_students_contacts WHERE studentId=?");
			checkpstmt.setString(1, registration.getStudentId().trim());
			ResultSet chckRs=checkpstmt.executeQuery();
			int chkcount=0;
			while(chckRs.next()) {
				chkcount=chckRs.getInt(1);
			}
			if(chkcount==0) {
				PreparedStatement contactpstmt=conn.prepareStatement("INSERT INTO campus_students_contacts (studentId,emergencyNo,smsNo) VALUES(?,?,?)");
				contactpstmt.setString(1, registration.getStudentId().trim());
				contactpstmt.setString(2, registration.getEmergencynumber());
				contactpstmt.setString(3, registration.getSmsnumber());
				contactpstmt.executeUpdate();
				contactpstmt.close();
			}
			else {
			PreparedStatement contactpstmt=conn.prepareStatement("UPDATE campus_students_contacts SET emergencyNo=?,smsNo=? WHERE studentId=?");
		
			contactpstmt.setString(1, registration.getEmergencynumber());
			contactpstmt.setString(2, registration.getSmsnumber());
			contactpstmt.setString(3,registration.getStudentId().trim());
			contactpstmt.executeUpdate();
			contactpstmt.close();
			}
			
			

			//To store student transport details

			PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION_MODIFY);


			stpstmt.setString(1, registration.getAcademicYear().trim());
			if(registration.getTransport() !=null && !registration.getTransport().equalsIgnoreCase("")){
				stpstmt.setString(2, registration.getTransport().trim());
			}else{
				stpstmt.setString(2, "");
			}
			stpstmt.setString(3, registration.getTranscategory().trim());
			stpstmt.setString(4, registration.getTranslocation().trim());
			stpstmt.setString(5, registration.getRoute());
			stpstmt.setString(6, registration.getModifyUser().trim());
			stpstmt.setString(7, HelperClass.getCurrentTimestamp().toString());
			stpstmt.setString(8, registration.getSchoolLocation());
			stpstmt.setString(9, registration.getStudentId().trim());

			stpstmt.executeUpdate();
			stpstmt.close();

			//Parent Details 


			String parentId =null;


			if (registration.getParentId()==null || "".equalsIgnoreCase(registration.getParentId())) {

				PreparedStatement preParentReg = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_REG);

				parentId = IDGenerator.getPrimaryKeyID("campus_parents",conn);

				String parentUserName = parentId.substring(3);

				if (registration.getPrimaryPerson().equals("father")) {

					relationship = "father";

					String fatherName = registration.getFatherName();
					fatherName = fatherName.replaceAll(" ", "");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, fatherName.concat(parentUserName));
					preParentReg.setString(27, fatherName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");


					Thread.sleep(1000);
					if (registration.getFatherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getFatherEmail().trim(), fatherName
								.concat(parentUserName), fatherName
								.concat(parentUserName));
					}

				} else if (registration.getPrimaryPerson().equals("mother")) {

					relationship = "mother";
					String motherName = registration.getMotherName();
					motherName = motherName.replaceAll(" ", "");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, motherName.concat(parentUserName));
					preParentReg.setString(27, motherName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");

					Thread.sleep(1000);
					if (registration.getMotherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getMotherEmail().trim(), motherName
								.concat(parentUserName), motherName
								.concat(parentUserName));
					}

				} else {
					relationship = "guardian";

					String guardianName = registration.getGuardianName();
					guardianName = guardianName.replaceAll(" ", " ");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, guardianName.concat(parentUserName));
					preParentReg.setString(27, guardianName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");

					Thread.sleep(1000);
					if (registration.getGuardianEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getGuardianEmail().trim(), guardianName
								.concat(parentUserName), guardianName
								.concat(parentUserName));
					}
				}
				
				preParentReg.setString(33, registration.getFatherDepartment());
				preParentReg.setString(34, registration.getFatherDesignation());
				preParentReg.setString(35, registration.getFatherOfficeAddress());
				preParentReg.setString(36, registration.getMotherDepartment());
				preParentReg.setString(37, registration.getMotherDesignation());
				preParentReg.setString(38, registration.getMotherOfficeAddress());
				preParentReg.setString(39, registration.getGuardianDepartment());
				preParentReg.setString(40, registration.getGuardianDesignation());
				preParentReg.setString(41, registration.getGuardianOfficeAddress());


				preParentReg.execute();
				preParentReg.close();

			}else{

				PreparedStatement preParentReg = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_PARENT_INFORMATION);

				parentId = registration.getParentId().trim();
				String parentUserName = parentId.substring(3);


				//ParentID,FatherName,mobileno,student_father_occupation,fatherPanNo,fatherAnnualIncome,email,Qualification,fatherPhoto,student_mothername_var,
				//student_mothermobileno_var,student_mother_occupation,motherPanNo,motherAnnualIncome,student_mother_mailid,student_motherqualification_var,motherPhoto,
				//student_gaurdianname_var,student_gardian_mobileno,guardianOccupation,guardianPanNo,guardianQualification,student_gardian_mailid,guardianPhoto,
				//UserName,password,address,presentAddress,createdby,createdate,pstatus

				//update campus_parents set FatherName=?,mobileno=?,student_father_occupation=?,fatherPanNo=?,fatherAnnualIncome=?,email=?,Qualification=?,fatherPhoto=?,student_mothername_var=?,student_mothermobileno_var=?,student_mother_occupation=?,motherPanNo=?,motherAnnualIncome=?,student_mother_mailid=?,student_motherqualification_var=?,motherPhoto=?,student_gaurdianname_var=?,student_gardian_mobileno=?,guardianOccupation=?,guardianPanNo=?,guardianQualification=?,student_gardian_mailid=?,guardianPhoto=?,address=?,presentAddress=?,UserName=?,password=?,modifiedby=?,modifiedDate=? where ParentID=?

				if (registration.getPrimaryPerson().equals("father")) {

					relationship = "father";

					String fatherName = registration.getFatherName();
					fatherName = fatherName.replaceAll(" ", "");

					//FatherName=?,mobileno=?,student_father_occupation=?,fatherPanNo=?,fatherAnnualIncome=?,email=?,Qualification=?,fatherPhoto=?,student_mothername_var=?,
					//student_mothermobileno_var=?,student_mother_occupation=?,motherPanNo=?,motherAnnualIncome=?,student_mother_mailid=?,student_motherqualification_var=?,
					//motherPhoto=?,student_gaurdianname_var=?,student_gardian_mobileno=?,guardianOccupation=?,guardianPanNo=?,guardianQualification=?,student_gardian_mailid=?,
					//guardianPhoto=?,address=?,presentAddress=?,UserName=?,password=?,modifiedby=?,modifiedDate=? where ParentID=?

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, fatherName.concat(parentUserName));
					preParentReg.setString(28, fatherName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);

					Thread.sleep(1000);
					if (registration.getFatherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getFatherEmail().trim(), fatherName
								.concat(parentUserName), fatherName
								.concat(parentUserName));
					}

				}else if (registration.getPrimaryPerson().equals("mother")) {

					relationship = "mother";
					String motherName = registration.getMotherName();
					motherName = motherName.replaceAll(" ", "");

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, motherName.concat(parentUserName));
					preParentReg.setString(28, motherName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);


					Thread.sleep(1000);
					if (registration.getMotherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getMotherEmail().trim(), motherName
								.concat(parentUserName), motherName
								.concat(parentUserName));
					}
				} else {
					relationship = "guardian";

					String guardianName = registration.getGuardianName();
					guardianName = guardianName.replaceAll(" ", " ");

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, guardianName.concat(parentUserName));
					preParentReg.setString(28, guardianName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);

					Thread.sleep(1000);
					if (registration.getGuardianEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getGuardianEmail().trim(), guardianName
								.concat(parentUserName), guardianName
								.concat(parentUserName));
					}
				}
				
				
				preParentReg.setString(31, registration.getFatherDepartment());
				preParentReg.setString(32, registration.getFatherDesignation());
				preParentReg.setString(33, registration.getFatherOfficeAddress());
				preParentReg.setString(34, registration.getMotherDepartment());
				preParentReg.setString(35, registration.getMotherDesignation());
				preParentReg.setString(36, registration.getMotherOfficeAddress());
				preParentReg.setString(37, registration.getGuardianDepartment());
				preParentReg.setString(38, registration.getGuardianDesignation());
				preParentReg.setString(39, registration.getGuardianOfficeAddress());
				System.out.println("update preParentReg "+preParentReg);

				preParentReg.executeUpdate(); 

				preParentReg.close();


			}



			preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_UPDATE);

			if (registration.getPrimaryPerson().equals("father")) {

				relationship = "father";

			} else if (registration.getPrimaryPerson().equals("mother")) {

				relationship = "mother";

			} else {

				relationship = "guardian";

			}

			preChildParentUpdate.setString(1, relationship);
			preChildParentUpdate.setString(2, parentId.trim());
			preChildParentUpdate.setString(3, registration.getStudentId().trim());


			preChildParentUpdate.executeUpdate();


			/*ps_parentChaildsUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILDS_UPDATE);

				if (registration.getPrimaryPerson().equals("father")) {

					relationship = "father";

				} else if (registration.getPrimaryPerson().equals("mother")) {

					relationship = "mother";

				} else {

					relationship = "guardian";

				}

				ps_parentChaildsUpdate.setString(1, relationship);
				ps_parentChaildsUpdate.setString(2, parentId.trim());


				ps_parentChaildsUpdate.executeUpdate();

				//For enquiry update

				if (registration.getEnquiryId() != null) {

					PreparedStatement enquiryUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_ENQUIRY_DETAILS);

					enquiryUpdate.setString(1, "Y");
					enquiryUpdate.setString(2, registration.getStudentId().trim());
					enquiryUpdate.setString(3, registration.getModifyUser().trim());
					enquiryUpdate.setTimestamp(4,HelperClass.getCurrentTimestamp());
					enquiryUpdate.setString(5, registration.getEnquiryId());


					enquiryUpdate.executeUpdate();


				}*/

			if (resultModify > 0) {

				successMsg = "successMessage";
				PreparedStatement deleteExtraParent=conn.prepareStatement("DELETE FROM `campus_parents` WHERE  `ParentID` NOT IN (SELECT DISTINCT parentid FROM `campus_parentchildrelation`)");
				deleteExtraParent.executeUpdate();
				deleteExtraParent.close();


			} else {

				successMsg = "errorMessage";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {
			try {
				if (rsClass != null && (!rsClass.isClosed())) {
					rsClass.close();
				}
				if (rsImagePath != null && (!rsImagePath.isClosed())) {
					rsImagePath.close();
				}
				if (preChildParentUpdate != null
						&& (!preChildParentUpdate.isClosed())) {
					preChildParentUpdate.close();
				}
				if (preParentRegUpdate != null
						&& (!preParentRegUpdate.isClosed())) {
					preParentRegUpdate.close();
				}

				if (getImagePath != null && (!getImagePath.isClosed())) {
					getImagePath.close();
				}
				if (rsacademicYear != null && (!rsacademicYear.isClosed())) {
					rsacademicYear.close();
				}

				if (duplicateRs != null && (!duplicateRs.isClosed())) {
					duplicateRs.close();
				}
				if (rsacademicYearId != null && (!rsacademicYearId.isClosed())) {
					rsacademicYearId.close();
				}
				if (resultSetclassName != null
						&& (!resultSetclassName.isClosed())) {
					resultSetclassName.close();
				}

				if (pstmclasscount != null && (!pstmclasscount.isClosed())) {
					pstmclasscount.close();
				}
				if (academicYearpre != null && (!academicYearpre.isClosed())) {
					academicYearpre.close();
				}
				if (prclassName != null && (!prclassName.isClosed())) {
					prclassName.close();
				}

				if (pstmmodifyObj != null && (!pstmmodifyObj.isClosed())) {
					pstmmodifyObj.close();
				}
				if (pstmacademicYearId != null
						&& (!pstmacademicYearId.isClosed())) {
					pstmacademicYearId.close();
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
				+ " Control in StudentRegistrationDaoImpl : modifyStudentDetails Ending");
		return successMsg;
	}


	//To search student details


	//To serch student
	@Override
	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Starting");
		String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_STCLSE);

			pstmObj.setString(1, registrationVo.getAcademicYearId());

			if (registrationVo.getClassDetailId() != null) {
				pstmObj.setString(2, "%" + registrationVo.getClassDetailId()
				+ "%");
			} else {
				pstmObj.setString(2, "%%");
			}
			if (registrationVo.getClassSectionId() != null) {
				pstmObj.setString(3, "%" + registrationVo.getClassSectionId()
				+ "%");
			} else {
				pstmObj.setString(3, "%%");
			}
			pstmObj.setString(4, searchTerm);
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs
						.getString("student_fname_var"));
				studentRegistrationVo.setStudentidlabel(rs
						.getString("student_id_int"));

				registrationList.add(studentRegistrationVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Ending");

		return registrationList;

	}


	@Override
	public List<StudentRegistrationVo> searchStudentResult(StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentResulto Starting");


		List<StudentRegistrationVo> studentVoList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_BY_SEARCH_TERM);
			pstmObj.setString(1, registrationVo.getSearchTerm());

			System.out.println("pstmObj---------------->"+pstmObj);
			rs = pstmObj.executeQuery();

			while (rs.next()) {

				StudentRegistrationVo studentvo1 = new StudentRegistrationVo();


				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1
				.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1
				.setStudentIDgenerator(registrationVo.getSearchTerm());
				studentvo1.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				studentvo1.setStudentFirstName(rs
						.getString("student_fname_var"));

				studentvo1.setApplicationNo(rs
						.getString("student_application_no"));
				studentvo1.setTransport(rs.getString("isTransport"));
				/*studentvo1.setHostel(rs.getString("isHostel"));*/

				studentvo1
				.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setDateofBirth(HelperClass
						.getDateFromSQLDateinDDMMYYYYFormat(rs
								.getDate("student_dob_var")));

				studentvo1.setAge(rs.getString("student_age_int"));

				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setPhysicallyChallenged(rs
						.getString("student_physicallychallenged"));
				studentvo1
				.setBloodGroup(rs.getString("student_bloodgroup_var"));

				studentvo1.setIdentificationMarks(rs
						.getString("student_identificationmarks_var"));

				studentvo1.setNationality(rs
						.getString("student_nationality_var"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setDateofJoin(HelperClass
						.getDateFromSQLDateinDDMMYYYYFormat(rs
								.getDate("student_doj_var")));
				studentvo1.setAcademicYearId(rs
						.getString("fms_acadamicyear_id_int"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1
				.setStudSectionId(rs.getString("classsection_id_int"));
				/*studentvo1.setGrade(rs.getString("student_grade"));
				studentvo1.setStudentquotaid(rs.getString("student_quota"));*/
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setPromotionStatus(rs
						.getString("student_promotionstatus"));
				studentvo1.setPreviousHistory(rs
						.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));

				studentvo1
				.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherQualification(rs
						.getString("Qualification"));
				studentvo1.setFatherMobileNo(rs
						.getString("mobileno"));

				studentvo1
				.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs
						.getString("student_mothermobileno_var"));
				studentvo1.setMotherQualification(rs
						.getString("student_motherqualification_var"));

				studentvo1.setGuardianName(rs
						.getString("student_gaurdianname_var"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setParentId(rs.getString("ParentID"));

				studentvo1
				.setStudentIDgenerator(rs.getString("student_id_int"));

				studentvo1.setFatheremailId(rs
						.getString("email"));
				studentvo1.setMotheremailId(rs
						.getString("student_mother_mailid"));
				studentvo1.setGuardianemailId(rs
						.getString("student_gardian_mailid"));
				studentvo1.setGuardianMobileNo(rs
						.getString("student_gardian_mobileno"));

				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs
						.getString("type_collectFee"));

				if (rs.getString("student_siblingId") != null) {
					PreparedStatement pstm = conn
							.prepareStatement(SQLUtilConstants.GET_SIBLING_DETAILS);
					pstm.setString(1, registrationVo.getSearchTerm());
					ResultSet rst = pstm.executeQuery();
					while (rs.next()) {
						studentvo1.setSibilingClassId(rst
								.getString("classdetails_name_var"));
						studentvo1.setStudentSibilingIdInt(rst
								.getString("student_id_int"));
						studentvo1.setStudentSibilingRegNo(rst
								.getString("student_admissionno_var"));
						studentvo1.setStudentSibilingName(rst
								.getString("siblingName"));
					}
				} else {
					studentvo1.setSibilingClassId("");
					studentvo1.setStudentSibilingIdInt("");
					studentvo1.setStudentSibilingRegNo("");
					studentvo1.setStudentSibilingName("");
				}

				studentVoList.add(studentvo1);

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			;
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : searchStudentResult Ending");
		return studentVoList;

	}


	@Override
	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchByParent Starting");
		List<StudentRegistrationVo> studentVoList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_DETAIL_BY_PARENTID);
			pstmObj.setString(1, registrationVo.getSearchTerm());
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				//classdetail_id_int,student_id_int,student_fname_var,student_admissionno_var,FatherName,Qualification,mobileno,student_mothername_var,
				//student_mothermobileno_var,student_gaurdianname_var,student_siblingId,parentid,classdetails_name_var,email,student_mother_mailid,
				//student_gardian_mobileno,student_gardian_mailid,relationship,student_imgurl_var,address,student_mother_occupation,student_father_occupation
				StudentRegistrationVo form = new StudentRegistrationVo();
				form.setSibilingClassId(rs.getString("classdetail_id_int"));
				form.setStudentSibilingIdInt(rs.getString("student_id_int"));
				form.setStudentFirstName(rs.getString("student_fname_var"));
				form.setSibilingadminno(rs.getString("student_admissionno_var"));
				form.setFatherName(rs.getString("FatherName"));
				form.setFatherQualification(rs.getString("Qualification"));
				form.setFatherMobileNo(rs.getString("mobileno"));
				form.setMotherName(rs.getString("student_mothername_var"));
				form.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				form.setMotherQualification(rs.getString("student_motherqualification_var"));
				form.setGaurdianName(rs.getString("student_gaurdianname_var"));
				form.setSibilingName(rs.getString("student_siblingId"));
				form.setParentId(rs.getString("parentid"));
				form.setSibilingClass(rs.getString("classdetails_name_var"));
				form.setFatheremailId(rs.getString("email"));
				form.setMotheremailId(rs.getString("student_mother_mailid"));
				form.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				form.setGuardianemailId(rs.getString("student_gardian_mailid"));
				form.setPrimaryPerson(rs.getString("relationship"));
				form.setStudentIDgenerator(rs.getString("student_id_int"));
				form.setImageFileName(rs.getString("student_imgurl_var"));
				form.setAddress(rs.getString("address"));
				form.setMotheroccupation(rs.getString("student_mother_occupation"));
				form.setFatheroccupation(rs.getString("student_father_occupation"));

				studentVoList.add(form);

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
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentSearchByParent Ending");
		return studentVoList;
	}


	public List<StudentRegistrationVo> studentSearchbySibling(
			StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Starting");
		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_SIBLING);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);
			pstmObj.setString(3, searchTerm);
			System.out.println("sibling query is "+pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs.getString("StudentName")+"-"+rs.getString("student_admissionno_var"));
				studentRegistrationVo.setStudentidlabel(rs.getString("student_id_int"));
				registrationList.add(studentRegistrationVo);

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
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentSearch Ending");

		return registrationList;

	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbyClass Starting");

		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_CLASSNAME);

			pst.setString(1, registrationVo.getAcademicYearId());

			if (registrationVo.getClassDetailId() != null) {
				pst.setString(2, "%" + registrationVo.getClassDetailId() + "%");
			} else {
				pst.setString(2, "%%");
			}

			pst.setString(3, searchTerm);
			rs = pst.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs
						.getString("student_fname_var"));
				studentRegistrationVo.setStudentidlabel(rs
						.getString("student_id_int"));

				registrationList.add(studentRegistrationVo);
			}
		} catch (Exception e) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbyClass Ending");

		return registrationList;
	}

	//To delete student


	//To delete student
	public boolean deactivateStudent(StudentRegistrationVo registrationVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl:deactivateStudent Starting");
		PreparedStatement pstmObj = null;
		Connection conn = null;
		int count = 0;
		boolean status = false;

		try {
			
			String locationId="";
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			PreparedStatement pstmt=conn.prepareStatement("SELECT locationId FROM campus_student where student_id_int=?");
			pstmt.setString(1, registrationVo.getStudentId());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				locationId=rs.getString("locationId");
			}
			rs.close();
			pstmt.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_student where student_id_int=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj1="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_student_classdetails where student_id_int=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj2="+pstmObj);
			
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_student_conduct_certificate where stu_id=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			
			System.out.println("pstmObj3="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_student_house where student_id=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj4="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_student_transportdetails where student_id_int=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj5="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_students_contacts where studentId=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj6="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_parents where ParentID IN(SELECT parentid FROM campus_parentchildrelation WHERE stu_addmissionNo=?)");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj7="+pstmObj);
			count = pstmObj.executeUpdate();
			pstmObj.close();
			
			pstmObj = conn.prepareStatement("DELETE FROM campus_parentchildrelation where stu_addmissionNo=?");
			pstmObj.setString(1, registrationVo.getStudentId());
			System.out.println("pstmObj8="+pstmObj);
			count = pstmObj.executeUpdate();
			
			
			if (count > 0) {
				
				PreparedStatement selectAutoGeneration=conn.prepareStatement("SELECT MAX(`student_admissionno_var`) maximcount FROM `campus_student` WHERE locationId=?");
				selectAutoGeneration.setString(1, locationId);
				rs=selectAutoGeneration.executeQuery();
				while(rs.next()){
					int mmaximcount = rs.getInt("maximcount");
					String incrementVal=(Integer.toString(mmaximcount).substring(1));
					int IncrementValue=Integer.parseInt(incrementVal);
					PreparedStatement updateAutomaicAdmissionGen = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_AUTO_GEN_ADMISSIONNO);
					updateAutomaicAdmissionGen.setInt(1, IncrementValue);
					updateAutomaicAdmissionGen.setString(2, locationId);
					updateAutomaicAdmissionGen.executeUpdate();
					updateAutomaicAdmissionGen.close();
				}
				rs.close();
				selectAutoGeneration.close();
				
				status = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl:deactivateStudent  Ending");
		return status;
	}

	//To get student edit details



	//To edit student

	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editStudent Starting");

		StudentRegistrationVo studentvo1 = new StudentRegistrationVo();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			System.out.println("Student Id "+registrationVo.getSearchTerm());
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_BY_SEARCH_TERM);
			pstmObj.setString(1, registrationVo.getSearchTerm());
			pstmObj.setString(2, registrationVo.getAcademicYear());
			rs = pstmObj.executeQuery();

			System.out.println("pstmObj:::"+pstmObj);

			while (rs.next()) {
				studentvo1.setStudentId(rs.getString("student_id_int"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setHouseId(rs.getString("student_house"));
				studentvo1.setApplicationNo(rs.getString("student_application_no"));
				studentvo1.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1.setStudSectionId(rs.getString("classsection_id_int"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setStudentFirstName(rs.getString("student_fname_var"));
				studentvo1.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setTransport(rs.getString("isTransport"));
				studentvo1.setTransferfile(rs.getString("student_tc_path"));
				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs.getString("type_collectFee"));
				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1.setRoute(rs.getString("route"));
				studentvo1.setSchoolLocation(rs.getString("locationId"));
				studentvo1.setIsWorkingTeacherGuardian(rs.getString("isParentsGuardianWorking"));
				studentvo1.setWorkingTeacherGuardianId(rs.getString("workingParentsGuardianId"));
				studentvo1.setWorkingTeacherName(rs.getString("workingParentsGuardianName"));
				studentvo1.setMedium(rs.getString("medium"));
				studentvo1.setSpecilization(rs.getString("specilization"));
				studentvo1.setTransferCertificateNo(rs.getString("transferCertificateNo"));
				studentvo1.setFirstlang(rs.getString("firstlanguage"));
				studentvo1.setSecondlang(rs.getString("secondlanguage"));
				studentvo1.setThirdlang(rs.getString("thirdlanguage"));
				studentvo1.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setAge(rs.getString("student_age_int"));
				studentvo1.setBloodGroup(rs.getString("student_bloodgroup_var"));
				studentvo1.setNationality(rs.getString("student_nationality_var"));
				studentvo1.setMothertongue(rs.getString("motherTounge"));
				studentvo1.setAadharno(rs.getString("adharNo"));
				studentvo1.setPhysicallyChallenged(rs.getString("student_physicallychallenged"));
				studentvo1.setPhysicalchalreason(rs.getString("physicallychallenged_reason"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setCasteCategory(rs.getString("casteCategory"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setIdentificationMarks(rs.getString("student_identificationmarks_var"));
				studentvo1.setIdentificationMarks1(rs.getString("student_identificationmarks1_var"));
				studentvo1.setPreviousHistory(rs.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));
				studentvo1.setState(rs.getString("state"));
				studentvo1.setPreviousSchool(rs.getString("prev_school"));
				studentvo1.setLandLine(rs.getString("landline"));
				//certificate1,certificate2,certificate3,certificate4,certificate5,student_tc_path,student_birthcert_path
				studentvo1.setCertificate1(rs.getString("certificate1"));
				studentvo1.setCertificate2(rs.getString("certificate2"));
				studentvo1.setCertificate3(rs.getString("certificate3"));
				studentvo1.setCertificate4(rs.getString("certificate4"));
				studentvo1.setCertificate5(rs.getString("certificate5"));
				studentvo1.setBirthcertificate(rs.getString("student_birthcert_path"));


				//ParentID,FatherName,mobileno,student_father_occupation,fatherPanNo,fatherAnnualIncome,email,Qualification,fatherPhoto,
				//student_mothername_var,student_mothermobileno_var,student_mother_occupation,motherPanNo,motherAnnualIncome,student_mother_mailid,
				//student_motherqualification_var,motherPhoto,
				//student_gaurdianname_var,student_gardian_mobileno,guardianOccupation,guardianPanNo,guardianAnnualIncome,guardianQualification,
				//student_gardian_mailid,guardianPhoto,
				//address,presentAddress,relationship

				studentvo1.setParentId(rs.getString("ParentID"));
				studentvo1.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherMobileNo(rs.getString("mobileno"));
				studentvo1.setFatheroccupation(rs.getString("student_father_occupation"));
				studentvo1.setFatherPanNo(rs.getString("fatherPanNo"));
				studentvo1.setFatherAnnualIncome(rs.getDouble("fatherAnnualIncome"));
				studentvo1.setFatheremailId(rs.getString("email"));
				studentvo1.setFatherQualification(rs.getString("Qualification"));
				studentvo1.setFatherPhoto(rs.getString("fatherPhoto"));
				studentvo1.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentvo1.setMotheroccupation(rs.getString("student_mother_occupation"));
				studentvo1.setMotherPanNo(rs.getString("motherPanNo"));
				studentvo1.setMotherAnnualIncome(rs.getDouble("motherAnnualIncome"));
				studentvo1.setMotheremailId(rs.getString("student_mother_mailid"));
				studentvo1.setMotherQualification(rs.getString("student_motherqualification_var"));
				studentvo1.setMotherPhoto(rs.getString("motherPhoto"));
				studentvo1.setGuardianName(rs.getString("student_gaurdianname_var"));
				studentvo1.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				studentvo1.setGuardianOccupation(rs.getString("guardianOccupation"));
				studentvo1.setGuardianPanNo(rs.getString("guardianPanNo"));
				studentvo1.setGuardianAnnualIncome(rs.getDouble("guardianAnnualIncome"));
				studentvo1.setGuardianQualification(rs.getString("guardianQualification"));
				studentvo1.setGuardianemailId(rs.getString("student_gardian_mailid"));
				studentvo1.setGuardianPhoto(rs.getString("guardianPhoto"));
				studentvo1.setAddress(rs.getString("address"));
				studentvo1.setPresentaddress(rs.getString("presentAddress"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setEmergencynumber(rs.getString("emergencyNo"));
				studentvo1.setSmsnumber(rs.getString("smsNo"));
				studentvo1.setFatherDepartment(rs.getString("fatherDepartment"));
				studentvo1.setMotherDepartment(rs.getString("motherDepartment"));
				studentvo1.setFatherDesignation(rs.getString("fatherDesignation"));
				studentvo1.setFatherOfficeAddress(rs.getString("fatherOfficeAddress"));
				studentvo1.setMotherDesignation(rs.getString("motherDesignation"));
				studentvo1.setMotherOfficeAddress(rs.getString("motherOfficeAddress"));
				studentvo1.setGuardianDepartment(rs.getString("guardianDepartment"));
				studentvo1.setGuardianDesignation(rs.getString("guardianDesignation"));
				studentvo1.setGuardianOfficeAddress(rs.getString("guardianOfficeAddress"));
				/*studentvo1.setAcademicYear(rs.getString("acadamic_year"));
				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1.setStudentIDgenerator(registrationVo.getSearchTerm());
				studentvo1.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentvo1.setStudentFirstName(rs.getString("student_fname_var"));
				studentvo1.setApplicationNo(rs.getString("student_application_no"));
				studentvo1.setTransport(rs.getString("isTransport"));
				//studentvo1.setHostel(rs.getString("isHostel"));
				studentvo1.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				studentvo1.setAge(rs.getString("student_age_int"));
				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setPhysicallyChallenged(rs.getString("student_physicallychallenged"));
				studentvo1.setBloodGroup(rs.getString("student_bloodgroup_var"));
				studentvo1.setIdentificationMarks(rs.getString("student_identificationmarks_var"));
				studentvo1.setNationality(rs.getString("student_nationality_var"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				studentvo1.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1.setStudSectionId(rs.getString("classsection_id_int"));
				//studentvo1.setGrade(rs.getString("student_grade"));
				//studentvo1.setAadharno(rs.getString("Aadhaar_No"));
				//studentvo1.setParent_annulIncome(rs.getString("Annual_parentIncome"));
				studentvo1.setMothertongue(rs.getString("mothertongue"));
				studentvo1.setMedium(rs.getString("medium"));
				System.out.println("DIOMPL: medium "+rs.getString("medium"));
				//studentvo1.setStudentquotaid(rs.getString("student_quota"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setPromotionStatus(rs.getString("student_promotionstatus"));
				//studentvo1.setPreviousHistory(rs.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));
				//studentvo1.setStudentquotaname(rs.getString("student_quota"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherQualification(rs.getString("Qualification"));
				studentvo1.setFatherMobileNo(rs.getString("mobileno"));
				studentvo1.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentvo1.setMotherQualification(rs.getString("student_motherqualification_var"));
				studentvo1.setGuardianName(rs.getString("student_gaurdianname_var"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setParentId(rs.getString("ParentID"));
				studentvo1.setStudentIDgenerator(rs.getString("student_id_int"));
				studentvo1.setFatheremailId(rs.getString("email"));
				studentvo1.setMotheremailId(rs.getString("student_mother_mailid"));
				studentvo1.setGuardianemailId(rs.getString("student_gardian_mailid"));
				studentvo1.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				studentvo1.setIsconcession(rs.getString("isConcession"));

				studentvo1.setImageFileName(rs.getString("student_imgurl_var"));
				studentvo1.setBirthcertificate(rs.getString("student_birthcert_path"));
				studentvo1.setTransferfile(rs.getString("student_tc_path"));

				studentvo1.setAddress(rs.getString("address"));
				studentvo1.setMotheroccupation(rs.getString("student_mother_occupation"));
				studentvo1.setFatheroccupation(rs.getString("student_father_occupation"));
				studentvo1.setRoute(rs.getString("route"));
				//studentvo1.setEmisNo(rs.getString("emisNo"));
				studentvo1.setStudentId(registrationVo.getSearchTerm());

				//System.out.println("  emis No: in DIOMPL : "+rs.getString("emisNo"));


				if (rs.getString("isConcession").equalsIgnoreCase("Y")) {

					studentvo1.setScholarShip(rs
							.getString("student_scholorship_var"));
				} else {

					studentvo1.setScholarShip("");
				}

				//studentvo1.setRte(rs.getString("isRTE").trim());
				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs.getString("type_collectFee"));*/


				if (rs.getString("student_siblingId") != null) {

					PreparedStatement pstm = conn.prepareStatement("SELECT sib.student_id_int AS siblingId,sib.student_admissionno_var AS siblingAdmissionNo,CASE WHEN sib.student_lname_var IS NULL THEN sib.student_fname_var ELSE CONCAT(sib.student_fname_var,' ',sib.student_lname_var)  END siblingName,cd.classdetails_name_var FROM campus_student st  JOIN campus_student sib ON sib.student_id_int = st.student_siblingId JOIN campus_student_classdetails cstd ON sib.student_id_int=cstd.student_id_int   JOIN campus_classdetail  cd ON cd.classdetail_id_int=cstd.classdetail_id_int  WHERE st.student_id_int = ? ORDER BY LENGTH(cstd.classdetail_id_int),cstd.classdetail_id_int DESC LIMIT 1");
					pstm.setString(1, registrationVo.getSearchTerm());

					System.out.println("siblingId"+pstm);
					ResultSet rst = pstm.executeQuery();

					while (rst.next()) {

						studentvo1.setSibilingClassId(rst.getString("classdetails_name_var"));
						studentvo1.setStudentSibilingIdInt(rst.getString("siblingId"));
						studentvo1.setStudentSibilingRegNo(rst.getString("siblingAdmissionNo"));
						studentvo1.setStudentSibilingName(rst.getString("siblingName"));
					}

				} else {


					studentvo1.setSibilingClassId("");
					studentvo1.setStudentSibilingIdInt("");
					studentvo1.setStudentSibilingRegNo("");
					studentvo1.setStudentSibilingName("");
				}



			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			;
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : editStudent Ending");
		return studentvo1;

	}

	//To get student list for listing page


	//Get student list
	@Override
	public List<registrationvo> StudentDetails(String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<registrationvo> list = new ArrayList<registrationvo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);

			pst.setString(1, academic_year);

			rs = pst.executeQuery();
			while (rs.next()) {
				registrationvo registrationVo = new registrationvo();
				sno++;
				registrationVo.setSno(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass
						.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs
						.getString("student_status_var"));

				list.add(registrationVo);
			}
		} catch (Exception e) {
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

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year,String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
				pst.setString(1, academic_year);
				pst.setString(2, schoolLocation);

				System.out.println("pst is "+pst);

			}
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSection_id(rs.getString("classsection_id_int"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
			
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : checkApplicationNo Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<StudentInfoVO> studentList = new ArrayList<StudentInfoVO>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_DETAILS);
			preparedStatement.setString(1, classname);
			preparedStatement.setString(2, accodamicyr);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				StudentInfoVO vo = new StudentInfoVO();

				vo.setId(rs.getString("student_id_int"));
				vo.setAdmissionno(rs.getString("student_admissionno_var"));
				vo.setName(rs.getString("student_fname_var"));

				studentList.add(vo);

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
				+ " Control in  StudentRegistrationDaoImpl : checkApplicationNo Ending");
		return studentList;
	}


	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Starting");
		List<StudentRegistrationVo> desstudentlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_DESTINATIONWISE_STUDENT_DETAILS);
			pst.setString(1,transloc);

			System.out.println("What destination wise student is printing:" +pst);
			rs = pst.executeQuery();
			System.out.println("What it is printing inside:" +pst);
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();

				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setStudentLastName(rs.getString("Studentname"));
				registrationVo.setStudClassId(rs.getString("classdetail_id_int"));
				registrationVo.setStage_name(rs.getString("stage_name"));
				registrationVo.setStage_id(rs.getString("stage_id"));
				desstudentlist.add(registrationVo);

			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Ending");

		return desstudentlist;

	}


	public List<StudentRegistrationVo> getStudentDetails(String parameter, String searchTerm) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails Starting");

		List<StudentRegistrationVo> searchlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		String searchName=parameter;
		String location=searchTerm.split(",")[1];
		String accyear=parameter.split(",")[0];
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SEARCH_STUDENT_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			pstmt.setString(7, "%" + searchName + "%");
			pstmt.setString(8, "%" + searchName + "%");
			pstmt.setString(9, "%" + searchName + "%");
			pstmt.setString(10, "%" + searchName + "%");
			pstmt.setString(11,location);
			pstmt.setString(12,accyear);

			System.out.println("feeDetailsList"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sno++;

				StudentRegistrationVo objvo = new StudentRegistrationVo();

				objvo.setSno(String.valueOf(sno));


				String studentname = (rs.getString("student_fname_var"))+" " + (rs.getString("student_lname_var"));
				String calssname  =  (rs.getString("classdetails_name_var"))+"-" + (rs.getString("classsection_name_var"));
				objvo.setStudentId(rs.getString("student_id_int"));
				objvo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				objvo.setStudentnamelabel(studentname);
				objvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				objvo.setStudentStatus(rs.getString("student_status_var"));
				objvo.setClassSectionId(calssname);
				objvo.setAcademicYear(rs.getString("acadamic_year"));
				/*objvo.setStudentnamelabel(rs.getString("student_fname_var"));
				objvo.setStudentnamelabel(rs.getString("student_lname_var"));*/



				searchlist.add(objvo);

			}



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
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
				+ " Control in  StudentRegistrationDaoImpl : getStudentDetails Ending");

		return searchlist;
	}


	public String getStudentName(String admissionno) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String StaffStudent=null;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STAFF_STUDENT_DETAILS);
			pst.setString(1,admissionno);

			System.out.println(pst);
			rs = pst.executeQuery();

			while (rs.next()) {

				StaffStudent= rs.getString("student");
						//(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Ending");

		return StaffStudent;
	}

	public String getSingleAcademicYear(String academicYear) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Starting");

	/*	List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();*/
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		String academicname=null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GETSINGLEACADEMICYEAR);
			preparedStatement.setString(1, academicYear);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAcademicYear(resultSet
						.getString(MessageConstants.AcademicYear));
				studentRegistrationVo.setAcademicYearId(resultSet
						.getString(MessageConstants.AcademicYearId));

				academicname = resultSet
						.getString(MessageConstants.AcademicYear);


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
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Ending");
		return academicname;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_LIST);
			pst.setString(1, location);
			System.out.println(pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_I_TO_X);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_XI_TO_XII);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TEMP_STUDENT_DETAILSLIST);
			pst.setString(1, registrationVo.getTempregid().trim());
			rs = pst.executeQuery();
			System.out.println("look query......"+pst);
			while (rs.next()) {

				registrationVo.setTempregid(rs.getString("temporary_admission_id"));
				registrationVo.setStudentFirstName(rs.getString("studentfirstName"));
				registrationVo.setStudentLastName(rs.getString("studentlastname"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId").trim()));
				registrationVo.setGender(rs.getString("gender"));
				registrationVo.setNationality(rs.getString("nationality"));
				registrationVo.setReligion(rs.getString("religion"));
				registrationVo.setCaste(rs.getString("caste"));
				registrationVo.setMothertongue(rs.getString("mothertongue"));
				registrationVo.setAadharno(rs.getString("aadharNo"));
				registrationVo.setAddress(rs.getString("permanentaddress"));
				registrationVo.setPresentaddress(rs.getString("addressofcommunication"));
				registrationVo.setClassname(rs.getString("classname"));

				registrationVo.setSibilingClassId(rs.getString("siblingid"));
				registrationVo.setSibilingName(rs.getString("siblingname"));

				registrationVo.setFatherName(rs.getString("fathername"));
				registrationVo.setFatherMobileNo(rs.getString("fathermobileno"));
				registrationVo.setFatherQualification(rs.getString("fatherqualification"));
				registrationVo.setFatheroccupation(rs.getString("fatheroccupation"));
				registrationVo.setFatherDepartment(rs.getString("fatherdepartment"));
				registrationVo.setFatherDesignation(rs.getString("fatherdesignation"));
				registrationVo.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
				registrationVo.setFatherAnnualIncome(rs.getDouble("fathermonthincome"));
				registrationVo.setFatheremailId(rs.getString("fatheremailid"));

				registrationVo.setMotherName(rs.getString("mothername"));
				registrationVo.setMotherMobileNo(rs.getString("mothermobile"));
				registrationVo.setMotherQualification(rs.getString("motherqualification"));
				registrationVo.setMotheroccupation(rs.getString("motheroccupation"));
				registrationVo.setMotherDepartment(rs.getString("motherdepartment"));
				registrationVo.setMotherDesignation(rs.getString("motherdesignation"));
				registrationVo.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
				registrationVo.setMotherAnnualIncome(rs.getDouble("mothermothlyincome"));
				registrationVo.setMotheremailId(rs.getString("motheremailid"));
				registrationVo.setBirthcertificate(rs.getString("BirthCertificateFile"));
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date d = f.parse(rs.getString("createdTime"));
				DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				registrationVo.setCreateDate(date.format(d));
				registrationVo.setImageFileName(rs.getString("imageUrl"));
				registrationVo.setEnquiryId(rs.getString("enquiryid"));
				registrationVo.setStreemcode(rs.getString("stream"));
				registrationVo.setCasteCategory(rs.getString("castecategory"));
				registrationVo.setSpecilization(rs.getString("group_prefered"));
				registrationVo.setPrimaryPerson(rs.getString("relationship"));
				registrationVo.setSmsnumber(rs.getString("preferedphno"));
				list.add(registrationVo);
			}
			

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_LKG);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String param) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academic_year=param.split(",")[0];
		String locationId=param.split(",")[1];
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TRANSPORT_ALL_STUDENT);
				pst.setString(1, academic_year);
				pst.setString(2, locationId);
			}
			System.out.println("transport"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getTransportStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStreamDetailsDao Starting");

		List<StudentRegistrationVo> searchlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		try {

			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SEARCH_TRANSPORT_STUDENT);

			pstmt.setString(1, "%" + searchTerm + "%");
			pstmt.setString(2, "%" + searchTerm + "%");
			pstmt.setString(3, "%" + searchTerm + "%");
			pstmt.setString(4, "%" + searchTerm + "%");
			pstmt.setString(5, "%" + searchTerm + "%");
			pstmt.setString(6, "%" + searchTerm + "%");
			pstmt.setString(7, "%" + searchTerm + "%");
			pstmt.setString(8, "%" + searchTerm + "%");
			pstmt.setString(9, "%" + searchTerm + "%");
			pstmt.setString(10, "%" + searchTerm + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo objvo = new StudentRegistrationVo();

				objvo.setSno(String.valueOf(sno));
				String studentname = (rs.getString("student_fname_var"))+" " + (rs.getString("student_lname_var"));
				String calssname  =  (rs.getString("classdetails_name_var"))+"-" + (rs.getString("classsection_name_var"));
				objvo.setStudentId(rs.getString("student_id_int"));
				objvo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				objvo.setStudentnamelabel(studentname);
				objvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				objvo.setStudentStatus(rs.getString("student_status_var"));
				objvo.setClassSectionId(calssname);
				objvo.setAcademicYear(rs.getString("acadamic_year"));

				searchlist.add(objvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
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
				+ " Control in  StudentRegistrationDaoImpl : searchStreamDetailsDao Ending");

		return searchlist;
	}


	public List<StudentRegistrationVo> getStudentList(String academic_year, String location) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		ResultSet rst = null;

		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {
			
		/*	String sql = "select csc.specilization,ccs.Specialization_name from campus_student_classdetails csc join campus_class_specialization ccs on ccs.Specialization_Id=csc.specilization where student_id_int=?";*/
			
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				

				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
					
				}
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getClassByLocation(String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassByLocation  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_BY_LOCATION);
			pst.setString(1, locationid.trim());
			rs = pst.executeQuery();
			System.out.println("class list by location-id>>>>"+pst);
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));


				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassByLocation  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTION_DEMOTION_ALL);
			pst.setString(1, "%"+location.trim()+"%");
			pst.setString(2, academic_year);
			System.out.println("student class "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getAllStudentDetails  Ending");
		

		return list;
	}

	public List<StudentRegistrationVo> studentFullList(String studentId, String accYear,String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentFullList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null,pstmObj1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;
		int conf_count = 0;



		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement("select count(cr.comments),cstd.fms_acadamicyear_id_int,cd.classdetail_id_int,sec.classsection_id_int,cl.Location_Id,cpcr.parentid,par.FatherName,par.mobileno,par.student_mothername_var,par.student_mothermobileno_var,par.student_gaurdianname_var,par.student_gardian_mobileno,st.student_id_int,case when cstd.student_rollno is null then '-' else cstd.student_rollno end student_rollno,st.student_admissionno_var,st.student_fname_var,st.student_lname_var,cstd.student_imgurl_var,acc.acadamic_year,cd.classdetails_name_var,sec.classsection_name_var,tr.RouteName,cstd.student_status,cl.Location_Name,cfs.stage_name,cstd.secondlanguage,cstd.thirdlanguage,cr.comments,chs.housename from campus_student st join campus_student_classdetails cstd on st.student_id_int=cstd.student_id_int join campus_student_transportdetails stt on st.student_id_int=stt.student_id_int join campus_parentchildrelation cpcr on cpcr.stu_addmissionNo=st.student_id_int join campus_parents par on cpcr.parentid=par.ParentID join campus_classdetail cd on cd.classdetail_id_int=cstd.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=cstd.classsection_id_int join campus_acadamicyear acc on acc.acadamic_id=cstd.fms_acadamicyear_id_int join campus_location cl on cstd.locationId=cl.Location_Id left outer join campus_fee_stage cfs on stt.StageId=cfs.stage_id left join transport_route tr on stt.route=tr.RouteCode left join campus_confidential_report cr on st.student_id_int=cr.student_id left join campus_house_settings chs on chs.house_id=cstd.student_house where cstd.locationId like ? and cstd.student_id_int=? and cstd.fms_acadamicyear_id_int=? ");
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
            rs = pstmObj.executeQuery();
			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				conf_count = rs.getInt(1);
                count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setClassDetailId(rs.getString("classdetail_id_int"));
				studentRegVo.setClassSectionId(rs.getString("classsection_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				
				String routeName="";
				String stageName="";
				
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				PreparedStatement ptr=conn.prepareStatement("SELECT  route_id,stage_id FROM `campus_student_route_stage_mapping` WHERE mapped_id=? AND  accyear=? GROUP BY route_id,stage_id");
				ptr.setString(1, stu_Id);
				ptr.setString(2, acc_year);
				ResultSet rt=ptr.executeQuery();
				while(rt.next()) {
					PreparedStatement ptr1=conn.prepareStatement("SELECT RouteName FROM `transport_route` WHERE RouteCode=?");
					ptr1.setString(1, rt.getString("route_id"));
					ResultSet rt1=ptr1.executeQuery();
					if(rt1.next()) {
						routeName=routeName+rt1.getString("RouteName")+",";
					}
					rt1.close();
					ptr1.close();
					ptr1=conn.prepareStatement("SELECT stage_name FROM `campus_fee_stage` WHERE stage_id=?");
					ptr1.setString(1, rt.getString("stage_id"));
					 rt1=ptr1.executeQuery();
					if(rt1.next()) {
						stageName=stageName+rt1.getString("stage_name")+",";
					}
					rt1.close();
					ptr1.close();
				}
				rt.close();
				ptr.close();
				
				studentRegVo.setRoute(routeName);
				studentRegVo.setStage_name(stageName);

				if(rs.getString("secondlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("secondlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						studentRegVo.setSecondLanguage(rs1.getString("subjectName"));
					}
				}

				if(rs.getString("thirdlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("thirdlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						studentRegVo.setThirdLanguage(rs1.getString("subjectName"));
					}
				}
				studentRegVo.setHouseName(rs.getString("housename"));

				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));

				if(conf_count>0)
				{
					studentRegVo.setConfidentialStatus("AVAILABLE");
				}else{
					studentRegVo.setConfidentialStatus("NOT AVAILABLE");
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (pstmObj1 != null && (!pstmObj1.isClosed())) {
					pstmObj1.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentFullList Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsList(String locationid, String accyear ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_REGISTERED_STUDENTDETAILS);
			pst.setString(1, locationid);
			pst.setString(2, accyear);

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setLocationId(rs.getString("Location_Id"));
				registrationVo.setLocation(rs.getString("Location_Name"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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

	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {
			if(classname.equalsIgnoreCase("all"))
			{
				classname="%%";
				
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				
			}else{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
			}

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
					
				}
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rs.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				
			}
			else{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				
				PreparedStatement SecondlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
		
				PreparedStatement thirdlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
		
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
					pstmt.setString(1, rs.getString("specilization"));
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
					
				}
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rs.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}
	

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;
		
		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("undefined") || sectionid.equalsIgnoreCase(null) ){
			sectionid="%%";
		}

		try {
			
			if(sortingby!=null && sortingby.equalsIgnoreCase("Gender")){
				if(orderby.equalsIgnoreCase("DESC")){
					conn = JDBCConnection.getSeparateGodaddyConnection();
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION6);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}else{
						conn = JDBCConnection.getSeparateGodaddyConnection();
						pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION7);
						pst.setString(1, locationid.trim());
						pst.setString(2, accyear.trim());
						pst.setString(3, classname.trim());
						pst.setString(4, sectionid.trim());
						rs = pst.executeQuery();
					}
			}else if(sortingby !=null && sortingby.equalsIgnoreCase("Admission")){
				if(orderby.equalsIgnoreCase("DESC")){
					conn = JDBCConnection.getSeparateGodaddyConnection();
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION5);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}else{
					conn = JDBCConnection.getSeparateGodaddyConnection();
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION4);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}
			}else if(sortingby !=null && sortingby.equalsIgnoreCase("Alphabetical")){
				if(orderby.equalsIgnoreCase("DESC")){
					conn = JDBCConnection.getSeparateGodaddyConnection();
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION3);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}else{
					conn = JDBCConnection.getSeparateGodaddyConnection();
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION2);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}
			}
			else{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION2);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}
			System.out.println(pst);
			
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				
				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				if(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				else{
					registrationVo.setSecondLanguageName("-");	
				}
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				if(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				else{
					registrationVo.setThirdLanguageName("-");	
				}
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
					
				}
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rs.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailsList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		String flag_tab = flag;
		int count = 0;

		try{
			
			if(flag_tab.equalsIgnoreCase("history"))
			{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS_BY_HISTORY);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();
				
				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
					
					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("confidential_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("entrydate")));
					studentRegVo.setConfidentialComments(rs.getString("comments"));

					list.add(studentRegVo);
				}
			}else{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS_BY_CONTACTS);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();
				
				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
					
					count++;
					studentRegVo.setCount(count);
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setParentId(rs.getString("parentid"));
					studentRegVo.setFatherName(rs.getString("FatherName"));
					studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
					studentRegVo.setMotherName(rs.getString("student_mothername_var"));
					studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
					studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
					studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
					

					list.add(studentRegVo);
				}
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailsList Ending");
		return list;
	}

	@Override
	public String saveStudentPromotion(StudentRegistrationVo formObj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsDaoImpl : saveLocalExpenseDetails Starting");

		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		Connection con = null;
		String message="";
		int status = 0,uploadStatus=0;
		try {
			System.out.println("Inside the saveStudentPromotion DaoImpl");
			con = JDBCConnection.getSeparateGodaddyConnection();
			con.setAutoCommit(false);
			Timestamp createdDate = HelperClass.getCurrentTimestamp();
		
			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.INSERT_STUDENT_PROMOTION);
				pstmt.setString(1, formObj.getAdmissionNoArray()[i]);
				pstmt.setString(2, formObj.getStudentIdArray()[i]);
				pstmt.setString(3, formObj.getClass_fromArray()[i]);
				pstmt.setString(4, formObj.getClass_toArray()[i]);
				pstmt.setString(5, formObj.getAcademicyear_fromArray()[i]);
				pstmt.setString(6, formObj.getAcademicyear_toArray()[i]);
				pstmt.setString(7, formObj.getSection_fromArray()[i]);
				pstmt.setString(8, formObj.getSection_toArray()[i]);
				pstmt.setString(9, formObj.getSpecilization_fromArray()[i]);
				pstmt.setString(10, formObj.getSpecilization_toArray()[i]);
				pstmt.setString(11, formObj.getStatusArray()[i]);
				pstmt.setString(12, formObj.getCreateUser().trim());
				pstmt.setTimestamp(13, createdDate);
				pstmt.setString(14, formObj.getLocationIdArray()[i]);
				String fromstream=toGetStreamName(formObj.getClass_fromArray()[i],formObj.getLocationIdArray()[i]);
				String tostream=toGetStreamName(formObj.getClass_toArray()[i],formObj.getLocationIdArray()[i]);
				//studentpromotion_fromstream,studentpromotion_tostream
				pstmt.setString(15, fromstream);
				pstmt.setString(16, tostream);
				System.out.println("pstmt "+pstmt);
				
				status = pstmt.executeUpdate();	
			}

			System.out.println("status "+status);
			if ((status > 0)) {
				if ((formObj.getStudentIdArray()[0] != null) && !(formObj.getStudentIdArray()[0].equalsIgnoreCase("")) ) {
					uploadStatus = saveStudentClassDetails(formObj, con);
				}
				if ((formObj.getStudentIdArray()[0] != null) && !(formObj.getStudentIdArray()[0].equalsIgnoreCase("")) ) {
					uploadStatus = updateStudentClassDetails(formObj, con);
				}
				
				if ((uploadStatus > 0)) {
					con.commit();
					message = "success";

				} else {
					try {
						con.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					message = "fail";
				}

			} else {
				try {
					con.rollback();
				} catch (SQLException e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
				message = "fail";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt2 != null && (!pstmt2.isClosed())) {

					pstmt2.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {

					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsDaoImpl : saveLocalExpenseDetails Ending");
		return message;
	}

	private int updateStudentClassDetails(StudentRegistrationVo formObj, Connection con) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveTravelExpenseDetails Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;
			
			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_CLASSDETAILS_PREVIOUS_RECORD);
				if(formObj.getStatusArray()[i].equalsIgnoreCase("promoted")){
					pstmt.setString(1,"passed");
				}else if(formObj.getStatusArray()[i].equalsIgnoreCase("demoted")){
					pstmt.setString(1,"failed");
				}else{
					pstmt.setString(1,"");
				}
				pstmt.setString(2, formObj.getStatusArray()[i]);
				pstmt.setString(3, formObj.getCreateUser().trim());
				pstmt.setTimestamp(4, createdDate);
				pstmt.setString(5, formObj.getStudentIdArray()[i]);
				pstmt.setString(6, formObj.getAcademicyear_fromArray()[i]);
								
				status=pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveTravelExpenseDetails Ending");
		return status;
	}

	private int saveStudentClassDetails(StudentRegistrationVo formObj, Connection con) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;
			
			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				String imgurl="";
				
				PreparedStatement imgpstmt=conn.prepareStatement("SELECT student_imgurl_var FROM campus_student_classdetails WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				imgpstmt.setString(1, formObj.getStudentIdArray()[i]);
				imgpstmt.setString(2, formObj.getAcademicyear_fromArray()[i]);
				ResultSet imgrs=imgpstmt.executeQuery();
				if(imgrs.next()) {
					imgurl=imgrs.getString("student_imgurl_var");
				}
				try {
					if (imgrs != null && (!imgrs.isClosed())) {

						imgrs.close();
					  }
					if (imgpstmt != null && (!imgpstmt.isClosed())) {

						imgpstmt.close();
					  }
				} catch (SQLException Sq) {
					// TODO: handle exception
				}
				
				pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.INSERT_STUDENT_PROMOTION_IN_CLASSDETAILS);
				pstmt.setString(1, formObj.getStudentIdArray()[i]);
				pstmt.setString(2, formObj.getAcademicyear_toArray()[i]);
				pstmt.setString(3, formObj.getLocationIdArray()[i]);
				pstmt.setString(4, formObj.getClass_toArray()[i]);
				pstmt.setString(5, formObj.getSection_toArray()[i]);
				pstmt.setString(6, formObj.getSpecilization_toArray()[i]);
				pstmt.setString(7, formObj.getCreateUser().trim());
				pstmt.setTimestamp(8, createdDate);
				String tostream=toGetStreamName(formObj.getClass_toArray()[i],formObj.getLocationIdArray()[i]);
				pstmt.setString(9, tostream);
				pstmt.setString(10, imgurl);
				status=pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					  pstmt.close();
				  }
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

	public String getNextAcademicYearId(String academicyearid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportCategoryType Starting");

		PreparedStatement preparedStatement = null,preparedStatement1 = null,preparedStatement2=null;
		ResultSet rs = null,rs1 = null,rs2=null;
		String enddate = null;
		String academicYearId="",accyear="",year="";
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTACADEMIC_YEAR);
			preparedStatement.setString(1, academicyearid);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				enddate = rs.getString("endDate");
				accyear = rs.getString("acadamic_year");
				SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
				Calendar cal = Calendar.getInstance();    
				cal.setTime( dateFormat.parse(enddate));
				cal.add( Calendar.DATE, 1 );    
				String convertedDate=dateFormat.format(cal.getTime());    
				
				preparedStatement1 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEAR_COUNT);
				preparedStatement1.setString(1, convertedDate);
				rs1 = preparedStatement1.executeQuery();
				while(rs1.next()){
					count=rs1.getInt("count");
					year=rs1.getString("acadamic_id");
				}
				if(count == 0){
					String[] accyearsplit=accyear.split("-");
					preparedStatement2 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEARSPLIT_VAL);
					preparedStatement2.setString(1, accyearsplit[1].trim()+"%");
					rs2 = preparedStatement2.executeQuery();
					while(rs2.next()){
						academicYearId=rs2.getString("acadamic_id");
					}
				}else{
					academicYearId=year;
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (preparedStatement1 != null
						&& (!preparedStatement1.isClosed())) {
					preparedStatement1.close();
				}
				if (preparedStatement2 != null
						&& (!preparedStatement2.isClosed())) {
					preparedStatement2.close();
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
				+ " Control in  StudentRegistrationDaoImpl : getTransportCategoryType Ending");
		return academicYearId;
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_LIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			System.out.println("promoted list "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentLocationList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_LOCATION);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			
			rs = pst.executeQuery();
			
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentLocationList Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			System.out.println(pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_LIST);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue);
			pst.setString(9, "%"+searchValue+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClasssection(rs.getString("classdetails_name_var"+"-"+"classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ACCYEAR);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, accYear);
			System.out.println("2: "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_LOCATION);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, stuReg.getAcademicYearId());
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FILTER);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId+"%");
			pst.setString(10, accYear+"%");
	
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+'-'+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_CLASS);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId+"%");
			pst.setString(10, accYear+"%");
			pst.setString(11, className+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER);
			pst.setString(1, searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId+"%");
			pst.setString(10, accYear+"%");
			pst.setString(11, className);
			pst.setString(12, section);
			System.out.println("tcsearch  === " + pst);
			rs = pst.executeQuery();
			
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");
		
		return list;
	}

	public String AddConfidentialDetails(String entryDate,
			String comments, String studentId, String accyear, String locationid,String userName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddConfidentialDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		String entrydate = entryDate;
		String comment = comments;
		String student_Id = studentId;
		String location_Id = locationid;
		String accYear = accyear;
		String username = userName;
		int result = 0;
		String status = null;
		
		try{
			
	
			
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			String s1 = IDGenerator.getPrimaryKeyID("campus_confidential_report",conn);
			pst = conn.prepareStatement(SQLUtilConstants.ADD_CONFIDENTIAL_DETAIL);
			pst.setString(1,s1);
			pst.setString(2,student_Id);
			pst.setString(3,accYear);
			pst.setString(4,location_Id);
			pst.setString(5,HelperClass.convertUIToDatabase(entrydate));
			pst.setString(6,comment);
			pst.setString(7,username);
			
			result = pst.executeUpdate();
			
				
			if (result > 0) {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_FAIL;

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddConfidentialDetails Ending");
		
		return status;
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_CONFIDENTIAL_REPORT);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			
			rs = pst.executeQuery();
			
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAccYearDetailsConfReport Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_CONF_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAccYearDetailsConfReport Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfDetailsLByFilter  Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENT_CONF_DETAILS);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getConfDetailsLByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(
			String locationid, String accyear, String classname,String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentBySectionForConfReport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_CONF_STUDENTS_BY_SECTION);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentBySectionForConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByConfReport Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_CONF_REPORT);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByConfReport Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentConfDetails Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		PreparedStatement pstmObj2 = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			System.out.println(pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				
				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				
				pstmObj2 = conn.prepareStatement("SELECT csh.house_id,csh.student_id,chs.housename FROM campus_student_house csh LEFT JOIN campus_house_settings chs ON chs.house_id=csh.house_id WHERE csh.loc_id LIKE ? AND csh.student_id=? AND csh.academic_year=?");
				pstmObj2.setString(1, loc_Id);
				pstmObj2.setString(2, stu_Id);
				pstmObj2.setString(3, acc_year);
				rst = pstmObj2.executeQuery();
				System.out.println("pstmObj2"+pstmObj2);
				while(rst.next())
				{
					if(rst.getString("house_id") == null || rst.getString("house_id") == ""){
						studentRegVo.setHouseName("");
					}
					else{
						studentRegVo.setHouseName(rst.getString("housename"));
					}
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (pstmObj2 != null && (!pstmObj2.isClosed())) {
					pstmObj2.close();
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
				+ " Control in StudentRegistrationDaoImpl : singleStudentConfDetails Ending");
		return list;
	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_REPORT_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+accYear+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByAccYear Ending");
		
		return list;
	}

	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<FeeReportDetailsVo> list1 = new ArrayList<FeeReportDetailsVo>();
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_FEE_DETAILS);
			pst.setString(1, studentId);
			pst.setString(2, accYear);
			pst.setString(3, locationId);
			rs = pst.executeQuery();
			while(rs.next()){
				sno++;
				FeeReportDetailsVo freport = new FeeReportDetailsVo();
				freport.setSno(sno);
				freport.setTermName(rs.getString("termcode"));
				freport.setTotalAmount(rs.getString("totalamount"));
				freport.setFineamount(rs.getString("fineAmount"));
				freport.setBlancefeeAmount(rs.getDouble("balance_amount"));
				freport.setPaidAmount(rs.getDouble("amount_paid"));
				freport.setPaidStatus(rs.getString("is_paid"));
				if( freport.getPaidStatus().equalsIgnoreCase("Y"))
				{
					freport.setPaidStatus("paid");
				}
				else
				{
					freport.setPaidStatus("NotPaid");
				}
					
			   list1.add(freport);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationDetails(
			StudentRegistrationVo svo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_EXAMINATION_DETAILS);
			pst.setString(1,svo.getClassDetailId());
			pst.setString(2,svo.getClassSectionId());
			pst.setString(3,svo.getStudentId());
			pst.setString(4,svo.getAcademicYearId());
			pst.setString(5,svo.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				sno++;
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setSno1(sno);
				exvo.setSubCode(rs.getString("Sub_Code"));
				exvo.setSubjectName(rs.getString("subjectName"));
				exvo.setPassmarks(rs.getString("passMarks"));
				exvo.setTot_marks(rs.getString("totalMarks"));
				exvo.setScoredmarks(rs.getString("scoredmarks"));
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_GRADE_FOR_STUDENTS);
				pstmt1.setString(1, exvo.getScoredmarks());
				pstmt1.setString(2, exvo.getScoredmarks());
			    ResultSet rs1=pstmt1.executeQuery(); 
			    System.out.println("pstmt1:" +pstmt1);
			    while(rs1.next())
			    {
			    exvo.setGradename(rs1.getString("grade_name"));
			    }
			    list1.add(exvo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,
			String acyear_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_EXAMINATION_CODES);
			pst.setString(1,loc_id);
			pst.setString(2,acyear_id);
			rs = pst.executeQuery();
			System.out.println("to run the query:" +pst);
			
			while(rs.next()){
				
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
                exvo.setExamid(rs.getString("examid"));
				exvo.setExamcode(rs.getString("examcode"));
				list1.add(exvo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}

	public String getExamName(String examcode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
	String ExamCode = null;
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_EXAMINATION_NAMES);
			pst.setString(1,examcode);
			rs = pst.executeQuery();
			
			while(rs.next()){
				
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
                exvo.setExamName(rs.getString("examname"));
                ExamCode=exvo.getExamName();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return ExamCode;
	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		List<StudentAttendanceVo> list1 = new ArrayList<StudentAttendanceVo>();
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_ATTENDANCE);

			pst.setString(1,stud_id);
			rs = pst.executeQuery();
			System.out.println("to run the query for attendance:" +pst);
			
			while(rs.next()){
				sno++;
				StudentAttendanceVo stuvo = new StudentAttendanceVo();
				stuvo.setSno(sno);
				stuvo.setDate(rs.getString("attendencedate"));
				stuvo.setAttendancestatus(rs.getString("attendence"));
				list1.add(stuvo);
                
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}

	public List<StudentAttendanceVo> getStudentAppraisal(
			StudentRegistrationVo spvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
        PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		List<StudentAttendanceVo> list1 = new ArrayList<StudentAttendanceVo>();
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_APPRAISAL);

			pst.setString(1,spvo.getLocationId());
			pst.setString(2,spvo.getAcademicYearId());
			pst.setString(3,spvo.getStudentId());
			rs = pst.executeQuery();
			System.out.println("to run the query for attendance:" +pst);
			
			while(rs.next()){
				sno++;
				StudentAttendanceVo stuvo = new StudentAttendanceVo();
				stuvo.setSno(sno);
				stuvo.setDate(rs.getString("meeting_date"));
				stuvo.setRecommendedby(rs.getString("recommented_by"));
				stuvo.setMeetingwith(rs.getString("meeting_with")); 
				stuvo.setActiontaken(rs.getString("action_taken")); 
				stuvo.setRemarksstatus(rs.getString("remarks")); 
				stuvo.setStatus(rs.getString("status")); 
				list1.add(stuvo);
                
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(
			StudentRegistrationVo svo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
        PreparedStatement pst = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.GET_EXAMID);
			pstmt.setString(1, svo.getExam_code());
			pstmt.setString(2, svo.getExam_name());
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next())
			{
			   svo.setExam_id(rs2.getString("examid"));
			}
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_EXAMINATION_DETAILS_BASED_EXAMS);
			pst.setString(1,svo.getClassDetailId());
			pst.setString(2,svo.getClassSectionId());
			pst.setString(3,svo.getStudentId());
			pst.setString(4,svo.getAcademicYearId());
			pst.setString(5,svo.getLocationId());
			pst.setString(6,svo.getExam_code());
			rs = pst.executeQuery();
			System.out.println("pst from query:" +pst);
			while(rs.next()){
				sno++;
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setSno1(sno);
				exvo.setSubCode(rs.getString("Sub_Code"));
				exvo.setSubjectName(rs.getString("subjectName"));
				exvo.setPassmarks(rs.getString("passMarks"));
				exvo.setTot_marks(rs.getString("totalMarks"));
				exvo.setScoredmarks(rs.getString("scoredmarks"));
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_GRADE_FOR_STUDENTS);
				pstmt1.setString(1, exvo.getScoredmarks());
				pstmt1.setString(2, exvo.getScoredmarks());
			    ResultSet rs1=pstmt1.executeQuery(); 
			    System.out.println("pstmt1:" +pstmt1);
			    while(rs1.next())
			    {
			    exvo.setGradename(rs1.getString("grade_name"));
			    }
			    list1.add(exvo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list1;
	}
	
	public List<StudentRegistrationVo> getIDCard(String studentId, String accYear,String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentFullList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;
		int conf_count = 0;



		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_ID_CARD);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);

			System.out.println("query==========>"+pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				  conf_count = rs.getInt(1);

				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setLocation_address(rs.getString("Location_Address"));
				studentRegVo.setLocation_phone(rs.getString("Location_Phone"));
				studentRegVo.setStreemcode(rs.getString("fms_classstream_id_int"));
				studentRegVo.setValidaty(HelperClass.convertDatabaseToUI(rs.getString("endDate")));

				
		
			
				studentRegVo.setHouseName(rs.getString("housename"));

				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				
				studentRegVo.setPresentaddress(rs.getString("address"));
			

				
				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentFullList Ending");

		return list;
	}
	

	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
			
			Connection conn = null;
			PreparedStatement psmt=null;
			ResultSet rs=null;
			List<PageFilterVo> list=new ArrayList<PageFilterVo>();
			int sno=0;
			
			try{
				
				
				conn=JDBCConnection.getSeparateGodaddyConnection();
			
				psmt=conn.prepareStatement("SELECT ct.TeacherID,ct.FirstName, ct.isActive,ct.department,cd.DEPT_NAME,cd.DEPT_ID,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id  WHERE cd.DEPT_ID LIKE ? AND des.DesignationCode LIKE ? AND cay.acadamic_id LIKE ? AND ct.Loc_ID LIKE ? ORDER BY CAST(SUBSTR(cay.acadamic_id,4)AS UNSIGNED),CAST(SUBSTR(ct.Loc_ID,4)AS UNSIGNED),CAST(SUBSTR(cd.DEPT_ID,4)AS UNSIGNED),CAST(SUBSTR(des.DesignationCode,4)AS UNSIGNED)");

				
		
				psmt.setString(1, filterpojo.getDepartmentId());
				psmt.setString(2, filterpojo.getDesignationId());
				psmt.setString(3, filterpojo.getAcademicYear());
				psmt.setString(4, filterpojo.getLocationId());
				
			
				rs=psmt.executeQuery();
				while(rs.next()){
					sno++;
					PageFilterVo filterVo = new PageFilterVo();
					filterVo.setSno(sno);
					filterVo.setAcademicYear(rs.getString("acadamic_year"));
					filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
					filterVo.setLocationName(rs.getString("Location_Name"));
					filterVo.setLocationId(rs.getString("Location_Id"));
					filterVo.setDesignationId(rs.getString("designation"));
					filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
					
			
				
					list.add(filterVo);
					
				}
			
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (psmt != null && (!psmt.isClosed())) {
						psmt.close();
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
					+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
			
			return list;
		}
	
	
	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByLocation Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByLocation Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByFilter Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid,String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, className);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByClass Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, className);
			pst.setString(11, section);
			
			
			
			System.out.println(pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByAllFilter Ending");
		
		return list;
	}

	public String deleteConfidentialDetails(String deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteConfidentialDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.DETAIL_CONFIDENTIAL_DETAIL);
			pst.setString(1,deleteid);
			
			result = pst.executeUpdate();
			System.out.println("from deleteConfidentialDetails :"+pst);
				
			if (result > 0) {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteConfidentialDetails Ending");
		
		return status;
	}

	public String editConfidentialDetails(String entryDate, String comments, String editId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editConfidentialDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		String entrydate = entryDate;
		String comment = comments;
		String editid = editId;
		int result = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.EDIT_CONFIDENTIAL_DETAIL);
			pst.setString(1,HelperClass.convertUIToDatabase(entrydate));
			pst.setString(2,comment);
			pst.setString(3,editid);
			
			result = pst.executeUpdate();
			System.out.println("from editConfidentialDetails :"+pst);
				
			if (result > 0) {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_FAIL;

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editConfidentialDetails Ending");
		
		return status;
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academic_year=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
				System.out.println("vvvvvvvv = "+pst);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
				pst.setString(1, academic_year);
				pst.setString(2, location);
				System.out.println("vvvvvvvv1 = "+pst);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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

	public List<StudentRegistrationVo> getStudentFeeSearchByList(
			String searchTerm,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
            rs = pst.executeQuery();
			while(rs.next()){
				
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1, accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					registrationVo.setAcademicYear(rs1.getString("acadamic_year"));
				}
				
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStatus(rs.getString("student_status_var"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(
			String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_FEE_SEARCH_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+searchValue+"%");
			
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
//		
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1, accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setDateofBirth(rs.getString("student_dob_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid)  {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_FEE_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+locationId+"%");
			pst.setString(6, "%"+accYear+"%");
			pst.setString(7, className);
			pst.setString(8, section);
			
			rs = pst.executeQuery();			
			
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1,accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(
			String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, "%"+className+"%");
			
			rs = pst.executeQuery();
				System.out.println("Print Query For all the " +pst);
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				PreparedStatement pstmt1 =conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accyear);
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(
			String searchTerm, String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//raji1
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setStudentStatus(rs.getString("student_status_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
			System.out.println("Academic year for all Location:" +accyear);
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSection_id(rs.getString("classsection_id_int"));
				registrationVo.setStudentStatus(rs.getString("student_status_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
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

	public List<StudentRegistrationVo> getStudentListByFeeSection(
			String locationid, String accyear, String classname,
			String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStudentStatus(rs.getString("student_status_var"));
                registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public String updateStudentPromotion(StudentRegistrationVo formObj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveLocalExpenseDetails Starting");
		PreparedStatement pstmt = null;
		Connection con = null;
		String message="";
		int status = 0,uploadStatus=0;
		try {
			con = JDBCConnection.getSeparateGodaddyConnection();
			con.setAutoCommit(false);
			Timestamp createdDate = HelperClass.getCurrentTimestamp();
			pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_PROMOTION);
			pstmt.setString(1, formObj.getNewsection().trim());
			pstmt.setString(2, formObj.getNewspecilaization().trim());
			pstmt.setString(3, formObj.getStudentStatus().trim());
			pstmt.setString(4, formObj.getCreateUser().trim());
			pstmt.setTimestamp(5, createdDate);
			pstmt.setString(6, formObj.getPromotedId().trim());
			System.out.println("UPDATE_STUDENT_PROMOTION "+pstmt);
			status = pstmt.executeUpdate();	
			if ((status > 0)) {
				
				if ((formObj.getPromotedId() != null) && !(formObj.getPromotedId().equalsIgnoreCase("")) ) {
					uploadStatus = updatePromotedStudentClassDetails(formObj, con);
				}
				
				if ((uploadStatus > 0)) {
					con.commit();
					message = "success";
				} else {
					try {
						con.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					message = "fail";
				}
			} else {
				try {
					con.rollback();
				} catch (SQLException e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
				message = "fail";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveLocalExpenseDetails Ending");
		return message;
	}
	private int updatePromotedStudentClassDetails(StudentRegistrationVo formObj, Connection con) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updatePromotedStudentClassDetails Starting");
		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_PROMOTED_CLASSSDETAILS);
			pstmt.setString(1, formObj.getNewsection());
			pstmt.setString(2, formObj.getNewspecilaization());
			if(formObj.getStudentStatus().equalsIgnoreCase("promoted")){
				pstmt.setString(3,"passed");
			}else if(formObj.getStudentStatus().equalsIgnoreCase("demoted")){
				pstmt.setString(3,"failed");
			}else{
				pstmt.setString(3,"");
			}
			pstmt.setString(4, formObj.getStudentStatus());
			pstmt.setString(5, formObj.getCreateUser().trim());
			pstmt.setTimestamp(6, createdDate);
			pstmt.setString(7, formObj.getStudentId());
			pstmt.setString(8, formObj.getAcademicYearId());
			pstmt.setString(9, formObj.getLocationId());
			System.out.println("UPDATE_STUDENT_PROMOTED_CLASSSDETAILS "+pstmt);
			status=pstmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					  pstmt.close();
				  }
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updatePromotedStudentClassDetails Ending");
		return status;
	}
	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearDemotedDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(accYear);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_ACCYEAR_LIST);
			pst.setString(1, locationId.trim());
			pst.setString(2, accYear.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearDemotedDetails  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentPromotedChange(String studentId, String accYear, String locationId, String promotedId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedChange Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_PROMOTED_CHANGE);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			pstmObj.setString(4, promotedId);
			
			rs = pstmObj.executeQuery();
			while(rs.next())
			{
				count++;
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("studentName"));
				if(rs.getString("student_status").equalsIgnoreCase("passed")){
					studentRegVo.setStudentStatus("PASSED");
				}else if(rs.getString("student_status").equalsIgnoreCase("failed")){
					studentRegVo.setStudentStatus("FAILED");
				}else{
					studentRegVo.setStudentStatus(rs.getString("student_status"));
				}
				studentRegVo.setPromotionStatus(rs.getString("studentpromotion_status"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setClasscode(rs.getString("classdetail_id_int"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setSectioncode(rs.getString("classsection_id_int"));
				studentRegVo.setSpecilization(rs.getString("Specialization_Id"));
				if(rs.getString("Specialization_name") != null){
					studentRegVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					studentRegVo.setSpecilizationname("");
				}
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				
				studentRegVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				if(rs.getString("housename") != null){
					studentRegVo.setHouseName(rs.getString("housename"));	
				}else{
					studentRegVo.setHouseName("");
				}
				studentRegVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				studentRegVo.setSectionto(rs.getString("studentpromotion_tosection_var"));
				

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedChange Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDemotedSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int count = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_DEMOTED_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDemotedSearchByList Ending");
		
		return list;
	}
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearPromotedDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(accYear);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_ACCYEAR_LIST);
			pst.setString(1, locationId.trim());
			pst.setString(2, accYear.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearPromotedDetails  Ending");
		

		return list;
	}

	
	public String toCheckNextYearAvailable(String academicyearid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportCategoryType Starting");

		PreparedStatement preparedStatement = null,preparedStatement1 = null,preparedStatement2=null;
		ResultSet rs = null,rs1 = null,rs2=null;
		String enddate = null,accyear=null;
		String academicYearId="";
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTACADEMIC_YEAR);
			preparedStatement.setString(1, academicyearid);
			rs = preparedStatement.executeQuery();
			int count=0,count1=0;
			while (rs.next()) {
				enddate = rs.getString("endDate");
				accyear = rs.getString("acadamic_year");
				SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
				Calendar cal = Calendar.getInstance();    
				cal.setTime( dateFormat.parse(enddate));
				cal.add( Calendar.DATE, 1 );    
				String convertedDate=dateFormat.format(cal.getTime());    
				
				preparedStatement1 = conn.prepareStatement("select count(*) as count,acadamic_id from campus_acadamicyear where startDate=? group by acadamic_id");
				preparedStatement1.setString(1, convertedDate);
				rs1 = preparedStatement1.executeQuery();
				while(rs1.next()){
					count=rs1.getInt("count");
				}
				if(count == 0){
					String[] accyearsplit=accyear.split("-");
					preparedStatement2 = conn.prepareStatement("select count(acadamic_id) as countval from campus_acadamicyear where acadamic_year like ? group by acadamic_id");
					preparedStatement2.setString(1, accyearsplit[1].trim()+"%");
					rs2 = preparedStatement2.executeQuery();
					while(rs2.next()){
						count1=rs2.getInt("countval");
					}
					if(count1 == 0){
						academicYearId="Next Year Not Avaliable";
					}else{
						academicYearId="Next Year Avaliable";
					}
				}else{
					academicYearId="Next Year Avaliable";
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
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
				+ " Control in  StudentRegistrationDaoImpl : getTransportCategoryType Ending");
		return academicYearId;
	}
	
	public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_SECTIONLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			pst.setString(4, regVo.getClassSectionId());
			System.out.println("promoted list "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassSectionList  Ending");
		return list;
	}
	
	public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_CLASSLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedListDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_LIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			System.out.println("demoted list "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedListDetails  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentPromotingSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int count = 0;
		System.out.println("Inside the dao impl");
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTING_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, accYear);
			pst.setString(9, locationId);
			pst.setString(10, className);
			pst.setString(11, section);
			System.out.println("list of record "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setStudentFullName(rs.getString("studentName"));
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");
		
		return list;
	}
	
	public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_SECTIONLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			pst.setString(4, regVo.getClassSectionId());
			
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassSectionList  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_CLASSLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassList  Ending");
		

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentWisePromotion(String studentId, String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentFullList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_WISE_PROMOTION);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);

			rs = pstmObj.executeQuery();
			while(rs.next())
			{
				count++;
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("studentName"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setClasscode(rs.getString("classdetail_id_int"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setSectioncode(rs.getString("classsection_id_int"));
				studentRegVo.setSpecilization(rs.getString("Specialization_Id"));
				if(rs.getString("Specialization_name") != null){
					studentRegVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					studentRegVo.setSpecilizationname("");
				}
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				
				studentRegVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"));
				studentRegVo.setAcademicYearId(accyearid);
				
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				if(rs.getString("housename") != null){
					studentRegVo.setHouseName(rs.getString("housename"));	
				}else{
					studentRegVo.setHouseName("");
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentFullList Ending");

		return list;
	}
	
	public String toCheckNextClassAvailable(String toclass, String locationId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : toCheckNextClassAvailable Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;
		String status=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_EXITING_CLASS);
			preparedStatement.setString(1, toclass);
			preparedStatement.setString(2, locationId);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				count = rs.getInt("count");

			}
			if(count == 0){
				status="notpresent";
			}else{
				status="present";
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
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : toCheckNextClassAvailable Ending");
		return status;
	}
	
	public List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(String academic_year, String location,String classId, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTION_DEMOTION);
			pst.setString(1, academic_year);
			pst.setString(2, "%"+location.trim()+"%");
			pst.setString(3, classId.trim());
			pst.setString(4, sectionid.trim());
			
			System.out.println("student class "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setStudentFullName(rs.getString("studentName"));
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");
		

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int count = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			System.out.println("lis "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByList Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;
		
		int count = 0;
		
		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, accYear);
			System.out.println("lis "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByAccYear Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByLocation Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		
		int count = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			System.out.println("lis "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByLocation Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int count = 0;
		
		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			pst.setString(9, accYear);
			System.out.println("lis "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByFilter Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int count = 0;
		
		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			pst.setString(9, accYear);
			pst.setString(10, className);
			System.out.println("execute query "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByClass Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int count = 0;
		
		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, accYear);
			pst.setString(10, className);
			pst.setString(11, section);
			System.out.println("lis "+pst);
			rs = pst.executeQuery();
				
			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				
				
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				
				list.add(registrationVo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByAllFilter Ending");
		
		return list;
	}


	public List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location,
			String currentaccyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			System.out.println("Inside the getStudentDetails");
			conn = JDBCConnection.getSeparateGodaddyConnection();

				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
				pst.setString(1, currentaccyear);
				pst.setString(2, location);
				System.out.println("pst is "+pst);
				rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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



	public List<StudentRegistrationVo> getStudentDetailsByJs(
			StudentRegistrationVo data) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJs  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if(data.getSearchTerm().equalsIgnoreCase("all") && data.getLocationId().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getAcademicYear());
				pst.setString(2, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getClasscode().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				
				pst.setString(3, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getSection_id().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
				pst.setString(5, data.getAcademicYear());
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_SEARCH_BY_JS_FOR_FEE);
				if(data.getLocationId().equalsIgnoreCase("all")){
					pst.setString(1, "%%");
				}
				else{
					pst.setString(1, data.getLocationId());
				}
				
				if(data.getAcademicYear().equalsIgnoreCase("all")){
					pst.setString(2, "%%");
				}
				else{
				pst.setString(2, data.getAcademicYear());
				}
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst.setString(3, "%%");
				}
				else{
				pst.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst.setString(4, "%%");
				}
				else{
				pst.setString(4, data.getSection_id());
				}
				
				pst.setString(5, data.getSearchTerm() + "%");
				pst.setString(6, data.getSearchTerm() + "%");
				pst.setString(7, data.getSearchTerm() + "%");
				pst.setString(8,data.getSearchTerm() + "%");
				pst.setString(9,data.getSearchTerm() + "%");
				pst.setString(10,data.getSearchTerm() + "%");
				pst.setString(11,data.getSearchTerm() + "%");
				pst.setString(12,data.getSearchTerm() + "%");
				pst.setString(13,data.getSearchTerm() + "%");
				pst.setString(14,data.getSearchTerm() + "%");
				pst.setString(15, data.getAcademicYear());
		
			}
			System.out.println("studentcCCCC "+pst);
			rs = pst.executeQuery();
		
			
			
			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				PreparedStatement termpstmt=conn.prepareStatement("SELECT termid,termname FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=?");
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement("SELECT COUNT(*) FROM  campus_fee_collection WHERE termcode=? AND admissionNo=?");
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				
				
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJs  Ending");

		return list;
	}

	public String getlocationteacher(String userCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getlocationteacher  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String locid=null;
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement("select Loc_ID from campus_teachers where TeacherID = ?");
			pst.setString(1,userCode);
			rs = pst.executeQuery();
			while(rs.next()){
				locid = rs.getString("Loc_ID");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null	&& (!pst.isClosed())) {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getlocationteacher Ending");
		return locid;
	}

	public String toGetStreamName(String toClass, String locationId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : toGetStreamName Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String status=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TOGET_STREAMNAME);
			preparedStatement.setString(1, toClass);
			preparedStatement.setString(2, locationId);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				status = rs.getString("classstream_id_int");
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
				+ " Control in  StudentRegistrationDaoImpl : toGetStreamName Ending");
		return status;
	}
	public String saveRollNo(StudentRegistrationVo svo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveRollNo Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		int result = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.SAVE_ROLL_NO);
			for(int i=0;i<svo.getStudentIdArray().length;i++){
			pst.setString(1,svo.getRollNoArray()[i]);
			pst.setString(2,svo.getStudentIdArray()[i]);
			pst.setString(3,svo.getAcademicyear_toArray()[i]);
			pst.setString(4,svo.getLocationIdArray()[i]);
			
			result = pst.executeUpdate();
			System.out.println("saveRollNo:"+pst);
				
			if (result > 0) {
				status = "true";
			} else {
				status ="false";

			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveRollNo Ending");
		
		return status;
	}
	
	public List<StudentRegistrationVo> individualStudentSearch(String studentId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentSearch Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null,pstmObj1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		int count = 0;
		int conf_count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_CLASS_HISTORY);
			pstmObj.setString(1,studentId);
            rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setAcademicYear(rs.getString("acadamic_year"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionnaem(rs.getString("classsection_name_var"));
				obj.setStudentrollno(rs.getString("student_rollno"));
				obj.setStatus(rs.getString("student_status"));
				list.add(obj);
				
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : individualStudentSearch Ending");

		return list;
	}

	public List<StudentRegistrationVo> individualStudentcontact(String studentId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentcontact Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null,pstmObj1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		int count = 0;
		int conf_count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_CONTACT_HISTORY);
			pstmObj.setString(1,studentId);
            rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setFatherName(rs.getString("FatherName"));
				obj.setFatherMobileNo(rs.getString("mobileno"));
				obj.setMotherName(rs.getString("student_mothername_var"));
				obj.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				obj.setGaurdianName(rs.getString("student_gaurdianname_var"));
				obj.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				list.add(obj);
				
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : individualStudentcontact Ending");

		return list;
	}

	public List<StudentRegistrationVo> getLanguageList(String classId, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getSection  Starting");
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SECTION);
			pst.setString(1, classId.trim());
			pst.setString(2, schoolLocation);
			System.out.println("pst "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setSectioncode(rs
						.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs
						.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getSection  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentListForTransport(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_FOR_TRANSPORT);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);
			pst.setString(3, academicYear);

			rs = pst.executeQuery();
			System.out.println("Student list from fee Collection:" +pst);
			
			
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListForTransportFilteration(
			String accyear, String location, String classId, String divisionId,
			String searchby) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransportFilteration  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_FOR_TRANSPORT_BY_FILTERATION);
			pst.setString(1, location);
			pst.setString(2, accyear);
			pst.setString(3, classId);
			pst.setString(4, divisionId);
			pst.setString(5, "%"+searchby+"%");
			pst.setString(6, "%"+searchby+"%");
			pst.setString(7, "%"+searchby+"%");
			pst.setString(8, "%"+searchby+"%");

			rs = pst.executeQuery();
			System.out.println("Student list from fee Collection:" +pst);
			
			
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransportFilteration  Ending");

		return list;
	}


	

	public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_WITHHELD_LIST);
			pst.setString(1, loc_Id);/*GET_STUDENTS_BY_CONFIDENTIAL_REPORT*/
			pst.setString(2, acc_year);
			
			rs = pst.executeQuery();
			
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeld Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		PreparedStatement pstmObj2 = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAIL);
			pstmObj.setString(1, loc_Id);                   /*GET_SINGLE_STUDENT_DETAILS*/
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			System.out.println(pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				
				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				
				pstmObj2 = conn.prepareStatement("SELECT csh.house_id,csh.student_id,chs.housename FROM campus_student_house csh LEFT JOIN campus_house_settings chs ON chs.house_id=csh.house_id WHERE csh.loc_id LIKE ? AND csh.student_id=? AND csh.academic_year=?");
				pstmObj2.setString(1, loc_Id);
				pstmObj2.setString(2, stu_Id);
				pstmObj2.setString(3, acc_year);
				rst = pstmObj2.executeQuery();
				System.out.println("pstmObj2"+pstmObj2);
				while(rst.next())
				{
					if(rst.getString("house_id") == null || rst.getString("house_id") == ""){
						studentRegVo.setHouseName("");
					}
					else{
						studentRegVo.setHouseName(rst.getString("housename"));
					}
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeld Ending");
		return list;
	}

	public String AddWithHeldDetails(StudentRegistrationVo studentvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddWithHeldDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
	
		int result = 0;
		String status = null;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if(studentvo.getStatus().equalsIgnoreCase("yes")){
				pst = conn.prepareStatement(SQLUtilConstants.ADD_WITHELD_DETAIL1); /*ADD_CONFIDENTIAL_DETAIL*/
				
				pst.setString(1,studentvo.getAccyear());
				pst.setString(2,studentvo.getLocationId());
				pst.setString(3,studentvo.getStudentId());
				pst.setString(4,HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(5,studentvo.getStatus());
				pst.setString(6,studentvo.getComments());
				pst.setString(7,studentvo.getCreateUser().trim());
				pst.setString(8,studentvo.getWithheldId());
				
				System.out.println(pst);
				
				result = pst.executeUpdate();
			}
			else{
			pst = conn.prepareStatement(SQLUtilConstants.ADD_WITHELD_DETAIL); /*ADD_CONFIDENTIAL_DETAIL*/
			
			pst.setString(1,studentvo.getAccyear());
			pst.setString(2,studentvo.getLocationId());
			pst.setString(3,studentvo.getStudentId());
			pst.setString(4,HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
			pst.setString(5,studentvo.getStatus());
			pst.setString(6,studentvo.getComments());
			pst.setString(7,HelperClass.convertUIToDatabase(studentvo.getCancelDate()));
			pst.setString(8,studentvo.getCancelcomment());
			pst.setString(9,studentvo.getCreateUser().trim());
			pst.setString(10,studentvo.getWithheldId());
			
			System.out.println(pst);
			
			result = pst.executeUpdate();
			}
			if (result > 0) {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_FAIL;

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddWithHeldDetails Ending");
		
		return status;
	}

	public String EditWithHeldDetails(StudentRegistrationVo studentvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : EditWithHeldDetails Starting");

		PreparedStatement pst = null;
		Connection conn = null;

		int result = 0;
		String status = null;

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if (studentvo.getStatus().equalsIgnoreCase("yes")) {
				pst = conn.prepareStatement(SQLUtilConstants.EDIT_WITHHELD_DETAIL1); /* EDIT_CONFIDENTIAL_DETAIL */

				pst.setString(1, HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(2, studentvo.getStatus());
				pst.setString(3, studentvo.getComments());
				pst.setString(4, studentvo.getCreateUser());
				pst.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pst.setString(6, studentvo.getWithheldId());
				result = pst.executeUpdate();
				System.out.println("from editConfidentialDetails :" + pst);
			} else {
				pst = conn.prepareStatement(SQLUtilConstants.EDIT_WITHHELD_DETAIL); /* EDIT_CONFIDENTIAL_DETAIL */
				pst.setString(1, HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(2, studentvo.getStatus());
				pst.setString(3, studentvo.getComments());
				pst.setString(4, HelperClass.convertUIToDatabase(studentvo.getCancelDate()));
				pst.setString(5, studentvo.getCancelcomment());
				pst.setString(6, studentvo.getCreateUser());
				pst.setTimestamp(7, HelperClass.getCurrentTimestamp());
				pst.setString(8, studentvo.getWithheldId());
				result = pst.executeUpdate();
				System.out.println("from editConfidentialDetails :" + pst);
			}
			if (result > 0) {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_FAIL;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : EditWithHeldDetails Ending");

		return status;
	}

	
					//studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));


	public String AddApparisalDetails(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
		   		+ " Control in StudentRegistrationDaoImpl : AddApparisalDetails  Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		String status=null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		System.out.println(vo.getSchedualdate());
		System.out.println(vo.getMeetingon());
		try{
			
			if(!vo.getHiddenid().equalsIgnoreCase(""))
			{
				
				conn= JDBCConnection.getSeparateGodaddyConnection();
				pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_APPRAISAL);
			
				pstmt.setString(1, vo.getStudentId());
				pstmt.setString(2, vo.getAcademicYearId());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getActiontaken());
				pstmt.setString(5, HelperClass.convertUIToDatabase(vo.getSchedualdate()));
				pstmt.setString(6, vo.getRecomendedby());
				pstmt.setString(7, HelperClass.convertUIToDatabase(vo.getMeetingdate()));
				pstmt.setString(8, vo.getMeetingwith());
				pstmt.setTime(9, HelperClass.getStringToTime(vo.getMeetingon()));
				pstmt.setString(10, vo.getRemarks());
				pstmt.setString(11, vo.getCreateUser());
				pstmt.setTimestamp(12, createdDate);
				pstmt.setString(13, vo.getHiddenid());
				
				System.out.println("//////////////////////////////"+pstmt);
				result = pstmt.executeUpdate();
				
				
				if (result > 0) {
					status = MessageConstants.UPDATE_APPRAISAL_REPORT_SUCCESS;
				} else {
					status = MessageConstants.UPDATE_APPRAISAL_REPORT_FAIL;
				}
				
					
				
			}else{
				conn= JDBCConnection.getSeparateGodaddyConnection();
				pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.ADD_APPRAISAL);
			
				pstmt.setString(1, vo.getStudentId());
				pstmt.setString(2, vo.getAcademicYearId());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getActiontaken());
				pstmt.setString(5, HelperClass.convertUIToDatabase(vo.getSchedualdate()));
				pstmt.setString(6, vo.getRecomendedby());
				if(vo.getMeetingdate().equalsIgnoreCase("") || vo.getMeetingdate()==null)
					pstmt.setString(7, vo.getMeetingdate());
				else
				pstmt.setString(7, HelperClass.convertUIToDatabase(vo.getMeetingdate()));
				pstmt.setString(8, vo.getMeetingwith());
				pstmt.setTime(9, HelperClass.getStringToTime(vo.getMeetingon()));
				pstmt.setString(10, vo.getRemarks());
				pstmt.setTimestamp(11, createdDate);
				pstmt.setString(12, vo.getCreateUser());
				System.out.println("//////////////////////////////"+pstmt);
				result = pstmt.executeUpdate();
				
				
				if (result > 0) {
					status = MessageConstants.ADD_APPRAISAL_REPORT_SUCCESS;
				} else {
					status = MessageConstants.ADD_APPRAISAL_REPORT_FAIL;

				}
				
				
			}
			
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		 }
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddApparisalDetails  Ending");

		
		return status;
	}

	public List<StudentRegistrationVo> singleStudentDetailReport(String studentId, String accyear, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
		   		+ " Control in StudentRegistrationDaoImpl : singleStudentDetailReport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			conn=JDBCConnection.getSeparateGodaddyConnection();
			pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.APPRAISAL_REPORT);
			pstmt.setString(2, studentId);
			pstmt.setString(3, accyear);
			pstmt.setString(1, locationid);
			rs=pstmt.executeQuery();
	       	while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentappraisalid(rs.getString("student_appraisal_id"));
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				
				studentRegVo.setLocationId(rs.getString("locationId"));
				studentRegVo.setActiontaken(rs.getString("action_taken"));
				studentRegVo.setSchedualdate(HelperClass.convertDatabaseToUI(rs.getString("schedule_date")));
				if(rs.getString("meeting_date") == null || rs.getString("meeting_date").equalsIgnoreCase(""))
					studentRegVo.setMeetingdate(rs.getString("meeting_date"));
				else
				studentRegVo.setMeetingdate(HelperClass.convertDatabaseToUI(rs.getString("meeting_date")));
				studentRegVo.setMeetingwith(rs.getString("meeting_with"));
				studentRegVo.setMeetingon((rs.getString("meeting_on")));
				studentRegVo.setRecomendedby(rs.getString("recommented_by"));
				studentRegVo.setRemark(rs.getString("remarks"));
				list.add(studentRegVo);
			}
	
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailReport  Ending");
	
		return list;
	}

	public String deleteApparaisalDetails(String deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteApparaisalDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.DETAIL_APPARAISAL_DETAIL);
			pst.setString(1,deleteid);
			
			result = pst.executeUpdate();
			System.out.println("from deleteConfidentialDetails :"+pst);
				
			if (result > 0) {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteApparaisalDetails Ending");
		
		return status;
	}


	public List<secadmissionformformatVO> searchStudentByTempAdmission(String studentName, String parentName, String mobileNo) {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationDaoImpl : searchStudentByTempAdmission Starting");
			
			List<secadmissionformformatVO> registrationList = new ArrayList<secadmissionformformatVO>();
			PreparedStatement pstmObj = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				secadmissionformformatVO list = new secadmissionformformatVO();
				
				conn = JDBCConnection.getSeparateGodaddyConnection();
				
				String sql = "select temporary_admission_id,concat(studentfirstName,' ',studentlastname) as studentname,dateofBirthId,preferedphno,schemeofstudy,classname,fathername,fathermobileno,mothername,mothermobile,createdTime from campus_temporary_admisssion_details where (";
				String remain_sql = null;
				String key = null;
				String val = null;
				String query = null;
				int count =0;
				
				HashMap map = new HashMap();
				ArrayList arr = new ArrayList();
				Vector vec = new Vector();
				
				
				map.put("concat(studentfirstName,' ',studentlastname)", studentName);
				vec.add("concat(studentfirstName,' ',studentlastname)");
				
				map.put("fathername", parentName);
				vec.add("fathername");
				
				map.put("mothername", parentName);
				vec.add("mothername");
				
				map.put("fathermobileno", mobileNo);
				vec.add("fathermobileno");
				
				map.put("mothermobile", mobileNo);
				vec.add("mothermobile");
				
				Iterator it = vec.iterator();
				
				while(it.hasNext())
				{
					key = it.next().toString();
					val = map.get(key).toString();
					
					
					if(count == 0)
					{
						remain_sql = key +" like '%"+val+"%'";
						count++;
					}
					else if(count == 1)
					{
						remain_sql = remain_sql + " and (";
						remain_sql = remain_sql + key +" like '"+val+"'";
						count++;
					}
					else if(count == 2)
					{
						remain_sql = remain_sql + " or ";
						remain_sql = remain_sql + key +" like '"+val+"'";
						remain_sql = remain_sql + ")";
						count++;
					}
					else if(count == 3)
					{
						remain_sql = remain_sql + " and (";
						remain_sql = remain_sql + key +" like '"+val+"'";
						count++;
					}
					else if(count == 4)
					{
						remain_sql = remain_sql + " or ";
						remain_sql = remain_sql + key +" like '"+val+"'";
						remain_sql = remain_sql + ")";
						
					}
			
				}
				
				remain_sql = remain_sql + " AND temporary_admission_id NOT IN(SELECT tempadmissionregno FROM campus_student WHERE tempadmissionregno IS NOT NULL))";
				query = sql + remain_sql+" ORDER BY createdTime";
				
				System.out.println("query  "+query);
				pstmObj = conn.prepareStatement(query);

				rs = pstmObj.executeQuery();

				while (rs.next()) {
					secadmissionformformatVO templist = new secadmissionformformatVO();
					secadmissionformformatVO vo = new secadmissionformformatVO();
					templist.setTemp_regid(rs.getString("temporary_admission_id"));
					templist.setStudentname(rs.getString("studentname"));
					templist.setDateofBirth(rs.getString("dateofBirthId"));
					templist.setFatherName(rs.getString("fathername"));
					
					if(rs.getString("fathername").equals(null) || rs.getString("fathername").equals(""))
					{
						templist.setFatherName("mothername");
					}
					
					templist.setFatherMobileNo(rs.getString("fathermobileno"));
					
					if((rs.getString("fathername").equals(null) || rs.getString("fathername").equals("")) && 
							(rs.getString("fathermobileno").equals(null) || rs.getString("fathermobileno").equals("")))
					{
						templist.setFatherMobileNo("mothermobile");
					}
					templist.setSchemeofstudy(rs.getString("schemeofstudy"));
					templist.setCreated_date(rs.getString("createdTime"));
					
					registrationList.add(templist);

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
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in StudentRegistrationDaoImpl : searchStudentByTempAdmission Ending");

			return registrationList;
	}

	public List<StudentRegistrationVo> searchStudentByStudentName(
			StudentRegistrationVo registrationVo) {
	
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Starting");
			String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
			List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
			PreparedStatement pstmObj = null;
			ResultSet rs = null;
			Connection conn = null;
			try {

				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmObj = conn
						.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_STUDENTNAME);

				pstmObj.setString(1, searchTerm);
				pstmObj.setString(2, searchTerm);

				rs = pstmObj.executeQuery();

				while (rs.next()) {
					StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
					studentRegistrationVo.setStudentnamelabel(rs.getString("studentname"));
					studentRegistrationVo.setStudentidlabel(rs.getString("temporary_admission_id"));
					registrationList.add(studentRegistrationVo);

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
					if (pstmObj != null && (!pstmObj.isClosed())) {
						pstmObj.close();
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
					+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Ending");

			return registrationList;

		}

	public List<StudentRegistrationVo> searchStudentByParentName(
			StudentRegistrationVo registrationVo) {
		
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Starting");
				
				String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
				List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
				PreparedStatement pstmObj = null;
				ResultSet rs = null;
				Connection conn = null;
				try {

					conn = JDBCConnection.getSeparateGodaddyConnection();
					pstmObj = conn
							.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_PARENTNAME);

					pstmObj.setString(1, searchTerm);
					pstmObj.setString(2, searchTerm);

					rs = pstmObj.executeQuery();

					while (rs.next()) {
						StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
						
						if(rs.getString("fathername").equals(null) || rs.getString("fathername").equals(""))
						{
							studentRegistrationVo.setParentNameLabel(rs.getString("mothername"));
						}
						else
						{
							studentRegistrationVo.setParentNameLabel(rs.getString("fathername"));
						}
						
						registrationList.add(studentRegistrationVo);

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
						if (pstmObj != null && (!pstmObj.isClosed())) {
							pstmObj.close();
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
						+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Ending");

				return registrationList;

			}

	public List<StudentRegistrationVo> searchStudentByMobileNo(
			StudentRegistrationVo registrationVo) {
			
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in StudentRegistrationDaoImpl : searchStudentByMobileNo Starting");
					
					String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
					List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
					PreparedStatement pstmObj = null;
					ResultSet rs = null;
					Connection conn = null;
					try {

						conn = JDBCConnection.getSeparateGodaddyConnection();
						pstmObj = conn
								.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_MOBILENO);

						pstmObj.setString(1, searchTerm);
						pstmObj.setString(2, searchTerm);

						rs = pstmObj.executeQuery();

						while (rs.next()) {
							StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
							
							if(rs.getString("fathermobileno").equals(null) || rs.getString("fathermobileno").equals(""))
							{
								studentRegistrationVo.setParentMobileLabel(rs.getString("mothermobile"));
							}
							else
							{
								studentRegistrationVo.setParentMobileLabel(rs.getString("fathermobileno"));
							}
							
							registrationList.add(studentRegistrationVo);

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
							if (pstmObj != null && (!pstmObj.isClosed())) {
								pstmObj.close();
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
							+ " Control in StudentRegistrationDaoImpl : searchStudentByMobileNo Ending");

					return registrationList;

				}



	public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
			String locationid, String flag, String status) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeldDetailsList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accyear;
		String loc_Id = locationid;
		String flag_tab = flag;
		String status1=status;
		
		int count = 0;

		try{
			if(flag_tab.equalsIgnoreCase("withheld"))
			{
				
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAILS_BY_HISTORY);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();
				System.out.println(pstmObj);
				
				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("withheld_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_date")));
					studentRegVo.setConfidentialComments(rs.getString("withheld_reason"));
					studentRegVo.setStatus(rs.getString("withheld_status"));
					if(rs.getString("withheld_cancelled_date") != null){
						studentRegVo.setCancelDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_cancelled_date")));
					}else{
						studentRegVo.setCancelDate("");
					}
					
					if(rs.getString("withheld_cancel_reason") != null){
						studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));
					}else{
						studentRegVo.setCancelDate("");
					}

					list.add(studentRegVo);
					
				}
			}else if(flag_tab.equalsIgnoreCase("cancel")){
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAILS_BY_CANCEL);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();
				
				while(rs.next())
				{
                    StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
					
					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("withheld_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_date")));
					studentRegVo.setConfidentialComments(rs.getString("withheld_reason"));
					studentRegVo.setStatus(rs.getString("withheld_status"));
					if(rs.getString("withheld_cancelled_date") != null){
						studentRegVo.setCancelDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_cancelled_date")));
					}else{
						studentRegVo.setCancelDate("");
					}
					if(rs.getString("withheld_cancel_reason") != null){
						studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));
					}else{
						studentRegVo.setCancelDate("");
					}
					/*studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));*/

					list.add(studentRegVo);
				}
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeldDetailsList Ending");
		return list;

	}

	public String deleteWithHeldDetails(String deleteId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteWithHeldDetails Starting");
		

		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.DELETEL_WITHHELD_DETAIL);
			pst.setString(1,deleteid);
			
			result = pst.executeUpdate();
			System.out.println("from deleteWithHeldDetails :"+pst);
				
			if (result > 0) {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteWithHeldDetails Ending");
		
		return status;
	}

	public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySpecialization  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {
			
			String locationid = details.getLocationid();
			String accyear = details.getAccyear();
			String classname = details.getClassid();
			String sectionid = details.getSectionid();
			String specializationName = details.getSpecialization();
			
			if(specializationName.equalsIgnoreCase("all")){
				specializationName="%%";
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SPECIALIZATION);
				pst.setString(1, specializationName);
				pst.setString(2, locationid);
				pst.setString(3, classname);
				pst.setString(4, sectionid);
				pst.setString(5, accyear);
			
			}
			else{
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SPECIALIZATION);
				pst.setString(1, specializationName);
				pst.setString(2, locationid);
				pst.setString(3, classname);
				pst.setString(4, sectionid);
				pst.setString(5, accyear);
		
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, specializationName);
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
					
				}
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySpecialization  Ending");

		return list;
	}
	
	



	public java.util.List<StudentRegistrationVo> getStudentListByJsForScholorship(
			StudentRegistrationVo data) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByJsForScholorship  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		

		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			if(data.getSearchTerm().equalsIgnoreCase("all")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_BY_JS_FOR_SCHOLORSHIP);
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_SEARCH_BY_JS_FOR_SCHOLORSHIP);
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
				pst.setString(5, "%" + data.getSearchTerm() + "%");
				pst.setString(6, "%" + data.getSearchTerm() + "%");
				pst.setString(7, "%" + data.getSearchTerm() + "%");
				pst.setString(8, "%" + data.getSearchTerm() + "%");
				pst.setString(9, "%" + data.getSearchTerm() + "%");
				pst.setString(10, "%" + data.getSearchTerm() + "%");
				pst.setString(11, "%" + data.getSearchTerm() + "%");
				pst.setString(12, "%" + data.getSearchTerm() + "%");
				pst.setString(13, "%" + data.getSearchTerm() + "%");
				pst.setString(14, "%" + data.getSearchTerm() + "%");
		
			}
			System.out.println("student "+pst);
			rs = pst.executeQuery();
		
			
			
			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				registrationVo.setConcession(rs.getString("concession"));
				registrationVo.setConcessionType(rs.getString("concessionType"));
				PreparedStatement termpstmt=conn.prepareStatement("SELECT termid,termname FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=?");
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement("SELECT COUNT(*) FROM  campus_fee_collection WHERE termcode=? AND admissionNo=?");
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				
				
				
				list.add(registrationVo);
			}

		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByJsForScholorship  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId, String accYear,String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getIDCardPhotoSheet Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String sec_Id = sectionId;
		String cls_Id = classId;
		String acc_year = accYear;
		String loc_Id = locationId;
	try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_IDCARD_STU_PHOTOSHEET);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, cls_Id);
			pstmObj.setString(3, sec_Id);
			pstmObj.setString(4, acc_year);

			
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				
				
				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : getIDCardPhotoSheet Ending");
		
		
		return list;
	}

	public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : ShowStudentAddress Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null,pstmObj1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		int count = 0;
		int conf_count = 0;

		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_ADDRESS_HISTORY);
			pstmObj.setString(1,studentId);
            rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setAddress(rs.getString("address"));
				obj.setPresentaddress(rs.getString("presentAddress"));
				list.add(obj);
				
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl : ShowStudentAddress Ending");

		return list;
	}

	public String getAdmissionNo(String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteWithHeldDetails Starting");
		

		PreparedStatement pst = null;
		ResultSet rs=null;
		Connection conn = null;
		String admissionNo = null,ConstantId=null;
		int IncrementValue=0;
		try{
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_ADMISSSION_NO);
			pst.setString(1,locationId);
			rs = pst.executeQuery();
			while(rs.next()){
				ConstantId=rs.getString("ConstantId");
				IncrementValue=rs.getInt("IncrementValue")+1;
				admissionNo=ConstantId+IncrementValue;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteConfidentialDetails Ending");
		
		return admissionNo;
	}
	
	
	public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result){

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Starting");
			StudentRegistrationForm registration = new StudentRegistrationForm();
			PreparedStatement pstmt = null;
			Connection con = null;
			int status = 0;
			String smsg = null;
			System.out.println("result  ==  " + result);
			try {
				System.out.println("Inside the saveStudentPromotion DaoImpl");
				con = JDBCConnection.getSeparateGodaddyConnection();
				con.setAutoCommit(false);
				Timestamp createdDate = HelperClass.getCurrentTimestamp();
				
				for (int i = 0; i < splitstudentid.length; i++) {
					String idgen=IDGenerator.getPrimaryKeyID("campus_tc_details",con);
					pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.GENERATE_TC);
					pstmt.setString(1, idgen);
					pstmt.setString(2, splitlocation[i]);
					pstmt.setString(3, splitaccyear[i]);
					pstmt.setString(4, splitstudentid[i]);
					pstmt.setString(5, splitadmid[i]);
					pstmt.setString(6, splitclassid[i]);
					
					pstmt.setTimestamp(7, createdDate);
					pstmt.setString(8, registration.getCreateUser());
					
					pstmt.setString(9, examdetails);
					pstmt.setString(10, result);
					pstmt.setString(11, reason);
					pstmt.setString(12, remarks);
					
					pstmt.setTimestamp(13, createdDate);
					pstmt.setString(14, registration.getCreateUser());
					
					status = pstmt.executeUpdate();	
					
					System.out.println("status "+pstmt);
					if(status>0){
						con.commit();
						smsg="TC Generated Successfully...";
					}else{
						con.rollback();
						smsg="TC Generation Failed";
					}
									
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}finally{

				try {
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (con != null && (!con.isClosed())) {
						con.close();
					}
				}catch (SQLException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Ending");
			return smsg;
		}

	public List<StudentRegistrationVo> TCGeneration(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			pst = conn.prepareStatement(SQLUtilConstants.TC_GENERATION);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			
			rs = pst.executeQuery();
			System.out.println(pst);
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration Ending");
		
		return list;
	}
	
public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration1  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {
			
			
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TC_GENERATION1);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);
			
			rs = pst.executeQuery();
			System.out.println(pst);
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
					pstmt.setString(1, rs.getString("specilization"));
					
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
				}
				list.add(registrationVo);
			}
			
		} catch (Exception e) {
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
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}

public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getNotGenTC Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		pst = conn.prepareStatement(SQLUtilConstants.TC_LIST);
				
		rs = pst.executeQuery();
		System.out.println(pst);
			
		while(rs.next()){
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			stuReg.setStudentId(rs.getString("student_id_int"));
			stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			stuReg.setLocationId(rs.getString("locationId"));
			stuReg.setStudentrollno(rs.getString("student_rollno"));
			stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			stuReg.setStudentFullName(rs.getString("studentName"));
			stuReg.setClassname(rs.getString("classdetails_name_var"));
			stuReg.setSectionnaem(rs.getString("classsection_name_var"));
			stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
			stuReg.setSectioncode(rs.getString("classsection_id_int"));
			stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getNotGenTC Ending");
	
	return list;
}


public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname,
		String sectionid,String sortingby,String orderby) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentListByTC  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rst = null;
	Connection conn = null;
	int sno=0;
	
	if(locationid.equalsIgnoreCase("all")){
		locationid="%%";
	}
	if(classname.equalsIgnoreCase("all")){
		classname="%%";
	}
	if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("undefined") || sectionid.equalsIgnoreCase(null) ){
		sectionid="%%";
	}

	try {
		conn = JDBCConnection.getSeparateGodaddyConnection();
		if(sortingby.equalsIgnoreCase("Gender")){
			System.out.println("orderby"+orderby);
			if(orderby.equalsIgnoreCase("DESC")){
				
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION61);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION71);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}
		}else if(sortingby.equalsIgnoreCase("Admission")){
			if(orderby.equalsIgnoreCase("DESC")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION51);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION41);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}
		}else if(sortingby.equalsIgnoreCase("Alphabetical")){
			if(orderby.equalsIgnoreCase("DESC")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION31);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION21);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}
		}
		System.out.println(pst);
		
		while (rs.next()) {
			sno++;
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			registrationVo.setCount(sno);
			registrationVo.setStudentId(rs.getString("student_id_int"));
			registrationVo.setLocationId(rs.getString("locationId"));
			registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			registrationVo.setStudentnamelabel(rs.getString("studentname"));
			registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			registrationVo.setAcademicYear(rs.getString("acadamic_year"));
			registrationVo.setStudentrollno(rs.getString("student_rollno"));
			registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
			registrationVo.setSectioncode(rs.getString("classsection_id_int"));
			registrationVo.setGender(rs.getString("student_gender_var"));
			registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
			registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
			registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			
			registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
			registrationVo.setStudentFullName(rs.getString("studentname"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			
			PreparedStatement SecondlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
			SecondlanguageName.setString(1, rs.getString("secondlanguage"));
			ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
			while(SecondLangaugeRs.next()){
				registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
			}
	
			PreparedStatement thirdlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
			thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
			ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
			while(thirdlanguageRs.next()){
				registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
			}	
	
			if(rs.getString("specilization").equalsIgnoreCase("-"))
			{
				registrationVo.setSpecilizationname("-");
			}
			else
			{
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
				pstmt.setString(1, rs.getString("specilization"));
				
				rst = pstmt.executeQuery();
				while(rst.next())
				{
					registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
				}
				
			}
			
			list.add(registrationVo);
		}

	} catch (Exception e) {
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
			+ " Control in StudentRegistrationDaoImpl : getStudentListByTC  Ending");

	return list;
}

/*public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in GeneratePayrollDaoImpl: TransferCertificateDownload : Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	PreparedStatement pst2 = null;


	ResultSet rs = null,rs1 = null,rs2 = null;

	Connection conn = null;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat format2 = new SimpleDateFormat("dd-MMMMM-yyyy");
	DateFormat format3 = new SimpleDateFormat("MMMMM-yyyy");
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		for(int i =0;i<studentid.split(",").length;i++)
		{
		pst = conn.prepareStatement("SELECT ctd.TC_NO,ctd.admission_class,ctd.esub,cscd.specilization,stu.student_doj_var,ccls.classdetails_name_var classId,ctd.TCCode,ctd.result,ctd.locationId,ctd.acadamic_year,ctd.admissionNo,ctd.student_id_int,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,cpar.FatherName,cpar.student_mothername_var AS MotherName,stu.student_nationality_var AS Nationality,ccc.casteCategory,ccaste.caste,stu.student_dob_var AS student_birth_date,stu.student_doj_var AS date_of_joining,ccls.classdetails_name_var AS class_latest,ccls.classdetail_id_int,ctd.csub AS compulsory_sub,ctd.ladate AS last_attendance, ctd.promotion_date AS promotion_date,ctd.appdate AS stuck_of_rolls,ctd.appdate AS date_applicatin_certificate,ctd.appdate AS date_issue_certificate,ccls.createdate,ctd.school_or_board_Examination,ctd.reason,ctd.remarks,cfc.is_paid,cftd.startdate,cftd.enddate,cschlr.concessionType,rel.religion  FROM campus_tc_details ctd LEFT JOIN campus_student stu ON ctd.student_id_int = stu.student_id_int LEFT JOIN campus_religion rel ON stu.student_religion_var = rel.religionId LEFT JOIN campus_caste_category ccc ON stu.casteCategory = ccc.castCatId LEFT JOIN campus_caste ccaste ON stu.student_caste = ccaste.casteId LEFT JOIN campus_parentchildrelation cpchil  ON ctd.student_id_int = cpchil.stu_addmissionNo LEFT JOIN campus_parents cpar  ON cpchil.parentid = cpar.ParentID LEFT JOIN  campus_student_classdetails cscd  ON ctd.student_id_int  = cscd.student_id_int AND ctd.acadamic_year=cscd.fms_acadamicyear_id_int LEFT JOIN  campus_classdetail ccls  ON (cscd.classdetail_id_int  = ccls.classdetail_id_int AND cscd.locationId=ccls.locationId) LEFT JOIN campus_fee_collection cfc ON ctd.student_id_int = cfc.admissionNo LEFT JOIN campus_fee_termdetails cftd ON cfc.termcode = cftd.termid LEFT JOIN campus_scholorship cschlr  ON ctd.admissionNo = cschlr.admissionNo WHERE ctd.student_id_int=? and ctd.acadamic_year=? ORDER BY startdate DESC LIMIT 1");
		pst.setString(1, studentid.split(",")[i]);
		pst.setString(2, accyear.split(",")[i]);
		
		System.out.println("TC_PDF = "+pst);
				
		rs = pst.executeQuery();
			
		while(rs.next()){
			com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();

			
			
			
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			
			stuReg.setAdmissionNo(rs.getString("admissionNo"));
			stuReg.setResult(rs.getString("result"));
			
			if(rs.getString("result").equalsIgnoreCase("fail")){
				stuReg.setResultstatus("NO");
			}else{
				stuReg.setResultstatus("YES");
			}
			System.out.println("rs.getString(is_paid)  == " + rs.getString("is_paid"));
			if(rs.getString("is_paid")==null || rs.getString("is_paid").equalsIgnoreCase("N")){
				stuReg.setFeestat("NO, PAID UPTO:");
				stuReg.setPaidupto("---");
			}else if(rs.getString("is_paid").equalsIgnoreCase("Y")){
				stuReg.setFeestat("YES, PAID UPTO:");
				
				String paidUpto=rs.getString("enddate").split("-")[0]+"-03-31";
				
				stuReg.setPaidupto(format3.format(format1.parse(paidUpto)));
			}
			
			if(rs.getString("concessionType") != null){
				stuReg.setScholarShip("YES");
			}else {
				stuReg.setScholarShip("NO");
			}
			
			String academicYear=HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[0].substring(2)+"-"+HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[1].substring(2);
			String tc_no=(rs.getString("class_latest")+"/"+academicYear+"/"+String.format("%04d", rs.getInt("TC_NO")));
			stuReg.setTc_no(tc_no);
			stuReg.setTccode(rs.getString("TCCode"));
			
			if(rs.getString("student_doj_var")!=null)
			stuReg.setDateofjoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
			
			String promodt = rs.getString("promotion_date");
			String prodt="";
			if(promodt !=null) {
				String arr[] = promodt.split(" ", 2);
				String firstWord = arr[0];
				Date pd = format1.parse(firstWord);
				 prodt = format2.format(pd);
			}
			
			stuReg.setPromotionDate(prodt);
			
			stuReg.setStudentFullName(rs.getString("studentname"));
			stuReg.setStudoj(rs.getString("student_doj_var"));
			stuReg.setTcno(rs.getString("TCCode"));
			stuReg.setFatherName(rs.getString("FatherName"));
			stuReg.setMotherName(rs.getString("MotherName"));
			stuReg.setNationality(rs.getString("Nationality"));
			String specialization=rs.getString("specilization");
			stuReg.setCasteCategory(rs.getString("religion")+","+rs.getString("caste"));
			if(stuReg.getCasteCategory() == "SC" || stuReg.getCasteCategory() == "ST" ){
				stuReg.setCasteCategoryStatus("YES,");
			}else{
				stuReg.setCasteCategoryStatus("No,");
			}
			
			stuReg.setCaste(rs.getString("caste"));
			
			Date date4 = format1.parse(rs.getString("student_birth_date"));
			String[] split=rs.getString("student_birth_date").split("-");
			int day = Integer.parseInt(split[2]);
			int month = Integer.parseInt(split[1]);
			int year = Integer.parseInt(split[0]);
			stuReg.setDateofBirthInWords("("+num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year)+")");

			
			String dateString4 = format2.format(date4);
			stuReg.setDateofBirth(dateString4);
			if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD1")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD2")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD3")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD4")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FIRST)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD5")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SECOND)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD6")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (THIRD)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD7")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FOURTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD8")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FIFTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD9")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SIXTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD10")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SEVENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD11")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (EIGHTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD12")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (NINTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD13")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (TENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD14")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (ELEVENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD15")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (TWELTH)");
			}
			
			
			stuReg.setCompulsorySub(rs.getString("compulsory_sub"));
			stuReg.setElectiveSub(rs.getString("esub"));
		
			if(rs.getString("last_attendance") != null){
				Date date = format1.parse(rs.getString("last_attendance"));
				String dateString = format2.format(date);
				stuReg.setLastAttended(dateString);
			}else{
				stuReg.setLastAttended("---");
			}
			
			Date date1 = format1.parse(rs.getString("stuck_of_rolls"));
			String dateString1 = format2.format(date1);
			stuReg.setStuckOfRolls(dateString1);
			
			stuReg.setCertificateDateApply(rs.getString("date_applicatin_certificate"));
			Date date2 = format1.parse(stuReg.getCertificateDateApply());
			String dateString2 = format2.format(date2);
			stuReg.setCertificateDateApply(dateString2);
			
			Date date3 = format1.parse(rs.getString("date_issue_certificate"));
			String dateString3 = format2.format(date3);
			stuReg.setCertificateIssueDate(dateString3);
			
			stuReg.setExam_name(rs.getString("school_or_board_Examination"));
			stuReg.setReasonForTc(rs.getString("reason"));
			stuReg.setRemarks(rs.getString("remarks"));
			stuReg.setClassname(rs.getString("admission_class"));
			list.add(stuReg);
			
		}
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (rs2 != null && (!rs2.isClosed())) {
				rs2.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
			}
			if (pst1 != null && (!pst1.isClosed())) {
				pst1.close();
			}
			if (pst2 != null && (!pst2.isClosed())) {
				pst2.close();
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
			+ " Control in StudentRegistrationDaoImpl : TransferCertificateDownload Ending");
	
	return list;

}*/
public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in GeneratePayrollDaoImpl: TransferCertificateDownload : Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	PreparedStatement pst2 = null;


	ResultSet rs = null,rs1 = null,rs2 = null;

	Connection conn = null;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat format2 = new SimpleDateFormat("dd-MMMMM-yyyy");
	DateFormat format3 = new SimpleDateFormat("MMMMM-yyyy");
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		for(int i =0;i<studentid.split(",").length;i++)
		{
		pst = conn.prepareStatement("SELECT ctd.TC_NO,ctd.admission_class,ctd.esub,cscd.specilization,stu.student_doj_var,ccls.classdetails_name_var classId,ctd.TCCode,ctd.result,ctd.locationId,ctd.acadamic_year,ctd.admissionNo,ctd.student_id_int,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,cpar.FatherName,cpar.student_mothername_var AS MotherName,stu.student_nationality_var AS Nationality,ccc.casteCategory,ccaste.caste,stu.student_dob_var AS student_birth_date,stu.student_doj_var AS date_of_joining,ccls.classdetails_name_var AS class_latest,ccls.classdetail_id_int,ctd.csub AS compulsory_sub,ctd.ladate AS last_attendance, stu.modifydate AS promotion_date,ctd.appdate AS stuck_of_rolls,ctd.appdate AS date_applicatin_certificate,ctd.appdate AS date_issue_certificate,ccls.createdate,ctd.school_or_board_Examination,ctd.reason,ctd.remarks,cfc.is_paid,cftd.startdate,cftd.enddate,cschlr.concessionType,rel.religion  FROM campus_tc_details ctd LEFT JOIN campus_student stu ON ctd.student_id_int = stu.student_id_int LEFT JOIN campus_religion rel ON stu.student_religion_var = rel.religionId LEFT JOIN campus_caste_category ccc ON stu.casteCategory = ccc.castCatId LEFT JOIN campus_caste ccaste ON stu.student_caste = ccaste.casteId LEFT JOIN campus_parentchildrelation cpchil  ON ctd.student_id_int = cpchil.stu_addmissionNo LEFT JOIN campus_parents cpar  ON cpchil.parentid = cpar.ParentID LEFT JOIN  campus_student_classdetails cscd  ON stu.student_id_int  = cscd.student_id_int LEFT JOIN  campus_classdetail ccls  ON (cscd.classdetail_id_int  = ccls.classdetail_id_int AND cscd.locationId=ccls.locationId) LEFT JOIN campus_fee_collection cfc ON ctd.student_id_int = cfc.admissionNo LEFT JOIN campus_fee_termdetails cftd ON cfc.termcode = cftd.termid LEFT JOIN campus_scholorship cschlr  ON ctd.admissionNo = cschlr.admissionNo WHERE ctd.student_id_int=? and ctd.acadamic_year=? ORDER BY startdate DESC LIMIT 1");
		pst.setString(1, studentid.split(",")[i]);
		pst.setString(2, accyear.split(",")[i]);
		
		System.out.println("TC_PDF = "+pst);
				
		rs = pst.executeQuery();
			
		while(rs.next()){
			com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();

			
			
			
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			
			stuReg.setAdmissionNo(rs.getString("admissionNo"));
			stuReg.setResult(rs.getString("result"));
			
			if(rs.getString("result").equalsIgnoreCase("pass")){
				stuReg.setResultstatus("YES");
			}else{
				stuReg.setResultstatus("No");
			}
			System.out.println("rs.getString(is_paid)  == " + rs.getString("is_paid"));
			if(rs.getString("is_paid")==null || rs.getString("is_paid").equalsIgnoreCase("N")){
				stuReg.setFeestat("NO, PAID UPTO:");
				stuReg.setPaidupto("---");
			}else if(rs.getString("is_paid").equalsIgnoreCase("Y")){
				stuReg.setFeestat("YES, PAID UPTO:");
				
				String paidUpto=rs.getString("enddate").split("-")[0]+"-03-31";
				
				stuReg.setPaidupto(format3.format(format1.parse(paidUpto)));
			}
			
			if(rs.getString("concessionType") != null){
				stuReg.setScholarShip("YES");
			}else {
				stuReg.setScholarShip("NO");
			}
			
			String academicYear=HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[0].substring(2)+"-"+HelperClass.getAcademicYearFace(accyear.split(",")[0]).split("-")[1].substring(2);
			String tc_no=(rs.getString("class_latest")+"/"+academicYear+"/"+String.format("%04d", rs.getInt("TC_NO")));
			stuReg.setTc_no(tc_no);
			stuReg.setTccode(rs.getString("TCCode"));
			
			if(rs.getString("student_doj_var")!=null)
			stuReg.setDateofjoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
			
			String promodt = rs.getString("createdate");
			String arr[] = promodt.split(" ", 2);
			String firstWord = arr[0];
			Date pd = format1.parse(firstWord);
			String prodt = format2.format(pd);
			stuReg.setPromotionDate(prodt);
			
			stuReg.setStudentFullName(rs.getString("studentname"));
			stuReg.setStudoj(rs.getString("student_doj_var"));
			stuReg.setTcno(rs.getString("TCCode"));
			stuReg.setFatherName(rs.getString("FatherName"));
			stuReg.setMotherName(rs.getString("MotherName"));
			stuReg.setNationality(rs.getString("Nationality"));
			String specialization=rs.getString("specilization");
			stuReg.setCasteCategory(rs.getString("religion")+","+rs.getString("caste"));
			if(stuReg.getCasteCategory() == "SC" || stuReg.getCasteCategory() == "ST" ){
				stuReg.setCasteCategoryStatus("YES,");
			}else{
				stuReg.setCasteCategoryStatus("No,");
			}
			
			stuReg.setCaste(rs.getString("caste"));
			
			Date date4 = format1.parse(rs.getString("student_birth_date"));
			String[] split=rs.getString("student_birth_date").split("-");
			int day = Integer.parseInt(split[2]);
			int month = Integer.parseInt(split[1]);
			int year = Integer.parseInt(split[0]);
			stuReg.setDateofBirthInWords("("+num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year)+")");

			
			String dateString4 = format2.format(date4);
			stuReg.setDateofBirth(dateString4);
			if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD1")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD2")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD3")) {
				stuReg.setLatestclass(rs.getString("class_latest"));
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD4")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FIRST)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD5")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SECOND)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD6")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (THIRD)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD7")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FOURTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD8")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (FIFTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD9")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SIXTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD10")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (SEVENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD11")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (EIGHTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD12")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (NINTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD13")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (TENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD14")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (ELEVENTH)");
			}
			else if(rs.getString("classdetail_id_int").equalsIgnoreCase("CCD15")) {
				stuReg.setLatestclass(rs.getString("class_latest")+" (TWELTH)");
			}
			
			
			stuReg.setCompulsorySub(rs.getString("compulsory_sub"));
			stuReg.setElectiveSub(rs.getString("esub"));
		
			if(rs.getString("last_attendance") != null){
				Date date = format1.parse(rs.getString("last_attendance"));
				String dateString = format2.format(date);
				stuReg.setLastAttended(dateString);
			}else{
				stuReg.setLastAttended("---");
			}
			
			Date date1 = format1.parse(rs.getString("stuck_of_rolls"));
			String dateString1 = format2.format(date1);
			stuReg.setStuckOfRolls(dateString1);
			
			stuReg.setCertificateDateApply(rs.getString("date_applicatin_certificate"));
			Date date2 = format1.parse(stuReg.getCertificateDateApply());
			String dateString2 = format2.format(date2);
			stuReg.setCertificateDateApply(dateString2);
			
			Date date3 = format1.parse(rs.getString("date_issue_certificate"));
			String dateString3 = format2.format(date3);
			stuReg.setCertificateIssueDate(dateString3);
			
			stuReg.setExam_name(rs.getString("school_or_board_Examination"));
			stuReg.setReasonForTc(rs.getString("reason"));
			stuReg.setRemarks(rs.getString("remarks"));
			stuReg.setClassname(rs.getString("admission_class"));
			list.add(stuReg);
			
		}
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (rs2 != null && (!rs2.isClosed())) {
				rs2.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
			}
			if (pst1 != null && (!pst1.isClosed())) {
				pst1.close();
			}
			if (pst2 != null && (!pst2.isClosed())) {
				pst2.close();
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
			+ " Control in StudentRegistrationDaoImpl : TransferCertificateDownload Ending");
	
	return list;

}

public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : GenTCListFilter Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	
	String locationid = vo.getLocationId();
	String accyear = vo.getAccyear();
	String classname = vo.getClassname();
	String sectionid = vo.getSection_id();
	String sortingby = vo.getSortBy();
	String orderby = vo.getOrderBy();
	
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		/*pst = conn.prepareStatement(SQLUtilConstants.TC_LIST);*/
		System.out.println("sortingby  ==  "+sortingby);
		System.out.println("orderby ==  "+orderby);
		
		if(sortingby.equalsIgnoreCase("Gender")){
			if(orderby.equalsIgnoreCase("DESC1")){
				
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST1);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST2);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());
					rs = pst.executeQuery();
				}
		}else if(sortingby.equalsIgnoreCase("Admission")){
			System.out.println(orderby);
			if(orderby.equalsIgnoreCase("DESC1")){
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST3);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST4);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}
		}else if(sortingby.equalsIgnoreCase("Alphabetical")){
			if(orderby.equalsIgnoreCase("DESC1")){
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST5);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}else{
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST6);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
			}
		}
				
		rs = pst.executeQuery();
		System.out.println("pst---------"+pst);
			
		while(rs.next()){
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			stuReg.setStudentId(rs.getString("student_id_int"));
			stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			stuReg.setLocationId(rs.getString("locationId"));
			stuReg.setStudentrollno(rs.getString("student_rollno"));
			stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			stuReg.setStudentFullName(rs.getString("studentName"));
			stuReg.setClassname(rs.getString("classdetails_name_var"));
			stuReg.setSectionnaem(rs.getString("classsection_name_var"));
			stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
			stuReg.setSectioncode(rs.getString("classsection_id_int"));
			stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : GenTCListFilter Ending");
	
	return list;
}


public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : GenTCListSearch Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	
	String searchValue = vo.getSearchTerm();
	
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement(SQLUtilConstants.TC_LIST7);
				pst.setString(1, "%"+searchValue+"%");
				pst.setString(2, "%"+searchValue+"%");
				pst.setString(3, "%"+searchValue+"%");
				
		rs = pst.executeQuery();
		System.out.println("pst---------"+pst);
			
		while(rs.next()){
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			stuReg.setStudentId(rs.getString("student_id_int"));
			stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			stuReg.setLocationId(rs.getString("locationId"));
			stuReg.setStudentrollno(rs.getString("student_rollno"));
			stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			stuReg.setStudentFullName(rs.getString("studentName"));
			stuReg.setClassname(rs.getString("classdetails_name_var"));
			stuReg.setSectionnaem(rs.getString("classsection_name_var"));
			stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
			stuReg.setSectioncode(rs.getString("classsection_id_int"));
			stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : GenTCListSearch Ending");
	
	return list;
}

public List<StudentRegistrationVo> getAdmissionDetails(String tempadmissionid) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getAdmissionDetails Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		pst = conn.prepareStatement("SELECT DISTINCT temp.*,CASE WHEN caste.caste IS NULL THEN temp.othercaste ELSE caste.caste END castename,state,CASE WHEN reg.religion IS NULL THEN temp.othersreligion ELSE reg.religion END religionname,cd.classdetails_name_var,CASE WHEN cat.casteCategory IS NULL THEN temp.othercastecategory ELSE cat.casteCategory END casteCategoryname,occu.occupation foccupation,occus.occupation moccupation FROM campus_temporary_admisssion_details temp JOIN campus_classdetail cd ON cd.classdetail_id_int = temp.classname LEFT JOIN campus_religion reg ON reg.religionId = temp.religion LEFT JOIN campus_caste caste ON temp.caste = caste.casteId LEFT JOIN campus_caste_category cat ON cat.castCatId = temp.othercastecategory LEFT JOIN campus_occupation occu ON occu.occupationId = temp.fatheroccupation LEFT JOIN  campus_occupation occus ON occus.occupationId = temp.motheroccupation  WHERE temporary_admission_id=?");
		pst.setString(1,tempadmissionid);
				
		rs = pst.executeQuery();
		System.out.println("pst---------"+pst);
			
		while(rs.next()){
			String cuudate = HelperClass.getCurrentSqlDate().toString();
			List<String> datesize = HelperClass.getDateListBetweenDates(rs.getString("dateofBirthId"),cuudate);
			int years = (datesize.size()-1)/365;
			int months = ((datesize.size()-1)%365)/30;
			StudentRegistrationVo stuReg = new StudentRegistrationVo();
			stuReg.setStudentFirstName(rs.getString("studentfirstName")+" "+rs.getString("studentlastname"));
			stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId")));
			stuReg.setGender(rs.getString("gender"));
			stuReg.setNationality(rs.getString("nationality"));
			stuReg.setReligion(rs.getString("religionname"));
			stuReg.setCaste(rs.getString("castename"));
			stuReg.setCasteCategory(rs.getString("casteCategoryname"));
			stuReg.setMothertongue(rs.getString("mothertongue"));
			stuReg.setAddress(rs.getString("permanentaddress"));
			stuReg.setPresentaddress(rs.getString("addressofcommunication"));
			stuReg.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
			stuReg.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
			stuReg.setFatherMobileNo(rs.getString("fathermobileno"));
			stuReg.setMotherMobileNo(rs.getString("mothermobile"));
			stuReg.setFatherName(rs.getString("fathername"));
			stuReg.setMotherName(rs.getString("mothername"));
			stuReg.setFatheroccupation(rs.getString("foccupation"));
			stuReg.setFatherDesignation(rs.getString("fatherdesignation"));
			stuReg.setMotherDesignation(rs.getString("motherdesignation"));
			stuReg.setMotheroccupation(rs.getString("moccupation"));
			stuReg.setImage(rs.getString("imageUrl"));
			stuReg.setSmsnumber(rs.getString("preferedphno"));
			stuReg.setMotherAnnualIncome(rs.getInt("mothermothlyincome"));
			stuReg.setFatherAnnualIncome(rs.getInt("fathermonthincome"));
			stuReg.setClassname(rs.getString("classdetails_name_var"));
			stuReg.setLastKindergartenName(rs.getString("previousschool"));
			stuReg.setSecondLanguage(rs.getString("secondlanguage"));
			stuReg.setThirdLanguage(rs.getString("thirdlanguage"));
			stuReg.setSchemeofstudy(rs.getString("schemeofstudy"));
			stuReg.setOpt_subjects(rs.getString("opt_subjects"));
			stuReg.setLandLine(rs.getString("landline_no"));
			stuReg.setCommunication_landline(rs.getString("comminication_landline"));
			stuReg.setFather_office_landline(rs.getString("father_office_landline"));
			stuReg.setMother_office_landline(rs.getString("mother_office_landline"));
			stuReg.setEmergencyNo(rs.getString("emergency_no"));
			stuReg.setAadharno(rs.getString("aadharNo"));
			if(rs.getString("Tc")==null) {
				stuReg.setIsTc("No");
			}
			else {
				if(rs.getString("Tc").length()>5) {
					stuReg.setIsTc("Yes");
				}
				else {
					stuReg.setIsTc("No");
				}
			}
			
			if(rs.getString("Migration")==null) {
				stuReg.setIsMigration("No");
			}
			else {
				if(rs.getString("Migration").length()>5) {
					stuReg.setIsMigration("Yes");
				}
				else {
					stuReg.setIsMigration("No");
				}
			}
			stuReg.setAge(years+" Years "+months+" Months ");
			
			
			com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();
			String datewords = null;
			String[] split=stuReg.getDateofBirth().split("-");
			int day = Integer.parseInt(split[0]);
			int month = Integer.parseInt(split[1]);
			int year = Integer.parseInt(split[2]);
			datewords = (num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year));
			stuReg.setDateofBirthInWords(datewords);
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getAdmissionDetails Ending");
	
	return list;
}

public List<StudentRegistrationVo> getStudentDetailsByJsInRegistration(
		StudentRegistrationVo data) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJsInRegistration  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	PreparedStatement pst1 = null;
	ResultSet rs1 = null;
	int totalCount=0;
	Connection conn = null;
	int sno=0;
	

	try {
		conn = JDBCConnection.getSeparateGodaddyConnection();
		if(data.getSearchTerm().equalsIgnoreCase("all") && data.getLocationId().equalsIgnoreCase("all")){
			pst = conn.prepareStatement("select ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.fms_acadamicyear_id_int=?   order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname limit ?,?");
			pst.setString(1, data.getAcademicYear());
			pst.setInt(2, data.getStartPoint());
			pst.setInt(3, data.getShow_per_page());
			
			pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.fms_acadamicyear_id_int=?");
			pst1.setString(1, data.getAcademicYear());
		}
		else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getClasscode().equalsIgnoreCase("all")){
			pst = conn.prepareStatement("select ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=?   order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname limit ?,?");
			pst.setString(1, data.getLocationId());
			pst.setString(2, data.getAcademicYear());
			pst.setInt(3, data.getStartPoint());
			pst.setInt(4, data.getShow_per_page());
			
			pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.locationId=? and csc.fms_acadamicyear_id_int=?");
			pst1.setString(1, data.getLocationId());
			pst1.setString(2, data.getAcademicYear());
			
			
		}
		else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getSection_id().equalsIgnoreCase("all")){
			pst = conn.prepareStatement("select ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=?  order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname limit ?,?");
			pst.setString(1, data.getLocationId());
			pst.setString(2, data.getAcademicYear());
			pst.setString(3, data.getClasscode());
			pst.setInt(4, data.getStartPoint());
			pst.setInt(5, data.getShow_per_page());
			
			pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=?");
			pst1.setString(1, data.getLocationId());
			pst1.setString(2, data.getAcademicYear());
			pst1.setString(3, data.getClasscode());
			
			
		}
		else if(data.getSearchTerm().equalsIgnoreCase("all")){
			pst = conn.prepareStatement("select  ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=?  order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname limit ?,?");
			pst.setString(1, data.getLocationId());
			pst.setString(2, data.getAcademicYear());
			pst.setString(3, data.getClasscode());
			pst.setString(4, data.getSection_id());
			pst.setInt(5, data.getStartPoint());
			pst.setInt(6, data.getShow_per_page());
			
			
			pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=?");
			pst1.setString(1, data.getLocationId());
			pst1.setString(2, data.getAcademicYear());
			pst1.setString(3, data.getClasscode());
			pst1.setString(4, data.getSection_id());
		}
		else{
			pst = conn.prepareStatement("select ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,stu.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ? and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname limit ?,?");
			if(data.getLocationId().equalsIgnoreCase("all")){
				pst.setString(1, "%%");
			}
			else{
				pst.setString(1, data.getLocationId());
			}
			
			if(data.getAcademicYear().equalsIgnoreCase("all")){
				pst.setString(2, "%%");
			}
			else{
			pst.setString(2, data.getAcademicYear());
			}
			if(data.getClasscode().equalsIgnoreCase("all")){
				pst.setString(3, "%%");
			}
			else{
			pst.setString(3, data.getClasscode());
			}
			if(data.getSection_id().equalsIgnoreCase("all")){
				pst.setString(4, "%%");
			}
			else{
			pst.setString(4, data.getSection_id());
			}
			
			pst.setString(5, "%" + data.getSearchTerm() + "%");
			pst.setString(6, "%" + data.getSearchTerm() + "%");
			pst.setString(7, "%" + data.getSearchTerm() + "%");
			pst.setString(8, "%" + data.getSearchTerm() + "%");
			pst.setString(9, "%" + data.getSearchTerm() + "%");
			pst.setString(10, "%" + data.getSearchTerm() + "%");
			pst.setString(11, "%" + data.getSearchTerm() + "%");
			pst.setString(12, "%" + data.getSearchTerm() + "%");
			pst.setString(13, "%" + data.getSearchTerm() + "%");
			pst.setString(14, "%" + data.getSearchTerm() + "%");
			pst.setInt(15, data.getStartPoint());
			pst.setInt(16, data.getShow_per_page());
			
			
			
			pst1 = conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ? and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?)");
			if(data.getLocationId().equalsIgnoreCase("all")){
				pst1.setString(1, "%%");
			}
			else{
				pst1.setString(1, data.getLocationId());
			}
			
			if(data.getAcademicYear().equalsIgnoreCase("all")){
				pst1.setString(2, "%%");
			}
			else{
			pst1.setString(2, data.getAcademicYear());
			}
			if(data.getClasscode().equalsIgnoreCase("all")){
				pst1.setString(3, "%%");
			}
			else{
			pst1.setString(3, data.getClasscode());
			}
			if(data.getSection_id().equalsIgnoreCase("all")){
				pst1.setString(4, "%%");
			}
			else{
			pst1.setString(4, data.getSection_id());
			}
			
			pst1.setString(5, "%" + data.getSearchTerm() + "%");
			pst1.setString(6, "%" + data.getSearchTerm() + "%");
			pst1.setString(7, "%" + data.getSearchTerm() + "%");
			pst1.setString(8, "%" + data.getSearchTerm() + "%");
			pst1.setString(9, "%" + data.getSearchTerm() + "%");
			pst1.setString(10, "%" + data.getSearchTerm() + "%");
			pst1.setString(11, "%" + data.getSearchTerm() + "%");
			pst1.setString(12, "%" + data.getSearchTerm() + "%");
			pst1.setString(13, "%" + data.getSearchTerm() + "%");
			pst1.setString(14, "%" + data.getSearchTerm() + "%");
			
	
		}
		System.out.println("studentcCCCC "+pst);
		rs = pst.executeQuery();
		rs1=pst1.executeQuery();
		while(rs1.next()){
		totalCount=rs1.getInt(1);	
		}
		
		
		while (rs.next()) {
			ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			sno++;
			registrationVo.setTotalCount(totalCount);
			registrationVo.setSno(String.valueOf(sno));
			registrationVo.setStudentId(rs.getString("student_id_int"));
			registrationVo.setLocationId(rs.getString("locationId"));
			registrationVo.setImage(rs.getString("imgurl"));
			registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			registrationVo.setStudentFullName(rs.getString("studentname"));
			registrationVo.setStudentrollno(rs.getString("student_rollno"));
			registrationVo.setClassname(rs.getString("classdetails_name_var"));
			registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
			registrationVo.setAcademicYear(rs.getString("acadamic_year"));
			registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			registrationVo.setDateofBirth(rs.getString("student_dob_var"));
			registrationVo.setStudentStatus(rs.getString("student_status"));
			PreparedStatement termpstmt=conn.prepareStatement(SQLUtilConstants.GET_TERMID_TERMNAME_BY_LOCATION_ACCYEAR);
			termpstmt.setString(1, rs.getString("locationId"));
			termpstmt.setString(2, data.getAcademicYear());
			ResultSet termRs=termpstmt.executeQuery();
			while(termRs.next()){
				int getcount=0;
				String paymentStatus="Not Paid";
				FeeNameVo fStatus=new FeeNameVo();
				PreparedStatement getStatus=conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_fee_collection_BY_TERMCODE_ADMISSIONNO);
				getStatus.setString(1, termRs.getString("termid"));
				getStatus.setString(2, rs.getString("student_id_int"));
				ResultSet getstRs=getStatus.executeQuery();
				if(getstRs.next()){
					getcount=getstRs.getInt(1);
				}
				if(getcount > 0){
					paymentStatus="Paid";
				}
				fStatus.setTerm(termRs.getString("termname"));
				fStatus.setStatus(paymentStatus);
				feeStatusList.add(fStatus);
			}
			registrationVo.setFeeStatus(feeStatusList);
			
			
			
			list.add(registrationVo);
		}

	} catch (Exception e) {
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
			+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJsInRegistration  Ending");

	return list;
}

public List<StudentConcessionVo> getStudentListbyAdmissionNo(String admissionNo, String accyear) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentListbyAdmissionNo Starting");
	
	List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	String sql="";
	
	
	
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement("SELECT st.student_id_int,st.student_fname_var,st.student_lname_var,cc.classdetail_id_int,cc.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var,csc.specilization FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int=csc.student_id_int  JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection cs ON (csc.classsection_id_int=cs.classsection_id_int AND csc.locationId=cs.locationId) WHERE st.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
				pst.setString(1, admissionNo);
				pst.setString(2, accyear);
				System.out.println("pst---------"+pst);
				rs = pst.executeQuery();
		
			
		while(rs.next()){
			StudentConcessionVo stuReg = new StudentConcessionVo();
			stuReg.setAdmissionNo(admissionNo);
			stuReg.setStudentId(rs.getString("student_id_int"));
			stuReg.setStudent(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
			stuReg.setClassId(rs.getString("classdetail_id_int"));
			stuReg.setClass_section(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
			stuReg.setSpecialization(rs.getString("specilization"));
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentListbyAdmissionNo Ending");
	
	return list;
}

public List<StudentConcessionVo> getTermdeatilsForConcession(String classId,
		String accyear, String specialization) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getTermdeatilsForConcession Starting");
	
	List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	
	
	
	try{
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement("SELECT cft.termid,cft.termname,cfs.`feecode`,cfm.`FeeName`, cfs.`feeAmount` FROM `campus_fee_setupdetails` cfs JOIN `campus_fee_master` cfm ON cfs.feecode=cfm.FeeCode JOIN `campus_fee_setup` cfst ON cfs.feeSettingCode=cfst.feeSettingcode JOIN `campus_fee_termdetails` cft ON cfst.`Termcode`=cft.termid WHERE cfs.feeSettingCode IN(SELECT `feeSettingcode` FROM `campus_fee_setup` WHERE ClassCode=? AND AccyearCode=? AND `specialization`=?) AND cfm.feeType='TOF'");
				pst.setString(1, classId);
				pst.setString(2, accyear);
				pst.setString(3, specialization);
				System.out.println("pst---------"+pst);
				rs = pst.executeQuery();
		
			
		while(rs.next()){
			StudentConcessionVo stuReg = new StudentConcessionVo();
			stuReg.setTermcode(rs.getString("termid"));
			stuReg.setTerm(rs.getString("termname"));
			stuReg.setFeecode(rs.getString("feecode"));
			stuReg.setFeename(rs.getString("FeeName"));
			stuReg.setTermTuitionFeeAmount(rs.getString("feeAmount"));
			list.add(stuReg);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
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
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getTermdeatilsForConcession Ending");
	
	return list;
}
public int savePhotoUploadLink(StudentRegistrationForm studentRegistrationForm) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl: savePhotoUploadLink : Starting");
	
	PreparedStatement pstmt = null;
	int count=0;
	ResultSet rs=null;
	Connection conn = null;
 	
	try {
		
		conn = JDBCConnection.getSeparateGodaddyConnection();
		for(int i=0;i<studentRegistrationForm.getPhotoUpload().size();i++){
		pstmt = conn.prepareStatement("SELECT student_id_int FROM `campus_student` WHERE student_admissionno_var=?");
		pstmt.setString(1, studentRegistrationForm.getPhotoAdmissionNoArray().get(i));
		
		rs=pstmt.executeQuery();
		while(rs.next()){
			PreparedStatement update=conn.prepareStatement("UPDATE campus_student_classdetails SET student_imgurl_var=? WHERE student_id_int=?");
			update.setString(1, studentRegistrationForm.getPhotoRealPath().get(i));
			update.setString(2, rs.getString("student_id_int"));
			
			count=update.executeUpdate();
			update.close();
		}
		}
		
		
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getClassesByStream : Ending");
	
	return count;
}

public List<StudentRegistrationVo> getAllStudentPromotionClassSearchDetails(String academic_year, String location,String classId, String sectionid,String searchVal) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	int count=0;
	try {
		conn = JDBCConnection.getSeparateGodaddyConnection();
		pst = conn.prepareStatement("SELECT distinct stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,csc.student_rollno,csc.`student_promotionstatus`,cls.classdetails_name_var,cls.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,csc.`specilization`,spec.`Specialization_name`,acy.acadamic_id,acy.acadamic_year,csc.`locationId` FROM campus_student stu JOIN campus_student_classdetails csc ON stu.`student_id_int`=csc.`student_id_int` JOIN campus_classdetail cls ON csc.`classdetail_id_int`=cls.`classdetail_id_int` JOIN campus_classsection sec ON csc.`classsection_id_int`=sec.`classsection_id_int` LEFT JOIN campus_class_specialization spec ON csc.`specilization`=spec.`Specialization_Id` join campus_acadamicyear acy on csc.fms_acadamicyear_id_int=acy.acadamic_id WHERE csc.`fms_acadamicyear_id_int`=? and csc.`locationId` LIKE ? and csc.classdetail_id_int like ? and csc.classsection_id_int like ? and csc.student_promotionstatus='NOTPROMOTED' AND student_status='STUDYING' AND (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ?  OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR cls.classdetails_name_var LIKE ? OR CONCAT(cls.classdetails_name_var,'-',sec.classsection_name_var) LIKE ? or stu.student_admissionno_var LIKE ?) order by studentName");
		pst.setString(1, academic_year);
		pst.setString(2, "%"+location.trim()+"%");
		pst.setString(3, classId.trim());
		pst.setString(4, sectionid.trim());
		pst.setString(5, searchVal.trim()+"%");
		pst.setString(6, searchVal.trim()+"%");
		pst.setString(7, searchVal.trim()+"%");
		pst.setString(8, searchVal.trim()+"%");
		pst.setString(9, searchVal.trim()+"%");
		pst.setString(10, searchVal.trim()+"%");
		System.out.println("student class "+pst);
		rs = pst.executeQuery();
		while (rs.next()) {
			count++;
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			registrationVo.setCount(count);
			registrationVo.setStudentId(rs.getString("student_id_int"));
			registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			if(rs.getString("student_rollno") != null){
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
			}else{
				registrationVo.setStudentrollno("");
			}
			registrationVo.setStudentFullName(rs.getString("studentName"));
			registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
			registrationVo.setClassname(rs.getString("classdetails_name_var"));
			registrationVo.setClasscode(rs.getString("classdetail_id_int"));
			registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
			registrationVo.setSectioncode(rs.getString("classsection_id_int"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			if(rs.getString("Specialization_name") != null){
				registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
			}else{
				registrationVo.setSpecilizationname("-");
			}
			registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
			String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"));
			registrationVo.setAcademicYearId(accyearid);
			registrationVo.setAcademicYear(rs.getString("acadamic_year"));
			registrationVo.setLocationId(rs.getString("locationId"));
			
			list.add(registrationVo);
		}

	} catch (Exception e) {
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
			+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");
	

	return list;
}

public String WithHeldActivationDeactivation(String studentId, String accyear, String status) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : checkApplicationNo Starting");
	PreparedStatement preparedStatement = null;
	String successMessage = null;
	int count = 0;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateGodaddyConnection();
		preparedStatement = conn
				.prepareStatement("UPDATE campus_student_classdetails SET student_status=? WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
		
		preparedStatement.setString(1, status);
		preparedStatement.setString(2, studentId);
		preparedStatement.setString(3, accyear);
		count = preparedStatement.executeUpdate();
			if(count>0) {
				successMessage="success";
			}
			else {
				successMessage="failure";
				
			}
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			
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
			+ " Control in  StudentRegistrationDaoImpl : checkApplicationNo Ending");
	return successMessage;
}
/*public String NewGenerateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result,String appdate,String ladate,String csub,String esub, String admclass){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Starting");
	StudentRegistrationForm registration = new StudentRegistrationForm();
	PreparedStatement pstmt = null;
	Connection con = null;
	int status = 0;
	String smsg = null;
	System.out.println("result  ==  " + result);
	try {
		System.out.println("Inside the saveStudentPromotion DaoImpl");
		con = JDBCConnection.getSeparateGodaddyConnection();
	int tc_no=0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		
		for (int i = 0; i < splitstudentid.length; i++) {
			con.setAutoCommit(false);
			String idgen=IDGenerator.getPrimaryKeyID("campus_tc_details",con);
			
			PreparedStatement getTcCount=con.prepareStatement("SELECT COUNT(*) FROM campus_tc_details WHERE acadamic_year=?");
			getTcCount.setString(1,splitaccyear[i]);
			
			ResultSet gerRSCount=getTcCount.executeQuery();
			while(gerRSCount.next()) {
				tc_no=gerRSCount.getInt(1)+1;
			}
			pstmt = con.prepareStatement("INSERT INTO campus_tc_details(`TCCode`,`locationId`,`acadamic_year`,`student_id_int`,`admissionNo`,`latestClassID`,`issueDate`,`issuedBy`,`school_or_board_Examination`,`result`,`reason`,`remarks`,`createdate`,`createdby`,appdate,ladate,csub,esub,TC_NO,admission_class,promotion_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, idgen);
			pstmt.setString(2, splitlocation[i]);
			pstmt.setString(3, splitaccyear[i]);
			pstmt.setString(4, splitstudentid[i]);
			pstmt.setString(5, splitadmid[i]);
			pstmt.setString(6, splitclassid[i]);
			
			pstmt.setTimestamp(7, createdDate);
			pstmt.setString(8, registration.getCreateUser());
			
			pstmt.setString(9, examdetails);
			pstmt.setString(10, result);
			pstmt.setString(11, reason);
			pstmt.setString(12, remarks);
			
			pstmt.setTimestamp(13, createdDate);
			pstmt.setString(14, registration.getCreateUser());
			pstmt.setString(15, HelperClass.convertUIToDatabase(appdate));
			pstmt.setString(16, HelperClass.convertUIToDatabase(ladate));
			pstmt.setString(17, csub);
			pstmt.setString(18, esub);
			pstmt.setInt(19, tc_no);
			pstmt.setString(20, admclass);
			//pstmt.setString(21, HelperClass.convertUIToDatabase(prodt));
			status = pstmt.executeUpdate();	
			
			System.out.println("status "+pstmt);
			if(status>0){
				
				PreparedStatement classDetaiUpdate=con.prepareStatement("UPDATE campus_student_classdetails SET student_status='TC' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				classDetaiUpdate.setString(1, splitstudentid[i]);
				classDetaiUpdate.setString(2, splitaccyear[i]);
				int classDetaiUpdateInt=classDetaiUpdate.executeUpdate();
				if(classDetaiUpdateInt>0) {
					con.commit();
					smsg="TC Generated Successfully...";
				}
				else{
					con.rollback();
					smsg="TC Generation Failed";
				}
				
			}else{
				con.rollback();
				smsg="TC Generation Failed";
			}
							
		}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}finally{

		try {
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (con != null && (!con.isClosed())) {
				con.close();
			}
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Ending");
	return smsg;
}*/
public String NewGenerateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result,String appdate,String ladate,String csub,String esub, String admclass, String prodt, String stuckofroll, String issueCertificate, String dateOfVatcation, String schoolMeeting, String pupilMeeting, String extra, String noOfFail){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Starting");
	StudentRegistrationForm registration = new StudentRegistrationForm();
	PreparedStatement pstmt = null;
	Connection con = null;
	int status = 0;
	String smsg = null;
	System.out.println("result  ==  " + result);
	try {
		System.out.println("Inside the saveStudentPromotion DaoImpl");
		con = JDBCConnection.getSeparateGodaddyConnection();
	int tc_no=0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		
		for (int i = 0; i < splitstudentid.length; i++) {
			con.setAutoCommit(false);
			String idgen=IDGenerator.getPrimaryKeyID("campus_tc_details",con);
			
			PreparedStatement getTcCount=con.prepareStatement("SELECT COUNT(*) FROM campus_tc_details WHERE acadamic_year=?");
			getTcCount.setString(1,splitaccyear[i]);
			
			ResultSet gerRSCount=getTcCount.executeQuery();
			while(gerRSCount.next()) {
				tc_no=gerRSCount.getInt(1)+1;
			}
			pstmt = con.prepareStatement("INSERT INTO campus_tc_details(`TCCode`,`locationId`,`acadamic_year`,`student_id_int`,`admissionNo`,`latestClassID`,`issueDate`,`issuedBy`,`school_or_board_Examination`,`result`,`reason`,`remarks`,`createdate`,`createdby`,appdate,ladate,csub,esub,TC_NO,admission_class) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, idgen);
			pstmt.setString(2, splitlocation[i]);
			pstmt.setString(3, splitaccyear[i]);
			pstmt.setString(4, splitstudentid[i]);
			pstmt.setString(5, splitadmid[i]);
			pstmt.setString(6, splitclassid[i]);
			
			pstmt.setTimestamp(7, createdDate);
			pstmt.setString(8, registration.getCreateUser());
			
			pstmt.setString(9, examdetails);
			pstmt.setString(10, result);
			pstmt.setString(11, reason);
			pstmt.setString(12, remarks);
			
			pstmt.setTimestamp(13, createdDate);
			pstmt.setString(14, registration.getCreateUser());
			pstmt.setString(15, HelperClass.convertUIToDatabase(appdate));
			pstmt.setString(16, HelperClass.convertUIToDatabase(ladate));
			pstmt.setString(17, csub);
			pstmt.setString(18, esub);
			pstmt.setInt(19, tc_no);
			pstmt.setString(20, admclass);
			status = pstmt.executeUpdate();	
			
			System.out.println("status "+pstmt);
			if(status>0){
				
				PreparedStatement classDetaiUpdate=con.prepareStatement("UPDATE campus_student_classdetails SET student_status='TC' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				classDetaiUpdate.setString(1, splitstudentid[i]);
				classDetaiUpdate.setString(2, splitaccyear[i]);
				int classDetaiUpdateInt=classDetaiUpdate.executeUpdate();
				if(classDetaiUpdateInt>0) {
					con.commit();
					smsg="TC Generated Successfully...";
				}
				else{
					con.rollback();
					smsg="TC Generation Failed";
				}
				
			}else{
				con.rollback();
				smsg="TC Generation Failed";
			}
							
		}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}finally{

		try {
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (con != null && (!con.isClosed())) {
				con.close();
			}
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Ending");
	return smsg;
}

public String NewCancelStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Starting");
	PreparedStatement pstmt = null;
	Connection con = null;
	int status = 0;
	String smsg = null;
	try {
		System.out.println("Inside the saveStudentPromotion DaoImpl");
		con = JDBCConnection.getSeparateGodaddyConnection();
	
		
		for (int i = 0; i < splitstudentid.length; i++) {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("DELETE FROM campus_tc_details WHERE locationId=? AND `acadamic_year`=? AND `student_id_int`=?");
			pstmt.setString(1, splitlocation[i]);
			pstmt.setString(2, splitaccyear[i]);
			pstmt.setString(3, splitstudentid[i]);
			
			status = pstmt.executeUpdate();	
			
			System.out.println("status "+pstmt);
			if(status>0){
				
				PreparedStatement classDetaiUpdate=con.prepareStatement("UPDATE campus_student_classdetails SET student_status='STUDYING' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				classDetaiUpdate.setString(1, splitstudentid[i]);
				classDetaiUpdate.setString(2, splitaccyear[i]);
				int classDetaiUpdateInt=classDetaiUpdate.executeUpdate();
				if(classDetaiUpdateInt>0) {
					con.commit();
					smsg="TC Cancelled Successfully...";
				}
				else{
					con.rollback();
					smsg="TC Cancelled Failed";
				}
				
			}else{
				con.rollback();
				smsg="TC Cancelled Failed";
			}
							
		}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}finally{

		try {
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (con != null && (!con.isClosed())) {
				con.close();
			}
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EmployeeExpenseDetailsDaoImpl : generateStudentTC Ending");
	return smsg;
}
public List<StudentRegistrationVo> getPhotoSheetForSearch(String admission,String accYear) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getPhotoSheetForSearch Starting");

	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	String admissionArray="";
	for(int i=0;i<admission.split(",").length;i++) {
		admissionArray+="'"+admission.split(",")[i]+"',";
	}
	admissionArray = admissionArray.replaceAll(",$", "");
	String acc_year = accYear;
	
try{
		conn = JDBCConnection.getSeparateGodaddyConnection();
		pstmObj = conn.prepareStatement("SELECT DISTINCT csc.`student_imgurl_var`,csc.`student_id_int`,cs.`student_admissionno_var` ,cs.`student_fname_var`,cl.`Location_Name`,cc.`classdetails_name_var`,csec.`classsection_name_var` FROM `campus_student_classdetails` csc LEFT JOIN `campus_student` cs ON csc.`student_id_int` = cs.`student_id_int` LEFT JOIN `campus_location` cl ON csc.locationId = cl.`Location_Id` LEFT JOIN `campus_classdetail` cc ON csc.classdetail_id_int = cc.`classdetail_id_int` LEFT JOIN `campus_classsection` csec ON csc.classsection_id_int = csec.`classsection_id_int` WHERE cs.student_admissionno_var IN("+admissionArray+")   AND csc.fms_acadamicyear_id_int LIKE ? ");
	
		pstmObj.setString(1, acc_year);

		
		rs = pstmObj.executeQuery();

		while(rs.next())
		{
			StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

			studentRegVo.setStudentId(rs.getString("student_id_int"));
			studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			studentRegVo.setStudentFullName(rs.getString("student_fname_var"));
			studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
			
			studentRegVo.setLocation(rs.getString("Location_Name"));
			studentRegVo.setClassname(rs.getString("classdetails_name_var"));
			studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
			
			
			list.add(studentRegVo);
		}
	}catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in StudentRegistrationDaoImpl : getPhotoSheetForSearch Ending");
	
	
	return list;
}

public List<StudentRegistrationVo> rollNoGeneration(String locationid, String accyear, String classname,
		String sectionid, String order1, String order2, String order3) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rst = null;
	Connection conn = null;
	int sno=0;
	
	

	try {
		
				conn = JDBCConnection.getSeparateGodaddyConnection();
				if(order2.trim().equalsIgnoreCase("NO")) {
					pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id  WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY secondlanguage,thirdlanguage,"+order1);
				}
				else if(order3.trim().equalsIgnoreCase("NO")) {
					pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id  WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY secondlanguage,thirdlanguage,"+order1+","+order2);
				}
				 
				else {
					pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id  WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY secondlanguage,thirdlanguage,"+order1+","+order2+","+order3);
				}
				
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();

		System.out.println(pst);
		
		while (rs.next()) {
			sno++;
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			registrationVo.setCount(sno);
			registrationVo.setStudentId(rs.getString("student_id_int"));
			registrationVo.setLocationId(rs.getString("locationId"));
			registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			registrationVo.setStudentnamelabel(rs.getString("studentname"));
			registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			registrationVo.setAcademicYear(rs.getString("acadamic_year"));
			registrationVo.setStudentrollno(rs.getString("student_rollno"));
			registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
			registrationVo.setSectioncode(rs.getString("classsection_id_int"));
			registrationVo.setGender(rs.getString("student_gender_var"));
			registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
			registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
			registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			registrationVo.setClassname(rs.getString("classdetails_name_var"));
			registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
			registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
			registrationVo.setStudentFullName(rs.getString("studentname"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			
			PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
			SecondlanguageName.setString(1, rs.getString("secondlanguage"));
			ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
			if(SecondLangaugeRs.next()){
				registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
			}
			else{
				registrationVo.setSecondLanguageName("-");	
			}
			PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
			thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
			ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
			if(thirdlanguageRs.next()){
				registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
			}	
			else{
				registrationVo.setThirdLanguageName("-");	
			}
			if(rs.getString("specilization").equalsIgnoreCase("-"))
			{
				registrationVo.setSpecilizationname("-");
			}
			else
			{
				pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
				pstmt.setString(1, rs.getString("specilization"));
				
				rst = pstmt.executeQuery();
				while(rst.next())
				{
					registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
				}
				
			}
			
			list.add(registrationVo);
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rst != null && (!rst.isClosed())) {
				rst.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentListBySectionForRollNo(String locationid, String accyear, String classname,
		String sectionid) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rst = null;
	Connection conn = null;
	int sno=0;
	

	try {
		
	
			
				conn = JDBCConnection.getSeparateGodaddyConnection();
				pst = conn.prepareStatement("select  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var)end studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu JOIN campus_student_classdetails csc on stu.student_id_int=csc.student_id_int left join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId left join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear acy on csc.fms_acadamicyear_id_int=acy.acadamic_id  where csc.locationId like ? and csc.fms_acadamicyear_id_int LIKE ? and csc.classdetail_id_int LIKE ? and csc.classsection_id_int like ? ORDER BY csc.student_rollno");
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				rs = pst.executeQuery();
		
		
	
		
		while (rs.next()) {
			sno++;
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			registrationVo.setCount(sno);
			registrationVo.setStudentId(rs.getString("student_id_int"));
			registrationVo.setLocationId(rs.getString("locationId"));
			registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
			registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			registrationVo.setStudentnamelabel(rs.getString("studentname"));
			registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
			registrationVo.setAcademicYear(rs.getString("acadamic_year"));
			registrationVo.setStudentrollno(rs.getString("student_rollno"));
			registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
			registrationVo.setSectioncode(rs.getString("classsection_id_int"));
			registrationVo.setGender(rs.getString("student_gender_var"));
			registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
			registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
			registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			registrationVo.setClassname(rs.getString("classdetails_name_var"));
			registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
			registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
			registrationVo.setStudentFullName(rs.getString("studentname"));
			registrationVo.setSpecilization(rs.getString("specilization"));
			
			PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
			SecondlanguageName.setString(1, rs.getString("secondlanguage"));
			ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
			if(SecondLangaugeRs.next()){
				registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
			}
			else{
				registrationVo.setSecondLanguageName("-");	
			}
			PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
			thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
			ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
			if(thirdlanguageRs.next()){
				registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
			}	
			else{
				registrationVo.setThirdLanguageName("-");	
			}
			if(rs.getString("specilization").equalsIgnoreCase("-"))
			{
				registrationVo.setSpecilizationname("-");
			}
			else
			{
				pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
				pstmt.setString(1, rs.getString("specilization"));
				
				rst = pstmt.executeQuery();
				while(rst.next())
				{
					registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
				}
				
			}
			
			list.add(registrationVo);
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rst != null && (!rst.isClosed())) {
				rst.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

	return list;
}

public String deleteTemRecord(String id) {
	

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : deleteTemRecord  Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	Connection conn = null;
	int sno=0;
	String status="";
		try {
			conn = JDBCConnection.getSeparateGodaddyConnection();
			
			pst=conn.prepareStatement("DELETE FROM campus_temporary_admisssion_details WHERE temporary_admission_id=?");
			pst.setString(1, id);
			sno=pst.executeUpdate();
			
			if(sno>0) {
				status="true";
			}
			else {
				status="false";
			}
			
	
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {

					
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
			+ " Control in StudentRegistrationDaoImpl : deleteTemRecord  Ending");
	return status;
}

public List<StudentRegistrationVo> getStudentcontact(String locationId,String accYear,String classId,String divisionId) {
	// TODO Auto-generated method stub
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentcontact Starting");
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	PreparedStatement pstmObj1 = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	Connection conn = null;
	int count = 0;
	
	if(divisionId.length() <= 0) {
		divisionId ="%%";
	}
	
	try
	{
		conn = JDBCConnection.getSeparateGodaddyConnection();
		//pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_CONTACT_HISTORY);
		if(!(divisionId=="all"))
		{
			pstmObj1=conn.prepareStatement("SELECT csc.smsNO,csc.studentId FROM campus_students_contacts csc JOIN campus_student_classdetails cscd ON cscd.student_id_int=csc.`studentId` AND cscd.fms_acadamicyear_id_int='ACY4' WHERE cscd.classdetail_id_int LIKE ?");
			pstmObj1.setString(1,classId);
			System.out.println(pstmObj1);
			rs1 = pstmObj1.executeQuery();
			 while(rs1.next())
				{	
					count++;
					StudentRegistrationVo obj = new StudentRegistrationVo();
					obj.setCount(count);
					obj.setSmsnumber(rs1.getString("smsNO"));
					//obj.setAdmissionno(rs.getString("smsNO"));
					list.add(obj);
					
				}
			
		}
		else
		{
		pstmObj=conn.prepareStatement("SELECT csc.smsNO, csc.studentId FROM campus_students_contacts csc JOIN campus_student_classdetails cscd ON cscd.student_id_int=csc.`studentId` AND cscd.fms_acadamicyear_id_int='ACY4' WHERE cscd.classdetail_id_int LIKE ? AND  cscd.classsection_id_int  LIKE ?");
		//pstmObj.setString(1,locationId);
		//pstmObj.setString(2,accYear);
		pstmObj.setString(1,classId);
		pstmObj.setString(2,divisionId);
		System.out.println(pstmObj);
		
        rs = pstmObj.executeQuery();
        while(rs.next())
		{	
			count++;
			StudentRegistrationVo obj = new StudentRegistrationVo();
			obj.setCount(count);
			obj.setSmsnumber(rs.getString("smsNO"));
			//obj.setAdmissionno(rs.getString("smsNO"));
			list.add(obj);
			
		}
	  }
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
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentcontact Ending");

	return list;
}

public ArrayList<StudentRegistrationVo> getStudentcontactDetails(String accaYear, String locId, String classid,String secId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getStudentcontactDetails Starting");
	//List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	PreparedStatement pstmObj1 = null;
	
	ResultSet rs = null;
	ResultSet rs1 = null;

	Connection conn = null;
	int count = 0;
	
	try
	{
		conn = JDBCConnection.getSeparateGodaddyConnection();
		if(!(secId=="all"))
		{
			pstmObj1=conn.prepareStatement("SELECT cs.student_admissionno_var,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS studentName,ccd.classdetails_name_var,ccs.classsection_name_var,cscs.smsNo FROM campus_students_contacts cscs JOIN campus_student cs ON cs.student_id_int = cscs.`studentId` JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int AND csc.fms_acadamicyear_id_int='ACY4' JOIN campus_classdetail ccd ON ccd.classdetail_id_int = csc.classdetail_id_int AND ccd.locationId=csc.locationId JOIN campus_classsection ccs ON ccs.`classsection_id_int`=csc.`classsection_id_int` WHERE ccd.classdetail_id_int=?");
			pstmObj1.setString(1,classid);
			System.out.println(pstmObj1);
			rs1 = pstmObj1.executeQuery();
			while(rs1.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setAdmissionNo(rs1.getString("student_admissionno_var"));
				obj.setStudentFirstName(rs1.getString("studentName"));
				//obj.setLocation(rs.getString("Location_Name"));
				obj.setClassname(rs1.getString("classdetails_name_var"));
				obj.setSectionnaem(rs1.getString("classsection_name_var"));
				obj.setSmsnumber(rs1.getString("smsNO"));
				//obj.setAdmissionno(rs.getString("smsNO"));
				list.add(obj);
				
			}
		}
		else 
		{
			pstmObj=conn.prepareStatement("SELECT cs.student_admissionno_var,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS studentName,ccd.classdetails_name_var,ccs.classsection_name_var,cscs.smsNo FROM campus_students_contacts cscs JOIN campus_student cs ON cs.student_id_int = cscs.`studentId` JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int AND csc.fms_acadamicyear_id_int='ACY4' JOIN campus_classdetail ccd ON ccd.classdetail_id_int = csc.classdetail_id_int AND ccd.locationId=csc.locationId JOIN campus_classsection ccs ON ccs.`classsection_id_int`=csc.`classsection_id_int` WHERE ccd.classdetail_id_int=? AND ccs.classsection_id_int=?"); 
		//pstmObj.setString(1,accaYear);
		//pstmObj.setString(2,locId);
		pstmObj.setString(1,classid);
		pstmObj.setString(2,secId);
		System.out.println(pstmObj);
		//System.out.println(accaYear);
		//System.out.println(locId);
		System.out.println(classid);
		System.out.println(secId);
        rs = pstmObj.executeQuery();
        while(rs.next())
		{	
			count++;
			StudentRegistrationVo obj = new StudentRegistrationVo();
			obj.setCount(count);
			obj.setAdmissionNo(rs.getString("student_admissionno_var"));
			obj.setStudentFirstName(rs.getString("studentName"));
			//obj.setLocation(rs.getString("Location_Name"));
			obj.setClassname(rs.getString("classdetails_name_var"));
			obj.setSectionnaem(rs.getString("classsection_name_var"));
			obj.setSmsnumber(rs.getString("smsNO"));
			//obj.setAdmissionno(rs.getString("smsNO"));
			list.add(obj);
			
		}
        
		}		
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
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentcontactDetails Ending");
	return list;
}


public List<StudentRegistrationVo> getdefaStudentcontact(String locationId, String accYear, String classId,
		String divisionId, String termId) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationDaoImpl : getdefaStudentcontactDetails Starting");
	ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	int count = 0;
	if(divisionId.length() <= 0) {
		divisionId ="%%";
	}
	try
	{
		conn = JDBCConnection.getSeparateGodaddyConnection();
		pstmObj=conn.prepareStatement("SELECT CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cs.`student_admissionno_var`,cs.`student_id_int`,l.Location_Name,cc.classdetails_name_var,ccs.classsection_name_var,t.`termname`,t.`termid`,t.enddate,csc.`classdetail_id_int`,csc.fms_acadamicyear_id_int,csc.locationId,csc.specilization,cstc.smsNO FROM `campus_student` cs JOIN `campus_student_classdetails` csc ON cs.student_id_int = csc.student_id_int JOIN campus_classdetail cc ON (csc.`classdetail_id_int`=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN `campus_fee_termdetails` t ON t.`locationId`=cs.`locationId` JOIN campus_students_contacts cstc ON cstc.studentId = cs.student_id_int WHERE cs.`student_id_int`  NOT IN (SELECT `admissionNo` FROM `campus_fee_collection` WHERE `accYear`=? AND `termcode`=?) AND cs.`student_admissionno_var` NOT IN (SELECT admissionNo FROM campus_scholorship WHERE concessionType='full' AND academic_year=?) AND csc.fms_acadamicyear_id_int=? AND cs.`locationId`=? AND csc.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? AND t.`termid`=? AND csc.student_status !='TC' ORDER BY LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,LENGTH(csc.classsection_id_int),csc.classsection_id_int");
		pstmObj.setString(1, accYear);
		pstmObj.setString(2, termId);
		pstmObj.setString(3, accYear);
		pstmObj.setString(4, accYear);
		pstmObj.setString(5, locationId);
		pstmObj.setString(6, classId);
		pstmObj.setString(7, divisionId);
		pstmObj.setString(8, termId);
		rs = pstmObj.executeQuery();
		System.out.println("&&&&&&&&&&&&  **************  ::: -- :::"+pstmObj);
		System.out.println("&&&&&&&&&&&&  **************  ::: -- :::"+rs);
		 while(rs.next())
			{	
			 count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setSmsnumber(rs.getString("smsNO"));
				//obj.setAdmissionno(rs.getString("smsNO"));
				list.add(obj);
				
				
			}
		
		
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
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in StudentRegistrationDaoImpl : getdefaStudentcontact Ending");
	return list;
}

}
