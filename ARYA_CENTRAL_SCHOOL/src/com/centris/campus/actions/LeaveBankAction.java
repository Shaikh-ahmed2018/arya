package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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




import com.centris.campus.delegate.LeaveBankDelegate;

import com.centris.campus.delegate.ReportsMenuBD;

import com.centris.campus.forms.LeaveBankForm;

import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

import com.centris.campus.vo.LeaveBankVO;


import com.centris.campus.vo.ReportMenuVo;


public class LeaveBankAction extends DispatchAction {
	
	private static Logger logger = Logger
			.getLogger(LeaveBankAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward addingleavebankscreen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);

		/*	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);*/
			 
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			  
			  
			  
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		return mapping.findForward(MessageConstants.ADD_LEAVEBANK_LIST);
	}
	
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			 
			LeaveBankForm aform = new LeaveBankForm();

		
			String createUser = HelperClass.getCurrentUserID(request);
			String academicyear = request.getParameter("academicyear");
			String totalleave = request.getParameter("totalleave");
			String allowedleave = request.getParameter("allowedleave");
			//String sno = request.getParameter("sno");
			String casualleave = request.getParameter("casualleave");
			String paidleave = request.getParameter("paidleave");
			String sickleave = request.getParameter("sickleave");
			String sno=request.getParameter("snoid");
			
			double sickleave1 = Double.parseDouble(sickleave);
			double paidleave1 = Double.parseDouble(paidleave);
			double casualleave1 = Double.parseDouble(casualleave);
			
			
			aform.setAcademicyear(academicyear);
			aform.setTotalleaves(totalleave);
			aform.setPermonth(allowedleave);
			aform.setSno(sno);
			aform.setSickleave(sickleave1);
			aform.setPaidleave(paidleave1);
			aform.setCasualleave(casualleave1);
			aform.setCreatedby(createUser);
			System.out.println("academicyear"+academicyear);
			System.out.println("totalleave"+totalleave);
			System.out.println("allowedleave"+allowedleave);
			System.out.println("sickleave"+sickleave1);
			System.out.println("paidleave"+paidleave1);
			System.out.println("setCasualleave"+casualleave1);
			System.out.println("sno"+sno);
			
			
			String delegate = new LeaveBankDelegate().insertLeaveBankDelegate(aform);
			
			request.setAttribute("attnhidden", sno);


			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", delegate);

			response.getWriter().print(jsonobj);

	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return null;

	}
	
	
	public ActionForward editleavebank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			
			System.out.println("edit leave banck action");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			LeaveBankForm aform = new LeaveBankForm();
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			
			String sno = request.getParameter("snoid");
			aform.setSno(sno);
		 			
			LeaveBankForm result = new LeaveBankDelegate().editleavebankdelegate(aform);	
         
		request.setAttribute("attnhidden", sno);
			
			request.setAttribute("editleavebank", result);
			
			 
		

	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return mapping.findForward(MessageConstants.ADD_LEAVEBANK_LIST);

	}
	
	
	public  ActionForward deleteLeavebank(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action: delete Leave Starting");
		try {
			System.out.println("deleteaction is working");
			
			
		

			String[] deletelist = request.getParameterValues("year[]");
			
			System.out.println("deletelist"+deletelist);
		

			Boolean deletestatus = new LeaveBankDelegate().deleteLeave(deletelist);
			
			System.out.println("delete status======" + deletestatus);
			
			request.setAttribute("message", deletestatus);
			
			JSONObject json = new JSONObject();
			json.accumulate("deletestatus", deletestatus);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward leavebankexcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action : leavebankexcel  Starting");
		
		try {
		System.out.println("DOWNLOADING Leave bank EXCEL");
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/LeaveBankDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			LeaveBankVO vo = new LeaveBankVO();
			ArrayList<LeaveBankVO> List = new LeaveBankDelegate().leavebanklist(vo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/LeaveBankDetailsXLSReport.xls"));
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
					request.getRealPath("Reports/LeaveBankDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LeaveBankDetailsXLSReport.xls");
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
				+ " Control in LeaveBank Action : leavebankexcel   Ending");
				return null;
		
		
	}
	
	public ActionForward leavebankpdf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassAction : classPathDetailsPDFReport  Starting");

			try {

				System.out.println("downloading leave bank pdf");

				LeaveBankVO vo = new LeaveBankVO();
				ArrayList<LeaveBankVO> Details = new LeaveBankDelegate()
						.leavebanklist(vo);
				
 				
				String sourceFileName = request
						.getRealPath("Reports/LeaveBankPDF.jrxml");
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
						+ "LeaveBankPDF - " + ".pdf\"");

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

	public  ActionForward searchleavbankList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Starting");
		
			
			String searchTextVal = request.getParameter("searchvalue");

			try {
				
				System.out.println("Search Action Is Working");
				System.out.println("Search Action Is Working"+searchTextVal);
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_SETTINGS);
				
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_SETTINGS);

				ArrayList<LeaveBankVO> list = new LeaveBankDelegate()
						.getSearchDetails(searchTextVal);
										
				request.setAttribute("leaveBank", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

	
				
		return mapping.findForward(MessageConstants.LEAVEBANK_LIST);

	}

	public synchronized ActionForward validAddLeave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validAddLeave Starting");
		try {
			String year = request.getParameter("academicyear");

			Boolean validate = new LeaveBankDelegate().validAddLeave(year);

			JSONObject json = new JSONObject();
			json.accumulate("validate", validate);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
	
	// Leave Category
	
	public ActionForward addingleaveCategoryscreen(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addingleaveCategoryscreen Starting");
		try{
		
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVETYPES);
			
			String title ="Add New Leave Type";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			  
			ArrayList<LeaveBankVO>   catList = new LeaveBankDelegate().getleaveCatList();
			request.setAttribute("LeaveCatList", catList);
			 
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();	
			request.setAttribute("locationList", locationList);	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addingleaveCategoryscreen Ending");

		return mapping.findForward(MessageConstants.LEAVECAT_ADD__LIST);
	}
	
	public ActionForward addLeavesCategory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate addLeavesCategory Starting");
		try{
			String  validate  = null;
			System.out.println("Inside Action leave Bank");
			
			String[] categorynames = request.getParameterValues("categorynames[]");
			String[] shortnames = request.getParameterValues("shortnames[]");
			String[] noofleaves = request.getParameterValues("noofleaves[]");
			String[] catId = request.getParameterValues("catId[]");
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String[] hiddenLEaveIdArray = request.getParameterValues("hiddenLEaveIdArray[]");
			/*
			if( hiddenLEaveIdArray !=null){
			 validate = new LeaveBankDelegate().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
			}*/
			
			 validate = new LeaveBankDelegate().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location);
		
			
			JSONObject json = new JSONObject();
			json.accumulate("validate", validate);
			response.getWriter().print(json);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addLeavesCategory Ending");
		
		
		return null;
	}
	
	public ActionForward editleavetypes(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate editleavetypes Starting");
		
		try{
				String accyear = request.getParameter("accyear");
				String loc =request.getParameter("loc");
				
				request.setAttribute("Accyear", accyear);
				request.setAttribute("loc", loc);
				
				
				
				String title = "Modify Leave Type";
				request.setAttribute("title", title);
			
				ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
				request.setAttribute("AccYearList", accYearList);
				
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();	
				request.setAttribute("locationList", locationList);	
				
				ArrayList<LeaveBankVO>   catList = new LeaveBankDelegate().getaccLocCatList(accyear,loc);
				
				request.setAttribute("AccName", catList.get(0).getAcademicyear());
				request.setAttribute("Locname",catList.get(0).getLocationName());
			
				ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
				list = new LeaveBankDelegate().editleavetypes(accyear,loc);
				request.setAttribute("editList",list);
				request.setAttribute("LeaveCatList", list);
				
				System.out.println(list.get(0).getLeaveName());
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:editleavetypes Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVECAT_EDIT__LIST);
	}
	
public ActionForward checkLeaveType (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate addingleaveCategoryscreen Starting");
		
		try{
			
			String accyear = request.getParameter("accyear");
			String loc =request.getParameter("location");
			String category = request.getParameter("cat");
			
			String status = new LeaveBankDelegate().checkLeaveType(accyear,loc,category);
			
			System.out.println(status);
			
			JSONObject json = new JSONObject();
			json.accumulate("status", status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	return null;
	}
	
public ActionForward checkDuplicacy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action:validate addingleaveCategoryscreen Starting");
	
	try{
		
		String accyear = request.getParameter("accyear");
		String loc =request.getParameter("location");
		
		String status = new LeaveBankDelegate().checkDuplicacy(accyear,loc);
		
		System.out.println(status);
		
		JSONObject json = new JSONObject();
		json.accumulate("status", status);
		response.getWriter().print(json);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	return null;
}
	






}
	
	

