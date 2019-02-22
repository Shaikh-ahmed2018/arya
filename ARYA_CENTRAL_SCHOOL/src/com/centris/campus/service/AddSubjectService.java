package com.centris.campus.service;

import java.util.List;

import com.centris.campus.actions.AddLabForm;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.vo.ViewallSubjectsVo;

public interface AddSubjectService {
	public List<ViewallSubjectsVo> subjectdetails(String schoolLocation);

	public boolean DeleteSubject(String[] subjectid, String[] locationList);

	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj);

	public boolean addSubject(AddSubjectForm obj);

	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj);

	public boolean updateSubjectDetails(AddSubjectForm obj);

	public String getpath(String classname);

	public String DdownloadsyllabuspathService(String subjectid);

	public String validateSubName(AddSubjectForm form1);

	public List<ViewallSubjectsVo> labdetails(String schoolLocation);

	public boolean addLab(AddSubjectForm addsubForm);

	public List<AddSubjectForm> getLaboratoryDetails();

	public boolean DeleteLab(String[] idList, String[] locationList);

	public AddSubjectForm getLabDetails(AddSubjectForm obj);

	public boolean updateLabDetails(AddSubjectForm addSubjectForm);

	public List<AddSubjectForm> labdetailsOnchangeListingPage(
			String locationid, String classname, String specialization);

	public String validateLabName(AddSubjectForm form1);



	public String DdownloadLabsyllabuspathService(String subjectid);

	public String getSubjectName(String subjectid);


}
