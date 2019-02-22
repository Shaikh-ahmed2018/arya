package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadStudentXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadStudentXLSDaoImpl.class);

	public int checkEmpCountBeforeInsert() {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(UploadStudentXLSqlUtil.CHECK_BEFORINSERT_COUNT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl: Ending");

		return beforeInsertCount;
}

	
	public int checkStudentID(String studentId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_STUDENT_ID);
			ps_emp_count.setString(1, studentId);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);

				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkCategorycode(String category, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_CATEGORY_ID);
			ps_emp_count.setString(1, category);
			System.out.println("category = "+category);
			rs_emp_count = ps_emp_count.executeQuery();
			System.out.println("check_category_id "+ps_emp_count);
			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);

				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkClassCode(String classname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_ID);
			ps_emp_count.setString(1, classname);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);

				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public int checkSectionCode(String sectionname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_ID);
			ps_emp_count.setString(1, sectionname);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);

				return count;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

					ps_emp_count.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return 0;
	}

	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> successlist,Connection connection) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadEmpXSLDaoImpl : insertEmpXSL : Starting");
	
		Set<UploadStudentXlsVO> failurelistOnDiompl = new LinkedHashSet<UploadStudentXlsVO>();
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		ResultSet rs_emp_count=null;
		ResultSet rsCountStudentSection=null;
		String registrationNo = null;
		String categoryName=null;
		PreparedStatement pstmcount = null;
		PreparedStatement academicYearpre = null;
		PreparedStatement prclassName=null;
		PreparedStatement preParentReg=null;
		PreparedStatement preChildParentUpdate=null;
		PreparedStatement precategoryName=null;
		 PreparedStatement pstmclasscount =null;
		PreparedStatement prsectionName=null;
		PreparedStatement ps_student_add = null;
		PreparedStatement ps_emp_count= null;
		PreparedStatement ps_student_contact=null;
		PreparedStatement PScountStudentPerSection=null;
		PreparedStatement PSsectionStrength=null;
		Connection conn = null;
		
		String academicYear = "";
		String studentno = null;
		String relationship = "";
		String parentId="";
	
		try{
			
			    conn = JDBCConnection.getSeparateConnection();
			   
			    conn.setAutoCommit(false);

			
		    	ps_student_add = conn.prepareStatement(UploadStudentXLSqlUtil.INSERT_STUDENT);
				//academicYearpre = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR_BYID);
				pstmcount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_ACADEMIC_YEAR);
				prclassName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_NAME);
			    preParentReg = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_REG_EXCEL_UPLOAD);
			    preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);
			    //precategoryName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_STREAM);
			    //prsectionName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SECTION_NAME);
			    pstmclasscount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_CLASS);
				//countDuplicate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE);
			    ps_student_contact=conn.prepareStatement("INSERT INTO campus_students_contacts (studentId,emergencyNo,smsNo) VALUES(?,?,?)");
				PScountStudentPerSection=conn.prepareStatement(UploadStudentXLSqlUtil.COUNT_STUDENT_PER_SECTION);
				PSsectionStrength=conn.prepareStatement(UploadStudentXLSqlUtil.SECTION_STRENGTH);
				

				
			
			 for(int i=0;i<successlist.size();i++){
				 
				 
				 
				 
				 UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO();
					
					uploadStudentXSLVo.setStudentFirstName(successlist.get(i).getStudentFirstName()+" "+successlist.get(i).getStudentLastName());
					uploadStudentXSLVo.setStudentAdmissionNo(successlist.get(i).getStudentAdmissionNo());
					uploadStudentXSLVo.setStudentRegNo(successlist.get(i).getApplicationNo());
					uploadStudentXSLVo.setDateofJoin(successlist.get(i).getDateofJoin());
					
					uploadStudentXSLVo.setAcademicYear(successlist.get(i).getAcademicYear());
					uploadStudentXSLVo.setCategory(successlist.get(i).getStreamValue());
					uploadStudentXSLVo.setClassname(successlist.get(i).getCalssValue());
					/*System.out.println("=============diompl class===========");
						System.out.println(uploadStudentXSLVo.getClassname());
					
					System.out.println("=============diompl class===========");*/
					uploadStudentXSLVo.setSectionname(successlist.get(i).getSectionValue());
					
				 
				 	System.out.println("Inside FOR Loop");
				 	
				 	String studentidgenerator=new IDGenerator().getPrimaryKeyID("campus_student");
				 	Timestamp createdDate = HelperClass.getCurrentTimestamp();
				 /*	System.out.println("createdDate++++"+studentidgenerator);
				 	System.out.println("DOB: "+successlist.get(i).getDateofBirth());
				 	System.out.println("DOB: "+successlist.get(i).getDateofJoin());
				 	System.out.println("DOB: "+successlist.get(i).getDateofJoin().trim());*/
				 
					conn.setAutoCommit(false);
				 	
				 	String sectionId=successlist.get(i).getSectionname();
				 	String accyear = successlist.get(i).getAcademicYear();
				 	String school =  successlist.get(i).getSchoolName();
				 	//fetching section Strength
				 	PSsectionStrength.setString(1,sectionId);
				 	
				 	ResultSet rsSectionCount=PSsectionStrength.executeQuery();
				 	
				 	System.out.println(PSsectionStrength);
				 	rsSectionCount.next();
				 	int sectionStrength=rsSectionCount.getInt(1);
				 	
				 	
				 	//counting the current no of student in section
				 	PScountStudentPerSection.setString(1, sectionId);
				 	PScountStudentPerSection.setString(2,accyear);
				 	PScountStudentPerSection.setString(3,school);
				 	rsCountStudentSection=PScountStudentPerSection.executeQuery();
				 	
				 	rsCountStudentSection.next();
				 	int noOfSutdentInSection=rsCountStudentSection.getInt(1);
				 	
					if(noOfSutdentInSection>=sectionStrength){
						uploadStudentXSLVo.setReason("Seat is not avialable in section.");
						failurelistOnDiompl.add(uploadStudentXSLVo);
					}else{

						ResultSet rs = pstmcount.executeQuery();
						rs.next();
						int count = rs.getInt(1);
							
						pstmclasscount.setString(1, successlist.get(i).getStudClassId());
						ResultSet rsClass = pstmclasscount.executeQuery();
						
						rsClass.next();
						int classcount = rsClass.getInt(1);
						String studentRegNo = null;
						
						
						System.out.println("DIOMPL :: class count :: "+classcount);
						studentRegNo = StringUtilContants.STUDENT_REGISTRATION_NO + "-";
						if (classcount < 9) {
							int k = classcount;
							studentRegNo = studentRegNo + "000" + (++k);
						} else if (classcount < 99) {
							int k = classcount;
							studentRegNo = studentRegNo + "00" + (++k);
						} else if (classcount < 999) {
							int k = classcount;
							studentRegNo = studentRegNo + "0" + (++k);
						} else {
							int k = classcount;
							studentRegNo = studentRegNo + (++k);
						}

						
						academicYear=successlist.get(i).getAcademicYear();
						System.out.println("Academic Year: "+academicYear);
						registrationNo = StringUtilContants.STUDENT_ADMISSION_NO + "-";
						
						if (count < 9) {
							int j = count;
							registrationNo = registrationNo + "000" + (++j) + "/"+ academicYear;
						} else if (count < 99) {
							int j = count;
							registrationNo = registrationNo + "00" + (++j) + "/"+ academicYear;
						} else if (count < 999) {
							int j = count;
							registrationNo = registrationNo + "0" + (++j) + "/"+ academicYear;
						} else {
							int j = count;
							registrationNo = registrationNo + (++j) + "/" + academicYear;
						}
						
						String dob1=successlist.get(i).getDateofBirth();
						Date today=HelperClass.getCurrentSqlDate();
						List<String> monthList=HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(dob1), today.toString());
						int ageOnDays=monthList.size();
						String ageOnYears=""+ageOnDays/365;
						
						System.out.println("studentno::"+studentno);
						System.out.println("Registration no::"+registrationNo);
						String academicYearId=getAcademicYearId(successlist.get(i).getAcademicYear(),connection);   
						
						//student registration
						
		//student_id_int,student_rollno,student_admissionno_var,fms_classstream_id_int,fms_acadamicyear_id_int,classdetail_id_int,
					   	ps_student_add.setString(1,studentidgenerator);
					 	ps_student_add.setString(2, studentRegNo); //For roll number
					 	ps_student_add.setString(3, successlist.get(i).getStudentAdmissionNo().trim()); //For addmission Number
					 	/*ps_student_add.setString(4, successlist.get(i).getCategory().trim());  */ //stream
					 	ps_student_add.setString(4, academicYearId);
					 	/*ps_student_add.setString(6, successlist.get(i).getClassname().trim());*/
					 
					 	
		////classsection_id_int,student_regno_var,student_fname_var,student_lname_var,student_dob_var,student_gender_var,		 	
					/*	ps_student_add.setString(7, successlist.get(i).getSectionname().trim());*/
					 	ps_student_add.setString(5, registrationNo); // For registartion number
					 	ps_student_add.setString(6, successlist.get(i).getStudentFirstName().trim());
					    ps_student_add.setString(7, successlist.get(i).getStudentLastName().trim());
						ps_student_add.setString(8, HelperClass.convertUIToDatabase((successlist.get(i).getDateofBirth().trim())));
						String genderconvert = successlist.get(i).getGender().trim().toLowerCase();
						String gender = StringUtils.capitalize(genderconvert);
						ps_student_add.setString(9, gender);
	//student_bloodgroup_var,student_age_int,student_doj_var,student_religion_var,student_nationality_var,student_promotionstatus,				
						ps_student_add.setString(10, successlist.get(i).getBloodGroup());
						
						
						
						//ps_student_add.setString(14, successlist.get(i).getAge().trim());
						ps_student_add.setString(11,ageOnYears.trim());
						ps_student_add.setDate(12, HelperClass.getSqlDateFromDdMmYyFormat(successlist.get(i).getDateofJoin().trim()));
						ps_student_add.setString(13, successlist.get(i).getReligion());
						
						String nationalityconvert = successlist.get(i).getNationality().trim().toLowerCase();
						String nationality = StringUtils.capitalize(nationalityconvert);
						ps_student_add.setString(14, nationality);
						/*ps_student_add.setString(15,"");*/ //promoted status

	//student_physicallychallenged,student_identificationmarks_var,student_siblingId,student_status_var,student_prehistory_var,			
				
						ps_student_add.setString(15, successlist.get(i).getPhysicallyChallenged().trim());
						ps_student_add.setString(16, successlist.get(i).getIdentificationMarks());
						ps_student_add.setString(17,successlist.get(i).getStudentSibilingIdInt());
						ps_student_add.setString(18, "active");
						ps_student_add.setString(19, successlist.get(i).getMedicalhistory());

	//,student_remarks_var,createuser,createdate,,student_caste,student_application_no,
						ps_student_add.setString(20, successlist.get(i).getRemarks());
						ps_student_add.setString(21, successlist.get(i).getCreateUser());
						ps_student_add.setTimestamp(22, createdDate);
						ps_student_add.setString(23, successlist.get(i).getCaste());
						ps_student_add.setString(24, successlist.get(i).getApplicationNo());
						
	//isTransport,,,,TransportType,StageId,,physicallychallenged_reason				
						/*ps_student_add.setString(29, successlist.get(i).getTransport().trim());
						ps_student_add.setString(30, successlist.get(i).getTranscategory().trim());
						ps_student_add.setString(31, successlist.get(i).getTranslocation().trim());*/
						ps_student_add.setString(25, successlist.get(i).getPhysicalchalreason());
	// newly added fields
						/*if(successlist.get(i).getSpecilization().trim()==null || successlist.get(i).getSpecilization().equalsIgnoreCase("")){
							ps_student_add.setString(33, "-");
						}else{
							ps_student_add.setString(33, successlist.get(i).getSpecilization().trim());
						}*/
						
						ps_student_add.setString(26, successlist.get(i).getSchoolName().trim());
						ps_student_add.setString(27, successlist.get(i).getIsParentsGuardianWorking().trim());
						ps_student_add.setString(28, successlist.get(i).getTransferCertificateNo().trim());
						/*ps_student_add.setString(37, successlist.get(i).getRoute().trim());*/
						
						String mediumconvert = successlist.get(i).getMedium().trim().toLowerCase();
						String medium = StringUtils.capitalize(mediumconvert);
						ps_student_add.setString(29, medium);
						
						ps_student_add.setString(30, successlist.get(i).getCasteCategory());
						ps_student_add.setString(31, successlist.get(i).getAadharNo().trim());
						
						String mothertongueconvert = successlist.get(i).getMotherTounge().trim().toLowerCase();
						String mothertongue = StringUtils.capitalize(mothertongueconvert);
						ps_student_add.setString(32, mothertongue);
						
						ps_student_add.setString(33, successlist.get(i).getWorkingParentsGuardianName().trim());
						ps_student_add.setString(34, successlist.get(i).getDob_in_words());
						
					
						System.out.println("ps_student_add"+ps_student_add);
						
						ps_student_add.executeUpdate();
						
						//To store student class details
						
						PreparedStatement scpstmt = conn.prepareStatement(UploadStudentXLSqlUtil.STUDENT_CLASS_REGISTRATION);

						scpstmt.setString(1,studentidgenerator.trim());
						scpstmt.setString(2,academicYearId);
						scpstmt.setString(3,successlist.get(i).getCategory());
						scpstmt.setString(4,successlist.get(i).getClassname());
						scpstmt.setString(5,successlist.get(i).getSectionname());
						if(successlist.get(i).getSpecilization()==null || successlist.get(i).getSpecilization().equalsIgnoreCase("")){
							scpstmt.setString(6, "-");
						}else{
							scpstmt.setString(6, successlist.get(i).getSpecilization());
						}
						scpstmt.setString(7,successlist.get(i).getCreateUser());
						scpstmt.setTimestamp(8,createdDate);
						scpstmt.setString(9,successlist.get(i).getSchoolName());
						scpstmt.setString(10,successlist.get(i).getSchoolName());
						scpstmt.setString(11,successlist.get(i).getRollno());
						scpstmt.setString(12,successlist.get(i).getStuimgurl());
						scpstmt.setString(13,successlist.get(i).getHousename());
						scpstmt.setString(14,successlist.get(i).getSecondLanguage());
						scpstmt.setString(15,successlist.get(i).getThirdLanguage());
						
						scpstmt.executeUpdate();
						scpstmt.close();
						
						//to store house details
						
						PreparedStatement pstmthouse = conn.prepareStatement(UploadStudentXLSqlUtil.STUDENT_HOUSE_REGISTRATION);
						pstmthouse.setString(1,studentidgenerator.trim());
						pstmthouse.setString(2,successlist.get(i).getClassname());
						pstmthouse.setString(3,successlist.get(i).getSectionname());
						pstmthouse.setString(4,successlist.get(i).getHousename());
						pstmthouse.setString(5,academicYearId);
						pstmthouse.setString(6,successlist.get(i).getSchoolName());
						pstmthouse.executeUpdate();
						pstmthouse.close();
						//To store student transport details
						String istransport = null;
						if(successlist.get(i).getTransport().equalsIgnoreCase("No")){
							istransport = "N";
						}
						else if(successlist.get(i).getTransport().equalsIgnoreCase("Yes")){
							istransport = "Y";
						}
						
						PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION);

						stpstmt.setString(1, studentidgenerator.trim());
						stpstmt.setString(2, academicYearId);
						stpstmt.setString(3,istransport);
						stpstmt.setString(4,successlist.get(i).getTranscategory());
						stpstmt.setString(5,successlist.get(i).getTranslocation());
						stpstmt.setString(6,successlist.get(i).getRoute());
						stpstmt.setString(7,successlist.get(i).getCreateUser());
						stpstmt.setTimestamp(8, createdDate);
						stpstmt.setString(9,successlist.get(i).getSchoolName());

						stpstmt.executeUpdate();
						stpstmt.close();	

						
					// Parent Registration	
							parentId = IDGenerator.getPrimaryKeyID("campus_parents");
						
							
							preParentReg.setString(1, parentId);
							preParentReg.setString(2, successlist.get(i).getFatherName());
							preParentReg.setString(3, successlist.get(i).getFatherMobileNo());
							preParentReg.setString(4, successlist.get(i).getFatheroccupation());
							preParentReg.setString(5, successlist.get(i).getFatherPanNO());
							if(successlist.get(i).getFatherAnnualIncome() != null && !successlist.get(i).getFatherAnnualIncome().equalsIgnoreCase("")){
								System.out.println(":::::::::fatherincome");
								preParentReg.setDouble(6, Double.parseDouble(successlist.get(i).getFatherAnnualIncome()));
							}else{
								preParentReg.setDouble(6,0.0);
							}
							preParentReg.setString(7, successlist.get(i).getFatheremailId());
							preParentReg.setString(8, successlist.get(i).getFatherQualification());
							preParentReg.setString(9, successlist.get(i).getMotherName());
							preParentReg.setString(10, successlist.get(i).getMotherMobileNo());
							preParentReg.setString(11, successlist.get(i).getMotheroccupation());
							preParentReg.setString(12, successlist.get(i).getMotherPanNo());
							
							if(successlist.get(i).getMotherAnnualIncome() != null && !successlist.get(i).getMotherAnnualIncome().equalsIgnoreCase("")){
								preParentReg.setDouble(13, Double.parseDouble(successlist.get(i).getMotherAnnualIncome()));
							}else{
								preParentReg.setDouble(13,0.0);
							}
							preParentReg.setString(14, successlist.get(i).getMotheremailId());
							preParentReg.setString(15, successlist.get(i).getMotherQualification());
							
							preParentReg.setString(16, successlist.get(i).getGuardianName());
							preParentReg.setString(17, successlist.get(i).getGuardianMobileNo());
							preParentReg.setString(18, successlist.get(i).getGuardianOccupation());
							preParentReg.setString(19, successlist.get(i).getGuardianPanNo());
							
							if(successlist.get(i).getGuardianAnnualIncome() != null && !successlist.get(i).getGuardianAnnualIncome().equalsIgnoreCase("")){
								preParentReg.setDouble(20, Double.parseDouble(successlist.get(i).getGuardianAnnualIncome()));
							}else{
								preParentReg.setDouble(20,0.0);
							}
							preParentReg.setString(21, successlist.get(i).getGuardianemailId());
							preParentReg.setString(22, successlist.get(i).getGuardianQualification());
							
							preParentReg.setString(23, successlist.get(i).getPermanentAddress());
							preParentReg.setString(24, successlist.get(i).getPresentAddress());
							
							preParentReg.setString(25, successlist.get(i).getCreateUser());
							preParentReg.setTimestamp(26, HelperClass.getCurrentTimestamp());
							preParentReg.setString(27, successlist.get(i).getFatherDesignation());
							preParentReg.setString(28, successlist.get(i).getFatherOffAddress());
							preParentReg.setString(29, successlist.get(i).getMotherDesignation());
							preParentReg.setString(30, successlist.get(i).getMotherOffAddress());
							preParentReg.setString(31, successlist.get(i).getGuardianDesignation());
							preParentReg.setString(32, successlist.get(i).getGuardianOffAddress());

							System.out.println("preParentReg:::"+preParentReg);
							preParentReg.execute();
							
							
							//Parent Child Relation
							
							if (successlist.get(i).getPrimaryPerson().equalsIgnoreCase("father")) {
								
								relationship = "father";
								
							} else if (successlist.get(i).getPrimaryPerson().equalsIgnoreCase("mother")) {
								
								relationship = "mother";
								
							} else {

								relationship = "guardian";
								
							}
							
							preChildParentUpdate.setString(1, relationship);
							preChildParentUpdate.setString(2, parentId.trim());
							preChildParentUpdate.setString(3, studentidgenerator);
							
							preChildParentUpdate.executeUpdate();
							
							ps_student_contact.setString(1, studentidgenerator);
							ps_student_contact.setString(2, successlist.get(i).getEmegencyNo());
							ps_student_contact.setString(3, successlist.get(i).getSmsNo());
							ps_student_contact.executeUpdate();
							System.out.println("last prepare statement ");

							conn.commit();
					}
			 }
		 }catch (SQLException sqle) {
		       	sqle.printStackTrace();
			   logger.error(sqle.getMessage(),sqle);
		    } 
          catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			
			try {if (ps_student_add != null && (!ps_student_add.isClosed())) {

				ps_student_add.close();
			}

			if (rs_emp_count != null && (!rs_emp_count.isClosed())) {

				rs_emp_count.close();
			}

			if (ps_emp_count != null && (!ps_emp_count.isClosed())) {

				ps_emp_count.close();
			}

			if (pstmcount != null && (!pstmcount.isClosed())) {

				pstmcount.close();
			}

			if (pstmclasscount != null && (!pstmclasscount.isClosed())) {

				pstmclasscount.close();
			}

			if (precategoryName != null && (!precategoryName.isClosed())) {

				precategoryName.close();
			}

			if (preChildParentUpdate != null
					&& (!preChildParentUpdate.isClosed())) {

				preChildParentUpdate.close();
			}

			if (preParentReg != null && (!preParentReg.isClosed())) {

				preParentReg.close();
			}

			if (prsectionName != null && (!prsectionName.isClosed())) {

				prsectionName.close();
			}

			if (prclassName != null && (!prclassName.isClosed())) {

				prclassName.close();
			}

			if (academicYearpre != null && (!academicYearpre.isClosed())) {

				academicYearpre.close();
			}
			if(PScountStudentPerSection !=null && (!PScountStudentPerSection.isClosed())){
				PScountStudentPerSection.close();
			}
			if(PSsectionStrength !=null && (!PSsectionStrength.isClosed())){
				PSsectionStrength.close();
			}
			if(conn !=null && (!conn.isClosed())){
				conn.close();
			}
			

           } catch (SQLException sqle) {
			       	sqle.printStackTrace();
				   logger.error(sqle.getMessage(),sqle);
			     } catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			
		        }
		
		
		       }
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : insertEmpXSL : Ending");
		
		
		return failurelistOnDiompl;
	}


	public String getStreamId(String category, String schoolLocationId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String steramId=null;

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_STREAM_ID);
			psmt.setString(1, schoolLocationId);
			psmt.setString(2, category);
			rs = psmt.executeQuery();
			if (rs.next()) {
				steramId=rs.getString("classstream_id_int");
			}
			else{
				steramId="";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return steramId;
	}


	public String getClassId(String classname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String classId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID);
			psmt.setString(1, classname);
			rs = psmt.executeQuery();

			if (rs.next()) {
				classId=rs.getString("classdetail_id_int");
			}
			else{
				classId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return classId;
	}


	public String getSectionId(String sectionname, String classId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sectionId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SECTION_ID);
			psmt.setString(1, sectionname);
			psmt.setString(2, classId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				sectionId=rs.getString("classsection_id_int");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return sectionId;
	}


	public String getReligionId(String religion, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String religionId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_RELIGION_ID);
			psmt.setString(1, religion);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				religionId=rs.getString("religionId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return religionId;
	}


	public String getSiblingId(String sibilingadminno, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String siblingId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SIBLING_ID);
			psmt.setString(1, sibilingadminno);
			rs = psmt.executeQuery();

			while (rs.next()) {
				siblingId=rs.getString("student_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return siblingId;
	}


	public String getCasteId(String caste, String religionId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_ID);
			psmt.setString(1, caste);
			psmt.setString(2, religionId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteId=rs.getString("casteId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return casteId;
	}


	public String getTransportTypeId(String transcategory, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String trasportTypeId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_ID);
			psmt.setString(1, transcategory);
			rs = psmt.executeQuery();

			while (rs.next()) {
				trasportTypeId=rs.getString("type_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return trasportTypeId;
	}


	public String getTransportLocationId(String translocation,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String transportLocationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_LOCATION_ID);
			psmt.setString(1, translocation);
			rs = psmt.executeQuery();
			System.out.println("transport location="+psmt);
			while (rs.next()) {
				transportLocationId=rs.getString("stage_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return transportLocationId;
	}


	public String getRouteId(String route, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String routeId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_ROUTE_ID);
			psmt.setString(1, route);
			System.out.println("Route Id: SQL::  "+psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				routeId=rs.getString("RouteCode");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return routeId;
	}


	public String getSpecilizationId(String specilization, String stream, String classId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String specilizationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_SPECILIZATINON_ID);
			psmt.setString(1, specilization);
			psmt.setString(2, stream);
			psmt.setString(3, classId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				specilizationId=rs.getString("Specialization_Id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return specilizationId;
	}


	public String getCasteCategoryId(String casteCategory, String casteId, String religionId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteCategoryId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_CATEGORY_ID);
			psmt.setString(1, casteCategory);
			psmt.setString(2, religionId);
			psmt.setString(3,casteId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteCategoryId=rs.getString("castCatId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return casteCategoryId;
	}


	public String getSchoolLocationId(String schoolName, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String schoolNameId=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_SCHOOL_LOCATION_ID);
			psmt.setString(1, schoolName);
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				schoolNameId=rs.getString("Location_Id");
			}
			else{
				schoolNameId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return schoolNameId;
	}


	public String getAcademicYearId(String academicYear, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String academicYearId=null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_ACADEMIC_YEAR_ID);
			psmt.setString(1, academicYear);
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				academicYearId=rs.getString("acadamic_id");
			}else{
				academicYearId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if(conn !=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return academicYearId;
	}


	public int checkAdmissionDate(String academicYearId, String dateofJoin, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		String startDate=null;
		String endDate=null;

		try {
			
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_START_END_ACADEMIC_YEAR);
			psmt.setString(1, academicYearId);
			psmt.setString(2, HelperClass.convertUIToDatabase(dateofJoin));
			psmt.setString(3,  HelperClass.convertUIToDatabase(dateofJoin));
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkClassByStream(String stream, String classId,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_BY_STREAM);
			psmt.setString(1, stream);
			psmt.setString(2, classId);
			
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkSectionByClass(String classId, String sectionId,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_BY_CLASS);
			psmt.setString(1, classId);
			psmt.setString(2, sectionId);
			
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public int checkSpecilizationByStream(String stream, String classId,String specilizationId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SPECILIZATION_BY_CLASS_STREAM);
			psmt.setString(1, specilizationId);
			psmt.setString(2, classId);
			psmt.setString(3, stream);
			
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return result;
	}


	public String getOccupationId(String occupation,Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String occupationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_OCCUPATION_ID);
			psmt.setString(1, occupation);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				occupationId=rs.getString("occupationId");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return occupationId;
	}


	public String getHouseId(String housename, String schoolLocationId, String academicYearId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String occupationId=null;

		try {

			psmt = connection
					.prepareStatement(UploadStudentXLSqlUtil.GET_HOUSE_ID);
			psmt.setString(1, housename);
			psmt.setString(2, schoolLocationId);
			psmt.setString(3, academicYearId);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			while (rs.next()) {
				occupationId=rs.getString("house_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return occupationId;
	}


	public String getClassIdByLoc(String classId, Connection connection, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String class_id = null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID_BY_LOC);
			psmt.setString(1, classId);
			psmt.setString(2, locationid);
			rs = psmt.executeQuery();

			if (rs.next()) {
				class_id=rs.getString("classdetail_id_int");
			}
			else{
				class_id = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return class_id;
	}


	public String getstuloc(String student, String accyearId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String loc_id = null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_LOC_ID_BY_STU);
			psmt.setString(1, student);
			psmt.setString(2, accyearId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				loc_id=rs.getString("locationId");
			}
			else{
				loc_id = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {

					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return loc_id;
	
	}


	public int validateStudent(String stuid,String accyearId, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Starting");
		int count = 0;
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs =null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT DISTINCT COUNT(*) FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ? AND locationId = ?");
			pstmt.setString(1,stuid);
			pstmt.setString(2,accyearId);
			pstmt.setString(3,locationid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {

			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : checkEmpID : Ending");
		return count;
	}


	public String getstudetails(String student, String accyearId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : getstudetails : Starting");
		String count = null;
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs =null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT locationId,classdetail_id_int,classsection_id_int,specilization FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ?");
			pstmt.setString(1,student);
			pstmt.setString(2,accyearId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4);
			}else{
				count = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {

			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : getstudetails : Ending");
		return count;
	}
	
	public String getSubjectId(String classid,String subjectName, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : getSubjectId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String subjectId=null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement("SELECT subjectID FROM `campus_subject` WHERE classid=? AND subjectName=?");
			psmt.setString(1, classid);
			psmt.setString(2, subjectName);
		
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				subjectId=rs.getString("subjectID");
			}else{
				subjectId = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if(conn !=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : getSubjectId : Ending");
		return subjectId;
	}
}
