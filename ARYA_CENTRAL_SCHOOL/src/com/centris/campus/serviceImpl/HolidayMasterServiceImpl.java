package com.centris.campus.serviceImpl;


import java.util.ArrayList;

import com.centris.campus.dao.HolidayMasterSDAO;
import com.centris.campus.daoImpl.HolidayMasterDAOImpl;
import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;
import com.centris.campus.service.HolidayMasterService;
import com.centris.campus.vo.HolidayMasterVo;
 
public class HolidayMasterServiceImpl  implements HolidayMasterService {

	HolidayMasterSDAO dao;
	public String addMultipleHoliday(HolidayMasterForm formObj, String usercode) {
		return new HolidayMasterDAOImpl().addMultipleHoliday(formObj,usercode);
	}

	@Override
	public ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year,String schoolLocation) {
		dao = new HolidayMasterDAOImpl();
		return dao.getHolidayDetails(academic_year,schoolLocation);
	}

	@Override
	public HolidayMasterVo editHolidayDetail(String deptId, String date) {
		dao = new HolidayMasterDAOImpl();
		return dao.editHolidayDetail(deptId,date);
	}

	public String addSingleHoliday(HolidayMasterPojo hpojo) {
		return new HolidayMasterDAOImpl().addSingleHolidayDetailDaoImpl(hpojo);
	}

	public Boolean deleteSingleHoliday(String holidaydate, String deptid) {
		return new HolidayMasterDAOImpl().deleteSingleHoliday(holidaydate,deptid);
	}

	public String dateValidate(String dateval, String location, String accYear) {
		return new HolidayMasterDAOImpl().dateValidate(dateval,location,accYear);
	}

	public ArrayList<HolidayMasterVo> searchLocationDetails(String searchterm, String academic_year) {
		return new HolidayMasterDAOImpl().searchLocationDetails(searchterm,academic_year);
	}

	public Boolean deleteSingleHoliday(String[] list) {
		return new HolidayMasterDAOImpl().deleteSingleHoliday(list);
	}

	@Override
	public HolidayMasterVo editHolidayDetail(String deptId) {
		return new HolidayMasterDAOImpl().editHolidayDetail(deptId);
	}

}
