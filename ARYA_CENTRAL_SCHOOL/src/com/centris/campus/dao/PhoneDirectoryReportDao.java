package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.vo.PhoneDirectoryVo;

public interface PhoneDirectoryReportDao {

	List<PhoneDirectoryVo> getPhoneDirectoryNamesDao(PhoneDirectoryVo vo);

	List<PhoneDirectoryVo> getPhoneDirectoryListDao(PhoneDirectoryVo vo);

	PhoneDirectoryVo getSelectedValueNameDao(PhoneDirectoryVo vo);

}
