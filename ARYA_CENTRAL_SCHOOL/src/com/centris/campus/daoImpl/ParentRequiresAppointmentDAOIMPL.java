package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.SendMail;
import com.centris.campus.dao.ParentRequiresAppointmentDAO;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public class ParentRequiresAppointmentDAOIMPL implements ParentRequiresAppointmentDAO {

	private static final Logger logger = Logger
			.getLogger(ParentRequiresAppointmentDAOIMPL.class);

	public List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_TEMPORARY_REGISTRATION_DETAILS_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setDateofBirthId(rs.getString("dateofBirthId"));
				vo.setGender(rs.getString("gender"));
				vo.setEmailId(rs.getString("emailId"));
				vo.setMobile_number(rs.getString("mobile_number"));

//				if (rs.getString("group_name") == null
//						|| rs.getString("group_name") == ""
//						|| rs.getString("group_name").isEmpty() == true) {
//					vo.setClassname(rs.getString("classdetails_name_var"));
//				} else {
//					vo.setClassname(rs.getString("classdetails_name_var")
//							+ " - " + rs.getString("group_name"));
//				}
//
//				vo.setAccyear(rs.getString("acadamic_year"));
//				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs
//						.getString("dateofBirthId")));
//				vo.setStatus(rs.getString("status"));

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	}

	@Override
	public ParentRequiresAppointmentVO EditingForAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SINGLE_REGISTRATION_DETAILS_LIST);

			pstmt.setString(1, detailsVo.getTemporary_id());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setDateofBirthId(rs.getString("dateofBirthId"));
				vo.setClassname(rs.getString("classname"));
				vo.setGroup_name(rs.getString("group_name"));
				vo.setAccyear(rs.getString("accyear"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setEmailId(rs.getString("emailId"));
				vo.setAddress(rs.getString("address"));
				vo.setSchool_name(rs.getString("school_name"));
				vo.setPercentage(rs.getString("percentage"));
				vo.setPrevious_classname(rs.getString("previous_classname"));
				vo.setGroup_name1(rs.getString("group_name1"));

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return vo;

	}

	@Override
	public String UpdatingFirstLevelAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		int rs = 0;

		Timestamp t = HelperClass.getCurrentTimestamp();

		String result = null;

		Connection conn = null;

		String emailId = null;
		String alternateemailId = null;
		String temporary_admission_id = null;
		String studentfirstName = null;
		String parentId = null;
		String dateofbirth = null;
		String mailcheck = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATING_STATUS_DETAILS_FOR_TEMPORARY_REGISTRATION);
			pstmt.setString(1, detailsVo.getRemarks());
			pstmt.setString(2, detailsVo.getStatus());
			pstmt.setString(3, detailsVo.getModifiedby());
			pstmt.setTimestamp(4, t);
			pstmt.setString(5, detailsVo.getAppointment_time());
			pstmt.setString(6, detailsVo.getAppointment_date());
			pstmt.setString(7, detailsVo.getTemporary_id());

			System.out.println(pstmt);

			rs = pstmt.executeUpdate();

			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_EMAIL_ID);
			pstmt1.setString(1, detailsVo.getTemporary_id());

			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();

			while (rs1.next()) {

				emailId = rs1.getString("emailId");

				alternateemailId = rs1.getString("alternateemailId");
				temporary_admission_id = rs1
						.getString("temporary_admission_id");
				studentfirstName = rs1.getString("studentfirstName");
				parentId = rs1.getString("parentId");
				dateofbirth = rs1.getString("appointment_date") + ","
						+ rs1.getString("appointment_time");

				System.out.println(emailId + "         " + alternateemailId
						+ "         " + temporary_admission_id + "         "
						+ studentfirstName + "         " + parentId
						+ "         " + dateofbirth);

			}

			if (detailsVo.getStatus() == "Called For Discussion"
					|| detailsVo.getStatus().equalsIgnoreCase(
							"Called For Discussion")) {
				mailcheck = new SendMail()
						.sendMailToParentsOnRequestOfAdmisssionFromOnlinePortal(
								emailId, alternateemailId,
								temporary_admission_id, studentfirstName,
								parentId, dateofbirth);

				System.out.println(mailcheck);
			}

			if (rs > 0
					&& (mailcheck == "sent" || mailcheck
							.equalsIgnoreCase("sent"))) {
				result = "true";
			} else {
				result = "false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		return result;

	}

	@Override
	public String InsertTemporaryStudentRegistration(ParentRequiresAppointmentForm secform) {

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
		Connection conn1;
		String admissionno="",stuname="",msg="";
		Timestamp t = HelperClass.getCurrentTimestamp();

		//String result = null;

		Connection conn = null;

		IDGenerator id = new IDGenerator();
		
		int i=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);  
			System.out.println("Father Monthly Income "+secform.getFatherMonthlyIncome());
			if(secform.getEnquiryid() != null){
				pstmt = conn.prepareStatement("insert into campus_temporary_admisssion_details (temporary_admission_id,studentfirstName,studentlastname,dateofBirthId,gender,nationality,placeofbirth,state,religion,othersreligion,caste,othercaste,castecategory,othercastecategory,mothertongue,aadharNo,permanentaddress,addressofcommunication,preferedphno,classname,issibilings,siblingid,siblingname,isaluminiparents,isaluminifathername,fatheraluminiyear,isaluminimothername,motheraluminiyear,fathername,fathermobileno,fatheroccupation,fatherdesignation,fatherdepartment,fathermonthincome,fatheremailid,fatherofficialaddress,mothername,mothermobile,motheroccupation,motherdesignation,motherdepartment,mothermothlyincome,motheremailid,motherofficialaddress,createdTime,imageUrl,enquiryid,stream,BirthCertificateFile,schoollocation,accyear,comminication_landline,landline_no,father_office_landline,mother_office_landline,emergency_no,previousschool) values ((SELECT concat('TAD',max(cast(SUBSTRING(abc.temporary_admission_id, 4, length(abc.temporary_admission_id)-3) as unsigned))+1) temporary_admission_id FROM campus_temporary_admisssion_details abc),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				
				pstmt.setString(1, secform.getStudentfirstName());
				pstmt.setString(2, secform.getStudentLastName());
				pstmt.setString(3, HelperClass.convertUIToDatabase(secform.getDateofBirth().trim()));
				pstmt.setString(4, secform.getGender());
				pstmt.setString(5, secform.getNationality());
				pstmt.setString(6, secform.getPlaceofBirth());
				pstmt.setString(7, secform.getState());
				pstmt.setString(8, secform.getReligion());
				pstmt.setString(9, secform.getOtherreligion());
				pstmt.setString(10, secform.getCaste());
				pstmt.setString(11, secform.getOthercaste());
				pstmt.setString(12, secform.getCasteCategory());
				pstmt.setString(13, secform.getOthercastecategory());
				pstmt.setString(14, secform.getMothertongue());
				pstmt.setString(15, secform.getAadharno());
				pstmt.setString(16, secform.getAddress());
				pstmt.setString(17, secform.getAddressofcommunication());
				pstmt.setString(18, secform.getPreferedphno());
				pstmt.setString(19, secform.getClassname());
				pstmt.setString(20, secform.getIsSibling());
				pstmt.setString(21, secform.getIsSiblingId());
				pstmt.setString(22, secform.getIsSiblingName());
				pstmt.setString(23, secform.getParentsAlumni());
				pstmt.setString(24, secform.getFatherAlumniname());
				pstmt.setString(25, secform.getFatherAlumniyear());
				pstmt.setString(26, secform.getMotherAlumniname());
				pstmt.setString(27, secform.getMotherAlumniyear());
                pstmt.setString(28, secform.getFatherName());
				pstmt.setString(29, secform.getFatherMobileNo());
				pstmt.setString(30, secform.getFatherOccupation());
				
				
				pstmt.setString(31, secform.getFatherDesignation());
				pstmt.setString(32, secform.getFatherDepartment());
				
				pstmt.setDouble(33, secform.getFatherMonthlyIncome());
				pstmt.setString(34, secform.getFatherEmail());
				pstmt.setString(35, secform.getFathersOfficialAddress());
				pstmt.setString(36, secform.getMothername());
				pstmt.setString(37, secform.getMotherMobileNo());
				pstmt.setString(38, secform.getMotherOccupation());
				
				pstmt.setString(39, secform.getMotherDesignation());
				pstmt.setString(40, secform.getMotherDepartment());
				
				pstmt.setDouble(41, secform.getMotherMonthlyIncome());
				pstmt.setString(42, secform.getMotherEmail());
				pstmt.setString(43, secform.getMothersOfficialAddress());
                pstmt.setTimestamp(44, HelperClass.getCurrentTimestamp());
				pstmt.setString(45, secform.getImageString());
				pstmt.setString(46, secform.getEnquiryid().trim());
				pstmt.setString(47, secform.getStream());
				pstmt.setString(48, secform.getBirthCertificateFileString());
				pstmt.setString(49, secform.getSchoolLocation());
				pstmt.setString(50, secform.getHaccyearid());
				
				pstmt.setString(51, secform.getMothPerAddLandLiNo());
				pstmt.setString(52, secform.getFatPerAddLandLiNo());
				pstmt.setString(53, secform.getFatOffiAddLandLiNo());
				pstmt.setString(54, secform.getMothOffiAddLandLiNo());
				pstmt.setString(55, secform.getEmergencyNo());
				pstmt.setString(56, secform.getLastSchool());
				System.out.println("temp reg "+pstmt);

				i=pstmt.executeUpdate();
				
				pstmt3=conn.prepareStatement("DELETE FROM campus_user WHERE employeecode=?");
				pstmt3.setString(1, secform.getEnquiryid().trim());
				
				System.out.println("DELETE QUERY "+pstmt3);
				updateCount=pstmt3.executeUpdate();
			}
			if(updateCount>0) {
				pstmt1=conn.prepareStatement(SQLUtilConstants.UPDATE_ENQUIRY_DETAILS);
				pstmt1.setString(1, secform.getEnquiryid());
				pstmt1.executeUpdate();
				
				psmt2=conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORMNO);
				psmt2.setString(1, secform.getEnquiryid());
				rs2=psmt2.executeQuery();
				if(rs2.next())
				{
					admissionno=rs2.getString("temporary_admission_id");
					stuname=rs2.getString("studentname");
				}
				

				conn.commit();
			}
			else {
				admissionno="false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
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
	public List<ParentRequiresAppointmentVO> searchadmissionsList(
			String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_TEMPORARY_REGISTRATION_DETAILS_LIST);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			pstmt.setString(7, "%" + searchName + "%");
			pstmt.setString(8, "%" + searchName + "%");
			pstmt.setString(9, "%" + searchName + "%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));

				if (rs.getString("group_name") == null
						|| rs.getString("group_name") == ""
						|| rs.getString("group_name").isEmpty() == true) {
					vo.setClassname(rs.getString("classdetails_name_var"));
				} else {
					vo.setClassname(rs.getString("classdetails_name_var")
							+ " - " + rs.getString("group_name"));
				}

				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs
						.getString("dateofBirthId")));
				vo.setStatus(rs.getString("status"));

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	}

	@Override
	public String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int no = 0;

		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.DELETE_TEMPORARY_REGISTRATION_DETAILS_LIST);

			pstmt.setString(1, vo.getTemporary_id());

			System.out.println(pstmt);

			no = pstmt.executeUpdate();

			System.out.println(no);

			if (no > 0) {
				status = "true";
			} else {
				status = "false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}

	@Override
	public List<ParentRequiresAppointmentVO> CalledForEvaluationList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_CALLED_FOR_EVALUATION_DETAILS_LIST);

			System.out.println("DAOIMPL" + pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));

				if (rs.getString("group_name") == null
						|| rs.getString("group_name") == ""
						|| rs.getString("group_name").isEmpty() == true) {
					vo.setClassname(rs.getString("classdetails_name_var"));
				} else {
					vo.setClassname(rs.getString("classdetails_name_var")
							+ " - " + rs.getString("group_name"));
				}

				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs
						.getString("dateofBirthId")));
				vo.setStatus(rs.getString("status"));
				vo.setAppointment_date(rs.getString("appointment_date") + " ; "
						+ rs.getString("appointment_time"));

				if (rs.getString("evaluation_recomendation_status") == null
						|| rs.getString("evaluation_recomendation_status") == "") {
					vo.setRemarks("Not Yet Done");
				} else {
					vo.setRemarks(rs
							.getString("evaluation_recomendation_status"));

				}

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	}

	@Override
	public ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SINGLE_CALLED_FOR_ADMISSION_DETAILS_LIST);

			pstmt.setString(1, detailsVo.getTemporary_id());

			System.out.println(pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setDateofBirthId(rs.getString("dateofBirthId"));
				vo.setClassname(rs.getString("classname"));
				vo.setGroup_name(rs.getString("group_name"));
				vo.setAccyear(rs.getString("accyear"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setEmailId(rs.getString("emailId"));
				vo.setAddress(rs.getString("address"));
				vo.setSchool_name(rs.getString("school_name"));
				vo.setPercentage(rs.getString("percentage"));
				vo.setPrevious_classname(rs.getString("previous_classname"));
				vo.setGroup_name1(rs.getString("group_name1"));
				vo.setAppointment_date(rs.getString("appointment_date"));
				vo.setAppointment_time(rs.getString("appointment_time"));

			}

			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_USER_NAME);
			pstmt1.setString(1, detailsVo.getCurrent_userid());

			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();

			while (rs1.next()) {

				vo.setCurrent_user(rs1.getString("FirstName") + " "
						+ rs1.getString("LastName"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
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
		return vo;

	}

	public String UpdatingCalledForEvaluationStatus(
			ParentRequiresAppointmentVO details) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		int rs = 0;

		Timestamp t = HelperClass.getCurrentTimestamp();

		String result = null;

		Connection conn = null;

		

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATING_STATUS_DETAILS_FOR_CALLED_FOR_EVALUATION);

			pstmt.setString(1, details.getEvaluated_by());
			pstmt.setString(2, HelperClass.convertUIToDatabase(details
					.getEvaluation_date()));
			pstmt.setString(3, details.getTest());
			pstmt.setString(4, details.getMax_marks());
			pstmt.setString(5, details.getMarks_secured());
			pstmt.setString(6, details.getStatus());
			pstmt.setString(7, details.getRemarks());
			pstmt.setString(8, details.getCurrent_user());
			pstmt.setTimestamp(9, t);
			pstmt.setString(10, details.getTemporary_id());

			System.out.println(pstmt);

			rs = pstmt.executeUpdate();

			if (rs > 0) {
				result = "true";
			} else {
				result = "false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		return result;

	}

	@Override
	public List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_GET_CALLED_FOR_EVALUATION_DETAILS_LIST);
			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			pstmt.setString(7, "%" + searchName + "%");
			pstmt.setString(8, "%" + searchName + "%");
			pstmt.setString(9, "%" + searchName + "%");
			System.out.println("DAOIMPL" + pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));

				if (rs.getString("group_name") == null
						|| rs.getString("group_name") == ""
						|| rs.getString("group_name").isEmpty() == true) {
					vo.setClassname(rs.getString("classdetails_name_var"));
				} else {
					vo.setClassname(rs.getString("classdetails_name_var")
							+ " - " + rs.getString("group_name"));
				}

				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs
						.getString("dateofBirthId")));
				vo.setStatus(rs.getString("status"));
				vo.setAppointment_date(rs.getString("appointment_date") + " ; "
						+ rs.getString("appointment_time"));

				if (rs.getString("evaluation_recomendation_status") == null
						|| rs.getString("evaluation_recomendation_status") == "") {
					vo.setRemarks("Not Yet Done");
				} else {
					vo.setRemarks(rs
							.getString("evaluation_recomendation_status"));

				}

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	}

	@Override
	public List<ParentRequiresAppointmentVO> FinalAdmisssionList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_FINAL_ADMISSION_DETAILS_LIST);

			System.out.println("DAOIMPL" + pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setParentId(rs.getString("parentId"));
				vo.setMobile_number(rs.getString("mobile_number"));

				if (rs.getString("group_name") == null|| rs.getString("group_name") == ""|| rs.getString("group_name").isEmpty() == true) {
					vo.setClassname(rs.getString("classdetails_name_var"));
				} else {
					vo.setClassname(rs.getString("classdetails_name_var")
							+ " - " + rs.getString("group_name"));
				}

				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId")));
				vo.setStatus(rs.getString("status"));
				vo.setRemarks(rs.getString("evaluation_recomendation_status"));
				vo.setEvaluated_by(rs.getString("evaluation_done_by"));
				vo.setEvaluation_date(HelperClass.convertDatabaseToUI(rs.getString("evaluated_date")));
				vo.setMax_marks(rs.getString("max_marks"));
				vo.setMarks_secured(rs.getString("marks_secured"));
				vo.setApplication_recieved_through(rs.getString("application_recieved_by"));
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	}

	@Override
	public ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//PreparedStatement pstmt1 = null;
		//ResultSet rs1 = null;
		ParentRequiresAppointmentVO vo = new ParentRequiresAppointmentVO();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SINGLE_FINAL_ADMISSION_DETAILS_LIST);

			pstmt.setString(1, detailsVo.getTemporary_id());

			System.out.println(pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setTemporary_id(rs.getString("temporary_admission_id"));
				vo.setStudentfirstName(rs.getString("studentfirstName"));
				vo.setDateofBirthId(rs.getString("dateofBirthId"));
				vo.setClassname(rs.getString("classname"));
				vo.setGroup_name(rs.getString("group_name"));
				vo.setAccyear(rs.getString("accyear"));
				vo.setAppointment_date(rs.getString("appointment_date"));
				vo.setAppointment_time(rs.getString("appointment_time"));
				
				vo.setStatus(rs.getString("evaluation_recomendation_status"));
				vo.setEvaluated_by(rs.getString("evaluation_done_by"));
				vo.setEvaluation_date(HelperClass.convertDatabaseToUI(rs.getString("evaluated_date")));
				vo.setMax_marks(rs.getString("max_marks"));
				vo.setMarks_secured(rs.getString("marks_secured"));
				vo.setEvaluator_remarks(rs.getString("evaluator_remarks").trim());
				vo.setTest_type(rs.getString("test_type"));
				
			}
			
			
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return vo;

	}

	@Override
	public String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmcount = null;
		PreparedStatement academicYearpre = null;

		ResultSet rs = null;

		Timestamp t = HelperClass.getCurrentTimestamp();

		Connection conn = null;

		String temporary_admission_id = null;
		String classname = null;
		String academic_year = null;
		String parentname = null;
		String mobilenumber = null;
		String emailId = null;
		String dob = null;
		String student_name = null;
		String addresss = null;
		String relationship_with_person = null;
		String group = null;
		String application_recieved_by=null;
		String stuAdmissionNo=null;
		String stream_id = null;
		String section_id = null;
		String studentphoto = null;
		int result = 0;
		String parentId = null;
		String relationship = null;
		String query = null; 
		String rolecode=null;
		String query2=null;
		String id = null;
		
		String finalresult =null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.FINAL_FETCHING_DETAILS_FROM_TABLE);
			
			pstmt.setString(1, details.getTemporary_id());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next())
		
			{
				 temporary_admission_id = rs.getString("temporary_admission_id");
				 classname = rs.getString("classname");
				 academic_year = rs.getString("accyear");
				 parentname = rs.getString("parentId");
				 mobilenumber = rs.getString("mobile_number");
				 emailId = rs.getString("emailId");
				 dob = rs.getString("dateofBirthId");
				 student_name = rs.getString("studentfirstName");
				 addresss = rs.getString("address");
				 relationship_with_person = rs.getString("relationship");
				 group = rs.getString("group_name");
				 application_recieved_by=rs.getString("application_recieved_by");
						//Student Registration Started
						conn = JDBCConnection.getSeparateConnection();
			
						academicYearpre = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR_BYID);
						academicYearpre.setString(1, academic_year);

						ResultSet rsacademicYear = academicYearpre.executeQuery();

						String academicYear = "";

						while (rsacademicYear.next()) {
							academicYear = rsacademicYear.getString("acadamic_year");
						}
						academicYearpre.close();
						
						
						pstmcount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_ACADEMIC_YEAR);
						ResultSet rs1 = pstmcount.executeQuery();
						
						rs1.next();
						int count = rs1.getInt(1);


							stuAdmissionNo = StringUtilContants.STUDENT_ADMISSION_NO + "-";
						if (count < 9) {
							int i = count;
							stuAdmissionNo = stuAdmissionNo + "000" + (++i) + "/"
									+ academicYear;
						} else if (count < 99) {
							int i = count;
							stuAdmissionNo = stuAdmissionNo + "00" + (++i) + "/"
									+ academicYear;
						} else if (count < 999) {
							int i = count;
							stuAdmissionNo = stuAdmissionNo + "0" + (++i) + "/"
									+ academicYear;
						} else {
							int i = count;
							stuAdmissionNo = stuAdmissionNo + (++i) + "/" + academicYear;
						}

						PreparedStatement pstmclasscount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_CLASS);
						pstmclasscount.setString(1, classname);
						ResultSet rsClass = pstmclasscount.executeQuery();
						rsClass.next();
						int classcount = rsClass.getInt(1);

						String studentRegNo = null;
							studentRegNo = StringUtilContants.STUDENT_REGISTRATION_NO + "-";

						if (classcount < 9) {
							int i = classcount;
							studentRegNo = studentRegNo + "000" + (++i);
						} else if (classcount < 99) {
							int i = classcount;
							studentRegNo = studentRegNo + "00" + (++i);
						} else if (classcount < 999) {
							int i = classcount;
							studentRegNo = studentRegNo + "0" + (++i);
						} else {
							int i = classcount;
							studentRegNo = studentRegNo + (++i);
						}

						rsacademicYear.close();
						
						
						String select_stream_id ="select classstream_id_int from campus_classdetail where classdetail_id_int = ?";
						PreparedStatement max_log_statement = conn.prepareStatement(select_stream_id);
						max_log_statement.setString(1, classname);
						
						rs=max_log_statement.executeQuery();
						
						while(rs.next())
						{
							stream_id=rs.getString("classstream_id_int");
							
							
						}
						
						
						String select_section_id ="select classsection_id_int from campus_classsection where classdetail_id_int = ? limit 1";
						PreparedStatement log_statement = conn.prepareStatement(select_section_id);
						
						log_statement.setString(1, classname);
						System.out.println(log_statement);
						rs=log_statement.executeQuery();
						
						while(rs.next())
						{
							section_id=rs.getString("classsection_id_int");
							System.out.println("section_idsection_idsection_id"+section_id);
						}
							//To store  student details
						
						 id = IDGenerator.getPrimaryKeyID("campus_student");
							
							PreparedStatement preparedStatement = conn
									.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_REGISTRATION);

							Timestamp createdDate = HelperClass.getCurrentTimestamp();
							
							preparedStatement.setString(1,id );
							preparedStatement.setString(2, studentRegNo); //For roll number
							preparedStatement.setString(3,id ); //For addmission Number
							preparedStatement.setString(4, stream_id.trim());
							preparedStatement.setString(5, academic_year.trim());
							preparedStatement.setString(6, classname.trim());
							preparedStatement.setString(7, section_id);
							preparedStatement.setString(8, stuAdmissionNo); // For registartion number
							preparedStatement.setString(9, student_name.trim());
							preparedStatement.setString(10, " ");
							preparedStatement.setString(11, dob.trim());
							preparedStatement.setString(12, "");
							preparedStatement.setString(13, "");
							preparedStatement.setInt(14, 0);
							preparedStatement.setString(15,details.getStudentPhoto().trim());
							preparedStatement.setDate(16, HelperClass.getCurrentSqlDate());
							preparedStatement.setString(17, "");
							preparedStatement.setString(18, "Indian");
							preparedStatement.setString(19, "");
							preparedStatement.setString(20, "NOTPROMOTED".trim());
							preparedStatement.setString(21, "");
							preparedStatement.setString(22, "");
							preparedStatement.setString(23, "");
							preparedStatement.setString(24,"");
							preparedStatement.setString(25, "active");
							preparedStatement.setString(26, "");
							preparedStatement.setString(27, "");
							preparedStatement.setString(28, details.getCurrent_user());
							preparedStatement.setTimestamp(29, createdDate);
							preparedStatement.setString(30,"CQA1");
							preparedStatement.setString(31, "");
							preparedStatement.setString(32, "");
							preparedStatement.setString(33, "N");
							preparedStatement.setString(34, "N");
							preparedStatement.setString(35, "N");
							preparedStatement.setString(36, "N");
							preparedStatement.setString(37, "");
							preparedStatement.setString(38, "");
							preparedStatement.setString(39, "");
							preparedStatement.setString(40, "");
							preparedStatement.setString(41, "");
							preparedStatement.setString(42, "");
							
							
							result = preparedStatement.executeUpdate();
							
							System.out.println(preparedStatement);
							
							preparedStatement.close();
							//Student Registration Completed
							
							//Parent Registration Started
							
							PreparedStatement preParentReg = (PreparedStatement) JDBCConnection.getStatement(StudentRegistrationSQLUtilConstants.PARENT_REG);
							
							 parentId = IDGenerator.getPrimaryKeyID("campus_parents");
							
							String parentUserName = parentId.substring(3);

							if (relationship_with_person.equals("father")) {
								
								relationship = "father";
								String fatherName = parentname;
								fatherName = fatherName.replaceAll(" ", "");
								
								preParentReg.setString(1, parentId);
								preParentReg.setString(2, parentname);
								preParentReg.setString(3, " ");
								preParentReg.setString(4, mobilenumber);
								preParentReg.setString(5, emailId);
								preParentReg.setString(6, "");
								preParentReg.setString(7, "");
								preParentReg.setString(8, "");
								preParentReg.setString(9, "");
								preParentReg.setString(10, "");
								preParentReg.setString(11, "");
								preParentReg.setString(12, "");
								preParentReg.setString(13, "");
								preParentReg.setString(14, "");
								preParentReg.setString(15, fatherName.concat(parentUserName));
								preParentReg.setString(16, fatherName.concat(parentUserName));
								preParentReg.setString(17, addresss);
								preParentReg.setString(18, details.getCurrent_user());
								preParentReg.setTimestamp(19, HelperClass.getCurrentTimestamp());
								preParentReg.setString(20, "Active");
								
								
								query   ="select RoleCode from campus_role where RoleName LIKE '%PAR%' limit 1";
								PreparedStatement statement = conn.prepareStatement(query);
								rs=statement.executeQuery();
								while(rs.next())
								{
									rolecode = rs.getString("RoleCode");
									
								}
								query   ="insert into campus_user (usercode, employeecode, username, password, role,type,createuser,createdate)values(?,?,?,?,?,?,?,?)";
								PreparedStatement statement1 = conn.prepareStatement(query);
								statement1.setString(1, IDGenerator.getPrimaryKeyID("campus_user"));
								statement1.setString(2,parentId);
								statement1.setString(3, fatherName.concat(parentUserName));
								statement1.setString(4, fatherName.concat(parentUserName));
								statement1.setString(5, rolecode);
								statement1.setString(6, "perent");
								statement1.setString(7, details.getCurrent_user());
								statement1.setTimestamp(8, t);
								System.out.println(statement1);
								int result2=statement1.executeUpdate();
								
								
								
								query   ="insert into campus_parentchildrelation (parentid, stu_addmissionNo, relationship)values(?,?,?)";
								PreparedStatement statement2 = conn.prepareStatement(query);
								statement2.setString(1, parentId);
								statement2.setString(2,id);
								statement2.setString(3, "father");
								
								System.out.println(statement2);
								int result3=statement2.executeUpdate();
								

								Thread.sleep(1000);
								if (emailId != null) {
									new SendMail().sendMailtoChild(emailId, fatherName
											.concat(parentUserName), fatherName
											.concat(parentUserName));
								}
								
							} else if (relationship_with_person.equals("mother")) {
								
								relationship = "mother";
								String motherName = parentname;
								motherName = motherName.replaceAll(" ", "");
								
								preParentReg.setString(1, parentId);
								preParentReg.setString(2, "");
								preParentReg.setString(3, "");
								preParentReg.setString(4, "");
								preParentReg.setString(5, "");
								preParentReg.setString(6, "");
								preParentReg.setString(7, parentname);
								preParentReg.setString(8, mobilenumber);
								preParentReg.setString(9, "");
								preParentReg.setString(10, emailId);
								preParentReg.setString(11, "");
								preParentReg.setString(12, "");
								preParentReg.setString(13, "");
								preParentReg.setString(14, "");
								preParentReg.setString(15, motherName.concat(parentUserName));
								preParentReg.setString(16, motherName.concat(parentUserName));
								preParentReg.setString(17, addresss);
								preParentReg.setString(18, details.getCurrent_user());
								preParentReg.setTimestamp(19, HelperClass.getCurrentTimestamp());
								preParentReg.setString(20, "Active");
								
								query   ="select RoleCode from campus_role where RoleName LIKE '%PAR%' limit 1";
								PreparedStatement statement = conn.prepareStatement(query);
								rs=statement.executeQuery();
								while(rs.next())
								{
									rolecode = rs.getString("RoleCode");
									
								}
								query   ="insert into campus_user (usercode, employeecode, username, password, role,type,createuser,createdate)values(?,?,?,?,?,?,?,?)";
								PreparedStatement statement1 = conn.prepareStatement(query);
								
								statement1.setString(1, IDGenerator.getPrimaryKeyID("campus_user"));
								statement1.setString(2,parentId);
								statement1.setString(3, motherName.concat(parentUserName));
								statement1.setString(4, motherName.concat(parentUserName));
								statement1.setString(5, rolecode);
								statement1.setString(6, "perent");
								statement1.setString(7, details.getCurrent_user());
								statement1.setTimestamp(8, t);
								System.out.println(statement1);
								int result2=statement1.executeUpdate();
								
								query   ="insert into campus_parentchildrelation (parentid, stu_addmissionNo, relationship)values(?,?,?)";
								PreparedStatement statement2 = conn.prepareStatement(query);
								statement2.setString(1, parentId);
								statement2.setString(2,id);
								statement2.setString(3, "mother");
								
								System.out.println(statement2);
								int result3=statement2.executeUpdate();
								
								

								Thread.sleep(1000);
								if (emailId != null) {
									new SendMail().sendMailtoChild(parentname, motherName
											.concat(parentUserName), motherName
											.concat(parentUserName));
								}
							
							} else {
								relationship = "guardian";

								String guardianName = parentname;
								guardianName = guardianName.replaceAll(" ", " ");
								
								preParentReg.setString(1, parentId);
								preParentReg.setString(2, "");
								preParentReg.setString(3, "");
								preParentReg.setString(4, "");
								preParentReg.setString(5, "");
								preParentReg.setString(6, "");
								preParentReg.setString(7, "");
								preParentReg.setString(8, "");
								preParentReg.setString(9, "");
								preParentReg.setString(10, "");
								preParentReg.setString(11, "");
								preParentReg.setString(12, parentname);
								preParentReg.setString(13, emailId);
								preParentReg.setString(14, mobilenumber);
								preParentReg.setString(15, guardianName.concat(parentUserName));
								preParentReg.setString(16, guardianName.concat(parentUserName));
								preParentReg.setString(17, addresss);
								preParentReg.setString(18, details.getCurrent_user());
								preParentReg.setTimestamp(19, HelperClass.getCurrentTimestamp());
								preParentReg.setString(20, "Active");

								query   ="select RoleCode from campus_role where RoleName LIKE '%PAR%' limit 1";
								PreparedStatement statement = conn.prepareStatement(query);
								rs=statement.executeQuery();
								while(rs.next())
								{
									rolecode = rs.getString("RoleCode");
									
								}
								query   ="insert into campus_user (usercode, employeecode, username, password, role,type,createuser,createdate)values(?,?,?,?,?,?,?,?)";
								PreparedStatement statement1 = conn.prepareStatement(query);
								
								statement1.setString(1, IDGenerator.getPrimaryKeyID("campus_user"));
								statement1.setString(2,parentId);
								statement1.setString(3, guardianName.concat(parentUserName));
								statement1.setString(4, guardianName.concat(parentUserName));
								statement1.setString(5, rolecode);
								statement1.setString(6, "perent");
								statement1.setString(7, details.getCurrent_user());
								statement1.setTimestamp(8, t);
								System.out.println(statement1);
								int result2=statement1.executeUpdate();
								
								query   ="insert into campus_parentchildrelation (parentid, stu_addmissionNo, relationship)values(?,?,?)";
								PreparedStatement statement2 = conn.prepareStatement(query);
								statement2.setString(1, parentId);
								statement2.setString(2,id);
								statement2.setString(3, "guardian");
								
								System.out.println(statement2);
								int result3=statement2.executeUpdate();
								
								Thread.sleep(1000);
								
								
								if (emailId!= null) {
									new SendMail().sendMailtoChild(emailId, guardianName
											.concat(parentUserName), guardianName
											.concat(parentUserName));
								}
							}

							
							preParentReg.execute();
							preParentReg.close();
							
						
							query2   ="update campus_temporary_admisssion_details set is_director_approved =? ,director_remarks=? where temporary_admission_id =?";
							PreparedStatement statement2 = conn.prepareStatement(query2);
							statement2.setString(1, details.getPrincipal_status());
							statement2.setString(2,details.getPrincipal_remarks());
							statement2.setString(3, temporary_admission_id);
							
							System.out.println(statement2);
							 result=statement2.executeUpdate();	
							
					}
			
			if(result>0)
			{
				finalresult ="true";
			}
				 
			else
			{
				finalresult ="false";
			}
				
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		return finalresult;

	}


	@Override
	public ArrayList<AryasmartschoolVo> getimageName(String tempId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRrequiresApp..: getimageName : Starting");
		PreparedStatement pstmt=null;
		Connection conn= null;
		ResultSet rs=null;
		String id=null;
		ArrayList<AryasmartschoolVo> obj=new ArrayList<AryasmartschoolVo>();
		String imageUrl=null;
		try{
			
			conn =JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.GETDETAILS);
			pstmt.setString(1, tempId);
			
			System.out.println(pstmt);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				AryasmartschoolVo list=new AryasmartschoolVo();
				list.setStudentfirstName(rs.getString("studentfirstName"));
				System.out.println(rs.getString("studentfirstName"));
				list.setGender(rs.getString("gender"));
				list.setDateofBirthId(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId")));
				System.out.println(rs.getString("dateofBirthId"));
				list.setNationality(rs.getString("nationality"));
				list.setState(rs.getString("state"));
				list.setReligion(rs.getString("religion"));
				list.setCaste(rs.getString("caste"));
				list.setIsscst(rs.getString("isscst"));
				list.setMothertongue(rs.getString("mothertongue"));
				list.setPeraddress(rs.getString("permanentaddress"));
				list.setPreaddress(rs.getString("addressofcommunication"));
				list.setTemporary_fathermobile(rs.getString("mobile_number"));
				list.setMothermobile(rs.getString("mothermobile"));
				list.setTemporary_fathername(rs.getString("parentId"));
				list.setFoccupation(rs.getString("foccupation"));
				list.setFqualification(rs.getString("fatherqualification"));
				list.setFofficialaddress(rs.getString("fofficialaddress"));
				list.setFincome(rs.getString("fathermonthincome"));
				list.setMothername(rs.getString("mothername"));
				list.setMqualification(rs.getString("mqualification"));
				list.setMoccupation(rs.getString("moccupation"));
				list.setMincome(rs.getString("mincome"));
				list.setMofficialaddress(rs.getString("mofficialaddress"));
				list.setIsSibling(rs.getString("issibilings"));
				list.setIsaluminiFather(rs.getString("isaluminifather"));
				list.setIsaluminiMother(rs.getString("isaluminimother"));
				list.setFaluminiyear(rs.getString("faluminiyear"));
				list.setMaluminiyear(rs.getString("maluminiyear"));
				list.setSiblingname(rs.getString("siblingname"));
				list.setSiblingclass(rs.getString("siblingclass"));
				obj.add(list);
				
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs !=null && (!rs.isClosed())){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		System.out.println(obj);
		return obj;
	}

	@Override
	public ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRrequiresApp..: downloadsecadmissionapplication : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SecondAdmissionformVo> list = new ArrayList<SecondAdmissionformVo>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt= conn.prepareStatement(SQLUtilConstants.GETSECONDFORM);
			pstmt.setString(1, parameter);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				SecondAdmissionformVo vo=new SecondAdmissionformVo();
				vo.setStudentfirstName((rs.getString("name")));
				vo.setDateofBirthId(HelperClass.convertDatabaseToUI(rs.getString("dateofbirth")));
				vo.setDistance(rs.getString("distance_from_school"));
				vo.setGender(rs.getString("gender"));
				vo.setTemporary_fathername(rs.getString("fathername"));
				vo.setMothername(rs.getString("mothername"));
				vo.setNationality(rs.getString("nationality"));
				vo.setState(rs.getString("state"));
				vo.setReligion(rs.getString("religion"));
				vo.setCaste(rs.getString("caste"));
				vo.setMothertongue(rs.getString("mothertongue"));
				vo.setAadhaarno(rs.getString("aadhaarno"));
				vo.setFqualification(rs.getString("qual_father"));
				vo.setMqualification(rs.getString("qual_mother"));
				vo.setCurr_adclass(rs.getString("current_admission_class"));
				vo.setMoccupation(rs.getString("occu_mother"));
				vo.setFoccupation(rs.getString("occu_father"));
				vo.setMdesignation(rs.getString("desg_mother"));
				vo.setFdesignation(rs.getString("desg_father"));
				vo.setFofficialaddress(rs.getString("official_add_father"));
				vo.setMofficialaddress(rs.getString("official_add_mother"));
				vo.setFincome(rs.getString("father_month_income"));
				vo.setMincome(rs.getString("mother_month_income"));
				vo.setFperaddress(rs.getString("father_per_address"));
				vo.setMperaddress(rs.getString("mother_per_address"));
				vo.setCom_address(rs.getString("comm_address"));
				vo.setMobile_number(rs.getString("phone_no"));
				
				
				if(rs.getString("previous_school") == null){
					vo.setTemporary_school_name("");
				}else{
					vo.setTemporary_school_name(rs.getString("previous_school"));
				}
				
				
				vo.setSecond_lang(rs.getString("second_language"));
				vo.setThird_lang(rs.getString("third_language"));
				vo.setStudy_scheme(rs.getString("study_scheme"));
				
				if(rs.getString("extra_curricular") == null){
					
					vo.setExtra_curricular("");
				}else{
					
					vo.setExtra_curricular(rs.getString("extra_curricular"));
						
				}
				
				vo.setIsSibling(rs.getString("IsSibling"));
				if(rs.getString("IsSibling").equalsIgnoreCase("Y")){
					vo.setSiblingname(rs.getString("name_sibling"));
					vo.setSiblingclass(rs.getString("class_sibling"));
				}
				else{
					vo.setSiblingname("-");
					vo.setSiblingclass("-");
				}
				
				vo.setIsaluminiFather(rs.getString("isaluminifather"));
				if(rs.getString("isaluminifather").equalsIgnoreCase("Y")){
					vo.setFaluminiyear(rs.getString("father_alumini_year"));
				}
				else{
					vo.setFaluminiyear("-");
				}
				
				vo.setIsaluminiMother(rs.getString("isaluminimother"));
				if(rs.getString("isaluminimother").equalsIgnoreCase("Y")){
					
					vo.setMaluminiyear(rs.getString("mother_alumini_year"));
				}else{
					vo.setMaluminiyear("-");
				}
				
				
				
				
				list.add(vo);
				
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs !=null && (!rs.isClosed())){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		
		return list;
	}



	@Override
	public String Insertthirdadmissionformat(
			ParentRequiresAppointmentForm parentform) {

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
			pstmt = conn.prepareStatement("insert into campus_temporary_admisssion_details (temporary_admission_id,studentfirstName,studentlastname,dateofBirthId,gender,nationality,placeofbirth,state,religion,othersreligion,caste,othercaste,castecategory,othercastecategory,mothertongue,aadharNo,permanentaddress,addressofcommunication,previousschool,schemeofstudy,otherboard,qualify_name,extraactivity,classname,group_prefered,opt_subjects,fathername,fathermobileno,fatheroccupation,fatherdesignation,fatherdepartment,fathermonthincome,fatheremailid,fatherofficialaddress,mothername,mothermobile,motheroccupation,motherdesignation,motherdepartment,mothermothlyincome,motheremailid,motherofficialaddress,createdTime,imageUrl,marksheetupload,enquiryid,stream,schoollocation,fatherqualification,motherqualification,preferedphno,TC,Migration,comminication_landline,landline_no,father_office_landline,mother_office_landline,emergency_no) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
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
			
				pstmt.setString(54, parentform.getMothPerAddLandLiNo());
				pstmt.setString(55, parentform.getFatPerAddLandLiNo());
				pstmt.setString(56, parentform.getFatOffiAddLandLiNo());
				pstmt.setString(57, parentform.getMothOffiAddLandLiNo());
				pstmt.setString(58, parentform.getEmergencyNo());
			
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

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		return result;

	}

	@Override
	public List<secadmissionformformatVO> getsecformadmissiondetails() {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: getsecformadmissiondetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORM_DETAILSLIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secadmissionformformatVO vo = new secadmissionformformatVO();
				vo.setTemp_regid(rs.getString("temporary_admission_id"));
				vo.setStudentname(rs.getString("studentname"));
				vo.setDateofBirth(rs.getString("dateofBirthId"));
				vo.setFatherName(rs.getString("fathername"));
				vo.setFatherMobileNo(rs.getString("fathermobileno"));
				vo.setSchemeofstudy(rs.getString("schemeofstudy"));
				vo.setCreated_date(rs.getString("createdTime"));

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
				+ " Control in Inventory_DAOIMPL: getsecformadmissiondetails : Ending");
		return list;
	
	}

	@Override
	public List<ThirdformformatVO> getthirdadmissiondetailslist() {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ThirdformformatVO> list = new ArrayList<ThirdformformatVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_THIRD_FORM_DETAILSLIST);
			System.out.println("get list details:" +pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ThirdformformatVO vo = new ThirdformformatVO();

				vo.setStu_name(rs.getString("stu_name"));
				vo.setDob(rs.getString("dateofbirth"));
				vo.setPre_school(rs.getString("previous_school"));
                vo.setTotal_matrks(rs.getString("total_marks"));
			



				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
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
		return list;

	

	}

	@Override
	public ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String param) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentRrequiresApp..: downloadsecadmissionapplication : Starting");
		PreparedStatement pstmt=null;
		Connection conn=null;
		ResultSet rs=null;
		ArrayList<ThirdAddmissionApplicationVo> list=new ArrayList<ThirdAddmissionApplicationVo>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.DOWNLOADFORM);
			pstmt.setString(1, param);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				ThirdAddmissionApplicationVo obj = new ThirdAddmissionApplicationVo();
				obj.setStudentfirstName(rs.getString("stu_name"));
				obj.setGender(rs.getString("gender"));
				obj.setMothertongue(rs.getString("mother_tongue"));
				obj.setDateofBirthId(HelperClass.convertDatabaseToUI(rs.getString("dateofbirth")));
				obj.setBirth_place(rs.getString("birth_place")+" - "+rs.getString("state"));
				obj.setNationality(rs.getString("nationality"));
				obj.setReligion(rs.getString("religion")+" - "+rs.getString("caste"));
				obj.setBelongsto(rs.getString("belongsto"));
				obj.setAadhaarno(rs.getString("aadhaar_No"));
				obj.setPer_address(rs.getString("Permanent_address"));
				obj.setPre_address(rs.getString("Present_address"));
				obj.setTemporary_fathername(rs.getString("father_name"));
				obj.setMothername(rs.getString("mother_name"));
				obj.setMoccupation(rs.getString("mother_occupation"));
				obj.setFoccupation(rs.getString("father_occupation"));
				obj.setFofficialaddress(rs.getString("fofficial_address"));
				obj.setMofficialaddress(rs.getString("mofficial_address"));
				obj.setMincome(rs.getString("m_income"));
				obj.setFincome(rs.getString("f_income"));
				obj.setMothermobile(rs.getString("m_mobile"));
				obj.setTemporary_fathermobile(rs.getString("f_mobile"));
				obj.setQ_name(rs.getString("q_name")+" - "+rs.getString("board"));
				obj.setTemporary_school_name(rs.getString("previous_school"));
				obj.setEnglish(rs.getString("english"));
				obj.setMathematics(rs.getString("mathematics"));
				obj.setScience(rs.getString("science"));
				obj.setSocial(rs.getString("social"));
				obj.setSecond_lang(rs.getString("sec_language"));
				obj.setOpt_subone(rs.getString("opt_sub_one"));
				obj.setOpt_subtwo(rs.getString("opt_sub_two"));
				obj.setOpt_subthree(rs.getString("opt_sub_three"));
				obj.setOpt_subfour(rs.getString("opt_sub_four"));
				obj.setSubject_one(rs.getString("subject1"));
				obj.setSubject_two(rs.getString("subject2"));
				obj.setSubject_three(rs.getString("subject3"));
				obj.setSubject_four(rs.getString("subject4"));
				obj.setSubject_five(rs.getString("subject5"));
				obj.setImageurl(rs.getString("student_image"));
				System.out.println(rs.getString("student_image"));
				list.add(obj);
				
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally{
			
			try{
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				
				e.printStackTrace();
			}
			
			
		}
		
		return list;
	}

	@Override
	public String getsecform() {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sec_adm_id=null;
		String  currid="";
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt =conn.prepareStatement(SQLUtilConstants.GET_SECTEMP_ID);
			   rs = pstmt.executeQuery();
				while(rs.next())
				{
					sec_adm_id=rs.getString("temp_secadmid");
				String regno=sec_adm_id.substring(0,3);	
				String befsplit=sec_adm_id.substring(3);
				
				
				int preid =Integer.parseInt(befsplit);
		        currid = regno + ++preid;
				System.out.println("curid:" +currid);
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
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
		return currid;
	}

	@Override
	public List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("select accyearid,student_first_name,student_last_name,email,mobile_number,address,stu_parrelationship,stream_id,class_id,parents_name from campus_parent_enquiry_details where enquiry_id=? and status='approved'");
			pst.setString(1, appointmentVo.getEnquiryId().trim());
			System.out.println("admission list "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				appointmentVo.setStudentfirstName(rs.getString("student_first_name"));
				appointmentVo.setStudentLastName(rs.getString("student_last_name"));
				appointmentVo.setStream(rs.getString("stream_id"));
				appointmentVo.setClassid(rs.getString("class_id"));
				appointmentVo.setRelationship(rs.getString("stu_parrelationship"));
				appointmentVo.setParents(rs.getString("parents_name"));
				appointmentVo.setMobile_number(rs.getString("mobile_number"));
				appointmentVo.setEmailId(rs.getString("email"));
				appointmentVo.setAddress(rs.getString("address"));
				appointmentVo.setAccyearid(rs.getString("accyearid"));
				list.add(appointmentVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");
		return list;
	}

	@Override
	public String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateRollNumber Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			preparedStatement = conn.prepareStatement(SQLUtilConstants.VALIDATE_ENQUIRYNO);
			preparedStatement.setString(1, appointmentVo.getEnquiryId().trim());
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			} else {
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validateRollNumber Ending");
		return successMessage;

	}


}
