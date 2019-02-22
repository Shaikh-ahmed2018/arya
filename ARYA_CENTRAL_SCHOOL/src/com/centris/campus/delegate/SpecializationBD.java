package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.service.SpecializationService;
import com.centris.campus.serviceImpl.SpecializationServiceImpl;
import com.centris.campus.vo.SpecializationVo;

public class SpecializationBD {
	
	SpecializationService service;
	public ArrayList<SpecializationVo> getspecializationList(String schoolLocation) {
		service = new SpecializationServiceImpl();
		return service.getspecializationList(schoolLocation);
	}
	public String insertSpecialization(SpecializationForm spec, String specId) {
		service = new SpecializationServiceImpl();
		return service.insertSpecialization(spec,specId);
	}
	public SpecializationVo editSpecialization(String edit) {
		service = new SpecializationServiceImpl();
		return service.editSpecialization(edit);
	}
	public String deleteSpec(String[] specId) {
		service = new SpecializationServiceImpl();
		return service.deleteSpec(specId);
	}
	public List<SpecializationVo> getSpecializationOnClassBased(String classId) {
		service = new SpecializationServiceImpl();
		return service.getSpecializationOnClassBased(classId);
	}
	public String validateSpecialization(SpecializationForm form1) {
		service = new SpecializationServiceImpl();
		return service.validateSpecialization(form1);
	}
	public ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school) {
		service = new SpecializationServiceImpl();
		return service.getSearchSpecializationList(searchterm,school);
	}
	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId) {
		return new SpecializationServiceImpl().getstreamdetailsforOnchange( locationid,  classname,  streamId);
	}
	
	public List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId) {
		service = new SpecializationServiceImpl();
		return service.getSpecializationOnClassWithoutLocId(classId);
	}

}
