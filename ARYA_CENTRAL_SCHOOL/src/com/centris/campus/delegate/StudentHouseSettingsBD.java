package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.service.StudentHouseSettingsService;
import com.centris.campus.serviceImpl.StudentHouseSettingsServiceImpl;
import com.centris.campus.vo.StudentHouseSettingsVO;

public class StudentHouseSettingsBD {

	static StudentHouseSettingsService service;
	static{
		service = new StudentHouseSettingsServiceImpl();
	}
	public static String savehouseSettings(StudentHouseSettingsPOJO pojo) {
		
		return service.savehouseSettings(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid, String accid) {
		return service.gethouseSettings(locid,accid);
	}
	public static String edithouseSettings(StudentHouseSettingsPOJO pojo) {
		return service.edithouseSettings(pojo);
	}
	public static String deletehouseSettings(StudentHouseSettingsPOJO pojo) {
		return service.deletehouseSettings(pojo);
	}
	public static String checkduplicateHouse(StudentHouseSettingsPOJO pojo) {
		return service.checkduplicateHouse(pojo);
	}
	public int gettotalstudentcount(String accid, String locid) {
		return service.gettotalstudentcount(accid,locid);
	}
	public int gettotalallostudent(String accid, String locid) {
		return service.gettotalallostudent(accid,locid);
	}
	public ArrayList<StudentHouseSettingsVO> getclassdetails(String locid, String accid) {
		return service.getclassdetails(locid,accid);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid, String classname, String accid, String filter1) {
		return service.getHouseSettingStudentWise(locid,classid,classname,accid,filter1);
	}
	public ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo) {
		return service.generateHousing(pojo);
	}
	public String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid) {
		return service.savegenerateHouseDetails(pojo,houseid,sectionid,studid);
	}
	public String checkHousing(String accyear, String locid) {
		return service.checkHousing(accyear,locid);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		return service.displayHouseSettingStudentWise(pojo,classname,filter1);
	}
	public ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo) {
		return service.houseadmiWise(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo) {
		return service.generateaddmiHousing(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid) {
		return service.getHouseSettingAdminoWise(locid,classid,classname,accid);
	}
	public ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo) {
		return service.byadminodescHousing(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid,
			String classname, String accid) {
		return service.getHouseSettingAdminodescWise(locid,classid,classname,accid);
	}
	public ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo) {
		return service.byadminoevenHousing(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid,
			String classname, String accid, String filter1) {
		return service.getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1);
	}
	public ArrayList<StudentHouseSettingsVO> byadminoddHousing(StudentHouseSettingsPOJO pojo) {
		return service.byadminooddHousing(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo) {
		return service.bystudentdescHousing(pojo);
	}
	public String checkselection(String accyear, String locid) {
		return service.checkselection(accyear,locid);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		return service.displayHouseSettingAdminoWise(pojo,classname,filter1);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		return service.displayHouseSettingAdminoEven(pojo,classname,filter1);
	}
	public String regenerateHousedetails(String accyear, String locid, String genhouid) {
		return service.regenerateHousedetails(accyear,locid,genhouid);
	}
	
}
