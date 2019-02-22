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

import com.centris.campus.dao.UserManagementDao;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UserManagementSqlutil;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementDaoImpl implements UserManagementDao {

	private static final Logger logger = Logger	.getLogger(UserManagementDaoImpl.class);
	
	public List<UserRecordVO> getUserRecordsDao() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserRecordsDao  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_getUsers = conn.prepareStatement(UserManagementSqlutil.USERRECORDS);
			
			rs_getUsers = ps_getUsers.executeQuery();
			
			while (rs_getUsers.next()) {
				
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs_getUsers.getString("username"));
				userRecordVO.setMobile(rs_getUsers.getString("mobileno"));
				userRecordVO.setAddress(rs_getUsers.getString("address"));
				userRecordVO.setFirstName(rs_getUsers.getString("FirstName"));
				userRecordVO.setDesignation(rs_getUsers.getString("RoleName"));
				
				userRecordVO.setUserId(rs_getUsers.getString("Id"));
				
				userRecordsList.add(userRecordVO);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserRecordsDao Ending");
	
		
		return userRecordsList;
	}




	public List<UserRecordVO> getSearchUserDetailsDao(UserManagementPojo userManagementPojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getSearchUserDetailsDao  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement chkUser=conn.prepareStatement("SELECT * FROM campus_role WHERE RoleCode=?");
			chkUser.setString(1,userManagementPojo.getType());
			ResultSet chkrs=chkUser.executeQuery();
			while(chkrs.next()) {
				if(!chkrs.getString("RoleName").equalsIgnoreCase("N")){
					
					ps_getUsers = conn.prepareStatement("SELECT cr.RoleName,ct.TeacherID AS Id,cu.UserName AS username ,CASE WHEN ct.LastName IS NULL THEN ct.FirstName ELSE CONCAT(ct.FirstName, ' ',ct.LastName) END FirstName,ct.mobileno,ct.presentAddress AS address FROM campus_role cr  JOIN campus_user cu ON cu.role=cr.RoleCode  JOIN campus_teachers ct ON cu.employeecode=ct.TeacherID WHERE (ct.FirstName like ? or ct.LastName like ? or cu.UserName like ? or ct.mobileno like ?) and ct.role=? and isActive='Y'");
					ps_getUsers.setString(1, "%"+userManagementPojo.getSearchtext()+"%");
					ps_getUsers.setString(2, "%"+userManagementPojo.getSearchtext()+"%");
					ps_getUsers.setString(3, "%"+userManagementPojo.getSearchtext()+"%");
					ps_getUsers.setString(4, "%"+userManagementPojo.getSearchtext()+"%");
					ps_getUsers.setString(5, userManagementPojo.getType());
					
				}else{
				
			      	ps_getUsers = conn.prepareStatement("SELECT cr.RoleName,cp.ParentID AS Id,cu.UserName AS UserName,cp.FatherName FirstName,cp.mobileno,cp.address FROM campus_role cr JOIN campus_user cu  ON cu.role=cr.RoleCode JOIN campus_parents cp  ON cu.employeecode=cp.ParentID WHERE (cp.FatherName ? or cu.UserName like ? or cp.mobileno like ?) and cp.role=? and cp.pstatus='active'");
			      	ps_getUsers.setString(1, "%"+userManagementPojo.getSearchtext()+"%");
			    	ps_getUsers.setString(2, "%"+userManagementPojo.getSearchtext()+"%");
			    	ps_getUsers.setString(3, "%"+userManagementPojo.getSearchtext()+"%");
			    	ps_getUsers.setString(4, userManagementPojo.getType());
				}
				
				rs_getUsers = ps_getUsers.executeQuery();
				
				while (rs_getUsers.next()) {
					UserRecordVO userRecordVO = new UserRecordVO();
					userRecordVO.setUserName(rs_getUsers.getString("username"));
					userRecordVO.setMobile(rs_getUsers.getString("mobileno"));
					/*userRecordVO.setAddress(rs_getUsers.getString("address"));*/
					userRecordVO.setFirstName(rs_getUsers.getString("FirstName"));
					if (rs_getUsers.getString("Id").startsWith("PAR")) {
						userRecordVO.setDesignation("Parent");
					} else {
						userRecordVO.setDesignation("Teacher");
					}
					userRecordVO.setUserId(rs_getUsers.getString("Id"));
					
					userRecordsList.add(userRecordVO);
				}
			}
			
		

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getSearchUserDetailsDao Ending");
	
		
		return userRecordsList;
	}





	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserDeatils  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		UserRecordVO  userRecordVO=new  UserRecordVO();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
            if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getUserId().substring(0, 3))){
				
				ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_TEACHER);
				ps_getUsers.setString(1, userManagementPojo.getUserId());
				
				
				rs_getUsers = ps_getUsers.executeQuery();
				while (rs_getUsers.next()) {
					
					userRecordVO.setFirstName(rs_getUsers.getString("FirstName"));
					userRecordVO.setEmail(rs_getUsers.getString("UserName"));
				}

				
			}else{
			
		      	ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_PARENT);
		      	ps_getUsers.setString(1, userManagementPojo.getUserId());
				rs_getUsers = ps_getUsers.executeQuery();
				while (rs_getUsers.next()) {
					
					userRecordVO.setFirstName(rs_getUsers.getString("FatherName"));
					userRecordVO.setEmail(rs_getUsers.getString("UserName"));
					
				
				}

			}
			
			
			
			
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserDeatils Ending");
	
		
		return userRecordVO;
	}




	public String changePassword(UserManagementPojo userManagementPojo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : changePassword  Starting");
		
		PreparedStatement ps_updatePwd = null;
		Connection conn = null;
		String result=MessageConstants.FALSE;
		PreparedStatement ps_user = null;
		int rs_user = 0;
		int count = 0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
            if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getUserId().substring(0, 3))){
				
            	ps_updatePwd = conn.prepareStatement(UserManagementSqlutil.CHANGE_TEACHER_PWD);
            	ps_updatePwd.setString(1, userManagementPojo.getPasswrd());
            	ps_updatePwd.setString(2, userManagementPojo.getUserId());
            	count  = ps_updatePwd.executeUpdate();
            	
            	
            	

			}else{
			
				ps_updatePwd = conn.prepareStatement(UserManagementSqlutil.CHANGE_PARENT_PWD);
				ps_updatePwd.setString(1, userManagementPojo.getPasswrd());
            	ps_updatePwd.setString(2, userManagementPojo.getUserId());
            	count  = ps_updatePwd.executeUpdate();
            	
            	
            	
			}
			
            ps_user = conn.prepareStatement(UserManagementSqlutil.CHANGE_TEACHER_PWD_USER);
        	ps_user.setString(1, userManagementPojo.getPasswrd());
        	ps_user.setString(2, userManagementPojo.getUserId());
        	rs_user = ps_user.executeUpdate();
			 
			
			if(count>0){
				
				result = MessageConstants.TRUE;
			}
			
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (ps_updatePwd != null && !ps_updatePwd.isClosed()) {
					ps_updatePwd.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : changePassword Ending");
	
		
		return result;
	}



	public String blockUser(UserManagementPojo userManagementPojo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : blockUser  Starting");
		
		PreparedStatement ps_blockUser = null;
		Connection conn = null;
		String result=MessageConstants.FALSE;
	
		try {
			conn = JDBCConnection.getSeparateConnection();
			
            if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getUserId().substring(0, 3))){
				
            	ps_blockUser = conn.prepareStatement(UserManagementSqlutil.BLOCK_TEACHER);
            	ps_blockUser.setString(1, userManagementPojo.getUserId());

			}else{
			
				ps_blockUser = conn.prepareStatement(UserManagementSqlutil.BLOCK_PARENT);
				ps_blockUser.setString(1, userManagementPojo.getUserId());
			}
			
			int count  = ps_blockUser.executeUpdate();
			
			if(count>0){
				
				result = MessageConstants.TRUE;
			}
			
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (ps_blockUser != null && !ps_blockUser.isClosed()) {
					ps_blockUser.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : blockUser Ending");
	
		
		return result;
	}

}
