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

import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TermMasterVo;

public class TermMasterAction extends DispatchAction

{

	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward addtermdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Starting");

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccesstermMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccesstermMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.SuccesstermUpMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccesstermUpMsg);
			}
		}

		String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		TermMasterDelegate delegates = new TermMasterDelegate();
		TermMasterVo vo = new TermMasterVo();

		vo.setAccid(accyear);

		try

		{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TERMSETUP);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
		
			String date=HelperClass.getCurrentSqlDate().toString();
			String statuss = delegates.dateOverLapValidate(date,accyear);
			if(statuss==null || statuss.equalsIgnoreCase("") || statuss.equalsIgnoreCase(null)){
				statuss=Integer.toString(HelperClass.getPastDateofAcademicYear(request)+1);
			}
			
			TermMasterDelegate delegate = new TermMasterDelegate();
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(request));

			
			TermMasterVo acclist = delegate.getaccdetails(vo);
			request.setAttribute("statuss", statuss);
			request.setAttribute("acclist", acclist);
			request.setAttribute("enddate", enddate);

		}

		catch (Exception e)

		{

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Ending");

		return mapping.findForward(MessageConstants.ADD_TERM);

	}

	public ActionForward getnamecount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : getnamecount Starting");

		String name = request.getParameter("name");
		String accyear = request.getParameter("accyear");
		String id = request.getParameter("id");
		String locationId=request.getParameter("locationId");

		boolean status = false;

		try {

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(accyear);
			vo.setTermid(id);
			vo.setLocationId(locationId);

			TermMasterDelegate delegate = new TermMasterDelegate();

			status = delegate.getnamecount(vo);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("message", status);

			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : getnamecount Ending");

		return null;

	}

	public ActionForward addtermfeedetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermfeedetails Starting");

		String accyear = (String) request.getSession(false).getAttribute(
				"current_academicYear");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_ADMIN);
			
			
			TermMasterVo vo = new TermMasterVo();
			vo.setTermid(request.getParameter("id"));
			vo.setTermname(request.getParameter("name"));
			vo.setDescription(request.getParameter("description"));
			vo.setStartdate(request.getParameter("startdate"));
			vo.setEnddate(request.getParameter("enddate"));
			vo.setAccyear(request.getParameter("academic_year"));
			vo.setLocationId(request.getParameter("locationId"));
			vo.setTransporttype(request.getParameter("transId"));
			vo.setCreateuser(HelperClass.getCurrentUserID(request));

			TermMasterDelegate delegate = new TermMasterDelegate();

			String result = delegate.addtermfeedetails(vo);

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
				+ " Control in TermMasterAction : addtermfeedetails Ending");

		return null;

	}

	public ActionForward edittermDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : edittermDetails Starting");

		String id = request.getParameter("id");

		try {

			TermMasterVo vo = new TermMasterVo();

			vo.setTermid(id);

			TermMasterVo editlist = new TermMasterDelegate()
					.edittermDetails(vo);

			request.setAttribute("editlist", editlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : edittermDetails Ending");

		return mapping.findForward(MessageConstants.ADD_TERM);

	}

	public ActionForward deleteTermDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : deleteTermDetails Starting");

		String result = "";
		String name = request.getParameter("getDataArray");//Name should be according to js name4
		String getDataArray[]=name.split(",");
		try {

			TermMasterVo vo = new TermMasterVo();
			vo.setGetDataArray(getDataArray);//-------------5

			result = new TermMasterDelegate().deleteTermDetails(vo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("jsonResponse", result);
			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : deleteTermDetails Ending");

		return null;

	}

	public ActionForward downloadtermlistDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : downloadtermlistDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/termdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			String name = request.getParameter("searchvalue");

			TermMasterVo vo = new TermMasterVo();
			vo.setTermname(name);

			System.out.println("namename" + name);

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
					.termList(vo);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					termlist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/termdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Term Details Excel Report" };
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

			pdfxls = new File(request.getRealPath("Reports/Termdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Termdetailsxls.xls");
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
				+ " Control in TermMasterAction : downloadtermlistDetailsXLS   Ending");
		return null;

	}

	public ActionForward downloadtermlistDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : downloadtermlistDetailsPDF  Starting");

		try {

			System.out.println("downloading pdf");

			TermMasterVo vo = new TermMasterVo();
			String name = request.getParameter("searchvalue");
			vo.setTermname(name);

			System.out.println("namename" + name);

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
					.termList(vo);

			String sourceFileName = request
					.getRealPath("Reports/termDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					termlist);

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
					+ "TermDetailsPDF - " + ".pdf\"");

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
				+ " Control in TermMasterAction : downloadtermlistDetailsPDF  Ending");
		return null;

	}
	public ActionForward dateOverLapValidate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : downloadtermlistDetailsPDF  Starting");

		String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		try {
			
			String date=request.getParameter("date");
			TermMasterDelegate delegate = new TermMasterDelegate();

			String status = delegate.dateOverLapValidate(date,accyear);
			
			
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("status", status);

			response.getWriter().println(jsonObject);
		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : downloadtermlistDetailsPDF  Ending");
		return null;

	}
	public ActionForward daterange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Starting");


		


		try

		{

			String accyear = request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			TermMasterDelegate delegates = new TermMasterDelegate();
			TermMasterVo vo = new TermMasterVo();

			vo.setAccid(accyear);
			
		
			String date=HelperClass.getCurrentSqlDate().toString();
			String startDate = delegates.dateOverLapValidate(date,accyear,locationId);
			if(startDate==null || startDate.equalsIgnoreCase("") || startDate.equalsIgnoreCase(null)){
				startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accyear)+1);
			}
			
			TermMasterDelegate delegate = new TermMasterDelegate();
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(accyear));

			
			TermMasterVo acclist = delegate.getaccdetails(vo);
			JSONObject obj=new JSONObject();
			
			obj.put("startDate", startDate+","+enddate);
			
			response.getWriter().print(obj);
			

		}

		catch (Exception e)

		{

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Ending");

		return null;

	}
	
	public ActionForward transportdaterange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Starting");


		try

		{

			String accyear = request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			TermMasterDelegate delegates = new TermMasterDelegate();
			TermMasterVo vo = new TermMasterVo();

			vo.setAccid(accyear);
			
		
			String date=HelperClass.getCurrentSqlDate().toString();
			String startDate = delegates.separatedateOverLapValidate(date,accyear,locationId);
			if(startDate==null || startDate.equalsIgnoreCase("") || startDate.equalsIgnoreCase(null)){
				startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accyear)+1);
			}
			
			TermMasterDelegate delegate = new TermMasterDelegate();
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(accyear));

			
			TermMasterVo acclist = delegate.getaccdetails(vo);
			JSONObject obj=new JSONObject();
			
			obj.put("startDate", startDate+","+enddate);
			
			response.getWriter().print(obj);
			

		}

		catch (Exception e)

		{

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermdetails Ending");

		return null;

	}
	public ActionForward termListByJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Starting");

		String academic_year = request.getParameter("accyear");
		String locationId=request.getParameter("locationId");
		if(locationId.equalsIgnoreCase("all")){
			locationId="%%";
		}
		String name = request.getParameter("searchvalue");

		try {
			

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(academic_year);
			vo.setLocationId(locationId);

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
		
			JSONObject obj=new JSONObject();
			obj.put("termlist", termlist);
			response.getWriter().print(obj);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Ending");

		return null;
	}
	
	public ActionForward TransporttermListByJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Starting");

		String academic_year = request.getParameter("accyear");
		String locationId=request.getParameter("locationId");
		if(locationId.equalsIgnoreCase("all")){
			locationId="%%";
		}
		String name = request.getParameter("searchvalue");

		try {
			

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(academic_year);
			vo.setLocationId(locationId);

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().separateTransportTermList(vo);
		
			JSONObject obj=new JSONObject();
			obj.put("termlist", termlist);
			response.getWriter().print(obj);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Ending");

		return null;
	}
}
