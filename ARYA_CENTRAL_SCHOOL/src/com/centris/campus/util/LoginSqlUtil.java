package com.centris.campus.util;

public class LoginSqlUtil {
	
	public static final String GET_FATHER_DETAILS = "select p.ParentID,concat(p.FatherName) as parentname,p.Qualification,p.address,p.mobileno,p.UserName,p.email,p.password,u.lastLogin,u.role,u.type,r.RoleName from campus_role r,campus_user u,campus_parents p where p.ParentID=u.employeecode and u.role=r.RoleCode and p.ParentID=?";
	public static final String GET_STAFF_DETAILS = "select t.teachingType,t.TeacherID,concat(t.FirstName,'  ',t.LastName) as teachername,t.qualification,t.presentAddress,t.mobileNo,t.username,t.gender,t.emailId,t.password,u.lastLogin,u.role,u.type,r.RoleName,t.Loc_ID from campus_role r,campus_user u,campus_teachers t where t.TeacherID=u.employeecode and u.role=r.RoleCode and t.TeacherID=?";
	public static final String GET_PERMISSION_DETAILS = "select rpm.shortName,rpm.isApplicable from campus_role_permissions_mapping rpm ,campus_permissions cp where cp.PermissionCode=rpm.permissionCode and rpm.roleCode=?";

	public static final String GET_PARENT_CHAILD_RELATION ="select distinct relationship from campus_parentchildrelation where parentid=?";
	
	public static final String GET_MOTHER_DETAILS = "select p.ParentID,concat(p.student_mothername_var) as parentname,p.student_motherqualification_var AS Qualification,p.address,p.student_mothermobileno_var AS mobileno,p.UserName,p.student_mother_mailid AS email,p.password,u.lastLogin,u.role,u.type,r.RoleName from campus_role r,campus_user u,campus_parents p where p.ParentID=u.employeecode and u.role=r.RoleCode and p.ParentID=?";
	
	public static final String GET_GUARDIAN_DETAILS = "select p.ParentID,concat(p.student_gaurdianname_var) as parentname,'NO' AS Qualification,p.address,p.student_gardian_mobileno AS mobileno,p.UserName,p.student_gardian_mailid AS email,p.password,u.lastLogin,u.role,u.type,r.RoleName from campus_role r,campus_user u,campus_parents p where p.ParentID=u.employeecode and u.role=r.RoleCode and p.ParentID=?";
}