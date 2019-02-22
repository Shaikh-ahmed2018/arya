package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class UserRolePermissionForm extends ActionForm{
private String roleCode;
private String roleName;
private String permissionCode;
private String permissionShortName;
private String isPermissionAllowed;



public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
public String getPermissionCode() {
	return permissionCode;
}
public void setPermissionCode(String permissionCode) {
	this.permissionCode = permissionCode;
}
public String getPermissionShortName() {
	return permissionShortName;
}
public void setPermissionShortName(String permissionShortName) {
	this.permissionShortName = permissionShortName;
}
public String getIsPermissionAllowed() {
	return isPermissionAllowed;
}
public void setIsPermissionAllowed(String isPermissionAllowed) {
	this.isPermissionAllowed = isPermissionAllowed;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
}
