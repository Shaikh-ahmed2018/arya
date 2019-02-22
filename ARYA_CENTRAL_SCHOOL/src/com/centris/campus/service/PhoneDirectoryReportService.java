package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.PhoneDirectoryVo;

public interface PhoneDirectoryReportService {

     List<PhoneDirectoryVo> getPhoneDirectoryNamesService(
			PhoneDirectoryVo vo);

	List<PhoneDirectoryVo> getPhoneDirectoryListService(PhoneDirectoryVo vo);

	PhoneDirectoryVo getSelectedValueNameService(PhoneDirectoryVo vo);

}
