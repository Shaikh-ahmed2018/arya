
package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
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
import org.json.JSONObject;

import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.Inventory_Delegate;
import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class InventoryMenuAction extends DispatchAction {
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ImageName = res.getString("ImageName");
	private static final Logger logger = Logger
			.getLogger(InventoryMenuAction.class);

	public ActionForward AddInventoryType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction:  AddSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {

			InventoryForm form1 = (InventoryForm) form;

			String currentuser = HelperClass.getCurrentUserID(request);
			form1.setCurrent_user(currentuser);
			System.out.println(form1.getInventory_id());

			String addsubject = new Inventory_Delegate()
					.AddInventoryType(form1);

			System.out.println(addsubject);
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
					.getDepartmentDetails();
			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);
			
			

			if (addsubject == "true") {
				request.setAttribute("successmessagediv",
						"Inventory Type Created Successfully");
			} else if (addsubject == "false") {
				request.setAttribute("errorMessage",
						"Inventory Type Not Created Successfully");
			} else if (addsubject == "true1") {
				request.setAttribute("successmessagediv",
						"Inventory Type Updated Successfully");
			} else if (addsubject == "false1") {
				request.setAttribute("errorMessage",
						"Inventory Type Not Updated Successfully");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.INVENTORY_ADD);

	}

	public ActionForward EditInventoryType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		try {

			String id = request.getParameter("name");
			System.out.println(id);
			InventoryVO vo = new InventoryVO();

			vo.setItem_type_id(id);

			InventoryVO status = new Inventory_Delegate().EditInventoryType(vo);

			request.setAttribute("INVENTORY_TYPE_LIST", status);
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
					.getDepartmentDetails();
			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return mapping.findForward(MessageConstants.INVENTORY_ADD);
		
	}

	public ActionForward DeleteInventoryType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		

		String name = request.getParameter("getDataArray");//Name should be according to js name4
		String getDataArray[]=name.split(",");
		
		try {

			
		
			InventoryVO vo = new InventoryVO();
			vo.setGetDataArray(getDataArray);//-------------5


			String status = new Inventory_Delegate().DeleteInventoryType(vo);

			if (status == "true") {
				request.setAttribute("successmessagediv",
						"Inventory Type Deleted Successfully");
			} else if (status == "false") {
				request.setAttribute("errorMessage",
						"Inventory Type Not Deleted Successfully");
			}

			List<InventoryVO> list = new ArrayList<InventoryVO>();
			list = new Inventory_Delegate().InventoryTypesList();
			request.setAttribute("InventoryTypesList", list);
			
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("jsonResponse", status);

			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return null;

	}

	public ActionForward InventoryTypeXLS (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InventoryTypeDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryVO> list = new ArrayList<InventoryVO>();
			
			String SearchName = request.getParameter("searchname");
			
			if(SearchName != null){
				
				list=obj.searchInventoryTypeList(SearchName);
				
			}
			else{
				
			 list =obj.InventoryTypesList();
				
			}
			

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/InventoryTypeDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Inventory Type Details Excel Report" };
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
					request.getRealPath("Reports/InventoryTypeDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=InventoryTypeDetailsXLSReport.xls");
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
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Ending");
		return null;

	}

	
	public ActionForward InventoryTypePDF (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Starting");

		try {

			
			List<InventoryVO> list = new ArrayList<InventoryVO>();
			
			String SearchName = request.getParameter("searchname");
			
			if(SearchName != null){
				
				list= new Inventory_Delegate().searchInventoryTypeList(SearchName);
				
			}
			else{
				
			 list = new Inventory_Delegate().InventoryTypesList();
				
			}
			
			String sourceFileName = request
					.getRealPath("Reports/InventoryTypePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Image", PropfilePath);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Inventory Type PDF Report - " + ".pdf\"");

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
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Ending");
		return null;

	}

	//Add or Modify or Delete
	public ActionForward CreatingAddorModifyorDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction:  AddSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {

			InventoryForm form1 = (InventoryForm) form;

			String currentuser = HelperClass.getCurrentUserID(request);
			form1.setCurrent_user(currentuser);
			System.out.println("Purchase Id::::"+form1.getPurchase_id());

			String addsubject = new Inventory_Delegate()
					.CreatingAddorModifyorDelete(form1);

			System.out.println(addsubject);
			
			/*ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
					.getDepartmentDetails();

			List<InventoryVO> list = new Inventory_Delegate()
					.InventoryTypesList();

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);*/
			
			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();
			list = new Inventory_Delegate().AddorModifyorDeleteList();
			request.setAttribute("list", list);
			
			

			if (addsubject == "true") {
				request.setAttribute("successmessagediv",
						"Purchase Type Created Successfully");
			} else if (addsubject == "false") {
				request.setAttribute("errorMessage",
						"Purchase Type Not Created Successfully");
			} else if (addsubject == "true1") {
				request.setAttribute("successmessagediv",
						"Purchase Type Updated Successfully");
			} else if (addsubject == "false1") {
				request.setAttribute("errorMessage",
						"Purchase Type Not Updated Successfully");
				
				
				
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.ADD_PURCHASE_DETAILS);

	}
	
	public ActionForward EditAddorModifyorDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		try {

			String id = request.getParameter("name");
			System.out.println(id);
			AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();

			vo.setPurchase_id(id);

			AddorModifyorDeleteVO status = new Inventory_Delegate().EditAddorModifyorDelete(vo);

			request.setAttribute("PURCHASE_TYPE_LIST", status);
			
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getDepartmentDetails();
			
			List<InventoryVO> list = new Inventory_Delegate().InventoryTypesList();

			request.setAttribute("InventoryTypesList", list);

					
			request.setAttribute("DepartmentDetails",DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return mapping.findForward(MessageConstants.ADD_PURCHASE_DETAILS);

	}
	
	public ActionForward DeleteAddorModifyorDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails  Starting");

		try {

			String id = request.getParameter("name");
			System.out.println(id);
			AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();

			vo.setPurchase_id(id);

			String status = new Inventory_Delegate().DeleteAddorModifyorDelete(vo);

			if (status == "true") {
				request.setAttribute("successmessagediv",
						"Purchase Type Deleted Successfully");
			} else if (status == "false") {
				request.setAttribute("errorMessage",
						"Purchase Type Not Deleted Successfully");
			}

			List<AddorModifyorDeleteVO> list = new Inventory_Delegate().AddorModifyorDeleteList();
			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return mapping.findForward(MessageConstants.ADD_or_MODIFY_or_DELETE_LIST);

	}

	public ActionForward AddorModifyorDeleteXLS (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Starting");

		try {
			System.out.println("Download EXCEL");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/PurchaseDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			
			Inventory_Delegate obj = new Inventory_Delegate();
			
			String SearchName = request.getParameter("searchname");
			
			if(SearchName != null){
				
				list=obj.SearchAddorModifyorDeleteList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
				
			}
			else{
				
			 list =new Inventory_Delegate().AddorModifyorDeleteList();
				
			}
			

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/PurchaseDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Purchase Details Excel Report" };
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
					request.getRealPath("Reports/PurchaseDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=PurchaseDetailsXLSReport.xls");
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
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Ending");
		return null;

	}

	public ActionForward AddorModifyorDeletePDF (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Starting");

		try {
			System.out.println("Download PDF");
			
			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			
			Inventory_Delegate obj = new Inventory_Delegate();
			
			String SearchName = request.getParameter("searchname");
			
			if(SearchName != null){
				
				list=obj.SearchAddorModifyorDeleteList(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
				
			}
			else{
				
			 list =new Inventory_Delegate().AddorModifyorDeleteList();
				
			}
			
			String sourceFileName = request
					.getRealPath("Reports/PurchaseDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Image", PropfilePath);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Purchase Details PDF Report" + ".pdf\"");

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
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Ending");
		return null;

	}
	
	
	public ActionForward CreateTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction:  AddSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			
	
			String tid=request.getParameter("transactionItemId");
			System.out.println("Action: TID: "+tid);
			InventoryTransactionForm form1 = (InventoryTransactionForm) form;
			System.out.println("Action class itetype: "+form1.getItem_type());
			System.out.println("Action class itemName: "+form1.getItem_type_id());
			if(tid == null || tid == "" || tid.equalsIgnoreCase(""))
			     form1.setTransaction_id(tid);
			
			String addsubject1 = new Inventory_Delegate().CreateTransactionDetails(form1,tid);
			System.out.println(addsubject1);
		
			
			
			/*List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();	
			Inventory_Delegate obj = new Inventory_Delegate();
			
			list = obj.InventoryList();
			
			request.setAttribute("list", list);	*/
			
			
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();
			Inventory_Delegate obj = new Inventory_Delegate();
			list = obj.InventoryTransactionList();
			request.setAttribute("TransactionDetailsList", list);
			
			

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);

	}
	
	
	public ActionForward deleteInventoryTransaction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction : DeleteInventoryTransaction  Starting");

		try {

			String id = request.getParameter("transactionItemId");
			System.out.println("Action Class:::   " +id);
			String status = new Inventory_Delegate().deleteInventoryTransaction(id);
			System.out.println("Status in Action Class: "+status);
			
			if (status == "true") {
				request.setAttribute("successmessagediv","Inventory Type Deleted Successfully");
			} else {
				request.setAttribute("errorMessage",
						"Inventory Type Not Deleted Successfully");
			}
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : editFuelDetails   Ending");

		return null;

	}
	
	// for return//
	
	public ActionForward returnInventoryItem(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id=request.getParameter("transactionItemId");
			
			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();	
			Inventory_Delegate obj = new Inventory_Delegate();
			
			list = obj.InventoryList();
			
			request.setAttribute("list", list);	
			
			List<AddorModifyorDeleteVO> lists = new ArrayList<AddorModifyorDeleteVO>();	
			Inventory_Delegate objs = new Inventory_Delegate();
			
			lists = objs.returnInventoryItem(id);
			request.setAttribute("lists", lists);	
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);
	}
	
	
	public ActionForward updateReturnItem(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction:  AddSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			
			String tid=request.getParameter("transactionItemId");
			System.out.println("Action: TID: "+tid);
			InventoryTransactionForm form1 = (InventoryTransactionForm) form;
			if(tid == null || tid == "" || tid.equalsIgnoreCase(""))
			form1.setTransaction_id(tid);
		
			String addsubject1 = new Inventory_Delegate().updateReturnItem(form1,tid);
			System.out.println(addsubject1);
			
			/*List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();	
			Inventory_Delegate obj = new Inventory_Delegate();
			
			list = obj.InventoryList();
			
			request.setAttribute("list", list);	*/
			
			
			
			
			
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();
			Inventory_Delegate obj = new Inventory_Delegate();
			list = obj.InventoryTransactionList();
			request.setAttribute("TransactionDetailsList", list);
			
			

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in InventoryMenuAction : AddSubject Ending");

		return mapping.findForward(MessageConstants.TRANSACTION_LIST);

	}


}



