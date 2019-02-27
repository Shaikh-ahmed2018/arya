package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.SubjectSqlUtils;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LocationVO;

public class FormsManagementdaoImpl {
	private static final Logger logger = Logger
			.getLogger(NewRegistrationDaoImpl.class);
	   String status=null;
	   public ArrayList<Issuedmenuvo> getissuedforms() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
        Connection conn = null;
        try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_ISSUED_FORM_DETAILSLIST);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
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
	@SuppressWarnings("null")
	public String insertapprovedlist(String idList, String reason, String othereason, String mobile_number) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: insertapprovedlist : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rt=null;
		int rs = 0;
		int userCount=0;
		String msg = null;
		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";
		String classname="";


		try{
			conn = JDBCConnection.getSeparateConnection();
            conn.setAutoCommit(false);
			if(othereason!="" && othereason!=null){
				reason = othereason;
			}
            
			pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_APPROVED_DETAILS);
			pstmt.setString(1,reason);
			pstmt.setString(2,idList.trim());
			rs = pstmt.executeUpdate();
			if(rs > 0){
				msg = "Approved";
				Issuedmenuvo vo = new Issuedmenuvo();
				IDGenerator id=new IDGenerator();
				System.out.println("mobile_numberDao="+mobile_number);
				pstmt2=conn.prepareStatement("INSERT INTO campus_user (usercode,employeecode,username,password,role,type) VALUES(?,?,?,?,?,?)");
				pstmt2.setString(1, IDGenerator.getPrimaryKeyID("campus_user"));
				pstmt2.setString(2, idList.trim());
				pstmt2.setString(3, mobile_number);
				pstmt2.setString(4, mobile_number);
				pstmt2.setString(5, "ENQUIRY");
				pstmt2.setString(6, "ENQUIRY");
				userCount=pstmt2.executeUpdate();
				
				pstmt1 =conn.prepareStatement(SQLUtilConstants.GET_MAIL_DETAILS_APPROVE);
				pstmt1.setString(1, idList.trim());
                rt = pstmt1.executeQuery();
				while(rt.next())
				{
					vo.setParentEmailId(rt.getString("email"));
					vo.setParentfirstName(rt.getString("student_first_name"));
					vo.setParents_name(rt.getString("parents_name"));
					vo.setReason(rt.getString("reason"));
					vo.setClassid(rt.getString("class_id"));
					vo.setClassName(rt.getString("classname"));
					parentname=vo.getParents_name();
					stuname=vo.getParentfirstName();
					mailid=vo.getParentEmailId();
					reason=vo.getReason();
					classname=vo.getClassName();
					if(mailid!=null)
					{
						new SendMail().sendMailtoParents(parentname, mailid,stuname,reason,idList,classname,mobile_number);

					}
				}
			}
			else{
				msg ="Not Approved";
			}
			conn.commit();
		}catch(Exception e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{

			try{
				if (rt != null && (!rt.isClosed())) {
					rt.close();
				}

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				else if(rt!=null && (!rt.isClosed())){
					rt.close();
				}
				else if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: insertapprovedlist : Ending");
		return msg;
	}
	public ArrayList<Issuedmenuvo> getApprovedForms() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: getApprovedForms : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_APPROVED_FORM_DETAILSLIST);
			System.out.println("get list details:" +pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				vo.setReason(rs.getString("reason"));
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
				+ " Control in FormsManagementdaoImpl: getApprovedForms : Ending");
		return list;


	}
	public String insertrejectedList(String idList, String rejectreason, String otherrsn) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rt = null;
		int rs = 0;
		String msg = null;
		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";

		try{
			if(otherrsn!="" && otherrsn!=null){
				rejectreason = otherrsn;
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_REJECTED_DETAILS);
			pstmt.setString(1, rejectreason);
			pstmt.setString(2, idList.trim());
			System.out.println("Approval page:" +pstmt);
			rs = pstmt.executeUpdate();
			if(rs > 0){
				msg = "Rejected";
				Issuedmenuvo vo = new Issuedmenuvo();
				pstmt1 =conn.prepareStatement(SQLUtilConstants.GET_MAIL_DETAILS_REJECT);
				pstmt1.setString(1, idList.trim());
				rt = pstmt1.executeQuery();
				while(rt.next())
				{
					vo.setParentEmailId(rt.getString("email"));
					vo.setParentfirstName(rt.getString("student_first_name"));
					vo.setParents_name(rt.getString("parents_name"));
					vo.setRejectreason(rt.getString("rejectreason"));
					parentname=vo.getParents_name();
					stuname=vo.getParentfirstName();
					mailid=vo.getParentEmailId();
					rejectreason=vo.getRejectreason();
					if(mailid!=null)
					{
						new SendMail().sendMailtoParentsForreject(parentname, mailid,stuname,rejectreason);

					}
				}
			}
			else{
				msg ="Not Rejected";
			}

		}catch(Exception e){

			e.printStackTrace();

		}
		finally{

			try{
				if (rt != null && !rt.isClosed()) {
					rt.close();
				}

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}


			}
			catch(Exception e){
				e.printStackTrace();
			}


		}
		return msg;
	}
	public ArrayList<Issuedmenuvo> getRejectedlist() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: getRejectedlist : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_REJECTED_FORM_DETAILSLIST);
			System.out.println("get list details:" +pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				vo.setRejectreason(rs.getString("rejectreason"));
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
				+ " Control in FormsManagementdaoImpl: getRejectedlist : Ending");
		return list;



	}

	public Issuedmenuvo getIssueddetails(Issuedmenuvo obj1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getSubjectDetails Starting");
		Issuedmenuvo issuedlist = new Issuedmenuvo();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.GET_ISSUED_FORM_DETAILS);
			pstmt.setString(1, obj1.getEnquiryid());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) 
			{
				issuedlist.setParentfirstName(resultSet.getString("studentname"));
				issuedlist.setParentEmailId(resultSet.getString("email"));
				issuedlist.setMobile_number(resultSet.getString("mobile_number"));
				issuedlist.setAddress(resultSet.getString("address"));
				issuedlist.setStu_parrelation("stu_parrelationship");
				issuedlist.setStreamname("stream_id");
				issuedlist.setClassid("class_id");
				issuedlist.setParents_name("parents_name");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in AddSubjectDaoImpl: getSubjectDetails Ending");
		return issuedlist;

	}
	public Issuedmenuvo editIssuedForm(String edit) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();

		Connection conn = null;

		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			iss_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_ISSUED_FORM);
			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) {
				issuedMenuVO = new Issuedmenuvo();
				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO
				.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;
	}
	public List<Issuedmenuvo> searchadmformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_ISSUED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(6, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(7, "%"+searchName+"%");
			System.out.println(issuedsearch_pstmt);

			rs = issuedsearch_pstmt.executeQuery();
			while (rs.next()) {

				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				list.add(vo);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}
	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid) {
		// TODO Auto-generated method stub

		int count =0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.UPDATE_ADMISSION_DETAILS);
			pstmt.setString(1, vo.getParentfirstName().trim());
			pstmt.setString(2, vo.getParent_LastName().trim());
			pstmt.setString(3, vo.getParentEmailId().trim());
			pstmt.setString(4, vo.getMobile_number().trim());
			pstmt.setString(5, vo.getAddress().trim()); 
			pstmt.setString(6, vo.getStu_parrelation().trim()); 
			pstmt.setString(7, vo.getStreamId().trim()); 
			pstmt.setString(8, vo.getClassid().trim()); 
			pstmt.setString(9, vo.getParents_name());  
			pstmt.setString(10, enquriyid);  
			count=pstmt.executeUpdate();
			if (count > 0) 
			{
				status = "updated successfully";
			} else 
			{
				status = "update fail";
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
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
		return status;
	}
	public List<Issuedmenuvo> searchapproveformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_APPROVED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");
	         issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			
	         issuedsearch_pstmt
				.setString(6, "%"+searchName+"%");
	         issuedsearch_pstmt
				.setString(7, "%"+searchName+"%");
             System.out.println("approve search:" +issuedsearch_pstmt);
			rs = issuedsearch_pstmt.executeQuery();
			System.out.println("approvedsearch_pstmt searchhhhhhhh:" +issuedsearch_pstmt);
			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				list.add(vo);
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}
	public List<Issuedmenuvo> searchrejectformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_REJECTED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");
			
			issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(6, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(7, "%"+searchName+"%");
			System.out.println("Serrr :::"+issuedsearch_pstmt);

			rs = issuedsearch_pstmt.executeQuery();
			while (rs.next()) {

				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				/*vo.setParent_LastName(rs.getString("student_last_name"));*/
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				System.out.println("It is coming to second form daoimpl:");
				list.add(vo);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}
	public ArrayList<Issuedmenuvo> sendmailtoparents() {
		// TODO Auto-generated method stub
		return null;
	}
	//Fort Getting Details of the Approved Form
	public Issuedmenuvo apprIssuedForm(String edit) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();

		Connection conn = null;

		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			iss_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_ISSUED_FORM);

			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) 
			{
				issuedMenuVO = new Issuedmenuvo();

				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO
				.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setReason(iss_rs.getString("reason"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));


			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_pstmt != null && (!iss_pstmt.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;
	}
	public Issuedmenuvo rejectFormdetails(String edit) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();

		Connection conn = null;

		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			iss_pstmt = conn
					.prepareStatement(SQLUtilConstants.REJECT_FORM_DETAILS);
			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) {
				issuedMenuVO = new Issuedmenuvo();
				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO
				.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setRejectreason(iss_rs.getString("rejectreason"));
				issuedMenuVO.setReason(iss_rs.getString("reason"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_pstmt != null && (!iss_pstmt.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;
	}
	public String insertcancelledlist(String idList, String reason, String canreason) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		ResultSet rt=null;
		int rs = 0;
		String msg = null;
		Connection conn = null;
		try{
			if(canreason!="" && canreason!=null){
				reason = canreason;
			}
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_CANCELLED_DETAILS);
			pstmt.setString(1,	reason);
			pstmt.setString(2, idList.trim());
			rs = pstmt.executeUpdate();

			if(rs > 0){
				msg = "Cancelled";
			}
			else{
				msg ="Not Cancelled";
			}
		}catch(Exception e)
		{

			e.printStackTrace();
		}
		finally{

			try{

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				else if(rt!=null && (!rt.isClosed())){
					rt.close();
				}
				else if (conn != null && (!conn.isClosed())) {
					conn.close();
				}


			}
			catch(Exception e){
				e.printStackTrace();
			}


		}
		return msg;
	}
	public ArrayList<Issuedmenuvo> getCancelledForms() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: getCancelledForms : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_CANCELLED_FORM_DETAILSLIST);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				vo.setCancelreason(rs.getString("cancelreason"));
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
				+ " Control in FormsManagementdaoImpl: getCancelledForms : Ending");
		return list;


	}
	public Issuedmenuvo cancelFormdetails(String edit) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();
		Connection conn = null;
		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			iss_pstmt = conn.prepareStatement(SQLUtilConstants.CANCEL_FORM_DETAILS);
			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) {
				issuedMenuVO = new Issuedmenuvo();
				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO
				.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setCancelreason(iss_rs.getString("cancelreason"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));


			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_pstmt != null && (!iss_pstmt.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;



	}
	public ArrayList<Issuedmenuvo> getSubmittedForms() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: getSubmittedForms : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SUBMITTED_FORM_DETAILSLIST);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				vo.setSubmitreason(rs.getString("submitreason"));
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
				+ " Control in FormsManagementdaoImpl: getSubmittedForms : Ending");
		return list;


	}
	public ArrayList<Issuedmenuvo> getProcessedForms() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FormsManagementdaoImpl: getSubmittedForms : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();

		Connection conn = null;
		String mailid="";
		String parentname="";
		String stuname="";

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_PROCESSED_FORM_DETAILSLIST);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("studentname"));
				vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				vo.setProcessreason(rs.getString("processreason"));
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
				+ " Control in FormsManagementdaoImpl: getSubmittedForms : Ending");
		return list;


	
		
	}
	public Issuedmenuvo submitFormdetails(String edit) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();
		Connection conn = null;
		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			iss_pstmt = conn.prepareStatement(SQLUtilConstants.SUBMIT_FORM_DETAILS);
			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) {
				issuedMenuVO = new Issuedmenuvo();
				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO
				.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setSubmitreason(iss_rs.getString("submitreason"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));


			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_pstmt != null && (!iss_pstmt.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;



	}
	public Issuedmenuvo processFormdetails(String edit) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement iss_pstmt = null;
		ResultSet iss_rs = null;
		LocationVO list = new LocationVO();
		Connection conn = null;
		Issuedmenuvo issuedMenuVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			iss_pstmt = conn.prepareStatement(SQLUtilConstants.PROCESS_FORM_DETAILS);
			iss_pstmt.setString(1, edit);
			iss_rs = iss_pstmt.executeQuery();
			while (iss_rs.next()) {
				issuedMenuVO = new Issuedmenuvo();
				issuedMenuVO.setParentfirstName(iss_rs.getString("student_first_name"));
				issuedMenuVO.setParent_LastName(iss_rs.getString("student_last_name"));
				issuedMenuVO.setParentEmailId(iss_rs.getString("email"));
				issuedMenuVO.setMobile_number(iss_rs.getString("mobile_number"));
				issuedMenuVO.setAddress(iss_rs.getString("address"));
				issuedMenuVO.setStu_parrelation(iss_rs.getString("stu_parrelationship"));
				issuedMenuVO.setStreamId(iss_rs.getString("stream_id"));
				issuedMenuVO.setClassid(iss_rs.getString("class_id"));
				issuedMenuVO.setParents_name(iss_rs.getString("parents_name"));
				issuedMenuVO.setProcessreason(iss_rs.getString("processreason"));
				issuedMenuVO.setEnquiryid(iss_rs.getString("enquiry_id"));
				issuedMenuVO.setClassName(iss_rs.getString("classdetails_name_var"));
				issuedMenuVO.setStreamname(iss_rs.getString("classstream_name_var"));


			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (iss_rs != null && (!iss_rs.isClosed())) {

					iss_rs.close();
				}
				if (iss_pstmt != null && (!iss_pstmt.isClosed())) {

					iss_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return issuedMenuVO;



	}
	public List<Issuedmenuvo> searchcancelformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_CANCELLED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");
			
			issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(6, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(7, "%"+searchName+"%");
			System.out.println("Serrr :::"+issuedsearch_pstmt);

			rs = issuedsearch_pstmt.executeQuery();
			while (rs.next()) {

				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
                vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				list.add(vo);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}
	public List<Issuedmenuvo> searchsubmitformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_SUBMITTED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");
			
			issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(6, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(7, "%"+searchName+"%");
			System.out.println("Serrr :::"+issuedsearch_pstmt);

			rs = issuedsearch_pstmt.executeQuery();
			while (rs.next()) {

				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
                vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				list.add(vo);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}
	public List<Issuedmenuvo> searchprocessformDetails(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");
		List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
		PreparedStatement issuedsearch_pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			issuedsearch_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_PROCESSED_DETAILS);
			issuedsearch_pstmt
			.setString(1, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(2, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(3, "%"+searchName+"%");

			issuedsearch_pstmt
			.setString(4, "%"+searchName+"%");
			
			issuedsearch_pstmt
			.setString(5, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(6, "%"+searchName+"%");
			issuedsearch_pstmt
			.setString(7, "%"+searchName+"%");
			System.out.println("Serrr :::"+issuedsearch_pstmt);

			rs = issuedsearch_pstmt.executeQuery();
			while (rs.next()) {

				Issuedmenuvo vo = new Issuedmenuvo();
				vo.setEnquiryid(rs.getString("enquiry_id"));
				vo.setFullname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
                vo.setParentEmailId(rs.getString("email"));
				vo.setMobile_number(rs.getString("mobile_number"));
				vo.setAddress(rs.getString("address"));
				vo.setParents_name(rs.getString("parents_name"));
				list.add(vo);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (issuedsearch_pstmt != null && (!issuedsearch_pstmt.isClosed())) {

					issuedsearch_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return list;
	}

}
