package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.service.StudentAttendanceService;
import com.centris.campus.serviceImpl.StudentAttendanceServiceImpl;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceBD {

	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate,String endDate) {
		// TODO Auto-generated method stub
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getStudentsAttendanceList(startDate,endDate);
	}
	
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo) {
		// TODO Auto-generated method stub
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getStudentAttendanceDetails(studentAttPojo);
	}
	
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo) {
		// TODO Auto-generated method stub
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.updateAttendanceStatus(studentAttPojo);
	}
	
public List<ParentVO> getAllStudent(String classVal, String sectionVal) {
		
		
		StudentAttendanceService service = new StudentAttendanceServiceImpl();
		
		return service.getAllStudentService(classVal,sectionVal);
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportBD(
			StudentAttendanceReportVo vo) {
		
    StudentAttendanceService service = new StudentAttendanceServiceImpl();
		
		return service.getStudentAttendanceReportService(vo);
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportBD(
			StudentAttendanceReportVo stuvo) {
		
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getStudentAttendanceListReportService(stuvo);
	}

	public StreamDetailsVO getStreamNameBD(String stream) {
		
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getStreamNameService(stream);
	}

	public ClassPojo getClassNameBD(String classname) {
	
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getClassNameService(classname);
	}

	public SectionPojo getSectionNameBD(String sectionname) {
		
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getSectionNameService(sectionname);
	}

	public ParentVO getStudentNameBD(String student) {
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getStudentNameService(student);
	}

	
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo) {
		
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getStudentPeriodAttendance(AttendancePojo);
	}
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo) {
		
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.updateStudentPeriodAtt(AttendancePojo);
	}

	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId) {

		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
			
			return service.getteacherByClass(classId,sectionId);
	}

	public ArrayList<StudentAttendanceVo> getClassSpec(String classId) {
		StudentAttendanceService service = new StudentAttendanceServiceImpl();
		
		return service.getClassSpec(classId);
	}

	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo) {
		StudentAttendanceService service = new StudentAttendanceServiceImpl();
		
		return service.editAttendance(pojo);
	}

	public List<ParentVO> getStudentByTransport(String classId, String sectionId) {
StudentAttendanceService service = new StudentAttendanceServiceImpl();
		
		return service.getStudentByTransport(classId,sectionId);
	}
	
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId,String accYear) {
		
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.searchStudentsAttendanceList(locationId,accYear);
	}

	public List<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname) {
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getAttendenceByClassList(locationid,accyear,classname);
	}

	public List<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid) {
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getAttendenceByClassSectionList(locationid,accyear,classname,sectionid);
	}

	public List<StudentAttendanceVo> getTeacherList(String locationid) {
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getTeacherList(locationid);
	}

	public List<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear, String classname,String sectionid, String teacherid) {
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
	}

	public List<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate) {
		StudentAttendanceService attService=new StudentAttendanceServiceImpl();
		
		return attService.getAttendanceListByDate(startdate,enddate);
	}
	
}
