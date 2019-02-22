package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.vo.SpecializationVo;

public interface SpecializationService {

	ArrayList<SpecializationVo> getspecializationList(String schoolLocation);

	String insertSpecialization(SpecializationForm spec, String specId);

	SpecializationVo editSpecialization(String edit);

	String deleteSpec(String[] specId);

	List<SpecializationVo> getSpecializationOnClassBased(String classId);

	String validateSpecialization(SpecializationForm form1);

	

	ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school);

	List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId);


}
