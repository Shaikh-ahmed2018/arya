package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.SendSMS;
import com.centris.campus.dao.SmsDao;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.util.CommunicateUtilConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public class SmsDaoIMPL implements SmsDao{

	private static final Logger logger = Logger
			.getLogger(SmsDaoIMPL.class);
	
	public List<ClassPojo> getclasslistDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getclasslistDao  Starting");
		
		List<ClassPojo> classlist = new ArrayList<ClassPojo>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_CLASS_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ClassPojo pojo = new ClassPojo();
				pojo.setClassid(rs.getString("classdetail_id_int"));
				pojo.setClassName(rs.getString("classdetails_name_var"));
				
				classlist.add(pojo);
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getclasslistDao  Ending");
		
		return classlist;
	}

	
	public ArrayList<SectionPojo> getsectionlistDao(String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsectionlistDao  Starting");
		
		ArrayList<SectionPojo> sec = new ArrayList<SectionPojo>();
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_SECTION_LIST);
			
			pstmt.setString(1, classid);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				SectionPojo pojo = new SectionPojo();
				
				pojo.setSecId(rs.getString("classsection_id_int"));
				pojo.setSectionName(rs.getString("classsection_name_var"));
				
				
				sec.add(pojo);
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
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
				exception.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsectionlistDao  Ending");
		
		return sec;
	}


	
	public ArrayList<SectionPojo> getsubjectlistDao(String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsubjectlistDao  Starting");
		
		ArrayList<SectionPojo> sec = new ArrayList<SectionPojo>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_SUBJECT_LIST);
			
			pstmt.setString(1, classid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				SectionPojo pojo = new SectionPojo();
				
				pojo.setSubjectid(rs.getString("subjectID"));
				pojo.setSubjectname(rs.getString("subjectName"));
				
				sec.add(pojo);
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
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
				exception.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsubjectlistDao  Ending");
		
		return sec;
	}


	
	public String inserthomeworDao(SmsVo vo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : inserthomeworDao  Starting");
		
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		
		PreparedStatement pstmtsub = null;
		
		ResultSet rs1 = null;
		
		ResultSet rssub = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmtsub = conn.prepareStatement(SmsUtilsConstants.GET_SUB_DETAILS);
			pstmtsub.setString(1, vo.getSubjectid());
			rssub = pstmtsub.executeQuery();
			
			SmsVo voval = new SmsVo();
			
			while(rssub.next())
				
			{                 
				
				String stuid = rssub.getString("student_id_int");
				
				pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
				
				pstmt1.setString(1, stuid);
				
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
					
				{
					String classval = rs1.getString("classdetail_id_int");
					
					String sectionval = rs1.getString("classsection_id_int");
					
					
					String str = IDGenerator.getPrimaryKeyID("campus_homework");
					
					pstmt = conn.prepareStatement(SmsUtilsConstants.INSERT_HOMEWORK);
					
					pstmt.setString(1, str);
					
					pstmt.setString(2, (HelperClass.convertUIToDatabase(vo.getDate())));
					
					pstmt.setString(3, classval);
					
					pstmt.setString(4, sectionval);
					
					pstmt.setString(5, vo.getSubjectid());
					
					pstmt.setString(6,vo.getDescription());
					
					pstmt.setTimestamp(7, time_stamp);
					
					pstmt.setString(8,vo.getCreateuser());
					
					String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), vo.getDescription());
					
					result = pstmt.executeUpdate();
				}
			}
			
			if (result == 1) {
				result_Status = "HomeWork Sent Successfully";
				
				
			} else {
				result_Status = "HomeWork Sending Failed";
				
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				if (rs1  != null && !rs1 .isClosed()) {
					rs1 .close();
				}
				if (rssub  != null && !rssub .isClosed()) {
					rssub .close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1  != null && !pstmt1 .isClosed()) {
					pstmt1 .close();
				}
				if (pstmtsub   != null && !pstmtsub  .isClosed()) {
					pstmtsub  .close();
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
				+ " Control in SmsDaoIMPL : inserthomeworDao  Ending");
		
		return result_Status;
	}



	public List<SmsVo> getHomeWorklistDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorklistDao  Starting");
		
		
		List<SmsVo> homeworklist = new ArrayList<SmsVo>();
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_HOMEWORK_LIST);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				SmsVo vo = new SmsVo();
				
				
				vo.setSubjectid(rs.getString("subjectid"));
				vo.setSubjectname(rs.getString("subjectName"));
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("dateid")));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setDescription(rs.getString("description"));
				
			
				
			
			
				
				homeworklist.add(vo);
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
				+ " Control in SmsDaoIMPL : getHomeWorklistDao  Ending");
		
		return homeworklist;
	}


	
	public List<SmsVo> getHomeWorkSearchlistDao(String searchTerm) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorkSearchlistDao  Starting");
		
		 List<SmsVo> searchlist = new ArrayList<SmsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SmsUtilsConstants.SEARCH_HOMEWORK_LIST);
			
			pstmt.setString(1, "%" + searchTerm + "%");
			pstmt.setString(2, "%" + searchTerm + "%");
			pstmt.setString(3, "%" + searchTerm + "%");
			pstmt.setString(4, "%" + searchTerm + "%");
			pstmt.setString(5, "%" + searchTerm + "%");
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SmsVo vo = new SmsVo();

				vo.setHomeworkid(rs.getString("homeworkid"));
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("dateid")));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setSubjectname(rs.getString("subjectName"));
				vo.setDescription(rs.getString("description"));
				
				searchlist.add(vo);
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
				+ " Control in SmsDaoIMPL : getHomeWorkSearchlistDao  Ending");
		
		return searchlist;
	}


	
	public String deletehomeworkDao(SmsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Starting");
		
		String status = "";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(SmsUtilsConstants.DELETE_HOMEWORK);
			
			pstmt.setString(1, vo.getHomeworkid());
			
			count = pstmt.executeUpdate();
			
			
			if(count==1){
				
				status = "HomeWork Deleted Successfully";
				
				
				
			}
			else{
				status = "HomeWork Deleting Failed";
				
				
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {
				
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
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Ending");
		
		return status;
	}


	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsServiceDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Starting");
		
		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
	
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
	
		ResultSet rs2 = null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_MEETING_LIST);
			
		
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String studentid = rs.getString("studentname");
				
				String[] categoryval=studentid.split(",");
				
					pstmt2 = conn
							.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);
					
					for(int i=0;i<categoryval.length;i++){
					
						pstmt2.setString(1, categoryval[i]);
						
						
					
					rs2 = pstmt2.executeQuery();
					
					while(rs2.next()){
						
						LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
						
						meetvo.setMeetingid(rs.getString("meetingid"));
						meetvo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("meetingdate")));
						meetvo.setStartTime(rs.getString("starttime"));
						meetvo.setEndTime(rs.getString("endtime"));
						meetvo.setTitle(rs.getString("title"));
						meetvo.setVenueid(rs.getString("venuedetails"));
						meetvo.setSubjectName(rs.getString("subjectname"));
						
						meetvo.setMeetingwith(rs2.getString("student_fname_var"));
						meetvo.setClassname(rs2.getString("classdetails_name_var"));
						meetvo.setSectionname(rs2.getString("classsection_name_var"));
						
						
						
						
						meetinglist.add(meetvo);
						
					}
					}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			
		}
		finally {

			try {
				if (rs  != null && !rs .isClosed()) {
					rs .close();
				}
				if (rs2  != null && !rs2 .isClosed()) {
					rs2 .close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt2  != null && !pstmt2 .isClosed()) {
					pstmt2 .close();
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
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Ending");
		
		return meetinglist;
	}
	
	
	
	public ArrayList<StudentInfoVO> getStudentListDetailsDao(
			String[] categoryval) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Starting");
		
		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			String accyear = HelperClass.getCurrentYearID();
			
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS);
			
				for(int i=0;i<categoryval.length;i++){
				
				pstmt.setString(1, categoryval[i]);
				pstmt.setString(2, accyear);
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					StudentInfoVO student = new StudentInfoVO();
					student.setId(rs.getString("student_id_int"));
					student.setName(rs.getString("studentname"));
					studentlist.add(student);

				}
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
				if (conn != null && !conn.isClosed()) {

					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Ending");
		return studentlist;
	}
	
	
public ArrayList<SubjectPojo> getSubjectListDetailsDao(String[] categoryval) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSubjectListDetailsDao  Starting");
		
		ArrayList<SubjectPojo> sec = new ArrayList<SubjectPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_SUBJECT_DETAILS);
			
			
			for(int i=0;i<categoryval.length;i++){
				
				pstmt.setString(1, categoryval[i]);
				
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					SubjectPojo subject = new SubjectPojo();
					
					subject.setSubjectId(rs.getString("subjectID"));
					
					subject.setSubjectName(rs.getString("subjectName"));
					
					/*section.setClassid(rs.getString("classdetail_id_int"));
					section.setClassname(rs.getString("classdetails_name_var"));
					section.setSectionId(rs.getString("classsection_id_int"));
					section.setSectionName(rs.getString("classsection_name_var"));*/

					sec.add(subject);

				}

				
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
				+ " Control in SmsDaoIMPL : getSubjectListDetailsDao  Ending");
		
		return sec;
	}
	

public String saveMeetingDetailsDao(LstmsUpcomingMeetingVO meetingvo) {
	
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Starting");
	
	java.util.Date today = new java.util.Date();
	java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
	PreparedStatement pstmt = null;
	String result_Status = "";
	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt1 = null;
	
	ResultSet rs1 = null;
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		
		
		for(int i = 0; i < meetingvo.getStudentname().length; i++)
			
		{
			
			
			
			
			
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
			
			pstmt1.setString(1, meetingvo.getStudentname()[i]);
			
			
			
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next())
				
			{
				
				String classval = rs1.getString("classdetail_id_int");
				
				String sectionval = rs1.getString("classsection_id_int");
				
				
				String str = IDGenerator.getPrimaryKeyID("sms_meeting");
				
				pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_MEETING);
				
				pstmt.setString(1, str);
				
				pstmt.setString(2, HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
				
				pstmt.setString(3, meetingvo.getTitle());
				
				pstmt.setString(4, classval);
				
				pstmt.setString(5, sectionval);
				
				pstmt.setString(6, meetingvo.getStartTime());
				
				pstmt.setString(7, meetingvo.getEndTime());
				
				pstmt.setString(8, meetingvo.getSubjectid());
				
				pstmt.setString(9, meetingvo.getStudentname()[i]);
				
				pstmt.setString(10, meetingvo.getDescription());
				
				pstmt.setString(11, meetingvo.getCreatedby());
				
				pstmt.setTimestamp(12, time_stamp);
				
				pstmt.setString(13, meetingvo.getAccyearid());
				
				
			
				
				
				String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), meetingvo.getDescription());
				
				result = pstmt.executeUpdate();
				
				
				
				
			}
			
			
		}
		
		
		
		if (result == 1) {
			result_Status = "Message sent successfully";
			
			
			
		} else {
			result_Status = "Message sending failed";
			
		}
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	finally {

		try {
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
			exception.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Ending");
	
	return result_Status;
}



public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : deletehomeworkDao  Starting");
	
	ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	Connection conn = null;
	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn
				.prepareStatement(SmsUtilsConstants.GET_LATECOMERS_LIST);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			String studentid = rs.getString("Student_Code");
				pstmt2 = conn
						.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);
				
				pstmt2.setString(1, studentid);
				
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()){
					
					LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
					meetvo.setMeetingid(rs.getString("Late_Comers_Code"));
					meetvo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
					meetvo.setTitle(rs.getString("Title"));
					meetvo.setMeetingwith(rs2.getString("student_fname_var"));
					meetvo.setClassname(rs2.getString("classdetails_name_var"));
					meetvo.setSectionname(rs2.getString("classsection_name_var"));
					meetvo.setDescription(rs.getString("Description"));
					meetinglist.add(meetvo);
					
				}
			
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
			if (rs2 != null && !rs2.isClosed()) {
				rs2.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
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
			+ " Control in SmsDaoIMPL : deletehomeworkDao  Ending");
	
	return meetinglist;
}



public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : addlatecomers  Starting");
	
	java.util.Date today = new java.util.Date();
	java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
	PreparedStatement pstmt = null;
	String result_Status = "";
	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt1 = null;
	
	ResultSet rs1 = null;
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
		
		
		
		for(int i = 0; i < meetingvo.getStudentname().length; i++)
			
		{
			
			
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
			
			pstmt1.setString(1, meetingvo.getStudentname()[i]);
			
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next())
				
			{
				String classval = rs1.getString("classdetail_id_int");
				
				String sectionval = rs1.getString("classsection_id_int");
				
				String str = IDGenerator.getPrimaryKeyID("latecomers_sms");
				
				pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_LATE);
				
				pstmt.setString(1, str);
				
				pstmt.setString(2, meetingvo.getStudentname()[i]);
				
				pstmt.setString(3, classval);
				
				pstmt.setString(4, sectionval);
				
				pstmt.setString(5, HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
				
				pstmt.setString(6, meetingvo.getTitle());
				
				pstmt.setString(6, meetingvo.getDescription());
				
				
				
				String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), meetingvo.getDescription());
				result = pstmt.executeUpdate();
				
			}
		}
		
		if (result == 1) {
			result_Status = "Message sent successfully";
			
		} else {
			result_Status = "Message sending failed";
			
		}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	finally {

		try {
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
			exception.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : addlatecomers  Ending");
	
	return result_Status;
}



public ArrayList<UniformSmsPojo> getUniformListDetailsDao() {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Starting");
	
	
	ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn
				.prepareStatement(SmsUtilsConstants.GET_UNIFORM_LIST);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			UniformSmsPojo pojo = new UniformSmsPojo();
			
			
			pojo.setUniformcode(rs.getString("Uniform_Code"));
			pojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
			pojo.setSmstext(rs.getString("Description"));
			pojo.setClassname(rs.getString("classdetails_name_var"));
			pojo.setSectionname(rs.getString("classsection_name_var"));
			pojo.setStudentname(rs.getString("student_fname_var"));
			
		
			
			uniformlist.add(pojo);
			
		
			
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
			+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Ending");
	
	return uniformlist;
}



public int storeUniformStudent(UniformSmsPojo upojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Starting");
	
	PreparedStatement pstmt = null;
	String result_Status = "";
	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt1 = null;
	
	ResultSet rs1 = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		for(int i = 0; i < upojo.getStudentid().length; i++)
			
		{
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
			
			pstmt1.setString(1, upojo.getStudentid()[i]);
			
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next())
				
			{
				
				String classval = rs1.getString("classdetail_id_int");
				String sectionval = rs1.getString("classsection_id_int");
				
				String str = IDGenerator.getPrimaryKeyID("uniform_sms");
				
				pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_UNIFORM);
				
				pstmt.setString(1, str);
				
				pstmt.setString(2, upojo.getStudentid()[i]);
				
				pstmt.setString(3, classval);
				
				pstmt.setString(4, sectionval);
				
				pstmt.setString(5, upojo.getDate());
				
				pstmt.setString(6, upojo.getSmstext());
				
				String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"),upojo.getSmstext());
				
				result = pstmt.executeUpdate();
			}
		}
		
		if (result == 1) {
			result_Status = "Message sent successfully";
			
		} else {
			result_Status = "Message sending failed";
			
		}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	finally {

		try {
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
			exception.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Ending");
	return result;
}


@Override
public String insertOtherSMS(UniformSmsPojo upojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : insertOtherSMS  Starting");
	
	Connection conn =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int status = 0;
	String result = null;
	try{
		conn = JDBCConnection.getSeparateConnection();
		for(int i = 0; i < upojo.getStudentid().length; i++)
			
		{
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
			
			pstmt.setString(1, upojo.getStudentid()[i]);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String classval = rs.getString("classdetail_id_int");
				String sectionval = rs.getString("classsection_id_int");
				pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_OTHERS);
				
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("sms_othersms"));
				
				pstmt.setString(2, upojo.getStudentid()[i]);
				
				pstmt.setString(3, classval);
				
				pstmt.setString(4, sectionval);
				
				pstmt.setString(5, HelperClass.convertUIToDatabase(upojo.getDate()));
				
				pstmt.setString(6, upojo.getSmstext());
				
				String sms = new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),upojo.getSmstext());
				status = pstmt.executeUpdate();
			}
		}
		
		if (status == 1) {
			result = "success";
		} else {
			result = "failed";
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
			exception.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsDaoIMPL : insertOtherSMS  Ending");
	return null;
}

	/*
	
	
	public ArrayList<StudentInfoVO> getStudentListDetailsDao(
			String[] categoryval) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Starting");
		
		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			String accyear = HelperClass.getCurrentYearID();
			
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS);
			
				for(int i=0;i<categoryval.length;i++){
				
				pstmt.setString(1, categoryval[i]);
				
				pstmt.setString(2, accyear);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					StudentInfoVO student = new StudentInfoVO();
					
					student.setId(rs.getString("student_id_int"));
					student.setName(rs.getString("studentname"));
					
					studentlist.add(student);

				}
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Ending");
		
		
		return studentlist;
	}
	
	public String deleteMeetingDao(SmsVo vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deleteMeetingDao  Starting");
		
		String status = "";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(SmsUtilsConstants.DELETE_MEETING);
			
			pstmt.setString(1, vo.getMeetingid());
			
			count = pstmt.executeUpdate();
			
			
			if(count==1){
				
				status = "Meeting/Event Deleted Successfully";
				
				
				
			}
			else{
				status = "Meeting/Event Deleting Failed";
				
				
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deleteMeetingDao  Ending");
		
		return status;
	}
	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsDao(
			String searchTerm) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingSearchListDetailsDao  Starting");
		
		 ArrayList<LstmsUpcomingMeetingVO> searchlist = new ArrayList<LstmsUpcomingMeetingVO>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = null;
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SmsUtilsConstants.SEARCH_MEETING_LIST);
				
				pstmt.setString(1, "%" + searchTerm + "%");
				pstmt.setString(2, "%" + searchTerm + "%");
				pstmt.setString(3, "%" + searchTerm + "%");
				pstmt.setString(4, "%" + searchTerm + "%");
				pstmt.setString(5, "%" + searchTerm + "%");
				pstmt.setString(6, "%" + searchTerm + "%");
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();

					vo.setMeetingid(rs.getString("meetingid"));
					vo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("meetingdate")));
					vo.setTitle(rs.getString("title"));
					vo.setStartTime(rs.getString("starttime"));
					vo.setEndTime(rs.getString("endtime"));
					vo.setVenueid(rs.getString("venuedetails"));
					
					searchlist.add(vo);
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingSearchListDetailsDao  Ending");
		
		return searchlist;
	}
	public int storeUniformDetails(UniformSmsPojo upojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : storeUniformDetails  Starting");
		
		PreparedStatement pstmt = null;
		int count = 0;
		String status=null;
		
		pstmt = (PreparedStatement) JDBCConnection
				.getStatement(SmsUtilsConstants.STORE_UNIFORM_SMS_DETAILS);
		
		try {
			
			pstmt.setString(1, upojo.getUniformcode());
			pstmt.setString(2, upojo.getDate());
			pstmt.setString(3, upojo.getSmstext().trim());
			pstmt.setInt(4, upojo.getIssection());
			pstmt.setInt(5, upojo.getIsstudent());
			pstmt.setString(6, upojo.getSmsstatus());
			pstmt.setTimestamp(7, upojo.getCreatedate());
			pstmt.setString(8, upojo.getCreatedby());

			count = pstmt.executeUpdate();
			
			
			if(count>0)
			{
				
				status="Uniform Details Stored Successfully";
				
			}
			else
			{
				status="Uniform Details Storing Failed";
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : storeUniformDetails  Ending");
		return count;
	}
	public int storeUniformSections(UniformSmsPojo upojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : storeUniformSections  Starting");
		
		
		PreparedStatement ps_sectiondetails = null;
		int count = 0;
		try {
			
			ps_sectiondetails = (PreparedStatement) JDBCConnection
					.getStatement(SmsUtilsConstants.STORE_UNIFORM_SECTION_DETAILS);
			
			for (int i = 0; i < upojo.getSectionid().length; i++) {

				ps_sectiondetails.setString(1,
						upojo.getUniformcode());
				ps_sectiondetails.setString(2,
						upojo.getSectionid()[i]);
				ps_sectiondetails.setTimestamp(3,
						upojo.getCreatedate());
				ps_sectiondetails.setString(4,
						upojo.getCreatedby());

				count = ps_sectiondetails.executeUpdate();

			}
			
			if(count>0)
			{
				
				Runnable r = new SMSThread(upojo.getUniformcode(),
						upojo.getDate(), "Uniform");
				new Thread(r).start();
				
				status="Uniform Details Stored Successfully";
				System.out.println(status);
			}
			else
			{
				status="Uniform Details Storing Failed";
				System.out.println(status);
			}

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : storeUniformSections  Ending");
		
		return count;
	}
	public int storeUniformStudent(UniformSmsPojo upojo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Starting");
		
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		
		ResultSet rs1 = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			
			
			
			
			for(int i = 0; i < upojo.getStudentid().length; i++)
				
			{
				pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
				
				pstmt1.setString(1, upojo.getStudentid()[i]);
				
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
					
				{
					
					String classval = rs1.getString("classdetail_id_int");
					
					String sectionval = rs1.getString("classsection_id_int");
					
					
					String str = IDGenerator.getPrimaryKeyID("uniform_sms");
					
					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_UNIFORM);
					
					pstmt.setString(1, str);
					
					pstmt.setString(2, upojo.getStudentid()[i]);
					
					pstmt.setString(3, classval);
					
					pstmt.setString(4, sectionval);
					
					pstmt.setString(5, upojo.getDate());
					
					pstmt.setString(6, upojo.getSmstext());
					
					String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), upojo.getSmstext());
					
					result = pstmt.executeUpdate();
				}
			}
			
			if (result == 1) {
				result_Status = "Message sent successfully";
				
			} else {
				result_Status = "Message sending failed";
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Ending");
		
		return result;
	}
	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Starting");
		
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		
		ResultSet rs1 = null;
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			
			
			for(int i = 0; i < meetingvo.getStudentname().length; i++)
				
			{
				
				
				pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
				
				pstmt1.setString(1, meetingvo.getStudentname()[i]);
				
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
					
				{
					String classval = rs1.getString("classdetail_id_int");
					
					String sectionval = rs1.getString("classsection_id_int");
					
					String str = IDGenerator.getPrimaryKeyID("latecomers_sms");
					
					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_LATE);
					
					pstmt.setString(1, str);
					
					pstmt.setString(2, meetingvo.getStudentname()[i]);
					
					pstmt.setString(3, classval);
					
					pstmt.setString(4, sectionval);
					
					pstmt.setString(5, HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
					
					pstmt.setString(6, meetingvo.getTitle());
					
					pstmt.setString(6, meetingvo.getDescription());
					
					String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), meetingvo.getDescription());
					result = pstmt.executeUpdate();
					
				}
			}
			
			if (result == 1) {
				result_Status = "Message sent successfully";
				
			} else {
				result_Status = "Message sending failed";
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : saveMeetingDetailsDao  Ending");
		
		return result_Status;
	}
	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() 

	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Starting");
		
		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Connection conn = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_LATECOMERS_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String studentid = rs.getString("Student_Code");
					pstmt2 = conn
							.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);
					
					pstmt2.setString(1, studentid);
					
					rs2 = pstmt2.executeQuery();
					
					while(rs2.next()){
						
						LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
						meetvo.setMeetingid(rs.getString("Late_Comers_Code"));
						meetvo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
						meetvo.setTitle(rs.getString("Title"));
						meetvo.setMeetingwith(rs2.getString("student_fname_var"));
						meetvo.setClassname(rs2.getString("classdetails_name_var"));
						meetvo.setSectionname(rs2.getString("classsection_name_var"));
						meetvo.setDescription(rs.getString("Description"));
						meetinglist.add(meetvo);
						
					}
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Ending");
		
		return meetinglist;
		
	}

	public ArrayList<UniformSmsPojo> getUniformListDetailsDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Starting");
		
		
		ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_UNIFORM_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				UniformSmsPojo pojo = new UniformSmsPojo();
				
				
				pojo.setUniformcode(rs.getString("Uniform_Code"));
				pojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				pojo.setSmstext(rs.getString("Description"));
				pojo.setClassname(rs.getString("classdetails_name_var"));
				pojo.setSectionname(rs.getString("classsection_name_var"));
				pojo.setStudentname(rs.getString("student_fname_var"));
				
			
				
				uniformlist.add(pojo);
				
			
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Ending");
		
		return uniformlist;
	}

	public int storeBdatDetailsDao() {
			
		System.out.println("Inside Send Birthday DAOIMPLssss");
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String smsContent = null;
		
		ResultSet rs1 = null,rs=null;
		try {
			
			System.out.println("Inside Send Birthday DAOIMPL");
			
			conn = JDBCConnection.getSeparateConnection();
			String todayDate = HelperClass.getCurrentSqlDate().toString().substring(5,10);
			System.out.println(todayDate);
			
			
			pstmt = conn.prepareStatement(SmsUtilsConstants.CHECK_BDAY_DATE);
			pstmt.setString(1, "%" +todayDate + "%");
			
			System.out.println("qqq" +pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				result = rs.getInt(1);
				
				if(result>0)
				{
					System.out.println("Call for BDAY Wishes");
					
					pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_SMS_DETAILS_FOR_BDAY);
					pstmt1.setString(1, "%" + todayDate + "%");
					
					rs1= pstmt1.executeQuery();
					
					while(rs1.next())
					{
						
						String str = IDGenerator.getPrimaryKeyID("birthday_sms");
						
						smsContent = "The sweetest greetings to the most adorable child.May your special day be filled with the moments of endless joy and fun!!! Happy Birthday " + rs1.getString("name")+ " ";
						
						pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_BIRTHDAY);
						pstmt.setString(1, str);
						pstmt.setDate(2, HelperClass.getCurrentSqlDate());
						pstmt.setString(3, rs1.getString("classdetail_id_int"));
						pstmt.setString(4, rs1.getString("classsection_id_int"));
						pstmt.setString(5, rs1.getString("student_id_int"));
						pstmt.setString(6, smsContent);
						pstmt.setString(7, "USR1");
						pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
						result = pstmt.executeUpdate();		
						
						System.out.println("BDAY" +pstmt);
				
						if (result == 1) {
							result_Status = "Message sent successfully";
							String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), smsContent);
							
						} else {
							result_Status = "Message sending failed";
							
						}
				
					}
					
				}
				else
				{
					System.out.println("Doesn't Exists");
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			for(int i = 0; i < upojo.getStudentid().length; i++)
				
			{
				pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);
				
				pstmt1.setString(1, upojo.getStudentid()[i]);
				
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
					
				{
					
					String classval = rs1.getString("classdetail_id_int");
					
					String sectionval = rs1.getString("classsection_id_int");
					
					
					String str = IDGenerator.getPrimaryKeyID("birthday_sms");
					
					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_BIRTHDAY);
					
					pstmt.setString(1, str);
					
					pstmt.setString(2, upojo.getDate());
					
					pstmt.setString(3, classval);
					
					pstmt.setString(4, sectionval);
					
					pstmt.setString(5, upojo.getStudentid()[i]);
					
					pstmt.setString(6, upojo.getSmstext());
					
				
					pstmt.setString(7, upojo.getCreatedby());
					
					pstmt.setTimestamp(8, upojo.getCreatedate());
					
					String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), upojo.getSmstext());
					
					result = pstmt.executeUpdate();
				}
			}
			
			if (result == 1) {
				result_Status = "Message sent successfully";
				
			} else {
				result_Status = "Message sending failed";
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	public ArrayList<UniformSmsPojo> getBdayListDetailsDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getBdayListDetailsDao  Starting");
		
		
		ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_BDAY_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				UniformSmsPojo pojo = new UniformSmsPojo();
				
				pojo.setUniformcode(rs.getString("bdaycode"));
				pojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("bdaydate")));
				pojo.setSmstext(rs.getString("smscontent"));
				pojo.setClassname(rs.getString("classdetails_name_var"));
				pojo.setSectionname(rs.getString("classsection_name_var"));
				pojo.setStudentname(rs.getString("student_fname_var"));
				
				uniformlist.add(pojo);
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getBdayListDetailsDao  Ending");
		
		
		return uniformlist;
	}


	public static void main (String[] args)
			{
		
		    new SmsDaoIMPL().storeBdatDetailsDao();
			
			}*/
	
	
}
