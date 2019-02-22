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

import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
public class AddDesignationAction extends DispatchAction 

{
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	
	
	private static final Logger logger = Logger
			.getLogger(AddDesignationAction.class);
	
	
//After checked check box to append checked values in that fields.....
	
	
	
	public  ActionForward Edit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Starting");
		try {
			
			String title="Modify Designation";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNATIONDETAILS);
			
			AddDesignation aform = new AddDesignation();

			
			aform.setCreatedby(HelperClass.getCurrentUserID(request));
		
		    String name=request.getParameter("name");
		    
		    aform.setDesignationid(name);	
		    
		    
		  		    
		    String createUser = HelperClass.getCurrentUserID(request);
		
		 			
		    AddDesignation delegate=new AddDesignationBD().EditDesignationDetails(aform);
	
            request.setAttribute("DesignationLIst", delegate);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

		/*if (MessageConstants.PRINCIPLE_CHAR_SEQUENCE.contains(username
				.substring(0, 3))) {
return null;
			return mapping
					.findForward(MessageConstants.PRINCIPLE_DESIGNATION_MASTER);
		} else {
			return null;
			return mapping.findForward(MessageConstants.DESIGNATION_MASTER);
		}*/
		
		
		
		return mapping.findForward(MessageConstants.ADD_DESIGNATION);


	}
	
	//Delete Designation
	
	public  ActionForward Delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Starting");
		try {
			
			System.out.println("Delete Action Is Working");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			String designation_code[] = request.getParameter("designation_list").split(",");
			
			System.out.println("event_code::::"+designation_code.length);
		
		  		    
		    

			String result = new AddDesignationBD().deleteDesignationDetails(designation_code);
			
			System.out.println("status:::"+result);
			
			
		
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);

	
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

		/*if (MessageConstants.PRINCIPLE_CHAR_SEQUENCE.contains(username
				.substring(0, 3))) {
return null;
			return mapping
					.findForward(MessageConstants.PRINCIPLE_DESIGNATION_MASTER);
		} else {
			return null;
			return mapping.findForward(MessageConstants.DESIGNATION_MASTER);
		}*/
		
		
		
		return null;


	}
	

	public  ActionForward SearchDesignation(ActionMapping mapping,
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

				ArrayList<AddDesignationVO> list = new AddDesignationBD()
						.getSearchDetails(searchTextVal);
				
				for(AddDesignationVO vo:list)
					
				{
					System.out.println("Response Msg is");
					System.out.println(vo.getDesgname());
					System.out.println(vo.getDesgdes());
					System.out.println(vo.getDesgid());
				}
				
				request.setAttribute("DesignationDetails", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

	
				
		return mapping.findForward(MessageConstants.DESIGNATION_LIST);

	}
	
	
	public ActionForward getnamecount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
			
		{
			
		System.out.println("getnamecount  Action class working");
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : getnamecount Starting");
		
		boolean status = false;
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		
		
		try {
			
			AddDesignationVO vo=new AddDesignationVO();
			vo.setDesgid(id);
			vo.setDesgname(name);
			
			AddDesignationBD delegate= new AddDesignationBD();
			
			status=delegate.getnamecount(vo);

			
			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("message", status);
			
			response.getWriter().println(jsonObject);
					
			
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : getnamecount Ending");

		return null;
		
		}
	public ActionForward downloadDesignationDetailsXLS(ActionMapping mapping,
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
					.getRealPath("Reports/DesignationDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			List<AddDesignationVO> MasterList = new ArrayList<AddDesignationVO>();
			
			MasterList = (List<AddDesignationVO>) request.getSession(false).getAttribute("EXcel");
		

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					MasterList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/DesignationDetailsXLS.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Role Details Excel Report" };
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
					request.getRealPath("Reports/DesignationDetailsXLS.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=DesignationDetailsXLS.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			} 
			
		}
		catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsXLS  Ending");
		return null;

	}
		
	
	public ActionForward downloadDesignationDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : downloadDesignationDetailsPDF  Starting");
	
	
		try {
			
			
		/*	AddDesignationVO vo = new AddDesignationVO();

			vo.setDesgname(request.getParameter("searchvalue"));

			ArrayList<AddDesignationVO> list = new AddDesignationBD()
					.DesignationDetails(vo);*/

			System.out.println("PDF Download");
			List<AddDesignationVO> MasterList = new ArrayList<AddDesignationVO>();
			
			MasterList = (List<AddDesignationVO>) request.getSession(false).getAttribute("EXcel");
			
			String sourceFileName = request
					.getRealPath("Reports/DesignatinoPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					MasterList);

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
					+ "DesignatinoPDFReport - " + ".pdf\"");

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
	
	
	
	
	
	
	
	
	
	
	
	
}
