package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.EmailContent;
import com.centris.campus.admin.SendMail;


import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StaffAttendanceSqlUtil;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.TeacherUtilsConstants;
import com.centris.campus.util.UploadStaffXLSqlUtil;
import com.centris.campus.util.UploadStageXLSqlUtil;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.centris.campus.vo.StaffAttendanceVo;
import com.itextpdf.text.log.SysoLogger;


public class UploadStaffXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadStaffXLSDaoImpl.class);

	
	public String getServerUrlFromBD(Connection connection) {
		String url = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement(TeacherUtilsConstants.GET_SERVER_URL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				url = rs.getString("URL");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return url;
	}
	
	public String sendEmailToEmployee(String username, String email,
			String password, String url) {
		try {
			EmailContent em = new EmailContent();
			// String[] mails = { emailId };

			System.out.println("email id in s send employee " + email);

			em.setUsername(username);
			em.setPassword(password);

			em.setContent("Greetings from E-CAMPUS PRO...  \n"
					+ " Thank you for Registering with us \n"
					+ "Please use below Url to track / view / update child activities in School \n"
					+ "Login Credentials are : \n" + "URL		: " + url + "\n"
					+ "User Name		: " + username + "\n" + "Password	: "
					+ password + "\n" + "Have a nice day\n\n\n" + "Regards \n"
					+ "E-CAMPUS PRO \n"
					+ "---------------------------------------------------\n"
					+ "This is System generated mail, Please do not reply."
					+ "\n");
			new SendMail().sendMail(email, em);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "sent";
	}
	
	public int checkStaffCountBeforeInsert() {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(UploadStaffXLSqlUtil.CHECK_BEFORINSERT_COUNT);

			System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
			System.out.println("In DIOMPL Before Insert: "+beforeInsertCount);
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
					conn = null;
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
				+ " Control in UploadStageXLSDaoImpl: Ending");

		return beforeInsertCount;
	}

	public int checkRegistrainId(String RegistrationId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXSLDaoImpl : checkRegistrainId : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;
		int count=0;

		try {

			ps_emp_count = connection
					.prepareStatement(UploadStaffXLSqlUtil.REGISTRATION_DUPLICATE);
			ps_emp_count.setString(1, RegistrationId);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				count = rs_emp_count.getInt(1);

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
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return count;
	}


	public String insertStaffXSL(List<UploadStaffXlsPOJO> successlist,
			Connection connection) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");
	
		
		
		//Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		
				int count=0;
				System.out.println("DAOIMPL Is Working Student Excel file Upload");
/*			    conn.setAutoCommit(false);
*/
				
			    System.out.println("successlist:::"+successlist.size());
			
			 for(int i=0;i<successlist.size();i++){
					
					ResultSet resultsetcount = null;

					PreparedStatement pstmt = null;
					
				
					
					PreparedStatement  ps_insertuser= null;

					try {
						connection = JDBCConnection.getSeparateConnection();
						 TeacherRegistrationPojo obj=new TeacherRegistrationPojo();
						 obj.setTfastname(successlist.get(i).getFirstName());
						 obj.setTeachermobno(successlist.get(i).getMobileNo());
						 String genPassword = HelperClass.genaratePasswordForTeacher(obj);

						String staffId=new IDGenerator().getPrimaryKeyID("campus_teachers"); 

						 pstmt = (PreparedStatement) JDBCConnection.getStatement(UploadStaffXLSqlUtil.INSERT_TEACHER);
						 		
						 //TeacherID,FirstName,LastName,Qualification,presentAddress,MobileNo,UserName,emailId,primarySubject,secondarySubject,
						 
								
						 		pstmt.setString(1, staffId);
								pstmt.setString(2, successlist.get(i).getFirstName());
								pstmt.setString(3, successlist.get(i).getLastName());
								pstmt.setString(4, successlist.get(i).getQualification());
								pstmt.setString(5, successlist.get(i).getPresentAddress());
								pstmt.setString(6, successlist.get(i).getMobileNo());
								pstmt.setString(7, successlist.get(i).getUserName());
								pstmt.setString(8, successlist.get(i).getEmail());
								//pstmt.setString(9, successlist.get(i).getPrimarySubject());
								//pstmt.setString(10, successlist.get(i).getSecondarySubject());
								
								
								 //DateOfBirth,DateOfJoining,designation,teachingType,department,gender,bankname
								
								pstmt.setString(9, HelperClass.convertUIToDatabase(successlist.get(i).getDob()));
								pstmt.setString(10, HelperClass.convertUIToDatabase(successlist.get(i).getDateOfJoining()));
								pstmt.setString(11, successlist.get(i).getDesignation());
								pstmt.setString(12, successlist.get(i).getTeachingType());
								pstmt.setString(13, successlist.get(i).getDepartment());
								pstmt.setString(14, successlist.get(i).getGender());
								pstmt.setString(15, successlist.get(i).getBankName());
								
								
								//accountnumber,pannumber,bloodgroup,fathername,mothername,permanentAddress
								pstmt.setString(16, successlist.get(i).getAccountNumber());
								pstmt.setString(17, successlist.get(i).getPanNumber());
								pstmt.setString(18, successlist.get(i).getBloodGroup());
								pstmt.setString(19, successlist.get(i).getFatherName());
								pstmt.setString(20, successlist.get(i).getMotherName());
								pstmt.setString(21, successlist.get(i).getPermanentAddress().trim());
								
								//createdby,createddate,registerId,password,role,reportingTo
								pstmt.setString(22, successlist.get(i).getCreatedby());
								pstmt.setTimestamp(23, HelperClass.getCurrentTimestamp());
								pstmt.setString(24, successlist.get(i).getRegistrationId());
								pstmt.setString(25, genPassword);
								pstmt.setString(26, successlist.get(i).getRole());
								pstmt.setString(27,  successlist.get(i).getReportingTo());
								
								//is_student_studying,studentName,student_admission_id,fatherMobile,motherMobile
								
								if(successlist.get(i).getIsStudentStudying()=="Y"||successlist.get(i).getIsStudentStudying().equalsIgnoreCase("Y"))
								{
									pstmt.setString(28, successlist.get(i).getIsStudentStudying());
									pstmt.setString(29, successlist.get(i).getStudentName());
									pstmt.setString(30, successlist.get(i).getAdmissionNo());
								}
								else
								{
									pstmt.setString(28, successlist.get(i).getIsStudentStudying());
									pstmt.setString(29, "");
									pstmt.setString(30, "");
								}
								
								pstmt.setString(31, successlist.get(i).getFatherMobile());
								pstmt.setString(32, successlist.get(i).getMotherMobile());
								pstmt.setString(33, successlist.get(i).getAbbreviation());
								pstmt.setString(34, successlist.get(i).getLocation());
								
								pstmt.setString(35, successlist.get(i).getMaritalStatus());
								pstmt.setString(36, successlist.get(i).getSpouseName());
								pstmt.setString(37, successlist.get(i).getSpouseMobile());
								
								System.out.println("INSERT STAFF::::"+pstmt);
								boolean result = pstmt.execute();
								
								
								String userId=new IDGenerator().getPrimaryKeyID("campus_user"); 
									//connection = JDBCConnection.getSeparateConnection();
									
									ps_insertuser=connection.prepareStatement("insert into campus_user(usercode,employeecode,username,password,role,type,createuser,createdate,locationId) values(?,?,?,?,?,?,?,?,?)");
									
									ps_insertuser.setString(1, userId);
									ps_insertuser.setString(2, staffId);
									ps_insertuser.setString(3,successlist.get(i).getUserName());
									ps_insertuser.setString(4,genPassword);
									ps_insertuser.setString(5, successlist.get(i).getRole());
									ps_insertuser.setString(6, successlist.get(i).getUserType());
									ps_insertuser.setString(7, successlist.get(i).getCreatedby());
									ps_insertuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
									ps_insertuser.setString(9, successlist.get(i).getLocation());
									System.out.println("insert user :: "+ps_insertuser);
									
									int insert=ps_insertuser.executeUpdate();
									if(insert>0){
										count++;
									}
								  //sending email.
									
									String url = getServerUrlFromBD(connection);
									// String url="www.google.com";
									String set = sendEmailToEmployee(obj.getUsername(),
									obj.getTeacheremail(), genPassword, url);
								
									List<String> category = new ArrayList<String>();
									 List<String> NoOfLEaves = new ArrayList<String>();
									PreparedStatement Leavedetails=connection.prepareStatement("SELECT * FROM campus_new_leave_bank WHERE Loc_ID=? AND year=?");
									Leavedetails.setString(1, successlist.get(i).getLocation());
									Leavedetails.setInt(2,HelperClass.getCurrentYear());
									ResultSet leaveRs= Leavedetails.executeQuery();
									while(leaveRs.next()) {
										category.add(leaveRs.getString("Leave_name"));
										NoOfLEaves.add(leaveRs.getString("No_Of_Leaves"));
									}
									 String[] categorynames = (String[]) category.toArray();
									 String[] noofleaves = (String[]) NoOfLEaves.toArray();
									 new LeaveBankDAOIMPL().leaveBankInsert(staffId,successlist.get(i).getDateOfJoining(),successlist.get(i).getLocation(),successlist.get(i).getTeachingType(), HelperClass.getCurrentAcadamicYear(), categorynames, noofleaves, connection);
									
							
						

					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} finally {
						try {
							if (resultsetcount != null && (!resultsetcount.isClosed())) {
								resultsetcount.close();
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
					
					
				}
					
		
			 
/*			 conn.commit();
*/		
        
		
		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Ending");

		/*returnCount=""+conutDuplicateRecord++;
		System.out.println("In DAIOMPL duplicate Count= "+returnCount);*/
		String retrunCount=""+count;
		return retrunCount;
	}

	public String getDepartmentId(String department) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getDepartmentId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String departmentId=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_DEPARTMENT_ID);
				
				psDep.setString(1,department);
			
				
				System.out.println("get departmentCode ::: "+psDep);
				
				rs=psDep.executeQuery();
				
				while(rs.next()){
					departmentId=rs.getString("dept_id");
					
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
			
			return departmentId;
		}

	public String getDesignationId(String designation) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getDepartmentId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String designationId=null;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_DESIGNATION_ID);
				
				psDep.setString(1,designation);
			
				
				System.out.println("get departmentCode ::: "+psDep);
				
				rs=psDep.executeQuery();
				
				while(rs.next()){
					designationId=rs.getString("DesignationCode");
					
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
			
			return designationId;
		}

	public String getSubjectId(String subject) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getprimarySubjectId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String subjectId=null;
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_SUBJECT_ID);
				
				psDep.setString(1,subject);
			
				
				System.out.println("get departmentCode ::: "+psDep);
				
				rs=psDep.executeQuery();
				
				while(rs.next()){
					subjectId=rs.getString("subjectID");
					
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
			
			return subjectId;
		}

	public String getRoleId(String role) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getRoleId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String roleId=null;
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_ROLE_ID);
				
				psDep.setString(1,role);
			
				
				System.out.println("get RoleCOde ::: "+psDep);
				
				rs=psDep.executeQuery();
				
				while(rs.next()){
					roleId=rs.getString("RoleCode");
					
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
			
			return roleId;
		}

	public String getReportingToId(String reportingTo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getRoleId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String reportingToId=null;
			
			
			try {
				
				conn = JDBCConnection.getSeparateConnection();
				System.out.println("Reporting To:  "+reportingTo);
				
				
				
				
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_REPORTING_CODE);
				
				psDep.setString(1,reportingTo);
				
			
				System.out.println("get ReportingTo ::: "+psDep);
				
				rs=psDep.executeQuery();
				
				while(rs.next()){
					reportingToId=rs.getString("TeacherID");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
			
			return reportingToId;
		}




}
