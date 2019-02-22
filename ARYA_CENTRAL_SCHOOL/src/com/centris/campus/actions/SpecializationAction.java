package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SpecializationBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.UserRolePermissionBD;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SpecializationVo;

public class SpecializationAction extends DispatchAction{
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static final Logger logger = Logger
			.getLogger(SpecializationAction.class);

	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward insertSpecialization(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : insertSpecialization Starting");
		try{
			
			SpecializationForm spec= new SpecializationForm();
			String createCode = HelperClass.getCurrentUserID(request);
			
			String streamId=request.getParameter("stream");
			String classId=request.getParameter("className");
			String specName=request.getParameter("specialization");
			String specId=request.getParameter("status");
			String locationId=request.getParameter("locationId");
			
			System.out.println(specId);
			
			spec.setStream_Id(streamId);
			spec.setClass_Id(classId);
			spec.setSpec_Name(specName);
			spec.setSpec_Id(specId);
			spec.setCreate_User(createCode);
			spec.setLocationId(locationId);
			
			SpecializationBD obj= new SpecializationBD();
			
			String result = obj.insertSpecialization(spec,specId);
			System.out.println("result"+result);
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
			
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SpecializationAction : insertSpecialization  Ending");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ActionForward editSpecialization(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String title = "Modify Specialization";
			request.setAttribute("title", title);
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			String edit = request.getParameter("specId");

			SpecializationVo edit_list = new SpecializationBD()
					.editSpecialization(edit);

			request.setAttribute("editlist",edit_list);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		 return mapping.findForward(MessageConstants.ADD_SPECIALIZATION);   
	}
	
	public ActionForward deleteSpec(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteSpec Starting");
		@SuppressWarnings("unused")
		String username = null;

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String specId[] = request.getParameterValues("specid[]");
			System.out.println("specId"+specId);
			
			String status = new SpecializationBD().deleteSpec(specId);
			
			
			
			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteDepartment Ending");

		return null;

	}
	

	public ActionForward getSpecializationOnClassBased(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			System.out.println("Specilization Action classId: "+request.getParameter("classId")+request.getParameter("locationId"));
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", specializationList);
				
				response.getWriter().print(jsonobj);
			
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Ending");

		 return null;   
	}
	
	public ActionForward validateSpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			String classId = request.getParameter("classId");
			String specialization = request.getParameter("specialization");
			String locationId=request.getParameter("locationId");

			System.out.println(classId);
			System.out.println(specialization);
			
			SpecializationForm form1= new SpecializationForm();

			form1.setClass_Id(classId);
			form1.setSpec_Name(specialization);
			form1.setLocationId(locationId);

			String spec_available = new SpecializationBD()
					.validateSpecialization(form1);

		
			
			JSONObject object = new JSONObject();
			object.put("status", spec_available);
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
	
	public ActionForward getSpecializationOnClassWithoutLocId (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassWithoutLocId(classId);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", specializationList);
				
				response.getWriter().print(jsonobj);
			
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getSpecializationOnClassBased Ending");

		 return null;   
	}
	

}
