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

import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.RoleMasterBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

public class RoleMasterAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(RoleMasterAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	public synchronized ActionForward getRoleEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : getRoleEntry Starting");

		try {
			
			String title = "Add New Role Details";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ROLEMASTER);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : getRoleEntry Ending");

		return mapping.findForward(MessageConstants.ROLE_ENTRY);
	}


	public synchronized ActionForward Add(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : Add Starting");

		try {
			
			JSONObject jsonObject = new JSONObject();

			String rolename=request.getParameter("roleName");
			String roledes=request.getParameter("roleDescription");
			String isAdministrator = request.getParameter("isadministrator");
			
			String usercode = HelperClass.getCurrentUserID(request);
			
			RoleMasterPojo pojo=new RoleMasterPojo();

			pojo.setRoleName(rolename);
			pojo.setRoleDescription(roledes);
			pojo.setCreatedBy(usercode);
			pojo.setIsadministrator(isAdministrator);
			

			RoleMasterBD roleMasterBD = new RoleMasterBD();

			String successMessage = roleMasterBD.addRole(pojo);
			
			jsonObject.put("status", successMessage);
			response.getWriter().println(jsonObject);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : Add Ending");

		return null;
	}

	public ActionForward deleteRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : deleteRole Starting");
		try {
			
			JSONObject jsonObject = new JSONObject();
			RoleMasterPojo masterPojo = new RoleMasterPojo();
			masterPojo.setRoleCode(request.getParameter("role_Code"));

			
			
			RoleMasterBD roleMasterBD = new RoleMasterBD();
			String  deleteRole= roleMasterBD.deleteRole(masterPojo);
			
			jsonObject.put("status", deleteRole);

			
			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : deleteRole Ending");

		return null;
	}

	public ActionForward editRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : editRole Starting");
		try {
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ROLEMASTER);
			
			String title = "Modify Role Details";
			request.setAttribute("title", title);
			String roleCodeId = request.getParameter("ROleCode");
			
			RoleMasterPojo roleDetails =new RoleMasterBD().updateRole(roleCodeId);
			
			request.setAttribute("RoleDetails", roleDetails);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : editRole Ending");
		return mapping.findForward(MessageConstants.ROLE_ENTRY);
	}

	public synchronized ActionForward validateRoleName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : validateRoleName: Starting");

		try {
			String roleName = (String) request.getParameter("roleName");
			
			boolean roleName_Available = new RoleMasterBD()
					.validateRoleName(roleName);

			JSONObject object = new JSONObject();
			object.put("roleName_Available", roleName_Available);

			response.getWriter().print(roleName_Available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction::validateRoleName: Ending");
		return null;
	}

	public ActionForward validateRoleNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction: validateRoleNameUpdate Starting");

		try {
			String roleName = (String) request.getParameter("roleName");
			String roleid = (String) request.getParameter("roleid");

			boolean roleName_Available = new RoleMasterBD()
					.validateRoleNameUpdate(roleName, roleid);

			JSONObject object = new JSONObject();
			object.put("roleName_Available", roleName_Available);

			response.getWriter().print(roleName_Available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction: validateRoleNameUpdate Ending");
		return null;
	}

	public synchronized ActionForward Update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction: Update Starting");
		
		try {
			JSONObject jsonObject = new JSONObject();
			
			String roleCode=request.getParameter("roleCode");
			String rolename=request.getParameter("roleName");
			String roledes=request.getParameter("roleDescription");
			String isAdministrator = request.getParameter("isadministrator");

			
			String usercode = HelperClass.getCurrentUserID(request);
			
			RoleMasterPojo pojo=new RoleMasterPojo();

			pojo.setRoleName(rolename);
			pojo.setRoleDescription(roledes);
			pojo.setRoleCode(roleCode);
			pojo.setCreatedBy(usercode);
			pojo.setIsadministrator(isAdministrator);

			RoleMasterBD roleMasterBD = new RoleMasterBD();

			String successMessage = roleMasterBD.updateRole(pojo);
		

			jsonObject.put("status", successMessage);
			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction: Update Ending");
		
		return null;
	}
	
	
	public ActionForward downloadRoleDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterAction : downloadRoleDetailsXLS  Starting");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/RoleDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*List<RoleMasterPojo> roleMasterList = new ArrayList<RoleMasterPojo>();
			roleMasterList = (List<RoleMasterPojo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");
		
*/
			
			
			
			List<RoleMasterPojo> roleMasterList = new ArrayList<RoleMasterPojo>();

			String searchTerm = request.getParameter("searchTerm");
			RoleMasterBD masterBD = new RoleMasterBD();

			if (searchTerm != null) {

				roleMasterList = masterBD.searchRole(searchTerm);

				request.setAttribute("searchTerm", searchTerm);
				
				request.setAttribute("searchnamelist", searchTerm);
				

			} else {

				roleMasterList = masterBD.getRoles();

			}
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					roleMasterList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/RoleDetailsXLSReport.xls"));
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
					request.getRealPath("Reports/RoleDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=RoleDetailsXLSReport.xls");
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
				+ " Control in RoleMasterAction : downloadRoleDetailsXLS  Ending");
		return null;

	}
	public ActionForward RoleDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassAction : classPathDetailsPDFReport  Starting");

						try {

					List<RoleMasterPojo> roleMasterList = new ArrayList<RoleMasterPojo>();

					String searchTerm = request.getParameter("searchTerm");
					RoleMasterBD masterBD = new RoleMasterBD();

					if (searchTerm != null) {

						roleMasterList = masterBD.searchRole(searchTerm);

						request.setAttribute("searchTerm", searchTerm);
						
						request.setAttribute("searchnamelist", searchTerm);
						

					} else {

						roleMasterList = masterBD.getRoles();

					}
					
					
				
					String sourceFileName = request
							.getRealPath("Reports/RoleDetailsPDF.jrxml");
					JasperDesign design = JRXmlLoader.load(sourceFileName);

					JasperReport jasperreport = JasperCompileManager
							.compileReport(design);

					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
							roleMasterList);

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
							+ "RoleDetailsPDF - " + ".pdf\"");

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
			
	
}
