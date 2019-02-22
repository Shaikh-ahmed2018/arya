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

import com.centris.campus.delegate.OnlineFeeReceiptBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.forms.OnlineFeeReceiptForn;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.OnlineFeeReceiptVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentAttendanceReportVo;

public class OnlineFeeReceiptAction extends DispatchAction{
	
	
	private static final Logger logger = Logger.getLogger(OnlineFeeReceiptAction.class);
	
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
	
	public ActionForward onlineFeeReceiptAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : onlineFeeReceiptAction Starting");
		
		
		
		
		try {
			

			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			
			String userid = HelperClass.getCurrentUserID(request);
			
			
			
			 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			  request.setAttribute("AccYearList", accYearList);
			  
			
			  List<ClassPojo> classlist =new StudentTransferCertifivateReportBD().getClassDetails();
			  
				request.setAttribute("classlist", classlist); 
				request.setAttribute("userid", userid); 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : onlineFeeReceiptAction Ending");
		
		return mapping.findForward(MessageConstants.onlinefeereceipt);
	}

	public ActionForward getParentStudentName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : getParentStudentName Starting");
		
		
		try {
			
			String sectionid = request.getParameter("sectionid").trim();
			String huserid = request.getParameter("huserid").trim();
			
			
			
			List<ParentVO> studentlist = new OnlineFeeReceiptBD().getParentChildBD(sectionid,huserid);
			
			
			
	
			
			JSONObject object=new JSONObject();
			 
			 object.put("studentlist", studentlist);
			 
			 response.getWriter().print(object);
			
			
			
		/*	List<ParentVO> studentlist = new StudentTransferCertifivateReportBD().getAllStudentNamesReportBD(sectionid);*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : getParentStudentName Ending");
		
		return null;
	}
	
	public ActionForward getOnlineFeeReceiptListAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : getOnlineFeeReceiptListAction Starting");
		
					
				try {
					
					OnlineFeeReceiptForn feeform = (OnlineFeeReceiptForn)form;
					
					OnlineFeeReceiptVo feevo = new OnlineFeeReceiptVo();
					
					feevo.setAccyearid(feeform.getAccyear());
					feevo.setClassid(feeform.getClassname());
					feevo.setSectionid(feeform.getSectionid());
					feevo.setStudentid(feeform.getStudentname());
					
					
					
					
					ArrayList<OnlineFeeReceiptVo> MasterList = new OnlineFeeReceiptBD().getSearchStudentFeeReceiptBD(feevo);
					
					  request.setAttribute("feeReceiptlist", MasterList);
					
					
					
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				
				
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeeReceiptAction : getOnlineFeeReceiptListAction Ending");
		
		return mapping.findForward(MessageConstants.onlinefeereceipt);
	}

	public ActionForward onlineFeeReceiptPDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.START_POINT);
logger.info(JDate.getTimeString(new Date())
		+ " Control in OnlineFeeReceiptAction : onlineFeeReceiptPDFReport Starting");





String userid = HelperClass.getCurrentUserID(request);



 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
  request.setAttribute("AccYearList", accYearList);
  

  List<ClassPojo> classlist =new StudentTransferCertifivateReportBD().getClassDetails();
  
	request.setAttribute("classlist", classlist); 
	request.setAttribute("userid", userid); 




	
	
	String accyear = request.getParameter("accyear");
	String sectionid = request.getParameter("sectionid");
	String classname = request.getParameter("classname");
	String studentname = request.getParameter("studentname");

	OnlineFeeReceiptVo feevo = new OnlineFeeReceiptVo();
	
	
	feevo.setAccyearid(accyear);
	feevo.setSectionid(sectionid);
	feevo.setClassid(classname);
	feevo.setStudentid(studentname);


	ArrayList<OnlineFeeReceiptVo> onlinefeereceiptlist = new OnlineFeeReceiptBD().getSearchStudentFeeReceiptBD(feevo);

	
	System.out.println(onlinefeereceiptlist.size());

	

	if(onlinefeereceiptlist.size()>0){
		
		try {
			
			
			String ImagePath = getServlet().getServletContext().getRealPath("")
					+ "\\images\\" + ImageName.trim();

			String MobIconPath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + MobIcon.trim();
			String EmailIconPath = getServlet().getServletContext()
					.getRealPath("") + "\\images\\" + EmailIcon.trim();
			
			
			
			Map mapdata = new HashMap();
			
			mapdata.put("conscPer", onlinefeereceiptlist.get(0).getConscper());
			mapdata.put("Admissionnum",onlinefeereceiptlist.get(0).getAddmissionnum());
			mapdata.put("StudentName",onlinefeereceiptlist.get(0).getStudentname());
			mapdata.put("classname",onlinefeereceiptlist.get(0).getClassname()+"-"+onlinefeereceiptlist.get(0).getSectionname());
			mapdata.put("sectionName",onlinefeereceiptlist.get(0).getSectionname());
			mapdata.put("termname", onlinefeereceiptlist.get(0).getTermname());
			mapdata.put("acadamic_year",onlinefeereceiptlist.get(0).getAccyearname());
			/*mapdata.put("paiddate",onlinefeereceiptlist.get(6).getPaiddate());*/
			

			
			mapdata.put("SchoolName", SchoolName);
			mapdata.put("AddressLine1", AddressLine1);
			mapdata.put("AddressLine2", AddressLine2);
			mapdata.put("AddressLine3", AddressLine3);
			mapdata.put("AddressLine4", "(Co-Educational)");
			mapdata.put("ImageName", ImagePath);
			mapdata.put("MobileIcon", MobIconPath);
			mapdata.put("EmailIcon", EmailIconPath);
			
			
			
			//System.out.println(onlinefeereceiptlist.get(5).getPaiddate());
			
		
			String filepath = request
					.getRealPath("Reports/Copy of FeeReceiptPDFReport.jrxml");

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					onlinefeereceiptlist);

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeReceiptPDFReport" + ".pdf\"");

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
				+ " Control in OnlineFeeReceiptAction : onlineFeeReceiptPDFReport Ending");
		
		return null;
		
	}

	else{
		
		request.setAttribute("Nothingfoundmsg", "Nothing found to Download");
		
		return mapping.findForward(MessageConstants.onlinefeereceipt);
		
		
	}






}
	
	
	
	

}
