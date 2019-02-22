package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.service.ClassService;
import com.centris.campus.service.SectionService;
import com.centris.campus.serviceImpl.ClassServiceImpl;
import com.centris.campus.serviceImpl.SectionServiceImpl;
import com.centris.campus.vo.SectionVO;

public class SectionBD {
	
	public String insertCampusClassSectionBD(
			
			SectionForm campusClassSectionForm) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		 return sectionService.insertCampusClassSectionService(campusClassSectionForm);
	}
	
	public String updateCampusClassSectionBD(
			SectionForm campusClassSectionForm) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.updateCampusClassSectionService(campusClassSectionForm);
	}
	
	public boolean deleteCampusClassSectionBD(
			String[] sectionId, String[] locationList) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		
		return sectionService.deleteCampusClassSectionService(sectionId,locationList);
	}
	
	public List<SectionForm> getCampusClassSectionAndClassDetailsBD(String schoolLocation) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionForm> sectionList=sectionService.getCampusClassSectionAndClassDetailsService(schoolLocation);
		return sectionList;
		
	}
	public List<SectionForm> getCampusClassDetailsIDandClassDetailsNameBD(String locationId) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionForm> sectionList=sectionService.getCampusClassDetailsIDandClassDetailsNameService(locationId);
		return sectionList;
		
	}

	
	public boolean checkSectionNameForUpdate(String sectionId,
			String sectionName, String className) {
	
		return new SectionServiceImpl().checkSectionNameForUpdate(sectionId,sectionName,className);
	}
	
	public List<SectionForm> searchSection(SectionForm searchText) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.searchSection(searchText);
	}
	
	public SectionForm editSection(SectionForm secCode) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.editSection(secCode);
	}
	
	public List<SectionVO> sectiondetailsdownload() throws Exception  {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionVO> sectionList=sectionService.sectiondetailsdownload();
		return sectionList;
		
	}

	public boolean sectionNameCheck(SectionForm sectionForm) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.sectionNameCheck(sectionForm);
		
	}

	public List<SectionForm> getstreamdetailsforOnchange(String locationid,String classname, String streamId) {
		return  new SectionServiceImpl().getstreamdetailsforOnchange( locationid, classname,  streamId);
		
	}
	
	

}
