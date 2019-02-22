package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.vo.TransportTypeVO;

public interface TransportTypeDao {

	ArrayList<TransportTypeVO> getAllTransportypeDetails();

	ArrayList<TransportTypeVO> getSearchDetails(TransportTypePOJO transportTypePOJO);

	String validateTypeName(TransportTypePOJO transportTypePOJO);

	String AddTransportType(TransportTypePOJO transportTypePOJO);

	String UpdateTransportType(TransportTypePOJO transportTypePOJO);

	TransportTypeVO editType(TransportTypePOJO transportTypePOJO);

	String deleteType(TransportTypePOJO transportTypePOJO);

}
