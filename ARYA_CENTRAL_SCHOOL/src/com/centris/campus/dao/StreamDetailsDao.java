package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.vo.StreamDetailsVO;


public interface StreamDetailsDao {

	
	public int insertStreamDetailsDao(StreamDetailsPojo detailsPojo);
	public List<StreamDetailsPojo> getStreamDetailsDao(String schoolLocation);
	public StreamDetailsVO editStreamDetailsDao(StreamDetailsVO detailsVo);
	public String deleteStreamDetailsDao(String[] detailsVo);
	public ArrayList<StreamDetailsVO> searchStreamDetailsDao(
			String searchTerm);
	public boolean validateStreamNameDao(StreamDetailsVO detailsVo);
	public int updateStreamDetailsDao(StreamDetailsPojo detailsPojo);
	public ArrayList<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear);
}
