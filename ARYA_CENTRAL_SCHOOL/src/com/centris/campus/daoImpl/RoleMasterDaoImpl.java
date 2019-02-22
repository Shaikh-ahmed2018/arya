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

import com.centris.campus.dao.RoleMasterDao;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.RoleMasterSqlConstants;

public class RoleMasterDaoImpl implements RoleMasterDao {
	private static Logger logger = Logger.getLogger(RoleMasterDaoImpl.class);

	@Override
	public synchronized String addRole(RoleMasterPojo roleMasterPojo)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl: addRole Starting");

		PreparedStatement psrole = null;
		String successMessage = null;
		int count = 0;
		RoleMasterDaoImpl daoImpl = new RoleMasterDaoImpl();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psrole = conn.prepareStatement(RoleMasterSqlConstants.ADD_ROLE);

			psrole.setString(1, roleMasterPojo.getRoleCode());
			psrole.setString(2, roleMasterPojo.getRoleName());
			psrole.setString(3, roleMasterPojo.getRoleDescription());
			psrole.setString(4, roleMasterPojo.getCreatedBy());
			
			if(roleMasterPojo.getIsadministrator()=="true"||roleMasterPojo.getIsadministrator().equalsIgnoreCase("true"))
			{
				psrole.setString(5, "Y");
			}
			else
			{
				psrole.setString(5,"N");
			}
			
			
			if (!(daoImpl.validateRoleName(roleMasterPojo.getRoleName()))) {

				count = psrole.executeUpdate();
				
			}
			if (count > 0) {
				successMessage = "Add Role Details Progressing...";
			} else {
				successMessage = "Role already Exists";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (psrole != null && (!psrole.isClosed())) {
					JDBCConnection.closeStatement();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:addRole Ending");

		return successMessage;
	}

	public String updateRole(RoleMasterPojo roleMasterPojo) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:updateRole Starting");

		PreparedStatement psrole = null;
		String successMessage = null;
		int count = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
				psrole = conn.prepareStatement(RoleMasterSqlConstants.UPDATE_ROLE);

				psrole.setString(1, roleMasterPojo.getRoleName());
				psrole.setString(2, roleMasterPojo.getRoleDescription());
				psrole.setString(3, roleMasterPojo.getModifiedBy());
				psrole.setTimestamp(4, HelperClass.getCurrentTimestamp());
				if(roleMasterPojo.getIsadministrator()=="true"||roleMasterPojo.getIsadministrator().equalsIgnoreCase("true"))
				{
					psrole.setString(5, "Y");
				}
				else
				{
					psrole.setString(5,"N");
				}
				psrole.setString(6, roleMasterPojo.getRoleCode());
				count = psrole.executeUpdate();

				if (count > 0) {
					successMessage = "Update Role Details Progressing...";
				} else {
					successMessage = "Failed to Update Roll !";
				}
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (psrole != null && (!psrole.isClosed())) {
					psrole.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:addRole Ending");

		return successMessage;
	}

	public synchronized List<RoleMasterPojo> getRoles() throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:getRoles Starting");
		List<RoleMasterPojo> getRoleList = new ArrayList<RoleMasterPojo>();

		PreparedStatement pstmt = null;
		ResultSet rsgetRoles = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(RoleMasterSqlConstants.GET_ROLES);
			rsgetRoles = pstmt.executeQuery();
			while (rsgetRoles.next()) {
				count++;
				RoleMasterPojo masterPojo = new RoleMasterPojo();
				masterPojo.setSno(count+"");
				masterPojo.setRoleCode(rsgetRoles.getString("RoleCode"));
				masterPojo.setRoleName(rsgetRoles.getString("RoleName"));
				masterPojo.setRoleDescription(rsgetRoles
						.getString("Description"));
				getRoleList.add(masterPojo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rsgetRoles != null && !rsgetRoles.isClosed()) {
					rsgetRoles.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:getRoles Ending");

		// TODO Auto-generated method stub
		return getRoleList;
	}

	@Override
	public synchronized String deleteRole(RoleMasterPojo roleMasterPojo)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl : deleteRole Starting");
		// TODO Auto-generated method stub

		PreparedStatement psdeleteRole = null;
		PreparedStatement psdeleteRole1 = null;
		int result = 0;
		String successMessage = null;
		ResultSet rolers = null;
		Connection conn = null;
		String roleCode[]=roleMasterPojo.getRoleCode().split(",");//
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			for(int i=0; i<roleCode.length;i++){ //
			psdeleteRole = conn.prepareStatement(RoleMasterSqlConstants.CHECK_ROLE_USER_MAPPING);
			psdeleteRole.setString(1, roleCode[i]);//
			
			rolers = psdeleteRole.executeQuery();
			while (rolers.next()) {

				result = rolers.getInt(1);
				
				
			}
			if (result == 0) {
				psdeleteRole = conn.prepareStatement(RoleMasterSqlConstants.CHECK_ROLE_USER_ADMIN_CREATION);
				psdeleteRole.setString(1, roleCode[i]);//
				
				rolers = psdeleteRole.executeQuery();

				while (rolers.next()) {

					result = rolers.getInt(1);
					
				}
			}
			
			

			if (result > 0) {

				successMessage = "Unable to Delete : Role is in Use";

			} else {

				psdeleteRole1 = conn.prepareStatement(RoleMasterSqlConstants.DELETE_ROLE);
			
				psdeleteRole1.setString(1, roleCode[i]);
				
				result = psdeleteRole1.executeUpdate();

				if (result > 0) {
					successMessage = "Delete Unmapped Roll Details Progressing...";
				} else {
					successMessage = "Failed to Delete Roll Details";
				}

			}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rolers != null && (!rolers.isClosed())) {
					rolers.close();
				}

				if (psdeleteRole1 != null && !psdeleteRole1.isClosed()) {
					psdeleteRole1.close();
				}

				if (psdeleteRole != null && (!psdeleteRole.isClosed())) {
					psdeleteRole.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:deleteRole Ending");
		return successMessage;
	}

	@Override
	public synchronized RoleMasterPojo updateRoles(String roleCodeId)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:updateRoles Starting");
		// TODO Auto-generated method stub

		RoleMasterPojo masterPojo = new RoleMasterPojo();
		ResultSet rsgetRoles = null;
		PreparedStatement psgetrolesUpdate = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psgetrolesUpdate = conn
					.prepareStatement(RoleMasterSqlConstants.UPDATE_ROLES);

			psgetrolesUpdate.setString(1, roleCodeId);

			rsgetRoles = psgetrolesUpdate.executeQuery();
			while (rsgetRoles.next()) {
				
				masterPojo.setRoleCode(rsgetRoles.getString("RoleCode"));
				masterPojo.setRoleName(rsgetRoles.getString("RoleName"));
				masterPojo.setRoleDescription(rsgetRoles.getString("Description"));
				masterPojo.setIsadministrator(rsgetRoles.getString("IsDefault"));
				

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rsgetRoles != null && !rsgetRoles.isClosed()) {
					rsgetRoles.close();
				}

				if (psgetrolesUpdate != null && !psgetrolesUpdate.isClosed()) {
					psgetrolesUpdate.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:updateRoles Ending");
		// TODO Auto-generated method stub
		return masterPojo;

	}

	public synchronized boolean validateRoleCode(String roleCodeId)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleCode Starting");

		boolean role_Code_Available = false;
		int count = 0;
		PreparedStatement pscheckRoleCode = null;
		ResultSet rsCheckRoleName = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pscheckRoleCode = conn
					.prepareStatement(RoleMasterSqlConstants.VALIDATE_ROLE_CODE);
			pscheckRoleCode.setString(1, roleCodeId);
			rsCheckRoleName = pscheckRoleCode.executeQuery();
			while (rsCheckRoleName.next()) {
				count = rsCheckRoleName.getInt("RoleCode");

			}
			if (count > 0) {

				role_Code_Available = true;

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rsCheckRoleName != null && !rsCheckRoleName.isClosed()) {
					rsCheckRoleName.close();
				}

				if (pscheckRoleCode != null && !pscheckRoleCode.isClosed()) {
					pscheckRoleCode.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleCode Ending");
		return role_Code_Available;
	}

	public synchronized boolean validateRoleName(String roleName)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleName Starting");

		boolean role_Name_Available = false;
		int count = 0;
		PreparedStatement pscheckRoleName = null;
		ResultSet rsCheckRoleName = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pscheckRoleName = conn
					.prepareStatement(RoleMasterSqlConstants.VALIDATE_ROLE_NAME);

			pscheckRoleName.setString(1, roleName);
			
			rsCheckRoleName = pscheckRoleName.executeQuery();
			
			while (rsCheckRoleName.next()) {
				
				count = rsCheckRoleName.getInt("RoleName");

			}
			if (count > 0) {

				role_Name_Available = true;

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rsCheckRoleName != null && !rsCheckRoleName.isClosed()) {
					rsCheckRoleName.close();
				}

				if (pscheckRoleName != null && !pscheckRoleName.isClosed()) {
					pscheckRoleName.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleName Ending");
		return role_Name_Available;
	}

	public synchronized boolean validateRoleNameUpdate(String roleName,
			String roleid) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleNameUpdate Starting");
		boolean role_Name_Available = false;
		int count = 0;
		PreparedStatement pscheckRoleName = null;
		ResultSet rsCheckRoleName = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pscheckRoleName = conn
					.prepareStatement(RoleMasterSqlConstants.VALIDATE_ROLE_NAME_UPDATE);
			pscheckRoleName.setString(1, roleName);
			pscheckRoleName.setString(2, roleid);
			
			rsCheckRoleName = pscheckRoleName.executeQuery();
			while (rsCheckRoleName.next()) {
				count = rsCheckRoleName.getInt("RoleName");

			}
			if (count > 0) {

				role_Name_Available = true;

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (rsCheckRoleName != null && !rsCheckRoleName.isClosed()) {
					rsCheckRoleName.close();
				}

				if (pscheckRoleName != null && !pscheckRoleName.isClosed()) {
					pscheckRoleName.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:validateRoleNameUpdate Ending");
		return role_Name_Available;
	}

	@Override
	public List<RoleMasterPojo> searchRole(String searchterm) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:searchRole Starting");
		List<RoleMasterPojo> getRoleList = new ArrayList<RoleMasterPojo>();
		int count=0;

		PreparedStatement pstmt = null;
		ResultSet rsgetRoles = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(RoleMasterSqlConstants.SEARCH_ROLE);
			pstmt.setString(1, "%"+searchterm+"%");
			pstmt.setString(2, "%"+searchterm+"%");
			
			rsgetRoles = pstmt.executeQuery();
			while (rsgetRoles.next()) {
				count++;
				RoleMasterPojo masterPojo = new RoleMasterPojo();
				masterPojo.setSno(count+"");
				masterPojo.setRoleCode(rsgetRoles.getString("RoleCode"));
				masterPojo.setRoleName(rsgetRoles.getString("RoleName"));
				masterPojo.setRoleDescription(rsgetRoles
						.getString("Description"));
				getRoleList.add(masterPojo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rsgetRoles != null && !rsgetRoles.isClosed()) {
					rsgetRoles.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterDaoImpl:searchRole Ending");

		// TODO Auto-generated method stub
		return getRoleList;
	}

}
