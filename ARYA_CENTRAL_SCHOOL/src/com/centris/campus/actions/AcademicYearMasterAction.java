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
import com.centris.campus.delegate.AcademicYearMasterBD;
import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.AddFeeVO;

public class AcademicYearMasterAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	private static final Logger logger = Logger
			.getLogger(AcademicYearMasterAction.class);

	public ActionForward addAcademicyear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : addAcademicyear  Starting");
		try {
			
			String title ="Add New Academic Year Details";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACADEMICYEARDETAILS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : addAcademicyear  Ending");

		return mapping.findForward(MessageConstants.ADDACADEMIC_YEAR);

	}

	public ActionForward createAcademicYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : createAcademicYear Starting");
		try {

			AcademicYearPojo ACY_pojo = new AcademicYearPojo();

			ACY_pojo.setAcadamic_name(request.getParameter("accyear"));
			ACY_pojo.setStartDate(request.getParameter("startdate"));
			ACY_pojo.setEndDate(request.getParameter("enddate"));
			ACY_pojo.setDescription(request.getParameter("description"));

			if ("NULL".equalsIgnoreCase(request.getParameter("accid"))) {
				ACY_pojo.setAcadamic_id(IDGenerator
						.getPrimaryKeyID("campus_acadamicyear"));
				ACY_pojo.setStatus("NULL");
			} else {
				ACY_pojo.setAcadamic_id(request.getParameter("accid"));
			}

			ACY_pojo.setCreateuser(HelperClass.getCurrentUserID(request));

			String status = new AcademicYearMasterBD()
					.createAcademicYear(ACY_pojo);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : createAcademicYear  Ending");
		return null;

	}

	public ActionForward deleteAcademicYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : deleteAcademicYear  Starting");
		try {

			boolean status = new AcademicYearMasterBD()
					.deleteAcademicYear(request.getParameter("Code"));
			
			
			
			System.out.println("Acc Code "+request.getParameter("Code"));
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : deleteAcademicYear  Ending");
		return null;

	}

	public ActionForward editAcademicYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : editAcademicYear  Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACADEMICYEARDETAILS);
			
			String title ="Modify Academic Year Details";
			request.setAttribute("title", title);
			ArrayList<AcademicYearVO> editacademicyearlist = new AcademicYearMasterBD()
					.editAcademicYear(request.getParameter("Acy_Code"));
			request.setAttribute("editacademicyear",
					editacademicyearlist.get(0));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : editAcademicYear  Ending");
		return mapping.findForward(MessageConstants.ADDACADEMIC_YEAR);

	}

	public ActionForward accyearNameCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : accyearNameCheck  Starting");
		try {
			AcademicYearPojo ACY_pojo = new AcademicYearPojo();

			ACY_pojo.setAcadamic_name(request.getParameter("accyearname"));
			ACY_pojo.setStartDate(request.getParameter("startdate"));
			ACY_pojo.setEndDate(request.getParameter("enddate"));
			ACY_pojo.setAcadamic_id(request.getParameter("accid"));

			
			String status = new AcademicYearMasterBD().accyearNameCheck(ACY_pojo);
			
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
				+ " Control in AcademicYearMasterAction : accyearNameCheck  Ending");
		return null;

	}

	public ActionForward getAccYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserAction : getAcademicYear Starting");

		List<AcademicYearVO> accYrVo = new ArrayList<AcademicYearVO>();

		AcademicYearMasterBD userBD = new AcademicYearMasterBD();
		accYrVo = userBD.getAccYear();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accYrVo", accYrVo);
		response.getWriter().print(jsonObject);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserAction : getAcademicYear Ending");
		return null;
	}

	public ActionForward getAcademicYearList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : getAcademicYearList  Starting");
		try {
			ArrayList<AcademicYearPojo> acdemic_year_list = new ArrayList<AcademicYearPojo>();

			acdemic_year_list = new AcademicYearMasterBD()
					.getAcademicYearList();

			JSONObject object = new JSONObject();
			object.accumulate("acdemic_year_list", acdemic_year_list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : getAcademicYearList  Ending");
		return null;

	}

	public ActionForward AcademicYearPathDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AcademicYearMasterAction : AcademicYearPathDetailsXLS  Starting");
		
		try {
	
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/AcademicYearDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
		
			ArrayList<AcademicYearVO> editacademicyearlist = null;

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm != null) {

				editacademicyearlist = new AcademicYearMasterBD()
						.searchAcademicYearDetails(searchTerm);
				
				
				request.setAttribute("searchnamelist", searchTerm);
				
			} else {
				editacademicyearlist = new AcademicYearMasterBD()
						.getAcademicYearDetails();

			}
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					editacademicyearlist);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/AcademicYearDetailsXLSReport.xls"));
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
					request.getRealPath("Reports/AcademicYearDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=AcademicYearDetailsXLSReport.xls");
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
				+ " Control in AcademicYearMasterAction : AcademicYearPathDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward AcademicYearPathDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AcademicYearMasterAction : AcademicYearPathDetailsPDFReport  Starting");

			try {

				ArrayList<AcademicYearVO> editacademicyearlist = null;

				String searchTerm = request.getParameter("searchTerm");

				if (searchTerm != null) {

					editacademicyearlist = new AcademicYearMasterBD()
							.searchAcademicYearDetails(searchTerm);
					
					
					request.setAttribute("searchnamelist", searchTerm);
					
				} else {
					editacademicyearlist = new AcademicYearMasterBD()
							.getAcademicYearDetails();

				}
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/AcademicYearDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						editacademicyearlist);

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
						+ "AcademicYearDetailsPDF - " + ".pdf\"");

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
					+ " Control in AcademicYearMasterAction : AcademicYearPathDetailsPDFReport  Ending");
			return null;

		}
	
}
