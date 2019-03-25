package com.centris.campus.util;

public class SmsUtilsConstants {
	
	public static final String GET_CLASS_LIST = "select classdetail_id_int,classdetails_name_var from campus_classdetail order by classdetails_name_var";
	
	public static final String GET_SECTION_LIST = "select classsection_id_int,classsection_name_var from campus_classsection where classdetail_id_int=?";

	public static final String GET_SUBJECT_LIST = "select subjectID,subjectName from campus_subject where classid=? order by subjectName";
	
	public static final String INSERT_HOMEWORK = "insert into campus_homework (homeworkid,dateid,classid,sectionid,subjectid,description,createdate,createuser) values(?,?,?,?,?,?,?,?)";
	public static final String SINGLE_SMS="insert into sms_single(mobileno,singlemsg)values(?,?)";
	
	public static final String GET_HOMEWORK_LIST = "select distinct hm.subjectid,sub.subjectName,hm.dateid,cls.classdetails_name_var,hm.description from campus_homework hm join campus_classdetail cls on cls.classdetail_id_int=hm.classid join campus_classsection sec on sec.classsection_id_int=hm.sectionid join campus_subject sub on sub.subjectID=hm.subjectid";
	
	public static final String SEARCH_HOMEWORK_LIST = "select hm.homeworkid,hm.dateid,cls.classdetails_name_var,sec.classsection_name_var,sub.subjectName,hm.description from campus_homework hm join campus_classdetail cls on cls.classdetail_id_int=hm.classid join campus_classsection sec on sec.classsection_id_int=hm.sectionid join campus_subject sub on sub.subjectID=hm.subjectid where hm.dateid like ? or cls.classdetails_name_var like ? or sec.classsection_name_var like ? or sub.subjectName like ? or hm.description like ?";

	public static final String DELETE_HOMEWORK = "delete from campus_homework where homeworkid=?";

	public static final String GET_MEETING_LIST = "select me.meetingid,me.meetingdate,me.title,me.starttime,me.endtime,me.studentname,case when me.subjectname='general' then me.subjectname else(select subjectName from campus_subject where subjectID=me.subjectname) end subjectname,me.venuedetails from sms_meeting me ORDER BY me.meetingdate DESC";
	
	public static final String GET_SUBJECT_DETAILS = "select subjectID,subjectName from campus_subject where classid=?";
	
	public static final String GET_STUDENT_DETAILS = "select csc.student_id_int,concat(cs.student_fname_var,' ',cs.student_lname_var,'-',cs.student_admissionno_var)as studentname from campus_student_classdetails csc join campus_student cs on csc.student_id_int=cs.student_id_int where csc.classsection_id_int=? and csc.fms_acadamicyear_id_int=?";

	public static final String SAVE_MEETING = "insert into sms_meeting (meetingid,meetingdate,title,classid,sectionid,starttime,endtime,subjectname,studentname,description,createuser,createdate,accyear) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String DELETE_MEETING = "delete from sms_meeting where meetingid=?";
	
	public static final String SEARCH_MEETING_LIST = "select meetingid,meetingdate,title,starttime,endtime,venuedetails from  sms_meeting where meetingid like ? or meetingdate like ? or title like ? or starttime like ? or endtime like ? or venuedetails like ?";

	public static final String SAVE_LATE = "insert into latecomers_sms (Late_Comers_Code,Student_Code,Class_Code,Section_Code,Date,Description) values(?,?,?,?,?,?)";

	public static final String GET_STUDENT_DETAILS_VAL = "select se.classsection_id_int,re.relationship,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var when re.relationship='guardian' then par.student_gardian_mobileno else '1' end) as student_contact_mobileno, cl.classdetail_id_int,cl.classdetails_name_var,stu.student_id_int,stu.student_fname_var from campus_student stu join campus_classdetail cl on cl.classdetail_id_int = stu.classdetail_id_int join campus_classsection se on se.classsection_id_int = stu.classsection_id_int join campus_parentchildrelation re on re.stu_addmissionNo = stu.student_id_int join campus_parents par on par.ParentID = re.parentid where stu.student_id_int = ?";

	public static final String GET_LATECOMERS_LIST = "select * from latecomers_sms";
	
	public static final String STORE_UNIFORM_SMS_DETAILS = "insert into sms_uniform_details(UNIFORM_CODE,UNIFORM_DATE,UNIFORM_CONTENT,IsSection,IsStudent,SMS_STATUS,CREATE_TIME,CREATED_BY) values(?,?,?,?,?,?,?,?)";
	
	public static final String STORE_UNIFORM_SECTION_DETAILS = "insert into sms_uniform_section(UNIFORM_CODE,SECTION_CODE,CREATED_TIME,CREATED_BY) values(?,?,?,?)";

	public static final String STORE_UNIFORM_STUDENT_DETAILS = "insert into sms_uniform_student(UNIFORM_CODE,STUDENT_ADM_NO,CREATED_TIME,CREATED_BY) values(?,?,?,?)";

	public static final String GET_ABSENT_SMS = "select  stu.student_admissionno_var,det.CREATED_BY,stu.student_id_int,stu.classsection_id_int,det.ABSENT_DATE smsDate,det.ABSENT_CONTENT as smsContent,re.relationship,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var when re.relationship='guardian' then par.student_gardian_mobileno else '1' end)as student_contact_mobileno,par.ParentID from campus_parents par join campus_parentchildrelation re on re.parentid = par.ParentID join campus_student stu on stu.student_id_int = re.stu_addmissionNo join sms_absent_student hol on hol.STUDENT_ADM_NO = stu.student_id_int join sms_absent_details det on det.ABSENT_CODE = hol.ABSENT_CODE where hol.ABSENT_CODE= ? and det.ABSENT_DATE=? ";
	
	public static final String GET_UNIFORM_LIST = "select us.Uniform_Code,us.Date,us.Description,cls.classdetails_name_var,sec.classsection_name_var,stu.student_fname_var from uniform_sms us join  campus_classdetail cls on cls.classdetail_id_int=us.Class_Code join campus_classsection sec on sec.classsection_id_int=us.Section_Code join campus_student stu on stu.student_id_int=us.Student_Code order by us.Uniform_Code";
	
	public static final String GET_SECTION_DETAILS = "select us.UNIFORM_CODE,us.SECTION_CODE,sec.classsection_name_var from sms_uniform_section us join campus_classsection sec on sec.classsection_id_int=us.SECTION_CODE where us.UNIFORM_CODE=?";
	
	public static final String GET_STUDENT_UNIFORM_DETAILS = "";

	public static final String SAVE_UNIFORM = "insert into uniform_sms (Uniform_Code,Student_Code,Class_Code,Section_Code,Date,Description) values(?,?,?,?,?,?)";
	
	public static final String SAVE_BIRTHDAY = "insert into birthday_sms(bdaycode,bdaydate,classcode,sectioncode,studentcode,smscontent,createuser,createTime) values(?,?,?,?,?,?,?,now())";
	
	public static final String GET_BDAY_LIST = "select bd.bdaycode,bd.bdaydate,bd.classcode,bd.sectioncode,bd.studentcode,bd.smscontent,cls.classdetails_name_var,sec.classsection_name_var,stu.student_fname_var from birthday_sms bd join  campus_classdetail cls on cls.classdetail_id_int=bd.classcode join campus_classsection sec on sec.classsection_id_int=bd.sectioncode join campus_student stu on stu.student_id_int=bd.studentcode order by bd.bdaycode";

	public static final String INSERT_FEE_DETAILS = "insert into sms_fee_details (FEE_CODE,FEE_DATE,STU_ID,FEE_CONTENT,TOTAL_AMT,PAID_AMT,BALANCE_AMT,CREATED_BY,CREATE_TIME) values(?,?,?,?,?,?,?,?,?)";

	public static final String SEND_FEE_DETAILS = "select stu.student_admissionno_var,fee.FEE_DATE as smsDate,fee.FEE_CONTENT as smsContent,fee.CREATE_TIME,fee.CREATED_BY,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var when re.relationship='guardian' then par.student_gardian_mobileno else '1' end)as student_contact_mobileno,par.ParentID from sms_fee_details fee join campus_parentchildrelation re on re.stu_addmissionNo =fee.STU_ID join campus_parents par on par.ParentID=re.parentid join campus_student stu on stu.student_id_int = fee.STU_ID where FEE_CODE=? and FEE_DATE=?";

	public static final String GET_FEE_DETAILS = "Select distinct f.*,concat(stu.student_fname_var,' ',student_lname_var) as name,stu.student_admissionno_var from campus_payment_collection f,campus_student stu where stu.student_admissionno_var=f.student_admission and feesettings_id=?";

	public static final String CHECK_BDAY_DATE = "select count(*) from campus_student where  student_dob_var like ?";

	public static final String GET_SMS_DETAILS_FOR_BDAY = "select concat(stu.student_fname_var,' ',student_lname_var) as name,stu.student_id_int,stu.classdetail_id_int,stu.classsection_id_int,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var when re.relationship='guardian' then par.student_gardian_mobileno else '1' end)as student_contact_mobileno from campus_student stu join campus_parentchildrelation re on re.stu_addmissionNo =stu.student_id_int join campus_parents par on par.ParentID = re.parentid where stu.student_dob_var like ?";
	
	public static final String GET_SUBJECT_DETAILS_VAL = "select stu.student_id_int,re.relationship,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var  when re.relationship='guardian' then par.student_gardian_mobileno else '1' end) as student_contact_mobileno,stu.student_fname_var,cl.classdetails_name_var,sub.subjectID,sub.subjectName from campus_subject sub join campus_classdetail cl on cl.classdetail_id_int = sub.classid join campus_student stu on stu.classdetail_id_int = cl.classdetail_id_int join campus_parentchildrelation re on re.stu_addmissionNo = stu.student_id_int join campus_parents par on par.ParentID = re.parentid where sub.subjectID = ?";

	public static final String GET_SUB_DETAILS = "select stu.student_id_int,re.relationship,(case when re.relationship='father' then par.mobileno when re.relationship='mother' then par.student_mothermobileno_var  when re.relationship='guardian' then par.student_gardian_mobileno else '1' end) as student_contact_mobileno,stu.student_fname_var,cl.classdetails_name_var,sub.subjectID,sub.subjectName from campus_subject sub join campus_classdetail cl on cl.classdetail_id_int = sub.classid join campus_student stu on stu.classdetail_id_int = cl.classdetail_id_int join campus_parentchildrelation re on re.stu_addmissionNo = stu.student_id_int join campus_parents par on par.ParentID = re.parentid where sub.subjectID = ?";

	public static final String SAVE_OTHERS = "insert into sms_othersms (other_sms_id,studentid,classid,sectionid,entry_date,created_by) values(?,?,?,?,?,?)";



	
	
	
	
	
}
