package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.SpecializationPojo;
import com.centris.campus.vo.SpecializationVo;

public interface SpecializationDao {

	ArrayList<SpecializationVo> getspecializationList(String schoolLocation);

	String insertSpecialization(SpecializationPojo pojo, String specId);

	SpecializationVo editSpecialization(String edit);

	String deleteSpec(String[] specId);

	List<SpecializationVo> getSpecializationOnClassBased(String classId);

	String getSpecializationOnClassBased(SpecializationForm form1);

	ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school);

	List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId);



}
