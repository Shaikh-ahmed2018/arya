package com.centris.campus.util;

public class HolidayMasterSqlUtil {


	public static final String GET_ALL_YEARS = "select accyear from onsite_accyear order by accyear";
	public static final String INSERT_HOLIDAY = "insert into campus_holidaymaster(HOL_ID,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,HOLIDAY_TYPE,CURRENT_YEAR,CREATEDBY,LOC_ID)values(?,?,?,?,?,?,?,?)";
	public static final String GET_ALL_HOLIDAYS = "select distinct loc.LocationCode,loc.Location,HOLIDAY_DATE,HOLIDAY_NAME,WEEKDAY from onsite_holidaymaster hm,ai_emp_location loc where hm.Location=loc.LocationCode and hm.Location like ? and hm.CURRENT_YEAR=? order by HOLIDAY_DATE  asc";
	public static final String GET_EDIT_HOLIDAY = "select HOL_ID,LOC_ID,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,CURRENT_YEAR,HOLIDAY_TYPE from campus_holidaymaster where HOL_ID like ?";
	public static final String DELETE_SINGLE_HOLIDAY = "delete from campus_holidaymaster where HOL_ID = ?";
	public static final String DATE_VALIDATE = "select count(*) from campus_holidaymaster where HOLIDAY_DATE=? and LOC_ID like ? and CURRENT_YEAR like ?";
	public static final String GET_DISTINCT_HOLIDAYLIST = "select distinct HOLIDAY_DATE,Location from onsite_holidaymaster";
	public static final String UPDATE_HOLIDAY = "UPDATE campus_holidaymaster SET HOLIDAY_NAME=?,HOLIDAY_TYPE=?,MODIFIED_BY=?,MODIFIED_DATE=?,HOLIDAY_DATE=?,LOC_ID=? WHERE HOL_ID like ?";
	public static final String CHECK_DUPLICATE_HOLIDAY="select count(*) from onsite_holidaymaster where HOLIDAY_DATE=? and Location=?";
	public static final String SELECT_LOCATION = "select LocationCode,Location from ai_emp_location where isActive='Y' and LocationCode in (select LocationCode from onsite_groupmapping where Groupid=?)";
	public static final String GET_ALL_HOLIDAY = "select HOLIDAY_NAME,WEEKDAY,HOLIDAY_DATE,HOLIDAY_TYPE,LOC_ID,HOL_ID from campus_holidaymaster where CURRENT_YEAR=? AND LOC_ID LIKE ? order by HOLIDAY_DATE ";
	public static final String GET_SEARCH_HOLIDAY = "select * from campus_holidaymaster where  LOC_ID like ? AND CURRENT_YEAR=? ";
	public static final String HOLIDAYNAME_VALIDATE = "SELECT COUNT(*) FROM campus_holidaymaster WHERE HOLIDAY_NAME=? AND LOC_ID like ? AND CURRENT_YEAR=?";


}
