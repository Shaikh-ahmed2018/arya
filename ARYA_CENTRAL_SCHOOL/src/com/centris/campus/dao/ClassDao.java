package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassForm;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.vo.AcademicYearVO;

public interface ClassDao {
	
	public List<ClassPojo> getClassDetails(String schoolLocation) ;
	public String createClass(ClassPojo classPojo,String createUser, String classcode);
	List<ClassPojo> getStreamDetailDao(String school);
	public boolean classNameCheck(ClassPojo classPojo);
	public ClassPojo editClass(String classCode);
	public boolean updateclassNameCheck(ClassPojo classPojo);
	public boolean deleteClass(String[] classCode, String[] locationCode);
	public boolean updateClass(ClassPojo classPojo);
	public List<ClassPojo> searchClassDetails(String  searchText);

}
