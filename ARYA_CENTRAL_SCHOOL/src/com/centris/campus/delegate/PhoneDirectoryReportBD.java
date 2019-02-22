package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.PhoneDirectoryReportService;
import com.centris.campus.serviceImpl.PhoneDirectoryReportServiceImpl;
import com.centris.campus.vo.PhoneDirectoryVo;

public class PhoneDirectoryReportBD {
	
	PhoneDirectoryReportService service = new PhoneDirectoryReportServiceImpl();

	public List<PhoneDirectoryVo> getPhoneDirectoryNamesBD(
			PhoneDirectoryVo vo) {
	
		return service.getPhoneDirectoryNamesService(vo);
	}

	public List<PhoneDirectoryVo> getPhoneDirectoryListBD(PhoneDirectoryVo vo) {
		
		return service.getPhoneDirectoryListService(vo);
	}

	public PhoneDirectoryVo getSelectedValueNameBD(PhoneDirectoryVo vo) {
	
		return service.getSelectedValueNameService(vo);
	}

	

}
