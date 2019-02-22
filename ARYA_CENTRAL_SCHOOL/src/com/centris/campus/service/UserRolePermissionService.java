package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;


public interface UserRolePermissionService {
	public UserRolePermissionVO getUserRolePermission();
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission);
	public List<PermissionVO>  getPermissionByRole(String roleCode);
	public PermissionVO  removePermission(String roleCode,String roleName);
}
