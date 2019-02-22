package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.dao.ClassDao;
import com.centris.campus.dao.ExamTimeTableDao;
import com.centris.campus.daoImpl.ClassDaoImpl;
import com.centris.campus.daoImpl.ExamTimeTableDaoImpl;
import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.service.ExamTimeTableService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamTimeTableServiceImpl implements ExamTimeTableService {
	private static final Logger logger = Logger
			.getLogger(ExamTimeTableServiceImpl.class);

	public Map<String, List<ExamTimetablePojo>> getExamDetails(
			ExamTimetablePojo groupLogReportPojo) {

		Map<String, Map<String, List<ExamTimetablePojo>>> consolidatedMap = new LinkedHashMap<String, Map<String, List<ExamTimetablePojo>>>();

		Map<String, List<ExamTimetablePojo>> listMap = null;
		Connection conn = null;
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		try {
			listMap = new LinkedHashMap<String, List<ExamTimetablePojo>>();
			List<ExamTimetablePojo> examMainList = new ArrayList<ExamTimetablePojo>();
			List<ExamTimetablePojo> examList = examTimeTableDao
					.getExamDetails();

			for (int i = 0; i < examList.size(); i++) {

				if (examMainList.size() != 0) {
					System.out.println(examMainList
							.get(examMainList.size() - 1).getExamId()
							+ " ----- " + examList.get(i).getExamId());

					if (!(examMainList.get(examMainList.size() - 1).getExamId()
							.equalsIgnoreCase(examList.get(i).getExamId()))) {

						JSONArray array1 = new JSONArray();
						array1.put(examMainList);
						System.out.println("exam list " + array1);

						listMap.put(examMainList.get(examMainList.size() - 1)
								.getExamId(), examMainList);

						// consolidatedMap.put(examMainList.get(examMainList.size()-1).getExamId(),
						// listMap);

						examMainList = new ArrayList<ExamTimetablePojo>();
					}

				}

				examMainList.add(examList.get(i));

			}

			if (examMainList.size() != 0) {

				listMap.put(examMainList.get(examMainList.size() - 1)
						.getExamName(), examMainList);
				// consolidatedMap.put(examMainList.get(examMainList.size()-1).getExamName(),
				// listMap);
			}

			JSONArray array = new JSONArray();
			array.put(listMap);

			System.out.println("consolidatedMap size " + array);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();

		return examTimeTableDao.getExamTimeTableDetails(classId, exam);
	}

	public ExamTimetablePojo getExamDate(String examId) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExaminations Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExaminations Ending");
		return examTimeTableDao.getExamDate(examId);
	}

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getAllSubjects Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getAllSubjects Ending");
		return examTimeTableDao.getAllSubjects(classId);
	}

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: saveExaminationClassMapping Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: saveExaminationClassMapping Ending");
		return examTimeTableDao
				.saveExaminationClassMapping(examinationclassmappinglist);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamdetails();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	public ArrayList<ExamTimetablePojo> getclassdetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getclassdetails();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmListYear() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmListYear();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableListYear();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableClassList(accYear);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getHeading Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getHeading(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getHeading Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getClassExamTimeTableDetails Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getClassExamTimeTableDetails(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getClassExamTimeTableDetails Ending");
		return examListYear;

	}

	@Override
	public String insertExam(ExamDetailsForm addExam) {
		
		ExamTimeTableDao objinsert = new ExamTimeTableDaoImpl();
		return objinsert.insertExam(addExam);
		
		
		
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear,String schoolLocation) {
		ExamTimeTableDao objinsert = new ExamTimeTableDaoImpl();
		return objinsert.getexamsettingsDetails(accyear,schoolLocation);
	}

	@Override
	public String deleteexamSettings(String deleteid,String location,String accyear) {
		ExamTimeTableDao delteobj = new ExamTimeTableDaoImpl();
		return delteobj.deleteexamSettings(deleteid,location,accyear);	
	}

	@Override
	public String editexamsettings(String editid, ExamDetailsForm editExam) {
		ExamTimeTableDao editobj = new ExamTimeTableDaoImpl();
		return editobj.editexamsettings(editid,editExam);	
	}

	
	@Override
	public String checkduplicateExam(String accyear, String examcode,String location,String classid) {
		ExamTimeTableDao checkdupobj = new ExamTimeTableDaoImpl();
		return checkdupobj.checkduplicateExam(accyear,examcode,location,classid);	
	}

	
	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableClassSubjectList(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Ending");
		return examListYear;

	}


	@Override
	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getstatusexamsettingsDetails(accyear,schoolLocation);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear) {
		return null;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getEaxmMarksListYear(accyear,locid);	
	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamdetails(String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamdetails(locid);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamcodeForDependency Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamcodeForDependency Ending");
		return maplist;

	}

	@Override
	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname,String examId) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		return 	 examTimeTableDao.saveDependency(examcode,dependency,mainexamcode,mainexamname,examId);
		
	}

	@Override
	public String insertGradeDependent(String project,String assignment,String practical,String attendance,String classId,String sectionId,String exam,String location,String accyear) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		return 	 examTimeTableDao.insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear);
		
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getstatusgrdDepDetails(accyear,hschoolLocation);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.disstudepdetails(accyear,locid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getToClassDetails(String location, String classId) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getToClassDetails(location,classId);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamTypeList() {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamTypeList();
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamNameList(locationid,classid,accyear);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameList1(String locationid, String classid, String accyear) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamNameList1(locationid,classid,accyear);
	}

	
	
	
}
