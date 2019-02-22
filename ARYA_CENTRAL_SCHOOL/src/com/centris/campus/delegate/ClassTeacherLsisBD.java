package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.service.ClassTeacherLsitService;
import com.centris.campus.serviceImpl.ClassTeacherLsitServiceImpl;
import com.centris.campus.vo.ClassTeacherVo;

public class ClassTeacherLsisBD {

	
	ClassTeacherLsitService servise = new ClassTeacherLsitServiceImpl();
	
	
	
	public ArrayList<ClassTeacherVo> getClassTeacherListBD() {
		
		return servise.getClassTeacherListService();
	}



	public ClassTeacherVo editClassTeacherBD(ClassTeacherVo vo) {
		
		return servise. editClassTeacherService(vo);
	}



	public String saveClassTeacherBD(ClassTeacherVo vo) {
		
		return servise.saveClassTeacherService(vo);
	}



	public boolean validateClassTeacherBD(ClassTeacherVo vo) {
		
		return servise.validateClassTeacherService(vo);
	}



	public ArrayList<ClassTeacherVo> getSearchClassTeacherListBD(
			String searchTerm) {
		
		return servise. getSearchClassTeacherListService(searchTerm);
	}

}
