package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class StudentAttendanceAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(StudentAttendanceAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward studentattendaceUploadEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : studentattendaceUpload Starting");
		
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : studentattendaceUpload Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_ATT_ENTRY);
	}
	
	public ActionForward getStudentAttendanceDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Starting");
		
		String classId=request.getParameter("classId");
		String section=request.getParameter("section");
		String date=request.getParameter("date");
		String spec = request.getParameter("spec");
		String teacher=request.getParameter("teacher");
		
		StudentAttendancePojo studentPojo=new StudentAttendancePojo();
		studentPojo.setClassId(classId);
		studentPojo.setSectinId(section);
		studentPojo.setDate(date);
		studentPojo.setSpecID(spec);
		
		ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(studentPojo);
		
		request.setAttribute("attendanceList", studentAttendanceList);
		
		request.setAttribute("classId", classId);
		request.setAttribute("section", section);
		request.setAttribute("date", date);
		request.setAttribute("teacher", teacher);
		request.setAttribute("spec", spec);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_ATT_ENTRY);
	}
	
	public ActionForward updateAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateAttendanceStatus Starting");
		
		
		String date = request.getParameter("date");
		String locationId=request.getParameter("locationId");
		String teacherId=request.getParameter("teacherid");
		String studentId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		String period1 = request.getParameter("period1");
		String period2 = request.getParameter("period2");
		String period3 = request.getParameter("period3");
		String period4 = request.getParameter("period4");
		String period5 = request.getParameter("period5");
		String period6 = request.getParameter("period6");
		String period7 = request.getParameter("period7");
		String period8 = request.getParameter("period8");
		String period9 = request.getParameter("period9");
		
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setPeriod1(period1);
		attendancepojo.setPeriod2(period2);
		attendancepojo.setPeriod3(period3);
		attendancepojo.setPeriod4(period4);
		attendancepojo.setPeriod5(period5);
		attendancepojo.setPeriod6(period6);
		attendancepojo.setPeriod7(period7);
		attendancepojo.setPeriod8(period8);
		attendancepojo.setPeriod9(period9);
		attendancepojo.setStudentid(studentId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setLocationId(locationId);		
		String status=new StudentAttendanceBD().updateAttendanceStatus(attendancepojo);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateAttendanceStatus Ending");
	
		
		return null;
	}
	
	public ActionForward getStudentPeriodAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentPeriodAttendance Starting");
		
		String stuId = request.getParameter("stuId");
		String classId=request.getParameter("classId");
		String sectionId=request.getParameter("section");
		String date=request.getParameter("date");
		String status=request.getParameter("status");
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setStudentid(stuId.split(",")[0]);
		attendancepojo.setStudentname(stuId.split(",")[1]);
		attendancepojo.setStatus(status);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setClassId(classId.split(",")[0]);
		attendancepojo.setClassname(classId.split(",")[1]);
		attendancepojo.setSectinId(sectionId.split(",")[0]);
		attendancepojo.setSectionname(sectionId.split(",")[1]);
		
		StudentAttendanceVo stuAttVo=new StudentAttendanceBD().getStudentPeriodAttendance(attendancepojo);
		
		request.setAttribute("stuAttVo", stuAttVo);
		request.setAttribute("attendancepojo", attendancepojo);
	
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentPeriodAttendance Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_PERIOD_ATT);
	}
	
	public ActionForward updateStudentPeriodAtt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateStudentPeriodAtt Starting");
		
		String stuId = request.getParameter("studentId");
		String classId=request.getParameter("classsId");
		String sectionId=request.getParameter("sectionId");
		String date=request.getParameter("date");
		
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setStudentid(stuId);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setClassId(classId);
		attendancepojo.setSectinId(sectionId);
		attendancepojo.setPeriod1(request.getParameter("period1"));
		attendancepojo.setPeriod2(request.getParameter("period2"));
		attendancepojo.setPeriod3(request.getParameter("period3"));
		attendancepojo.setPeriod4(request.getParameter("period4"));
		attendancepojo.setPeriod5(request.getParameter("period5"));
		attendancepojo.setPeriod6(request.getParameter("period6"));
		attendancepojo.setPeriod7(request.getParameter("period7"));
		attendancepojo.setPeriod8(request.getParameter("period8"));
		
		String status=new StudentAttendanceBD().updateStudentPeriodAtt(attendancepojo);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
	
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateStudentPeriodAtt Ending");
	
		
		return null;
	}
	
	public ActionForward downloadStudentAttendanceDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/Studentattendancexls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			String startdate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			
			ArrayList<StudentAttendanceVo> studentAttendance=new StudentAttendanceBD().getStudentsAttendanceList(startdate, endDate);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentAttendance);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/Studentattendancexls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Attendance Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/Studentattendancexls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Studentattendancexls.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStudentAttendanceDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS  Starting");

			try {

				String startdate=request.getParameter("startDate");
				String endDate=request.getParameter("endDate");
				
				ArrayList<StudentAttendanceVo> studentAttendance=new StudentAttendanceBD().getStudentsAttendanceList(startdate, endDate);
				
				
				String sourceFileName = request
						.getRealPath("Reports/studentattendancedetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						studentAttendance);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentAttendanceDetailsPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}

			catch (Exception e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS  Ending");
			return null;

		}
	
	public ActionForward getteacherByClass(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getteacherByClass  Starting");

	try{
	
		String classId=request.getParameter("classId");
		String sectionId = request.getParameter("sectionId");
		
		StudentAttendanceBD obj= new StudentAttendanceBD();
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		list = obj.getteacherByClass(classId,sectionId);
		
		JSONObject jsonObject = new JSONObject(list);
		jsonObject.accumulate("teacherName", list);
		response.getWriter().print(jsonObject);
		
	}
	catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}

	public ActionForward getClassSpec(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getClassSpec  Starting");

	try{
		String classId = request.getParameter("classidVal");
		
		StudentAttendanceBD obj= new StudentAttendanceBD();
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		list = obj.getClassSpec(classId);
		
		request.setAttribute("specList", list);
		JSONObject jsonObject = new JSONObject(list);
		jsonObject.accumulate("specList", list);
		response.getWriter().print(jsonObject);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getClassSpec Ending");
	
	return null;
}

	public ActionForward editAttendance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : editAttendance  Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
		
		String input=request.getParameter("idString");
		
		StudentAttendancePojo pojo = new StudentAttendancePojo();
		
		pojo.setClassId(input.split(",")[0]);
		pojo.setSectinId(input.split(",")[1]);
		pojo.setDate(input.split(",")[2]);
		pojo.setSpecID(input.split(",")[3]);
		pojo.setTeacherId(input.split(",")[4]);
		
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		
		StudentAttendanceVo stuAttVo=new StudentAttendanceBD().editAttendance(pojo);
		ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(pojo);
	
		stuAttVo.setDate(pojo.getDate());
		request.setAttribute("attendancelist", studentAttendanceList);	
		request.setAttribute("stuattEdit", stuAttVo);

		request.setAttribute("attendancepojo", pojo);
		
		
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : editAttendance Ending");
	
	return mapping.findForward(MessageConstants.GET_EDIT_ATT);
}

	public ActionForward getStudentAttendanceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Starting");
	
		String classId=request.getParameter("classId");
		String section=request.getParameter("section");
		String date=request.getParameter("date");
		String spec = request.getParameter("spec");
		String teacher=request.getParameter("teacher");
	
		StudentAttendancePojo studentPojo=new StudentAttendancePojo();
		studentPojo.setClassId(classId);
		studentPojo.setSectinId(section);
		studentPojo.setDate(date);
		studentPojo.setSpecID(spec);
	
		ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(studentPojo);
	
		//request.setAttribute("attendanceList", studentAttendanceList);
	
		request.setAttribute("classId", classId);
		request.setAttribute("section", section);
		request.setAttribute("date", date);
		request.setAttribute("teacher", teacher);
		request.setAttribute("spec", spec);
	
		JSONObject jsonObject = new JSONObject(studentAttendanceList);
		jsonObject.accumulate("attendanceList", studentAttendanceList);
		response.getWriter().print(jsonObject);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Ending");
	
	
		return null;
	}

	public ActionForward searchAttendanceYearList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : searchAllAcademicYearDetails Starting");
		
		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");
		
		List<StudentAttendanceVo> list= null;
		
		try {
 			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){
				
				locationId="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){
				
				accYear="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){
				
				locationId="%%";
				accYear="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear);
			}else{
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear);
			}
			
			//request.setAttribute("SearchAttendanceList", list);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchAttendanceList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : searchAllAcademicYearDetails Ending");

		return null;
	}

	public ActionForward getAttendenceByClassList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Starting");

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
				list = new StudentAttendanceBD().getAttendenceByClassList(locationid,accyear,classname);
			}
			else{
				list = new StudentAttendanceBD().getAttendenceByClassList(locationid,accyear,classname);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Ending");

		return null;

	}
	
	public ActionForward getAttendenceByClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String accyear=request.getParameter("accyear");
			String locationid=request.getParameter("location");
			String classname=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				list = new StudentAttendanceBD().getAttendenceByClassSectionList(locationid,accyear,classname,sectionid);
			}
			else{
				list = new StudentAttendanceBD().getAttendenceByClassSectionList(locationid,accyear,classname,sectionid);
			}
			
			//list = delegateObj.getPromotedClassSectionList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Ending");

		return null;
	}
	
	public ActionForward getTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTeacherList Starting");

		try {

			String locationid = request.getParameter("locationId");
			
			List<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
			
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
				list = new StudentAttendanceBD().getTeacherList(locationid);
			}
			else{
				list = new StudentAttendanceBD().getTeacherList(locationid);
			}

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getTeacherList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTeacherList Ending");

		return null;

	}
	
	public ActionForward getAttendanceListByTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceListByTeacher Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String accyear=request.getParameter("accyear");
			String locationid=request.getParameter("location");
			String classname=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			String teacherid=request.getParameter("teacherid");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			
			if(!locationid.equalsIgnoreCase("all") && teacherid.equalsIgnoreCase("all")){
				teacherid="%%";
				sectionid="%%";
				classname="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
			}else if(locationid.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && !teacherid.equalsIgnoreCase("all")){
				sectionid="%%";
				classname="%%";
				locationid="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
			}else if(!locationid.equalsIgnoreCase("all") && !teacherid.equalsIgnoreCase("all")){
				sectionid="%%";
				classname="%%";
				
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
			}else if(locationid.equalsIgnoreCase("all") && teacherid.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")){
				teacherid="%%";
				sectionid="%%";
				classname="%%";
				locationid="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
			}else{
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceListByTeacher Ending");

		return null;
	}
	
	public ActionForward daterange(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : daterange Starting");

		try {
			
			String accyear = request.getParameter("accyear");

			String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accyear)+1);
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(accyear));

			JSONObject obj=new JSONObject();

			obj.put("startDate", startDate+","+enddate);

			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : daterange Ending");

		return null;

	}
	
	public ActionForward getAttendanceListByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceListByDate Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
						
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			
			list = new StudentAttendanceBD().getAttendanceListByDate(startdate,enddate);			

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceListByDate Ending");

		return null;
	}
	
	public ActionForward modifyAttendance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getClassSpec  Starting");
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
			
		/*	String input=request.getParameter("idString");*/
			
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String date = request.getParameter("date");
			String spec = request.getParameter("spec");
			String teacher = request.getParameter("teacher");
			
			StudentAttendancePojo pojo = new StudentAttendancePojo();
			
			pojo.setClassId(classId);
			pojo.setSectinId(section);
			pojo.setDate(date);
			pojo.setSpecID(spec);
			pojo.setTeacherId(teacher);
			
			StudentAttendanceVo stuAttVo=new StudentAttendanceBD().editAttendance(pojo);
			ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(pojo);
		
			stuAttVo.setDate(pojo.getDate());
			request.setAttribute("attendancelist", studentAttendanceList);	
			request.setAttribute("stuattEdit", stuAttVo);

			request.setAttribute("attendancepojo", pojo);
			
			JSONObject object=new JSONObject();
			object.put("attendancelist", studentAttendanceList);
			
			response.getWriter().print(object); 
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return null;
	}
	
}
