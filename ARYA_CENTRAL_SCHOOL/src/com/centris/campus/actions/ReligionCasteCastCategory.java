package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.delegate.StreamDetailsBD;
import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;

public class ReligionCasteCastCategory extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(ReligionCasteCastCategory.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	// Religion Caste CasteCategory

	public ActionForward addReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : addReligion Starting");

		try {
			String title ="Add New Religion";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : addReligion Ending");

		return mapping.findForward(MessageConstants.ADD_RELIGION);
	}

	public ActionForward addCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addCaste Starting");

		try {
			
			String title ="Add New Caste";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			request.setAttribute("religiondetails",
					new ReligionCasteCasteCategoryBD().getReligionDetails());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addCaste Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE);
	}

	public ActionForward addCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addCasteCatogory Starting");

		try {
			
			String title ="Add New CasteCategory";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);
			
			request.setAttribute("religiondetails",new ReligionCasteCasteCategoryBD().getReligionDetails());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addCasteCatogory Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE_CATEGORY);
	}

	public ActionForward insertReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : insertReligion Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);

			String religionId = request.getParameter("religionId");
			String religion = request.getParameter("religion");
			String hiddenreligion=request.getParameter("hiddenreligion");

			System.out.println("hiddenreligion : " + hiddenreligion);

			detailsForm.setReligionId(religionId);
			detailsForm.setReligion(religion);
			detailsForm.setHiddenreligion(hiddenreligion);
			detailsForm.setCreateUser(createCode);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String result = detailsBD.insertReligion(detailsForm);
			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : insertReligion  Ending");
		return null;

	}

	public ActionForward editReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editReligion  Starting");
		try {
			
			String title = "Modify Religion";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

			String religionId = request.getParameter("religionId");
			System.out.println("Action class Religion Id: "
					+ request.getParameter("religionId"));
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setReligionId(religionId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleReligion(detailsPojo);
			System.out.println("action class getSingleReligion: "+ ckeck.getReligion());
			request.setAttribute("religionList", ckeck);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editReligion   Ending");

		return mapping.findForward(MessageConstants.ADD_RELIGION);

	}

	public ActionForward deleteReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteReligion  Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			//String religionId = request.getParameter("religionId");
			
			String religionId[] = request.getParameterValues("religionList[]");
			System.out.println("locid"+religionId.length);

			System.out.println("religion deleted action class: "
					+ request.getParameter("religionId"));

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();

			detailsPojo.setReligionIdArray(religionId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String ckeck = detailsBD.deleteReligion(detailsPojo);

			request.setAttribute("delete", ckeck);

			JSONObject json = new JSONObject();

			json.put("status", ckeck);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteReligion   Ending");

		return null;

	}

	// caste
	public ActionForward deleteCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteReligion  Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String casteId[] = request.getParameterValues("casteList[]");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();

			detailsPojo.setCasteIdArray(casteId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String ckeck = detailsBD.deleteCaste(detailsPojo);

			request.setAttribute("delete", ckeck);

			JSONObject json = new JSONObject();

			json.put("status", ckeck);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteCaste   Ending");

		return null;

	}

	public ActionForward insertCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : insertCaste Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);

			String casteId = request.getParameter("religionId");
			String caste = request.getParameter("religion");
			String main_religion = request.getParameter("main_religion");
			String hiddencaste = request.getParameter("hiddencaste");
			System.out.println("hiddencaste :::"+hiddencaste);
			
			detailsForm.setCasteId(casteId);
			detailsForm.setCaste(caste);
			detailsForm.setCreateUser(createCode);
			detailsForm.setHiddencaste(hiddencaste);
			detailsForm.setMain_religion(main_religion);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String result = detailsBD.insertCaste(detailsForm);
			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : insertCaste  Ending");
		return null;

	}

	public ActionForward editCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editCaste  Starting");
		try {
			
			String title = "Modify Caste";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			String casteId = request.getParameter("casteId");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteId(casteId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD
					.getSingleCaste(detailsPojo);
			
			request.setAttribute("religiondetails",
					new ReligionCasteCasteCategoryBD().getReligionDetails());

			request.setAttribute("religionList", ckeck);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editCaste   Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE);

	}

	public ActionForward insertCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : insertCasteCategory Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);

			String religionId = request.getParameter("religionId");
			String casteId = request.getParameter("casteId");
			String casteCategory = request.getParameter("casteCategoryId");
			String casteCategoryIdVal=request.getParameter("castecateVal");
			String hiddencastecategory=request.getParameter("hiddencastecategory");
			System.out.println("hiddencastecategory ::"+hiddencastecategory);
			
			detailsForm.setReligionId(religionId);
			detailsForm.setCasteId(casteId);
			detailsForm.setCasteCategory(casteCategory);
			detailsForm.setCasteCatId(casteCategoryIdVal);
			detailsForm.setHiddencastecategory(hiddencastecategory);
			detailsForm.setCreateUser(createCode);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String result = detailsBD.insertCasteCategory(detailsForm);
			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : insertCasteCategory  Ending");
		return null;

	}

	public ActionForward deleteCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteCasteCategory  Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String casteCategoryId[] = request.getParameterValues("casteCategoryList[]");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();

			detailsPojo.setCasteCategoryIdArray(casteCategoryId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String ckeck = detailsBD.deleteCasteCategory(detailsPojo);

			request.setAttribute("delete", ckeck);

			JSONObject json = new JSONObject();

			json.put("status", ckeck);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteCasteCategory   Ending");

		return null;

	}

	public ActionForward editCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editCasteCategory  Starting");
		try {
			
			String title = "Modify CasteCategory";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);

			String casteCategoryId = request.getParameter("religionId");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteCategoryId(casteCategoryId);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleCasteCategory(detailsPojo);

			request.setAttribute("religionList", ckeck);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editCasteCategory   Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE_CATEGORY);

	}

	public ActionForward getReligionForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : getReligionForDropDown Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			List<ReligionCasteCasteCategoryVo> result = detailsBD.getReligionDetails();

			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : getReligionForDropDown  Ending");
		return null;

	}

	public ActionForward getCasteForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getCasteForDropDown Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String religionId=request.getParameter("religionId");
			System.out.println("DropdownCast Action: religion Id: "+religionId);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			
			List<ReligionCasteCasteCategoryVo> result = detailsBD.getCasteDetailsList(religionId);
			
			/*System.out.println("Action class Result: "+result);
			
	          JSONObject jsonobj = new JSONObject();
			


			List<ReligionCasteCasteCategoryVo> result = detailsBD
					.getCasteDetails();
*/
			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();


			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : getCasteForDropDown  Ending");
		return null;

	}

	public ActionForward getCasteCategoryForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : getCasteCategoryForDropDown Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String casteId=request.getParameter("casteId");
			
			System.out.println("casteid is "+casteId);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			List<ReligionCasteCasteCategoryVo> result = detailsBD.getCasteCategoryListDetails(casteId);

			System.out.println("Action class Result: " + result);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : getCasteCategoryForDropDown  Ending");
		return null;

	}

	public ActionForward addOccupation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : addOccupation Starting");

		try {
			
			String title="Add New Occupation";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : addOccupation Ending");

		return mapping.findForward(MessageConstants.ADD_OCCUPATION);
	}
	

	public ActionForward insertOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : insertOccupation Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();
			
			String createCode = HelperClass.getCurrentUserID(request);
			
			String occupationId = request.getParameter("occupationId");
			String occupation = request.getParameter("occupation");
			String hiddenoccupation = request.getParameter("hiddenoccupation");
			
			
			System.out.println("Action class insertOccupation() : "+ occupation);
			System.out.println("Action class insertOccupationId() : "+ occupationId);
			
			detailsForm.setOccupationId(occupationId);
			detailsForm.setOccupation(occupation);
			detailsForm.setHiddenoccupation(hiddenoccupation);
			detailsForm.setCreateUser(createCode);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			
			
			String result = detailsBD.insertOccupation(detailsForm);
			System.out.println("Action class Result: "+result);
			
	          JSONObject jsonobj = new JSONObject();
			
			jsonobj.put("jsonResponse", result);
			
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : insertOccupation  Ending");
	return null;

	}

	public ActionForward editOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editOccupation  Starting");
		try {
			
			String title ="Modify Occupation";
			request.setAttribute("title",title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);
			
			String occupationId = request.getParameter("occupationId");
			System.out.println("Action class Religion Id: "+request.getParameter("occupationId"));
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setOccupationId(occupationId);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleOccupation(detailsPojo);
			request.setAttribute("religionList", ckeck);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategory : editOccupation   Ending");
		
		 return mapping.findForward(MessageConstants.ADD_OCCUPATION);
	
	}
	

	public ActionForward deleteOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteOccupation  Starting");
	
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			String occupationId[] = request.getParameterValues("occupationList[]");
			
			System.out.println("religion deleted action class: "+request.getParameter("occupationId"));
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			
			detailsPojo.setOccupationIdArray(occupationId);
			System.out.println("Array Size of occupation id: "+detailsPojo.getOccupationIdArray().length);
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			
			String ckeck = detailsBD.deleteOccupation(detailsPojo);
			
			request.setAttribute("delete", ckeck); 
			
			JSONObject json= new JSONObject();
			
			json.put("status", ckeck);
		   
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteOccupation Ending");
		
		return null;
		
	}
		
}
