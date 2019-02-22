package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.service.StreamDetailsService;
import com.centris.campus.serviceImpl.StreamDetailsServiceImpl;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class StreamDetailsBD {

	StreamDetailsService   detailsService;
	
	public List<StreamDetailsVO> getStreamDetailsBD(String schoolLocation) {
		detailsService=new StreamDetailsServiceImpl();
		List<StreamDetailsVO> allStreamList=new ArrayList<StreamDetailsVO>();	
		allStreamList=detailsService.getStreamDetailsService(schoolLocation);
		
		return allStreamList;
	
	}
	
	
	public String insertStreamDetailsBD(StreamDetailsForm detailsForm) {
		
		detailsService=new StreamDetailsServiceImpl();
		return	detailsService.insertStreamDetailsService(detailsForm);
		 
	}
	
	public StreamDetailsVO editStreamDetailsBD(StreamDetailsVO detailsVo) {
		
		
		detailsService=new StreamDetailsServiceImpl();
		
		return	detailsService.editStreamDetailsService(detailsVo);
		
		
	}
	
public String deleteStreamDetailsBD(String[] streamid) {
		
	
		detailsService=new StreamDetailsServiceImpl();
		
		return	detailsService.deleteStreamDetailsService(streamid);
}

public ArrayList<StreamDetailsVO> searchStreamDetailsBD(String searchTerm)
{	
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.searchStreamDetailsService(searchTerm);
}


public boolean validateStreamNameBD(StreamDetailsVO detailsVo) {
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.validateStreamNameService(detailsVo);
}

public List<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear) {
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.searchByLocationOnly(locationId,accYear);
}



}
