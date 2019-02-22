package com.centris.campus.actions;

import java.text.DateFormat;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StaffServiceReportBD;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PayCertificateReportVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;

public class StaffPayReportAction extends DispatchAction{
	
	private static final Logger logger = Logger.getLogger(StaffPayReportAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String AddressLine1 = res.getString("AddressLine1");
	private static String AddressLine2 = res.getString("AddressLine2");
	private static String AddressLine3 = res.getString("AddressLine3");

	private static String SchoolName = res.getString("SchoolName");
	private static String ImageName = res.getString("ImageName");
	private static String MobIcon = res.getString("MobIcon");
	private static String EmailIcon = res.getString("EmailIcon");
	private static String SchoolName1 = res.getString("SchoolName1");
	
	public ActionForward staffPayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayReportAction : staffPayReport Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_PAYCERTIFICATE);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			
			
			
			 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			  
		
				
			List <TeacherVo> teacherlist =HelperClass.getAllTeacherList(); 
				request.setAttribute("teacherlist", teacherlist);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayReportAction : staffPayReport Ending");
		
		return  mapping.findForward(MessageConstants.payCertificate);
	}
	
	
	public ActionForward staffPayCertificatePDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffPayReportAction : staffPayCertificatePDFReport Starting");
		
		 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		  request.setAttribute("AccYearList", accYearList);
		  
		  List <TeacherVo> teacherlist =HelperClass.getAllTeacherList(); 
			request.setAttribute("teacherlist", teacherlist);
		
			String accyear = request.getParameter("accyear");
			String month = request.getParameter("monthid");
			String teachername = request.getParameter("teachername");
			
			ArrayList<PayCertificateReportVO> teacherdetails = new StaffServiceReportBD().getTeacherPayDetails(accyear,month,teachername); 
			
				if(teacherdetails.size()>0){
					try {
						String ImagePath = getServlet().getServletContext().getRealPath("")
							+ "\\images\\" + ImageName.trim();

					String MobIconPath = getServlet().getServletContext().getRealPath(
							"")
							+ "\\images\\" + MobIcon.trim();
					String EmailIconPath = getServlet().getServletContext()
							.getRealPath("") + "\\images\\" + EmailIcon.trim();
					
					
					 Date todaysDate = new Date();
				       DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				       String testDateString = df.format(todaysDate);
					
					Map mapdata = new HashMap();

					mapdata.put("SchoolName", SchoolName1);
					mapdata.put("AddressLine1", AddressLine1);
					mapdata.put("AddressLine2", AddressLine2);
					mapdata.put("AddressLine3", AddressLine3);
					mapdata.put("AddressLine4", "(Co-Educational)");
					mapdata.put("Image", ImagePath);
					mapdata.put("MobileIcon", MobIconPath);
					mapdata.put("EmailIcon", EmailIconPath);
					mapdata.put("currentdate", testDateString);
					String filepath = request
							.getRealPath("Reports/PayCertificate.jrxml");

					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
							teacherdetails);

					JasperDesign design = JRXmlLoader.load(filepath);
					JasperReport jasperreport = JasperCompileManager
							.compileReport(design);
					byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
							mapdata, beanColDataSource);
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
					response.setHeader("Content-Disposition", "outline; filename=\""
							+ "Pay Certificate" + ".pdf\"");

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
						+ " Control in ReportsAction : teacherPayCertificate Ending");

				return null;
				
				}	
			
			else{
				request.setAttribute("Nothingfoundmsg", "Nothing found to display");
				return mapping.findForward(MessageConstants.payCertificate);
			}
	}
	
}
