package com.centris.campus.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;

public class StudentBonafiedReportCertificateReportAction extends
		DispatchAction {

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static final Logger logger = Logger
			.getLogger(StaffReleivingOrderReportAction.class);

	public ActionForward studentBonafiedCertificateReportAction(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentBonafiedReportCertificateReportAction : studentBonafiedCertificateReportAction Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_BONAFIDECERTIFICATE);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD()
					.getClassDetails();

			request.setAttribute("classlist", classlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentBonafiedReportCertificateReportAction :  Ending");

		return mapping.findForward(MessageConstants.studentBonafiedCertificate);
	}

	public ActionForward studentBonafiedCertificatePDFReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentBonafiedReportCertificateReportAction : studentBonafiedCertificatePDFReport Starting");

		try {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentBonafiedReportCertificateReportAction : studentBonafiedCertificatePDFReport Starting");

			try {

				String PropfilePath = getServlet().getServletContext()
						.getRealPath("") + "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				String accyear = request.getParameter("accyear");
				String sectionid = request.getParameter("sectionid");
				String classname = request.getParameter("classname");
				String studentname = request.getParameter("studentname");
				String description = request.getParameter("description");

				Date currentdate = HelperClass.getCurrentSqlDate();

				String todaydate = "";

				SimpleDateFormat ddMMMyyFormat = new SimpleDateFormat(
						"dd-MMM-yy");
				todaydate = ddMMMyyFormat.format(currentdate);

				Map mapdata = new HashMap();
				mapdata.put("schoolname", schName);
				mapdata.put("AddressLine1", schAddLine1);
				mapdata.put("AffNo", "1930344");
				mapdata.put("Image", PropfilePath);
				mapdata.put("studentname", studentname);
				mapdata.put("todaydate", todaydate);

				String filepath = request.getRealPath("Reports/Bonafide.jrxml");

				JasperDesign design = JRXmlLoader.load(filepath);
				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						mapdata, JDBCConnection.getConnection());
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition",
						"outline; filename=\"" + "Bonafide Certificate"
								+ ".pdf\"");

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
					+ " Control in StudentBonafiedReportCertificateReportAction : studentBonafiedCertificatePDFReport Ending");

			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentBonafiedReportCertificateReportAction : studentBonafiedCertificatePDFReport Ending");

		return null;
	}

}
