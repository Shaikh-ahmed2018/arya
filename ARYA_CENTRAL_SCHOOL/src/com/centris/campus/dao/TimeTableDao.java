package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;

public interface TimeTableDao {

	public ArrayList<TimeTableVo> getTimeTableDetails(String timetableDetails, String details2);

	public ArrayList<TimeTableVo> getClassName();

	public ArrayList<TimeTableVo> getSectionName(String classid);

	public String updateTimeTableDetails(TimeTablePojo pojo);

	public ArrayList<TimeTableVo> getTeacherTimeTableDetails(
			String teacherid, String accyearid);

	public ArrayList<TeacherTimeTableVo> getTeacherName();
	
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo);
	
	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String viewBy);
	
	public ArrayList<TimeTableVo> getClassSectionList();

	public String getClassNameDetailsDao(String classid);

	public String getSectionNameDetailsDao(String sectionid);

	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy, String classId, String locationId);

	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid, String classId, String sectionId);

	public String getTeacherNameDetails(String classId, String sectionId);
}
