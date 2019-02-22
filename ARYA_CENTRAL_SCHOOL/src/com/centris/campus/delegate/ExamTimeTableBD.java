package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.service.ExamTimeTableService;
import com.centris.campus.serviceImpl.ExamTimeTableServiceImpl;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamTimeTableBD {

	public Map<String, List<ExamTimetablePojo>> getExamDetails(
			ExamTimetablePojo groupLogReportPojo) {
		return new ExamTimeTableServiceImpl()
				.getExamDetails(groupLogReportPojo);
	}

	public ExamTimetablePojo getExamDate(String examId) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();
		return serviceobj.getExamDate(examId);
	}

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();
		return serviceobj.getAllSubjects(classId);
	}

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam) {

		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamTimeTableDetails(classId, exam);
	}

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj
				.saveExaminationClassMapping(examinationclassmappinglist);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails() {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamdetails();
	}

	public ArrayList<ExamTimetablePojo> getclassdetails() {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getclassdetails();
	}

	public ArrayList<ExaminationDetailsVo> getEaxmListYear(String currentaccyear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmListYear();
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear(String currentaccyear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableListYear();
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableClassList(accYear);
	}

	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getHeading(exampojo);
	}

	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getClassExamTimeTableDetails(exampojo);
	}

	public String insertExam(ExamDetailsForm addExam) {
		ExamTimeTableService insertobj = new ExamTimeTableServiceImpl();

		return insertobj.insertExam(addExam);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear, String schoolLocation) {
		ExamTimeTableService insertobj = new ExamTimeTableServiceImpl();
		return insertobj.getexamsettingsDetails(accyear,schoolLocation);
	}

	public String deleteexamSettings(String deleteid, String location, String accyear) {
		ExamTimeTableService deleteobj = new ExamTimeTableServiceImpl();
		return deleteobj.deleteexamSettings(deleteid,location,accyear);
	}

	public String editexamsettings(String editid, ExamDetailsForm editExam) {
		ExamTimeTableService editobj = new ExamTimeTableServiceImpl();
		return editobj.editexamsettings(editid,editExam);
	}

	public String checkduplicateExam(String accyear, String examcode, String location, String classid) {
		ExamTimeTableService dupexam = new ExamTimeTableServiceImpl();
		return dupexam.checkduplicateExam(accyear,examcode,location,classid);
	}

	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getstatusexamsettingsDetails(accyear,schoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableClassSubjectList(exampojo);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmMarksListYear(accyear,locid);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails(String locid) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamdetails(locid);
	}

	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);
	}

	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname, String examId) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.saveDependency(examcode,dependency,mainexamcode,mainexamname,examId);
	}

	public String insertGradeDependent(String project, String assignment,
			String practical,String attendance,String classId,String sectionId,String exam,String location,String accyear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear);
	}

	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getstatusgrdDepDetails(accyear,hschoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.disstudepdetails(accyear,locid);
	}

	public ArrayList<ExaminationDetailsVo> getToClassDetails(String location, String classId) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getToClassDetails(location,classId);
	}

	public ArrayList<ExaminationDetailsVo> getExamTypeList() {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamTypeList();
	}

	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamNameList(locationid,classid,accyear);
	}

	public ArrayList<ExaminationDetailsVo> getExamNameList1(String locationid, String classid, String accyear) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamNameList1(locationid,classid,accyear);
	}
}
