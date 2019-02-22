package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SecadmissionformatDao;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.forms.Secondadmissionformformat;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;

public class SecadmissionFormDaoImpl implements SecadmissionformatDao {    

	private static final Logger logger = Logger
			.getLogger(SecadmissionFormDaoImpl.class);


	@Override
	public String InsertSecadmissionform(ParentRequiresAppointmentForm secform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2=null;
		ResultSet rs2= null;

		Timestamp t = HelperClass.getCurrentTimestamp();


		Connection conn = null;
		IDGenerator id = new IDGenerator();
		String msg ="",admissionno="",stuname="";
		int i=0;

		try {

			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			System.out.println("enquiry id is "+secform.getEnquiryid());
			if(secform.getEnquiryid() != null){
				pstmt = conn.prepareStatement("insert into campus_temporary_admisssion_details (temporary_admission_id,studentfirstName,studentlastname,dateofBirthId,gender,nationality,state,religion,othersreligion,caste,othercaste,mothertongue,aadharNo,permanentaddress,addressofcommunication,distance,preferedphno,schoollocation,schemeofstudy,otherboard,secondlanguage,thirdlanguage,classname,extraactivity,issibilings,siblingid,siblingname,fathername,fathermobileno,fatherqualification,fatheroccupation,fatherdesignation,fatherdepartment,fathermonthincome,fatheremailid,fatherofficialaddress,mothername,mothermobile,motherqualification,motheroccupation,motherdesignation,motherdepartment,mothermothlyincome,motheremailid,motherofficialaddress,isaluminiparents,isaluminifathername,fatheraluminiyear,isaluminimothername,motheraluminiyear,createdTime,imageUrl,enquiryid,castecategory,othercastecategory,stream,previousschool,accyear,comminication_landline,landline_no,father_office_landline,mother_office_landline,emergency_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, id.getPrimaryKeyID("campus_temporary_admisssion_details"));
				pstmt.setString(2, secform.getStudentfirstName());
				pstmt.setString(3, secform.getStudentLastName());
				pstmt.setString(4, HelperClass.convertUIToDatabase(secform.getDateofBirth().trim()));
				pstmt.setString(5, secform.getGender());
				pstmt.setString(6, secform.getNationality());
				pstmt.setString(7, secform.getState());
				pstmt.setString(8, secform.getReligion());
				pstmt.setString(9, secform.getOtherreligion());
				pstmt.setString(10, secform.getCaste());
				pstmt.setString(11, secform.getOthercaste());
				pstmt.setString(12, secform.getMothertongue());
				pstmt.setString(13, secform.getAadharno());
				pstmt.setString(14, secform.getAddress());
				pstmt.setString(15, secform.getAddressofcommunication());
				pstmt.setDouble(16, secform.getDistance());
				pstmt.setString(17, secform.getPreferedphno());
				pstmt.setString(18, secform.getSchoolLocation());
				pstmt.setString(19, secform.getSchemeofstudy());
				pstmt.setString(20, secform.getAnyotherboard());
				pstmt.setString(21, secform.getSecondlanguage());
				pstmt.setString(22, secform.getThirdlanguage());
				pstmt.setString(23, secform.getClassname());
				pstmt.setString(24, secform.getActivities());
				pstmt.setString(25, secform.getIsSibling());
				pstmt.setString(26, secform.getIsSiblingId());
				pstmt.setString(27, secform.getIsSiblingName());
				pstmt.setString(28, secform.getFatherName());
				pstmt.setString(29, secform.getFatherMobileNo());
				pstmt.setString(30, secform.getFatherQualification());
				pstmt.setString(31, secform.getFatherOccupation());
				pstmt.setString(32, secform.getFatherDesignation());
				
				pstmt.setString(33, secform.getFatherDepartment());
				
				pstmt.setDouble(34, secform.getFatherMonthlyIncome());
				pstmt.setString(35, secform.getFatherEmail());
				pstmt.setString(36, secform.getFathersOfficialAddress());
				pstmt.setString(37, secform.getMothername());
				pstmt.setString(38, secform.getMotherMobileNo());
				pstmt.setString(39, secform.getMotherQualification());
				pstmt.setString(40, secform.getMotherOccupation());
				pstmt.setString(41, secform.getMotherDesignation());
				
				pstmt.setString(42, secform.getMotherDepartment());
				
				pstmt.setDouble(43, secform.getMotherMonthlyIncome());
				pstmt.setString(44, secform.getMotherEmail());
				pstmt.setString(45, secform.getMothersOfficialAddress());
				pstmt.setString(46, secform.getParentsAlumni());
				pstmt.setString(47, secform.getFatherAlumniname());
				pstmt.setString(48, secform.getFatherAlumniyear());
				pstmt.setString(49, secform.getMotherAlumniname());
				pstmt.setString(50, secform.getMotherAlumniyear());
				pstmt.setTimestamp(51, HelperClass.getCurrentTimestamp());
				pstmt.setString(52, secform.getImageString());
				pstmt.setString(53, secform.getEnquiryid());
				pstmt.setString(54, secform.getCasteCategory());
				pstmt.setString(55, secform.getOthercastecategory());
				pstmt.setString(56, secform.getStream());
				pstmt.setString(57, secform.getLastSchool());
				pstmt.setString(58, secform.getHaccyearid());
				
				
				pstmt.setString(59, secform.getMothPerAddLandLiNo());
				pstmt.setString(60, secform.getFatPerAddLandLiNo());
				pstmt.setString(61, secform.getFatOffiAddLandLiNo());
				pstmt.setString(62, secform.getMothOffiAddLandLiNo());
				pstmt.setString(63, secform.getEmergencyNo());
				
				System.out.println("temp reg "+pstmt);

				i=pstmt.executeUpdate();
				
				pstmt1=conn.prepareStatement(SQLUtilConstants.UPDATE_ENQUIRY_DETAILS);
				pstmt1.setString(1, secform.getEnquiryid());
		        pstmt1.executeUpdate();
			   }
			   pstmt1=conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORMNO);
			   pstmt1.setString(1, secform.getEnquiryid());
			   rs2=pstmt1.executeQuery();
			   while(rs2.next())
			   {
				   admissionno=rs2.getString("temporary_admission_id");
				   stuname=rs2.getString("studentname");
			   }
			   
			  /* if(i>0)
				{
					msg="inserted successfully"+"-"+admissionno;
				}
				else
				{
					msg="insertion failed";
				}*/
			   
			   
			   
			   conn.commit();
			   
			   
			

		   }   catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(rs2!=null && (!rs2.isClosed())){
					rs2.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
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
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return admissionno;
	}


	@Override
	public String InsertThirdadmissionform(ParentRequiresAppointmentForm parentform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		PreparedStatement psmt2=null;
		ResultSet rs2=null;
		int rs = 0;
		int updateCount=0;
		String admissionno="",stuname="",msg="";
		Timestamp t = HelperClass.getCurrentTimestamp();

		String result = null;

		Connection conn = null;
		Connection conn1= null;
		IDGenerator id = new IDGenerator();

		try {

			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("insert into campus_temporary_admisssion_details (temporary_admission_id,studentfirstName,studentlastname,dateofBirthId,gender,nationality,placeofbirth,state,religion,othersreligion,caste,othercaste,castecategory,othercastecategory,mothertongue,aadharNo,permanentaddress,addressofcommunication,previousschool,schemeofstudy,otherboard,qualify_name,extraactivity,classname,group_prefered,opt_subjects,fathername,fathermobileno,fatheroccupation,fatherdesignation,fatherdepartment,fathermonthincome,fatheremailid,fatherofficialaddress,mothername,mothermobile,motheroccupation,motherdesignation,motherdepartment,mothermothlyincome,motheremailid,motherofficialaddress,createdTime,imageUrl,marksheetupload,enquiryid,stream,schoollocation,fatherqualification,motherqualification,preferedphno,TC,Migration,accyear,comminication_landline,landline_no,father_office_landline,mother_office_landline,emergency_no) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
			pstmt.setString(1, id.getPrimaryKeyID("campus_temporary_admisssion_details"));
			pstmt.setString(2, parentform.getStudentfirstName());
			pstmt.setString(3, parentform.getStudentLastName());
			pstmt.setString(4,HelperClass.convertUIToDatabase(parentform.getDateofBirth()));
			pstmt.setString(5, parentform.getGender());
			pstmt.setString(6, parentform.getNationality());
			pstmt.setString(7, parentform.getPlaceofBirth());
			pstmt.setString(8, parentform.getState());
			pstmt.setString(9, parentform.getReligion());
			pstmt.setString(10, parentform.getOtherreligion());
			pstmt.setString(11, parentform.getCaste());
			pstmt.setString(12, parentform.getOthercaste());
			pstmt.setString(13,parentform.getCasteCategory());
			pstmt.setString(14,parentform.getOthercastecategory());
			pstmt.setString(15,parentform.getMothertongue());
			pstmt.setString(16,parentform.getAadharno());
			pstmt.setString(17,parentform.getAddress());
			pstmt.setString(18,parentform.getAddressofcommunication());
			pstmt.setString(19,parentform.getLastSchool());
			pstmt.setString(20,parentform.getSchemeofstudy());
			pstmt.setString(21,parentform.getAnyotherboard());
			pstmt.setString(22,parentform.getQualifying());
			pstmt.setString(23,parentform.getActivities());
			pstmt.setString(24,parentform.getClassname());
			pstmt.setString(25,parentform.getSpecilization());
			pstmt.setString(26,parentform.getOptionalsubject());
			pstmt.setString(27,parentform.getFatherName());
			pstmt.setString(28,parentform.getFatherMobileNo());
			pstmt.setString(29,parentform.getFatherOccupation());
			pstmt.setString(30,parentform.getFatherDesignation());
			pstmt.setString(31,parentform.getFatherDepartment());
			pstmt.setDouble(32,parentform.getFatherMonthlyIncome());
			pstmt.setString(33,parentform.getFatherEmail());
			pstmt.setString(34,parentform.getFathersOfficialAddress());
			
			
			pstmt.setString(35,parentform.getMothername());
			pstmt.setString(36,parentform.getMotherMobileNo());
			pstmt.setString(37,parentform.getMotherOccupation());
			pstmt.setString(38,parentform.getMotherDesignation());
			pstmt.setString(39,parentform.getMotherDepartment());
			pstmt.setDouble(40,parentform.getMotherMonthlyIncome());
			pstmt.setString(41,parentform.getMotherEmail());
			pstmt.setString(42,parentform.getMothersOfficialAddress());
			 pstmt.setTimestamp(43, HelperClass.getCurrentTimestamp());
			 if(parentform.getImageString() != null && !parentform.getImageString().equalsIgnoreCase("")){
					pstmt.setString(44, parentform.getImageString());
				}else{
					pstmt.setString(44, "");
				}
			 if(parentform.getMarkImageFile() != null && !parentform.getMarkImageFile().equalsIgnoreCase("")){
					pstmt.setString(45, parentform.getMarkImageFile());
				}else{
					pstmt.setString(45, "");
				}
				pstmt.setString(46, parentform.getEnquiryid());
				pstmt.setString(47, parentform.getStream());
				pstmt.setString(48, parentform.getSchoolLocation());
				
				pstmt.setString(49, parentform.getFatherQualification());
				pstmt.setString(50, parentform.getMotherQualification());
				pstmt.setString(51, parentform.getPreferedphno());
				pstmt.setString(52, parentform.getTccertificateString());
				pstmt.setString(53, parentform.getMigrationcertificateString());
				pstmt.setString(54, parentform.getHaccyearid());
				
				pstmt.setString(55, parentform.getMothPerAddLandLiNo());
				pstmt.setString(56, parentform.getFatPerAddLandLiNo());
				pstmt.setString(57, parentform.getFatOffiAddLandLiNo());
				pstmt.setString(58, parentform.getMothOffiAddLandLiNo());
				pstmt.setString(59, parentform.getEmergencyNo());
			
			System.out.println("pstmt:::" + pstmt);

			rs = pstmt.executeUpdate();

			pstmt3=conn.prepareStatement("DELETE FROM campus_user WHERE employeecode=?");
			pstmt3.setString(1, parentform.getEnquiryid().trim());
			
			System.out.println("DELETE QUERY "+pstmt3);
			updateCount=pstmt3.executeUpdate();
		
			
			
		pstmt1=conn.prepareStatement(SQLUtilConstants.UPDATE_ENQUIRY_DETAILS);
		pstmt1.setString(1, parentform.getEnquiryid());
		pstmt1.executeUpdate();
		
		psmt2=conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORMNO);
		psmt2.setString(1, parentform.getEnquiryid());
		rs2=psmt2.executeQuery();
		if(rs2.next())
		{
			admissionno=rs2.getString("temporary_admission_id");
			stuname=rs2.getString("studentname");
		}
		

		conn.commit();
		}   catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(rs2!=null && (!rs2.isClosed())){
					rs2.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
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
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return admissionno;
	}
	
}
