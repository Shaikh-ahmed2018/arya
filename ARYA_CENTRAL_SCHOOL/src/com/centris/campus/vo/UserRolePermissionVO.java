package com.centris.campus.vo;

import java.util.List;

import com.centris.campus.pojo.RoleMasterPojo;


public class UserRolePermissionVO {
private List<RoleMasterPojo> roleList;
private List<PermissionVO> permissionList;
private String serverMessage;

public List<RoleMasterPojo> getRoleList() {
	return roleList;
}

public void setRoleList(List<RoleMasterPojo> roleList) {
	this.roleList = roleList;
}

public List<PermissionVO> getPermissionList() {
	return permissionList;
}

public void setPermissionList(List<PermissionVO> permissionList) {
	this.permissionList = permissionList;
}

public String getServerMessage() {
	return serverMessage;
}

public void setServerMessage(String serverMessage) {
	this.serverMessage = serverMessage;
}
}
