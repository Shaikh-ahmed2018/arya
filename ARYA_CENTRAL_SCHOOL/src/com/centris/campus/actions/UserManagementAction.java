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
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.delegate.UserManagementBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementAction extends DispatchAction{
	
	
	private static final Logger logger = Logger.getLogger(UserManagementAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward changePasswordHome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePasswordHome Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PASSWORDMAINTAINANCE);
			
			String userId = request.getParameter("userId");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserId(userId);
			
			UserRecordVO userrecordVo = new UserManagementBD().getUserDeatils(userManagementPojo);
			request.setAttribute("userrecordVo", userrecordVo);
		
			request.setAttribute("UserId", userId);
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePasswordHome Ending");

		return mapping.findForward(MessageConstants.CHANGE_PASSWORD);

	}
	
	public ActionForward changePassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePassword Starting");
		try {

			
			String userId=request.getParameter("selectUser");
			String passwrd=request.getParameter("confirmpasswrd");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserId(userId);
			userManagementPojo.setPasswrd(passwrd);
			
			String result = new UserManagementBD().changePassword(userManagementPojo);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", result);
			response.getWriter().print(jsonObject);
			
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePassword Ending");

		return null;

	}
	
	public ActionForward blockUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Starting");
		try {
			
			String userId =  request.getParameter("userId");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserId(userId);
			
			String result =  new UserManagementBD().blockUser(userManagementPojo);
				
			JSONObject jsonObject = new JSONObject();
			if(MessageConstants.TRUE.equalsIgnoreCase(result)){
				jsonObject.put("status", MessageConstants.BLOCK_USER_SUCCESS);
			}else{
				jsonObject.put("status", MessageConstants.BLOCK_USER_FAILURE);
				
			}
			
			
			response.getWriter().print(jsonObject);
			
			
			
			List<UserRecordVO>	 userRecords = new UserManagementBD().getUserRecordsBD();
			request.setAttribute("userRecords", userRecords);
				
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Ending");

		return null;

	}
	
	public ActionForward downloaduserManagementXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction : downloaduserManagementXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/userdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

			String searchterm = request.getParameter("searchterm");
			String type = request.getParameter("type");

			if (searchterm != "" && type != "") {

				UserManagementPojo userManagementPojo = new UserManagementPojo();
				userManagementPojo.setType(type);
				userManagementPojo.setSearchtext(searchterm);

				userRecords = new UserManagementBD()
						.getSearchUserDetailsBD(userManagementPojo);
				
			} else {

				userRecords = new UserManagementBD().getUserRecordsBD();
				
			}
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					userRecords);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/userdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "User Details Excel Report" };
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
					request.getRealPath("Reports/userdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=userdetailsxls.xls");
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
				+ " Control in UserManagementAction : downloaduserManagementXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloaduserManagementPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UserManagementAction : downloaduserManagementPDF  Starting");

			try {

				

				List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

				String searchterm = request.getParameter("searchterm");
				String type = request.getParameter("type");

				
				if (searchterm != "" && type != "") {

					UserManagementPojo userManagementPojo = new UserManagementPojo();
					userManagementPojo.setType(type);
					userManagementPojo.setSearchtext(searchterm);

					userRecords = new UserManagementBD()
							.getSearchUserDetailsBD(userManagementPojo);
				

				} else {

					userRecords = new UserManagementBD().getUserRecordsBD();
					
				}
				
	
				String sourceFileName = request
						.getRealPath("Reports/userDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						userRecords);

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
						+ "userDetailsPDF.jrxml - " + ".pdf\"");

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
					+ " Control in UserManagementAction : downloaduserManagementPDF  Ending");
			return null;

		}

	
}
