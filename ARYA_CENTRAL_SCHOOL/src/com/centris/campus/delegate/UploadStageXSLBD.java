package com.centris.campus.delegate;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.serviceImpl.UploadStageXSLServiceIMPL;
import com.centris.campus.serviceImpl.UploadStudentXSLServiceIMPL;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public class UploadStageXSLBD {

	public Set<UploadStageXlsVO> insertStageXSL(List<UploadStageXlsPOJO> list, String username, String duplicate) {
		// TODO Auto-generated method stub
		return new UploadStageXSLServiceIMPL().insertStageXSL(list, username,duplicate);
	}

}
