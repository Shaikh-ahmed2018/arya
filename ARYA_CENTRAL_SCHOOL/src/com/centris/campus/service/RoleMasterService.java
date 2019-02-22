package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.RoleMasterForm;
import com.centris.campus.pojo.RoleMasterPojo;


public interface RoleMasterService {
	public  String addRole(RoleMasterPojo pojo) throws Exception;
	public List<RoleMasterPojo> getRoles() throws Exception;
	public String updateRole(RoleMasterPojo pojo)throws Exception;
	public String deleteRole(RoleMasterPojo roleMasterPojo) throws Exception;
	public RoleMasterPojo updateRoles(String roleCodeId) throws Exception;
	public boolean validateRoleName(String roleNameValidate) throws Exception;
	public boolean validateRoleNameUpdate(String roleNameValidate, String roleid)throws Exception;
	public List<RoleMasterPojo> searchRole(String searchterm)throws Exception;
	
}
