package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.TermMasterVo;

public interface TermMasterDAO {

	TermMasterVo getaccdetails(TermMasterVo vo);

	boolean getnamecount(TermMasterVo vo);

	String addtermfeedetails(TermMasterVo vo);

	ArrayList<TermMasterVo> termList(TermMasterVo vo);

	TermMasterVo edittermDetails(TermMasterVo vo);

	String updatetermDetails(TermMasterVo vo);

	String deleteTermDetails(TermMasterVo vo);

	String dateOverLapValidate(String date, String accyear);

	String addtermSeparatefeedetails(TermMasterVo vo);

	ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo vo);

	String deleteSeparateTermDetails(TermMasterVo vo);


	String separatedateOverLapValidate(String date, String accyear, String locationId);

	boolean getTermnamecount(TermMasterVo vo);

	List<TermMasterVo> getTermDetails(String academic_year, String location);

	String dateOverLapValidate(String date, String accyear, String locationId);

	String updatesepataretermDetails(TermMasterVo vo);


	
}
