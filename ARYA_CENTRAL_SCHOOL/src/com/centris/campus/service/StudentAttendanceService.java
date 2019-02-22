package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.vo.StudentAttendanceVo;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;

public interface StudentAttendanceService {
	
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate,String endDate);
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo);
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo);

	List<ParentVO> getAllStudentService(String classVal, String sectionVal);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportService(
			StudentAttendanceReportVo vo);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportService(
			StudentAttendanceReportVo stuvo);

	StreamDetailsVO getStreamNameService(String stream);

	ClassPojo getClassNameService(String classname);

	SectionPojo getSectionNameService(String sectionname);

	ParentVO getStudentNameService(String student);
	
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo);
	
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo);
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId);
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId);
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo);
	public List<ParentVO> getStudentByTransport(String classId, String sectionId);
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId,String accYear);
	public List<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname);
	public List<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid);
	public List<StudentAttendanceVo> getTeacherList(String locationid);
	public List<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear, String classname,String sectionid, String teacherid);
	public List<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate);
}
