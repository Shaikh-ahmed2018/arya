package com.centris.campus.vo;

public class PermissionVO {
private String permissionId;
private String permissionName;
private String permissionShortName;
private String permissionIsApplicable;
private String serverMessage;

private int sno;
private String module;
private String submodule;




public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getSubmodule() {
	return submodule;
}
public void setSubmodule(String submodule) {
	this.submodule = submodule;
}
public String getPermissionId() {
	return permissionId;
}
public void setPermissionId(String permissionId) {
	this.permissionId = permissionId;
}
public String getPermissionName() {
	return permissionName;
}
public void setPermissionName(String permissionName) {
	this.permissionName = permissionName;
}
public String getPermissionShortName() {
	return permissionShortName;
}
public void setPermissionShortName(String permissionShortName) {
	this.permissionShortName = permissionShortName;
}
public String getPermissionIsApplicable() {
	return permissionIsApplicable;
}
public void setPermissionIsApplicable(String permissionIsApplicable) {
	this.permissionIsApplicable = permissionIsApplicable;
}
public String getServerMessage() {
	return serverMessage;
}
public void setServerMessage(String serverMessage) {
	this.serverMessage = serverMessage;
}
}
