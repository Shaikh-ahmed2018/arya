package com.centris.campus.delegate;

import java.util.List;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.service.RoleMasterService;
import com.centris.campus.serviceImpl.RoleMasterServiceImpl;

public class RoleMasterBD {

	RoleMasterService roleservice = new RoleMasterServiceImpl();

	public String addRole(RoleMasterPojo pojo)
			throws Exception {
		return roleservice.addRole(pojo);
	}

	public String updateRole(RoleMasterPojo pojo)
			throws Exception {
		return roleservice.updateRole(pojo);
	}

	public List<RoleMasterPojo> getRoles() throws Exception {
		return roleservice.getRoles();
	}

	public String deleteRole(RoleMasterPojo roleMasterPojo) throws Exception {
		return roleservice.deleteRole(roleMasterPojo);
	}

	public RoleMasterPojo updateRole(String roleCodeId) throws Exception {
		return roleservice.updateRoles(roleCodeId);
	}

	public boolean validateRoleName(String roleNameValidate) throws Exception {
		return roleservice.validateRoleName(roleNameValidate);
	}

	public boolean validateRoleNameUpdate(String roleNameValidate, String roleid)
			throws Exception {
		return roleservice.validateRoleNameUpdate(roleNameValidate, roleid);
	}
	
	public List<RoleMasterPojo> searchRole(String searchterm) throws Exception {
		return roleservice.searchRole(searchterm);
	}
	

}
