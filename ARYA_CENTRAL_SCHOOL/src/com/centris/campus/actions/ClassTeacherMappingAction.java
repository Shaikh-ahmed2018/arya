package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.delegate.ClassTeacherMappingDelegate;
import com.centris.campus.delegate.RemainderMasterDelegate;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;

public class ClassTeacherMappingAction extends DispatchAction 

{
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public ActionForward adddisplaydetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
			
		{
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : adddisplaydetails Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_ADMIN);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_ADMIN);
		String status = request.getParameter("result"); 
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccessMappingMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.SuccessMappingMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.UpdateMappingMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.UpdateMappingMsg);
			}
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : adddisplaydetails Ending");
		
		
			return mapping.findForward(MessageConstants.ADD_DISPLAY_DETAILS);
		
		
		
		}
	
	
	public ActionForward getclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
			
		{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : getclass Starting");
		
		
		try {
			
			ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
			
			vo.setClassteacherid(request.getParameter("Classteacherid")); 
			
			JSONObject jsonObject=new JSONObject();
			
			if( null==vo.getClassteacherid() || !vo.getClassteacherid().equalsIgnoreCase(""))
				
			{
				
				ArrayList<ClassTeacherMappingVO> updateclasslist = new ClassTeacherMappingDelegate().getclassupdate(vo);
				jsonObject.put("jsonupdateResponse", updateclasslist);
			
			}
			
			ArrayList<ClassTeacherMappingVO> classlist = new ClassTeacherMappingDelegate().getclass(vo);
			
			jsonObject.put("jsonResponse", classlist);
			
			response.getWriter().println(jsonObject);
			
			
		
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : getclass Ending");
			return null;
		
		} 
		
		
		
		public ActionForward getsection(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception 
				
			{
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : getsection Starting");
			
			
			try {
				
				ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
				vo.setClassid(request.getParameter("classid"));
				vo.setClassteacherid(request.getParameter("classteacherid").trim());
				
				
				
				if(vo.getClassteacherid()!=null && !( "".equalsIgnoreCase(vo.getClassteacherid())))
					
				{
					
				ArrayList<ClassTeacherMappingVO> updatesectionlist = new ClassTeacherMappingDelegate().getsectionupdate(vo);
				
				JSONObject jsonObject=new JSONObject();
				
				jsonObject.put("updatesectionlist", updatesectionlist);
				
				response.getWriter().println(jsonObject);
				
				}
				
				
				
				else{
					

					
					ArrayList<ClassTeacherMappingVO> sectionlist = new ClassTeacherMappingDelegate().getsection(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonResponse", sectionlist);
					
					response.getWriter().println(jsonObject);
					
					
					
				}
				
			
			
				
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
			
			
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : getsection Ending");
				return null;
			
			}
			
			
			
			public ActionForward getteacher(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception 
					
				{
				
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingAction : getteacher Starting");
				
				
				try {
					
					ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
					
					vo.setClassteacherid(request.getParameter("Classteacherid")); 
					
					if(vo.getClassteacherid().equalsIgnoreCase("")||vo.getClassteacherid()==null)
					
					{
					
					ArrayList<ClassTeacherMappingVO> teacherlist = new ClassTeacherMappingDelegate().getteacher(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonResponse", teacherlist);
					
					response.getWriter().println(jsonObject);
					
					}
					
					
					if(!vo.getClassteacherid().equalsIgnoreCase(""))
						
					{
					
					ArrayList<ClassTeacherMappingVO> updateteacherlist = new ClassTeacherMappingDelegate().getupdateteacherlist(vo);
					ArrayList<ClassTeacherMappingVO> upteacherlist = new ClassTeacherMappingDelegate().getupteacherlist(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonupdateteacherResponse", updateteacherlist);
					jsonObject.put("jsonupteacherlist", upteacherlist);
					
					response.getWriter().println(jsonObject);
					
					}
					
				
					
				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				}
				
				
				
				
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingAction : getteacher Ending");
					return null;
				
				}
				
				public ActionForward addmappingdetails(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response) throws Exception 
						
					{

					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : addmappingdetails Starting");
					
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						vo.setClassid(request.getParameter("classid"));
						vo.setSectionid(request.getParameter("sectionid"));
						vo.setTeacherid(request.getParameter("teacherid"));
						vo.setClassteacherid(request.getParameter("classteacherid"));
						vo.setCreateuser(HelperClass.getCurrentUserID(request));
							
						ClassTeacherMappingDelegate delegate=new ClassTeacherMappingDelegate();
						
						String result=delegate.addmappingdetails(vo);
						
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
							+ " Control in ClassTeacherMappingAction : addmappingdetails Ending");

					
					return null;
					
					
				
				}
				
				
				
				public ActionForward editclassdetails(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{

					

					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : editclassdetails Starting");
					
					
					String classteacherid=request.getParameter("id");
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						
						vo.setClassteacherid(classteacherid);
						request.setAttribute("classteacherid", vo);
						
					

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}

					
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : editclassdetails Ending");

					return mapping.findForward(MessageConstants.ADD_DISPLAY_DETAILS);
				
				}
				
				
				public ActionForward deletemappingDetails(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deletemappingDetails Starting");
					
					
					String result="";
					
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						
						vo.setClassteacherid(request.getParameter("classteacher"));
						
						result = new ClassTeacherMappingDelegate()
						.deletemappingDetails(vo);
						

						JSONObject jsonObject=new JSONObject();
						
						jsonObject.put("jsonResponse", result);
						
						response.getWriter().println(jsonObject);
					

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}

					
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deletemappingDetails Ending");

					

					return null;
					
				}
				
				
				public ActionForward downloadteachermapping(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : downloadteachermapping Starting");
					
					
					try {

						File pdfxls = null;
						FileInputStream input = null;
						BufferedInputStream buf = null;
						ServletOutputStream stream = null;

						String sourceFileName = request
								.getRealPath("Reports/TeacherMappingDetailsXLSReport.jrxml");
						JasperDesign design = JRXmlLoader.load(sourceFileName);
						JasperReport jasperreport = JasperCompileManager
								.compileReport(design);
						List<ClassTeacherMappingVO> MasterList = new ArrayList<ClassTeacherMappingVO>();
						MasterList = (List<ClassTeacherMappingVO>) request.getSession(false)
								.getAttribute("Exceldownload");
					

						JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
								MasterList);
						Map parameters = new HashMap();

						stream = response.getOutputStream();
						JasperPrint print = JasperFillManager.fillReport(jasperreport,
								parameters, beanColDataSource);
						JRXlsExporter exporter = new JRXlsExporter();
						File outputFile = new File(
								request.getRealPath("Reports/TeacherMappingDetailsXLSReport.xls"));
						FileOutputStream fos = new FileOutputStream(outputFile);
						String[] sheetNames = { "Teacher Mapping Details Excel Report" };
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
								request.getRealPath("Reports/TeacherMappingDetailsXLSReport.xls"));
						response.setContentType("application/octet-stream");
						response.addHeader("Content-Disposition",
								"attachment; filename=TeacherMappingDetailsXLS.xls");
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
							+ " Control in ClassTeacherMappingAction : downloadteachermapping Ending");


					
					
					return null;
					
					
}
				
		
}
