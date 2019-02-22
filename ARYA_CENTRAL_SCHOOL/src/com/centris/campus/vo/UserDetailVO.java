package com.centris.campus.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class UserDetailVO {
	
		 private boolean userExist;
	
	  	 private String userId;
	  	 private String userName;
	  	 private String userNametype;
	  	 
	  	 private String roleCode;
	 	 private String roleName;
	  	
		 private String firstName;
	     private String qualification;
	     private String address;
	     private String mobileno;
	     private String gender;
	     private String email;
	     private Timestamp createdate;
	     private String createdby;
	     private Date lasttimevisit;
	     private String password;
	     private String eployeecode;
	     private String teacherid;
	     private String teachername;
	     private String classname;
	     private String academicyear;
	     private String locationid;
	     private String classid;
	     private String sectionid;
	     private String teamapid;
	     private String employeeType;
	     private String stream;
	     private String streamId;
	     
	     
	 	 public String getStreamId() {
			return streamId;
		}
		public void setStreamId(String streamId) {
			this.streamId = streamId;
		}
		public String getStream() {
			return stream;
		}
		public void setStream(String stream) {
			this.stream = stream;
		}
		public String getEmployeeType() {
			return employeeType;
		}
		public void setEmployeeType(String employeeType) {
			this.employeeType = employeeType;
		}
		public String getClassid() {
			return classid;
		}
		public void setClassid(String classid) {
			this.classid = classid;
		}
		public String getSectionid() {
			return sectionid;
		}
		public void setSectionid(String sectionid) {
			this.sectionid = sectionid;
		}
		public String getTeamapid() {
			return teamapid;
		}
		public void setTeamapid(String teamapid) {
			this.teamapid = teamapid;
		}
		public String getLocationid() {
			return locationid;
		}
		public void setLocationid(String locationid) {
			this.locationid = locationid;
		}
		public String getAcademicyear() {
			return academicyear;
		}
		public void setAcademicyear(String academicyear) {
			this.academicyear = academicyear;
		}
		public String getEployeecode() {
			return eployeecode;
		}
		public void setEployeecode(String eployeecode) {
			this.eployeecode = eployeecode;
		}
		public String getTeacherid() {
			return teacherid;
		}
		public void setTeacherid(String teacherid) {
			this.teacherid = teacherid;
		}
		public String getTeachername() {
			return teachername;
		}
		public void setTeachername(String teachername) {
			this.teachername = teachername;
		}
		public String getClassname() {
			return classname;
		}
		public void setClassname(String classname) {
			this.classname = classname;
		}
		private List<String> permissionCodeList;
	 	 private List<String> permissionNameList;
	 	 private List<String> permissionShortNameList;
	 	 private HashMap<String, String> permissionmap;
	 	
	 	
	 	

	
	    public String getUserNametype() {
			return userNametype;
		}
		public void setUserNametype(String userNametype) {
			this.userNametype = userNametype;
		}
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public boolean isUserExist() {
			return userExist;
		}
		public void setUserExist(boolean userExist) {
			this.userExist = userExist;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getMobileno() {
			return mobileno;
		}
		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Timestamp getCreatedate() {
			return createdate;
		}
		public void setCreatedate(Timestamp createdate) {
			this.createdate = createdate;
		}
	
		public Date getLasttimevisit() {
			return lasttimevisit;
		}
		public void setLasttimevisit(Date lasttimevisit) {
			this.lasttimevisit = lasttimevisit;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRoleCode() {
			return roleCode;
		}
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public List<String> getPermissionCodeList() {
			return permissionCodeList;
		}
		public void setPermissionCodeList(List<String> permissionCodeList) {
			this.permissionCodeList = permissionCodeList;
		}
		public List<String> getPermissionNameList() {
			return permissionNameList;
		}
		public void setPermissionNameList(List<String> permissionNameList) {
			this.permissionNameList = permissionNameList;
		}
		public List<String> getPermissionShortNameList() {
			return permissionShortNameList;
		}
		public void setPermissionShortNameList(List<String> permissionShortNameList) {
			this.permissionShortNameList = permissionShortNameList;
		}
		public HashMap<String, String> getPermissionmap() {
			return permissionmap;
		}
		public void setPermissionmap(HashMap<String, String> permissionmap) {
			this.permissionmap = permissionmap;
		}
	
	
	
}
