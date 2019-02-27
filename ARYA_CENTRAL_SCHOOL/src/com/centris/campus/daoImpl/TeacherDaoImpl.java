package com.centris.campus.daoImpl;

import java.rmi.server.ObjID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.EmailContent;
import com.centris.campus.admin.SendMail;
import com.centris.campus.dao.TeacherDao;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TeacherUtilsConstants;
import com.centris.campus.util.UserRolePermissionSqlConstatnts;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.TeacherMappingClassesVo;
import com.centris.campus.vo.UserRolePermissionVO;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TeacherDaoImpl implements TeacherDao {
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ClientURL = res.getString("ClientURL");
	
	private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class);
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.ALL_TEACHER_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("totalSalary"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				allTeacherDetailsVo.setPannum(rs.getString("pannumber"));
				allTeacherDetailsVo.setBankname(rs.getString("bankname"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
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
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Ending");
		return getTeacherDetails;
	}
	
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.ALL_TEACHER_DETAILS1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("CTC"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				allTeacherDetailsVo.setPannum(rs.getString("pannumber"));
				allTeacherDetailsVo.setBankname(rs.getString("bankname"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
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
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Ending");
		return getTeacherDetails;
	}
	
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String searchterm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSearchTeacherDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.SEARCH_TEACHER_EMPLOYEMENT_DETAILS);
			
			pstmt.setString(1, "%"+searchterm+"%");
			pstmt.setString(2, "%"+searchterm+"%");
			pstmt.setString(3, "%"+searchterm+"%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs
						.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs
						.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("totalSalary"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
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
				+ " Control in TeacherDaoImpl : getSearchTeacherDetails Ending");
		return getTeacherDetails;
	}

	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(
			AllTeacherDetailsVo obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : searchStaffDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.SEARCH_TEACHER_DETAILS);
			pstmt.setString(1, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(2, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(3, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(4, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(5, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(6, "%" + obj.getSearchTerm() + "%");
			pstmt.setString(7, "%" + obj.getSearchTerm() + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs
						.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs
						.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				slNo++;
				getTeacherDetails.add(allTeacherDetailsVo);
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
				+ " Control in TeacherDaoImpl : searchStaffDetails Ending");
		return getTeacherDetails;

	}

	public boolean deleteStaffDetails(String[]  teachercode ) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : deleteStaffDetails Starting");

		Connection conn = null;
		boolean status = false;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			for(int i=0;i<teachercode.length;i++){
				
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.DELETE_TEACHER_DETAILS);
			pstmt.setString(1, teachercode[i]);
			
			System.out.println("Delete Query:::::"+pstmt);

			count = pstmt.executeUpdate();
			if (count > 0) {
				status = true;
			} else {
				status = true;
			}

		} 
		}catch (SQLException e) {
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
				+ " Control in TeacherDaoImpl : deleteStaffDetails Ending");
		return status;
	}

	public List<ViewallSubjectsVo> getSubjects(String classId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSubjects  Starting");
		ResultSet resultset = null;
		ArrayList<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_SUBJECTS);
			pstmt.setString(1, classId);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				ViewallSubjectsVo viewallSubjectsVo = new ViewallSubjectsVo();
				viewallSubjectsVo
						.setSubjectid(resultset.getString("subjectID"));
				viewallSubjectsVo.setSubjectname(resultset
						.getString("subjectName"));
				subjectlist.add(viewallSubjectsVo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : getSubjects  Ending");

		return subjectlist;
	}

	public boolean checkUsername(String username, String teacherId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSubjects  Starting");
		boolean status = false;
		int count = 0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_TEACHER_COUNT);
			pstmt.setString(1, username);
			pstmt.setString(2, teacherId);
			resultset = pstmt.executeQuery();
			System.out.println("prepared statement " + pstmt);

			while (resultset.next()) {
				count = resultset.getInt(1);
			}

			if (count > 0) {
				status = true;

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
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : teacherUpdate  Ending");
		return status;
	}

	public boolean checkMail(String mailid, String teacherId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkUsername  Starting");
		boolean status = false;
		int count = 0;
		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_TEACHERMAIL_COUNT);
			pstmt.setString(1, mailid);
			pstmt.setString(2, teacherId);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				count = resultset.getInt(1);
			}
			if (count > 0) {
				status = true;
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
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : checkUsername  Ending");
		return status;
	}

	public boolean teacherregister(TeacherRegistrationPojo obj) {

		boolean result = false;
		boolean result_class = false;
		boolean result_subject = false;

		ResultSet resultsetcount = null;
		PreparedStatement pstmtcount = null;

		Connection conn = null;

		PreparedStatement pstmt_clssmap_delete = null;

		PreparedStatement  pstmt_classmap= null;

		PreparedStatement pstmt_subjectmap_delete = null;

		PreparedStatement  pstmt_subjectmap= null;

		PreparedStatement  ps_insertuser= null;
		
		PreparedStatement psInsertLeave=null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TeacherDaoImpl : teacherregister  Starting");

			String genPassword = HelperClass.genaratePasswordForTeacher(obj);

			System.out.println("genPassword:::::"+genPassword);

			pstmtcount = conn.prepareStatement(TeacherUtilsConstants.CHECK_TACHER_COUNT_);
			pstmtcount.setString(1, obj.getUsername());
			pstmtcount.setString(2, obj.getDept());
			pstmtcount.setString(3, obj.getDesignation());
			pstmtcount.setString(4, obj.getTeachermobno());
			pstmtcount.setString(5, obj.getDateofbirth());
			pstmtcount.setString(6, obj.getJoiningdate());
			resultsetcount = pstmtcount.executeQuery();

			System.out.println("DIOMPL obj.getDocument1(): "+obj.getDocument1());

			while (resultsetcount.next()) {
				int count = resultsetcount.getInt(1);

				if (count > 0) {
					result = false;
					return result;
				}else {

					pstmt = conn.prepareStatement(TeacherUtilsConstants.INSERT_TEACHER);

					pstmt.setString(1, obj.getTeacherId());
					pstmt.setString(2, obj.getTfastname());
					pstmt.setString(3, obj.getTlastname());
					pstmt.setString(4, obj.getTqualification());
					pstmt.setString(5, obj.getPresentadd());
					pstmt.setString(6, obj.getTeachermobno());
					pstmt.setString(7, obj.getUsername());
					pstmt.setString(8, obj.getTeacheremail());
					pstmt.setString(9, "%%");//We removed Primary subject from front end that is the reason we are storing %%
					pstmt.setString(10, "%%");//We removed Secondary subject that is the reason we are storing %%
					pstmt.setString(11, obj.getProfilepath());
					pstmt.setString(12, obj.getImgpath());
					pstmt.setString(13, obj.getDateofbirth());
					pstmt.setString(14, obj.getJoiningdate());
					pstmt.setString(15, obj.getDesignation());
					pstmt.setString(16, obj.getIdproof());
					pstmt.setString(17, obj.getTeachingtype());
					pstmt.setString(18, obj.getDept());
					pstmt.setString(19, obj.getGender());
					pstmt.setString(20, obj.getBankName());
					pstmt.setString(21, obj.getAccountNumber());
					pstmt.setString(22, obj.getPanNumber());
					pstmt.setString(23, obj.getBlood());
					pstmt.setString(24, obj.getFathername());
					pstmt.setString(25, obj.getMothername());
					pstmt.setString(26, obj.getPermanentadd().trim());
					pstmt.setString(27, obj.getCreatedby());
					pstmt.setTimestamp(28, HelperClass.getCurrentTimestamp());
					pstmt.setString(29, obj.getRegsitrationNo());
					pstmt.setString(30, genPassword);
					pstmt.setString(31, obj.getRole());
					pstmt.setString(32, obj.getDocument1());
					pstmt.setString(33, obj.getDocument2());
					pstmt.setString(34, obj.getDocument3());
					pstmt.setString(35, obj.getDocument4());
					pstmt.setString(36, obj.getDocument5());
					pstmt.setString(37,  obj.getReportingTo());

					if(obj.getIs_student_studying()=="Y"||obj.getIs_student_studying().equalsIgnoreCase("Y"))
					{
						pstmt.setString(38, obj.getIs_student_studying());
						pstmt.setString(39, obj.getStudentName());
						pstmt.setString(40, obj.getStudent_admission_id());
					}else
					{
						pstmt.setString(38, obj.getIs_student_studying());
						pstmt.setString(39, "");
						pstmt.setString(40, "");
					}
					pstmt.setString(41, obj.getFatherMobile());
					pstmt.setString(42, obj.getMotherMobile());
					pstmt.setString(43, obj.getAbbreviate());
					pstmt.setString(44, obj.getAadhaarnumber());
					pstmt.setString(45, obj.getMaritalstatus());
					pstmt.setString(46, obj.getSpousename());
					pstmt.setString(47, obj.getSpouseMobile());
					pstmt.setString(48, obj.getSchoolName());

					System.out.println("INSERT STAFF::::"+pstmt);

					result = pstmt.execute();

					result = true;

					if(result){

						String userId=IDGenerator.getPrimaryKeyID("campus_user"); 
						ps_insertuser=conn.prepareStatement("insert into campus_user(usercode,employeecode,username,password,role,type,createuser,createdate,locationId) values(?,?,?,?,?,?,?,?,?)");
						ps_insertuser.setString(1, userId);
						ps_insertuser.setString(2, obj.getTeacherId());
						ps_insertuser.setString(3,obj.getUsername());
						ps_insertuser.setString(4,genPassword);
						ps_insertuser.setString(5, obj.getRole());
						ps_insertuser.setString(6, obj.getUsertype());
						ps_insertuser.setString(7, obj.getCreatedby());
						ps_insertuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
						ps_insertuser.setString(9, obj.getSchoolName());
						System.out.println("insert user :: "+ps_insertuser);

						ps_insertuser.executeUpdate();


						pstmt_clssmap_delete=conn.prepareStatement(TeacherUtilsConstants.DELETE_CLAA_MAPPINGS);
						pstmt_clssmap_delete.setString(1, obj.getTeacherId());

						System.out.println("delete ::: "+pstmt_clssmap_delete);

						pstmt_clssmap_delete.executeUpdate();

						Map dynformValues =obj.getClassmap();
						int mapcount = dynformValues.size()/2;

						System.out.println("Map Size: " + dynformValues.size());



						for(int i =0; i<mapcount ; i++){

							String classTeacherId=IDGenerator.getPrimaryKeyID("campus_classteacher");

							pstmt_classmap=conn.prepareStatement(TeacherUtilsConstants.INSERT_CLASS_MAPPINGS);
							pstmt_classmap.setString(1, classTeacherId);
							pstmt_classmap.setString(2, (String)dynformValues.get("name"+i));
							pstmt_classmap.setString(3, (String)dynformValues.get("value"+i));
							pstmt_classmap.setString(4, obj.getTeacherId());
							pstmt_classmap.setTimestamp(5, HelperClass.getCurrentTimestamp());
							pstmt_classmap.setString(6, obj.getCreatedby());


							System.out.println("class map :: "+pstmt_classmap);

							pstmt_classmap.executeUpdate();

						}

						result_class=true;

					}

					if(result_class){


						pstmt_subjectmap_delete=conn.prepareStatement(TeacherUtilsConstants.DELETE_SUBJECT_MAPPINGS);
						pstmt_subjectmap_delete.setString(1, obj.getTeacherId());

						System.out.println("delete SUBJECT ::: "+pstmt_clssmap_delete);

						pstmt_subjectmap_delete.executeUpdate();

						Map dynformValues =obj.getSubjectsmap();
						int mapcount = dynformValues.size()/2;

						System.out.println("Map Size: " + dynformValues.size());

						for(int i =0; i<mapcount ; i++){

							pstmt_subjectmap=conn.prepareStatement(TeacherUtilsConstants.INSERT_SUBJECTS_MAPPINGS);
							pstmt_subjectmap.setString(1, obj.getTeacherId());
							pstmt_subjectmap.setString(2, (String)dynformValues.get("name"+i));
							pstmt_subjectmap.setString(3, (String)dynformValues.get("value"+i));
							pstmt_subjectmap.setString(4, obj.getCreatedby());
							pstmt_subjectmap.setTimestamp(5, HelperClass.getCurrentTimestamp());


							System.out.println("subject map :: "+pstmt_subjectmap);

							pstmt_subjectmap.executeUpdate();

						}

						result_subject=true;

					}
					if (result) {
						//String url = getServerUrlFromBD(conn);
						String url=ClientURL;
						// String url="www.google.com";
						String set = sendEmailToEmployee(obj.getUsername(),	obj.getTeacheremail(), genPassword, url);
						result = true;
					}
					 List<String> category = new ArrayList<String>();
					 List<String> NoOfLEaves = new ArrayList<String>();
					PreparedStatement Leavedetails=conn.prepareStatement("SELECT * FROM campus_new_leave_bank WHERE Loc_ID=? AND year=?");
					Leavedetails.setString(1, obj.getSchoolName());
					Leavedetails.setInt(2,HelperClass.getCurrentYear());
					ResultSet leaveRs= Leavedetails.executeQuery();
					while(leaveRs.next()) {
						category.add(leaveRs.getString("Leave_name"));
						NoOfLEaves.add(leaveRs.getString("No_Of_Leaves"));
					}
					 String[] categorynames =  category.toArray((new String[0]));
					 String[] noofleaves = NoOfLEaves.toArray((new String[0]));
					 new LeaveBankDAOIMPL().leaveBankInsert(obj.getTeacherId(), obj.getJoiningdate(), obj.getSchoolName(), obj.getTeachingtype(), HelperClass.getCurrentYearID(), categorynames, noofleaves, conn);
					
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
				if (resultsetcount != null && (!resultsetcount.isClosed())) {
					resultsetcount.close();
				}
				if (pstmtcount != null && (!pstmtcount.isClosed())) {
					pstmtcount.close();
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
				+ " Control in TeacherDaoImpl : teacherregister  Ending");
		return result;
	}

	public String getServerUrlFromBD(Connection connection) {
		String url = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement(TeacherUtilsConstants.GET_SERVER_URL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				url = rs.getString("URL");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection != null && !connection.isClosed()) {

					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		return url;
	}

	public String sendEmailToEmployee(String username, String email,
			String password, String url) {
		try {
			EmailContent em = new EmailContent();
			 String[] mails = { email };

			System.out.println("email id in s send employee " + email);
			em.setMailids(mails);
			em.setUsername(username);
			em.setPassword(password);
			em.setSubject("Staff Registration Confirmation Mail");
			em.setContent("Greetings from E-CAMPUS PRO...  \n"
					+ " Thank you for Registering with us \n"
					+ "Login Credentials are : \n" + "URL		: " + url + "\n"
					+ "User Name		: " + username + "\n" + "Password	: "
					+ password + "\n" + "Have a nice day\n\n\n" + "Regards \n"
					+ "E-CAMPUS PRO \n"
					+ "---------------------------------------------------\n"
					+ "This is System generated mail, Please do not reply."
					+ "\n");
			
			if(email.equalsIgnoreCase("")) {
				
			}
			else {
				new SendMail().sendMail(em);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "sent";
	}

	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getTeacherDetails  Starting");
		boolean status = false;
		int count = 0;
		ResultSet resultset = null;

		Connection conn = null;
		TeacherRegistrationPojo obj1 = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_SINGLE_TEACHER_DEATILS);
			pstmt.setString(1, obj.getTeacherId());
			System.out.println(" pstmt " + pstmt);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				obj1 = new TeacherRegistrationPojo();
				obj1.setTeacherId(obj.getTeacherId());
				obj1.setTfastname(resultset.getString("FirstName"));
				obj1.setTlastname(resultset.getString("LastName"));
				obj1.setRegsitrationNo(resultset.getString("registerId"));
				obj1.setDept(resultset.getString("department"));
				obj1.setDesignation(resultset.getString("designation"));
				obj1.setTqualification(resultset.getString("qualification"));
				obj1.setJoiningdate(HelperClass.convertDatabaseToUI(resultset.getString("dateofJoining")));
				obj1.setTeachingtype(resultset.getString("teachingType"));
				obj1.setPrimary(resultset.getString("primarySubject"));
				obj1.setSecondary(resultset.getString("secondarySubject"));
				obj1.setGender(resultset.getString("gender"));
				obj1.setBankName(resultset.getString("bankname"));
				obj1.setAccountNumber(resultset.getString("accountnumber"));
				obj1.setPanNumber(resultset.getString("pannumber"));
				obj1.setDateofbirth(HelperClass.convertDatabaseToUI(resultset.getString("dateofBirth")));
				obj1.setTeachermobno(resultset.getString("mobileNo"));
				obj1.setTeacheremail(resultset.getString("emailId"));
				obj1.setBlood(resultset.getString("bloodgroup"));
				obj1.setImgpath(resultset.getString("imagePath"));
				obj1.setProfilepath(resultset.getString("profilePath"));
				obj1.setIdproof(resultset.getString("idProofPath"));
				obj1.setFathername(resultset.getString("fathername"));
				obj1.setMothername(resultset.getString("mothername"));
				obj1.setPresentadd(resultset.getString("presentAddress").trim());
				obj1.setPermanentadd(resultset.getString("permanentAddress").trim());
				obj1.setUsername(resultset.getString("username"));
				obj1.setStatus("update");
				obj1.setRole(resultset.getString("role"));
				obj1.setDocument1(resultset.getString("document1"));
				obj1.setDocument2(resultset.getString("document2"));
				obj1.setDocument3(resultset.getString("document3"));
				obj1.setDocument4(resultset.getString("document4"));
				obj1.setDocument5(resultset.getString("document5"));
				obj1.setReportingTo(resultset.getString("reportingTo"));
				obj1.setIs_student_studying(resultset.getString("is_student_studying"));
				obj1.setStudentName(resultset.getString("studentName"));
				obj1.setStudent_admission_id(resultset.getString("student_admission_id"));
				obj1.setFatherMobile(resultset.getString("fatherMobile"));
				obj1.setMotherMobile(resultset.getString("motherMobile"));
				
				obj1.setAbbreviate(resultset.getString("Abbreviative_Id"));
				obj1.setAadhaarnumber(resultset.getString("aadhaarnumber"));
				obj1.setMaritalstatus(resultset.getString("maritalstatus"));
				obj1.setSpouseMobile(resultset.getString("spouseMobile"));
				obj1.setSpousename(resultset.getString("spousename"));
				obj1.setUsertype(resultset.getString("type"));
				obj1.setSchoolName(resultset.getString("Location_Name"));
				obj1.setSchoolNameId(resultset.getString("Loc_ID"));
				

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : getTeacherDetails  Ending");
		return obj1;
	}

	public String teacherUpdate(TeacherRegistrationPojo obj) {

		String result = "";
		ResultSet resultsetcount = null;
		PreparedStatement pstmtcount = null;

		Connection conn = null;
		
		PreparedStatement pstmt_clssmap_delete = null;
		
		PreparedStatement  pstmt_classmap= null;
		
		PreparedStatement pstmt_subjectmap_delete = null;
		
		PreparedStatement  pstmt_subjectmap= null;
		
		PreparedStatement  ps_updateuser= null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TeacherDaoImpl : teacherregister  Starting");

			pstmt = conn.prepareStatement(TeacherUtilsConstants.UPDATE_TEACHER);

			
			pstmt.setString(1,  obj.getTfastname());
			pstmt.setString(2,  obj.getTlastname());
			pstmt.setString(3,  obj.getTqualification());
			pstmt.setString(4,  obj.getPresentadd());
			pstmt.setString(5,  obj.getTeachermobno());
			pstmt.setString(6,  obj.getUsername());
			pstmt.setString(7,  obj.getTeacheremail());
			pstmt.setString(8,  obj.getPrimary());
			pstmt.setString(9,  obj.getSecondary());
			pstmt.setString(10, obj.getProfilepath());
			pstmt.setString(11, obj.getImgpath());
			pstmt.setString(12, HelperClass.convertUIToDatabase(obj.getDateofbirth()));
			pstmt.setString(13, HelperClass.convertUIToDatabase(obj.getJoiningdate()));
			pstmt.setString(14, obj.getDesignation());
			pstmt.setString(15, obj.getIdproof());
			pstmt.setString(16, obj.getTeachingtype());
			pstmt.setString(17, obj.getDept());
			pstmt.setString(18, obj.getGender());
			pstmt.setString(19, obj.getBlood());
			pstmt.setString(20, obj.getFathername());
			pstmt.setString(21, obj.getMothername());
			pstmt.setString(22, obj.getPermanentadd());
			pstmt.setString(23, obj.getCreatedby());
			pstmt.setTimestamp(24, HelperClass.getCurrentTimestamp());
			pstmt.setString(25, obj.getRole());
			pstmt.setString(26, obj.getReportingTo());
			pstmt.setString(27, obj.getBankName());
			pstmt.setString(28, obj.getAccountNumber());
			pstmt.setString(29, obj.getPanNumber());
			pstmt.setString(30, obj.getDocument1());
			pstmt.setString(31, obj.getDocument2());
			pstmt.setString(32, obj.getDocument3());
			pstmt.setString(33, obj.getDocument4());
			pstmt.setString(34, obj.getDocument5());
			
			if(obj.getIs_student_studying()=="Y"||obj.getIs_student_studying().equalsIgnoreCase("Y"))
			{
			pstmt.setString(35, obj.getIs_student_studying());
			pstmt.setString(36, obj.getStudentName());
			pstmt.setString(37, obj.getStudent_admission_id());
			}
			else
			{
				pstmt.setString(35, obj.getIs_student_studying());
				pstmt.setString(36, "");
				pstmt.setString(37, "");
			}
			
			pstmt.setString(38, obj.getFatherMobile());
			pstmt.setString(39, obj.getMotherMobile());
			pstmt.setString(40, obj.getAbbreviate());
			pstmt.setString(41, obj.getAadhaarnumber());
			pstmt.setString(42, obj.getMaritalstatus());
			pstmt.setString(43, obj.getSpousename());
			pstmt.setString(44, obj.getSpouseMobile());
			pstmt.setString(45, obj.getSchoolName());
			pstmt.setString(46, obj.getTeacherId());
			
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println(" update:::::: "+pstmt);
			
			int update=pstmt.executeUpdate();
			
			if(update>0){
				
				System.out.println("obj.getRole() for UPDATE USER DETAILS "+obj.getRole());
				ps_updateuser= conn.prepareStatement(TeacherUtilsConstants.UPDATE_USER_DETAILS);
				ps_updateuser.setString(1, obj.getUsername());
				ps_updateuser.setString(2, obj.getRole());
				ps_updateuser.setString(3, obj.getCreatedby());
				ps_updateuser.setTimestamp(4, HelperClass.getCurrentTimestamp());
				ps_updateuser.setString(5, obj.getTeacherId());
				
				System.out.println("update user :: "+ps_updateuser);
				
				ps_updateuser.executeUpdate();
				
				pstmt_clssmap_delete=conn.prepareStatement(TeacherUtilsConstants.DELETE_CLAA_MAPPINGS);
				pstmt_clssmap_delete.setString(1, obj.getTeacherId());
				
				pstmt_clssmap_delete.executeUpdate();
				
				 Map dynformValues =obj.getClassmap();
			       int mapcount = dynformValues.size()/2;
			         
			        System.out.println("Map Size: " + dynformValues.size());
			        
			        
			        
			        for(int i =0; i<mapcount ; i++){
			        	
			        	String classTeacherId=IDGenerator.getPrimaryKeyID("campus_classteacher");
			        	
			        	pstmt_classmap=conn.prepareStatement(TeacherUtilsConstants.INSERT_CLASS_MAPPINGS);
			        	pstmt_classmap.setString(1, classTeacherId);
			        	pstmt_classmap.setString(2, (String)dynformValues.get("name"+i));
			        	pstmt_classmap.setString(3, (String)dynformValues.get("value"+i));
			        	pstmt_classmap.setString(4, obj.getTeacherId());
			        	pstmt_classmap.setTimestamp(5, HelperClass.getCurrentTimestamp());
			        	pstmt_classmap.setString(6, obj.getCreatedby());
			        	
			        	update=pstmt_classmap.executeUpdate();
			        	
			        }
			        
			}
			
			
			if(update>0){
				
				pstmt_subjectmap_delete=conn.prepareStatement(TeacherUtilsConstants.DELETE_SUBJECT_MAPPINGS);
				pstmt_subjectmap_delete.setString(1, obj.getTeacherId());
				
				pstmt_subjectmap_delete.executeUpdate();
				
				 Map dynformValues =obj.getSubjectsmap();
			       int mapcount = dynformValues.size()/2;
			         
			        System.out.println("Map Size: " + dynformValues.size());
			        
			        
			        
			        for(int i =0; i<mapcount ; i++){
			        	
			        	
			        	pstmt_subjectmap=conn.prepareStatement(TeacherUtilsConstants.INSERT_SUBJECTS_MAPPINGS);
			        	pstmt_subjectmap.setString(1, obj.getTeacherId());
			        	pstmt_subjectmap.setString(2, (String)dynformValues.get("name"+i));
			        	pstmt_subjectmap.setString(3, (String)dynformValues.get("value"+i));
			        	pstmt_subjectmap.setString(4, obj.getCreatedby());
			        	pstmt_subjectmap.setTimestamp(5, HelperClass.getCurrentTimestamp());
			        	
			        	update=pstmt_subjectmap.executeUpdate();
			        	
			        }
			        
			}
			
			
			if(update > 0)
			{
				
				System.out.println("obj.getHpl()=="+obj.getHpl());
				
				if(Double.parseDouble(obj.getHpl()) > 0) {
					PreparedStatement gthpl=conn.prepareStatement("SELECT total_available FROM campus_teacher_new_leave_bank_details where EmpId=? and AccYearCode=currentYear() and Leave_Type=leaveType('HPL')");
					gthpl.setString(1, obj.getTeacherId());
					System.out.println("gthpl=="+gthpl);
					ResultSet hplrs=gthpl.executeQuery();
					if(hplrs.next()) {
						PreparedStatement updhpl=conn.prepareStatement("UPDATE campus_teacher_new_leave_bank_details SET total_available=? where EmpId=? and AccYearCode=currentYear() and Leave_Type=leaveType('HPL')");
						updhpl.setString(1, obj.getHpl());
						updhpl.setString(2, obj.getTeacherId());
						updhpl.executeUpdate();
						System.out.println("jhjhjh=="+updhpl);
					}
					else {
						PreparedStatement updhpl=conn.prepareStatement("INSERT INTO campus_teacher_new_leave_bank_details  (total_available,EmpId,AccYearCode,Leave_Type,January,LOC_id,Date_Of_join,Leave_Name) VALUES(?,?,currentYear(),leaveType('HPL'),?,?,?,'HALF PAID')");
						updhpl.setString(1, obj.getHpl());
						updhpl.setString(2, obj.getTeacherId());
						updhpl.setString(3, obj.getHpl());
						updhpl.setString(4, obj.getSchoolName());
						updhpl.setString(5,HelperClass.convertUIToDatabase(obj.getJoiningdate()));
						System.out.println("jhjhjh=="+updhpl);
						updhpl.executeUpdate();
					}
				}
				
				result="true";
			}
			else{
				result="false";
			}
			
			
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultsetcount != null && (!resultsetcount.isClosed())) {
					resultsetcount.close();
				}
				if (pstmtcount != null && (!pstmtcount.isClosed())) {
					pstmtcount.close();
				}
				if (pstmt_clssmap_delete  != null && (!pstmt_clssmap_delete .isClosed())) {
					pstmt_clssmap_delete .close();
				}
				if (pstmt_classmap != null && (!pstmt_classmap.isClosed())) {
					pstmt_classmap.close();
				}
				if (pstmt_subjectmap_delete  != null && (!pstmt_subjectmap_delete .isClosed())) {
					pstmt_subjectmap_delete .close();
				}
				if (pstmt_subjectmap != null && (!pstmt_subjectmap.isClosed())) {
					pstmt_subjectmap.close();
				}
				if (ps_updateuser != null && (!ps_updateuser.isClosed())) {
					ps_updateuser.close();
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
				+ " Control in TeacherDaoImpl : teacherregister  Ending");
		return result;

	}

	public boolean checkRegistrationNo(String registratn, String teacherId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Starting");
		boolean status = false;
		int count = 0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_REGISTRAION_COUNT);
			pstmt.setString(1, registratn);
			pstmt.setString(2, teacherId);
			resultset = pstmt.executeQuery();
			System.out.println("prepared statement " + pstmt);

			while (resultset.next()) {
				count = resultset.getInt(1);
			}

			if (count > 0) {
				status = true;

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
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Ending");
		return status;
	}

	@Override
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingClasses  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_MAPPING_CLASSES);
			mappingpstmt.setString(1, teacherID);
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				mappingvo.setMappintID(resultset.getString("CTCode"));
				mappingvo.setClassId(resultset.getString("ClassCode"));
				mappingvo.setSectionId(resultset.getString("SectionCode"));
				mappingvo.setTeacherID(teacherID);
				
				mappinglist.add(mappingvo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (mappingpstmt != null && (!mappingpstmt.isClosed())) {
					mappingpstmt.close();
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
				+ " Control in TeacherDaoImpl : getMappingClasses  Ending");
		
		return mappinglist;
	}

	@Override
	public ArrayList<TeacherMappingClassesVo> getMappedSection(
			String teacherID, String classId,String SectionID) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappedSection  Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();
		

		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_UNMAPPING_SECTION);
			pstmt.setString(1, classId);
			pstmt.setString(2, teacherID);
			pstmt.setString(3, classId);
			
			System.out.println("prepared statement " + pstmt);
			
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				
				System.out.println("I m In");
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				
				mappingvo.setSectionId(rs.getString("classsection_id_int"));
				mappingvo.setSectionname(rs.getString("classsection_name_var"));
				
				mappinglist.add(mappingvo);
			}
			
		
			System.out.println(mappinglist.size());

		
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
				+ " Control in TeacherDaoImpl : getMappedSection  Ending");
		
		return mappinglist;
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingSubjects  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_MAPPING_SUBJECTS);
			mappingpstmt.setString(1, teacherID);
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				mappingvo.setClassId(resultset.getString("classID"));
				mappingvo.setSubjectId(resultset.getString("subjectID"));
				mappingvo.setTeacherID(teacherID);
				
				mappinglist.add(mappingvo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (mappingpstmt != null && (!mappingpstmt.isClosed())) {
					mappingpstmt.close();
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
				+ " Control in TeacherDaoImpl : getMappingSubjects  Ending");
		
		return mappinglist;
	}
	
	public ArrayList<AllTeacherDetailsVo> reportingToList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingSubjects  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<AllTeacherDetailsVo> reportingToList=new ArrayList<AllTeacherDetailsVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.REPORTING_TO_LIST);
			
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				AllTeacherDetailsVo teacherVo=new AllTeacherDetailsVo();
				teacherVo.setTeacherId(resultset.getString("TeacherID"));
				teacherVo.setTeacherName(resultset.getString("NAME"));
				System.out.println("DIOMPL:  TeacherID "+resultset.getString("TeacherID"));
				System.out.println("DIOMPL:  TeacherID "+resultset.getString("NAME"));
				reportingToList.add(teacherVo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (mappingpstmt != null && (!mappingpstmt.isClosed())) {
					mappingpstmt.close();
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
				+ " Control in TeacherDaoImpl : getMappingSubjects  Ending");
		
		return reportingToList;
	}

	public ArrayList<AllTeacherDetailsVo> StudentAdmissionNumber(String academicYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingSubjects  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<AllTeacherDetailsVo> reportingToList=new ArrayList<AllTeacherDetailsVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_STUDENT_ADMISSION_DETAILS);
			
			mappingpstmt.setString(1, academicYear);
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				AllTeacherDetailsVo teacherVo=new AllTeacherDetailsVo();
				
				teacherVo.setStudentid(resultset.getString("student_id_int"));
				teacherVo.setAdmission(resultset.getString("student_admissionno_var"));
				reportingToList.add(teacherVo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (mappingpstmt != null && (!mappingpstmt.isClosed())) {
					mappingpstmt.close();
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
				+ " Control in TeacherDaoImpl : getMappingSubjects  Ending");
		
		return reportingToList;
	
		
	}

	public List<TeacherRegistrationPojo> getTeacherDetailsSingle(
			TeacherRegistrationPojo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getTeacherDetails  Starting");
		boolean status = false;
		int count = 0;
		ResultSet resultset = null;

		Connection conn = null;
		List<TeacherRegistrationPojo> objlist=new ArrayList<TeacherRegistrationPojo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_SINGLE_TEACHER_DEATIL);
			pstmt.setString(1, obj.getTeacherId());
			System.out.println(" pstmt " + pstmt);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				TeacherRegistrationPojo obj1 = new TeacherRegistrationPojo();
				obj1.setTeacherId(obj.getTeacherId());
				obj1.setTfastname(resultset.getString("FirstName"));
				obj1.setTlastname(resultset.getString("LastName"));
				obj1.setRegsitrationNo(resultset.getString("registerId"));
				obj1.setDept(resultset.getString("department"));
				obj1.setDesignation(resultset.getString("designation"));
				obj1.setTqualification(resultset.getString("qualification"));
				obj1.setJoiningdate(HelperClass.convertDatabaseToUI(resultset.getString("dateofJoining")));
				obj1.setTeachingtype(resultset.getString("teachingType"));
				obj1.setPrimary(resultset.getString("primarySubject"));
				obj1.setSecondary(resultset.getString("secondarySubject"));
				obj1.setGender(resultset.getString("gender"));
				
				obj1.setBankName(resultset.getString("bankname"));
				obj1.setAccountNumber(resultset.getString("accountnumber"));
				obj1.setPanNumber(resultset.getString("pannumber"));
				
				obj1.setDateofbirth(HelperClass.convertDatabaseToUI(resultset.getString("dateofBirth")));
				obj1.setTeachermobno(resultset.getString("mobileNo"));
				obj1.setTeacheremail(resultset.getString("emailId"));
				obj1.setBlood(resultset.getString("bloodgroup"));
				System.out.println("BooldGroup in Diompl: "+resultset.getString("bloodgroup"));
				
				obj1.setImgpath(resultset.getString("imagePath"));
				System.out.println("DIOMPL image path:"+ resultset.getString("imagePath"));
				
				obj1.setProfilepath(resultset.getString("profilePath"));
				obj1.setIdproof(resultset.getString("idProofPath"));
				obj1.setFathername(resultset.getString("fathername"));
				obj1.setMothername(resultset.getString("mothername"));
				obj1.setPresentadd(resultset.getString("presentAddress").trim());
				obj1.setPermanentadd(resultset.getString("permanentAddress").trim());
				obj1.setUsername(resultset.getString("username"));
				obj1.setStatus("update");
				obj1.setRole(resultset.getString("role"));
				obj1.setDocument1(resultset.getString("document1"));
				
				obj1.setDocument2(resultset.getString("document2"));
				obj1.setDocument3(resultset.getString("document3"));
				obj1.setDocument4(resultset.getString("document4"));
				obj1.setDocument5(resultset.getString("document5"));
				
				obj1.setReportingTo(resultset.getString("reportingTo"));
				

				
				
				obj1.setIs_student_studying(resultset.getString("is_student_studying"));
				obj1.setStudentName(resultset.getString("studentName"));
				obj1.setStudent_admission_id(resultset.getString("student_admission_id"));
				
				objlist.add(obj1);
				System.out.println("DIOMPL get Single Teacher Reporting To: "+resultset.getString("reportingTo"));

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : getTeacherDetails  Ending");
		return objlist;
	}

	public boolean checkAbbreviativeId(String abbreviate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Starting");
		boolean status = false;
		int count = 0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_ABBREVATIVE_COUNT);
			
			pstmt.setString(1, abbreviate);
			
			resultset = pstmt.executeQuery();
			
			System.out.println("abbreviate iD::::::: " + pstmt);

			while (resultset.next())
			
			{
				
				count = resultset.getInt(1);
			
			}

			if (count > 0) {
				status = true;

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
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Ending");
		return status;
	}

	@Override
	public int checkStaffInTDS(String currentUser) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Starting");
		int count=0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_STAFF_COUNT);
			
			pstmt.setString(1, currentUser);
			
			resultset = pstmt.executeQuery();
			
			System.out.println("abbreviate iD::::::: " + pstmt);

			while (resultset.next())
			{
				count = resultset.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
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
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Ending");
		return count;
	}


	public synchronized List<RoleMasterPojo> getRoles()
			throws Exception {
				
		List<RoleMasterPojo> getRoleList=new ArrayList<RoleMasterPojo>();
		
		 PreparedStatement psgetUsers=null;
		 ResultSet rsgetRoles=null;
		 Connection conn=null;
		 try{
				 conn=JDBCConnection.getSeparateConnection();				 
				psgetUsers=conn.prepareStatement(UserRolePermissionSqlConstatnts.GET_ROLES);
		  rsgetRoles=psgetUsers.executeQuery();
		 while(rsgetRoles.next()){
			 RoleMasterPojo masterPojo=new RoleMasterPojo();
			 masterPojo.setRoleCode(rsgetRoles.getString("RoleCode"));
			 masterPojo.setRoleName(rsgetRoles.getString("RoleName"));
			 masterPojo.setRoleDescription(rsgetRoles.getString("Description"));
			 getRoleList.add(masterPojo);
		 }
		 }
			 catch (SQLException e) {
				 logger.error(e);
			        e.printStackTrace();
			      }catch (Exception e) {
			    	  logger.error(e);
						e.printStackTrace();
					}finally {
						try {
							if (rsgetRoles != null && (!rsgetRoles.isClosed())) {

								rsgetRoles.close();
							}
							if (psgetUsers != null && (!psgetUsers.isClosed())) {
								
								psgetUsers.close();
							}
							if (conn != null && (!conn.isClosed())) {
								
								conn.close();
							}
						} catch (SQLException e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} catch (Exception e1) {

							logger.error(e1.getMessage(), e1);
							e1.printStackTrace();
						}
					}
					
			
			
		// TODO Auto-generated method stub
		return getRoleList;
	}
}
