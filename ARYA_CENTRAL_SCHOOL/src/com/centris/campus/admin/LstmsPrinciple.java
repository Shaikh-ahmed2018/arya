package com.centris.campus.admin;

import java.io.Serializable;
import java.sql.Timestamp;

 public class LstmsPrinciple implements Serializable{

	
	 private String id;
		private String userName;
		private String password;
		private String type;
		private String createuser;
		private String modifyuser;
		private Timestamp createdate;
		private Timestamp modifydate;
		private String FirstName;
		private String LastName;
		private String Qualification;
		private String address;
		private String adminstatus;
		
		
		
		
		
		public String getFirstName() {
			return FirstName;
		}
		public void setFirstName(String firstName) {
			FirstName = firstName;
		}
		public String getLastName() {
			return LastName;
		}
		public void setLastName(String lastName) {
			LastName = lastName;
		}
		public String getQualification() {
			return Qualification;
		}
		public void setQualification(String qualification) {
			Qualification = qualification;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAdminstatus() {
			return adminstatus;
		}
		public void setAdminstatus(String adminstatus) {
			this.adminstatus = adminstatus;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCreateuser() {
			return createuser;
		}
		public void setCreateuser(String createuser) {
			this.createuser = createuser;
		}
		public String getModifyuser() {
			return modifyuser;
		}
		public void setModifyuser(String modifyuser) {
			this.modifyuser = modifyuser;
		}
		public Timestamp getCreatedate() {
			return createdate;
		}
		public void setCreatedate(Timestamp createdate) {
			this.createdate = createdate;
		}
		public Timestamp getModifydate() {
			return modifydate;
		}
		public void setModifydate(Timestamp modifydate) {
			this.modifydate = modifydate;
		}
	
		
		
		
	
}
