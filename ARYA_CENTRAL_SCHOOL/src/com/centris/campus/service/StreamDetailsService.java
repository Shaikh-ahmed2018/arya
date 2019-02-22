package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.vo.StreamDetailsVO;



public interface StreamDetailsService {

	
	public String insertStreamDetailsService(StreamDetailsForm  detailsForm);
	public StreamDetailsVO editStreamDetailsService(StreamDetailsVO detailsVo);
	public String deleteStreamDetailsService(String[] streamid);
	public List<StreamDetailsVO>  getStreamDetailsService(String schoolLocation);
	//public boolean checkStreamName(StreamDetailsPojo streamDetailsPojo);
	public ArrayList<StreamDetailsVO> searchStreamDetailsService(String searchTerm);
	public boolean validateStreamNameService(StreamDetailsVO detailsVo);
	public List<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear);

	

}
