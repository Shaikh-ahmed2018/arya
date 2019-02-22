package com.centris.campus.util;

public class ParentModuleUtil {

	
	public static final String GET_STUDENT_PARENT_DETAILS = "select p.parentid,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,p.stu_addmissionNo,concat(s.student_fname_var,' ',s.student_lname_var) as student  from campus_parentchildrelation p join campus_student s on s.student_id_int=p.stu_addmissionNo left join campus_student_classdetails csc on csc.student_id_int = s.student_id_int where p.parentid=? and csc.fms_acadamicyear_id_int = ?";
	
	//public static final String GET_EXAM_STUDENT_DETAILS ="select cl.classdetails_name_var,sub.subjectName,ex.examname,ex.startdate,ex.enddate,ti.examtime,ti.endtime from campus_examination_timetable ti,campus_examination ex,campus_classdetail cl,campus_subject sub where ti.examinationid=ex.examid and ti.classid = cl.classdetail_id_int and ti.subjectid=sub.subjectID and cl.classdetail_id_int in (select classdetail_id_int from campus_student where student_id_int=?)";
	public static final String GET_EXAM_STUDENT_DETAILS = "select examid,examname,startdate,enddate from campus_examination where acadamicyear = ? and loc_id = ? order by startdate;";
	public static final String GET_ASSIGNMENT_STUDENT_DETAILS = "select cls.classdetails_name_var,case when ass.SubjectID='general' then ass.SubjectID else (select subjectName from campus_subject where subjectID=ass.SubjectID) end subjectname,ass.AssignmentCode,ass.Name,ac.acadamic_year,ass.AssignmentDate,ass.CompletionDate,ass.MaxMarks,sec.classsection_name_var from campus_assignment ass, campus_classdetail cls, campus_acadamicyear ac,campus_classsection sec where ass.ClassID=cls.classdetail_id_int and ass.AcadamicID=ac.acadamic_id and ass.SectionID=sec.classsection_id_int and cls.classdetail_id_int in (select classdetail_id_int from campus_student_classdetails where student_id_int=? and fms_acadamicyear_id_int = ?) and sec.classsection_id_int in (select classsection_id_int from campus_student_classdetails where student_id_int=? and fms_acadamicyear_id_int = ?)";
	
	public static final String GET_VIEW_ASSIGNMENT_DETAILS = "select asd.AdmissionNo,asd.ActualCompletionDate,asd.AcquiredMarks,asd.Remarks,sa.Description from campus_assignmentdetails asd join campus_assignment sa on sa.AssignmentCode=asd.AssignmentCode where asd.AssignmentCode=? and asd.AdmissionNo=? and Acc_id = ?";

	
	public static final String GET_STUDENT_INFO_DETAILS = "select st.student_remarks_var,acc.acadamic_year,csc.student_rollno,st.student_id_int,st.student_application_no,st.student_admissionno_var,st.student_doj_var,st.student_fname_var,st.student_lname_var,sr.religion,sc.caste,scc.casteCategory,st.student_dob_var,st.student_gender_var,st.student_bloodgroup_var,st.student_age_int,st.student_nationality_var,st.student_physicallychallenged,st.physicallychallenged_reason,st.student_identificationmarks_var,st.student_identificationmarks1_var,st.adharNo,st.motherTounge,st.medium,st.annualParentsIncome,st.student_tc_path,st.student_siblingId,st.student_birthcert_path,st.student_prehistory_var,csc.firstlanguage,csc.secondlanguage,csc.thirdlanguage,csc.student_imgurl_var,cd.classdetails_name_var,cs.classsection_name_var,loc.Location_Name,spe.Specialization_name,str.classstream_name_var,rt.RouteName,sta.stage_name,par.FatherName,par.Qualification,par.mobileno,par.email,occu.occupation as fatheroccupation,par.student_mothername_var,par.student_mothermobileno_var,par.student_motherqualification_var,par.student_mother_mailid,moccu.occupation as moccupation,par.student_gaurdianname_var,pra.relationship,par.student_gardian_mailid,par.student_gardian_mobileno,par.fatherPanNo,par.fatherAnnualIncome,par.motherPanNo,par.motherAnnualIncome,goccu.occupation as goccupation,par.guardianPanNo,par.guardianAnnualIncome,par.guardianQualification,par.address,par.presentAddress,stt.isTransport from campus_student st left join campus_student_classdetails csc on st.student_id_int = csc.student_id_int left join campus_student_transportdetails stt on stt.student_id_int =csc.student_id_int left join campus_classstream str on str.classstream_id_int = csc.fms_classstream_id_int left join campus_classdetail cd on cd.classdetail_id_int = csc.classdetail_id_int left join campus_classsection cs on cs.classsection_id_int = csc.classsection_id_int left join campus_location loc on loc.Location_Id = csc.locationId left join campus_class_specialization spe on spe.Specialization_Id = csc.specilization left join campus_fee_stage sta on sta.stage_id = stt.StageId left join transport_route rt on rt.RouteCode = stt.route left join campus_parentchildrelation pra on pra.stu_addmissionNo = csc.student_id_int left join campus_parents par on par.ParentID = pra.parentid left join campus_acadamicyear acc on acc.acadamic_id = csc.fms_acadamicyear_id_int left join campus_caste sc on sc.casteId = st.student_caste left join campus_caste_category scc on scc.castCatId = st.casteCategory left join campus_religion sr on sr.religionId = st.student_religion_var left join campus_occupation occu on occu.occupationId = par.student_father_occupation left join campus_occupation moccu on moccu.occupationId  = par.student_mother_occupation left join campus_occupation goccu on goccu.occupationId  = par.guardianOccupation where csc.student_id_int = ?  and csc.fms_acadamicyear_id_int = ? ";

	public static final String SAVE_FEEDBACK = "insert into campus_feedback(FeedBackCode,ToUser,Description,FilePath,createdby,createtime) values(?,?,?,?,?,?)";

	public static final String GET_FEEDBACK_DETAILS = "select FeedBackCode,ToUser,FilePath,Description from campus_feedback order by FeedBackCode";
	
	public static final String GET_MONTH_ATTENDANCE_DETAILS = "select count(*) attendence  from  campus_attendence where  attendencedate like ? and addmissionno=? and attendencedate>=? and  attendencedate<=?";
	
	public static final String GET_TOTAL_PRESENT_DETAILS = "select count(*) attendence  from  campus_attendence where  attendencedate like ? and attendence='present' and addmissionno=? and attendencedate>=? and  attendencedate<=?";
	
	public static final String GET_TOTAL_ABSENT_DETAILS = "select count(*) attendence  from  campus_attendence where  attendencedate like ? and attendence='absent' and addmissionno=? and attendencedate>=? and  attendencedate<=?";
	
	public static final String GET_TOTAL_LEAVE_DETAILS ="select count(*) attendence  from  campus_attendence where  attendencedate like ? and attendence='holiday' and addmissionno=? and attendencedate>=? and  attendencedate<=?";
	
	public static final String GET_TOTAL_HOLIDAY_DETAILS ="select count(*) attendence  from  campus_attendence where  attendencedate like ? and attendence='leave' and addmissionno=? and attendencedate>=? and  attendencedate<=?";
	
	public static final String GET_DAYS_ATTENDANCE_DETAILS = "select attendencedate,attendence from campus_attendence where addmissionno=? and attendencedate like ? and attendencedate>=? and  attendencedate<=? ";
	
	public static final String GET_STREAM_DETAILS = "select classstream_id_int,classstream_name_var from campus_classstream order by classstream_name_var";
	
	//public static final String GET_CLASS_DETAILS ="select cl.classdetail_id_int,cl.classdetails_name_var from campus_classdetail cl join campus_classstream st on st.classstream_id_int=cl.classstream_id_int where cl.classstream_id_int=?";
	public static final String GET_CLASS_DETAILS = "select cl.classdetail_id_int,cl.classdetails_name_var from campus_classdetail cl order by length(classdetail_id_int),classdetail_id_int";
	
	public static final String GET_SUBJECT_DETAILS = "select subjectID,subjectName,syllabous,decription from campus_subject where classid=? and locationId = ?";

	public static final String FEEDBACK_PATH = "select FilePath from campus_feedback where FeedBackCode=?";
	
	public static final String SUBJECT_SYLLABUS_PATH = "select syllabous from campus_subject where subjectID=?";
 
	public static final String GET_ALL_MEETING_DETAILS = "select me.meetingid,me.meetingdate,me.starttime,me.endtime,me.title,me.venuedetails,md.StudentId,md.TeacherId,case when me.subjectId='general' then me.subjectId else(select subjectName from campus_subject where subjectID=me.subjectId) end subjectname from campus_meeting me, campus_meeting_details md where meetingid=MeetingCode";

	public static final String GET_TEACHER_MEETING ="select FirstName from campus_teachers where TeacherID=?";
	
	public static final String GET_STUDENT_MEETING = "select stu.student_fname_var,cls.classdetails_name_var, sec.classsection_name_var from campus_student stu, campus_classdetail cls ,campus_classsection sec where stu.classdetail_id_int=cls.classdetail_id_int and stu.classsection_id_int=sec.classsection_id_int and stu.student_id_int=?";

	public static final String GET_STUDENT_MEETING_DETAILS = "select me.meetingid,me.meetingdate,me.starttime,me.endtime,me.title,me.venuedetails,md.StudentId,concat(stu.student_fname_var, '' ,student_lname_var) as studentname,cd.classdetails_name_var,cs.classsection_name_var,case when me.subjectId='general' then me.subjectId else(select subjectName from campus_subject where subjectID=me.subjectId) end subjectname from campus_meeting me, campus_meeting_details md,campus_student stu,campus_classdetail cd,campus_classsection cs where meetingid=MeetingCode and stu.student_id_int=md.StudentId and cd.classdetail_id_int = stu.classdetail_id_int and cs.classsection_id_int=stu.classsection_id_int and md.StudentId=?";


	public static final String GET_PARENT_USER_DETAILS = "select usr.employeecode,concat(tea.FirstName, '' ,LastName) as teachername,cls.classdetails_name_var,ts.teacherID  from campus_user usr,campus_teachers tea,campus_classdetail cls,campus_teachersettings ts where ts.classID=cls.classdetail_id_int and usr.employeecode = tea.TeacherID and usr.employeecode=ts.teacherID and tea.isActive='Y'";

	public static final String GET_TEACHER_USER_DETAILS = "select usr.employeecode,concat(tea.FirstName, '' ,LastName) as teachername from campus_user usr,campus_teachers tea where usr.employeecode=tea.TeacherID and usr.type='admin' and tea.isActive ='Y'";

	public static final String SAVE_LEAVE_REQUEST = "insert into campus_student_leave_request(RequestedTo,studentId,StartDate,EndDate,SessionStart,SessionEnd,LeaveType,NoofLeaves,ReasonForLeave,filepaath,LeaveStatus,RequestedBy,RequestedDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String GET_LEAVE_REQUEST_DETAILS = "select rq.SNO,rq.NoofLeaves,rq.StartDate,rq.EndDate,rq.SessionStart,rq.SessionEnd,rq.LeaveStatus,rq.LeaveType,concat(tea.FirstName, '' ,LastName) as teachername,usr.type,concat(stu.student_fname_var,' ',student_lname_var)as studentname,rq.ReasonForLeave from campus_student_leave_request rq,campus_teachers tea ,campus_student stu,campus_user usr where rq.studentId=stu.student_id_int and rq.RequestedTo=tea.TeacherID and rq.RequestedBy=usr.employeecode order by rq.LeaveStatus='NOTAPPROVED'";

	public static final String GET_LEAVE_DETAILS = " select concat(tea.FirstName, '' ,LastName) as teachername,req.RequestedTo,req.NoofLeaves,req.StartDate,stu.student_fname_var,req.EndDate,req.SessionStart,req.SessionEnd,req.LeaveType,req.filepaath,req.ReasonForLeave from campus_student_leave_request req,campus_teachers tea ,campus_student stu where req.RequestedTo=tea.TeacherID and req.studentId=stu.student_id_int and req.SNO=?";
	
	public static final String DELETE_STUDENT_LEAVE_DETAILS = "delete from campus_student_leave_request where SNO=?";
	
	public static final String UPDATE_LEAVE_REQUEST = "update campus_student_leave_request set RequestedTo=?,studentId=?,NoofLeaves=?,StartDate=?,EndDate=?,SessionStart=?,SessionEnd=?,LeaveType=?,filepaath=?,ReasonForLeave=?,RequestedDate=?,RequestedBy=?,LeaveStatus=? where SNO=?";
	
	public static final String GET_TIMETABLE_ID ="select timetable_id,concat(ti.classid, '-' ,sectionid) as classname from campus_timetable_student ti , campus_student_classdetails csc where csc.classdetail_id_int = ti.classid and csc.classsection_id_int = ti.sectionid and ti.accyearid = csc.fms_acadamicyear_id_int and csc.student_id_int = ? and csc.fms_acadamicyear_id_int = ?";
	
	
	public static final String GET_STUDENT_TIME_TABLE = "select days.dayname,days.daycode, ti.period1, ti.period2, ti.period3, ti.period4, ti.period5, ti.period6, ti.period7, ti.period8 from campus_timetable_studentdetails ti join campus_timetable_day days where ti.daycode = days.daycode and timetableid =?";
	
	public static final String GET_SUBJECT_NAME = "select subjectName from campus_subject where subjectID=?";
	public static final String GET_DAY_NAME ="select dayname from campus_timetable_day where daycode=?";
	
	public static final String GET_TEACHER_TIME_TABLE_ID = "select ti.teachertimetable_id from campus_timetable_teacher ti where teacherid=?";
	

	public static final String GET_TEACHER_TIME_TABLE = "select day.dayname,day.daycode,ti.period1,ti.period2,ti.period3,ti.period4,ti.period5,ti.period6,ti.period7,ti.period8 from campus_timetable_teacherdetails ti join campus_timetable_day day where ti.daycode=day.daycode and teachertimetable_id=?";
	
	public static final String GET_CLASS_NAME = "select cls.classdetails_name_var,sec.classsection_name_var  from campus_classdetail cls,campus_classsection sec where cls.classdetail_id_int=sec.classdetail_id_int and cls.classdetail_id_int=? and sec.classsection_id_int=?";
	
    

    public static final String GET_LEAVE_APPROVED_BY_ADMIN = "select nlb.Leave_name,tea.SNO,tea.RequestedBy,tea.ReasonForLeave,tea.NoofLeaves,tea.StartDate,tea.EndDate,tea.RequestedDate,tea.LeaveStatus,tea.LeaveType,tea.RequestedTo,concat(tr.FirstName, ' ' ,LastName)as name from campus_teachers_leave_request tea  join campus_teachers tr on tea.RequestedBy=tr.TeacherID join campus_new_leave_bank nlb on nlb.Leave_ID = tea.LeaveType  where tea.LeaveStatus='Pending' ";

    public static final String GET_LEAVE_APPROVED_BY_TEACHER = "select st.SNO,st.RequestedBy,st.ReasonForLeave,st.NoofLeaves,st.StartDate,st.EndDate,st.RequestedDate,st.LeaveStatus,st.LeaveType,st.RequestedTo,concat(par.FatherName)as name from campus_student_leave_request st join campus_parents par on par.ParentID=st.RequestedBy where st.LeaveStatus='Pending'and st.RequestedTo=?";

    public static final String  GET_LEAVE_FOR_APPROVE_DETAILS_TEACHER = "select le.RequestedBy,usr.type,le.StartDate,le.EndDate,le.LeaveType,le.NoofLeaves,le.ReasonForLeave,le.SessionStart,le.SessionEnd from campus_student_leave_request le , campus_user usr where le.RequestedBy=usr.employeecode and le.SNO=?";

    public static final String  GET_LEAVE_FOR_APPROVE_DETAILS_STUDENT = "select le.RequestedBy,usr.type,le.StartDate,le.EndDate,le.LeaveType,le.NoofLeaves,le.ReasonForLeave,le.SessionStart,le.SessionEnd from campus_teachers_leave_request le , campus_user usr where le.RequestedBy=usr.employeecode and le.SNO=?";

    public static final String LEAVE_APPROVAL_BY_ADMIN = "select tea.SNO,tea.RequestedBy,tea.ReasonForLeave,tea.NoofLeaves,tea.StartDate,tea.EndDate,tea.RequestedDate,tea.LeaveStatus,tea.LeaveType,tea.RequestedTo,concat(tr.FirstName, ' ' ,LastName)as name from campus_teachers_leave_request tea join campus_teachers tr on tea.RequestedBy=tr.TeacherID where tea.RequestedTo=?";

    public static final String GET_LEAVE_APPROVING_BY_ADMIN = "update campus_teachers_leave_request set TotalDaysAproved=?,ApprovedStartDate=?,ApprovedEndDate=?,LeaveStatus=?,commennts=?,ApprovedBy=?,AprovedDate=?,ApproveSessionStart=?,ApproveSessionEnd=? where SNO=?";
    public static final String GET_LEAVE_APPROVING_BY_TEACHER = "update campus_student_leave_request set TotalDaysAproved=?,ApprovedStartDate=?,ApprovedEndDate=?,LeaveStatus=?,commennts=?,ApprovedBy=?,AprovedDate=?,ApproveSessionStart=?,ApproveSessionEnd=? where SNO=?";
    
    public static final String DELETE_TEACHER_LEAVE_DETAILS = "update campus_teachers_leave_request set LeaveStatus = 'Canceled' where SNO=?";

    public static final String  GET_PARENT_REMAINDER = "select remainder_title,description,remainder_to from campus_remainder where parents='Y' ORDER BY created_time DESC";

    public static final String GET_LEAVE_SEARCH_BY_ADMIN = "select nlb.Leave_name,tea.SNO,tea.RequestedBy,tea.ReasonForLeave,tea.NoofLeaves,tea.StartDate,tea.EndDate,tea.RequestedDate,tea.LeaveStatus,tea.LeaveType,tea.RequestedTo,concat(tr.FirstName, ' ' ,LastName)as name from campus_teachers_leave_request tea join campus_teachers tr on tea.RequestedBy=tr.TeacherID join campus_new_leave_bank nlb on nlb.Leave_ID = tea.LeaveType where tea.RequestedTo like ? or tea.RequestedBy like ? or tea.ReasonForLeave like ? or tea.NoofLeaves like ? or tea.StartDate like ? or tea.EndDate like ? or tea.RequestedDate like ? or tea.LeaveStatus like ? or tea.LeaveType like ? or tr.FirstName like ? or tr.LastName like?";

    public static final String GET_LEAVE_SEARCH_BY_TEACHER = "select st.SNO,st.RequestedBy,st.ReasonForLeave,st.NoofLeaves,st.StartDate,st.EndDate,st.RequestedDate, st.LeaveStatus,st.LeaveType,st.RequestedTo,concat(par.FirstName, ' ' ,LastName)as name  from campus_student_leave_request st join campus_parents par on par.ParentID=st.RequestedBy where st.RequestedTo like ? or st.RequestedBy like ? or st.ReasonForLeave like ? or st.NoofLeaves like ? or st.StartDate like ? or st.EndDate like ? or st.RequestedDate like ? or st.LeaveStatus like ? or st.LeaveType like ? or par.FirstName like ? or par.LastName like ?";
    
    public static final String SEARCH_LEAVE_REQUEST_DETAILS = "select rq.SNO,rq.NoofLeaves,rq.StartDate,rq.EndDate,rq.SessionStart,rq.SessionEnd,rq.LeaveStatus,rq.LeaveType,concat(tea.FirstName, '' ,LastName) as teachername,usr.type,concat(stu.student_fname_var,' ',student_lname_var)as studentname,rq.ReasonForLeave from campus_student_leave_request rq join campus_teachers tea on rq.RequestedTo=tea.TeacherID join campus_student stu on rq.studentId=stu.student_id_int join campus_user usr on rq.RequestedBy=usr.employeecode where rq.NoofLeaves like ? or rq.StartDate like ? or rq.EndDate like ? or rq.SessionStart like ? or rq.SessionEnd like ? or rq.LeaveStatus like ? or rq.LeaveType like ? or tea.FirstName like ? or tea.LastName like ? or usr.type like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or rq.ReasonForLeave like ? "; 

    public static final String GET_LEAVE_REQUEST_BY_PARENTS = "SELECT rq.SNO,rq.NoofLeaves,rq.StartDate,rq.EndDate,rq.SessionStart,rq.SessionEnd,rq.LeaveStatus,rq.LeaveType,CONCAT(tea.FirstName, '' ,LastName) AS teachername,usr.type,CONCAT(stu.student_fname_var,' ',student_lname_var)AS studentname,rq.ReasonForLeave FROM campus_student_leave_request rq LEFT JOIN campus_teachers tea ON rq.RequestedTo=tea.TeacherID LEFT JOIN campus_student stu ON rq.studentId=stu.student_id_int LEFT JOIN campus_user usr ON rq.RequestedBy=usr.employeecode  WHERE  rq.RequestedBy=? ORDER BY rq.LeaveStatus='NOTAPPROVED'";

    public static final String GET_LEAVE_REQUEST_BY_TEACHER = "select nlb.Leave_name ,rq.SNO,rq.NoofLeaves,rq.StartDate,rq.EndDate,rq.SessionStart,rq.SessionEnd,rq.LeaveStatus,rq.LeaveType,concat(tea.FirstName, '' ,LastName) as teachername,rq.ReasonForLeave,case when rq.TotalDaysAproved = 0 then '-' else rq.TotalDaysAproved end TotalDaysAproved from campus_new_leave_bank nlb,campus_teachers_leave_request rq,campus_teachers tea where nlb.Leave_ID=rq.LeaveType and rq.RequestedBy=tea.TeacherID and rq.RequestedBy=? and rq.LeaveStatus!= 'Canceled' order by rq.LeaveStatus='Approved'";

	public static final String VIEWEXAMDETAILS = "select cs.subjectID,cs.subjectName,ed.startdate,starttime,endtime from campus_exam_timetable et join campus_examination ex on et.examcode = ex.examid join campus_detailed_timetable ed on ed.examtimetablecode = et.timetable_id join campus_subject cs on cs.subjectID = ed.sub_id where et.examcode = ? and et.accyear_id = ? and et.loc_id = ? and et.class_id = ? and et.section_id = ? order by subjectName";

	public static final String EXAMDETAILS = "select examid,examname,startdate,enddate from campus_examination where examid = ? and acadamicyear = ? and loc_id = ?";

	public static final String STU_DETAILS = "select csc.classdetail_id_int,csc.classsection_id_int,csc.locationId from campus_student_classdetails csc join campus_student st on csc.student_id_int = st.student_id_int where csc.student_id_int = ? and csc.fms_acadamicyear_id_int = ?";
}















