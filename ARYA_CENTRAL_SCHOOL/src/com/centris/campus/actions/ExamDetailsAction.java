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

import com.centris.campus.delegate.CreateExaminationBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.UserDetailVO;

public class ExamDetailsAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(StreamDetailsAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward createExamAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExamAction Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			CreateExaminationForm examform = new CreateExaminationForm();

			String createUser = HelperClass.getCurrentUser(request);

			String examid = request.getParameter("examid").trim();
			String eamName = request.getParameter("examname");
			String examdate = request.getParameter("examdate");
			String endDate = request.getParameter("enddate");
			String accadamicyear = request.getParameter("accadamicyear");
			String description = request.getParameter("description");

			examform.setExamId(examid);
			examform.setExamname(eamName);
			examform.setExamdate(examdate);
			examform.setEnddate(endDate);
			examform.setAccyear(accadamicyear);
			examform.setDescription(description);
			examform.setCreateUser(createUser);

			String result = new CreateExaminationBD()
					.createExamination(examform);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :createExamAction Ending");

		return null;
	}

	public ActionForward editExamAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editExam Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String examid = request.getParameter("examid");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);

			ExaminationDetailsVo result = new CreateExaminationBD()
					.editExamination(examvo);

			CreateExaminationForm examform = new CreateExaminationForm();
			List<Object> examnameslist = null;
			String accyear = examform.getAccyear();

			examnameslist = new CreateExaminationBD().getAllExamNames(examform);

			Map<String, String> map = (Map<String, String>) new CreateExaminationBD()
					.getAccadamicYearsBD();

			request.setAttribute("ALLACCYEARS", map);
			request.setAttribute("examlist", result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward getExam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getExam Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward deleteExamAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : deleteExam Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String examid = request.getParameter("examid");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);

			String check = new CreateExaminationBD().deleteExamination(examvo);

			request.setAttribute("delete", check);
			JSONObject json = new JSONObject();

			json.put("status", check);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : deleteExam Ending");

		return null;

	}

	public ActionForward searchexamdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchExam Starting");

		try {

			String SearchName = request.getParameter("searchname");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			ArrayList<ExaminationDetailsVo> examlist = new CreateExaminationBD()
					.searchExamination(SearchName);

			request.setAttribute("examDetailsList", examlist);
			request.setAttribute("searchname", SearchName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchExam Ending");

		return mapping.findForward(MessageConstants.EXAM_LIST);

	}

	public ActionForward validateExamName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : validateExam Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String examid = request.getParameter("examid");
			String examname = request.getParameter("examname");
			String accyear = request.getParameter("accyear");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);
			examvo.setExamName(examname);
			examvo.setAccyear(accyear);

			boolean examname_Available = new CreateExaminationBD()
					.validateExamination(examvo);

			JSONObject jsonobject = new JSONObject();
			if (examname_Available) {

				jsonobject.put("status", "true");
			} else {
				jsonobject.put("status", "false");
			}
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : validateEnding");
		return null;

	}

	public ActionForward downloadExamDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : downloadExamDetailsXLS Starting");

		try {

			System.out.println("download exam action");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/ExamDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			/*
			 * List<ExaminationDetailsVo> examList = new
			 * ArrayList<ExaminationDetailsVo>(); examList =
			 * (List<ExaminationDetailsVo>) request.getSession(false)
			 * .getAttribute("EXcelDownLoad");
			 */

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
						.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();
				System.out.println("listing is working");
			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examvo);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ExamDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Exam Details Excel Report" };
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
					request.getRealPath("Reports/ExamDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ExamDetailsXLSReport.xls");
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
				+ " Control in ExamDetailsAction : downloadExamDetailsXLS");
		return null;

	}

	public ActionForward downloadExamDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Starting");

		try {

			System.out.println("downloading pdf");

		/*	List<ExaminationDetailsVo> examList = new ArrayList<ExaminationDetailsVo>();
			examList = (List<ExaminationDetailsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/
			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
						.searchExamination(searchTerm);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate();
				System.out.println("listing is working");
			}


			String sourceFileName = request
					.getRealPath("Reports/ExamDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examvo);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ExamDetailsPDF - " + ".pdf\"");

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
				+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Ending");
		return null;

	}

	public ActionForward addGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Starting");

		 try{
			 request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_EXAM);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_EXAM);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADESETTINGS);
				
				String accyear = request.getParameter("accyear");
				String location = request.getParameter("location");
				String accyname =new ExamDetailsBD().getaccyName(accyear);
				String currentlocation =new ExamDetailsBD().getlocationname(location);
				
				ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
				list = new ExamDetailsBD().displayGradeSettings(accyear,location);
				request.setAttribute("gradeSettingsList",list);
				
				request.setAttribute("accyName",accyname);
				request.setAttribute("locName", currentlocation);
				request.setAttribute("accyId",accyear);
				request.setAttribute("locId",location);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return mapping.findForward(MessageConstants.ADDGRADESETTINGS);
	 }
	public ActionForward gradeSettingsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Starting");

		 try{
			 request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_EXAM);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_EXAM);
				String accyear = request.getParameter("accyear");
				String location = request.getParameter("location");
				String accyname =new ExamDetailsBD().getaccyName(accyear);
				String currentlocation =new ExamDetailsBD().getlocationname(location);
				
				ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
				list = new ExamDetailsBD().displayGradeSettings(accyear,location);
				
				JSONObject json = new JSONObject();
				json.put("gradeSettingsList",list);
				response.getWriter().print(json);
				
				request.setAttribute("accyName",accyname);
				request.setAttribute("locName", currentlocation);
				request.setAttribute("accyId",accyear);
				request.setAttribute("locId",location);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return null;
	 }
	public ActionForward insertGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertGradeSettings  Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request
				.getSession(false).getAttribute("UserDetails");

		String userType = userDetailVO.getUserNametype();
		String userCode = userDetailVO.getUserId();
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String grade = request.getParameter("grade");
		String comments = request.getParameter("comments");
		String minmarks = request.getParameter("minmarks");
		String maxmarks = request.getParameter("maxmarks");
		
		ExamDetailsPojo obj = new ExamDetailsPojo();
		obj.setGradename(grade);
		obj.setComments(comments);
		obj.setMin_marks(minmarks);
		obj.setMax_marks(maxmarks);
		obj.setCreatedBy(userCode);
		obj.setAccyearId(accyear);
		obj.setLocid(location);
		
		String status = new ExamDetailsBD().insertGradeSettings(obj);
		/*ArrayList<ExamDetailsPojo> list = new ExamDetailsBD().insertGradeSettings(obj);*/
		
		JSONObject json = new JSONObject();
		json.put("status",status);
		response.getWriter().print(json);
		
	return null;
	}
	
	public ActionForward deleteGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String gradeid = request.getParameter("gradeid");
		String location = request.getParameter("loaction");
		String accyear = request.getParameter("accyear");
		
		System.out.println("gradeid---------"+gradeid);
		String result = new ExamDetailsBD().deleteGradeSettings(gradeid,location,accyear);
		JSONObject json = new JSONObject();
		json.put("status",result);
		response.getWriter().print(json);
		
		return null;
	}
	public ActionForward editGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertGradeSettings  Starting");
		try{
			
			System.out.println("asasasasa------------");
			
			String gradeid = request.getParameter("editid");
			String gradename = request.getParameter("gradename");
			String comments = request.getParameter("comments");
			String min_marks = request.getParameter("min_marks");
			String max_marks = request.getParameter("max_marks");
			String location = request.getParameter("loaction");
			String accyear = request.getParameter("accyear");
			
			ExaminationDetailsVo list = new ExaminationDetailsVo();
			list.setGradeid(gradeid);
			list.setGradename(gradename);
			list.setComments(comments);
			list.setMax_marks(max_marks);
			list.setMin_marks(min_marks);
			list.setLocationid(location);
			list.setAccyear(accyear);
			String msg = new ExamDetailsBD().editGradeSettings(list);
			
			JSONObject json = new JSONObject();
			json.put("status",msg);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		 return null;
	}
	public ActionForward checkduplicateGrade(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateGrade  Starting");
		try{
			String accyear = request.getParameter("accyear");
			String gradename = request.getParameter("gradename");
			String loc = request.getParameter("loc");
			
			
			String result= new ExamDetailsBD().checkduplicateGrade(accyear,gradename,loc);
			
			System.out.println(result);
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getSubjectmarksStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectmarksStatus  Starting");
		
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			String accyear = request.getParameter("accyear");
			String locname = request.getParameter("hschoolLocation");
			
			String accyname =new ExamDetailsBD().getaccyName(accyear);
			request.setAttribute("accyName",accyname);
			request.setAttribute("accyId",accyear);
			
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectmarksList(accyear,locname);
			request.setAttribute("subjectmarksList",list);
			
			String currentlocation =new ExamDetailsBD().getlocationname(locname);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locId",locname);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return mapping.findForward("subjectwisemarksList");
	}
	public ActionForward getSubjectClass(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectmarksStatus  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}


			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			System.out.println("examid from action:" +examid);
			System.out.println("Accyear from action:" +accyear);
			String locid=request.getParameter("hschoolLocation");
			String accyname =new ExamDetailsBD().getaccyName(accyear);
			request.setAttribute("accyName",accyname);
			request.setAttribute("accyId",accyear);
			
			String examname = new ExamDetailsBD().getexamName(examid,accyear,locid);
			
			String examarray[];
			String examName = "";
			String examCode = "";
			if(examname !="" && examname !=null){
				 examarray = examname.split(",");
				 examName = examarray[0];
				 examCode = examarray[1];
			}
			
			
			request.setAttribute("examName",examName);
			request.setAttribute("examCode",examCode);
			request.setAttribute("examid",examid);
			
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getsubjectstudent(accyear,examid,locid);
			request.setAttribute("subjectClassList",list);


			 String currentlocation =new ExamDetailsBD().getlocationname(locid);
			 request.setAttribute("currentlocation", currentlocation);
			 request.setAttribute("locid", locid);
			
			/*JSONObject json = new JSONObject();
			json.put("subjectExamList",list);
			response.getWriter().print(json);*/
		}catch(Exception e){
			e.printStackTrace();
		}
		 return mapping.findForward("getSubjectClass");
	}
	
	public ActionForward classWiseSubject(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}
			
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String location = request.getParameter("hschoolLocation");
			
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(location);
			ArrayList<ExaminationDetailsVo>  list = new ExamDetailsBD().getexamclassDetails(obj);
			request.setAttribute("examid",examid);
			request.setAttribute("classId",classId);
			request.setAttribute("sectionId",sectionId);
			request.setAttribute("accyear",accyear);
			request.setAttribute("accyName",list.get(0).getAccyear());
			request.setAttribute("classname",list.get(0).getClassname());
			request.setAttribute("sectionname",list.get(0).getSectionName());
			request.setAttribute("examcode",list.get(0).getExamCode());
			request.setAttribute("examname",list.get(0).getExamName());
			request.setAttribute("startdate",list.get(0).getStartDate());
			request.setAttribute("enddate",list.get(0).getEndDate());
			
			ArrayList<ExaminationDetailsVo> subjectList = new ArrayList<ExaminationDetailsVo>();
			subjectList = new ExamDetailsBD().classWiseSubject(obj);
			request.setAttribute("subjectList",subjectList);
			
			String currentlocation =new ExamDetailsBD().getlocationname(location);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locid", location);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return mapping.findForward("classWiseSubject");
	}

	public ActionForward savesubWiseMarksDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}
	   		
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String subid = request.getParameter("subid");
			String location = request.getParameter("hschoolLocation");
			 String currentlocation =new ExamDetailsBD().getlocationname(location);
				request.setAttribute("currentlocation", currentlocation);
				request.setAttribute("locationid", location);
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			
			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setSubId(subid);
			obj.setLocationid(location);
			ArrayList<ExaminationDetailsVo>  list = new ExamDetailsBD().getexamclassDetails(obj);
			request.setAttribute("examid",examid);
			request.setAttribute("classId",classId);
			request.setAttribute("sectionId",sectionId);
			request.setAttribute("accyear",accyear);
			request.setAttribute("subid",subid);
			request.setAttribute("accyName",list.get(0).getAccyear());
			request.setAttribute("classname",list.get(0).getClassname());
			request.setAttribute("sectionname",list.get(0).getSectionName());
			request.setAttribute("examcode",list.get(0).getExamCode());
			request.setAttribute("examname",list.get(0).getExamName());
			request.setAttribute("startdate",list.get(0).getStartDate());
			request.setAttribute("enddate",list.get(0).getEndDate());
			
			String  result = new ExamDetailsBD().getsubDetails(obj);
			String arr[]=result.split(",");
			request.setAttribute("subjectName",arr[0]);
			request.setAttribute("subjectCode",arr[1]);
			request.setAttribute("total_marks",arr[2]);
			request.setAttribute("passmarks",arr[3]); 
			
			
			ArrayList<ExaminationDetailsVo> studentlist = new ExamDetailsBD().getstudentsList(obj,location);
			if(studentlist.size()!=0){
			request.setAttribute("studentname", studentlist.get(0).getStudentname());
			request.setAttribute("admissionno", studentlist.get(0).getAddmisiionno());
			request.setAttribute("studentlist",studentlist);
			}else{
				request.setAttribute("size","nodata");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return mapping.findForward("savesubWiseMarksDetails");
	}
	public ActionForward InsertMarkEntrySubjectWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
            
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			System.out.println("current school Location:" +schoolLocation);
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}



			String studentids[] = request.getParameterValues("studentids[]");
			String statusvalues[] = request.getParameterValues("statusvalues[]");
			String scoredmarks[] = request.getParameterValues("scoredmarks[]");
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String primaryid[] = request.getParameterValues("primaryid[]");
			String location = request.getParameter("location");
			System.out.println("classid from action:" +classId);
			String sectionId = request.getParameter("sectionId");
			String hiddensubid = request.getParameter("hiddensubid");
			
			System.out.println(scoredmarks.length);
			
			
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			obj.setClassid(classId);
			obj.setAccyear(accyear);
			obj.setExamid(examid);
			obj.setSectionid(sectionId);
			obj.setStudentids(studentids);
			obj.setStatusvalues(statusvalues);
			obj.setSubId(hiddensubid);
			obj.setScoremarks(scoredmarks);
			obj.setSubmarksid(primaryid);
			System.out.println("Lenght from action:" +primaryid.length);
			String result=null;
			/*for(int i=0;i<primaryid.length;i++){
				if(primaryid[i].equals("")){
					 result=new ExamDetailsBD().insertmarkentrysubjectwise(obj,schoolLocation);
					
				}else{
					 result=new ExamDetailsBD().updatemarkentrysubjectwise(obj,schoolLocation);
					
				}
			}*/
			
			result=new ExamDetailsBD().insertmarkentrysubjectwise(obj,location);
			JSONObject object = new JSONObject();
			object.put("status",result);
			response.getWriter().print(object);



		return null;
	}
	public ActionForward gradeList(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try{
			
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
		
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatusGrade(accyear,location);

			JSONObject obj=new JSONObject();
			obj.put("accYearList",accYearList);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getexamsettingslist(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try{
			
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
		
			ArrayList<ExaminationDetailsVo> examlist= new ExamDetailsBD().getexamsettingslist(accyear,locid);
			System.out.println(examlist.size());
			JSONObject obj=new JSONObject();
			obj.put("examlist",examlist);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward getexamsettingslistfordep(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try{
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
		
			ArrayList<ExaminationDetailsVo> examlist= new ExamDetailsBD().getexamsettingslistfordep(accyear,locid);
			System.out.println(examlist.size());
			JSONObject obj=new JSONObject();
			obj.put("examlist",examlist);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
	

