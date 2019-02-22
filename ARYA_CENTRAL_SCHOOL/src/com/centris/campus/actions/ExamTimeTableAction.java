package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ExamTimeTableBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassFeeSetupVo;

import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.itextpdf.text.log.SysoLogger;

public class ExamTimeTableAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(ExamTimeTableAction.class);

	public ActionForward getAllSubjects(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getAllSubjects Starting");
		try {
			String examclassiddetails = request.getParameter("classAndExamId");

			String[] examclassid = examclassiddetails.split(",");

			String classId = examclassid[0];
			String exam = examclassid[1];

			ExamTimetablePojo examinationList = new ExamTimetablePojo();
			examinationList = new ExamTimeTableBD().getExamDate(exam);
			ArrayList<ExamTimetablePojo> subject_list = new ExamTimeTableBD()
					.getExamTimeTableDetails(classId, exam);
			ArrayList<ExamTimetablePojo> exam_list = new ArrayList<ExamTimetablePojo>();
			ExamTimetablePojo obj = new ExamTimetablePojo();
			obj.setClassId(classId);
			obj.setExamId(exam);
			exam_list.add(obj);

			request.setAttribute("subject_list", subject_list);
			request.setAttribute("exam_list", exam_list);
			request.setAttribute("examDate", examinationList);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getAllSubjects Ending");
		return mapping.findForward(MessageConstants.ADD_TIME_TABLE);
	}

	public ActionForward getExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getExamDetails Starting");
		try {
			request.setAttribute("examList",new ExamTimeTableBD().getExamdetails());
			request.setAttribute("classList",new ExamTimeTableBD().getclassdetails());

			if ("true".equalsIgnoreCase(request.getParameter("Status"))) {
				request.setAttribute("successMessage",
						MessageConstants.SUCCESSMSG);
			} else if ("false".equalsIgnoreCase(request.getParameter("Status"))) {
				request.setAttribute("errorMessage",
						MessageConstants.UNSUCCESSMSG);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getExamDetails Ending");
		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE);
	}

	public ActionForward saveExaminationClassMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction : saveExaminationClassMapping  Starting");
		try {
			String classid = request.getParameter("classId");
			String examid = request.getParameter("examId");

			String subid = request.getParameter("subjectid_str");
			String date = request.getParameter("date_str");
			String starttime = request.getParameter("time_str");
			String endtime = request.getParameter("end_time_str");
			String crreatedby = HelperClass.getCurrentUserID(request);

			String[] datearray = date.split(",");
			String[] subjectarray = subid.split(",");
			String[] timearray = starttime.split(",");
			String[] endtimearray = endtime.split(",");

			ArrayList<ExamTimetablePojo> examinationclassmappinglist = new ArrayList<ExamTimetablePojo>();

			for (int i = 0; i < subjectarray.length; i++) {

				ExamTimetablePojo voObj = new ExamTimetablePojo();
				voObj.setClassId(classid);
				voObj.setExamId(examid);

				voObj.setSubId(subjectarray[i]);

				voObj.setExamDate(HelperClass.convertUIToDatabase(datearray[i]));

				voObj.setExamStartTime(timearray[i]);

				voObj.setExamEndTime(endtimearray[i]);

				voObj.setCreatedBy(crreatedby);

				examinationclassmappinglist.add(voObj);

			}

			String status = new ExamTimeTableBD()
					.saveExaminationClassMapping(examinationclassmappinglist);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction : saveExaminationClassMapping Ending");
		return null;
	}

	public ActionForward getEaxmListYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMSETTING);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("accYearList", accYearList);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");

		return mapping.findForward(MessageConstants.EXAMLISTYEAR);
	}

	public ActionForward getEaxmTimeTableListYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableListYear Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD().getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList",list);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("accYearList",accYearList);
			/*
			 * ArrayList<ReportMenuVo> classList = new
			 * ReportsMenuBD().getStudentClass(schoolLocation);
			 * request.setAttribute("classList", classList);
			 */

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableListYear Ending");

		return mapping.findForward(MessageConstants.EXAM_TIMETABLE_LISTYEAR);
	}

	public ActionForward getaccyeardetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getaccyeardetails  Starting");
		try {

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examTimeTableListYear = new ExamDetailsBD().examTimeTableListYear(accyear, location);

			JSONObject json = new JSONObject();
			json.put("examTimeTableListYear", examTimeTableListYear);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward setExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMSETTING);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();

		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");

		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass.getPastDateofAcademicYear(accyearid) + 1);

		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass.getForDateofAcademicYear(accyearid));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(currentaccyear);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);

		// For setting Academic year based on academic year id
		String accyear = request.getParameter("accyear");
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		// getting locname
		String currentlocation = new ExamDetailsBD().getlocationname(location);
		request.setAttribute("locName", currentlocation);
		request.setAttribute("locid", location);

		return mapping.findForward(MessageConstants.SETEXAMLIST);
	}

	public ActionForward getexamdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getexamsettingsDetails(accyearid, location);
		request.setAttribute("examsettings", list);

		JSONObject object = new JSONObject();
		object.put("examsettings", list);
		response.getWriter().print(object);
		return null;
	}

	public ActionForward insertexamdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String user = HelperClass.getCurrentUserID(request);
		String academiyear = request.getParameter("academiyear");
		String examcode = request.getParameter("examcode");
		String examname = request.getParameter("examname");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String location = request.getParameter("location");
		String fromclassid = request.getParameter("fromclassid");
		String toClassId = request.getParameter("toClassId");
		String examtype = request.getParameter("examtype");
		String isapplicable = request.getParameter("isapplicable");
		

		ExamDetailsForm addExam = new ExamDetailsForm();
		addExam.setExamcode(examcode);
		addExam.setExamname(examname);
		addExam.setStartdate(startdate);
		addExam.setEnddate(enddate);
		addExam.setAccyear(academiyear);
		addExam.setUser(user);
		addExam.setLocationid(location);
		addExam.setFromClassId(fromclassid);
		addExam.setToClassId(toClassId);
		addExam.setExamtype(examtype);
		addExam.setIsapplicableperiodic(isapplicable);
		
		String addexam = new ExamTimeTableBD().insertExam(addExam);
		JSONObject object = new JSONObject();
		object.put("status", addexam);
		response.getWriter().print(object);
		return null;
	}

	public ActionForward deleteExamSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String deleteid = request.getParameter("examid");
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String delteresult = new ExamTimeTableBD().deleteexamSettings(deleteid,location, accyear);
		JSONObject object = new JSONObject();
		object.put("status", delteresult);
		response.getWriter().print(object);
		return null;
	}

	public ActionForward editExamSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid=HelperClass.getCurrentUserID(request);
		String examcode = request.getParameter("examcode");
		String examname = request.getParameter("examname");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String accyear = request.getParameter("academiyear");
		String location = request.getParameter("location");
		String fromclassid = request.getParameter("fromclassid");
		String examtype = request.getParameter("examtype");
		String isapplicable = request.getParameter("isapplicable");
		ExamDetailsForm editExam = new ExamDetailsForm();
		editExam.setExamcode(examcode);
		editExam.setExamname(examname);
		editExam.setStartdate(startdate);
		editExam.setEnddate(enddate);
		editExam.setAccyear(accyear);
		editExam.setLocationid(location);
		editExam.setFromClassId(fromclassid);
		editExam.setExamtype(examtype);
		editExam.setIsapplicableperiodic(isapplicable);
		editExam.setUserid(userid);
		
		String editid = request.getParameter("examid");
		String editresult = new ExamTimeTableBD().editexamsettings(editid,editExam);
		request.setAttribute("editexamsettings", editresult);
		JSONObject object = new JSONObject();
		object.put("status", editresult);
		response.getWriter().print(object);
		return null;
	}

	public ActionForward checkduplicateExamcode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateGrade  Starting");
		try {

			String accyear = request.getParameter("accyear");
			String examcode = request.getParameter("exmcode");
			String location = request.getParameter("location");
			String classid = request.getParameter("classid");
			String result = new ExamTimeTableBD().checkduplicateExam(accyear,examcode, location,classid);

			JSONObject json = new JSONObject();
			json.put("status", result);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward getEaxmTimeTableClassList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String accYear = request.getParameter("accYear");

			ArrayList<ExaminationDetailsVo> classTimeTableList = new ArrayList<ExaminationDetailsVo>();

			classTimeTableList = new ExamTimeTableBD()
					.getEaxmTimeTableClassList(accYear);

			request.setAttribute("startDate", classTimeTableList.get(0)
					.getStartDate());
			request.setAttribute("endDate", classTimeTableList.get(0)
					.getEndDate());
			request.setAttribute("classTimeTableList", classTimeTableList);
			request.setAttribute("accYear", classTimeTableList.get(0)
					.getAccyear());

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableClassList Ending");

		return mapping.findForward(MessageConstants.EXAM_TIMETABLE_CLASSLIST);
	}

	public ActionForward setClassExamTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getFeeSetupDetails Starting");

		String forward = "";
		try {

			String accYear = request.getParameter("accYear");
			String classId = request.getParameter("classId");
			ExamTimetablePojo exampojo = new ExamTimetablePojo();
			exampojo.setAccyearid(accYear);
			exampojo.setClassId(classId);

			ExamTimeTableBD classExamSetupBD = new ExamTimeTableBD();

			ArrayList<ExaminationDetailsVo> getClassExamWithOutSpecializationDetails = null;
			ArrayList<ExaminationDetailsVo> getSpecializationFeeMasterSetupDetails = null;
			ArrayList<ExaminationDetailsVo> getHeading = null;

			try {

				getHeading = classExamSetupBD.getHeading(exampojo);
				if (getHeading.get(0).getSpecilazationCount() == 0) {

					getClassExamWithOutSpecializationDetails = classExamSetupBD
							.getClassExamTimeTableDetails(exampojo);
					request.setAttribute(
							"getClassExamWithOutSpecializationDetails",
							getClassExamWithOutSpecializationDetails);
					request.setAttribute("accYear",
							getClassExamWithOutSpecializationDetails.get(0)
									.getAccyear());
					request.setAttribute("className",
							getClassExamWithOutSpecializationDetails.get(0)
									.getClassname());

					forward = MessageConstants.SET_EXAMTIMETABLE_UPTO_TENTH;

				}
				/*
				 * else{ getSpecializationFeeMasterSetupDetails =
				 * feeSetupBD.getApprovedFees(feeSetupPojo);
				 * 
				 * 
				 * request.setAttribute("getSpecializationFeeMasterSetupDetails",
				 * getSpecializationFeeMasterSetupDetails);
				 * forward=MessageConstants.FEE_SETUP_ENTRY_WITH_SPECIALIZATION;
				 * }
				 */

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getFeeSetupDetails Ending");

		return mapping.findForward(forward);
	}

	public ActionForward setClassSubjectExamTimeTableDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setClassSubjectExamTimeTableDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String accYear = request.getParameter("accYear");
			String classId = request.getParameter("classId");
			String examId = request.getParameter("examId");

			ExamTimetablePojo exampojo = new ExamTimetablePojo();
			exampojo.setAccyearid(accYear);
			exampojo.setClassId(classId);
			exampojo.setExamId(examId);

			ArrayList<ExaminationDetailsVo> classSubjectTimeTableList = new ArrayList<ExaminationDetailsVo>();

			classSubjectTimeTableList = new ExamTimeTableBD()
					.getEaxmTimeTableClassSubjectList(exampojo);

			request.setAttribute("classSubjectTimeTableList",
					classSubjectTimeTableList);

			request.setAttribute("accYear", classSubjectTimeTableList.get(0)
					.getAccyear());
			request.setAttribute("examCode", classSubjectTimeTableList.get(0)
					.getExamCode());
			request.setAttribute("examName", classSubjectTimeTableList.get(0)
					.getExamName());
			request.setAttribute("examStartDate", classSubjectTimeTableList
					.get(0).getExamstartdate());
			request.setAttribute("examEndDate", classSubjectTimeTableList
					.get(0).getExamenddate());
			request.setAttribute("className", classSubjectTimeTableList.get(0)
					.getClassname());
			request.setAttribute("sectionName", classSubjectTimeTableList
					.get(0).getSection());

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setClassSubjectExamTimeTableDetails Ending");

		return mapping
				.findForward(MessageConstants.EXAM_TIMETABLE_CLASSSUBJECTLIST);
	}

	public ActionForward getExamMarksStudentwise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
			/*
			 * String currentaccyear =
			 * request.getSession(false).getAttribute("current_academicYear"
			 * ).toString(); String searchTerm =
			 * request.getParameter("searchTerm");
			 * 
			 * ArrayList<ExaminationDetailsVo> examListYear = new
			 * ArrayList<ExaminationDetailsVo>();
			 * 
			 * if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {
			 * 
			 * examListYear = new
			 * ExamTimeTableBD().getEaxmListYear(currentaccyear);
			 * 
			 * } request.setAttribute("startDate",
			 * examListYear.get(0).getStartDate());
			 * request.setAttribute("endDate",
			 * examListYear.get(0).getEndDate());
			 * request.setAttribute("examListYear", examListYear);
			 */

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("accYearList", accYearList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");
		return mapping.findForward(MessageConstants.EXAMARKS_STUDENTWISELIST);
	}

	public ActionForward setMarkEntryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String accyear = request.getParameter("accyear");
		String hschoolLocation = request.getParameter("hschoolLocation");
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getstatusexamsettingsDetails(accyear,hschoolLocation);
		request.setAttribute("examsettings", list);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(hschoolLocation);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", hschoolLocation);
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_DETAILS);

	}

	public ActionForward setMarkEntryStudentandClasswise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
		
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("hschoolLocation");

		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		String examid = request.getParameter("examid");
		String classid = request.getParameter("classid");
		String examname = new ExamDetailsBD().getexamName(examid, accyear,locid);
		String examarray[] = examname.split(",");
		String examName = examarray[0];
		String examCode = examarray[1];

		request.setAttribute("examName", examName);
		request.setAttribute("examCode", examCode);
		request.setAttribute("examid", examid);
		if(classid != "CCD14" || classid != "CCD15"){
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClass(accyear, examid, locid,classid);
			request.setAttribute("subjectClassList", list);
		}else{
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClassBySpec(accyear, examid, locid,classid);
			request.setAttribute("subjectSpecClassList", list);
		}
		
		
		ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		list1 = new ExamTimeTableBD().getstatusexamsettingsDetails(accyear,schoolLocation);
		request.setAttribute("examsettings", list1);

		String currentlocation = new ExamDetailsBD().getlocationname(schoolLocation);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", locid);
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_CLASSWISEDETAILS);

	}

	public ActionForward setMarkEntryStudentwise(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Starting");
		try {

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);

			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String hschoolLocation = request.getParameter("hschoolLocation");
			ExaminationDetailsVo obj = new ExaminationDetailsVo();

			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(hschoolLocation);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getexamclassDetails(obj);
			request.setAttribute("examid", examid);
			request.setAttribute("classId", classId);
			request.setAttribute("sectionId", sectionId);
			request.setAttribute("accyear", accyear);
			request.setAttribute("accyName", list.get(0).getAccyear());
			request.setAttribute("classname", list.get(0).getClassname());
			request.setAttribute("sectionname", list.get(0).getSectionName());
			request.setAttribute("examcode", list.get(0).getExamCode());
			request.setAttribute("examname", list.get(0).getExamName());
			request.setAttribute("startdate", list.get(0).getStartDate());
			request.setAttribute("enddate", list.get(0).getEndDate());

			ArrayList<ExaminationDetailsVo> studentlist = new ArrayList<ExaminationDetailsVo>();
			studentlist = new ExamDetailsBD().classWiseStudent(obj);
			System.out.println("classwise student:" + studentlist);

			request.setAttribute("studentlist", studentlist);
			String currentlocation = new ExamDetailsBD().getlocationname(schoolLocation);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locationid", hschoolLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_STUDENTWISE);
	}

	public ActionForward AddMarkEntryStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			//System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String hschoolLocation = request.getParameter("hschoolLocation");
			String admissionno = request.getParameter("admissionno");
			String studentname = request.getParameter("studentname");
			request.setAttribute("admno", admissionno);
			request.setAttribute("stdname", studentname);
			ExaminationDetailsVo obj = new ExaminationDetailsVo();

			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(hschoolLocation);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getexamclassDetails(obj);
			request.setAttribute("examid", examid);
			request.setAttribute("classId", classId);
			request.setAttribute("sectionId", sectionId);
			request.setAttribute("accyear", accyear);
			request.setAttribute("accyName", list.get(0).getAccyear());
			request.setAttribute("classname", list.get(0).getClassname());
			request.setAttribute("sectionname", list.get(0).getSectionName());
			request.setAttribute("examcode", list.get(0).getExamCode());
			request.setAttribute("examname", list.get(0).getExamName());
			request.setAttribute("startdate", list.get(0).getStartDate());
			request.setAttribute("enddate", list.get(0).getEndDate());
			request.setAttribute("workedu_marks", list.get(0).getWorkedu_grade());
			request.setAttribute("artedu_marks", list.get(0).getArtedu_grade());
			request.setAttribute("healthedu_marks", list.get(0).getHealthedu_grade());
			request.setAttribute("discipline_marks", list.get(0).getDisciplinedu_grade());
			request.setAttribute("examtypeprefix", list.get(0).getExamtypeprefix());
			String examtypeprefix=(String) request.getAttribute("examtypeprefix");
			System.out.println("examtypeprefix"+examtypeprefix);

			ArrayList<ExaminationDetailsVo> studentlist = new ArrayList<ExaminationDetailsVo>();
			studentlist = new ExamDetailsBD().classWiseStudent(obj);

			request.setAttribute("admissionno", studentlist.get(0).getAdmissionno());
			request.setAttribute("studentname", studentlist.get(0).getStudentname());
			request.setAttribute("studentid", studentlist.get(0).getStudentid());
			request.setAttribute("studentlist", studentlist);
			String classId1 = request.getParameter("classId");
			String hschoolLocation1 = request.getParameter("hschoolLocation");
			String studentid = request.getParameter("studentid");
			ExaminationDetailsVo obj1 = new ExaminationDetailsVo();
			obj1.setClassid(classId1);
			obj1.setLocationid(hschoolLocation1);
			obj1.setStudentid(studentid);
			obj1.setExamid(examid);
			obj1.setExamtypeprefix(examtypeprefix);
			obj1.setAccyearid(accyear);
			ArrayList<ExaminationDetailsVo> resultlist = new ExamDetailsBD().getStudentDetails(obj1);

			request.setAttribute("markdetailslist", resultlist);
			String currentlocation = new ExamDetailsBD().getlocationname(hschoolLocation);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locationid", hschoolLocation);

			String studentid1 = request.getParameter("studentid");
			request.setAttribute("hiddenstudentid", studentid1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.ADDMARK_ENTRY_STUDENTWISE);

	}

	public ActionForward SaveMarkEntryStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String createdby = HelperClass.getCurrentUserID(request);
		String subids[] = request.getParameterValues("subids[]");
		String statusvalues[] = request.getParameterValues("statusvalues[]");
		String scoremarks[] = request.getParameterValues("scoredmarks[]");
		String primaryidstu[] = request.getParameterValues("primaryidstu[]");
		String notebookmarks[] = request.getParameterValues("notebookmarks[]");
		String subjectenrichment[] = request.getParameterValues("subjectenrichment[]");
		String maxnotebookmarks[] = request.getParameterValues("maxnotebookmarks[]");
		String maxsubjectenrichmarks[] = request.getParameterValues("maxsubjectenrichmarks[]");
		String maxscoredmarks[] = request.getParameterValues("maxscoredmarks[]");
		String maxperiodicmarks[] = request.getParameterValues("maxperiodicmarks[]");
		String periodicscoredmarks[] = request.getParameterValues("periodicscoredmarks[]");
		String accyear = request.getParameter("accyear");
		String examid = request.getParameter("examid");
		String classId = request.getParameter("classId");
		String sectionId = request.getParameter("sectionId");
		String studentid = request.getParameter("studentid");
		String locationid = request.getParameter("hschoolLocation");
		String studentname = request.getParameter("studentname");
		String workedu = request.getParameter("workedu");
		String artedu = request.getParameter("artedu");
		String disciplineedu = request.getParameter("disciplineedu");
		String healthedu = request.getParameter("healthedu");
		String examtypeprfix = request.getParameter("examtypeprfix");
		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyear(accyear);
		obj.setExamid(examid);
		obj.setClassid(classId);
		obj.setSectionid(sectionId);
		obj.setSubid(subids);
		obj.setScoremarks(scoremarks);
		obj.setStatusvalues(statusvalues);
		obj.setStudentname(studentname);
		obj.setStudentid(studentid);
		obj.setLocationid(locationid);
		obj.setStuprimaryid(primaryidstu);
		obj.setNotebookmarks(notebookmarks);
		obj.setSubjectenrichment(subjectenrichment);
		obj.setMaxnotebookmarks(maxnotebookmarks);
		obj.setMaxsubenrichmentmarks(maxsubjectenrichmarks);
		obj.setMax_scored_marks(maxscoredmarks);
		obj.setWorkedu_grade(workedu);
		obj.setArtedu_grade(artedu);
		obj.setDisciplinedu_grade(disciplineedu);
		obj.setHealthedu_grade(healthedu);
		obj.setCreatedBy(createdby);
		obj.setMaxperiodicmarks(maxperiodicmarks);
		obj.setPeriodicscoredmarks(periodicscoredmarks);
		obj.setExamtypeprefix(examtypeprfix);
		
		String result = null;
		result = new ExamDetailsBD().insertmarkentrydetails(obj);
		JSONObject object = new JSONObject();
		object.put("status", result);
		response.getWriter().print(object);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(schoolLocation);
		request.setAttribute("currentlocation", currentlocation);
		return null;
	}

	public ActionForward subjectmarksList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : subjectmarksList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);

			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("accYearList", accYearList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("subjetmarkList");
	}

	public ActionForward getExamCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		ArrayList<ExaminationDetailsVo> examcodes = new ExamDetailsBD()
				.getlistofExamCodes(schoolLocation);
		return null;
	}

	public ActionForward ExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();
		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass
				.getPastDateofAcademicYear(currentaccyear) + 1);
		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass
				.getForDateofAcademicYear(currentaccyear));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(currentaccyear);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);
		// For setting Academic year based on academic year id
		String accyear = request.getParameter("accyear");
		String accyname = new ExamDetailsBD().getaccyName(currentaccyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getexamsettingsDetails(currentaccyear,
				schoolLocation);
		request.setAttribute("examsettings", list);

		JSONObject obj = new JSONObject();
		obj.put("examsettings", list);
		response.getWriter().print(obj);
		return null;
	}

	public ActionForward getexamtimetableclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
		
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");

		String currentlocation = new ExamDetailsBD().getlocationname(locid);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);

		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);

		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_CLASS);
	}

	public ActionForward getexamtimetableclasssection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String classname = request.getParameter("classname");
		String examid = request.getParameter("examid");
		String currentlocation = new ExamDetailsBD().getlocationname(locid);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);

		request.setAttribute("classid", classid);
		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);
		request.setAttribute("classname", classname);
		request.setAttribute("examid", examid);
		List<ExaminationDetailsVo> ClassList = new ExamDetailsBD()
				.getExamClassByLocation(locid, accyear, examid);
		request.setAttribute("ClassList", ClassList);
		return mapping
				.findForward(MessageConstants.EXAM_TIME_TABLE_CLASS_SECTION);
	}

	public ActionForward getexamlistbyclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String accyear = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classid = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String examid = request.getParameter("examid");
		/*
		 * String currentlocation =new ExamDetailsBD().getlocationname(locid);
		 * request.setAttribute("location",currentlocation); String accyname
		 * =new ExamDetailsBD().getaccyName(accyear);
		 * request.setAttribute("accyName",accyname);
		 */
		ExamTimetablePojo pojo = new ExamTimetablePojo();
		pojo.setLocid(location);
		pojo.setAccyearid(accyear);
		pojo.setClassId(classid);
		pojo.setSectionid(sectionid);
		pojo.setExamId(examid);
		List<ExaminationDetailsVo> examlist = new ExamDetailsBD()
				.getexamlistbyclass(pojo);

		JSONObject obj = new JSONObject();
		obj.put("examlist", examlist);
		response.getWriter().print(obj);

		return null;
	}

	public ActionForward dispalyexamsavepage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String section = request.getParameter("section");
			String examid = request.getParameter("examid");
			
			String currentlocation = new ExamDetailsBD().getlocationname(locid);
			request.setAttribute("location", currentlocation);
			String accyname = new ExamDetailsBD().getaccyName(accyear);
			request.setAttribute("accyName", accyname);

			request.setAttribute("locid", locid);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("section", section);
			request.setAttribute("examid", examid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(section);

			ExaminationDetailsVo examdetails = new ExamDetailsBD().getexamdetails(pojo);
			request.setAttribute("examdetails", examdetails);
			
			int minDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()), examdetails.getExamstartdate());
			request.setAttribute("minDate", minDate);
			int maxDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()), examdetails.getExamenddate());
			request.setAttribute("maxDate", maxDate);
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_DISPLAY_SAVE);
	}

	public ActionForward getsubdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");

			request.setAttribute("locid", location);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("examid", examid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);

			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD()
					.getsubdetails(pojo);

			JSONObject obj = new JSONObject();
			obj.put("sublist", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward savetimetabledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");

			if (sectionid.equalsIgnoreCase("all")) {
				sectionid = "%%";
			}

			String subid1[] = request.getParameterValues("subid1[]");
			String subcode1[] = request.getParameterValues("subcode1[]");
			String subname1[] = request.getParameterValues("subname1[]");
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			String list = new ExamDetailsBD().savetimetabledetails(pojo,
					subid1, starttime1, endtime1, startdate);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward updatetimetabledetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");
			String ttid = request.getParameter("timetable");
			if (sectionid.equalsIgnoreCase("all")) {
				sectionid = "%%";
			}

			String subid1[] = request.getParameterValues("subid1[]");
			String subcode1[] = request.getParameterValues("subcode1[]");
			String subname1[] = request.getParameterValues("subname1[]");
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setTimetableid(ttid);
			String list = new ExamDetailsBD().updatetimetabledetailsset(pojo,
					subid1, starttime1, endtime1, startdate);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward getexamsbtselection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examlist = new ExamDetailsBD()
					.getexamsbtselection(accyear, locid);

			JSONObject obj = new JSONObject();
			obj.put("examlist", examlist);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward disstumarksdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
			examListYear = new ExamTimeTableBD().getEaxmMarksListYear(accyear,
					locid);

			JSONObject obj = new JSONObject();
			obj.put("markslist", examListYear);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward displaysubmarkslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.getAccYearsSubject(accyear, locid);
			request.setAttribute("AccYearList", accYearList);

			JSONObject obj = new JSONObject();
			obj.put("examlist", accYearList);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward getclasslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			String examcode = request.getParameter("examcode");

			List<ExaminationDetailsVo> ClassList = new ArrayList<ExaminationDetailsVo>();

			if (examcode == "" || examcode == null) {
			
				ClassList = new ExamDetailsBD().getExamClassByLocation(locid,accyear);
				request.setAttribute("ClassList", ClassList);
			} else {
				ClassList = new ExamDetailsBD().getExamClassByLocation(locid,accyear,examcode);
				request.setAttribute("ClassList", ClassList);
			}

			JSONObject obj = new JSONObject();
			obj.put("ClassList", ClassList);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward checkduplicatedate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setExamStartdate(HelperClass.convertUIToDatabase(startdate));
			pojo.setExamEndDate(HelperClass.convertUIToDatabase(enddate));
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setClassId(classid);
			String duplicate = new ExamDetailsBD().checkduplicatedate(pojo);
			JSONObject obj = new JSONObject();
			obj.put("status", duplicate);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward dispalyexamsavepagedata(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String section = request.getParameter("section");
			String examid = request.getParameter("examid");
			String timetableid = request.getParameter("timetableid");
			System.out.println(section);
			String currentlocation = new ExamDetailsBD().getlocationname(locid);
			request.setAttribute("location", currentlocation);
			String accyname = new ExamDetailsBD().getaccyName(accyear);
			request.setAttribute("accyName", accyname);

			request.setAttribute("locid", locid);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("section", section);
			request.setAttribute("examid", examid);
			request.setAttribute("timetableid", timetableid);
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(section);
			pojo.setTimetableid(timetableid);

			ExaminationDetailsVo examdetails = new ExamDetailsBD()
					.getexamdetailsbyset(pojo);
			request.setAttribute("examdetails", examdetails);
			int minDate = HelperClass.totalDaysBetweenTwoDates(HelperClass
					.convertDatabaseToUI(HelperClass.getCurrentSqlDate()
							.toString()), examdetails.getExamstartdate());
			System.out.println(minDate);
			System.out.println(HelperClass.convertDatabaseToUI(HelperClass
					.getCurrentSqlDate().toString()));
			request.setAttribute("minDate", minDate);
			int maxDate = HelperClass.totalDaysBetweenTwoDates(HelperClass
					.convertDatabaseToUI(HelperClass.getCurrentSqlDate()
							.toString()), examdetails.getExamenddate());
			request.setAttribute("maxDate", maxDate);
			System.out.println(maxDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping
				.findForward(MessageConstants.EXAM_TIME_TABLE_DISPLAY_SAVE_PAGE);
	}

	public ActionForward getsubdetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String timetableid = request.getParameter("timetableid");

			request.setAttribute("locid", location);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("examid", examid);
			request.setAttribute("timetableid", timetableid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setTimetableid(timetableid);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getsubdetailsset(pojo);
		
			JSONObject obj = new JSONObject();
			obj.put("sublist", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward savetimetabledetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");
			String timetableid = request.getParameter("timetable");

			if (sectionid.equalsIgnoreCase("all")) {
				sectionid = "%%";
			}

			String subid1[] = request.getParameterValues("subid1[]");
			
			System.out.println(subid1[0]);
			System.out.println(subid1[1]);
			/*String subcode1[] = request.getParameterValues("subcode1[]");
			String subname1[] = request.getParameterValues("subname1[]");*/
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setTimetableid(timetableid);
			String list = new ExamDetailsBD().savetimetabledetailsset(pojo,
					subid1, starttime1, endtime1, startdate);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward examdependency(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {
			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("accYearList", accYearList);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME , LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMDEPENDANCY);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");
		return mapping.findForward(MessageConstants.EXAMDEPENDENCY);
	}

	public ActionForward setExamDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME ,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMDEPENDANCY);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String currentaccyear = request.getSession(false)
				.getAttribute("current_academicYear").toString();

		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");

		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass
				.getPastDateofAcademicYear(accyearid) + 1);

		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass
				.getForDateofAcademicYear(accyearid));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(currentaccyear);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);

		// For setting Academic year based on academic year id
		String accyear = request.getParameter("accyear");
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		// getting locname
		String currentlocation = new ExamDetailsBD().getlocationname(location);
		request.setAttribute("locName", currentlocation);
		request.setAttribute("locid", location);

		return mapping.findForward(MessageConstants.SETEXAMDEPENDENCY);
	}

	public ActionForward getExamcodeForDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getExamDetails Starting");
		try {
			
			String locid = request.getParameter("locid");
			String accYear = request.getParameter("accYear"); 
			String startdate = request.getParameter("stDate"); 
			String enddate = request.getParameter("enDate"); 
			String examCode = request.getParameter("exCode"); 
			
			ArrayList<ExamTimetablePojo>  examList =new ExamTimeTableBD().getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);

			JSONObject json = new JSONObject();

			json.put("exam_Lists",examList);

			response.getWriter().print(json);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getExamDetails Ending");
		return null;
	}

	public ActionForward gradeDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("accYearList", accYearList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");
		return mapping.findForward(MessageConstants.GRADE_DEP);
	}
	
	public ActionForward setGradeDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);
		
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String accyear = request.getParameter("accyear");
		String hschoolLocation = request.getParameter("hschoolLocation");
		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getstatusgrdDepDetails(accyear,
				hschoolLocation);
		request.setAttribute("examsettings", list);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(hschoolLocation);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", hschoolLocation);
		return mapping.findForward(MessageConstants.SET_GRADE_DETAILS);

	}

	public ActionForward setGradeDependencyinDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);

request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		System.out.println("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("hschoolLocation");

		String accyname = new ExamDetailsBD().getaccyName(accyear);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		String examid = request.getParameter("examid");
		String classid = request.getParameter("classid");
		String examname = new ExamDetailsBD().getexamName(examid, accyear,
				locid);
		String examarray[] = examname.split(",");
		String examName = examarray[0];
		String examCode = examarray[1];

		request.setAttribute("examName", examName);
		request.setAttribute("examCode", examCode);
		request.setAttribute("examid", examid);
		ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClass(accyear, examid, locid,classid);

		request.setAttribute("subjectClassList", list);
		ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		list1 = new ExamTimeTableBD().getstatusexamsettingsDetails(accyear,
				schoolLocation);
		request.setAttribute("examsettings", list1);

		String currentlocation = new ExamDetailsBD()
				.getlocationname(schoolLocation);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", locid);
		
		return mapping.findForward(MessageConstants.GRADE_DEPENDENCY_DETAIL);

	}
	
	public ActionForward saveExamDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		
		String[] examcode=request.getParameterValues("myselectArray[]");
		String[] dependency=request.getParameterValues("myinputArray[]");
		String mainexamcode=request.getParameter("examcode");
		String mainexamname=request.getParameter("examname");
		String examId=request.getParameter("examId");
	      String result=new ExamTimeTableBD().saveDependency(examcode,dependency,mainexamcode,mainexamname,examId);
	
		JSONObject json = new JSONObject();
		json.put("status", result);
		response.getWriter().print(json);

		return null;

	}
	public ActionForward insertGradeDependent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		
		String project=request.getParameter("project");
		String assignment=request.getParameter("assignment");
		String practical=request.getParameter("practical");
		String attendance=request.getParameter("attendance");
		String classId=request.getParameter("classId");
		String sectionId=request.getParameter("sectionId");
		String exam=request.getParameter("exam");
		String location=request.getParameter("location");
		String accyear=request.getParameter("accyear");
	    String result=new ExamTimeTableBD().insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear);
	
		JSONObject json = new JSONObject();
		json.put("status", result);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward disstudepdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
			examListYear = new ExamTimeTableBD().disstudepdetails(accyear,
					locid);

			JSONObject obj = new JSONObject();
			obj.put("markslist", examListYear);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getToClassDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String classId = request.getParameter("classId");
		String location = request.getParameter("location");
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getToClassDetails(location,classId);

		JSONObject object = new JSONObject();
		object.put("ClassList", list);
		response.getWriter().print(object);
		return null;
	}
	
	public ActionForward getExamTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamTypeList();

		JSONObject object = new JSONObject();
		object.put("ExamList", list);
		response.getWriter().print(object);
		return null;
	}
	public ActionForward getExamNameList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamNameList(locationid,classid,accyear);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		return null;
	}
	public ActionForward getExamNameList1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamNameList1(locationid,classid,accyear);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		return null;
	}
	

}
