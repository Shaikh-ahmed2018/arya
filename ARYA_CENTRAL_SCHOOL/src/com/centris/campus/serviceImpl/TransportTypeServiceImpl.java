package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.TransportTypeDao;
import com.centris.campus.daoImpl.TransportTypeDaoImpl;
import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.service.TransportTypeService;
import com.centris.campus.vo.TransportTypeVO;

public class TransportTypeServiceImpl implements TransportTypeService{


	public ArrayList<TransportTypeVO> getAllTransportypeDetails() {
		
		TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
		
		return transportTypeDao.getAllTransportypeDetails();
	}


	public ArrayList<TransportTypeVO> getSearchDetails(TransportTypePOJO transportTypePOJO) {

		TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
		
		return transportTypeDao.getSearchDetails(transportTypePOJO);
	}


	public String validateTypeName(TransportTypePOJO transportTypePOJO) {
		
	TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
		
		return transportTypeDao.validateTypeName(transportTypePOJO);
	}


	public String AddTransportType(TransportTypePOJO transportTypePOJO) {
		
	  TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
		
		return transportTypeDao.AddTransportType(transportTypePOJO);
	}



	public String UpdateTransportType(TransportTypePOJO transportTypePOJO) {
		
		 TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
			
			return transportTypeDao.UpdateTransportType(transportTypePOJO);
	}


	
	public TransportTypeVO editType(TransportTypePOJO transportTypePOJO) {
		
		TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
			
			return transportTypeDao.editType(transportTypePOJO);
	}



	public String deleteType(TransportTypePOJO transportTypePOJO) {
		
		TransportTypeDao transportTypeDao = new TransportTypeDaoImpl();
		
		return transportTypeDao.deleteType(transportTypePOJO);
	}

}
