package com.centris.campus.util;

public class InternalJobPostSQLUtilConstants {
	// careers

	public static final String CARREER_DATA = "select jobcode,title,qualification,noofpositions,experience,description,status from campus_careers where status='active'";
	public static final String ALL_CARREER_DATA = "select jobcode,title,qualification,noofpositions,experience,description,status from campus_careers";
	public static final String ADD_JOB_DETAILS = "insert into campus_careers (jobcode,title,qualification,noofpositions,experience,description,createdby,createtime)values(?,?,?,?,?,?,?,now());";
	public static final String VIEW_DATA = "select jobcode,title,qualification,noofpositions,experience,description,status from campus_careers where jobcode = ?";
	public static final String UPDATE_JOB_DETAILS = "update campus_careers set title=?,qualification=?,noofpositions=?,experience=?,description=?,status=?,updatedby=?,updatedtime=now() where jobcode = ?";
	public static final String DELETE_CAREER = "update campus_careers set status = 'deactive' where jobcode = ?";
	public static final String CHECK_JOB_TITLE = "select count(*) from campus_careers where title = ?";
	public static final String CHECK_JOB_TITLE_WHILE_UPDATING = "select count(*) from campus_careers where title = ? and jobcode != ?";
	public static final String GET_SEARCH_LIST = "select jobcode,title,qualification,noofpositions,experience,description,status from campus_careers where title like ?";
	public static final String JOB_CHECK_APPLIEDJOBS = "select count(*) job from campus_appliedjobs where jobcode = ?";
}
