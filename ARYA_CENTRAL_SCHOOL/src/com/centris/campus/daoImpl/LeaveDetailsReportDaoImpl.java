package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.LeaveDetailsReportDao;
import com.centris.campus.forms.LeaveDetailsReportForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeaveReportSqlUtils;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveDetailsReportVo;
import com.centris.campus.vo.LeaveStatusListVO;
import com.centris.campus.vo.TeacherVo;



public class LeaveDetailsReportDaoImpl implements LeaveDetailsReportDao{
	
private static Logger logger = Logger.getLogger(LeaveDetailsReportDaoImpl.class);
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	int year=localCalendar.get(Calendar.YEAR);
	
	
	public  ArrayList<LeaveDetailsReportVo> getLeaveDetails(LeaveDetailsReportForm leaveDetailsReportForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportDaoImpl: getLeaveDetails : Starting");
		
		Connection connection=null;
		
		PreparedStatement ps_leaveType=null;
		ResultSet rs_leaveType=null;
		PreparedStatement ps_leavecount=null;
		ResultSet rs_leave_count=null;
		
		
	
		ArrayList<LeaveDetailsReportVo> leave_list=new ArrayList<LeaveDetailsReportVo>();
		
		String empid=leaveDetailsReportForm.getTeachername();
		if(leaveDetailsReportForm.getTeachername().equalsIgnoreCase("all")){
			empid="%%";
		}
		
		
		try {
			
		connection=JDBCConnection.getSeparateConnection();
			
			ps_leavecount=connection.prepareStatement(LeaveReportSqlUtils.GET_NO_OF_LEAVES);
			
			ps_leavecount.setString(1,leaveDetailsReportForm.getAccyear());
			ps_leavecount.setString(2,leaveDetailsReportForm.getTeachingtype());
			ps_leavecount.setString(3,leaveDetailsReportForm.getLocation());
			ps_leavecount.setString(4,empid);
			System.out.println("asgvd "+ps_leavecount);
			rs_leave_count=ps_leavecount.executeQuery();
			
			while(rs_leave_count.next()){
				
				ArrayList<LeaveDetailsReportVo> leaveNameL=new ArrayList<LeaveDetailsReportVo>();
				ArrayList<LeaveDetailsReportVo> leaveList=new ArrayList<LeaveDetailsReportVo>();
				LeaveDetailsReportVo vo=new LeaveDetailsReportVo();
				vo.setTeachername(rs_leave_count.getString("Name"));
				vo.setTeacherId(rs_leave_count.getString("EmpId"));
				vo.setDoj(HelperClass.convertDatabaseToUI(rs_leave_count.getString("dateofjoining")));
				vo.setDesignationName(rs_leave_count.getString("designationName"));
				vo.setTeachertype(rs_leave_count.getString("teachingType"));
				for(int k=0;k<rs_leave_count.getString("Leave_Type").split(",").length;k++) {
					LeaveDetailsReportVo vo1=new LeaveDetailsReportVo();
					vo1.setLeaveName(rs_leave_count.getString("ShortName").split(",")[k]);
					vo1.setLeaveValue(rs_leave_count.getString("total_available").split(",")[k]);
					vo1.setLeaveConsumed(rs_leave_count.getString("total_consumed").split(",")[k]);
					vo1.setLeaveAvailable(Double.toString(Double.parseDouble(rs_leave_count.getString("total_available").split(",")[k])-Double.parseDouble(rs_leave_count.getString("total_consumed").split(",")[k])));
					leaveNameL.add(vo1);
				}
				
				vo.setLeaveArrayList(leaveNameL);
				
				ps_leaveType=connection.prepareStatement("SELECT CONCAT(ct.FirstName,' ',Lastname) ApproverName,cb.shortName,cr.* FROM `campus_teachers_leave_request` cr JOIN campus_new_leave_bank cb ON cr.LeaveType=cb.Leave_ID LEFT JOIN campus_teachers ct ON cr.ApprovedBy=ct.TeacherID WHERE RequestedBy=? AND LeaveStatus='Approved' AND SUBSTR(EndDate,1,4)=YEAR(CURDATE())");
				ps_leaveType.setString(1, rs_leave_count.getString("EmpId"));
				
				System.out.println("jdfjksdgb  "+ps_leaveType);
				rs_leaveType=ps_leaveType.executeQuery();
				while(rs_leaveType.next()){
					LeaveDetailsReportVo vo1=new LeaveDetailsReportVo();
					vo1.setStartDate(HelperClass.convertDatabaseToUI(rs_leaveType.getString("ApprovedStartDate")));
					vo1.setEndDate(HelperClass.convertDatabaseToUI(rs_leaveType.getString("ApprovedEndDate")));
					vo1.setNoOfleave(rs_leaveType.getString("TotalDaysAproved"));
					vo1.setLeaveName(rs_leaveType.getString("shortName"));
					vo1.setApprovedBy(rs_leaveType.getString("ApproverName"));
					leaveList.add(vo1);
					
				}
				
				
				
				vo.setLeaveList(leaveList);
				
				PreparedStatement pstmtt=connection.prepareStatement("SELECT COUNT(*) halfday FROM `campus_teacher_attendence` cta ,`campus_acadamicyear` ca WHERE cta.AttendenceStatus='Half Day' AND cta.TeacherID=? AND cta.AttendenceDate BETWEEN ca.startDate AND ca.endDate AND ca.acadamic_id=?");
				pstmtt.setString(1,rs_leave_count.getString("EmpId"));
				pstmtt.setString(2,leaveDetailsReportForm.getAccyear());
				ResultSet rss=pstmtt.executeQuery();
				if(rss.next()) {
					vo.setHaflDay(rss.getDouble("halfday"));
				}
				
				rss.close();
				pstmtt.close();
				
				 pstmtt=connection.prepareStatement("SELECT COUNT(*) lwa FROM `campus_teacher_attendence` cta ,`campus_acadamicyear` ca WHERE cta.AttendenceStatus='LWA' AND cta.TeacherID=? AND cta.AttendenceDate BETWEEN ca.startDate AND ca.endDate AND ca.acadamic_id=?");
				pstmtt.setString(1,rs_leave_count.getString("EmpId"));
				pstmtt.setString(2,leaveDetailsReportForm.getAccyear());
				 rss=pstmtt.executeQuery();
				if(rss.next()) {
					vo.setLwa(rss.getDouble("lwa"));
				}
				rss.close();
				pstmtt.close();
				leave_list.add(vo);
				
			}
		
			
			
		}
	catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		} finally {
			try {

				if (rs_leaveType != null && (!rs_leaveType.isClosed())) {

					rs_leaveType.close();
				}
				if (rs_leave_count != null && (!rs_leave_count.isClosed())) {

					rs_leave_count.close();
				}
				if (ps_leaveType != null && (! ps_leaveType.isClosed())) {

					ps_leaveType.close();
				}
				if (ps_leavecount != null && (! ps_leavecount.isClosed())) {

					ps_leavecount.close();
				}
				if (connection != null && (!connection.isClosed())) {

					connection.close();
				}
				

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(),sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage(),e1);
			}
		}

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportDaoImpl: getLeaveDetails : Ending");
		
		JSONArray array=new JSONArray();
		array.put(leave_list);
		
		System.out.println("list of leaves :: "+array);
		
		return leave_list;
	}
	
	public ArrayList<LeaveDetailsReportVo> getLeaveLabelDetails(
			LeaveDetailsReportForm detailsReportForm) {
		
		PreparedStatement ps_leavelabel = null;
		ResultSet rs_leave = null;
		Connection connectionl=null;
		LeaveDetailsReportVo leaveDetailsReportVo = new LeaveDetailsReportVo();
		
		PreparedStatement ps_Accyear=null;
		ResultSet rs_Accyear=null;
		
		ArrayList<LeaveDetailsReportVo> leave_label=new ArrayList<LeaveDetailsReportVo>();
		try{
			
			connectionl=JDBCConnection.getSeparateConnection();
			
			
			 ps_Accyear=connectionl.prepareStatement(LeaveReportSqlUtils.GET_ACC_YEAR);
			 ps_Accyear.setString(1, detailsReportForm.getAccyear());
			 
			 rs_Accyear=ps_Accyear.executeQuery();
			 
			 String yearname=null;
			 
			 while(rs_Accyear.next()){
				 
				 yearname=rs_Accyear.getString("acadamic_year");
				
				 
			 }
			
			ps_leavelabel=connectionl.prepareStatement(LeaveReportSqlUtils.GET_ALL_LABEL);
			
			ps_leavelabel.setString(1,detailsReportForm.getTeachername());
			ps_leavelabel.setString(2,detailsReportForm.getDepartment());
			ps_leavelabel.setString(3,detailsReportForm.getTeachingtype());
			
			rs_leave=ps_leavelabel.executeQuery();
		
			while(rs_leave.next()){
				
				leaveDetailsReportVo.setAccyearName(yearname);
				leaveDetailsReportVo.setDepartmentname(rs_leave.getString("DEPT_NAME"));
				leaveDetailsReportVo.setTeachertype(rs_leave.getString("teachingType"));
				leaveDetailsReportVo.setTeacherId(rs_leave.getString("TeacherID"));
				leaveDetailsReportVo.setTeachername(rs_leave.getString("teacherName"));
				
				
			leave_label.add(leaveDetailsReportVo);
			}
		
		}
		
		catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		} finally {
			try {

				if (rs_leave != null && (!rs_leave.isClosed())) {

					rs_leave.close();
				}
			
				if (ps_leavelabel != null && (! ps_leavelabel.isClosed())) {

					ps_leavelabel.close();
				}
				
				if (connectionl != null && (!connectionl.isClosed())) {

					connectionl.close();
				}
				

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(),sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage(),e1);
			}
		}

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportDaoImpl: getLeaveDetails : Ending");
		
		
		return leave_label;
	}

	@Override
	public ArrayList<TeacherVo> getTeachername(String teachingtype, String dept) {
		
		PreparedStatement ps_leavelabel = null;
		ResultSet rs_leave = null;
		
		Connection connectionl=null;
		
		ArrayList<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		
		try{
			
			connectionl=JDBCConnection.getSeparateConnection();
			
			
			ps_leavelabel=connectionl.prepareStatement(LeaveReportSqlUtils.GET_TEACHERS);
			if(dept.equalsIgnoreCase("all") && teachingtype.equalsIgnoreCase("all")){
				
				ps_leavelabel.setString(1,"%%");
				ps_leavelabel.setString(2,"%%");
			}
			else if(teachingtype.equalsIgnoreCase("all")){
				
				ps_leavelabel.setString(1,"%%");
				ps_leavelabel.setString(2,dept);
				
			}else if(dept.equalsIgnoreCase("all")){
				
				ps_leavelabel.setString(1,teachingtype);
				ps_leavelabel.setString(2,"%%");
			
			}else{
			
				ps_leavelabel.setString(1,teachingtype);
				ps_leavelabel.setString(2,dept);
			}
			
			
			rs_leave=ps_leavelabel.executeQuery();
		
			while(rs_leave.next()){
				
				TeacherVo teacherVo=new TeacherVo();
				
				teacherVo.setTeacherId(rs_leave.getString("TeacherID"));
				teacherVo.setTeacherName(rs_leave.getString("teacherName"));
				
				teacherList.add(teacherVo);
			}
		
		}
		
		catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		} finally {
			try {

				if (rs_leave != null && (!rs_leave.isClosed())) {

					rs_leave.close();
				}
			
				if (ps_leavelabel != null && (! ps_leavelabel.isClosed())) {

					ps_leavelabel.close();
				}
				
				if (connectionl != null && (!connectionl.isClosed())) {

					connectionl.close();
				}
				

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(),sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage(),e1);
			}
		}

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportDaoImpl: getLeaveDetails : Ending");
		
		
		return teacherList;
	}
	
	
	public static void main(String[] args) {
		
		LeaveDetailsReportForm reportform=new LeaveDetailsReportForm();
		reportform.setAccyear("ACY1");
		reportform.setTeachingtype("all");
		reportform.setDepartment("all");
		reportform.setTeachername("all");
		
		new LeaveDetailsReportDaoImpl().getLeaveDetails(reportform);
	}
	

}
