package com.centris.campus.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamTimeTableService {

	public ExamTimetablePojo getExamDate(String examId);

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId);

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam);

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist);

	public ArrayList<ExamTimetablePojo> getExamdetails();

	public ArrayList<ExamTimetablePojo> getclassdetails();

	public ArrayList<ExaminationDetailsVo> getEaxmListYear();

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear();

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear);

	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo);

	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo);

	public String insertExam(ExamDetailsForm addExam);

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear, String schoolLocation);

	public String deleteexamSettings(String deleteid, String location, String accyear);

	public String editexamsettings(String editid, ExamDetailsForm editExam);


	public String checkduplicateExam(String accyear, String gradename, String location, String classid);

	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear);

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo);

	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation);

	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid);

	public ArrayList<ExamTimetablePojo> getExamdetails(String locid);

	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode);

	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname, String examId);

	public String insertGradeDependent(String project, String assignment,
			String internal, String attendance, String classId, String sectionId, String exam, String location, String accyear);

	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation);

	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid);

	public ArrayList<ExaminationDetailsVo> getToClassDetails(String location, String classId);

	public ArrayList<ExaminationDetailsVo> getExamTypeList();

	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear);

	public ArrayList<ExaminationDetailsVo> getExamNameList1(String locationid, String classid, String accyear);

}
