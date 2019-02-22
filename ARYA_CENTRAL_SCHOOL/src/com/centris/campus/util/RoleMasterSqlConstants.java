package com.centris.campus.util;

public class RoleMasterSqlConstants {
	
	// Role master
	public static final String GET_ROLES = "select RoleCode,RoleName,Description from campus_role";
	public static final String ADD_ROLE = "insert into campus_role(RoleCode,RoleName,Description,createdby,IsDefault) values(?,?,?,?,?)";
	public static final String VALIDATE_ROLE_NAME = "select count(*) RoleName from campus_role where RoleName=?";
	public static final String UPDATE_ROLE = "update campus_role set RoleName=?,Description=?,modifiedby=?,modifiedtime=?,IsDefault=? where RoleCode=?";
	public static final String CHECK_ROLE_USER_MAPPING = "select count(*) from campus_role_permissions_mapping where roleCode=?";
	public static final String CHECK_ROLE_USER_ADMIN_CREATION = "select count(*) from campus_admin where Role=?";
	public static final String DELETE_ROLE = "delete from campus_role where RoleCode=?";
	public static final String UPDATE_ROLES = "select * from campus_role where RoleCode=?";
	public static final String VALIDATE_ROLE_CODE = "select count(*) RoleCode from campus_role where RoleCode=?";
	public static final String VALIDATE_ROLE_NAME_UPDATE = "select count(*) RoleName from campus_role where RoleName=? and RoleCode!=? ";
	public static final String SEARCH_ROLE ="select RoleCode,RoleName,Description from campus_role where RoleName like ? or  Description like ?";
	

}
