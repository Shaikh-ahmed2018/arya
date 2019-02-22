package com.centris.campus.util;

public class SuddenHolidaySqlUtil {
	
	public static final String GET_SUDDEN_HOLIDAY_DETAILS = "select * from sms_suddenholidays_details ";
	public static final String STORE_SUDDENHOLIDAYS_SMS_DETAILS = "insert into sms_suddenholidays_details(SUDDENHOLIDAYS_CODE,SUDDENHOLIDAYS_DATE,SUDDENHOLIDAYS_CONTENT,isSection,isStudent,CREATE_TIME,CREATED_BY) values(?,?,?,?,?,?,?)";
	public static final String STORE_SUDDENHOLIDAYS_SECTION_DETAILS = "insert into sms_suddenholidays_section(SUDDENHOLIDAYS_CODE,SECTION_CODE,CREATE_TIME,CREATED_BY) values(?,?,?,?)";
	public static final String VALIDATE_SUDDENHOLIDAYS_SMS = "select count(*) from sms_suddenholidays_details where SUDDENHOLIDAYS_DATE=? and SUDDENHOLIDAYS_CONTENT=?";
	public static final String GET_ABSENT_STUDENT_DETAILS = "select se.classsection_name_var,cl.classdetails_name_var,concat(st.student_fname_var,' ',student_lname_var)as name,de.ABSENT_CODE,de.ABSENT_DATE,de.ABSENT_CONTENT from sms_absent_details de join sms_absent_student ab on ab.ABSENT_CODE = de.ABSENT_CODE join campus_student st on st.student_id_int = ab.STUDENT_ADM_NO join campus_classdetail cl on cl.classdetail_id_int = st.classdetail_id_int join campus_classsection se on se.classsection_id_int = st.classsection_id_int ";
	public static final String STORE_ABSENT_SMS_DETAILS = "insert into sms_absent_details(ABSENT_CODE,ABSENT_DATE,ABSENT_CONTENT,IsSection,IsStudent,CREATE_TIME,CREATED_BY) values(?,?,?,?,?,?,?)";
	public static final String STORE_ABSENT_STUDENT_DETAILS = "insert into sms_absent_student(ABSENT_CODE,STUDENT_ADM_NO,CREATED_TIME,CREATED_BY) values(?,?,?,?)";
	public static final String STORE_ABSENT_SECTION_DETAILS = "insert into sms_absent_section(ABSENT_CODE,SECTION_CODE,CREATED_TIME,CREATED_BY) values(?,?,?,?)";
	public static final String GET_COUNT_ABSENT = "select count(*) from sms_absent_details where ABSENT_CONTENT=? and ABSENT_DATE=?";
	
	
	
	
	
	
}
