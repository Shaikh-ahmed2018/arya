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
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SectionVO;
import com.centris.campus.vo.UpcomingRemarksVO;

public class SectionAction extends DispatchAction{
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	private static final Logger logger = Logger
			.getLogger(SectionAction.class);

	public ActionForward insertSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionAction:insertCampusClassSectionAction Starting");
		try {
			SectionForm sectionForm = new SectionForm();
			String classId = request.getParameter("classId");
			String sectionName = request.getParameter("sectionName");
			String sectionStrength = request.getParameter("sectionStrength");
			String status = request.getParameter("status");
			String updateClassCode = request.getParameter("updateClassCode");
			String locationId=request.getParameter("locationId");
			
			
			if(status.equalsIgnoreCase("update")){
				
				sectionForm.setStatus(status);
				
			}
			else
			{
				sectionForm.setStatus("insert");
			}
			
			String createUser = HelperClass.getCurrentUserID(request);
		  
        
           
           if(updateClassCode==""){
        	   sectionForm.setSecId(null);
            }else{
            	sectionForm.setSecId(updateClassCode);
            }

			sectionForm.setSecDetailsId(classId);
			sectionForm.setLocationId(locationId);
			sectionForm.setSectionName(sectionName);
			sectionForm.setSectionStrength(sectionStrength);
			
			
			sectionForm.setCreateUser(createUser);
			SectionBD sectionDelegate = new SectionBD();
			String check = sectionDelegate.insertCampusClassSectionBD(sectionForm);
			
			JSONObject json= new JSONObject();
			
			json.put("status",check);
		
			response.getWriter().print(json);
			
			
			
			/*JSONObject jsonObject=new JSONObject();
			if (check==true) {

				request.setAttribute("successmessagediv",
						"Section Saved Successfully");

			} else {
				request.setAttribute("error", "Section Faild to Save");
			}
			response.getWriter().print(jsonObject);*/
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:insertCampusClassSectionAction Ending");

		return null;

	}

	public ActionForward updateCampusClassSectionAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CampusClassSectionAction:updateCampusClassSectionAction Starting");
		try {
			SectionForm sectionForm = new SectionForm();
			String updateStreamName = request.getParameter("updateStreamName");
			String updateSectionId = request.getParameter("updateSectionId");
			String updateSecDetailsId = request
					.getParameter("updateSecDetailsId");
			String updateSecDetailsName = request
					.getParameter("updateSecDetailsName");
			String sectionName = request.getParameter("updateSectionName");
			String sectionStrength = request
					.getParameter("updateSectionStrength");
			String modifyUser = HelperClass.getCurrentUserID(request);

			sectionForm.setStreamName(updateStreamName);
			sectionForm.setSectionId(updateSectionId);
			sectionForm.setSecDetailsId(updateSecDetailsId);
			sectionForm.setSecDetailsName(updateSecDetailsName);
			sectionForm.setSectionName(sectionName);
			sectionForm.setSectionStrength(sectionStrength);
			sectionForm.setModifyUser(modifyUser);
			SectionBD sectionDelegate = new SectionBD();
			String check = sectionDelegate
					.updateCampusClassSectionBD(sectionForm);
JSONObject jsonObject=new JSONObject();
			if (check == "Section Details Added Successfully") {
				request.getSession().setAttribute("message",
						"Section updated Successfully");
			jsonObject.accumulate("status",true);

			}
			else
			{
				request.getSession().setAttribute("message",
						"Section not Updated");
			jsonObject.accumulate("status", false);
			}
			
		response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:updateCampusClassSectionAction Ending");

		/*return mapping.findForward(MessageConstants.SUCCESS);*/
		return null;

	}

	public ActionForward deleteCampusClassSectionAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:deleteCampusClassSectionAction Starting");
		try {
			SectionForm sectionForm = new SectionForm();
			String sectionId[] = request.getParameterValues("sectionCode[]");
			String locationList[]=request.getParameterValues("locationList[]");
			System.out.println(sectionId.length);
			
			/*sectionForm.setSectionId(sectionId);*/
			SectionBD sectionDelegate = new SectionBD();
			boolean flag = sectionDelegate.deleteCampusClassSectionBD(sectionId,locationList);
			
				JSONObject jsonObject=new JSONObject();
				jsonObject.accumulate("status", flag);
				response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:deleteCampusClassSectionAction Ending");

		return null;

	}

	public ActionForward getCampusClassSectionAndClassDetailsAction(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:getCampusClassSectionAndClassDetailsAction Starting");

		try {
			List<SectionForm> ClassSectionAndClassDetailsList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			ClassSectionAndClassDetailsList = sectionDelegate
					.getCampusClassSectionAndClassDetailsBD("%%");
			request.setAttribute("ClassSectionAndClassDetailsList",
					ClassSectionAndClassDetailsList);

			JSONObject jsonObject = new JSONObject(
					ClassSectionAndClassDetailsList);
			jsonObject.accumulate("ClassSectionAndClassDetailsList",
					ClassSectionAndClassDetailsList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:getCampusClassSectionAndClassDetailsAction Ending");

		return null;

	}

	public ActionForward getCampusClassDetailsIDandClassDetailsNameAction(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:getCampusClassDetailsIDandClassDetailsNameAction  Starting");
	
		
		String locationId=request.getParameter("locationId");
		
		/*String locationId = (String) request.getSession(false).getAttribute("current_schoolLocation");*/
		
		
		if(locationId.equalsIgnoreCase("all")){
			locationId="%%";
		}	
		
		try {
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(locationId);
			request.setAttribute("classDetailsIDandClassDetailsNameList",classDetailsIDandClassDetailsNameList);

			JSONObject jsonObject = new JSONObject(
					classDetailsIDandClassDetailsNameList);
			jsonObject.accumulate("classDetailsIDandClassDetailsNameList",
					classDetailsIDandClassDetailsNameList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:getCampusClassDetailsIDandClassDetailsNameAction Ending");

		return null;

	}

	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  CampusClassSectionAction:removeMessage Starting");

			request.getSession().setAttribute("error", null);
			request.getSession().setAttribute("message", null);
			response.getWriter().print("Message Removed");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:removeMessage Ending");

		return null;
	}

	public ActionForward checkValidSectionName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:checkValidSectionName  Starting");
		try {
			String sectionId = request.getParameter("sectionId");
			String sectionName = request.getParameter("sectionName");
			String className = request.getParameter("className");
			boolean flag = new SectionBD()
					.checkSectionNameForUpdate(sectionId, sectionName,
							className);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", flag);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  CampusClassSectionAction:checkValidSectionName Ending");

		return null;
	}
	
	public ActionForward addSection(ActionMapping mapping, ActionForm form,
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
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DIVISIONDETAILS);
			
			String title ="Add New Division";
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

		return mapping.findForward(MessageConstants.ADD_SECTION);
	}
	
	public ActionForward searchSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchSection  Starting");

		String usertype = null;
		List<SectionForm> classList = null;
		try {

			SectionForm secForm = new SectionForm();
			
			SectionBD bd = new SectionBD();
			usertype = HelperClass.getCurrentUser(request);
			
			String searchTextVal = request.getParameter("searchText");
			secForm.setSectionName(searchTextVal);

			if (searchTextVal == null || searchTextVal == "") {
				classList = bd.getCampusClassSectionAndClassDetailsBD("%%");
			} else {

				classList = bd.searchSection(secForm);
				
			}

			request.setAttribute("ClassSectionAndClassDetailsList", classList);
			request.getSession(false).setAttribute("ClassSectionAndClassDetailsList",
					classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchSection Ending");

		return mapping.findForward(MessageConstants.SECTION_LIST);

	}
	
	public ActionForward editSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DIVISIONDETAILS);
			  
			String title ="Modify Division";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			SectionForm secForm = new SectionForm();
			secForm.setSectionId(request.getParameter("classCode"));
			SectionForm editClasslist = new SectionBD().editSection(secForm);
			request.setAttribute("editClasslist", editClasslist);

	

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Ending");
		return mapping.findForward(MessageConstants.ADD_SECTION);

	}

	public ActionForward classPathDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionAction : classPathDetailsXLS  Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
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
			
			/*List<AddFeeVO> List = new ArrayList<AddFeeVO>();
			List = (List<AddFeeVO>) request.getSession(false)
					.getAttribute("feelistdownload");*/
			List<ClassPojo> List = new ArrayList<ClassPojo>();
			ClassBD delegate = new ClassBD();
			List = delegate.getClassDetails(schoolLocation);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
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
				+ " Control in SectionAction : classPathDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward classPathDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SectionAction : classPathDetailsPDFReport  Starting");

			try {

				

				/*List<AddFeeVO> Details = new ArrayList<AddFeeVO>();
				Details = (List<AddFeeVO>) request.getSession(false).getAttribute("feelistdownload");
*/               
				List<SectionForm> Details = new ArrayList<SectionForm>();
				SectionBD sectionDelegate = new SectionBD();

				
				Details = sectionDelegate.getCampusClassSectionAndClassDetailsBD("%%");
				
				/*List<ClassPojo> Details = new ArrayList<ClassPojo>();
				ClassBD delegate = new ClassBD();
				Details = delegate.getClassDetails();*/
 				
				String sourceFileName = request
						.getRealPath("Reports/ClassDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						Details);

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
					+ " Control in SectionAction : classPathDetailsPDFReport  Ending");
			return null;

		}
	
	
	
	public ActionForward downloadSectionDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SectionAction : downloadSectionDetailsXLS  Starting");

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			try {
				 
				
				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				String sourceFileName = request
						.getRealPath("Reports/SectoinXLSReport.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				
				
				/*List<SectionVO> ClassSectionAndClassDetailsList = new ArrayList<SectionVO>();
				SectionBD sectionDelegate = new SectionBD();
				ClassSectionAndClassDetailsList = sectionDelegate.sectiondetailsdownload();*/
				
				
			/*	
				
				List<SectionVO> ClassSectionAndClassDetailsList = new ArrayList<SectionVO>();
			
				
				
				ClassSectionAndClassDetailsList = (List<SectionVO>) request.getSession(false).getAttribute("EXcelDownLoad");
				
				*/
				List<SectionForm> ClassSectionAndClassDetailsList = new ArrayList<SectionForm>();
				
				List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
				String Message = request.getParameter("msg");
			
				SectionBD sectionDelegate = new SectionBD();

				classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation);
				
				
				SectionForm secForm = new SectionForm();
				SectionBD bd = new SectionBD();
				
				String searchTextVal = request.getParameter("searchTerm");
				secForm.setSectionName(searchTextVal);
				
				
				
				if(searchTextVal != null){
					
					
					ClassSectionAndClassDetailsList =  bd.searchSection(secForm);
					
					request.setAttribute("searchnamelist", searchTextVal);
					
					
				}
				else{
					
					
					ClassSectionAndClassDetailsList = sectionDelegate.getCampusClassSectionAndClassDetailsBD("%%");
				}
				
				
				
				
				
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						ClassSectionAndClassDetailsList);
				Map parameters = new HashMap();

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						parameters, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(
						request.getRealPath("Reports/SectoinXLSReport.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "Section Details Excel Report" };
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
						request.getRealPath("Reports/SectoinXLSReport.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=SectoinXLSReport.xls");
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
					+ " Control in SectionAction : downloadSectionDetailsXLS  Ending");
			
			return null;

		}
	

	public ActionForward downloadSectionDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionAction : downloadSectionDetailsPDF  Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		try {

			List<SectionForm> ClassSectionAndClassDetailsList = new ArrayList<SectionForm>();
			
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			String Message = request.getParameter("msg");
		
			SectionBD sectionDelegate = new SectionBD();

			classDetailsIDandClassDetailsNameList = sectionDelegate
					.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation);
			
			
			SectionForm secForm = new SectionForm();
			SectionBD bd = new SectionBD();
			
			String searchTextVal = request.getParameter("searchTerm");
			secForm.setSectionName(searchTextVal);
			
			
			
			if(searchTextVal != null){
				
				
				ClassSectionAndClassDetailsList =  bd.searchSection(secForm);
				
				request.setAttribute("searchnamelist", searchTextVal);
				
				
			}
			else{
				
				
				ClassSectionAndClassDetailsList = sectionDelegate.getCampusClassSectionAndClassDetailsBD("%%");
			}
			
			
			
			
			String sourceFileName = request
					.getRealPath("Reports/sectionPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					ClassSectionAndClassDetailsList);

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
					+ "sectionPDF - " + ".pdf\"");

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
				+ " Control in SectionAction : downloadSectionDetailsPDF  Ending");
		return null;

	}
	
	public ActionForward sectionNameCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classNameCheck  Starting");
		try {
			
			System.out.println(request.getParameter("className"));
			System.out.println(request.getParameter("locationId"));
			SectionForm sectionForm = new SectionForm();

			sectionForm.setClassId(request.getParameter("classId"));
			sectionForm.setSectionName(request.getParameter("stream"));
			sectionForm.setLocationId(request.getParameter("locationId"));
			boolean status = new SectionBD()
					.sectionNameCheck(sectionForm);

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
				+ " Control in SectionAction : sectionNameCheck  Ending");
		return null;

	}
	
	
	
	
	
	
	
	
	
	
}
