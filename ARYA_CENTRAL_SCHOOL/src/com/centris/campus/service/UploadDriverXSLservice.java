package com.centris.campus.service;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public interface UploadDriverXSLservice {
	
	public Set<UploadDriverXlsVO> insertDriverXSL(List<UploadDriverXlsPOJO> list, String username, String studentid);

}
