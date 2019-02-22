package com.centris.campus.actions;

import java.sql.Timestamp;
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
import org.json.JSONObject;

import com.centris.campus.delegate.StaffReleivingReportBD;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public class StaffReleivingOrderReportAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	private static final Logger logger = Logger.getLogger(StaffReleivingOrderReportAction.class);
	
	
	public ActionForward staffReleivingOrderReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingOrderReport Starting");
		
		
		
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_RELEIVINGORDER);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			UserDetailVO vo = new UserDetailVO();
			
			List<UserDetailVO> userList =  new StaffReleivingReportBD().getUsersList();
			
			request.setAttribute("userList", userList);
			
			
			/*vo.setTeacherName(reform.getTeacherlist());
			vo.setTodate(reform.getResignationdate());
			vo.setDescription(reform.getDescription());
			
			
			
			String userID = HelperClass.getCurrentUserID(request);
			List<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		

			ArrayList<TimeTableVo> TeacherTimeTableList =  new TimeTableBD().getClassTimeTableList( accyearid,viewBy);	
			
			
			list=new ReleivingOrderBD().getTeacherListBD();
			
			request.setAttribute("teacherList", list);
			
			request.setAttribute("selecteddetails", vo);*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingOrderReport Ending");

		
		return  mapping.findForward(MessageConstants.releivingOrdeReport);
	}
	
	
	
	
	public ActionForward getTeachernameAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : getTeachernameAction Starting");
		
		
	try {
		
		
		String teachertype = "";
		teachertype = request.getParameter("teachertype");
		String usertype = request.getParameter("usertype");
		
		AllTeacherDetailsVo vo = new AllTeacherDetailsVo();
		vo.setTeacherType(teachertype);
		vo.setUsertype(usertype);
		
		System.out.println("teachertype:::"+teachertype);
		
		
		
		if(teachertype.equalsIgnoreCase("teaching")){
			
			
			List<AllTeacherDetailsVo> teachinglist = new StaffReleivingReportBD().getTeachingListBD(vo);
			
			
			 JSONObject object=new JSONObject();
			 
			 object.put("teachinglist", teachinglist);
			 
			 response.getWriter().print(object);	
		
		}
		else{
			
		
			List<AllTeacherDetailsVo> nonteachinglist = new StaffReleivingReportBD().getNonTeachingListBD(vo);
			
			
			
			 JSONObject object=new JSONObject();
			 
			 object.put("nonteachinglist", nonteachinglist);
			 
			 response.getWriter().print(object);
			
		}
		
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : getTeachernameAction Ending");

		
		return null;
	}
	
	
	public ActionForward staffReleivingPDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingPDFReport Starting");
		
		List<ReleivingOrderVo> teacherdetails = new ArrayList<ReleivingOrderVo>();
			
		
		try {
			
			
			
			
			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();
			
			String user = HelperClass.getCurrentUserID(request);
		
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			
			String usertype = request.getParameter("usertype");
			String teachername = request.getParameter("teachername");
			String teachertype = request.getParameter("teachertype");
			String releivedate = request.getParameter("releivedate");
			
			RelievingOrderPojo pojo = new RelievingOrderPojo();
			
			pojo.setUsertype(usertype);
			pojo.setTeachername(teachername);
			pojo.setTeachertype(teachertype);
			pojo.setReleivingdate(releivedate);
			
			teacherdetails =  new StaffReleivingReportBD().getReleivingDetailsBD(pojo);	
			
			
			Date currentdate = HelperClass.getCurrentSqlDate();
			
			String todaydate = "";
			
			SimpleDateFormat ddMMMyyFormat = new SimpleDateFormat("dd-MMM-yy");
			todaydate = ddMMMyyFormat.format(currentdate);


			
		
			Map mapdata = new HashMap();
			mapdata.put("SchoolName", schName);
			mapdata.put("AddressLine1", schAddLine1);
			mapdata.put("AffNo", "1930344");
			mapdata.put("Image", PropfilePath);
			mapdata.put("dateofRelieving", releivedate);
			mapdata.put("teachername", teachername);
			mapdata.put("currentdate", todaydate );

			
			String filepath = request
					.getRealPath("Reports/RelievingOrder.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					teacherdetails);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition",
					"outline; filename=\"" + "Relieving Order - "
							+ teachername + ".pdf\"");

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
				+ " Control in StaffReleivingOrderReportAction : staffReleivingPDFReport Ending");

		
		return null;
	}
	
	
	
}
