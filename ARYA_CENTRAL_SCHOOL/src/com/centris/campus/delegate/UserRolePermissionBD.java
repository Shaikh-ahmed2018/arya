package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.serviceImpl.UserRolePermissionServiceImpl;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public class UserRolePermissionBD {

	public UserRolePermissionVO getUserRolePermission(){
		return new UserRolePermissionServiceImpl().getUserRolePermission();
	}
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission){
		return new UserRolePermissionServiceImpl().insertRolePermission(userRolePermission);
	}
	public List<PermissionVO>  getPermissionByRole(String roleCode){
		return new UserRolePermissionServiceImpl().getPermissionByRole(roleCode);
	}
	public PermissionVO  removePermission(String roleCode,String roleName){
		return new UserRolePermissionServiceImpl().removePermission(roleCode,roleName);
	}
	
}
