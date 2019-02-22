package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.vo.LocationVO;

public interface LocationDao {

	List<LocationVO> getLocationDetails();

	String insertLocationDetailsAction(LocationMasterPojo locationpojo, String idupdate);

	boolean validateLocName(LocationMasterForm form1);

	boolean validateLocNameUpdate(LocationVO validateUpdate);

	LocationVO editLocation(String edit);

	String deleteDepartmentDetails(String[] locid);

	List<LocationVO> searchLocationDetails(String searchName);

}
