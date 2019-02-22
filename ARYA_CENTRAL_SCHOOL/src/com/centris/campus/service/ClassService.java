package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassForm;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.vo.AcademicYearVO;

public interface ClassService{
	
	List<ClassPojo> getClassDetails(String schoolLocation);
	public String createClass(ClassPojo classPojo,String createUser, String vehiclecode);
	public List<ClassPojo> getStreamDetailService(String school);
	public boolean classNameCheck(ClassPojo classPojo);
	public boolean updateclassNameCheck(ClassPojo classPojo);
	public ClassPojo editClass(String classCode);
	public boolean deleteClass(String[] classcode, String[] locationcode);
	public boolean updateClass(ClassPojo classPojo);
	public List<ClassPojo> searchClassDetails(String searchText);


}
