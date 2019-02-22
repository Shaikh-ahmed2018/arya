package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LoginSqlUtil;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.UserDetailVO;

public class LoginDaoImpl {
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

	public LoginVo validateUserDaoImpl(String username, String password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : validateUserDaoImpl  Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		LoginVo loginvo=new LoginVo();
		try {

			String sql = null;

			con = JDBCConnection.getSeparateConnection();
			
			sql = "select usr.employeecode,usr.username,usr.password,usr.type,usr.role,CASE WHEN rol.IsDefault IS NULL THEN 'N' ELSE rol.IsDefault END IsDefault from  campus_user usr left outer join campus_role rol on rol.RoleCode = usr.role  where  usr.username = ? and usr.password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				loginvo.setUsercode(rs.getString("employeecode"));
				loginvo.setUsername(rs.getString("username"));
				loginvo.setUsertype(rs.getString("type"));
				loginvo.setUserrole(rs.getString("role"));
				loginvo.setPassword(rs.getString("password"));
				loginvo.setIsAdministrator(rs.getString("IsDefault"));
				
			}
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : validateUserDaoImpl  Ending");
		
		return loginvo;
	}
	
	public UserDetailVO loadUserDaoImpl(LoginVo loginvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadUserServiceImpl  Starting");
		UserDetailVO userDetails = new UserDetailVO();
		PreparedStatement statement = null;
		ResultSet userDetailsResultSet = null;
		
		PreparedStatement ps_permission = null;
		ResultSet rs_permission = null;
		
		HashMap<String, String> permissionmap=new HashMap<String, String>();
		
		Connection con = null;
		String roleCode=null;
		boolean flag=true;
		try {

			con=JDBCConnection.getSeparateConnection();
			System.out.println(loginvo.getUsertype());
			
			
			if(loginvo.getUsertype().equalsIgnoreCase("ENQUIRY")){

				
				statement=con.prepareStatement("SELECT cped.*,cc.classstream_name_var FROM campus_parent_enquiry_details cped join campus_classstream cc on cped.stream_id=cc.classstream_id_int WHERE enquiry_id=?");
				
				statement.setString(1, loginvo.getUsercode());
				
				System.out.println("statement::::"+statement);
				
				userDetailsResultSet = statement.executeQuery();

				while (userDetailsResultSet.next()) {

					userDetails.setUserId(userDetailsResultSet.getString("enquiry_id"));
					userDetails.setFirstName(userDetailsResultSet.getString("student_first_name"));
					userDetails.setLocationid(userDetailsResultSet.getString("LocationId"));
					userDetails.setStream(userDetailsResultSet.getString("classstream_name_var"));
					userDetails.setStreamId(userDetailsResultSet.getString("stream_id"));
					
				}
			 
		 
				
			}
			
			else if(loginvo.getUsertype().equalsIgnoreCase("parent")){
				
				PreparedStatement pstmt=con.prepareStatement(LoginSqlUtil.GET_PARENT_CHAILD_RELATION);
				pstmt.setString(1, loginvo.getUsercode());
				
				ResultSet rs=pstmt.executeQuery();
				
				String relationship=null;
				
				System.out.println("pstmt :: "+pstmt);
				
				while(rs.next()){
					
					relationship=rs.getString("relationship");
				}
			
				if(relationship.endsWith("father")){
					
					statement=con.prepareStatement(LoginSqlUtil.GET_FATHER_DETAILS);
				
				}else if(relationship.endsWith("mother")){
					
					statement=con.prepareStatement(LoginSqlUtil.GET_MOTHER_DETAILS);
				}else{
					
					statement=con.prepareStatement(LoginSqlUtil.GET_GUARDIAN_DETAILS);
				}
			
				
			
			statement.setString(1, loginvo.getUsercode());
			
			System.out.println("statement :: "+statement);
			
			userDetailsResultSet = statement.executeQuery();

			while (userDetailsResultSet.next()) {

				userDetails.setUserId(userDetailsResultSet.getString("ParentID"));
				userDetails.setFirstName(userDetailsResultSet.getString("parentname"));
				userDetails.setQualification(userDetailsResultSet.getString("Qualification"));
				userDetails.setAddress(userDetailsResultSet.getString("address"));
				userDetails.setMobileno(userDetailsResultSet.getString("mobileno"));
				userDetails.setUserName(userDetailsResultSet.getString("UserName"));
				userDetails.setGender("");
				userDetails.setEmail(userDetailsResultSet.getString("email"));
				userDetails.setPassword(userDetailsResultSet.getString("password"));
				userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
				userDetails.setRoleCode(userDetailsResultSet.getString("role"));
				userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
				roleCode=userDetailsResultSet.getString("role");
				userDetails.setUserNametype(userDetailsResultSet.getString("type"));
			}
			
		 }else{
				
				statement=con.prepareStatement(LoginSqlUtil.GET_STAFF_DETAILS);
				
				statement.setString(1, loginvo.getUsercode());
				
				System.out.println("statement::::"+statement);
				
				userDetailsResultSet = statement.executeQuery();

				while (userDetailsResultSet.next()) {

					userDetails.setUserId(userDetailsResultSet.getString("TeacherID"));
					userDetails.setFirstName(userDetailsResultSet.getString("teachername"));
					userDetails.setQualification(userDetailsResultSet.getString("qualification"));
					userDetails.setAddress(userDetailsResultSet.getString("presentAddress"));
					userDetails.setMobileno(userDetailsResultSet.getString("mobileNo"));
					userDetails.setUserName(userDetailsResultSet.getString("username"));
					userDetails.setGender(userDetailsResultSet.getString("gender"));
					userDetails.setEmail(userDetailsResultSet.getString("emailId"));
					userDetails.setPassword(userDetailsResultSet.getString("password"));
					userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
					userDetails.setRoleCode(userDetailsResultSet.getString("role"));
					userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
					userDetails.setEmployeeType(userDetailsResultSet.getString("teachingType"));
					roleCode=userDetailsResultSet.getString("role");
					System.out.println("roleCode::::"+roleCode);
					userDetails.setUserNametype(userDetailsResultSet.getString("type"));
					userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
				}
			 
		 }
			
			if(roleCode!=null || roleCode !=""){
				
				ps_permission = con.prepareStatement(LoginSqlUtil.GET_PERMISSION_DETAILS);
				ps_permission.setString(1, roleCode);
				
				System.out.println("Permission Details ::: "+ps_permission);
				
				rs_permission = ps_permission.executeQuery();
				
				while(rs_permission.next()) {
					
					flag=false;
				
					permissionmap.put(rs_permission.getString("shortName"),rs_permission.getString("isApplicable"));
							
					userDetails.setPermissionmap(permissionmap);
					
				} 
				
				if(flag){
					
					userDetails.setPermissionmap(permissionmap);
				}
				
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
			exception.printStackTrace();
			
		} finally {

			try {
				if (userDetailsResultSet != null && !userDetailsResultSet.isClosed()) {
					userDetailsResultSet.close();
				}
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadUserServiceImpl  Ending");
	
			return userDetails;
		
	}

	public int checkCurrentPassword(String currentuser, String currentPassword,
			String currentuserId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Starting");
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		Connection con = null;
		try {
			con = JDBCConnection.getSeparateConnection();
			if (currentuser.equalsIgnoreCase("userDetails")) {
				statement = con
						.prepareStatement(SQLUtilConstants.CHECK_PARENTS_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());

			} else if (currentuser.equalsIgnoreCase("Teacher")) {
				statement = con
						.prepareStatement(SQLUtilConstants.CHECK_TEACHERS_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());

			} else if (currentuser.equalsIgnoreCase("Principle")) {

				statement = con
						.prepareStatement(SQLUtilConstants.CHECK_PRINCIPAL_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());

			} else {
				statement = con
						.prepareStatement(SQLUtilConstants.CHECK_ADMIN_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());

			}
			rs = statement.executeQuery();
			while (rs.next()) {

				count = rs.getInt(1);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {

				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Ending");

		return count;
	}

	public int changePassword(String currentuser, String newpassword,
			String currentuserId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Starting");
		PreparedStatement statement = null;
		int count = 0;
		Connection con = null;
		try {
			con = JDBCConnection.getSeparateConnection();

			if (currentuser.equalsIgnoreCase("userDetails")) {
				statement = con
						.prepareStatement(SQLUtilConstants.UPDATE_PERENTS_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());

			} else if (currentuser.equalsIgnoreCase("Teacher")) {
				statement = con
						.prepareStatement(SQLUtilConstants.UPDATE_TEACHERS_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());

			} else if (currentuser.equalsIgnoreCase("Principle")) {
				statement = con
						.prepareStatement(SQLUtilConstants.UPDATE_PRINCIPAL_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());

			} else {

				statement = con
						.prepareStatement(SQLUtilConstants.UPDATE_ADMIN_PASSWORD);
				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());

			}

			count = statement.executeUpdate();

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Ending");

		return count;
	}

	public String userValidCase(String uname, String password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:userValid Starting");
	

		LoginVo lvo=new LoginVo();
		String userexist = null;
		String query = SQLUtilConstants.LOGINCHECK;

		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{


			con=  JDBCConnection.getSeparateConnection();
			pstmt =  con.prepareStatement(query); 
			pstmt.setString(1, uname);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next())
			{
				
				/*SELECT u.,u., u. ,u.role,r.RoleName roleName
				from campus_user u,campus_role r WHERE u.role=r.RoleCode and u.username= ? AND u.password= ?
		*/

				lvo.setUsername(rs.getString("username"));
				lvo.setPassword(rs.getString("password"));
				lvo.setUsercode(rs.getString("usercode"));
				
				if(uname.equals(rs.getString("username")) && password.equals(rs.getString("password"))){
					
					userexist = MessageConstants.TRUE;
					
				} else {
					userexist = MessageConstants.FALSE;
				}
				
				
			}

		}catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		finally{

			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL: userValid Ending");
		return userexist;
	}

	public String saveDairy(String content, String rowid, String date,
			String userId, String userType) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : saveDairy  Starting");
		
		PreparedStatement pstmtcheck = null;
		ResultSet rscheck=null;
		PreparedStatement pstmt = null;
		int rs = 0;
		int count=0;
		Connection con = null;
		String status=null;
		try {

			String sql = null;

			con = JDBCConnection.getSeparateConnection();
			pstmtcheck=con.prepareStatement("SELECT COUNT(*) FROM campus_dairy WHERE rowId=? AND userId=?");
			pstmtcheck.setString(1, rowid);
			pstmtcheck.setString(2, userId);
			rscheck=pstmtcheck.executeQuery();
			while (rscheck.next()) {
				count = rscheck.getInt(1);
				
			}
			if(count>0){
				sql = "UPDATE campus_dairy SET content=?,contentDate=?,createdBy=? where rowId=? AND userId=?";
			}
			else{
			sql = "INSERT INTO campus_dairy (content,contentDate,createdBy,rowId,userId) VALUES(?,?,?,?,?)";
			}
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, content);
			pstmt.setString(2, HelperClass.convertUIToDatabase(date));
			pstmt.setString(3, userId);
			pstmt.setString(4, rowid);
			pstmt.setString(5, userId);
			
			System.out.println(pstmt);

			rs = pstmt.executeUpdate();
			if(rs>0){
				status="true";
			}
			else{
				status="false";
			}
			
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : saveDairy  Ending");
		
		return status;
	}

	public List<DairyDetailsVo> getDairy(String userId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : getDairy  Starting");
		List<DairyDetailsVo> commentList=new ArrayList<DairyDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			String sql = null;

			con = JDBCConnection.getSeparateConnection();
			
			sql = "select * from campus_dairy where userId=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DairyDetailsVo vo=new DairyDetailsVo();
				vo.setRowId(rs.getString("rowId"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getString("contentDate"));
				commentList.add(vo);
			}
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : getDairy  Ending");
		
		return commentList;
	}

	public ArrayList<UserDetailVO> getteacherClassDetails(LoginVo loginVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : getteacherClassDetails  Starting");
		
		ArrayList<UserDetailVO> list =new ArrayList<UserDetailVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try{
				con = JDBCConnection.getSeparateConnection();
				PreparedStatement pstmt1 = con.prepareStatement("select CTCode,ClassCode,SectionCode from campus_classteacher where TeacherCode =?");
				pstmt1.setString(1,loginVo.getUsercode());
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					UserDetailVO loginvo= new UserDetailVO();
							
					loginvo.setTeamapid(rs1.getString("CTCode"));
					loginvo.setClassid(rs1.getString("ClassCode"));
					loginvo.setSectionid(rs1.getString("SectionCode"));
					list.add(loginvo);
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : getDairy  Ending");
		return list;
	}

	}