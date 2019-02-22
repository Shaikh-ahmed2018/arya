package com.centris.campus.service;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.vo.UploadLibraryXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public interface UploadLibraryXSLservice {
	
	public Set<UploadLibraryXlsVO> insertLibXSL(List<UploadLibraryXlsPOJO> list, String username, String studentid,String currentLoc);

	public Set<UploadLibraryXlsVO> insertsubscriberDetailXSL(List<UploadLibraryXlsPOJO> list, String username,
			String demo, String schoolLocation);
}
