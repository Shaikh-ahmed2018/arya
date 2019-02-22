package com.centris.campus.actions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.LeaveDetailsReportDaoImpl;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.LeaveDetailsReportBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.LeaveDetailsReportForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeaveReportXLS;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LeaveDetailsReportVo;
import com.centris.campus.vo.LeaveStatusListVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;



public class LeaveDetailsReportAction extends DispatchAction {


	private static Logger logger = Logger.getLogger(LeaveDetailsReportAction.class);
	
	public ActionForward getLeaveDetailsFilters(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportAction : getLeaveDetailsFilters Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_LEAVEREPORT);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
	      ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		  request.setAttribute("AccYearList", accYearList);
		  
		  List<HelperClassVo> locationList=HelperClass.getAllLocation();
			 request.setAttribute("locationList", locationList);
		  ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails();
		  request.setAttribute("DepartmentList", departmentList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportAction : getLeaveDetailsFilters Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVE_DETAILED_REPORT);
	}
	
	public ActionForward getLeaveDetailsReport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportAction : getLeaveDetailsReport Starting");
	
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			
			LeaveDetailsReportForm detailsReportForm=(LeaveDetailsReportForm)form;
		
			
		
		 ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		 List<HelperClassVo> locationList=HelperClass.getAllLocation();
		 request.setAttribute("locationList", locationList);
		  request.setAttribute("AccYearList", accYearList);
		  ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails();
		  request.setAttribute("DepartmentList", departmentList);
		  ArrayList<LeaveDetailsReportVo>	 arrayList=new LeaveDetailsReportBD().getLeaveDetails(detailsReportForm);
		  request.setAttribute("detailsList",arrayList);
		
}
		 catch (Exception exception) {
			 logger.error(exception.getMessage(),exception);
		     exception.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())+ " Control in  LeaveDetailsReportAction : getLeaveDetailsReport Ending");
			
			
			System.out.println(" Control in LeaveDetailsReportAction : getLeaveDetailsReport Ending");
	
		return mapping.findForward(MessageConstants.LEAVE_DETAILED_REPORT);
		
	}


	
	public ActionForward getTeachername(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportAction : getTeachername Starting");
		
		
		try {
			
			String department=request.getParameter("department");
			String teachingtype=request.getParameter("teachertype");
			
			ArrayList<TeacherVo> TeacherList=new LeaveDetailsReportBD().getTeachername(teachingtype,department);
		  
			JSONObject object=new JSONObject();
			object.put("TeacherList", TeacherList);
			
			response.getWriter().print(object);
		  
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveDetailsReportAction : getTeachername Ending");
		
		
		return null;
	}
	
	public ActionForward LeaveReportDownloadXLs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "\\MonthReports\\NewLeaveReport.xls";
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("content-disposition", "attachment; filename="+ "LeaveReport.xls");

		int octet;
		while ((octet = in.read()) != -1)
			out.write(octet);

		in.close();
		/*out.close();*/
		         
		
		return null;

	}


}
