package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.TeacherMappingClassesVo;

public interface TeacherService {
     
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails();
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1();
	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(AllTeacherDetailsVo obj);
	public boolean deleteStaffDetails(String[] teachercode);
	public boolean teacherregister(TeacherForm obj);
	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj);
	public String teacherUpdate(TeacherRegistrationPojo obj);
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID);
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID);
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID);
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName);
	public ArrayList<AllTeacherDetailsVo> reportingToList();
	public Object StudentAdmissionNumber(String academicYear);
	public int checkStaffInTDS(String currentUser);
}
