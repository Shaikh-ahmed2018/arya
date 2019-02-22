package com.centris.campus.util;

public class AcademicYearSQLUtilConstants {
	public static final String GET_ALL_ACADEMICYEAR = "select acadamic_id,acadamic_year,startDate,endDate,Description from campus_acadamicyear order by acadamic_year";
	public static final String CREATE_ACADEMIC_YEAR = "insert into campus_acadamicyear(acadamic_id,acadamic_year,startDate,endDate,Description,createuser,createdate)values(?,?,?,?,?,?,now())";
	public static final String UPDATE_ACADEMIC_YEAR = "update campus_acadamicyear set acadamic_year=?,startDate=?,endDate=?,Description=?,modifyuser=?,modifydate=now() where acadamic_id = ?";
	public static final String DELETE_ACADEMIC_YEAR = "delete from campus_acadamicyear where acadamic_id = ?";
	public static final String EDIT_ACADEMICYEAR = "select acadamic_id,acadamic_year,startDate,endDate,Description from campus_acadamicyear where acadamic_id = ?";
	public static final String CHECK_ACADEMICYEAR_NAME = "select count(*)Acdname from campus_acadamicyear where acadamic_year = ? and (? between startDate and endDate or ? between startDate and endDate)";
	public static final String CHECK_ACADEMICYEAR_DATES = "select count(*)datescount from campus_acadamicyear where ? between startDate and endDate or ? between startDate and endDate";
	public static final String CHECKING_ACADEMIC_YEAR_MAPPING = "select count(*) from campus_student_mappings where AccdamicYearCode =?";
	public static final String GET_ALL_ACADEMICYEAR_SEARCH = "select acadamic_id,acadamic_year,startDate,endDate,Description from campus_acadamicyear where acadamic_year like ? order by acadamic_year";
	public static final String GET_ACTIVE_ACC_YEAR = "select acadamic_id,acadamic_name from campus_acadamicyear where isActive='Y'";

	public static final String GET_ACADEMIC_YEAR_LIST = "select acadamic_id,substring(startDate,1,4) as startYear,substring(endDate,1,4) as endYear,acadamic_name from campus_acadamicyear where isActive='Y'";

	public static final String GET_ALL_ACADEMICYEAR_BY_BRANCHID = "select act.acadamic_id,act.acadamic_name,act.startDate,act.endDate,act.Description,act.isActive,brn.BranchName from campus_acadamicyear act,campus_branch brn where act.BranchCode=brn.BranchCode and act.BranchCode=? order by isActive desc";

	public static final String GET_ALL_ACADEMICYEAR_BY_BRANCHID_SEARCH = "select acadamic_id,acadamic_name,startDate,endDate,Description,isActive from campus_acadamicyear where acadamic_name like ? order by isActive desc";
	
	
	
	public static final String CHECK_ACADEMICYEAR_NAME_WHILE_UPATE="select count(*)Acdname from campus_acadamicyear where acadamic_year = ? and (? between startDate and endDate or ? between startDate and endDate) and acadamic_id != ?";

	
	public static final String CHECK_ACADEMICYEAR_DATES_WHILE_UPATE="select count(*)datescount from campus_acadamicyear where (? between startDate and endDate or ? between startDate and endDate)and acadamic_id != ?";
	
	
	
	public static final String ACADEMIC_YEAR_CHECK_ASSINGMENT="select count(*) academicyear from campus_assignment where AcadamicID = ?";
	public static final String ACADEMIC_YEAR_CHECK_EXAMINATION="select count(*) academicyear from campus_examination where acadamicyear = ?";
	public static final String ACADEMIC_YEAR_CHECK_FEECOLLECTION="select count(*) academicyear from campus_fee_collection where accYear = ?";
	public static final String ACADEMIC_YEAR_CHECK_FEESETUP="select count(*) academicyear from campus_fee_setup where AccyearCode = ?";
	public static final String ACADEMIC_YEAR_CHECK_STAGESETUP="select count(*) academicyear from campus_fee_stagesetup where AccyearCode = ?";
	public static final String ACADEMIC_YEAR_CHECK_TERMDETAILS="select count(*) academicyear from campus_fee_termdetails where accyear = ?";
	public static final String ACADEMIC_YEAR_CHECK_PRIMARY="select count(*) academicyear from campus_grademarks_primary where accyear_id = ?";
	public static final String ACADEMIC_YEAR_CHECK_SECONDARY="select count(*) academicyear from campus_grademarks_secondary where accyear_id = ?";
	public static final String ACADEMIC_YEAR_CHECK_STUDENT="select count(*) academicyear from campus_student where fms_acadamicyear_id_int = ?";
	public static final String ACADEMIC_YEAR_CHECK_ENQUIRY="select count(*) academicyear from campus_student_enquiry where acadamicyear_id = ?";
	public static final String ACADEMIC_YEAR_CHECK_TIMETABLE_STUDENT="select count(*) academicyear from campus_timetable_student where accyearid = ?";
	public static final String ACADEMIC_YEAR_CHECK_TIMETABLE_TEACHER="select count(*) academicyear from campus_timetable_teacher where accyearid = ?";
}
