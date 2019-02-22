package com.centris.campus.util;

public class AssignmentUploadUtilConstants {
	
	public static final String GET_STUDENT_DETAILS = "select student_id_int,student_admissionno_var,concat(student_fname_var,' ',student_lname_var) studentName from campus_student where classsection_id_int=?";
	
	public static final String INSERT_ASSIGNMENT = "insert into campus_assignment(AssignmentCode,ClassID,SectionID,SpecializationId,locationId,AssignmentDate,CompletionDate,Name,Description,SubjectID,MaxMarks,AcadamicID,CreatedBy,CreatedDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String GET_ASSIGNMENT = "select a.AssignmentCode,a.Name,a.AssignmentDate,a.CompletionDate,case when a.SubjectID='general' then a.SubjectID else (select subjectName from campus_subject where subjectID = a.SubjectID) end as subjectName,cls.classdetails_name_var,sec.classsection_name_var,a.MaxMarks from campus_assignment a,campus_classsection sec,campus_classdetail cls where a.ClassID=cls.classdetail_id_int and a.SectionID=sec.classsection_id_int and a.AcadamicID=? and CreatedBy=? and Name like ? order by cls.classdetails_name_var,sec.classsection_name_var ASC";

	public static final String GET_ASSIGNMENT_DETAILS = "select s.student_id_int,s.student_admissionno_var,concat(s.student_fname_var,' ',s.student_lname_var) as studentName,ad.ActualCompletionDate,ad.AcquiredMarks,ad.Remarks from campus_assignmentdetails ad,campus_student s where s.student_id_int=ad.student_id and ad.AssignmentCode=?";

	public static final String GET_SINGLE_ASSIGNMENT = "select a.AssignmentCode,a.Name,a.AssignmentDate,a.CompletionDate,case when a.SubjectID='general' then a.SubjectID else (select subjectName from campus_subject where subjectID = a.SubjectID) end as subjectName,cls.classdetails_name_var,sec.classsection_name_var,a.MaxMarks,a.SpecializationId from campus_assignment a,campus_classsection sec,campus_classdetail cls where a.ClassID=cls.classdetail_id_int and a.SectionID=sec.classsection_id_int and a.AssignmentCode=?";

	public static final String UPDATE_ASSIGNMENT_DETAILS = "update campus_assignmentdetails set ActualCompletionDate=?,AcquiredMarks=?,Remarks=?,UpdatedBy=?,UpdatedTime=? where AssignmentCode=? and student_id=?";
	
	public static final String INSERT_ASSIGNMENT_DETAILS = "Insert into campus_assignmentdetails(Acc_id,Loc_id,AssignmentCode,student_id,MaxMarks,ActualCompletionDate,SpecializationId,CreatedBy,CreatedTime) values(?,?,?,?,?,?,?,?,?)";

	public static final String DELETE_ASSIGNMENT = "delete from campus_assignment where AssignmentCode=?";
	
	public static final String DELETE_ASSIGNMENT_DETAILS = "delete from campus_assignmentdetails where AssignmentCode=?";

	public static final String INSERT_PROJECT = "INSERT INTO `campus_project`(`projectCode`,`projectName`,`AccYear`,`ClassId`,`SectionId`,`SpecializationId`,`SubjectId`,`LocationId`,`AssignedDate`,`SubmissionDate`,`Description`,`MaxMarks`,`CreatedBy`,`CreatedDate`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String INSERT_PROJECT_STUDENTWISE = "INSERT INTO `campus_project_studentwise`(`AcademicYearId`,`LocationId`,`projectCode`,`studentId`,`MaxMarks`,`SubmissionDate`,`CreatedBy`,`CreatedTime`) VALUES(?,?,?,?,?,?,?,?)";

	public static final String GET_PROJECT_LIST ="select pj.projectCode,pj.projectName,pj.ClassId,pj.SectionId,pj.AssignedDate,pj.SubmissionDate,pj.MaxMarks,cls.classdetails_name_var,sec.classsection_name_var from campus_project pj,campus_classsection sec,campus_classdetail cls where pj.ClassId=cls.classdetail_id_int and pj.SectionId=sec.classsection_id_int and pj.AccYear=? and pj.CreatedBy=? and pj.projectName like ? order by cls.classdetails_name_var,sec.classsection_name_var ASC";
	
	public static final String GET_PROJECT_DETAILS = "select s.student_id_int,s.student_admissionno_var,concat(s.student_fname_var,' ',s.student_lname_var) as studentName,pjs.SubmissionDate,pjs.obtainedMarks,pjs.Remarks from campus_project_studentwise pjs,campus_student s where s.student_id_int=pjs.studentId and pjs.projectCode=?";

	public static final String GET_SINGLE_PROJECT = "select pj.projectCode,pj.projectName,pj.AssignedDate,pj.SubmissionDate,pj.MaxMarks,pj.SpecializationId,pj.SubjectId,cls.classdetails_name_var,sec.classsection_name_var from campus_project pj,campus_classsection sec,campus_classdetail cls where pj.ClassId=cls.classdetail_id_int and pj.SectionId=sec.classsection_id_int and pj.projectCode=?";

	public static final String UPDATE_PROJECT_DETAILS = "update campus_project_studentwise set SubmissionDate=?,obtainedMarks=?,Remarks=?,UpdatedBy=?,UpdatedTime=? where projectCode=? and studentId=?";

	public static final String DELETE_PROJECT = "delete from campus_project where projectCode=?";

	public static final String DELETE_PROJECT_DETAILS = "delete from campus_project_studentwise where projectCode=?";


}
