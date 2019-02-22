package com.centris.campus.util;

public class StudentPromotionSqlUtil {

	// new
	//concat(cs.student_fname_var,cs.student_lname_var)
	public static final String RETRIVEYEAR = "SELECT acadamic_id,acadamic_year from campus_acadamicyear order by startDate";
	//public static final String GET_STUDENT_DATA = "select cs.student_id_int,cs.student_admissionno_var,cs.student_fname_var,cs.student_lname_var,cs.student_status_var,ccs.classstream_id_int,ccs.classstream_name_var,ccd.classdetail_id_int,ccd.classdetails_name_var,cce.classsection_id_int,cce.classsection_name_var,ca.acadamic_id,ca.acadamic_year from campus_student cs,campus_classstream ccs,campus_classdetail ccd,campus_classsection cce,campus_acadamicyear ca  where cs.fms_classstream_id_int=ccs.classstream_id_int  and cs.classdetail_id_int=ccd.classdetail_id_int and cs.classsection_id_int=cce.classsection_id_int and cs.fms_acadamicyear_id_int=ca.acadamic_id and cs.student_status_var='active' and cs.classsection_id_int= ? and cs.fms_acadamicyear_id_int= ? and cs.classdetail_id_int=? and cs.specilization=? and cs.student_promotionstatus='NOTPROMOTED'";
	public static final String GET_STUDENT_DATA = "SELECT cs.student_id_int,cs.student_admissionno_var,cs.student_fname_var,cs.student_lname_var,cs.student_status_var,ccs.classstream_id_int,ccs.classstream_name_var,ccd.classdetail_id_int,ccd.classdetails_name_var,cce.classsection_id_int,cce.classsection_name_var,ca.acadamic_id,ca.acadamic_year FROM campus_student cs,campus_student_classdetails csc,campus_classstream ccs,campus_classdetail ccd,campus_classsection cce,campus_acadamicyear ca WHERE csc.fms_classstream_id_int=ccs.classstream_id_int  AND csc.classdetail_id_int=ccd.classdetail_id_int AND csc.classsection_id_int=cce.classsection_id_int AND cs.fms_acadamicyear_id_int=ca.acadamic_id AND cs.student_status_var='active' AND csc.classsection_id_int= ? AND cs.fms_acadamicyear_id_int= ? AND csc.classdetail_id_int=? AND csc.specilization=? AND csc.student_promotionstatus='NOTPROMOTED'";
	public static final String INSERT_STUDENT_PROMOTION = "insert into campus_studentpromotion(studentpromotion_id_int,student_admissionno_var,student_id_int,studentpromotion_fromclass_var,studentpromotion_toclass_var,studentpromotion_acadamicyearfrom_var,studentpromotion_acadamicyearto_var,studentpromotion_fromsection_var,studentpromotion_tosection_var,createuser,createdate,studentpromotion_fromSpecialization,studentpromotion_toSpecialization) values(?,?,?,?,?,?,?,?,?,?,now(),?,?)";
	public static final String UPDATE_STUDENT = "update campus_student set student_promotionstatus=? where student_id_int=?";
	public static final String GET_ADMISSIONNO_VAL = "select student_admissionno_var from campus_student where student_id_int=?";
	public static final String CHECK_DUPLICATE_ADDMISSIONNO = "select count(*) from campus_student where  student_admissionno_var=? and fms_acadamicyear_id_int=?";
	public static final String CLASS_STREAM = "select classstream_name_var from campus_classstream where classstream_id_int=?";
	public static final String GET_ACADEMIC_YEAR_BYID = "select acadamic_year from campus_acadamicyear where acadamic_id=?";
	public static final String CLASS_NAME = "select * from campus_classdetail where classdetail_id_int =?";
	public static final String SECTION_NAME = "select * from campus_classsection where classsection_id_int =?";
	public static final String COUNT_ACADEMIC_YEAR = "select count(*) from campus_student ";
	public static final String COUNT_CLASS = "select count(*) from campus_student where classdetail_id_int=?";
	public static final String STUDENT_REGISTRATION = "insert into campus_student(student_id_int,student_rollno,student_admissionno_var,fms_acadamicyear_id_int,classdetail_id_int,classsection_id_int,student_regno_var,student_fname_var,student_lname_var,student_dob_var,student_gender_var,student_bloodgroup_var,student_age_int,student_imgurl_var,student_doj_var,student_religion_var,student_nationality_var,student_promotionstatus,student_physicallychallenged,student_identificationmarks_var,student_siblingId,student_status_var,student_prehistory_var,student_remarks_var,createuser,createdate,student_caste,student_application_no,isTransport,TransportType,StageId) values (?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)";
	/*public static final String STUDENT_REGISTRATION = "insert into campus_student(student_id_int,student_rollno,student_admissionno_var,fms_classstream_id_int,fms_acadamicyear_id_int,classdetail_id_int,classsection_id_int,student_regno_var,student_fname_var,student_lname_var,student_dob_var,student_gender_var,student_bloodgroup_var,student_age_int,student_imgurl_var,student_doj_var,student_religion_var,student_nationality_var,student_scholorship_var,student_promotionstatus,student_physicallychallenged,student_identificationmarks_var,student_grade,student_siblingId,student_status_var,student_prehistory_var,student_remarks_var,createuser,createdate,student_quota,student_caste,student_application_no,isTransport,isHostel,isConcession,isRTE,TransportType,StageId,emisNo,physicallychallenged_reason,student_tc_path,student_birthcert_path) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
	/*public static final String STUDENT_REGISTRATION = "insert into campus_student(student_id_int,student_rollno,student_admissionno_var,fms_classstream_id_int,fms_acadamicyear_id_int,classdetail_id_int,classsection_id_int,student_regno_var,student_fname_var,student_lname_var,student_dob_var,student_gender_var,student_bloodgroup_var,student_age_int,student_imgurl_var,student_doj_var,student_religion_var,student_nationality_var,student_scholorship_var,student_promotionstatus,student_physicallychallenged,student_identificationmarks_var,student_grade,student_siblingId,student_status_var,student_prehistory_var,student_remarks_var,createuser,createdate,student_quota,student_caste,student_application_no,isTransport,isHostel,isConcession,isRTE,TransportType,StageId,emisNo,physicallychallenged_reason,student_tc_path,student_birthcert_path) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
	public static final String PARENT_REG_CHECK = "select count(*) parentid from campus_parents where ParentID=?";
	public static final String PARENT_REG = "insert into campus_parents(ParentID,FirstName,Qualification,address,mobileno,UserName,gender,email,password,pstatus) values(?,?,?,?,?, ?,?,?,?,?)";
	public static final String PARENT_REG_UPDATE = "update campus_parents set FirstName=?,Qualification=?,address=?,mobileno=?,UserName=?,gender=?,email=?,password=? where ParentID=?";
	public static final String PARENT_CHILD = "insert into campus_parentchildrelation(parentid,stu_addmissionNo,relationship) values(?,?,?)";
	public static final String PARENT_CHILD_UPDATE = "update campus_parentchildrelation set relationship=? where parentid=? and stu_addmissionNo=?";
	public static final String GET_ENQUIRY_STUDENT_DATA = "select st.student_id_int,st.student_admissionno_var,case when st.student_lname_var is null then st.student_fname_var else concat_ws(' ',st.student_fname_var,st.student_lname_var) end studentname,cl.classdetails_name_var,sc.classsection_name_var,ac.acadamic_year from campus_student st,campus_studentpromotion sp,campus_classdetail cl,campus_classsection sc,campus_acadamicyear ac where sp.student_id_int=st.student_id_int and sp.studentpromotion_toclass_var=cl.classdetail_id_int and sp.studentpromotion_tosection_var= sc.classsection_id_int and sp.studentpromotion_acadamicyearto_var=ac.acadamic_id order by classdetails_name_var ,classsection_name_var";
	public static final String GET_ENQUIRY_STUDENT_SEARCH_DATA ="select sp.student_id_int,st.student_admissionno_var,case when st.student_lname_var is null then st.student_fname_var else concat_ws(' ',st.student_fname_var,st.student_lname_var) end studentname,cl.classdetails_name_var,sc.classsection_name_var,ac.acadamic_year from campus_student st,campus_studentpromotion sp,campus_classdetail cl,campus_classsection sc,campus_acadamicyear ac where sp.student_id_int=st.student_id_int and sp.studentpromotion_toclass_var=cl.classdetail_id_int and sp.studentpromotion_tosection_var= sc.classsection_id_int and sp.studentpromotion_acadamicyearto_var=ac.acadamic_id and cl.classdetail_id_int=? and sc.classsection_id_int=? order by classdetails_name_var ,classsection_name_var";
	public static final String GET_SECTION_DATA = "select * from campus_classsection where classdetail_id_int=? and classsection_name_var=? ";
	public static final String GET_STREAM_DATA="select class.classstream_id_int,stream.classstream_name_var,class.classdetails_name_var from campus_classdetail class join campus_classstream stream on stream.classstream_id_int=class.classstream_id_int  where classdetail_id_int=?";
	
	
	/*
	 * 
	 * 
	 * 
	 * public static final String INSERT_EVENT_FORM =
	 * "insert into campus_event(eventid,eventname,eventdate,starttime,endtime,description,invitation,status,createdby) values(?,?,?,?,?,?,?,?,?)"
	 * ; // Jagannath Ends
	 * 
	 * public static final String CLASS_STREAM_NAME =
	 * "select  classstream_id_int,classstream_name_var from campus_classstream"
	 * ;
	 * 
	 * public static final String CLASS_DETILS_NAME =
	 * "select lcd.classdetails_name_var from campus_classdetail lcd join campus_classstream lcs on lcd.classstream_id_int=lcs.classstream_id_int where lcd.classstream_id_int=?"
	 * ;
	 * 
	 * public static final String INSERT_ADDSUBJECT_FORM =
	 * "insert into campus_subject(subjectID,subjectName,syllabous,classid,decription,createdby) values(?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String VALIDATE_CLASS_SUBJECT =
	 * "select count(*) count from campus_subject where subjectName=? and classid=?"
	 * ;
	 * 
	 * public static final String INSERT_ATTENDANCE =
	 * "insert into campus_attendence(attendencedate,addmissionno,attendence) values(?,?,?)"
	 * ;
	 * 
	 * public static final String CLASSSTREAM_ID =
	 * "select classstream_id_int from campus_classstream";
	 * 
	 * public static final String MONTISSORY_SYLLABUS_DETAILS =
	 * "select lcd.classdetails_name_var,ls.subjectName,ls.syllabous  from campus_classdetail lcd join campus_subject ls  on ls.classid=lcd.classdetail_id_int and lcd.classstream_id_int =?"
	 * ; public static final String GET_CATEGORY =
	 * "select classstream_id_int,classstream_name_var from campus_classstream";
	 * public static final String GET_SYLLABUS_DETAILS =
	 * "select lcd.classdetails_name_var,ls.subjectName,ls.syllabous  from campus_classdetail lcd inner join campus_subject ls on lcd.classdetail_id_int = ls.classid where ls.classid=?"
	 * ;
	 * 
	 * public static final String PRIMARY_SYLLABUS_DETAILS =
	 * "select lcd.classdetails_name_var,ls.subjectName,ls.syllabous  from campus_classdetail lcd join campus_subject ls  on lcd.classstream_id_int =? and ls.classid=?"
	 * ; public static final String INSERT_CREATE_MEATING =
	 * "insert into campus_meeting(meetingid,meetingdate,starttime ,endtime ,description ,forall,classid ,createdby,title) values(?,?,?,?,?,?,?,?,?)"
	 * ; public static final String INSERT_CREATE_MEATINGN =
	 * "insert into campus_meeting(meetingid,meetingdate,starttime ,endtime ,description ,forall,allcalsses,allsections,classid,sectionid,allstudents,studentid,createdby,title) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String INSERT_ADD_JOB =
	 * "insert into campus_careers (jobcode,qualification,noofpositions,experience,description,createdby) values (?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String UPCOMING_MEETING =
	 * "select * from campus_meeting where status='active' order by meetingid desc"
	 * ;
	 * 
	 * public static final String UPCOMING_MEETING_ID_DETAILS =
	 * "select * from campus_meeting  where meetingid=?";
	 * 
	 * public static final String UPCOMING_EVENT =
	 * "select eventid,eventdate,starttime,endtime,eventname,description,status,invitation from campus_event where status='active' and eventdate > ? order by eventid desc"
	 * ;
	 * 
	 * public static final String UPCOMING_EVENT_DETAILS =
	 * "select * from campus_event where eventid=?";
	 * 
	 * public static final String GET_STUDENT_DETAILS =
	 * "select campus_student.student_fname_var,campus_classdetail.classdetails_name_var,campus_student.student_admissionno_var from campus_student join campus_classdetail on campus_student.classdetail_id_int=campus_classdetail.classdetail_id_int where student_admissionno_var in(select campus_parentchildrelation.stu_addmissionNo from campus_parents join campus_parentchildrelation  on campus_parents.ParentID=campus_parentchildrelation.parentid where campus_parents.ParentID=?)"
	 * ;
	 * 
	 * public static final String GET_STUDENT_ATTENDANCE =
	 * "select monthname(attendencedate)as monthname,count(*) as totalworkingdays ,(select count(*) from campus_attendence where attendence='present'and addmissionno=?) as present,(select count(*) from campus_attendence where attendence='absent' and addmissionno=?) as absent from campus_attendence group by attendencedate"
	 * ;
	 * 
	 * 
	 * 
	 * public static final String Class_oneview =
	 * "select  * from campus_student where classdetail_id_int=1 and classsection_id_int=1"
	 * ;
	 * 
	 * public static final String Class_twoview =
	 * "select  * from campus_student where classdetail_id_int=2 and classsection_id_int=2"
	 * ;
	 * 
	 * public static final String Class_threeview =
	 * "select  * from campus_student where classdetail_id_int=3 and classsection_id_int=3"
	 * ;
	 * 
	 * public static final String STUD_DETAIL_DISPLAY =
	 * "select st.student_location,st.student_towncity,st.student_zippostcode,st.student_religion_var,st.student_street1,st.student_street2,concat(st.student_fname_var,' ',st.student_lname_var)StudentName,st.student_admissionno_var,acc.acadamicyear_year,student_regno_var,st.student_dob_var,st.student_fathername_var,st.student_gaurdianname_var,st.student_gardian_mobileno,st.student_doj_var,st.student_bloodgroup_var,st.student_fathermobileno_var,st.student_mothername_var,st.student_mothermobileno_var,st.student_fathermobileno_var,st.student_imgurl_var from campus_student st join campus_acadamicyear acc on acc.acadamicyear_id_int=st.fms_acadamicyear_id_int where st.student_id_int=?"
	 * ;
	 * 
	 * public static final String Carrer_Data =
	 * "select * from campus_careers where status='active'";
	 * 
	 * public static final String Upload_NoticeForm =
	 * "insert into campus_noticeboard(noticeid,title,description,status,startdate,enddate,filepath,createdby) values (?,?,?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String Upload_Image =
	 * "insert into campus_photos(photoid,name,albumname,path,uploaddate,createdby) values (?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String Upload_Video =
	 * "insert into campus_photos(photoid,name,albumname,path,uploaddate,createdby) values (?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String View_Image = "select path from campus_photos";
	 * 
	 * public static final String NOTICEBOARDQUERY =
	 * " select * from campus_noticeboard order by length(noticeid),noticeid";
	 * 
	 * public static final String DELETENOTICEBOARDQUERY =
	 * "update campus_noticeboard set status='InActive' where noticeid=?";
	 * public static final String EDITNOTICEBOARDQUERY =
	 * " select * from campus_noticeboard where noticeid=?"; public static final
	 * String ACTIVENOTICEBOARDQUERY =
	 * " select * from campus_noticeboard where status='Active'"; public static
	 * final String INACTIVENOTICEBOARDQUERY =
	 * " select * from campus_noticeboard where status='InActive'"; public
	 * static final String UPDATENOTICEBOARDQUERY =
	 * "UPDATE campus_noticeboard SET title=?,description=?,status=?,startdate=?,enddate=?,createdby=?,createtime=? WHERE noticeid = ?"
	 * ;
	 * 
	 * public static final String VIEW_DATA =
	 * "select * from campus_careers where jobcode=?"; public static final
	 * String DELETE_CAREER = "delete from campus_careers where jobcode=?";
	 * public static final String UPDATE_CAREER =
	 * "update campus_careers SET jobcode=?,qualification=?, noofpositions=?, experience=?, description=?, status=? where jobcode=?"
	 * ;
	 * 
	 * public static final String INSERT_CONTACT_DETAILS =
	 * "insert into campus_contactus(name,address,city,country,phoneno,email,comments) values(?,?,?,?,?,?,?)"
	 * ; public static final String SELECT_FACULTY_DETAILS =
	 * "SELECT t.FirstName,t.LastName,t.Qualification,des.designationName designation,t.Address,t.MobileNo,t.email  FROM campus_teachers t join campus_designation des on des.DesignationCode=t.designation where  t.tstatus='ACTIVE'"
	 * ;
	 * 
	 * public static final String SELCT_ALL_DEACTIVEMEETINGS =
	 * "select * from campus_meeting where status='deactive' or status='hold'";
	 * public static final String GET_TEACHER =
	 * "Select tea.FirstName,tea.LastName,tea.MobileNo,tea.Address,tea.email,tea.Qualification,des.designationName from campus_teachers tea,campus_designation des where tea.designation=des.DesignationCode and TeacherID = ?"
	 * ; public static final String Update_Meeting =
	 * "update campus_meeting set status = ? where meetingid = ? ";
	 * 
	 * public static final String Update_Event =
	 * " update campus_event set status = ? where eventid = ? ";
	 * 
	 * public static final String Delete_Meeting =
	 * "delete from campus_meeting where meetingid = ?"; public static final
	 * String Delete_Event = "delete from campus_event where eventid = ?";
	 * 
	 * public static final String SELCT_ALL_DEACTIVEEVENTS =
	 * "select * from campus_event where status='deactive' or status='hold' ";
	 * public static final String SELECTE_ALL_DEACTIVEREMARKS =
	 * "select rem.remarksCode,rem.remarksType,rem.commentedby,rem.remarks,rem.createtime,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when rem.remarksType='general' then '-' else sub.subjectName end SubjectName,concat(cls.classdetails_name_var,' - ',sec.classsection_name_var)ClassName from campus_student stu, campus_admin_remarks rem  left outer join campus_subject sub on sub.subjectID=rem.subjectCode,campus_classdetail cls ,campus_classsection sec where rem.AdmissionNo = stu.student_id_int and cls.classdetail_id_int in (select classdetail_id_int from campus_student where student_id_int=rem.AdmissionNo)and sec.classsection_id_int in (select classsection_id_int from campus_student where student_id_int = rem.AdmissionNo)and isApproved='N'  order by studentname"
	 * ; public static final String SELECTE_ALL_TEACHER_DEACTIVEREMARKS =
	 * "select rem.remarksCode,rem.remarksType,case when ad.FirstName is null then ad.LastName else concat(ad.FirstName,' ',ad.LastName)end commentedby,rem.remarks,rem.createtime,case when teach.FirstName is null then teach.FirstName else concat(teach.FirstName,' ',teach.LastName)end teachername,case when rem.remarksType='general' then '-' else sub.subjectName end SubjectName from campus_teachers teach,campus_admin ad, campus_admin_remarks rem left outer join campus_subject sub on sub.subjectID=rem.subjectCode where teach.TeacherID = rem.TeacherCode and rem.commentedby=ad.AdminID and rem.isApproved='N'"
	 * ; public static final String GET_TEACHERS_NAME =
	 * "select case when LastName is null then FirstName else concat(FirstName, ' ' ,LastName) end TeacherName from campus_teachers where TeacherID = ?"
	 * ; public static final String GET_ADMIN_NAME =
	 * "select case when LastName is null then FirstName else concat(FirstName, ' ' ,LastName) end AdminnName from campus_admin where AdminID = ?"
	 * ; public static final String getChildinf =
	 * "select student_admissionno_var,classdetail_id_int,classsection_id_int,student_fname_var  from campus_student where student_admissionno_var in (select stu_addmissionNo from campus_parentchildrelation where parentid = ?)"
	 * ; public static final String getClassName =
	 * "select classdetails_name_var from campus_classdetail where classdetail_id_int = ? "
	 * ; public static final String getSectionName =
	 * " select classsection_name_var from campus_classsection where classsection_id_int= ?"
	 * ; public static final String UPDATE_REMARKS =
	 * "update campus_admin_remarks set remarks=?,updatetime=?,createtime=? where remarksCode=?"
	 * ; public static final String CONFIRMATION_REMARKS =
	 * "update campus_admin_remarks set isApproved = 'y',updatedby=?, updatetime=now() where remarksCode=?"
	 * ; public static final String DELETING_REMARKS =
	 * "delete from campus_admin_remarks where remarksCode=?"; //
	 * ***************
	 * ***********************************************************
	 * ***************
	 * ***********************************************************
	 * ***************
	 * ***********************************************************
	 * ***********************
	 * 
	 * public static final String TEACHER_INFO =
	 * "select TeacherID,UserName,email from campus_teachers"; public static
	 * final String PARENT_INFO =
	 * "select concat(st.student_fname_var,'(',concat(cd.classdetails_name_var,'-',cs.classsection_name_var))UserName,par.ParentID from campus_student st join campus_classdetail cd on cd.classdetail_id_int=st.classdetail_id_int join campus_classsection cs on cs.classsection_id_int=st.classsection_id_int join campus_parentchildrelation cpr on cpr.stu_addmissionNo=st.student_admissionno_var join campus_parents par on par.ParentID=cpr.parentid join campus_teachersettings tsr on tsr.classID=st.classdetail_id_int and tsr.sectionID=st.classsection_id_int join campus_teachers ct on ct.TeacherID=tsr.teacherID where ct.TeacherID=? order by st.student_fname_var asc"
	 * ; public static final String SAVEMAIL =
	 * "insert into campus_mail(mailid,mailto,mailfrom,message,createuser,attachment)  values(?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String Check_Jobcdoe =
	 * "select Distinct(jobcode) from campus_careers where jobcode= ?";
	 * 
	 * public static final String COUNT_USERNAME =
	 * "select count(*) from campus_parents where UserName=?"; public static
	 * final String COUNT_EMAIL =
	 * "select count(*) from campus_parents where email=?";
	 * 
	 * 
	 * public static final String ALL_CLASS_NAME =
	 * "select campus_classdetail.classdetail_id_int,classdetails_name_var,classsection_name_var  from campus_teachers join campus_teachersettings on campus_teachers.TeacherID=campus_teachersettings.teacherID join campus_classdetail on campus_teachersettings.classID=campus_classdetail.classdetail_id_int join campus_classsection on campus_teachersettings.sectionID=campus_classsection.classsection_id_int where campus_teachers.UserName=?"
	 * ;
	 * 
	 * public static final String ALL_CLASS_NAME =
	 * "select distinct(clas.classdetails_name_var), clas.classdetail_id_int,sec.classsection_id_int,sec.classsection_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_classsection sec on sec.classsection_id_int = teadet.sectionID join campus_subject subj on subj.subjectID=teadet.subjectID  join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=? "
	 * ; public static final String GET_Class_DETAILS =
	 * "select * from campus_student where classdetail_id_int=? and classsection_id_int=? order by student_admissionno_var"
	 * ; public static final String MAIL_INFO =
	 * "select mailid,mailto,campus_teachers.firstname,message,campus_mail.createdate,campus_teachers.firstname,attachment from campus_teachers join campus_mail on TeacherID=mailfrom where mailto=?"
	 * ; public static final String MAIL_INFO1 =
	 * "select mailid,mailto,campus_parents.firstname,message,campus_mail.createdate,campus_parents.firstname,attachment from campus_parents join campus_mail on ParentID=mailfrom where mailto=?"
	 * ; public static final String GET_ALL_SUBNAMES =
	 * " select * from campus_examination order by examid"; public static final
	 * String TEACHER_INFO1 =
	 * "select email from campus_teachers where TeacherID=?"; public static
	 * final String STUD_DETAIL_DISPLAY_ADDMISSION =
	 * "select distinct lpc.stu_addmissionNo from campus_parents lp,campus_parentchildrelation lpc where lp.parentid=? and lp.ParentID=lpc.parentid"
	 * ; public static final String PARENT_INFO1 =
	 * "select email from campus_parents where ParentID=?"; public static final
	 * String CHILD_INFO_STUDENTNAME =
	 * "select * from campus_student  where classsection_id_int=?"; public
	 * static final String GET_CLASS_STU_DETAILS =
	 * "select * from campus_student where student_admissionno_var=? "; public
	 * static final String SUBJECT_INFOTECH =
	 * "select * from campus_subject ls where ls.classid=? and ls.subjectID not in(select subjectID  from campus_teachersettings)"
	 * ; public static final String TEACHER_SETTING_COUNT =
	 * "select count(*) from campus_teachersettings where teacherID=? and classID =? and sectionID =? and subjectID =?"
	 * ; public static final String GET_PARENT_STUDENT_DETAILS =
	 * "select campus_student.student_fname_var,campus_classdetail.classdetails_name_var,campus_classsection.classsection_name_var,campus_student.student_admissionno_var,campus_student.student_id_int  from campus_student join campus_classdetail on campus_student.classdetail_id_int=campus_classdetail.classdetail_id_int join campus_classsection on campus_classsection.classsection_id_int=campus_student.classsection_id_int where student_id_int in(select campus_parentchildrelation.stu_addmissionNo from campus_parents join campus_parentchildrelation  on campus_parents.ParentID=campus_parentchildrelation.parentid where campus_parents.ParentID=?)"
	 * ; public static final String GET_PARENT_ATTENDANCE_DETAILS =
	 * "select addmissionno,monthname(attendencedate) as monthname,count(*) as totalworkingdays, sum(CASE WHEN attendence = 'present' THEN 1 ELSE 0 END) as present,SUM(CASE WHEN attendence = 'absent' THEN 1 ELSE 0 END) AS absent from  campus_attendence where attendencedate >= ? and attendencedate <= ? and addmissionno=? GROUP BY monthname(attendencedate)"
	 * ; public static final String GET_PARENT_MONTH_DETAILS =
	 * "select distinct monthname(attendencedate) as monthname FROM campus_attendence where addmissionno=?"
	 * ;
	 * 
	 * // ************
	 * 
	 * public static final String ViewAllSubjects =
	 * "select * from campus_subject where classid=?"; public static final
	 * String ViewAllSubjectsPath =
	 * "select * from campus_subject where subjectID=?";
	 * 
	 * // public static final String //
	 * ViewAllSubjects="select * from campus_subject where classid=?"; // public
	 * static final String //
	 * ViewAllSubjectsPath="select * from campus_subject where subjectID=?";
	 * public static final String GetSubjectDetails =
	 * "select s.subjectID,s.subjectName,s.decription,cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_name_var,cs.classstream_id_int,s.syllabous from campus_subject s inner join campus_classdetail cd on s.classid=cd.classdetail_id_int inner join campus_classstream cs on cd.classstream_id_int=cs.classstream_id_int where s.subjectID=?"
	 * ; public static final String UPDATE_SUBJECT =
	 * "update campus_subject set subjectName=? ,syllabous=?,classid=?, decription=?, createdby=? where subjectID=?"
	 * ;
	 * 
	 * public static final String FututeUpcomingEvents =
	 * "select * from campus_event where status='active' and eventdate >=? order by eventdate"
	 * ;
	 * 
	 * public static final String GET_NOTICE_BOARD_DETAILS =
	 * "select * from campus_noticeboard where status='active' group by enddate desc"
	 * ;
	 * 
	 * 
	 * public static final String GET_NOTICE_BOARD_DETAILS =
	 * "select * from campus_noticeboard where status='active' group by enddate desc"
	 * ; public static final String GET_NOTICE_BOARD_POPUP =
	 * "select * from campus_noticeboard where status='active' AND noticeid=? group by enddate desc"
	 * ; // ******************* public static final String GET_APPLIED_JOBS =
	 * "select * from campus_appliedjobs "; public static final String
	 * GET_DOWNLOAD_PATH_APPLIED_JOBS =
	 * "select * from  campus_appliedjobs where sno=?"; public static final
	 * String INSERT_APPLIED_JOBS =
	 * "insert into  campus_appliedjobs(Name,phoneno,address,resume,email,status,jobcode,createdtime) values(?,?,?,?,?,?,?,?) "
	 * ;
	 * 
	 * // *********** public static final String GET_CONTACTUS =
	 * "select * from  campus_contactus";
	 * 
	 * public static final String GET_ALL_ACCYEARS =
	 * " select * from campus_acadamicyear order by acadamicyear_id_int"; public
	 * static final String insertExaminationQuery =
	 * "insert into campus_examination (examid,examname,description,accadamicyear,examdate,exam_enddate,createuser) values (?,?,?,?,?,?,?)"
	 * ; public static final String GET_ALL_EXAMNAMES =
	 * "select examname from campus_examination where accadamicyear=?"; public
	 * static final String CheckValidDate =
	 * "select count(addmissionno) from campus_attendence where attendencedate=? and addmissionno=? "
	 * ; public static final String CheckStudent =
	 * "select count(student_admissionno_var) from campus_student where student_admissionno_var=?  and student_status_var='active' and classdetail_id_int=? and classsection_id_int=?"
	 * ; public static final String STUDENT_CheckSubject =
	 * "select count(*) from campus_subject where subjectID=? and classid=?";
	 * public static final String Check_EventCount =
	 * "select count(eventname) from campus_event where eventname=? and eventdate=?"
	 * ; public static final String Check_SubjectCount =
	 * "select count(teacherID) from campus_teachersettings where classID=? and sectionID=? and subjectID=?"
	 * ; public static final String Check_NoticeTitleCount =
	 * "select count(title) from campus_noticeboard where title= ? and (? between StartDate and EndDate or  ?  between StartDate and EndDate)"
	 * ; public static final String GET_NOTICE_BOARD =
	 * "select title,description,startdate,enddate from campus_noticeboard where status='Active' order by startdate"
	 * ;
	 * 
	 * // marks entry form// public static final String INSERT_MARKS_UPLOAD =
	 * "insert into campus_marks_upload(marks_upload_id,examid,subid,categoryid,classid ,sectionid ,maximum_marks,required_marks ) values(?,?,?,?,?,?,?,?)"
	 * ; public static final String ENTER_STUDENT_MARKS =
	 * "insert into campus_marks(marks_upload_id,admissionno,scoredmarks) values(?,?,?)"
	 * ; public static final String SHOW_STUDENT_MARKS =
	 * "select student_admissionno_var,student_fname_var,student_mname_var,student_lname_var from campus_student where classsection_id_int=?"
	 * ; public static final String GET_STUDENT_MARKS =
	 * "select a.student_id_int,a.student_admissionno_var,concat(case when a.student_fname_var is NULL then '' else a.student_fname_var end,' ', case when a.student_lname_var is NULL then '' else a.student_lname_var end) name,b.scoredmarks from (select student_id_int,student_admissionno_var,student_fname_var,student_lname_var from campus_student where classsection_id_int=? and fms_classstream_id_int=? and classdetail_id_int=? ) a left outer join (select * from campus_marks where marks_upload_id=(select max(marks_upload_id) from campus_marks_upload where examid=? and subid=? and categoryid=? and classid=? and sectionid=?)) b on a.student_id_int=b.admissionno"
	 * ; public static final String GET_MAX_MIN_MARKS =
	 * "select maximum_marks,required_marks from campus_marks_upload where marks_upload_id=(select max(marks_upload_id) from campus_marks_upload where examid=? and subid=? and categoryid=? and classid=? and sectionid=?)"
	 * ;
	 * 
	 * public static final String CHILD_INFO_ALLSTUDENTNAME =
	 * "select * from campus_student  where fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=?"
	 * ;
	 * 
	 * public static final String GET_CAMPUS_STUDENT_DETAILS =
	 * "select stud.student_admissionno_var,stud.student_regno_var,strm.classstream_name_var,cldt.classdetails_name_var,clsc.classsection_name_var, accd.acadamicyear_year,mmar.maximum_marks,mmar.required_marks,scmar.scoredmarks,exam.examname,subt.subjectName,exam.examdate  from campus_student stud join campus_classstream strm on stud.fms_classstream_id_int = strm.classstream_id_int join campus_classdetail cldt on stud.classdetail_id_int = cldt.classdetail_id_int join campus_classsection clsc on stud.classsection_id_int = clsc.classsection_id_int join campus_acadamicyear accd on stud.fms_acadamicyear_id_int =accd.acadamicyear_id_int join campus_marks_upload mmar on stud.fms_classstream_id_int = mmar.categoryid join campus_marks scmar on mmar.marks_upload_id=scmar.marks_upload_id join campus_examination exam on exam.examid=mmar.examid join campus_subject subt on subt.subjectID=mmar.subid where stud.student_admissionno_var=?"
	 * ; public static final String GET_CAMPUS_STUDENT_DETAILS_BY_EXAM =
	 * "select stud.student_admissionno_var,stud.student_regno_var,strm.classstream_name_var,cldt.classdetails_name_var,clsc.classsection_name_var, accd.acadamicyear_year,mmar.maximum_marks,mmar.required_marks,scmar.scoredmarks,exam.examname,subt.subjectName,exam.examdate  from campus_student stud join campus_classstream strm on stud.fms_classstream_id_int = strm.classstream_id_int join campus_classdetail cldt on stud.classdetail_id_int = cldt.classdetail_id_int join campus_classsection clsc on stud.classsection_id_int = clsc.classsection_id_int join campus_acadamicyear accd on stud.fms_acadamicyear_id_int =accd.acadamicyear_id_int join campus_marks_upload mmar on stud.fms_classstream_id_int = mmar.categoryid join campus_marks scmar on mmar.marks_upload_id=scmar.marks_upload_id join campus_examination exam on exam.examid=mmar.examid join campus_subject subt on subt.subjectID=mmar.subid where stud.student_admissionno_var=? and exam.examname=?"
	 * ;
	 * 
	 * // UploadAttendence// public static final String GET_STUDENT_INFO =
	 * "select cs.student_fname_var,cs.student_admissionno_var,cs.student_id_int,ca.attendence from campus_student cs join campus_attendence ca on cs.student_id_int=ca.addmissionno where ca.attendencedate=? and cs.fms_classstream_id_int=? and cs.classdetail_id_int=? and cs.classsection_id_int=?"
	 * ; public static final String GET_STUDENT_INFO2 =
	 * "select student_id_int,student_admissionno_var,student_fname_var from campus_student where fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=?"
	 * ;
	 * 
	 * public static final String GET_STUDENT_COUNT =
	 * "select count(*) from campus_student cs join campus_attendence ca on cs.student_id_int=ca.addmissionno where ca.attendencedate=? and cs.fms_classstream_id_int=? and cs.classdetail_id_int=? and cs.classsection_id_int=?"
	 * ; public static final String UPLOAD_ATTENDANCE =
	 * "insert into campus_attendence(attendencedate,addmissionno,attendence) values(?,?,?)"
	 * ; public static final String STUDENT_COUNT =
	 * "select count(*) from campus_attendence where attendencedate=? and addmissionno=? "
	 * ; public static final String STUDENT_UPDATE =
	 * "update campus_attendence set attendence=? where attendencedate=? and addmissionno=?"
	 * ; public static final String STUDENT_ATTENDANCE_COUNT =
	 * "select count(*) from campus_attendence where attendencedate=? and addmissionno=?"
	 * ; public static final String ISTUDENT_ATTENDANCE_UPDATE =
	 * "update campus_attendence set attendence=? where attendencedate=? and addmissionno=?"
	 * ; public static final String AssignMent_Creation =
	 * "insert into campus_assignment(AssignmentCode,StreamID,ClassID,SectionID,AssignmentDate,CompletionDate,AssignmentType,Name,Description,SubjectId,MaxMarks,AcadamicID,CreatedBy) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_STUDENTASSIGNMENT_NAMES =
	 * "SELECT distinct ca.AssignmentCode,ca.Name,ca.CompletionDate,ca.ClassID,ca.SectionID from campus_assignment ca,campus_teachersettings cts where cts.teacherID=? or ca.AssignmentType ='Genaral' "
	 * ; public static final String GET_STUDENTASSIGNMENT_DETAILS =
	 * "select distinct ca.MaxMarks,ca.Name,cs.student_fname_var,cad.ActualCompletionDate,cad.AcquiredMarks,cad.Remarks,cs.student_admissionno_var from campus_assignment ca,campus_assignmentdetails cad,campus_student cs where ca.AssignmentCode=cad.AssignmentCode and cs.student_id_int =cad.AdmissionNo and cad.AssignmentCode=?"
	 * ; public static final String ASSIGNMENT_DUPLICATE_CHECKING =
	 * "select count(*) from campus_assignment where StreamID=? and ClassID=? and SectionID=? and AssignmentDate=? and AssignmentType=? and Name=? and AcadamicID=? and SubjectID=?"
	 * ;
	 * 
	 * public static final String SAVE_STUDENTASSIGNMENT_DETAILS =
	 * "insert into campus_assignmentdetails(AssignmentCode,AdmissionNo,ActualCompletionDate,AcquiredMarks,Remarks,CreatedBy,UpdatedBy,CreatedTime,UpdatedTime) values(?,?,?,?,?,?,?,?,?)"
	 * ; public static final String SAVE_STUDENTASSIGNMENT_DETAILSVIEW =
	 * "select  AssignmentCode,AdmissionNo,ActualCompletionDate,AcquiredMarks,Remarks from campus_assignmentdetails where AdmissionNo=? and AssignmentCode =?"
	 * ; public static final String UPDATE_STUDENTASSIGNMENT_DETAILS =
	 * "update campus_assignmentdetails set ActualCompletionDate=?,AcquiredMarks=?,Remarks=?,UpdatedBy=?,UpdatedTime=? where AdmissionNo=? and AssignmentCode=?"
	 * ; public static final String COUNT_OF_ADMISSION =
	 * "select count(*) admissioncount from campus_assignmentdetails where AdmissionNo=? and AssignmentCode=?"
	 * ; public static final String TEST_STUDENTASSIGNMENT_DETAILS =
	 * "select distinct ca.MaxMarks,ca.AssignmentCode,ca.Name,ca.StreamID,cs.student_admissionno_var,cs.student_fname_var from campus_assignment ca,campus_student cs where ca.ClassID=cs.classdetail_id_int and ca.AssignmentCode=?"
	 * ; public static final String COUNT_STUDENTASSIGNMENT_DETAILS =
	 * "select count(*) usercount from campus_assignmentdetails where AssignmentCode=?"
	 * ; public static final String GET_TEACHER_NAME =
	 * "select UserName from campus_teachers where TeacherID =? ";
	 * 
	 * public static final String GET_CAMPUS_STUDENT_SUBJECT_DETAILS =
	 * "select cst.student_admissionno_var,cst.classdetail_id_int,csu.subjectID,csu.subjectName from campus_student cst,campus_subject csu where csu.classid=cst.classdetail_id_int and cst.student_id_int=?"
	 * ; public static final String GET_CAMPUS_STUDENT_EXAMINATION_DETAILS =
	 * "select distinct ce.examid,ce.examname from campus_student cst,campus_examination ce where cst.fms_acadamicyear_id_int=ce.accadamicyear and cst.student_id_int=?"
	 * ; public static final String GET_CAMPUS_STUDENT_MARKS =
	 * "select distinct cm.scoredmarks from campus_marks_upload cmu,campus_marks cm,campus_examination ce,campus_subject csu,campus_student cst where cmu.examid=? and cmu.subid=? and cst.classdetail_id_int=cmu.classid and cmu.marks_upload_id=cm.marks_upload_id and cm.admissionno=cst.student_id_int and cst.student_id_int=?"
	 * ; public static final String CHECK_ADMISSION_NUMBER =
	 * "select count(*) usercount from campus_student cst where cst.student_admissionno_var=?"
	 * ; public static final String CHECK_STUDENT_PARENT_RELATION =
	 * "select count(*) usercount from campus_student cst,campus_parents cp,campus_parentchildrelation cpr where cst.student_admissionno_var=cpr.stu_addmissionNo and cp.ParentID=cpr.parentid and cst.student_admissionno_var=? and cp.ParentID=?"
	 * ;
	 * 
	 * // public static final String TEA_CHILD_INFO_STREAM = //
	 * "select distinct strm.classstream_id_int,strm.classstream_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?"
	 * ; // public static final String TEA_CHILD_INFO_CLASS = //
	 * "select distinct clas.classdetail_id_int, clas.classdetails_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and strm.classstream_id_int=?"
	 * ; // public static final String TEA_CHILD_INFO_SECTION = //
	 * "select distinct sec.classsection_id_int,sec.classsection_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_classsection sec on sec.classsection_id_int = teadet.sectionID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and clas.classdetail_id_int=?"
	 * ; // public static final String UPLOAD_REMARKS = //
	 * "insert into campus_remarks(remarksCode,AdmissionNo,remarks,commentedby) values(?,?,?,?)"
	 * ; // public static final String REMARKS_UPDATE = //
	 * "update campus_remarks set remarks=? where AdmissionNo=? and remarksCode=?"
	 * ; // public static final String GET_STUDENT_REMARKS = //
	 * "select remarksCode,AdmissionNo,remarks from campus_remarks where AdmissionNo=? order by createtime desc"
	 * ; // public static final String GET__REMARKS = //
	 * "select remarksCode,AdmissionNo,remarks from campus_remarks where remarksCode=?"
	 * ; // public static final String REMARKS_DELETE = //
	 * "delete from campus_remarks where remarksCode=?";
	 * 
	 * public static final String CREATETIMETABLE =
	 * " select  sessioncode, sessionname ,starttime,endtime from campus_sessions order by starttime"
	 * ; public static String INSERT_CREATE_TIMETABLE =
	 * "insert  into campus_timetable (ttccode,classstreamid,classid,classsectionid,sessioncode,subjectid,teacherid,createtime,createdby) values (?,?,?,?,?,?,?,?,?)"
	 * ; public static String CHECK_TIMETABLE =
	 * "select count(*) from campus_timetable where  sessionCode=?  and teacherid=? "
	 * ; public static String CHECK_CREATETIMETABLE =
	 * "select ttccode from campus_timetable where classstreamid=? and classid=? and classsectionid=?  and sessionCode=? "
	 * ; public static final String GET_TEACHERID =
	 * "select  ts.teacherId ,concat(ct.FirstName, ' ' ,ct.LastName) FirstName from campus_teachersettings ts,campus_teachers ct  where  ts.teacherID= ct.TeacherID and ts.subjectId=?"
	 * ; public static final String GET_TIMETABLEDATA =
	 * "select sess.SessionCode,tea.subjectid,tea.teacherid,tea.TeacherName,tea.subjectName from (select SessionCode from campus_sessions order by StartTime) sess left outer join (select tt.sessionCode,tt.subjectid,tt.teacherid,concat(tc.FirstName,' ',tc.LastName) TeacherName,s.subjectName from campus_timetable tt,campus_teachers tc,campus_subject s where tc.TeacherID=tt.teacherid and tt.subjectid=s.subjectID and tt.classstreamid=? and tt.classid=? and tt.classsectionid=?) tea using(sessionCode)"
	 * ; public static final String Teacher_TIMETABLE =
	 * "select sess.SessionCode,ts.SubjectName,ts.ClassName,ts.SectionName from (select SessionCode from campus_sessions order by StartTime) sess left outer join (select ct.SessionCode, cs.subjectName SubjectName ,cd.classdetails_name_var ClassName,cc.classsection_name_var SectionName from campus_timetable ct ,campus_subject cs,campus_classsection cc,campus_classdetail cd where cd.classdetail_id_int=ct.classid and cc.classsection_id_int=ct.classsectionid and cs.subjectid=ct.subjectid  and ct.teacherid=? ) ts using(SessionCode)"
	 * ; public static final String UPDATE_CREATE_TIMETABLE =
	 * "UPDATE campus_timetable SET classstreamid=?,classid=?,classsectionid=?,subjectid=?,teacherid=? WHERE ttccode =?"
	 * ; public static final String GET_SUBJECT =
	 * "select subjectID,subjectName from campus_subject where classid=?";
	 * 
	 * public static final String GET_CLASS_BASED_ON_PARENT =
	 * "select cls.classdetail_id_int,classdetails_name_var from campus_classdetail cls where cls.classdetail_id_int in (select st.classdetail_id_int from campus_student st, campus_parentchildrelation pa where st.student_id_int = pa.stu_addmissionNo and pa.parentid = ?)"
	 * ; public static final String GET_STUDENT_NAME_BASED_ON_PARENT =
	 * "select st.student_id_int,concat(st.student_fname_var,' ',st.student_lname_var)StudentName from campus_student st join campus_parentchildrelation pa where st.student_id_int = pa.stu_addmissionNo and pa.parentid = ?"
	 * ;
	 * 
	 * public static final String GET_STUDENT_DETAILS_BASED_ON_PARENT =
	 * "select classdetail_id_int, classsection_id_int, fms_acadamicyear_id_int from campus_student where student_admissionno_var = ?"
	 * ; public static final String GET_TIMETABLE_ID =
	 * "select ti.timetable_id from campus_timetable_student ti join campus_student st where ti.classid = st.classdetail_id_int and ti.sectionid = st.classsection_id_int and ti.accyearid = st.fms_acadamicyear_id_int and st.student_id_int = ?"
	 * ; public static final String GET_STUDENT_TIME_TABLE =
	 * "select days.dayname, ti.period1, ti.period2, ti.period3, ti.period4, ti.period5, ti.period6, ti.period7, ti.period8 from campus_timetable_studentdetails ti join campus_timetable_day days where ti.daycode = days.daycode and timetableid = ?"
	 * ;
	 * 
	 * public static final String Student_TIMETABLE =
	 * "select cs.subjectName,tt.FirstName  from campus_timetable ct , campus_teachers tt ,campus_subject cs where  cs.subjectid=ct.subjectid and  tt.TeacherId=ct.teacherid and ct.classid=? and ct.classsectionid=?"
	 * ; public static final String STUDENT_INFO_STREAM =
	 * "select distinct strm.classstream_id_int,strm.classstream_name_var from campus_parents cp join campus_parentchildrelation cpc on cp.ParentID = cpc.parentid join campus_student clas on clas.student_admissionno_var= cpc.stu_addmissionNo  join campus_classstream strm on strm.classstream_id_int=fms_classstream_id_int where cp.ParentID=?"
	 * ; public static final String ACADEMICYEAR =
	 * "select * from campus_acadamicyear"; public static final String
	 * STUDENT_INFO_CLASSNAME =
	 * "select distinct strm.classdetail_id_int,strm.classdetails_name_var from campus_parents cp join campus_parentchildrelation cpc on cp.ParentID = cpc.parentid join campus_student clas on clas.student_admissionno_var= cpc.stu_addmissionNo  join campus_classdetail strm on strm.classdetail_id_int=clas.classdetail_id_int where cp.ParentID=? and strm.classstream_id_int=?"
	 * ; public static final String STUDENT_INFO_SECTION =
	 * "select distinct strm.classsection_id_int,strm.classsection_name_var from campus_parents cp join campus_parentchildrelation cpc on cp.ParentID = cpc.parentid join campus_student clas on clas.student_admissionno_var= cpc.stu_addmissionNo  join campus_classsection strm on strm.classsection_id_int=clas.classsection_id_int where cp.ParentID=? and clas.classdetail_id_int=?"
	 * ;
	 * 
	 * // Campus Class Section public static final String
	 * GET_CAMPUS_CLASS_SECTION_MAXID =
	 * "select max(classsection_id_int) from campus_classsection"; public static
	 * final String GET_CLASS_ID =
	 * "select classstream_id_int from campus_classstream where classstream_name_var=?"
	 * ;
	 * 
	 * // Campus Class Stream
	 * 
	 * public static final String INSERT_DETAILS_CAMPUS_CLASS_STREAM =
	 * "INSERT INTO campus_classstream (classstream_name_var, createuser, createdate,modifyuser,modifydate) VALUES (?,?,?,?,?)"
	 * ; public static final String UPDATE_DETAILS_CAMPUS_CLASS_STREAM =
	 * "UPDATE campus_classdetail SET classdetails_name_var= ?,modifyuser= ?, modifydate = ?  WHERE classdetail_id_int =?"
	 * ; public static final String DELETE_DETAILS_CAMPUS_CLASS_STREAM =
	 * "DELETE FROM campus_classdetail WHERE classdetail_id_int =?"; public
	 * static final String UPDATE_CAMPUS_STREAM =
	 * "UPDATE campus_classstream SET classstream_name_var= ?,createuser= ?, createdate = ?  WHERE classstream_id_int =?"
	 * ; // School sessions queries ----> DIWAKAR public static final String
	 * GET_SCHOOL_TIMING_DETAILS =
	 * "select * from campus_sessions order by length(SessionCode),SessionCode";
	 * public static final String GET_SCHOOL_TIMING =
	 * "select * from campus_timings"; public static final String
	 * CHECK_STCODE_SCHOOL_TIMING =
	 * "select STCode from campus_timings WHERE StartTime=? and EndTime=?";
	 * public static final String UPDATE_SCHOOL_TIMING_DETAILS =
	 * "UPDATE campus_timings SET StartTime=?,EndTime=? WHERE STCode =?"; public
	 * static final String ADD_SCHOOL_TIMING_DETAILS =
	 * "insert into campus_sessions(SessionCode,SessionName,StartTime,EndTime,isBreak,createdBy) values(?,?,?,?,?,?)"
	 * ; public static final String ADD_SCHOOL_TIMING_DETAILS1 =
	 * "insert into campus_timings(STCode,StartTime,EndTime) values(?,?,?)";
	 * public static final String EDIT_SCHOOL_TIMING_DETAILS =
	 * "update campus_sessions set  SessionName=?, startTime=?, endTime=?,isBreak=?,UpdateTime=?,UpdatedBy=? where SessionCode=?"
	 * ; public static final String DELETE_SCHOOL_TIMING_DETAILS =
	 * "delete from campus_sessions where SessionCode=?"; public static final
	 * String DELETE_SCHOOL_TIMING_DETAILS_SESSIONCODE =
	 * "delete from campus_timetable where SessionCode=?"; public static final
	 * String TABLE_CAMPUS_SESSIONS = "campus_sessions"; public static final
	 * String TABLE_CAMPUS_TIMINGS = "campus_timings";
	 * 
	 * public static final String FEE_DETAILS =
	 * "SELECT * from campus_feesettings"; public static final String
	 * FEE_SETTINGS_SELECT =
	 * "SELECT * from campus_feesettings WHERE feesettings_id_int=?"; public
	 * static final String FEE_SETTINGS_UPDATE =
	 * "UPDATE campus_feesettings SET feesettings_admissionfee_double=?,feesettings_monthlyfee_double=?,feesettings_annualfee_double=?,	feesettings_tutionfee_double=?,feesettings_computerfee_double=?,feesettings_libraryfee_double=?,feesettings_maintainencefee_double=?,feesettings_securityfee_double=?,feesettings_transportfee_double=?,feesettings_hostelfee_double=?,feesettings_examinationfee_double=?,feesettings_fine_double=?,feesettings_others_double=?,feesettings_acadamicyear_var=?,feesettings_lastpaymentdate_var=?,feesettings_concessionamt_double=?,modifyuser=? WHERE feesettings_id_int=? and classdetail_id_int=?"
	 * ;
	 * 
	 * // Student Registration public static final String GET_ACADEMIC_YEAR =
	 * "select acadamicyear_id_int,acadamicyear_year from campus_acadamicyear";
	 * 
	 * public static final String GET_STUDENT_MAXID =
	 * "SELECT count(student_regno_var) FROM campus_student";
	 * 
	 * public static final String STUDENT_ADMISSION_NO =
	 * "select student_admissionno_var from campus_student"; public static final
	 * String STUDENT_REGNO = "select student_regno_var from campus_student";
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public static final String STUDENT_SEARCH =
	 * "select student_fname_var,student_id_int from campus_student where student_status_var ='active' and  fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fname_var like ?"
	 * ; public static final String GET_STUDENT_QUOTA =
	 * "select Quota_Name,Quota_Code from campus_quota"; public static final
	 * String GETFEEDETAILS =
	 * "select stud.student_fname_var,stud.student_admissionno_var,clas.classdetails_name_var,fes.feesettings_monthlyfee_double,pay.payment_id_int,pay.payment_date_var,pay.payment_month,pay.payment_totalamount_double,pay.payment_paidamount_double,pay.payment_dueamount_double,pay.createdate,pay.payment_concessionamount_double,fes.feesettings_fine_double,fes.feesettings_maintainencefee_double from campus_payment pay join campus_student stud on stud.student_admissionno_var=pay.student_admissionno_var join campus_classdetail clas on clas.classdetail_id_int=stud.classdetail_id_int join campus_feesettings fes on fes.feesettings_id_int=pay.feesettings_id_int where pay.payment_id_int=?"
	 * ; public static final String GET_STUDENT_DETAIL_BY_SEARCH_TERM =
	 * "select acc.acadamicyear_year,st.isRTE,st.isConcession,st.student_fname_var,st.student_lname_var,st.student_id_int,st.student_rollno,st.student_admissionno_var,student_application_no,isTransport,isHostel,st.fms_classstream_id_int,strm.classstream_name_var,st.fms_acadamicyear_id_int,st.classdetail_id_int,cd.classdetails_name_var,st.classsection_id_int,sec.classsection_name_var,st.student_regno_var,st.student_dob_var,st.student_gender_var,st.student_bloodgroup_var,st.student_age_int,st.student_imgurl_var,st.student_doj_var,st.student_religion_var,st.student_nationality_var,st.student_scholorship_var,st.student_promotionstatus,st.student_physicallychallenged,st.student_identificationmarks_var,st.student_grade,st.student_fathername_var,st.student_mothername_var,st.student_gaurdianname_var,st.student_fatherqualification_var,st.student_fathermobileno_var,st.student_mothermobileno_var,st.student_motherqualification_var,st.student_street1,st.student_street2,st.student_location,st.student_towncity,st.student_zippostcode,st.student_state,st.student_country,st.student_siblingname_var,st.student_siblingclass_var,st.student_status_var,st.student_prehistory_var,st.student_remarks_var,st.student_quota,st.student_father_mailid,st.student_mother_mailid,st.student_gardian_mailid,st.student_gardian_mobileno,st.student_caste,par.ParentID,cpr.relationship,ttd.type_name,ttd.type_id,cfs.stage_name,cfs.stage_id,ttd.type_collectFee from campus_student st join campus_classstream strm on strm.classstream_id_int=st.fms_classstream_id_int join campus_classdetail cd on cd.classdetail_id_int=st.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=st.classsection_id_int join campus_acadamicyear acc on acc.acadamicyear_id_int=st.fms_acadamicyear_id_int join campus_quota qt on qt.Quota_Code=st.student_quota join campus_parentchildrelation cpr on cpr.stu_addmissionNo=st.student_id_int join campus_parents par on cpr.parentid=par.ParentID left outer  join transport_typedetails ttd on ttd.type_id=st.TransportType left outer join campus_fee_stage cfs on cfs.stage_id=st.StageId left outer join campus_fee_stagesetup fss on fss.stage_id=cfs.stage_id and fss.accyear_id=st.fms_acadamicyear_id_int where st.student_status_var ='active' and st.student_id_int=?"
	 * ; public static final String GET_SIBLING_DETAILS =
	 * "select sib.student_id_int as siblingId,sib.student_admissionno_var as siblingAdmissionNo,case when sib.student_lname_var is null then sib.student_fname_var else concat(sib.student_fname_var,' ',sib.student_lname_var)  end siblingName,cd.classdetails_name_var from campus_student st   join campus_student sib on sib.student_id_int = st.student_siblingname_var  join campus_classdetail  cd on cd.classdetail_id_int=st.classdetail_id_int  where st.student_id_int = ?"
	 * ; public static final String GET_STREAM_CLASS_NAME =
	 * "select classstream_name_var from campus_classstream where classstream_id_int =?"
	 * ; public static final String STUDENT_QUOTA_NAME =
	 * "select Quota_Name from campus_quota where Quota_Code=?"; public static
	 * final String STUDENT_READMISSION =
	 * "select cs.student_id_int,cs.student_fname_var,cs.student_lname_var,cs.student_dob_var,cs.student_age_int,cs.student_gender_var,cs.student_religion_var,cs.student_remarks_var,cs.student_prehistory_var,cs.student_bloodgroup_var,cs.student_nationality_var,cs.student_identificationmarks_var,cs.student_performancegrade_var,cs.student_scholorship_var,cs.student_status_var,cs.student_quota,cs.fms_classstream_id_int,cs.classdetail_id_int,cs.classsection_id_int,cs.fms_acadamicyear_id_int,cs.student_doj_var,cs.student_status_var,cs.student_imgurl_var,cs.student_mobno,cs.student_physicalstatus,cs.student_mothername_var,cs.student_mothermobileno_var,cs.student_motherqualification_var,cs.student_motherprofession_var,cs.student_fathername_var,cs.student_fathermobileno_var,cs.student_fatherprofession_var,cs.student_parentqualification_var,cs.student_emailid_var,cs.student_gaurdianname_var,cs.student_regno_var,cs.student_admissionno_var,cs.student_officeaddress_var,cs.student_residenceaddress_var,cpc.relationship,cs.student_father_mailid,cs.student_mother_mailid,cs.student_gardian_mailid,cs.student_gardian_mobileno from campus_student cs,campus_parentchildrelation cpc where cs.student_status_var ='active' and cs.student_id_int=? and cs.student_admissionno_var=cpc.stu_addmissionNo"
	 * ; public static final String STUDENT_REGISTRATION_MODIFY =
	 * "update campus_student set student_rollno=?,fms_classstream_id_int=?,fms_acadamicyear_id_int=?,classdetail_id_int=?,classsection_id_int =?,student_regno_var=?,student_fname_var=?,student_lname_var=?,student_dob_var=?,student_gender_var=?,student_bloodgroup_var=?,student_age_int=?,student_imgurl_var=?,student_doj_var=?,student_religion_var=?,student_nationality_var=?,student_scholorship_var=?,student_promotionstatus=?,student_physicallychallenged=?,student_identificationmarks_var=?,student_grade=?,student_fathername_var =?,student_mothername_var=?,student_gaurdianname_var=?,student_fatherqualification_var=?,student_fathermobileno_var=?,student_mothermobileno_var =?,student_motherqualification_var=?,student_street1=?,student_street2=?,student_location=?,student_towncity=?,student_zippostcode=?,student_state=?,student_country=?,student_siblingname_var=?,student_siblingclass_var=?,student_status_var=?,student_prehistory_var=?,student_remarks_var=?,modifyuser=?,modifydate=?,student_quota=?,student_father_mailid=?,student_mother_mailid=?,student_gardian_mailid =?,student_gardian_mobileno=?,student_caste=?,student_application_no=?,isTransport=?,isHostel=?,isConcession=?,isRTE =?,TransportType=?,StageId=? where student_id_int=?"
	 * ;
	 * 
	 * public static final String ACADEMICYEAR_BY_ID =
	 * "select acadamicyear_id_int from campus_acadamicyear where acadamicyear_year =?"
	 * ; public static final String GET_CLASS_STREAM_BY_ID =
	 * "select classstream_name_var from campus_classstream where classstream_id_int=?"
	 * ; public static final String GET_CLASS_NAME__BY_ID =
	 * "select classdetails_name_var from campus_classdetail where classdetail_id_int =?"
	 * ; public static final String GET_SECTION_NAME__BY_ID =
	 * "select classsection_name_var from campus_classsection where classsection_id_int =?"
	 * ; public static final String GET_STUDENT_DETAIL_BY_PARENTID =
	 * "select * from campus_student cs,campus_parentchildrelation cpc,campus_classdetail cc where student_status_var ='active' and student_id_int=? and cs.student_id_int=cpc.stu_addmissionNo and cc.classdetail_id_int=cs.classdetail_id_int"
	 * ; public static final String GET_ACADEMIC_YEAR_ID_BY_NAME =
	 * "select acadamicyear_id_int from campus_acadamicyear where acadamicyear_year=?"
	 * ;
	 * 
	 * // feecollection Start// public static final String GET_ADMISSIONNO =
	 * "SELECT student_admissionno_var FROM campus_student where student_admissionno_var like ?"
	 * ; public static final String GET_ADMISSIONNUMBERS =
	 * "SELECT student_admissionno_var,student_status_var FROM campus_student";
	 * public static final String MAX_PAYMENT_ID =
	 * "SELECT MAX(payment_id_int) FROM campus_payment group by length(payment_id_int) desc limit 1"
	 * ; public static final String FEE_SEARCH_FORM =
	 * "SELECT s.student_admissionno_var,s.student_fname_var,c.classdetails_name_var,f.feesettings_admissionfee_double,f.feesettings_monthlyfee_double,f.feesettings_annualfee_double,f.feesettings_tutionfee_double,f.feesettings_computerfee_double,f.feesettings_libraryfee_double,f.feesettings_maintainencefee_double,f.feesettings_securityfee_double,f.feesettings_transportfee_double,f.feesettings_hostelfee_double,f.feesettings_examinationfee_double,f.feesettings_fine_double,f.feesettings_others_double,f.feesettings_id_int,s.student_scholorship_var,f.feesettings_lastpaymentdate_var,f.feesettings_concessionamt_double FROM campus_feesettings f JOIN campus_student s ON s.classdetail_id_int = f.classdetail_id_int JOIN campus_classdetail c ON s.classdetail_id_int = c.classdetail_id_int WHERE s.student_admissionno_var=? AND s.student_status_var='active'"
	 * ; public static final String LOAD_STUDENT_ID =
	 * "SELECT student_id_int FROM  campus_student WHERE student_admissionno_var=?"
	 * ; public static final String INSERT_FEE_PAYMENT =
	 * "INSERT INTO campus_payment(payment_id_int,acadamicyear_id_int,feesettings_id_int,student_admissionno_var,student_id_int,payment_mode_var,payment_type_var,payment_paidamount_double,payment_concessionamount_double,payment_totalamount_double,payment_dueamount_double,payment_date_var,payment_status_var,payment_month,createuser,createdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())"
	 * ; public static final String LOAD_PAYMENT_ID =
	 * "SELECT payment_id_int FROM campus_payment"; public static final String
	 * LOAD_STUDENT_ADMISSION_NO =
	 * "SELECT student_admissionno_var FROM campus_payment WHERE payment_id_int=?"
	 * ; public static final String LOAD_PAYMENT_SEARCH_FORM =
	 * "SELECT s.student_admissionno_var,s.student_fname_var,c.classdetails_name_var,f.feesettings_admissionfee_double,f.feesettings_monthlyfee_double,f.feesettings_annualfee_double,f.feesettings_tutionfee_double,f.feesettings_computerfee_double,f.feesettings_libraryfee_double,f.feesettings_maintainencefee_double,f.feesettings_securityfee_double,f.feesettings_transportfee_double,f.feesettings_hostelfee_double,f.feesettings_examinationfee_double,f.feesettings_fine_double,f.feesettings_others_double,f.feesettings_id_int,s.student_scholorship_var,f.feesettings_lastpaymentdate_var,f.feesettings_concessionamt_double FROM campus_feesettings f JOIN campus_student s ON s.classdetail_id_int = f.classdetail_id_int JOIN 	campus_classdetail c ON	s.classdetail_id_int = c.classdetail_id_int WHERE s.student_admissionno_var=? AND s.student_status_var='active'"
	 * ; public static final String LOAD_FEE_PAYMENT =
	 * "SELECT p.payment_id_int,a.acadamicyear_year,p.payment_mode_var,p.payment_type_var,p.payment_concessionamount_double,p.payment_date_var,	p.payment_status_var,p.payment_paidamount_double, p.payment_dueamount_double,p.payment_totalamount_double,p.payment_month FROM campus_payment p JOIN campus_acadamicyear a ON p.acadamicyear_id_int = a.acadamicyear_id_int WHERE p.payment_id_int=?"
	 * ; public static final String LOAD_INITIAL_ACADAMIC_YEAR =
	 * "SELECT acadamicyear_id_int,acadamicyear_year from campus_acadamicyear order by acadamicyear_id_int"
	 * ; public static final String LOAD_PAYMENT_TIME =
	 * "select createdate from campus_payment where payment_id_int=?"; public
	 * static final String LOAD_MONTH_FEE =
	 * "select campus_feesettings.feesettings_monthlyfee_double from campus_feesettings,campus_payment where campus_feesettings.feesettings_id_int=campus_payment.feesettings_id_int and campus_payment.payment_id_int=?"
	 * ; public static final String LOAD_MAINTAINANCE_FEE =
	 * "select campus_feesettings.feesettings_maintainencefee_double from campus_feesettings,campus_payment where campus_feesettings.feesettings_id_int=campus_payment.feesettings_id_int AND campus_payment.payment_id_int=?"
	 * ; public static final String LOAD_FINE =
	 * "select campus_feesettings.feesettings_fine_double,campus_feesettings.feesettings_lastpaymentdate_var,campus_payment.payment_date_var from campus_feesettings,campus_payment where campus_feesettings.feesettings_id_int=campus_payment.feesettings_id_int AND campus_payment.payment_id_int=?"
	 * ; public static final String LOAD_BALANCE =
	 * "select payment_dueamount_double from campus_payment where payment_id_int=?"
	 * ; public static final String LOAD_PAID_AMOUNT =
	 * "select payment_paidamount_double from campus_payment where payment_id_int=?"
	 * ;
	 * 
	 * // Fee Ammendment
	 * 
	 * public static final String GET_PAYID =
	 * "SELECT payment_id_int FROM campus_payment"; public static final String
	 * FEE_PAYMENT_UPDATE =
	 * "UPDATE campus_payment SET acadamicyear_id_int=?,payment_mode_var=?,payment_type_var=?,payment_paidamount_double=?,payment_concessionamount_double=?,payment_totalamount_double=?,payment_dueamount_double=?,payment_date_var=?,payment_status_var=?,payment_month=? , modifydate=now() WHERE payment_id_int=?"
	 * ; public static final String GET_ADMISSIONNUMBER =
	 * "SELECT stud.student_admissionno_var,stud.classdetail_id_int FROM campus_student stud,campus_payment pay WHERE pay.student_admissionno_var=stud.student_admissionno_var  AND pay.payment_id_int=?"
	 * ; public static final String GET_PAYMENTID =
	 * "select min(pay.payment_id_int) from campus_payment pay,campus_student stud where pay.student_admissionno_var=stud.student_admissionno_var AND  stud.student_admissionno_var=? AND stud.classdetail_id_int=?"
	 * ; public static final String GET_FEEID_COUNT =
	 * "select count(payment_id_int) from campus_payment where payment_id_int=?"
	 * ;
	 * 
	 * // Feesettings Start//
	 * 
	 * public static final String RETRIV_CLASS =
	 * "SELECT * from campus_classdetail"; public static final String
	 * MAXFEESETTINGSID =
	 * "SELECT MAX(feesettings_id_int) FROM campus_feesettings"; // public
	 * static final String FEE_DETAILS = //
	 * "SELECT cf.feesettings_id_int,cc.classdetails_name_var,cf.classdetail_id_int,cf.feesettings_admissionfee_double,cf.feesettings_monthlyfee_double,cf.feesettings_maintainencefee_double,cf.feesettings_examinationfee_double,cf.feesettings_fine_double,cf.feesettings_concessionamt_double from campus_feesettings cf,campus_classdetail cc where cc.classdetail_id_int=cf.classdetail_id_int"
	 * ; public static final String FEE_SETTINGS_INSERT =
	 * "insert into campus_feesettings(feesettings_id_int,classdetail_id_int,feesettings_admissionfee_double,feesettings_monthlyfee_double,feesettings_annualfee_double,feesettings_tutionfee_double,feesettings_computerfee_double,feesettings_libraryfee_double,feesettings_maintainencefee_double,feesettings_securityfee_double,feesettings_transportfee_double,feesettings_hostelfee_double,feesettings_examinationfee_double,feesettings_fine_double,feesettings_others_double,feesettings_acadamicyear_var,feesettings_lastpaymentdate_var,feesettings_concessionamt_double,createuser) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; // public static final String FEE_SETTINGS_SELECT = //
	 * "SELECT * from campus_feesettings WHERE feesettings_id_int=?"; // public
	 * static final String FEE_SETTINGS_UPDATE = //
	 * "UPDATE campus_feesettings SET feesettings_admissionfee_double=?,feesettings_monthlyfee_double=?,feesettings_annualfee_double=?,	feesettings_tutionfee_double=?,feesettings_computerfee_double=?,feesettings_libraryfee_double=?,feesettings_maintainencefee_double=?,feesettings_securityfee_double=?,feesettings_transportfee_double=?,feesettings_hostelfee_double=?,feesettings_examinationfee_double=?,feesettings_fine_double=?,feesettings_others_double=?,feesettings_acadamicyear_var=?,feesettings_lastpaymentdate_var=?,feesettings_concessionamt_double=?,modifyuser=? WHERE feesettings_id_int=? and classdetail_id_int=?"
	 * ; public static final String FEE_SETTINGS_DELETE =
	 * "delete from campus_feesettings where feesettings_id_int=?";
	 * 
	 * // Bug Fix for Feesettings
	 * 
	 * public static final String FEE_COUNT =
	 * "select count(*) from campus_feesettings where classdetail_id_int=? and feesettings_acadamicyear_var=?"
	 * ; public static final String FEE_SETTINGS_EDIT =
	 * "SELECT cd.classdetails_name_var,cf.feesettings_id_int,cf.classdetail_id_int,cf.feesettings_admissionfee_double,cf.feesettings_monthlyfee_double,cf.feesettings_annualfee_double,cf.feesettings_tutionfee_double,cf.feesettings_computerfee_double,cf.feesettings_libraryfee_double,cf.feesettings_maintainencefee_double,cf.feesettings_securityfee_double,cf.feesettings_transportfee_double,cf.feesettings_hostelfee_double,cf.feesettings_examinationfee_double,cf.feesettings_fine_double,cf.feesettings_others_double,cf.feesettings_acadamicyear_var,cf.feesettings_lastpaymentdate_var,cf.feesettings_concessionamt_double from campus_feesettings cf,campus_classdetail cd where cd.classdetail_id_int=cf.classdetail_id_int and cf.feesettings_id_int=?"
	 * ; public static final String FEE_SETTINGS_UPDATE1 =
	 * "UPDATE campus_feesettings SET classdetail_id_int=?,feesettings_admissionfee_double=?,feesettings_monthlyfee_double=?,feesettings_annualfee_double=?,feesettings_tutionfee_double=?,feesettings_computerfee_double=?,feesettings_libraryfee_double=?,feesettings_maintainencefee_double=?,feesettings_securityfee_double=?,feesettings_transportfee_double=?,feesettings_hostelfee_double=?,feesettings_examinationfee_double=?,feesettings_fine_double=?,feesettings_others_double=?,feesettings_acadamicyear_var=?,feesettings_lastpaymentdate_var=?,feesettings_concessionamt_double=?,modifyuser=? WHERE feesettings_id_int=?"
	 * ; public static final String LOAD_FEE_DETAILS =
	 * "SELECT cf.feesettings_id_int,cc.classdetails_name_var,cf.classdetail_id_int,cf.feesettings_admissionfee_double,cf.feesettings_monthlyfee_double,cf.feesettings_annualfee_double,cf.feesettings_tutionfee_double,cf.feesettings_computerfee_double from campus_feesettings cf,campus_classdetail cc where cc.classdetail_id_int=cf.classdetail_id_int"
	 * ;
	 * 
	 * // Campus Class Section public static final String
	 * INSERT_DETAILS_CAMPUS_CLASS_SECTION =
	 * "INSERT INTO campus_classsection(classsection_id_int,classdetail_id_int,classsection_name_var,classsection_strength_int,createuser,createdate,modifyuser,modifydate) VALUES (?,?,?,?,?,?,?,?)"
	 * ; public static final String UPDATE_CAMPUS_CLASS_SECTION_STRENGTH =
	 * "UPDATE campus_classsection SET classsection_strength_int=?, modifyuser=?, modifydate=?  WHERE classsection_id_int = ?"
	 * ; public static final String UPDATE_DETAILS_CAMPUS_CLASS_SECTION =
	 * "UPDATE campus_classsection SET classdetail_id_int=?,classsection_name_var= ?,classsection_strength_int=?, modifyuser=?, modifydate=?  WHERE classsection_id_int = ?"
	 * ; public static final String DELETE_DETAILS_CAMPUS_CLASS_SECTION =
	 * "DELETE FROM campus_classsection WHERE classsection_id_int =? "; public
	 * static final String GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS =
	 * "select ccd.classdetail_id_int,ccd.classdetails_name_var,ccst.classstream_name_var,ccs.classsection_id_int,ccs.classsection_name_var,ccs.classsection_strength_int from campus_classdetail ccd,campus_classsection ccs,campus_classstream ccst where ccd.classdetail_id_int=ccs.classdetail_id_int and ccd.classstream_id_int=ccst.classstream_id_int order by length(ccs.classsection_id_int),ccs.classsection_id_int"
	 * ; public static final String
	 * GET_CAMPUS_CLASS_DETAILS_ID_AND_CLASS_DETAILS_NAME =
	 * "select DISTINCT classdetails_name_var,classdetail_id_int from campus_classdetail ORDER BY classdetail_id_int ASC"
	 * ; // public static final String GET_CLASS_ID = //
	 * "select classstream_id_int from campus_classstream where classstream_name_var=?"
	 * ; public static final String GET_CLASS_DETAIL_ID =
	 * "select classdetail_id_int from campus_classdetail where classdetails_name_var=?"
	 * ; public static final String CHECK_SECTION =
	 * "select count(*) usercount from campus_classsection where classdetail_id_int=? and classsection_name_var=?"
	 * ;
	 * 
	 * // Campus class details
	 * 
	 * public static final String INSERT_CLASS_NAME =
	 * "INSERT INTO campus_classdetail(classdetail_id_int,classstream_id_int, classdetails_name_var, createuser,createdate,modifyuser,modifydate) VALUES (?,?,?,?,?,?,?)"
	 * ; public static final String GET_CAMPUS_CLASS_STREAM_AND_CLASS_DETAILS =
	 * "select cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_id_int,cs.classstream_name_var from campus_classdetail cd,campus_classstream cs where cd.classstream_id_int=cs.classstream_id_int order by length(cd.classdetail_id_int),cd.classdetail_id_int"
	 * ; public static final String UPDATE_CLASS_DETAIL =
	 * "UPDATE campus_classdetail SET classdetails_name_var= ?,modifyuser= ?, modifydate = ?  WHERE classdetail_id_int =?"
	 * ; public static final String DELETE_CLASS_DETAIL =
	 * "DELETE FROM campus_classdetail WHERE classdetail_id_int =?"; public
	 * static final String CHECK_CLASS_DETAIL =
	 * "select count(*) usercount from campus_classdetail where classdetails_name_var=? "
	 * ;
	 * 
	 * // student admission print
	 * 
	 * public static final String GET_ALL_STUDENT_DETAILS =
	 * "select * from campus_student cst,campus_acadamicyear ca,campus_classsection ccs,campus_classdetail ccd,campus_classstream ccst where cst.fms_acadamicyear_id_int=ca.acadamicyear_id_int and cst.fms_classstream_id_int=ccst.classstream_id_int and cst.classdetail_id_int=ccd.classdetail_id_int and cst.classsection_id_int=ccs.classsection_id_int and cst.student_admissionno_var=?"
	 * ; public static final String GET_CLASS_STREAM =
	 * "select classstream_id_int,classstream_name_var from campus_classstream";
	 * public static final String GET_ADMISSION_NO =
	 * "select student_admissionno_var from campus_student where student_admissionno_var like ?"
	 * ;
	 * 
	 * // stream details
	 * 
	 * public static final String INSERT_STREAM_DETAILS =
	 * "INSERT INTO campus_classstream (classstream_id_int,classstream_name_var,description, createuser, createdate,modifyuser,modifydate) VALUES (?,?,?,?,?,?,?)"
	 * ; public static final String CHECK_STREAM_NAME =
	 * "select count(*) usercount from campus_classstream where classstream_name_var=?"
	 * ; public static final String UPDATE_STREAM_DETAILS =
	 * "UPDATE campus_classstream SET classstream_name_var=?,description= ?,modifyuser= ?, modifydate = ?  WHERE classstream_id_int =?"
	 * ; public static final String DELETE_STREAM_DETAILS =
	 * "DELETE FROM campus_classstream WHERE classstream_id_int =?"; public
	 * static final String GET_STREAM_DETAILS =
	 * "select * from campus_classstream order by length(classstream_id_int),classstream_id_int"
	 * ; public static final String UPDATE_MEETING =
	 * "update campus_meeting set meetingdate = ?,starttime=?,endtime=?,description=?,title=? where meetingid = ?"
	 * ; public static final String UPDATE_EVENT =
	 * "update campus_event set eventname = ?,eventdate=?,starttime=?,endtime=?,description=? where eventid = ?"
	 * ; public static final String EVENTNAME_DUPLICATECHECK =
	 * "select count(*) from campus_event where eventname=? and eventdate=?";
	 * public static final String CHECK_SESSION_NAME =
	 * "select count(*) usercount from campus_sessions where SessionName=?";
	 * public static final String CHECK_SESSION_NAME_AND_CODE =
	 * "select count(*) usercount from campus_sessions where SessionName=? and sessionCode!=?"
	 * ; public static final String CHECK_SESSION_NAME_IN_TIMETABLE =
	 * "select count(*) usercount from campus_timetable where sessionCode=?";
	 * public static final String UPDATE_SCHOOL_SESSION_DETAILS =
	 * "update campus_sessions set  startTime=?, endTime=?,UpdateTime=?,UpdatedBy=? where SessionCode=?"
	 * ; public static final String GET_ALL_MEETING_DEATILS =
	 * "select title, description, meetingdate, createdate, status from campus_meeting order by title"
	 * ; public static final String GET_ALL_MEETING_DEATILSDYSTATUS =
	 * "select title, description, meetingdate, createdate,status from campus_meeting where status=? order by title"
	 * ; public static final String GET_ALL_EVENT_DEATILS =
	 * "select eventname, description, eventdate, createddate,status from campus_event order by eventname"
	 * ; public static final String GET_ALL_EVENT_DEATILS_STATUS =
	 * "select eventname, description, eventdate, createddate, status from campus_event where status=? order by eventname"
	 * ;
	 * 
	 * public static final String STUDENT_ALL_DETAILS_STREAM =
	 * "select s.student_fname_var,s.student_fathername_var, s.student_admissionno_var,a.acadamicyear_year,cd.classdetails_name_var,cs.classsection_name_var,s.student_status_var from campus_acadamicyear a join campus_student s on a.acadamicyear_id_int=s.fms_acadamicyear_id_int join campus_classsection cs on s.classdetail_id_int=cs.classdetail_id_int and s.classsection_id_int=cs.classsection_id_int join campus_classstream csm on s.fms_classstream_id_int=csm.classstream_id_int join campus_classdetail cd on cs.classdetail_id_int=cd.classdetail_id_int where s.student_fname_var like ? and csm.classstream_name_var like ?"
	 * ;
	 * 
	 * 
	 * public static final String TEA_CHILD_INFO_STREAM =
	 * "select distinct strm.classstream_id_int,strm.classstream_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?"
	 * ; public static final String TEA_CHILD_INFO_CLASS =
	 * "select distinct clas.classdetail_id_int, clas.classdetails_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and strm.classstream_id_int=?"
	 * ; public static final String TEA_CHILD_INFO_SECTION =
	 * "select distinct sec.classsection_id_int,sec.classsection_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_classsection sec on sec.classsection_id_int = teadet.sectionID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and clas.classdetail_id_int=?"
	 * ; public static final String UPLOAD_REMARKS =
	 * "insert into campus_remarks(remarksCode,AdmissionNo,remarks,commentedby) values(?,?,?,?)"
	 * ; public static final String REMARKS_UPDATE =
	 * "update campus_remarks set remarks=? where AdmissionNo=? and remarksCode=?"
	 * ; public static final String GET_STUDENT_REMARKS =
	 * "select remarksCode,AdmissionNo,remarks from campus_remarks where AdmissionNo=? order by createtime desc"
	 * ; public static final String GET__REMARKS =
	 * "select remarksCode,AdmissionNo,remarks from campus_remarks where remarksCode=?"
	 * ; public static final String REMARKS_DELETE =
	 * "delete from campus_remarks where remarksCode=?";
	 * 
	 * public static final String UPLOAD_REMARKS =
	 * "insert into campus_admin_remarks(remarksCode,AdmissionNo,remarks,remarksType,subjectCode,commentedby,createtime) values(?,?,?,?,?,?,now())"
	 * ; public static final String REMARKS_UPDATE =
	 * "update campus_admin_remarks set remarks=?,updatedby=?,updatetime=now() where AdmissionNo=?  and remarksCode=?"
	 * ; public static final String GET_STUDENT_REMARKS =
	 * "select remarksCode,AdmissionNo,remarksType,subjectCode,remarks,isApproved from campus_admin_remarks  where AdmissionNo=? and commentedby=?  and isApproved='Y' order by createtime desc"
	 * ; public static final String GET__REMARKS =
	 * "select rm.remarksCode,rm.AdmissionNo,rm.remarks,case when st.student_lname_var is null then st.student_fname_var else concat(st.student_fname_var,' ',st.student_lname_var)end Name from campus_admin_remarks rm,campus_student st where st.student_id_int=rm.AdmissionNo and rm.remarksCode=?"
	 * ; public static final String REMARKS_DELETE =
	 * "delete from campus_admin_remarks where remarksCode=?"; public static
	 * final String TEA_CHILD_INFO_STREAM =
	 * "select distinct strm.classstream_id_int,strm.classstream_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?"
	 * ; public static final String TEA_CHILD_INFO_CLASS =
	 * "select distinct clas.classdetail_id_int, clas.classdetails_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and strm.classstream_id_int=?"
	 * ; public static final String TEA_CHILD_INFO_SECTION =
	 * "select distinct sec.classsection_id_int,sec.classsection_name_var from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_classsection sec on sec.classsection_id_int = teadet.sectionID join campus_subject subj on subj.subjectID=teadet.subjectID join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where teadet.teacherID=?  and clas.classdetail_id_int=?"
	 * ; public static final String GET_PREVIOUS_IMAGE =
	 * "select student_imgurl_var from campus_student where student_admissionno_var =?"
	 * ;
	 * 
	 * public static final String GET_PHONE_DIRECTORY_STUDENT =
	 * "select * from (select CASE  WHEN cst.student_lname_var is null then cst.student_fname_var else concat(cst.student_fname_var,' ',cst.student_lname_var) end name,cst.student_fathermobileno_var,cst.student_towncity,cst.student_state,cst.student_country,cst.student_zippostcode,cst.student_street1,cst.student_id_int,CASE WHEN cst.student_father_mailid = null or cst.student_father_mailid = '' then cst.student_mother_mailid else cst.student_father_mailid end mail,cst.student_gardian_mailid from campus_student cst ORDER BY  name) s where name like ?"
	 * ; public static final String GET_PHONE_DIRECTORY_PARENTS =
	 * "select * from ( select concat(FirstName,' ',CASE  WHEN LastName IS NULL THEN '' else LastName end,' ') name,mobileno,email,address from campus_parents ORDER BY  name) s where name like ?"
	 * ; public static final String GET_PHONE_DIRECTORY_TEACHERS =
	 * "select * from ( select concat(FirstName,' ',CASE  WHEN LastName IS NULL THEN '' else LastName end,' ') name,MobileNo,email,Address from campus_teachers ORDER BY  name) s where name like ?"
	 * ; public static final String GET_PHONE_DIRECTORY_ADMIN =
	 * "select username from campus_user where username like ? ORDER BY  username"
	 * ;
	 * 
	 * public static final String GET_TEACHERS =
	 * " select distinct ccs.classsection_name_var,ccd.classdetails_name_var,cs.subjectName, ct.TeacherID,ct.FirstName,ct.LastName,ct.Qualification,ct.designation,ct.MobileNo,ct.email,cts.classID,cts.sectionID,cts.subjectID from campus_teachers ct,campus_teachersettings cts,campus_classsection ccs,campus_classdetail ccd, campus_subject cs where cts.classID=ccd.classdetail_id_int and cts.sectionID=ccs.classsection_id_int and cts.subjectID=cs.subjectID and  ct.TeacherID=cts.teacherID and tstatus='active'"
	 * ; public static final String GET_TEACHERS_BY_SEARCHTERM =
	 * "select FirstName from campus_teachers where tstatus='active' and FirstName like ?"
	 * ;
	 * 
	 * public static final String EDIT_TEACHER_SETTING =
	 * "update campus_teachersettings set classID=?,sectionID=?,subjectID =? where teacherID=?"
	 * ;
	 * 
	 * public static final String GET_TEACHERS_SEARCH_RESULT =
	 * "select distinct ccs.classsection_name_var,ccd.classdetails_name_var,cs.subjectName, ct.TeacherID,ct.FirstName,ct.LastName,ct.Qualification,ct.designation,ct.MobileNo,ct.email,cts.classID,cts.sectionID,cts.subjectID from campus_teachers ct,campus_teachersettings cts,campus_classsection ccs,campus_classdetail ccd, campus_subject cs where cts.classID=ccd.classdetail_id_int and cts.sectionID=ccs.classsection_id_int and cts.subjectID=cs.subjectID and  ct.TeacherID=cts.teacherID and tstatus='active' and FirstName =?"
	 * ; public static final String DELETE_TEACHER_SETTING =
	 * "delete from campus_teachersettings where classID =? and sectionID =? and subjectID =?"
	 * ; public static final String DELETE_TEACHER_SETTING_COUNT =
	 * "select count(*) from campus_teachersettings where classID =? and sectionID =? and subjectID =?"
	 * ; public static final String GET_TEACHER_ID =
	 * "select TeacherID from campus_teachers where FirstName =?"; public static
	 * final String GET_ALL_CLASSES =
	 * "select classstream_name_var from campus_classstream"; public static
	 * final String GET_HOLIDAY_MASTER =
	 * "select ch.HOLIDAY_DATE,ch.WEEKDAY,ch.HOLIDAY_NAME from campus_holidaymaster ch where ch.HOLIDAY_DATE like ? ORDER BY ch.HOLIDAY_DATE"
	 * ; public static final String GET_EVENTS =
	 * "select ce.eventdate,ce.eventname,ce.description from campus_event ce where ce.eventdate like ? ORDER BY eventdate"
	 * ; public static final String GET_MEETINGS =
	 * "select cm.title, cm.meetingdate,cm.description from campus_meeting cm where cm.meetingdate like ? ORDER BY meetingdate"
	 * ; public static final String GET_HOLIDAY_DATE =
	 * "select distinct HOLIDAY_DATE from campus_holidaymaster"; public static
	 * final String GET_MEETING_DATE =
	 * "select distinct meetingdate from campus_meeting"; public static final
	 * String GET_EVENT_DATE = "select distinct eventdate from campus_event";
	 * 
	 * public static final String GET_SUBJECTS =
	 * "select distinct(subjectName),subjectID  from campus_subject group by subjectName order by subjectName"
	 * ; // public static final String INSERT_TEACHER = //
	 * "insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,Address,MobileNo,UserName,email,Subject_specialization1,Subject_specialization2,resume,image,DateOfBirth,DateOfJoining,designation,Idproof,Type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String INSERT_TEACHER =
	 * "insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,Address,MobileNo,UserName,email,Subject_specialization1,Subject_specialization2,resume,image,DateOfBirth,DateOfJoining,designation,Idproof,Type,department,RoleId,gender,IsTransport,stageId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String SAVE_HOLIDAYS =
	 * "insert into campus_holidaymaster(CURRENT_YEAR,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,CREATEDDATE ,CREATEDBY ,MODIFIED_BY,MODIFIED_DATE) values(?,?,?,?,?,?,?,?) "
	 * ;
	 * 
	 * public static final String DISPLAY_HOLIDAYS =
	 * "select distinct chm.WEEKDAY,chm.HOLIDAY_NAME,chm.HOLIDAY_DATE from campus_holidaymaster chm where chm.CURRENT_YEAR=?"
	 * ; public static final String HOLIDAYS_COUNT =
	 * "select (*) from campus_holidaymaster where CURRENT_YEAR=?,HOLIDAY_DATE=?,WEEKDAY=?,HOLIDAY_NAME=?,CREATEDDATE=? ,CREATEDBY=?,MODIFIED_BY=?,MODIFIED_DATE=?"
	 * ; public static final String ACADEMICYEAR_NAME =
	 * "select acadamicyear_year  from campus_acadamicyear where acadamicyear_id_int  =?"
	 * ; public static final String UPDATE_HOLIDAYS =
	 * "update campus_holidaymaster set WEEKDAY=?,HOLIDAY_NAME=?,CURRENT_YEAR=? ,MODIFIED_DATE=?, MODIFIED_BY=? where HOLIDAY_DATE=?"
	 * ; public static final String DELETE_HOLIDAYS =
	 * " delete from campus_holidaymaster where HOLIDAY_DATE=?"; public static
	 * final String GET_STUDPK =
	 * "select student_id_int from campus_student where student_admissionno_var=?"
	 * ;
	 * 
	 * public static final String CHILD_INFO_SUBJECT =
	 * "select * from campus_subject where classid=?"; public static final
	 * String GET_CLASS =
	 * "select classdetail_id_int, classdetails_name_var from campus_classdetail"
	 * ; public static final String GET_STUDENTS_NAMES =
	 * "select student_id_int, case when student_fname_var is null then student_lname_var else concat(student_fname_var,' ',student_lname_var) end StudentName  from campus_student where classdetail_id_int = ? and classsection_id_int= ?"
	 * ; public static final String GET_CLASS_NAMES =
	 * "select * from campus_classdetail"; public static final String
	 * GET_STUDENT_COUNT_BY_SECTION =
	 * "select count(*) from campus_student where classsection_id_int like ?";
	 * 
	 * public static final String GET_ADMISSION =
	 * "select par.stu_addmissionNo from campus_parentchildrelation par,campus_student stu where parentid=? and stu.student_status_var = 'active' and stu.student_id_int = par.stu_addmissionNo"
	 * ; // public static final String GET_DATA = //
	 * "select ca.SubjectID,ca.AssignmentCode from campus_assignment ca,campus_assignmentdetails cad where ca.AssignmentCode=cad.AssignmentCode and cad.AdmissionNo=?"
	 * ;
	 * 
	 * // seshu
	 * 
	 * public static final String GET_DATA =
	 * "select ca.SubjectID,ca.Name,ca.AssignmentType,ca.MaxMarks,cad.AcquiredMarks,ca.Description,cs.student_fname_var from campus_assignment ca,campus_assignmentdetails cad,campus_student cs where ca.AssignmentCode=cad.AssignmentCode and cad.AdmissionNo=? and cs.student_admissionno_var=?"
	 * ; public static final String GET_ASSIGNEMENT_SUBJECT =
	 * "select subjectName from campus_subject where subjectID=?";
	 * 
	 * public static final String GET_ASSIGNMENT_DETAILS =
	 * "select ca.Name,cs.subjectName,ca.MaxMarks,cad.AcquiredMarks,ca.Description from campus_assignment ca,campus_subject cs,campus_parentchildrelation cpc,campus_assignmentdetails cad where ca.AssignmentCode=cad.AssignmentCode and ca.SubjectID=cs.subjectID and cpc.stu_addmissionNo=cad.AdmissionNo  and ca.SubjectID=? and cpc.parentid=? and ca.AssignmentCode=?"
	 * ; public static final String GET_ASSIGNMENT_GENERAL =
	 * "select ca.Name,ca.MaxMarks,cad.AcquiredMarks,ca.Description from campus_assignment ca,campus_parentchildrelation cpc,campus_assignmentdetails cad where ca.AssignmentCode=cad.AssignmentCode and  cpc.stu_addmissionNo=cad.AdmissionNo  and cpc.parentid=? and ca.AssignmentCode=?"
	 * ;
	 * 
	 * public static final String FEE_PAYMENT_ID =
	 * "select count(*) from campus_payment where student_admissionno_var=? and acadamicyear_id_int=?"
	 * ; public static final String GET_TEACHER_COUNT =
	 * "select count(*) from campus_teachers where UserName=? and TeacherID!=?";
	 * // public static final String FEE_PAYMENT_ID = //
	 * "select count(*) from campus_payment where student_admissionno_var=? and acadamicyear_id_int=?"
	 * ; // public static final String GET_TEACHER_COUNT = //
	 * "select count(*) from campus_teachers where UserName=?"; public static
	 * final String GET_TEACHERMAIL_COUNT =
	 * "select count(*) from campus_teachers where email=? and TeacherID!=?";
	 * 
	 * public static final String GET_FEE_AMMENDMENT_MAX_PAYID =
	 * "SELECT MAX(payment_id_int) FROM campus_payment where student_admissionno_var=? group by length(payment_id_int) desc limit 1"
	 * ; public static final String MAX_PAYMENT_ID1 =
	 * "SELECT MAX(payment_id_int) FROM campus_payment where student_admissionno_var=? group by length(payment_id_int) desc limit 1"
	 * ;
	 * 
	 * public static final String FEE_STATUS_TRANSPORT =
	 * "select ctr.Cost_per_person from transport_route ctr  join transport_stopsdetails cts on cts.RouteCode=ctr.RouteCode join transport_student_route_mapping trm on trm.RouteCode = ctr.RouteCode and trm.StopCode=cts.StopCode JOIN campus_student s ON s.student_admissionno_var = trm.admission_no where trm.admission_no=? AND s.student_status_var='active' group by trm.admission_no "
	 * ; public static final String FEE_STATUS_TRANSPORT_COUNT =
	 * "select count(admission_no) from transport_student_route_mapping where admission_no=?"
	 * ; public static final String FEE_STATUS_Present =
	 * "SELECT count(f.feesettings_id_int) FROM campus_feesettings f JOIN campus_student s ON s.classdetail_id_int = f.classdetail_id_int WHERE s.student_admissionno_var=? AND s.student_status_var='Active'"
	 * ;
	 * 
	 * public static final String FEE_SEARCH_FORMN =
	 * "select ctr.RouteName,ctr.Cost_per_person,cts.Stop_Name,s.student_admissionno_var,s.student_fname_var,c.classdetails_name_var,f.feesettings_admissionfee_double,f.feesettings_monthlyfee_double,f.feesettings_annualfee_double,f.feesettings_tutionfee_double,f.feesettings_computerfee_double,f.feesettings_libraryfee_double,f.feesettings_maintainencefee_double,f.feesettings_securityfee_double,f.feesettings_transportfee_double,f.feesettings_hostelfee_double,f.feesettings_examinationfee_double,f.feesettings_fine_double,f.feesettings_others_double,f.feesettings_id_int,s.student_scholorship_var,f.feesettings_lastpaymentdate_var,f.feesettings_concessionamt_double from transport_route ctr join transport_stopsdetails cts on cts.RouteCode=ctr.RouteCode join transport_student_route_mapping trm on trm.RouteCode = ctr.RouteCode and trm.StopCode=cts.StopCode JOIN campus_student s ON s.student_admissionno_var = trm.admission_no JOIN campus_classdetail c ON s.classdetail_id_int = c.classdetail_id_int join campus_feesettings f on f.classdetail_id_int=c.classdetail_id_int where trm.admission_no=? AND s.student_status_var='active' "
	 * ;
	 * 
	 * public static final String INSERT_TEACHER_ATTENDANCE =
	 * "insert into campus_teacher_attendence (TeacherID,AttendenceDate,AttendenceStatus,CreateDate,CreatedBy,isApproved) values (?,?,?,?,?,?)"
	 * ; public static final String UPDATE_TEACHER_ATTENDANCE =
	 * "update campus_teacher_attendence set AttendenceStatus=?,UpdateDate=?,UpdatedBy=?,isApproved='Y' where AttendenceDate=? and TeacherID=?"
	 * ; public static final String CHECK_TEACHER_ATTENDANCE =
	 * "select count(*) from campus_teacher_attendence where AttendenceDate=? and TeacherID=?"
	 * ; // public static final String GET_TEACHER_ATTENDANCE_DETAILS = //
	 * "select distinct cta.TeacherID,ct.FirstName,ct.LastName,cta.AttendenceStatus from campus_teacher_attendence cta,campus_teachers ct,campus_classdetail ccd,campus_classstream ccs,campus_teachersettings cts where cta.AttendenceDate=? and ccs.classstream_id_int=ccd.classstream_id_int and ccd.classdetail_id_int=cts.classID and cts.teacherID=ct.TeacherID and ct.TeacherID=cta.TeacherID and ct.tstatus='active'"
	 * ; public static final String GET_TEACHER_ATTENDANCE_DETAILS =
	 * "select distinct cta.TeacherID,ct.FirstName,ct.LastName,cta.AttendenceStatus from campus_teacher_attendence cta,campus_teachers ct,campus_classdetail ccd,campus_classstream ccs,campus_teachersettings cts where cta.AttendenceDate=? and  ct.TeacherID=cta.TeacherID and ct.tstatus='active' and ct.isActive ='Y' order by length(cta.TeacherID),cta.TeacherID"
	 * ; public static final String LOAD_TUTIONFEE =
	 * "select campus_feesettings.feesettings_tutionfee_double from campus_feesettings,campus_payment where campus_feesettings.feesettings_id_int=campus_payment.feesettings_id_int AND campus_payment.payment_id_int=?"
	 * ;
	 * 
	 * // public static final String GET_REMARKS_DATA = //
	 * "select distinct cs.student_fname_var,cr.remarks from campus_student cs,campus_admin_remarks cr,campus_parentchildrelation cpc where cs.student_admissionno_var=cr.AdmissionNo and cs.student_admissionno_var=?"
	 * ;
	 * 
	 * // writen by seshu
	 * 
	 * public static final String GET_REMARKS_DATA =
	 * "select car.createtime,car.remarksType,car.subjectCode,concat( cs.student_fname_var) as StudentName,car.remarks,car.commentedby from campus_admin_remarks car,campus_student cs where car.AdmissionNo=? and car.AdmissionNo=cs.student_id_int and isApproved='Y'"
	 * ;
	 * 
	 * public static final String GET_REMARKSVIEW_SUBJECTNAME =
	 * "select subjectName from campus_subject where subjectID=?"; public static
	 * final String GET_REMARKSVIEW_TEACHERNAME =
	 * "select concat( FirstName,' ', LastName) as teachername from campus_teachers where TeacherID=?"
	 * ;
	 * 
	 * public static final String GET_REPORT_ADMISSION_NO =
	 * "select student_admissionno_var from campus_student where student_admissionno_var like ? and fms_acadamicyear_id_int=?"
	 * ;
	 * 
	 * public static final String GET_ALL_TEACHER_ATTENDANCE_DETAILS =
	 * "select distinct ct.TeacherID,ct.FirstName,ct.LastName from campus_teachers ct where ct.tstatus='active' and ct.isActive ='Y' order by length(ct.TeacherID),ct.TeacherID"
	 * ; public static final String CHECK_TEACHER_ATTENDANCEDATE =
	 * "select count(*) from campus_teacher_attendence where AttendenceDate=?";
	 * 
	 * public static final String GetSubjectsData =
	 * "select s.subjectID,s.subjectName,s.decription,s.syllabous,cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_name_var,cs.classstream_id_int from campus_subject s inner join campus_classdetail cd on s.classid=cd.classdetail_id_int inner join campus_classstream cs on cd.classstream_id_int=cs.classstream_id_int where s.subjectID=?"
	 * ; public static final String GET_TEAHER =
	 * "select distinct TeacherID from campus_teachers where FirstName=?";
	 * 
	 * // HALL TICKET public static final String GET_EXAMNAMES =
	 * "select examid, examname from campus_examination"; public static final
	 * String GET_EXAM_DATA =
	 * "select distinct ce.examdate,ce.accadamicyear,ce.description,ccd.classdetails_name_var,ccd.classdetail_id_int,ccd.classstream_id_int,ccs.classstream_name_var from campus_classdetail ccd,campus_classstream ccs,campus_examination ce where ccd.classstream_id_int=ccs.classstream_id_int and ce.examid=?"
	 * ; public static final String GET_SUBJECT_FROM_SUBJECT =
	 * "select distinct count(subjectid) from campus_subject where classid=?";
	 * public static final String GET_SUBJECT_FROM_EXAMTIMETABLE =
	 * "select distinct count(subjectid) from campus_examination_timetable where classid=? and examinationid=?"
	 * ;
	 * 
	 * public static final String GET_HOLIDAY_DATES =
	 * "select HOLIDAY_DATE from campus_holidaymaster";
	 * 
	 * // --------------------Teachers Leave //
	 * Request---------------------------------- public static final String
	 * GET_LEAVEREQUEST_USERS = "select id,Name from campus_user"; public static
	 * final String INSERT_LEAVEREQUEST_USERS =
	 * "insert into campus_teachers_leave_request (NoofLeaves,ReasonForLeave,StartDate,EndDate,RequestedBy,RequestedDate,filepaath,LeaveStatus) values (?,?,?,?,?,?,?,?)"
	 * ; public static final String CHECKING_LEAVE_DUPLICATS =
	 * "select count(*)as LeaveCount from campus_teachers_leave_request where RequestedBy=? and StartDate=? and EndDate=? and ReasonForLeave=?"
	 * ; // -------------------Leave Request Status ---------------------------
	 * 
	 * public static final String GET_LEAVEREQUEST_DETAILS =
	 * "select SNO,NoofLeaves,ReasonForLeave,StartDate,EndDate,RequestedDate,LeaveStatus,ApprovedBy,AprovedDate,TotalDaysAproved from campus_teachers_leave_request where RequestedBy=? order by RequestedDate desc "
	 * ; public static final String APPROVED_DETAILS =
	 * "select ApprovedBy,AprovedDate,TotalDaysAproved,commennts from campus_teachers_leave_request where SNO=?"
	 * ;
	 * 
	 * public static final String GET_TEACHERNAME_ID =
	 * "select FirstName  from campus_teachers where TeacherID=?";
	 * 
	 * public static final String GET_EXAMINATION_DATA =
	 * "select distinct ce.examdate,ca.acadamicyear_year,ce.description,ccd.classdetails_name_var,ccd.classdetail_id_int,ccd.classstream_id_int,ccs.classstream_name_var from campus_classdetail ccd,campus_classstream ccs,campus_examination ce,campus_acadamicyear ca where ccd.classstream_id_int=ccs.classstream_id_int and ca.acadamicyear_id_int=ce.accadamicyear and ce.examid=?"
	 * ; public static final String GET_STUDENT =
	 * "select stud.student_id_int,strm.classstream_id_int,clas.classdetail_id_int,sect.classsection_id_int from campus_student stud join campus_parentchildrelation parch on parch.stu_addmissionNo=stud.student_id_int join campus_classstream strm on strm.classstream_id_int=stud.fms_classstream_id_int join campus_classdetail clas on clas.classdetail_id_int = stud.classdetail_id_int and clas.classstream_id_int=strm.classstream_id_int join campus_classsection sect on sect.classsection_id_int=stud.classsection_id_int and sect.classdetail_id_int=clas.classdetail_id_int where parch.ParentID=?"
	 * ; public static final String GET_MEETING_DATA_FOR_PARENT =
	 * "select meetingid,title,meetingdate,starttime,endtime,description,createdby from campus_meeting where studentid=? and status='active' union select meetingid,title,meetingdate,starttime,endtime,description,createdby from campus_meeting where classid=? and sectionid=? and allstudents='all' and status='active' union select meetingid,title,meetingdate,starttime,endtime,description,createdby from campus_meeting where forall=? and classid=? and  allsections='all' and status='active' union select meetingid,title,meetingdate,starttime,endtime,description,createdby from campus_meeting where forall=? and allcalsses='all' and status='active' union select meetingid,title,meetingdate,starttime,endtime,description,createdby from campus_meeting where forall='all' and status='active'"
	 * ;
	 * 
	 * public static final String GET_SUBJECTSFORTEACHERS =
	 * "select distinct teach.FirstName,teadet.teacherID,teadet.subjectID, clas.classdetails_name_var,sec.classsection_name_var,subj.subjectName from campus_teachers teach join campus_teachersettings teadet on teach.TeacherID = teadet.teacherID join campus_classdetail clas on clas.classdetail_id_int= teadet.classID join campus_classsection sec on sec.classsection_id_int = teadet.sectionID join campus_subject subj on subj.subjectID=teadet.subjectID where teadet.classID=? and teadet.sectionID=? and teadet.teacherID=? order by teach.FirstName"
	 * ;
	 * 
	 * 
	 * 
	 * // --------------------Quota Details -------------------------------
	 * 
	 * public static final String GET_QUOTA_DETAILS =
	 * "select Quota_Code,Quota_Name,Quota_Description from campus_quota order by Quota_Name"
	 * ; public static final String STORE_QUOTA_DETAILS =
	 * "insert into campus_quota(Quota_Code,Quota_Name,Quota_Description) values(?,?,?)"
	 * ; public static final String GET_PARTICULAR_QUOTA =
	 * "select Quota_Code,Quota_Name,Quota_Description from campus_quota where Quota_Code=?"
	 * ; public static final String GET_QUOTA_NAME =
	 * "select count(*) from campus_quota where Quota_Name=? "; public static
	 * final String UPATE_QUOTA_DETAILS =
	 * "update campus_quota set Quota_Name=?,Quota_Description=? where Quota_Code=?"
	 * ; public static final String DELETE_QUOTA =
	 * "Delete from campus_quota where Quota_Code=?";
	 * 
	 * public static final String GET_CLASS_NAME_UPDATE =
	 * "select classdetail_id_int,classdetails_name_var from campus_classdetail"
	 * ; public static final String STUDENT_SEARCH_ADMISSIONDETAILSPRINT =
	 * "select student_fname_var,student_admissionno_var from campus_student where student_status_var ='Active' and student_fname_var like ? and classdetail_id_int=? and classsection_id_int=?"
	 * ; public static final String ALLSTUDENTDETAILS =
	 * "select stu.isConcession,stu.isTransport,stu.student_scholorship_var,stu.student_quota,stu.fms_acadamicyear_id_int,stu.classdetail_id_int,stu.student_admissionno_var, case when stu.student_rollno is null then '-' else stu.student_rollno end student_rollno, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,'',stu.student_lname_var) end StudentName, stu.student_dob_var, concat(cls.classdetails_name_var,' - ',sec.classsection_name_var)ClassName  from campus_student stu join campus_classdetail cls on stu.classdetail_id_int = cls.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=stu.classsection_id_int  order by stu.student_admissionno_var,StudentName ASC"
	 * ; public static final String SEARCHSTUDENTDETAILS =
	 * "select stu.student_id_int,stu.isConcession,stu.fms_acadamicyear_id_int,stu.classdetail_id_int,stu.student_scholorship_var,stu.student_quota,stu.isTransport,stu.student_admissionno_var, case when stu.student_rollno is null then '-' else stu.student_rollno end student_rollno, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,'',stu.student_lname_var) end StudentName, stu.student_dob_var,  concat(cls.classdetails_name_var,' - ',sec.classsection_name_var) classdetails_name_var from campus_student stu join campus_classdetail cls on stu.classdetail_id_int = cls.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=stu.classsection_id_int where (stu.student_admissionno_var like ? OR stu.student_rollno like ?  or concat(stu.student_fname_var,'',stu.student_lname_var) like ? or stu.student_dob_var like ? or classdetails_name_var like ? or sec.classsection_name_var like ? or concat(cls.classdetails_name_var,' - ',sec.classsection_name_var) like ? or concat(cls.classdetails_name_var,'-',sec.classsection_name_var) like ?)and stu.fms_acadamicyear_id_int=? order by stu.student_admissionno_var,stu.student_fname_var ASC"
	 * ;
	 * 
	 * //
	 * .............................Character,NoObjection,Complaitopn.........
	 * ....................................// public static final String
	 * GET_CLASS_DETAILS =
	 * "select classdetail_id_int,classdetails_name_var from campus_classdetail"
	 * ; public static final String GET_SECTION_DETAILS =
	 * "select classsection_id_int ,classsection_name_var  from campus_classsection where classdetail_id_int =?"
	 * ; public static final String GET_STUDENT_NAMES =
	 * "select cs.student_fname_var,cs.student_admissionno_var from campus_student cs where cs.fms_classstream_id_int=? and cs.classdetail_id_int=? and cs.classsection_id_int=? and cs.student_fname_var like ?"
	 * ; public static final String ADD_STUDENT_REMARKS =
	 * "insert into campus_remarks(remarksCode,AdmissionNo,remarks,remarksType,commentedby,createtime) values(?,?,?,?,?,?)"
	 * ; public static final String GET_TEACHER_NAMES =
	 * "select ct.TeacherID,ct.FirstName from campus_teachers ct where isActive ='Y' and ct.FirstName like ?"
	 * ; public static final String GET_SUBJECTS_BY_TEACHERID =
	 * "select cts.subjectID,cs.subjectName from campus_subject cs,campus_teachersettings cts where cs.subjectID=cts.subjectID and cts.teacherID=?"
	 * ; public static final String GET_STUDENTS_REMARKS =
	 * "select DISTINCT cr.remarksCode,cr.AdmissionNo,cr.remarks,cr.remarksType,cr.createtime,cr.commentedby,css.classstream_name_var ,cc.classdetails_name_var,ccs.classsection_name_var from campus_student cs,campus_remarks cr,campus_classstream css,campus_classdetail cc,campus_classsection ccs where cs.student_admissionno_var=cr.AdmissionNo and css.classstream_id_int = cs.fms_classstream_id_int and cc.classdetail_id_int=cs.classdetail_id_int and ccs.classsection_id_int=cs.classsection_id_int"
	 * ;
	 * 
	 * public static final String GET_STUDENT_DETAIL_REPORT =
	 * "select * from campus_student where student_status_var ='Active' and student_admissionno_var=?"
	 * ; public static final String REPORT_STUDENT_SEARCH =
	 * "select student_fname_var,student_admissionno_var from campus_student where student_status_var ='Active' and student_fname_var like ? and classdetail_id_int=? and classsection_id_int=? order by student_fname_var limit 10"
	 * ;
	 * 
	 * // ----------------------Admin Confirmation for teacher leave //
	 * request------------------------
	 * 
	 * public static final String GET_TEACHERLEAVEREQUEST =
	 * "select cr.SNO,cr.NoofLeaves,cr.ReasonForLeave,cr.StartDate,cr.EndDate,ct.FirstName,cr.RequestedDate,cr.filepaath,cr.LeaveStatus,cr.ApprovedBy,cr.AprovedDate,cr.TotalDaysAproved from campus_teachers_leave_request cr,campus_teachers ct where cr.RequestedBy=ct.TeacherID and LeaveStatus in ('NOT APPROVED','HOLD')  order by RequestedDate"
	 * ; public static final String GET_SINGLE_TEACHERLEAVEREQUEST =
	 * "select SNO,NoofLeaves,ReasonForLeave,StartDate,EndDate,concat(tea.FirstName,' ',tea.LastName) requestname,RequestedDate,filepaath,LeaveStatus,ApprovedBy,AprovedDate,TotalDaysAproved from campus_teachers_leave_request leavereq,campus_teachers tea where LeaveStatus in ('NOT APPROVED','HOLD') and SNO=? and leavereq.RequestedBy = tea.TeacherID order by RequestedDate"
	 * ; public static final String CONFIRM_LEAVE_REQUEST =
	 * "update campus_teachers_leave_request set LeaveStatus=?,ApprovedBy=?,AprovedDate=?,TotalDaysAproved=?,commennts=?,ApprovedStartDate=?,ApprovedEndDate=? where SNO=?"
	 * ; public static final String HOLD_LEAVE_REQUEST =
	 * "update campus_teachers_leave_request set LeaveStatus=?,ApprovedBy=?,AprovedDate=?,TotalDaysAproved=?,commennts=? where SNO=?"
	 * ;
	 * 
	 * // modification in studentregistration
	 * 
	 * 
	 * 
	 * 
	 * // Exammination postpone
	 * 
	 * public static final String GET_POSTPONE_EXAMINATION_DETAILS =
	 * "select ce.examid,ce.examname,ce.description,ca.acadamicyear_year,ce.examdate,ce.exam_enddate from campus_examination ce,campus_acadamicyear ca where ce.accadamicyear=ca.acadamicyear_id_int and examid=?"
	 * ; public static final String GET_ALL_EXAMS =
	 * "select examid,examname from campus_examination where examdate>?";
	 * 
	 * public static final String INSERT_POSTPONEEXAM_DETAILS =
	 * "insert into campus_examination_postpone(ExampostponedCode,ExamCode,previousExamDate,postponedExamDate,reason,createdby,createdDate) values (?,?,?,?,?,?,?)"
	 * ; public static final String UPDATE_POSTPONE_EXAMS =
	 * "update campus_examination set examdate=?,modifiedby=?,modifiedDate=? where examid=?"
	 * ;
	 * 
	 * 
	 * // STUDENT RE-ADMISSION public static final String DATE_OF_BIRTH =
	 * "select student_dob_var from  campus_student where student_admissionno_var=?"
	 * ; public static final String GET_QUOTA =
	 * "select student_quota from campus_student where student_admissionno_var=?"
	 * ; public static final String GET_ACADAMIC_YEAR_ID =
	 * "select fms_acadamicyear_id_int from campus_student where student_admissionno_var=?"
	 * ; public static final String CHECK_STREAM =
	 * "select fms_classstream_id_int from campus_student where student_admissionno_var=?"
	 * ; public static final String GET_ACADAMICYEAR =
	 * "select acadamicyear_year from campus_acadamicyear where acadamicyear_id_int=?"
	 * ; public static final String STUDENT_READMISSION_DATA_INSERT =
	 * "update campus_student set student_status_var =?,modifyuser=?,modifydate=? where student_id_int=?"
	 * ;
	 * 
	 * public static final String BIRTHCERTIFICATE_STUDENT_SEARCH =
	 * "select st.student_fname_var,st.student_admissionno_var from campus_student st left outer join campus_birthcertificate bc on (st.student_admissionno_var=bc.admissionNo) where st.student_status_var ='Active' and st.student_admissionno_var not in (select admissionNo from campus_birthcertificate) and st.student_fname_var like ? and st.classdetail_id_int=? and st.classsection_id_int=? order by st.student_fname_var"
	 * ; public static final String TRANSFERCERTIFICATE_STUDENT_SEARCH =
	 * "select st.student_fname_var,st.student_admissionno_var from campus_student st left outer join campus_tc_details bc on (st.student_admissionno_var=bc.admissionNo) where st.student_status_var ='Active' and st.student_admissionno_var not in (select admissionNo from campus_tc_details) and st.student_fname_var like ? and st.classdetail_id_int=? and st.classsection_id_int=? order by st.student_fname_var"
	 * ;
	 * 
	 * // Exam Timetable
	 * 
	 * public static final String GET_TIMETABLE_DETAILS =
	 * "select cs.classstream_id_int,cc.classdetail_id_int,ce.examname from campus_examination ce,campus_classdetail cc,campus_classstream cs where cc.classdetails_name_var=? and cs.classstream_name_var=? and ce.examid=?"
	 * ;
	 * 
	 * public static final String GET_TIMETABLE_SUBJECT =
	 * "select subjectID,subjectName from campus_subject where classid=?";
	 * 
	 * public static final String STORE_EXAM_TIMETABLE_DETAILS =
	 * "insert into campus_examination_timetable(categoryid,classid,subjectid,examinationid,examdate,examtime,endtime,createuser,createdate) values (?,?,?,?,?,?,?,?,now())"
	 * ;
	 * 
	 * public static final String GET_EXAM_TIMETABLE =
	 * "select examdate,examtime from campus_examination_timetable where categoryid=? and classid=? and subjectid=? and examinationid=?"
	 * ;
	 * 
	 * public static final String CHECK_DUPLICATE_EXAM =
	 * "select count(*) examdate from campus_examination_timetable where categoryid=? and classid=? and subjectid=? and examinationid=?"
	 * ;
	 * 
	 * public static final String UPDATE_EXAMDATE =
	 * "update campus_examination_timetable set examdate=?,examtime=?,endtime=?,updateuser=?,updatedate=now()  where categoryid=? and classid=? and subjectid=? and examinationid=?"
	 * ;
	 * 
	 * // ADMIN REMARKS public static final String GET_TEACHERS_BY_SECTION =
	 * "select distinct t.TeacherID,t.FirstName from campus_teachers t,campus_teachersettings ts where t.teacherID=ts.teacherID  and  ts.classID=? and ts.sectionID=? and t.tstatus='active'"
	 * ; public static final String GET_STUDENTS_BY_SECTION =
	 * "select student_id_int,student_fname_var from campus_student where classdetail_id_int=? and classsection_id_int=?"
	 * ; public static final String GET_TEACHER_SUBJECTS =
	 * "select cs.subjectID,cs.subjectName from campus_subject cs,campus_teachersettings cts where cts.subjectID=cs.subjectID and cts.classID=? and cts.sectionID=? and cts.teacherID=?"
	 * ; public static final String GET_STUDENT_SUBJECTS =
	 * "select subjectID,subjectName from campus_subject where classid=?";
	 * public static final String INSERT_REMARKS =
	 * "insert into campus_admin_remarks(remarksCode,AdmissionNo,TeacherCode,remarks,remarksType,subjectCode,commentedby,createtime) values(?,?,?,?,?,?,?,now())"
	 * ; public static final String GET_ADMISSIONO =
	 * "select student_admissionno_var from campus_student where student_id_int=?"
	 * ; public static final String GET_EXAM_LIST =
	 * "select examid,examname from campus_examination where examdate>?"; public
	 * static final String GET_ADMISSIONO_COUNT =
	 * "select count(*) from campus_admin_remarks where AdmissionNo=? and subjectCode=?"
	 * ; public static final String UPDATE_REMARKS_STUDENT =
	 * "update campus_admin_remarks set remarks=? ,remarksType=? ,updatedby=?,updatetime=now() where AdmissionNo=? and subjectCode=?"
	 * ; public static final String GET_REMARKS_TEACHER_COUNT =
	 * "select count(*) from campus_admin_remarks where TeacherCode=? and subjectCode=?"
	 * ; public static final String UPDATE_REMARKS_TEACHER =
	 * "update campus_admin_remarks set remarks=? ,remarksType=? ,updatedby=?,updatetime=now() where TeacherCode=? and subjectCode=?"
	 * ;
	 * 
	 * // Class Teacher
	 * 
	 * public static final String GET_CLASSES =
	 * "select classdetail_id_int,classdetails_name_var from campus_classdetail"
	 * ; public static final String GET_TEACHER_DETAILS =
	 * "select distinct ct.TeacherID,ct.FirstName from campus_teachersettings cts,campus_teachers ct where cts.classID=? and ct.TeacherID=cts.teacherID"
	 * ; public static final String GET_CLASS_SUBJECTS =
	 * "select cs.subjectID,cs.subjectName from campus_teachersettings cts,campus_subject cs where cts.teacherID=? and cts.subjectID=cs.subjectID and cts.classID=?"
	 * ; public static final String STORE_CLASSTEACHER_DETAILS =
	 * "insert into campus_classteacher (CTCode,ClassCode,TeacherCode,SubjectCode,CreateUser) values (?,?,?,?,?)"
	 * ; public static final String UPDATE_CLASSTEACHER_DETAILS =
	 * "update campus_classteacher set TeacherCode=?,SubjectCode=? where ClassCode=?"
	 * ; // public static final String CHECK_CLASSTEACHER = //
	 * "select count(*) classcode from campus_classteacher where ClassCode=?";
	 * public static final String GET_CLASSTEACHER_DETAILS =
	 * "select cct.ClassCode,ccd.classdetails_name_var,cct.TeacherCode,ct.FirstName,cct.SubjectCode,cs.subjectName from campus_classteacher cct,campus_classdetail ccd,campus_subject cs,campus_teachers ct where cct.ClassCode=ccd.classdetail_id_int and cct.TeacherCode=ct.TeacherID and cct.SubjectCode=cs.subjectID"
	 * ;
	 * 
	 * // Fee Stream Settings public static final String GET_FEE_DATA =
	 * "select fsd.FeeCode,fm.FeeName,ft.FeeType,fsd.FeeAmount,fsd.LastDate,fsd.Fine from campus_fee_structure_details fsd,campus_fee_master fm,campus_fee_type ft where fsd.FeeCode=fm.FeeCode and ft.FeeTypeCode=fsd.FeeTypeCode and  fsd.FeeStructureCode=?"
	 * ; public static final String GET_FEE_STRUCTURECODE =
	 * "select FeeStructureCode from campus_fee_structure where ClassCode=? and AccyearCode=?"
	 * ;
	 * 
	 * // Teacher Leave Request
	 * 
	 * public static final String VALIDATE_DUPLICATELEAVE_REQUEST =
	 * "select count(*) duplicateleave,StartDate,EndDate from campus_teachers_leave_request where RequestedBy=? and ? between StartDate and EndDate and ? "
	 * ; public static final String TEACHER_LEAVE_REQUEST =
	 * "select TeacherID,case when LastName is null then FirstName else concat(FirstName,' ',LastName)end TearcherNames from campus_teachers order by TearcherNames"
	 * ;
	 * 
	 * public static final String GET_COUNT_EXAM_SUBJECT =
	 * "select count(*) from campus_marks_upload where subid=? and examid=? and classid=? and sectionid=?"
	 * ; public static final String GET_MARKS_COUNT =
	 * "select count(*) from campus_marks where marks_upload_id=? and admissionno=?"
	 * ;
	 * 
	 * public static final String GET_SUBJECT_NAME =
	 * "select subjectName from campus_subject where subjectID=?"; public static
	 * final String GET_STUDENT_NAME =
	 * "select student_fname_var from campus_student where student_id_int=?";
	 * public static final String GET_REMARKS_COUNT =
	 * "select count(*) from campus_admin_remarks where AdmissionNo=? and subjectCode=?  and commentedby=?"
	 * ; public static final String GET_REMARKS_GENERAL_COUNT =
	 * "select count(*) from campus_admin_remarks where AdmissionNo=? and remarksType='general'  and commentedby=?"
	 * ;
	 * 
	 * public static final String BIRTH_STUDENT_SEARCH =
	 * "select student_fname_var,student_admissionno_var from campus_student where student_status_var ='Active' and student_fname_var like ? and classdetail_id_int=? and classsection_id_int=? and student_admissionno_var not in (select admissionNo from campus_birthcertificate) order by student_fname_var limit 10"
	 * ; public static final String TRANSFER_STUDENT_SEARCH =
	 * "select student_fname_var,student_admissionno_var from campus_student where student_status_var ='Active' and student_fname_var like ? and classdetail_id_int=? and classsection_id_int=? and student_admissionno_var not in (select admissionNo from campus_tc_details) order by student_fname_var limit 10"
	 * ;
	 * 
	 * public static final String FEEMASTER_GETFEETYPE =
	 * "select FeeTypeCode,FeeType from campus_fee_type"; public static final
	 * String FEEMASTER_INSERTFEEMASTER =
	 * "insert into campus_fee_master (FeeCode,FeeName,FeeTypeCode,isActive,description,createdby) values (?,?,?,?,?,?)"
	 * ; public static final String FEEMASTER_CREATEDTIME =
	 * "select createtime from campus_fee_master where FeeCode=?"; public static
	 * final String FEEMASTER_UPDATEFEEMASTER =
	 * "update campus_fee_master set FeeName=?,description=?,FeeTypeCode=?,isActive=?,createtime=?,modifiedby=?,modifiedtime=now() where FeeCode=?"
	 * ; public static final String FEEMASTER_FEENAMECHECK =
	 * "SELECT distinct(count(*)) FROM campus_fee_master where FeeName=?";
	 * public static final String FEEMASTER_ALLLISTDATAS =
	 * "select fm.FeeCode,fm.description,fm.FeeName,ft.FeeType,ft.FeeTypeCode,fm.isActive from campus_fee_master fm join campus_fee_type ft on ft.FeeTypeCode=fm.FeeTypeCode"
	 * ; public static final String FEEMASTER_REMOVEFEE =
	 * "delete from campus_fee_master where FeeCode=? and FeeCode not in (select distinct FeeCode from campus_fee_settings)"
	 * ; public static final String FEEMASTER_GETAPPROVEDDETAILS =
	 * "select cd.classdetails_name_var,fs.FeeCode,fm.FeeName,ft.FeeTypeCode,ft.FeeType from campus_fee_master fm join campus_fee_settings fs on fm.FeeCode=fs.FeeCode  join campus_fee_type ft on ft.FeeTypeCode=fm.FeeTypeCode join campus_classdetail cd on cd.classdetail_id_int=fs.ClassCode where fs.ClassCode=? and fs.AccyearCode=?"
	 * ; public static final String FEEMASTER_GETACTIVEFEEDETAILS =
	 * "select fm.FeeCode,fm.FeeName,ft.FeeType from campus_fee_master fm join campus_fee_type ft on ft.FeeTypeCode=fm.FeeTypeCode where fm.isActive='Y' and fm.FeeCode not in (select FeeCode from campus_fee_settings where ClassCode=? and AccyearCode=?)"
	 * ; public static final String FEEMASTER_APPROVEFEEDETAILS =
	 * "insert into campus_fee_settings (FeeCode,ClassCode,AccyearCode,createuser) values (?,?,?,?)"
	 * ; public static final String FEEMASTER_FEECOUNT =
	 * "select count(FeeCode) from campus_fee_settings where FeeCode=? and ClassCode=? and AccyearCode=?"
	 * ; public static final String FEEMASTER_UNAPPROVEFEEDETAILS =
	 * "delete from campus_fee_settings where FeeCode=? and ClassCode=? and AccyearCode=?"
	 * ;
	 * 
	 * public static final String FEEMASTER_UNAPPROVECOUNT =
	 * "select count(*) from campus_fee_structure_details fst join campus_fee_structure fs on fs.FeeStructureCode=fst.FeeStructureCode where fst.FeeCode=? and fs.ClassCode=? and fs.AccyearCode=?"
	 * ; public static final String FEEMASTER_GETALLSETTINGDETAILS =
	 * "select fs.FeeCode,fm.FeeName,ft.FeeTypeCode,ft.FeeType from campus_fee_master fm join campus_fee_settings fs on fm.FeeCode=fs.FeeCode join campus_fee_type ft on ft.FeeTypeCode=fm.FeeTypeCode where fs.ClassCode=? and  fs.AccyearCode=? and fs.FeeCode not in(select fsd.FeeCode from campus_fee_structure_details  fsd  join campus_fee_structure fs on fs.FeeStructureCode=fsd.FeeStructureCode where fs.AccyearCode=? and fs.ClassCode=?)"
	 * ; public static final String FEEMASTER_GETALLSTRUCTUREDETAILS =
	 * "select distinct fst.FeeStructureCode,fstd.FeeCode,fm.FeeName,fstd.FeeTypeCode,ft.FeeType,fstd.FeeAmount,fstd.LastDate,fstd.Fine from campus_fee_structure_details fstd join campus_fee_structure fst on fst.FeeStructureCode=fstd.FeeStructureCode join campus_fee_master fm on fm.FeeCode=fstd.FeeCode join campus_fee_settings fs on fs.FeeCode=fm.FeeCode join campus_fee_type ft on ft.FeeTypeCode=fm.FeeTypeCode where fst.ClassCode=? and fst.AccyearCode=?"
	 * ; public static final String FEEMASTER_DELETEFEESTRUCTUREDATA =
	 * "DELETE FROM campus_fee_structure_details USING campus_fee_structure_details, campus_fee_structure where campus_fee_structure_details.FeeStructureCode=? and campus_fee_structure_details.FeeCode=? and campus_fee_structure_details.FeeTypeCode=? and campus_fee_structure.ClassCode=? and campus_fee_structure.AccyearCode=?"
	 * ; public static final String FEEMASTER_DELETEFEESETTINGSDATA =
	 * "delete from campus_fee_settings where ClassCode=? and FeeCode =? and AccyearCode=?"
	 * ;
	 * 
	 * public static final String FEEMASTER_INSERTFEESTRCTUREDETAILS =
	 * "insert into campus_fee_structure_details (FeeStructureCode,FeeCode,FeeTypeCode,FeeAmount,LastDate,Fine) values (?,?,?,?,?,?)"
	 * ; public static final String FEEMASTER_UPDATEFEESTRCTUREDETAILS =
	 * "update campus_fee_structure_details set FeeAmount=?,LastDate=?,Fine=? where FeeStructureCode=? and FeeCode=? and FeeTypeCode=?"
	 * ; public static final String FEEMASTER_INSERTFEESTRUCTURE =
	 * "insert into campus_fee_structure (FeeStructureCode,StreamCode,ClassCode,AccyearCode,createuser) values(?,?,?,?,?) "
	 * ; public static final String FEEMASTER_INSERTFEESTRCTUREDETAILS_1 =
	 * "insert into campus_fee_structure_details (FeeStructureCode,FeeCode,FeeTypeCode,FeeAmount,LastDate,Fine) values (?,?,?,?,?,?)"
	 * ; public static final String FEEMASTER_FEESTRUCTURECOUNT =
	 * "select count(*) from campus_fee_structure where FeeStructureCode=?";
	 * public static final String FEEMASTER_FEESTRUCTURECLASSandACCYEARCOUNT =
	 * "select count(*) from campus_fee_structure where FeeStructureCode=? and ClassCode=? and AccyearCode=?"
	 * ; public static final String FEEMASTER_FEESTRUCTURECLASSCOUNT =
	 * "select distinct classstream_id_int from campus_classdetail where classdetail_id_int=?"
	 * ; public static final String REPORTS_GETSTUDENTID =
	 * "select student_id_int from campus_student where student_admissionno_var=?"
	 * ;
	 * 
	 * public static final String CHECK_PASSWORD =
	 * "select password from campus_user where username=?"; public static final
	 * String CHANGE_PASSWORD =
	 * "update campus_user set password=? where username=?";
	 * 
	 * // View Admin Remark on Teacher
	 * 
	 * public static final String GET_REMARK =
	 * "select remarks,subjectCode,remarksType,createtime from campus_admin_remarks  where TeacherCode=? and isApproved='Y'"
	 * ;
	 * 
	 * public static final String GET_SUBJECTNAME =
	 * "select subjectName from campus_subject where subjectID=?";
	 * 
	 * public static final String INSERT_PAYMENT =
	 * "insert into campus_payment(PaymentCode,AdmissionNo,FeeStructureCode,Payment_Date,TotalAmuont,AccadmicYear,CreateUser,Createtime)values(?,?,?,?,?,?,?,now())"
	 * ; public static final String INSERT_PAYMENT_DETAILS =
	 * "insert into campus_payment_details(PaymentCode,FeeCode,FeeTypeCode,FeeAmount,PaidDate,Fine,createtime)values(?,?,?,?,?,?,now())"
	 * ; public static final String FEE_TYPE_CODE =
	 * "select FeeTypeCode from campus_fee_type where FeeType=?"; public static
	 * final String GET_PAYMENT_COUNT =
	 * "select count(*) from campus_payment_details cpd, campus_payment cp   where cp.AdmissionNo=? and cp.AccadmicYear=? and cpd.FeeTypeCode=? and cp.PaymentCode=cpd.PaymentCode"
	 * ; public static final String GET_PAYMENT_DETAILS_COUNT =
	 * "select count(*) from campus_payment_details cpd, campus_payment cp where cp.AdmissionNo=? and cpd.FeeCode=? and  cp.AccadmicYear=? and cp.PaymentCode=cpd.PaymentCode"
	 * ; public static final String GET_PAYMENT_LIST =
	 * "select cpd.FeeCode,cpd.FeeTypeCode,cpd.FeeAmount,cpd.PaidDate,cpd.Fine,cft.FeeType from campus_payment_details cpd,campus_payment cp,campus_fee_type cft where cp.AdmissionNo=?   and cp.AccadmicYear=? and cpd.FeeCode=? and cp.PaymentCode=cpd.PaymentCode and cft.FeeTypeCode=cpd.FeeTypeCode"
	 * ; public static final String GET_PAYMENT_DETAILS =
	 * "select admissionNo,AccYear,StudentPaymentType,PaidDate,Month,Year from campus_student_feetype where admissionNo=? and AccYear=? and Month=(select max(Month) from campus_student_feetype where admissionNo=? and AccYear=? and Year=(select max(Year) from campus_student_feetype where admissionNo=? and AccYear=?)) "
	 * ;
	 * 
	 * public static final String GET_TEACHERNAME_MEETING =
	 * "select concat(FirstName, ' ' , LastName) as TeacherName from campus_teachers where TeacherID=?"
	 * ;
	 * 
	 * // Delete job applicant public static final String DELETE_APPLICANT =
	 * "delete from campus_appliedjobs where sno=?";
	 * 
	 * public static final String ALL_Carrer_Data =
	 * "select * from campus_careers"; public static final String
	 * UPDATE_MARKS_UPLOAD =
	 * "update campus_marks_upload set maximum_marks=?,required_marks=? where examid=? and subid=? and categoryid=? and classid=? and sectionid=?"
	 * ; public static final String GET_MARKS_UPLOADID =
	 * "select marks_upload_id from campus_marks_upload where subid=? and examid=? and classid=? and sectionid=?"
	 * ; public static final String DELETE_OLDMARKS =
	 * "delete  from campus_marks where marks_upload_id=?";
	 * 
	 * public static final String GET_FEE_MASTER =
	 * "select FeeCode,FeeName from campus_fee_master where isActive='y'";
	 * public static final String GET_FEE_STRUCTURE_CODE =
	 * "select cf.FeeStructureCode,cf.AccyearCode,ca.acadamicyear_year from campus_fee_structure cf join campus_acadamicyear ca on ca.acadamicyear_id_int=cf.AccyearCode   where cf.ClassCode=?"
	 * ; public static final String GET_FEE_STRUCTURE_DETAILS =
	 * "select fm.FeeName,fsd.FeeAmount from campus_fee_structure_details  fsd join campus_fee_master fm  on fsd.FeeCode=fm.FeeCode where fsd.FeeStructureCode=? and fsd.FeeCode=?"
	 * ; public static final String GET_FEE_STRUCTURE_COUNT =
	 * "select count(*) from campus_fee_structure_details where FeeStructureCode=? and FeeCode=?"
	 * ;
	 * 
	 * public static final String GET_CLASS_NAME_BY_TEACHER =
	 * "select ClassCode,classdetails_name_var from campus_classteacher ct,campus_classdetail cd where ct.ClassCode=cd.classdetail_id_int and TeacherCode=?"
	 * ; public static final String GET_STUDENT_FEE_BY_CLASSID =
	 * "select distinct student_id_int,student_admissionno_var, student_fname_var studentName,case when csf.admissionNo is null then 'Not Paid' when csf.admissionNo is not null then 'Paid' end feeStatus from campus_student cs left outer join (select admissionNo from campus_student_feetype where (Month=? and year=?) or (year=? and StudentPaymentType=?)) csf on cs.student_admissionno_var=csf.admissionNo where cs.classdetail_id_int=?"
	 * ; public static final String INSERT_PAYMENT_FEE_TYPE =
	 * "insert into campus_student_feetype (admissionNo,AccYear,StudentPaymentType,PaidDate,PaidAmount,Month,Year,MonthName) values(?,?,?,?,?, ?,?,? )"
	 * ;
	 * 
	 * public static final String GETPAIDFEECOUNT_FORPARTICULARMONTH =
	 * "select count(admissionNo) from campus_student_feetype where admissionNo=? and Year=? and Month=?"
	 * ; public static final String GETPAIDFEE_FEEID =
	 * "select distinct p.PaymentCode,cmp.PaidDate from campus_payment p join campus_payment_details cmp on cmp.PaymentCode=p.PaymentCode where p.AdmissionNo=? "
	 * ; public static final String GET_ALLTEACHERS =
	 * "SELECT distinct ct.TeacherID,ct.FirstName from campus_teachers ct where tstatus='active'"
	 * ; public static final String GET_TEACHER_ATTENDENCE_REPORT =
	 * "select distinct cta.AttendenceDate,cta.AttendenceStatus, ct.FirstName from campus_teachers ct,campus_teacher_attendence cta where ct.TeacherID  like ? and cta.AttendenceDate between ? and ?"
	 * ;
	 * 
	 * // DOWNLOAD HALLTICKET public static final String
	 * GET_HALLTICKET_ADMISSIONNO_COUNT =
	 * "select count(*) from campus_student where fms_classstream_id_int=? and fms_acadamicyear_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_admissionno_var=?"
	 * ; public static final String GET_HALLTICKET_STUDENT_COUNT =
	 * "select count(*) from campus_student where fms_classstream_id_int=? and fms_acadamicyear_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fname_var like ? and student_dob_var=?"
	 * ; public static final String GET_HALLTICKET =
	 * "select cls.classstream_name_var,ccd.classdetails_name_var,exam.examid, exam.examname,exam.examdate as start_date,exam.exam_enddate,sub.subjectName, examtt.examdate,examtt.examtime,examtt.endtime from campus_examination_timetable examtt join campus_classstream cls on cls.classstream_id_int=examtt.categoryid join campus_classdetail ccd on ccd.classdetail_id_int=examtt.classid join campus_examination exam on  exam.examid=examtt.examinationid  join campus_subject sub on sub.subjectID=examtt.subjectid  where  examtt.categoryid=? and examtt.classid=? and exam.accadamicyear=? and exam.examid=?"
	 * ; public static final String GET_STUDENTNAME_LIST =
	 * "select student_id_int,student_fname_var from campus_student where student_fname_var like ? and fms_classstream_id_int like ? and classdetail_id_int like ? and classsection_id_int like ? and fms_acadamicyear_id_int like ?"
	 * ; public static final String GET_STUDENTSEARCHDETAILS_LIST =
	 * "select student_admissionno_var,student_dob_var from campus_student where student_id_int=?"
	 * ;
	 * 
	 * // Customer FeedBack public static final String INSERT_CUSTOMER_FEEDBACK
	 * =
	 * "insert into campus_customer_feedback(feedback_Id,Customer_Name,Customer_ContactNo,Customer_EmailId,Customer_Address,Customer_Rating,Customer_Comments,createtime,updatedby,updatetime) values(?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String COUNT_CUSTOMER_FEEDBACK_PHONENO =
	 * "select count(*) from campus_customer_feedback where Customer_ContactNo=?"
	 * ; public static final String COUNT_CUSTOMER_FEEDBACK_EMAIL =
	 * "select count(*) from campus_customer_feedback where Customer_EmailId=?";
	 * public static final String GET_SECTION =
	 * "select classsection_name_var from campus_classsection where classsection_id_int=?"
	 * ;
	 * 
	 * // For Student Attendance
	 * 
	 * public static final String GET_STUDENT_ATTENDENCE_REPORT_ACCYEAR =
	 * "select acadamicyear_year from campus_acadamicyear where acadamicyear_id_int=?"
	 * ; public static final String GET_STUDENT_ATTENDENCE_COUNT =
	 * "SELECT count(*) FROM campus_attendence WHERE attendencedate LIKE ?";
	 * public static final String GET_ATTENDANCE_STUDENTLIST =
	 * "select student_id_int from campus_student where classdetail_id_int=? and classsection_id_int=? order by student_admissionno_var"
	 * ; public static final String GET_ATTENDANCE_STUDENTNAME =
	 * "select concat(student_fname_var,' ',student_lname_var) as StudentName from campus_student where student_id_int=?"
	 * ; public static final String GET_ATTENDANCE_STUDENTADMISSIONNO =
	 * "select student_admissionno_var from campus_student where student_id_int=?"
	 * ; public static final String GET_ATTENDANCE_STATUSCOUNT =
	 * "select count(*) from campus_attendence where addmissionno=? and attendencedate=?"
	 * ; public static final String GET_ATTENDANCE_DATA =
	 * "select attendence from campus_attendence where addmissionno=? and attendencedate=?"
	 * ; public static final String GET_PASSWORD_BY_EMAIL =
	 * "select password from campus_teachers where email=? and isActive='Y'";
	 * public static final String GET_PARENT_PASSWORD_BY_EMAIL =
	 * "select password from campus_parents where email=? and pstatus ='active' "
	 * ; public static final String STUDENT_SEARCH_BY_STCLSE =
	 * "select student_fname_var,student_id_int from campus_student where student_status_var ='active' and fms_acadamicyear_id_int=? and classdetail_id_int like ? and classsection_id_int like ? and student_fname_var like ?"
	 * ; public static final String GET_ADMIN_PASSWORD_BY_EMAIL =
	 * "select password from campus_admin where email=? and adminstatus ='active' "
	 * ;
	 * 
	 * // teacher amendment
	 * 
	 * public static final String GET_SEARCH_TEACHER =
	 * "select TeacherID,FirstName,LastName,UserName,Qualification,MobileNo,email,DateOfBirth,DateOfJoining,Subject_specialization1,Subject_specialization2,Address,designation,image,resume,Idproof,Type,department,RoleId,gender,IsTransport,stageId from campus_teachers where TeacherID=?"
	 * ; public static final String GET_TEACHER_SUBJECT =
	 * "select subjectName from campus_subject where subjectID=?"; public static
	 * final String SEARCH_TEACHER =
	 * "select TeacherID,FirstName from campus_teachers where tstatus='active' and FirstName like ?"
	 * ; public static final String UPDATE_TEACHER_DETAILS =
	 * "UPDATE campus_teachers SET FirstName=?,LastName=?,Qualification=?,Address=?,MobileNo=?,UserName=?,email=?,Subject_specialization1=?,Subject_specialization2=?,resume=?,image=?,DateOfBirth=?,DateOfJoining=?,designation=?,Idproof=?,modifiedby=?,Type=?,department=?,RoleId=?,gender=?,IsTransport=?,stageId=? WHERE TeacherID=?"
	 * ;
	 * 
	 * public static final String GET_IMAGE_VIDEOS =
	 * "select albumname,path from campus_photos;"; public static final String
	 * GET_TEACHERREPORTDETAILS =
	 * "select TeacherID,concat(s.FirstName,' ',s.LastName)as TeacherName,s.Qualification,s.email,s.Address,s.MobileNo,s.designation,s.DateOfBirth,s.DateOfJoining ,substring(ImageName,2)as Teacherimage,des.designationName,dept.DEPT_NAME from ( SELECT *, substring(image from position('/' in image) for 25) as ImageName  FROM campus_teachers )s left outer join campus_subject t on  t.subjectID=s.Subject_specialization1,campus_designation des,campus_department dept where s.designation=des.DesignationCode and s.department=dept.DEPT_ID  and s.tstatus='active' order by length(TeacherID),TeacherID"
	 * ; public static final String GET_STUDENTREPORTDETAILS =
	 * "select s.student_doj_var,s.student_id_int,concat(s.student_fname_var,' ',s.student_lname_var) as StudentName,st.classstream_name_var,concat(c.classdetails_name_var,' - ',sec.classsection_name_var) ClassName,s.student_admissionno_var,s.student_dob_var,s.student_bloodgroup_var,s.student_fathername_var,s.student_mothername_var,s.student_street1,s.student_location,s.student_towncity,s.student_zippostcode,s.student_state,s.student_country,s.student_fathermobileno_var,s.student_mothermobileno_var,s.student_gardian_mobileno,s.student_gaurdianname_var,substring(ImageName,2)as Studentimage from ( SELECT *,substring(student_imgurl_var from position('/' in student_imgurl_var) for 25) as ImageName FROM campus_student )s join campus_classdetail c on c.classdetail_id_int=s.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=s.classsection_id_int join campus_classstream st on st.classstream_id_int=s.fms_classstream_id_int where s.fms_acadamicyear_id_int=? and s.fms_classstream_id_int like ?  and c.classdetail_id_int like ?  and sec.classsection_id_int like ? order by length(s.student_id_int),s.student_id_int,c.classdetail_id_int asc,sec.classsection_id_int asc,s.student_admissionno_var"
	 * ;
	 * 
	 * public static final String SELCT_USER_NAME =
	 * "select FirstName from campus_admin where AdminID=?";
	 * 
	 * public static final String SELCT_TEACHER_USER_NAME =
	 * "select FirstName from campus_teachers where TeacherID=?";
	 * 
	 * // principle module tasks public static final String
	 * ADMIN_USERNAME_COUNT_ =
	 * "select count(*) from campus_admin where UserName=?"; public static final
	 * String ADMIN_EMAIL_COUNT =
	 * "select count(*) from campus_admin where email=?"; public static final
	 * String ADMIN_INSERT =
	 * "insert into campus_admin(AdminID,FirstName,LastName,Qualification,address,mobileno,gender,email,createdby,createdate,adminstatus,UserName,password,Role) values(?,?,?,?,?,?,?,?,?,now(),?,?,?,?)"
	 * ; public static final String GET_ADMIN =
	 * "select AdminID,Concat(FirstName,LastName) as Name from campus_admin";
	 * public static final String VALIDATE_ADMIN_DETAILS =
	 * "select count(*) from campus_admin where FirstName=? and LastName=? and Qualification=? and address=? and gender=?"
	 * ; public static final String GET_STUDENTATTENDANCEDETAILS =
	 * "select attendence,addmissionno,monthname(attendencedate) as monthname,attendencedate from  campus_attendence where attendencedate >= ? and attendencedate <= ? and addmissionno=?"
	 * ;
	 * 
	 * public static final String GET_ADMIN_DATA =
	 * "select * from campus_admin where AdminID=?"; public static final String
	 * ADMIN_UPDATE =
	 * " update campus_admin set FirstName=?,LastName=?,Qualification=?,address=?,mobileno=?,gender=?,email=?,modifiedby=?,modifiedate=now(),adminstatus=?,UserName=?,Role=? where AdminID=?"
	 * ;
	 * 
	 * public static final String GET_CONTACT_DETAILS_COUNT =
	 * "select count(*) from campus_contactus where name=? and address=? and city=? and country=? and phoneno=? and email=?"
	 * ;
	 * 
	 * public static final String ADMIN_AMMENDMENT_USERNAME_COUNT =
	 * "select count(*) from campus_admin where UserName=? and AdminID!=?";
	 * public static final String ADMIN_AMMENDMENT_EMAIL_COUNT =
	 * "select count(*) from campus_admin where email=? and AdminID!=?"; public
	 * static final String GET_LEAVEREQUEST_DETAILS_BYADMIN =
	 * "select lq.SNO,lq.NoofLeaves,lq.ReasonForLeave,lq.StartDate,lq.EndDate,lq.LeaveStatus,ct.FirstName from campus_teachers_leave_request lq,campus_teachers ct where lq.RequestedBy=ct.TeacherID order by SNO "
	 * ; public static final String GET_LEAVEREQUEST_DETAILS_BY_ADMIN_SNO =
	 * "select lq.NoofLeaves,lq.ReasonForLeave,lq.StartDate,lq.EndDate,lq.LeaveStatus,ct.FirstName,case when lq.TotalDaysAproved is null then '0.0' else lq.TotalDaysAproved end TotalDaysAproved,case when lq.ApprovedStartDate is null then '-' else lq.ApprovedStartDate end ApprovedStartDate,case when lq.ApprovedEndDate is null then '-' else lq.ApprovedEndDate end ApprovedEndDate,lq.ApprovedBy,lq.commennts,lq.RequestedDate from campus_teachers_leave_request lq,campus_teachers ct where lq.SNO=? and lq.RequestedBy=ct.TeacherID"
	 * ; public static final String APPROVEDBY =
	 * "select Name from campus_user where id =?"; public static final String
	 * PHONENO_VALIDATE =
	 * "select count(*) from campus_admin where mobileno = ?";
	 * 
	 * public static final String STUDENT_SEARCH_WITHACCYEAR =
	 * "select student_fname_var,student_admissionno_var from campus_student where student_status_var ='Active' and  student_fname_var like ? and classdetail_id_int=? and classsection_id_int=? and fms_acadamicyear_id_int=?"
	 * ;
	 * 
	 * // CHECKING STREAM COUNT IN ALL TABLES public static final String
	 * STREAM_COUNT_IN_CAMPUS_CLASSSTREAM =
	 * "select count(*) from campus_classstream  where  classstream_id_int=?";
	 * public static final String STREAM_COUNT_IN_CAMPUS_ASSIGNMENT =
	 * "select  count(*) from campus_assignment  where  StreamID=?"; public
	 * static final String STREAM_COUNT_IN_CAMPUS_CLASSDETAIL =
	 * "select  count(*) from campus_classdetail  where  classstream_id_int=?";
	 * public static final String STREAM_COUNT_IN_CAMPUS_EXAMINATION_TIME_TABLE
	 * =
	 * "select  count(*) from campus_examination_timetable  where  categoryid=?"
	 * ; public static final String STREAM_COUNT_IN_CAMPUS_FEE_STRUCTURE =
	 * "select  count(*) from campus_fee_structure  where  StreamCode=?"; public
	 * static final String STREAM_COUNT_IN_MARKS_UPLOAD =
	 * "select  count(*) from campus_marks_upload  where  categoryid=?"; public
	 * static final String STREAM_COUNT_IN_CAMPUS_STUDENT =
	 * "select  count(*) from campus_student  where  fms_classstream_id_int=?";
	 * public static final String STREAM_COUNT_IN_CAMPUS_STUDENT_PROMOTION =
	 * "select  count(*) from campus_studentpromotion  where  studentpromotion_fromstream=? or studentpromotion_tostream=?"
	 * ; public static final String STREAM_COUNT_IN_CAMPUS_TIME_TABLE =
	 * "select  count(*) from campus_timetable  where  classstreamid=?";
	 * 
	 * // CHECKING CLASS COUNT IN ALL TABLES public static final String
	 * CLASS_COUNT_IN_CAMPUS_ASSIGNMENT =
	 * "select  count(*) from campus_assignment  where  ClassID=?"; public
	 * static final String CLASS_COUNT_IN_CAMPUS_CLASSSECTION =
	 * "select count(*) from campus_classsection  where  classdetail_id_int=?";
	 * public static final String CLASS_COUNT_IN_CAMPUS_CLASSTEACHER =
	 * "select  count(*) from campus_classteacher  where  ClassCode=?"; public
	 * static final String CLASS_COUNT_IN_CAMPUS_EXAMINATION_TIME_TABLE =
	 * "select  count(*) from campus_examination_timetable  where  classid=?";
	 * public static final String CLASS_COUNT_IN_FEE_SETUP =
	 * "select  count(*) from campus_fee_setup  where  ClassCode=?"; public
	 * static final String CLASS_COUNT_IN_CAMPUS_FEE_STRUCTURE =
	 * "select  count(*) from campus_fee_structure  where  ClassCode=?"; public
	 * static final String CLASS_COUNT_IN_CAMPUS_FEESETTINGS =
	 * "select  count(*) from campus_feesettings  where  classdetail_id_int=?";
	 * public static final String CLASS_COUNT_IN_MARKS_UPLOAD =
	 * "select  count(*) from campus_marks_upload  where  classid=?"; public
	 * static final String CLASS_COUNT_IN_CAMPUS_MEETING =
	 * "select  count(*) from campus_meeting  where  classid=?"; public static
	 * final String CLASS_COUNT_IN_CAMPUS_STUDENT =
	 * "select  count(*) from campus_student  where  classdetail_id_int=?";
	 * public static final String CLASS_COUNT_IN_CAMPUS_STUDENT_PROMOTION =
	 * "select  count(*) from campus_studentpromotion  where  studentpromotion_fromclass_var=? or studentpromotion_toclass_var=?"
	 * ; public static final String CLASS_COUNT_IN_CAMPUS_TIME_TABLE =
	 * "select  count(*) from campus_timetable  where  classid=?"; public static
	 * final String CLASS_COUNT_IN_CAMPUS_SUBJECT =
	 * "select  count(*) from campus_subject  where  classid=?"; public static
	 * final String CLASS_COUNT_IN_TEACHERSETTINGS =
	 * "select  count(*) from campus_teachersettings  where  classID=?";
	 * 
	 * // CHECKING SECTION COUNT IN ALL TABLES public static final String
	 * SECTION_COUNT_IN_CAMPUS_ASSIGNMENT =
	 * "select  count(*) from campus_assignment  where  SectionID=?"; public
	 * static final String SECTION_COUNT_IN_CAMPUS_CLASSTEACHER =
	 * "select  count(*) from campus_classteacher  where  ClassCode=?"; public
	 * static final String SECTION_COUNT_IN_MARKS_UPLOAD =
	 * "select  count(*) from campus_marks_upload  where  sectionid=?"; public
	 * static final String SECTION_COUNT_IN_MEETING =
	 * "select  count(*) from campus_meeting  where  sectionid=?"; public static
	 * final String SECTION_COUNT_IN_CAMPUS_STUDENT =
	 * "select  count(*) from campus_student  where  classsection_id_int=?";
	 * public static final String SECTION_COUNT_IN_CAMPUS_STUDENT_PROMOTION =
	 * "select  count(*) from campus_studentpromotion  where  studentpromotion_fromsection_var=? or studentpromotion_tosection_var=?"
	 * ; public static final String SECTION_COUNT_IN_CAMPUS_TIME_TABLE =
	 * "select  count(*) from campus_timetable  where  classsectionid=?"; public
	 * static final String SECTION_COUNT_IN_TEACHERSETTINGS =
	 * "select  count(*) from campus_teachersettings  where  sectionID=?";
	 * 
	 * // // public static final String CHECK_TACHER_COUNT_ =
	 * "select count(*) from campus_teachers where UserName=? and department=? and designation=? and MobileNo=? and DateOfBirth=? and DateOfJoining=? "
	 * ;
	 * 
	 * public static final String STUDENT_SEARCH_BY_SIBLING =
	 * "select case when student_lname_var is null then student_fname_var else concat(student_fname_var, ' ',student_lname_var) end StudentName,student_id_int from campus_student where student_status_var ='active' and student_fname_var like ? || student_lname_var like ?"
	 * ;
	 * 
	 * public static final String GET_QUOTA_COUNT_BEFOREDELETE =
	 * "select count(student_quota) from  campus_student where student_quota=?";
	 * 
	 * public static final String CHECK_STREAM_NAME_FOR_UPDATE =
	 * "select count(*) usercount from campus_classstream where classstream_name_var=? and classstream_id_int !=?"
	 * ; public static final String CHECK_SECTION_FOR_UPDATE =
	 * "select count(*) usercount from campus_classsection where classdetail_id_int=? and classsection_name_var=? and classsection_id_int!=?"
	 * ; public static final String GET_CLASS_ID_BY_NAME =
	 * "select classdetail_id_int from campus_classdetail where classdetails_name_var=?"
	 * ; public static final String GET_NOTICE_TITLE_COUNT =
	 * "select count(title) from campus_noticeboard where title=? and noticeid not in(?) and startdate between ? and ? and enddate between ? and ?"
	 * ;
	 * 
	 * public static final String GET_CREATE_MEATING_COUNT =
	 * "select count(*) from campus_meeting where meetingdate=? and starttime=? and endtime=? and  classid=? and sectionid=? and studentid=? "
	 * ;
	 * 
	 * public static final String GET_QUOTA_COUNT_FOR_UPDATE =
	 * "select count(*) from campus_quota where Quota_Name=? and Quota_Code!=?";
	 * 
	 * public static final String GET_TEACHER_ATTENDENCE_COUNT =
	 * "SELECT count(*) FROM campus_teacher_attendence WHERE AttendenceDate LIKE ?"
	 * ; public static final String GET_ATTENDANCE_TEACHERLIST =
	 * "select TeacherID from campus_teachers where  tstatus='active' order by length(TeacherID),TeacherID"
	 * ; public static final String GET_ATTENDANCE_TEACHERNAME =
	 * "select concat(FirstName,' ',LastName) as TeacherName from campus_teachers where TeacherID=?"
	 * ; public static final String GET_TEACHER_ATTENDANCE_COUNT =
	 * "select count(*) from campus_teacher_attendence where TeacherID=? and AttendenceDate=?"
	 * ; public static final String GET_TEACHER_ATT_DATA =
	 * "select AttendenceStatus from campus_teacher_attendence where TeacherID=? and AttendenceDate=?"
	 * ; public static final String GET_STUDENT_HOLIDAYDETAILS =
	 * "select count(HOLIDAY_DATE) from campus_holidaymaster where HOLIDAY_DATE=? and CURRENT_YEAR=?"
	 * ; public static final String GET_TEACHER_HOLIDAYDETAILS =
	 * "select count(HOLIDAY_DATE) from campus_holidaymaster where HOLIDAY_DATE=?"
	 * ;
	 * 
	 * public static final String GET_STUDENT_MARKS_LIST =
	 * " select count(*)from campus_student stud join campus_classstream strm on stud.fms_classstream_id_int = strm.classstream_id_int join campus_classdetail cldt on stud.classdetail_id_int = cldt.classdetail_id_int join campus_classsection clsc on stud.classsection_id_int = clsc.classsection_id_int join campus_marks_upload mmar on stud.fms_classstream_id_int = mmar.categoryid join campus_marks scmar on mmar.marks_upload_id=scmar.marks_upload_id and stud.student_id_int=scmar.admissionno join campus_examination exam on exam.examid=mmar.examid join campus_acadamicyear accd on exam.accadamicyear =accd.acadamicyear_id_int join campus_subject subt on subt.subjectID=mmar.subid join campus_teachersettings teach on teach.classID=mmar.classid and teach.sectionID=mmar.sectionid and teach.subjectID =mmar.subid where stud.fms_classstream_id_int=? and stud.classdetail_id_int=? and stud.classsection_id_int=? and exam.examid=? and subt.subjectID=? and teach.teacherID=? and exam.accadamicyear=?"
	 * ; // Student Registration public static final String COUNT_PARENT_EMAILS
	 * =
	 * "select count(*) from campus_student where (student_father_mailid<> '' and student_father_mailid=?) or (student_mother_mailid <> '' and student_mother_mailid =?) or (student_gardian_mailid<> '' and student_gardian_mailid=?)"
	 * ; public static final String STUDENT_DUPLICATE =
	 * "select  count(*)  from campus_student where  student_fname_var=?  and fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fathername_var=? and student_mothername_var=? and student_dob_var=? and student_doj_var=?"
	 * ; public static final String STUDENT_DUPLICATE_AMENDMENT =
	 * "select  count(*)  from campus_student where  student_fname_var=?  and fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fathername_var=? and student_mothername_var=? and student_dob_var=? and student_doj_var=? and fms_acadamicyear_id_int=? and student_id_int not in (select  student_id_int  from campus_student where student_id_int=?)"
	 * ; public static final String COUNT_PARENT_PHONENO =
	 * "select count(*) from campus_student where (student_gardian_mobileno<>'' and student_gardian_mobileno=?) or (student_fathermobileno_var<>'' and student_fathermobileno_var=?) or (student_mothermobileno_var<>'' and student_mothermobileno_var=?)"
	 * ;
	 * 
	 * public static final String STUDENT_DOB =
	 * "select student_doj_var from campus_student where student_admissionno_var=?"
	 * ;
	 * 
	 * public static final String CHECK_CLASS_DETAIL_FOR_UPDATE =
	 * "select count(*) usercount from campus_classdetail where classdetails_name_var=?  and classdetail_id_int!=?"
	 * ;
	 * 
	 * public static final String GET_ADMIN_PHONE_DIRECTORY =
	 * "select * from campus_admin where FirstName like ?"; public static final
	 * String CONFIRM_LEAVE_REQUEST_HOLD =
	 * "update campus_teachers_leave_request set LeaveStatus=?,ApprovedBy=?,AprovedDate=?,TotalDaysAproved=?,commennts=?  where SNO=?"
	 * ; public static final String CONFIRM_LEAVE_REQUEST_REJECT =
	 * "update campus_teachers_leave_request set LeaveStatus=?,ApprovedBy=?,AprovedDate=?,TotalDaysAproved=?,commennts=? where SNO=?"
	 * ;
	 * 
	 * public static final String GET_STUDENT_CASTE =
	 * "select caste_id,caste_name from campus_caste"; public static final
	 * String STUDENT_DUPLICATE_CHECK_ADDTIME =
	 * "select  count(*)  from campus_student where  student_fname_var=?  and fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fathername_var=? and student_mothername_var=? and student_dob_var=? and student_doj_var=? and fms_acadamicyear_id_int=?"
	 * ; public static final String STUDENT_DUPLICATE_CHECK_UPDATETIME =
	 * "select  count(*)  from campus_student where  student_fname_var=?  and fms_classstream_id_int=? and classdetail_id_int=? and classsection_id_int=? and student_fathername_var=? and student_mothername_var=? and student_dob_var=? and student_doj_var=? and fms_acadamicyear_id_int=? and student_id_int !=?"
	 * ; public static final String CHECK_ROLL_NUMBER =
	 * "select count(*) from campus_student where (student_admissionno_var<> '' and student_admissionno_var=?)"
	 * ;
	 * 
	 * public static final String GET_INACTIVE_ENQUIRY_DETAILS =
	 * "select case when enq.lname is null then enq.fname else concat(enq.fname,' ',enq.lname)end StudentName,enq.application_no,cd.classdetails_name_var ClassName,enq.enquiry_id,enq.classstream_id,enq.acadamicyear_id,enq.classdetail_id,enq.fname,enq.lname,enq.dob,enq.gender,enq.age,enq.expec_doj,enq.religion,enq.physically_challenged,enq.contact_type,enq.contact_name,enq.contact_mobileno,enq.contact_mailid,enq.street1,enq.street2,enq.location,enq.towncity,enq.zippostcode,enq.state,enq.country,isJoined,enq.studentId,enq.interaction_Status,enq.interaction_date,enq.registration_Status,enq.registration_date,enq.enquiredby from campus_student_enquiry enq  join campus_classstream cls on cls.classstream_id_int=enq.classstream_id  join campus_classdetail cd on cd.classdetail_id_int=enq.classdetail_id join campus_acadamicyear acc on acc.acadamicyear_id_int=enq.acadamicyear_id order by enq.create_user asc"
	 * ; public static final String GET_SELECTED_ENQUIRY_DETAILS =
	 * "select enq.enquiry_id,enq.application_no,enq.fname,enq.lname,enq.acadamicyear_id,enq.dob,enq.gender,enq.age,enq.expec_doj,enq.religion,enq.physically_challenged,enq.contact_type,enq.contact_name,enq.contact_mobileno,enq.contact_mailid,enq.street1,enq.street2,enq.location,enq.towncity,enq.zippostcode,enq.state,enq.country,enq.classstream_id,enq.classdetail_id from campus_student_enquiry enq  where enq.isJoined='N'and enq.enquiry_id=?"
	 * ; public static final String UPDATE_ENQUIRY_DETAILS =
	 * "update campus_student_enquiry set isJoined=?,studentId=?,modified_user=?,modified_date=? where enquiry_id=?"
	 * ; public static final String RE_ADMISSION_STUDENT_SEARCH =
	 * "select student_fname_var,student_id_int from campus_student where fms_acadamicyear_id_int =? and classdetail_id_int like ? and classsection_id_int like ? and student_fname_var like ?"
	 * ; public static final String RE_ADMISSION_STUDENT_DETAIL_BY_SEARCH_TERM =
	 * "select st.isRTE,st.isConcession,st.student_fname_var,st.student_lname_var,st.student_id_int,st.student_rollno,st.student_admissionno_var,student_application_no,isTransport,isHostel,st.fms_classstream_id_int,st.fms_acadamicyear_id_int,st.classdetail_id_int,st.classsection_id_int,st.student_regno_var,st.student_dob_var,st.student_gender_var,st.student_bloodgroup_var,st.student_age_int,st.student_imgurl_var,st.student_doj_var,st.student_religion_var,st.student_nationality_var,st.student_scholorship_var,st.student_promotionstatus,st.student_physicallychallenged,st.student_identificationmarks_var,st.student_grade,st.student_fathername_var,st.student_mothername_var,st.student_gaurdianname_var,st.student_fatherqualification_var,st.student_fathermobileno_var,st.student_mothermobileno_var,st.student_motherqualification_var,st.student_street1,st.student_street2,st.student_location,st.student_towncity,st.student_zippostcode,st.student_state,st.student_country,st.student_siblingname_var,st.student_siblingclass_var,st.student_status_var,st.student_prehistory_var,st.student_remarks_var,st.student_quota,st.student_father_mailid,st.student_mother_mailid,st.student_gardian_mailid,st.student_gardian_mobileno,st.student_caste,par.ParentID,cpr.relationship from campus_student st join campus_classstream strm on strm.classstream_id_int=st.fms_classstream_id_int join campus_classdetail cd on cd.classdetail_id_int=st.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=st.classsection_id_int join campus_acadamicyear acc on acc.acadamicyear_id_int=st.fms_acadamicyear_id_int join campus_quota qt on qt.Quota_Code=st.student_quota join campus_parentchildrelation cpr on cpr.stu_addmissionNo=st.student_admissionno_var join campus_parents par on cpr.parentid=par.ParentID where  st.student_id_int=?"
	 * ; public static final String INSERTING_ENQUIRY_VALUES =
	 * "insert into campus_student_enquiry(enquiry_id, application_no, classstream_id, acadamicyear_id, classdetail_id, fname, lname, dob, gender, age, expec_doj, religion, physically_challenged, contact_type, contact_name, contact_mobileno, contact_mailid, enquiredby, street1, street2, location, towncity, zippostcode, state, country, create_user, create_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())"
	 * ; public static final String DUBNLICATE_CHECKING =
	 * "select count(*) from campus_student_enquiry where fname = ? and dob = ? and gender = ? and physically_challenged = ? and classstream_id = ?  and classdetail_id= ?  and acadamicyear_id = ? and contact_type = ? and contact_name = ? and contact_mobileno = ?"
	 * ; public static final String VALIDATION_MOBILE_NO =
	 * "select count(*) from campus_student_enquiry where contact_mobileno = ?";
	 * public static final String GET_ENQUIRY_DETAILS_BYSEARCH =
	 * "select case when enq.lname is null then enq.fname else concat(enq.fname,' ',enq.lname)end StudentName,cd.classdetails_name_var ClassName ,enq.enquiry_id,enq.classstream_id,enq.acadamicyear_id,enq.classdetail_id,enq.fname,enq.lname,enq.dob,enq.gender,enq.age,enq.expec_doj,enq.religion,enq.physically_challenged,enq.contact_type,enq.contact_name,enq.contact_mobileno,enq.contact_mailid,enq.street1,enq.street2,enq.location,enq.towncity,enq.zippostcode,enq.state,enq.country,isJoined,enq.studentId,enq.interaction_Status,enq.registration_Status,enq.enquiredby from campus_student_enquiry enq  join campus_classstream cls on cls.classstream_id_int=enq.classstream_id  join campus_classdetail cd on cd.classdetail_id_int=enq.classdetail_id  join campus_acadamicyear acc on acc.acadamicyear_id_int=enq.acadamicyear_id where enq.create_date like ? and concat(enq.fname,'',enq.lname) like ? and enq.interaction_Status like ? and enq.registration_Status like ? order by enq.create_user asc"
	 * ; public static final String ALL_TEACHER_DETAILS =
	 * "select concat(t.FirstName,'',t.LastName) as teacherName,t.Qualification,d.designationName,t.MobileNo,t.TeacherID,t.email from campus_teachers t,campus_designation d where t.designation=d.DesignationCode and t.tstatus='active' "
	 * ; public static final String TEACHER_DETAILS_BY_SEARCHTERM =
	 * "select case when t.LastName is null then t.FirstName else concat(t.FirstName,'',t.LastName) end teacherName,t.Qualification, des.designationName designation,t.MobileNo,t.TeacherID,t.email from campus_teachers t join campus_designation des on des.DesignationCode=t.designation where t.Qualification like ? or t.designation like ? or t.MobileNo like ? or t.email like ?  or concat(t.FirstName,'',t.LastName) like ? or concat(t.FirstName,' ',t.LastName) like ? and t.tstatus='active'"
	 * ; public static final String TEACHER_DETAILS_BY_ID =
	 * "select te.FirstName,te.image,te.Qualification,te.designation,te.MobileNo,te.TeacherID,te.email,te.DateOfBirth,te.DateOfJoining,te.Type,te.Address,su.subjectName,des.designationName,dept.DEPT_NAME from campus_teachers te left outer join campus_subject su on su.subjectID=te.Subject_specialization1,campus_designation des,campus_department dept where te.TeacherID =? and te.designation=des.DesignationCode and te.department=dept.DEPT_ID"
	 * ; public static final String UPDATE_ENQUIRY_STUDENT_DETAILS =
	 * "update campus_student_enquiry set classstream_id = ?, acadamicyear_id = ?,classdetail_id = ?,fname = ?, lname = ?,dob = ?,gender = ?, age = ?, expec_doj = ?, religion = ?, physically_challenged = ?, contact_type= ?, contact_name= ?, contact_mobileno= ?, contact_mailid= ?, street1= ?, street2= ?, location= ?, towncity= ?, zippostcode= ?, state= ?, country= ?, modified_user= ?, modified_date= ?, interaction_Status= ?, registration_Status= ?, application_no = ?, interaction_date= ?, registration_date= ?  where enquiry_id = ?"
	 * ;
	 * 
	 * public static final String VALIDATION_APPLICATION_NO =
	 * "select count(*) from campus_student_enquiry where application_no = ?";
	 * 
	 * public static final String CHECK_APPLICATION_NUMBER =
	 * "select count(*) from campus_student where (student_application_no<> '' and student_application_no=?)"
	 * ; public static final String CHECKING_STUDENT_VALIDATION =
	 * "select count(*) from campus_student_enquiry where fname = ? and dob = ? and gender = ? and physically_challenged = ? and classstream_id = ?  and classdetail_id= ?  and acadamicyear_id = ? and contact_type = ? and contact_name = ? and contact_mobileno = ? and application_no=? and enquiry_id != ?"
	 * ; public static final String VALIDATION_PHONE_NO =
	 * "select count(*) from campus_student_enquiry where contact_mobileno = ? and enquiry_id != ?"
	 * ;
	 * 
	 * // holiday master by preethi
	 * 
	 * public static final String GET_DEPARTMENTS =
	 * "select * from campus_department"; public static final String
	 * GET_HOLIDAY_DATES_WITH_DEPT =
	 * "select HOLIDAY_DATE from campus_holidaymaster"; public static final
	 * String SAVE_HOLIDAYS_WITH_DEPT =
	 * "insert into campus_holidaymaster(CURRENT_YEAR,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,CREATEDDATE ,CREATEDBY ) values(?,?,?,?,?,?) "
	 * ; public static final String DISPLAY_HOLIDAYS_WITH_DEPT =
	 * "select distinct chm.WEEKDAY,chm.HOLIDAY_NAME,chm.HOLIDAY_DATE from campus_holidaymaster chm where chm.CURRENT_YEAR=? and DEPT_ID=?"
	 * ; public static final String DEPARTMENT_NAME =
	 * "select DEPT_NAME  from campus_department where DEPT_ID  =?"; public
	 * static final String UPDATE_HOLIDAYS_WITH_DEPT =
	 * "update campus_holidaymaster set WEEKDAY=?,HOLIDAY_NAME=?,CURRENT_YEAR=? ,MODIFIED_DATE=?, MODIFIED_BY=? ,DEPT_ID=? where HOLIDAY_DATE=? "
	 * ; public static final String DELETE_HOLIDAYS_WITH_DEPT =
	 * " delete from campus_holidaymaster where HOLIDAY_DATE=? and DEPT_ID=?";
	 * 
	 * // Designation Master Created by Swathi
	 * 
	 * public static final String INSERT_DESIGNATION_DETAILS =
	 * "insert into campus_designation (DesignationCode,designationName,description,CreatedBy,isActive) values(?,?,?,?,?)"
	 * ; public static final String GET_DESIGNATION_DETAILS =
	 * "select DesignationCode,designationName,description,createdate,CreatedBy from campus_designation where isActive='Y' order by designationName asc"
	 * ; public static final String VALIDE_DES_NAME =
	 * "select count(*) desname from campus_designation where designationName=? and isActive='Y'"
	 * ; public static final String EDIT_DESIGNATION =
	 * "select DesignationCode,designationName,description,createdate,CreatedBy from campus_designation where DesignationCode=?"
	 * ; public static final String DELETE_DESIGNATION =
	 * "update campus_designation set isActive=? where DesignationCode =?";
	 * public static final String VALIDATE_DESID =
	 * "select count(*) desname from campus_designation where DesignationCode=?"
	 * ; public static final String UPDATE_DESIGNATION =
	 * "update campus_designation set designationName= ?,description=?,isActive=? where DesignationCode =?"
	 * ; public static final String CHECK_DESIGNATION_MAP =
	 * "select count(*) desname from campus_teachers where designation=?";
	 * public static final String VALIDATE_DESIGNATION_UPDATE =
	 * "select count(*) desname from campus_designation where designationName=? and DesignationCode!=? and isActive='Y'"
	 * ; public static final String GET_SINGLE_DESIGNATION =
	 * "select designationName from campus_designation where DesignationCode=?";
	 * 
	 * // Department Master created by Swathi public static final String
	 * INSERT_DEPARTMENT_DETAILS =
	 * "insert into campus_department (DEPT_ID,DEPT_NAME,DESCRIPTION,createdby,isActive) values(?,?,?,?,?)"
	 * ; public static final String GET_DEPARTMENT_DETAILS =
	 * "select DEPT_ID,DEPT_NAME,DESCRIPTION,CREATE_DATE,createdby from campus_department where isActive='Y' order by DEPT_NAME asc"
	 * ; public static final String VALIDE_DEPT_NAME =
	 * "select count(*) deptname from campus_department where DEPT_NAME=? and isActive='Y'"
	 * ; public static final String EDIT_DEPARTMENT =
	 * "select DEPT_ID,DEPT_NAME,DESCRIPTION,CREATE_DATE,createdby from campus_department where DEPT_ID=?"
	 * ; public static final String DELETE_DEPARTMENT =
	 * "update campus_department set isActive=? where DEPT_ID =?"; public static
	 * final String VALIDATE_DEPTID =
	 * "select count(*) deptname from campus_department where DEPT_ID=?"; public
	 * static final String UPDATE_DEPARTMENT =
	 * "update campus_department set DEPT_NAME= ?,DESCRIPTION=?,isActive=?,UpdatedBy=?,UpdatedTime=now() where DEPT_ID =?"
	 * ; public static final String CHECK_DEPARTMENT_MAP =
	 * "select count(*) deptname from campus_teachers where department=?";
	 * public static final String VALIDATE_DEPARTMENT_UPDATE =
	 * "select count(*) deptname from campus_department where DEPT_NAME=? and DEPT_ID!=? and isActive='Y'"
	 * ; public static final String GET_SINGLE_DEPARTMENT =
	 * "select DEPT_NAME from campus_department where DEPT_ID=?";
	 * 
	 * // Circulars Master created by swathi public static final String
	 * INSERT_CIRCULAR =
	 * "insert into campus_circular(circularID,circularName,date,description,type,priority,createdby) values(?,?,?,?,?,?,?)"
	 * ; public static final String GET_ALL_CIRCULARS =
	 * "select * from campus_circular"; public static final String
	 * UPDATE_CIRCULAR =
	 * "update campus_circular set circularName=?,date=?,description=?,type=?,priority=?,updatedby=?,updatedtime=now() where circularID =?"
	 * ; public static final String DELETE_CIRCULAR =
	 * "delete from campus_circular where circularID=? "; public static final
	 * String CHECK_CIRCULAR =
	 * "select count(*) crname from campus_circular where circularName=? and circularID!=?"
	 * ;
	 * 
	 * // teacher master by seshu
	 * 
	 * public static final String SAVE_TEACHER =
	 * "insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,Address,MobileNo,UserName,email,Subject_specialization1,Subject_specialization2,resume,image,DateOfBirth,DateOfJoining,designation,Idproof,Type,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_ALL_TEACHER =
	 * "Select * from campus_teachers where isActive = 'Y'";
	 * 
	 * // post Attendance by preethi
	 * 
	 * public static final String POST_ATTENDANCE =
	 * "insert into campus_teacher_attendence(TeacherID,AttendenceDate,AttendenceStatus,isApproved,CreateDate,UpdateDate,CreatedBy,UpdatedBy) values(?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_ATTENDANCE_DATES =
	 * "select AttendenceDate from campus_teacher_attendence"; public static
	 * final String GET_CREATEDBY =
	 * "select UserName from campus_teachers where TeacherID=?";
	 * 
	 * // TeacherAttendance in Principal by Satish public static final String
	 * TEACHER_ATTENDANCE_STATUS =
	 * "select count(HOLIDAY_DATE) TotalHolidays, ta.AttendenceDate,count(td.TeacherID) as TotalTeachers,sum(case when ta.AttendenceStatus='Present' then 1 else 0 end)TotalPresent,sum(case when ta.AttendenceStatus='Absent' then 1 else 0 end)TotalAbsent,sum(case when ta.AttendenceStatus='Leave' then 1 else 0 end) TotalLeaves from campus_teacher_attendence ta,campus_teachers td,campus_holidaymaster hol where hol.DEPT_ID=td.department and ta.TeacherID =td.TeacherID and ta.isApproved='N' group by ta.AttendenceDate order by ta.AttendenceDate asc"
	 * ; public static final String GET_TEACHER_ATTENDANCE_BY_DATE =
	 * "select ta.AttendenceDate,ta.AttendenceStatus,ta.TeacherID,td.FirstName from  campus_teachers td,campus_teacher_attendence ta where td.TeacherID=ta.TeacherID and ta.isApproved='N' and ta.AttendenceDate=?"
	 * ; public static final String TEACHER_ATTENDANCE_APPROVED_BY_PRINCIPAL =
	 * "update campus_teacher_attendence set AttendenceDate=?,AttendenceStatus=?,isApproved=? where TeacherID=? and AttendenceDate=?"
	 * ;
	 * 
	 * // Remainder Master created by Swathi public static final String
	 * INSERT_REMAINDER =
	 * "insert into campus_remainder(remainder_id,remainder_title,description,remainder_to,teacher,parents,backoffice,created_by) values(?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_ALL_REMAINDERS =
	 * "select * from campus_remainder"; public static final String
	 * UPDTAE_REMAINDER =
	 * "update campus_remainder set remainder_title=?,description=?,remainder_to=?,teacher=?,parents=?,backoffice=?,updated_by=?,updated_time=now() where remainder_id =?"
	 * ; public static final String DELETE_REMAINDER =
	 * "delete from campus_remainder where remainder_id=?"; public static final
	 * String CHECK_REMAINDER =
	 * "select count(*) reminder from campus_remainder where remainder_title=? and remainder_id!=?"
	 * ;
	 * 
	 * // Class Teacher
	 * 
	 * public static final String GET_CLASS_SECTION =
	 * "select cd.classdetail_id_int,cd.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var  from campus_classdetail cd join campus_classsection cs  on cd.classdetail_id_int=cs.classdetail_id_int join campus_classstream cst on cst.classstream_id_int=cd.classstream_id_int order by cst.classstream_id_int ,length(cs.classdetail_id_int),cs.classdetail_id_int,length(cs.classsection_id_int),cs.classsection_id_int asc"
	 * ; public static final String GET_CLASSTEACHER_DETAILS_FROM_CLASS =
	 * "select distinct ct.TeacherID,ct.FirstName from campus_classteacher cts,campus_teachers ct where cts.ClassCode=? and cts.SectionCode=? and ct.TeacherID=cts.TeacherCode"
	 * ; public static final String GET_ALL_TEACHERS =
	 * "select * from campus_teachers where isActive='Y' and tstatus='active' and Type !='Co-Ordinators'"
	 * ; public static final String UPDATE_CLASSTEACHER_MAPPING =
	 * "update campus_classteacher set TeacherCode=?where ClassCode=? and SectionCode=?"
	 * ; public static final String STORE_CLASSTEACHER_MAPPING =
	 * "insert into campus_classteacher (CTCode,ClassCode,TeacherCode,SectionCode,CreateUser) values (?,?,?,?,?)"
	 * ; public static final String CHECK_CLASSTEACHER =
	 * "select count(*) classcode from campus_classteacher where ClassCode=? and SectionCode=?"
	 * ;
	 * 
	 * // Class Coordinators
	 * 
	 * public static final String GET_COORDINATORS =
	 * "select distinct ct.TeacherID,ct.FirstName from campus_classcoordinators cts,campus_teachers ct where cts.ClassId=? and ct.TeacherID=cts.CoOrdinatorId and ct.Type='Co-Ordinators'"
	 * ; public static final String GET_COORDINATORS_DETAILS =
	 * "select TeacherID,FirstName,LastName from campus_teachers where isActive='Y' and tstatus='active' and Type='Co-Ordinators'"
	 * ; public static final String UPDATE_COORDINATORS =
	 * "update campus_classcoordinators set CoOrdinatorId=?,UpdatedBy=?,UpdateTime=? where ClassId=?"
	 * ; public static final String STORE_COORDINATORS =
	 * "insert into campus_classcoordinators (ClassCoOrdinatorId,ClassId,CoOrdinatorId,CreatedBy) values (?,?,?,?)"
	 * ; public static final String CHECK_COORDINATORS =
	 * "select count(*) classcode from campus_classcoordinators where ClassId=?"
	 * ; // Role master public static final String GET_ROLES =
	 * "select RoleCode,RoleName,Description from campus_role"; public static
	 * final String ADD_ROLE =
	 * "insert into campus_role(RoleCode,RoleName,Description,createdby) values(?,?,?,?)"
	 * ; public static final String VALIDATE_ROLE_NAME =
	 * "select count(*) RoleName from campus_role where RoleName=?"; public
	 * static final String UPDATE_ROLE =
	 * "update campus_role set RoleName=?,Description=?,modifiedby=?,modifiedtime=? where RoleCode=?"
	 * ; public static final String CHECK_ROLE_USER_MAPPING =
	 * "select count(*) from campus_permissions_mapping where roleCode=?";
	 * public static final String CHECK_ROLE_USER_ADMIN_CREATION =
	 * "select count(*) from campus_admin where Role=?"; public static final
	 * String DELETE_ROLE = "delete from campus_role where RoleCode=?"; public
	 * static final String UPDATE_ROLES =
	 * "select * from campus_role where RoleCode=?"; public static final String
	 * VALIDATE_ROLE_CODE =
	 * "select count(*) RoleCode from campus_role where RoleCode=?"; public
	 * static final String VALIDATE_ROLE_NAME_UPDATE =
	 * "select count(*) RoleName from campus_role where RoleName=? and RoleCode!=? "
	 * ;
	 * 
	 * // User Role Permission public static final String GET_USERS =
	 * "select u.username ,u.password, u.id,r.RoleName ,u.RoleCode,u.MobileNumber,u.EmailId from campus_role r,campus_user u where u.RoleCode =r.RoleCode"
	 * ; public static final String GET_PERMISSION =
	 * "select PermissionCode,permission,shortName from campus_permissions";
	 * public static final String GET_PERMISSION_BY_ROLE =
	 * "select permissionCode,isApplicable from campus_role_permissions_mapping where roleCode=?"
	 * ; public static final String INSERT_ROLE_PERMISSION_MAPPING =
	 * "insert into campus_role_permissions_mapping(permissionCode,roleCode,shortName,isApplicable) values(?,?,?,?)"
	 * ; public static final String DELETE_ROLE_PERMISSION_MAPPING =
	 * "delete from campus_role_permissions_mapping where roleCode like ?";
	 * public static final String USERDETAILS =
	 * "select rpm.shortName,rpm.isApplicable from campus_role_permissions_mapping rpm ,campus_permissions cp where cp.PermissionCode=rpm.permissionCode and rpm.roleCode=?"
	 * ; public static final String GET_USER_ROLE =
	 * "select Role from campus_admin where username=? and password=?";
	 * 
	 * // check password
	 * 
	 * public static final String CHECK_PRINCIPAL_PASSWORD =
	 * "select count(*) from  campus_user where id = ? and password= ?"; public
	 * static final String CHECK_ADMIN_PASSWORD =
	 * "select count(*) from  campus_admin where AdminID = ? and password= ?";
	 * public static final String CHECK_PERENTS_PASSWORD =
	 * "select count(*) from  campus_parents where ParentID = ? and password= ?"
	 * ; public static final String CHECK_TEACHERS_PASSWORD =
	 * "select count(*) from  campus_teachers where TeacherID = ? and password= ?"
	 * ;
	 * 
	 * // change password
	 * 
	 * public static final String UPDATE_PRINCIPAL_PASSWORD =
	 * "update  campus_user set password= ? where id = ?"; public static final
	 * String UPDATE_ADMIN_PASSWORD =
	 * "update  campus_admin set password= ? where AdminID= ? "; public static
	 * final String UPDATE_PERENTS_PASSWORD =
	 * "update  campus_parents set password= ? where ParentID= ?"; public static
	 * final String UPDATE_TEACHERS_PASSWORD =
	 * "update  campus_teachers set password= ? where TeacherID= ?";
	 * 
	 * public static final String GET_ALL_CLASSES_BY_TEACHER =
	 * "select distinct cd.classdetails_name_var,cd.classdetail_id_int from campus_classdetail cd,campus_teachersettings ts where ts.classID=cd.classdetail_id_int and ts.teacherID=?"
	 * ; public static final String GET_SECTION_BY_CLASS_TEACHER =
	 * "select distinct cs.classsection_id_int,cs.classsection_name_var from campus_classsection cs,campus_teachersettings ts where ts.classID=cs.classdetail_id_int and ts.sectionID=cs.classsection_id_int and ts.classID=? and ts.teacherID=?"
	 * ; public static final String GET_SUBJECT_BY_CLASS_SECTION_TEACHER =
	 * "select s.subjectID,s.subjectName from campus_subject s,campus_teachersettings ts where ts.subjectID=s.subjectID and ts.classID=? and  ts.sectionID=? and  ts.teacherID=?"
	 * ; // Student Attendance public static final String
	 * STUDENTATTENDANCEBYCOORDINATOR =
	 * "select cd.classdetail_id_int,cd.classdetails_name_var from campus_classdetail cd,campus_classcoordinators cc where cd.classdetail_id_int=cc.ClassId and ClassCoOrdinatorId=?"
	 * ; public static final String GET_STUDENTATTENDANCEBYCLASSCODE =
	 * "select distinct count(cst.classsection_id_int) as studentCount,cs.classsection_id_int,cs.classsection_name_var,cc.classdetail_id_int,cc.classdetails_name_var from campus_classsection cs,campus_classdetail cc,campus_student cst where cst.classsection_id_int=cs.classsection_id_int and cst.classdetail_id_int=? GROUP BY cst.classsection_id_int,cst.student_id_int"
	 * ; // Teacher Attedance chiru public static final String
	 * GET_TEACHERS_NAMES_WITH_STATUS =
	 * "select att.TeacherID,case when LastName is null then tea.FirstName else concat(tea.FirstName,' ',tea.LastName) end TeacherName,att.AttendenceStatus from campus_teacher_attendence att, campus_teachers tea where att.TeacherID = tea.TeacherID and AttendenceDate=? order by TeacherName"
	 * ; public static final String GET_TEACHERS_NAMES =
	 * "select TeacherID, case when LastName is null then FirstName else concat(FirstName,' ',LastName) end TeacherName from campus_teachers order by TeacherName"
	 * ; public static final String UPDATE_TEACHER_ATTENDANCE_DETAILS =
	 * "update campus_teacher_attendence set AttendenceStatus = ?, UpdateDate=?, UpdatedBy=? where TeacherID=? and AttendenceDate=?"
	 * ; public static final String GET_TEACHERS_BASED_ON_DATE =
	 * "select TeacherID from campus_teacher_attendence where AttendenceDate=?";
	 * public static final String INSERT_TEACHER_ATTENDANCE_DETAILS =
	 * "insert into campus_teacher_attendence(TeacherID,AttendenceDate,AttendenceStatus,isApproved,CreateDate,CreatedBy)values(?,?,?,'N',?,?);"
	 * ; // Co-Ordinator Module
	 * 
	 * public static final String ALL_CLASS_NAME_COORDINATOR =
	 * "select distinct(clas.classdetails_name_var), clas.classdetail_id_int,sec.classsection_id_int,sec.classsection_name_var from campus_teachers teach join campus_classcoordinators cco on cco.CoOrdinatorId = teach.TeacherID join campus_classdetail clas on clas.classdetail_id_int= cco.ClassId left outer join campus_classsection sec on sec.classdetail_id_int = cco.ClassId  join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where cco.CoOrdinatorId=?"
	 * ; public static final String GET_CLASS_NAME_BY_COORDINATOR =
	 * "select ClassId,classdetails_name_var from campus_classcoordinators co,campus_classdetail cd where co.ClassId=cd.classdetail_id_int and CoOrdinatorId=?"
	 * ; public static final String COORDINATOR_CHILD_INFO_STREAM =
	 * "select distinct strm.classstream_id_int,strm.classstream_name_var from campus_classcoordinators cco join campus_classdetail clas on clas.classdetail_id_int= cco.ClassId join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where cco.CoOrdinatorId=?"
	 * ; public static final String COORDINATOR_CHILD_INFO_CLASS =
	 * "select distinct clas.classdetail_id_int, clas.classdetails_name_var from campus_teachers teach join campus_classcoordinators cco on teach.TeacherID = cco.CoOrdinatorId join campus_classdetail clas on clas.classdetail_id_int= cco.ClassId join campus_classstream strm on strm.classstream_id_int=clas.classstream_id_int where cco.CoOrdinatorId=?  and strm.classstream_id_int=?"
	 * ; public static final String COORDINATOR_CHILD_INFO_SECTION =
	 * "select distinct sec.classsection_id_int,sec.classsection_name_var from campus_classsection sec where sec.classdetail_id_int=?"
	 * ;
	 * 
	 * // StudentCoordinator;;;;;;;;;;;;
	 * 
	 * public static final String GET_ATTENDANCE_BY_SELECT_DATE =
	 * "select count(*) from campus_daily_studentattendance where attendance_date=?"
	 * ; public static final String GET_STUDENTATTENDANCEAPPROVEDBYCO_ORDINATOR
	 * =
	 * "insert into campus_daily_studentattendance(attendance_date,classid,sectionid,totalabsent,studentid,postedby,postedtime) values(?,?,?,?,?,?,?);"
	 * ; public static final String
	 * UPDATE_STUDENTATTENDANCEAPPROVEDBYCO_ORDINATOR =
	 * "update campus_daily_studentattendance set classid=?,sectionid=?,totalabsent=?,studentid=?,updatedby=?,updateddate=? where attendance_date=?;"
	 * ; public static final String GET_ATTENDANCE_BY_COORDINATOR =
	 * "select concat(co.ClassId,',',cs.classsection_id_int)ClassId,co.CoOrdinatorId,concat(tea.FirstName,' ',tea.LastName)TeacherName,cd.classdetails_name_var ClassName, cs.classsection_name_var SectionName from campus_classcoordinators co join campus_teachers tea on tea.TeacherID=co.CoOrdinatorId join campus_classdetail cd on cd.classdetail_id_int=co.ClassId left join campus_classsection cs on cs.classdetail_id_int=cd.classdetail_id_int where co.CoOrdinatorId=?"
	 * ; public static final String COUNT_BY_ATTENDANCEDATE =
	 * "select count(*) from campus_daily_studentattendance where classid=? and sectionid=? and attendance_date=?"
	 * ;
	 * 
	 * public static final String GET_DETAILS_BY_CLASSCODE =
	 * "select cd.classdetails_name_var,cs.classsection_name_var,cd.classdetail_id_int,cs.classsection_id_int,count(st.student_id_int)StudentCount from campus_student st  join campus_classsection cs on cs.classsection_id_int=st.classsection_id_int join campus_classdetail cd on cd.classdetail_id_int=st.classdetail_id_int where st.classdetail_id_int=? and st.classsection_id_int=? group by st.classsection_id_int"
	 * ; public static final String GET_DETAILS_BY_ATTENDANCEDATE =
	 * "select cd.classdetails_name_var,cs.classsection_name_var,cd.classdetail_id_int,cs.classsection_id_int,count(st.student_id_int)StudentCount from campus_student st  join campus_classsection cs on cs.classsection_id_int=st.classsection_id_int join campus_classdetail cd on cd.classdetail_id_int=st.classdetail_id_int where st.classdetail_id_int=? and st.classsection_id_int=? group by st.classsection_id_int"
	 * ;
	 * 
	 * public static final String GET_TOTAL_ABSENT_BY_ATTENDANCEDATE =
	 * "select da.totalabsent from campus_daily_studentattendance da where da.classid=? and da.sectionid=? and da.attendance_date=?"
	 * ;
	 * 
	 * // leave request details by preethi
	 * 
	 * public static final String GET_LEAVE_DETAILS =
	 * "select lr.SNO,t.FirstName,lr.NoofLeaves,lr.ReasonForLeave, lr.StartDate,lr.EndDate ,lr.RequestedDate,lr.LeaveStatus from campus_teachers_leave_request lr ,campus_teachers t where lr.RequestedBy=t.TeacherID "
	 * ; public static final String GET_APPOVED_DATE =
	 * "select AprovedDate from campus_teachers_leave_request where SNO=?";
	 * 
	 * // Concession Details by preethi
	 * 
	 * public static final String CHECK_CONCESSION_NAME =
	 * "select count(*) usercount from campus_fee_concessiondetails where concessionname=?"
	 * ; public static final String INSERT_CONCESSION_DETAILS =
	 * "INSERT INTO campus_fee_concessiondetails (concessionid,concessionname,percentage, description, createdby,createdtime,updatedby,updatedtime) VALUES (?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_CONCESSION_DETAILS =
	 * "select * from campus_fee_concessiondetails order by concessionname";
	 * public static final String UPDATE_CONCESSION_DETAILS =
	 * "UPDATE campus_fee_concessiondetails SET concessionname=?,description= ? ,percentage=? ,updatedby= ?, updatedtime = ?  WHERE concessionid =?"
	 * ; public static final String DELETE_CONCESSION =
	 * "delete from campus_fee_concessiondetails where concessionid=?"; public
	 * static final String CHECK_CONCESSION_NAME_FOR_UPDATE =
	 * "select count(*) usercount from campus_fee_concessiondetails where concessionname=? and concessionid !=?"
	 * ;
	 * 
	 * // FEE TERM MASTER BY CHIRU
	 * 
	 * public static final String GETTING_FEE_TERM_DETAILS =
	 * "select termid,termname,startdate,enddate,description from campus_fee_termdetails"
	 * ;
	 * 
	 * // Relieving Order Report by preethi public static final String
	 * GET_RELIEVING_DETAILS =
	 * "select t.Address, t.DateOfBirth,d.DEPT_NAME  from campus_teachers as t,campus_department as d where TeacherID=? and d.DEPT_ID=t.department"
	 * ; public static final String INSERT_REPORT_AUDIT =
	 * "insert into campus_reportsaudit(rep_generatorid,rep_receiverid,rep_received_date,description,issuedby,issueddate) values(?,?,?,?,?,?)"
	 * ; public static final String SAVE_TRANSFER_CERTIFICATE_DETAILS =
	 * "insert into campus_reportsaudit(rep_generatorid,rep_receiverid,rep_received_date,description,issuedby,issueddate)value(?,?,?,?,?,?)"
	 * ; public static final String SAVE_BONAFIED_CERTIFICATE_DETAILS =
	 * "insert into campus_reportsaudit(rep_generatorid,rep_receiverid,rep_received_date,description,issuedby,issueddate)value(?,?,?,?,?,?)"
	 * ;
	 * 
	 * public static final String GET_TEACHER_MAIL_DETAILS =
	 * "select UserName,password,email from campus_teachers where TeacherID=?";
	 * 
	 * // VEHICLE MASTER BY CHIRU public static final String
	 * GET_ALL_VEHICLE_DETAILS =
	 * "select VehicleCode,VehicleName,Vehicle_Reg_No,VehicleType,Type_Of_Body,Makers_name,Manifacture_Date,Chassis_No,Seating_Capacity,Fuel_Type,Body_Color from transport_vehicle order by VehicleType"
	 * ; public static final String SAVE_VEHICLE_DETAILS =
	 * "insert into transport_vehicle(VehicleCode,Vehicle_Reg_No,VehicleName,VehicleType,Type_Of_Body,Makers_name,Manifacture_Date,Chassis_No,Seating_Capacity,Fuel_Type,Body_Color,CreateDate,CreateUser) values(?,?,?,?,?,?,?,?,?,?,?,now(),?)"
	 * ; public static final String UPDATE_VEHICLE_DETAILS =
	 * "update transport_vehicle set Vehicle_Reg_No=?,VehicleName=?,VehicleType=?,Type_Of_Body=?,Makers_name=?,Manifacture_Date=?,Chassis_No=?,Seating_Capacity=?,Fuel_Type=?,Body_Color=?,UpdateDate=now(),UpdateUser=? where VehicleCode= ?"
	 * ; public static final String DELETE_VEHICLE_DETAILS =
	 * "delete from transport_vehicle where VehicleCode = ?"; public static
	 * final String CHECKING_VEHICLE_CODE_IN_FUAL =
	 * "select count(*) from transport_fuel_maintainence where VehicleCode = ?";
	 * public static final String CHECKING_VEHICLE_CODE_IN_ROUTE_MAPPING =
	 * "select count(*) from transport_driver_route_vehicle_mapping where VehicleCode = ?"
	 * ; public static final String VALIDATE_VEHICLE_REG_NO =
	 * "select count(*) from transport_vehicle where Vehicle_Reg_No= ?"; public
	 * static final String VALIDATE_UPDATE_VEHICLE_REG_NO =
	 * "select count(*) from transport_vehicle where Vehicle_Reg_No = ? and VehicleCode !=?"
	 * ; public static final String VALIDATE_VEHICLE_CHASSIS_NO =
	 * "select count(*) from transport_vehicle where Chassis_No= ?"; public
	 * static final String VALIDATE_UPDATE_VEHICLE_CHASSIS_NO =
	 * "select count(*) from transport_vehicle where Chassis_No = ? and VehicleCode !=?"
	 * ;
	 * 
	 * // VEHICLE INSURANCE MASTER BY CHIRU public static final String
	 * GET_ALL_VEHICLE_INSURANCE_DETAILS =
	 * "select vi.slno,vi.VehicleCode,vi.CompanyName,vi.IssuedDate,vi.ExpiryDate,vi.doneby,v.VehicleName from transport_vehicle_insurancedetails vi left outer join  transport_vehicle v on v.VehicleCode=vi.VehicleCode order by vi.slno"
	 * ; public static final String GET_ALL_VEHICLE_NAME =
	 * "select VehicleCode,VehicleName from transport_vehicle order by VehicleName"
	 * ; public static final String SAVE_VEHICLE_INSURANCE_DETAILS =
	 * "insert into transport_vehicle_insurancedetails(VehicleCode,CompanyName,IssuedDate,ExpiryDate,doneby,updatedBy,updatedTime) values(?,?,?,?,?,?,now())"
	 * ; public static final String UPDATE_VEHICLE_INSURANCE_DETAILS =
	 * "update transport_vehicle_insurancedetails set CompanyName=?,IssuedDate=?,ExpiryDate=?,doneby=?,updatedBy=?,updatedTime=now() where slno = ?"
	 * ; public static final String DELETE_VEHICLE_INSURANCE_DETAILS =
	 * "delete from transport_vehicle_insurancedetails where slno = ?"; public
	 * static final String CHECKING_VEHICLE_INSURANCE_DATE =
	 * "select count(*) from transport_vehicle_insurancedetails where VehicleCode= ? and (? between IssuedDate and ExpiryDate or ? between IssuedDate and ExpiryDate)"
	 * ; public static final String CHECKING_VEHICLE_INSURANCE_DATE_WHILE_UPDATE
	 * =
	 * "select count(*) from transport_vehicle_insurancedetails where VehicleCode= ? and slno != ? and (? between IssuedDate and ExpiryDate or ? between IssuedDate and ExpiryDate)"
	 * ;
	 * 
	 * public static final String GET_EXAMINATION =
	 * "select e.examid,e.examname from campus_examination e where accadamicyear=?"
	 * ; public static final String GET_ALL_SUBJECTS =
	 * "select s.subjectID,s.subjectName from campus_subject s where classid=?";
	 * public static final String GET_SUBJECT_CLASS =
	 * "select distinct ett.categoryid,s.subjectID,s.subjectName,ett.examdate,ett.examtime,ett.endtime from campus_classdetail cd,campus_subject s,campus_examination_timetable ett where  s.subjectID=ett.subjectid and ett.examinationid=? and ett.classid=? and ett.subjectid like ?"
	 * ; public static final String SAVE_CLASS_MAPPING =
	 * "insert into campus_examination_classmapping(ExamDetailsId,ExamId,AccyearId,ClassId,createdDate,createdBy) values(?,?,?,?,?,?)"
	 * ; public static final String CHECK_DUPLICATE_EXAM_ID =
	 * "select count(*) from campus_examination_classmapping where AccyearId=? and ClassId=? and ExamId=?"
	 * ; public static final String CHECK_DUPLICATE_EXAM_DETAILS =
	 * "select count(*) from campus_examination_classmapping_details where SubjectId=?"
	 * ; public static final String MAX_RECORD =
	 * "select max(ExamDetailsId) examid from campus_examination_classmapping";
	 * public static final String SAVE_EXAM_DETAILS =
	 * "insert into campus_examination_classmapping_details(ExamDetailsId,SubjectId,ExamDate,StartTime,EndTime) values(?,?,?,?,?)"
	 * ; public static final String UPDATE_EXAM_DETAILS =
	 * "update campus_examination_classmapping_details set ExamDate=?,StartTime=?,EndTime=? where SubjectId=? and ExamDetailsId=?"
	 * ; public static final String GET_EXAM_DATE =
	 * "select e.examdate, e.exam_enddate from campus_examination e where examid=?"
	 * ; public static final String CHECK_DUPLICATE_CLASS_ID =
	 * "select count(*) from campus_examination_classmapping where ExamCode=? and classId=?"
	 * ; public static final String CHECK_DUPLICATE_SUB_DETAILS =
	 * "select count(*) from campus_examination_postpone where SubjectId=?";
	 * public static final String SAVE_EXAM_POSTPONE =
	 * "insert into campus_examination_postpone(ExampostponedCode,ExamCode,classId,subjectId,previousExamDate,postponedExamDate,starttime,endtime,reason,createdby,createdDate) values(?,?,?,?,?,?,?,?,?,?,now())"
	 * ;
	 * 
	 * public static final String GET_STUDENT_ASSIGMENT_DETAILS =
	 * "select ast.AssignmentCode,ast.Name,ast.AssignmentType,case when ast.AssignmentType='Genaral' then '-' else sub.subjectName end SubjectName,ast.MaxMarks,ast.Description,case when st.student_lname_var is null then st.student_fname_var else concat(st.student_fname_var,' ',st.student_lname_var) end student_fname_var,st.student_admissionno_var,st.student_id_int from  campus_assignment ast left join campus_subject sub on sub.subjectID=ast.SubjectID join campus_student st on st.classdetail_id_int=ast.ClassID and st.classsection_id_int=ast.SectionID  join campus_parentchildrelation cpc on cpc.stu_addmissionNo=st.student_id_int join campus_parents par on par.ParentID=cpc.parentid where cpc.parentid=? order by ast.AssignmentDate asc"
	 * ;
	 * 
	 * public static final String GET_CONCESSION_DETAILS_COUNT =
	 * "select count(*) from campus_student where student_scholorship_var=?";
	 * 
	 * public static final String GET_ASSIGNMENT_SCORE_COUNT =
	 * "select count(*) from  campus_assignmentdetails astd join campus_student st on st.student_id_int=astd.AdmissionNo where st.student_id_int=? and astd.AssignmentCode=?"
	 * ; public static final String GET_ASSIGNMENT_SCORE =
	 * "select AcquiredMarks from  campus_assignmentdetails astd join campus_student st on st.student_id_int=astd.AdmissionNo where st.student_id_int=? and astd.AssignmentCode=?"
	 * ;
	 * 
	 * // Bug fixing by swathi public static final String CHECK_APPLIED_JOBS =
	 * "select count(*) from campus_appliedjobs where email=? and jobcode=?";
	 * public static final String GET_EXAM_TIME_TABLE =
	 * "select * from campus_examination_timetable where categoryid=? and classid=? and subjectid=? and examinationid=?"
	 * ; public static final String GET_EXAM_ACADAMIC_YEAR =
	 * "select ac.acadamicyear_id_int,ac.acadamicyear_year from campus_acadamicyear ac,campus_examination e where e.accadamicyear=ac.acadamicyear_id_int and examid=?"
	 * ; public static final String UPDATE_EXAM_TIME_TABLE =
	 * "update campus_examination_timetable set examdate=?,examtime=?,endtime=?,updateuser=?,updatedate=now() where classid=? and subjectid=? and examinationid=? "
	 * ; public static final String CHECK_POSTPONE_SUBJECT_WISE =
	 * "select count(*) from campus_examination_postpone where ExamCode=? and classId=? and subjectId=?"
	 * ; public static final String GET_EXAM_POSTPONE_CODE =
	 * "select ExampostponedCode from campus_examination_postpone where ExamCode=? and classId=? and subjectId=?"
	 * ; public static final String UPDATE_EXAM_POSTPONE =
	 * "update campus_examination_postpone set previousExamDate=?,postponedExamDate=?,starttime=?,endtime=?,reason=?,createdby=?,createdDate=now() where ExampostponedCode=?"
	 * ;
	 * 
	 * // Displaying Examination Deatils by chiru public static final String
	 * GET_CHILD_LIST_BY_PARENT =
	 * "select stu.student_id_int, case when stu.student_fname_var is null then stu.student_lname_var else concat(stu.student_fname_var,' ',stu.student_lname_var)end studentname from campus_student stu, campus_parentchildrelation par where stu.student_id_int=par.stu_addmissionNo and par.parentid=?"
	 * ; public static final String GET_CHILD_EXAMINATION_DETAILS =
	 * "select cls.classdetails_name_var,sub.subjectName,exm.examname,exmtl.examdate,exmtl.examtime,exmtl.endtime from campus_examination_timetable exmtl, campus_classdetail cls, campus_subject sub,campus_examination exm where exmtl.classid = cls.classdetail_id_int and exmtl.subjectid = sub.subjectID and exmtl.examinationid= exm.examid and exmtl.classid = ?"
	 * ; public static final String GET_CHILD_CLASS =
	 * "select classdetail_id_int from campus_student where student_id_int =?";
	 * 
	 * // primary and secoundary upload grade marks by preethi public static
	 * final String GET_LOCATION_DETAILS =
	 * "select s.stage_id,s.stage_name from campus_fee_stage s"; public static
	 * final String GET_CLASS_BY_TEACHER =
	 * "select distinct cls.classdetails_name_var,cls.classdetail_id_int from campus_classdetail cls,campus_classteacher clt where clt.ClassCode=cls.classdetail_id_int and clt.TeacherCode=?"
	 * ; public static final String GET_SECTION_BY_TEACHER =
	 * "select distinct cls.classsection_name_var,cls.classsection_id_int from campus_classsection cls,campus_classteacher clt where clt.SectionCode=cls.classsection_id_int and clt.TeacherCode=?"
	 * ; public static final String GET_STUDENTS_BY_TEACHER =
	 * "select student_id_int,student_fname_var from campus_student where classdetail_id_int=? and classsection_id_int=? and fms_acadamicyear_id_int=?"
	 * ; public static final String GET_SECOUNDARY_GRADE_MARKS =
	 * "select * from campus_grademarks_secondary where stud_Id=? and accyear_id=?"
	 * ; public static final String UPDATE_SECOUNDARY_GRADE_MARKS =
	 * "update campus_grademarks_secondary set lang_1_SA1=?,lang_2_SA1=?,lang_3_SA1=?,lang_4_SA1=?,lang_5_SA1=?,lang_1_SA2=?,lang_2_SA2=?,lang_3_SA2=?,lang_4_SA2=?,lang_5_SA2=?,updatedby=?,updatedtime=? where stud_Id=?"
	 * ; public static final String CHECK_SECOUNDARY_MARKS =
	 * "select count(*) from campus_grademarks_secondary where stud_Id=? and accyear_id=?"
	 * ; public static final String INSERT_SECOUNDARY_GRADE_MARKS =
	 * "insert into campus_grademarks_secondary(stud_Id,accyear_id,lang_1_SA1,lang_2_SA1,lang_3_SA1,lang_4_SA1,lang_5_SA1,lang_1_SA2,lang_2_SA2,lang_3_SA2,lang_4_SA2,lang_5_SA2,updatedby,updatedtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String GET_PRIMARY_MARKS =
	 * "select * from campus_grademarks_primary where stud_Id=? and accyear_id=?"
	 * ; public static final String CHECK_PRIMARY_MARKS =
	 * "select count(*) from campus_grademarks_primary where stud_Id=? and accyear_id=?"
	 * ; public static final String INSERT_PRIMARY_GRADE_MARKS =
	 * "insert into campus_grademarks_primary(lang1_Sa1_Comp,lang1_Sa1_Conv,lang1_Sa1_Reci,lang1_Sa1_Pron,lang1_Sa1_Flue,lang1_Sa1_Hand,lang1_Sa1_Gram,lang1_Sa1_Spel,lang1_Sa1_Voca,lang1_Sa1_Crea,lang1_Sa1_Acti,lang1_Sa1_Extr,lang1_Sa2_Comp,lang1_Sa2_Conv,lang1_Sa2_Reci,lang1_Sa2_Pron,lang1_Sa2_Flue,lang1_Sa2_Hand,lang1_Sa2_Gram,lang1_Sa2_Spel,lang1_Sa2_Voca,lang1_Sa2_Crea,lang1_Sa2_Acti,lang1_Sa2_Extr,lang2_Sa1_Comp,lang2_Sa1_Conv,lang2_Sa1_Reci,lang2_Sa1_Pron,lang2_Sa1_Flue,lang2_Sa1_Hand,lang2_Sa1_Gram,lang2_Sa1_Spel,lang2_Sa1_Voca,lang2_Sa1_Crea,lang2_Sa1_Acti,lang2_Sa1_Extr,lang2_Sa2_Comp,lang2_Sa2_Conv,lang2_Sa2_Reci,lang2_Sa2_Pron,lang2_Sa2_Flue,lang2_Sa2_Hand,lang2_Sa2_Gram,lang2_Sa2_Spel,lang2_Sa2_Voca,lang2_Sa2_Crea,lang2_Sa2_Acti,lang2_Sa2_Extr,lang3_Sa1_Conc,lang3_Sa1_Tabl,lang3_Sa1_Ment,lang3_Sa1_Writ,lang3_Sa1_Acti,lang3_Sa2_Conc,lang3_Sa2_Tabl,lang3_Sa2_Ment,lang3_Sa2_Writ,lang3_Sa2_Acti,lang4_Sa1_Envi,lang4_Sa1_Grop,lang4_Sa1_Acti,lang4_Sa1_Writ,lang4_Sa2_Envi,lang4_Sa2_Grop,lang4_Sa2_Acti,lang4_Sa2_Writ,lang5_Sa1_Mapp,lang5_Sa1_Acti,lang5_Sa1_Writ,lang5_Sa2_Mapp,lang5_Sa2_Acti,lang5_Sa2_Writ,stud_Id,accyear_id,updatedby,updatedtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; public static final String UPDATE_PRIMARY_GRADE_MARKS =
	 * "update campus_grademarks_primary set lang1_Sa1_Comp=?,lang1_Sa1_Conv=?,lang1_Sa1_Reci=?,lang1_Sa1_Pron=?,lang1_Sa1_Flue=?,lang1_Sa1_Hand=?,lang1_Sa1_Gram=?,lang1_Sa1_Spel=?,lang1_Sa1_Voca=?,lang1_Sa1_Crea=?,lang1_Sa1_Acti=?,lang1_Sa1_Extr=?,lang1_Sa2_Comp=?,lang1_Sa2_Conv=?,lang1_Sa2_Reci=?,lang1_Sa2_Pron=?,lang1_Sa2_Flue=?,lang1_Sa2_Hand=?,lang1_Sa2_Gram=?,lang1_Sa2_Spel=?,lang1_Sa2_Voca=?,lang1_Sa2_Crea=?,lang1_Sa2_Acti=?,lang1_Sa2_Extr=?,lang2_Sa1_Comp=?,lang2_Sa1_Conv=?,lang2_Sa1_Reci=?,lang2_Sa1_Pron=?,lang2_Sa1_Flue=?,lang2_Sa1_Hand=?,lang2_Sa1_Gram=?,lang2_Sa1_Spel=?,lang2_Sa1_Voca=?,lang2_Sa1_Crea=?,lang2_Sa1_Acti=?,lang2_Sa1_Extr=?,lang2_Sa2_Comp=?,lang2_Sa2_Conv=?,lang2_Sa2_Reci=?,lang2_Sa2_Pron=?,lang2_Sa2_Flue=?,lang2_Sa2_Hand=?,lang2_Sa2_Gram=?,lang2_Sa2_Spel=?,lang2_Sa2_Voca=?,lang2_Sa2_Crea=?,lang2_Sa2_Acti=?,lang2_Sa2_Extr=?,lang3_Sa1_Conc=?,lang3_Sa1_Tabl=?,lang3_Sa1_Ment=?,lang3_Sa1_Writ=?,lang3_Sa1_Acti=?,lang3_Sa2_Conc=?,lang3_Sa2_Tabl=?,lang3_Sa2_Ment=?,lang3_Sa2_Writ=?,lang3_Sa2_Acti=?,lang4_Sa1_Envi=?,lang4_Sa1_Grop=?,lang4_Sa1_Acti=?,lang4_Sa1_Writ=?,lang4_Sa2_Envi=?,lang4_Sa2_Grop=?,lang4_Sa2_Acti=?,lang4_Sa2_Writ=?,lang5_Sa1_Mapp=?,lang5_Sa1_Acti=?,lang5_Sa1_Writ=?,lang5_Sa2_Mapp=?,lang5_Sa2_Acti=?,lang5_Sa2_Writ=?,updatedby=?,updatedtime=? where stud_Id=? and accyear_id=?"
	 * ;
	 * 
	 * // Transport type Deatils by chiru public static final String
	 * GET_TRANSPORT_TYPE_DETAILS =
	 * "select type_id,type_name,type_collectFee,type_description from transport_typedetails"
	 * ;
	 * 
	 * // Transport type status Deatils by seshu
	 * 
	 * public static final String GET_TRANSPORTSTATUS_TYPE =
	 * "select type_collectFee from transport_typedetails where type_id=?";
	 * public static final String GET_SECTION_BY_TEACHER_AND_CLASS =
	 * "select distinct cls.classsection_name_var,cls.classsection_id_int from campus_classsection cls,campus_classteacher clt where clt.SectionCode=cls.classsection_id_int and clt.TeacherCode=? and clt.ClassCode=? order by cls.classsection_name_var"
	 * ; public static final String GET_STUDENT_ID =
	 * "select student_id_int from campus_student where student_admissionno_var=?"
	 * ;
	 * 
	 * // Holiday Master created by swathi public static final String
	 * CHECK_HOLIDAY_DATE =
	 * "select count(*) from campus_holidaymaster where HOLIDAY_DATE=?"; public
	 * static final String ADD_HOLIDAY =
	 * "insert into campus_holidaymaster(HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,CURRENT_YEAR,CREATEDDATE,CREATEDBY) values(?,?,?,?,?,?)"
	 * ; public static final String GET_HOLIDAYS =
	 * "select  h.WEEKDAY,h.HOLIDAY_NAME,h.HOLIDAY_DATE,a.acadamicyear_year from campus_holidaymaster h,campus_acadamicyear a where a.acadamicyear_id_int=h.CURRENT_YEAR and h.CURRENT_YEAR=?"
	 * ; public static final String UPDATE_HOLIDAY =
	 * "update campus_holidaymaster set HOLIDAY_NAME=?,MODIFIED_BY=?,MODIFIED_DATE=? where HOLIDAY_DATE=?"
	 * ; public static final String DELETE_HOLIDAY =
	 * "delete from campus_holidaymaster where HOLIDAY_DATE=?"; public static
	 * final String GET_DISTINCT_HOLIDAYS =
	 * "select HOLIDAY_DATE from campus_holidaymaster";
	 * 
	 * // Teacher Pay Certificate Report created by swathi public static final
	 * String GET_TEACHER_PAYCERTIFICATE =
	 * "select p.userId,t.FirstName,d.designationName,s.nonboard_pf,p.salary_amount,s.nonboard_salary,p.main_adv,p.fest_Adv,p.LOP  from campus_payroll_details p,campus_teachers t,campus_teacher_salarydetails s,campus_designation d where   p.userId=t.TeacherID  and s.teacher_id=p.userId and d.DesignationCode=t.designation  and  p.userId=? and p.month=? and p.year=? "
	 * ;
	 * 
	 * // Get Next Year Accodamic year Details public static final String
	 * GET_NEXT_ACCODAMIC_YEAR =
	 * "select acadamicyear_id_int,acadamicyear_year from campus_acadamicyear where acadamicyear_id_int =?"
	 * ;
	 * 
	 * 
	 * public static final String STUDENT_SEARCH_BY_CLASSNAME =
	 * "select student_fname_var,student_id_int from campus_student where student_status_var ='active' and fms_acadamicyear_id_int=? and classdetail_id_int like ? and student_fname_var like ?"
	 * ;
	 */
}
