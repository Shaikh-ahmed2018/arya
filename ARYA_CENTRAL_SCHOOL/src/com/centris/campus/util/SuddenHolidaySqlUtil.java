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
	
	public static final String GET_ACCYEAR = "select acadamic_id,acadamic_year from campus_acadamicyear order by startDate";
	public static final String GET_STREAMS = "select classstream_id_int,classstream_name_var from campus_classstream";
	public static final String GET_CLASSES = "select classdetail_id_int,classdetails_name_var from campus_classdetail where classstream_id_int like ? order by length(classdetail_id_int),classdetail_id_int asc";
	public static final String GET_SECTIONS = "select distinct classsection_id_int,classsection_name_var from campus_classsection where classdetail_id_int =? and locationId=?  order by classsection_name_var ";
	public static final String GETLOCATION = "select Location_Id,Location_Name from campus_location";
	public static final String GET_STUDENTSiNFORMATION = "select st.student_id_int,student_admissionno_var,concat(student_fname_var,' ',student_lname_var) as studentname,student_age_int,student_doj_var,FatherName,mobileno, student_mothername_var,student_mothermobileno_var from campus_student st,campus_parents p,campus_parentchildrelation pc where pc.stu_addmissionNo=st.student_id_int and pc.parentid=p.ParentID and fms_acadamicyear_id_int=?  and fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and st.student_status_var='active'"; 
	
	public static final String GET_SELECTED_ITEMS = "select acc.acadamic_year,s.classstream_name_var,c.classdetails_name_var,sec.classsection_name_var,Location_Name from campus_acadamicyear acc,campus_classstream s,campus_classdetail c,campus_classsection sec,campus_location where acc.acadamic_id=? and s.classstream_id_int=? and c.classdetail_id_int=? and sec.classsection_id_int like ? and Location_Id=?";
	
	public static final String GET_CLASS = "select classdetail_id_int,classdetails_name_var from campus_classdetail where locationId = ? order by length(classdetail_id_int),classdetail_id_int asc";
	
	
	
	
	
	
}
