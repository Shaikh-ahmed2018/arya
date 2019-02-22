package com.centris.campus.util;

public class LeaveReportSqlUtils {

	public static final String GET_NO_OF_LEAVES = "SELECT cdes.designationName,ct.teachingType,ct.dateofjoining,CONCAT(ct.FirstName,' ',Lastname) Name,ctb.AccyearCode,ctb.EmpId,GROUP_CONCAT(ctb.`Leave_Type`) `Leave_Type`,GROUP_CONCAT(ctb.`Leave_Name`) `Leave_Name`,GROUP_CONCAT(ctb.`total_available`) `total_available`,GROUP_CONCAT(ctb.`total_consumed`) `total_consumed`,GROUP_CONCAT(ctb.`total_avaliable_leaves`) `total_avaliable_leaves`, GROUP_CONCAT(ShortName) ShortName FROM `campus_teacher_new_leave_bank_details` ctb  JOIN campus_teachers ct ON ctb.EmpId=ct.TeacherID JOIN `campus_new_leave_bank` cl ON ctb.Leave_Type=cl.Leave_ID JOIN campus_designation cdes ON ct.designation=cdes.DesignationCode  WHERE ctb.AccyearCode=? AND ct.teachingType=? AND cl.Loc_ID LIKE ? AND ctb.EmpId LIKE ?  GROUP BY ctb.EmpId ";
	
	public static final String GET_EMP_FOR_LEAVES = "select t.TeacherID,concat(t.FirstName,' ',t.LastName) as teacherName,t.dateofJoining from campus_teachers t where t.TeacherID like ? and t.department like ? and t.teachingType like ? and isActive='Y' order by t.FirstName";

	public static final String GET_LEAVES_TYPE_FROM_ATTENDANCE="select LeaveType,ApprovedStartDate from campus_teachers_leave_request where RequestedBy=? and ((substring(ApprovedStartDate,1,4)=? and substring(ApprovedStartDate,6,2)>06) or(substring(ApprovedStartDate,1,4)=? and substring(ApprovedStartDate,6,2)<05))";
	
	public static final String GET_ACC_YEAR="select acadamic_year from campus_acadamicyear where acadamic_id=?";
	
	public static final String GET_ALL_LABEL="select t.TeacherID,concat(t.FirstName,' ',t.LastName) as teacherName,t.teachingType,d.DEPT_NAME from campus_teachers t join campus_department d on d.DEPT_ID=t.department where t.TeacherID like ? and t.department like ? and t.teachingType like ?";

	public static final String GET_TEACHERS="select DISTINCT TeacherID,concat(FirstName,' ',LastName) as teacherName from campus_teachers where teachingType like ? and department like ? and isActive='Y'";
}
