package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.service.FeeMasterService;
import com.centris.campus.service.TermMasterService;
import com.centris.campus.serviceImpl.FeeMasterServiceImpl;
import com.centris.campus.serviceImpl.TermMasterServiceIMPL;
import com.centris.campus.vo.TermMasterVo;

public class TermMasterDelegate 

{

	TermMasterService obj_Term = new TermMasterServiceIMPL();

	public TermMasterVo getaccdetails(TermMasterVo vo) {
		
		return obj_Term.getaccdetails(vo);
	}

	public boolean getnamecount(TermMasterVo vo) 
	
	{
		
		return obj_Term.getnamecount(vo);
	}

	public String addtermfeedetails(TermMasterVo vo) {
		
		return obj_Term.addtermfeedetails(vo);
	}

	public ArrayList<TermMasterVo> termList(TermMasterVo vo) {
		
		return obj_Term.termList(vo);
	}

	public TermMasterVo edittermDetails(TermMasterVo vo) {
		
		return obj_Term.edittermDetails(vo);
	}

	public String deleteTermDetails(TermMasterVo vo) {
		return obj_Term.deleteTermDetails(vo);
	}

	public String dateOverLapValidate(String date, String accyear) {
		// TODO Auto-generated method stub
		return obj_Term.dateOverLapValidate(date,accyear);
	}

	public ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo vo) {
		return obj_Term.separateTransportTermList(vo);
	}

	public String addtermSeparatefeedetails(TermMasterVo vo) {
		return obj_Term.addtermSeparatefeedetails(vo);
	}

	public String deleteSeparateTermDetails(TermMasterVo vo) {
		return obj_Term.deleteSeparateTermDetails(vo);
	}

	public String separatedateOverLapValidate(String date, String accyear, String locationId) {
		// TODO Auto-generated method stub
		return obj_Term.separatedateOverLapValidate(date,accyear,locationId);
	}

	public boolean getTermnamecount(TermMasterVo vo) {
		return obj_Term.getTermnamecount(vo);
	}

	public List<TermMasterVo> getTermDetails(String academic_year, String location) {
		return obj_Term.getTermDetails(academic_year,location);
	}

	public String dateOverLapValidate(String date, String accyear,
			String locationId) {
		return obj_Term.dateOverLapValidate(date,accyear,locationId);
	}


	
}
