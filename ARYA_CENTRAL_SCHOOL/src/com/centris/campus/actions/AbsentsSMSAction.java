/**
 * 
 */
package com.centris.campus.actions;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.delegate.AbsentSMSBD;
import com.centris.campus.forms.AbsentsSMSForm;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

/**
 * @author sathish
 *
 */
public class AbsentsSMSAction extends  DispatchAction {
	private static final Logger logger = Logger.getLogger(AbsentsSMSAction.class);
	
	public ActionForward storeAbsentSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentsSMSAction: storeAbsentSms Starting");
		try {
			
			
			
			
			AbsentsSMSForm absentform=(AbsentsSMSForm)form;
			
			String[] classList = absentform.getClassList().split(",");
			String[] secList = absentform.getSectionList().split(",");
			String[] stuList = absentform.getStudent().split(",");
			
			
			AbsentsSMSPojo absentpojo=new AbsentsSMSPojo();
			
			absentpojo.setCategoryid(absentform.getCategory());
			absentpojo.setClassid(classList);
			absentpojo.setSectionid(secList);
			absentpojo.setStudentid(stuList);
			absentpojo.setDate(HelperClass.convertUIToDatabase(absentform.getDate()));
			absentpojo.setSmstext(absentform.getSmstext());
			absentpojo.setCreatedby(HelperClass.getCurrentUserID(request));
			absentpojo.setCreatedate(HelperClass.getCurrentTimestamp());
			
			String status=new AbsentSMSBD().storeAbsentSms(absentpojo);
			
			
			request.setAttribute("message", status);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentsSMSAction : storeAbsentSms Ending");
		
		
		return mapping.findForward(MessageConstants.SMS_ABSENT_FILTER);
	}
	
	public ActionForward validateAbsentSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentsSMSAction: validateAbsentSms Starting");
		try {
			
			String date =HelperClass.convertUIToDatabase(request.getParameter("date"));
			String smstext =request.getParameter("smstext");
			
			
			boolean status=new AbsentSMSBD().validateAbsentSms( date,smstext);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentsSMSAction : validateAbsentSms Ending");
		
		
		return null;
	}
	
	
}