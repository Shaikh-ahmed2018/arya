package com.centris.campus.service;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.vo.UploadStudentXlsVO;

public interface UploadStudentXSLservice {
	
	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> list, String username, String studentid,String currentLoc);

}
