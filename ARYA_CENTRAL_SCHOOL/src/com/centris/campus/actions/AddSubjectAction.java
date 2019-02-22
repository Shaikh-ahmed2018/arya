package com.centris.campus.actions;

import java.io.File;




import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
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
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.daoImpl.AddSubjectDaoImpl;
import com.centris.campus.delegate.AddSubjectDelegate;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.JPropertyReader;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.ViewallSubjectsVo;




public class AddSubjectAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(AddSubjectAction.class);
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String EcampusPro_Documents_Dir  = JPropertyReader
			.getProperty("EcampusPro_Documents_Dir");
		private static String ImageName = res.getString("ImageName");
	
	
	
	public ActionForward classList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : classList Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			List<ClassPojo> classList = new ArrayList<ClassPojo>();
			ClassBD delegate = new ClassBD();
			classList = delegate.getClassDetails(schoolLocation);

			JSONObject object = new JSONObject();
			object.put("classList", classList);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : classList Ending");

		return null;
	}

	public ActionForward getsubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Starting");

		try {

			String title="Add New Subject";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Ending");

		return mapping.findForward(MessageConstants.ADD_SUBJECT);
	}

	@SuppressWarnings("resource")
	  
	public ActionForward addSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  AddSubject Starting");

		try {


			String userId = HelperClass.getCurrentUserID(request);
			String extension = null;
			AddSubjectForm addSubjectForm = (AddSubjectForm) form;
			addSubjectForm.setCreatedUserId(userId);

			FormFile formFile = null;


			String path = null;

			File fileURL = null;

			FileOutputStream fos = null;

			formFile = addSubjectForm.getFile();




			File secondDir=null;
			File firstDir=null;


			/*  firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");*/
			path = getServlet().getServletContext().getRealPath("/")+ "FIles\\SYLLABUS";
			System.out.println("path is "+path);
			File files = new File(path);


			if (!files.exists()) {
				if (files.mkdirs()) {
				}
			}
			path = files.getAbsolutePath();




			int i = formFile.getFileName().lastIndexOf('.');




			if (i > 0) {
				extension = formFile.getFileName().substring(i + 1);
			}

			String sub=addSubjectForm.getSubjtname();
			String subname=sub.replace("/", "_");

			String fileName = subname + "_" + addSubjectForm.getClassname() + "." + extension;
			System.out.println("fileName is "+fileName);
			fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());

			String file1 = fileURL.getPath();



			FileOutputStream fileOutStream =null;
			if(!fileURL.exists()){

				fileURL.mkdir();

				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());
				fileURL.mkdir();
				addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);


			}
			else{

				fileURL.mkdir();


				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());


				addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);
			}


			boolean addsubject = new AddSubjectDelegate().addSubject(addSubjectForm);




			if (addsubject == false) {
				request.setAttribute("errorMessage",
						"Subject Already Exists");
			} else if(addsubject == true) {
				request.setAttribute("successmessagediv",
						"Adding Subject Progressing...");
			}
			else
			{
				request.setAttribute("errorMessage",
						"Subject not created ! Try Again");
			}


			fileOutStream.close();
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);



			request.setAttribute("allsubjects", subjectlist);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.ADD_SUBJECT);

	}
	
	public ActionForward updateSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  updateSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			String userId = HelperClass.getCurrentUserID(request);
			String extension = null;
			AddSubjectForm addSubjectForm = (AddSubjectForm) form;
			addSubjectForm.setCreatedUserId(userId);
			
			

			FormFile formFile = null;
			
			
			String path = null;

			File fileURL = null;

			FileOutputStream fos = null;

			formFile = addSubjectForm.getFile();

			
			
			
			File secondDir=null;
			File firstDir=null;
			FileOutputStream fileOutStream =null;
			
			
			
		  /* firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");*/
			
			
			path = getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS";
			File files = new File(path);
			if (!files.exists()) {
				if (files.mkdirs()) {
				}
			}
			path = files.getAbsolutePath();
			

		  
		    if(formFile != null){
		    int i = formFile.getFileName().lastIndexOf('.');
			if (i > 0) {
				extension = formFile.getFileName().substring(i + 1);
			}
			String sub=addSubjectForm.getSubjtname();
			String subname=sub.replace("/", "_");
			String fileName = subname + "_" + addSubjectForm.getClassname() + "." + extension;

		
			fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());
			
			String file1 = fileURL.getPath();
			
			if(fileURL.exists()){
			
				/* secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
							+ addSubjectForm.getClassname() + "." + extension);*/
				
				fileURL.mkdir();
				 fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				 fileURL.mkdir();
				 addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
							+ addSubjectForm.getClassname() + "." + extension);

				 
			}
			else{
				
				/*firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS");*/
				fileURL.mkdir();
				
			/*	secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);*/
				fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				
				
				addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);
			}
			
		
		   }
			addSubjectForm.setFile(addSubjectForm.getFile());
			
			 boolean updatesubject = new AddSubjectDelegate().updateSubjectDetails(addSubjectForm);

			 
			 
			
      	
						 if (updatesubject == false) {
								request.setAttribute("errorMessage",
										"Subject Already Exists");
							    } else if(updatesubject == true){
								request.setAttribute("successmessagediv",
										"Update Subject Progressing...");
							    }
							    else
							    {
							    	request.setAttribute("errorMessage",
											"Subject not Updated ! Try Again");
							    }


			fileOutStream.close();
			
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);


			request.setAttribute("allsubjects", subjectlist);

			
			
			
			
			
			
			

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : updateSubject Ending");

		return mapping.findForward(MessageConstants.UPDATE_SUBJECT);

	}
	
	public ActionForward DeleteSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : DeleteSubject Starting");
		try {
			System.out.println("coming inside ActionL:");
			String subList = request.getParameter("subjectlist");
			String locationList[]=request.getParameter("locationList").split(",");
			System.out.println("subList" +subList);
			String[] idList=subList.split(",");
			System.out.println("idList" +idList.length);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			boolean status = new AddSubjectDelegate().DeleteSubject(idList,locationList);
			System.out.println("status" +status);

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
				+ " Control in AddSubjectAction : DeleteSubject Ending");

		return null;
	}

	public ActionForward searchsubjectdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : searchsubjectdetails Starting");

		try {
			ViewallSubjectsVo voOvj = new ViewallSubjectsVo();
			String SearchName = request.getParameter("searchname");
			voOvj.setSearchName(SearchName);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			subjectlist = new AddSubjectDelegate().searchsubjectdetails(voOvj);

			request.setAttribute("allsubjects", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : searchsubjectdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	public ActionForward getSubjectDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Starting");

		try {
			
			String title = "Modify Subject";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			String subjectcode = request.getParameter("subjectcode");
			obj.setSubjectid(subjectcode);

			ViewallSubjectsVo subjectdetails = new AddSubjectDelegate().getSubjectDetails(obj);
			request.setAttribute("success", "success");
			request.setAttribute("singlesubjectdetails", subjectdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Ending");

		return mapping.findForward(MessageConstants.UPDATE_SUBJECT);
	}

	public ActionForward getpathdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload Starting");
		try {

			
			String docPath = request.getParameter("Path");
			
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS"+("/")+docPath);
			
			
		
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in AddSubjectAction : getpathdownload  Ending");
		return null;
	}
	
	public ActionForward getsyllabusdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsyllabusdownload Starting");
	
		try {
			
			String subjectid = request.getParameter("subjectid");
			
			response.setContentType("application/octet-stream");
			
		
			
			String docPath = new AddSubjectDelegate().DdownloadsyllabuspathBD(subjectid);
			
			
			
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS"+("/")+docPath);
			
			
		
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in AddSubjectAction : getsyllabusdownload  Ending");
		return null;
	}
	
	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : removeMessage Starting");
		
		try {
			request.getSession().setAttribute("errormessagediv", null);
			request.getSession().setAttribute("successmessagediv", null);
			response.getWriter().print("Message Removed");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : removeMessage  Ending");
		return null;
	}
	
	public ActionForward downloadsubjectDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/subjectXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			
			List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
			
			streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");
			
			
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/subjectXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Subject Details Excel Report" };
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
					request.getRealPath("Reports/subjectXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=subjectXLSReport.xls");
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
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadsubjectDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Starting");

			try {

				
/*
				List<ViewallSubjectsVo> Details = new ArrayList<ViewallSubjectsVo>();
				Details = new AddSubjectDelegate().subjectdetails();
				*/
				
				
				
				List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
				
				streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
						.getAttribute("EXcelDownLoad");
				
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/subjectPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						streamList);

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
						+ "subjectdetailsPDF - " + ".pdf\"");

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
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Ending");
			return null;

		}
	public ActionForward validateSubNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");
               System.out.println("whether it is coming inside the action:");
		try {
			
			
			
			System.out.println("---------Inside the Action ----");
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specialization");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);

			String subame_available = new AddSubjectDelegate().validateSubName(form1);
			System.out.println("subame_available" +subame_available);

			JSONObject object = new JSONObject();
			object.put("des_available", subame_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}
	public ActionForward getLangauageOnClassBased(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");
               System.out.println("whether it is coming inside the action:");
               List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
          try {
		
			String classname = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specializationId");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);

			subjectlist = new AddSubjectDaoImpl().getLangauageOnClassBased(form1);
		

			JSONObject object = new JSONObject();
			object.put("jsonResponse", subjectlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}
	
	public ActionForward getSubjectByClass(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			ArrayList<SubjectPojo> subjectlist = new ReportsMenuBD().getSubjectByClass(classId,locationId);
			request.setAttribute("subjectlist", subjectlist);
			
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", subjectlist);
				
				response.getWriter().print(jsonobj);
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Ending");

		 return null;   
	}
	
	public ActionForward addLabDetails(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			ArrayList<SubjectPojo> subjectlist = new ReportsMenuBD().getSubjectByClass(classId,locationId);
			request.setAttribute("subjectlist", subjectlist);
			
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", subjectlist);
				
				response.getWriter().print(jsonobj);
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Ending");

		 return null;   
	}
	
	@SuppressWarnings("resource")
	  
	public ActionForward addingLabDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  AddSubject Starting");

		try {


			String userId = HelperClass.getCurrentUserID(request);
			String extension = null;
			AddSubjectForm addsubForm = (AddSubjectForm) form;
			addsubForm.setCreatedUserId(userId);

			FormFile formFile = null;


			String path = null;

			File fileURL = null;

			FileOutputStream fos = null;

			formFile = addsubForm.getFile();




			File secondDir=null;
			File firstDir=null;


			/*  firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");*/
			path = getServlet().getServletContext().getRealPath("/")+ "FIles\\Lab_SYLLABUS";
			System.out.println("path is "+path);
			File files = new File(path);


			if (!files.exists()) {
				if (files.mkdirs()) {
				}
			}
			path = files.getAbsolutePath();
            int i = formFile.getFileName().lastIndexOf('.');
            if (i > 0) {
				extension = formFile.getFileName().substring(i + 1);
			}

			String sub=addsubForm.getSubjtname();
			String subjectname = new AddSubjectDelegate().getSubjectName(sub);
			System.out.println(sub);
			String subname=subjectname;

			String fileName = subname  + "." + extension;
			System.out.println("file name printing>>>>>>>>>"+fileName);
			System.out.println("fileName is "+fileName);
			fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());

			String file1 = fileURL.getPath();

            FileOutputStream fileOutStream =null;
			if(!fileURL.exists()){

				fileURL.mkdir();

				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());
				fileURL.mkdir();
				addsubForm.setFilename(subname+  "." + extension);
                 }
			else{

				fileURL.mkdir();


				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());


				addsubForm.setFilename(subname +  "." + extension);
			}

			boolean addlab = new AddSubjectDelegate().addLab(addsubForm);




			if (addlab == false) {
				request.setAttribute("errorMessage",
						"Lab Already Exists");
			} else if(addlab == true) {
				request.setAttribute("successmessagediv",
						"Adding Lab Details Progressing...");
			}
			else
			{
				request.setAttribute("errorMessage",
						"Lab not created ! Try Again");
			}


			fileOutStream.close();
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);
			request.setAttribute("allsubjects", subjectlist);


			

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.ADD_LAB);

	}
	
	public ActionForward DeleteLab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : DeleteSubject Starting");
		try {
			System.out.println("coming inside ActionL:");
			String labList = request.getParameter("lablist");
			String locationList[]=request.getParameter("locationList").split(",");
			System.out.println("subList" +labList);
			String[] idList=labList.split(",");
			System.out.println("idList" +idList.length);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			boolean status = new AddSubjectDelegate().DeleteLab(idList,locationList);
			System.out.println("status" +status);

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
				+ " Control in AddSubjectAction : DeleteSubject Ending");

		return null;
	}

	public ActionForward getLabDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Starting");

		try {
			
			String title = "Modify Lab";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			
			
			AddSubjectForm obj = new AddSubjectForm();
			String labcode = request.getParameter("labcode");
			obj.setLab_id(labcode);
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			
			AddSubjectForm labdetails = new AddSubjectDelegate().getLabDetails(obj);
			request.setAttribute("success", "success");
			request.setAttribute("singlelabdetails", labdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Ending");

		return mapping.findForward(MessageConstants.UPDATE_LAB);
	}

	public ActionForward updateLab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  updateSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			String userId = HelperClass.getCurrentUserID(request);
			String extension = null;
			AddSubjectForm addSubjectForm = (AddSubjectForm) form;
			addSubjectForm.setCreatedUserId(userId);
			
			

			FormFile formFile = null;
			
			
			String path = null;

			File fileURL = null;

			FileOutputStream fos = null;

			formFile = addSubjectForm.getFile();

			
			
			
			File secondDir=null;
			File firstDir=null;
			FileOutputStream fileOutStream =null;
			
			
			
		  /* firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");*/
			
			
			path = getServlet().getServletContext().getRealPath("/")
					+ "FIles\\Lab_SYLLABUS";
			File files = new File(path);
			if (!files.exists()) {
				if (files.mkdirs()) {
				}
			}
			path = files.getAbsolutePath();
			

		  
		    if(formFile != null){
		    int i = formFile.getFileName().lastIndexOf('.');
			if (i > 0) {
				extension = formFile.getFileName().substring(i + 1);
			}
			String sub=addSubjectForm.getSubjtname();
			String subjectname = new AddSubjectDelegate().getSubjectName(sub);
			String subname=subjectname;
			String fileName = subname +  "." + extension;

		
			fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());
			
			String file1 = fileURL.getPath();
			
			if(fileURL.exists()){
			
				/* secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
							+ addSubjectForm.getClassname() + "." + extension);*/
				
				fileURL.mkdir();
				 fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				 fileURL.mkdir();
				 addSubjectForm.setFilename(subname+  "." + extension);

				 
			}
			else{
				
				/*firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS");*/
				fileURL.mkdir();
				
			/*	secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);*/
				fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				
				
				addSubjectForm.setFilename(subname + "." + extension);
			}
			
		
		   }
			addSubjectForm.setFile(addSubjectForm.getFile());
			
			 boolean labsubject = new AddSubjectDelegate().updateLabDetails(addSubjectForm);
                        
			                 if (labsubject == false)
			                    {
								request.setAttribute("errorMessage",
										"Subject Already Exists");
							    } else if(labsubject == true){
								request.setAttribute("successmessagediv",
										"Update Subject Progressing...");
							    }
							    else
							    {
							    	request.setAttribute("errorMessage",
											"Subject not Updated ! Try Again");
							    }


			fileOutStream.close();
			
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);


			request.setAttribute("allsubjects", subjectlist);
        } catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : updateSubject Ending");

		return mapping.findForward(MessageConstants.UPDATE_LAB);

	}
	
	public ActionForward validateLabNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");
               System.out.println("whether it is coming inside the lab action:");
		try {
			
			
			
			System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specialization");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);

			String subame_available = new AddSubjectDelegate().validateLabName(form1);
			System.out.println("subame_available" +subame_available);

			JSONObject object = new JSONObject();
			object.put("des_available", subame_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}
	
	public ActionForward getpathdownloadOfLab(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload Starting");
		try {

			
			String docPath = request.getParameter("Path");
			
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\Lab_SYLLABUS"+("/")+docPath);
			
			
		
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in AddSubjectAction : getpathdownload  Ending");
		return null;
	}
	
	public ActionForward getLabsyllabusdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsyllabusdownload Starting");
	
		try {
			
			String subjectid = request.getParameter("labid");
			
			response.setContentType("application/octet-stream");
			
			String docPath = new AddSubjectDelegate().DdownloadLabsyllabuspathBD(subjectid);
			String fileName = "FileName";
			fileName=docPath;
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\Lab_SYLLABUS"+("/")+docPath);
			
			
		
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
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
				+ " Control in AddSubjectAction : getsyllabusdownload  Ending");
		return null;
	}
	
	public ActionForward downloadLabDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : downloadLabDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/subjectXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			
			List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
			
			streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");
			
			
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/subjectXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Subject Details Excel Report" };
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
					request.getRealPath("Reports/subjectXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=subjectXLSReport.xls");
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
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS   Ending");
				return null;
		
		
	}
	public ActionForward downloadLabDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Starting");

			try {

				
/*
				List<ViewallSubjectsVo> Details = new ArrayList<ViewallSubjectsVo>();
				Details = new AddSubjectDelegate().subjectdetails();
				*/
				
				
				
				List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
				
				streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
						.getAttribute("EXcelDownLoad");
				
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/subjectPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						streamList);

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
						+ "subjectdetailsPDF - " + ".pdf\"");

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
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Ending");
			return null;

		}
	
	


}
