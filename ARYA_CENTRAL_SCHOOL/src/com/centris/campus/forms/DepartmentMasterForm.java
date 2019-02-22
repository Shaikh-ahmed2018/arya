package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class DepartmentMasterForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String departmentid;
	String dept_name;
	String department_description;
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartment_description() {
		return department_description;
	}

	public void setDepartment_description(String department_description) {
		this.department_description = department_description;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

}
