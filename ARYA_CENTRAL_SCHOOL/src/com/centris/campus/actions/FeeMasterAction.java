package com.centris.campus.actions;

import java.awt.print.PrinterJob;
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

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.FeeMasterDAOIMPL;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.FeeCollectionBD;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UserDetailVO;

public class FeeMasterAction extends DispatchAction

{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	private static String Scholladd = res.getString("AddressLine1");
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	public ActionForward addfeedetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		String status = request.getParameter("result");
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccessMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccessMsg);
			} else if ((status.equalsIgnoreCase(MessageConstants.ErrorMsg))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ErrorMsg);
			} else if (status.equalsIgnoreCase(MessageConstants.SuccessUpMsg)) {
				request.setAttribute("successmessagediv",
						MessageConstants.SuccessUpMsg);
			} else if (status.equalsIgnoreCase(MessageConstants.ErrorUpMsg)) {
				request.setAttribute("successmessagediv",
						MessageConstants.ErrorUpMsg);
			}

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : addfeedetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
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
				+ " Control in AddFeeDetailsAction : addfeedetails Ending");

		return mapping.findForward(MessageConstants.FEE_ADD_DETAILS_LIST);

	}

	public ActionForward AddFeeDetailsMaster(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : AddFeeDetailsMaster Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			AddFeeVO vo = new AddFeeVO();
			
			vo.setFeeTypeId(request.getParameter("feeTypeId"));
			vo.setId(request.getParameter("id"));
			vo.setName(request.getParameter("name"));
			vo.setAcademicYear(request.getParameter("academicYear"));
			vo.setLocationId(request.getParameter("locationId"));
			
			System.out.println("name" + request.getParameter("name"));

			vo.setDescription(request.getParameter("description"));

			vo.setCreatedby(HelperClass.getCurrentUserID(request));

			vo.setConcessiontype(request.getParameter("concessiontype"));

			System.out.println("fee type  "
					+ request.getParameter("feeTypeId"));

			FeeMasterDelegate delegate = new FeeMasterDelegate();

			String result = delegate.insertFeeDetails(vo);

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
				+ " Control in AddFeeDetailsAction : AddFeeDetailsMaster Ending");

		/* return mapping.findForward(MessageConstants.FEE_DETAILS_LIST); */
		return null;

	}

	
	public ActionForward getnamecount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : getnamecount Starting");

		boolean status = false;
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String locationId=request.getParameter("locationId");
		String accyear=request.getParameter("accyear");

		try {

			AddFeeVO vo = new AddFeeVO();
			vo.setId(id);
			vo.setName(name);
			vo.setLocationId(locationId);
			vo.setAcademicYear(accyear);

			FeeMasterDelegate delegate = new FeeMasterDelegate();
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
				+ " Control in AddFeeDetailsAction : getnamecount Ending");

		return null;

	}



	public ActionForward editFeeDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : editFeeDetails Starting");

		String name = request.getParameter("name");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			AddFeeVO vo = new AddFeeVO();

			vo.setId(name);

			AddFeeVO editlist = new FeeMasterDelegate().editFeeDetails(vo);
			System.out.println("Edit method : feeTypeId "+editlist.getFeeTypeId()+" FeeType: "+editlist.getFeeType());

			request.setAttribute("editlist", editlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : editFeeDetails Ending");

		return mapping.findForward(MessageConstants.FEE_ADD_DETAILS_LIST);

	}

	public ActionForward deleteFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : deleteFeeDetails Starting");

		boolean result = false;

		String name = request.getParameter("getDataArray");//Name should be according to js name4
		String getDataArray[]=name.split(",");

		try {

			AddFeeVO vo = new AddFeeVO();
			vo.setGetDataArray(getDataArray);//-------------5
			

			result = new FeeMasterDelegate().deleteFeeDetails(vo);
			System.out.println("result::::::::::"+result);

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
				+ " Control in AddFeeDetailsAction : deleteFeeDetails Ending");

		return null;

	}

	public ActionForward searchFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : searchFeeDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			AddFeeVO vo = new AddFeeVO();

			vo.setName(request.getParameter("searchvalue"));

			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate()
					.searchFeeDetails(vo);

			request.setAttribute("feelist", feelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : searchFeeDetails Ending");

		return mapping.findForward(MessageConstants.FEE_DETAILS_LIST);
	}

	public ActionForward FeeDetailsXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction  Starting");

		try {
			System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FeeDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			AddFeeVO vo = new AddFeeVO();

			vo.setName(request.getParameter("searchvalue"));
			ArrayList<AddFeeVO> List = new FeeMasterDelegate()
					.getfeedetails(vo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(List);
			
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Stream Details Excel Report" };
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
					request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeDetailsXLSReport.xls");
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
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction   Ending");
		return null;
	}

	public ActionForward FeeDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

		try {

			System.out.println("downloading pdf");
			AddFeeVO vo = new AddFeeVO();
			vo.setName(request.getParameter("searchvalue"));

			ArrayList<AddFeeVO> Details = new FeeMasterDelegate()
					.getfeedetails(vo);

			String sourceFileName = request
					.getRealPath("Reports/FeeDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Details);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
		return null;

	}
	
	

	
	public ActionForward getFeeTypeCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : getFeeTypeCount Starting");

		boolean status = false;
		String feeTypeId = request.getParameter("feeTypeId");
		String feeType = request.getParameter("feeType");
		String locationId=request.getParameter("locationId");
		String accyear=request.getParameter("accyear");
		System.out.println("FeeType COunt method in Action: "+feeTypeId+" --- "+feeType);

		try {
			
			AddFeeVO vo = new AddFeeVO();
			vo.setFeeTypeId(feeTypeId);
			vo.setFeeType(feeType);
			vo.setAcademicYear(accyear);
			vo.setLocationId(locationId);
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("feeTypeId "+vo.getFeeTypeId()+" feeType "+vo.getFeeType());
			
			FeeMasterDelegate delegate = new FeeMasterDelegate();
			status = delegate.getFeeTypeCount(vo);

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
				+ " Control in AddFeeDetailsAction : getnamecount Ending");

		return null;

	}
	
	public ActionForward getFeeTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : getFeeTypeList Starting");

		List<AddFeeVO> lists = new ArrayList<AddFeeVO>();
		try {
			
			AddFeeVO vo = new AddFeeVO();

			FeeMasterDelegate delegate = new FeeMasterDelegate();
			lists = delegate.feeTypeListBD();
			
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("FeeTypeList", lists);
			
			response.getWriter().println(jsonObject);
			
			System.out.println("Action class feeTypelist after JSON setting");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddFeeDetailsAction : getnamecount Ending");

		return null;

	}
	public ActionForward saveFineConfiguration(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFineConfiguration Starting");
		
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

		String userType = userDetailVO.getUserNametype();
		String userCode = userDetailVO.getUserId();
		
		try {
			String locationArray[]=request.getParameter("locationArray").split(",");
			String classArray[]=request.getParameter("classArray").split(",");
			String accyearArray[]=request.getParameter("accyearArray").split(",");
			String termArray[]=request.getParameter("termArray").split(",");
			String days[]=request.getParameter("dayArray").split(",");
			String amountArray[]=request.getParameter("amountArray").split(",");
			String isApplicable=request.getParameter("isApplicable");
			String accyear=request.getParameter("accyear");
			String status=new FeeMasterDAOIMPL().saveFineConfiguration(accyearArray,termArray,locationArray,classArray,days,amountArray,isApplicable,userCode,accyear);
			
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFineConfiguration Ending");
		
		return null;
	}
	//edited by anu
	public ActionForward doublePaymentDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : FeeDetailsDefaulterPDF  Starting");

		try {
			System.out.println("downloading pdf");
			
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId);
			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			parameters.put("locname", locname);
			parameters.put("classname",classname+" & "+divname);
			parameters.put("divname", divname);
			parameters.put("termname", termname);
			parameters.put("accname", accname);
			parameters.put("schladd", schladd);
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\"" + "FeeDetailsPDF - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
		}
		catch (Exception e)
		{logger.error(e.getMessage(), e);
			e.printStackTrace();}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : FeeDetailsDefaulterPDF   Ending");
		return null;
	}
	//
	public ActionForward FeeDetailsDefaulterPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : FeeDetailsDefaulterPDF  Starting");

		try {
			System.out.println("downloading pdf");
			
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId);
			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			parameters.put("locname", locname);
			parameters.put("classname",classname+" & "+divname);
			parameters.put("divname", divname);
			parameters.put("termname", termname);
			parameters.put("accname", accname);
			parameters.put("schladd", schladd);
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\"" + "FeeDetailsPDF - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
		}
		catch (Exception e)
		{logger.error(e.getMessage(), e);
			e.printStackTrace();}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeConcession : FeeDetailsDefaulterPDF   Ending");
		return null;
	}
		
	
	public ActionForward FeeDetailsDefaulterXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction  Starting");

		try {
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			
			System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			
			Map parameters = new HashMap();
			//parameters.put("Image", PropfilePath);
			parameters.put("locname", locname);
			parameters.put("classname",classname+" & "+divname); 
			parameters.put("divname", divname);
			parameters.put("termname", termname);
			parameters.put("accname", accname);
			parameters.put("schladd", schladd);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/FeeReportDefaulterExcel.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Defaulter Fee Report" };
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

			pdfxls = new File(request.getRealPath("Reports/FeeReportDefaulterExcel.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeReportDefaulterExcel.xls");
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
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction   Ending");
		return null;
	}
	
	
	public ActionForward PrintDefautFeeReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : PrintDefautFeeReport Starting");
		try{
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			
			Map parameters = new HashMap();
				//parameters.put("Image", PropfilePath);
				parameters.put("locname", locname);
				parameters.put("classname",classname+" & "+divname);
				parameters.put("divname", divname);
				parameters.put("termname", termname);
				parameters.put("accname", accname);
				parameters.put("schladd", schladd);
			
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId);
			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();


			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			//JasperViewer.viewReport(jasperPrint, false);
			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			/*   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
			   printRequestAttributeSet.add(mediaSizeName);*/
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
	}
}