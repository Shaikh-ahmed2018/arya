package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffAttendanceReportDao;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;

public class StaffAttendanceReportDaoImpl implements StaffAttendanceReportDao {

	
	private static final Logger logger = Logger
			.getLogger(StaffAttendanceReportDaoImpl.class);
	
	
	
	public ReportMenuVo getSelectedItemsDaoImpl(String acc) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getSelectedItemsDaoImpl Starting");
		
		
		
		ReportMenuVo selecteditems=new ReportMenuVo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STAFF_ATTENDANCE_ITEMS);
			
			
			pstmt.setString(1, acc);
			
			
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
			
				
				selecteditems.setAccyearname(rs.getString("acadamic_year"));
				selecteditems.setAccyearId(rs.getString("acadamic_id"));
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getSelectedItemsDaoImpl Ending");
		
		return selecteditems;
	}




	public ArrayList<StaffAttendanceVo> getStaffAttendanceReportDaoImpl(ReportMenuVo vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StaffAttendanceReportDaoImpl : getStaffAttendanceReportDaoImpl Starting");
		
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		Connection conn = null;
		ArrayList<StaffAttendanceVo> attendList=new ArrayList<StaffAttendanceVo>();
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement("select concat(FirstName,' ',LastName) as teachername,TeacherID,registerId,Loc_ID from campus_teachers  where teachingType LIKE ? and isActive='Y'  and TeacherID like ? and designation like ? order by teachername");
				pstmt.setString(1, vo.getTeachertype());
				pstmt.setString(2, vo.getTeachertId());
				pstmt.setString(3, vo.getDesignation());
				rs = pstmt.executeQuery();
				while(rs.next()){
					
				List<String> dateSize=HelperClass.getDateListBetweenDates(vo.getFromdate(), vo.getTodate());
					for(int i=0;i<dateSize.size();i++) {
						count++;
						String holiday="";
						
						
						StaffAttendanceVo attvo = new StaffAttendanceVo();
						//DeviceLogs_Processed
						attvo.setLate("On Time");
						attvo.setEarly("On Time");
						attvo.setCount(count);
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
							
						
							
							if(java.sql.Time.valueOf(punch[0]).compareTo(java.sql.Time.valueOf("08:21:00")) > 0) {
							
								attvo.setLate("Late");
							}
							
							if(java.sql.Time.valueOf(punch[punch.length-1]).compareTo(java.sql.Time.valueOf("14:00:00")) < 0) {
								attvo.setEarly("Early");
							}
							
						}
						else {
							pstmt2=conn.prepareStatement("SELECT `AttendenceStatus` FROM `campus_teacher_attendence` WHERE `TeacherID`=? AND `AttendenceDate`=?");
							pstmt2.setString(1, rs.getString("TeacherID"));
							pstmt2.setString(2, dateSize.get(i));
							rs2=pstmt2.executeQuery();
							if(rs2.next()) {
								if(rs2.getString("AttendenceStatus").equalsIgnoreCase("Present")) {
									attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
									attvo.setStatus("Present");
									attvo.setInTime("Manual");
									attvo.setOutTime("Manual");
								}
								else if(rs2.getString("AttendenceStatus").equalsIgnoreCase("Absent")) {
									attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
									attvo.setStatus("Absent");
									attvo.setInTime("-");
									attvo.setOutTime("-");
								}
								else if(rs2.getString("AttendenceStatus").equalsIgnoreCase("Holiday")) {
									attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
									attvo.setStatus("Holiday");
									holiday="Holiday";
									attvo.setInTime("-");
									attvo.setOutTime("-");
								}
								else {
									attvo.setDate(HelperClass.convertDatabaseToUI(dateSize.get(i)));
									attvo.setStatus(rs2.getString("AttendenceStatus"));
									attvo.setInTime("-");
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
									attvo.setStatus(hrs.getString("HOLIDAY_NAME"));
									holiday=hrs.getString("HOLIDAY_NAME");
									attvo.setInTime("-");
									attvo.setOutTime("-");
								}
								else {
									int levcnt=0;
									PreparedStatement hpl=conn.prepareStatement("SELECT cnlb.ShortName FROM `campus_teachers_leave_request` ctlr JOIN campus_new_leave_bank cnlb ON ctlr.LeaveType=cnlb.Leave_ID WHERE ctlr.`RequestedBy`=? AND ? BETWEEN ctlr.`ApprovedStartDate` AND ctlr.`ApprovedEndDate`");
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
								    		attvo.setStatus("SUNDAY");
											attvo.setInTime("-");
											attvo.setOutTime("-");
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
						}
						
						if(vo.getAttendenceStatus().equalsIgnoreCase("%%")) {
							attendList.add(attvo);
						}
						else if(vo.getAttendenceStatus().equalsIgnoreCase("present")) {
							if(attvo.getStatus().equalsIgnoreCase("Present"))
							attendList.add(attvo);
							
						}
						else if(vo.getAttendenceStatus().equalsIgnoreCase("absent")) {
							if(attvo.getStatus().equalsIgnoreCase("Absent"))
								attendList.add(attvo);
						}
						else if(vo.getAttendenceStatus().equalsIgnoreCase("late")) {
							if(attvo.getLate().equalsIgnoreCase("Late"))
								attendList.add(attvo);
						}
						else if(vo.getAttendenceStatus().equalsIgnoreCase("early")) {
							if(attvo.getEarly().equalsIgnoreCase("Early"))
								attendList.add(attvo);
						}
						else if(vo.getAttendenceStatus().equalsIgnoreCase("leave")) {
							if(!attvo.getStatus().equalsIgnoreCase("Present") && !attvo.getStatus().equalsIgnoreCase("Absent") && !attvo.getStatus().equalsIgnoreCase("SUNDAY") && !attvo.getStatus().equalsIgnoreCase(holiday))
								attendList.add(attvo);
						}
					}
					
					
					
				}
		
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null && (!rs.isClosed())) {
					rs.close();
				}
				if(rs1!=null && (!rs1.isClosed())) {
					rs1.close();
				}
				if(rs2!=null && (!rs2.isClosed())) {
					rs2.close();
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
				if(conn!=null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getStaffAttendanceReportDaoImpl Ending");
		
		return attendList;
	}





	public ArrayList<AllTeacherDetailsVo> getTeachingListDaoImpl(
			AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STAFF_TEACHING_LIST);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getTeachingListDaoImpl Ending");
		
		return teachinglist;
	}




	
	public ArrayList<AllTeacherDetailsVo> getNonTeachingListDaoImpl(
			AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getNonTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STAFF_NON_TEACHING_LIST);
		
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getNonTeachingListDaoImpl Ending");
		
		return teachinglist;
	}




	
	public StaffAttendanceVo getSelectedTeacherNameReportDao(ReportMenuVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getSelectedTeacherNameReportDao Starting");
		
		StaffAttendanceVo staffname = new StaffAttendanceVo();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
         pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.TEACHER_NAMES);
		
         pstmt.setString(1, vo.getTeachertId());
         
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
				

				staffname.setTeacherId(rs.getString("TeacherID"));
				staffname.setTeacherName(rs.getString("teachername"));
				
				
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getSelectedTeacherNameReportDao Ending");
		
		return staffname;
	}




	public List<AllTeacherDetailsVo> getStaffList(AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getStaffList Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement("select TeacherID,CONCAT(Abbreviative_Id,'-',FirstName,' ',LastName) as teachername from campus_teachers where teachingType LIKE ? and designation LIKE ? and isActive='Y'");
			pstmt.setString(1, vo.getTeacherType());
			pstmt.setString(2, vo.getDesignation());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getStaffList Ending");
		
		return teachinglist;
	}




	public List<AllTeacherDetailsVo> getDesignationList(AllTeacherDetailsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getDesignationList Starting");
		
		ArrayList<AllTeacherDetailsVo> designationList = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement("SELECT DISTINCT cd.DesignationCode,cd.designationName FROM campus_teachers ct JOIN campus_designation cd ON ct.designation=cd.DesignationCode WHERE ct.teachingType=? and ct.isActive='Y'");
			pstmt.setString(1, vo.getTeacherType());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo designationVo = new AllTeacherDetailsVo();
				designationVo.setDesignationId(rs.getString("DesignationCode"));
				designationVo.setDesignation(rs.getString("designationName"));
				
				designationList.add(designationVo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportDaoImpl : getDesignationList Ending");
		
		return designationList;
	}

}
