package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.service.ClassService;
import com.centris.campus.serviceImpl.ClassServiceImpl;

public class ClassBD {
	
	public List<ClassPojo> getClassDetails(String schoolLocation) {
		ClassService classService=new ClassServiceImpl();
		List<ClassPojo> classList=classService.getClassDetails(schoolLocation);
		return classList;
	}
	
	public String createClass(ClassPojo classPojo,String createUser, String classcode) {
		ClassService classService=new ClassServiceImpl();
		return classService.createClass(classPojo,createUser,classcode);
	}
	
	public List<ClassPojo> getStreamDetailBD(String school) {
		ClassService classService=new ClassServiceImpl();
		List<ClassPojo> classList=classService.getStreamDetailService(school);
		return classList;
	}
	
	public boolean classNameCheck(ClassPojo classPojo) {
		ClassService classService=new ClassServiceImpl();
		return classService.classNameCheck(classPojo);
	}
	
	public boolean updateclassNameCheck(ClassPojo classPojo) {
		ClassService classService=new ClassServiceImpl();
		return classService.updateclassNameCheck(classPojo);
	}
	
	public ClassPojo editClass(String classCode) {
		ClassService classService=new ClassServiceImpl();
		return classService.editClass(classCode);
	}
	
	public boolean deleteClass(String[] classcode, String[] locationcode) {
		ClassService classService=new ClassServiceImpl();
		return classService.deleteClass(classcode,locationcode);
	}
	
	public boolean updateClass(ClassPojo classPojo) {
		ClassService classService=new ClassServiceImpl();
		return classService.updateClass(classPojo);
	}
	
	public List<ClassPojo> searchClassDetails(String searchText) {
		ClassService classService=new ClassServiceImpl();
		return classService.searchClassDetails(searchText);
	}

	public List<ClassPojo> getClassDetailsOnChangeFunction(String streamId,String locationid) {
		return ClassServiceImpl.getClassDetailsOnChangeFunction( streamId, locationid);
		
	}

	

}
