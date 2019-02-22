package com.centris.campus.util;

public class TeacherUtilsConstants {

	public static final String ALL_TEACHER_DETAILS = "select t.registerId,t.Abbreviative_Id,concat(t.FirstName,' ',t.LastName) as teacherName,t.Qualification,d.designationName,t.MobileNo,t.TeacherID, t.emailId,dept.DEPT_NAME,case when t.bankname is null then '-' else t.bankname end  bankname,case when t.pannumber is null then '-' else t.pannumber end pannumber,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC,ss.totalSalary from campus_teachers t left outer join campus_staff_salarydetails ss on ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept where  t.designation=d.DesignationCode and dept.DEPT_ID=t.department and  t.isActive='Y' order by concat(t.FirstName,'',t.LastName)"; 
	public static final String ALL_TEACHER_DETAILS1 = "select t.registerId,t.Abbreviative_Id,concat(t.FirstName,' ',t.LastName) as teacherName,t.Qualification,t.department,d.designationName,t.MobileNo,t.TeacherID, t.emailId,dept.DEPT_NAME,case when t.bankname is null then '-' else t.bankname end  bankname,case when t.pannumber is null then '-' else t.pannumber end pannumber,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC from campus_teachers t left outer join campus_staff_salarydetails ss on ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept where  t.designation=d.DesignationCode and dept.DEPT_ID=t.department and  t.isActive='Y' order by concat(t.FirstName,'',t.LastName)"; 
	public static final String SEARCH_TEACHER_DETAILS="select t.registerId,t.Abbreviative_Id,concat(t.FirstName,'',t.LastName) as teacherName,t.Qualification,d.designationName,t.mobileNo,t.TeacherID,t.emailId from campus_teachers t,campus_designation d where t.designation=d.DesignationCode and t.isActive='Y' and (concat(t.FirstName,'',t.LastName) like ? or t.mobileNo like ?  or t.Qualification like ? or d.designationName like ? or t.emailId like ? or t.registerId like ? or t.Abbreviative_Id like ?)";
    public static final String DELETE_TEACHER_DETAILS="update campus_teachers  set isActive='N' where TeacherID=?";
    public static final String GET_SUBJECTS = "select distinct(subjectName),subjectID  from campus_subject WHERE classid=? group by subjectName order by subjectName";
    public static final String GET_TEACHER_COUNT = "select count(*) from campus_teachers where UserName=? and TeacherID!=?";
    public static final String GET_TEACHERMAIL_COUNT = "select count(*) from campus_teachers where emailId=? and TeacherID!=?";
    public static final String CHECK_TACHER_COUNT_ = "select count(*) from campus_teachers where UserName=? and department=? and designation=? and MobileNo=? and DateOfBirth=? and DateOfJoining=? ";
    public static final String INSERT_TEACHER = "insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,presentAddress,MobileNo,UserName,emailId,primarySubject,secondarySubject,profilePath,imagePath,DateOfBirth,DateOfJoining,designation,idProofPath,teachingType,department,gender,bankname,accountnumber,pannumber,bloodgroup,fathername,mothername,permanentAddress,createdby,createddate,registerId,password,role,document1,document2,document3,document4,document5,reportingTo,is_student_studying,studentName,student_admission_id,fatherMobile,motherMobile,Abbreviative_Id,aadhaarnumber,maritalstatus,spousename,spouseMobile,Loc_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String GET_SERVER_URL = "select URL from campus_settings";
    public static final String GET_SINGLE_TEACHER_DEATILS="SELECT tea.*,usr.type, loc.Location_Name, loc.Location_Id FROM campus_teachers tea JOIN campus_user usr ON usr.employeecode = tea.TeacherID LEFT OUTER JOIN campus_location loc ON loc.Location_Id=tea.Loc_ID where tea.TeacherID=?";
    public static final String UPDATE_TEACHER="update campus_teachers set FirstName=?,LastName=?,Qualification=?,presentAddress=?,MobileNo=?,UserName=?,emailId=?,primarySubject=?,secondarySubject=?,profilePath=?,imagePath=?,DateOfBirth=?,DateOfJoining=?,designation=?,idProofPath=?,teachingType=?,department=?,gender=?,bloodgroup=?,fathername=?,mothername=?,permanentAddress=?,updatedby=?,updateddate=?,role=?,reportingTo=?, bankname=?, accountnumber=?, pannumber=?, document1=?,document2=?,document3=?,document4=?,document5=?,is_student_studying=?,studentName=?,student_admission_id=?, fatherMobile=?, motherMobile=?,Abbreviative_Id=?,aadhaarnumber=?,maritalstatus=?,spousename=?,spouseMobile=?,Loc_ID=? where TeacherID=?";
    public static final String GET_REGISTRAION_COUNT="select count(*) from campus_teachers where registerId=? and TeacherID!=?";
 
   
   //For Class Mapping
   public static final String GET_MAPPING_CLASSES="select CTCode,ClassCode,SectionCode from campus_classteacher where TeacherCode=?";
   public static final String GET_UNMAPPING_SECTION="select se.classsection_id_int,se.classsection_name_var from campus_classsection se join campus_classdetail cl on cl.classdetail_id_int=se.classdetail_id_int and cl.locationId=se.locationId where se.classdetail_id_int=? and se.classsection_id_int not in (select SectionCode from campus_classteacher where TeacherCode!=? and  ClassCode=?) order by se.classsection_name_var";
   public static final String GET_SECTION_NAME="select classsection_name_var from campus_classsection where classsection_id_int=?";
   
   public static final String DELETE_CLAA_MAPPINGS="delete from campus_classteacher where TeacherCode=?";
   public static final String INSERT_CLASS_MAPPINGS="insert into campus_classteacher(CTCode,ClassCode,SectionCode,TeacherCode,CreateTime,CreateUser) values(?,?,?,?,?,?)";
   
   
   //For Subject Mappings
   
   public static final String DELETE_SUBJECT_MAPPINGS="delete from campus_teachersettings where teacherID=?";
   public static final String INSERT_SUBJECTS_MAPPINGS="insert into campus_teachersettings(teacherID,classID,subjectID,createdBy,createTime) values(?,?,?,?,?)";
   public static final String GET_MAPPING_SUBJECTS="select teacherID,classID,subjectID from campus_teachersettings where teacherID=?";
   

   public static final String SEARCH_TEACHER_EMPLOYEMENT_DETAILS = "select t.registerId,concat(t.FirstName,'',t.LastName) as teacherName,t.Qualification,d.designationName,t.MobileNo,t.TeacherID,t.emailId,dept.DEPT_NAME,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC,ss.totalSalary from campus_teachers t left outer join campus_staff_salarydetails ss on ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept where  t.designation=d.DesignationCode and dept.DEPT_ID=t.department and  t.isActive='Y' and (t.FirstName like ? or t.registerId like ? or dept.DEPT_NAME like ?)";
   public static final String REPORTING_TO_LIST = "SELECT TeacherId, CONCAT(FirstName,' ',LastName) AS NAME FROM campus_teachers WHERE isActive='Y'  ORDER BY FirstName";
   
   
   //Insert Into user table
   
   public static final String INSERT_USER_DETAILS = "insert into campus_user(usercode,employeecode,username,password,role,type,createuser,createdate) values(?,?,?,?,?,?,?,?)";
   
   public static final String UPDATE_USER_DETAILS = "update campus_user set username=?,role=?,modifyuser=?,modifydate=? where employeecode=?";
   public static final String GET_STUDENT_ADMISSION_DETAILS = "select student_admissionno_var,student_id_int from campus_student where fms_acadamicyear_id_int=?";
   public static final String GET_SINGLE_TEACHER_DEATIL="select * from campus_teachers where registerId=?";

   
   public static final String GET_ABBREVATIVE_COUNT="select count(*) from campus_teachers where Abbreviative_Id =?";
   
   public static final String GET_LEAVE_TYPES="select * from campus_new_leave_bank where Accy_Id=? AND Loc_ID=?";
   public static final String GET_NO_OF_LEAVES="select No_Of_Leaves from campus_new_leave_bank where Accy_Id=? AND Loc_ID=? AND Leave_ID=?";
   //`campus_teacher_new_leave_bank_details``AccYearCode``EmpId``Leave_Type``Leave_Name``total_available``total_consumed``total_avaliable_leaves``LastUpdate``UpdatedBy``Date_Of_Join``LOC_Id`
   public static final String INSERT_LEAVE = "";
   
   public static final String GET_STAFF_COUNT="select count(TeacherID) from campus_staff_income_section where TeacherID=?";

}
