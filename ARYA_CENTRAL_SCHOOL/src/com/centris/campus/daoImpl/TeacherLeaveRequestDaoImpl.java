package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TeacherLeaveRequestDao;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ParentModuleUtil;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.TimeTableSqlConstants;
import com.centris.campus.vo.LeaveBalanceVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;

public class TeacherLeaveRequestDaoImpl implements TeacherLeaveRequestDao {

	private static final Logger logger = Logger
			.getLogger(TeacherLeaveRequestDaoImpl.class);

	public List<UserDetailVO> getRequestUserListDao(String user) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRequestUserListDao Starting");

		List<UserDetailVO> userlist = new ArrayList<UserDetailVO>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			if (user.contains("PAR")) {

				pstmt = conn
						.prepareStatement(ParentModuleUtil.GET_PARENT_USER_DETAILS);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					UserDetailVO getuser = new UserDetailVO();

					getuser.setEployeecode(rs.getString("employeecode"));
					getuser.setTeachername(rs.getString("teachername"));

					getuser.setClassname(rs.getString("classdetails_name_var"));
					getuser.setTeacherid(rs.getString("teacherID"));

					userlist.add(getuser);
				}

			} else {

				pstmt = conn
						.prepareStatement(ParentModuleUtil.GET_TEACHER_USER_DETAILS);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					UserDetailVO getuser = new UserDetailVO();

					getuser.setEployeecode(rs.getString("employeecode"));

					getuser.setTeachername(rs.getString("teachername"));

					userlist.add(getuser);

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRequestUserListDao  Ending");

		return userlist;
	}

	public ArrayList<TimeTableVo> getTeacherTimetableListDao(String userid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherTimetableListDao Starting");

		ArrayList<TimeTableVo> teachertimetablelist = new ArrayList<TimeTableVo>();
		Connection conn = null;
		

		// String classSection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = JDBCConnection.getSeparateConnection();
			for(int i=1;i<=9;i++){
				pstmt = conn.prepareStatement("SELECT ctt.daycode,ctd.dayname,ctt.period"+i+" teacherlist,ctts.period"+i+" subjectlist,ccd.classdetails_name_var,ccs.classsection_name_var FROM `campus_timetable_teacherdetails` ctt JOIN `campus_timetable_student` cts ON cts.timetable_id=ctt.teachertimetable_id   JOIN campus_timetable_studentdetails ctts ON cts.timetable_id=ctts.timetableid JOIN `campus_classdetail` ccd ON ccd.classdetail_id_int=cts.classId AND cts.locationId=ccd.locationId JOIN `campus_classsection` ccs ON ccs.classsection_id_int=cts.sectionid AND cts.locationId=ccs.locationId JOIN `campus_timetable_day` ctd ON (ctd.daycode=ctt.daycode AND ctd.daycode=ctts.daycode) WHERE ctt.period"+i+" REGEXP CONCAT('(^|,)(', REPLACE(?, ',', '|'), ')(,|$)')");
				pstmt.setString(1, userid);
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					HashMap<String, String> perio=new HashMap<String, String>();
					TimeTableVo tableVo = new TimeTableVo();
					
					
					String subjectName="";
					String[] teacherIDList=rs.getString(3).split(",");
					String[] subjectDList=rs.getString(4).split(",");
					for(int k=0;k<teacherIDList.length;k++){
						
					
						
						if(userid.equalsIgnoreCase(teacherIDList[k])){
							subjectName=getSubjectfName(subjectDList[k]);
						}
					}
					
					tableVo.setDayid(rs.getString("daycode"));
					tableVo.setDayname(rs.getString("dayname"));
					tableVo.setClassname(rs.getString("classdetails_name_var"));
					tableVo.setSectionname(rs.getString("classsection_name_var"));
					perio.put("period"+i, rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var")+"   ("+subjectName+")");
					tableVo.setPeriod(perio);
					
					teachertimetablelist.add(tableVo);
				}
				
			}
			
			

			

	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherTimetableListDao  Ending");

		return teachertimetablelist;
	}

	public String getSubjectName(String period, String dayname,
			String classSection) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getClassName Starting");

		String subjectname = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;

		try {

			String accyearid = HelperClass.getCurrentYearID();

			String classid = "";
			String sectionId = "";

			if (classSection != "" && !("-".equalsIgnoreCase(classSection))) {

				classid = classSection.split("-")[0];
				sectionId = classSection.split("-")[1];

			}

			connection = JDBCConnection.getSeparateConnection();

			String query = "select subjectName from campus_subject where subjectID in (select "
					+ period
					+ " from campus_timetable_studentdetails where timetableid in (select timetable_id from campus_timetable_student where classid='"
					+ classid
					+ "'  and sectionid='"
					+ sectionId
					+ "' and accyearid='"
					+ accyearid
					+ "') and daycode='"
					+ dayname + "' )";

			pstmt = connection.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				subjectname = rs.getString("subjectName");

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherTimetableListDao  Ending");

		return subjectname;
	}

	public String getClassName(String classname) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getClassName Starting");
		String className = "";
		String sectionName = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;

		try {

			String classid = "";
			String sectionId = "";

			if (classname != " " && !("-".equalsIgnoreCase(classname))) {
				classid = classname.split("-")[0];
				sectionId = classname.split("-")[1];
			}

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(ParentModuleUtil.GET_CLASS_NAME);

			pstmt.setString(1, classid);
			pstmt.setString(2, sectionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				className = rs.getString("classdetails_name_var") + "-"
						+ rs.getString("classsection_name_var");

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getClassName  Ending");

		return className;
	}

	public String leaveRequestEntryDao(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : leaveRequestEntryDao Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		String status = MessageConstants.REQUESTFALSE;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.SAVE_LEAVE_REQUEST);

			pstmt.setString(1, leavevo.getRequestto());

			pstmt.setString(2, leavevo.getFromdate());
			pstmt.setString(3, leavevo.getTodate());
			pstmt.setString(4, leavevo.getStarttime());
			pstmt.setString(5, leavevo.getEndtime());
			pstmt.setString(6, leavevo.getLeavetype());
			pstmt.setString(7, leavevo.getTotalleave());
			pstmt.setString(8, leavevo.getReason());
			pstmt.setString(9, leavevo.getFileupload());
			pstmt.setString(10, "Pending");
			pstmt.setString(11, leavevo.getUserid());
			pstmt.setTimestamp(12, time_stamp);

			System.out.println("pstmt is "+pstmt);
			
			int count = pstmt.executeUpdate();

			if (count > 0) {

				status = MessageConstants.REQUESTTRUE;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : leaveRequestEntryDao  Ending");

		return status;
	}

	public String updateleaveRequestEntryDao(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : updateleaveRequestEntryDao Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "";

		Connection conn = null;

		String status = MessageConstants.REQUESTFALSE;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATE_LEAVE_REQUEST);

			pstmt.setString(1, leavevo.getRequestto());
			pstmt.setString(2, leavevo.getTotalleave());
			pstmt.setString(3, leavevo.getFromdate());
			pstmt.setString(4, leavevo.getTodate());
			pstmt.setString(5, leavevo.getStarttime());
			pstmt.setString(6, leavevo.getEndtime());
			pstmt.setString(7, leavevo.getLeavetype());
			pstmt.setString(8, leavevo.getFileupload());
			pstmt.setString(9, leavevo.getReason());
			pstmt.setTimestamp(10, time_stamp);
			pstmt.setString(11, leavevo.getCreateuser());
			pstmt.setString(12,"Pending");
			pstmt.setInt(13, leavevo.getSno());

			rs = pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : updateleaveRequestEntryDao  Ending");
		return result;
	}

	public LeaveRequestVo getRequestLeaveDao(int sno) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRequestLeaveDao Starting");

		LeaveRequestVo leavelist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			

			leavelist = new LeaveRequestVo();
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_DETAILS);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				leavelist.setRequestto(rs.getString("teachername"));
				leavelist.setTotalleave(rs.getString("NoofLeaves"));
				leavelist.setFromdate(HelperClass.convertDatabaseToUI(rs
						.getString("StartDate")));

				leavelist.setTodate(HelperClass.convertDatabaseToUI(rs
						.getString("EndDate")));
				leavelist.setStarttime(rs.getString("SessionStart"));
				leavelist.setEndtime(rs.getString("SessionEnd"));
				leavelist.setLeavetype(rs.getString("LeaveType"));
				leavelist.setFileupload(rs.getString("filepaath"));
				leavelist.setReason(rs.getString("ReasonForLeave"));
				leavelist.setRequesttoid(rs.getString("RequestedTo"));
				leavelist.setLeavename(rs.getString("Leave_name"));
				leavelist.setSno(sno);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRequestLeaveDao  Ending");

		return leavelist;
	}

	public static void main(String[] args) {

		new TeacherLeaveRequestDaoImpl().getRequestUserListDao(null);
	}

	public ArrayList<ParentVO> getTeacherListDao(ParentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherListDao Starting");

		ArrayList<ParentVO> stulist = new ArrayList<ParentVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_DETAILS);

			pstmt.setString(1, vo.getParentID());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentVO vo1 = new ParentVO();

				vo1.setTeacherID(rs.getString("TeacherID"));

				vo1.setUserName(rs.getString("teachername"));

				stulist.add(vo1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherListDao  Ending");

		return stulist;
	}

	public ArrayList<LstmsUpcomingMeetingVO> getteachermeetinglistDao(
			LstmsUpcomingMeetingVO meetvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getteachermeetinglistDao Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(SQLUtilConstants.GET_TEACHER_MEETING_DETAILS);

			pstmt.setString(1, meetvo.getTeacher());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();

				vo.setMeetingid(rs.getString("meetingid"));
				vo.setMeetingDate(rs.getString("meetingdate"));
				vo.setStartTime(rs.getString("starttime"));
				vo.setEndTime(rs.getString("endtime"));
				vo.setTitle(rs.getString("title"));
				vo.setVenueid(rs.getString("venuedetails"));
				vo.setMeetingwith(rs.getString("teachername"));
				vo.setSubjectName(rs.getString("subjectname"));

				meetinglist.add(vo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getteachermeetinglistDao  Ending");

		return meetinglist;
	}

	public ArrayList<LeaveRequestVo> getleaveRequestDetailsDao(String parentid,
			LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getleaveRequestDetailsDao Starting");

		ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno1 = 0;

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (parentid.contains("PAR")) {

				pstmt = conn
						.prepareStatement(SQLUtilConstants.GET_PARENT_LEAVE_REQUEST);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					LeaveRequestVo vo1 = new LeaveRequestVo();

					vo1.setSno(rs.getInt("SNO"));
					vo1.setTotalleave(rs.getString("NoofLeaves"));

					vo1.setFromdate(HelperClass.convertDatabaseToUI(rs
							.getString("StartDate")));
					vo1.setTodate(HelperClass.convertDatabaseToUI(rs
							.getString("EndDate")));
					vo1.setStarttime(rs.getString("SessionStart"));
					vo1.setEndtime(rs.getString("SessionEnd"));
					vo1.setStatus(rs.getString("LeaveStatus"));
					vo1.setReason(rs.getString("ReasonForLeave"));
					vo1.setLeavetype(rs.getString("LeaveType"));
					vo1.setRequestto(rs.getString("teachername"));
					vo1.setStudentname(rs.getString("studentname"));
					vo1.setRequestby(rs.getString("type"));

					leavelist.add(vo1);

				}

			}

			else if (parentid.contains("TEA")) {

				pstmt = conn
						.prepareStatement(SQLUtilConstants.GET_TEACHER_LEAVE_REQUEST);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					LeaveRequestVo vo1 = new LeaveRequestVo();

					vo1.setSno(rs.getInt("SNO"));
					vo1.setTotalleave(rs.getString("NoofLeaves"));

					vo1.setFromdate(HelperClass.convertDatabaseToUI(rs
							.getString("StartDate")));
					vo1.setTodate(HelperClass.convertDatabaseToUI(rs
							.getString("EndDate")));
					vo1.setStarttime(rs.getString("SessionStart"));
					vo1.setEndtime(rs.getString("SessionEnd"));
					vo1.setStatus(rs.getString("LeaveStatus"));
					vo1.setReason(rs.getString("ReasonForLeave"));
					vo1.setLeavetype(rs.getString("LeaveType"));
					vo1.setRequestto(rs.getString("teachername"));
					vo1.setRequestby(rs.getString("type"));
					leavelist.add(vo1);
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getleaveRequestDetailsDao  Ending");

		return leavelist;
	}

	public ArrayList<RemainderMasterVO> getRemainderlistDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRemainderlistDao Starting");

		ArrayList<RemainderMasterVO> remainderlist = new ArrayList<RemainderMasterVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(SQLUtilConstants.GET_TEACHER_REMAINDER);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				RemainderMasterVO vo = new RemainderMasterVO();

				vo.setName(rs.getString("remainder_title"));
				vo.setDescription(rs.getString("description"));
				vo.setRemtype(rs.getString("remainder_to"));

				remainderlist.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getRemainderlistDao  Ending");

		return remainderlist;
	}

	public List<LeaveBalanceVo> viewLeaveBalListDetailsDao(String userid,
			String accyear, LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : viewLeaveBalListDetailsDao Starting");

		ArrayList<LeaveBalanceVo> leanebal = new ArrayList<LeaveBalanceVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SL_COUNT);

			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			double slbal = 0.0;
			double clbal = 0.0;
			double plbal = 0.0;

			while (rs.next()) {

				slbal = rs.getDouble("totaldaysaproved");

			}

			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_CL_COUNT);

			pstmt1.setString(1, userid);

			rs1 = pstmt1.executeQuery();

			while (rs1.next()) {

				clbal = rs1.getDouble("totaldaysaproved");

			}

			pstmt2 = conn.prepareStatement(SQLUtilConstants.GET_PL_COUNT);

			pstmt2.setString(1, userid);

			rs2 = pstmt2.executeQuery();

			while (rs2.next()) {

				plbal = rs2.getDouble("totaldaysaproved");

			}

			pstmt3 = conn.prepareStatement(SQLUtilConstants.GET_TOTAL_LEAVES);

			pstmt3.setString(1, accyear);

			rs3 = pstmt3.executeQuery();

			while (rs3.next()) {

				LeaveBalanceVo vo = new LeaveBalanceVo();

				vo.setOpeningbal(rs3.getShort("SL"));
				vo.setLeavetype("SL");
				vo.setConsumebal(slbal);
				vo.setClosingbal(rs3.getShort("SL") - slbal);

				leanebal.add(vo);

				vo = new LeaveBalanceVo();

				vo.setOpeningbal(rs3.getShort("PL"));
				vo.setLeavetype("PL");
				vo.setConsumebal(plbal);
				vo.setClosingbal(rs3.getShort("PL") - plbal);

				leanebal.add(vo);

				vo = new LeaveBalanceVo();

				vo.setOpeningbal(rs3.getShort("CL"));
				vo.setLeavetype("CL");
				vo.setConsumebal(clbal);
				vo.setClosingbal(rs3.getShort("CL") - clbal);

				leanebal.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : viewLeaveBalListDetailsDao  Ending");

		return leanebal;
	}

	@SuppressWarnings("null")
	public ArrayList<TeacherVo> getTeacherListForTimeTable(String userid,
			String userType) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherListForTimeTable Starting");
		
		ArrayList<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		userType=userType.toLowerCase();
			try {
				conn = JDBCConnection.getSeparateConnection();
				if(userType.contains("admin") || userType.contains("superadmin") || userType.contains("super admin")){
					pstmt = conn.prepareStatement("SELECT TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM campus_teachers WHERE teachingType IN('TEACHING','SUBJECT TEACHER','MISC','TEMPORARY')");
				}
				else{
					pstmt = conn.prepareStatement("SELECT TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM campus_teachers WHERE teachingType IN('TEACHING','SUBJECT TEACHER','MISC','TEMPORARY') AND TeacherID=?");
					pstmt.setString(1, userid);
				}
				rs=pstmt.executeQuery();
				while(rs.next()){
					TeacherVo vo=new TeacherVo();
					vo.setTeacherId(rs.getString("TeacherID"));
					vo.setTeacherName(rs.getString("FirstName")+"-"+rs.getString("LastName"));
					vo.setAbbrvation(rs.getString("Abbreviative_Id"));
					vo.setSubscriberId(rs.getString("registerId"));
					
					teacherList.add(vo);
				}
				
				System.out.println("shdfghsdgfisd="+pstmt);
		
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			finally{
				try {
					if(rs!=null || !(rs.isClosed())){
						rs.close();
					}
					if(pstmt!=null || !(pstmt.isClosed())){
						pstmt.close();
					}
					if(conn!=null || !(conn.isClosed())){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherListForTimeTable  Ending");

		return teacherList;
	}

	public ArrayList<TimeTableVo> getTodaytimeTableDetails(String userid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherTimetableListDao Starting");

		ArrayList<TimeTableVo> teachertimetablelist = new ArrayList<TimeTableVo>();
		Connection conn = null;
		

		// String classSection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT cday.dayname,CONCAT(cc.`classdetails_name_var`,'-',cs.`classsection_name_var`) class,csub.`subjectName`,ctd.* FROM `today_timetable_details_for_substitution` ctd JOIN campus_timetable_day cday ON cday.daycode=ctd.daycode  JOIN `campus_subject` csub ON csub.subjectID=ctd.subjectid  JOIN `campus_timetable_student` cts ON cts.`timetable_id`=ctd.timtable_id JOIN `campus_classsection` cs ON cs.`classsection_id_int`=cts.sectionid JOIN `campus_classdetail` cc ON (cc.`classdetail_id_int`=cs.classdetail_id_int AND cc.locationId=cs.locationId) WHERE ctd.`teacherid`=? AND cday.dayname=DAYNAME(CURDATE()) ORDER BY ctd.period");
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				HashMap<String, String> perio=new HashMap<String, String>();
				TimeTableVo tableVo = new TimeTableVo();
				
				
				
				
				tableVo.setDayid(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
				tableVo.setDayname(rs.getString("dayname"));
				
				perio.put(rs.getString("period"), rs.getString("class")+"   ("+rs.getString("subjectName")+")");
				tableVo.setPeriod(perio);
				
				teachertimetablelist.add(tableVo);
			}
			
			

			

	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getTeacherTimetableListDao  Ending");

		return teachertimetablelist;
	}
	
	
public String getSubjectfName(String subjectId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String subjectName=null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
			pstmt.setString(1, subjectId);
			
			
			rst = pstmt.executeQuery();
			if (rst.next()) {
				subjectName=rst.getString("subjectName");
			}else{
				subjectName="";
			}
			
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
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getSubjectfName Ending");
		return subjectName;
	}

}
