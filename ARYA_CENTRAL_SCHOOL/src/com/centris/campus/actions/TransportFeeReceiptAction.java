package com.centris.campus.actions;

import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TransportVo;

public class TransportFeeReceiptAction extends DispatchAction 
{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static final Logger logger = Logger
			.getLogger(TransportFeeReceiptAction.class);

	public ActionForward TransportFeeReceipt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : TransportFeeReceipt Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_TRANSPORTFEE);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			
			ArrayList<ReportMenuVo> classDetails = new ReportsMenuBD().getClassDetails();
 
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("locationList", locationList);
			
			request.setAttribute("classDetails", classDetails);
			
			System.out.println("year name "+accYearList.get(0).getAccyearname());
			request.getSession(false).setAttribute("accYear",accYearList.get(0).getAccyearname());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : TransportFeeReceipt Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_FEE_REPORT);
	}
	
	
	public ActionForward getSectionByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getSectionByClass Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD().getSectionsByClass(classId,location);

			JSONObject object = new JSONObject();
			object.put("SectionList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getSectionByClass Ending");

		return null;
	}
	
	public ActionForward getTransportFeeExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Starting");
		
	/*	String accYear = null;
		String classId = null;
		String section = null;
		String term = null;*/
		
		String filePath = null;

		try {
			
			
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			String classname=request.getParameter("classname");
			String accYear=request.getParameter("accYear");
		
			
			System.out.println("location in EX"+location);
			System.out.println("accyear"+accyear);
			System.out.println("classId"+classId);
			System.out.println("section"+section);
			System.out.println("term"+term);
			System.out.println("termstatusId"+termstatusId);
		
			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
				accyear = HelperClass.getCurrentYearID();
			}
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null || section == "" || section.equalsIgnoreCase("")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
		ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
			obj.setClass_and_section(classname);
				
			
			
			ArrayList<ReportMenuVo> list = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentList");
			
			ArrayList<ReportMenuVo> data = new ReportsMenuBD().gettransportfeeDetails(obj);
			ArrayList<ReportMenuVo> datalist = new ReportsMenuBD().getAccYears();
			System.out.println("data length>>>"+data.size());
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
			
			Map mapdata = new HashMap();

			mapdata.put("accYear",accYear);
			mapdata.put("class_section",classname);
			mapdata.put("totalstudents", Integer.toString(data.size()));
			
			
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/TransportFeeReportXLS.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TransportFee.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Transport Details" };
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

			pdfxls = new File(request.getRealPath("Reports/TransportFee.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TransportFee.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Ending");

		return null;
	}
	
	public ActionForward getTransportFeePDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Starting");

			try {

				String location = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classId = request.getParameter("classId");
				String section = request.getParameter("section");
				String term = request.getParameter("term");
				String termstatusId = request.getParameter("termstatusId");
				String classname=request.getParameter("classname");
				String accYear=request.getParameter("accYear");
				
				System.out.println("location"+location);
				System.out.println("accyear"+location);
				System.out.println("classId"+location);
				System.out.println("section"+location);
			
				if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
					accyear = HelperClass.getCurrentYearID();
				}
				if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
					classId = "%%";
				}
				
				if(section == null || section == "" || section.equalsIgnoreCase("")) {
					section = "%%";
				}
				if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
					term = "%%";
				}
				if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
					termstatusId = "%%";
				}
				
				
			ReportMenuVo obj = new ReportMenuVo();
				
				obj.setAccyearId(accyear);
				obj.setSectionId(section);
				obj.setClassId(classId);
				obj.setLocationId(location);
				obj.setTermId(term);
				obj.setTermStatusId(termstatusId);
		
					
				
				ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj);
				String sourceFileName = request
						.getRealPath("Reports/TransportFeeReportPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

				String PropfilePath = getServlet().getServletContext().getRealPath("")
						+ "\\images\\" + ImageName.trim();

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);
				parameters.put("class_div", classname);
				parameters.put("accYear",accYear);
				parameters.put("total_stu_val",Integer.toString(list.size()));
			
				

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "TransportFeePDF" + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}catch (Exception e){
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Ending");
			return null;

		}
	
	public ActionForward getBusRouteDetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getBusRouteDetail Starting");
		try{
			
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String routeNo = request.getParameter("routeNo");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			
			System.out.println("termstatusId"+termstatusId);
		
			
			if(routeNo == null || routeNo.equalsIgnoreCase("") || routeNo.equalsIgnoreCase("all")) {
				routeNo = "%%";
			}
			
			
			
			
			
			System.out.println("termstatusId"+termstatusId);
			
			
			TransportVo obj = new TransportVo();
			
			obj.setAccyear(accyear);
			obj.setLoc_id(location);
			obj.setRouteNo(routeNo);
			
				
			
			ArrayList<TransportVo> list = new ReportsMenuDaoImpl().getBusRouteDetail(obj);
			JSONObject object = new JSONObject();
			object.put("busroute",list);
			response.getWriter().print(object);
			
			request.getSession(false).setAttribute("studentList",list);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getBusRouteDetail Ending");
		
		
		return null;
	}
	
	
	public ActionForward gettransportfeeDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : gettransportfeeDetails Starting");
		try{
			
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			
			System.out.println("termstatusId"+termstatusId);
		
			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
				accyear = HelperClass.getCurrentYearID();
			}
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null ||section.equalsIgnoreCase("") || section.equalsIgnoreCase("all")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
			
			System.out.println("termstatusId"+termstatusId);
			
			
			ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
				
			
			ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj);
			JSONObject object = new JSONObject();
			object.put("studentList",list);
			response.getWriter().print(object);
			
			request.getSession(false).setAttribute("studentList",list);
			
		/*	request.getSession(false).setAttribute("classId",list.get(0).getClassname());*/
	          /* System.out.println("classidfrom action:" +list.get(0).getClassname());
			request.getSession(false).setAttribute("sectionname",list.get(0).getSectionname());
			request.getSession(false).setAttribute("class_section",list.get(0).getClass_and_section());*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : gettransportfeeDetails Ending");
		return null;
	}
	//edited by anu

	public ActionForward getonlinelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String setionid=request.getParameter("sectionid");
			 String paymodeid=request.getParameter("paymodid");
			 String paymenttype =request.getParameter("paymenttype");
			 String termId=request.getParameter("termId");
				
				if(termId==null || termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 if(locationid==null || locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
			 }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			 if(setionid.equalsIgnoreCase("all")){
				 setionid="%%";
	    		   
			 }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<TransportVo> list=new ArrayList<TransportVo>();
			list=TransportBD.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JSONObject json = new JSONObject();
			json.put("studentList", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Ending");

		return null;
	}
	
	public ActionForward getFeeCollectionPaymodeReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String setionid=request.getParameter("sectionid");
			 String paymodeid=request.getParameter("paymodid");
			 String paymenttype =request.getParameter("paymenttype");
			 String termId=request.getParameter("termId");
				
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
	    	   }
			 if(setionid.equalsIgnoreCase("all")){
				 setionid="%%";
	    		   
	    	   }
			 if(accyear.equalsIgnoreCase("all")){
				 accyear="%%";
	    		   
			 }
			 if(paymodeid.equalsIgnoreCase("all")){
				 paymodeid="%%";
	    		   
			 }
			 if(paymenttype.equalsIgnoreCase("all")){
				 paymenttype="%%";
	    		   
			 }
			ArrayList<TransportVo> list=new ArrayList<TransportVo>();
			list=TransportBD.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId);
			JSONObject json = new JSONObject();
			json.put("studentList", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Ending");

		return null;
	}
	
	public ActionForward getfeecollectiondatelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			
			String locationid=request.getParameter("location");
			 String accyear=request.getParameter("accyear");
			 String classid=request.getParameter("classid");
			 String termId=request.getParameter("termId");
			String startdate=request.getParameter("startdate");	
			String enddate=request.getParameter("enddate");	
			String PaymentType=request.getParameter("PaymentType");
			String paymode=request.getParameter("paymode");
			if(PaymentType.equalsIgnoreCase("all")){
				 PaymentType="%%";
		    		   
		    	   }
			 if(paymode.equalsIgnoreCase("all")){
				 paymode="%%";
		    		   
		    	   }
			
				if(termId.equalsIgnoreCase("all")){
					termId="%%";
		    		   
		    	   }
			 
			 if(locationid.equalsIgnoreCase("all")){
				 locationid="%%";
	    		   
	    	   }
			 if(classid.equalsIgnoreCase("all")){
				 classid="%%";
	    		   
			 }
			ArrayList<TransportVo> list=new ArrayList<TransportVo>();
			list=new TransportDaoImpl().getfeecollectiondatelist(locationid,accyear,classid,termId,startdate,enddate,paymode);
			
			JSONObject json = new JSONObject();
			json.put("studentList", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Ending");

		return null;
	}
	//end
	public ActionForward getTerm(ActionMapping map,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTerm Starting");
		try{
			
			String accyear=request.getParameter("accyear");
			String location=request.getParameter("location");
			ReportMenuVo terms = new ReportMenuVo();
			
			ArrayList<ReportMenuVo> termlist = new ReportsMenuBD().getTerm(accyear,location);
			
			JSONObject object = new JSONObject();
			object.accumulate("termlist",termlist);
			response.getWriter().print(object);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTerm Ending");
		return null;
	}
	
	public ActionForm getTermFeeStatus(ActionMapping map,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTermFeeStatus Starting");
		
		try
		{
			ArrayList<ReportMenuVo> termstatus = new ArrayList<ReportMenuVo>();
			
			JSONObject object = new JSONObject();
			object.accumulate("termstatus",termstatus);
			response.getWriter().print(object);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTermFeeStatus Ending");
		
		return null;
	}
	
	public ActionForward printTransportFeeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction : getStudentBusCard Starting");
		try{
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			String classname=request.getParameter("classname");
			String accYear=request.getParameter("accYear");
			
			System.out.println("location"+location);
			System.out.println("accyear"+location);
			System.out.println("classId"+location);
			System.out.println("section"+location);
		
			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
				accyear = HelperClass.getCurrentYearID();
			}
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null || section == "" || section.equalsIgnoreCase("")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
		ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
				
			
			ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj);
			String sourceFileName = null;
			String termName = null;
			
			sourceFileName = request
					.getRealPath("Reports/TransportFeeReportPDF.jrxml");
			
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

			

			Map parameters = new HashMap();
			parameters.put("Image", PropfilePath);
			parameters.put("class_div", classname);
			parameters.put("accYear",accYear);
			parameters.put("total_stu_val",Integer.toString(list.size()));
			
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
		return mapping.findForward(MessageConstants.TRANSPORT_FEE_REPORT);
	}
	
	
	public ActionForward route(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: route settings");

		try

		{
			
			request.setAttribute("AccyearId", HelperClass.getAllAcademicYear());
			
			TransportBD obj = new TransportBD();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String searchYear=request.getParameter("accyear");
			
				
				list = obj.getTransportMasterDetails(searchYear);

				JSONObject jobj=new JSONObject();
				jobj.put("routeList", list);
				response.getWriter().print(jobj);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: route Ending");

		return null;
	}
	
	public ActionForward getRouteWiseStudentDetailwithClassAndSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetailwithClassAndSection settings");

		try

		{
			
		
			
			TransportDaoImpl obj = new TransportDaoImpl();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String location=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String section=request.getParameter("section");
			
				if(classId==null || classId.equalsIgnoreCase("all") || classId.equalsIgnoreCase("")) {
					classId="%%";
				}
				if(section==null || section.equalsIgnoreCase("all") || section.equalsIgnoreCase("")) {
					section="%%";
				}
				list = obj.getRouteWiseStudentDetailwithClassAndSection(location,accyear,classId,section);

				JSONObject jobj=new JSONObject();
				jobj.put("studentList", list);
				response.getWriter().print(jobj);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetailwithClassAndSection Ending");

		return null;
	}
	
	
	public ActionForward getRouteWiseStudentDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetail settings");

		try

		{
			
		
			
			TransportDaoImpl obj = new TransportDaoImpl();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String location=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String routeNo=request.getParameter("routeNo");
			//String stop=request.getParameter("stop");
			
				if(routeNo==null || routeNo.equalsIgnoreCase("all") || routeNo.equalsIgnoreCase("")) {
					routeNo="%%";
				}
				
				list = obj.getRouteWiseStudentDetail(location,accyear,routeNo);

				JSONObject jobj=new JSONObject();
				jobj.put("studentList", list);
				response.getWriter().print(jobj);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetail Ending");

		return null;
	}
	//edited for sms purpose by anu
	public ActionForward getBusWiseStudentDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetail settings");

		try

		{
		
			TransportDaoImpl obj = new TransportDaoImpl();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String loc=request.getParameter("location");
			String acc=request.getParameter("accyear");
			String routeNo=request.getParameter("routeNo");
			String stop=request.getParameter("stop");
			
				/*if(routeNo==null || routeNo.equalsIgnoreCase("all") || routeNo.equalsIgnoreCase("")) {
					routeNo="%%";
				}*/
				
				list = obj.getBusWiseStudentDetail(loc,acc,routeNo,stop);
				System.out.println(list);

				JSONObject jobj=new JSONObject();
				jobj.put("studentList", list);
				response.getWriter().print(jobj);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetail Ending");

		return null;
	}
	//
		//FOR SMS
	public ActionForward getContactBusStudentDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getContactBusStudentDetail settings");

		try

		{
		
			//TransportDaoImpl obj = new TransportDaoImpl();
			//List<TransportVo> list = new ArrayList<TransportVo>();
			String loct=request.getParameter("location");
			String accy=request.getParameter("accyear");
			String route=request.getParameter("routeNo");
			String stopp=request.getParameter("stop");
			
				/*if(routeNo==null || routeNo.equalsIgnoreCase("all") || routeNo.equalsIgnoreCase("")) {
					routeNo="%%";
				}*/
				
				//list = obj.getContactBusStudentDetail(loct,accy,route,stopp);
				List<TransportVo> list = new TransportDaoImpl().getContactBusStudentDetail(loct,accy,route,stopp);
				System.out.println(list);

				request.setAttribute("studentSearchList", list);
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("studentSearchList", list);
				response.getWriter().print(jsonobj);

		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransporFeeReceiptAction: getRouteWiseStudentDetail Ending");

		return null;
	}
	
}
