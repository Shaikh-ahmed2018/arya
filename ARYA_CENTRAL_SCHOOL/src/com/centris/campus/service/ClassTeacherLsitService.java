package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.vo.ClassTeacherVo;

public interface ClassTeacherLsitService {

	ArrayList<ClassTeacherVo> getClassTeacherListService();

	ClassTeacherVo editClassTeacherService(ClassTeacherVo vo);

	String saveClassTeacherService(ClassTeacherVo vo);

	boolean validateClassTeacherService(ClassTeacherVo vo);

	ArrayList<ClassTeacherVo> getSearchClassTeacherListService(String searchTerm);

}
