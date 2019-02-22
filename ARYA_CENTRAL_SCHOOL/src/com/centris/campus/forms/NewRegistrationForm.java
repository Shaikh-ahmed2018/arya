package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

import com.centris.campus.delegate.NewRegistrationBD;

public class NewRegistrationForm extends ActionForm {
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String firstName;
		private String lastName;
		
		private String email;
		private String mobile;
		
		private String address;
		private String userName;
		
		private String password;
		private String rePssword;
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		public String getRePssword() {
			return rePssword;
		}
		public void setRePssword(String rePssword) {
			this.rePssword = rePssword;
		}
		
		}
		
		
		



