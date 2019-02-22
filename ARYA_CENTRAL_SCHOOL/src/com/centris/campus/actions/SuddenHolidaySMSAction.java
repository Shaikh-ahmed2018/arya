package com.centris.campus.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.SuddenHolidayListBD;
import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

public class SuddenHolidaySMSAction  extends DispatchAction{
	private static final Logger logger = Logger.getLogger(SuddenHolidaySMSAction.class);
	
	public ActionForward SuddenHolidayFilter(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : SuddenHolidayFilter : Starting");
		
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_SUDDENHOLIDAYS);
				
			ClassPojo pojo = new ClassPojo();
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo);
			request.setAttribute("classpojo", classpojo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : SuddenHolidayFilter : Ending");

		
		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_ENTRY);
		
		
	}
	
	public ActionForward AddSuddenHoliday(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : AddSuddenHoliday : Starting");
		
		
		try {
			
						
			SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();
			
			ClassPojo pojo = new ClassPojo();
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo);
			request.setAttribute("classpojo", classpojo);
			
			
			
			SuddenHolidayForm form2 = (SuddenHolidayForm)form;

			String[] classList = form2.getClassList().split(",");
			String[] secList = form2.getSectionList().split(",");
			
			form2.setClassListArray(classList);
			form2.setSectionListArray(secList);
			
			String createdUser = HelperClass.getCurrentUserID(request);
			form2.setCreatedUser(createdUser);

			
		
			String success = holidayListBD.AddSuddenHoliday(form2);
			
			if(success=="Holiday Details Sent Successfully")
			{
			
			request.setAttribute("message", success);
			}
			else
			{
				request.setAttribute("errormessage", success);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : AddSuddenHoliday : Ending");

		
		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_ENTRY);
		
		
	}
	
	
	
	public ActionForward validateSuddenHolidaysSms(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : AddSuddenHoliday : Starting");
		
		try {
						
			SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();
			
			String date =HelperClass.convertUIToDatabase(request.getParameter("date"));
			String smstext =request.getParameter("smstext");
			
			boolean status = holidayListBD.validateSuddenHolidaysSms( date,smstext);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : validateSuddenHolidaysSms : Ending");

		
		return null;
		
		
	}
	
	
	
	
	

}
