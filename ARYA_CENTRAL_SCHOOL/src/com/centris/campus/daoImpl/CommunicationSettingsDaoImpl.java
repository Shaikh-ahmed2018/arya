package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.CommunicationSettingsDao;
import com.centris.campus.pojo.BdayPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.MeetingPojo;
import com.centris.campus.pojo.RemarksPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.CommunicateUtilConstants;
import com.centris.campus.util.ExamSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TransportUtilConstants;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class CommunicationSettingsDaoImpl implements CommunicationSettingsDao {

	private static final Logger logger = Logger
			.getLogger(CommunicationSettingsDaoImpl.class);

	public ArrayList<UpcomingRemarksVO> getRemarksListDetailsDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getRemarksListDetailsDao Starting");

		ArrayList<UpcomingRemarksVO> remarkslist = new ArrayList<UpcomingRemarksVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		

		try {

			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(CommunicateUtilConstants.GET_ALL_STUDENT_REMARKS_DETAILS);

			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UpcomingRemarksVO remarkvo = new UpcomingRemarksVO();
				remarkvo.setDateId(HelperClass.convertDatabaseToUI(rs
						.getString("Date")));
				remarkvo.setRemarkcode(rs.getString("commentCode"));
				remarkvo.setName(rs.getString("studentname"));
				remarkvo.setSubjectName(rs.getString("subjectName"));
				remarkvo.setClassname(rs.getString("classdetails_name_var"));
				remarkvo.setSectionname(rs.getString("classsection_name_var"));
				remarkvo.setRemarks(rs.getString("remarks"));

				
				remarkslist.add(remarkvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		try {

			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(CommunicateUtilConstants.GET_ALL_TEACHER_REMARKS_DETAILS);
			
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				UpcomingRemarksVO remarkvo = new UpcomingRemarksVO();
				remarkvo.setDateId(HelperClass.convertDatabaseToUI(rs
						.getString("Date")));
				remarkvo.setRemarkcode(rs.getString("commentCode"));
				remarkvo.setName(rs.getString("teachername"));
				remarkvo.setSubjectName(rs.getString("subjectName"));

				remarkvo.setRemarks(rs.getString("remarks"));
				remarkvo.setClassname("-");
				remarkvo.setSectionname("-");
				
				

				remarkslist.add(remarkvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getRemarksListDetailsDao  Ending");
		return remarkslist;
	}

	public List<StreamDetailsVO> getStreamListDetailsDao() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getStreamListDetailsDao Starting");

		List<StreamDetailsVO> streamvo = null;

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_STREAM_DETAILS);

			streamvo = new ArrayList<StreamDetailsVO>();
			rs = pstmt.executeQuery();

			while (rs.next()) {

				StreamDetailsVO stream = new StreamDetailsVO();

				stream.setStreamId(rs.getString("classstream_id_int"));
				stream.setStreamName(rs.getString("classstream_name_var"));

				streamvo.add(stream);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getStreamListDetailsDao  Ending");

		return streamvo;
	}

	public List<ClassPojo> getClassListDetailsDao(ClassPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getClassListDetailsDao Starting");

		List<ClassPojo> claspojo = new ArrayList<ClassPojo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_CLASS_DETAILS);

			//pstmt.setString(1, pojo.getStreamId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ClassPojo getclas = new ClassPojo();
				getclas.setClassId(rs.getString("classdetail_id_int"));
				getclas.setClassName(rs.getString("classdetails_name_var"));

				claspojo.add(getclas);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getClassListDetailsDao  Ending");

		return claspojo;
	}

	public ArrayList<SectionPojo> getSectionListDetailsDao(String[] categoryval) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getSectionListDetailsDao Starting");

		ArrayList<SectionPojo> sec = new ArrayList<SectionPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_SECTION_DETAILS);
			
			
			
			
			for(int i=0;i<categoryval.length;i++){
				
				pstmt.setString(1, categoryval[i]);
				
				
				
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					SectionPojo section = new SectionPojo();
					section.setClassid(rs.getString("classdetail_id_int"));
					section.setClassname(rs.getString("classdetails_name_var"));
					section.setSectionId(rs.getString("classsection_id_int"));
					section.setSectionName(rs.getString("classsection_name_var"));

					sec.add(section);

				}

				
			}
			
			
			

			

			

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getSectionListDetailsDao  Ending");

		return sec;
	}

	public ArrayList<StudentInfoVO> getStudentListDetailsDao(
			StudentInfoVO studentvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getStudentListDetailsDao Starting");

		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_STUDENT_DETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				StudentInfoVO studvo = new StudentInfoVO();

				studvo.setStudentId(rs.getString("student_id_int"));
				studvo.setName(rs.getString("student_fname_var"));

				studentlist.add(studvo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getStudentListDetailsDao  Ending");
		return studentlist;
	}

	public ArrayList<TeacherRegistrationPojo> getTeacherListDetailsDao(
			TeacherRegistrationPojo teacherpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getTeacherListDetailsDao Starting");

		ArrayList<TeacherRegistrationPojo> teacherlist = new ArrayList<TeacherRegistrationPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_TEACHER_DETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				TeacherRegistrationPojo teapojo = new TeacherRegistrationPojo();
				teapojo.setTeacherId(rs.getString("TeacherID"));
				teapojo.setTfastname(rs.getString("FirstName"));

				teacherlist.add(teapojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getTeacherListDetailsDao  Ending");
		return teacherlist;
	}

	public ArrayList<SubjectPojo> getSubjectListDetailsDao(SubjectPojo subpojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getSubjectListDetailsDao Starting");

		ArrayList<SubjectPojo> subjectlist = new ArrayList<SubjectPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.GET_SUBJECT_DETAILS);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				SubjectPojo subjectpojo = new SubjectPojo();
				subjectpojo.setSubjectId(rs.getString("subjectID"));
				subjectpojo.setSubjectName(rs.getString("subjectName"));

				subjectlist.add(subjectpojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getSubjectListDetailsDao  Ending");

		return subjectlist;
	}

	public String saveRemarkDetailsDao(RemarksPojo remarkpojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : saveRemarkDetailsDao Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;

		try {

			

			String str = IDGenerator.getPrimaryKeyID("campus_comments");
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(CommunicateUtilConstants.SAVE_REMARK);

			pstmt.setString(1, str);
			pstmt.setString(2, remarkpojo.getAccyear());

			if (remarkpojo.getStudentid().equalsIgnoreCase("S"))

			{
				

				pstmt.setString(3, remarkpojo.getRemarksto());

			} else {
				pstmt.setString(3, "");
			}

			if (remarkpojo.getStudentid().equalsIgnoreCase("T")) {
				

				pstmt.setString(4, remarkpojo.getRemarksto());
			} else {
				pstmt.setString(4, "");
			}

			pstmt.setString(5, remarkpojo.getRemarks());
			pstmt.setString(6, remarkpojo.getRemarkstype());
			pstmt.setString(7, remarkpojo.getSubjectid());
			pstmt.setString(8, "Y");
			pstmt.setString(9, remarkpojo.getCreateUser());
			pstmt.setTimestamp(10, time_stamp);
			pstmt.setString(11, remarkpojo.getDateId());

			

			result = pstmt.executeUpdate();

			if (result == 1) {
				result_Status = MessageConstants.SuccesstermRemark;
			} else {
				result_Status = MessageConstants.ErrortermRemark;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : saveRemarkDetailsDao  Ending");

		return result_Status;
	}

	public ArrayList<UpcomingRemarksVO> getRemarkListDetailsDao(
			UpcomingRemarksVO remrakvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getRemarkListDetailsDao Starting");

		ArrayList<UpcomingRemarksVO> remarklist = new ArrayList<UpcomingRemarksVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SEARCH_STUDENT_REMARKS_DETAILS);
			pstmt.setString(1, remrakvo.getFromdate());
			pstmt.setString(2, remrakvo.getTodate());
			
			

			rs = pstmt.executeQuery();

			while (rs.next()) {

				

				UpcomingRemarksVO vo = new UpcomingRemarksVO();

				vo.setDateId(HelperClass.convertDatabaseToUI(rs
						.getString("Date")));
				vo.setName(rs.getString("studentname"));
				vo.setSubjectName(rs.getString("subjectName"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setRemarks(rs.getString("remarks"));

				remarklist.add(vo);
			}

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SEARCH_TEACHER_REMARKS_DETAILS);

			pstmt.setString(1, remrakvo.getFromdate());
			pstmt.setString(2, remrakvo.getTodate());

			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				UpcomingRemarksVO vo = new UpcomingRemarksVO();

				vo.setDateId(HelperClass.convertDatabaseToUI(rs
						.getString("Date")));
				vo.setName(rs.getString("teachername"));
				vo.setSubjectName(rs.getString("subjectName"));
				vo.setRemarks(rs.getString("remarks"));

				vo.setClassname("-");
				vo.setSectionname("-");

				remarklist.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getRemarkListDetailsDao  Ending");

		return remarklist;
	}

	public boolean validateRemarkDao(RemarksVo remarkform) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : validateRemarkDao Starting");

		boolean remark_validate = false;

		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();

			pscheckExamName = conn
					.prepareStatement(CommunicateUtilConstants.VALIDATE_STUDENT_REMARK);

			pscheckExamName.setString(1, remarkform.getStudentid());
			pscheckExamName.setString(2, remarkform.getRemarks());
			pscheckExamName.setString(3, remarkform.getSubjectid());
			pscheckExamName.setString(4, remarkform.getDateId());

			
		
			rsCheckExamName = pscheckExamName.executeQuery();

			while (rsCheckExamName.next()) {

				count = rsCheckExamName.getInt(1);

			}

			if (count > 0) {

				remark_validate = true;

			} else {

				remark_validate = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : validateRemarkDao  Ending");

		return remark_validate;
	}

	

	public String saveMeetingDetailsDao(MeetingPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : saveMeetingDetailsDao Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String result_Status = "";
		int result = 0;
		int result1 = 0;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			String str = IDGenerator.getPrimaryKeyID("campus_meeting");
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SAVE_MEETING);
			
			pstmt.setString(1, str);
			pstmt.setString(2, pojo.getMeetingDate());
			pstmt.setString(3, pojo.getStartTime());
			pstmt.setString(4, pojo.getEndTime());
			pstmt.setString(5, pojo.getDescription());
			
			pstmt.setString(6, pojo.getCreatedby());
			
			pstmt.setString(7, "active");
			pstmt.setTimestamp(8, time_stamp);
			pstmt.setString(9, pojo.getTitle());
			pstmt.setString(10, pojo.getSubjectid());
			pstmt.setString(11, pojo.getVenueid());
			pstmt.setString(12, pojo.getMeetingfor());
			
			
			
			result = pstmt.executeUpdate();
			
			
			if(result !=0){
				
				String meetingperson[]=pojo.getMeetingwith().split(",");
				/*String sectionid[]=pojo.getSectionid().split(",");
				String classid[]=pojo.getClassid().split(",");*/
				
				pstmt1 = conn.prepareStatement(CommunicateUtilConstants.SAVE_MEETING1);
					
						for(int i =0;i<meetingperson.length;i++){
							
							
							pstmt1.setString(1, str);
							
						
							
							if (pojo.getMeetingfor().equalsIgnoreCase("S"))

							{
								pstmt1.setString(2, meetingperson[i]);
								
							} else {
								
								pstmt1.setString(2, "");
							}

							if (pojo.getMeetingfor().equalsIgnoreCase("T")) {
								

								pstmt1.setString(3, meetingperson[i]);
								
							}else {
								
								pstmt1.setString(3, "");
							}
							
							
							
							
							
							
							result1 = pstmt1.executeUpdate();
						}
					}
					
				
			
			/*String str = IDGenerator.getPrimaryKeyID("campus_meeting");
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SAVE_MEETING);

			pstmt.setString(1, str);
			pstmt.setString(2, pojo.getMeetingDate());
			pstmt.setString(3, pojo.getStartTime());
			pstmt.setString(4, pojo.getEndTime());
			pstmt.setString(5, pojo.getDescription());
			pstmt.setString(6, pojo.getSectionid());
			pstmt.setString(7, pojo.getClassid());

			if (pojo.getStudentid().equalsIgnoreCase("S"))

			{
			

				pstmt.setString(8, pojo.getMeetingwith());

			} else {
				pstmt.setString(8, "");
			}

			if (pojo.getStudentid().equalsIgnoreCase("T")) {
				

				pstmt.setString(9, pojo.getMeetingwith());
			} else {
				pstmt.setString(9, "");
			}

			pstmt.setString(10, pojo.getCreatedby());
			pstmt.setString(11, "active");
			pstmt.setTimestamp(12, time_stamp);
			pstmt.setString(13, pojo.getTitle());
			pstmt.setString(14, pojo.getSubjectid());

		

			result = pstmt.executeUpdate();*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : saveMeetingDetailsDao  Ending");

		return result_Status;
	}

	

	public ArrayList<UpcomingBdayVo> searchBadyListDetailsDao(
			UpcomingBdayVo bdayvo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : searchBadyListDetailsDao Starting");
		
		
		ArrayList<UpcomingBdayVo> bdaylist = new ArrayList<UpcomingBdayVo>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SEARCH_ALL_BDAY_DETAILS);

			pstmt.setString(1, bdayvo.getFromdate());
			pstmt.setString(2, bdayvo.getTodate());

	

			rs = pstmt.executeQuery();

             while (rs.next()) {
				
				UpcomingBdayVo bdayvo1 = new UpcomingBdayVo();

			
			

				String person = rs.getString("STUDENT_ADM_NO");
				

				if (person.contains("TEA")) {
					
					bdayvo1.setBdayid(rs.getString("DOB_CODE"));
					bdayvo1.setBdayDate(rs.getString("DOB_DATE"));
					bdayvo1.setContent(rs.getString("DOB_CONTENT"));
					
					
				
	
						pstmt1 = conn
								.prepareStatement(CommunicateUtilConstants.GET_TEACHER_BDAY_WISHES);
						
						
						pstmt1.setString(1, person);
						
						
						
						rs1 = pstmt1.executeQuery();
						
						while(rs1.next()){
							
						
							
							bdayvo1.setBdayperson(rs1.getString("teachername"));
							bdayvo1.setClassname("-");
							bdayvo1.setSectionname("-");
							
						}
						
				}
				
				else if(person.contains("STU")){
					
					bdayvo1.setBdayid(rs.getString("DOB_CODE"));
					bdayvo1.setBdayDate(rs.getString("DOB_DATE"));
					bdayvo1.setContent(rs.getString("DOB_CONTENT"));
					
						pstmt2 = conn.prepareStatement(CommunicateUtilConstants.GET_STUDENT_BDAY_WISHES);
						
						pstmt2.setString(1, person);
						
						
						
						rs2 = pstmt2.executeQuery();
						
						while(rs2.next()){
							
						
							bdayvo1.setBdayperson(rs2.getString("studentname"));
							bdayvo1.setClassname(rs2.getString("classdetails_name_var"));
							bdayvo1.setSectionname(rs2.getString("classsection_name_var"));
							
							
						}
						
				}

				
				
				bdaylist.add(bdayvo1);
				
			} 
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : searchBadyListDetailsDao  Ending");
		return bdaylist;
	}
	
	
	
	
	
	public ArrayList<LstmsUpcomingMeetingVO> searchMeetingListDetailsDao(
			LstmsUpcomingMeetingVO meetingvo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : searchMeetingListDetailsDao Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection connection = null;

		
		try {
			
			
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(CommunicateUtilConstants.SEARCH_ALL_MEETING_DETAILS);
			
			
			pstmt.setString(1, meetingvo.getFromdate());
			pstmt.setString(2, meetingvo.getTodate());

	

			rs = pstmt.executeQuery();
			
			
while(rs.next()){
				
				LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
				
				
				String teacherid = rs.getString("TeacherId");
				String studentid = rs.getString("StudentId");
				
				if (teacherid.contains("TEA")) {
					
					meetvo.setMeetingid(rs.getString("meetingid"));
					meetvo.setMeetingDate(rs.getString("meetingdate"));
					meetvo.setStartTime(rs.getString("starttime"));
					meetvo.setEndTime(rs.getString("endtime"));
					meetvo.setTitle(rs.getString("title"));
					meetvo.setVenueid(rs.getString("venuedetails"));
					meetvo.setSubjectName(rs.getString("subjectname"));
					
					pstmt1 = connection
							.prepareStatement(CommunicateUtilConstants.GET_TEACHER_MEETING);
					
					
					pstmt1.setString(1, teacherid);
					
					
					
					rs1 = pstmt1.executeQuery();
					
					while(rs1.next()){
						
					
						
						meetvo.setMeetingwith(rs1.getString("FirstName"));
						meetvo.setClassname("-");
						meetvo.setSectionname("-");
					}
					
					
				}
				
				
				else if(studentid.contains("STU")){
					
					meetvo.setMeetingid(rs.getString("meetingid"));
					meetvo.setMeetingDate(rs.getString("meetingdate"));
					meetvo.setStartTime(rs.getString("starttime"));
					meetvo.setEndTime(rs.getString("endtime"));
					meetvo.setTitle(rs.getString("title"));
					meetvo.setVenueid(rs.getString("venuedetails"));
					meetvo.setSubjectName(rs.getString("subjectname"));
					
					pstmt2 = connection
							.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);
					
					
					pstmt2.setString(1, studentid);
					
					rs2 = pstmt2.executeQuery();
					
					while(rs2.next()){
						
					
						meetvo.setMeetingwith(rs2.getString("student_fname_var"));
						meetvo.setClassname(rs2.getString("classdetails_name_var"));
						meetvo.setSectionname(rs2.getString("classsection_name_var"));
						
						
					}
					
					
					
				}
				
				meetinglist.add(meetvo);
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SEARCH_STUDENT_MEETING_DETAILS);

			pstmt.setString(1, meetingvo.getFromdate());
			pstmt.setString(2, meetingvo.getTodate());

		

			rs = pstmt.executeQuery();

			while (rs.next()) {

			

				LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();

				vo.setMeetingDate(HelperClass.convertDatabaseToUI(rs
						.getString("meetingdate")));
				vo.setName(rs.getString("studentname"));
				vo.setSubjectName(rs.getString("subjectName"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setTitle(rs.getString("title"));
				vo.setDescription(rs.getString("description"));
				vo.setStartTime(rs.getString("starttime"));
				vo.setEndTime(rs.getString("endtime"));

				meetinglist.add(vo);
			}

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.SEARCH_TEACHER_MEETING_DETAILS);

			pstmt.setString(1, meetingvo.getFromdate());
			pstmt.setString(2, meetingvo.getTodate());

			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();

				vo.setMeetingDate(HelperClass.convertDatabaseToUI(rs
						.getString("meetingdate")));
				vo.setName(rs.getString("teachername"));
				vo.setSubjectName(rs.getString("subjectName"));
				vo.setTitle(rs.getString("title"));
				vo.setDescription(rs.getString("description"));
				vo.setStartTime(rs.getString("starttime"));
				vo.setEndTime(rs.getString("endtime"));

				meetinglist.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}*/

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : searchMeetingListDetailsDao  Ending");
		return meetinglist;
	}

	public String createBdayDetailsDao(BdayPojo bdaypojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : createBdayDetailsDao Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String result_Status = "";
		int result = 0;
		int result1 = 0;
		Connection conn = null;

		try {

			String str = IDGenerator.getPrimaryKeyID("campus_dob_details");
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(CommunicateUtilConstants.CREATE_BDAY);

			pstmt.setString(1, str);

			pstmt.setString(2, bdaypojo.getBdayDate());
			pstmt.setString(3, bdaypojo.getContent());
			pstmt.setString(4, "active");
			pstmt.setTimestamp(5, time_stamp);
			pstmt.setString(6, bdaypojo.getCreateuser());
			
			result = pstmt.executeUpdate();
			
			if(result!=0){
			
			String messageTo[]=bdaypojo.getBdayperson().split(",");
			
			pstmt1 = conn.prepareStatement(CommunicateUtilConstants.CREATE_BDAY1);
			
			for(int i=0;i<messageTo.length;i++){
				
				pstmt1.setString(1, str);
				pstmt1.setString(2,messageTo[i]);
				pstmt1.setTimestamp(3, time_stamp);
				pstmt1.setString(4, bdaypojo.getCreateuser());
				
				result1 = pstmt1.executeUpdate();
				
			}
			
			}
			
			if(result1>0){
				
				result_Status="Created Successfully";
			}else{
				
				result_Status="Creation failed";
			}
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : createBdayDetailsDao  Ending");

		return result_Status;
	}

	
	
	
	public ArrayList<UpcomingBdayVo> getBdayListDetailsDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getBdayListDetailsDao Starting");

		ArrayList<UpcomingBdayVo> bdaylist = new ArrayList<UpcomingBdayVo>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection connection = null;
		
		try {

			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(CommunicateUtilConstants.GET_ALL_BDAY_WISHES);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				UpcomingBdayVo bdayvo = new UpcomingBdayVo();

			
			

				String person = rs.getString("STUDENT_ADM_NO");
				

				if (person.contains("TEA")) {
					
					bdayvo.setBdayid(rs.getString("DOB_CODE"));
					bdayvo.setBdayDate(rs.getString("DOB_DATE"));
					bdayvo.setContent(rs.getString("DOB_CONTENT"));
					
					
				
	
						pstmt1 = connection
								.prepareStatement(CommunicateUtilConstants.GET_TEACHER_BDAY_WISHES);
						
						
						pstmt1.setString(1, person);
						
						
						
						rs1 = pstmt1.executeQuery();
						
						while(rs1.next()){
							
						
							
							bdayvo.setBdayperson(rs1.getString("teachername"));
							bdayvo.setClassname("-");
							bdayvo.setSectionname("-");
						
						
						}
						
				}
				
				else if(person.contains("STU")){
					
					bdayvo.setBdayid(rs.getString("DOB_CODE"));
					bdayvo.setBdayDate(rs.getString("DOB_DATE"));
					bdayvo.setContent(rs.getString("DOB_CONTENT"));
					
						pstmt2 = connection.prepareStatement(CommunicateUtilConstants.GET_STUDENT_BDAY_WISHES);
						
						pstmt2.setString(1, person);
						
						rs2 = pstmt2.executeQuery();
						
						while(rs2.next()){
							
						
							
							bdayvo.setBdayperson(rs2.getString("studentname"));
							bdayvo.setClassname(rs2.getString("classdetails_name_var"));
							bdayvo.setSectionname(rs2.getString("classsection_name_var"));
							
						
							
							
							
						}
						
				}

				
				
				bdaylist.add(bdayvo);
				
			} 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace(); 
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getBdayListDetailsDao  Ending");

		return bdaylist;
	}

	
	
	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsDao() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getMeetingListDetailsDao Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection connection = null;
		
		
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(CommunicateUtilConstants.GET_ALL_MEETING_DETAILS);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
				
				
				String teacherid = rs.getString("TeacherId");
				String studentid = rs.getString("StudentId");
				
				if (teacherid.contains("TEA")) {
					
					meetvo.setMeetingid(rs.getString("meetingid"));
					meetvo.setMeetingDate(rs.getString("meetingdate"));
					meetvo.setStartTime(rs.getString("starttime"));
					meetvo.setEndTime(rs.getString("endtime"));
					meetvo.setTitle(rs.getString("title"));
					meetvo.setVenueid(rs.getString("venuedetails"));
					meetvo.setSubjectName(rs.getString("subjectname"));
					
					pstmt1 = connection
							.prepareStatement(CommunicateUtilConstants.GET_TEACHER_MEETING);
					
					
					pstmt1.setString(1, teacherid);
					
					rs1 = pstmt1.executeQuery();
					
					while(rs1.next()){
						
						meetvo.setMeetingwith(rs1.getString("FirstName"));
						meetvo.setClassname("-");
						meetvo.setSectionname("-");
						
					}
					
					
				}
				
				
				else if(studentid.contains("STU")){
					
					meetvo.setMeetingid(rs.getString("meetingid"));
					meetvo.setMeetingDate(rs.getString("meetingdate"));
					meetvo.setStartTime(rs.getString("starttime"));
					meetvo.setEndTime(rs.getString("endtime"));
					meetvo.setTitle(rs.getString("title"));
					meetvo.setVenueid(rs.getString("venuedetails"));
					meetvo.setSubjectName(rs.getString("subjectname"));
					
					pstmt2 = connection
							.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);
					
					pstmt2.setString(1, studentid);
					
					rs2 = pstmt2.executeQuery();
					
					while(rs2.next()){
						
						meetvo.setMeetingwith(rs2.getString("student_fname_var"));
						meetvo.setClassname(rs2.getString("classdetails_name_var"));
						meetvo.setSectionname(rs2.getString("classsection_name_var"));
						
					}
					
					
					
				}
				
				meetinglist.add(meetvo);
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : getMeetingListDetailsDao  Ending");

		return meetinglist;
	}

	
	public RemarksVo editRemarkDetailsDao(RemarksVo remarkvo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : editRemarkDetailsDao Starting");
		
		
		RemarksVo revo = new RemarksVo();
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn
					.prepareStatement(CommunicateUtilConstants.EDIT_STUDENT_REMARK);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CommunicationSettingsDaoImpl : editRemarkDetailsDao  Ending");
		return null;
	}
	
	
	
	
	
	
	
	
	
	

}
