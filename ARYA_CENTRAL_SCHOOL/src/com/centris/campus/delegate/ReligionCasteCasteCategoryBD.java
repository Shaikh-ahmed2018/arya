package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.service.ReligionCasteCasteCategoryService;
import com.centris.campus.service.StreamDetailsService;
import com.centris.campus.serviceImpl.ReligionCasteCasteCategoryServiceImpl;
import com.centris.campus.serviceImpl.StreamDetailsServiceImpl;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class ReligionCasteCasteCategoryBD {

	ReligionCasteCasteCategoryService detailsServices;
	
public String insertReligion(ReligionCasteCasteCategoryForm detailsForm) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertReligion(detailsForm);
}

public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchReligion(searchName);
}

public List<ReligionCasteCasteCategoryVo> getReligionDetails() {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getReligionDetails();
}

public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleReligion(detailsPojo);
}

public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteReligion(detailsPojo);
}

public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchCaste(searchName);
}

public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteDetails(religionId);
}

public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteCaste(detailsPojo);
}

public String insertCaste(ReligionCasteCasteCategoryForm detailsForm) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertCaste(detailsForm);
}

public ReligionCasteCasteCategoryVo getSingleCaste(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleCaste(detailsPojo);
}

public List<ReligionCasteCasteCategoryVo> searchCasteCategory(String searchName) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchCasteCategory(searchName);
}

public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails() {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteCategoryDetails();
}

public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertCasteCategory(detailsForm);
}

public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteCasteCategory(detailsPojo);
}

public ReligionCasteCasteCategoryVo getSingleCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleCasteCategory(detailsPojo);
}

public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertOccupation(detailsForm);
}

public List<ReligionCasteCasteCategoryVo> getOccupationDetails() {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getOccupationDetails();
}

public ReligionCasteCasteCategoryVo getSingleOccupation(
		ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleOccupation(detailsPojo);
}

public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteOccupation(detailsPojo);
}

public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteDetailsList(religionId);
}

public List<ReligionCasteCasteCategoryVo> getListOfCaste() {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getListOfCaste();
}

public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteCategoryListDetails(casteId);
}

public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
		String searchName) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getOccupationDetailsSearch(searchName);
}

}
