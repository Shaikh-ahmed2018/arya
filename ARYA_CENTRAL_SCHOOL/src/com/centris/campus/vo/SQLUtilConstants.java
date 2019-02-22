package com.centris.campus.vo;

public class SQLUtilConstants {

	public static final String GET_NEXT_ACCADAMIC_YEAR = "select acadamic_id,acadamic_year from campus_acadamicyear where acadamic_id =?";

	// check password

	public static final String CHECK_PRINCIPAL_PASSWORD = "select count(*) from  campus_user where id = ? and password= ?";
	public static final String CHECK_ADMIN_PASSWORD = "select count(*) from  campus_admin where AdminID = ? and password= ?";
	public static final String CHECK_PARENTS_PASSWORD = "select count(*) from  campus_parents where ParentID = ? and password= ?";
	public static final String CHECK_TEACHERS_PASSWORD = "select count(*) from  campus_teachers where TeacherID = ? and password= ?";

	// change password

	public static final String UPDATE_PRINCIPAL_PASSWORD = "update  campus_user set password= ? where id = ?";
	public static final String UPDATE_ADMIN_PASSWORD = "update  campus_admin set password= ? where AdminID= ? ";
	public static final String UPDATE_PERENTS_PASSWORD = "update  campus_parents set password= ? where ParentID= ?";
	public static final String UPDATE_TEACHERS_PASSWORD = "update  campus_teachers set password= ? where TeacherID= ?";

	public static final String USERDETAILS = "select rpm.shortName,rpm.isApplicable from campus_role_permissions_mapping rpm ,campus_permissions cp where cp.PermissionCode=rpm.permissionCode and rpm.roleCode=?";
	public static final String GET_USER_ROLE = "select Role from campus_admin where username=? and password=?";

	// Fee Master

	public static final String ADD_FEE_COUNT = "SELECT distinct(count(*)) FROM campus_fee_master where FeeName=?";
	public static final String EDIT_FEE_COUNT = "select count(*) from campus_fee_master where FeeCode!=? and FeeName=?";
	public static final String ADD_FEE_DETAILS = "insert into campus_fee_master(FeeCode,FeeName,description,createdby,createtime) values(?,?,?,?,?)";
	public static final String GET_FEE_DETAILS = "select FeeCode,FeeName,description from campus_fee_master order by FeeName";
	public static final String GET_EDIT_DETAILS = "select FeeCode,FeeName,description from campus_fee_master where FeeCode=?";
	public static final String EDIT_FEE_DETAILS = "update campus_fee_master set FeeName=?,description=?,createdby=?,createtime=? where FeeCode= ?";
	public static final String DELETE_FEE_DETAILS = "delete from campus_fee_master where FeeCode=?";
	public static final String SEARCH_FEE_DETAILS = "select FeeName,FeeCode,description from campus_fee_master where FeeName like ?";

	// Department Master

	public static final String GET_DEPARTMENT_DETAILS = "select DEPT_ID,DEPT_NAME,DESCRIPTION,isActive,CREATE_DATE,UpdatedBy,createdby,UpdatedTime from campus_department where isActive='Y' order by DEPT_NAME asc";

	public static final String INSERT_DEPARTMENT_DETAILS = "insert into campus_department (DEPT_ID,DEPT_NAME,DESCRIPTION,isActive,CREATE_DATE,createdby) values(?,?,?,?,?,?)";

	public static final String EDIT_DEPARTMENT = "select DEPT_ID,DEPT_NAME,DESCRIPTION,CREATE_DATE,createdby from campus_department where DEPT_ID=?";

	public static final String CHECK_DEPARTMENT_MAP = "select count(*) deptname from campus_teachers where department=?";

	public static final String GET_SINGLE_DEPARTMENT = "select DEPT_NAME from campus_department where DEPT_ID=?";

	public static final String DELETE_DEPARTMENT = "update campus_department set isActive=? where DEPT_ID =?";

	public static final String UPDATE_DEPARTMENT = "update campus_department set DEPT_NAME= ?,DESCRIPTION=?,isActive=?,UpdatedBy=?,UpdatedTime=now() where DEPT_ID =?";

	public static final String SEARCH_DEPARTMENT_DETAILS = "select * from campus_department where (DEPT_NAME like ? or DESCRIPTION like ?) AND isActive='Y'";

	public static final String VALIDATE_DEPT_NAME = "select count(*) deptname from campus_department where DEPT_NAME=? and isActive='Y'";

	public static final String VALIDATE_DEPARTMENT_UPDATE = "select count(*) deptname from campus_department where DEPT_NAME=? and DEPT_ID!=? and isActive='Y'";

	
	//Designation Master
	public static final String GET_DESIGNATION_DETAILS = "select DesignationCode,designationName,description,createdate,CreatedBy from campus_designation where isActive='Y' order by designationName asc";
	public static final String INSERT_DESIGNATION_DETAILS = "insert into campus_designation (DesignationCode,designationName,description,CreatedBy,isActive) values(?,?,?,?,?)";
	public static final String EDIT_DESIGNATION = "select DesignationCode,designationName,description,createdate,CreatedBy from campus_designation where DesignationCode=?";
	public static final String VALIDE_DES_NAME = "select count(*) desname from campus_designation where designationName=? and isActive='Y'";
	public static final String UPDATE_DES_DETAILS = "update campus_designation set designationName= ?,description=?,isActive=? where DesignationCode =?";
	public static final String GET_SINGLE_DESIGNATION = "select designationName from campus_designation where DesignationCode=?";
	public static final String DELETE_DESIGNATION = "update campus_designation set isActive=? where DesignationCode =?";
	public static final String CHECK_DESIGNATION_MAP = "select count(*) desname from campus_teachers where designation=?";
	public static final String GET_SEARCH_DETAILS = "select * from campus_designation where designationName like ?  AND isActive='Y';";
	public static final String ADD_DESG_COUNT = "SELECT distinct(count(*)) FROM campus_designation where designationName=? and isActive='Y'";
	public static final String EDIT_DESG_COUNT = "select count(*) from campus_designation where DesignationCode!=? and designationName=? and isActive='Y'";
	
	//stage master
	public static final String GET_STAGEMASTER_DETAILS = "select cs.stage_id,cs.stage_name,cs.description from campus_fee_stage cs";
	public static final String INSERT_STAGE_DETAILS = "insert into campus_fee_stage (stage_id,stage_name,description,createdby) values(?,?,?,?)";
	public static final String UPDATE_STAGE_DETAILS = "update campus_fee_stage set stage_name= ?,description=? where stage_id =?";
	public static final String EDIT_STAGE= "select stage_id,stage_name,description,createdtime,createdby from campus_fee_stage where stage_id=?";
	public static final String CHECK_STAGE_MAP = "select count(*) from campus_fee_stagesetup where stage_id=?";
	public static final String DELETE_STAGEMASTER = "delete from campus_fee_stage where stage_id=?";
	public static final String GET_STAGE_DETAILS = "select * from campus_fee_stage where stage_name like ?";
	public static final String ADD_STAGE_COUNT = "select distinct(count(*)) from campus_fee_stage where stage_name=?";
	public static final String EDIT_STAGE_COUNT = "select count(*) from campus_fee_stage where stage_id!=? and stage_name=?";


	
	
	// Term Master
	
	
	
		public static final String GET_ACC_DETAILS = "select acadamic_id,acadamic_year,startDate,endDate from campus_acadamicyear where acadamic_id=?";
		public static final String TERM_COUNT = "SELECT distinct(count(*)) FROM campus_fee_termdetails where termname=?";
		public static final String ADD_TERM = "insert into campus_fee_termdetails(termid,termname,startdate,enddate,accyear,description,ternOrder,isTransportTerm,createdby,createdtime) values(?,?,?,?,?,?,?,?,?,?)";
		public static final String GET_TERM = "select te.termid,te.termname,te.startdate,te.enddate,ac.acadamic_year,te.accyear from campus_fee_termdetails te join campus_acadamicyear ac on te.accyear=ac.acadamic_id order by te.termname";
		public static final String EDIT_TERM = "select termid,termname,description,startdate,enddate,isTransportTerm from campus_fee_termdetails where termid=?";
		public static final String EDIT_TERM_COUNT = "SELECT distinct(count(*)) FROM campus_fee_termdetails where termid!=? and termname=?";
		public static final String EDIT_TERM_DETAILS = "update campus_fee_termdetails set termname=?,startdate=?,enddate=?,accyear=?,description=?,isTransportTerm=?,updatedby=?,updatedtime=? where termid= ?";
		public static final String CHECK_TERM_MAP = "select count(*) termcode from campus_fee_collection where termcode=?";
		public static final String CHECK_TERM_MAP1 = "select count(*) Termcode from campus_fee_setup where Termcode=?";
		public static final String DELETE_TERM = "delete from campus_fee_termdetails where termid=?";
		public static final String SEARCH_TERM_DETAILS = "select te.termid,te.termname,te.startdate,te.enddate,ac.acadamic_year,te.accyear from campus_fee_termdetails te join campus_acadamicyear ac on te.accyear=ac.acadamic_id where te.termname like ? order by te.termname";

	//Quota Details
	public static final String GET_QUOTA_DETAILS = "select Quota_Code,Quota_Name,Quota_Description from campus_quota order by Quota_Name";

	public static final String INSERT_QUOTA_DETAILS = "insert into campus_quota(Quota_Code,Quota_Name,Quota_Description,created_date,created_by) values(?,?,?,?,?);";

	public static final String GET_QUOTA_COUNT_BEFOREDELETE = "select count(student_quota) from  campus_student where student_quota=?";

	public static final String DELETE_QUOTA = "Delete from campus_quota where Quota_Code=?";

	public static final String GET_PARTICULAR_QUOTA = "select Quota_Code,Quota_Name,Quota_Description from campus_quota where Quota_Code=?";

	public static final String GET_QUOTA_COUNT_FOR_UPDATE = "select count(*) from campus_quota where Quota_Name=? and Quota_Code!=?;";

	public static final String UPDATE_QUOTA_DETAILS = "update campus_quota set Quota_Name=?,Quota_Description=?,updated_by=?,updated_date=? where Quota_Code=?";

	public static final String SEARCH_QUOTA_DETAILS = "select * from campus_quota where Quota_Name like ? or Quota_Description like ?";

	public static final String VALIDATE_QUOTA_NAME = "select count(*) quotaname from campus_quota where Quota_Name=?";

	public static final String VALIDATE_QUOTA_UPDATE = "select count(*) quotaname from campus_quota where Quota_Name=? and Quota_Code!=?";
	
}
