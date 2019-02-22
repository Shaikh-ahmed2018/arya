package com.centris.campus.util;

public class UserRolePermissionSqlConstatnts {
	
	// User Role Permission
	public static final String GET_USERS = "select u.username ,u.password, u.id,r.RoleName ,u.RoleCode,u.MobileNumber,u.EmailId from campus_role r,campus_user u where u.RoleCode =r.RoleCode";
//	public static final String GET_PERMISSION = "select PermissionCode,permission,shortName from campus_permissions";
	public static final String GET_PERMISSION_BY_ROLE = "select permissionCode,isApplicable from campus_role_permissions_mapping where roleCode=?";
	public static final String INSERT_ROLE_PERMISSION_MAPPING = "insert into campus_role_permissions_mapping(permissionCode,roleCode,shortName,isApplicable) values(?,?,?,?)";
	public static final String DELETE_ROLE_PERMISSION_MAPPING = "delete from campus_role_permissions_mapping where roleCode like ?";
	public static final String USERDETAILS = "select rpm.shortName,rpm.isApplicable from campus_role_permissions_mapping rpm ,campus_permissions cp where cp.PermissionCode=rpm.permissionCode and rpm.roleCode=?";
	public static final String GET_USER_ROLE = "select Role from campus_admin where username=? and password=?";
	public static final String GET_ROLES = "select RoleCode,RoleName,Description from campus_role";

	public static final String GET_PERMISSION ="select * from campus_permissions order by Module";
}
