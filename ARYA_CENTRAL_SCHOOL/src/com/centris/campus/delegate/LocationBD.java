package com.centris.campus.delegate;


import java.util.List;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.service.LocationService;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.vo.LocationVO;

public class LocationBD {
	
	static LocationService locationbd ;
	static{
		locationbd = new LocationServiceImpl();
	}
	public List<LocationVO> getLocationDetails() {
		return locationbd.getLocationDetails();
	}
	public List<LocationVO> searchLocationDetails(String searchName) {
		return locationbd.searchLocationDetails(searchName);
	}
	public String insertLocationDetailsAction(LocationMasterForm form, String locationId) {
		return locationbd.insertLocationDetailsAction(form,locationId);
	}
	public boolean validateLocName(LocationMasterForm form1) {
		return locationbd.validateLocName(form1);
	}
	
	public boolean validateLocNameUpdate(LocationVO validateUpdate) {
		return locationbd.validateLocNameUpdate(validateUpdate);
	}
	public LocationVO editLocation(String edit) {
		return locationbd.editLocation(edit);
	}
	public String deleteDepartmentDetails(String[] locid) {
		return locationbd.deleteDepartmentDetails(locid);
	}
}	

