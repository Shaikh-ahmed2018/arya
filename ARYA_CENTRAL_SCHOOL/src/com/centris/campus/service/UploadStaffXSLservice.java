package com.centris.campus.service;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public interface UploadStaffXSLservice {
	
	public Set<UploadStaffXlsVO> insertStaffXSL(List<UploadStaffXlsPOJO> list, String username, String studentid);

}
