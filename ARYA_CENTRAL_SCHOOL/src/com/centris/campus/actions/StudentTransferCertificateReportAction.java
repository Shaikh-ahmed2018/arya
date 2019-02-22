package com.centris.campus.actions;

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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StreamDetailsVO;

public class StudentTransferCertificateReportAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(StudentTransferCertificateReportAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward studentTransferCertifivateReportAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : studentAttendanceReport Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			  
			
			  List<ClassPojo> classlist =new StudentTransferCertifivateReportBD().getClassDetails();
			  
				request.setAttribute("classlist", classlist);  
			  
			  
			  
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : studentAttendanceReport Ending");
		
		return mapping.findForward(MessageConstants.studentTransferCertificate);
	}
	
	
	
	public ActionForward getSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : getSectionList Starting");
		
		try {
			
			String classname=request.getParameter("classname");
			String location=request.getParameter("location");
			
			
			List<SectionPojo> seclist=new StudentTransferCertifivateReportBD().getSectionList(classname,location);
			
			JSONObject object=new JSONObject();
			 
			 object.put("seclist", seclist);
			 
			 response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : getSectionList Ending");
		
		return null;	
	}
	
	
	
	
	public ActionForward getAllStudentNames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : getAllStudentNames Starting");
		
		
		try {
			
			String sectionid = request.getParameter("sectionid").trim();
			
			System.out.println("sectionid:::"+sectionid);
			
			
			
			List<ParentVO> studentlist = new StudentTransferCertifivateReportBD().getAllStudentNamesReportBD(sectionid);
			
			
			
			JSONObject object=new JSONObject();
			 
			 object.put("studentlist", studentlist);
			 
			 response.getWriter().print(object);
		} 
		
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : getAllStudentNames Ending");
		
		return null;	
	}
	
	
	
	
	public ActionForward studentTransferCertificatePDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentTransferCertificateReportAction : studentTransferCertificatePDFReport Starting");
		
		
	
		try {
			
			
			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();
			
			String user = HelperClass.getCurrentUserID(request);
		
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			
			String accyear = request.getParameter("accyear");
			String sectionid = request.getParameter("sectionid");
			String reason = request.getParameter("reason");
			String classname = request.getParameter("classname");
			String studentname = request.getParameter("studentname");
			String description = request.getParameter("description");
			
			
			
			
			
			Map mapdata = new HashMap();
			mapdata.put("SchoolName", schName);
			mapdata.put("AddressLine1", schAddLine1);
			mapdata.put("AffNo", "1930344");
			mapdata.put("Image", PropfilePath);
			mapdata.put("studentname", studentname);
			mapdata.put("reason", reason);
			
			
			String filepath = request
					.getRealPath("Reports/TransferCentificate.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection());
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition",
					"outline; filename=\"" + "Transfer Certificate"
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
				+ " Control in StudentTransferCertificateReportAction : studentTransferCertificatePDFReport Ending");
		
		return null;	
	}
	
	
	

}
