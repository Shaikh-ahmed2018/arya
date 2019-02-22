package com.centris.campus.util;

public class MarksUploadUtilConstants {

	

	// marks entry form//
	public static final String INSERT_MARKS_UPLOAD = "insert into campus_marks_upload(marks_upload_id,examid,subid,classid,sectionid,maximum_marks,required_marks ) values(?,?,?,?,?,?,?)";
	public static final String ENTER_STUDENT_MARKS = "insert into campus_marks(marks_upload_id,admissionno,scoredmarks) values(?,?,?)";
	public static final String SHOW_STUDENT_MARKS = "select student_admissionno_var,student_fname_var,student_mname_var,student_lname_var from campus_student where classsection_id_int=?";
	public static final String GET_STUDENT_MARKS = "select a.student_id_int,a.student_admissionno_var,concat(case when a.student_fname_var is NULL then '' else a.student_fname_var end,' ', case when a.student_lname_var is NULL then '' else a.student_lname_var end) name,b.scoredmarks from (select student_id_int,student_admissionno_var,student_fname_var,student_lname_var from campus_student where classsection_id_int=?  and classdetail_id_int=? order by student_fname_var) a left outer join (select * from campus_marks where marks_upload_id=(select max(marks_upload_id) from campus_marks_upload where examid=? and subid=?  and classid=? and sectionid=?)) b on a.student_id_int=b.admissionno";
	public static final String GET_MAX_MIN_MARKS = "select maximum_marks,required_marks from campus_marks_upload where marks_upload_id=(select max(marks_upload_id) from campus_marks_upload where examid=? and subid=? and classid=? and sectionid=?)";

	public static final String CHILD_INFO_ALLSTUDENTNAME = "select * from campus_student  where fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=?";

	public static final String GET_CAMPUS_STUDENT_DETAILS = "select stud.student_admissionno_var,stud.student_regno_var,strm.classstream_name_var,cldt.classdetails_name_var,clsc.classsection_name_var, accd.acadamicyear_year,mmar.maximum_marks,mmar.required_marks,scmar.scoredmarks,exam.examname,subt.subjectName,exam.examdate  from campus_student stud join campus_classstream strm on stud.fms_classstream_id_int = strm.classstream_id_int join campus_classdetail cldt on stud.classdetail_id_int = cldt.classdetail_id_int join campus_classsection clsc on stud.classsection_id_int = clsc.classsection_id_int join campus_acadamicyear accd on stud.fms_acadamicyear_id_int =accd.acadamicyear_id_int join campus_marks_upload mmar on stud.fms_classstream_id_int = mmar.categoryid join campus_marks scmar on mmar.marks_upload_id=scmar.marks_upload_id join campus_examination exam on exam.examid=mmar.examid join campus_subject subt on subt.subjectID=mmar.subid where stud.student_admissionno_var=?";
	public static final String GET_CAMPUS_STUDENT_DETAILS_BY_EXAM = "select stud.student_admissionno_var,stud.student_regno_var,strm.classstream_name_var,cldt.classdetails_name_var,clsc.classsection_name_var, accd.acadamicyear_year,mmar.maximum_marks,mmar.required_marks,scmar.scoredmarks,exam.examname,subt.subjectName,exam.examdate  from campus_student stud join campus_classstream strm on stud.fms_classstream_id_int = strm.classstream_id_int join campus_classdetail cldt on stud.classdetail_id_int = cldt.classdetail_id_int join campus_classsection clsc on stud.classsection_id_int = clsc.classsection_id_int join campus_acadamicyear accd on stud.fms_acadamicyear_id_int =accd.acadamicyear_id_int join campus_marks_upload mmar on stud.fms_classstream_id_int = mmar.categoryid join campus_marks scmar on mmar.marks_upload_id=scmar.marks_upload_id join campus_examination exam on exam.examid=mmar.examid join campus_subject subt on subt.subjectID=mmar.subid where stud.student_admissionno_var=? and exam.examname=?";

	public static final String GET_COUNT_EXAM_SUBJECT = "select marks_upload_id from campus_marks_upload where  examid=? and subid=? and classid=? and sectionid=?";
	public static final String GET_MARKS_COUNT = "select count(*) from campus_marks where marks_upload_id=? and admissionno=?";

	public static final String ALL_Carrer_Data = "select * from campus_careers";
	public static final String UPDATE_MARKS_UPLOAD = "update campus_marks_upload set maximum_marks=?,required_marks=? where examid=? and subid=? and categoryid=? and classid=? and sectionid=?";
	public static final String GET_MARKS_UPLOADID = "select marks_upload_id from campus_marks_upload where subid=? and examid=? and classid=? and sectionid=?";
	public static final String DELETE_OLDMARKS = "delete  from campus_marks where marks_upload_id=?";
	
	//Written by seshu
	
	public static final String GET_MARKSUPLOADED_LIST ="select e.examname,e.examid,c.classdetail_id_int,c.classdetails_name_var,s.classsection_id_int,s.classsection_name_var,sub.subjectID,sub.subjectName,et.examdate,et.examtime,et.endtime from campus_examination e,campus_examination_timetable et,campus_classdetail c,campus_classsection s,campus_subject sub  where  et.examinationid=e.examid and et.classid=c.classdetail_id_int and s.classdetail_id_int=c.classdetail_id_int and et.subjectid=sub.subjectID  and (e.examname like ? or c.classdetails_name_var like ? or s.classsection_name_var like ? or sub.subjectName like ?) order by e.examname,length(c.classdetail_id_int),c.classdetail_id_int,c.classdetails_name_var,length(s.classsection_id_int),s.classsection_id_int,s.classsection_name_var,sub.subjectName";
}
