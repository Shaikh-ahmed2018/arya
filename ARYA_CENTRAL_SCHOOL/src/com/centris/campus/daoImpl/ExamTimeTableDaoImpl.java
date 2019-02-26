package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ExamTimeTableDao;
import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.util.ExamTimeTableSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.itextpdf.text.log.SysoLogger;

public class ExamTimeTableDaoImpl implements ExamTimeTableDao {
	private static final Logger logger = Logger
			.getLogger(ExamTimeTableDaoImpl.class);

	@Override
	public synchronized List<ExamTimetablePojo> getExamDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :updateStreamCheck Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<ExamTimetablePojo> getExamDetailsList = new ArrayList<ExamTimetablePojo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_ALL_EXAM_DETAILS);

			ResultSet getStreamDetailsRs = pstmt.executeQuery();

			while (getStreamDetailsRs.next()) {
				ExamTimetablePojo masterPojo = new ExamTimetablePojo();
				masterPojo.setExamId(getStreamDetailsRs.getString("examid"));
				masterPojo
						.setExamName(getStreamDetailsRs.getString("examname"));
				masterPojo.setDiscription(getStreamDetailsRs
						.getString("description"));
				masterPojo.setStartDate(getStreamDetailsRs
						.getString("startdate"));
				masterPojo.setEndDate(getStreamDetailsRs.getString("enddate"));
				masterPojo.setClassId(getStreamDetailsRs.getString("classid"));
				masterPojo.setExamDate(HelperClass
						.convertDatabaseToUI(getStreamDetailsRs
								.getString("examdate")));
				masterPojo.setExamStartTime(getStreamDetailsRs
						.getString("examtime"));
				masterPojo.setExamEndTime(getStreamDetailsRs
						.getString("endtime"));
				masterPojo.setSubId(getStreamDetailsRs.getString("subjectid"));
				masterPojo.setClassName(getStreamDetailsRs
						.getString("classdetails_name_var"));

				getExamDetailsList.add(masterPojo);

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
		return getExamDetailsList;

	}

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTabledaoIMPL: getAllSubjects Starting");
		PreparedStatement subject_pstmt = null;
		ResultSet subject_pstmt_rs = null;
		ArrayList<ExamTimetablePojo> subject_list = new ArrayList<ExamTimetablePojo>();

		PreparedStatement pstmt_examtimtable = null;
		ResultSet rs_examtimtable = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			subject_pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_ALL_SUBJECTS);
			subject_pstmt.setString(1, classId);

			subject_pstmt_rs = subject_pstmt.executeQuery();

			while (subject_pstmt_rs.next()) {
				ExamTimetablePojo voobj = new ExamTimetablePojo();

				String subjectId = subject_pstmt_rs.getString("subjectID");
				String subname = subject_pstmt_rs.getString("subjectName");

				if (checkExamDate(classId, subjectId, exam)) {

					pstmt_examtimtable = conn
							.prepareStatement(ExamTimeTableSqlUtils.GET_EXAM_TIME_TABLE);
					// pstmt_examtimtable.setString(1, category);
					pstmt_examtimtable.setString(1, classId);
					pstmt_examtimtable.setString(2, subjectId);
					pstmt_examtimtable.setString(3, exam);

					rs_examtimtable = pstmt_examtimtable.executeQuery();
					while (rs_examtimtable.next()) {
						voobj.setSubId(subjectId);
						voobj.setSubName(subname);
						voobj.setExamDate((HelperClass
								.convertDatabaseToUI(rs_examtimtable
										.getString("examdate"))));
						voobj.setExamStartTime(rs_examtimtable
								.getString("examtime"));
						voobj.setExamEndTime(rs_examtimtable
								.getString("endtime"));
					}

				} else {
					voobj.setSubId(subject_pstmt_rs.getString("subjectID"));
					voobj.setSubName(subject_pstmt_rs.getString("subjectName"));

				}

				subject_list.add(voobj);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_examtimtable != null && !rs_examtimtable.isClosed()) {
					rs_examtimtable.close();
				}
				if (subject_pstmt != null && !subject_pstmt.isClosed()) {
					subject_pstmt.close();
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
				+ " Control in  ExaminationTimeTabledaoIMPL: getAllSubjects Ending");
		return subject_list;
	}

	public ExamTimetablePojo getExamDate(String examId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTabledaoIMPL: getExaminations Starting");
		PreparedStatement examination_pstmt = null;
		ResultSet examination_pstmt_rs = null;
		// ExaminationTimeTableVO exams_list=null;
		ExamTimetablePojo voobj = new ExamTimetablePojo();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			examination_pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_EXAM_DATE);
			examination_pstmt.setString(1, examId);

			examination_pstmt_rs = examination_pstmt.executeQuery();

			while (examination_pstmt_rs.next()) {

				// voobj.setExamId(examination_pstmt_rs.getString("examid"));
				// voobj.setExamName(examination_pstmt_rs.getString("examname"));
				voobj.setStartDate(HelperClass
						.convertDatabaseToUI(examination_pstmt_rs
								.getString("startdate")));
				voobj.setEndDate(HelperClass
						.convertDatabaseToUI(examination_pstmt_rs
								.getString("enddate")));

			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (examination_pstmt_rs != null
						&& !examination_pstmt_rs.isClosed()) {
					examination_pstmt_rs.close();
				}
				if (examination_pstmt != null && !examination_pstmt.isClosed()) {
					examination_pstmt.close();
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
				+ " Control in  ExaminationTimeTabledaoIMPL: getExaminations Ending");
		return voobj;
	}

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTabledaoIMPL: getAllSubjects Starting");
		PreparedStatement subject_pstmt = null;
		ResultSet subject_pstmt_rs = null;
		ArrayList<ExamTimetablePojo> subject_list = new ArrayList<ExamTimetablePojo>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			subject_pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_ALL_SUBJECTS);
			subject_pstmt.setString(1, classid);

			subject_pstmt_rs = subject_pstmt.executeQuery();

			while (subject_pstmt_rs.next()) {
				ExamTimetablePojo voobj = new ExamTimetablePojo();
				voobj.setSubId(subject_pstmt_rs.getString("subjectID"));
				voobj.setSubName(subject_pstmt_rs.getString("subjectName"));
				voobj.setClassId(classid);
				subject_list.add(voobj);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (subject_pstmt_rs != null && !subject_pstmt_rs.isClosed()) {
					subject_pstmt_rs.close();
				}
				if (subject_pstmt != null && !subject_pstmt.isClosed()) {
					subject_pstmt.close();
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
				+ " Control in  ExaminationTimeTabledaoIMPL: getAllSubjects Ending");
		return subject_list;
	}

	public boolean checkExamDate(String classid, String subjectid, String examid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTabledaoIMPL: checkExamDate  Starting");

		int count = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean status = false;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.CHECK_DUPLICATE_EXAM);
			// pstmt.setString(1, streenId);
			pstmt.setString(1, classid);
			pstmt.setString(2, subjectid);
			pstmt.setString(3, examid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt("examdate");
			}

			if (count >= 1) {

				status = true;
			} else {

				status = false;
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
				+ " Control in ExaminationTimeTabledaoIMPL: checkExamDate Ending");
		return status;
	}

	

	public ArrayList<ExamTimetablePojo> getExamdetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamdetails Starting");
		ArrayList<ExamTimetablePojo> examlist = new ArrayList<ExamTimetablePojo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMDETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExamTimetablePojo exampojo = new ExamTimetablePojo();
				exampojo.setExamId(rs.getString("examid"));
				exampojo.setExamName(rs.getString("examname"));
				exampojo.setExamcode(rs.getString("examcode"));
				examlist.add(exampojo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamdetails Ending");
		return examlist;

	}

	public ArrayList<ExamTimetablePojo> getclassdetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getclassdetails Starting");
		ArrayList<ExamTimetablePojo> classlist = new ArrayList<ExamTimetablePojo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(ExamTimeTableSqlUtils.GET_CLASSDETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExamTimetablePojo exampojo = new ExamTimetablePojo();
				exampojo.setClassId(rs.getString("classdetail_id_int"));
				exampojo.setClassName(rs.getString("classdetails_name_var"));
				classlist.add(exampojo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getclassdetails Ending");
		return classlist;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmListYear() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				
				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_ACCYEAR_COUNT);
				pstmt1.setString(1, rs.getString("acadamic_id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				
				examlistyear.add(exampojo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Starting");
		ArrayList<ExaminationDetailsVo> examtimetablelistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startDate")));
				exampojo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("endDate")));

				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_TIMETABLE_ACCYEAR_COUNT);
				pstmt1.setString(1, rs.getString("acadamic_id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}

				examtimetablelistyear.add(exampojo);
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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Ending");
		return examtimetablelistyear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableDaoImpl:getEaxmTimeTableClassList Starting");

		ArrayList<ExaminationDetailsVo> examClassList = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		Connection conn = null;
		int sno = 0;
		int examcount = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_CLASS_DETAILS);
			pstmt.setString(1, accYear);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				ExaminationDetailsVo examvo = new ExaminationDetailsVo();
				sno++;
				examvo.setSno1(sno);
				examvo.setExamcount(examcount);
				examvo.setAccyear(resultSet.getString("acadamic_year"));
				examvo.setAccyearid(resultSet.getString("acadamic_id"));
				examvo.setStartDate(HelperClass.convertDatabaseToUI(resultSet.getString("startDate")));
				examvo.setEndDate(HelperClass.convertDatabaseToUI(resultSet.getString("endDate")));
				examvo.setClassid(resultSet.getString("classdetail_id_int"));
				examvo.setClassname(resultSet.getString("classdetails_name_var"));

				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAM_TIMETABLE_COUNT);
				pstmt1.setString(1, resultSet.getString("classdetail_id_int"));
				pstmt1.setString(2, resultSet.getString("acadamic_id"));

				resultSet1 = pstmt1.executeQuery();

				while (resultSet1.next()) {

					examvo.setExamcount(resultSet1.getInt("examcount"));
					if (resultSet1.getInt("examcount") > 0) {
						examvo.setStatus("Set");
					} else {
						examvo.setStatus("Not Set");
					}
				}

				examClassList.add(examvo);

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
				
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
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
				+ " Control in ExamTimeTableDaoImpl:getEaxmTimeTableClassList Ending");

		return examClassList;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableDaoImpl: getHeading Starting");

		ArrayList<ExaminationDetailsVo> allclassexamlist = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAM_HEADING);
			pstmt.setString(1, exampojo.getClassId());
			pstmt.setString(2, exampojo.getAccyear());
			pstmt.setString(3, exampojo.getClassId());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				count++;
				ExaminationDetailsVo examvo = new ExaminationDetailsVo();
				examvo.setSno1(count);
				examvo.setClassname(resultSet.getString("className"));
				examvo.setAccyear(resultSet.getString("year"));
				examvo.setSpecilazationCount(resultSet.getInt("SpecializationCount"));

				allclassexamlist.add(examvo);
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
				+ " Control in ExamTimeTableDaoImpl: getHeading  Ending");

		return allclassexamlist;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableDaoImpl: getClassExamTimeTableDetails Starting");

		ArrayList<ExaminationDetailsVo> allclassexamlist = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null,pstmt2=null;
		ResultSet resultSet = null,resultSet1 = null,resultSet2 = null;
		int examcount=0,count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_CLASS_SECTION);
			pstmt.setString(1, exampojo.getClassId());
			String sectionid="",sectionname="",classid="",classname="";
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				sectionid=resultSet.getString("classsection_id_int");
				sectionname=resultSet.getString("classsection_name_var");
				classid=resultSet.getString("classdetail_id_int");
				classname=resultSet.getString("classdetails_name_var");
				
				pstmt2 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMINATION_TIMETABLE_COUNT);
				pstmt2.setString(1, exampojo.getAccyearid());
				pstmt2.setString(2, exampojo.getClassId());
				pstmt2.setString(3, sectionid);
				resultSet2 = pstmt2.executeQuery();
				while(resultSet2.next()){
					examcount=resultSet2.getInt("examcount");
				}
				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMINATION_TIMETABLE);
				pstmt1.setString(1, exampojo.getAccyearid());
				resultSet1 = pstmt1.executeQuery();
				while(resultSet1.next()){
					count++;
					ExaminationDetailsVo examvo = new ExaminationDetailsVo();
					examvo.setSno1(count);
					examvo.setSectionid(sectionid);
					examvo.setSection(sectionname);
					examvo.setClassId(classid);
					examvo.setClassname(classname);
					
					examvo.setExamid(resultSet1.getString("examid"));
					examvo.setExamcode(resultSet1.getString("examcode"));
					examvo.setExamName(resultSet1.getString("examname"));
					examvo.setAccyearid(exampojo.getAccyearid());
					examvo.setClassid(exampojo.getClassId());
					examvo.setAccyear(resultSet1.getString("acadamic_year"));
					examvo.setStartDate(resultSet1.getString("startdate"));
					examvo.setEndDate(resultSet1.getString("enddate"));
					
					if (examcount > 0) {
						examvo.setStatus("Set");
					} else {
						examvo.setStatus("Not Set");
					}
					
					allclassexamlist.add(examvo);
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
				+ " Control in ExamTimeTableDaoImpl: getClassExamTimeTableDetails  Ending");

		return allclassexamlist;
	}

//For inserting New Exam
	public String insertExam(ExamDetailsForm addExam) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String msg="";
		String primaryid = null;
		
		try {
			
		    primaryid = IDGenerator.getPrimaryKeyID("campus_examination");
		     
            conn = JDBCConnection.getSeparateConnection();
            pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.INSERT_EXAMS);
            pstmt.setString(1,primaryid);
			pstmt.setString(2,addExam.getExamcode());
			pstmt.setString(3,addExam.getExamname());
	        pstmt.setString(4,HelperClass.convertUIToDatabase(addExam.getStartdate()));
			pstmt.setString(5,HelperClass.convertUIToDatabase(addExam.getEnddate()));
			pstmt.setString(6,addExam.getAccyear());
            pstmt.setString(7,addExam.getUser());
            pstmt.setString(8,addExam.getLocationid());
            pstmt.setString(9,addExam.getFromClassId());
            pstmt.setString(10, addExam.getExamtype());
            pstmt.setString(11, addExam.getIsapplicableperiodic());
			int i =pstmt.executeUpdate();
			if(i>0)
			{
				msg="true";
			}
			else
			{
				msg="not inserted";
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		
		}
		 finally {

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
		
		return msg;
	}

//For Listing the Exams
	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(
			String accyear, String schoolLocation) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			 conn = JDBCConnection.getSeparateConnection();
	         pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GETEXAMSETTINGSDETAILS);
	         pstmt.setString(1, accyear);
	         pstmt.setString(2, schoolLocation);
	         rs= pstmt.executeQuery();
	         while(rs.next()){
	        	 ExaminationDetailsVo obj= new ExaminationDetailsVo();
	        	 sno++;
	        	 obj.setSno(Integer.toString(sno));
	        	 obj.setExamid(rs.getString("examid"));
	        	 obj.setClassid(rs.getString("classid"));
	        	 obj.setClassname(rs.getString("classdetails_name_var"));
	        	 obj.setExamCode(rs.getString("examcode"));
	        	 obj.setExamName(rs.getString("examname"));
	        	 obj.setIsapplicableperiodic(rs.getString("isapplicableperiodic"));
	        	 obj.setExamtypeid(rs.getString("examtypeid"));
	        	 obj.setExamtypename(rs.getString("examtypename"));
	        	 obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
	        	 obj.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
	        	
	        	 list.add(obj);
	        	 
	         }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {

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
	
		return list;
	}
//For Deleting the Exam Settings
	public String deleteexamSettings(String deleteid,String location,String accyear) {
		System.out.println("deleteid from daoimpl:" +deleteid);

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String msg=null;
				int timecount =0 ;
				int i= 0;
				try{
					 conn = JDBCConnection.getSeparateConnection();
					 
					 PreparedStatement timetable = conn.prepareStatement("select count(*) from campus_exam_timetable where examcode = ? and accyear_id = ? and loc_id = ?");
					 timetable.setString(1, deleteid);
					 timetable.setString(2, accyear);
					 timetable.setString(3, location);
					 ResultSet timers = timetable.executeQuery();
					 while(timers.next()){
						 timecount = timers.getInt(1);
					 }
					 timers.close();
					 timetable.close();
					 if(timecount == 0 ){
						 PreparedStatement subjectmarks = conn.prepareStatement("select count(*) from campus_studentwise_mark_details where exam_id = ? and Academic_yearid = ? and location_id = ?");
						 subjectmarks.setString(1, deleteid);
						 subjectmarks.setString(2, accyear);
						 subjectmarks.setString(3, location);
						 ResultSet subrs = subjectmarks.executeQuery();
						 while(subrs.next()){
							 timecount = subrs.getInt(1);
						 }subrs.close();
						 subjectmarks.close();
						 if(timecount == 0){
							 PreparedStatement studentmarks = conn.prepareStatement("select count(*) from campus_subject_marks_wise where ExamId = ? and Accyear_Id = ? and loc_id = ?");
							 studentmarks.setString(1, deleteid);
							 studentmarks.setString(2, accyear);
							 studentmarks.setString(3, location);
							 ResultSet studentrs = studentmarks.executeQuery();
							 while(studentrs.next()){
								 timecount = studentrs.getInt(1);
							 }
							 studentrs.close();
							 studentmarks.close();
							 if(timecount == 0){
								 pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.DELETEEXAMSETTINGDETAILS);
						         pstmt.setString(1, deleteid);
						         pstmt.setString(2, accyear);
						         pstmt.setString(3, location);
						         i =pstmt.executeUpdate();
							 }
						 }
					 }
			        
			         if(i>0)
			         {
			        	msg="true";
			         }
			         else
			         {
			        	msg="notdeleted";
			         }
					
				}catch(Exception e){
					e.printStackTrace();
				}finally {

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
			
				return msg;
			
		
		
	}
	//For Edit the Exam Details
	public String editexamsettings(String editid,ExamDetailsForm editExam) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String msg=null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.EDITEXAMSETTINGDETAILS);

			pstmt.setString(1,editExam.getExamcode());
			pstmt.setString(2,editExam.getExamname());
			pstmt.setString(3,HelperClass.convertUIToDatabase(editExam.getStartdate()));
			pstmt.setString(4,HelperClass.convertUIToDatabase(editExam.getEnddate()));
			pstmt.setString(5,editExam.getFromClassId());
			pstmt.setString(6,editExam.getExamtype());
			pstmt.setString(7,editExam.getIsapplicableperiodic());
			pstmt.setString(8,editExam.getUserid());
			pstmt.setDate(9,HelperClass.getCurrentSqlDate());
			pstmt.setString(10,editid.trim());
			pstmt.setString(11,editExam.getAccyear());
			pstmt.setString(12,editExam.getLocationid());

			System.out.println("pstmt:" +pstmt);
			int i =pstmt.executeUpdate();
			if(i>0)
			{
				msg="true";
			}
			else
			{
				msg="notedited";
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {

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

		return msg;
}

	@Override
	public String checkduplicateExam(String accyear, String examcode,String location,String classid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) examcode from campus_examination where examcode=? and acadamicyear=? and loc_id=? and classid=?");
			pstmt.setString(1,examcode);
			pstmt.setString(2,accyear);
			pstmt.setString(3,location);
			pstmt.setString(4,classid);
			System.out.println("pstmt:" +pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("examcode");
			}
			if(count > 0){
				msg = "true";
			}else{
				msg = "false";
			}
		}catch(Exception e){
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
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return msg;
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation) {
		
		System.out.println("location from daoimpl:" +schoolLocation);
		ArrayList<ExaminationDetailsVo> examliststudent = new ArrayList<ExaminationDetailsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1=null;
		ResultSet rs = null,rs1 = null;
		
		int clascount = 0;
		int examcount =0;
		int classexamcount =0;
		int count =0;
		try { 
		    conn = JDBCConnection.getSeparateConnection();
		    //pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR_FOR_MARKS);
            pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GETEXAMSETTINGSDETAILS);
			pstmt.setString(1, accyear);
			pstmt.setString(2, schoolLocation);
			rs = pstmt.executeQuery();
			System.out.println("pstmt:" +pstmt);
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				exampojo.setClassid(rs.getString("classid"));
				exampojo.setClassname(rs.getString("classdetails_name_var"));
				exampojo.setExamid(rs.getString("examid"));
				exampojo.setExamCode(rs.getString("examcode"));
				exampojo.setExamName(rs.getString("examname"));
				exampojo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				exampojo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				
				pstmt1 = conn.prepareStatement("select count(*) as classcount from campus_classdetail where locationId=? ");
				pstmt1.setString(1,schoolLocation );
				System.out.println("pstmt1 "+ pstmt1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					clascount = rs1.getInt(1);
				}
				System.out.println("clascount "+ clascount);
				PreparedStatement dispstmt = conn.prepareStatement("select distinct classid from campus_studentwise_mark_details where location_id=? and Academic_yearid =?");
				dispstmt.setString(1,schoolLocation);
				dispstmt.setString(2,accyear);
				System.out.println("dispstmt "+ dispstmt);
				ResultSet disrs = dispstmt.executeQuery();
				while(disrs.next()){
						classexamcount++;
						PreparedStatement exampstmt =  conn.prepareStatement("select count(exam_id) from campus_studentwise_mark_details where location_id=? and Academic_yearid =? and classid =?");
						exampstmt.setString(1,schoolLocation);
						exampstmt.setString(2,accyear);
						exampstmt.setString(3,disrs.getString("classid"));
						System.out.println("exampstmt "+ exampstmt);
						ResultSet examrs = exampstmt.executeQuery();
						while(examrs.next()){
							examcount = examrs.getInt(1);
							System.out.println("examcount "+ examcount);
							if(examcount == 0){
								count = 1;
							}
						}
						System.out.println("classexamcount "+ classexamcount);
					}
					
					if(clascount == classexamcount || clascount != classexamcount){
						exampojo.setStatus("Pending");
					}
					else{
						if(count == 1){
							exampojo.setStatus("Pending");
						}else{
							exampojo.setStatus("Completed");
						}
					}
				examliststudent.add(exampojo);
			}
	          }catch (SQLException sqlExp) {
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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getclassdetails Ending");
		return examliststudent;

	}
			
	
	@Override
	public String saveExaminationClassMapping(ArrayList<ExamTimetablePojo> examinationclassmappinglist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTabledaoIMPL: saveExaminationClassMapping Starting");

		int count = 0;
		String status = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			for (int i = 0; i < examinationclassmappinglist.size(); i++) {

				if (checkExamDate(examinationclassmappinglist.get(i)
						.getClassId(), examinationclassmappinglist.get(i)
						.getSubId(), examinationclassmappinglist.get(i)
						.getExamId())) {

					// UPDATE_EXAMDATE

					pstmt = conn
							.prepareStatement(ExamTimeTableSqlUtils.UPDATE_EXAMDATE);
					pstmt.setString(1, examinationclassmappinglist.get(i)
							.getExamDate());
					pstmt.setString(2, examinationclassmappinglist.get(i)
							.getExamStartTime());
					pstmt.setString(3, examinationclassmappinglist.get(i)
							.getExamEndTime());
					pstmt.setString(4, examinationclassmappinglist.get(i)
							.getCreatedBy());

					pstmt.setString(5, examinationclassmappinglist.get(i)
							.getClassId());
					pstmt.setString(6, examinationclassmappinglist.get(i)
							.getSubId());
					pstmt.setString(7, examinationclassmappinglist.get(i)
							.getExamId());

					count = pstmt.executeUpdate();

				} else {

					pstmt = conn
							.prepareStatement(ExamTimeTableSqlUtils.STORE_EXAM_TIMETABLE_DETAILS);

					pstmt.setString(1, examinationclassmappinglist.get(i)
							.getClassId());
					pstmt.setString(2, examinationclassmappinglist.get(i)
							.getSubId());
					pstmt.setString(3, examinationclassmappinglist.get(i)
							.getExamId());
					pstmt.setString(4, examinationclassmappinglist.get(i)
							.getExamDate());
					pstmt.setString(5, examinationclassmappinglist.get(i)
							.getExamStartTime());
					pstmt.setString(6, examinationclassmappinglist.get(i)
							.getExamEndTime());
					pstmt.setString(7, examinationclassmappinglist.get(i)
							.getCreatedBy());

					count = pstmt.executeUpdate();

				}

			}

			if (count > 0) {

				status = "true";
			} else {
				status = "false";
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
				+ " Control in  ExaminationTimeTabledaoIMPL: saveExaminationClassMapping Ending");
		return status;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableDaoImpl:getEaxmTimeTableClassList Starting");

		ArrayList<ExaminationDetailsVo> examClassSubjectList = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet resultSet = null,resultSet1 = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_CLASSSUBJECT_DETAILS);
			pstmt.setString(1, exampojo.getAccyearid());
			pstmt.setString(2, exampojo.getClassId());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				ExaminationDetailsVo examvo = new ExaminationDetailsVo();
				sno++;
				examvo.setSno1(sno);
				examvo.setExamCode(resultSet.getString("examcode"));
				examvo.setExamName(resultSet.getString("examname"));
				examvo.setExamstartdate(HelperClass.convertDatabaseToUI(resultSet.getString("examstartdate")));
				examvo.setExamenddate(HelperClass.convertDatabaseToUI(resultSet.getString("examenddate")));
				examvo.setAccyear(resultSet.getString("acadamic_year"));
				examvo.setAccyearid(resultSet.getString("acadamic_id"));
				examvo.setSubjectId(resultSet.getString("subjectID"));
				examvo.setSubjectName(resultSet.getString("subjectName"));
				examvo.setClassname(resultSet.getString("classdetails_name_var"));
				examvo.setSection(resultSet.getString("classsection_name_var"));
				examClassSubjectList.add(examvo);

			}
			
			pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_CLASSWISEEXAM_TIMETABLE);
			pstmt1.setString(1, exampojo.getAccyearid());
			pstmt1.setString(2, exampojo.getClassId());
			pstmt1.setString(3, exampojo.getExamId());
			resultSet1 = pstmt.executeQuery();

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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
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
				+ " Control in ExamTimeTableDaoImpl:getEaxmTimeTableClassList Ending");

		return examClassSubjectList;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int yearcount=0;
		try {

			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,startDate,endDate,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate,Location_Name");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement("select count(*) as accyearcount from campus_studentwise_mark_details where Academic_yearid=? and location_id=?");
			
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				
				examlistyear.add(exampojo);
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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamdetails(String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamdetails Starting");
		ArrayList<ExamTimetablePojo> examlist = new ArrayList<ExamTimetablePojo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMDETAILS1);
			pstmt.setString(1, locid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExamTimetablePojo exampojo = new ExamTimetablePojo();
				exampojo.setExamId(rs.getString("examid"));
				exampojo.setExamName(rs.getString("examname"));
				exampojo.setExamcode(rs.getString("examcode"));
				examlist.add(exampojo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamdetails Ending");
		return examlist;

	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableDAOIMPL: getExamcodeForDependency Starting");
		ArrayList<ExamTimetablePojo> examlist = new ArrayList<ExamTimetablePojo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			String acc_Year = accYear;
			String start_date = startdate;
			String end_date = enddate;
			String exam_code = examCode;
			

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAM_DEPEND_DETAILS1);
			pstmt.setString(1, acc_Year);
			pstmt.setString(2, locid);
			pstmt.setString(3, exam_code);
			pstmt.setString(4, HelperClass.convertUIToDatabase(start_date));
			pstmt.setString(5, HelperClass.convertUIToDatabase(end_date));
			
			rs = pstmt.executeQuery();
            System.out.println("DEPENDENT EXAM"+pstmt);
			while (rs.next()) {
				ExamTimetablePojo exampojo = new ExamTimetablePojo();
				exampojo.setExamId(rs.getString("examid"));
				exampojo.setExamName(rs.getString("examname"));
				exampojo.setExamcode(rs.getString("examcode"));
				examlist.add(exampojo);
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
				+ " Control in  ExaminationTimeTableDAOIMPL: getExamcodeForDependency Ending");
		return examlist;
	}

	@Override
	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname,String examId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableDAOIMPL: getExamcodeForDependency Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		String result=null;
		boolean validation=false;
		try {
			int a=0;
			 validation=validateDependency(examId,examcode);
			if(validation==true){
				for(int i=0; i<examcode.length; i++){
					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.INS_EXM_DEPENDENCY);
					  pstmt.setString(1,examId);
					  pstmt.setString(2,mainexamname);
					  pstmt.setString(3,examcode[i]);
					  pstmt.setString(4,dependency[i]);
					  a=pstmt.executeUpdate();
					}
					if(a>0){
						result="success";
					}else{
						result="failure";
					}
			}else{
				result="already";
			}
			} catch (Exception sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
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
				+ " Control in  ExaminationTimeTableDAOIMPL: getExamcodeForDependency Ending");
		return result;
	}

	private boolean validateDependency(String examId,String[] examcode) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs=null;
		int count = 0;
		boolean status = true;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0; i<examcode.length; i++){
				pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.VALIDATE_DEPENDENCY);
				pstmt.setString(1, examId);
				pstmt.setString(2, examcode[i]);
				rs=pstmt.executeQuery();
				while(rs.next()){
					count = rs.getInt(1);
				}
			}
			if(count>0)
			{
				status = false;
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try {
					if(rs !=null && !rs.isClosed()){
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
		return status;
		
	}

	@Override
	public String insertGradeDependent(String project, String assignment,String practical, String attendance,String classId,String sectionId,String exam,String location,String accyear) {
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs=null;
			Connection conn = null;
			int count = 0,count1 = 0;
			String status = null;
			try{
				//validating
				conn = JDBCConnection.getSeparateConnection();
				pstmt1=conn.prepareCall(ExamTimeTableSqlUtils.VALIDATE_DEPENDENCY_INSERTION);
				pstmt1.setString(1,location);
				pstmt1.setString(2,classId);
				pstmt1.setString(3,sectionId);
				pstmt1.setString(4,exam);
				pstmt1.setString(5,accyear);
				rs=pstmt1.executeQuery();
				while(rs.next()){
					count1=rs.getInt(1);
				}
				if(count1==0){
					pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.DEPENDENCY_INSERTION);
					pstmt.setString(1,accyear);
					pstmt.setString(2, location);
					pstmt.setString(3, classId);
					pstmt.setString(4, sectionId);
					pstmt.setString(5, exam);
					pstmt.setString(6, project);
					pstmt.setString(7, assignment);
					pstmt.setString(8, practical);
					pstmt.setString(9, attendance);
					count =pstmt.executeUpdate();
					if(count>0){
						status="success";
					}else{
						status="unsuccess";
						}
				}else{
					status="Already Dependency Created For this Exam";
				}
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(rs !=null && !rs.isClosed()){
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
			return status;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String schoolLocation) {
		
		System.out.println("location from daoimpl:" +schoolLocation);
		ArrayList<ExaminationDetailsVo> examliststudent = new ArrayList<ExaminationDetailsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1=null;
		ResultSet rs = null,rs1 = null;
		
		int clascount = 0;
		int count =0;
		try { 
			
			System.out.println("====================GRADE DEPENDENCY SUCCESSFULL ===================");
		    conn = JDBCConnection.getSeparateConnection();
            //pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR_FOR_MARKS);
		    pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GETEXAMSETTINGSDETAILS);
			pstmt.setString(1, accyear);
			pstmt.setString(2, schoolLocation);
			rs = pstmt.executeQuery();
			System.out.println("pstmt:" +pstmt);
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				exampojo.setClassid(rs.getString("classid"));
				exampojo.setClassname(rs.getString("classdetails_name_var"));
				exampojo.setExamid(rs.getString("examid"));
				exampojo.setExamCode(rs.getString("examcode"));
				exampojo.setExamName(rs.getString("examname"));
				exampojo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				exampojo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				
				pstmt1 = conn.prepareStatement("select count(*) as classcount from campus_classdetail where locationId=? ");
				pstmt1.setString(1,schoolLocation );
				System.out.println("pstmt1 "+ pstmt1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					clascount = rs1.getInt(1);
				}
                PreparedStatement shpstmt=conn.prepareStatement("SELECT COUNT(`class_Id`) FROM `campus_grade_dependency` WHERE `acc_year`=? AND `loc_id`=?");
                shpstmt.setString(1, accyear);
                shpstmt.setString(2, schoolLocation);
                ResultSet cs=shpstmt.executeQuery();
                while(cs.next()){
                	count=cs.getInt(1);
                }
				if(clascount!=0&&clascount==count){
					exampojo.setStatus("Compleated");
				}else{
					exampojo.setStatus("Pending");
				}
				examliststudent.add(exampojo);
			}
	          }catch (SQLException sqlExp) {
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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getclassdetails Ending");
		return examliststudent;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int yearcount=0;
		try {

			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,startDate,endDate,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate,Location_Name");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			System.out.println("MAIN"+pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_grade_dependency` WHERE `acc_year`=? AND `loc_id`=?");
			
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt(1);
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				
				examlistyear.add(exampojo);
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
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getToClassDetails(String location, String preClass) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Starting");
		List<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pst = null,pst1 = null;;
		ResultSet rs = null,rs1= null;
		Connection conn = null;
		int count=0;
		String[] strc=preClass.split("");
		String classId=null;

		ArrayList<Integer> cname =new ArrayList<Integer>();

		if(strc.length>4) {
			classId=strc[3]+""+strc[4];
		}else {
			classId=strc[3];
		}
		try {
			conn = JDBCConnection.getSeparateConnection();
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement("SELECT SUBSTR(classdetail_id_int,4) classInt FROM campus_classdetail WHERE `locationId`=?");
			pst.setString(1,location);
			rs = pst.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>=Integer.parseInt(classId)) {
					cname.add(rs.getInt(1));
					count++;
				}
			}
			Collections.sort(cname);
			for(int i=0; i<cname.size(); i++){
				pst1 =conn.prepareStatement("SELECT`classdetails_name_var` FROM `campus_classdetail` WHERE `classdetail_id_int`=?");
				pst1.setString(1, "CCD"+cname.get(i));
				rs1=pst1.executeQuery();
				while(rs1.next()){
					ExaminationDetailsVo vo=new ExaminationDetailsVo();
					vo.setClasscode("CCD"+cname.get(i));
					vo.setClassname(rs1.getString("classdetails_name_var"));
					list.add(vo);
				}

			}

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
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
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

		return (ArrayList<ExaminationDetailsVo>) list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamTypeList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamdetails Starting");
		ArrayList<ExaminationDetailsVo> examlist = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMTYPE_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExaminationDetailsVo vo = new ExaminationDetailsVo();
				//SELECT `examtypeid`,`examtypename` FROM `campus_examtype` 
				vo.setExamtypeid(rs.getString("examtypeid"));
				vo.setExamtypename(rs.getString("examtypename"));
				
				examlist.add(vo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamdetails Ending");
		return examlist;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamdetails Starting");
		ArrayList<ExaminationDetailsVo> examlist = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMNAME_DETAILS);
			pstmt.setString(1, locationid);
			pstmt.setString(2, classid);
			pstmt.setString(3, accyear);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExaminationDetailsVo vo = new ExaminationDetailsVo();
				//ce.`examid`,ce.`examtype`,ce.`examcode`,ce.`examname`,ce.`startdate`,ce.`enddate`,cet.`examtypename`
				vo.setExamid(rs.getString("examid"));
				vo.setExamcode(rs.getString("examcode"));
				vo.setExamName(rs.getString("examname"));
				vo.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				vo.setExamtypeid(rs.getString("examtype"));
				vo.setExamtypename(rs.getString("examtypename"));
				
				examlist.add(vo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamdetails Ending");
		return examlist;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameList1(String locationid, String classid, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamdetails Starting");
		ArrayList<ExaminationDetailsVo> examlist = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMNAME_DETAILS1);
			pstmt.setString(1, locationid);
			pstmt.setString(2, classid);
			pstmt.setString(3, accyear);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExaminationDetailsVo vo = new ExaminationDetailsVo();
				//ce.`examid`,ce.`examtype`,ce.`examcode`,ce.`examname`,ce.`startdate`,ce.`enddate`,cet.`examtypename`
				vo.setExamid(rs.getString("examid"));
				vo.setExamcode(rs.getString("examcode"));
				vo.setExamName(rs.getString("examname"));
				vo.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				vo.setExamtypeid(rs.getString("examtype"));
				vo.setExamtypename(rs.getString("examtypename"));
				
				examlist.add(vo);
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
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamdetails Ending");
		return examlist;

	}
}