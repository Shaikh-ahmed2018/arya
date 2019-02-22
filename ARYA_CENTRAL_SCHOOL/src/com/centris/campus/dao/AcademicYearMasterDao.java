package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.vo.AcademicYearVO;

public interface AcademicYearMasterDao {

	public ArrayList<AcademicYearVO> getAcademicYearDetails();

	public String createAcademicYear(AcademicYearPojo ACY_pojo);

	public boolean deleteAcademicYear(String ACY_code);

	public ArrayList<AcademicYearVO> editAcademicYear(String ACY_code);

	public String accyearNameCheck(AcademicYearPojo ACY_pojo);

	public ArrayList<AcademicYearVO> searchAcademicYearDetails(String searchname);

	public List<AcademicYearVO> getAccYear();

	public ArrayList<AcademicYearPojo> getAcademicYearList();

	public ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid);

	public ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname);

}
