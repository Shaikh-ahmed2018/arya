package com.centris.campus.service;





import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.vo.AddDesignationVO;






public interface AddDesignationService {

	public ArrayList<AddDesignationVO> DesignationDetails();
	public String insertDesignationDetails(AddDesignation aform);
    public AddDesignation EditDesignationDetails(AddDesignation aform);
    
    public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal);
	String deleteDesignationDetails(String[] vo);
}

