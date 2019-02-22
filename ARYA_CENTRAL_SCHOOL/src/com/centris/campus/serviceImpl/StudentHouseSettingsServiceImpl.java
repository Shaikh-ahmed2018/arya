package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.StudentHouseSettingsDAO;
import com.centris.campus.daoImpl.StudentHouseSettingsDAOImpl;
import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.service.StudentHouseSettingsService;
import com.centris.campus.vo.StudentHouseSettingsVO;

public class StudentHouseSettingsServiceImpl implements StudentHouseSettingsService {

	static StudentHouseSettingsDAO dao;
	static{
		dao = new StudentHouseSettingsDAOImpl();
	}
	
	@Override
	public String savehouseSettings(StudentHouseSettingsPOJO pojo) {
		
		return dao.savehouseSettings(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid,String accid) {
		return dao.gethouseSettings(locid,accid);
	}

	@Override
	public String edithouseSettings(StudentHouseSettingsPOJO pojo) {
		return dao.edithouseSettings(pojo);
	}

	@Override
	public String deletehouseSettings(StudentHouseSettingsPOJO pojo) {
		return dao.deletehouseSettings(pojo);
	}

	@Override
	public String checkduplicateHouse(StudentHouseSettingsPOJO pojo) {
		return dao.checkduplicateHouse(pojo);
	}

	@Override
	public int gettotalstudentcount(String accyear,String locid) {
		return dao.gettotalstudentcount(accyear,locid);
	}

	@Override
	public int gettotalallostudent(String accyear,String locid) {
		return dao.gettotalallostudent(accyear,locid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getclassdetails(String locid,String accid) {
		return dao.getclassdetails(locid,accid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid,String classname,String accid,String filter1) {
		return dao.getHouseSettingStudentWise(locid,classid,classname,accid,filter1);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo) {
		return dao.generateHousing(pojo);
	}

	@Override
	public String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid) {
		return dao.savegenerateHouseDetails(pojo,houseid,sectionid,studid);
	}

	@Override
	public String checkHousing(String accyear, String locid) {
		return dao.checkHousing(accyear,locid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo,
			String classname,String filter1) {
		return dao.displayHouseSettingStudentWise(pojo,classname,filter1);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo) {
		return dao.houseadmiWise(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo) {
		return dao.generateaddmiHousing(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid) {
		return dao.getHouseSettingAdminoWise(locid,classid,classname,accid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo) {
		return dao.byadminodescHousing(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid,
			String classname, String accid) {
		return dao.getHouseSettingAdminodescWise(locid,classid,classname,accid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo) {
		return dao.byadminoevenHousing(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid,
			String classname, String accid,String filter1) {
		return dao.getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminooddHousing(StudentHouseSettingsPOJO pojo) {
		return dao.byadminooddHousing(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo) {
		return dao.bystudentdescHousing(pojo);
	}

	@Override
	public String checkselection(String accyear, String locid) {
		return dao.checkselection(accyear,locid);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		return dao.displayHouseSettingAdminoWise(pojo,classname,filter1);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		return dao.displayHouseSettingAdminoEven(pojo,classname,filter1);
	}

	@Override
	public String regenerateHousedetails(String accyear, String locid, String genhouid) {
		return dao.regenerateHousedetails(accyear,locid,genhouid);
	}
}
