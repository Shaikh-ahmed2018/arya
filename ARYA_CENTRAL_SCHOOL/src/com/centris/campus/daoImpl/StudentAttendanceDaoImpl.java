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

import com.centris.campus.dao.StudentAttendanceDao;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentAttendanceSqlUtil;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceDaoImpl implements StudentAttendanceDao{
	
	private static final Logger logger = Logger.getLogger(StudentAttendanceDaoImpl.class);

	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST);

			if(startDate==null || "".equalsIgnoreCase(startDate)){

				ps_insertPlan.setString(1,lastdate);
				ps_insertPlan.setString(2,todayDate);

			}else{

				ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(startDate));
				ps_insertPlan.setString(2,HelperClass.convertUIToDatabase(endDate));

			}

			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));
				attendanceVo.setLocationId(rs.getString("locationId"));

				staffAttendanceList.add(attendanceVo);

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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(
			StudentAttendancePojo studentAttPojo) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails : Starting");
		
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
				if(studentAttPojo.getSpecID() == "" || studentAttPojo.getSpecID() == null ){
					
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_ATTENDANCE_LIST);
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
				
				}
				else{
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SPEC_STUDENT_ATTENDANCE_LIST);	
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
					ps_insertPlan.setString(4,studentAttPojo.getSpecID());
				}
			
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				
				count++;
				
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setStudentid(rs.getString("student_id_int"));
				attendanceVo.setAddmissionNo(rs.getString("student_admissionno_var"));
				attendanceVo.setStudentname(rs.getString("studentname"));
				attendanceVo.setClassname(rs.getString("classdetails_name_var"));
				attendanceVo.setSectionname(rs.getString("classsection_name_var"));
				attendanceVo.setLocationId(rs.getString("locationId"));
				attendanceVo.setStatus(rs.getString("status"));
				attendanceVo.setPeriod1(rs.getString("period1Status"));
				attendanceVo.setPeriod2(rs.getString("period2Status"));
				attendanceVo.setPeriod3(rs.getString("period3Status"));
				attendanceVo.setPeriod4(rs.getString("period4Status"));
				attendanceVo.setPeriod5(rs.getString("period5Status"));
				attendanceVo.setPeriod6(rs.getString("period6Status"));
				attendanceVo.setPeriod7(rs.getString("period7Status"));
				attendanceVo.setPeriod8(rs.getString("period8Status"));
				attendanceVo.setPeriod9(rs.getString("period9Status"));
				staffAttendanceList.add(attendanceVo);
				
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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceDaoImpl: getStaffAttendance : Starting");
			
			PreparedStatement ps_count = null;
			PreparedStatement ps_insert = null;
			
			ResultSet rs=null;
			Connection conn = null;
			int count=0;
			
			int statuscount=0;
			
			String result=null;
			
			try {
				conn = JDBCConnection.getSeparateConnection();
			
				ps_count = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
				
				String teacherId[]=studentAttPojo.getStudentid().split(",");
				String attaStatus[] =studentAttPojo.getStatus().split(",");
				String period1[] = studentAttPojo.getPeriod1().split(",");
				String period2[] = studentAttPojo.getPeriod2().split(",");
				String period3[] = studentAttPojo.getPeriod3().split(",");
				String period4[] = studentAttPojo.getPeriod4().split(",");
				String period5[] = studentAttPojo.getPeriod5().split(",");
				String period6[] = studentAttPojo.getPeriod6().split(",");
				String period7[] = studentAttPojo.getPeriod7().split(",");
				String period8[] = studentAttPojo.getPeriod8().split(",");
				String period9[] = studentAttPojo.getPeriod9().split(",");
				
				for(int i=0;i<teacherId.length;i++){
					
					ps_count.setString(1,teacherId[i]);
					ps_count.setString(2,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					rs=ps_count.executeQuery();
					
					while(rs.next()){
						
						count=rs.getInt(1);
					}
					
					if(count>0){
						
						ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.UPDATE_ATTENDANCE);
						ps_insert.setString(1, attaStatus[i]);
						ps_insert.setString(2, studentAttPojo.getUserId());
						ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
						
						ps_insert.setString(4,period1[i]);
						ps_insert.setString(5,period2[i]);
						ps_insert.setString(6,period3[i]);
						ps_insert.setString(7,period4[i]);
						ps_insert.setString(8,period5[i]);
						ps_insert.setString(9,period6[i]);
						ps_insert.setString(10,period7[i]);
						ps_insert.setString(11,period8[i]);
						ps_insert.setString(12,period9[i]);
						ps_insert.setString(13, teacherId[i]);
						ps_insert.setString(14, HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
						
						statuscount=ps_insert.executeUpdate();
						
					}else{
						
						ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_ATTENDANCE);
						ps_insert.setString(1, teacherId[i]);
						ps_insert.setString(2, HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
						ps_insert.setString(3, attaStatus[i]);
						ps_insert.setString(4, studentAttPojo.getUserId());
						ps_insert.setTimestamp(5, HelperClass.getCurrentTimestamp());
						ps_insert.setString(6,period1[i]);
						ps_insert.setString(7,period2[i]);
						ps_insert.setString(8,period3[i]);
						ps_insert.setString(9,period4[i]);
						ps_insert.setString(10,period5[i]);
						ps_insert.setString(11,period6[i]);
						ps_insert.setString(12,period7[i]);
						ps_insert.setString(13,period8[i]);
						ps_insert.setString(14,period9[i]);
						ps_insert.setString(15,studentAttPojo.getTeacherId());
						ps_insert.setString(16,studentAttPojo.getLocationId());
						statuscount=ps_insert.executeUpdate();
						
					}
				}
				
				if(statuscount>0){
					
					result="true";
				
				}else{
					
					result="false";
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
					if (ps_insert != null&& (!ps_insert.isClosed())) {
						ps_insert.close();
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
					+ " Control in StudentAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return result;
		}

	public List<LstmsStudentPOJO> getAllStudentDao(String classVal, String sectionVal) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Starting");
		
		
		ArrayList<LstmsStudentPOJO> lstmsStudentPOJOList = new ArrayList<LstmsStudentPOJO>();
		LstmsStudentPOJO lstmsStudentPOJO = null;
		ResultSet resultSetClass = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			 pstmt = (PreparedStatement) conn.prepareStatement(SQLUtilConstants.ALLSTUDENTNAME);
				pstmt.setString(1, classVal);
				pstmt.setString(2, sectionVal);
				resultSetClass = pstmt.executeQuery();
				while(resultSetClass.next()){
					
					lstmsStudentPOJO = new LstmsStudentPOJO();
					lstmsStudentPOJO.setStudentIdInt(resultSetClass
							.getString("student_id_int"));

					lstmsStudentPOJO.setStudentFnameVar(resultSetClass
							.getString("studentname"));
					lstmsStudentPOJO.setStudentAdmissionnoVar(resultSetClass
							.getString("student_admissionno_var"));

					
					lstmsStudentPOJOList.add(lstmsStudentPOJO);
					
				}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Ending");
		
		return lstmsStudentPOJOList;
	}
	
	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportDao(
			StudentAttendanceReportVo vo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceReportVo> attendList=new ArrayList<StudentAttendanceReportVo>();
		int count=0;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ATTENDANCE_LIST);
			
			
			pstmt.setString(1, vo.getFromdate());
			pstmt.setString(2, vo.getTodate());
			pstmt.setString(3, vo.getStudentid());
			
		
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				count++;
				StudentAttendanceReportVo attvo = new StudentAttendanceReportVo();
				
				attvo.setCount(count);
				attvo.setStudentname(rs.getString("studentname"));
				attvo.setStreamname(rs.getString("classstream_name_var"));
				attvo.setClassname(rs.getString("classdetails_name_var"));
				attvo.setSectionname(rs.getString("classsection_name_var"));
				
				attvo.setAttdancedate(HelperClass.convertDatabaseToUI(rs.getString("attendencedate")));
				attvo.setAttendancestatus(rs.getString("attendence"));
				attvo.setAccyrname(rs.getString("acadamic_year"));
				
				attendList.add(attvo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Ending");
		
		return attendList;
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportDao(
			StudentAttendanceReportVo stuvo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceReportVo> attendList=new ArrayList<StudentAttendanceReportVo>();
		int count=0;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ATTENDANCE_LIST);
			pstmt.setString(1, stuvo.getFromdate());
			pstmt.setString(2, stuvo.getTodate());
			pstmt.setString(3, stuvo.getStudentid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count++;
				StudentAttendanceReportVo attvo = new StudentAttendanceReportVo();
				
				attvo.setCount(count);
				attvo.setStudentname(rs.getString("studentname"));
				attvo.setStreamname(rs.getString("classstream_name_var"));
				attvo.setClassname(rs.getString("classdetails_name_var"));
				attvo.setSectionname(rs.getString("classsection_name_var"));
				
				attvo.setAttdancedate(HelperClass.convertDatabaseToUI(rs.getString("attendencedate")));
				attvo.setAttendancestatus(rs.getString("attendence"));
				attvo.setAccyrname(rs.getString("acadamic_year"));
				
				attendList.add(attvo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Ending");
		
		return attendList;
	}
	
	public StreamDetailsVO getStreamNameDaoImpl(String stream) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStreamNameDaoImpl Starting");
		
		
		
		StreamDetailsVO selecteditems=new StreamDetailsVO();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		
/*		int count=0; */
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STREAM_NAME);
			
			
			pstmt.setString(1, stream);
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
			
				
				selecteditems.setStreamName(rs.getString("classstream_name_var"));
				selecteditems.setStreamId(rs.getString("classstream_id_int"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStreamNameDaoImpl Ending");
		return selecteditems;
	}

	public ClassPojo getClassNameDaoImpl(String classname) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getClassNameDaoImpl Starting");
		
		
		
		ClassPojo selecteditems=new ClassPojo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		
	/*	int count=0; */
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_NAME);
			
			
			pstmt.setString(1, classname);
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
			
				
				selecteditems.setClassId(rs.getString("classdetail_id_int"));
				selecteditems.setClassName(rs.getString("classdetails_name_var"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getClassNameDaoImpl Ending");
		return selecteditems;
	}
	
	public SectionPojo getSectionNameDaoImpl(String sectionname) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getSectionNameDaoImpl Starting");
		
		
		
		SectionPojo selecteditems=new SectionPojo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		
		/*int count=0; */
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_SECTION_NAME);
			
			
			pstmt.setString(1, sectionname);
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
			
				
				selecteditems.setSectionId(rs.getString("classsection_id_int"));
				selecteditems.setSectionName(rs.getString("classsection_name_var"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getSectionNameDaoImpl Ending");
		
		return selecteditems;
	}
	
	public ParentVO getStudentNameDaoImpl(String student) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentNameDaoImpl Starting");
		
		
		
		ParentVO selecteditems=new ParentVO();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		
		/*int count=0; */
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_NAME);
			
			
			pstmt.setString(1, student);
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
			
				
				selecteditems.setStudentid(rs.getString("student_id_int"));
				selecteditems.setStudentFnameVar(rs.getString("studentname"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentNameDaoImpl Ending");
		
		return selecteditems;
	}

	@Override
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo) {
		
		// TODO Auto-generated method stub
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentPeriodAttendance Starting");
		
		StudentAttendanceVo selecteditems=new StudentAttendanceVo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.GET_STU_PERIOD_ATT);
			
			pstmt.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
			pstmt.setString(2, AttendancePojo.getStudentid());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				selecteditems.setStudentid(rs.getString("StudentId"));
				selecteditems.setDate(rs.getString("AttendanceDate"));
				selecteditems.setPeriod1(rs.getString("Period1"));
				selecteditems.setPeriod2(rs.getString("Period2"));
				selecteditems.setPeriod3(rs.getString("Period3"));
				selecteditems.setPeriod4(rs.getString("Period4"));
				selecteditems.setPeriod5(rs.getString("Period5"));
				selecteditems.setPeriod6(rs.getString("Period6"));
				selecteditems.setPeriod7(rs.getString("Period7"));
				selecteditems.setPeriod8(rs.getString("Period8"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentPeriodAttendance Ending");
		
		return selecteditems;
	
	}

	@Override
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo) {
		
		// TODO Auto-generated method stub
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Starting");
		
		String status=null;

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		PreparedStatement pstmt1 = null;
		
		Connection conn = null;
		
		int count=0;
		int result=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.CHECK_STU_PERIOD_ATT);
			
			pstmt.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
			pstmt.setString(2, AttendancePojo.getStudentid());
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count=rs.getInt(1);
			}
			
			if(count>0){
				
				pstmt1 = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.UPDATE_STU_PERIOD_ATT);
				
				pstmt1.setString(1, AttendancePojo.getPeriod1());
				pstmt1.setString(2, AttendancePojo.getPeriod2());
				pstmt1.setString(3, AttendancePojo.getPeriod3());
				pstmt1.setString(4, AttendancePojo.getPeriod4());
				pstmt1.setString(5, AttendancePojo.getPeriod5());
				pstmt1.setString(6, AttendancePojo.getPeriod6());
				pstmt1.setString(7, AttendancePojo.getPeriod7());
				pstmt1.setString(8, AttendancePojo.getPeriod8());
				pstmt1.setString(9, AttendancePojo.getUserId());
				pstmt1.setTimestamp(10, HelperClass.getCurrentTimestamp());
				pstmt1.setString(11, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
				pstmt1.setString(12, AttendancePojo.getStudentid());
				
				
				
				result=pstmt1.executeUpdate();
				
				
			}else{
				
				pstmt1 = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_STU_PERIOD_ATT);
				pstmt1.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
				pstmt1.setString(2, AttendancePojo.getStudentid());
				pstmt1.setString(3, AttendancePojo.getPeriod1());
				pstmt1.setString(4, AttendancePojo.getPeriod2());
				pstmt1.setString(5, AttendancePojo.getPeriod3());
				pstmt1.setString(6, AttendancePojo.getPeriod4());
				pstmt1.setString(7, AttendancePojo.getPeriod5());
				pstmt1.setString(8, AttendancePojo.getPeriod6());
				pstmt1.setString(9, AttendancePojo.getPeriod7());
				pstmt1.setString(10, AttendancePojo.getPeriod8());
				pstmt1.setString(11, AttendancePojo.getUserId());
				pstmt1.setTimestamp(12, HelperClass.getCurrentTimestamp());
				
				
				
				result=pstmt1.executeUpdate();
			}
			
			if(result>0){
				
				status="true";
			}else{
				
				status="false";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Ending");
		
		return status;
	
	}

	@Override
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Starting");
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentAttendanceSqlUtil.GET_TEACHERBY_CLASS);
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionId);
			rs= pstmt.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setTeacherID(rs.getString("TeacherID"));
				if(rs.getString("teacher").equalsIgnoreCase(""))
					vo.setTeacherName(rs.getString(""));
				else
				vo.setTeacherName(rs.getString("teacher"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getClassSpec Starting");
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select Specialization_Id,Specialization_name from campus_class_specialization cs,campus_classdetail cd where cs.ClassDetails_Id = cd.classdetail_id_int and  cs.ClassDetails_Id = ? ");
			pstmt.setString(1, classId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setSpecId(rs.getString("Specialization_Id"));
				vo.setSpecName(rs.getString("Specialization_name"));
				list.add(vo);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		StudentAttendanceVo  listvo = new StudentAttendanceVo();
		
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			if( pojo.getSpecID().equalsIgnoreCase("-")){
				
				//pstmt=conn.prepareStatement("select distinct ta.attendencedate,ta.locationId,clt.ClassCode,clt.SectionCode,ct.TeacherID,concat(ct.FirstName,' ',ct.LastName) as teachername from campus_attendence ta join campus_classteacher clt on clt.TeacherCode=ta.teacherId and clt.locationId=ta.locationId join campus_teachers ct on ct.TeacherID=clt.TeacherCode where clt.ClassCode=? and clt.SectionCode=? and clt.TeacherCode = ?");
				pstmt=conn.prepareStatement("select classdetails_name_var,cd.classdetail_id_int,classsection_name_var,cs.classsection_id_int,Loc_ID,TeacherID,concat(FirstName,'',LastName) as teacher from campus_teachers,campus_classdetail cd,campus_classsection cs where cd.classdetail_id_int=? and classsection_id_int like ? and TeacherID = ?");
				pstmt.setString(1, pojo.getClassId());
				pstmt.setString(2, pojo.getSectinId());
				pstmt.setString(3, pojo.getTeacherId());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					/*listvo.setClassId(rs.getString("ClassCode"));
					listvo.setSectionId(rs.getString("SectionCode"));
					listvo.setSpecId("-");
					listvo.setSpecName("-");
					listvo.setTeacherName(rs.getString("teachername"));
					listvo.setTeacherID(rs.getString("TeacherID"));*/
					listvo.setClassname(rs.getString("classdetails_name_var"));
					listvo.setClassId(rs.getString("classdetail_id_int"));
					listvo.setSectionname(rs.getString("classsection_name_var"));
					listvo.setSectionId(rs.getString("classsection_id_int"));
					listvo.setSpecId("-");
					listvo.setSpecName("-");
					listvo.setTeacherName(rs.getString("teacher"));
					listvo.setTeacherID(rs.getString("TeacherID"));
					listvo.setLocationId(rs.getString("Loc_ID"));
				}
			
			}
			else{
				pstmt=conn.prepareStatement("select classdetails_name_var,cd.classdetail_id_int,Loc_ID,classsection_name_var,cs.classsection_id_int,Specialization_name,ccs.Specialization_Id,TeacherID,concat(FirstName,'',LastName) as teacher from campus_teachers,campus_classdetail cd,campus_classsection cs,campus_class_specialization ccs where cd.classdetail_id_int=? and classsection_id_int like ? and Specialization_Id = ? and TeacherID = ?");
				pstmt.setString(1, pojo.getClassId());
				pstmt.setString(2, pojo.getSectinId());
				pstmt.setString(3, pojo.getSpecID());
				pstmt.setString(4, pojo.getTeacherId());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					listvo.setClassname(rs.getString("classdetails_name_var"));
					listvo.setClassId(rs.getString("classdetail_id_int"));
					listvo.setSectionname(rs.getString("classsection_name_var"));
					listvo.setSectionId(rs.getString("classsection_id_int"));
					listvo.setSpecId(rs.getString("Specialization_Id"));
					listvo.setSpecName(rs.getString("Specialization_name"));
					listvo.setTeacherName(rs.getString("teacher"));
					listvo.setTeacherID(rs.getString("TeacherID"));
					listvo.setLocationId(rs.getString("Loc_ID"));
			}
			
			
			}	
		}catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return listvo;
	}
	
	public ArrayList<StudentAttendanceVo> getEditStudentAttendanceDetails(
			StudentAttendancePojo studentAttPojo) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");
		
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
				if(studentAttPojo.getSpecID() == "" || studentAttPojo.getSpecID() == null ){
					
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_ATTENDANCE_LIST);
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
				
				}
				else{
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SPEC_STUDENT_ATTENDANCE_LIST);	
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
					ps_insertPlan.setString(4,studentAttPojo.getSpecID());
				}
			
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				
				count++;
				
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setStudentid(rs.getString("student_id_int"));
				attendanceVo.setAddmissionNo(rs.getString("student_admissionno_var"));
				attendanceVo.setStudentname(rs.getString("studentname"));
				attendanceVo.setClassname(rs.getString("classdetails_name_var"));
				attendanceVo.setSectionname(rs.getString("classsection_name_var"));
				attendanceVo.setStatus(rs.getString("status"));
				attendanceVo.setPeriod1(rs.getString("period1Status"));
				attendanceVo.setPeriod2(rs.getString("period2Status"));
				attendanceVo.setPeriod3(rs.getString("period3Status"));
				attendanceVo.setPeriod4(rs.getString("period4Status"));
				attendanceVo.setPeriod5(rs.getString("period5Status"));
				attendanceVo.setPeriod6(rs.getString("period6Status"));
				attendanceVo.setPeriod7(rs.getString("period7Status"));
				attendanceVo.setPeriod8(rs.getString("period8Status"));
				attendanceVo.setPeriod9(rs.getString("period9Status"));
				staffAttendanceList.add(attendanceVo);
				
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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public List<LstmsStudentPOJO> getStudentByTransport(String classId, String sectionId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Starting");
		
		
		ArrayList<LstmsStudentPOJO> lstmsStudentPOJOList = new ArrayList<LstmsStudentPOJO>();
		LstmsStudentPOJO lstmsStudentPOJO = null;
		ResultSet resultSetClass = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			 pstmt = (PreparedStatement) conn
					.prepareStatement(SQLUtilConstants.ALLSTUDENTNAME);
			
			
			
				pstmt.setString(1, classId);
				pstmt.setString(2, sectionId);
				
				resultSetClass = pstmt.executeQuery();
				
				while(resultSetClass.next()){
					
					lstmsStudentPOJO = new LstmsStudentPOJO();
					lstmsStudentPOJO.setStudentIdInt(resultSetClass
							.getString("student_id_int"));

					lstmsStudentPOJO.setStudentFnameVar(resultSetClass
							.getString("studentname"));
					lstmsStudentPOJO.setStudentAdmissionnoVar(resultSetClass
							.getString("student_admissionno_var"));

					
					lstmsStudentPOJOList.add(lstmsStudentPOJO);
					
				}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Ending");
		
		return lstmsStudentPOJOList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null,ps_insertPlan1=null;
		ResultSet rs=null,rs1=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		String StartDate=null,EndDate=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			ps_insertPlan1 = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ACADAMICYEAR_DATE);
			ps_insertPlan1.setString(1,accYear);
			rs1=ps_insertPlan1.executeQuery();
			while(rs1.next()){
				StartDate=rs1.getString("startDate");
				EndDate=rs1.getString("endDate");
			}
			
			//String todayDate=HelperClass.getCurrentSqlDate().toString();
			//String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SEARCH_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,locationId);
			ps_insertPlan.setString(2, StartDate);
			ps_insertPlan.setString(3, EndDate);
			
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

				staffAttendanceList.add(attendanceVo);

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
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_insertPlan1 != null&& (!ps_insertPlan1.isClosed())) {
					ps_insertPlan1.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear,String classname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,locationid);
			ps_insertPlan.setString(2,classname);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

				staffAttendanceList.add(attendanceVo);

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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_SECTION_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,locationid);
			ps_insertPlan.setString(2,classname);
			ps_insertPlan.setString(3,sectionid);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

				staffAttendanceList.add(attendanceVo);

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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getTeacherList(String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getTeacherList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffList=new ArrayList<StudentAttendanceVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();

			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_TEACHER_LIST);
			ps_insertPlan.setString(1,locationid);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				attendanceVo.setTeacherID(rs.getString("TeacherID"));
				attendanceVo.setTeacherName(rs.getString("teachername"));
				staffList.add(attendanceVo);
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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getTeacherList: Ending");

		return staffList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear,String classname, String sectionid, String teacherid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST_BY_TEACHER);
			ps_insertPlan.setString(1,locationid);
			ps_insertPlan.setString(2,classname);
			ps_insertPlan.setString(3,sectionid);
			ps_insertPlan.setString(4, teacherid);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

				staffAttendanceList.add(attendanceVo);

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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByDate : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST_BY_DATE);
			ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(startdate));
			ps_insertPlan.setString(2,HelperClass.convertUIToDatabase(enddate));
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

				staffAttendanceList.add(attendanceVo);

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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByDate: Ending");

		return staffAttendanceList;
	}

	public List<ParentVO> getAllStudentListName(String locationval, String accyearVal, String classVal,
			String sectionVal) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Starting");
		
		
		ArrayList<ParentVO> lstmsStudentParentVO = new ArrayList<ParentVO>();
		ParentVO lstmsStudentVo = null;
		ResultSet resultSetClass = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			 pstmt = (PreparedStatement) conn.prepareStatement("select concat(cs.student_fname_var,' ',cs.student_lname_var) as studentname,csc.student_id_int,cs.student_admissionno_var from campus_student cs join campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int where csc.classdetail_id_int=? and csc.classsection_id_int=? and csc.student_status='STUDYING' AND csc.locationId=? AND csc.fms_acadamicyear_id_int=? order by cs.student_fname_var");
				pstmt.setString(1, classVal);
				pstmt.setString(2, sectionVal);
				pstmt.setString(3, locationval);
				pstmt.setString(4, accyearVal);
				resultSetClass = pstmt.executeQuery();
				while(resultSetClass.next()){
					
					lstmsStudentVo = new ParentVO();
					lstmsStudentVo.setStudentid(resultSetClass.getString("student_id_int"));

					lstmsStudentVo.setStudentFnameVar(resultSetClass
							.getString("studentname"));
					lstmsStudentVo.setStdAdmisiionNo(resultSetClass.getString("student_admissionno_var"));

					
					lstmsStudentParentVO.add(lstmsStudentVo);
					
				}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Ending");
		
		return lstmsStudentParentVO;
	}
}
