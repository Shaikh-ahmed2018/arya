package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.TeacherMappingClassesVo;
import com.centris.campus.vo.TeacherVo;

public interface TeacherDao {
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails();
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1();
	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(AllTeacherDetailsVo obj);
	public boolean deleteStaffDetails(String[] teachercode);
	 public boolean teacherregister(TeacherRegistrationPojo obj);
	 public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID);
	 public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID);
	 public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID);
	 public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName);
	public ArrayList<AllTeacherDetailsVo> reportingToList();
	public ArrayList<AllTeacherDetailsVo> StudentAdmissionNumber(String academicYear);
	public int checkStaffInTDS(String currentUser);
	public boolean deactivateStaffDetails(String[] teachercode);
	public ArrayList<TeacherVo> getdeactivatedTeachers(String teacherId, String registerId, String status);
	public boolean activateStaff(String registerid);
	
	 

}
