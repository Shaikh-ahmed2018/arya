package com.centris.campus.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffAttendanceDao;
import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StaffAttendanceSqlUtil;
import com.centris.campus.vo.StaffAttendanceVo;


public class StaffAttendanceDaoImpl implements StaffAttendanceDao{
	
	private static final Logger logger = Logger.getLogger(StaffAttendanceDaoImpl.class);

	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance : Starting");
			
			PreparedStatement ps_insertPlan = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
			int count=0;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_insertPlan = conn.prepareStatement(StaffAttendanceSqlUtil.GET_STAFF_ATTENDANCE);
				
				ps_insertPlan.setString(1,date);
				ps_insertPlan.setString(2,department);
				
				System.out.println("get attendance ::: "+ps_insertPlan);
				
				rs=ps_insertPlan.executeQuery();
				
				while(rs.next()){
					
					count++;
					
					StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
					
					attendanceVo.setCount(count);
					attendanceVo.setTeacherId(rs.getString("TeacherID"));
					attendanceVo.setRegid(rs.getString("registerId"));
					attendanceVo.setTeacherName(rs.getString("teacherName"));
					attendanceVo.setDesignation(rs.getString("designationName"));
					attendanceVo.setDepartment(rs.getString("DEPT_NAME"));
					attendanceVo.setStatus(rs.getString("status"));
					
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return staffAttendanceList;
		}


	
	public String updateAttendanceStatus(StaffAttendancePojo attPojo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance : Starting");
			
			PreparedStatement ps_count = null;
			PreparedStatement ps_insert = null;
			PreparedStatement ps_leaveCheck=null;
			PreparedStatement ps_leave=null;
			ResultSet rs_lv_chk=null;
			int upd=0;
			ResultSet rs=null;
			Connection conn = null;
			int count=0;
			
			int statuscount=0;
			
			String result=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
			
				ps_count = conn.prepareStatement(StaffAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
				
				String teacherId[]=attPojo.getTeacherId().split(",");
				String attaStatus[] =attPojo.getStatus().split(",");
				
				for(int i=0;i<teacherId.length;i++){
					
					ps_count.setString(1,teacherId[i]);
					ps_count.setString(2,HelperClass.convertUIToDatabase(attPojo.getDate()));
					
					rs=ps_count.executeQuery();
					
					while(rs.next()){
						
						count=rs.getInt(1);
					}
					
					if(count>0){
						
						ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.UPDATE_ATTENDANCE);
						ps_insert.setString(1, attaStatus[i]);
						ps_insert.setString(2, attPojo.getUserId());
						ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
						ps_insert.setString(4, teacherId[i]);
						ps_insert.setString(5, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						statuscount=ps_insert.executeUpdate();
						
						
					}else{
						
						ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.INSERT_ATTENDANCE);
						ps_insert.setString(1, teacherId[i]);
						ps_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						ps_insert.setString(3, attaStatus[i]);
						ps_insert.setString(4, attPojo.getUserId());
						ps_insert.setTimestamp(5, HelperClass.getCurrentTimestamp());
						
						statuscount=ps_insert.executeUpdate();
						
					}
					
					if(attaStatus[i].equalsIgnoreCase("Present")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
					}
					else if(attaStatus[i].equalsIgnoreCase("Absent")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("LWA")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Half Day")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("CL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							
							
						}
						else {
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("EL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							
						}
						else {
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("HPL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 2.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 2.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 2.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 2.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 2.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 2.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Present/EL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Present/CL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Present/HPL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("EL/Present")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							
								ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
								ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
								ps_leave.executeUpdate();
								
								
								PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
								ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
								ps_lv_update.setString(2, teacherId[i]);
								ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
								ps_lv_update.executeUpdate();
								ps_lv_update.close();
								
								PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
								ps_leave_insert.setDouble(1, 0.5);
								ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
								ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
								ps_leave_insert.setString(4, "FH");
								ps_leave_insert.setString(5, "FH");
								ps_leave_insert.setString(6, "-");
								ps_leave_insert.setString(7, "EL");
								ps_leave_insert.setString(8, teacherId[i]);
								ps_leave_insert.setString(9, "Approved");
								ps_leave_insert.setString(10, "-");
								ps_leave_insert.setDouble(11, 0.5);
								ps_leave_insert.setString(12, "FH");
								ps_leave_insert.setString(13, "FH");
								ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
								ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
								ps_leave_insert.executeUpdate();
								ps_leave_insert.close();
								
								 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
								ps_lv_update.setDouble(1, 0.5);
								ps_lv_update.setString(2, teacherId[i]);
								ps_lv_update.setString(3, "EL");
								ps_lv_update.executeUpdate();
								ps_lv_update.close();
							
							
						}
						else {
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("CL/Present")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("HPL/Present")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Absent/EL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
						
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Absent/CL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Absent/HPL")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "SH");
							ps_leave_insert.setString(5, "SH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "SH");
							ps_leave_insert.setString(13, "SH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("EL/Absent")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "EL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "EL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("CL/Absent")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 0.5);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "CL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 0.5);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 0.5);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "CL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("HPL/Absent")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							 ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
						}
						else {
							
							PreparedStatement ps_leave_insert=conn.prepareStatement("INSERT INTO campus_teachers_leave_request (NoofLeaves,StartDate,EndDate,SessionStart,SessionEnd,RequestedTo,LeaveType,RequestedBy,LeaveStatus,ApprovedBy,TotalDaysAproved,ApproveSessionStart,ApproveSessionEnd,ApprovedStartDate,ApprovedEndDate) VALUES(?,?,?,?,?,?,leaveType(?),?,?,?,?,?,?,?,?)");
							ps_leave_insert.setDouble(1, 1.0);
							ps_leave_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(3, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(4, "FH");
							ps_leave_insert.setString(5, "FH");
							ps_leave_insert.setString(6, "-");
							ps_leave_insert.setString(7, "HPL");
							ps_leave_insert.setString(8, teacherId[i]);
							ps_leave_insert.setString(9, "Approved");
							ps_leave_insert.setString(10, "-");
							ps_leave_insert.setDouble(11, 1.0);
							ps_leave_insert.setString(12, "FH");
							ps_leave_insert.setString(13, "FH");
							ps_leave_insert.setString(14, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.setString(15, HelperClass.convertUIToDatabase(attPojo.getDate()));
							ps_leave_insert.executeUpdate();
							ps_leave_insert.close();
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed+?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=leaveType(?)");
							ps_lv_update.setDouble(1, 1.0);
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, "HPL");
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
							
							
						}
						
					}
					else if(attaStatus[i].equalsIgnoreCase("Holiday")) {
						ps_leaveCheck=conn.prepareStatement("SELECT SNO,LeaveType,NoofLeaves FROM `campus_teachers_leave_request` WHERE RequestedBy=? AND ? BETWEEN StartDate AND EndDate");
						ps_leaveCheck.setString(1, teacherId[i]);
						ps_leaveCheck.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						
						rs_lv_chk=ps_leaveCheck.executeQuery();
						if(rs_lv_chk.next()) {
							ps_leave=conn.prepareStatement("DELETE FROM campus_teachers_leave_request WHERE SNO=?");
							ps_leave.setInt(1, rs_lv_chk.getInt("SNO"));
							ps_leave.executeUpdate();
							
							
							PreparedStatement ps_lv_update=conn.prepareStatement("UPDATE `campus_teacher_new_leave_bank_details` SET total_consumed=(total_consumed-?) WHERE AccYearCode=currentYear() AND EmpId=? AND Leave_Type=?");
							ps_lv_update.setDouble(1, rs_lv_chk.getDouble("NoofLeaves"));
							ps_lv_update.setString(2, teacherId[i]);
							ps_lv_update.setString(3, rs_lv_chk.getString("LeaveType"));
							ps_lv_update.executeUpdate();
							ps_lv_update.close();
						}
						
					}
					
				}
				
				if(statuscount>0){
					
					result="true";
					
				CallableStatement calst=conn.prepareCall("{call todayTimeTable_Procedure()}");
				calst.executeUpdate();
					
				
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
					if (rs_lv_chk != null&& (!rs_lv_chk.isClosed())) {
						rs_lv_chk.close();
					}
					if (ps_insert != null&& (!ps_insert.isClosed())) {
						ps_insert.close();
					}
					if (ps_count  != null&& (!ps_count .isClosed())) {
						ps_count .close();
					}
					if (ps_leaveCheck != null&& (!ps_leaveCheck.isClosed())) {
						ps_leaveCheck.close();
					}
					if (ps_leave != null&& (!ps_leave.isClosed())) {
						ps_leave.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return result;
		}
	
	
	
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceList : Starting");
		
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			String todayDate=null;
			String startDate=null;
			
			if(startdate==null || "".equalsIgnoreCase(startdate) && enddate==null || "".equalsIgnoreCase(enddate)){
				
			 todayDate=HelperClass.getCurrentSqlDate().toString();
			 startDate=HelperClass.getLastThirtyDateFromNow(todayDate);
			
			}else{
				
				 todayDate=HelperClass.convertUIToDatabase(enddate);
				 startDate=HelperClass.convertUIToDatabase(startdate);
				
			}
			
			
			ps_insertPlan = conn.prepareStatement(StaffAttendanceSqlUtil.GET_ATTENDANCE_LIST);
			
			
			ps_insertPlan.setString(1,startDate);
			ps_insertPlan.setString(2,todayDate);
			
			System.out.println("get attendance ::: "+ps_insertPlan);
			
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				
				count++;
				
				StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				
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
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceList: Ending");
		
		return staffAttendanceList;
	}

	public String RefreshAttendanceStatus(String userId) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendance : Starting");
		PreparedStatement ps_getemp=null;
		PreparedStatement ps_count = null;
		PreparedStatement ps_insert = null;
		
		ResultSet rs_count=null;
		ResultSet rs_getemp=null;
		Connection conn = null;
		int count=0;
		
		int statuscount=0;
		
		String result=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ArrayList<StaffAttendanceVo> attendList=new ArrayList<StaffAttendanceVo>();
		try {
			
			conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement("select concat(FirstName,' ',LastName) as teachername,TeacherID,registerId,Loc_ID from campus_teachers where isActive='Y' order by teachername");
				rs = pstmt.executeQuery();
				while(rs.next()){
					
				List<String> dateSize=HelperClass.getDateListBetweenDates(HelperClass.getCurrentSqlDate().toString(), HelperClass.getCurrentSqlDate().toString());
					for(int i=0;i<dateSize.size();i++) {
						
						StaffAttendanceVo attvo = new StaffAttendanceVo();
						
						attvo.setTeacherName(rs.getString("teachername"));
						attvo.setTeacherId(rs.getString("TeacherID"));
						attvo.setRegid(rs.getString("registerId"));
						pstmt1=conn.prepareStatement("SELECT GROUP_CONCAT(SUBSTR(`LogDate`,12)) punchtime FROM `DeviceLogs_Processed` WHERE UserId=? AND SUBSTR(`LogDate`,1,10)=? GROUP BY  SUBSTR(`LogDate`,1,10)");
						pstmt1.setString(1, rs.getString("registerId"));
						pstmt1.setString(2, dateSize.get(i));
						rs1=pstmt1.executeQuery();
						if(rs1.next()) {
							attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
							attvo.setStatus("Present");
							String[] punch=rs1.getString("punchtime").split(",");
							attvo.setInTime(punch[0]);
							if(punch.length%2==0) {
								attvo.setOutTime(punch[punch.length-1]);
							}
							else {
								attvo.setOutTime("-");
							}
						}
						else {
								PreparedStatement hpmt=conn.prepareStatement("SELECT `HOLIDAY_NAME` FROM `campus_holidaymaster` WHERE `HOLIDAY_DATE`=? AND LoC_ID=?");
								hpmt.setString(1,dateSize.get(i));
								hpmt.setString(2,rs.getString("Loc_ID"));
								ResultSet hrs=hpmt.executeQuery();
								if(hrs.next()) {
									attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
									attvo.setStatus("HOLIDAY");
									attvo.setInTime("-");
									attvo.setOutTime("-");
								}
								else {
									PreparedStatement hpl=conn.prepareStatement("SELECT cnlb.ShortName FROM campus_teachers_leave_request ctlr JOIN campus_new_leave_bank cnlb ON ctlr.LeaveType=cnlb.Leave_ID WHERE ctlr.RequestedBy=? AND ? BETWEEN ctlr.ApprovedStartDate AND ctlr.ApprovedEndDate");
									hpl.setString(1,rs.getString("TeacherID"));
									hpl.setString(2,dateSize.get(i));
									ResultSet hls=hpl.executeQuery();
									if(hls.next()) {
										
										attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
										attvo.setStatus(hls.getString("ShortName"));
										attvo.setInTime("-");
										attvo.setOutTime("-");
									}
									
									else {
										java.util.Date sd;
										sd = new SimpleDateFormat("dd-MM-yyyy").parse(HelperClass.convertDatabaseToUI(dateSize.get(i)));
								    	 SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
								    		SimpleDateFormat frmt=new SimpleDateFormat("yyyy-MM-dd");
								    	if(simpleDateformat.format(sd).equalsIgnoreCase("Sunday")) {
								    		attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
								    		attvo.setStatus("WEEKOFF");
											attvo.setInTime("-");
											attvo.setOutTime("-");
								    	}
								    	else if(simpleDateformat.format(sd).equalsIgnoreCase("Saturday")) {

								    		 int iYear = Integer.parseInt(dateSize.get(i).split("-")[0]);
								        	 int iMonth =Integer.parseInt(dateSize.get(i).split("-")[1])-1; // 1 (months begin with 0)
								        	 int iDay = 1;
								        	 int satcont=0;
								        	 List<String> evenSat=new ArrayList<String>();
								        	 Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

								        	 // Get the number of days in that month
								        	 int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
								        	 for(int k=1;k<=daysInMonth;k++) {
								        		 java.util.Date st=new SimpleDateFormat("dd-MM-yyyy").parse(k+"-"+(iMonth+1)+"-"+iYear);
								        		 if(simpleDateformat.format(st).equalsIgnoreCase("Saturday")) {
								        			 satcont++;
								        			 if(satcont%2==0) {
								        				 evenSat.add(frmt.format(st));
								        			 }
								        			 else {
								        				 attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
								        				 attvo.setStatus("Absent");
															attvo.setInTime("-");
															attvo.setOutTime("-");
								        			 }
								        			 
								        		 }
								        	 }
								        	 for(int l=0;l<evenSat.size();l++) {
								        		 if(evenSat.get(l).equalsIgnoreCase(dateSize.get(i))) {
								        			 attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
								        			 attvo.setStatus("WEEKOFF");
														attvo.setInTime("-");
														attvo.setOutTime("-");
								        		 }
								        		 
								        	 }
								    	 
								    	}
								    	else {
								    		attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
								    		 attvo.setStatus("Absent");
												attvo.setInTime("-");
												attvo.setOutTime("-");
								    	}
									}
								
								hls.close();
								hpl.close();
								}
								
								
								hrs.close();
								hpmt.close();
						
						}
					
						attendList.add(attvo);
					}
					
					
					
				}
		
			ps_count = conn.prepareStatement(StaffAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
			
			for(int i=0;i<attendList.size();i++){
				
				ps_count.setString(1,attendList.get(i).getTeacherId());
				ps_count.setString(2,HelperClass.convertUIToDatabase(attendList.get(i).getDate()));
				
				rs_count=ps_count.executeQuery();
				
				while(rs_count.next()){
					
					count=rs_count.getInt(1);
				}
				
				if(count>0){
					
					ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.UPDATE_ATTENDANCE);
					ps_insert.setString(1, attendList.get(i).getStatus());
					ps_insert.setString(2, userId);
					ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
					ps_insert.setString(4, attendList.get(i).getTeacherId());
					ps_insert.setString(5, HelperClass.convertUIToDatabase(attendList.get(i).getDate()));
					
					statuscount=ps_insert.executeUpdate();
					
					
				}else{
					
					ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.INSERT_ATTENDANCE);
					ps_insert.setString(1, attendList.get(i).getTeacherId());
					ps_insert.setString(2, HelperClass.convertUIToDatabase(attendList.get(i).getDate()));
					ps_insert.setString(3, attendList.get(i).getStatus());
					ps_insert.setString(4, userId);
					ps_insert.setTimestamp(5, HelperClass.getCurrentTimestamp());
					
					statuscount=ps_insert.executeUpdate();
					
				}
			}
			
			if(statuscount>0){
				
				result="true";
				
			CallableStatement calst=conn.prepareCall("{call todayTimeTable_Procedure()}");
			calst.executeUpdate();
				
			
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
				if (rs_count != null&& (!rs_count.isClosed())) {
					rs_count.close();
				}
				if(rs!=null && (!rs.isClosed())) {
					rs.close();
				}
				if(rs1!=null && (!rs1.isClosed())) {
					rs1.close();
				}
				if(rs2!=null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (ps_insert != null&& (!ps_insert.isClosed())) {
					ps_insert.close();
				}
				if(pstmt1!=null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if(pstmt2!=null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if(pstmt!=null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
		
		return result;
	}
}
