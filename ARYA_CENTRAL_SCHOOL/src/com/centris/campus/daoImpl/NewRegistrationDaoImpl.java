package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.NewRegistrationDao;
import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.ExamDetailsPojo;

import com.centris.campus.util.ExamSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.NewRegistrationVO2;
import com.centris.campus.vo.StudentRegistrationVo;
import com.itextpdf.text.log.SysoLogger;

public  class NewRegistrationDaoImpl implements NewRegistrationDao {

	private static final Logger logger = Logger
			.getLogger(NewRegistrationDaoImpl.class);


	public void getregdetails(NewRegistrationForm newregform) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in NewRegistrationDaoImpl : getregdetails Starting");
		int nn = 0;
		String status="false";

		PreparedStatement psr = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psr = conn.prepareStatement("INSERT INTO newregistration (firstName,lastName,email,mobile,address,userName,password,rePssword) VALUES (?,?,?,?,?,?,?,?)");

			psr.setString(1, newregform.getFirstName());
			psr.setString(2, newregform.getLastName());
			psr.setString(3, newregform.getEmail());
			psr.setString(4, newregform.getMobile());
			psr.setString(5, newregform.getAddress());
			psr.setString(6, newregform.getUserName());
			psr.setString(7, newregform.getPassword());
			psr.setString(8, newregform.getRePssword());
			nn = psr.executeUpdate();

			if(nn > 0){
				status="true";
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {

				if (psr != null && !psr.isClosed()) {
					psr.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
	}

	public String InsertNewRegistrationUser(NewUserRegistrationForm registrationform) 

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in NewRegistrationDaoImpl:InsertNewRegistrationUser:Starting");

		PreparedStatement pstmt = null;
		String status = null;
		Connection conn = null;

		int result1=0;
		Timestamp t = HelperClass.getCurrentTimestamp();

		try {

			String id1 = IDGenerator.getPrimaryKeyID("campus_parent_enquiry_details");

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.REGISTER_PARENT_ENQUIRY_DETAILS);

			pstmt.setString(1, id1);
			pstmt.setString(2, registrationform.getParentfirstName());
			pstmt.setString(3,registrationform.getParent_LastName());
			pstmt.setString(4, registrationform.getParentEmailId());
			pstmt.setString(5, registrationform.getMobile_number());
			pstmt.setString(6, registrationform.getAddress());
			pstmt.setString(7, registrationform.getStu_parrelation()); 
			pstmt.setString(8, registrationform.getStreamname()); 
			pstmt.setString(9, registrationform.getClassid()); 
			pstmt.setString(10, registrationform.getParents_name());
			pstmt.setString(11,"pending");
			pstmt.setString(12,registrationform.getLocation());
			pstmt.setString(13,registrationform.getAccyearId());
			System.out.println("REGISTER_PARENT_ENQUIRY_DETAILS:;:"+pstmt);
			result1 = pstmt.executeUpdate();

			if (result1 > 0 ) {

				status = "true";

			} else {

				status = "false";

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in NewRegistrationDaoImpl:InsertNewRegistrationUser:Ending");
		return status;
	}

	@Override
	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in NewRegistrationDaoImpl:InsertNewRegistrationUser:Starting");

		PreparedStatement pstmt = null;
		String status = null;
		Connection conn = null;
		int result1=0;
		Timestamp t = HelperClass.getCurrentTimestamp();
		try {
			String id1 = IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details");
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.REGISTER_PARENT__STUDENT_DETAILS_FOR_ENQUIRY);


			pstmt.setString(1, id1);
			pstmt.setString(2, registrationform.getStudentfirstName().trim());
			pstmt.setString(3,HelperClass.convertUIToDatabase(registrationform.getDateofBirthId()));
			pstmt.setString(4, registrationform.getClassname().trim());
			pstmt.setString(5, registrationform.getGroup_name().trim());
			pstmt.setString(6, registrationform.getAccyear().trim());
			pstmt.setString(7, registrationform.getParentId().trim());
			pstmt.setString(8, registrationform.getMobile_number().trim());
			pstmt.setString(9,registrationform.getEmailId().trim());
			pstmt.setString(10, registrationform.getAddress());
			pstmt.setString(11,registrationform.getRelationship().trim());
			pstmt.setString(12,registrationform.getAlternateMobileNo().trim());
			pstmt.setString(13, registrationform.getAlternateemailId().trim());
			pstmt.setString(14, registrationform.getAdvertisement());
			pstmt.setString(15,registrationform.getPaper());
			pstmt.setString(16, registrationform.getWebsites());
			pstmt.setString(17, registrationform.getChannels());
			pstmt.setString(18, registrationform.getOthers());
			pstmt.setString(19, registrationform.getParents());
			pstmt.setTimestamp(20,t);
			pstmt.setString(21, registrationform.getSchool_name().trim());
			pstmt.setString(22,registrationform.getPercentage().trim());
			pstmt.setString(23,registrationform.getPrevious_classname().trim());
			pstmt.setString(24, registrationform.getGroup_name1().trim());
			result1 = pstmt.executeUpdate();

			if (result1 > 0 ) {

				status = "true";

			} else {

				status = "false";

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
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
		return status;
	}

}
