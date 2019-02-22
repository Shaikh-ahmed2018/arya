package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.service.ExamDetailsService;
import com.centris.campus.serviceImpl.ExamDetailsServiceImpll;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamDetailsBD {

	static ExamDetailsService detailsercive;
	static{
		detailsercive = new ExamDetailsServiceImpll();
	}
	
	public List<ExaminationDetailsVo> getexamdeligate() {
		List<ExaminationDetailsVo> allexamlist = new ArrayList<ExaminationDetailsVo>();
		allexamlist = detailsercive.getExamDetailsService();
		return allexamlist;
	}

	public String getaccyName(String accyear) {
		return detailsercive.getaccyName(accyear);
	}

	public String insertGradeSettings(ExamDetailsPojo obj) {
		return detailsercive.insertGradeSettings(obj);
	}

	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location) {
		return detailsercive.displayGradeSettings(accyear,location);
	}

	public String deleteGradeSettings(String gradeid, String location, String accyear) {
		return detailsercive.deleteGradeSettings(gradeid,location,accyear);
	}

	public String editGradeSettings(ExaminationDetailsVo list) {
		return detailsercive.editGradeSettings(list);
	}

	public String checkduplicateGrade(String accyear, String gradename, String loc) {
		return detailsercive.checkduplicateGrade(accyear,gradename,loc);
	}

	public String getexamName(String examid, String accyear, String locid) {
		return detailsercive.getexamName(examid,accyear,locid);
	}

	
	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj) {
		return detailsercive.getexamclassDetails(obj);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		return detailsercive.getSubjectmarksStatus(acyear);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation) {
		return detailsercive.getSubjectmarksList(accyear,schoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid, String locid, String classid) {
		return detailsercive.getSubjectClass(accyear,examid,locid,classid);
	}

	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj) {
		return detailsercive.classWiseSubject(obj);
	}

	public String getsubDetails(ExaminationDetailsVo obj) {
		return detailsercive.getsubDetails(obj);
	}

	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj, String schoolLocation) {
		return detailsercive.getstudentsList(obj,schoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj) {
		return detailsercive.classWiseStudent(obj);
	}

	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1) {
		return detailsercive.getStudentDetails(obj1);
	}

	public String insertmarkentrydetails(
			ExaminationDetailsVo obj) {
		return detailsercive.insertmarkentrydetails(obj);
	}

	public String getlocationname(String schoolLocation) {
		return detailsercive.getlocationname(schoolLocation);
	}

	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return detailsercive.insertmarkentrysubjectwise(obj,schoolLocation);
	}

	public String getclassname(String classid) {
		return detailsercive.getclassname(classid);
	}

	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid) {
		return detailsercive.getsubjectstudent(accyear,examid,locid);
	}

	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation) {
		return detailsercive.getlistofExamCodes(schoolLocation);
	}

	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return detailsercive.updatemarkentrysubjectwise(obj,schoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String location) {
		return detailsercive.examTimeTableListYear(accyear,location);
	}

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear) {
		return detailsercive.getExamClassByLocation(locid,accyear);
	}

	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo) {
		return detailsercive.getexamlistbyclass(pojo);
	}

	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo) {
		return detailsercive.getexamdetails(pojo);
	}

	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo) {
		return detailsercive.getsubdetails(pojo);
	}

	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate) {
		return detailsercive.savetimetabledetails(pojo,subid1,starttime1,endtime1,startdate);
	}

	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid) {
		return detailsercive.getexamsbtselection(accyear,locid);
	}

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, String examid) {
		return detailsercive.getExamClassByLocation(accyear,locid,examid);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid) {
		return detailsercive.getexamsettingslist(accyear,locid);
	}

	public String checkduplicatedate(ExamTimetablePojo pojo) {
		return detailsercive.checkduplicatedate(pojo);
	}

	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo) {
		return detailsercive.getexamdetailsbyset(pojo);
	}

	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo) {
		return detailsercive.getsubdetailsset(pojo);
	}

	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return detailsercive.savetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}

	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return detailsercive.updatetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {
		return detailsercive.getexamsettingslistfordep(accyear,locid);
		}

	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,String classid) {
		return detailsercive.getSubjectClassBySpec(accyear,examid,locid,classid);
	}

	
}

