package com.centris.campus.daoImpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hsqldb.jdbc.jdbcConnection;

import com.centris.campus.dao.UserRolePermissionDAO;
import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.util.UserRolePermissionSqlConstatnts;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public class UserRolePermissionDAOImpl  implements	UserRolePermissionDAO {
	private static Logger logger = Logger.getLogger(UserRolePermissionDAOImpl.class);;
	@Override

	public synchronized UserRolePermissionVO getUserRolePermission() {
		UserRolePermissionVO userRolePermissionVO = new UserRolePermissionVO();
		try {
			userRolePermissionVO.setPermissionList(getPermissionDetails());
			userRolePermissionVO.setRoleList(getRoles());
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return userRolePermissionVO;
	}

	public synchronized List<PermissionVO> getPermissionDetails() {
		

		PreparedStatement ps_Permission=null;
		ResultSet rs_Permission=null;
		Connection connection=null;
		int count=0;
		List<PermissionVO> permissionVOList = new ArrayList<PermissionVO>();
		try{
			connection=JDBCConnection.getSeparateConnection();
		PermissionVO permissionVO =null;
		 ps_Permission = connection.prepareStatement(UserRolePermissionSqlConstatnts.GET_PERMISSION);
		 rs_Permission = ps_Permission.executeQuery();
		while(rs_Permission.next()){
			permissionVO= new PermissionVO();
			count++;
			permissionVO.setSno(count);
			
			permissionVO.setModule(rs_Permission.getString("Module"));
			permissionVO.setSubmodule(rs_Permission.getString("SubModule"));
			permissionVO.setPermissionId(rs_Permission.getString("PermissionCode").trim());
			permissionVO.setPermissionName(rs_Permission.getString("permission").trim());
			permissionVO.setPermissionShortName(rs_Permission.getString("shortName").trim());
			permissionVOList.add(permissionVO);
		}
	
	 } catch (SQLException e) {
		   
	        e.printStackTrace();
	      }catch (Exception e) {
				
				e.printStackTrace();
			}
			finally{
				
				 try {

						if (rs_Permission != null && (!rs_Permission.isClosed())) {

							rs_Permission.close();
						}
						if (ps_Permission != null && (!ps_Permission.isClosed())) {
							
							ps_Permission.close();
						}
					 
				 } catch (SQLException e) {
					
					e.printStackTrace();
				}catch (Exception e) {
					
					e.printStackTrace();
				}
		}
		return permissionVOList;
	
		
		
		
		
		
		
		
		
		
		/*
		PreparedStatement ps_Permission = null;
		ResultSet rs_Permission = null;
		
		List<PermissionVO> permissionVOList = new ArrayList<PermissionVO>();
		try {
			PermissionVO permissionVO = null;
			ps_Permission = (PreparedStatement)JDBCConnection.getStatement(UserRolePermissionSqlConstatnts.GET_PERMISSION);
			rs_Permission = ps_Permission.executeQuery();
			while (rs_Permission.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermissionId(rs_Permission
						.getString("PermissionCode").trim());
				permissionVO.setPermissionName(rs_Permission
						.getString("permission").trim());
				permissionVO.setPermissionShortName(rs_Permission
						.getString("shortName").trim());
				permissionVOList.add(permissionVO);
			}

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				if (rs_Permission != null && (!rs_Permission.isClosed())) {

					rs_Permission.close();
				}
				if (ps_Permission != null && (!ps_Permission.isClosed())) {
					
					JDBCConnection.closeStatement();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return permissionVOList;
	*/}

	public synchronized List<PermissionVO> getPermissionByRole(String roleCode) {
		PreparedStatement ps_Permission = null;
		ResultSet rs_Permission = null;
		Connection conn=null;
		List<PermissionVO> permissionVOList = new ArrayList<PermissionVO>();
		try {
			conn=JDBCConnection.getSeparateConnection();
			PermissionVO permissionVO = null;
			ps_Permission = conn.prepareStatement(UserRolePermissionSqlConstatnts.GET_PERMISSION_BY_ROLE);
			ps_Permission.setString(1, roleCode);
			rs_Permission = ps_Permission.executeQuery();
			while (rs_Permission.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermissionId(rs_Permission
						.getString("permissionCode"));
				permissionVO.setPermissionIsApplicable(rs_Permission
						.getString("isApplicable"));
				permissionVOList.add(permissionVO);
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (rs_Permission != null && (!rs_Permission.isClosed())) {

					rs_Permission.close();
				}
				if (ps_Permission != null && (!ps_Permission.isClosed())) {
					
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
		return permissionVOList;
	}

	@Override
	public synchronized UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission) {

		Connection conn=null;
		PreparedStatement ps_Permission = null;
		UserRolePermissionVO userRolePermissionVO = new UserRolePermissionVO();
		try {
			conn=JDBCConnection.getSeparateConnection();
			String[] permissionCode=userRolePermission.getPermissionCode().split(",");
			String[] shortname=userRolePermission.getPermissionShortName().split(",");
			String[] isPermissionAllowed=userRolePermission.getIsPermissionAllowed().split(",");
			
			PermissionVO permissionVO = removePermission(userRolePermission.getRoleCode(),userRolePermission.getRoleName());

			ps_Permission = conn.prepareStatement(UserRolePermissionSqlConstatnts.INSERT_ROLE_PERMISSION_MAPPING);
			for (int i = 0; i < permissionCode.length; i++) {
				int inserCount = 0;
				ps_Permission.setString(1,
						permissionCode[i]);
				ps_Permission.setString(2, userRolePermission.getRoleCode());
				
			
				ps_Permission.setString(3,
						shortname[i]);
				ps_Permission.setString(4,
						isPermissionAllowed[i].trim());
				inserCount = ps_Permission.executeUpdate();
				if (inserCount > 0) {
					userRolePermissionVO.setServerMessage(userRolePermission
							.getRoleName() + " Permissions Mapped Successfully");
				}
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				if (ps_Permission != null && (!ps_Permission.isClosed())) {
					
					JDBCConnection.closeStatement();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return userRolePermissionVO;
	}

	@Override
	public synchronized PermissionVO removePermission(String roleCode,
			String roleName) {
		PreparedStatement ps_Permission = null;
		Connection conn=null;
		PermissionVO permissionVO = new PermissionVO();
		try {
			conn=JDBCConnection.getSeparateConnection();
			ps_Permission = conn.prepareStatement(UserRolePermissionSqlConstatnts.DELETE_ROLE_PERMISSION_MAPPING);
			ps_Permission.setString(1, roleCode);
			int i = ps_Permission.executeUpdate();
			if (i > 0) {
				if (roleCode != null && roleCode.equals("All"))
					permissionVO.setServerMessage("Clear All Permission");
				else
					permissionVO.setServerMessage("Clear " + roleName
							+ " Permissions");
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (ps_Permission != null && (!ps_Permission.isClosed())) {
					
					JDBCConnection.closeStatement();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return permissionVO;
	}

	
	public synchronized List<RoleMasterPojo> getRoles()
			throws Exception {
				
		List<RoleMasterPojo> getRoleList=new ArrayList<RoleMasterPojo>();
		Connection conn=null;
		 PreparedStatement psgetUsers=null;
		 ResultSet rsgetRoles=null;
		 try{
			 conn=JDBCConnection.getSeparateConnection();						 
	  psgetUsers=conn.prepareStatement(UserRolePermissionSqlConstatnts.GET_ROLES);
		  rsgetRoles=psgetUsers.executeQuery();
		 while(rsgetRoles.next()){
			 RoleMasterPojo masterPojo=new RoleMasterPojo();
			 masterPojo.setRoleCode(rsgetRoles.getString("RoleCode"));
			 masterPojo.setRoleName(rsgetRoles.getString("RoleName"));
			 masterPojo.setRoleDescription(rsgetRoles.getString("Description"));
			 getRoleList.add(masterPojo);
		 }
		 }
			 catch (SQLException e) {
				 logger.error(e);
			        e.printStackTrace();
			      }catch (Exception e) {
			    	  logger.error(e);
						e.printStackTrace();
					}finally {
						try {
							if (rsgetRoles != null && (!rsgetRoles.isClosed())) {

								rsgetRoles.close();
							}
							if (psgetUsers != null && (!psgetUsers.isClosed())) {
								
								JDBCConnection.closeStatement();
							}
						} catch (SQLException e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} catch (Exception e1) {

							logger.error(e1.getMessage(), e1);
							e1.printStackTrace();
						}
					}
					
			
			
		// TODO Auto-generated method stub
		return getRoleList;
	}


}
