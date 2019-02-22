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

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.delegate.AcademicYearMasterBD;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ReportMenuVo;

public class ClassAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(ClassAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward insertClassAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction: insertClassAction Starting");

		try {
			

			boolean check = false;
			String academic = request.getParameter("adddetails");
			//String[] classdetails = academic.split(",");
			

			ClassPojo classStreamForm = new ClassPojo();
			
			String streamId = request.getParameter("stream");
			String classID=request.getParameter("className");
			String locationId=request.getParameter("locationId");
			String secDetailsName = request.getParameter("className");
			String createUser = HelperClass.getCurrentUserID(request);
			classStreamForm.setLocationId(locationId);
			classStreamForm.setStreamId(streamId);
			classStreamForm.setCreateUser(createUser);
			classStreamForm.setSecDetailName(HelperClass.className(classID));
			classStreamForm.setClassId(classID);
			classStreamForm.setStatus(request.getParameter("status"));
			classStreamForm.setUpdateClassCode(request.getParameter("updateClassCode"));

			classStreamForm.setClassName(HelperClass.className(classID));
			ClassBD delegate = new ClassBD();
			
			String status = delegate.createClass(classStreamForm,classID,createUser);
			
			System.out.println(status);
			
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
				+ " Control in ClassAction: insertClassAction Ending");

		return null;

	}
	public ActionForward getLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			
			String Message = request.getParameter("msg");
			request.setAttribute("successmessagediv", Message);
			
			String title = "Add New Class";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Ending");

		return mapping.findForward(MessageConstants.ADD_CLASS);
	}
	public ActionForward getSchoolforClassDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Starting");
		try {
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			//request.setAttribute("AccYearList", accYearList);
			
			JSONObject obj=new JSONObject();
			obj.put("locationList", locationList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Ending");

		return null;
	}
	public ActionForward getStream(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Starting");
		try {
			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
			//request.setAttribute("AccYearList", accYearList);
			
			JSONObject obj=new JSONObject();
			obj.put("streamList", streamList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Ending");

		return null;
	}
	public ActionForward addClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			
			
			String Message = request.getParameter("msg");
			request.setAttribute("successmessagediv", Message);
			
			String title = "Add New Class";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Ending");

		return mapping.findForward(MessageConstants.ADD_CLASS);
	}
	
	public ActionForward getStreamDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction :getStreamDetailAction Starting");
		String school=request.getParameter("school");
		if(school!=null){
			if(school.equalsIgnoreCase("all")){
				school="%%";
			}
		}
		else{
			school=(String) request.getSession(false).getAttribute("current_schoolLocation");
			if(school.equalsIgnoreCase("all")){
				school="%%";
			}
		}
		
		try {
			List<ClassPojo> streamList = new ArrayList<ClassPojo>();
			ClassBD delegate = new ClassBD();
			streamList = delegate.getStreamDetailBD(school);

			request.setAttribute("streamList", streamList);
			JSONObject jsonObject = new JSONObject(streamList);
			jsonObject.accumulate("streamList", streamList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getStreamDetailAction Ending");

		return null;
	}
	
	public ActionForward classNameCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classNameCheck  Starting");
		try {
			
			System.out.println(request.getParameter("className"));
			
			ClassPojo classPojo = new ClassPojo();
			classPojo.setLocationId(request.getParameter("school"));
			classPojo.setStreamId(request.getParameter("stream"));
			classPojo.setClassName(HelperClass.className(request.getParameter("className")));
			classPojo.setUpdateClassCode(request.getParameter("updateClassCode"));
			
			System.out.println("Inside Action "+HelperClass.className(request.getParameter("className")));
			

			boolean status = new ClassBD()
					.classNameCheck(classPojo);

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
				+ " Control in ClassAction : classNameCheck  Ending");
		return null;

	}
	
	public ActionForward checkUpdateClassName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classNameCheck  Starting");
		try {
			ClassPojo classPojo = new ClassPojo();

			classPojo.setStreamId(request.getParameter("stream"));
			classPojo.setClassName(request.getParameter("className"));
			classPojo.setUpdateClassCode(request.getParameter("updateClassCode"));

			boolean status = new ClassBD()
					.updateclassNameCheck(classPojo);

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
				+ " Control in ClassAction : classNameCheck  Ending");
		return null;

	}
	
	public ActionForward editClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Starting");
		try {
			
			String title = "Modify Class";
			request.setAttribute("title", title);
			ClassPojo editClasslist = new ClassBD().editClass(request.getParameter("classCode"));
			System.out.println(editClasslist.getClassName());
			request.setAttribute("editClasslist", editClasslist);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Ending");
		return mapping.findForward(MessageConstants.ADD_CLASS);

	}
	
	public ActionForward deleteClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : deleteClass  Starting");
		
		try {

			
			String classcode[] = request.getParameterValues("classCode[]");
			String locationcode[]=request.getParameterValues("locationCode[]");
			
			
			
			boolean status = new ClassBD().deleteClass(classcode,locationcode);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : deleteClass  Ending");
		return null;
	}
	
	public ActionForward updateClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : updateClass  Starting");
		try {

			ClassPojo classPojo = new ClassPojo();

			String streamId = request.getParameter("stream");
			String secDetailsName = request.getParameter("className");
			String createUser = HelperClass.getCurrentUserID(request);
			classPojo.setStreamId(streamId);
			classPojo.setCreateUser(createUser);
			classPojo.setSecDetailName(secDetailsName);
			//classPojo.setClassId(classId);

			boolean status = new ClassBD()
					.updateClass(classPojo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : updateClass  Ending");
		return null;

	}
	
	public ActionForward searchClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchClass  Starting");

		String usertype = null;
		List<ClassPojo> classList = null;
		try {

			

			ClassBD bd = new ClassBD();
			usertype = HelperClass.getCurrentUser(request);
			
			String searchTextVal = request.getParameter("searchText");

			if (searchTextVal == null || searchTextVal == "") {
				classList = bd.getClassDetails("%%");
			} else {

				classList = bd.searchClassDetails(searchTextVal);
				
			}

			request.setAttribute("classList", classList);
			request.getSession(false).setAttribute("classList",
					classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchClass Ending");

		return mapping.findForward(MessageConstants.CLASS_LIST);

	}

	public ActionForward classPathDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classPathDetailsXLS  Starting");
		
		try {
		
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/ClassDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			/*List<ClassPojo> classList = new ArrayList<ClassPojo>();
			
			
			classList = (List<ClassPojo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/
			
			
			
			List<ClassPojo> classList = new ArrayList<ClassPojo>();
			
			ClassBD delegate = new ClassBD();
			

			
			String searchTextVal = request.getParameter("searchTerm");
			
			
			
			if(searchTextVal != null){
				
				
				classList = delegate.searchClassDetails(searchTextVal);
				request.setAttribute("searchnamelist", searchTextVal);
				
			}
			else{
				
				classList = delegate.getClassDetails("%%");
				
			}
			
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(classList);
			
			
			
			
			
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ClassDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Class Details Excel Report" };
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
					request.getRealPath("Reports/ClassDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ClassDetailsXLSReport.xls");
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
				+ " Control in ClassAction : classPathDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward classPathDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassAction : classPathDetailsPDFReport  Starting");

			try {

				List<ClassPojo> classList = new ArrayList<ClassPojo>();
				
				ClassBD delegate = new ClassBD();
				
				String searchTextVal = request.getParameter("searchTerm");
				
				if(searchTextVal != null){
					
					classList = delegate.searchClassDetails(searchTextVal);
					request.setAttribute("searchnamelist", searchTextVal);
					
				}
				else{
					
					classList = delegate.getClassDetails("%%");
					
				}
				
				String sourceFileName = request
						.getRealPath("Reports/ClassDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						classList);

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
						+ "ClassDetailsPDF - " + ".pdf\"");

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
					+ " Control in ClassAction : classPathDetailsPDFReport  Ending");
			return null;

		}

}
