package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.service.AcademicYearMasterService;
import com.centris.campus.serviceImpl.AcademicYearMasterServiceImpl;
import com.centris.campus.vo.AcademicYearVO;

public class AcademicYearMasterBD {

	AcademicYearMasterService academicYear_obj = new AcademicYearMasterServiceImpl();

	public ArrayList<AcademicYearVO> getAcademicYearDetails() {
		return academicYear_obj.getAcademicYearDetails();
	}

	public String createAcademicYear(AcademicYearPojo ACY_pojo) {
		return academicYear_obj.createAcademicYear(ACY_pojo);
	}

	public boolean deleteAcademicYear(String ACY_code) {
		return academicYear_obj.deleteAcademicYear(ACY_code);
	}

	public ArrayList<AcademicYearVO> editAcademicYear(String ACY_code) {
		return academicYear_obj.editAcademicYear(ACY_code);
	}

	public String accyearNameCheck(AcademicYearPojo ACY_pojo) {
		return academicYear_obj.accyearNameCheck(ACY_pojo);
	}

	public ArrayList<AcademicYearVO> searchAcademicYearDetails(String searchname) {
		return academicYear_obj.searchAcademicYearDetails(searchname);
	}

	public List<AcademicYearVO> getAccYear() throws Exception {
		return academicYear_obj.getAccYear();
	}

	public ArrayList<AcademicYearPojo> getAcademicYearList() {
		return academicYear_obj.getAcademicYearList();
	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid) {
		return academicYear_obj.getAcademicYearDetailsByBranchId(brancid);
	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname) {
		return academicYear_obj.getAcademicYearDetailsbysearch(brancid,
				searchname);
	}
}
