package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;



public interface ReligionCasteCasteCategoryService {

	//
	public String insertReligion(ReligionCasteCasteCategoryForm detailsForm);

	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName);

	public List<ReligionCasteCasteCategoryVo> getReligionDetails();

	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo);

	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo);

	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName);

	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId);

	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo);

	public String insertCaste(ReligionCasteCasteCategoryForm detailsForm);

	public ReligionCasteCasteCategoryVo getSingleCaste(
			ReligionCasteCasteCategoryPojo detailsPojo);

	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(
			String searchName);

	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails();

	public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm);

	public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo);

	public ReligionCasteCasteCategoryVo getSingleCasteCategory(
			ReligionCasteCasteCategoryPojo detailsPojo);

	public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm);

	public List<ReligionCasteCasteCategoryVo> getOccupationDetails();
 
	public ReligionCasteCasteCategoryVo getSingleOccupation(
			ReligionCasteCasteCategoryPojo detailsPojo);

	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo);

	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId);

	public List<ReligionCasteCasteCategoryVo> getListOfCaste();

	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId);

	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
			String searchName);

	
 
}
