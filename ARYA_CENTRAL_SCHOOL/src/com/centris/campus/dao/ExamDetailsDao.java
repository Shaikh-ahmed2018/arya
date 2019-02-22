package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamDetailsDao {

	List<ExamDetailsPojo> getExamDetailsDao();

	String getaccyName(String accyear);


	String insertGradeSettings(ExamDetailsPojo obj);

	ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location);

	String deleteGradeSettings(String gradeid, String locname, String accyear);

	String editGradeSettings(ExaminationDetailsVo gradeid);

	String checkduplicateGrade(String accyear, String gradename, String loc);

	ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear);

	ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation);

	ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid, String locid, String classid);

	String getexamName(String examid, String accyear, String locid);

	ArrayList<ExaminationDetailsVo> getexamclassDetails(ExaminationDetailsVo obj);

	ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj);

	String getsubDetails(ExaminationDetailsVo obj);


	ArrayList<ExaminationDetailsVo> classWiseStudent(ExaminationDetailsVo obj);

    ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj);

	String insertmarkentrydetails(
			ExaminationDetailsVo obj);

	String getlocationname(String schoolLocation);

	ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,
			String schoolLocation);

	String insertmarkentrysubjectwise(ExaminationDetailsVo obj, String schoolLocation);

	String getclassname(String classid);

	ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid);

	ArrayList<ExaminationDetailsVo> getlistofExamCodes(String schoolLocation);

	String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation);

	ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String loc);

	List<ExaminationDetailsVo> getExamClassByLocation(String loc, String accyear);

	List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo);

	ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo);

	ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo);

	String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate);

	ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid);

	List<ExaminationDetailsVo> getExamClassByLocation(String accyear, String locid, String examid);

	ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid);

	String checkduplicatedate(ExamTimetablePojo pojo);

	ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo);

	ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo);

	String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate);

	String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate);

	ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear,
			String locid);

	ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid, String classid);


}
