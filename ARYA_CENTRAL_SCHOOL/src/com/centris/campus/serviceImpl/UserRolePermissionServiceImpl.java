package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.daoImpl.UserRolePermissionDAOImpl;
import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.service.UserRolePermissionService;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;


public class UserRolePermissionServiceImpl implements UserRolePermissionService {
	public UserRolePermissionVO getUserRolePermission(){
		return new UserRolePermissionDAOImpl().getUserRolePermission();
	}
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission){
		return new UserRolePermissionDAOImpl().insertRolePermission(userRolePermission);
	}
	public List<PermissionVO>  getPermissionByRole(String roleCode){
		return new UserRolePermissionDAOImpl().getPermissionByRole(roleCode);
	}
	public PermissionVO  removePermission(String roleCode,String roleName){
		return new UserRolePermissionDAOImpl().removePermission(roleCode,roleName);
	}
	
}
