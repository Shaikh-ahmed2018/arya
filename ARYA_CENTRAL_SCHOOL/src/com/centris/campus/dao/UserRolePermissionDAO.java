package com.centris.campus.dao;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public interface UserRolePermissionDAO {
	public UserRolePermissionVO getUserRolePermission();
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission);
	public PermissionVO  removePermission(String roleCode,String roleName);
}
