package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.vo.AddDesignationVO;

public class AddDesignationBD {
	
	
	
	
public  ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo){
	
	

		return new AddDesignationServiceImpl().DesignationDetails(vo);
	
}
	
	
public String insertDesignationDetails(AddDesignation aform){
	
	

		return new AddDesignationServiceImpl().insertDesignationDetails(aform);
	
}

public AddDesignation EditDesignationDetails(AddDesignation aform){
	
	

		return new AddDesignationServiceImpl().EditDesignationDetails(aform);
}
		
		



public String deleteDesignationDetails(String[] designation_code) 
{
	

	return new AddDesignationServiceImpl().deleteDesignationDetails(designation_code);
}

public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal)

{

	return new AddDesignationServiceImpl().getSearchDetails(searchTextVal);
}


public boolean getnamecount(AddDesignationVO vo) {
	
	return new AddDesignationServiceImpl().getnamecount(vo);
}
}
	
