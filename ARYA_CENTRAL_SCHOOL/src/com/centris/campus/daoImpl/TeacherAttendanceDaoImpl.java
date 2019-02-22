package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TeacherAttendanceDao;
import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StaffAttendancePostingSqlUtil;
import com.centris.campus.vo.PostAttendanceVO;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;
import com.centris.campus.vo.TeacherManualAttendanceVO;

public class TeacherAttendanceDaoImpl implements TeacherAttendanceDao {
	private static final Logger logger = Logger
			.getLogger(TeacherAttendanceDaoImpl.class);
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs = null;
	java.util.Date today = new java.util.Date();
	java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());

	@Override
	public boolean insertTeacherAttendanceDao(
			TeacherAttendanceForm attendanceForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl : insertTeacherAttendanceDao Starting");
		// TODO Auto-generated method stub
		boolean rowInsert = false;
		String teacherIdArr[] = attendanceForm.getTeacherIdArr();
		String date = attendanceForm.getDate();
		String statusArr[] = attendanceForm.getStatusArr();
		String createUser = attendanceForm.getCreatedBy();
		String teacherId = null;
		String status = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i < teacherIdArr.length && i < statusArr.length; i++) {
				teacherId = teacherIdArr[i];
				status = statusArr[i];
				PreparedStatement CheckTeacherAttendence = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.CHECK_TEACHER_ATTENDANCE);
				CheckTeacherAttendence.setString(1, date.trim());
				CheckTeacherAttendence.setString(2, teacherId.trim());
				ResultSet rs = CheckTeacherAttendence.executeQuery();

				int count = 0;
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count != 0) {

					pstmt1 = conn
							.prepareStatement(StaffAttendancePostingSqlUtil.UPDATE_TEACHER_ATTENDANCE);
					pstmt1.setString(1, status);
					pstmt1.setTimestamp(2, time_stamp);
					pstmt1.setString(3, createUser);
					pstmt1.setString(4, date);
					pstmt1.setString(5, teacherId);
					rowInsert = pstmt1.execute();

					rowInsert = true;

				} else {

					pstmt1 = conn
							.prepareStatement(StaffAttendancePostingSqlUtil.INSERT_TEACHER_ATTENDANCE);
					pstmt1.setString(1, teacherId);
					pstmt1.setString(2, date);
					pstmt1.setString(3, status);
					pstmt1.setTimestamp(4, time_stamp);
					pstmt1.setString(5, createUser);
					pstmt1.setString(6, "Y");
					rowInsert = pstmt1.execute();

					rowInsert = true;

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
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in TeacherAttendanceDaoImpl : insertTeacherAttendanceDao Ending");
		return rowInsert;
	}

	@Override
	public List<TeacherAttendanceVo> getTeachersAttendanceDetailsDao(
			TeacherAttendanceForm attendanceForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl : getTeachersAttendanceDetailsDao Starting");

		System.out.println(new SimpleDateFormat("yyyy/MM/dd")
				.format(new Date()));
		List<TeacherAttendanceVo> attendanceList = new ArrayList<TeacherAttendanceVo>();

		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.CHECK_TEACHER_ATTENDANCEDATE);
			pstmt.setString(1,
					new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1);
				if (count != 0) {
					PreparedStatement getTeacherDetailsPst = conn
							.prepareStatement(StaffAttendancePostingSqlUtil.GET_TEACHER_ATTENDANCE_DETAILS);
					getTeacherDetailsPst.setString(1, HelperClass
							.convertUIToDatabase(new SimpleDateFormat(
									"yyyy/MM/dd").format(new Date())));

					rs1 = getTeacherDetailsPst.executeQuery();
					while (rs1.next()) {
						TeacherAttendanceVo streamVo = new TeacherAttendanceVo();
						streamVo.setTeacherId(rs1.getString("TeacherID"));
						streamVo.setTeacherName(rs1.getString("FirstName")
								+ " " + rs1.getString("LastName"));
						streamVo.setStatus(rs1.getString("AttendenceStatus"));
						attendanceList.add(streamVo);
					}
				} else {
					PreparedStatement getAllTeacherDetailsPst = conn
							.prepareStatement(StaffAttendancePostingSqlUtil.GET_ALL_TEACHER_ATTENDANCE_DETAILS);
					rs1 = getAllTeacherDetailsPst.executeQuery();
					while (rs1.next()) {
						TeacherAttendanceVo streamVo = new TeacherAttendanceVo();
						streamVo.setTeacherId(rs1.getString("TeacherID"));
						streamVo.setTeacherName(rs1.getString("FirstName")
								+ " " + rs1.getString("LastName"));
						attendanceList.add(streamVo);
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
				+ " Control in  TeacherAttendanceDaoImpl : getTeachersAttendanceDetailsDao Ending");
		System.out
				.println("size::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  "
						+ attendanceList.size());
		return attendanceList;
	}

	public String PostAttendance(PostAttendanceVO postattendancevo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl : PostAttendance Starting");

		Connection conn = null;

		PreparedStatement pstgetattndanceDates = null;
		PreparedStatement postattendance = null;
		ResultSet rsgetattendanceDates = null;
		PreparedStatement getcreatedby = null;
		ResultSet rscreatedby = null;
		String createdBy = null;
		String result = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			List<String> getAttendanceDates = new ArrayList<String>();
			boolean checkDate = false;
			pstgetattndanceDates = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.GET_ATTENDANCE_DATES);
			rsgetattendanceDates = pstgetattndanceDates.executeQuery();
			while (rsgetattendanceDates.next()) {

				getAttendanceDates.add(rsgetattendanceDates
						.getString("AttendenceDate"));

			}

			getcreatedby = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.GET_CREATEDBY);
			getcreatedby.setString(1, postattendancevo.getCreatedBy());
			rscreatedby = getcreatedby.executeQuery();
			while (rscreatedby.next()) {

				createdBy = rscreatedby.getString("UserName");

			}

			checkDate = getAttendanceDates.contains(HelperClass
					.convertUIToDatabase(postattendancevo.getDate()));

			if (checkDate) {
				result = "Date " + postattendancevo.getDate()
						+ " already Exists";

			}

			else {
				postattendance = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.POST_ATTENDANCE);

				postattendance.setString(1, postattendancevo.getCreatedBy());
				postattendance.setString(2, HelperClass
						.convertUIToDatabase(postattendancevo.getDate()));
				postattendance.setString(3, "present");
				postattendance.setString(4, "N");
				postattendance.setTimestamp(5,
						HelperClass.getCurrentTimestamp());
				postattendance.setTimestamp(6,
						HelperClass.getCurrentTimestamp());
				postattendance.setString(7, createdBy);
				postattendance.setString(8, createdBy);

				int status = postattendance.executeUpdate();
				if (status > 0) {
					result = "success";
				} else {
					result = "failure";
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

				if (pstgetattndanceDates != null
						&& (!pstgetattndanceDates.isClosed())) {
					pstgetattndanceDates.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherAttendanceDaoImpl : PostAttendance Ending");
		return result;
	}

	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceStatus() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: getTeacherAttendanceStatus Starting");

		PreparedStatement preparedStatement = null;
		ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceList = null;
		ResultSet resultSet = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			getTeacherAttendanceList = new ArrayList<TeacherAttendanceStatusVo>();

			preparedStatement = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.TEACHER_ATTENDANCE_STATUS);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TeacherAttendanceStatusVo teacherAttendanceStatusVo = new TeacherAttendanceStatusVo();
				teacherAttendanceStatusVo.setAttendanceDate(HelperClass
						.convertDatabaseToUI(resultSet
								.getString("AttendenceDate")));
				teacherAttendanceStatusVo.setToatlLeave(resultSet
						.getString("totalleave"));
				teacherAttendanceStatusVo.setTotalAbsent(resultSet
						.getString("totalAbsent"));
				teacherAttendanceStatusVo.setTotalPresent(resultSet
						.getString("totalpresent"));
				teacherAttendanceStatusVo.setTotalHolidays(resultSet
						.getString("totalHoliday"));
				getTeacherAttendanceList.add(teacherAttendanceStatusVo);

			}
			System.out
					.println("TeacherAttendanceList ::::::::::::::::::::::::::::::::::::::  "
							+ getTeacherAttendanceList.size());
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: getTeacherAttendanceStatus Ending");
		return getTeacherAttendanceList;
	}

	@Override
	public ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate(
			String attendanceDate) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: getTeacherAttendanceByDate Starting");

		PreparedStatement preparedStatement = null;
		ArrayList<TeacherAttendanceStatusVo> getTeacherAttendanceByDate = null;
		ResultSet resultSet = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			int count = 1;
			getTeacherAttendanceByDate = new ArrayList<TeacherAttendanceStatusVo>();

			preparedStatement = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.GET_TEACHER_ATTENDANCE_BY_DATE);

			preparedStatement.setString(1,
					HelperClass.convertUIToDatabase(attendanceDate));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TeacherAttendanceStatusVo teacherAttendanceStatusVo = new TeacherAttendanceStatusVo();

				teacherAttendanceStatusVo.setAttendanceDate(HelperClass
						.convertDatabaseToUI(resultSet
								.getString("AttendenceDate")));
				teacherAttendanceStatusVo.setAttendanceStatus(resultSet
						.getString("AttendenceStatus"));
				teacherAttendanceStatusVo.setSno(count);
				teacherAttendanceStatusVo.setTeacherName(resultSet
						.getString("FirstName"));
				teacherAttendanceStatusVo.setTeacherId(resultSet
						.getString("TeacherID"));
				count++;
				getTeacherAttendanceByDate.add(teacherAttendanceStatusVo);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: getTeacherAttendanceByDate Ending");
		return getTeacherAttendanceByDate;
	}

	@Override
	public int approvedByPrincipal(
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVos) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: approvedByPrincipal Starting");
		/*
		 * ArrayList<TeacherAttendanceStatusVo> attendanceStatusVos=new
		 * ArrayList<TeacherAttendanceStatusVo>();
		 */
		PreparedStatement preparedStatement = null;

		int result = 0;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			for (int i = 0; i <= attendanceStatusVos.size(); i++) {

				preparedStatement = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.TEACHER_ATTENDANCE_APPROVED_BY_PRINCIPAL);

				preparedStatement.setString(1, HelperClass
						.convertUIToDatabase(attendanceStatusVos.get(i)
								.getAttendanceDate()));
				preparedStatement.setString(2, attendanceStatusVos.get(i)
						.getAttendanceStatus());
				preparedStatement.setString(3, "Y");
				preparedStatement.setString(4, attendanceStatusVos.get(i)
						.getTeacherId());
				preparedStatement.setString(5, HelperClass
						.convertUIToDatabase(attendanceStatusVos.get(i)
								.getAttendanceDate()));
				result = preparedStatement.executeUpdate();

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {

				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new java.util.Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new java.util.Date())
				+ " Control in TeacherAttendanceDaoImpl: approvedByPrincipal Ending");
		return result;
	}

	public ArrayList<TeacherManualAttendanceVO> GetTeacherAttendance(
			String user, String date) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl: GetTeacherAttendance Starting");
		String date1 = "";

		if (date == null) {
			date1 = String.valueOf(HelperClass.getCurrentSqlDate());
		} else {
			date1 = String.valueOf(HelperClass.convertUIToDatabase(date));
		}

		ArrayList<TeacherManualAttendanceVO> statuslist = new ArrayList<TeacherManualAttendanceVO>();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		int presentcount = 0;
		int absentcount = 0;
		boolean flag = false;
		int sno = 0;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			preparedStatement = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.GET_TEACHERS_NAMES_WITH_STATUS);

			preparedStatement.setString(1, date1);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				sno++;
				flag = true;
				TeacherManualAttendanceVO teachervo = new TeacherManualAttendanceVO();
				teachervo.setSno(String.valueOf(sno));
				teachervo.setTeacherid(resultSet.getString("TeacherID"));
				teachervo.setTeachername(resultSet.getString("TeacherName"));
				teachervo.setStatus(resultSet.getString("AttendenceStatus"));
				if ("Present".equalsIgnoreCase(resultSet
						.getString("AttendenceStatus"))) {
					presentcount++;
				} else {
					absentcount++;
				}
				teachervo.setPresentcount(presentcount);
				teachervo.setAbsentcount(absentcount);
				teachervo.setUpdatedby(user);
				statuslist.add(teachervo);
			}

			if (flag == false) {

				preparedStatement1 = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.GET_TEACHERS_NAMES);

				resultSet1 = preparedStatement1.executeQuery();

				while (resultSet1.next()) {

					sno++;
					TeacherManualAttendanceVO teachervo = new TeacherManualAttendanceVO();
					teachervo.setSno(String.valueOf(sno));
					teachervo.setTeacherid(resultSet1.getString("TeacherID"));
					teachervo.setTeachername(resultSet1
							.getString("TeacherName"));

					teachervo.setStatus("");
					teachervo.setStatus("present");
					teachervo.setUpdatedby(user);
					statuslist.add(teachervo);
				}
			}

		} catch (SQLException exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {

				if (preparedStatement1 != null
						&& (!preparedStatement1.isClosed())) {
					preparedStatement1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl: GetTeacherAttendance Ending");
		return statuslist;
	}

	public String updateTeacherAttendanceDetails(String teacherid[],
			String TeacherStatus[], String date, String updateby) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl: updateTeacherAttendanceDetails Starting");
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		int count = 0;
		String status = "";
		ResultSet rs = null;
		int total = teacherid.length - 1;
		boolean flag = false;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			preparedStatement = conn
					.prepareStatement(StaffAttendancePostingSqlUtil.GET_TEACHERS_BASED_ON_DATE);
			preparedStatement.setString(1,
					HelperClass.convertUIToDatabase(date));
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			if (flag == true) {
				preparedStatement = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.UPDATE_TEACHER_ATTENDANCE_DETAILS);

				for (int i = 0; total >= i; i++) {

					preparedStatement.setString(1, TeacherStatus[i]);
					preparedStatement.setDate(2,
							HelperClass.getCurrentSqlDate());
					preparedStatement.setString(3, updateby);
					preparedStatement.setString(4, teacherid[i]);
					preparedStatement.setString(5,
							HelperClass.convertUIToDatabase(date));
					count = preparedStatement.executeUpdate();

				}
				if (count > 0) {
					status = "Attendance Updated sucessfully";
				} else {
					status = "Attendance Updation Failed";
				}
			} else {
				preparedStatement1 = conn
						.prepareStatement(StaffAttendancePostingSqlUtil.INSERT_TEACHER_ATTENDANCE_DETAILS);
				for (int i = 0; total >= i; i++) {
					preparedStatement1.setString(1, teacherid[i]);
					preparedStatement1.setString(2,
							HelperClass.convertUIToDatabase(date));
					preparedStatement1.setString(3, TeacherStatus[i]);
					preparedStatement1.setDate(4,
							HelperClass.getCurrentSqlDate());
					preparedStatement1.setString(5, updateby);
					count = preparedStatement1.executeUpdate();
				}
				if (count > 0) {
					status = "Attendance Inserted sucessfully";
				} else {
					status = "Attendance Insertion Failed";
				}
			}

		} catch (SQLException exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceDaoImpl: updateTeacherAttendanceDetails Ending");
		return status;
	}
}