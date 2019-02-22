package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamDetailsService {

	public List<ExaminationDetailsVo> getExamDetailsService();


	public String getaccyName(String accyear);

	public String insertGradeSettings(ExamDetailsPojo obj);

	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location);

	public String deleteGradeSettings(String gradeid, String location, String accyear);

	public String editGradeSettings(ExaminationDetailsVo list);

	public String checkduplicateGrade(String accyear, String gradename, String loc);


    public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear,String examid, String locid, String classid);


	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj);

	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear);

	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation);

    public String getexamName(String examid, String accyear, String locid);

    public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj);

	public String getsubDetails(ExaminationDetailsVo obj);

	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj, String schoolLocation);


	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj);

	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj);

	public String insertmarkentrydetails(
			ExaminationDetailsVo obj);

	public String getlocationname(String schoolLocation);

	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj, String schoolLocation);

	public String getclassname(String classid);

	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid);

	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation);


	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation);


	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String location);

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear);
	
	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear,String examid);

	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo);


	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo);


	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo);


	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate);


	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid);


	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid);


	public String checkduplicatedate(ExamTimetablePojo pojo);


	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo);


	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo);


	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate);


	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate);


	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid);


	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,String classid);



}
