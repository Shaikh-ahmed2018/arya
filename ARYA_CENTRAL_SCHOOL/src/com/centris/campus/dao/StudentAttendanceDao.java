package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public interface StudentAttendanceDao {

	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate);
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo);
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo);

	List<LstmsStudentPOJO> getAllStudentDao(String classVal, String sectionVal);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportDao(
			StudentAttendanceReportVo vo);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportDao(
			StudentAttendanceReportVo stuvo);

	StreamDetailsVO getStreamNameDaoImpl(String stream);

	ClassPojo getClassNameDaoImpl(String classname);

	SectionPojo getSectionNameDaoImpl(String sectionname);

	ParentVO getStudentNameDaoImpl(String student);
	
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo);
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo);
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId);
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId);
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo);
	public List<LstmsStudentPOJO> getStudentByTransport(String classId, String sectionId);
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear);
	public ArrayList<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname);
	public ArrayList<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid);
	public ArrayList<StudentAttendanceVo> getTeacherList(String locationid);
	public ArrayList<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear,String classname, String sectionid, String teacherid);
	public ArrayList<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate);



}
