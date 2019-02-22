package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.vo.HolidayMasterVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.daoImpl.HolidayMasterDAOImpl;
import com.centris.campus.delegate.HolidayMasterBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;

public class HolidayMaster extends DispatchAction {
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static final Logger logger = Logger
			.getLogger(HolidayMaster.class);

	private static String ImageName = res.getString("ImageName");
	
	public ActionForward addMultiHolidayDetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :addHolidayDetail: Starting");
		try {
			System.out.println("----------------");
			HolidayMasterForm formObj=new HolidayMasterForm();
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usercode=userDetailVO.getUserId();
			
			String[] date = request.getParameterValues("holiday_date[]");
			String[] weekday = request.getParameterValues("weekday[]");
			String[] holiday = request.getParameterValues("holiday[]");
			String[] holiday_type =request.getParameterValues("holidaytype[]");
			
		    System.out.println("holiday_type.length"+holiday_type.length);
			String location = request.getParameter("location");
			String year = request.getParameter("year");
			
			System.out.println(year);
			
			formObj.setDate(date);
			formObj.setWeekday(weekday);
			formObj.setHoliday(holiday);
			formObj.setLocation(location);
			formObj.setYear(year);
			formObj.setHoliday_type(holiday_type);
			
			String result = new HolidayMasterBD().addMultipleHoliday(formObj,usercode);

				System.out.println(result);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", result);
				response.getWriter().print(jsonObject);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :addHolidayDetail: Ending");
		
		return null;

	}

	public ActionForward editHolidayMaster(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMaster :editHolidayMaster: Starting");

		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);
		
			String deptId = request.getParameter("deptId");

			HolidayMasterVo holidayMasterVO = new HolidayMasterBD().editHolidayDetail(deptId);
	    request.setAttribute("holidayDetails", holidayMasterVO);
	    request.setAttribute("locationid", holidayMasterVO.getLocId());
	    request.setAttribute("HolidayName", holidayMasterVO.getHolidaysName());
	    request.setAttribute("HolidayType", holidayMasterVO.getHolidayType());
	    request.setAttribute("Year", holidayMasterVO.getYear());
	    request.setAttribute("WeekDays", holidayMasterVO.getWeekDay());
	    request.setAttribute("date", holidayMasterVO.getDate());
	    request.setAttribute("locName", holidayMasterVO.getLocName());
	    
	    System.out.println(holidayMasterVO.getHolId());
	    
	    
	    System.out.println(holidayMasterVO.getLocId());
	    
	    UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String groupcode=userDetailVO.getUserId();
	    
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();	
		request.setAttribute("locationList", locationList);	
		  
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
				.getAccYears();  
		request.setAttribute("AccYearList", accYearList);
	
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	    
	    
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :editHolidayMaster: Ending");
		
		return mapping.findForward(MessageConstants.HOLIDAY_UPDATE);

	}
	
	public ActionForward addHolidayDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :addHolidayDetail: Starting");
		try {

			System.out.println("Hello inside addHoliday");
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usercode=userDetailVO.getUserId();
			
			String date = request.getParameter("date");
			String weekday = request.getParameter("weekdays");
			String holiday = request.getParameter("holidayname");
			String location = request.getParameter("location");
			String year = request.getParameter("year");
			String holidaytype = request.getParameter("holidaytype");
			String holiId = request.getParameter("holiid");
		    HolidayMasterPojo hpojo=new HolidayMasterPojo();
			hpojo.setCreateddate(HelperClass.getCurrentTimestamp());
			hpojo.setDate(date);
			hpojo.setLocation(location);
			hpojo.setHoliday(holiday);
			hpojo.setWeekday(weekday);
			hpojo.setYear(year);
			hpojo.setCreatedby(usercode);
			hpojo.setHolidayType(holidaytype);
			hpojo.setHolidayid(holiId);
			String result = new HolidayMasterBD().addSingleHoliday(hpojo);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", result);
				response.getWriter().print(jsonObject);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :addHolidayDetail: Ending");
		
		return null;

	}
	
	public ActionForward deleteHolidayData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception

	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :deleteHolidayData: Starting");
		
		
		try {
			
			System.out.println("Inside delete");
			/*String holidaydate = request.getParameter("holidaydate");
			String deptid = request.getParameter("deptId");*/
			String list[]=request.getParameterValues("studentIdlist[]");
			

			
			Boolean result = new  HolidayMasterBD().deleteSingleHoliday(list);
			
			
			/*Boolean result = new HolidayMasterBD().deleteSingleHoliday(holidaydate,deptid);*/
			String message=null;
			if(result){
				
				 message="Delete Record progressing...";
			
			}else{
				
				message="Failed to Delete Holiday...";
				
			}
			
			JSONObject object=new JSONObject();
			object.put("status", message);
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :deleteHolidayData: Ending");
		
		return null;

	}
	
	public ActionForward dateValidate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Starting");
		
		try {
			String dateval = request.getParameter("date");
			String location=request.getParameter("location");
			String accYear=request.getParameter("accYear");
		System.out.println("date"+dateval);
			String result = new HolidayMasterBD().dateValidate(dateval,location,accYear);
			
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateVal", result);
				response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Ending");
		
		return null;
	}
	
	public ActionForward downloadHoliday(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Starting");
		
		
		try {
			 String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			 String schoolId = (String)request.getSession(false).getAttribute("current_schoolLocation");

			/*LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			
			String SearchName = request.getParameter("searchTerm");
			
			if(SearchName != null){
				
				list =obj.searchLocationDetails(SearchName);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				list = obj.getLocationDetails();
				
			}*/
			
			HolidayMasterBD obj = new HolidayMasterBD();
			 ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();
			 list = obj.getHolidayDetails(academic_year,schoolId);
			 
			
			
			String sourceFileName = request
					.getRealPath("Reports/HolidayDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

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
					+ "LocationDetailsPDFReport - " + ".pdf\"");

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
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Ending");
	
		return null;

	}
	
	public ActionForward dateValidatation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Starting");
		
		try {
			String dateval = request.getParameter("date");
			String location=request.getParameter("location");
			String accYear=request.getParameter("accYear");
			System.out.println("date"+dateval);
			String result = new HolidayMasterBD().dateValidate(dateval,location,accYear);
			
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateVal", result);
				response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Ending");
		
		return null;
	}
	
	
	public ActionForward HolidayNameValidate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Starting");
		
		try {
			String dateval = request.getParameter("holiday");
			String location=request.getParameter("location");
			String accyear=request.getParameter("academicYear");
		System.out.println("date"+dateval);
			String result = new HolidayMasterDAOImpl().HolidayNameValidate(dateval,location,accyear);
			
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateVal", result);
				response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterAction :dateValidate: Ending");
		
		return null;
	}
	public ActionForward holidaymaster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : holidaymaster Starting");
		try {

			HolidayMasterBD obj = new HolidayMasterBD();
			ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();

			String locationId = request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all") || locationId.equalsIgnoreCase("") || locationId==null){
				locationId="%%";
			}
			String academic_year= request.getParameter("accyear");
				list = obj.getHolidayDetails(academic_year,locationId);
			
			request.setAttribute("HolidayList", list);
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
