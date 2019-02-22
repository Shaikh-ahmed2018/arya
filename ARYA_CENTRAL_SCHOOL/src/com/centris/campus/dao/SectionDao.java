package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;

public interface SectionDao {
	
	public String insertCampusClassSectionDao(SectionPojo campusClassSectionPojo)throws Exception;
	public String updateCampusClassSectionDao(SectionPojo campusClassSectionPojo)throws Exception;
	public boolean deleteCampusClassSectionDao(String[] id, String[] locationList)throws Exception;
	
	public List<SectionPojo>  getCampusClassSectionAndClassDetailsDao(String schoolLocation) throws Exception;
	public List<SectionPojo>  getCampusClassDetailsIDandClassDetailsNameDao(String locationId) throws Exception;
	public boolean checkSectionName(String sectionName, String secDetailId);
	public List<SectionForm> searchSection(SectionForm  searchText);
	public SectionForm editSection(SectionForm classCode);
	public List<SectionPojo>  sectiondetailsdownload() throws Exception;
	public boolean sectionNameCheck(SectionForm sectionForm);


}
