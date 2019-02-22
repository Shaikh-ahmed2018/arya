package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import com.centris.campus.dao.LocationDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.service.LocationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;

public class LocationServiceImpl implements LocationService {

	private static Logger logger = Logger
			.getLogger(LocationServiceImpl.class);
	static LocationDao locationMasterDao;
	static{
		locationMasterDao = new LocationDAOImpl();
	}
	
	@Override
	public List<LocationVO> getLocationDetails() {
		return locationMasterDao.getLocationDetails();
	}

	@Override
	public String insertLocationDetailsAction(LocationMasterForm form,String idupdate) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl: insertDepartmentDetails : Starting");

		String result = null;

		try {
			LocationMasterPojo locationpojo = new LocationMasterPojo();
			String s1 = IDGenerator.getPrimaryKeyID("campus_location");

			locationpojo.setLocId(form.getLoc_id());
			locationpojo.setLocationname(form.getLocationname());
			locationpojo.setDesc(form.getDesc());
			locationpojo.setCreatedBy(form.getCreatedBy());
			locationpojo.setLocation_id(s1);

			result = locationMasterDao.insertLocationDetailsAction(locationpojo,idupdate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl : insertDepartmentDetails: Ending");
		return result;
	}

	@Override
	public boolean validateLocName(LocationMasterForm form1) {
		return locationMasterDao.validateLocName(form1);
	}

	@Override
	public boolean validateLocNameUpdate(LocationVO validateUpdate) {
		return locationMasterDao.validateLocNameUpdate(validateUpdate);
	}

	@Override
	public LocationVO editLocation(String edit) {
		return locationMasterDao.editLocation(edit);
	}

	@Override
	public String deleteDepartmentDetails(String[] locid) {
		return locationMasterDao.deleteDepartmentDetails(locid);
	}

	@Override
	public List<LocationVO> searchLocationDetails(String searchName) {
		return locationMasterDao.searchLocationDetails(searchName);
	}

	
}
