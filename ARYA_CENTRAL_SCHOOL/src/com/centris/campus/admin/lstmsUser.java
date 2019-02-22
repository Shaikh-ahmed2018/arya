package com.centris.campus.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class lstmsUser implements Serializable {

	/**
	 * @param args
	 */
	private String id;
	private String userName;
	private String password;
	private String type;
	private String createuser;
	private String modifyuser;
	private Timestamp createdate;
	private Timestamp modifydate;
	private String lastLogin;

	public lstmsUser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the createuser
	 */
	public String getCreateuser() {
		return createuser;
	}

	/**
	 * @param createuser
	 *            the createuser to set
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the modifyuser
	 */
	public String getModifyuser() {
		return modifyuser;
	}

	/**
	 * @param modifyuser
	 *            the modifyuser to set
	 */
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	/**
	 * @return the createdate
	 */
	public Timestamp getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate
	 *            the createdate to set
	 */
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the modifydate
	 */
	public Timestamp getModifydate() {
		return modifydate;
	}

	/**
	 * @param modifydate
	 *            the modifydate to set
	 */
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

}