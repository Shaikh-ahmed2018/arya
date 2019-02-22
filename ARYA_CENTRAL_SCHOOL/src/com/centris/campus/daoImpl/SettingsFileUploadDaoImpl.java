package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;


public class SettingsFileUploadDaoImpl {
	private static Logger logger = Logger.getLogger(SettingsFileUploadDaoImpl.class);

	public Set<studentExcelUploadVo> insertExamReport(List<StudentExcelUploadPojo> successlist,
			Set<studentExcelUploadVo> failurelist, Connection connection) throws SQLException {

		Set<studentExcelUploadVo> failurelistOnDiompl = new LinkedHashSet<studentExcelUploadVo>();
		
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		
		try{
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection();
		
			for(int i=0;i<successlist.size();i++){
				studentExcelUploadVo uploadClass = new studentExcelUploadVo();
				System.out.println("======="+successlist.size());
				System.out.println("======="+i);
				conn.setAutoCommit(false);

				studentExcelUploadVo vo = new studentExcelUploadVo();
				
				vo.setStudentName(successlist.get(i).getStudentName());
				vo.setStudentId(successlist.get(i).getStudentId());
				vo.setStudentIdNo(successlist.get(i).getStudentIdNo());
				vo.setSchoolName(successlist.get(i).getSchoolName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setAcademicYear(successlist.get(i).getAcademicYear());
				vo.setAccyearId(successlist.get(i).getAccyearId());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setSectionId(successlist.get(i).getSectionId());
				vo.setSectionName(successlist.get(i).getSectionName());
				vo.setExamId(successlist.get(i).getExamId());
				vo.setExamName(successlist.get(i).getExamName());
				vo.setExamCode(successlist.get(i).getExamCode());
				vo.setExamType(successlist.get(i).getExamType());
				vo.setStartdate(successlist.get(i).getStartdate());
				vo.setEnddate(successlist.get(i).getEnddate());
				vo.setSubjectName(successlist.get(i).getSubjectName());
				vo.setAttandance(successlist.get(i).getAttandance());
				vo.setSubjectId(successlist.get(i).getSubjectId());
				vo.setTestMaximumMarks(successlist.get(i).getTestMaximumMarks());
				vo.setTestScoredMarks(successlist.get(i).getTestScoredMarks());
				vo.setNotebookMaximumMarks(successlist.get(i).getNotebookMaximumMarks());
				vo.setNotebookScoredMarks(successlist.get(i).getNotebookScoredMarks());
				vo.setSubjectMaximumMarks(successlist.get(i).getSubjectMaximumMarks());
				vo.setSubjectScoredMarks(successlist.get(i).getSubjectScoredMarks());
				vo.setWorkEducation(successlist.get(i).getWorkEducation());
				vo.setArtEducation(successlist.get(i).getArtEducation());
				vo.setHealthEducation(successlist.get(i).getHealthEducation());
				vo.setDescipline(successlist.get(i).getDescipline());
				vo.setRemarks(successlist.get(i).getRemarks());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_studentwise_mark_details` WHERE `Academic_yearid`=? AND `stu_id`=? AND `exam_id`=? AND`classid`=? AND`sectionid`=? AND `sub_id`=? AND `location_id`=?");
				pstm1.setString(1,vo.getAccyearId());
				pstm1.setString(2,vo.getStudentId());
				pstm1.setString(3,vo.getExamId());
				pstm1.setString(4,vo.getClassId());
				pstm1.setString(5,vo.getSectionId());
				pstm1.setString(6,vo.getSubjectId());
				pstm1.setString(7,vo.getLocationId());
				
				System.out.println("check status"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					System.out.println("check statusdsfsdfgs");
					uploadClass.setAcademicYear(vo.getAcademicYear());
					uploadClass.setStudentName(vo.getStudentName());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setSectionName(vo.getSectionName());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSchoolName(vo.getSchoolName());
					uploadClass.setStudentIdNo(vo.getStudentIdNo());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setExamCode(vo.getExamCode());
					uploadClass.setReason("Duplicate Data upload ");
					failurelist.add(uploadClass);
					
				}else {
					
					if(vo.getExamType().equalsIgnoreCase("EMT2") || vo.getExamType().equalsIgnoreCase("EMT3")){
						System.out.println("INSERT TO CO TABLE$$$$$$$$$$$$$########################################"+vo.getExamType());
						pstm1 = conn.prepareStatement("INSERT INTO `campus_student_co_scholastic_areas`(`exam_id`,`student_id`,`location_id`,`acc_yearid`,`class_id`,`section_id`,`work_edu_grade`,`art_edu_grade`,`health_edu_grade`,`discipline_grade`,remarks)VALUES(?,?,?,?,?,?,?,?,?,?,?)");
						pstm1.setString(1,vo.getExamId());
						pstm1.setString(2, vo.getStudentId());
						pstm1.setString(3, vo.getLocationId());
						pstm1.setString(4, vo.getAccyearId());
						pstm1.setString(5, vo.getClassId());
						pstm1.setString(6, vo.getSectionId());
						pstm1.setString(7, vo.getWorkEducation());
						pstm1.setString(8, vo.getArtEducation());
						pstm1.setString(9, vo.getHealthEducation()); // max_notebook_marks
						pstm1.setString(10, vo.getDescipline());
						pstm1.setString(11, vo.getRemarks());
					
						System.out.println("insert data::::::::: BangloreBangloreBanglore"+pstm1);
						 pstm1.executeUpdate();
						 
						 pstmt = conn.prepareStatement("INSERT INTO `campus_studentwise_mark_details`"
									+ "(`Stu_mark_id`,`Academic_yearid`,`stu_id`,`exam_id`,`admission_no`,"
									+ "`classid`,`sectionid`,`exam_code`,`exam_name`,`subject_code`,`subject_name`,`attendance_status`,"
	+ "`total_marks`,`scored_marks`,`sub_id`,`location_id`,`notebook_marks`,`subject_enrich_marks`,"
	+ "`max_notebook_marks`,`max_subjenrich_marks`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					}
					else{
						System.out.println("Inside Else loop");
						pstmt = conn.prepareStatement("INSERT INTO `campus_studentwise_mark_details`"
								+ "(`Stu_mark_id`,`Academic_yearid`,`stu_id`,`exam_id`,`admission_no`,"
								+ "`classid`,`sectionid`,`exam_code`,`exam_name`,`subject_code`,`subject_name`,`attendance_status`,"
+ "`max_periodic_marks`,`periodic_test`,`sub_id`,`location_id`,`notebook_marks`,`subject_enrich_marks`,"
+ "`max_notebook_marks`,`max_subjenrich_marks`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						
					}
					
						pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_studentwise_mark_details"));
						pstmt.setString(2, vo.getAccyearId());
						pstmt.setString(3, vo.getStudentId());
						pstmt.setString(4, vo.getExamId());
						pstmt.setString(5, vo.getStudentIdNo());
						pstmt.setString(6, vo.getClassId());
						pstmt.setString(7, vo.getSectionId());
						pstmt.setString(8, vo.getExamCode());
						pstmt.setString(9,vo.getExamName());
						pstmt.setString(10,vo.getSubjectId());
						pstmt.setString(11,vo.getSubjectName());
						pstmt.setString(12,vo.getAttandance());
						pstmt.setString(13, vo.getTestMaximumMarks());
						pstmt.setString(14, vo.getTestScoredMarks());
						pstmt.setString(15, vo.getSubjectId());
						pstmt.setString(16, vo.getLocationId());
						if(vo.getNotebookScoredMarks() == "" || vo.getNotebookScoredMarks().equalsIgnoreCase(null)){
							pstmt.setString(17, "0");
						}else{
							pstmt.setString(17, vo.getNotebookScoredMarks());
						}
						if(vo.getSubjectScoredMarks() == "" || vo.getSubjectScoredMarks().equalsIgnoreCase(null)){
							pstmt.setString(18, "0");
						}else{
							pstmt.setString(18, vo.getSubjectScoredMarks());
						}
						if(vo.getNotebookMaximumMarks() == "" || vo.getNotebookMaximumMarks().equalsIgnoreCase(null)){
							pstmt.setString(19, "0");
						}else{
							pstmt.setString(19, vo.getNotebookMaximumMarks());
						}
						if(vo.getSubjectMaximumMarks() == "" || vo.getSubjectMaximumMarks().equalsIgnoreCase(null)){
							pstmt.setString(20, "0");
						}else{
							pstmt.setString(20, vo.getSubjectMaximumMarks());
						}
						//pstmt.setString(19, vo.getNotebookMaximumMarks()); // max_notebook_marks
						//pstmt.setString(20, vo.getSubjectMaximumMarks());
					
						System.out.println("insert data::::::::: BangloreBangloreBanglore"+pstmt);
						int result = pstmt.executeUpdate();
						
						if(result > 0)
							//HelperClass.recordLog_Activity(log_audit_session,"Settings","StreamExcelUpload","Insert",pstmt.toString());
						conn.commit();
					}
					}
			System.out.println("jhjh="+failurelist.size());
     		}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstm1!=null && (!pstm1.isClosed())){
					pstm1.close();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return failurelistOnDiompl;
	}
	
	
	
	
	
	
	
	
	

	public Set<classVo> insertSubjectDetails(List<ClassPojo> successlist, Set<classVo> failurelist,
			Connection connection, String userId, String log_audit_session) {


		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		Map<String, String> subjectdetails = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null,pstmt=null,pstm3=null,pstm4=null,pstm5=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
		
		
		try{
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection();
		
			for(int i=0;i<successlist.size();i++){
				
				System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);

				IDGenerator id = new IDGenerator();
				classVo vo = new classVo();
				
		
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setSpecializationName(successlist.get(i).getSpecializationName());
				vo.setSpecializationId(successlist.get(i).getSpecializationId());
				vo.setSubjectName(successlist.get(i).getSubjectName());
				vo.setSubjectCode(successlist.get(i).getSubjectCode());
				vo.setIsLanguage(successlist.get(i).getIsLanguage());
				vo.setTotalMarks(successlist.get(i).getTotalMarks());
				vo.setPassmarks(successlist.get(i).getPassmarks());
				vo.setDescription(successlist.get(i).getDescription());
				
				
				if(successlist.get(i).getTotalMarks()=="" ||successlist.get(i).getTotalMarks()==null || successlist.get(i).equals("-")){
					vo.setTotalMarks("0");
				}
				if(successlist.get(i).getPassmarks()=="" || successlist.get(i).getPassmarks()==null || successlist.get(i).equals("-")){
				vo.setPassmarks("0");
				}
				if(successlist.get(i).getIsLanguage()=="" || successlist.get(i).getIsLanguage()==null || successlist.get(i).equals("-")){
					vo.setIsLanguage("N");
				}
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_subject` WHERE `subjectName`=? AND `classid`=? AND `locationId`=? AND `specialization`=? AND `Sub_Code`=? AND `status`='active'");
				pstm1.setString(1,vo.getSubjectName());
				pstm1.setString(2, vo.getClassId());
				pstm1.setString(3,vo.getLocationId());
				pstm1.setString(4, vo.getSpecializationId());	
				pstm1.setString(5, vo.getSubjectCode());
				System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setClassId(vo.getClassId());
					
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setSpecializationId(vo.getSpecializationId());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSubjectCode(vo.getSubjectCode());
					uploadClass.setIsLanguage(vo.getIsLanguage());
					uploadClass.setReason("Records already Exists");
					failurelist.add(uploadClass);
				}
				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_subject`(`subjectID`,`subjectName`,`classid`,`decription`,`totalMarks`,`passMarks`,`Sub_Code`,`locationId`,`specialization`,`isLanguage`,`createdby`,`ctrate time`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_subject"));
						pstmt.setString(2, vo.getSubjectName());
						pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getDescription());
						pstmt.setString(5, vo.getTotalMarks());
						pstmt.setString(6, vo.getPassmarks());
						pstmt.setString(7, vo.getSubjectCode());
						pstmt.setString(8, vo.getLocationId());
						pstmt.setString(9, vo.getSpecializationId());
						pstmt.setString(10, vo.getIsLanguage());
						pstmt.setString(11,userId);
						pstmt.setTimestamp(12,HelperClass.getCurrentTimestamp());
						
						
						System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result=pstmt.executeUpdate();
						if(result>0)
							//HelperClass.recordLog_Activity(log_audit_session,"Settings","subjectExcelUpload","Insert",pstmt.toString());
						conn.commit();
					}
				}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		return failurelistOnDiompl;
	}










	public Set<classVo> insertHoliday(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String accyear, String log_audit_session) {
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null,pstmt=null,pstm3=null,pstm4=null,pstm5=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
		
		try{
			System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection();
		
			for(int i=0;i<successlist.size();i++){
				
				System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);

				IDGenerator id = new IDGenerator();
				classVo vo = new classVo();
				
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setHolidaytype(successlist.get(i).getHolidaytype());
				vo.setHolidayDate(successlist.get(i).getHolidayDate());
				vo.setHolidayName(successlist.get(i).getHolidayName());
				vo.setAccyearId(successlist.get(i).getAccyearId());
				vo.setAccyearName(successlist.get(i).getAccyearName());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_holidaymaster` WHERE `HOLIDAY_DATE`=? AND `HOLIDAY_NAME`=? AND `HOLIDAY_TYPE`=? AND `LOC_ID`=? AND `CURRENT_YEAR`=?");
				pstm1.setString(1,HelperClass.convertUIToDatabase(vo.getHolidayDate()));
				pstm1.setString(2, vo.getHolidayName());
				pstm1.setString(3,vo.getHolidaytype());
				pstm1.setString(4, vo.getLocationId());
				pstm1.setString(5,vo.getAccyearId());
				System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setHolidayName(vo.getHolidayName());
					uploadClass.setHolidayDate(vo.getHolidayDate());
					uploadClass.setHolidaytype(vo.getHolidaytype());
					uploadClass.setAccyearId(vo.getAccyearId());
					uploadClass.setAccyearName(HelperClass.getAcademicYearFace(vo.getAccyearId()));
					uploadClass.setReason("Holiday setup already exists with '"+HelperClass.getAcademicYearFace(vo.getAccyearId())+"");
					failurelist.add(uploadClass);
				}
				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_holidaymaster`(`HOL_ID`,`HOLIDAY_DATE`,`WEEKDAY`,`HOLIDAY_NAME`,`HOLIDAY_TYPE`,`CURRENT_YEAR`,`CREATEDDATE`,`CREATEDBY`,`LOC_ID`)VALUES(?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_holidaymaster"));
						pstmt.setString(2, HelperClass.convertUIToDatabaseSettingReports(vo.getHolidayDate()));
					/*	pstmt.setString(3, HelperClass.getWeekDay(vo.getHolidayDate()));getWeekDaySettingReports*/
						pstmt.setString(3, HelperClass.getWeekDaySettingReports(vo.getHolidayDate()));
						pstmt.setString(4, vo.getHolidayName());
						pstmt.setString(5, vo.getHolidaytype());
						pstmt.setString(6, vo.getAccyearId());
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8,userId);
						pstmt.setString(9, vo.getLocationId());
						System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result=  pstmt.executeUpdate();
						if(result>0)
						//HelperClass.recordLog_Activity(log_audit_session,"Settings","SpecializationExcelUpload","Insert",pstmt.toString());
						//conn.commit();
						conn.commit();
					}
				}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		return failurelistOnDiompl;
	}
}
