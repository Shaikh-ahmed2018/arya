package com.centris.campus.util;

public class SubjectSqlUtils {
	
	public static final String VIEW_SUBJECT_DETAILS="select DISTINCT cs.specialization,cs.locationId,cl.Location_Name,cs.subjectID ,cs.subjectName,cs.syllabous,cs.decription, cs.totalMarks, cs.passMarks, cd.classdetails_name_var  from campus_subject cs ,campus_classdetail cd,campus_location cl where  cs.locationId=cl.Location_Id and cs.classid=cd.classdetail_id_int and cs.status='active' and cs.locationId like ? order by length(classid),classid,cs.subjectName"; 
    public static final String DELETE_SUBJECT_DETAILS="delete from campus_subject where subjectID=? and locationId=?";
    public static final String SEARCH_SUBJECT_DETAILS="select DISTINCT ccsp.Specialization_name,cl.Location_Name,s.*,cd.classdetails_name_var from campus_subject s,campus_classdetail cd,campus_location cl,campus_class_specialization ccsp where s.locationId=cl.Location_Id and s.specialization=ccsp.Specialization_Id and (cl.Location_Name like ? or cd.classdetails_name_var like ? or ccsp.Specialization_name like ? or s.subjectName like ? or s.decription like ? or s.totalMarks like ? or s.passMarks like ? ) and s.classid=cd.classdetail_id_int and s.locationId like ?";
    public static final String INSERT_ADDSUBJECT_FORM = "insert into campus_subject(subjectID,subjectName,syllabous,classid,decription,createdby,totalMarks,passMarks,locationId,specialization,Sub_Code,isLanguage) values(?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_SINGLE_SUBJECT_DETAILS="select * from campus_subject  where subjectID=?";
    public static final String UPDATE_SUBJECT = "update campus_subject set subjectName=? ,syllabous=?,classid=?, decription=?, updateby=? ,updatedtime=?, totalMarks=?, passMarks=?,locationId=?,specialization=?,Sub_Code=? where subjectID=?";
    public static final String VALIDATE_CLASS_SUBJECT = "select count(*) count from campus_subject where subjectName=? and classid=?";
    public static final String ViewAllSubjectsPath = "select * from campus_subject where subjectID=?";
    public static final String GET_SYLLABUS_PATH = "select syllabous,subjectName from campus_subject where subjectID=?";
    public static final String GET_ISSUED_FORM_DETAILS="select concat(student_first_name,student_last_name) as studentname,email,mobile_number,address,stu_parrelationship,stream_id,class_id,parents_name from campus_parent_enquiry_details where enquiry_id=?";
	public static final String VIEW_LAB_DETAILS = "SELECT loc.Location_Name,lab.Lab_Name,lab.Total_Marks,CASE WHEN spe.Specialization_name IS NULL THEN 'General' ELSE spe.Specialization_name END Specialization_name,cd.classdetails_name_var,lab.Pass_Marks,lab.Lab_Code,lab.Description,lab.Syllabus FROM campus_location loc JOIN laboratory_details lab ON loc.Location_Id = lab.School_Name JOIN campus_classdetail cd ON lab.Class_Name = cd.classdetail_id_int LEFT JOIN campus_class_specialization spe ON spe.Specialization_Id = lab.Specialization WHERE cd.locationId = lab.School_Name ORDER BY LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,spe.Specialization_name,lab.Lab_Name";
	 public static final String VALIDATE_CLASS_LAB = "select count(*) count from campus_subject where subjectName=? and classid=?";
	public static final String INSERT_LAB_FORM = "insert into laboratory_details(lab_id,School_Name,Class_Name,Subject,Lab_Name,Total_Marks,Specialization,Pass_Marks,Description,Lab_Code,Syllabus) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String DELETE_LAB_DETAILS = "delete from laboratory_details where lab_id=? and School_Name=? ";
	public static final String GET_SINGLE_LAB_DETAILS = "select * from laboratory_details  where lab_id=? ";
	public static final String UPDATE_LAB = "update laboratory_details set Subject=? ,Lab_Name=?,Syllabus=?,Class_Name=?, Description=? , Total_Marks=?, Pass_Marks=?,School_Name=?,Specialization=?,Lab_Code=? where lab_id=?";
	public static final String GET_LAB_SYLLABUS_PATH = "select Syllabus from laboratory_details where lab_id=?";

}
