package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.vo.LocationVO;

public interface LocationService {

	List<LocationVO> getLocationDetails();

	String insertLocationDetailsAction(LocationMasterForm form, String locationId);

	boolean validateLocName(LocationMasterForm form1);

	boolean validateLocNameUpdate(LocationVO validateUpdate);

	LocationVO editLocation(String edit);

	String deleteDepartmentDetails(String[] locid);

	List<LocationVO> searchLocationDetails(String searchName);

}
