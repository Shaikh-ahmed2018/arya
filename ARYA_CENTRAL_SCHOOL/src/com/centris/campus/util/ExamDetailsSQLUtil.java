package com.centris.campus.util;


public class ExamDetailsSQLUtil {
	public static final String INSERT_MARK_ENTRY_STUDENTTWISE="insert into campus_studentwise_mark_details(Stu_mark_id,classid,sectionid,exam_id,Academic_yearid,sub_id,attendance_status,scored_marks,stu_id,location_id,`notebook_marks`,`subject_enrich_marks`,`total_marks`,`max_notebook_marks`,`max_subjenrich_marks`,`created_by`,`max_periodic_marks`,`periodic_test`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String MARKENTRY_STUDENTWISE_STATUS="select count(class_id) from campus_studentwise_mark_details where class_id=?";
	public static final String GET_LOCATION="select Location_Name from campus_location where Location_Id like ?";
	public static final String INSERT_MARK_ENTRY_SUBJECTTWISE="insert into campus_subject_marks_wise(Sub_marks_id,Accyear_Id,classId,ExamId,SectionId,subject_id,loc_id,StudentId,scoredmarks,statusvalues)values(?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_LISTOFEXAM_CODES="select examcode,examname from campus_examination where loc_id=? and acadamicyear=?";
	public static final String GET_CLASS="select classdetails_name_var from campus_classdetail where classdetail_id_int=?";
	public static final String UPDATE_MARK_ENTRY_SUBJECTTWISE="update campus_subject_marks_wise set scoredmarks=?,statusvalues=? where Sub_marks_id=?";
	public static final String UPDATE_MARK_ENTRY_STUDENTTWISE="update campus_studentwise_mark_details set attendance_status=?,scored_marks=?,`notebook_marks`=?,`subject_enrich_marks`=?,`total_marks`=?,`max_notebook_marks`=?,`max_subjenrich_marks`=?,`modify_by`=?,`modify_date`=?,`max_periodic_marks`=?,`periodic_test`=? where Stu_mark_id=? and exam_id=?";
	public static final String INSERT_GRADING_SCALE = "INSERT INTO `campus_student_co_scholastic_areas` (`student_id`,`location_id`,`acc_yearid`,`class_id`,`section_id`,`work_edu_grade`,`art_edu_grade`,`health_edu_grade`,`discipline_grade`,`creadted_by`,exam_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_GRADING_SCALE = "update campus_student_co_scholastic_areas set `work_edu_grade`=?,`art_edu_grade`=?,`health_edu_grade`=?,`discipline_grade`=?,`modify_by`=?,`modify_date`=? where `student_id`=? and `location_id`=? and `acc_yearid`=? and `class_id`=? and exam_id=?";
	public static final String GET_EXAM_COUNT = "SELECT COUNT(*) AS COUNT FROM `campus_studentwise_mark_details` WHERE `exam_id`=? AND `Academic_yearid`=? AND `location_id`=?";
}
