package com.centris.campus.actions;

import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.Request;

import com.centris.campus.daoImpl.EventsDaoImpl;
import com.centris.campus.daoImpl.StudentIDDaoImpl;
import com.centris.campus.delegate.AddSubjectDelegate;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.LibraryDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.eventBD;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.eventRegPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.IDcardVo;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentIDVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.eventRegVo;
import com.itextpdf.text.log.SysoLogger;

public class EventsAction extends DispatchAction{
	

	
	static ResourceBundle res=ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName=res.getString("ImageName");
	private static String IdTemplate=res.getString("IdTemplate1");
	private static final Logger logger =Logger.getLogger(IDCreationAction.class);
	
	public ActionForward EventstudentRegistration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentRegistration Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_EVENTS_STUDENTREGISTRATION);
			
		request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
		request.setAttribute("locationList", HelperClass.getAllLocation());
		
		ArrayList<eventRegVo> Classlist = new eventBD().getClassList();
		ArrayList<eventRegVo> name = new eventBD().getEventNameStudentReg();
		ArrayList<eventRegVo> IndivEventName = new eventBD().getEventNameStudentReg();
		
		ArrayList<eventRegVo> house = new eventBD().getHouse();
		request.setAttribute("houseList", house);
		
		request.setAttribute("classList", Classlist);
		request.setAttribute("eventList", name);
		
	}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentRegistration Ending");

		return mapping.findForward(MessageConstants.EventStudentReg);
	}
	
public ActionForward VolunteerReg(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : VolunteerReg Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_EVENTS_VOLUNTEERREGISTRATION);

			request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
			request.setAttribute("locationList", HelperClass.getAllLocation());
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : VolunteerReg Ending");

		return mapping.findForward(MessageConstants.VolunteerReg);
	}
public ActionForward EventRegistration(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : EventReagistration Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
		LeftMenusHighlightMessageConstant.MODULE_EVENTS_EVENTREGISTRATION);
		
		
		request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
		request.setAttribute("locationList", HelperClass.getAllLocation());
		
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : EventReagistration Ending");

	return mapping.findForward(MessageConstants.eventReg);
}

public ActionForward getAdmissionNo(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getAdmissionNo Starting");

		try {
			String searchterm = request.getParameter("searchTerm");
			ArrayList<eventRegVo> data = new EventsDaoImpl().getAdmissionNo(searchterm);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, data);
			response.getWriter().print(jsonObject);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getAdmissionNo ending");

		return null;
}

public ActionForward studentAdmnName(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentAdmnName Starting");

		try {
			String id=request.getParameter("id");
			ArrayList<eventRegVo> list = new EventsDaoImpl().studentAdmnName(id);
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentAdmnName ending");

		return null;
}

public ActionForward studentAdmnNameForEvent(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentAdmnName Starting");

		try {
			String houseName = request.getParameter("houseName");
			if(houseName.equalsIgnoreCase("all")) {
				houseName="%%";
			}
			String id=request.getParameter("id");
			String eventName=request.getParameter("eventName");
			String categoryName=request.getParameter("categoryName");
			
			System.out.println("id"+id);
			System.out.println("eventName"+eventName);
			System.out.println("categoryName"+categoryName);
			
			ArrayList<eventRegVo> list = new EventsDaoImpl().studentAdmnNameForEvent(id,eventName,categoryName,houseName);
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentAdmnName ending");

		return null;
}
public ActionForward deleteEventStudReg(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteStage Starting");
	try{

		String id=request.getParameter("id");
		String data =new eventBD().deleteEventStudReg(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteStage Ending");
	return null;
	
}
public ActionForward SaveEventRegistration(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : SaveEventRegistration Starting");
	try{
		
		eventRegPojo pojo= new eventRegPojo();
			
		String eventName= request.getParameter("eventName");
		String startsOn= request.getParameter("startsOn");
		String endsOn= request.getParameter("endsOn");
		String eventType= request.getParameter("eventType");
		String strtReg= request.getParameter("strtReg");
		String endReg= request.getParameter("endReg");
		String isAprovPps= request.getParameter("isAprovPps");
		String isHouseWise= request.getParameter("isHouseWise");
		String status= request.getParameter("status");
		String description= request.getParameter("description");
		String accyear = request.getParameter("accyear");
		String location = request.getParameter("location");
		String hiddenEventId =request.getParameter("hiddenEventId");
		String eventNameHidden =request.getParameter("eventNameHidden");
		String locationIdHidden =request.getParameter("locationIdHidden");
		String importSettings=request.getParameter("importSettings");
		
		pojo.setImportSettings(importSettings);
		pojo.setEventName(eventName);
		pojo.setStartsOn(startsOn);
		pojo.setEndsOn(endsOn);
		pojo.setEventType(eventType);
		pojo.setStartsRegis(strtReg);
		pojo.setEndsRegis(endReg);
		pojo.setIsAprovPps(isAprovPps);
		pojo.setIshouseWise(isHouseWise);
		pojo.setStatus(status);
		pojo.setDescription(description);
		pojo.setAccyear(accyear);
		pojo.setLocation(location);
		pojo.setHiddenEventId(hiddenEventId);
		pojo.setEventNameHidden(eventNameHidden);
		pojo.setLocationIdHidden(locationIdHidden);
		
		System.out.println(hiddenEventId);
		System.out.println(eventName);
		System.out.println(startsOn);
		
		System.out.println(endsOn);
		System.out.println(eventType);
		System.out.println(strtReg);
		
		System.out.println(endReg);
		System.out.println(isAprovPps);
		System.out.println(isHouseWise);
		
		System.out.println(status);
		System.out.println(description);
		System.out.println(location);
		System.out.println(accyear);
		
		
		String result = new eventBD().SaveEventRegistration(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : SaveEventRegistration Ending");

	return null;
}

public ActionForward saveEventStudentReg(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStudentReg Starting");
	try{
		
		String eventId= request.getParameter("evId");
		String categoryId= request.getParameter("catId");
		String progId= request.getParameter("progId");
		String houseId= request.getParameter("houseId");
		String programCaptain= request.getParameter("programCaptain");
		String participantsName = request.getParameter("participantsName");
		String info_staff= request.getParameter("info_staff");
		String info_synopsis= request.getParameter("info_synopsis");
		String info_req= request.getParameter("info_req");
		String programCaptainHidden = request.getParameter("programCaptainHidden");
		String participationType = request.getParameter("participationType");
		String grpSubstr = request.getParameter("grpSubstr");//for registration number
		String accId = request.getParameter("accId");
		String eventIdHidden = request.getParameter("eventIdHidden");
		String categoryIdHidden = request.getParameter("categoryIdHidden");
		String progIdHidden = request.getParameter("progIdHidden");
		
		String captainCompare = request.getParameter("CaptainCompare");
		String progCaptainHidden = request.getParameter("progCaptainHidden");
		String registrationId = request.getParameter("registrationId");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ registrationId "+registrationId);
		
		System.out.println(""+eventIdHidden);
		System.out.println(""+categoryIdHidden);
		System.out.println(""+progIdHidden);
		System.out.println(" progCaptainHidden compare1 actual"+progCaptainHidden);
		System.out.println("captainCompare to comare"+captainCompare);
		
		eventRegPojo pojo= new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setProgId(progId);
		pojo.setCategoryId(categoryId);
		pojo.setHouseId(houseId);
		pojo.setProgramCaptain(programCaptain);
		pojo.setInfo_req(info_req);
		pojo.setInfo_synopsis(info_synopsis);
		pojo.setInfo_staff(info_staff);
		pojo.setParticipantsName(participantsName);
		pojo.setProgramCaptainHidden(programCaptainHidden);
		pojo.setParticipationType(participationType);
		pojo.setGrpSubstr(grpSubstr);
		pojo.setEventIdHidden(eventIdHidden);
		pojo.setCategoryHidden(categoryIdHidden);
		pojo.setProgramIdHidden(progIdHidden);
		pojo.setAccId(accId);
		pojo.setCaptainCompare(captainCompare);
		pojo.setProgCaptainHidden(progCaptainHidden);
		pojo.setRegistrationId(registrationId);
		String result = new eventBD().saveEventStudentReg(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in EventsAction : saveStudentReg Ending");

	return null;
}

public ActionForward getDataForUpdateEventStdRegis(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   mainList =new eventBD().getDataForUpdateEventStdRegis(id);
		JSONObject obj =new JSONObject();
		obj.put("mainList", mainList);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Ending");
	return null;
}

public ActionForward udpateIndividualSave(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : udpateIndividual Starting");
	try{
		
		String eventId = request.getParameter("eventName");
		String categoryId= request.getParameter("categoryName");
		String progId= request.getParameter("programName");
		String houseId= request.getParameter("houseName");
		String programCaptain= request.getParameter("programCaptain");
		String info_staff= request.getParameter("info_staffI");
		String info_synopsis= request.getParameter("info_synopsis");
		String info_req= request.getParameter("info_reqI");
		String programCaptainHidden = request.getParameter("programCaptainHidden");
		
		System.out.println("eventName "+eventId);
		System.out.println("categoryId "+categoryId);
		System.out.println("progId "+progId);
		System.out.println("houseId "+houseId);
		System.out.println("programCaptain "+programCaptain);
		System.out.println("info_staff "+info_staff);
		System.out.println("info_synopsis "+info_synopsis);
		System.out.println("info_req "+info_req);
		System.out.println("programCaptainHidden "+programCaptainHidden);
		
		eventRegPojo pojo= new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setProgId(progId);
		pojo.setCategoryId(categoryId);
		pojo.setHouseId(houseId);
		pojo.setProgramCaptain(programCaptain);
		pojo.setInfo_req(info_req);
		pojo.setInfo_synopsis(info_synopsis);
		pojo.setInfo_staff(info_staff);
		pojo.setProgramCaptainHidden(programCaptainHidden);
		
		String result = new eventBD().udpateIndividualSave(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in EventsAction : udpateIndividual Ending");

	return null;
}
public ActionForward getEventRegistrationList(ActionMapping mapping, ActionForm from,HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventRegistrationList Starting");
	try{
		String accyear = request.getParameter("accyear");
		String location = request.getParameter("locationId");
		if(location.equalsIgnoreCase("all")){
			location="%%";
		}
		if(accyear.equalsIgnoreCase("all")){
			accyear="%%";
		}
		System.out.println(location);
		String searchTerm=request.getParameter("saerchTerm").trim();
		if(searchTerm.equalsIgnoreCase("all")){
			searchTerm="%%";
		}
		eventRegPojo pojo= new eventRegPojo();
		
		pojo.setAccyear(accyear);
		pojo.setLocation(location);
		pojo.setSaerchTerm(searchTerm);
		
		ArrayList<eventRegVo> list = new eventBD().getEventRegistrationList(pojo);
		JSONObject obj = new JSONObject();
		obj.put("data",list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventRegistrationList Ending");
	return null;
}
public ActionForward deleteEventRegistration(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteEventRegistration Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deleteEventRegistration(id);
		System.out.println(data);
		JSONObject obj =new JSONObject();
		obj.put("status",data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteEventRegistration Ending");
	return null;
	
}
public ActionForward getDataForUpdate(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdate Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdate(id);
		JSONObject obj =new JSONObject();
		obj.put("DataList", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdate Ending");
	return null;
}
public ActionForward categorySetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : eventRegistration Starting");
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
	LeftMenusHighlightMessageConstant.MODULE_EVENTS_CATEGORYSETTING);
	try{
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		ArrayList<eventRegVo> eventName = new eventBD().getEventName();
		request.setAttribute("eventList", eventName);
	}catch(Exception e){
		e.printStackTrace();
	}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : eventRegistration Ending");
	return mapping.findForward(MessageConstants.CategorySettingEvents);
}

public ActionForward getClassListforCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getClassList Starting");
	
	try{
		String eventName = request.getParameter("eventName");
		ArrayList<eventRegVo> Classlist = new EventsDaoImpl().getClassListForCategory(eventName);
		
		JSONObject obj = new JSONObject();
		obj.put("data", Classlist);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getClassList Ending");
	return null;
}
public ActionForward saveCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveCategory Starting");
	try{
		
		String eventId =request.getParameter("eventName");
		String categoryName = request.getParameter("categoryName");
		String classList = request.getParameter("classList"); 
		String eventIdHidden= request.getParameter("eventIdHidden");
		String categoryHidden =request.getParameter("categoryHidden");
		String categoryNameHidden =request.getParameter("categoryNameHidden");
		
		System.out.println(categoryName);
		System.out.println("eventId"+eventId);
		System.out.println();
	
		eventRegPojo pojo = new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setCategoryName(categoryName);
		pojo.setClassList(classList);
		pojo.setEventIdHidden(eventIdHidden);
		pojo.setCategoryHidden(categoryHidden);
		pojo.setCategoryNameHidden(categoryNameHidden);
		
		String result = new eventBD().saveCategory(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveCategory Ending");
	return null;
}
public ActionForward getPrizeCategoryName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getPrizeCategoryName Starting");
	try{
		
		String id = request.getParameter("id");
		String group = request.getParameter("flag");
		//ArrayList<eventRegVo> categoryName = new eventBD().getCategoryName(id,group);
		
		ArrayList<eventRegVo> categoryName = new eventBD().getPrizeCategoryName(id);
		JSONObject obj = new JSONObject();
		obj.put("data", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getPrizeCategoryName Ending");
	return null;
}
public ActionForward getCategorySettingList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Starting");
	try{
		String catId = request.getParameter("catId");
		if(catId.equalsIgnoreCase("all")){
			catId="%%";
		}
		String eventId =request.getParameter("eventId");
		if(eventId.equalsIgnoreCase("all")){
			eventId="%%";
		}
		System.out.println(eventId);
		System.out.println(catId);
		
		ArrayList<eventRegVo> list = new eventBD().getCategorySettingList(eventId,catId);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Ending");
	return null;
}
public ActionForward deleteCategory(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteCategory Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deleteCategory(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteCategory Ending");
	return null;
	
}
public ActionForward getDataForUpdateCategorySetting(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdateCategorySetting Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdateCategorySetting(id);
		JSONObject obj =new JSONObject();
		obj.put("DataList", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdateCategorySetting Ending");
	return null;
}
public ActionForward stageSetting(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : stageSetting Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
		LeftMenusHighlightMessageConstant.MODULE_EVENTS_STAGESETTING);
		
		ArrayList<eventRegVo> eventName = new eventBD().getEventName();
		request.setAttribute("eventList", eventName);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : stageSetting Ending");
	return mapping.findForward(MessageConstants.stageSetting);
}
public ActionForward saveStageSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Starting");
	try{
		String eventId =request.getParameter("eventName");
		String stageName = request.getParameter("stageName");
		String building = request.getParameter("building"); 
		String floorName= request.getParameter("floorName");
		String roomNumber =request.getParameter("roomNumber");
		String info = request.getParameter("info");
		String hiddenStageId = request.getParameter("hiddenStageId");
		String stageNameHidden = request.getParameter("stageNameHidden");
		
		eventRegPojo pojo = new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setStageName(stageName);
		pojo.setBuilding(building);
		pojo.setFloorName(floorName);
		pojo.setRoomNumber(roomNumber);
		pojo.setInfo(info);
		pojo.setHiddenStageId(hiddenStageId);
		pojo.setStageNameHidden(stageNameHidden);
		
		String result = new eventBD().saveStageSetting(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Ending");
	return null;
}
public ActionForward getstageList(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getstageList Starting");
	try{
		String id = request.getParameter("id");
		System.out.println("iddddddddddddddddddddddddddddddddddddddddddddd"+id);
		ArrayList<eventRegVo> list = new eventBD().getstageList(id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getstageList Ending");
	return null;
}
public ActionForward getstageSettingList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Starting");
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
	LeftMenusHighlightMessageConstant.MODULE_EVENTS_CATEGORYSETTING);
	try{
		String eventId = request.getParameter("id");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		String stageId =request.getParameter("stageNameList");
		if(stageId.equalsIgnoreCase("all") ||stageId.equalsIgnoreCase("")){
			stageId="%%";
		}
		System.out.println("eventId"+eventId);
		System.out.println("stageId"+stageId);
		
		ArrayList<eventRegVo> list = new eventBD().getstageSettingList(eventId,stageId);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Ending");
	return null;
}
public ActionForward deleteStage(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteStage Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deleteStage(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteStage Ending");
	return null;
	
}

public ActionForward StageAllocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_STAGEALLOCATION);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());

	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
	/*ArrayList<eventRegVo> stagelist = new eventBD().getStage();
	request.setAttribute("stageList", stagelist);*/
	
	/*ArrayList<eventRegVo> participantsList = new eventBD().getParticipants();
	request.setAttribute("participantsList", participantsList);*/
	
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.stageAllocation);
}

public ActionForward ProgramNumbering(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PROGRAMNUMBERING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.programNumbering);
}


public ActionForward ProgramScheduling(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PROGRAMSCHEDULING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.programScheduling);
}

public ActionForward eventIdCardPrintSingle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
	
	
	
			
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EVENTS_IDCARDPRINTING);

		ArrayList<eventRegVo> eventList = new eventBD().geteventList();
		request.setAttribute("eventList", eventList);
		
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.eventIdCardPrintSingle);
}

public ActionForward IdCardDesign(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");


	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EVENTS_IDCARDDESIGN);
		
		ArrayList<eventRegVo> eventList = new eventBD().geteventList();
		request.setAttribute("eventList", eventList);
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

	return mapping.findForward(MessageConstants.idCardDesign);
}


public ActionForward programSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
	try{
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PROGRAMSETTING);
		
	
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);

	
	}	catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.programSetting);
}

public ActionForward GreenRoomSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_GREENROOMSETTING);
		
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.greenRoomSetting);
}

public ActionForward PrizeLevelSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	String id = request.getParameter("id");
	ArrayList<eventRegVo> categoryName = new eventBD().getCategory();
	request.setAttribute("categoryName", categoryName);
	ArrayList<eventRegVo> programName = new eventBD().getProgramName();
	request.setAttribute("programList", programName);
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PRIZELEVELSETTING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.prizeLevelSetting);
}

public ActionForward CriteriaSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_CRITERIASETTING);
		
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	String id = request.getParameter("id");
	ArrayList<eventRegVo> categoryName = new eventBD().getCategory();
	request.setAttribute("categoryName", categoryName);
	ArrayList<eventRegVo> programName = new eventBD().getProgramName();
	request.setAttribute("programList", programName);
	
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.criteriaSetting);
}


public ActionForward marksEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_MARKSENTRY);
		
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.marksEntry);
}


public ActionForward ParticipantsList(ActionMapping mapping,
ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PARTICIPANTSLIST);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.participantsList);
}

public ActionForward ProgrammeScheduleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PROGRAMMESCHEDULELIST);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.ProgrammeScheduleList);
}
public ActionForward JudgementSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_JUDGEMENTSHEET);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.JudgementSheet);
}
public ActionForward ChestnumberPrinting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_CHESTNUMBERPRINTING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.ChestnumberPrinting);
}
public ActionForward ResultPrinting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_RESULTPRINTING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
      }catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.ResultPrinting);
}
public ActionForward CertificatePrinting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_CERTIFICATEPRINTING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.CertificatePrinting);
}
public ActionForward OverallResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_OVERALLRESULT);
		
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.OverallResult);
}
public ActionForward getDataForUpdateStage(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdateStage(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Ending");
	return null;
}

public ActionForward saveCriteriaSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveCriteriaSetting Starting");
	try{
	
		String eventId =request.getParameter("eventdetails").split(",")[0];
		String category = request.getParameter("eventdetails").split(",")[1];
		String programmeName = request.getParameter("eventdetails").split(",")[2]; 
		
		
		String criteria= request.getParameter("criteria");
		String hiddenCriteriaId = request.getParameter("criteriaid");
		String totalmarks=request.getParameter("totalmarks");
		
		System.out.println("eventId"+eventId);
		System.out.println("programmeName"+programmeName);
		System.out.println("category"+category);
		
		System.out.println("hiddenCriteriaId"+hiddenCriteriaId);
		
		eventRegPojo pojo = new eventRegPojo();
		
		pojo.setEventName(eventId);
		pojo.setProgrammeName(programmeName);
		pojo.setCategory(category);
	
		pojo.setCriteria(criteria);
		pojo.setMaxMarks(totalmarks);
		/*pojo.setSeqNo(seqNo);*/
		pojo.setHiddenCriteriaId(hiddenCriteriaId);
	
		
		String result = new eventBD().saveCriteriaSetting(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveCriteriaSetting Ending");
	return null;
}

public ActionForward getCriteriaSettingList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Starting");
	
	try{
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		
		String categoryList =request.getParameter("categoryList");
		if(categoryList.equalsIgnoreCase("all") ||categoryList.equalsIgnoreCase("")){
			categoryList="%%";
		}
		String programmeList =request.getParameter("programmeList");
		if(programmeList.equalsIgnoreCase("all") ||programmeList.equalsIgnoreCase("")){
			programmeList="%%";
		}

		System.out.println("eventId"+eventId);
		System.out.println("programmeList"+programmeList);
		System.out.println("categoryList"+categoryList);

		ArrayList<eventRegVo> list = new eventBD().getCriteriaSettingList(eventId,programmeList,categoryList);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Ending");
	return null;
}

public ActionForward deleteCriteria(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteCriteria Starting");
	try{
		String id=request.getParameter("id");
		System.out.println("id to delete>>>>>"+id);
		String data =new eventBD().deleteCriteria(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteCriteria Ending");
	return null;
	
}
public ActionForward getDataForUpdateCriteria(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdateCriteria(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateStage Ending");
	return null;
}
public ActionForward getCategoryList(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getstageList Starting");
	try{
		String id = request.getParameter("id");
		System.out.println("iddddddddddddddddddddddddddddddddddddddddddddd"+id);
		ArrayList<eventRegVo> list = new eventBD().getCategoryList(id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getstageList Ending");
	return null;
}

public ActionForward getProgrammeName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgrammeName Starting");
	try{
		
		String id = request.getParameter("id");
		String event_id = request.getParameter("event_id");
		ArrayList<eventRegVo> categoryName = new eventBD().getProgrammeName(id,event_id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgrammeName Ending");
	return null;
}

public ActionForward getCriteriaList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaList Starting");
	try{
		
		String id = request.getParameter("id");
		String event_id = request.getParameter("event_id");
		String cat_id = request.getParameter("cat_id");
		ArrayList<eventRegVo> CriteriaList = new eventBD().getCriteriaList(id,event_id,cat_id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", CriteriaList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaList Ending");
	return null;
}

public ActionForward validateCriteria(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateCriteria: Starting");
           System.out.println("whether it is coming inside the event action:");
	try {
		System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
		String eventName = request.getParameter("eventName");
		String programmeName = request.getParameter("programmeName");
		String category=request.getParameter("category");
		String Criteria=request.getParameter("Criteria");
		
		String list = new eventBD().validateCriteria(eventName,programmeName,category,Criteria);
		System.out.println("list" +list);

		JSONObject object = new JSONObject();
		object.put("list", list);
		response.getWriter().print(object);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
	return null;
}

public ActionForward saveGreenRoom(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveGreenRoom Starting");
	try{
		
		String eventId =request.getParameter("eventId");
		String greenRoomName =request.getParameter("greenRoomName");
		String greenRoomType = request.getParameter("greenRoomType");
		String building = request.getParameter("building"); 
		String floorName= request.getParameter("floorName");
		String roomNumber =request.getParameter("roomNumber");
		String greenRoomIdHidden = request.getParameter("greenRoomIdHidden");
		String greenRoomNameHidden = request.getParameter("greenRoomNameHidden");
		
		System.out.println("eventid hidden"+greenRoomIdHidden);
		
		eventRegPojo pojo = new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setGreenRoomName(greenRoomName);
		pojo.setGreenRoomType(greenRoomType);
		pojo.setBuilding(building);
		pojo.setFloorName(floorName);
		pojo.setRoomNumber(roomNumber);
		pojo.setGreenRoomIdHidden(greenRoomIdHidden);
		pojo.setGreenRoomNameHidden(greenRoomNameHidden);
		
		String result = new eventBD().saveGreenRoom(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveGreenRoom Ending");
	return null;
}

public ActionForward getGreenRoom(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Starting");
	try{
		String id = request.getParameter("id");
		System.out.println(id);
		ArrayList<eventRegVo> list = new eventBD().getGreenRoom(id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Ending");
	return null;
}
public ActionForward getGreenRoomList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoomList Starting");
	
	try{
		String eventId = request.getParameter("id");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		String greenRoomId =request.getParameter("greenRoomId");
		if(greenRoomId.equalsIgnoreCase("all") ||greenRoomId.equalsIgnoreCase("")){
			greenRoomId="%%";
		}
		System.out.println("eventId"+eventId);
		System.out.println("greenRoomId"+greenRoomId);
		
		ArrayList<eventRegVo> list = new eventBD().getGreenRoomList(eventId,greenRoomId);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoomList Ending");
	return null;
}
public ActionForward deleteGreenRoom(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteGreenRoom Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deleteGreenRoom(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteGreenRoom Ending");
	return null;
	
}


public ActionForward getDataForUpdateGreenRoom(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdateGreenRoom Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdateGreenRoom(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataForUpdateGreenRoom Ending");
	return null;
}

public ActionForward saveProgram(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveProgram Starting");
	try{
		
		String eventName= request.getParameter("eventName");
		String categoryName= request.getParameter("categoryName");
		String partiType= request.getParameter("partiType");
		String progName= request.getParameter("progName");
		
		String termsAllowed= request.getParameter("termsAllowed");
		String PartiNo= request.getParameter("PartiNo");
		String duration= request.getParameter("duration");
		
		String progType= request.getParameter("progType");
		String NoOfChildHouse= request.getParameter("NoOfChildHouse");
		String progDate= request.getParameter("progDate");
		
		String infoStaff= request.getParameter("infoStaff");
		String infoGeneral= request.getParameter("infoGeneral");
		String isHouse= request.getParameter("isHouse");
		String progIdHidden = request.getParameter("progIdHidden");
		String judgeList = request.getParameter("judgeList");
		String programNameHidden = request.getParameter("programNameHidden");
		
		
		eventRegPojo pojo= new eventRegPojo();
		pojo.setEventId(eventName);
		pojo.setCategoryId(categoryName);
		pojo.setPartiType(partiType);
		pojo.setProgName(progName);
		pojo.setProgIdHidden(progIdHidden);
		
		pojo.setTermsAllowed(termsAllowed);
		pojo.setPartiNo(PartiNo);
		pojo.setDuration(duration);
		
		pojo.setProgType(progType);
		pojo.setNoOfChildHouse(NoOfChildHouse);
		pojo.setProgDate(progDate);
		
		pojo.setInfoStaff(infoStaff);
		pojo.setInfoGeneral(infoGeneral);
		pojo.setIsHouse(isHouse);
		pojo.setJudgeList(judgeList);
		pojo.setProgramNameHidden(programNameHidden);
		
		String result = new eventBD().saveProgram(pojo,judgeList);
		
		JSONObject obj=new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveProgram Ending");

	return null;
}

public ActionForward getprogramName(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramName Starting");
	try{
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String group = request.getParameter("flag");
		String proType=request.getParameter("proType");
		System.out.println("getprogramName"+evId);
		System.out.println("getprogramName"+catId);
		
		ArrayList<eventRegVo> result = new EventsDaoImpl().getprogramNameReg(evId,catId,group,proType);
		
		JSONObject obj=new JSONObject();
		obj.put("data", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		} 
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramName Ending");
	return null;
}
 
public ActionForward getStageNameListGroup(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramName Starting");
	try{
		String evId = request.getParameter("evId");
		String progId = request.getParameter("progId");
		ArrayList<eventRegVo> result = new eventBD().getStageNameListGroup(evId,progId);
		JSONObject obj=new JSONObject();
		obj.put("data", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramName Ending");
	return null;
}


public ActionForward getProgramSettingList(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSettingList Starting");
	try{
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		
		if(evId.equalsIgnoreCase("all")  || evId.equalsIgnoreCase("")){
			evId="%%";
		}
		if(catId.equalsIgnoreCase("all")  || catId.equalsIgnoreCase("")){
			catId="%%";
		}
		if(progId.equalsIgnoreCase("all")  || progId.equalsIgnoreCase("")){
			progId="%%";
		}
		System.out.println(evId);
		System.out.println(catId);
		System.out.println(progId);
		
		ArrayList<eventRegVo> result = new eventBD().getProgramSettingList(evId,catId,progId);
		
		JSONObject obj=new JSONObject();
		obj.put("data", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSettingList Ending");
	return null;
}
public ActionForward deleteProgram(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgram Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deleteProgram(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgram Ending");
	return null;
}
public ActionForward getDataforUpdateProgram(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateProgram Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataforUpdateProgram(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateProgram Ending");
	return null;
}
public ActionForward getPrizeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaList Starting");
	try{
		
		String id = request.getParameter("id");
		String event_id = request.getParameter("event_id");
		String cat_id = request.getParameter("cat_id");
		ArrayList<eventRegVo> PrizeList = new eventBD().getPrizeList(id,event_id,cat_id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", PrizeList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaList Ending");
	return null;
}

public ActionForward savePrizelevelSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Starting");
	try{
		
		String eventId =request.getParameter("eventName");
	
		String programmeName = request.getParameter("programmeName");
		String category = request.getParameter("category"); 
		String prizelevel= request.getParameter("prizelevel");
		String points =request.getParameter("points");
		String seqNo = request.getParameter("seqNo");
		String hiddenCriteriaId = request.getParameter("hiddenCriteriaId");
		String description = request.getParameter("description");
		
		
		eventRegPojo pojo = new eventRegPojo();
	
		pojo.setEventName(eventId);
		pojo.setProgrammeName(programmeName);
		pojo.setCategory(category);
		pojo.setPrizeLevel(prizelevel);
		pojo.setPoints(points);
		pojo.setSeqNo(seqNo);
		pojo.setDescription(description);
		pojo.setHiddenCriteriaId(hiddenCriteriaId);
	
		
		String result = new eventBD().savePrizelevelSetting(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Ending");
	return null;
}

public ActionForward getPrizeSettingList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Starting");
	
	try{
		/*ArrayList<eventRegVo> categoryName = new eventBD().getCategory();
		request.setAttribute("categoryName", categoryName);*/
		
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		
		String categoryList =request.getParameter("categoryList");
		if(categoryList.equalsIgnoreCase("all") ||categoryList.equalsIgnoreCase("")){
			categoryList="%%";
		}
		String programmeList =request.getParameter("programmeList");
		if(programmeList.equalsIgnoreCase("all") ||programmeList.equalsIgnoreCase("")){
			programmeList="%%";
		}
		/*String prizelist =request.getParameter("prizelist");
		System.out.println("prizelist1"+prizelist);
		if(prizelist.equalsIgnoreCase("all") ||prizelist.equalsIgnoreCase("")){
			prizelist="%%";
		}
		*/
		System.out.println("eventId"+eventId);
		System.out.println("programmeList"+programmeList);
		System.out.println("categoryList"+categoryList);
		/*System.out.println("prizelist2"+prizelist);*/
		ArrayList<eventRegVo> list = new eventBD().getPrizeSettingList(eventId,programmeList,categoryList);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategorySettingList Ending");
	return null;
}
public ActionForward getDataForUpdatePrize(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updatePrize Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdatePrize(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updatePrize Ending");
	return null;
}
public ActionForward deletePrize(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deletePrize Starting");
	try{
		String id=request.getParameter("id");
		System.out.println("id to delete>>>>>"+id);
		String data =new eventBD().deletePrize(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deletePrize Ending");
	return null;
	
}
public ActionForward getSeqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getSeqList Starting");
	try{
		
		String id = request.getParameter("id");
		String event_id = request.getParameter("event_id");
		String cat_id = request.getParameter("cat_id");
		String prog_id = request.getParameter("prog_id");
		ArrayList<eventRegVo> PrizeList = new eventBD().getSeqList(id,event_id,cat_id,prog_id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", PrizeList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getSeqList Ending");
	return null;
}

public ActionForward getPointsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getSeqList Starting");
	try{
		
		String id = request.getParameter("id");
		String event_id = request.getParameter("event_id");
		String cat_id = request.getParameter("cat_id");
		String prog_id = request.getParameter("prog_id");
		String prize_id = request.getParameter("prize_id");
		System.out.println("prize_id value>>>>"+prize_id);
		ArrayList<eventRegVo> PrizeList = new eventBD().getPointsList(id,event_id,cat_id,prog_id,prize_id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", PrizeList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getSeqList Ending");
	return null;
}
public ActionForward validateSeq(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateSeq: Starting");
           System.out.println("whether it is coming inside the event action:");
	try {
		System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
		String eventName = request.getParameter("eventName");
		String programmeName = request.getParameter("programmeName");
		String category=request.getParameter("category");
		String seqNo=request.getParameter("seqNo");
		String prizelevel=request.getParameter("prizelevel");
		String points=request.getParameter("points");
		
		
		String list = new eventBD().validateSeq(eventName,programmeName,category,seqNo,prizelevel,points);
		System.out.println("list" +list);

		JSONObject object = new JSONObject();
		object.put("list", list);
		response.getWriter().print(object);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateSeq: Ending");
	return null;
}

public ActionForward validatePrize(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validatePrize: Starting");
           System.out.println("whether it is coming inside the event action:");
	try {
		System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
		String eventName = request.getParameter("eventName");
		String programmeName = request.getParameter("programmeName");
		String category=request.getParameter("category");
		String seqNo=request.getParameter("seqNo");
		String prizelevel=request.getParameter("prizelevel");
		String points=request.getParameter("points");
		
		String list = new eventBD().validatePrize(eventName,programmeName,category,seqNo,prizelevel,points);
		System.out.println("list" +list);

		JSONObject object = new JSONObject();
		object.put("list", list);
		response.getWriter().print(object);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validatePrize: Ending");
	return null;
}
public ActionForward getProgramList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgrammeName Starting");
	try{
		
		String id = request.getParameter("id");
	
		ArrayList<eventRegVo> categoryName = new eventBD().getProgramList(id);
		System.out.println("id))))))))))))))))))))))))))))"+id);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgrammeName Ending");
	return null;
}
public ActionForward saveProgramScheduling(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Starting");
	try{
		
		String eventId =request.getParameter("eventName");
		String programmeName = request.getParameter("programmeName");
		String FromDate = request.getParameter("FromDate"); 
		String stageNameList= request.getParameter("stageNameList");
		String greenRoomList =request.getParameter("greenRoomList");
		String makeup = request.getParameter("makeup");
		String backStage = request.getParameter("backStage");
		String hiddenCriteriaId=request.getParameter("hiddenCriteriaId");
		String programTime=request.getParameter("programTime");
			
		
		
		eventRegPojo pojo = new eventRegPojo();
		pojo.setEventId(eventId);
		pojo.setEventName(eventId);
		pojo.setProgrammeName(programmeName);
		pojo.setProgramDate(FromDate);
		pojo.setStageName(stageNameList);
		pojo.setGreenRoomName(greenRoomList);
		pojo.setMakeup(makeup);
		pojo.setBackStage(backStage);
		pojo.setHiddenCriteriaId(hiddenCriteriaId);
		pojo.setProgramTime(programTime);
	
		
		String result = new eventBD().saveProgramScheduling(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageSetting Ending");
	return null;
}
public ActionForward getProgramSchedulingList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingList Starting");
	
	try{
		/*ArrayList<eventRegVo> categoryName = new eventBD().getCategory();
		request.setAttribute("categoryName", categoryName);*/
		
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		
		String prog_date = request.getParameter("prog_date");
		
		System.out.println("eventId"+eventId);
		
		ArrayList<eventRegVo> list = new eventBD().getProgramSchedulingList(eventId,prog_date);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingList Ending");
	return null;
}
public ActionForward getDataForUpdateScheduling(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateScheduling Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>   list =new eventBD().getDataForUpdateScheduling(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : updateScheduling Ending");
	return null;
}
public ActionForward deleteProgramScheduling(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgramScheduling Starting");
	try{
		String id=request.getParameter("id");
		System.out.println("id to delete>>>>>"+id);
		String data =new eventBD().deleteProgramScheduling(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgramScheduling Ending");
	return null;
	
}
public ActionForward validateScheduling(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateScheduling: Starting");
           System.out.println("whether it is coming inside the event action:");
	try {
		System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
		String eventName = request.getParameter("eventName");
		String programmeList = request.getParameter("programmeList");
		String FromDate=request.getParameter("FromDate");
		String stageNameList=request.getParameter("stageNameList");
		String greenRoomList=request.getParameter("greenRoomList");
		
		
		String list = new eventBD().validateScheduling(eventName,programmeList,FromDate,stageNameList,greenRoomList);
		System.out.println("list" +list);

		JSONObject object = new JSONObject();
		object.put("list", list);
		response.getWriter().print(object);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateScheduling: Ending");
	return null;
}
public ActionForward getEventCategory(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventCategory: Starting");
	try {
		String eid = request.getParameter("eid");
		System.out.println("vvvvvvvvvv"+eid);
		
		ArrayList<eventRegVo> eventCategory = new eventBD().eventCategory(eid);
		
		JSONObject obj1= new JSONObject();
		obj1.put("eventCategory", eventCategory);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventCategory: Ending");
	return null;
}

public ActionForward getProgramName(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventCategory: Starting");
	try {
		String cid = request.getParameter("cid");
		System.out.println("vvvvvvvvvv"+cid);
		
		ArrayList<eventRegVo> eventProgramName = new eventBD().eventProgramName(cid);
		
		JSONObject obj1= new JSONObject();
		obj1.put("eventProgramName", eventProgramName);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventCategory: Ending");
	return null;
}

public ActionForward getEventNameList_Group(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction :  getEventNameList_Group Starting");
	try{
		String val=request.getParameter("flag");
		ArrayList<eventRegVo> evName = new eventBD().getEventNameList_Group(val);
		
		JSONObject obj = new JSONObject();
		obj.put("data", evName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventNameList_Group Ending");
	return null;
}
public ActionForward getHouseNameListGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getHouseNameListGroup Starting");
	try{
		
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		String flag = request.getParameter("flag");
		System.out.println(evId);
		System.out.println(catId);
		System.out.println(progId);
		System.out.println(flag);
		
		ArrayList<eventRegVo> regNo = new eventBD().getHouseNameListGroup(evId,catId,progId,flag);
		
		JSONObject obj = new JSONObject();
		obj.put("data", regNo);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getHouseNameListGroup Ending");
	return null;
}


public ActionForward getEventIdList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventIdList Starting");

	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();

	try {
		String eventList=request.getParameter("eventList");
		if(eventList.equalsIgnoreCase("all")){
			eventList="%%";
		}
		String eventcategory=request.getParameter("eventcategory");
		if(eventcategory.equalsIgnoreCase("all")){
			eventcategory="%%";
		}
		String eventprogramname=request.getParameter("eventprogramname");
		if(eventprogramname.equalsIgnoreCase("all")){
			eventprogramname="%%";
		}
		
		System.out.println("vvvvvvvvv"+eventList+" "+eventcategory+" "+eventprogramname);
		
		eventRegPojo filterpojo=new eventRegPojo();
		filterpojo.setEventId(eventList);
		filterpojo.setCategoryId(eventcategory);
		filterpojo.setProgId(eventprogramname);
		
		System.out.println(filterpojo);
		
		list=new eventBD().getEventIdList(filterpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", list);
		response.getWriter().print(obj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventIdList Ending");

	return null;
}

public ActionForward getEventStudentRegList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("evId");
			String progId =request.getParameter("progId");
			String catId =request.getParameter("catId");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			
			System.out.println(evId);
			System.out.println(progId);
			System.out.println(catId);
			System.out.println(houseId);
			

			
			ArrayList<eventRegVo> mainList = new eventBD().getEventStudentRegList(evId,progId,catId,houseId,accId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}

public ActionForward getParticipantList_Group(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String accyear = (String) request.getSession(false).getAttribute("current_academicyear");
			
			String evId =request.getParameter("evId");
			String catId =request.getParameter("catId");
			String progId =request.getParameter("progId");
	/*		String stageId =request.getParameter("stageId");
			String searchval =request.getParameter("searchval");*/
			
			if(evId.equalsIgnoreCase("all") || evId.equalsIgnoreCase("") ){
				evId="%%";	
			}
			if(catId.equalsIgnoreCase("all") || catId.equalsIgnoreCase("")){
				catId="%%";	
			}
			if(progId.equalsIgnoreCase("all") || progId.equalsIgnoreCase("")){
				progId="%%";	
			}
	/*		if(stageId.equalsIgnoreCase("all")){
				stageId="%%";	
			}
			if(searchval.equalsIgnoreCase("all")){
				searchval="%%";	
			}*/
			
			ArrayList<eventRegVo> mainList = new eventBD().getParticipantList_Group(evId,catId,progId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}


public ActionForward getEventStudentRegListIndividual(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegListIndividual Starting");

		try {
			String evId =request.getParameter("evId");
			String catId =request.getParameter("catId");
			String progId =request.getParameter("progId");
			String houseId =request.getParameter("houseId");
			String accId =request.getParameter("accId");

			ArrayList<eventRegVo> mainList = new EventsDaoImpl().getEventStudentRegListIndividual(evId,catId,progId,houseId,accId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegListIndividual ending");

		return null;
}


public ActionForward getAllocatedParticipants(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateScheduling: Starting");
           System.out.println("whether it is coming inside the event action:");
	try {
String event=request.getParameter("event");
String stage=request.getParameter("stage");
		ArrayList<eventRegVo> participantsList = new eventBD().getAllocatedParticipants(event,stage);
		request.setAttribute("participantsList", participantsList);
		

		JSONObject object = new JSONObject();
		object.put("list", participantsList);
		response.getWriter().print(object);
        
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : validateScheduling: Ending");
	return null;
}
public ActionForward saveStageParticipantMapping(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageParticipantMapping Starting");
	try{
		
		String stageId =request.getParameter("stageId");
		String event =request.getParameter("event");
		String participantsDetails =request.getParameter("participantsDetails");
		
		
		
		eventRegPojo pojo = new eventRegPojo();
	
		pojo.setStageId(stageId);
		pojo.setEventId(event);
		pojo.setParticipantsList(participantsDetails);
		
		System.out.println("participantsDetails "+participantsDetails);
		String result = new eventBD().saveStageParticipantMapping(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveStageParticipantMapping Ending");
	return null;
}

	public ActionForward getMappedParticipants(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Eventaction : getMappedParticipants: Starting");
	           System.out.println("whether it is coming inside the event action:");
		try {
			String stageVal =request.getParameter("stageVal");
			ArrayList<eventRegVo> mappedParticipants= new eventBD().getMappedParticipants(stageVal);
			request.setAttribute("mappedParticipants", mappedParticipants);

			JSONObject object = new JSONObject();
			object.put("list", mappedParticipants);
			response.getWriter().print(object);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Eventaction : getMappedParticipants: Ending");
		return null;
	}
	
	public ActionForward getUnAllocatedProgramList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
			logger.info(JDate.getTimeString(new Date())
					+ " Control in Eventaction : getUnAllocatedProgramList: Starting");
		           System.out.println("whether it is coming inside the event action:");
			try {
				String event =request.getParameter("event");
				ArrayList<eventRegVo> unMappedProgram= new EventsDaoImpl().getUnAllocatedProgramList(event);

				JSONObject object = new JSONObject();
				object.put("list", unMappedProgram);
				response.getWriter().print(object);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}

			
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in Eventaction : getUnAllocatedProgramList: Ending");
			return null;
		}
	

public ActionForward newEventIdCardPrintTemplate(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : newEventIdCardPrintTemplate Starting");
	
	
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EVENTS_IDCARDDESIGN);
		
		String eventList=request.getParameter("eventList");
		String eventcategory=request.getParameter("eventcategory");
		
		String evntname=request.getParameter("evntname");
		
		ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
		
		eventRegVo filterpojo=new eventRegVo();
		
		filterpojo.setEventId(eventList);
		filterpojo.setCategoryId(eventcategory);
		
		
		request.setAttribute("eventname", evntname);
		request.setAttribute("templateName",eventcategory) ;
		request.setAttribute("templateClass",eventList+eventcategory);
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

		
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : newEventIdCardPrintTemplate Ending");
	
	

	return mapping.findForward(MessageConstants.New_EventId_Print_Template);
}


public ActionForward getEventIdListToPrint(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventIdList Starting");

	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();

	try {
		String eventList=request.getParameter("eventList");
		if(eventList.equalsIgnoreCase("all")){
			eventList="%%";
		}
		String eventcategory=request.getParameter("eventcategory");
		if(eventcategory.equalsIgnoreCase("all")){
			eventcategory="%%";
		}
		String eventprogramname=request.getParameter("eventprogramname");
		if(eventprogramname.equalsIgnoreCase("all")){
			eventprogramname="%%";
		}
		
		System.out.println("vvvvvvvvv"+eventList+" "+eventcategory+" "+eventprogramname);
		
		eventRegPojo filterpojo=new eventRegPojo();
		filterpojo.setEventId(eventList);
		filterpojo.setCategoryId(eventcategory);
		filterpojo.setProgId(eventprogramname);
		
		System.out.println(filterpojo);
		
		list=new eventBD().getEventIdListToPrint(filterpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", list);
		response.getWriter().print(obj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getEventIdList Ending");

	return null;
}
	public ActionForward getProgramNumberingDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramNumberingDetails Starting");
		
		try{
			
			String event=request.getParameter("event");
			String stage=request.getParameter("stageId");
			ArrayList<eventRegVo> list = new eventBD().getProgramNumberingDetails(event,stage);
			JSONObject obj= new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramNumberingDetails Ending");
		return null;
	}

public ActionForward getEventIdPrintList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			
			String evId =request.getParameter("evId");
			String catId =request.getParameter("catId");
			String regNo =request.getParameter("regNo");
			
			
			
			ArrayList<eventRegVo> mainList = new EventsDaoImpl().getEventIdPrintList(evId,catId,regNo);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}


public ActionForward PrintEventSingleIDCard(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : PrintStudentSingleIDCard Starting");


	try {
		String admno = request.getParameter("admno");
		String prognm = request.getParameter("prognm");
		String stunm = request.getParameter("stunm");
		String cls = request.getParameter("cls");
		String rolno = request.getParameter("rolno");
		String evenm = request.getParameter("evenm");
		String evecat = request.getParameter("evecat");
		String schlnm = request.getParameter("schlnm");
		String img = request.getParameter("img");
		
		request.setAttribute("admno", admno);
		request.setAttribute("schlnm", schlnm);
		request.setAttribute("stunm", stunm);
		request.setAttribute("cls", cls);
		request.setAttribute("rolno", rolno);
		request.setAttribute("evenm", evenm);
		request.setAttribute("evecat", evecat);
		request.setAttribute("prognm", prognm);
		request.setAttribute("img", img);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : PrintStudentSingleIDCard Ending");

	return mapping.findForward(MessageConstants.PrintEventSingleIDCard);
}


public ActionForward PrintPreviewEventMultipleIDCard(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : PrintPreviewEventMultipleIDCard Starting");
	
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_IDCARDPRINTING);
	

	String prognm=request.getParameter("prognm");
	String evenm=request.getParameter("evenm");
	String evecat=request.getParameter("evecat");
	
	
	if(evecat.equalsIgnoreCase("VOLANTEER")) {
			ArrayList<eventRegVo> reg=new EventsDaoImpl().getEventVolanteerIdPrintList( evenm,evecat,prognm);
		
		
		
		request.setAttribute("template", evenm+"VOLANTEER");
		request.setAttribute("list", reg);
	}
	
	else {
		ArrayList<eventRegVo> reg=new EventsDaoImpl().getEventIdPrintList( evenm,evecat,prognm);
		
		
		
		request.setAttribute("template", evenm+"PARTICIPANT");
		request.setAttribute("list", reg);
	}
		
	
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentIDPDFReport Ending");

	return mapping.findForward(MessageConstants.PrintPreviewEventMultipleIDCard);
}


public ActionForward saveProgramNumberingDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveProgramNumberingDetails Starting");
	try{
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String regid[]=request.getParameter("regid").split(",");
		String stageId =request.getParameter("stageId");
		String event =request.getParameter("event");
		String category[] =request.getParameter("category").split(",");
		String progparticipant[] =request.getParameter("progparticipant").split(",");
		String participants[] =request.getParameter("participants").split(",");
		String programNumber[]=request.getParameter("id").split(",");
		
		System.out.println("category>>>"+category);
		System.out.println("progparticipant>>>"+progparticipant);
		System.out.println("participants>>"+participants);
		System.out.println("programNumber>>>"+programNumber);
		System.out.println("registration id>>>>"+regid);
		eventRegPojo pojo = new eventRegPojo();
	
		pojo.setStageId(stageId);
		pojo.setEventId(event);     
		pojo.setCategoryArray(category);
		pojo.setProgramArray(progparticipant);
		pojo.setParticipant(participants);
		pojo.setProgramNumberArray(programNumber);
		pojo.setRegistrationIdArray(regid);
		
		String result = new eventBD().saveProgramNumberingDetails(pojo);
		
		JSONObject obj = new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveProgramNumberingDetails Ending");
	return null;
}

public ActionForward getMappedProgramNumberingDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramNumberingDetails Starting");
	
	try{
		
		String event=request.getParameter("event");
		String stage=request.getParameter("stageId");
		ArrayList<eventRegVo> list = new eventBD().getMappedProgramNumberingDetails(event,stage);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramNumberingDetails Ending");
	return null;
}

public ActionForward deleteProgramNumber(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgramNumber Starting");
	try{
		String id[]=request.getParameter("id").split(",");
		System.out.println("id to delete in program numbering >>>>>"+id[0]);
		String data =new eventBD().deleteProgramNumber(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deleteProgramNumber Ending");
	return null;
	
}


public ActionForward EvntIdDesign(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : EvntIdDesign Starting");
	String mainCss = request.getParameter("mainCss");
	String layout=request.getParameter("layout");
	String imgUrl=request.getParameter("imgUrl");
	System.out.println("imgurl"+imgUrl);
	
	System.out.println("realimgurl"+request.getRealPath("/")+imgUrl);
	if(!imgUrl.equalsIgnoreCase("noImage")){
		
		 FileInputStream is = null;
		         FileOutputStream os = null;
		         try {
		        	 File sourceFile=new File(request.getRealPath("/")+imgUrl);
		        	 File desFile=new File(request.getRealPath("/")+"images/IdCard/"+layout+".jpg");
		             is = new FileInputStream(sourceFile);
		             os = new FileOutputStream(desFile);
		             byte[] buffer = new byte[1024];
		             int length;
		             while ((length = is.read(buffer)) > 0) {
		                 os.write(buffer, 0, length);
		             }
		         }catch(Exception ex) {
		             System.out.println("Unable to copy file:"+ex.getMessage());
		         }   
		         finally {
		             try {
		                 is.close();
		                 os.close();
		             }catch(Exception ex) {}
		         }
		     }
	

	
	String newCssArray[] = mainCss.split("}");
	
	File file = new File(request.getRealPath("/")+ "CSS/IdCard/"+layout+".css");
	if(file.exists()){
		
	}
	else{
		file.createNewFile();
	}
	
	for (String css : newCssArray) {
		System.out.println("each css" + css);

	}
	BufferedWriter bw = null;
	FileWriter fw = null;
	BufferedReader br = null;
	FileReader fr = null;
	String FILENAME =request.getRealPath("/")+ "CSS/IdCard/"+layout+".css";
	File temp=null;
	temp = new File("IdCard.css");
	String absolutePath = temp.getAbsolutePath();
	System.out.println("absolutePath  "+absolutePath);
	try {

		System.out.println(FILENAME);

		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String sCurrentLine;

		br = new BufferedReader(new FileReader(FILENAME));

		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			String[] words = sCurrentLine.split("\\s");
			String sCurrentLineArray[] = sCurrentLine.split("}");
			for (String css : words) {
				System.out.println("each css" + css);

			}

		}

		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);
		bw.write(mainCss);

		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {

		try {

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : EvntIdDesign Ending");
	return null;
}




public ActionForward getcategorylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		
		
		String event_id = request.getParameter("eventname");
		ArrayList<eventRegVo> categoryName = new eventBD().getcategorylist(event_id);
		
		
		
		JSONObject obj = new JSONObject();
		obj.put("catlist", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}

public ActionForward getprogramlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramlist Starting");
	try{
		String cat_id=request.getParameter("cat_id");
		String evId=request.getParameter("evId");
		ArrayList<eventRegVo> programname = new EventsDaoImpl().getprogramlist(cat_id,evId);
		JSONObject obj = new JSONObject();
		obj.put("proglist", programname);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getprogramlist Ending");
	return null;
}

public ActionForward getstagelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id = request.getParameter("eventname");
		String flagVal = request.getParameter("flag");
		ArrayList<eventRegVo> categoryName = new eventBD().getstagelist(event_id,flagVal);
		
		JSONObject obj = new JSONObject();
		obj.put("stagelist", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}


public ActionForward getChestNoForMarksEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getChestNoForMarksEntry Starting");
	try{
		UserDetailVO userdetail=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=userdetail.getUserId();
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		
		if(evId.equalsIgnoreCase("")){
			evId="%%";
		}
		if(catId.equalsIgnoreCase("")){
			catId="%%";
		}
		if(progId.equalsIgnoreCase("")){
			progId="%%";
		}
		ArrayList<eventRegVo> chestNo = new EventsDaoImpl().getChestNoForMarksEntry(evId,catId,progId,userId);
		JSONObject obj = new JSONObject();
		obj.put("data", chestNo);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getChestNoForMarksEntry Ending");
	return null;
}

public ActionForward getCriteriaForMarksEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaForMarksEntry Starting");
	try{
		UserDetailVO userdetail=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=userdetail.getUserId();
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		
		ArrayList<eventRegVo> criteriaList = new EventsDaoImpl().getCriteriaForMarksEntry(evId,catId,progId);
		
		JSONObject obj = new JSONObject();
		obj.put("data", criteriaList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaForMarksEntry Ending");
	return null;
}
public ActionForward getCriteriaForMarksEntrySaved(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaForMarksEntrySaved Starting");
	try{
		UserDetailVO userdetail=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=userdetail.getUserId();
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		String chestno=request.getParameter("chestno");
		ArrayList<eventRegVo> criteriaList = new EventsDaoImpl().getCriteriaForMarksEntrySaved(evId,catId,progId,userId,chestno);
		
		JSONObject obj = new JSONObject();
		obj.put("data", criteriaList);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCriteriaForMarksEntrySaved Ending");
	return null;
}
public ActionForward saveMarksEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveMarksEntry Starting");
	
	try{
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=userDetailVO.getUserId();
		
		String accId =request.getParameter("accId");
		String catId = request.getParameter("catId");
		String evId = request.getParameter("evId"); 
		String progId= request.getParameter("progId");
		
		String marksObtained[] =request.getParameter("marksObtained").split(",");
		String chestNo[] =request.getParameter("chestNo").split(",");
		String criteriaName[]= request.getParameter("criteriaName").split(",");
		String criteriaMarks[]= request.getParameter("criteriaMarks").split(",");
		
		String status = new EventsDaoImpl().saveMarksEntry(accId,catId,progId,evId,marksObtained,chestNo,criteriaName,criteriaMarks,userId);
		
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveMarksEntry Ending");
	return null;
}


public ActionForward marksEntryNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_MARKSENTRY);
		
	
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");

	return mapping.findForward(MessageConstants.marksEntryNew);
}




public ActionForward getProgrammeDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id = request.getParameter("evId");
		String ProgramId = request.getParameter("progId");
		String stageId = request.getParameter("stage");
		ArrayList<eventRegVo> pgmName = new eventBD().getProgrammeDate(event_id,ProgramId,stageId);
		JSONObject obj = new JSONObject();
		obj.put("data", pgmName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}

public ActionForward getStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id = request.getParameter("evId");
		ArrayList<eventRegVo> stagelist = new eventBD().getStage(event_id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", stagelist);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}
public ActionForward getTableByData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		System.out.println("GET TABLE BY DATA");
		String event_id = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		String houseId = request.getParameter("houseId");
		String accId = request.getParameter("accId");
		
		if(progId==null || progId.equalsIgnoreCase("")) {
			progId="%%";
		}
		if(houseId==null || houseId.equalsIgnoreCase("")) {
			houseId="%%";
		}
		ArrayList<eventRegVo> stagelist = new eventBD().getTableByData(event_id,catId,progId,houseId,accId);
		
		JSONObject obj = new JSONObject();
		obj.put("data", stagelist);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}
public ActionForward getHouse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		ArrayList<eventRegVo> house = new eventBD().getHouse();
		JSONObject obj = new JSONObject();
		obj.put("data", house);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}


public ActionForward getHouseForParticipantList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		
		String eventID=request.getParameter("eventID");
		ArrayList<eventRegVo> house = new EventsDaoImpl().getHouseForParticipantList(eventID);
		JSONObject obj = new JSONObject();
		obj.put("data", house);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}

public ActionForward getHouseWise(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		ArrayList<eventRegVo> house = new eventBD().getHouseWise(event_id,catId,progId);
		JSONObject obj = new JSONObject();
		obj.put("data", house);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}
public ActionForward getTableByHouse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		String houseId = request.getParameter("houseId");
		String accId = request.getParameter("accId");
		String stageId = request.getParameter("stageId");
		
		if(progId==null || progId.equalsIgnoreCase("")) {
			progId="%%";
		}
		ArrayList<eventRegVo> stagelist = new eventBD().getTableByHouse(event_id,catId,progId,houseId,accId,stageId);
		
		JSONObject obj = new JSONObject();
		obj.put("data", stagelist);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}

public ActionForward EventParticipentListPDF(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

	try {

		System.out.println("downloading pdf");
		String event_id = request.getParameter("eventId");
		String catId = request.getParameter("catid");
		String progId = request.getParameter("programmeId");
		String houseId = request.getParameter("houseId");
		String accId = request.getParameter("accId");
		String stageId = request.getParameter("stageId");
		System.out.println(event_id);
		System.out.println(catId);
		System.out.println(progId);
		System.out.println(accId);
		
		eventRegVo vo=new eventBD().getPartEventDetail(event_id,catId,progId,houseId,stageId);
		ArrayList<eventRegVo> stagelist=null;
		if(!houseId.equalsIgnoreCase("")){
			 stagelist = new eventBD().getTableByHouse(event_id,catId,progId,houseId,accId,stageId);
			
		}else{
			 stagelist = new eventBD().getTableByData(event_id,catId,progId,houseId,accId);
		}
		
		
		
		String sourceFileName = request.getRealPath("Reports/EventParticipantListPDF.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stagelist);

		//String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

		String schName = res.getString("SchoolName");
		
		System.out.println("SCHOOL NAME "+schName);
		String EventAddress=res.getString("EventAdd");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("locationname", schName);
		parameters.put("EventAddress", EventAddress);
		parameters.put("EventName", vo.getEventName());
		parameters.put("Category", vo.getCategoryName());
		parameters.put("ProgrammeName", vo.getProgrammeName());
		parameters.put("Date", vo.getProg_date());
		parameters.put("Stage", vo.getStageName());
		
		byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "outline; filename=\""
				+ "Participant List - " + ".pdf\"");
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
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
	return null;
}

public ActionForward EventParticipentListEXCEL(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StreamDetailsAction : downloadStreamDetailsAction  Starting");

	try {
		System.out.println("DOWNLOADING EXCEL");
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request.getRealPath("Reports/FeeDetailsXLSReport.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		AddFeeVO vo = new AddFeeVO();
		vo.setName(request.getParameter("searchvalue"));
		ArrayList<AddFeeVO> List = new FeeMasterDelegate().getfeedetails(vo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(List);
		
		Map parameters = new HashMap();
		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "Stream Details Excel Report" };
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);
		exporter.exportReport();
		pdfxls = new File(request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment; filename=FeeDetailsXLSReport.xls");
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
			+ " Control in StreamDetailsAction : downloadStreamDetailsAction   Ending");
	return null;
}




public ActionForward getProgramSchedulingPdfReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramSchedulingPdfReport  Starting");

		try {

			String eventId = request.getParameter("eventNameList");
			if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
				eventId="%%";
			}
			
			String prog_date = request.getParameter("prog_date");
			
			
			
			ArrayList<eventRegVo> list = new eventBD().getProgramScheduledList(eventId,prog_date);
			System.out.println("list size in pdf>>"+list.size());
			String sourceFileName = request 
					.getRealPath("Reports/ProgramScheduledListPdf.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath =request.getRealPath("/")+ "images/" + ImageName.trim();

			Map parameters = new HashMap();
			parameters.put("prog_date", prog_date);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ProgramSchedulingPDF" + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();

		}catch (Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramSchedulingPdfReport  Ending");
		return null;

	}

public ActionForward getProgramScheduledList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingList Starting");
	try{
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		String prog_date = request.getParameter("prog_date");
		ArrayList<eventRegVo> list = new eventBD().getProgramScheduledList(eventId,prog_date);
		JSONObject obj= new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingList Ending");
	return null;
}
public ActionForward getProgramSchedulingExcelReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingExcelReport Starting");
	

	
	String filePath = null;

	try {
		
		
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		String prog_date = request.getParameter("prog_date");
		if(prog_date.equalsIgnoreCase("all") || prog_date.equalsIgnoreCase(""))
		{
			prog_date="%%";
		}
		
		
		
		
		
		ArrayList<eventRegVo> data = new eventBD().getProgramScheduledList(eventId,prog_date);
		ArrayList<ReportMenuVo> datalist = new ReportsMenuBD().getAccYears();
		System.out.println("data length>>>"+data.size());
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
		
		Map mapdata = new HashMap();

	
		
		
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/ProgramScheduledListExcel.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		

		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				mapdata, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/ProgramScheduledlist.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "Program ScheduledList Details" };
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

		pdfxls = new File(request.getRealPath("Reports/ProgramScheduledlist.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=ProgramScheduledlist.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}
		stream.close();

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSchedulingExcelReport Ending");

	return null;
}

public ActionForward printProgramScheduledList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : printProgramScheduledList Starting");
	try{
		String eventId = request.getParameter("eventNameList");
		if(eventId.equalsIgnoreCase("all")  || eventId.equalsIgnoreCase("")){
			eventId="%%";
		}
		
		String prog_date = request.getParameter("prog_date");
		
	
		
		ArrayList<eventRegVo> data = new eventBD().getProgramScheduledList(eventId,prog_date);
		String sourceFileName = null;
		String termName = null;
		
		
	
		
		 sourceFileName = request
				.getRealPath("Reports/ProgramScheduledListPdf.jrxml");
		
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

		String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

		

		Map parameters = new HashMap();
	
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		viewer.setVisible(true);
		
		PrinterJob job = PrinterJob.getPrinterJob();
		   int selectedService = 0;
		   selectedService = 0;
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return mapping.findForward(MessageConstants.programScheduling);
}



public ActionForward getparticipanteventList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction :getparticipanteventList Starting");

	
	try {
		
		String event_name=request.getParameter("eventname");
		
		
		ArrayList<eventRegVo> list = new eventBD().getparticipanteventList(event_name);
		
			
			JSONObject obj1= new JSONObject();
			obj1.put("eventlist",list);
			response.getWriter().print(obj1);

	}catch(Exception e){
		e.printStackTrace();
	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction: StudentList ending"
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction: getparticipanteventList ending");

	return null;
}


public ActionForward ChestNoGeneration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
try{
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_CHESTNUMBERPRINTING);
		
	request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
	request.setAttribute("locationList", HelperClass.getAllLocation());
	ArrayList<eventRegVo> eventName = new eventBD().getEventName();
	request.setAttribute("eventList", eventName);
    String id = request.getParameter("id");
	ArrayList<eventRegVo> greennamelist = new eventBD().getGreenRoom(id);
    request.setAttribute("greennamelist",greennamelist);
	
}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");
	return mapping.findForward(MessageConstants.ChestnumberPrinting);
}
/*public ActionForward ParticepantReportPdf(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : ParticepantReportPdf  Starting");
	try{
		
		 
		ArrayList<eventRegVo> eventreport=(ArrayList<eventRegVo>)request.getSession(false).getAttribute("");
		 String sourceFileName = request
					.getRealPath("Reports/ParticipantReportPdf.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					eventreport);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("section",request.getSession(false).getAttribute("sectionName"));
			parameters.put("term",request.getSession(false).getAttribute("term"));
			parameters.put("class",request.getSession(false).getAttribute("classname"));
			parameters.put("accyear",request.getSession(false).getAttribute("accyear"));
			parameters.put("studentName",request.getSession(false).getAttribute("studentName"));
			parameters.put("totalAmount",request.getSession(false).getAttribute("totalAmount"));
			parameters.put("paidAmount",request.getSession(false).getAttribute("paidAmount"));
			parameters.put("balanceAmount",request.getSession(false).getAttribute("balanceAmount"));
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ParticipantReportPdf - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

			
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : ParticepantReportPdf  Ending");
	
	return null;
}*/

public ActionForward saveChestNoRegister(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveChestNoRegister Starting");
	try{
		String eventName= request.getParameter("eventName");
		String categoryName= request.getParameter("categoryName");
		String progName= request.getParameter("progName");
		String hiddenStageId= request.getParameter("hiddenStageId");
		
		eventRegPojo pojo= new eventRegPojo();
		
		pojo.setEventId(eventName);
		pojo.setCategoryId(categoryName);
		pojo.setProgName(progName);
		pojo.setHiddenStageId(hiddenStageId);
		
		String result = new eventBD().saveChestNoRegister(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);
	}catch(Exception e){
			e.printStackTrace();
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveChestNoRegister Ending");
	return null;
}

public ActionForward getChestNoList(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSettingList Starting");
	try{
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId");
		
		if(evId.equalsIgnoreCase("all")  || evId.equalsIgnoreCase("")){
			evId="%%";
		}
		if(catId.equalsIgnoreCase("all")  || catId.equalsIgnoreCase("")){
			catId="%%";
		}
		if(progId.equalsIgnoreCase("all")  || progId.equalsIgnoreCase("")){
			progId="%%";
		}
		ArrayList<eventRegVo> mainList = new eventBD().getChestNoList(evId,catId,progId);
		JSONObject obj=new JSONObject();
		obj.put("data", mainList);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramSettingList Ending");
	return null;
}

public ActionForward saveGeneratedchestNo(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveGeneratedchestNo Starting");
	try{
		String[] admissionNo= request.getParameter("admisionNoId").split(",");
		String[] chestNo= request.getParameter("chestNoId").split(",");
		
		eventRegPojo pojo= new eventRegPojo();
		
		pojo.setAdmissionNoArray(admissionNo);
		pojo.setChestNoArray(chestNo);
		
		String result = new eventBD().saveGeneratedchestNo(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);
	}catch(Exception e){
			e.printStackTrace();
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : saveGeneratedchestNo Ending");
	return null;
}
public ActionForward getEventStudentRegChestNoList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");
		try {
			System.out.println("Inside Action");
			
			String accyear =request.getParameter("Acyearid");
			String evId =request.getParameter("evId");
			String catId =request.getParameter("catId");
			String progId =request.getParameter("progId");
			
			
			if(evId.equalsIgnoreCase("all")|| evId=="" || evId==null){
				evId="%%";
			}
			if(catId.equalsIgnoreCase("all")|| catId=="" || catId==null){
				catId="%%";
			}
			if(progId.equalsIgnoreCase("all")|| progId=="" || progId==null){
				progId="%%";
			}
			Set<eventRegVo> mainList = new HashSet<eventRegVo>();
			mainList =  new eventBD().getEventStudentRegChestNoList(evId,catId,progId,accyear);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}

public ActionForward getEventNameList(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Starting");
	try{
		ArrayList<eventRegVo> list = new eventBD().getEventNameList();
		
		JSONObject obj = new JSONObject();
		obj.put("data",list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Ending");
	return null;
}
public ActionForward getVolunteerList(ActionMapping mapping, ActionForm from, HttpServletRequest request, HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getVolunteerList Starting");
	try{
		String eveId = request.getParameter("eveId");
		String locId = request.getParameter("locId");
		
		
		if(eveId.equalsIgnoreCase("all")  || eveId.equalsIgnoreCase("")){
			eveId="%%";
		}
		if(locId.equalsIgnoreCase("all")  || locId.equalsIgnoreCase("")){
			locId="%%";
		}
		
		System.out.println(eveId);
		System.out.println(locId);
	
		
		ArrayList<eventRegVo> result = new eventBD().getVolunteerList(eveId,locId);
		
		JSONObject obj=new JSONObject();
		obj.put("data", result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
			e.printStackTrace();
			
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getVolunteerList Ending");
	return null;
}
public ActionForward getDataforUpdateVolanteer(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateVolanteer Starting");
	try{
		String id=request.getParameter("id");
		ArrayList<eventRegVo>list =new eventBD().getDataforUpdateVolanteer(id);
		JSONObject obj =new JSONObject();
		obj.put("data",list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateVolanteer Ending");
	return null;
}
public ActionForward savevolunteer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Starting");
	try{
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
	/*request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
			LeftMenusHighlightMessageConstant.MODULE_EVENTS_PROGRAMSETTING);*/
		
	String eventName= request.getParameter("eventName");
	String schoolNameList= request.getParameter("schoolNameList");
	String volanteeradmissionno= request.getParameter("volanteeradmissionno");
	String volanteername= request.getParameter("volanteerName");
	String greenroomName= request.getParameter("greenroomName");
	String stagename= request.getParameter("stagename");
	String starttime= request.getParameter("starttime");
	String endtime= request.getParameter("endtime");
	String startsOn= request.getParameter("startsOn");
	String endsOn= request.getParameter("endsOn");
	String createdUser = HelperClass.getCurrentUserID(request);
	String EditUser = HelperClass.getCurrentUserID(request);
	String volnteerhiddenid = request.getParameter("volnteerhiddenid");
	
	eventRegPojo pojo= new eventRegPojo();
	pojo.setEventId(eventName);
	pojo.setVolanteername(volanteername);
	pojo.setLocation(schoolNameList);
	pojo.setVolanteeradmissionno(volanteeradmissionno);
	pojo.setGreenRoomName(greenroomName);
	pojo.setStageName(stagename);
	pojo.setStarttime(starttime);
	pojo.setEndtime(endtime);
	pojo.setCreateUser(createdUser);
	pojo.setStartsOn(startsOn);
	pojo.setEndsOn(endsOn);
	pojo.setEditUser(EditUser);
	
	pojo.setVolanteerhiddenid(volnteerhiddenid);
	String result = new eventBD().savevolunteer(pojo);
	
	JSONObject obj = new JSONObject();
	obj.put("status", result);
	response.getWriter().print(obj);
	
	
	}	catch(Exception e){
		e.printStackTrace();
	}
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : studentRegistration Ending");
	return null;
}

public ActionForward getEventName(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Starting");
	try{
		String id = request.getParameter("id");
		if(id.equalsIgnoreCase("all")|| id=="" || id==null){
			id="%%";
		}
		System.out.println(id);
		ArrayList<eventRegVo> list = new eventBD().getEventName(id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getGreenRoom Ending");
	return null;
}

public ActionForward deletevolanteer(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deletevolanteer Starting");
	try{
		String id=request.getParameter("id");
		String data =new eventBD().deletevolanteer(id);
		JSONObject obj =new JSONObject();
		obj.put("status", data);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : deletevolanteer Ending");
	return null;
}

public ActionForward getAdmissionNoByVolanteer(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateProgram Starting");
	try{
		/*String admissionno=request.getParameter("admissionno");
		eventRegVo requestingNo=new eventRegVo();
		requestingNo.setVolanteerAdmissionNo(admissionno);
		searchadmissionNo =new eventBD().getAdmissionNoByVolanteer(requestingNo);*/
		String searchterm = request.getParameter("searchTerm");
		ArrayList<eventRegVo> data = new EventsDaoImpl().getAdmissionNoByVolanteer(searchterm);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(MessageConstants.JSON_RESPONSE,data);
		response.getWriter().print(jsonObject);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getDataforUpdateProgram Ending");
	return null;
}

public ActionForward EventParticipentListPrint(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

	try {

		System.out.println("downloading pdf");
		String event_id = request.getParameter("eventId");
		String catId = request.getParameter("catid");
		String progId = request.getParameter("programmeId");
		String houseId = request.getParameter("houseId");
		String accId = request.getParameter("accId");
		String stageId = request.getParameter("stageId");
		System.out.println(event_id);
		System.out.println(catId);
		System.out.println(progId);
		System.out.println(accId);
		ArrayList<eventRegVo> stagelist=null;
		eventRegVo vo=new eventBD().getPartEventDetail(event_id,catId,progId,houseId,stageId);
		if(!houseId.equalsIgnoreCase("")){
			 stagelist = new eventBD().getTableByHouse(event_id,catId,progId,houseId,accId,stageId);
			
		}else{
			 stagelist = new eventBD().getTableByData(event_id,catId,progId,houseId,accId);
		}
		String sourceFileName = request.getRealPath("Reports/EventParticipantListPDF.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stagelist);

		//String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

		String schName = res.getString("SchoolName");
		
		System.out.println("SCHOOL NAME "+schName);
		System.out.println("Stage NAME "+vo.getStageName());
		String EventAddress=res.getString("EventAdd");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("locationname", schName);
		parameters.put("EventAddress", EventAddress);
		parameters.put("EventName", vo.getEventName());
		parameters.put("Category", vo.getCategoryName());
		parameters.put("ProgrammeName", vo.getProgrammeName());
		parameters.put("Date", vo.getProg_date());
		parameters.put("Stage", vo.getStageName());
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		System.out.println("JASPER VIVER");
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		System.out.println("AFTER JASPER VIVER");
		viewer.setVisible(true);
		PrinterJob job = PrinterJob.getPrinterJob();
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);

	}

	catch (Exception e)

	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
	return null;
	
}
public ActionForward getProgramNumberingExcelReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramNumberingExcelReport Starting");
	

	
	String filePath = null;

	try {
		
		
		String event=request.getParameter("event");
		if(event.equalsIgnoreCase("all")||event.equalsIgnoreCase(""))
				{
			event="%%";
				}
		String stage=request.getParameter("stage");
		if(stage.equalsIgnoreCase("all")||stage.equalsIgnoreCase(""))
				{
			stage="%%";
				}
		ArrayList<eventRegVo> list = new eventBD().getProgramNumberingList(event,stage);
		ArrayList<ReportMenuVo> datalist = new ReportsMenuBD().getAccYears();
		System.out.println("list  length in excel>>>"+list.size());
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		
		Map mapdata = new HashMap();

	

		
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/ProgramNumberingExcel.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		

		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				mapdata, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/ProgramNumberingExcel.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "Program Numbering List" };
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

		pdfxls = new File(request.getRealPath("Reports/ProgramNumberingExcel.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=ProgramNumberingExcel.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}
		stream.close();

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramNumberingExcelReport Ending");

	return null;
}
public ActionForward getProgramNumberingPdfReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramNumberingPdfReport  Starting");

		try {
///ARYA_CENTRAL_SCHOOL/WebContent/Reports/ProgramNumberingPdfList.jrxml
			String event=request.getParameter("event");
			if(event.equalsIgnoreCase("all")||event.equalsIgnoreCase(""))
			{
				event="%%";
			}
			String stage=request.getParameter("stage");
			if(stage.equalsIgnoreCase("all")||stage.equalsIgnoreCase(""))
			{
				stage="%%";
			}
			ArrayList<eventRegVo> list = new eventBD().getProgramNumberingList(event,stage);
			System.out.println("action list size>>"+list.size());
			String sourceFileName = request 
					.getRealPath("Reports/ProgramNumberingPdfList.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = getServlet().getServletContext().getRealPath("")
					+ "\\images\\" + ImageName.trim();

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			
			

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ProgramNumberingListPDF" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}catch (Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getProgramNumberingPdfReport  Ending");
		return null;

	}

public ActionForward printProgramNumberingList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : printProgramNumberingList Starting");
	try{
		String event=request.getParameter("event");
		String stage=request.getParameter("stage");
		ArrayList<eventRegVo> list = new eventBD().getProgramNumberingList(event,stage);
		String sourceFileName = null;
		String termName = null;
		
		
	
		
		 sourceFileName = request
				.getRealPath("Reports/ProgramNumberingPdfList.jrxml");
		
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

		String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

		

		Map parameters = new HashMap();
	
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		viewer.setVisible(true);
		
		PrinterJob job = PrinterJob.getPrinterJob();
		   int selectedService = 0;
		   selectedService = 0;
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return mapping.findForward(MessageConstants.programNumbering);
}

public ActionForward getStageAllocatedParticipants(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Eventaction : getStageAllocatedParticipants: Starting");
	           System.out.println("whether it is coming inside the event action:");
		try {
			String stageVal =request.getParameter("stage");
			String event =request.getParameter("event");
			ArrayList<eventRegVo> mappedParticipants= new eventBD().getStageAllocatedParticipants(event,stageVal);
			request.setAttribute("mappedParticipants", mappedParticipants);

			JSONObject object = new JSONObject();
			object.put("list", mappedParticipants);
			response.getWriter().print(object);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Eventaction : getMappedParticipants: Ending");
		return null;
	}

public ActionForward getStageAllocatedPdfReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getStageAllocatedPdfReport  Starting");

		try {
			//ArrayList<eventRegVo> mappedParticipants= new eventBD().getMappedParticipants(stageVal);
			//request.setAttribute("mappedParticipants", mappedParticipants);

		
			String stageVal =request.getParameter("stage");
			String event =request.getParameter("event");
			ArrayList<eventRegVo> list= new eventBD().getMappedParticipants(stageVal);
			
			System.out.println("list size in pdf>>>"+list.size());
			String sourceFileName = request 
					.getRealPath("Reports/StageAllocatedList.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = getServlet().getServletContext().getRealPath("")
					+ "\\images\\" + ImageName.trim();

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			
			///ARYA_CENTRAL_SCHOOL/src/com/centris/campus/vo/eventRegVo.java

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StageAllocatedList" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}catch (Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getStageAllocatedPdfReport  Ending");
		return null;

	}

public ActionForward getStageAllocatedExcelReport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getStageAllocatedExcelReport Starting");
	

	
	String filePath = null;

	try {
		
		
		String stageVal =request.getParameter("stage");
		String event =request.getParameter("event");
		ArrayList<eventRegVo> list= new eventBD().getMappedParticipants(stageVal);
		
		System.out.println("list size in excel>>"+list.size());
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		
		Map mapdata = new HashMap();

	
		File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;

		String sourceFileName = request
				.getRealPath("Reports/StageAllocatedListExcel.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		

		stream = response.getOutputStream();
		JasperPrint print = JasperFillManager.fillReport(jasperreport,
				mapdata, beanColDataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(
				request.getRealPath("Reports/StageAllocatedListExcel.xls"));
		FileOutputStream fos = new FileOutputStream(outputFile);
		String[] sheetNames = { "Stage Allocated Participant List" };
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

		pdfxls = new File(request.getRealPath("Reports/StageAllocatedListExcel.xls"));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=StageAllocatedListExcel.xls");
		response.setContentLength((int) pdfxls.length());
		input = new FileInputStream(pdfxls);
		buf = new BufferedInputStream(input);
		int readBytes = 0;
		stream = response.getOutputStream();
		while ((readBytes = buf.read()) != -1) {
			stream.write(readBytes);
		}
		stream.close();

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getStageAllocatedExcelReport Ending");

	return null;
}

public ActionForward printStageAllocatedList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : printStageAllocatedList Starting");
	try{
		String stageVal =request.getParameter("stage");
		String event =request.getParameter("event");
		ArrayList<eventRegVo> list= new eventBD().getMappedParticipants(stageVal);
		System.out.println("list size in print>>"+list.size());
		String sourceFileName = null;
		String termName = null;
		
		 sourceFileName = request
				.getRealPath("Reports/StageAllocatedList.jrxml");
		
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

		String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

		

		Map parameters = new HashMap();
	
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		viewer.setVisible(true);
		
		PrinterJob job = PrinterJob.getPrinterJob();
		   int selectedService = 0;
		   selectedService = 0;
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return mapping.findForward(MessageConstants.stageAllocation);
}

public ActionForward meritCertificatePrint(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception{

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

	try {
		String event_id=request.getParameter("evId");
		String catId=request.getParameter("catId");
		String progId=request.getParameter("progId");
		String certificateon=request.getParameter("certificateon");
		String accId=request.getParameter("accId");
		String place=request.getParameter("place");
		String admissionNo=request.getParameter("admissionNo");
		String classsec=request.getParameter("classsec");
		String StdName=request.getParameter("StdName");
		System.out.println("STUDENT NAME "+StdName);
		
		
		eventRegVo vo=new eventBD().getEventCertificateDetail(event_id, catId, progId,accId);
		ArrayList<eventRegVo> stagelist = new eventBD().getCertificateDetail(event_id,catId,progId,accId);
		String sourceFileName=null;
		if(certificateon.equalsIgnoreCase("merit")){
		    sourceFileName = request.getRealPath("Reports/Certificate.jrxml");
		}else{
			 sourceFileName = request.getRealPath("Reports/ParticipationCertificate.jrxml");
		}
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stagelist);
		String PropfilePath = request.getRealPath("/")+"images/"+ ImageName.trim();
		String schName = res.getString("SchoolName");
		String left = request.getRealPath("/")+"images/"+res.getString("Certificate3").trim();
		String left1 = request.getRealPath("/")+"images/"+res.getString("Certificate4").trim();
		String right1 = request.getRealPath("/")+"images"+res.getString("Certificate1").trim();
		String right = request.getRealPath("/")+"/images/"+res.getString("Certificate2").trim();
		System.out.println("SCHOOL NAME "+schName);
		String EventAddress=res.getString("EventAdd");
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		System.out.println(vo.getAccyear());
		parameters.put("PropfilePath",PropfilePath);
		parameters.put("locationname", schName);
		parameters.put("admissionNo", admissionNo);
		parameters.put("classsec", classsec);
		parameters.put("place", place);
		parameters.put("StdName", StdName);
		parameters.put("EventName", vo.getEventName());
		parameters.put("ProgName", vo.getProgrammeName());
		parameters.put("Accyear", vo.getAccyear());
		parameters.put("Prog_date", vo.getProg_date());
	/*	parameters.put("acs", acc[0] );
		parameters.put("ace", acc[1]);*/
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		viewer.setVisible(true);
		PrinterJob job = PrinterJob.getPrinterJob();
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);
	}
	catch (Exception e)
	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
	return null;
}


public ActionForward getTableOnCertificate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Starting");
	try{
		String event_id=request.getParameter("evId");
		String catId=request.getParameter("catId");
		String progId=request.getParameter("progId");
		String certificateon=request.getParameter("certificateon");
		String accId=request.getParameter("accId");
		ArrayList<eventRegVo> stagelist = new eventBD().getStudents(event_id,catId,progId,accId,certificateon);
		JSONObject obj = new JSONObject();
		obj.put("data", stagelist);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getcategorylist Ending");
	return null;
}

public ActionForward getCategoryName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Starting");
	try{
		
		String id = request.getParameter("id");
		String group = request.getParameter("flag");
		ArrayList<eventRegVo> categoryName = new eventBD().getCategoryName(id,group);
		
		JSONObject obj = new JSONObject();
		obj.put("data", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Ending");
	return null;
}

public ActionForward judgeentSheetPrint(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");

	try {

		System.out.println("downloading pdf");
		String event_id = request.getParameter("eventId");
		String catId = request.getParameter("catid");
		String progId = request.getParameter("programmeId");
		String houseId = request.getParameter("houseId");
		String accId = request.getParameter("accId");
		System.out.println(event_id);
		System.out.println(catId);
		System.out.println(progId);
		System.out.println(accId);
		
		eventRegVo vo=new eventBD().getEventDetail(event_id,catId,progId,houseId);
		ArrayList<eventRegVo> list=new eventBD().getCriteriaForJudgementSheet(event_id,progId,catId);
		
		String sourceFileName = request.getRealPath("Reports/EventJudgementSheet.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager.compileReport(design);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

		String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

		String schName = res.getString("SchoolName");
		
		System.out.println("SCHOOL NAME "+schName);
		String EventAddress=res.getString("EventAdd");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("locationname", schName);
		parameters.put("EventAddress", EventAddress);
		parameters.put("EventName", vo.getEventName());
		parameters.put("Category", vo.getCategoryName());
		parameters.put("ProgrammeName", vo.getProgrammeName());
		parameters.put("Date", vo.getProg_date());
		parameters.put("Stage", vo.getStageName());
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		System.out.println("JASPER VIVER");
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		System.out.println("AFTER JASPER VIVER");
		viewer.setVisible(true);
		PrinterJob job = PrinterJob.getPrinterJob();
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);

	}

	catch (Exception e)

	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeConcession : ConcessionDetailsPDFReport  Ending");
	return null;
	
}
public ActionForward getEventStudentMeritList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("evId");
			String progId =request.getParameter("progId");
			String catId =request.getParameter("catId");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			
			System.out.println(evId);
			System.out.println(progId);
			System.out.println(catId);
			System.out.println(houseId);
			

			
			ArrayList<eventRegVo> mainList = new eventBD().getEventStudentMeriList(evId,progId,catId,houseId,accId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}

public ActionForward getEventStudentMeritListByOnlyEvent(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("evId");
			String progId =request.getParameter("progId");
			String catId =request.getParameter("catId");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			int place= Integer.parseInt(request.getParameter("place"));
			
			System.out.println(evId);
			System.out.println(progId);
			System.out.println(catId);
			System.out.println(houseId);
			

			
			ArrayList<eventRegVo> mainList = new eventBD().getEventStudentMeritListByOnlyEvent(evId,progId,catId,houseId,accId,place);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}
public ActionForward getEventStudentMeritListByProgramme(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("evId");
			String progId =request.getParameter("progId");
			String catId =request.getParameter("catId");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			String resultType= request.getParameter("resultType");
			
			
			

			
			ArrayList<eventRegVo> mainList = new eventBD().getEventStudentMeritListByProgramme(evId,progId,catId,houseId,accId,resultType);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}
public ActionForward EventResultDownloadPDF(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("eventId");
			String progId =request.getParameter("programmeId");
			String catId =request.getParameter("catid");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			int place= Integer.parseInt(request.getParameter("place"));
			
			System.out.println(evId);
			System.out.println(progId);
			System.out.println(catId);
			System.out.println(place);
			

			eventRegVo vo=new eventBD().getEventDetail(evId,catId,progId,houseId);
			
			ArrayList<eventRegVo> mainList = new eventBD().EventResultPrint(evId,progId,catId,houseId,accId,place);
			
			String sourceFileName = request.getRealPath("Reports/EventResultPrintingPDFdownload.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(mainList);

			//String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			
			System.out.println("SCHOOL NAME "+schName);
			String EventAddress=res.getString("EventAdd");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("locationname", schName);
			parameters.put("EventAddress", EventAddress);
			parameters.put("EventName", vo.getEventName());
			parameters.put("Category", vo.getCategoryName());
			parameters.put("ProgrammeName", vo.getProgrammeName());
			parameters.put("Date", vo.getProg_date());
			parameters.put("Stage", vo.getStageName());
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Event Result - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}

public ActionForward EventResultPrint(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("eventId");
			String progId =request.getParameter("programmeId");
			String catId =request.getParameter("catid");
			String houseId =request.getParameter("houseId");
			String accId= request.getParameter("accId");
			int place= Integer.parseInt(request.getParameter("place"));
			
			System.out.println(evId);
			System.out.println(progId);
			System.out.println(catId);
			System.out.println(place);
			

			eventRegVo vo=new eventBD().getEventDetail(evId,catId,progId,houseId);
			
			ArrayList<eventRegVo> mainList = new eventBD().EventResultPrint(evId,progId,catId,houseId,accId,place);
			
			String sourceFileName = null;
			String termName = null;
			sourceFileName = request.getRealPath("Reports/EventResultPrintingPDFdownload.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(mainList);
			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();
			String schName = res.getString("SchoolName");
			System.out.println("SCHOOL NAME "+schName);
			String EventAddress=res.getString("EventAdd");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("locationname", schName);
			parameters.put("EventAddress", EventAddress);
			parameters.put("EventName", vo.getEventName());
			parameters.put("Category", vo.getCategoryName());
			parameters.put("ProgrammeName", vo.getProgrammeName());
			parameters.put("Date", vo.getProg_date());
			parameters.put("Stage", vo.getStageName());
			
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}
public ActionForward getEventStudentOverallResult(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String evId =request.getParameter("evId");
			
			
			ArrayList<eventRegVo> mainList = new eventBD().getEventStudentOverallResult(evId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", mainList);
			response.getWriter().print(obj);
			
			
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}


public ActionForward getProgramNameByCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Starting");
	try{
		
		String eventid = request.getParameter("eventid");
		String categoryid = request.getParameter("categoryid");
		if(eventid.equalsIgnoreCase("all")|| eventid=="")
		{
			eventid="%%";
		}
		if(categoryid.equalsIgnoreCase("all")|| categoryid=="")
		{
			categoryid="%%";
		}
		ArrayList<eventRegVo> programName = new eventBD().getProgramNameByCategory(eventid,categoryid);
		
		JSONObject obj = new JSONObject();
		obj.put("data", programName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Ending");
	return null;
}

public ActionForward getCategoryNameByEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Starting");
	try{
		
		String id = request.getParameter("id");
		ArrayList<eventRegVo> categoryName = new eventBD().getCategoryNameByEvent(id);
		
		JSONObject obj = new JSONObject();
		obj.put("data", categoryName);
		response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getCategoryName Ending");
	return null;
}

public ActionForward printChestNo(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String accyear = HelperClass.getCurrentYearID();
			String evId =request.getParameter("eventId");
			String progId =request.getParameter("programmeId");
			String catId =request.getParameter("catid");

			if(evId.equalsIgnoreCase("all") || evId.equalsIgnoreCase("")){
				evId = "%%";
			}
			if(progId.equalsIgnoreCase("all") || progId.equalsIgnoreCase("")){
				progId = "%%";
			}
			if(catId.equalsIgnoreCase("all") || catId.equalsIgnoreCase("")){
				catId = "%%";
			}
			eventRegVo obj = new eventRegVo();
			obj.setEventId(evId);
			obj.setProgId(progId);
			obj.setCategoryId(catId);
			obj.setAccId(accyear);
			ArrayList<eventRegVo> list=new eventBD().printChestNo(obj);
			
			
			String sourceFileName = null;
			String termName = null;
			sourceFileName = request.getRealPath("Reports/EventOverallResult.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();
			String schName = res.getString("SchoolName");
			System.out.println("SCHOOL NAME "+schName);
			String EventAddress=res.getString("EventAdd");
			Map<String, Object> parameters = new HashMap<String, Object>();
			/*parameters.put("locationname", schName);
			parameters.put("EventAddress", EventAddress);
			parameters.put("EventName", vo.getEventName());
			parameters.put("Category", vo.getCategoryName());
			parameters.put("ProgrammeName", vo.getProgrammeName());
			parameters.put("Date", vo.getProg_date());
			parameters.put("Stage", vo.getStageName());*/
			
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}

public ActionForward getProgramDateByEventID(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramDateByEventID Starting");
	try{
		String id=request.getParameter("evId");
		ArrayList<eventRegVo>   list =new EventsDaoImpl().getProgramDateByEventID(id);
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getProgramDateByEventID Ending");
	return null;
}
public ActionForward checkIsHouseWiseForEventReg(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : checkIsHouseWiseForEventReg: Starting");
	try {
		String evId = request.getParameter("evId");
		String catId = request.getParameter("catId");
		String progId = request.getParameter("progId").split(",")[0];
		
		ArrayList<eventRegVo> housewise = new EventsDaoImpl().checkIsHouseWiseForEventReg(evId,catId,progId);
		
		JSONObject obj1= new JSONObject();
		obj1.put("data", housewise);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : checkIsHouseWiseForEventReg: Ending");
	return null;
}
	
	public ActionForward printChestNoexcel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String accyear = HelperClass.getCurrentYearID();
			String evId =request.getParameter("eventId");
			String progId =request.getParameter("programmeId");
			String catId =request.getParameter("catid");
			
			String programmeName =request.getParameter("programmeName");
			String eventName =request.getParameter("eventName");
			String categoryName =request.getParameter("categoryName");
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			if(evId.equalsIgnoreCase("all") || evId.equalsIgnoreCase("")){
				evId = "%%";
			}
			if(progId.equalsIgnoreCase("all") || progId.equalsIgnoreCase("")){
				progId = "%%";
			}
			if(catId.equalsIgnoreCase("all") || catId.equalsIgnoreCase("")){
				catId = "%%";
			}
			eventRegVo obj = new eventRegVo();
			obj.setEventId(evId);
			obj.setProgId(progId);
			obj.setCategoryId(catId);
			obj.setAccId(accyear);
			ArrayList<eventRegVo> list=new eventBD().printChestNo(obj);
			
			
			String sourceFileName = null;
			String termName = null;
			sourceFileName = request.getRealPath("Reports/ChestNoListReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();
			String schName = res.getString("SchoolName");
			System.out.println("SCHOOL NAME "+schName);
			
			String EventAddress=res.getString("EventAdd");
			
			System.out.println("EventAdd");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("locationname", schName);
			parameters.put("EventAddress", EventAddress);
			parameters.put("EventName", eventName);
			parameters.put("Category",categoryName);
			parameters.put("ProgrammeName",programmeName);
			parameters.put("Date", obj.getProg_date());
			parameters.put("Stage", obj.getStageName());
			
		/*	JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);*/
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StageAllocatedListExcel.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Stage Allocated Participant List" };
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

			pdfxls = new File(request.getRealPath("Reports/StageAllocatedListExcel.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StageAllocatedListExcel.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();


	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}


public ActionForward getAdmissionNoForEventStdReg(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getAdmissionNo Starting");

		try {
			String houseName = request.getParameter("houseName");
			if(houseName.equalsIgnoreCase("all")) {
				houseName="%%";
			}
			String categoryName =request.getParameter("category");
			String searchterm = request.getParameter("searchTerm");
			ArrayList<eventRegVo> data = new EventsDaoImpl().getAdmissionNoForEventStdReg(searchterm,categoryName,houseName);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, data);
			response.getWriter().print(jsonObject);
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getAdmissionNo ending");

		return null;
}


public ActionForward printChestNopdf(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventStudentRegList Starting");

		try {
			String accyear = HelperClass.getCurrentYearID();
			String evId =request.getParameter("eventId");
			String progId =request.getParameter("programmeId");
			String catId =request.getParameter("catid");
			
			String programmeName =request.getParameter("programmeName");
			String eventName =request.getParameter("eventName");
			String categoryName =request.getParameter("categoryName");

			if(evId.equalsIgnoreCase("all") || evId.equalsIgnoreCase("")){
				evId = "%%";
			}
			if(progId.equalsIgnoreCase("all") || progId.equalsIgnoreCase("")){
				progId = "%%";
			}
			if(catId.equalsIgnoreCase("all") || catId.equalsIgnoreCase("")){
				catId = "%%";
			}
			eventRegVo obj = new eventRegVo();
			obj.setEventId(evId);
			obj.setProgId(progId);
			obj.setCategoryId(catId);
			obj.setAccId(accyear);
			ArrayList<eventRegVo> list=new eventBD().printChestNo(obj);
			
			String sourceFileName = null;
			String termName = null;
			sourceFileName = request.getRealPath("Reports/ChestNoListReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();
			String schName = res.getString("SchoolName");
			System.out.println("SCHOOL NAME "+schName);
			
			
			String EventAddress=res.getString("EventAdd");
			
			System.out.println("EventAdd");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("locationname", schName);
			parameters.put("EventAddress", EventAddress);
			parameters.put("EventName", eventName);
			parameters.put("Category",categoryName);
			parameters.put("ProgrammeName",programmeName);
			parameters.put("Date", obj.getProg_date());
			parameters.put("Stage", obj.getStageName());
			
		/*	JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);*/
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters,beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Chest No List - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();

	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : getEventStudentRegList ending");

		return null;
}


public ActionForward getHouseNamebyCategoryId(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getHouseNamebyCategoryId: Starting");
	try {
		
		String catId = request.getParameter("catId");
		String evnId=request.getParameter("evnId");
		
		ArrayList<eventRegVo> housewise = new EventsDaoImpl().getHouseNamebyCategoryId(catId,evnId);
		JSONObject obj1= new JSONObject();
		obj1.put("data", housewise);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in Eventaction : getHouseNamebyCategoryId: Ending");
	return null;
}


public ActionForward getJudgeListforProgram(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getJudgeListforProgram Starting");
	try{
		ArrayList<eventRegVo>   list = new EventsDaoImpl().getJudgeListforProgram();
		
/*		ArrayList<eventRegVo> teacherName = new EventsDaoImpl().getTeacherName();
		request.setAttribute("teacherName", teacherName);*/
		
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getJudgeListforProgram Ending");
	return null;
}
public ActionForward getOverAllReport(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReport Starting");
	try{
		
		String eventID=request.getParameter("eventID");
		List<eventRegVo>   list = new EventsDaoImpl().getOverAllReport(eventID);
		
		System.out.println("fhgfghkfdh="+list.size());
		
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReport Ending");
	return null;
}


public ActionForward getOverAllReportMarksHouseAndCategory(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReportMarksHouseAndCategory Starting");
	try{
		
		String eventID=request.getParameter("eventID");
		List<eventRegVo>   list = new EventsDaoImpl().getOverAllReportMarksHouseAndCategory(eventID);
		
		System.out.println("fhgfghkfdh="+list.size());
		
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReportMarksHouseAndCategory Ending");
	return null;
}

public ActionForward getOverAllReportMarksHouse(ActionMapping mapping, ActionForm from, HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReportMarksHouse Starting");
	try{
		
		String eventID=request.getParameter("eventID");
		List<eventRegVo>   list = new EventsDaoImpl().getOverAllReportMarksHouse(eventID);
		
		System.out.println("fhgfghkfdh="+list.size());
		
		JSONObject obj =new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getOverAllReportMarksHouse Ending");
	return null;
}
public ActionForward getEventIdDesignList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventIdDesignList Starting");
	
	
	try {
		
		List<IDcardVo> list = new ArrayList<IDcardVo>();
		
		
		
		
		File folder = new File(request.getRealPath("/")+ "CSS/IdCard/");
		File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
    	  if(!listOfFiles[i].getName().contains("transport") && listOfFiles[i].getName().contains("EVR")){
    		  IDcardVo template=new IDcardVo();
    		  
    		  List<eventRegVo> templateNameList=new ArrayList<eventRegVo>();
    		  
    		  eventRegVo filterpojo=new eventRegVo();
    		  	filterpojo.setEventId(listOfFiles[i].getName().substring(0, 4));
  				templateNameList=new  EventsDaoImpl().getEventIdCardDesignList(filterpojo);
    		  
  			
  				if(templateNameList.size()>0){
  					template.setIdCardtemplateValue(listOfFiles[i].getName());
  					template.setIdCardtemplateName(templateNameList.get(0).getEventName());
  					list.add(template);
  				}
    	  }
      } 
    }
    JSONObject obj=new JSONObject();
	obj.put("List", list);
	response.getWriter().print(obj);
    
    
	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsAction : getEventIdDesignList Ending");
	return null;

}

}

