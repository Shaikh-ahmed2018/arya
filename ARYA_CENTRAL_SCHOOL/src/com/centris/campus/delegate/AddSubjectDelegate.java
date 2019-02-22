package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.actions.AddLabForm;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.service.AddSubjectService;
import com.centris.campus.serviceImpl.AddSubjectServiceImpl;
import com.centris.campus.vo.ViewallSubjectsVo;

public class AddSubjectDelegate {

	AddSubjectService service = new AddSubjectServiceImpl();

	public List<ViewallSubjectsVo> subjectdetails(String schoolLocation) {
		return service.subjectdetails(schoolLocation);
	}

	public boolean DeleteSubject(String[] subjectid, String[] locationList) {
		return service.DeleteSubject(subjectid,locationList);
	}

	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj) {
		return service.searchsubjectdetails(voObj);
	}

	public boolean addSubject(AddSubjectForm obj) {
		return service.addSubject(obj);
	}

	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj) {
		return service.getSubjectDetails(obj);
	}

	public boolean updateSubjectDetails(AddSubjectForm obj) {
		return service.updateSubjectDetails(obj);
	}

	public String getpath(String classname) {
		return service.getpath(classname);
	}

	public String DdownloadsyllabuspathBD(String subjectid) {
		
		return service.DdownloadsyllabuspathService(subjectid);
	}

	public boolean DeleteSubject(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public String validateSubName(AddSubjectForm form1) {
		// TODO Auto-generated method stub
		return service.validateSubName(form1);
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization) {
		return new AddSubjectServiceImpl().subjectdetailsOnchangeListingPage( locationid,  classname,  specialization);
	}

	public List<ViewallSubjectsVo> labdetails(String schoolLocation) {
		// TODO Auto-generated method stub
		return service.labdetails(schoolLocation);
	}

	public boolean addLab(AddSubjectForm addsubForm) {
		// TODO Auto-generated method stub
		return service.addLab(addsubForm);
	}

	public List<AddSubjectForm> getLaboratoryDetails() {
		// TODO Auto-generated method stub
		return service.getLaboratoryDetails();
	}

	public boolean DeleteLab(String[] idList, String[] locationList) {
		// TODO Auto-generated method stub
		return service.DeleteLab(idList,locationList);
	}

	public AddSubjectForm getLabDetails(AddSubjectForm obj) {
		// TODO Auto-generated method stub
		return service.getLabDetails(obj);
	}

	public boolean updateLabDetails(AddSubjectForm addSubjectForm) {
		// TODO Auto-generated method stub
		return service.updateLabDetails(addSubjectForm);
	}

	public List<AddSubjectForm> labdetailsOnchangeListingPage(
			String locationid, String classname, String specialization) {
		// TODO Auto-generated method stub
		return service.labdetailsOnchangeListingPage(locationid,classname,specialization);
	}

	public String validateLabName(AddSubjectForm form1) {
		// TODO Auto-generated method stub
		return service.validateLabName(form1);
	}

	public String DdownloadLabsyllabuspathBD(String subjectid) {
		// TODO Auto-generated method stub
		return service.DdownloadLabsyllabuspathService(subjectid);
	}

	public String getSubjectName(String subjectid) {
		// TODO Auto-generated method stub
		return service.getSubjectName(subjectid);
	}

}
