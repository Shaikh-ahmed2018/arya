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

import com.centris.campus.daoImpl.StaffServiceReportDaoImpl;
import com.centris.campus.delegate.ClassTeacherLsisBD;
import com.centris.campus.delegate.StaffServiceReportBD;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherVo;

public class ClassTeachetMappingAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(ClassTeachetMappingAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward editClassTeacherAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : editClassTeacherAction Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHERMAPPING);

			
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String teacherid = request.getParameter("teacherid");
			String locationid = request.getParameter("locationid");
			System.out.println("teacherid "+teacherid);
			ClassTeacherVo vo = new ClassTeacherVo();
			
			vo.setClassId(classid);
			vo.setSectionId(sectionid);
			vo.setTeacherId(teacherid);
			vo.setLocationId(locationid);
			
			ArrayList<TeacherRegistrationPojo> teacherlist =new StaffServiceReportDaoImpl().getTeacherListForClassTeacher(locationid); 
			request.setAttribute("teacherlist", teacherlist);
			request.setAttribute("locationid", locationid);
			request.setAttribute("teacherid", teacherid);
			
			
			ClassTeacherVo classlist = new ClassTeacherLsisBD().editClassTeacherBD(vo);
			
			request.setAttribute("getcalssteacher", classlist);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : editClassTeacherAction Ending");

		return mapping.findForward(MessageConstants.editClassTeacher);
	}

	
	public ActionForward saveClassTeachetAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : saveClassTeachetAction Starting");
		
		System.out.println("action");
		try {
			
			String userid = HelperClass.getCurrentUser(request);
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String teacherid = request.getParameter("teacherid");
			String locationid = request.getParameter("locationid");
			String teacherid1 = request.getParameter("teacherid1");
			
			
			System.out.println("classid"+classid);
			System.out.println("sectionid"+sectionid);
			System.out.println("teacherid"+teacherid);
			System.out.println("teacherid1"+teacherid1);
			
			ClassTeacherVo vo = new ClassTeacherVo();
			
			vo.setClassId(classid);
			vo.setSectionId(sectionid);
			vo.setTeacherId(teacherid);
			vo.setCreateuser(userid);
			vo.setTeacherId1(teacherid1);
			vo.setLocationId(locationid);
			String result = new ClassTeacherLsisBD().saveClassTeacherBD(vo);
			
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
				+ " Control in ClassTeachetMappingAction : saveClassTeachetAction Ending");

		return null;
	}
	
	
	
	public ActionForward viewValidateClassTeacherAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : viewValidateClassTeacherAction Starting");
		
		
		
		try {
			
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String teacherid = request.getParameter("teacherid");
			
			ClassTeacherVo vo = new ClassTeacherVo();
			
			vo.setClassId(classid);
			vo.setSectionId(sectionid);
			vo.setTeacherId(teacherid);
			
			
			boolean classTeacher_Available= new ClassTeacherLsisBD().validateClassTeacherBD(vo);
			
			
			JSONObject jsonobject= new JSONObject();
			
			if(classTeacher_Available){
			
				jsonobject.put("status", "true");
			}else{
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
				+ " Control in ClassTeachetMappingAction : viewValidateClassTeacherAction Ending");

		return null;
	}
	
	
	
	
	
	
	public ActionForward classTeacherMappingXLSReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : classTeacherMappingXLSReport Starting");
		
		
	
		try {
			
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/ClassTeacherXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			ArrayList<ClassTeacherVo> list =new ArrayList<ClassTeacherVo>();
			list = (ArrayList<ClassTeacherVo>) request.getSession(false).getAttribute("EXcelDownLoad");
		

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/ClassTeacherXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "ClassTeacher Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/ClassTeacherXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ClassTeacherXLSReport.xls");
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
				+ " Control in ClassTeachetMappingAction : classTeacherMappingXLSReport Ending");

		return null;
	}
	
	
	
	
	
	
	
	

	public ActionForward classTeacherMappingPDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : classTeacherMappingPDFReport Starting");
		
		
	try {
		
		
		ArrayList<ClassTeacherVo> list =new ArrayList<ClassTeacherVo>();
		list = (ArrayList<ClassTeacherVo>) request.getSession(false)
				.getAttribute("EXcelDownLoad");
	
		String sourceFileName = request
				.getRealPath("Reports/ClassTeacherPDFReport.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				list);

		String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName = res.getString("SchoolName");
		String schAddLine1 = res.getString("AddressLine1");

		Map parameters = new HashMap();
		
		parameters.put("Image", PropfilePath);


		byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
				parameters, beanColDataSource);

		response.setContentType("application/pdf");

		response.setContentLength(bytes.length);

		response.setHeader("Content-Disposition", "outline; filename=\""
				+ "ClassTeacherPDFReport - " + ".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();

		outstream.write(bytes, 0, bytes.length);

		outstream.flush();
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeachetMappingAction : classTeacherMappingPDFReport Ending");

		return null;
	}
	
	
	
	
	
	
	
	

}
