package com.centris.campus.dao;




import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.vo.AddDesignationVO;



public interface AddDesignationDao {
	
	public ArrayList<AddDesignationVO>  DesignationDetails();

	public String insertDesignationDetails(AddDesignationPojo apojo);
	
	public AddDesignation EditDesignationDetails(AddDesignation aform);

	public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal) ;

}
