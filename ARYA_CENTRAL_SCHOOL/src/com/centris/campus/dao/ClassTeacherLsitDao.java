package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.ClassTeacherVo;

public interface ClassTeacherLsitDao {

	ArrayList<ClassTeacherVo> getClassTeacherListDao();

	ClassTeacherVo editClassTeacherDao(ClassTeacherVo vo);

	String saveClassTeacherDao(ClassTeacherVo vo);

	boolean validateClassTeacherDao(ClassTeacherVo vo);

	ArrayList<ClassTeacherVo> getSearchClassTeacherListDao(String searchTerm);

}
