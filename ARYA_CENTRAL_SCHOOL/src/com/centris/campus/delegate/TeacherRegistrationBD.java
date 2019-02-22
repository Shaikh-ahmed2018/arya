package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.service.TeacherService;
import com.centris.campus.serviceImpl.TeacherServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.TeacherMappingClassesVo;

public class TeacherRegistrationBD {
	TeacherService serviceobj = new TeacherServiceImpl();

	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails() {
		return serviceobj.getAllTeacherDetails();
	}
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1() {
		return serviceobj.getAllTeacherDetails1();
	}
	
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName) {
		return serviceobj.getSearchTeacherDetails(serchName);
	}

	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(
			AllTeacherDetailsVo obj) {
		return serviceobj.searchStaffDetails(obj);
	}

	public boolean deleteStaffDetails(String[] teachercode) {
		return serviceobj.deleteStaffDetails(teachercode);
	}

	public boolean teacherregister(TeacherForm obj) {
		return serviceobj.teacherregister(obj);
	}

	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj) {
		return serviceobj.getTeacherDetails(obj);

	}

	public String teacherUpdate(TeacherRegistrationPojo obj) {
		return serviceobj.teacherUpdate(obj);
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID) {
		return serviceobj.getMappingClasses(teacherID);
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID) {
		return serviceobj.getMappedSection(teacherID,classId,SectionID);
	}
	
	
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID) {
		return serviceobj.getMappingSubjects(teacherID);
	}

	public ArrayList<AllTeacherDetailsVo> reportingToList() {
		
		return serviceobj.reportingToList();
	}

	public Object StudentAdmissionNumber(String academicYear) {
		// TODO Auto-generated method stub
		return serviceobj.StudentAdmissionNumber(academicYear);
	}

	public int checkStaffInTDS(String currentUser) {
		// TODO Auto-generated method stub
		return serviceobj.checkStaffInTDS(currentUser);
	}
}
