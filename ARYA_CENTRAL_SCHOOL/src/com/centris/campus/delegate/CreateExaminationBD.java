package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.serviceImpl.CreateExaminationServiceIMPL;
import com.centris.campus.vo.ExaminationDetailsVo;


public class CreateExaminationBD {


	public Map<String, String> getAccadamicYearsBD() {
	
		
		return new CreateExaminationServiceIMPL().getAccadamicYearsServiceImpl();
	}

	public List<Object> getAllExamNames(CreateExaminationForm examform) {


		List examnamelist = new CreateExaminationServiceIMPL().getAllExamNames(examform);
		return examnamelist;
	}

	public String createExamination(CreateExaminationForm examform) {
	
	
		return new CreateExaminationServiceIMPL().createExamination(examform);
	}

	public ExaminationDetailsVo editExamination(ExaminationDetailsVo examvo) {
		

		
		return new CreateExaminationServiceIMPL().editExamination(examvo);
	}

	public String deleteExamination(ExaminationDetailsVo examvo) {
		
		return new CreateExaminationServiceIMPL().deleteExamination(examvo);
	}

	public ArrayList<ExaminationDetailsVo> searchExamination(String searchName) {
	
		return  new CreateExaminationServiceIMPL().searchExamination(searchName);
	}

	public boolean validateExamination(ExaminationDetailsVo examvo) {
		
		return new CreateExaminationServiceIMPL().validateExamination(examvo);
	}

}
