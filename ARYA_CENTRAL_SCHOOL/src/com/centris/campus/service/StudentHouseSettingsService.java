package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.vo.StudentHouseSettingsVO;

public interface StudentHouseSettingsService {

	String savehouseSettings(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid, String accid);

	String edithouseSettings(StudentHouseSettingsPOJO pojo);

	String deletehouseSettings(StudentHouseSettingsPOJO pojo);

	String checkduplicateHouse(StudentHouseSettingsPOJO pojo);

	int gettotalstudentcount(String accid, String locid);

	int gettotalallostudent(String accid, String locid);

	ArrayList<StudentHouseSettingsVO> getclassdetails(String locid, String accid);

	ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid, String classname, String accid, String filter1);

	ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo);

	String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid);

	String checkHousing(String accyear, String locid);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo, String classname, String filter1);

	ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid);

	ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid, String classname,
			String accid);

	ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid, String classname,
			String accid, String filter1);

	ArrayList<StudentHouseSettingsVO> byadminooddHousing(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo);

	String checkselection(String accyear, String locid);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo, String classname,
			String filter1);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo, String classname,
			String filter1);

	String regenerateHousedetails(String accyear, String locid, String genhouid);

}
