package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.vo.CareerUpdatePopuVO;
import com.centris.campus.vo.CareersViewVo;

public interface CareersViewDao {

	public List<CareersViewVo> getCareersViewDao();

	public CareersViewVo getValues(CareersViewPojo pojo);

	public boolean getDelete(CareersViewPojo pojo);

	public List<CareersViewVo> getAllCareersView();

	public String addJobs(CareersViewPojo pojo);

	public boolean checkTitle(CareersViewPojo pojo);

	public List<CareersViewVo> searchDetails(String name);

}
