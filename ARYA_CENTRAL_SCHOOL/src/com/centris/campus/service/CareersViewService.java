package com.centris.campus.service;

import java.util.List;

import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.vo.CareerUpdatePopuVO;
import com.centris.campus.vo.CareersViewVo;

public interface CareersViewService {

	List<CareersViewVo> getCareersView();

	public CareersViewVo getValues(CareersViewPojo pojo);

	public boolean getDelete(CareersViewPojo pojo);

	List<CareersViewVo> getAllCareersView();

	public String addJobs(CareersViewPojo pojo);

	public boolean checkTitle(CareersViewPojo pojo);

	public List<CareersViewVo> searchDetails(String name);
}
