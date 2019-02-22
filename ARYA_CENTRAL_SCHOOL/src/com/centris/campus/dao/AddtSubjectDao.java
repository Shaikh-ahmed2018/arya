package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.actions.AddLabForm;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.vo.ViewallSubjectsVo;

public interface AddtSubjectDao {
	public ArrayList<ViewallSubjectsVo> subjectdetails(String schoolLocation);
	public boolean DeleteSubject(String[] voObj, String[] locationList);
	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj);
	public boolean addSubject(AddSubjectForm obj);
	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj);
	
	public boolean updateSubjectDetails(AddSubjectForm obj);
	public String validateSubName(AddSubjectForm form1);
	public  ArrayList<ViewallSubjectsVo> labdetails(String schoolLocation);
	public boolean addLab(AddSubjectForm obj);
	public boolean DeleteLab(String[] idList, String[] locationList);
		public AddSubjectForm getLabDetails(AddSubjectForm obj);
		public boolean updateLabDetails(AddSubjectForm addSubjectForm);
		public String validateLabName(AddSubjectForm form1);
		public String getSubjectName(String subjectid);

}
