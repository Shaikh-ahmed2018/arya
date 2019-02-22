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
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class MenusAction extends DispatchAction{
	private static final Logger logger = Logger.getLogger(MenusAction.class);
	
	public ActionForward Admission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: Admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_ADMIN);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Admission Ending");
		
		
		return mapping.findForward("AdmissionLanding");
	}
	
	public ActionForward Settings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Setting Ending");
		
		
		return mapping.findForward("SettingLanding");
	}
	public ActionForward Student(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : storeAbsentSms Ending");
		
		
		return mapping.findForward("StudentLanding");
	}
	public ActionForward Staff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : storeAbsentSms Ending");
		
		
		return mapping.findForward("StaffLanding");
	}
	
	public ActionForward Fee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : storeAbsentSms Ending");
		
		
		return mapping.findForward("FeeLanding");
	}
	public ActionForward Exam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: Exam Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Exam Ending");
		
		
		return mapping.findForward("ExamLanding");
	}
	
	
	public ActionForward Leave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LEAVE);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : storeAbsentSms Ending");
		
		
		return mapping.findForward("LeaveLanding");
	}
	
	public ActionForward Transport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: admission Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : storeAbsentSms Ending");
		
		
		return mapping.findForward("TransportLanding");
	}
	
	public ActionForward Library(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: Library Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Library Ending");
		
		
		return mapping.findForward("LibraryLanding");
	}
	
	
	public ActionForward Event(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: Event Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Event Ending");
		
		
		return mapping.findForward("EventLanding");
	}
	
	public ActionForward Election(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction: Election Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_ELECTION);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in MenusAction : Election Ending");
		
		
		return mapping.findForward("ElectionLanding");
	}
}
