package com.centris.campus.util;

public class CommunicateUtilConstants {

	public static final String GET_ALL_TEACHER_REMARKS_DETAILS = "select c.commentCode,c.Date,concat(t.FirstName,' ',t.LastName) teachername,s.subjectName,c.remarks from campus_comments c,campus_subject s,campus_teachers t where c.TeacherCode=t.TeacherID and c.subjectCode=s.subjectID ORDER BY c.Date DESC";
	
	
	public static final String GET_ALL_STUDENT_REMARKS_DETAILS = "select re.commentCode,re.Date,concat(stu.student_fname_var,' ',stu.student_lname_var )studentname,sub.subjectName, cls.classdetails_name_var, sec.classsection_name_var,re.remarks from campus_comments re, campus_subject sub, campus_student stu,campus_classdetail cls, campus_classsection sec where  re.subjectCode=sub.subjectID and re.studentId=stu.student_id_int and cls.classdetail_id_int=stu.classdetail_id_int  and sec.classsection_id_int=stu.classsection_id_int ORDER BY re.Date DESC";
	
	
	public static final String GET_STREAM_DETAILS = "select classstream_id_int,classstream_name_var from campus_classstream order by classstream_name_var";
	public static final String GET_CLASS_DETAILS = "select classdetail_id_int,classdetails_name_var from campus_classdetail order by classdetail_id_int";
	public static final String GET_SECTION_DETAILS = "select ccd.classdetail_id_int,ccd.classdetails_name_var,cls.classsection_id_int,concat(ccd.classdetails_name_var,'-',cls.classsection_name_var) as classsection_name_var from campus_classdetail ccd,campus_classsection cls where ccd.classdetail_id_int=cls.classdetail_id_int and cls.classdetail_id_int=?";
	public static final String GET_STUDENT_DETAILS = "select student_id_int,student_fname_var from campus_student order by student_fname_var";
	//public static final String GET_TEACHER_DETAILS = "select TeacherID, concat(FirstName, ' ' ,LastName) as Teachername from campus_teachers order by FirstName";

	public static final String GET_TEACHER_DETAILS = "select TeacherID, FirstName from campus_teachers order by FirstName";
	public static final String GET_SUBJECT_DETAILS = "select subjectID,subjectName from campus_subject order by subjectName";
	
	public static final String SAVE_REMARK = "insert into campus_comments (commentCode,accyearId,studentId,TeacherCode,remarks,remarksType,subjectCode,isApproved,commentedby,createtime,Date) values(?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SEARCH_STUDENT_REMARKS_DETAILS = "select re.Date,concat(stu.student_fname_var,' ',stu.student_lname_var )studentname,sub.subjectName,  cls.classdetails_name_var, sec.classsection_name_var,re.remarks from  campus_comments re, campus_subject sub, campus_student stu,campus_classdetail cls, campus_classsection sec where  re.subjectCode=sub.subjectID and re.studentId=stu.student_id_int  and cls.classdetail_id_int=stu.classdetail_id_int  and sec.classsection_id_int=stu.classsection_id_int and re.Date between ? and ?";
	public static final String SEARCH_TEACHER_REMARKS_DETAILS = "select c.Date,concat(t.FirstName,' ',t.LastName) teachername,s.subjectName,c.remarks from campus_comments c,campus_subject s,campus_teachers t where c.TeacherCode=t.TeacherID and c.subjectCode=s.subjectID and c.Date between ? and ?";

	public static final String VALIDATE_STUDENT_REMARK = "select count(*) remarkcount from campus_comments where studentId=? and remarks=? and subjectCode=? and Date=?";
	public static final String VALIDATE_TEACHER_REMARK = "select count(*)remarkcount from campus_comments where TeacherCode=? and remarks=? and subjectCode=? and Date=?";
	public static final String GET_ALL_STUDENT_MEETING_DETAILS = "select me.meetingdate,me.starttime,me.endtime,me.description,me.title,cls.classdetails_name_var,sec.classsection_name_var, concat(stu.student_fname_var,' ',stu.student_lname_var)studentname, sub.subjectName from campus_meeting me, campus_classdetail cls, campus_classsection sec,campus_student stu, campus_subject sub  where me.classid=cls.classdetail_id_int and me.sectionid=sec.classsection_id_int and me.studentid=stu.student_id_int and me.subjectId=sub.subjectID";
	
	
	public static final String SAVE_MEETING = "insert into campus_meeting(meetingid,meetingdate,starttime,endtime,description,createdby,status,createdate,title,subjectId,venuedetails,meetingFor)values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SAVE_MEETING1 = "insert into campus_meeting_details(MeetingCode,StudentId,TeacherId) values(?,?,?)";
	
	public static final String GET_ALL_TEACHER_MEETING_DETAILS = "select me.meetingdate,me.starttime,me.endtime,me.description,me.title, concat(tea.FirstName,' ',LastName)teachername,sub.subjectName from campus_meeting me,campus_teachers tea,campus_subject sub where me.teacherid=tea.TeacherID and me.subjectId=sub.subjectID";

	public static final String SEARCH_STUDENT_MEETING_DETAILS = "select me.meetingdate,me.starttime,me.endtime,me.description,me.title,cls.classdetails_name_var,sec.classsection_name_var, concat(stu.student_fname_var,' ',stu.student_lname_var)studentname, sub.subjectName  from campus_meeting me, campus_classdetail cls, campus_classsection sec,campus_student stu, campus_subject sub  where me.classid=cls.classdetail_id_int  and me.sectionid=sec.classsection_id_int and me.studentid=stu.student_id_int and me.subjectId=sub.subjectID and me.meetingdate between ? and ?";
	public static final String SEARCH_TEACHER_MEETING_DETAILS = "select me.meetingdate,me.starttime,me.endtime,me.description,me.title, concat(tea.FirstName,' ',LastName)teachername,sub.subjectName from campus_meeting me,campus_teachers tea,campus_subject sub where me.teacherid=tea.TeacherID and me.subjectId=sub.subjectID and me.meetingdate between ? and ?";
	
	public static final String CREATE_BDAY = "insert into campus_dob_details(DOB_CODE,DOB_DATE,DOB_CONTENT,SMS_STATUS,CREATE_TIME,CREATED_BY) values(?,?,?,?,?,?)";
	public static final String CREATE_BDAY1 = "insert into campus_dob_student(DOB_CODE,STUDENT_ADM_NO,CREATED_TIME,CREATED_BY) values (?,?,?,?)";
	
	
	public static final String GET_ALL_BDAY_WISHES = "select d.DOB_CODE,d.DOB_DATE,d.DOB_CONTENT,ds.STUDENT_ADM_NO from campus_dob_details d,campus_dob_student ds where d.DOB_CODE=ds.DOB_CODE ORDER BY d.DOB_DATE DESC ";
	
	
	public static final String GET_TEACHER_BDAY_WISHES="select concat(FirstName,' ',LastName) as teachername from campus_teachers where TeacherID=?";
	
	
	public static final String GET_STUDENT_BDAY_WISHES="select concat(stu.student_fname_var,' ',student_lname_var)as studentname,cls.classdetails_name_var, sec.classsection_name_var from campus_student stu, campus_classdetail cls ,campus_classsection sec where stu.classdetail_id_int=cls.classdetail_id_int and stu.classsection_id_int=sec.classsection_id_int and stu.student_id_int=?";
	
	
	public static final String SEARCH_ALL_BDAY_DETAILS = "select d.DOB_CODE,d.DOB_DATE,d.DOB_CONTENT,ds.STUDENT_ADM_NO from campus_dob_details d,campus_dob_student ds where d.DOB_CODE=ds.DOB_CODE and d.DOB_DATE between ? and ?";
	
	public static final String GET_ALL_MEETING_DETAILS = "select me.meetingid,me.meetingdate,me.starttime,me.endtime,me.title,me.venuedetails,md.StudentId,md.TeacherId,me.venuedetails,case when me.subjectId='general' then me.subjectId else(select subjectName from campus_subject where subjectID=me.subjectId) end subjectname from campus_meeting me, campus_meeting_details md where meetingid=MeetingCode ORDER BY me.meetingdate DESC";
	
	public static final String GET_TEACHER_MEETING ="select FirstName from campus_teachers where TeacherID=?";
	
	public static final String GET_STUDENT_MEETING = "select stu.student_fname_var,cls.classdetails_name_var, sec.classsection_name_var from campus_student stu, campus_classdetail cls ,campus_classsection sec where stu.classdetail_id_int=cls.classdetail_id_int and stu.classsection_id_int=sec.classsection_id_int and stu.student_id_int=?";

	public static final String SEARCH_ALL_MEETING_DETAILS = "select me.meetingid,me.meetingdate,me.starttime,me.endtime,me.title,me.venuedetails,md.StudentId,md.TeacherId,case when me.subjectId='general' then me.subjectId else(select subjectName from campus_subject where subjectID=me.subjectId) end subjectname from campus_meeting me, campus_meeting_details md where meetingid=MeetingCode and meetingdate between ? and ?";



	public static final String EDIT_STUDENT_REMARK = "select re.commentCode,concat(stu.student_fname_var,' ',student_lname_var) as studentname from campus_comments re,campus_student stu where re.studentId=stu.student_id_int and re.commentCode=?";




}
