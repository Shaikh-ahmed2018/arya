package com.centris.campus.util;

public class UploadStudentXLSqlUtil {
	
	public static final String CHECK_BEFORINSERT_COUNT= "select count(*) from campus_student";
	public static final String CHECK_STUDENT_ID= "select count(*) from campus_student where  student_admissionno_var=?";
	public static final String CHECK_CATEGORY_ID= "select count(*) from campus_classstream where  classstream_name_var=?";
	public static final String CHECK_CLASS_ID= "select count(*) from campus_classdetail where  classdetails_name_var=?";
	public static final String CHECK_SECTION_ID="select count(*) from campus_classsection where  classsection_name_var=?";
	public static final String INSERT_STUDENT="insert into campus_student(student_id_int,student_rollno,student_admissionno_var,fms_acadamicyear_id_int,student_regno_var,student_fname_var,student_lname_var,student_dob_var,student_gender_var,student_bloodgroup_var,student_age_int,student_doj_var,student_religion_var,student_nationality_var,student_physicallychallenged,student_identificationmarks_var,student_siblingId,student_status_var,student_prehistory_var,student_remarks_var,createuser,createdate,student_caste,student_application_no,physicallychallenged_reason,locationId,isParentsGuardianWorking,transferCertificateNo,MEDIUM,casteCategory,adharNo,motherTounge,workingParentsGuardianName,dob_in_words) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_STREAM_ID="select classstream_id_int from campus_classstream where locationId=? AND classstream_name_var=?";
	public static final String GET_CLASS_ID="select classdetail_id_int from campus_classdetail where classdetails_name_var=?";
    public static final String GET_SECTION_ID="select classsection_id_int from campus_classsection where classsection_name_var=? and classdetail_id_int=?";
    public static final String GET_RELIGION_ID="select religionId from campus_religion where religion=?";
    public static final String GET_SIBLING_ID="select student_id_int from campus_student where student_admissionno_var=?";
    public static final String GET_CASTE_ID="select casteId from campus_caste where caste=? AND religionId=?";
    public static final String GET_TRANSPORT_ID="select type_id from transport_typedetails where type_name=?";
    public static final String GET_TRANSPORT_LOCATION_ID="select stage_id from campus_fee_stage where stage_name=?";
    public static final String GET_ROUTE_ID="select RouteCode from transport_route where RouteName=?";
    public static final String GET_SPECILIZATINON_ID="select Specialization_Id from campus_class_specialization where Specialization_name=?  and Stream_Id=? and ClassDetails_Id=?";
    public static final String GET_CASTE_CATEGORY_ID="SELECT castCatId FROM campus_caste_category WHERE casteCategory=? AND religionId=? AND casteId=?";
    public static final String GET_SCHOOL_LOCATION_ID="select Location_Id from campus_location where Location_Name=?";
    public static final String GET_ACADEMIC_YEAR_ID="select acadamic_id from campus_acadamicyear where acadamic_year=?";
    public static final String GET_START_END_ACADEMIC_YEAR="select count(*) from campus_acadamicyear where acadamic_id=? and startDate<=? and endDate >=?";
    public static final String CHECK_CLASS_BY_STREAM="select count(*) from campus_classdetail where classstream_id_int=? and classdetail_id_int=?";
    public static final String CHECK_SECTION_BY_CLASS="select count(*) from campus_classsection where classdetail_id_int=? and classsection_id_int=?";
    public static final String CHECK_SPECILIZATION_BY_CLASS_STREAM="select count(*) from campus_class_specialization where Specialization_Id=? and ClassDetails_Id=? and Stream_Id=?";
    public static final String SECTION_STRENGTH="SELECT classsection_strength_int FROM campus_classsection WHERE classsection_id_int=?";
    
    public static final String COUNT_STUDENT_PER_SECTION="select count(*) from campus_student_classdetails where classsection_id_int=? and fms_acadamicyear_id_int = ? and locationId = ?";
    
    public static final String GET_OCCUPATION_ID="select occupationId from campus_occupation where occupation=?";
    
	public static final String STUDENT_CLASS_REGISTRATION = "insert into campus_student_classdetails(student_id_int,fms_acadamicyear_id_int,fms_classstream_id_int,classdetail_id_int,classsection_id_int,specilization,createuser,createdate,locationId,preferedlocationId,student_rollno,student_imgurl_var,student_house,secondlanguage,thirdlanguage) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_HOUSE_ID = "select house_id from campus_house_settings where housename=? and loc_id=? and accyear_id = ?";
	public static final String STUDENT_HOUSE_REGISTRATION = "insert into campus_student_house(student_id,class_id,section_id,house_id,academic_year,loc_id) values (?,?,?,?,?,?)";
	public static final String GET_CLASS_ID_BY_LOC = "select classdetail_id_int from campus_classdetail where classdetails_name_var=? and locationId = ?";
	public static final String CHECK_CATEGORY_NAME = "SELECT COUNT(*) FROM library_category WHERE category_name=? AND category_code=?";
	
	public static String checkLibrarytypeCode="SELECT COUNT(*) FROM library_category WHERE category_code=?";
	public static final String GET_LOC_ID_BY_STU = "SELECT locationId FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ?";
}
