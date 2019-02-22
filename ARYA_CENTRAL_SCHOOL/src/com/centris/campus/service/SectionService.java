package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.vo.SectionVO;


public interface SectionService  {
	
	public String insertCampusClassSectionService(SectionForm campusClassSectionForm)throws Exception;
	public String updateCampusClassSectionService(SectionForm campusClassSectionForm)throws Exception;
	public boolean deleteCampusClassSectionService(String[] sectionId, String[] locationList)throws Exception;
	
	public List<SectionForm>  getCampusClassSectionAndClassDetailsService(String schoolLocation) throws Exception;
	public List<SectionForm>  getCampusClassDetailsIDandClassDetailsNameService(String locationId) throws Exception;
	public List<SectionForm> searchSection(SectionForm searchText);
	public SectionForm editSection(SectionForm classCode);
	public List<SectionVO>  sectiondetailsdownload() throws Exception;
	public boolean sectionNameCheck(SectionForm sectionForm);


}
