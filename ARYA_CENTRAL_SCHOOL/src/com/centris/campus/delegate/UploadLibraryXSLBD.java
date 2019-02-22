package com.centris.campus.delegate;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.StockEntryPOJO;
import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.serviceImpl.UploadLibraryXSLServiceIMPL;
import com.centris.campus.serviceImpl.UploadStudentXSLServiceIMPL;
import com.centris.campus.vo.StockEntryVo;
import com.centris.campus.vo.UploadLibraryXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public class UploadLibraryXSLBD {

	public Set<UploadLibraryXlsVO> insertLibXSL(List<UploadLibraryXlsPOJO> list, String username, String duplicate, String schoolLocation) {
		return new UploadLibraryXSLServiceIMPL().insertLibXSL(list, username,duplicate,schoolLocation);
	}

	public Set<UploadLibraryXlsVO> insertsubscriberDetailXSL(List<UploadLibraryXlsPOJO> list, String username,
			String demo, String schoolLocation) {
		return new UploadLibraryXSLServiceIMPL().insertsubscriberDetailXSL(list,username,demo,schoolLocation);
	}

	public Set<StockEntryVo> insertStockEntryXSL(List<StockEntryPOJO> list, String username, String duplicate, String schoolLocation) {
		return new UploadLibraryXSLServiceIMPL().insertStockEntryXSL(list, username,duplicate,schoolLocation);
	}

}
