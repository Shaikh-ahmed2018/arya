package com.centris.campus.actions;

import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.LibraryDAOIMPL;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.LibraryDelegate;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StreamDetailsBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.eventBD;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySearchSubscriberPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;


import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.LibraryMessageConstants;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.LibraryJournalSubscriptionVo;
import com.centris.campus.vo.LibraryLocationVO;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.LibrarySearchSubscriberVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SubCategoryType1VO;
import com.centris.campus.vo.SubCategoryType2VO;
import com.centris.campus.vo.SubCategoryTypeVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.eventRegVo;
import com.itextpdf.text.log.SysoLogger;

public class LibraryAction extends DispatchAction {
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	

	private static String ImageName = res.getString("ImageName");
	private static String SchoolName = res.getString("SchoolName");
	
	
	
	private static final Logger logger = Logger.getLogger(LibraryAction.class);

	// .................... LIBRARY REPORTS START............................
	public ActionForward LibraryHome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute(
				"current_academicYear");

		String location = (String) request.getSession(false).getAttribute(
				"current_schoolLocation");
		
	
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_OVERDUESTATEMENT);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);

			if (location.equalsIgnoreCase("all")) {
				location = "%%";
				list = new StudentRegistrationDelegate()
				.getStudentLocationList(academic_year, location);
			} else {

				list = new StudentRegistrationDelegate().getStudentList(
						academic_year, location);
			}
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID();
			}
		     ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			ArrayList<AllTeacherDetailsVo> list1 = new ArrayList<AllTeacherDetailsVo>();
			list1 = new TeacherRegistrationBD().getAllTeacherDetails1();
			request.setAttribute("allTeacherDetailsList", list1);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD()
			.getClassDetails();

			request.setAttribute("classlist", classlist);

			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");
		return mapping.findForward(LibraryMessageConstants.OverDueStatement);
	}

	public ActionForward StockStatements(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKSTATEMENTS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList1", locationList);
			
			ArrayList<ReportMenuVo> locList = new LibraryDelegate().getLibraryLocation();
			request.setAttribute("locationList", locList);
			
		
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.StockStatements);
	}

	public ActionForward ReservationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

	
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_RESERVATIONLIST);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);

			 String accademinyear=(String)request.getSession(false).getAttribute("current_academicYear");
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
				request.setAttribute("locationList", locationList);

				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
				request.setAttribute("AccYearList", accYearList);
	               request.setAttribute("accademic_year", accademinyear);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.ReservationList);
	}

	// .................... LIBRARY REPORTS END............................

	public ActionForward generalSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_GENERALSETTINGS);
			String arg = "Add Genarel Settings";
			request.setAttribute("msg", arg);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.generalSettings);
	}

	public ActionForward publisherSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);
			
			String arg = "Add Publisher";
			request.setAttribute("msg", arg);
			LibraryStockEntryVO obj=new LibraryStockEntryVO();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.publisherSettings);
	}

	public ActionForward supplierSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUPPLIERSETTINGS);
			String arg = "Add Supplier ";
			request.setAttribute("msg", arg);
			 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.supplierSettings);
	}

	public ActionForward categorySettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_CATEGORYTYPE);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.categorySettings);
	}

	public ActionForward groupregistration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.groupRegistration);
	}

	public ActionForward overdue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.overDue);
	}

	public ActionForward issues(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_ISSUES);


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList",locationList);
			
			ArrayList<LibraryStockEntryVO> accessionNo = new LibraryDelegate().getAccessionNoList();
			request.setAttribute("AccessionList",accessionNo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");
		return mapping.findForward(LibraryMessageConstants.issues);
	}

	public ActionForward returns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_RETURNS);

			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			ArrayList<ReportMenuVo> accessionNo = new ReportsMenuBD().getaccessionNo();
			
			request.setAttribute("locationList", locationList);
			request.setAttribute("accessionNo", accessionNo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.returns);
	}

	public ActionForward reservations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_RESERVATIONS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			ArrayList<LibraryStockEntryVO> accessionNo = new LibraryDelegate().getAccessionNoList();
			request.setAttribute("AccessionList", accessionNo);
			
			LibraryDelegate obj = new LibraryDelegate();
			 String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
			List<LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = obj.getLibLocationsDetails(location);
			request.setAttribute("librarylocationsDetails", loclist);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.reservations);
	}

	public ActionForward stockstatements(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.stockStatements);
	}

	public ActionForward reservationlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.reservationList);
	}

	public ActionForward issuestatements(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_ISSUEORREISSUESTATEMENTS);

			String accyear =(String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("accyear",accyear);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);

			ArrayList<DepartmentMasterVO> deplist = new DepartmentMasterBD().getDepartmentDetails();
			request.setAttribute("deplist",deplist);

			AddDesignationVO vo = new AddDesignationVO();
			ArrayList<AddDesignationVO> DESIGNATIONLIST = new AddDesignationBD().DesignationDetails(vo);
			request.setAttribute("DESIGNATIONLIST",DESIGNATIONLIST);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.issueStatements);
	}

	public ActionForward journalreports(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_JOURNALREPORT);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.journalReports);
	}

	public ActionForward newarrivalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_NEWARRIVALLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.newarrivalList);
	}

	public ActionForward subscriberList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBSCRIBERLIST);
			
			String accyear =(String) request.getSession(false).getAttribute("current_academicYear");
			
			request.setAttribute("accyear",accyear);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);


			ArrayList<DepartmentMasterVO> deplist = new DepartmentMasterBD().getDepartmentDetails();
			request.setAttribute("deplist",deplist);

			
			AddDesignationVO vo = new AddDesignationVO();
			ArrayList<AddDesignationVO> DESIGNATIONLIST = new AddDesignationBD().DesignationDetails(vo);
			request.setAttribute("DESIGNATIONLIST",DESIGNATIONLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.subscriberList);
	}

	public ActionForward reissueStatements(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_REISSUESTATEMENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.reissueStatements);
	}

	public ActionForward searchCardprint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHCARDPRINT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.searchCardprint);
	}

	public ActionForward BlackListSubscriber(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : BlackListSubscriber Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_BLACKLISTEDSUBSCRIBER);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : BlackListSubscriber Ending");

		return mapping.findForward(LibraryMessageConstants.BLACKLISTSUBSCRIBER);
	}

	/*
	 * ............................barcode generation
	 * start........................
	 */
	public ActionForward barcodeGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_BARCODE);

			
			/*ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails();
			request.setAttribute("allTeacherDetailsList", list);*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.barcodeGeneration);
	}


	public ActionForward subscribersDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBSCRIBERDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");

		return mapping.findForward(LibraryMessageConstants.subscribersDetails);
	}

	public ActionForward StockEntryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);

			String msg="Stock Entry Details";
			request.setAttribute("msg", msg);
			 String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
          	LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getCategoryDetails();
			request.setAttribute("categoryDetails", list);
			
			List<LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = obj.getLibLocationsDetails(location);
			request.setAttribute("librarylocationsDetails", loclist);
			System.out.println(loclist);
			
			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
			request.setAttribute("publicationlist", publist);
			List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
			request.setAttribute("suplist", suplist);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
 
		return mapping.findForward(LibraryMessageConstants.StockEntryDetails);
	}

	public ActionForward SearchIssueDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHISSUEDETAILS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD()
			.getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
			.getAccYears();
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.ISSUE_DETAIL);
	}

	public ActionForward SearchSubscriberDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHSUBSCRIBER);
			
            String accademinyear=(String)request.getSession(false).getAttribute("current_academicYear");
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
               request.setAttribute("accademic_year", accademinyear);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping
				.findForward(LibraryMessageConstants.SearchsubscribersDetails);
	}

	public ActionForward getClassByLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String locationid = request.getParameter("locationid");

			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate()
			.getClassByLocation(locationid);

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}

	public ActionForward getClassSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassSection Starting");

		String schoolLocation = null;
		schoolLocation = request.getParameter("locationId");
		if (schoolLocation == null || schoolLocation.equalsIgnoreCase("")) {
			schoolLocation = (String) request.getSession(false).getAttribute(
					"current_schoolLocation");
		}

		try {
			String classidVal = request.getParameter("classidVal");
			System.out.println("class id val is" + classidVal);
			List<StudentRegistrationVo> List = new StudentRegistrationDelegate()
			.getSection(classidVal + "," + schoolLocation);
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.accumulate("sectionList", List);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getClassSection Ending");
		return null;
	}


	/*--------------------------MASTER START----------------------*/

	public ActionForward categoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_CATEGORYTYPE);
			
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getCategoryDetails();
			request.setAttribute("categoryDetails", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.CATEGORY_TYPE);
	}

	public ActionForward getSubCategoryByCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String CategoryCode = request.getParameter("categoryid");

			List<SubCategoryTypeVO> SubCategoryList = new LibraryDelegate()
			.getSubCategoryByCategory(CategoryCode);

			JSONObject jsonObject = new JSONObject(SubCategoryList);
			jsonObject.accumulate("SubCategoryList", SubCategoryList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}

	public ActionForward addCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_CATEGORYTYPE);
			
			String title = "Add Category Type Details";
			request.setAttribute("category", title);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_CATEGORY_TYPE);
	}



	public ActionForward insertCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {



			String userId=HelperClass.getCurrentUserID(request);
			CategoryTypeVO insert_categoryType=new CategoryTypeVO();

			String hidden_id=request.getParameter("category_id");

			String CategoryTypeCode=request.getParameter("categorytypecode");
			String CategoryTypeName=request.getParameter("categorytypename");
			String Status=request.getParameter("Status");
			String Description=request.getParameter("description");
			
			String hiddencode=request.getParameter("hiddencode");

			insert_categoryType.setHidden_category_id(hidden_id);
			insert_categoryType.setCategorytypecode(CategoryTypeCode);
			insert_categoryType.setCategorytypename(CategoryTypeName);
			insert_categoryType.setStatus(Status);
			insert_categoryType.setDescription(Description);
			insert_categoryType.setCreated_by(userId);
			insert_categoryType.setHiddencode(hiddencode);
			


			String status = new LibraryDelegate().insertCategoryTypeDetail(insert_categoryType);
			System.out.println("status "+status);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward editCategoryTypeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_CATEGORYTYPE);
			String title = "Update Category Type Details";
			request.setAttribute("category", title);

			String id = request.getParameter("catid");

			CategoryTypeVO edit_category = new LibraryDelegate().editCategoryType(id);

			request.setAttribute("editlist",edit_category);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_CATEGORY_TYPE);

	}

	public ActionForward insertLibraryLocations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userCode = userDetailVO.getUserId();
			String result = null;
			LibraryLocationPojo insert_libLoc =new LibraryLocationPojo();

			String SchoolName=request.getParameter("schoolName");
			String LibraryLocations=request.getParameter("libraryLocations");
			String Description=request.getParameter("description");
			String hiddenlocationsid = request.getParameter("hiddenlocationid"); 
			insert_libLoc.setSchoolName(SchoolName);
			insert_libLoc.setLibraryLocations(LibraryLocations);
			insert_libLoc.setDescription(Description);
			insert_libLoc.setCurrentuserid(userCode);
			insert_libLoc.setHiddenlibid(hiddenlocationsid);

			if(!hiddenlocationsid.equalsIgnoreCase("")&& hiddenlocationsid!=null )
			{
				result = new LibraryDelegate().updateLibLocations(insert_libLoc);
			}
			else
			{
				result= new LibraryDelegate().insertLibraryLocations(insert_libLoc);
			}

			JSONObject obj1= new JSONObject();
			obj1.put("status",result);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}


	public ActionForward deleteLibraryLocations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			String librarylocid[] = request.getParameterValues("librarylocid[]");
		
			String	status= new LibraryDelegate().deleteLibraryLocations(librarylocid);
			System.out.println(".........");
			JSONObject obj1= new JSONObject();
			obj1.put("status",status);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward addLibraryLocations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_LIBRARYLOCATION);
			String title = "Add Library Location Details";
			request.setAttribute("library", title);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userCode = userDetailVO.getUserId();

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.addLibraryLocations);
	}

	public ActionForward editLibraryLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddLibrarylocationsDetails : editLibrarylocations Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_LIBRARYLOCATION);
			String title = "Update Library location  Details";
			request.setAttribute("library", title);

			String id = request.getParameter("libid");

			LibraryLocationVO edit_libloc = new LibraryDelegate().editLibraryLocation(id);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			System.out.println("/////////////////////");
			System.out.println(locationList);
			request.setAttribute("locationList", locationList);
			request.setAttribute("editlist",edit_libloc);
			request.setAttribute("libid",id);



		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return mapping.findForward(LibraryMessageConstants.addLibraryLocations);

	}

	public ActionForward insertSubCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {

			String userId=HelperClass.getCurrentUserID(request);
			SubCategoryTypeVO vo=new SubCategoryTypeVO();

			String hidden_id=request.getParameter("subcategory_id");
			String Category_id=request.getParameter("category_id");
			String CategoryTypeCode = request.getParameter("categorytype");
			String SubCategoryTypeCode=request.getParameter("subcategorytypecode");
			String SubCategoryTypeName=request.getParameter("subcategorytypename");
			String Status=request.getParameter("status");
			String Description=request.getParameter("description");

			vo.setSubcategory_id(hidden_id);			
			vo.setCategorytypecode(CategoryTypeCode);
			vo.setCategory_id(Category_id);
			vo.setSubcategorytypecode(SubCategoryTypeCode);
			vo.setSubcategorytypename(SubCategoryTypeName);
			vo.setStatus(Status);
			vo.setDescription(Description);
			vo.setCreated_by(userId);

			String status = new LibraryDelegate().insertSubCategoryTypeDetail(vo);
			System.out.println("status "+status);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward editSubCategoryTypeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE);
			String title = "Update Sub Category Type Details";
			request.setAttribute("category", title);


			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);

			String id = request.getParameter("catid");

			SubCategoryTypeVO edit_category = new LibraryDelegate().editSubCategoryType(id);

			request.setAttribute("editlist",edit_category);

		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE);

	}


	
	public ActionForward libraryLocations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_LIBRARYLOCATION);
			String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
			LibraryDelegate obj = new LibraryDelegate();
			List<LibraryLocationPojo> list = new ArrayList<LibraryLocationPojo>();
			
			list = obj.getLibLocationsDetails(location);
			request.setAttribute("librarylocationsDetails", list);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			System.out.println(list.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.libraryLocations);
	}



	public ActionForward subCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE);
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			List<CategoryTypeVO> list1 = new ArrayList<CategoryTypeVO>();


			list = obj.getSubCategoryDetails();
			list1 =  obj.getSubCategoryDetails1();


			request.setAttribute("SubcategoryDetails", list);
			request.setAttribute("SubcategoryDetails1", list1);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.SUB_CATEGORY_TYPE);
	}
	

	public ActionForward addSubCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE);

			String title = "Add Sub Category Type Details";
			request.setAttribute("category", title);

			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE);
	}

	public ActionForward CategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			String title = "Update Category Type Details";
			request.setAttribute("category", title);

			String id = request.getParameter("catid");


			/*String status=new LibraryDelegate().inactiveCategoryType(id);*/

			/*JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);*/


		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}

	public ActionForward inactiveCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);

			String CatIdlist[] = request.getParameterValues("CatIdlist[]");
			
			String	result= new LibraryDelegate().inactiveCategoryType(CatIdlist);
			System.out.println(".........");
			JSONObject obj1= new JSONObject();
			obj1.put("status",result);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
	
	
	/*--------------------------MASTER END----------------------*/

	/*--------------------SUB CAT TYPE 1 IN MASTER START---------------------*/

	public ActionForward subCategoryType1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE1);

			LibraryDelegate obj = new LibraryDelegate();
			List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
			list = obj.getSubCategoryType1Details();
			request.setAttribute("SubcategoryType1Details", list);
			
			List<CategoryTypeVO> list1=new ArrayList<CategoryTypeVO>();
			    list1=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list1);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.SUB_CATEGORY_TYPE1);
	}

	public ActionForward addSubCatagoryType1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE1);

			String title = "Add Sub Category Type1";
			request.setAttribute("category", title);

			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE1);
	}

	public ActionForward insertSubCategoryType1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {



			String userId=HelperClass.getCurrentUserID(request);
			SubCategoryType1VO sub1=new SubCategoryType1VO();
			String hidden_id=request.getParameter("subcategorytype1_id");
			String CategoryTypeCode=request.getParameter("categorytypecode");
			String SubCategoryTypeCode=request.getParameter("subcategorytypecode");
			String SubCategoryType1Code=request.getParameter("subcategorytype1code");
			String SubCategoryType1Name=request.getParameter("subcategorytype1name");
			String Status=request.getParameter("Status");
			String Description=request.getParameter("description");
			String Hiddenid=request.getParameter("hiddenid");

			sub1.setHidden_subcategory1_id(hidden_id);
			sub1.setCategorytypecode(CategoryTypeCode);
			sub1.setSubcategorytypecode(SubCategoryTypeCode);
			sub1.setSubcategorytype1code(SubCategoryType1Code);
			sub1.setSubcategorytype1name(SubCategoryType1Name);
			sub1.setDescription(Description);
			sub1.setStatus(Status);
			sub1.setCreated_by(userId);
			sub1.setHiddenid(Hiddenid);


			String status = new LibraryDelegate().insertSubCategoryType1Detail(sub1);
			System.out.println("status "+status);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward editSubCategoryType1Detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE1);

			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String title = "Update Category Type 1";
			request.setAttribute("category", title);

			String id = request.getParameter("catid");

			SubCategoryType1VO edit_category = new LibraryDelegate().editSubCategoryType1(id);

			request.setAttribute("editlist",edit_category);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE1);

	}

	public ActionForward inactiveSubCategoryType1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);

			String id[] = request.getParameterValues("Inactivelist[]");


			String status=new LibraryDelegate().inactiveSubCategoryType1(id);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}

	public ActionForward validateSubCatagoryType1Code(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : validateStreamStreamDetailsAction  Starting");



		String CategoryTypeCode=request.getParameter("categorytypecode");
		String SubCategoryTypeCode=request.getParameter("subcategorytypecode");
		String SubCategoryType1Code=request.getParameter("subcategorytype1code");

		SubCategoryType1VO sub1=new SubCategoryType1VO();

		sub1.setCategorytypecode(CategoryTypeCode);
		sub1.setSubcategorytypecode(SubCategoryTypeCode);
		sub1.setSubcategorytype1code(SubCategoryType1Code);

		boolean SubCategoryType1_available= new LibraryDelegate().validateSubcategoryType1(sub1);

		JSONObject jsonobject= new JSONObject();
		if(SubCategoryType1_available){
			jsonobject.put("status", "true");
		}else{
			jsonobject.put("status", "false");
		}
		response.getWriter().print(jsonobject);


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : validateStreamDetailsAction   Ending");

		return null;

	}
	/*--------------------SUB CAT TYPE 1 IN MASTER END---------------------*/

	/*-------------SUB CAT TYPE 2 MASTER START---------------------*/

	public ActionForward subCategoryType2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE2);
			
			LibraryDelegate obj = new LibraryDelegate();
			List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
			list = obj.getSubCategoryType2Details();
			request.setAttribute("SubcategoryType2Details", list);
			
			
			List<CategoryTypeVO> list1=new ArrayList<CategoryTypeVO>();
			list1=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list1);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(LibraryMessageConstants.SUB_CAT_2);

	}

	public ActionForward addSubCatagoryType2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		try {
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE2);


			String title = "Add Sub Category Type2";
			request.setAttribute("category", title);

			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CAT_TYPE_2);

	}
	
	public ActionForward getSubCategory1ByCategoryAndSubCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String SubCategoryTypeCode=request.getParameter("subcategorycode");

			List<SubCategoryType1VO> SubCategoryType1List = new LibraryDelegate()
					.getSubCategory1ByCategoryAndSubCategory(SubCategoryTypeCode);

			JSONObject jsonObject = new JSONObject(SubCategoryType1List);
			jsonObject.accumulate("SubCategoryType1List", SubCategoryType1List);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}
	
	public ActionForward editSubCategoryType2Detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE2);

			LibraryDelegate obj=new LibraryDelegate();
			List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
			list=obj.getCategoryDetails();
			request.setAttribute("CategoryDetails", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String title = "Update Category Type 2";
			request.setAttribute("category", title);

			String id = request.getParameter("catid");
			SubCategoryType2VO edit_category = new LibraryDelegate().editSubCategoryType2(id);
			request.setAttribute("editlist",edit_category);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");
		return mapping.findForward(LibraryMessageConstants.ADD_SUB_CAT_TYPE_2);
	}

	public ActionForward insertSubCategoryType2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			
			
			
			String userId=HelperClass.getCurrentUserID(request);
            SubCategoryType2VO sub2=new SubCategoryType2VO();
            String CategoryTypeCode=request.getParameter("categorytypecode");
            String SubCategoryTypeCode=request.getParameter("subcategorytypecode");
			String SubCategoryType1Code=request.getParameter("subcategorytype1code");
			String SubCategoryType2Code=request.getParameter("subcategorytype2code");
			String SubCategoryType2Name=request.getParameter("subcategorytype2name");
			String Status=request.getParameter("Status");
			String Description=request.getParameter("description");
			String hidden_id =request.getParameter("hidden_subcat2_id");
			String hiddemcode=request.getParameter("subcat2code");

			sub2.setCategorytypecode(CategoryTypeCode);
			sub2.setSubcategorytypecode(SubCategoryTypeCode);
			sub2.setSubcategorytype1code(SubCategoryType1Code);
			sub2.setSubcategorytype2code(SubCategoryType2Code);
			sub2.setSubcategorytype2name(SubCategoryType2Name);
			sub2.setDescription(Description);
			sub2.setStatus(Status);
			sub2.setCreated_by(userId);
			sub2.setHidden_subcat2_id(hidden_id);
			sub2.setHiddenSubcat2code(hiddemcode);


			String status = new LibraryDelegate().insertSubCategoryType2Detail(sub2);
			System.out.println("status "+status);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward inactiveSubCategoryType2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			
			String id[] = request.getParameterValues("Inactivelist[]");

			String status=new LibraryDelegate().inactiveSubCategoryType2(id);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);


		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
	
	/*-------------------------SUB 2 END---------------------*/

	public ActionForward studentSearchbyadmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");


		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			
			String academicYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String searchterm = request.getParameter("searchTerm");
			String locid = request.getParameter("locid");
			String accid = request.getParameter("accid");
			if(locid ==null || locid.equalsIgnoreCase("") || locid.equalsIgnoreCase("all")){
				locid="%%";
			}
			
			System.out.println("................."+request.getParameter("locid"));
			System.out.println(searchterm);
			
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			registrationVo.setSearchTerm(searchterm);
			registrationVo.setLocationId(locid);
			registrationVo.setAcademicYear(accid);

			searchStudentList = new LibraryDelegate().studentSearchbyadmissionNo(registrationVo,locid);
			System.out.println("size...........");
			System.out.println(searchStudentList.size());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

		return null;
	}

	public ActionForward teacherSearchbyId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");


		List<TeacherVo> searchTeacherList = new ArrayList<TeacherVo>();
		try {
			TeacherVo registrationVo = new TeacherVo();
			String searchterm = request.getParameter("searchTerm");
			String locid = request.getParameter("locid");
			System.out.println("................."+request.getParameter("locid"));
			System.out.println(searchterm);
			registrationVo.setSearchTerm(searchterm);
			registrationVo.setLocid(locid);

			searchTeacherList = new LibraryDelegate().teacherSearchbyId(registrationVo);
			System.out.println("size...........");
			System.out.println(searchTeacherList.size());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchTeacherList);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

		return null;
	}

	public ActionForward getStudentIssuedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :StudentList Starting");

		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String cls= request.getParameter("classname");
		String div=request.getParameter("section");
		try {
			if(locid.equalsIgnoreCase("all")){
				locid="%%";
			}
			list= new  LibraryDelegate().getStudentIssuedList(locid,accyear);

			JSONObject obj1= new JSONObject();
			obj1.put("stulist",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: StudentList ending");

		return null;
	}



	//--------------subscriber details----


	public ActionForward getLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getLocation Starting");
		try {
			ArrayList<ReportMenuVo> locList = new LibraryDelegate().getLibraryLocation();
			//request.setAttribute("AccYearList", accYearList);
			JSONObject obj=new JSONObject();
			obj.put("locList", locList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction: getLocation Ending");
		return null;
	}



	public ActionForward getStudentData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Starting");
		try {


			String academicYear = request.getParameter("academicYear");
			String admissionNo = request.getParameter("adminssionNo");
			System.out.println("**************************************"+admissionNo);

			ArrayList<LibrarySubscribVO> studentData = new LibraryDelegate().getStudentData(academicYear,admissionNo);

			//request.setAttribute("studentIdHidden", studentData.get(0).getStudentIdHidden());

			JSONObject obj=new JSONObject();
			obj.put("studentData", studentData);
			response.getWriter().print(obj);


		} catch (Exception e) {	
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Ending");

		return null;
	}

	public ActionForward getStaffData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Starting");
		try {

			String staffid = request.getParameter("staffRegId");
			String locId = request.getParameter("locId");
			System.out.println("**********************************************************************s");
			System.out.println(staffid);
			System.out.println(locId);
			ArrayList<LibrarySubscribVO> teacherData = new LibraryDelegate().getStaffData(staffid,locId);

			JSONObject obj=new JSONObject();
			obj.put("teacherData", teacherData);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Ending");

		return null;
	}

	public ActionForward saveSubscriberDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Starting");
		try{			
			String locationName = request.getParameter("locationName");
			String accId = request.getParameter("accyear");

			String subId= request.getParameter("subId");
			String subscriberType= request.getParameter("subscriberType");
			String teacher = request.getParameter("teacher");
			String student = request.getParameter("student");
			String other = request.getParameter("other");
			String suscriberType=request.getParameter("suscriberType");	
			String subscriberNo = request.getParameter("subscriberNo");
			String adminssionNo = request.getParameter("adminssionNo");
			String studentIdHidden = request.getParameter("studentIdHidden");
			String cardNoAmount = request.getParameter("cardNoAmount");
			String checkNoAmount = request.getParameter("checkNoAmount");

			String staffRegId = request.getParameter("staffRegId");
			String staffId = request.getParameter("staffId");
			String deptId = request.getParameter("deptId");
			String desigId = request.getParameter("desigId");


			String depositType = request.getParameter("depositType");
			String enterAmount = request.getParameter("enterAmount");
			String cardNo = request.getParameter("cardNo");
			String checkNo = request.getParameter("checkNo");

			String status = request.getParameter("status");
			String paymentDate = request.getParameter("paymentDate");

			String otherUserAddr = request.getParameter("otherUserAddr");
			String otherUserName = request.getParameter("otherUserName");
			String otherUserGender = request.getParameter("otherUserGender");
			String otherUserContact = request.getParameter("otherUserContact");
			String otherUserEmail = request.getParameter("otherUserEmail");

			String classId = request.getParameter("classId");
			String divisionId = request.getParameter("divisionId");

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();

			pojo.setLocationName(locationName);
			pojo.setTeacher(teacher);
			pojo.setStudent(student);
			pojo.setOther(other);
			pojo.setCardNoAmount(cardNoAmount);
			pojo.setCheckNoAmount(checkNoAmount);
			pojo.setStaffid(staffId);
			pojo.setDeptId(deptId);
			pojo.setDesgId(desigId);

			pojo.setSubscriberNo(subscriberNo);
			pojo.setAdminssionNo(adminssionNo);
			pojo.setStudentIdHidden(studentIdHidden);
			pojo.setClassId(classId);
			pojo.setSectionId(divisionId);

			pojo.setStaffRegId(staffRegId);

			pojo.setDepositType(depositType);
			pojo.setEnterAmount(enterAmount);
			pojo.setCardNo(cardNo);
			pojo.setCheckNo(checkNo);

			pojo.setStatus(status);
			pojo.setPaymentDate(paymentDate);
			pojo.setSuscriberType(suscriberType);
			pojo.setOtherUserAddr(otherUserAddr);
			pojo.setOtherUserName(otherUserName);
			pojo.setOtherUserContact(otherUserContact);
			pojo.setOtherUserEmail(otherUserEmail);
			pojo.setOtherUserGender(otherUserGender);
			pojo.setAccId(accId);

			String resultData = new LibraryDelegate().saveSubscriberDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("resultData", resultData);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Ending");

		return null;
	}

	public ActionForward getStudentListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			String searchTerm = request.getParameter("searchname".trim());

			String select = request.getParameter("select");

			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all")){
				academic_year = "%%";
			}
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname = "%%";
			}
			  if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("")){
					sectionid ="%%";
				}

			list = new LibraryDelegate().getStudentListDetails(academic_year,location,select,classname,sectionid);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	public ActionForward getIssueStudentClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueStudentClassListStarting");


		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String classname=request.getParameter("classid");
		try {	
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			list= new  LibraryDelegate().getIssueStudentClassList(locid,accyear,classname);

			JSONObject obj1= new JSONObject();
			obj1.put("clslist",list);
			response.getWriter().print(obj1);

		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueStudentClassList Ending");

		return null;
	}




	public ActionForward getIssueStudentSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueStudentSectionList Starting");


		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String classname=request.getParameter("classid");
		String sectionnm=request.getParameter("sectionid");
		try {	

		 

			if(sectionnm.equalsIgnoreCase("all")){
				sectionnm="%%";
			}
			list= new  LibraryDelegate().getIssueStudentSectionList(locid,accyear,classname,sectionnm);

			JSONObject obj = new JSONObject();
			obj.put("Seclist",list);
			response.getWriter().print(obj);


		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueStudentSectionList Ending");

		return null;
	}

	public ActionForward getStudentListByClassName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select");

			String searchTerm = request.getParameter("searchname".trim());

			if(academic_year.equalsIgnoreCase("all")){
				academic_year = "%%";
			}
			
			if(classname.equalsIgnoreCase("all")){
				classname = "%%";
			}

			list = new LibraryDelegate().getStudentListByClassName(academic_year,location,classname,sectionid,select);



			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward getStudentListBySection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select");

			String searchTerm = request.getParameter("searchname".trim());

			if(academic_year.equalsIgnoreCase("all")){
				academic_year ="%%";
			}
			if(classname.equalsIgnoreCase("all")){
				classname ="%%";
			}
			
			if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("")){
				sectionid ="%%";
			}

			list = new LibraryDelegate().getStudentListBySection(academic_year,location,classname,sectionid,select);
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}


	public ActionForward inactiveSubCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);

			String id[] = request.getParameterValues("Inactivelist[]");

			SubCategoryTypeVO vo=new SubCategoryTypeVO();


			String status=new LibraryDelegate().inactiveSubCategoryType(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);


		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}

	/*-----------------------------SearchSubscriberDetailsByAnyWhere(searchSubscriber)-----------------------------------*/

	public ActionForward SearchSubscriberDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select");
			String searchTextVal  = request.getParameter("searchname").trim();

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")){
				academic_year = "%%";
			}
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname = "%%";
			}
			if(sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")){
				sectionid = "%%";
			}
				
			List = librarydelegate.SearchSubscriberDetailsByAnyWhere(searchTextVal,location,academic_year,select,classname,sectionid);
			
			request.setAttribute("searchnamelistValue", searchTextVal);
			/*else {
				List = librarydelegate.getClassDetails();
			}*/

			/*request.setAttribute("SubscriberList",List);	

			request.setAttribute("studentList", list);*/

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	/*-----------------------------SearchSubscriberDetailsByStartsWith(searchSubscriber)-----------------------------------*/

	public ActionForward SearchSubscriberDetailsByStartWith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select");
			String searchTextVal  = request.getParameter("searchname").trim();
			
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			if (location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")) {
				location="%%";
			}
			if (academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")) {
				academic_year="%%";
			}
			if (classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")) {
				classname="%%";
			}
			if (sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")) {
				sectionid="%%";
			}
			
			List = librarydelegate.SearchSubscriberDetailsByStartWith(searchTextVal,location,select,classname,sectionid,academic_year);
			request.setAttribute("searchnamelistValue", searchTextVal);
			

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	/*-----------------------------SearchSubscriberDetailsByEndsWith(searchSubscriber)-----------------------------------*/

	public ActionForward SearchSubscriberDetailsByEndsWith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select");
			String searchTextVal=request.getParameter("searchname").trim();
			
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			if (location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")) {
				location="%%";
			}
			if (academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")) {
				academic_year="%%";
			}
			if (classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")) {
				classname="%%";
			}
			if (sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")) {
				sectionid="%%";
			}
			
			List = librarydelegate.SearchSubscriberDetailsByEndsWith(searchTextVal,location,select,academic_year,classname,sectionid);
			request.setAttribute("searchnamelistValue",searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	/*---------------------------Staff List details---------------------------*/

	public ActionForward getStaffListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			String select = request.getParameter("select");

			String searchTerm = request.getParameter("searchname".trim());

			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all")){
				academic_year = "%%";
			}
			if(department.equalsIgnoreCase("all")){
				department = "%%";
			}
			if(designation.equalsIgnoreCase("all")){
				designation = "%%";
			}
			list = new LibraryDelegate().getStaffListDetails(academic_year,location,select,department,designation);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward getSubscriberDetailsListPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryMenu : getSubscriberDetailsListPage Starting");

	try{
		
		String academicYear = request.getParameter("accyear");
		if(academicYear.equalsIgnoreCase("all") || academicYear.equalsIgnoreCase("") || academicYear==null ){
			academicYear="%%";
		}
	
		String locId = request.getParameter("locId");
		if(locId.equalsIgnoreCase("all") || locId.equalsIgnoreCase("") || locId==null ){
			locId="%%";
		}
		
		String classId = request.getParameter("classId");
		if(classId.equalsIgnoreCase("all") || classId.equalsIgnoreCase("") || classId==null){
			classId="%%";
		}
		String sectionName = request.getParameter("sectionName");
		if(sectionName.equalsIgnoreCase("all")  || sectionName.equalsIgnoreCase("") || sectionName==null){
			sectionName="%%";
		}
		String suscriberType = request.getParameter("suscriberType");
		
		String designation = request.getParameter("designation");
		if(designation.equalsIgnoreCase("all")  || designation.equalsIgnoreCase("") || designation==null){
			designation="%%";
		}
		String department = request.getParameter("department");
		if(department.equalsIgnoreCase("all")  || department.equalsIgnoreCase("") || department==null){
			department="%%";
		}
		String otherName = request.getParameter("otherName");
	
		/*if(otherName.equalsIgnoreCase("all")  || otherName.equalsIgnoreCase("")|| otherName=="undefined" || otherName==null){
			otherName="%%";
		}*/
		
		
		ArrayList<LibrarySubscribVO> list = new LibraryDelegate().getSubscriberDetailsListPage(academicYear,locId,classId,sectionName,suscriberType,department,designation,otherName);
		request.getSession(false).setAttribute("sublist",list);
		request.getSession(false).setAttribute("suscriberType",suscriberType);
		JSONObject obj = new JSONObject();
		obj.put("listInfo",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryMenu : getSubscriberDetailsListPage Ending");
		return null;
	}
	
	public ActionForward getIssueDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueDetailsByAnyWhere Starting");


		try {
			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
			String locid = request.getParameter("locationid");
			String accyear = request.getParameter("Acyearid");
			String classname=request.getParameter("classid");
			String sectionnm=request.getParameter("sectionid");
			
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchIssueDetailsVO> List = new ArrayList<LibrarySearchIssueDetailsVO>();

			String searchTextVal  = request.getParameter("searchid");

			if (searchTextVal != null) {
				List = librarydelegate.getIssueDetailsByAnyWhere(searchTextVal,locid,accyear);
			}
			request.setAttribute("searchnamelistValue", searchTextVal);



			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueDetailsByAnyWhere Ending");

		return null;
	}


	public ActionForward getSchoolLocations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddLibrarylocationsDetails : editLibrarylocations Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String title = "Update Library locations  Details";
			request.setAttribute("library", title);

			String id = request.getParameter("locid");
			if(id.equalsIgnoreCase("all")){
					id="%%";
			}
			ArrayList<LibraryLocationVO> get_libloc = new LibraryDelegate().getSchoolLocations(id);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			request.setAttribute("getlist",get_libloc);
			request.setAttribute("locationid",id);
			System.out.println("...................................");
			System.out.println(request);
			JSONObject obj=new JSONObject();
			obj.put("status",get_libloc);
			response.getWriter().print(obj);


		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;

	}

	public ActionForward getIssueDetailsByStartwith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueDetailsByStartwith Starting");


		try {

			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
			String locid = request.getParameter("locationid");
			String accyear = request.getParameter("Acyearid");
			String classname=request.getParameter("classid");
			String sectionnm=request.getParameter("sectionid");
			String selection = request.getParameter("selection");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchIssueDetailsVO> List = new ArrayList<LibrarySearchIssueDetailsVO>();


			String searchTextVal  = request.getParameter("searchid");

			if (searchTextVal != null) {
				List = librarydelegate.getIssueDetailsByStartwith(searchTextVal,locid,accyear,selection);
			}

			request.setAttribute("", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueDetailsByAnyWhere Ending");

		return null;
	}


	public ActionForward getStaffdetailsByDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear_ID = request.getParameter("accyear");
			String Loc_ID = request.getParameter("location");
			String department=request.getParameter("department");
			String designation=request.getParameter("designation");
			
			if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase(""))
			{
				department="%%";
			}
			if(designation.equalsIgnoreCase("all") || designation.equalsIgnoreCase(""))
			{
				designation="%%";
			}

			String searchTerm = request.getParameter("searchname");

			list = new LibraryDelegate().getStaffdetailsByDepartment(accyear_ID,Loc_ID,department,designation);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}				



	public ActionForward getStaffdetailsByDesignation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear_ID = request.getParameter("accyear");
			String Loc_ID = request.getParameter("location");
			String department=request.getParameter("department");
			String designation=request.getParameter("designation");

			if(department.equalsIgnoreCase("all"))
			{
				department="%%";
			}

			if(designation.equalsIgnoreCase("all"))
			{
				designation="%%";
			}

			String searchTerm = request.getParameter("searchname");

			list = new LibraryDelegate().getStaffdetailsByDesignation(accyear_ID,Loc_ID,department,designation);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}


	public ActionForward SearchStaffDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String searchTextVal = request.getParameter("searchname").trim();
			String select = request.getParameter("select");   
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
            	location="%%";
            }
            if(accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")){
            	accyear="%%";
            }
            if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase("")){
            	department="%%";
            }
            if(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase("all")){
            	designation="%%";
            }

			list = new LibraryDelegate().SearchStaffDetailsByAnyWhere(searchTextVal,location,select,
					department,designation,accyear);
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}


	public ActionForward SearchStaffDetailsByStartWith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String select = request.getParameter("select");
			String searchTextVal = request.getParameter("searchname").trim();
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
            	location="%%";
            }
            if(accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")){
            	accyear="%%";
            }
            if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase("")){
            	department="%%";
            }
            if(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase("all")){
            	designation="%%";
            }
			list = new LibraryDelegate().SearchStaffDetailsByStartWith(searchTextVal,location,select,department,designation,accyear);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}

	public ActionForward SearchStaffDetailsByEndsWith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		
			String location = request.getParameter("location");
			String searchTextVal = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String accyear = request.getParameter("accyear");
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
            	location="%%";
            }
            if(accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")){
            	accyear="%%";
            }
            if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase("")){
            	department="%%";
            }
            if(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase("all")){
            	designation="%%";
            }
            
			list = new LibraryDelegate().SearchStaffDetailsByEndsWith(searchTextVal,location,select,department,designation,accyear);
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}
	public ActionForward getStaffListFilterByLocationAndAcyearid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear_ID = request.getParameter("accyear");
			String Loc_ID = request.getParameter("location");


			if(Loc_ID.equalsIgnoreCase("all"))
			{
				Loc_ID="%%";
			}

			if(accyear_ID.equalsIgnoreCase("all"))
			{
				accyear_ID="%%";
			}
			String searchTerm = request.getParameter("searchname");



			list = new LibraryDelegate().getStaffListFilterByLocationAndAcyearid(accyear_ID,Loc_ID);



			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward getOthersList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getOthersList Starting");

		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		/* String dept= request.getParameter("classname");
		     String desig=request.getParameter("section");*/
		try {
			if(locid.equalsIgnoreCase("all")){
				locid="%%";
			}
			//list= new  LibraryDelegate().getTeacherList(locid,accyear);

			/*JSONObject obj1= new JSONObject();
				obj1.put("stulist",list);
				response.getWriter().print(obj1);*/
				
				list= new  LibraryDelegate().getOthersList(locid,accyear);
				
				JSONObject obj1= new JSONObject();
				obj1.put("OtherData",list);
				response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: StudentList ending"
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getOthersList ending");

		return null;
	}

	
	public ActionForward getTeacherDeptList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getTeacherDeptList Starting");

		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		  String dept =request.getParameter("deptId");
		    
		try {
			if(locid.equalsIgnoreCase("all")){
				locid="%%";
			}
				if(dept.equalsIgnoreCase("all")){
					dept="%%";
				}
				list= new  LibraryDelegate().getTeacherDeptList(locid,accyear,dept);
				
				JSONObject obj1= new JSONObject();
				obj1.put("stffdept",list);
				response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getTeacherDeptList ending");

			return null;
	}



	public ActionForward getCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String cattype = request.getParameter("cattype");
		String status = request.getParameter("status");


		System.out.println(cattype);

		try {

			if( cattype.equalsIgnoreCase("all")){
				cattype="%%";
			}

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getSubCategoryDetails(cattype,status);
			
			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward validateLibLocationUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Library Location DetailsAction : validateLibraryLocationDetailsAction  Starting");



		String schoolname=request.getParameter("schoolName");
		String liblocations=request.getParameter("libraryLocations");

		LibraryLocationVO lib =new LibraryLocationVO();


		lib.setLibraryLocations(schoolname);
		lib.setSchoolName(liblocations);

		boolean LibLocationName_available= new LibraryDelegate().validateLibLocationUpdate(lib);

		JSONObject jsonobject= new JSONObject();
		if(LibLocationName_available){
			jsonobject.put("status", "true");
		}else{
			jsonobject.put("status", "false");
		}
		response.getWriter().print(jsonobject);


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : validateStreamDetailsAction   Ending");

		return null;


	}
	
	public ActionForward getSubCategoryTypeName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String categoryName = request.getParameter("categoryName");


		System.out.println(categoryName);

		try {

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getSubCategoryTypeName(categoryName);


			JSONObject obj1= new JSONObject();
			obj1.put("subcategorynamelist", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward getSubCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String catcode = request.getParameter("catcode");
		String subcatcode = request.getParameter("subcatcode");
		String status = request.getParameter("status");


		System.out.println(catcode+"     "+subcatcode);

		try {
			
			if( catcode.equalsIgnoreCase("all")){
				catcode="%%";
			}

			if( subcatcode.equalsIgnoreCase("all") || subcatcode.equalsIgnoreCase("")){
				subcatcode="%%";
			}

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getSubCategoryList(catcode,subcatcode,status);


			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward getbystatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String catcode = request.getParameter("catcode");
		String subcatcode = request.getParameter("subcatcode");
		String status = request.getParameter("status");


		System.out.println(catcode+"///---///"+subcatcode+"///---///"+status);

		try {
			
			if( catcode.equalsIgnoreCase("all")){
				catcode="%%";
			}

			if(subcatcode.equalsIgnoreCase("all") || subcatcode.equalsIgnoreCase("")){
				subcatcode="%%";
			}
			
			

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getbystatusList(catcode,subcatcode,status);

			System.out.println("mmmmmmmm"+list);
			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	

	
	public ActionForward getTeacherDesgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getTeacherDesgList Starting");

		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		  String dept =request.getParameter("deptId");
		    String desg=request.getParameter("desgid");
		try {
				if(desg.equalsIgnoreCase("all")){
					desg="%%";
				}
				list= new  LibraryDelegate().getTeacherDesgList(locid,accyear,dept,desg);
				
				JSONObject obj1= new JSONObject();
				obj1.put("stffdesg",list);
				response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getTeacherDesgList ending");

			return null;
	}
	
	public ActionForward getIssueByStartwith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueByStartwith Starting");
		
		
		try {
			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
			String locid = request.getParameter("locationid");
			String accyear = request.getParameter("Acyearid");
			String dept =request.getParameter("deptId");
			String desg=request.getParameter("desgid");
			String selection = request.getParameter("selection");
			String searchTextVal  = request.getParameter("searchvalue");
			
			if(locid.equalsIgnoreCase("all")){
				locid = "%%";
			}
			 if(dept.equalsIgnoreCase("all")){
				dept  = "%%";
			}
			if(desg.equalsIgnoreCase("all")){
				desg  = "%%";
			}
			LibrarySearchIssueDetailsVO vo = new LibrarySearchIssueDetailsVO();
			vo.setLocid(locid);
			vo.setAcademicYear(accyear);
			vo.setStaffDepartment(dept);
			vo.setStaffDesignation(desg);
			vo.setSearchText(searchTextVal);
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchIssueDetailsVO> List = new ArrayList<LibrarySearchIssueDetailsVO>();
		
	
				List = librarydelegate.getIssueByStartwith(vo,selection);
		
				request.setAttribute("searchnamelistValue", searchTextVal);
			
				
				JSONObject obj=new JSONObject();
				obj.put("StafData",List);
				response.getWriter().print(obj);
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueByStartwith Ending");

		return null;
	}
	

	

	public ActionForward getTeacherList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getTeacherList Starting");

		ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		/*String dept=request.getParameter("dept");
		String desg=request.getParameter("desg");*/
		System.out.println(locid);
		try {
				if(locid.equalsIgnoreCase("all")){
					locid="%%";
				}
				/*if(dept.equalsIgnoreCase("all")){
					dept="%%";
				}
				if(desg.equalsIgnoreCase("all")){
					desg="%%";
				}*/
				list= new  LibraryDelegate().getTeacherList(locid,accyear);
				
				JSONObject obj1= new JSONObject();
				obj1.put("stfflist",list);
				response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getTeacherList ending");

			return null;
	}

	public ActionForward getOthersListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		
		
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String select = request.getParameter("select");

			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all")){
				academic_year = "%%";
			}
			list = new LibraryDelegate().getOthersListDetails(location,select,academic_year);
			
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward SearchCategoryTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String catcode = request.getParameter("catcode");
		String subcatcode = request.getParameter("subcatcode");
		String status = request.getParameter("status");
		String searchname = request.getParameter("searchname");


		System.out.println(catcode+"///---///"+subcatcode+"///---///"+status);

		try {
				

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.SearchCategoryTypeList(catcode,subcatcode,status,searchname);


			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	
	public ActionForward getIssueotherByStartwith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueotherByStartwith() Starting");
		
		
		try {
			
			ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
			String locid = request.getParameter("locationid");
			String accyear = request.getParameter("Acyearid");
			String selection = request.getParameter("selection");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchIssueDetailsVO> List = new ArrayList<LibrarySearchIssueDetailsVO>();

			String searchTextVal  = request.getParameter("searchvalue");
		
				List = librarydelegate.getIssueotherByStartwith(searchTextVal,locid,accyear,selection);
		

				request.setAttribute("searchnamelistValue", searchTextVal.trim());
				
				JSONObject obj=new JSONObject();
				obj.put("OtherData",List);
				response.getWriter().print(obj);
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getIssueDetailsByStartwith Ending");

		return null;
	}
	/*---------------------------Others search Any where------------------------*/
	public ActionForward SearchOthersDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String location = request.getParameter("location");
			String searchTextVal = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String accyear = request.getParameter("accyear");
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
	           location="%%";
               }
            if(accyear.equalsIgnoreCase("all") || accyear.equalsIgnoreCase("")){
            	accyear="%%";
                }
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			list = new LibraryDelegate().SearchOthersDetailsByAnyWhere(searchTextVal,location,select,accyear);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	} 
	public ActionForward getTabByCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String cattype = request.getParameter("categorytype");
		String status = request.getParameter("status");
		String subcacode=request.getParameter("subcategorycode");
		String subcacode1=request.getParameter("subcategorytype1");



		System.out.println(cattype);

		try {

			if( cattype.equalsIgnoreCase("all")){
				cattype="%%";
			}
			if( subcacode.equalsIgnoreCase("all")){
				subcacode="%%";
			}
			if( subcacode1.equalsIgnoreCase("all")){
				subcacode1="%%";
			}
			
			LibraryDelegate obj = new LibraryDelegate();
			List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
			list = obj.getTabByCategoryType(cattype,status,subcacode,subcacode1);


			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
				
	public ActionForward getAccessionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");


		List<LibraryStockEntryVO> searchAccessionNo = new ArrayList<LibraryStockEntryVO>();
		try {
			LibraryStockEntryVO registrationVo = new LibraryStockEntryVO();
			
			String accNo = request.getParameter("accessionId");
			String subid = request.getParameter("subid");
			registrationVo.setAccessionNo(accNo);
			registrationVo.setSubscriberId(subid);
			
			searchAccessionNo = new LibraryDelegate().getAccessionNo(registrationVo);
			
			System.out.println("size in action cls;...........");
			System.out.println(searchAccessionNo.size());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchAccessionNo);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo ending");

		return null;
	}

	
	public ActionForward getAccessionNoByIssue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");


		List<LibraryStockEntryVO> searchAccessionNo = new ArrayList<LibraryStockEntryVO>();
		try {
            LibraryStockEntryVO registrationVo = new LibraryStockEntryVO();
			
			String accNo = request.getParameter("accessionId");
			registrationVo.setAccessionNo(accNo);
			searchAccessionNo = new LibraryDelegate().getAccessionNoByIssue(registrationVo);
			
			System.out.println("size in action cls;...........");
			System.out.println(searchAccessionNo.size());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchAccessionNo);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo ending");
		return null;
	}
	
	
	public ActionForward getclassdescrlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getclassdescrlist Starting");

		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String cateid = request.getParameter("categoryid");
	  
	
		try {
		
			list= new  LibraryDelegate().getclassdescrlist(cateid);

			JSONObject obj1= new JSONObject();
			obj1.put("catlist",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getclassdescrlist ending");

		return null;
	}
	
	
	public ActionForward getlibcategorysectionlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorysectionlist Starting");

		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String cateid = request.getParameter("categoryid");
		String classid = request.getParameter("classid");
	
	  
	
		try {
		
			list= new  LibraryDelegate().getlibcategorysectionlist(cateid,classid);

			JSONObject obj1= new JSONObject();
			obj1.put("seclist",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getlibcategorysectionlist ending");

		return null;
	}
	
	
	public ActionForward getlibcategorydivisionlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorydivisionlist Starting");

		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
	
		String sectionid = request.getParameter("sectionid");
		try {
		
			list= new  LibraryDelegate().getlibcategorydivisionlist(sectionid);

			JSONObject obj1= new JSONObject();
			obj1.put("divlist",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getlibcategorydivisionlist ending");

		return null;
	}
	
	
	
	

	
	public ActionForward getBookIssueDetailsByAccessionNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
		try {
			String searchTerm = request.getParameter("searchTerm");
			LibraryStockEntryVO libVo = new LibraryStockEntryVO();
			libVo.setAccessionNo(searchTerm);
			
			bookIssueList = LibraryDelegate.getBookIssueDetailsByAccessionNo(libVo);
            System.out.println("iside get action method...........bookissuelist");
			System.out.println(bookIssueList);
			JSONObject array = new JSONObject();
			array.put("accessionList",bookIssueList);
			response.getWriter().print(array);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchByParent Ending");
		return null;
	}
	
	public ActionForward getBookReturnDetailsByAccessionNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
		List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
		
		
		try {
			String searchTerm = request.getParameter("searchTerm");
			String subscriberId = request.getParameter("subscriberId");
			String accessionNo = request.getParameter("accessionNo");
			LibraryStockEntryVO libVo = new LibraryStockEntryVO();
			libVo.setAccessionNo(searchTerm);
			libVo.setSubscriberId(subscriberId);
			
			bookIssueList = LibraryDelegate.getBookReturnDetailsByAccessionNo(libVo);
            System.out.println("iside get action method...........bookissuelist");
			System.out.println(bookIssueList);
			JSONObject array = new JSONObject();
			array.put("accessionList",bookIssueList);
			response.getWriter().print(array);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchByParent Ending");
		return null;
	}
	
	public ActionForward insertBookIssueDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookIssueDetails : insertBookIssueDetails Starting");
		try {
		
			String result = null;
			LibraryStockEntryVO insert_issue =new LibraryStockEntryVO();

			String accessionNo=request.getParameter("accession_no");
			String itemtype=request.getParameter("itemtype");
			String bookTitle=request.getParameter("bookTitle");
			String author=request.getParameter("author");
			String  category = request.getParameter("category");
			String  ddc = request.getParameter("ddc");
			String  currentStatus = request.getParameter("currentstatus");
			String  issuedId = request.getParameter("userId").split("-")[0];
			String  issuedType = request.getParameter("usertype");
			String  imagePreview = request.getParameter("imagePreview");
			String  shelfNo = request.getParameter("shelfNo");
			String  location = request.getParameter("locationname");
			String  issued_date = request.getParameter("fromdate");
			String to_date = request.getParameter("todate");
			String  subscriberId = request.getParameter("userId");
			
			
			insert_issue.setAccessionNo(accessionNo);
			insert_issue.setItemType(itemtype);
			insert_issue.setBookTitle(bookTitle);
			insert_issue.setAuthor(author);
			insert_issue.setCategory(category);
			insert_issue.setDdc(ddc);
			insert_issue.setCurrentStatus(currentStatus);
			insert_issue.setIssued_to(issuedId);
			insert_issue.setIssued_user_id(issuedType);
			insert_issue.setImageurl(imagePreview);
			insert_issue.setShelfNo(shelfNo);
			insert_issue.setLocation(location);
			insert_issue.setIssued_date(issued_date);
			insert_issue.setToDate(to_date);
			insert_issue.setSubscriberId(subscriberId);

		result= new LibraryDelegate().insertBookIssueDetails(insert_issue);
			

			JSONObject obj1= new JSONObject();
			obj1.put("status",result);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookIssueDetails : insertBookIssueDetails Ending");

		return null;
	}

	
	public ActionForward insertBookReturnDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookIssueDetails : insertBookIssueDetails Starting");
		try {
		
			String result = null;
			
			LibraryStockEntryVO insert_issue =new LibraryStockEntryVO();

			String accessionNo=request.getParameter("accession_no");
			String itemtype=request.getParameter("itemtype");
			String bookTitle=request.getParameter("bookTitle");
			String author=request.getParameter("author");
			String  category = request.getParameter("category");
			String  ddc = request.getParameter("ddc");
			String  currentStatus = request.getParameter("currentstatus");
			String  issuedId = request.getParameter("userId").split("-")[0];
			String  issuedType = request.getParameter("usertype");
			String  shelfNo = request.getParameter("shelfNo");
			String  location = request.getParameter("locationname");
			String  issued_date = request.getParameter("fromdate");
            String  issued_return_date = request.getParameter("Lastdate");
			String  status = request.getParameter("status");
			String  subscriberId = request.getParameter("userId").split("-")[1];
			String  issueId = request.getParameter("issueId");
			
			
			insert_issue.setAccessionNo(accessionNo);
			insert_issue.setItemType(itemtype);
			insert_issue.setBookTitle(bookTitle);
			insert_issue.setAuthor(author);
			insert_issue.setCategory(category);
			insert_issue.setDdc(ddc);
			insert_issue.setCurrentStatus(currentStatus);
			insert_issue.setIssued_to(issuedId);
			insert_issue.setIssued_user_id(issuedType);
			insert_issue.setShelfNo(shelfNo);
			insert_issue.setLocation(location);
			insert_issue.setIssued_date(issued_date);
			insert_issue.setIssued_return_date(issued_return_date);
			insert_issue.setStatus(status);
			insert_issue.setSubscriberId(subscriberId);
			insert_issue.setIssueId(issueId);

		result= new LibraryDelegate().insertBookReturnDetails(insert_issue);
			

			JSONObject obj1= new JSONObject();
			obj1.put("status",result);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in insertBookIssueDetails : insertBookIssueDetails Ending");

		return null;
	}
	
	public ActionForward getSubCategory2ByCategoryAndSubCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String SubCategoryTypeCode=request.getParameter("subcategorycode");
			System.out.println(SubCategoryTypeCode);

			List<SubCategoryType1VO> SubCategoryType2List = new LibraryDelegate()
					.getSubCategory2ByCategoryAndSubCategory(SubCategoryTypeCode);


			JSONObject jsonObject = new JSONObject(SubCategoryType2List);
			jsonObject.accumulate("SubCategoryType2List", SubCategoryType2List);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}
	
	public ActionForward getTableBycategorytypeandSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		String category = request.getParameter("categorytype");
		String subcacode1=request.getParameter("subcategorytype1");
		String cattype = request.getParameter("subcategorytype");
		String status = request.getParameter("status");


		System.out.println(cattype);

		try {

			if( cattype.equalsIgnoreCase("all")){
			       cattype="%%";
				}
				if( category.equalsIgnoreCase("all")){
					category="%%";
				}
				if( subcacode1.equalsIgnoreCase("all")){
					subcacode1="%%";
				}

			LibraryDelegate obj = new LibraryDelegate();
			List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
			list = obj.getTableBycategorytypeandSub(cattype,status,category,subcacode1);


			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}


public ActionForward getTableBycategorytypeandSub1(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	 String category = request.getParameter("categorytype");
		String status = request.getParameter("status");
		String subcacode=request.getParameter("subcategorycode");
	String cattype = request.getParameter("subcategorytype1");

	System.out.println(cattype);

	try {

		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		list = obj.getTableBycategorytypeandSub1(cattype,status,category,subcacode);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

	   /*----------Others search Start With--------------*/
public ActionForward SearchOthersDetailsByStartWith(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();

		String searchTextVal = request.getParameter("searchname").trim();
		String location = request.getParameter("location");
		String select = request.getParameter("select");
		String accyear = request.getParameter("accyear");
		
		if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
			location="%%";
		}
		if(accyear.equalsIgnoreCase("all") || accyear.equalsIgnoreCase("")){
			accyear="%%";
		}
		/*request.setAttribute("searchTextVal", searchTextVal);*/

		list = new LibraryDelegate().SearchOthersDetailsByStartWith(searchTextVal,location,select,accyear);
		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
		request.setAttribute("studentList", list);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");
	return null;
}

public ActionForward getTableByStatus(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String status = request.getParameter("status");
	String categorycode = request.getParameter("categorycode");
	String subcategorycode = request.getParameter("subcategorycode");
	String subcategory1code = request.getParameter("subcategory1code");
	
	try {
		if( categorycode.equalsIgnoreCase("all")){
			categorycode="%%";
		}
		
		if( subcategorycode.equalsIgnoreCase("all")){
			subcategorycode="%%";
		}
		if( subcategory1code.equalsIgnoreCase("all")){
			subcategory1code="%%";
		}
		
		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		list = obj.getTableByStatus(status,categorycode,subcategorycode,subcategory1code);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
	public ActionForward getCategoryType3(ActionMapping mapping, ActionForm form,

			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");



	
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();

			String searchTextVal = request.getParameter("searchname");
			if(searchTextVal!=null){
				searchTextVal=searchTextVal.trim();
			}
			/*request.setAttribute("searchTextVal", searchTextVal);*/

		String cattype = request.getParameter("cattype");
		String subcatcode = request.getParameter("subcatcode");
		String subcatcode1 = request.getParameter("subcatcode1");
		String subcatcode2 = request.getParameter("subcatcode2");
		String subcatcode3 = request.getParameter("subcatcode3");
		String status = request.getParameter("status");
		try {

			if( cattype.equalsIgnoreCase("all")){
				cattype="%%";
			}
			if( subcatcode.equalsIgnoreCase("all")){
				subcatcode="%%";
			}
			if( subcatcode1.equalsIgnoreCase("all")){
				subcatcode1="%%";
			}
			if( subcatcode2.equalsIgnoreCase("all")){
				subcatcode2="%%";
			}
			if( subcatcode3.equalsIgnoreCase("all")){
				subcatcode3="%%";
			}

			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list1 = new ArrayList<CategoryTypeVO>();
			list1 = obj.getSubCategoryDetails3(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status);
			System.out.println(list1+"mvvvvvvvvvmmmmmmmmm");

			JSONObject obj1= new JSONObject();
			obj1.put("list", list1);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward getSubscriberNumber(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getSubscriberNumber Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
			String loc = request.getParameter("location");
			String subscriberType = request.getParameter("suscriberType");
			System.out.println("location name"+loc);
			System.out.println("subscriber type"+subscriberType);
			
			ArrayList<LibrarySubscribVO> subscriberNumber= new LibraryDelegate().getOtherSubscribeNunmber(loc,subscriberType);
			
			JSONObject obj = new JSONObject();
			obj.put("subscriberNumber", subscriberNumber);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getSubscriberNumber Ending");

		return null;
	}
	public ActionForward showBlockListedData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : showBlockListedData Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
			String loc = request.getParameter("locationName");
			String subscriberNo = request.getParameter("subscriberNo");
			String subscriberType = request.getParameter("suscriberType");
			
			System.out.println("location name"+loc);
			System.out.println("subscriber type"+subscriberType);
			
			ArrayList<LibrarySubscribVO> BlockListData= new LibraryDelegate().showBlockListedData(loc,subscriberType,subscriberNo);
			
			JSONObject obj = new JSONObject();
			obj.put("data", BlockListData);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : showBlockListedData Ending");

		return null;
	}
	/*---------------------------Others search Ends With------------------------*/
	public ActionForward SearchOthersDetailsByEndsWith(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();

			String searchTextVal = request.getParameter("searchname").trim();
			String location = request.getParameter("location");
			String select = request.getParameter("select");
			String accyear = request.getParameter("accyear");
			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location="%%";
			}
			if(accyear.equalsIgnoreCase("all") || accyear.equalsIgnoreCase("")){
				accyear="%%";
			}
			list = new LibraryDelegate().SearchOthersDetailsByEndsWith(searchTextVal,location,select,accyear);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	/*----------------------Seach subscriber GO Details--------------------------*/
	public ActionForward gotosubscribersDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHSUBSCRIBER);
			
			LibrarySubscribVO vo=new LibrarySubscribVO();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String subId = request.getParameter("subId");
			String subscriberType=request.getParameter("subscriberType");

			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if(academic_year.equalsIgnoreCase("all")){
				academic_year="%%";
			}
			vo = new LibraryDelegate().gotosubscribersDetails(location,subId,academic_year,subscriberType);
			request.setAttribute("studentList", vo);
			request.setAttribute("subId",subId);
			request.setAttribute("location",location);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryHome Ending");
             
		return mapping.findForward(LibraryMessageConstants.subscribersDetails);
	}
	
	
	public ActionForward updateSubscriberDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Starting");
		try{	
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
			LibrarySubscribVO resultData=new LibrarySubscribVO();
			
			resultData.setLoc_ID(request.getParameter("locationName"));
			//resultData.setLocationName(request.getParameter("locationName"));
			resultData.setOtherUserName(request.getParameter("otherUserName"));
			resultData.setOtherUserGender(request.getParameter("otherUserGender"));
			resultData.setOtherUserContact(request.getParameter("otherUserContact"));
			resultData.setOtherUserEmail(request.getParameter("otherUserEmail"));
			
			resultData.setAccyear(request.getParameter("accyear"));
			resultData.setSubscriberId(request.getParameter("subscriberId"));
			resultData.setSubscriberType(request.getParameter("suscriberType"));
			resultData.setTeacher(request.getParameter("teacher"));
			resultData.setStudent(request.getParameter("student"));
			resultData.setOther(request.getParameter("other"));
			resultData.setSubscriberNo(request.getParameter("subscriberNo"));
			resultData.setStaffRegisterId(request.getParameter("staffRegId"));
			resultData.setStaffID(request.getParameter("staffId"));
			resultData.setDepositType(request.getParameter("depositType"));
			resultData.setEnterAmount(request.getParameter("enterAmount"));
			resultData.setCardNo(request.getParameter("cardNo"));
			resultData.setCheckNo(request.getParameter("checkNo"));
			resultData.setStatus(request.getParameter("status"));
			resultData.setPaymentDate(request.getParameter("paymentDate"));
			resultData.setOtherUserAddr(request.getParameter("otherUserAddr").trim());


			
			String result = new LibraryDelegate().updateSubscriberDetails(resultData);

			JSONObject obj=new JSONObject();
			obj.put("resultData", result);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getSingleNomineeDetails Ending");

		return null;
	}
	
	
	
	
	public ActionForward getcategorylist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getcategorylist Starting");

		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String cateid = request.getParameter("categoryid");
		System.out.println("cateid is "+cateid);
		String status=request.getParameter("statusid");
		System.out.println("status is "+status);
		try {
			if(cateid.equalsIgnoreCase("all")){
				cateid="%%";
			}
			if(status.equalsIgnoreCase("all")){
				status="%%";
			}
			list= new  LibraryDelegate().getcategorylist(cateid,status);

			JSONObject obj1= new JSONObject();
			obj1.put("catlist",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getcategorylist ending");

		return null;
	}

	
	public ActionForward validatesubcatname(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			String list = null;
			String subname = request.getParameter("subname");
			System.out.println("subname "+subname);

			list= new  LibraryDelegate().ValidateSubcat(subname);
			System.out.println("listttttttt"+list);


			JSONObject object = new JSONObject();
			object.put("des_available", list);
			response.getWriter().print(list);

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
	
	public ActionForward validatesubcatnameupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			String list = null;
			String subname = request.getParameter("subname");
			
			System.out.println("subname "+subname);

			list= new  LibraryDelegate().ValidateSubcatupdate(subname);


			JSONObject object = new JSONObject();
			object.put("des_available", list);
			response.getWriter().print(list);

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
	
	public ActionForward validatesubcat3name(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			String list = null;
			String subname = request.getParameter("subname");
			System.out.println("subname "+subname);

			list= new  LibraryDelegate().ValidateSubcat3(subname);
 System.out.println(list);
			JSONObject object = new JSONObject();
			object.put("des_available", list);
			response.getWriter().print(list);

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




public ActionForward getTabBySub2CategoryType(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String cattype = request.getParameter("categorytype");
	String status = request.getParameter("status");
	String subcategory = request.getParameter("subcategorycode");
	String subcategory1 = request.getParameter("subcategory1code");
	String subcategory2 = request.getParameter("subcategory2code");
	


	
	try {

		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		} 
		if( subcategory.equalsIgnoreCase("all")){
			subcategory="%%";
		}
		if( subcategory1.equalsIgnoreCase("all")){
			subcategory1="%%";
		}
		if( subcategory2.equalsIgnoreCase("all")){
			subcategory2="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.getTabBySub2CategoryType(cattype,status,subcategory,subcategory1,subcategory2);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward getTabBySub2subCategoryType(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String cattype = request.getParameter("categorytype");
	String status = request.getParameter("status");
	String subcategory = request.getParameter("subcategorycode");
	String subcategory1 = request.getParameter("subcategory1code");
	String subcategory2 = request.getParameter("subcategory2code");

	System.out.println(cattype);

	try {

		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		}
		if( subcategory.equalsIgnoreCase("all")){
			subcategory="%%";
		}
		if( subcategory1.equalsIgnoreCase("all")){
			subcategory1="%%";
		}
		if( subcategory2.equalsIgnoreCase("all")){
			subcategory2="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.getTabBySub2subCategoryType(cattype,status,subcategory,subcategory1,subcategory2);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward getTabBySub2subCategory1Type(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");



	String cattype = request.getParameter("categorytype");
	String status = request.getParameter("status");
	String subcategory = request.getParameter("subcategorycode");
	String subcategory1 = request.getParameter("subcategory1code");
	String subcategory2 = request.getParameter("subcategory2code");

	System.out.println(cattype);

	try {

		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		}
		if( subcategory.equalsIgnoreCase("all")){
			subcategory="%%";
		}
		if( subcategory1.equalsIgnoreCase("all")){
			subcategory1="%%";
		}
		if( subcategory2.equalsIgnoreCase("all")){
			subcategory2="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.getTabBySub2subCategory1Type(cattype,status,subcategory,subcategory1,subcategory2);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward searchSubCatType1(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String catcode = request.getParameter("catcode");
	
	String subcatcode = request.getParameter("subcatcode");
	String subcatcode1 = request.getParameter("subcategorytype1");
	String status = request.getParameter("status");
	String searchname = request.getParameter("searchname").trim();

	System.out.println(searchname);

	System.out.println("Cat Code "+subcatcode1);
	try {

		if(catcode.equalsIgnoreCase("all")){
			catcode="%%";
		}
		if(subcatcode.equalsIgnoreCase("all") || subcatcode.equalsIgnoreCase(null)){
			subcatcode="%%";
		}
		if(subcatcode1.equalsIgnoreCase("all") || subcatcode1.equalsIgnoreCase(null)){
			subcatcode1="%%";
		}				
		if(searchname.equalsIgnoreCase("all") ||searchname.equalsIgnoreCase(null)||searchname==""){
			searchname="%%";
		}


		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType1VO> list = new ArrayList<SubCategoryType1VO>();
		list = obj.searchSubCatType1(searchname,catcode,subcatcode,subcatcode1,status);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward getTableBySub2Status(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String cattype = request.getParameter("categorytype");
	String status = request.getParameter("status");
	String subcategory = request.getParameter("subcategorycode");
	String subcategory1 = request.getParameter("subcategory1code");
	String subcategory2 = request.getParameter("subcategory2code");

	try {
		
		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		}
		if( subcategory.equalsIgnoreCase("all")){
			subcategory="%%";
		}
		if( subcategory1.equalsIgnoreCase("all")){
			subcategory1="%%";
		}
		if( subcategory2.equalsIgnoreCase("all")){
			subcategory2="%%";
		}
		
		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.getTableBySub2Status(cattype,status,subcategory,subcategory1,subcategory2);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward getTabBySub2subCategory2Type(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String cattype = request.getParameter("categorytype");
	String status = request.getParameter("status");
	String subcategory = request.getParameter("subcategorycode");
	String subcategory1 = request.getParameter("subcategory1code");
	String subcategory2 = request.getParameter("subcategory2code");

	System.out.println(cattype);
	
	try {

		if( cattype.equalsIgnoreCase("all")){
			cattype="%%";
		}
		if( subcategory.equalsIgnoreCase("all")){
			subcategory="%%";
		}
		if( subcategory1.equalsIgnoreCase("all")){
			subcategory1="%%";
		}
		if( subcategory2.equalsIgnoreCase("all")){
			subcategory2="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.getTabBySub2subCategory2Type(cattype,status,subcategory,subcategory1,subcategory2);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward searchSubCatType2(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String searchname = request.getParameter("searchname");
	String categorytype = request.getParameter("categorytype");
	String subcategorytype = request.getParameter("subcategorycode");
	String subcategorytype1 = request.getParameter("subcategory1code");
	String subcategorytype2 = request.getParameter("subcategory2code");
	String status = request.getParameter("status");

	System.out.println(searchname);

	try {

		if( searchname.equalsIgnoreCase("")){
			searchname="%%";
		}
		if( categorytype.equalsIgnoreCase("all")){
			categorytype="%%";
		}
		if( subcategorytype.equalsIgnoreCase("all")){
			subcategorytype="%%";
		}
		if( subcategorytype1.equalsIgnoreCase("all")){
			subcategorytype1="%%";
		}
		if( subcategorytype2.equalsIgnoreCase("all")){
			subcategorytype2="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryType2VO> list = new ArrayList<SubCategoryType2VO>();
		list = obj.searchSubCatType2(searchname,categorytype,subcategorytype,subcategorytype1,subcategorytype2,status);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
 }

   /*------------------------IssueStatementBySubScriberType--------------------------*/
public ActionForward IssueStatementBySubScriberType(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
	try {
               
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHSUBSCRIBER);
		
		
		LibrarySubscribVO vo=new LibrarySubscribVO();
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String subId = request.getParameter("subId");
		String subscriberType=request.getParameter("subscriberType");

		if(location.equalsIgnoreCase("all")){
			location="%%";
		}
		
		vo = new LibraryDelegate().IssueStatementBySubScriberType(location,subId,academic_year,subscriberType);
		list=new LibraryDelegate().IssueStatementTable(location,subId,academic_year,subscriberType);
		request.setAttribute("studentList", vo);
		request.setAttribute("issuedetails",list);
		
		request.setAttribute("listsize",list.size());
		request.setAttribute("subId", subId);
		request.setAttribute("subscriberType", subscriberType);
		
	    }
	catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");
	return mapping.findForward(LibraryMessageConstants.IssueStatementBySubScriberType);
}

      /*------------------------IssueReturns By SubScriberType--------------------------*/

public ActionForward GOtOIssueReturns(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : LibraryHome Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHSUBSCRIBER);
		
		LibrarySubscribVO vo=new LibrarySubscribVO();
		String subId = request.getParameter("subId");
		String subscriberType=request.getParameter("subscriberType");
		String issueId = request.getParameter("subId");
		vo = new LibraryDelegate().GOtOIssueReturns(subId,subscriberType,issueId);
		
		request.setAttribute("studentList",vo);
		request.setAttribute("subId",subId);
		request.setAttribute("subscriberType",subscriberType);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : LibraryHome Ending");
	return mapping.findForward(LibraryMessageConstants.returns);
}

public ActionForward subCategoryType3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE3);
		
		LibraryDelegate obj = new LibraryDelegate();
		List<SubCategoryTypeVO> list = new ArrayList<SubCategoryTypeVO>();
		List<CategoryTypeVO> list1 = new ArrayList<CategoryTypeVO>();


		list = obj.getSubCategoryDetails3();
		list1 =  obj.getSubCategoryDetails1();


		request.setAttribute("SubcategoryDetails", list);
		request.setAttribute("SubcategoryDetails1", list1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return mapping.findForward(LibraryMessageConstants.SUB_CATEGORY_TYPE3);
}

public ActionForward addSubCategoryType3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE3);

		String title = "Add Sub Category Type 3 Details";
		request.setAttribute("category", title);

		LibraryDelegate obj=new LibraryDelegate();
		List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
		List<CategoryTypeVO> subcatlist=new ArrayList<CategoryTypeVO>();
		List<CategoryTypeVO> subcatlist1=new ArrayList<CategoryTypeVO>();
		List<CategoryTypeVO> subcatlist2=new ArrayList<CategoryTypeVO>();
		list=obj.getCategoryDetails();
		
		subcatlist=obj.getSubCategoryDetails();
		subcatlist1=obj.getSubCategoryDetails1();
		subcatlist2=obj.getSubCategoryDetails1();
		
		request.setAttribute("CategoryDetails", list);
		request.setAttribute("SubCategoryDetails", subcatlist);
		request.setAttribute("SubCategoryDetails1", subcatlist1);
		request.setAttribute("SubCategoryDetails2", subcatlist2);


	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE3);
}

public ActionForward editSubCategoryTypeDetail3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

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
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBCATEGORYTYPE3);
		String title = "Update Sub Category Type 3 Details";
		request.setAttribute("category", title);


		LibraryDelegate obj=new LibraryDelegate();
		List<CategoryTypeVO> list=new ArrayList<CategoryTypeVO>();
		list=obj.getCategoryDetails();
		request.setAttribute("CategoryDetails", list);

		String id = request.getParameter("catid");
		SubCategoryTypeVO edit_category = new LibraryDelegate().editSubCategoryType3(id);
		System.out.println(edit_category);
		request.setAttribute("editlist",edit_category);

	}
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

	return mapping.findForward(LibraryMessageConstants.ADD_SUB_CATEGORY_TYPE3);


}

public ActionForward insertSubCategoryType3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {

		String userId=HelperClass.getCurrentUserID(request);
		SubCategoryTypeVO vo=new SubCategoryTypeVO();

		String hidden_id=request.getParameter("subcategory3_id");
		String categorytype=request.getParameter("categorytype");
		String subcategorytype = request.getParameter("subcategorytype");
		String subcategorytype1=request.getParameter("subcategorytype1");
		String subcategorytype2=request.getParameter("subcategorytype2");
		String subcategorytype3code=request.getParameter("subcategorytype3code");
		String subcategorytype3name=request.getParameter("subcategorytype3name");
		String Status=request.getParameter("status");
		String Description=request.getParameter("description");

		vo.setSubcategory_id(hidden_id);			
		vo.setCategorytypecode(categorytype);
		vo.setSubcategory_code(subcategorytype);			
		vo.setSubcategory_code1(subcategorytype1);
		vo.setSubcategory_code2(subcategorytype2);
		vo.setSubcategorytypecode3(subcategorytype3code);
		vo.setSubcategorytypename3(subcategorytype3name);
		vo.setStatus(Status);
		vo.setDescription(Description);
		vo.setCreated_by(userId);

		String status = new LibraryDelegate().insertSubCategoryTypeDetail3(vo);
		System.out.println("status "+status);

		JSONObject obj1= new JSONObject();
		obj1.put("status", status);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward inactiveSubCategoryType3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

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
		String title = "Update Sub Category Type 3 Details";
		request.setAttribute("category", title);

		String id[] = request.getParameterValues("Inactivelist[]");

		SubCategoryTypeVO vo=new SubCategoryTypeVO();


		String status=new LibraryDelegate().inactiveSubCategoryType3(id,vo);

		JSONObject obj1= new JSONObject();
		obj1.put("status", status);
		response.getWriter().print(obj1);


	}

	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

	return null;

}

public ActionForward getSubCategory3ByCategoryAndSubCategory(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationAction: getClassByLocation Starting");
	try {

		String SubCategoryTypeCode=request.getParameter("subcategorycode");
		System.out.println(SubCategoryTypeCode);

		List<SubCategoryType1VO> SubCategoryType2List = new LibraryDelegate()
				.getSubCategory3ByCategoryAndSubCategory(SubCategoryTypeCode);

		JSONObject jsonObject = new JSONObject(SubCategoryType2List);
		jsonObject.accumulate("SubCategoryType2List", SubCategoryType2List);
		response.getWriter().print(jsonObject);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationAction: getClassByLocation Ending");
	return null;
}


public ActionForward issuestatementissue(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : LibraryHome Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		
		LibrarySubscribVO vo=new LibrarySubscribVO();
		String subId = request.getParameter("subId");
		String issueId = request.getParameter("subId");
		String subscriberType=request.getParameter("subscriberType");
		
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		
		vo = new LibraryDelegate().issuestatementissue(subId,issueId,subscriberType);
		
		request.setAttribute("studentList",vo);
		request.setAttribute("subId",subId);
		request.setAttribute("issueId",issueId);
		request.setAttribute("subscriberType",subscriberType);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : LibraryHome Ending");
	return mapping.findForward(LibraryMessageConstants.issues);
}

  
  public ActionForward getStockEntryBookList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getStockEntryBookList Starting");
		try {

			List<LibraryStockEntryDetailsForm > liblist = new LibraryDelegate().getStockEntryBookList();
					
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("StockList",liblist );
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getStockEntryBookList Ending");
		return null;
	}
  
  
  public ActionForward editStockEntryDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction: editStockEntryDetail Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);
			
			String arg = "Modify Stock Entry Details";
			request.setAttribute("msg", arg);
			
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getCategoryDetails();
			request.setAttribute("categoryDetails", list);
			String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
			List<LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = obj.getLibLocationsDetails(location);
			request.setAttribute("librarylocationsDetails", loclist);
			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
			request.setAttribute("publicationlist", publist);
			List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
			request.setAttribute("suplist", suplist);
			

			String id = request.getParameter("stockid");

			LibraryStockEntryDetailsForm edit_StockEntry = new LibraryDelegate().editStockEntryDetail(id);
			System.out.println(edit_StockEntry.getBookPhoto());
			request.setAttribute("editlist",edit_StockEntry);
			
			request.setAttribute("entryid", id);

			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction : editStockEntryDetail Starting Ending");

		return mapping.findForward(LibraryMessageConstants.StockEntryDetails);

	}

  
  public ActionForward blockTheSubscriber(ActionMapping map,ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :blockSubscriberDetails Starting");
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
			String subscriberNo = request.getParameter("subscriberNo");
			System.out.println(subscriberNo);
			String status = new LibraryDelegate().blockTheSubscriber(subscriberNo);
			
			JSONObject obj = new JSONObject();
			obj.put("status",status);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in blockSubscriberDetails ending");
	return null;

}
  public ActionForward getStaffRegId(ActionMapping map,ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :blockSubscriberDetails Starting");
		try{
			
			String loc = request.getParameter("loc");
			String searchterm = request.getParameter("searchTerm");
			System.out.println(loc);
			
			ArrayList<LibrarySubscribVO> list = new LibraryDelegate().getStaffRegId(loc,searchterm);
			
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, list);
			response.getWriter().print(jsonObject);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in blockSubscriberDetails ending");
	return null;
}
  
  



public ActionForward getSubCategoryType3(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	String catcode = request.getParameter("catcode");
	String subcatcode = request.getParameter("subcatcode");
	String status = request.getParameter("status");


	System.out.println(catcode+"     "+subcatcode);

	try {
		
		if( catcode.equalsIgnoreCase("all")){
			catcode="%%";
		}

		if( subcatcode.equalsIgnoreCase("all") || subcatcode.equalsIgnoreCase("")){
			subcatcode="%%";
		}

		LibraryDelegate obj = new LibraryDelegate();
		List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		list = obj.getSubCategoryList3(catcode,subcatcode,status);


		JSONObject obj1= new JSONObject();
		obj1.put("list", list);
		response.getWriter().print(obj1);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}




  public ActionForward LibraryStockEntryDetailsList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : LibraryStockEntryDetailsList Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction :LibraryStockEntryDetailsList Ending");

	return mapping.findForward(LibraryMessageConstants.StockEntryDetailslist);
}

  
 
  
  public ActionForward SearchCategoryType3List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		String catcode = request.getParameter("catcode");
		String subcatcode = request.getParameter("subcatcode");
		String subcatcode1 = request.getParameter("subcatcode1");
		String subcatcode2 = request.getParameter("subcatcode2");
		String subcatcode3 = request.getParameter("subcatcode3");
		String status = request.getParameter("status");
		String searchname = request.getParameter("searchname").trim();


		System.out.println(catcode+"///---///"+subcatcode+"///---///"+status);

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);
				if(catcode.equalsIgnoreCase("all")){
					catcode="%%";
				};
				if(subcatcode.equalsIgnoreCase("all") || subcatcode.equalsIgnoreCase(null)){
					subcatcode="%%";
				}
				if(subcatcode1.equalsIgnoreCase("all") || subcatcode1.equalsIgnoreCase(null)){
					subcatcode1="%%";
				}
				if(subcatcode2.equalsIgnoreCase("all") || subcatcode2.equalsIgnoreCase(null)){
					subcatcode2="%%";
				}
				if(subcatcode3.equalsIgnoreCase("all") || subcatcode3.equalsIgnoreCase(null)){
					subcatcode3="%%";
				}				
				if(searchname.equalsIgnoreCase("all") ||searchname.equalsIgnoreCase(null)){
					searchname="%%";
				}
			
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.SearchCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);

			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

  
  public ActionForward duplicateDataCheck(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : duplicateDataCheck Starting");
		try{
			
			String studentIdHidden = request.getParameter("studentIdHidden");
			String suscriberType = request.getParameter("suscriberType");
			String locationName = request.getParameter("locationName");
			String accyear = request.getParameter("accyear");
			String staffId = request.getParameter("staffId");
			 
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setStudentIdHidden(studentIdHidden);
			pojo.setSuscriberType(suscriberType);
			pojo.setLocationName(locationName);
			pojo.setAccId(accyear);
			pojo.setStaffid(staffId);
			
			System.out.println(studentIdHidden);
			System.out.println(suscriberType);
			System.out.println(locationName);
			System.out.println(accyear);
			System.out.println("Staff id"+staffId);
			
			
			String result = new LibraryDelegate().duplicateDataCheck( pojo);
			JSONObject obj = new JSONObject();
			obj.put("status", result);
			response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : duplicateDataCheck Ending");

	  return null;
  }
  
  public ActionForward activeSubCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			String id[] = request.getParameterValues("Activelist[]");

			SubCategoryTypeVO vo=new SubCategoryTypeVO();
			String status=new LibraryDelegate().activeSubCategoryType(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
  
  public ActionForward activeSubCategoryType3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			String id[] = request.getParameterValues("Activelist[]");
			System.out.println("********************************************"+id);
			SubCategoryTypeVO vo=new SubCategoryTypeVO();
			String status=new LibraryDelegate().activeSubCategoryType3(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
  
  
  public ActionForward getBlockListedStaffData(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStaffData Starting");
	  try{
		  String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		  String location = request.getParameter("location");
		  
		  System.out.println("***********************************************************"+accyear);
		  System.out.println("************************************************************"+location);
		 
		  ArrayList<LibrarySubscribVO> list = new LibraryDelegate().getBlockListedStaffData(accyear,location);
		  JSONObject obj = new JSONObject();
		  obj.put("data", list);
		  response.getWriter().print(obj);
 	  }catch(Exception e){
			e.printStackTrace();
		}
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStaffData Ending");
  return null;
}

  public ActionForward getBlockListedStudentData(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStudentData Starting");
	  try{
		  String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		  String location = request.getParameter("location");
		  
		  ArrayList<LibrarySubscribVO> list = new LibraryDelegate().getBlockListedStudentData(accyear,location);
		  JSONObject obj = new JSONObject();
		  obj.put("data", list);
		  response.getWriter().print(obj);
 	  }catch(Exception e){
			e.printStackTrace();
		}
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStudentData Ending");
   return null;
}
  public ActionForward getBlockListedOtherData(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStudentData Starting");
	  try{
		  String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		  String location = request.getParameter("location");
		  
		  System.out.println("***********************************************************"+accyear);
		  System.out.println("************************************************************"+location);
		 
		  ArrayList<LibrarySubscribVO> list = new LibraryDelegate().getBlockListedOtherData(accyear,location);
		  JSONObject obj = new JSONObject();
		  obj.put("data", list);
		  response.getWriter().print(obj);
 	  }catch(Exception e){
			e.printStackTrace();
		}
	  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getBlockListedStudentData Ending");
  return null;
}
  public ActionForward unblockSubscriber(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
  {
	  JLogger.log(0, JDate.getTimeString(new Date())
			  + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date())
			  + " Control in LibraryAction : getBlockListedStudentData Starting");
	  try{
		  String id = request.getParameter("id");

		  System.out.println("***********************************************************"+id);

		  String result = new LibraryDelegate().unblockSubscriber(id);
		  JSONObject obj = new JSONObject();
		  obj.put("status", result);
		  response.getWriter().print(obj);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  JLogger.log(0, JDate.getTimeString(new Date())
			  + MessageConstants.END_POINT);
	  logger.info(JDate.getTimeString(new Date())
			  + " Control in LibraryAction : getBlockListedStudentData Ending");
	  return null;
}

  
  public ActionForward getCategoryListBySearch(ActionMapping mapping, ActionForm form,
		  HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	  logger.setLevel(Level.DEBUG);
	  JLogger.log(0, JDate.getTimeString(new Date())
			  + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date())
			  + " Control in LibraryAction :getcategorylist Starting");

	  ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
	  String cateid = request.getParameter("categoryid");
	  String status=request.getParameter("statusid");
	  String searchname=request.getParameter("searchname").trim();
	  try {
		  if(cateid.equalsIgnoreCase("all")){
			  cateid="%%";
		  }
		  if(status.equalsIgnoreCase("all")){
			  status="%%";
		  }
		  if(searchname.equalsIgnoreCase("")){
			  searchname="%%";
		  }
		  list= new  LibraryDelegate().getCategoryListBySearch(cateid,status,searchname);

		  JSONObject obj1= new JSONObject();
		  obj1.put("catlist",list);
		  response.getWriter().print(obj1);

	  }catch(Exception e){
		  e.printStackTrace();
	  }


	  JLogger.log(0, JDate.getTimeString(new Date())
			  + MessageConstants.END_POINT);
	  logger.info(JDate.getTimeString(new Date())
			  + " Control in LibraryAction:getcategorylist ending");

	  return null;
  }

  
  public ActionForward activeSubCategoryType1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			String id[] = request.getParameterValues("Activelist[]");

			SubCategoryType1VO vo=new SubCategoryType1VO();
			String status=new LibraryDelegate().activeSubCategoryType1(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
  
  public ActionForward activeSubCategoryType2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			String id[] = request.getParameterValues("Activelist[]");

			SubCategoryType2VO vo=new SubCategoryType2VO();
			String status=new LibraryDelegate().activeSubCategoryType2(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
  
  public ActionForward activeCategoryType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

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
			String title = "Update Category Type Details";
			request.setAttribute("category", title);
			String id[] = request.getParameterValues("Activelist[]");

			CategoryTypeVO vo=new CategoryTypeVO();
			String status=new LibraryDelegate().activeCategoryType(id,vo);

			JSONObject obj1= new JSONObject();
			obj1.put("status", status);
			response.getWriter().print(obj1);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return null;

	}
  
  public ActionForward studentSearchbyadmissionNoForlibrary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");

		String currentYear=(String) request.getSession(false).getAttribute("current_academicYear");
			List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
			try {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String searchterm = request.getParameter("searchTerm");
				String locationId=request.getParameter("locid");
				System.out.println("Location Name ****************"+locationId);
				
				registrationVo.setSearchTerm(searchterm);
				registrationVo.setLocationId(locationId);
				registrationVo.setAcademicYear(currentYear);
				
				LibraryDAOIMPL daoImpl = new LibraryDAOIMPL();
				searchStudentList = daoImpl.studentSearchbyadmissionNoForlibrary(registrationVo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

				response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

			return null;
	}

  public ActionForward addPublisherSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	  logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : addPublisherSettings Starting");
	  
	
	  LibraryStockEntryVO obj=new LibraryStockEntryVO();
	  obj.setPublisher(request.getParameter("publisher"));
	  obj.setAddress(request.getParameter("address"));
	  obj.setEmail(request.getParameter("email"));
	  obj.setPubdate(request.getParameter("pubdate"));
	  obj.setTelephone(request.getParameter("telphone"));
	  obj.setMobilenum(request.getParameter("mobilenum"));
	  obj.setFax(request.getParameter("fax"));
	  obj.setEntry_id(request.getParameter("hiddenid"));
	  
	     UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		 String userCode = userDetailVO.getUserId();
		 obj.setCreatedby(userCode);
		
	
	  String result = new LibraryDelegate().publisherSettings(obj);
	  
	  JSONObject json = new JSONObject();
	  json.put("status",result);
	  response.getWriter().print(json);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : addPublisherSettings Ending");
	  return null;
  }
  public ActionForward ListpublisherSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : ListpublisherSettings Starting");

	
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : ListpublisherSettings Ending");

		return mapping.findForward(LibraryMessageConstants.ListPublisherSettings);
	}
  public ActionForward getPublisherSettingList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getStockEntryBookList Starting");
		try {

			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
					
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("pubList",publist );
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getStockEntryBookList Ending");
		return null;
	}

  
  public ActionForward editpublisherSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction: editStockEntryDetail Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			
			String arg = "Modify Publisher";
			request.setAttribute("msg", arg);
			 
			
			LibraryDelegate obj = new LibraryDelegate();
	
			String id = request.getParameter("pubid");

			LibraryStockEntryVO edit_PubSetting = new LibraryDelegate().editpublisherSetting(id);
			request.setAttribute("pubid",id);
			request.setAttribute("editlist",edit_PubSetting);
	
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction : editStockEntryDetail Starting Ending");

		return mapping.findForward(LibraryMessageConstants.publisherSettings);

	}
  public ActionForward deletepublisherSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : deletepublisherSetting Starting");
		
		String status = null;
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);
			String deleteId[] = request.getParameter("deleteid").split(",");
			
			System.out.println("deleteId :"+deleteId);
			
			
			status =  new LibraryDelegate().deletepublisherSetting(deleteId);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("pubsetting", status);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : deletepublisherSetting Ending");
		
		return null;
	}
  public ActionForward validationpubsettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :validationpubsettings Starting");
		try{
		
			 String pub =request.getParameter("publisher");
			 String address=request.getParameter("address");
			 String email=request.getParameter("email");
			 String telphone=request.getParameter("telphone");
			 String mobilenum=request.getParameter("mobilenum");
			 
			 
			String result = new LibraryDelegate().validationpubsettings(pub,address,email,telphone,mobilenum);
			
			JSONObject obj=new JSONObject();
			obj.put("result", result);
			response.getWriter().print(obj);

			           
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :validationpubsettings Ending");
		
		return null;
	}
  public ActionForward TransferSubscriberDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_TRANSFERSUBSCRIBERS );
			
          String accademinyear=(String)request.getSession(false).getAttribute("current_academicYear");
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
          	ArrayList <LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = new LibraryDelegate().getLibLocationsDetails(location);
			
			request.setAttribute("librarylocationsDetails", loclist);
			request.setAttribute("libloc",loclist);
          
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
             request.setAttribute("accademic_year", accademinyear);
            


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return mapping
				.findForward(LibraryMessageConstants.TransferSubscriberDetails);
	}
  public ActionForward TransferStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferStudent Starting");
		
		String status = null;
		
		try {
			
			
			String subscriberId[] = request.getParameter("subscriberId").split(",");
			String locid=request.getParameter("location");

			status = new LibraryDelegate().TransferStudent(subscriberId,locid);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("trasub", status);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferStudent Ending");
		
		return null;
	}

  
  
  
	public ActionForward getTranferStudentListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getTranferStudentListDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String libloc=request.getParameter("liblocation");

		/*	String searchTerm = request.getParameter("searchname".trim());*/

			String select = request.getParameter("select");

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all") || academic_year.equalsIgnoreCase("")){
				academic_year = "%%";
			}
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname = "%%";
			}
			  if(sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")){
					sectionid ="%%";
				}
			  if(libloc.equalsIgnoreCase("") || libloc.equalsIgnoreCase("all")){
				  libloc ="%%";
				}

			list = new LibraryDelegate().getTranferStudentListDetails(academic_year,location,select,classname,sectionid,libloc);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getTranferStudentListDetails Ending");

		return null;
	}
	
	public ActionForward getTrasferStudentListByClassName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String select = request.getParameter("select"); 
			String liblocation = request.getParameter("liblocation");
			String searchTerm = request.getParameter("searchname".trim());

			if(academic_year.equalsIgnoreCase("all") || academic_year.equalsIgnoreCase("")){
				academic_year = "%%";
			}
			
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname = "%%";
			}
			if(liblocation.equalsIgnoreCase("all") || liblocation.equalsIgnoreCase("")){
				liblocation = "%%";
			}
			list = new LibraryDelegate().getTrasferStudentListByClassName(academic_year,location,classname,select,liblocation);



			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	
  
	public ActionForward getTrasferStudentListBySection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String select = request.getParameter("select"); 
			String liblocation = request.getParameter("liblocation");

			String searchTerm = request.getParameter("searchname".trim());

			if(academic_year.equalsIgnoreCase("all") || academic_year.equalsIgnoreCase("")){
				academic_year ="%%";
			}
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname ="%%";
			}
			
			if(sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")){
				sectionid ="%%";
			}
			if(liblocation.equalsIgnoreCase("") || liblocation.equalsIgnoreCase("all")){
				liblocation ="%%";
			}

			list = new LibraryDelegate().getTrasferStudentListBySection(academic_year,location,classname,sectionid,select,liblocation);
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}

	public ActionForward getTransferStaffListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String department = request.getParameter("department");
			String designation = request.getParameter("designation");
			String select = request.getParameter("select");
			String libloc = request.getParameter("libloc");

			String searchTerm = request.getParameter("searchname".trim());

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all") || academic_year.equalsIgnoreCase("")){
				academic_year = "%%";
			}
			if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase("")){
				department = "%%";
			}
			if(designation.equalsIgnoreCase("all") || designation.equalsIgnoreCase("")){
				designation = "%%";
			}
			if(libloc.equalsIgnoreCase("all") || libloc.equalsIgnoreCase("")){
				libloc = "%%";
			}
			list = new LibraryDelegate().getTransferStaffListDetails(academic_year,location,select,department,designation,libloc);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	public ActionForward getTransferOthersListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		
		
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String select = request.getParameter("select"); 
			String libloc = request.getParameter("libloc");
			
			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location = "%%";
			}
			if(academic_year.equalsIgnoreCase("all") || academic_year.equalsIgnoreCase("")){
				academic_year = "%%";
			}
			if(libloc.equalsIgnoreCase("all") || libloc.equalsIgnoreCase("")){
				libloc = "%%";
			}
			
			list = new LibraryDelegate().getTransferOthersListDetails(location,select,academic_year,libloc);
			
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
  
	public ActionForward getlocationStudentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String libloc=request.getParameter("liblocation");

			String searchTerm = request.getParameter("searchname".trim());

			String select = request.getParameter("select");

			

			list = new LibraryDelegate().getlocationStudentList(libloc,select);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentData", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
  
	public ActionForward getliblocationstafflist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getliblocationstafflist Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String libloc=request.getParameter("liblocation");

			String searchTerm = request.getParameter("searchname".trim());

			String select = request.getParameter("select");

			

			list = new LibraryDelegate().getliblocationstafflist(libloc,select);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentData", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : getliblocationstafflist Ending");

		return null;
	}
  
	public ActionForward getliblocatinotherlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		
		
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String libloc=request.getParameter("libloc");
			String select = request.getParameter("select");

			if(location.equalsIgnoreCase("all")){
				location = "%%";
			}
			
			list = new LibraryDelegate().getliblocatinotherlist(academic_year,location,libloc,select);
			
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
				

			request.setAttribute("studentList", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	
	public ActionForward TransferSubscriberbySearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferSubscriberbySearch Starting");


		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String liblocid=request.getParameter("liblocid");
			String select = request.getParameter("select");
			String searchTextVal  = request.getParameter("searchname").trim();

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location ="%%";
			}
			if(academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")){
				academic_year ="%%";
			}
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname ="%%";
			}
			if(sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")){
				sectionid ="%%";
			}
			
			List = librarydelegate.TransferSubscriberbySearch(searchTextVal,location,academic_year,liblocid,select,classname,sectionid);
			
			request.setAttribute("searchnamelistValue", searchTextVal);
			
			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferSubscriberbySearch Ending");

		return null;
	}
	
	public ActionForward TransferSubscriberbyStaffSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferSubscriberbyStaffSearch Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String liblocid=request.getParameter("liblocid");
			String department=request.getParameter("department");
			String designation=request.getParameter("designation"); 
			
			String searchTextVal = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
            	location="%%";
            }
            if(accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")){
            	accyear="%%";
            }
            if(liblocid.equalsIgnoreCase("all") || liblocid.equalsIgnoreCase("")){
            	liblocid="%%";
            }
            if(department.equalsIgnoreCase("") || department.equalsIgnoreCase("all")){
            	department="%%";
            }
            if(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase("all")){
            	designation="%%";
            }
            
            
			/*request.setAttribute("searchTextVal", searchTextVal);*/

			list = new LibraryDelegate().TransferSubscriberbyStaffSearch(searchTextVal,location,liblocid,select,department,designation,accyear);
			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			/*request.setAttribute("studentList", list);*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : TransferSubscriberbyStaffSearch Ending");
		return null;
	}
	public ActionForward TransferSubscriberbyotherSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String location = request.getParameter("location");
			String searchTextVal = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String liblocid=request.getParameter("liblocid");
			String accyear=request.getParameter("accyear");
			
		
			
            if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase(""))
            {
	           location="%%";
               }
            if(accyear.equalsIgnoreCase("all") || accyear.equalsIgnoreCase("")){
            	accyear="%%";
                }
            if(liblocid.equalsIgnoreCase("all") || liblocid.equalsIgnoreCase(""))
            {
            	liblocid="%%";
            }
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			list = new LibraryDelegate().TransferSubscriberbyotherSearch(searchTextVal,location,select,liblocid,accyear);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	} 
	
	
	
	public ActionForward LibraryLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryLocation Starting");

		try {
			
			
			 String location=request.getParameter("schoolLocation");
          	
          	ArrayList <LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = new LibraryDelegate().getLibLocationsDetails(location);
			
			JSONObject obj=new JSONObject();
			obj.put("librarylocationsDetails", loclist);
			response.getWriter().print(obj);
 
            


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryLocation Ending");

		return null;
	}
	
	public ActionForward categorytypeexcelfileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryLocation Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_CATEGORYTYPE);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : LibraryLocation Ending");
		return mapping.findForward(LibraryMessageConstants.categorytypeexcelfileupload);
	}	
	
	 public ActionForward addSupplierSettings(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		  logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : addSupplierSettings Starting");
			
			
		  LibraryStockEntryVO obj=new LibraryStockEntryVO();
		  
		  obj.setSuppliedBy(request.getParameter("supplier"));
		  obj.setSupplieraddress(request.getParameter("supplieradd"));
		  obj.setEmail(request.getParameter("emailid"));
		  obj.setSupdate(request.getParameter("supdate"));
		  obj.setTelephone(request.getParameter("telephone"));
		  obj.setMobilenum(request.getParameter("supmobnum"));
		  obj.setFax(request.getParameter("fax"));
		  obj.setEntry_id(request.getParameter("hiddenid"));
		  		  
		     UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			 String userCode = userDetailVO.getUserId();
			 obj.setCreatedby(userCode);
			
			 System.out.println(obj.getEntry_id());
		
		  String result = new LibraryDelegate().addSupplierSettings(obj);
		  
		  JSONObject json = new JSONObject();
		  json.put("status",result);
		  response.getWriter().print(json);

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : addSupplierSettings Ending");
		  return null;
	  }
	 public ActionForward ListsupplierSettings(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : ListsupplierSettings Starting");

		
			try {
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUPPLIERSETTINGS);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : ListsupplierSettings Ending");

			return mapping.findForward(LibraryMessageConstants.ListSupplierSettings);
		}
	 public ActionForward getSupplierSettingList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getSupplierSettingList Starting");
			try {

				List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
						
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("suplist",suplist );
				response.getWriter().print(jsonObject);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction:getSupplierSettingList Ending");
			return null;
		}
	 
	 
	  public ActionForward editSupplierSetting(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction: editSupplierSetting Starting");
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUPPLIERSETTINGS);
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				
				String arg = "Modify Supplier ";
				request.setAttribute("msg", arg);
				 
				
				LibraryDelegate obj = new LibraryDelegate();
		
				String id = request.getParameter("supid");

				LibraryStockEntryVO edit_supSetting = new LibraryDelegate().editSupplierSetting(id);
				request.setAttribute("supid",id);
				request.setAttribute("editlist",edit_supSetting);
		
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction : editSupplierSetting Starting Ending");

			return mapping.findForward(LibraryMessageConstants.supplierSettings);

		}
	  public ActionForward deleteSupplierSetting(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : deleteSupplierSetting Starting");
			
			String status = null;
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);
				String deleteId[] = request.getParameter("deleteid").split(",");
				
				System.out.println("deleteId :"+deleteId);
				
				
				status =  new LibraryDelegate().deleteSupplierSetting(deleteId);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("supsetting", status);
				response.getWriter().print(jsonobj);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				
			}
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : deleteSupplierSetting Ending");
			
			return null;
		}
	 
	
	 
	  public ActionForward  validationsubsettings(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction :validationsubsettings Starting");
			try{
				
				 String suplier =request.getParameter("supplier");
				 String supadd =request.getParameter("supplieradd");
				 String emailid =request.getParameter("emailid");
				 String telephone =request.getParameter("telephone"); 
				 String supnum =request.getParameter("supmobnum");
				
				String result = new LibraryDelegate().validationsubsettings(suplier,supadd,emailid,telephone,supnum);
				
				JSONObject obj=new JSONObject();
				obj.put("result", result);
				response.getWriter().print(obj);

				           
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction :validationsubsettings Ending");
			
			return null;
		}

		public ActionForward publisherDetailsSearch(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : publisherDetailsSearch Starting");

			
			try {
				ArrayList<LibraryStockEntryVO> list=new ArrayList<LibraryStockEntryVO>();
				String pub=request.getParameter("publisher");

				LibraryDelegate librarydelegate=new LibraryDelegate();
				ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();

				String searchTextVal  = request.getParameter("searchid");

				if (searchTextVal == null || searchTextVal =="") {
					searchTextVal="%%";
				}
				
					List = librarydelegate.publisherDetailsSearch(searchTextVal.trim(),pub);
				
				request.setAttribute("searchnamelistValue", searchTextVal);
				
				JSONObject obj=new JSONObject();
				obj.put("publist",List);
				response.getWriter().print(obj);


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : publisherDetailsSearch Ending");

			return null;
		}
		
		public ActionForward SupplierDetailsSearch(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : SupplierDetailsSearch Starting");
			
			
			try {
				ArrayList<LibraryStockEntryVO> list=new ArrayList<LibraryStockEntryVO>();
				String sup=request.getParameter("supplier");

				LibraryDelegate librarydelegate=new LibraryDelegate();
				ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();

				String searchTextVal  = request.getParameter("searchid");
                  if(searchTextVal == null || searchTextVal ==""){
                	  searchTextVal="%%";
                  }
				
				if (searchTextVal != null || searchTextVal !="") {
					List = librarydelegate.SupplierDetailsSearch(searchTextVal.trim(),sup);
				}
				
				request.setAttribute("searchnamelistValue", searchTextVal);
				
				JSONObject obj=new JSONObject();
				obj.put("suplist",List);
				response.getWriter().print(obj);


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : SupplierDetailsSearch Ending");

			return null;
		}
		

		

	  
	  public ActionForward getStudentIssueDetailsBySubscriberNo(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Starting");
			List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
			
			
			try {
				String accyear = request.getSession(false).getAttribute("current_academicYear").toString();
				String searchTerm = request.getParameter("searchTerm");
				String userType = request.getParameter("userType");
				LibraryStockEntryVO libVo = new LibraryStockEntryVO();
				libVo.setUserType(userType);
				libVo.setAccessionNo(searchTerm);
			
				bookIssueList = LibraryDelegate.getStudentIssueDetailsBySubscriberNo(libVo);
	            System.out.println("iside get action method...........bookissuelist");
				System.out.println(bookIssueList);
				JSONObject array = new JSONObject();
				array.put("accessionList",bookIssueList);
				response.getWriter().print(array);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			logger.info(JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Ending");
			return null;
		}
	  
	  public ActionForward getTeacherIssueDetails(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Starting");
			List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
			
			
			try {
				String accyear = request.getSession(false).getAttribute("current_academicYear").toString();
				String searchTerm = request.getParameter("searchTerm");
				String userType = request.getParameter("userType");
				System.out.println("*****************************************************************************//////////");
				LibraryStockEntryVO libVo = new LibraryStockEntryVO();
				libVo.setUserType(userType);
				libVo.setAccessionNo(searchTerm);
			
				bookIssueList = LibraryDelegate.getTeacherIssueDetails(libVo);
	            System.out.println("iside get action method...........bookissuelist");
				System.out.println(bookIssueList);
				JSONObject array = new JSONObject();
				array.put("accessionList",bookIssueList);
				response.getWriter().print(array);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			logger.info(JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Ending");
			return null;
		}
	  
	  public ActionForward insertBookReservationDetails(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in insertBookIssueDetails : insertBookIssueDetails Starting");
			try {
				String accyear = (String) request.getSession(false).getAttribute(
    					"current_academicYear");
    			
				String result = null;
				LibraryStockEntryVO insert_issue =new LibraryStockEntryVO();

				String accessionNo=request.getParameter("accession_no");
				String itemtype=request.getParameter("itemtype");
				String bookTitle=request.getParameter("bookTitle");
				String author=request.getParameter("author");
				String  category = request.getParameter("category");
				String  ddc = request.getParameter("ddc");
				String  currentStatus = request.getParameter("currentstatus");
				String  issuedId = request.getParameter("subid").split("-")[0];
				String  issuedType = request.getParameter("usertype");
				String  imagePreview = request.getParameter("imagePreview");
				String  shelfNo = request.getParameter("shelfNo");
				String  location = request.getParameter("locationname");
				String  fromDate = request.getParameter("fromdate");
				String  status = request.getParameter("status");
				String todate=request.getParameter("todate");
				String  subscriberId = request.getParameter("userId");
				String  hiddenreserveid=request.getParameter("hiddenreserveid");
			
				System.out.println("accessionNo>>>"+accessionNo);
				System.out.println("location>>>"+location);
				
				insert_issue.setAccessionNo(accessionNo);
				insert_issue.setItemType(itemtype);
				insert_issue.setBookTitle(bookTitle);
				insert_issue.setAuthor(author);
				insert_issue.setCategory(category);
				insert_issue.setDdc(ddc);
				insert_issue.setCurrentStatus(currentStatus);
				insert_issue.setIssued_to(issuedId);
				insert_issue.setIssued_user_id(issuedType);
				insert_issue.setImageurl(imagePreview);
				insert_issue.setShelfNo(shelfNo);
				insert_issue.setLocation(location);
				insert_issue.setFromDate(fromDate);
				insert_issue.setStatus(status);
				insert_issue.setToDate(todate);
				insert_issue.setSubscriberId(subscriberId);
				insert_issue.setHiddenReserveId(hiddenreserveid);
				insert_issue.setAccyear(accyear);
				
				System.out.println("hiddenreserveid"+hiddenreserveid);
				
				if(!hiddenreserveid.equalsIgnoreCase("")&& hiddenreserveid!=null )
				{
					System.out.println("inside update condition>>");
					result = new LibraryDelegate().updateBookReservationDetails(insert_issue);
				}
				else
				{
					System.out.println("inside insert condition>>>");
					result= new LibraryDelegate().insertBookReservationDetails(insert_issue);
				}

				JSONObject obj1= new JSONObject();
				obj1.put("status",result);
				response.getWriter().print(obj1);

				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in insertBookIssueDetails : insertBookIssueDetails Ending");

			return null;
		}
	  public ActionForward BookReservationDetailslist(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : BookReservationDetailslist Starting");

			try {
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_RESERVATIONS);
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction :BookReservationDetailslist Ending");

			return mapping.findForward(LibraryMessageConstants.BookReservationDetailslist);
		}

	  public ActionForward getReservationListDetails(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getStockEntryBookList Starting");
			try {

				List<LibraryStockEntryDetailsForm > reservationList = new LibraryDelegate().getReservationListDetails();
						
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("reservationList",reservationList );
				response.getWriter().print(jsonObject);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction:getStockEntryBookList Ending");
			return null;
		}
	  
	  public ActionForward othersSearchbyId(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");
			List<TeacherVo> searchTeacherList = new ArrayList<TeacherVo>();
			try {
				TeacherVo registrationVo = new TeacherVo();
				String searchterm = request.getParameter("searchTerm");
				String locid = request.getParameter("locid");
				registrationVo.setSearchTerm(searchterm);
				registrationVo.setLocid(locid);

				searchTeacherList = new LibraryDelegate().othersSearchbyId(registrationVo);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchTeacherList);

				response.getWriter().print(jsonObject);
			}catch(Exception e){
				e.printStackTrace();
			}


			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

			return null;
		}
		 
	  public ActionForward getOtherIssueDetails(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Starting");
			List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
			
			
			try {
				String accyear = request.getSession(false).getAttribute("current_academicYear").toString();
				String searchTerm = request.getParameter("searchTerm");
				String userType = request.getParameter("userType");
				LibraryStockEntryVO libVo = new LibraryStockEntryVO();
				libVo.setUserType(userType);
				libVo.setAccessionNo(searchTerm);
			
				bookIssueList = LibraryDelegate.getOtherIssueDetails(libVo);
	            System.out.println("iside get action method...........bookissuelist");
				System.out.println(bookIssueList);
				JSONObject array = new JSONObject();
				array.put("accessionList",bookIssueList);
				response.getWriter().print(array);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			logger.info(JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info("Control in getStudentIssueDetailsBySubscriberNo : getStudentIssueDetailsBySubscriberNo Ending");
			return null;
		}

	  public ActionForward addGeneralSettings(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		  logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : addGeneralSettings Starting");
			try{
		  
		 
		LibraryStockEntryVO obj=new LibraryStockEntryVO();
		  
		  obj.setItemType(request.getParameter("itemdescription"));
		  obj.setNoofdaytaken(request.getParameter("noofday"));
		  obj.setFineamount(request.getParameter("fineamount"));
		  obj.setTodaydate(request.getParameter("todaydate"));
		  obj.setAmountperday(request.getParameter("amountperday"));
		  obj.setFineincperday(request.getParameter("fineincrement"));
		  obj.setDuedate(request.getParameter("duedate"));
		  obj.setEntry_id(request.getParameter("hiddenid"));
		  		  
		     UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			 String userCode = userDetailVO.getUserId();
			 obj.setCreatedby(userCode);
			
			 
		
		  String result = new LibraryDelegate().addGeneralSettings(obj);
		  
		  JSONObject json = new JSONObject();
		  json.put("status",result);
		  response.getWriter().print(json);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : addGeneralSettings Ending");
		  return null;
	  }
	  
	public ActionForward ListgeneralSettings(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : LibraryHome Starting");

			try {
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_GENERALSETTINGS);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : LibraryHome Ending");

			return mapping.findForward(LibraryMessageConstants.ListgeneralSettings);
		}
	public ActionForward getGenarelSettingList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getGenarelSettingList Starting");
		try {

			List<LibraryStockEntryVO> genlist = new LibraryDelegate().getGenarelSettingList();
					
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("genlist",genlist );
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction:getGenarelSettingList Ending");
		return null;
	}
	  
	  public ActionForward editGenarelSetting(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction: editGenarelSetting Starting");
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_GENERALSETTINGS);
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				
				String arg = "Modify Genarel Settings";
				request.setAttribute("msg", arg);
				 
				
				LibraryDelegate obj = new LibraryDelegate();
		
				String id = request.getParameter("genid");

				LibraryStockEntryVO edit_genSetting = new LibraryDelegate().editGenarelSetting(id);
				request.setAttribute("genid",id);
				request.setAttribute("editlist",edit_genSetting);
		
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction : editGenarelSetting Starting Ending");

			return mapping.findForward(LibraryMessageConstants.generalSettings);

		}
	  
	  public ActionForward deleteGenarelSetting(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : deleteGenarelSetting Starting");
			
			String status = null;
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_PUBLISHERSETTINGS);
				String deleteId[] = request.getParameter("deleteid").split(",");
				
				System.out.println("deleteId :"+deleteId);
				
				
				status =  new LibraryDelegate().deleteGenarelSetting(deleteId);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("gensetting", status);
				response.getWriter().print(jsonobj);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				
			}
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : deleteGenarelSetting Ending");
			
			return null;
		}
	 
	  
	  public ActionForward GenarelDetailsSearch(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : GenarelDetailsSearch Starting");
			
			
			try {
				ArrayList<LibraryStockEntryVO> list=new ArrayList<LibraryStockEntryVO>();
				

				LibraryDelegate librarydelegate=new LibraryDelegate();
				ArrayList<LibraryStockEntryVO> List=new ArrayList<LibraryStockEntryVO>();

				String searchTextVal  = request.getParameter("searchid");
              if(searchTextVal == null || searchTextVal ==""){
            	  searchTextVal="%%";
              }
				
				if (searchTextVal != null || searchTextVal !="") {
					List = librarydelegate.GenarelDetailsSearch(searchTextVal.trim());
				}
				
				request.setAttribute("searchnamelistValue", searchTextVal);
				
				JSONObject obj=new JSONObject();
				obj.put("genlist",List);
				response.getWriter().print(obj);


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction : GenarelDetailsSearch Ending");

			return null;
		}
	  
	 
	  public ActionForward  JournalSubscription(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction:  JournalSubscription Starting");
			
			try {
				
			
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_JOURNAL_SUBSCRIPTION);
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				
				String arg = "Add Journal Subscription";
				request.setAttribute("msg", arg);
				
				ArrayList<LibraryVO> locationList = new LibraryDelegate().getcodeList();
				request.setAttribute("locationList", locationList);
				List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
    			request.setAttribute("publicationlist", publist);
    			
    			List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
				request.setAttribute("supplist", suplist);
				
				LibraryDelegate obj = new LibraryDelegate();
				List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
				list = obj.getCategoryDetails();
				request.setAttribute("categoryDetails", list);
				
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
				request.setAttribute("AccYearList", accYearList);

			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction :  JournalSubscription Starting Ending");

			return mapping.findForward(LibraryMessageConstants.JournalSubscription);

		}
	  
	  public ActionForward JournalEntry(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction:    JournalEntry Starting");
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant. MODULE_LIBRARY_JOURNAL_ENTRY);
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				
			
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction :    JournalEntry Starting Ending");

			return mapping.findForward(LibraryMessageConstants.JournalEntry);

		}
	  public ActionForward JournalDailyEntry(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction:    JournalDailyEntry Starting");
			
			try {
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
						LeftMenusHighlightMessageConstant. MODULE_LIBRARY_JOURNAL_DAILY_ENTRY);
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
				
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in  LibraryAction :    JournalDailyEntry Starting Ending");

			return mapping.findForward(LibraryMessageConstants.JournalDailyEntry);

		}
	  
	    public ActionForward getcodeName(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getAccessionNo : getAccessionNo Starting");


			List<LibraryStockEntryVO> searchAccessionNo = new ArrayList<LibraryStockEntryVO>();
			try {
	            LibraryStockEntryVO registrationVo = new LibraryStockEntryVO();
				
				String categoryname = request.getParameter("categoryname");
				registrationVo.setCodeName(categoryname);
				searchAccessionNo = new LibraryDelegate().getcodeName(registrationVo);
				
				System.out.println("size in action cls;...........");
				System.out.println(searchAccessionNo.size());
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchAccessionNo);

				response.getWriter().print(jsonObject);
			}catch(Exception e){
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in getAccessionNo : getAccessionNo ending");
			return null;
		}
	    
		public ActionForward getCodeByCodeName(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info("Control in getCodeByCodeName : getCodeByCodeName Starting");
			List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
			
			
			try {
				String searchTerm = request.getParameter("searchTerm");
				LibraryStockEntryVO libVo = new LibraryStockEntryVO();
				libVo.setCodeName(searchTerm);
				
				bookIssueList = LibraryDelegate.getCodeByCodeName(libVo);
				JSONObject array = new JSONObject();
				array.put("accessionList",bookIssueList);
				response.getWriter().print(array);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			logger.info(JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info("Control in getCodeByCodeName : getCodeByCodeName Ending");
			return null;
		}
	  

	    public ActionForward getStudentOverDueStatement(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction :getOverDueStatement Starting");

			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String locid = request.getParameter("locationid");
			String accyear = request.getParameter("Acyearid");
			String select=request.getParameter("select");
			
			try {
				if(locid.equalsIgnoreCase("all")||locid.equalsIgnoreCase("")){
					locid="%%";
				}
				if(accyear.equalsIgnoreCase("all")||accyear.equalsIgnoreCase("")){
					accyear="%%";
				}
				LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
				
				pojo.setLocationName(locid);
				pojo.setAccId(accyear);
				pojo.setSuscriberType(select);
				
				list= new  LibraryDelegate().getOverDueStatement(pojo);

				JSONObject obj1= new JSONObject();
				obj1.put("SearchList",list);
				response.getWriter().print(obj1);

			}catch(Exception e){
				e.printStackTrace();
			}


			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getOverDueStatement ending");

			return null;
		}
	  
	  
		 public ActionForward editReservationBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
							throws Exception {

				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in editReservationBook : editReservationBook Starting");
				try {
					request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
					request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
					request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
							LeftMenusHighlightMessageConstant.MODULE_LIBRARY_LIBRARYLOCATION);
					String title = "Update Book Reservation Details";
					request.setAttribute("reservation", title);

					String id = request.getParameter("reserveid");
					

					LibraryStockEntryVO edit_reserve = new LibraryDelegate().editReservationBook(id);
				
					
					
					request.setAttribute("editlist",edit_reserve);
					request.setAttribute("reserveid",id);
	             }
				catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}

				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in editReservationBook : editReservationBook Ending");
				return mapping.findForward(LibraryMessageConstants.editreservation);

			}

		 
		 public ActionForward getBookReservationDetailsByAccNo(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info("Control in getBookReservationDetailsByAccNo : getBookReservationDetailsByAccNo Starting");
				List<LibraryStockEntryVO> bookReservationList = new ArrayList<LibraryStockEntryVO>();


				try {
					String searchTerm = request.getParameter("searchTerm");
					LibraryStockEntryVO libVo = new LibraryStockEntryVO();
					libVo.setAccessionNo(searchTerm);

					bookReservationList = LibraryDelegate.getBookReservationDetailsByAccNo(libVo);
					System.out.println("inside get action method...........bookReservationList");
					System.out.println("list size>>>"+bookReservationList.size());
					JSONObject array = new JSONObject();
					array.put("accessionList",bookReservationList);



					response.getWriter().print(array);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}

				logger.info(JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info("Control in getBookReservationDetailsByAccNo : getBookReservationDetailsByAccNo Ending");
				return null;
			}

		 public ActionForward deleteReservedBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception {

				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : deleteReservedBook Starting");

				try {
					String librarylocid[] = request.getParameterValues("librarylocid[]");
				
					String	result= new LibraryDelegate().deleteReservedBook(librarylocid);
					System.out.println(".........");
					JSONObject obj1= new JSONObject();
					obj1.put("status",result);
					response.getWriter().print(obj1);

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}

				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : deleteReservedBook Ending");

				return null;
			}

	  
		 public ActionForward validateReservedBook(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception {

				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in Library Location DetailsAction : validateReservedBook  Starting");



				String accession_no=request.getParameter("accession_no");
				String subscriberid=request.getParameter("subscriberid");
				String Fromdate=request.getParameter("Fromdate");
				String ToDate=request.getParameter("ToDate");
				
				
				LibraryStockEntryVO reserve =new LibraryStockEntryVO();


				reserve.setAccessionNo(accession_no);
				reserve.setSubscriberId(subscriberid);
				reserve.setFromDate(Fromdate);
				reserve.setToDate(ToDate);

				boolean LibLocationName_available= new LibraryDelegate().validateReservedBook(reserve);

				JSONObject jsonobject= new JSONObject();
				if(LibLocationName_available){
					jsonobject.put("status", "true");
				}else{
					jsonobject.put("status", "false");
				}
				response.getWriter().print(jsonobject);


				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in StreamDetailsAction : validateReservedBook   Ending");

				return null;


			}
			public ActionForward subscriberdetailexcelfileupload(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : LibraryLocation Starting");
				try {
					request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
					request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
					request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
							LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SUBSCRIBERDETAILS);
					
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : LibraryLocation Ending");
				return mapping.findForward(LibraryMessageConstants.subscriberdetailexcelfileupload);
			}
              public ActionForward stockEntryExcelUpload(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : LibraryLocation Starting");
				try {
					request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
					request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
					request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
							LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);
					
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in LibraryAction : LibraryLocation Ending");
				return mapping.findForward(LibraryMessageConstants.STOCK_ENTRY_EXCEL);
			}

public ActionForward LibraryMostWantedDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_MOSTWANTED);
		
		 String accademinyear=(String)request.getSession(false).getAttribute("current_academicYear");
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
            request.setAttribute("accademic_year", accademinyear);
		   
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return mapping.findForward(LibraryMessageConstants.LibraryMostWantedDetails);
}
public ActionForward getMostWantedStudentListDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");

		String searchTerm = request.getParameter("searchname".trim());

		String select = request.getParameter("select");

		if(location.equalsIgnoreCase("all")||location.equalsIgnoreCase("")){
			location = "%%";
			classname = "%%";
			sectionid ="%%";
		}
		if(academic_year.equalsIgnoreCase("all")||academic_year.equalsIgnoreCase("")){
			academic_year = "%%";
		}
		if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
			classname = "%%";
		}
		  if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("")){
				sectionid ="%%";
			}

		list = new LibraryDelegate().getMostWantedStudentListDetails(academic_year,location,select,classname,sectionid);

		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);


		request.setAttribute("studentList", list);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward SearchMostWantedStudentDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String select = request.getParameter("select");
		String startwith = request.getParameter("startwith");

		LibraryDelegate librarydelegate=new LibraryDelegate();
		List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

		String searchTextVal  = request.getParameter("searchname").trim();

		if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
			location = "%%";
		}
		if(academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")){
			academic_year = "%%";
		}
		if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
			classname = "%%";
		}
		if(sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")){
			sectionid = "%%";
		}
		
		
			List = librarydelegate.SearchMostWantedStudentDetailsByAnyWhere(searchTextVal,location,academic_year,select,startwith,classname,sectionid);
		
		request.setAttribute("searchnamelistValue", searchTextVal);

		JSONObject obj=new JSONObject();
		obj.put("studentData",List);
		response.getWriter().print(obj);


	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward getMostWantedStudentListByClassName(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String select = request.getParameter("select");

		String searchTerm = request.getParameter("searchname".trim());

		if(academic_year.equalsIgnoreCase("all")){
			academic_year = "%%";
		}
		
		if(classname.equalsIgnoreCase("all")){
			classname = "%%";
		}

		list = new LibraryDelegate().getMostWantedStudentListByClassName(academic_year,location,classname,sectionid,select);



		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);


		request.setAttribute("studentList", list);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward getMostWantedStudentListBySection(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String select = request.getParameter("select");

		String searchTerm = request.getParameter("searchname".trim());

		if(academic_year.equalsIgnoreCase("all")){
			academic_year ="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname ="%%";
		}
		
		if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("")){
			sectionid ="%%";
		}

		list = new LibraryDelegate().getMostWantedStudentListBySection(academic_year,location,classname,sectionid,select);
		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward getMostWantedStaffListDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String department = request.getParameter("department");
		String designation = request.getParameter("designation");
		String select = request.getParameter("select");

		String searchTerm = request.getParameter("searchname".trim());

		if(location.equalsIgnoreCase("all")){
			location = "%%";
		}
		if(academic_year.equalsIgnoreCase("all")){
			academic_year = "%%";
		}
		if(department.equalsIgnoreCase("all")){
			department = "%%";
		}
		if(designation.equalsIgnoreCase("all")){
			designation = "%%";
		}
		list = new LibraryDelegate().getMostWantedStaffListDetails(academic_year,location,select,department,designation);

		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);


		request.setAttribute("studentList",list);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}

public ActionForward SearchMostWantedStaffDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String accyear = request.getParameter("accyear");
		String location = request.getParameter("location");
		String searchTextVal = request.getParameter("searchname").trim();
		String select = request.getParameter("select");
		String startwith = request.getParameter("startwith");
		
        if(location.equalsIgnoreCase("all")){
        	location="%%";
        }
        if(accyear.equalsIgnoreCase("")){
        	accyear="%%";
        }

		list = new LibraryDelegate().SearchMostWantedStaffDetailsByAnyWhere(searchTextVal,location,select,startwith);
		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
		request.setAttribute("studentList", list);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");
	return null;
}
public ActionForward getMostWantedStaffdetailsByDepartment(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");


	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String accyear_ID = request.getParameter("accyear");
		String Loc_ID = request.getParameter("location");
		String department=request.getParameter("department");
		String designation=request.getParameter("designation");
		String select=request.getParameter("select");
		
		if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase(""))
		{
			department="%%";
		}
		if(designation.equalsIgnoreCase("all") || designation.equalsIgnoreCase(""))
		{
			designation="%%";
		}

		String searchTerm = request.getParameter("searchname");

		list = new LibraryDelegate().getMostWantedStaffdetailsByDepartment(accyear_ID,Loc_ID,department,designation,select);

		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);


		request.setAttribute("studentList", list);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward getMostWantedStaffdetailsByDesignation(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String accyear_ID = request.getParameter("accyear");
		String Loc_ID = request.getParameter("location");
		String department=request.getParameter("department");
		String designation=request.getParameter("designation");
		String select=request.getParameter("select");

		if(department.equalsIgnoreCase("all"))
		{
			department="%%";
		}
		if(designation.equalsIgnoreCase("all"))
		{
			designation="%%";
		}
		String searchTerm = request.getParameter("searchname");

		list = new LibraryDelegate().getMostWantedStaffdetailsByDesignation(accyear_ID,Loc_ID,department,designation,select);

		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
		request.setAttribute("studentList", list);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");
	return null;
}
public ActionForward getMostWantedOthersListDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("location");
		String select = request.getParameter("select");

		if(location.equalsIgnoreCase("all")){
			location = "%%";
		}
		if(academic_year.equalsIgnoreCase("all")){
			academic_year = "%%";
		}
		list = new LibraryDelegate().getMostWantedOthersListDetails(location,select,academic_year);
		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
		request.setAttribute("studentList", list);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");

	return null;
}
public ActionForward SearchMostWantedOthersDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Starting");
	try {
		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String location = request.getParameter("location");
		String searchTextVal = request.getParameter("searchname").trim();
		String select = request.getParameter("select");
		String startwith = request.getParameter("startwith");
        if(location.equalsIgnoreCase("all")){
           location="%%";
           }
		/*request.setAttribute("searchTextVal", searchTextVal);*/
		list = new LibraryDelegate().SearchMostWantedOthersDetailsByAnyWhere(searchTextVal,location,select,startwith);

		JSONObject obj=new JSONObject();
		obj.put("studentData",list);
		response.getWriter().print(obj);
		request.setAttribute("studentList", list);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LibraryAction : StockEntryDetails Ending");
	return null;
}

              public ActionForward savejournalsubscriptiondetail(ActionMapping mapping,
          			ActionForm form, HttpServletRequest request,
          			HttpServletResponse response) throws Exception {
          	  logger.setLevel(Level.DEBUG);
          		JLogger.log(0, JDate.getTimeString(new Date())
          				+ MessageConstants.START_POINT);
          		logger.info(JDate.getTimeString(new Date())
          				+ " Control in LibraryAction : savejournalsubscriptiondetail Starting");
          		try{
          			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
     
          	     LibraryJournalSubscriptionVo obj=new LibraryJournalSubscriptionVo();
          			obj.setName(request.getParameter("Name"));
          			obj.setCode(request.getParameter("code"));
          			obj.setJournaltype(request.getParameter("journaltype"));
          			obj.setPublisher(request.getParameter("publisher"));
          			obj.setSupplier(request.getParameter("Supplier"));
          			obj.setRatepercopy(request.getParameter("ratepercopy"));
          			obj.setNumberofcopy(request.getParameter("noofcopy"));
          			obj.setDepartment(request.getParameter("department"));
          			obj.setOtherdetails(request.getParameter("otherdetails"));
          			obj.setAccessionno(request.getParameter("select"));
          			obj.setDateon(request.getParameter("dateon"));
          			obj.setSubscriptionperiode(request.getParameter("subscription"));
          			obj.setTodate(request.getParameter("todate"));
          			obj.setDuedate(request.getParameter("duedate"));
          			obj.setTotalamount(request.getParameter("totalrate"));
          			obj.setAnnualratepercopy(request.getParameter("annualratepercopy"));
          			obj.setIsaccessionno(request.getParameter("accessionno"));
          			obj.setAccyear(accyear);
          			obj.setEntryId(request.getParameter("hiddenid"));
          			
          		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
          		 String userCode = userDetailVO.getUserId();
          		 obj.setCreatedby(userCode);
          		
          	
          	  String result = new LibraryDelegate().savejournalsubscriptiondetail(obj);
          	  
          	  JSONObject json = new JSONObject();
          	  json.put("status",result);
          	  response.getWriter().print(json);
          		}
          		catch(Exception e){
          			e.printStackTrace();
          		}
          		JLogger.log(0, JDate.getTimeString(new Date())
          				+ MessageConstants.END_POINT);
          		logger.info(JDate.getTimeString(new Date())
          				+ " Control in LibraryAction : savejournalsubscriptiondetail Ending");
          	  return null;
            }
              
          	public ActionForward LibraryjournalSubscriptionList(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : LibraryHome Starting");

        		try {
        			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
    						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_JOURNAL_SUBSCRIPTION);
    				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    				
    				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
    				request.setAttribute("AccYearList", accYearList);
        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}

        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : LibraryHome Ending");

        		return mapping.findForward(LibraryMessageConstants.JournalSubscriptionList);
        	}
              
              
   public ActionForward GetBookSearch(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) 
		   throws Exception {
     			logger.setLevel(Level.DEBUG);
     			JLogger.log(0, JDate.getTimeString(new Date())
     					+ MessageConstants.START_POINT);
     			logger.info(JDate.getTimeString(new Date())
     					+ " Control in LibraryAction : ListsupplierSettings Starting");
     			try {
     				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
     				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
     				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
     						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCH);

     			} catch (Exception e) {
     				logger.error(e.getMessage(), e);
     				e.printStackTrace();
     			}

     			JLogger.log(0, JDate.getTimeString(new Date())
     					+ MessageConstants.END_POINT);
     			logger.info(JDate.getTimeString(new Date())
     					+ " Control in LibraryAction : ListsupplierSettings Ending");

     			return mapping.findForward(LibraryMessageConstants.BookSearch);
     		}     
           
   public ActionForward getAllBookDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}
	public ActionForward SearchBookSearchByaccNoandTitleAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");


		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String orderby = request.getParameter("orderby");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(orderby);
			
		    List = librarydelegate.SearchBookSearchByaccNoandTitleAnyWhere(pojo);
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
          	   
            public ActionForward getJournalSubscriptionList(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction: getJournalSubscriptionList Starting");
        		try {

        		List<LibraryJournalSubscriptionVo> journal = new LibraryDelegate().getJournalSubscriptionList();
        					
        			JSONObject jsonObject = new JSONObject();
        			jsonObject.accumulate("journal",journal );
        			response.getWriter().print(jsonObject);
        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction:getJournalSubscriptionList Ending");
        		return null;
        	}
            
            
            
            public ActionForward editeLibraryJournalSubscription(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in  LibraryAction: editeLibraryJournalSubscription Starting");
        		
        		try {
        			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
    						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_JOURNAL_SUBSCRIPTION);
    				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    				ArrayList<LibraryVO> locationList = new LibraryDelegate().getcodeList();
    				String arg = "Modify Journal Subscription";
    				request.setAttribute("msg", arg);
    				request.setAttribute("locationList", locationList);
    				List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
        			request.setAttribute("publicationlist", publist);
        			
        			List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
    				request.setAttribute("supplist", suplist);
    				
    				LibraryDelegate obj = new LibraryDelegate();
    				List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
    				list = obj.getCategoryDetails();
    				request.setAttribute("categoryDetails", list);
        	
        	
        			String id = request.getParameter("journalId");

        			LibraryJournalSubscriptionVo Journal_list = new LibraryDelegate().editeLibraryJournalSubscription(id);
        			request.setAttribute("journalId",id);
        			request.setAttribute("journaleditlist",Journal_list);
        	
        		}

        		catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}

        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in  LibraryAction : editeLibraryJournalSubscription Starting Ending");

        		return mapping.findForward(LibraryMessageConstants.JournalSubscription);

        	}
            
            public ActionForward deleteJournalSbscription(ActionMapping mapping,
        			ActionForm form, HttpServletRequest request,
        			HttpServletResponse response) throws Exception {
        		
        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : deleteJournalSbscription Starting");
        		
        		String status = null;
        		
        		try {
        			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
    						LeftMenusHighlightMessageConstant.MODULE_LIBRARY_JOURNAL_SUBSCRIPTION);
    				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
        			String deleteId[] = request.getParameter("deleteid").split(",");
        			
        			System.out.println("deleteId :"+deleteId);
        			
        			
        			status =  new LibraryDelegate().deleteJournalSbscription(deleteId);
        			
        			JSONObject jsonobj = new JSONObject();
        			jsonobj.put("journalsub", status);
        			response.getWriter().print(jsonobj);
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        			logger.error(e);
        			
        		}
        		
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : deleteJournalSbscription Ending");
        		
        		return null;
        	}
            public ActionForward getAllBookPublisherDetails(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {
        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : StockEntryDetails Starting");
        		try {
        			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
        			
        			String select = request.getParameter("select");
        			String orderby = request.getParameter("orderby");
        			/*request.setAttribute("searchTextVal", searchTextVal);*/
        			
        			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
        			pojo.setSelect(select);
        			pojo.setOrderBy(orderby);
        			
        			list = new LibraryDelegate().getAllBookPublisherDetails(pojo);

        			JSONObject obj=new JSONObject();
        			obj.put("studentData",list);
        			response.getWriter().print(obj);
        			request.setAttribute("studentList", list);
        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : StockEntryDetails Ending");
        		return null;
        	}      
            
            
            public ActionForward getJournalSubscriptioncodelist(ActionMapping mapping, ActionForm form,
    				HttpServletRequest request, HttpServletResponse response)
    						throws Exception {

    			logger.setLevel(Level.DEBUG);
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.START_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in  LibraryAction:    JournalEntry Starting");
    			
    			try {
    				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
    						LeftMenusHighlightMessageConstant. MODULE_LIBRARY_JOURNAL_ENTRY);
    				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    				
    				
    				List<LibraryJournalSubscriptionVo> journal = new LibraryDelegate().getJournalSubscriptioncodelist();
    				//request.setAttribute("codelist", journal);
    				JSONObject jsonObject = new JSONObject(journal);
    				jsonObject.accumulate("response", journal);
    				response.getWriter().print(jsonObject);
    			}

    			catch (Exception e) {
    				logger.error(e.getMessage(), e);
    				e.printStackTrace();
    			}

    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.END_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in  LibraryAction :    JournalEntry Starting Ending");

    			return null;

    		} 
            
            
            public ActionForward getnamelist(ActionMapping mapping, ActionForm form,
    				HttpServletRequest request, HttpServletResponse response)
    						throws Exception {

    			logger.setLevel(Level.DEBUG);
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.START_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in  LibraryAction:    getnamelist Starting");
    			
    			try {
    				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
    						LeftMenusHighlightMessageConstant. MODULE_LIBRARY_JOURNAL_ENTRY);
    				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    				
    				
    				List<LibraryJournalSubscriptionVo> journal = new LibraryDelegate().getnamelist();
    				//request.setAttribute("codelist", journal);
    				JSONObject jsonObject = new JSONObject(journal);
    				jsonObject.accumulate("response", journal);
    				response.getWriter().print(jsonObject);
    			}

    			catch (Exception e) {
    				logger.error(e.getMessage(), e);
    				e.printStackTrace();
    			}

    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.END_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in  LibraryAction :    getnamelist Starting Ending");

    			return null;

    		} 
            
    public ActionForward SearchBookSearchByPublisherAnyWhere(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : StockEntryDetails Starting");

        		try {
        			String searchTextVal  = request.getParameter("searchname").trim();
        			String select = request.getParameter("select");
        			String searching = request.getParameter("searching"); 
        			String orderby = request.getParameter("orderby");
        			
        			LibraryDelegate librarydelegate=new LibraryDelegate();
        			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

        			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
        			
        			pojo.setSelect(select);
        			pojo.setSearchTextVal(searchTextVal);
        			pojo.setSearch(searching);
        			pojo.setOrderBy(orderby);
        			
        			List = librarydelegate.SearchBookSearchByPublisherAnyWhere(pojo);
        			
        			request.setAttribute("searchnamelistValue", searchTextVal);

        			JSONObject obj=new JSONObject();
        			obj.put("studentData",List);
        			response.getWriter().print(obj);


        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}

        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in LibraryAction : StockEntryDetails Ending");


        		return null;
        	}      
    public ActionForward getAllBookItemTypeDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookItemTypeDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}   
    
    public ActionForward SearchBookSearchByItemTypeAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String orderby = request.getParameter("orderby");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(orderby);
			
			if (searchTextVal != null || !searchTextVal.equalsIgnoreCase("")) {
				List = librarydelegate.SearchBookSearchByItemTypeAnyWhere(pojo);
			}
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
    public ActionForward getAllBookDDCDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookDDCDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}

            public ActionForward getstockEntryList(ActionMapping mapping, ActionForm form,
    				HttpServletRequest request, HttpServletResponse response)
    						throws Exception {

    			logger.setLevel(Level.DEBUG);
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.START_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in LibraryAction: getstockEntryList Starting");
    			try {
                        
    				String locId=request.getParameter("location");
    			 String itemId=request.getParameter("itemType");
   			      String regdateId=request.getParameter("regDate");
   			      String booktitle=request.getParameter("bookTitle");
   			      String authorId=request.getParameter("author");
   			      String pubId=request.getParameter("publisher");
   			   
   			      
   			   if(itemId.equalsIgnoreCase("all")){
			    	  itemId="%%";
			      }
			      
			      if(booktitle.equalsIgnoreCase("all")){
			    	  booktitle="%%";
			      }
			      
			      if(authorId.equalsIgnoreCase("all")){
			    	  authorId="%%";
			      }
			      if(pubId.equalsIgnoreCase("all")){
			    	  pubId="%%";
			      }
    				List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getstockEntryList(locId,itemId,regdateId,booktitle,authorId,pubId);
    			
    				JSONObject jsonObject = new JSONObject();
    				jsonObject.accumulate("stocklist",stocklist );
    				response.getWriter().print(jsonObject);
    			} catch (Exception e) {
    				logger.error(e.getMessage(), e);
    				e.printStackTrace();
    			}
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.END_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in LibraryAction:getstockEntryList Ending");
    			return null;
    		}
    	 
            public ActionForward getlocationlist(ActionMapping mapping, ActionForm form,
    				HttpServletRequest request, HttpServletResponse response)
    						throws Exception {

    			logger.setLevel(Level.DEBUG);
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.START_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in LibraryAction: getlocationlist Starting");
    			try {
                      String locId=request.getParameter("location");
    			      String itemId=request.getParameter("itemType");
    			      String regdateId=request.getParameter("regDate");
    			      String booktitle=request.getParameter("bookTitle");
    			      String authorId=request.getParameter("author");
    			      String pubId=request.getParameter("publisher");
    			      
    			      
    			     
    			      if(itemId.equalsIgnoreCase("all")){
    			    	  itemId="%%";
    			      }
    			      
    			      if(booktitle.equalsIgnoreCase("all")){
    			    	  booktitle="%%";
    			      }
    			      
    			      if(authorId.equalsIgnoreCase("all")){
    			    	  authorId="%%";
    			      }
    			      if(pubId.equalsIgnoreCase("all")){
    			    	  pubId="%%";
    			      }
    			      
    			      
    				List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
    			
    				JSONObject jsonObject = new JSONObject();
    				jsonObject.accumulate("stocklist",stocklist );
    				response.getWriter().print(jsonObject);
    			} catch (Exception e) {
    				logger.error(e.getMessage(), e);
    				e.printStackTrace();
    			}
    			JLogger.log(0, JDate.getTimeString(new Date())
    					+ MessageConstants.END_POINT);
    			logger.info(JDate.getTimeString(new Date())
    					+ " Control in LibraryAction:getlocationlist Ending");
    			return null;
    		}
    	 
            
            public ActionForward StockEntryReportPdf(ActionMapping mapping,
        			ActionForm form, HttpServletRequest request,
        			HttpServletResponse response) throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in ReportsAction : FeeCollectionPdfReport Starting");
        		try {
        			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
        		  String userName = userDetailVO.getUserName();
        		
        		  String locId=request.getParameter("LocId");
  			      String itemId=request.getParameter("itemType");
  			      String regdateId=request.getParameter("regDate");
  			      String booktitle=request.getParameter("bookTitle");
  			      String authorId=request.getParameter("author");
  			      String pubId=request.getParameter("publisher");
  			      String loctionname=request.getParameter("locationname");
  			   
  			       String itemtype=request.getParameter("itemType");
  			     String bookId=request.getParameter("bookTitle");
 			      String author=request.getParameter("author");
 			      String pub=request.getParameter("publishernm");
  			      if(itemId.equalsIgnoreCase("all")){
  			    	  itemId="%%";
  			    	itemtype = "ALL";
  			      }
  			      
  			      if(booktitle.equalsIgnoreCase("all")){
  			    	   booktitle="%%";
  			    	 bookId="ALL";
  			      }
  			      
  			      if(authorId.equalsIgnoreCase("all")){
  			    	  authorId="%%";
  			    	author="ALL";
  			      }
  			      if(pubId.equalsIgnoreCase("all")){
  			    	  pubId="%%";
  			    	pub="ALL";
  			      }
  			      

  				List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
  			System.out.println("//////////////////"+stocklist.size());
        			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
        					stocklist);		
        			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

        			String schName=request.getParameter("location");
        			String schAddLine1=res.getString("AddressLine1");

        			Map mapdata=new HashMap();

        			mapdata.put("image",PropfilePath);
        			mapdata.put("schName", SchoolName);
        			mapdata.put("schadd", schAddLine1);
        			mapdata.put("locId", loctionname);
        			mapdata.put("itemId",itemtype);
        			mapdata.put("booktitle", bookId);
        			mapdata.put("authorId", author);
        			mapdata.put("pubId", pub);
        			
        	
        			String sourceFileName=request.getRealPath("Reports/StockEntryReportPdf.jrxml");

        			JasperDesign design = JRXmlLoader.load(sourceFileName);
        			JasperReport jasperreport = JasperCompileManager.compileReport(design);

        			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

        			response.setContentType("application/pdf");
        			response.setContentLength(bytes.length);
        			response.setHeader("Content-Disposition", "outline; filename=\""
        					+ "StockEntryReport" + ".pdf\"");

        			ServletOutputStream outstream = response.getOutputStream();
        			outstream.write(bytes,0,bytes.length);
        			outstream.flush();
        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in ReportsAction : FeeCollectionPdfReport Ending");

        		return null;
        	}

            public ActionForward StockEntryReportPrint(ActionMapping mapping,
        			ActionForm form, HttpServletRequest request,
        			HttpServletResponse response) throws Exception {

        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in ReportsAction : StockEntryReportPrint Starting");

        		
        		try {

        			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
          		  String userName = userDetailVO.getUserName();
          		
          		  String locId=request.getParameter("LocId");
    			      String itemId=request.getParameter("itemType");
    			      String regdateId=request.getParameter("regDate");
    			      String booktitle=request.getParameter("bookTitle");
    			      String authorId=request.getParameter("author");
    			      String pubId=request.getParameter("publisher");
    			      String loctionname=request.getParameter("locationname");
    			   
    			       String itemtype=request.getParameter("itemType");
    			     String bookId=request.getParameter("bookTitle");
   			      String author=request.getParameter("author");
   			      String pub=request.getParameter("publishernm");
    			      if(itemId.equalsIgnoreCase("all")){
    			    	  itemId="%%";
    			    	itemtype = "ALL";
    			      }
    			      
    			      if(booktitle.equalsIgnoreCase("all")){
    			    	   booktitle="%%";
    			    	 bookId="ALL";
    			      }
    			      
    			      if(authorId.equalsIgnoreCase("all")){
    			    	  authorId="%%";
    			    	author="ALL";
    			      }
    			      if(pubId.equalsIgnoreCase("all")){
    			    	  pubId="%%";
    			    	pub="ALL";
    			      }
    			      

    				List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
    			System.out.println("//////////////////"+stocklist.size());
          			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
          					stocklist);		
          			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

          			String schName=request.getParameter("location");
          			String schAddLine1=res.getString("AddressLine1");

          			Map mapdata=new HashMap();

          			mapdata.put("image",PropfilePath);
          			mapdata.put("schName", SchoolName);
          			mapdata.put("schadd", schAddLine1);
          			mapdata.put("locId", loctionname);
          			mapdata.put("itemId",itemtype);
          			mapdata.put("booktitle", bookId);
          			mapdata.put("authorId", author);
          			mapdata.put("pubId", pub);
          			
        			/*examReportClassWiseDetails*/
        			
          			String sourceFileName=request.getRealPath("Reports/StockEntryReportPdf.jrxml");

        			JasperDesign design = JRXmlLoader.load(sourceFileName);
        			JasperReport jasperreport = JasperCompileManager
        					.compileReport(design);

        			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
        			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
        	
        			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
        			//JasperViewer.viewReport(jasperPrint, false);
        			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
        			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
        			JasperViewer viewer = new JasperViewer(jasperPrint, false);
        			viewer.setVisible(true);
        			
        			PrinterJob job = PrinterJob.getPrinterJob();
        			   int selectedService = 0;
        			   selectedService = 0;
        			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
        			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
        			/*   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
        			   printRequestAttributeSet.add(mediaSizeName);*/
        			   printRequestAttributeSet.add(new Copies(1));
        			   JRPrintServiceExporter exporter;
        			   exporter = new JRPrintServiceExporter();
        			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
        			   exporter.exportReport();
        			   job.print(printRequestAttributeSet);
        		} catch (Exception e) {
        			logger.error(e.getMessage(), e);
        			e.printStackTrace();
        		}
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.END_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in ReportsAction : StockEntryReportPrint Ending");

        		return null;
        	}  

        public ActionForward SearchBookSearchByDDCAnyWhere(ActionMapping mapping, ActionForm form,
        			HttpServletRequest request, HttpServletResponse response)
        					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String orderby = request.getParameter("orderby");
             
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			System.out.println(orderby);
			
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(orderby);
			
			List = librarydelegate.SearchBookSearchByDDCAnyWhere(pojo);
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
    public ActionForward getAllBookContentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookContentDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}
    
    public ActionForward SearchBookSearchByContentAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String ordeby = request.getParameter("ordeby");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(ordeby);
			
			List = librarydelegate.SearchBookSearchByContentAnyWhere(pojo);
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
    public ActionForward journalsubscriptionDetailsSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : journalsubscriptionDetailsSearch Starting");

		
		try {
			ArrayList<LibraryJournalSubscriptionVo> list=new ArrayList<LibraryJournalSubscriptionVo>();
			String name=request.getParameter("Name");
			
			

			LibraryDelegate librarydelegate=new LibraryDelegate();
			ArrayList<LibraryJournalSubscriptionVo> List=new ArrayList<LibraryJournalSubscriptionVo>();

			String searchTextVal  = request.getParameter("searchid");

			if(searchTextVal == null || searchTextVal ==""){
				searchTextVal="%%";
			}
			
			if (searchTextVal != null || searchTextVal !="") {
				List = librarydelegate.journalsubscriptionDetailsSearch(searchTextVal.trim(),name);
			}
			
			request.setAttribute("searchnamelistValue", searchTextVal);
			
			JSONObject obj=new JSONObject();
			obj.put("jurserch",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : journalsubscriptionDetailsSearch Ending");

		return null;
	} 
    public ActionForward getAllBookLanguageDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookLanguageDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}
    public ActionForward SearchBookSearchByLanguageAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String orderby = request.getParameter("orderby");

			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(orderby);
			
			List = librarydelegate.SearchBookSearchByLanguageAnyWhere(pojo);
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
    public ActionForward getAllBookSupplierDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			
			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			/*request.setAttribute("searchTextVal", searchTextVal);*/
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			list = new LibraryDelegate().getAllBookSupplierDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);
			request.setAttribute("studentList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");
		return null;
	}
    public ActionForward SearchBookSearchBySupplierAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");

		try {
			String searchTextVal  = request.getParameter("searchname").trim();
			String select = request.getParameter("select");
			String searching = request.getParameter("searching");
			String orderby = request.getParameter("orderby");

			
			LibraryDelegate librarydelegate=new LibraryDelegate();
			List<LibrarySearchSubscriberVO> List = new ArrayList<LibrarySearchSubscriberVO>();

			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSelect(select);
			pojo.setSearchTextVal(searchTextVal);
			pojo.setSearch(searching);
			pojo.setOrderBy(orderby);
			
			List = librarydelegate.SearchBookSearchBySupplierAnyWhere(pojo);
			
			request.setAttribute("searchnamelistValue", searchTextVal);

			JSONObject obj=new JSONObject();
			obj.put("studentData",List);
			response.getWriter().print(obj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
    
    public ActionForward gotostockDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction: editStockEntryDetail Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCH);
			
			String arg = "Modify Stock Entry Details";
			request.setAttribute("msg", arg);
			
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getCategoryDetails();
			request.setAttribute("categoryDetails", list);
			String location=(String) request.getSession(false).getAttribute("current_schoolLocation");
			List<LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			loclist = obj.getLibLocationsDetails(location);
			request.setAttribute("librarylocationsDetails", loclist);
			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
			request.setAttribute("publicationlist", publist);
			List<LibraryStockEntryVO> suplist = new LibraryDelegate().getSupplierSettingList();
			request.setAttribute("suplist", suplist);
			

			String id = request.getParameter("EntryId");

			LibraryStockEntryDetailsForm edit_StockEntry = new LibraryDelegate().gotostockDetails(id);
			System.out.println(edit_StockEntry.getBookPhoto());
			request.setAttribute("editlist",edit_StockEntry);
			
			request.setAttribute("entryid", id);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  LibraryAction : editStockEntryDetail Starting Ending");

		return mapping.findForward(LibraryMessageConstants.StockEntryDetails);
	}
    
    public ActionForward IssueStatementByStockEntryId(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    				throws Exception {

    	logger.setLevel(Level.DEBUG);
    	JLogger.log(0, JDate.getTimeString(new Date())
    			+ MessageConstants.START_POINT);
    	logger.info(JDate.getTimeString(new Date())
    			+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
    	try {
    		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
    				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCH);
    		
    		LibrarySubscribVO vo=new LibrarySubscribVO();
    		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
    		
    		String EntryId = request.getParameter("EntryId");
    		String selection = request.getParameter("selection");
    		
    		LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
    		
    		pojo.setSelect(selection);
    		pojo.setEntryId(EntryId);
    		
    		vo = new LibraryDelegate().IssueStatementByStockEntryId(pojo);
    		list=new LibraryDelegate().IssueStatementTableByStockEntryId(pojo);
    		
    		request.setAttribute("studentList",vo);
    		request.setAttribute("issuedetails",list);
    		
    		request.setAttribute("listsize",list.size());
    		
    	    }
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    		e.printStackTrace();
    	}
    	JLogger.log(0, JDate.getTimeString(new Date())
    			+ MessageConstants.END_POINT);
    	logger.info(JDate.getTimeString(new Date())
    			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");
    	return mapping.findForward(LibraryMessageConstants.IssueStatementByEntryId);
    }
    
    
    public ActionForward ReturnStatementByEntryId(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    				throws Exception {

    	logger.setLevel(Level.DEBUG);
    	JLogger.log(0, JDate.getTimeString(new Date())
    			+ MessageConstants.START_POINT);
    	logger.info(JDate.getTimeString(new Date())
    			+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
    	try {
    		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
    		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
    		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
    				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCH);
    		
    		LibrarySubscribVO vo=new LibrarySubscribVO();
    		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
    		
    		String EntryId = request.getParameter("EntryId");
    		String selection = request.getParameter("selection");
    		
    		LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
    		
    		pojo.setSelect(selection);
    		pojo.setEntryId(EntryId);
    		
    		vo = new LibraryDelegate().IssueStatementByStockEntryId(pojo);
    		list=new LibraryDelegate().ReturnStatementTableByStockEntryId(pojo);
    		
    		request.setAttribute("studentList",vo);
    		request.setAttribute("issuedetails",list);
    		
    		request.setAttribute("listsize",list.size());
    		
    	    }
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    		e.printStackTrace();
    	}
    	JLogger.log(0, JDate.getTimeString(new Date())
    			+ MessageConstants.END_POINT);
    	logger.info(JDate.getTimeString(new Date())
    			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");
    	return mapping.findForward(LibraryMessageConstants.ReturnStatementByEntryId);
    }
            
      public ActionForward StockEntryExelReport(ActionMapping mapping,
        			ActionForm form, HttpServletRequest request,
        			HttpServletResponse response) {
        		logger.setLevel(Level.DEBUG);
        		JLogger.log(0, JDate.getTimeString(new Date())
        				+ MessageConstants.START_POINT);
        		logger.info(JDate.getTimeString(new Date())
        				+ " Control in ReportsAction : StockEntryExelReport Starting");

        		
        		String filePath = null;

        		try {

        			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
            		  String userName = userDetailVO.getUserName();
            		
            		  String locId=request.getParameter("LocId");
      			      String itemId=request.getParameter("itemType");
      			      String regdateId=request.getParameter("regDate");
      			      String booktitle=request.getParameter("bookTitle");
      			      String authorId=request.getParameter("author");
      			      String pubId=request.getParameter("publisher");
      			      String loctionname=request.getParameter("locationname");
      			   
      			       String itemtype=request.getParameter("itemType");
      			     String bookId=request.getParameter("bookTitle");
     			      String author=request.getParameter("author");
     			      String pub=request.getParameter("publishernm");
      			      if(itemId.equalsIgnoreCase("all")){
      			    	  itemId="%%";
      			    	itemtype = "ALL";
      			      }
      			      
      			      if(booktitle.equalsIgnoreCase("all")){
      			    	   booktitle="%%";
      			    	 bookId="ALL";
      			      }
      			      
      			      if(authorId.equalsIgnoreCase("all")){
      			    	  authorId="%%";
      			    	author="ALL";
      			      }
      			      if(pubId.equalsIgnoreCase("all")){
      			    	  pubId="%%";
      			    	pub="ALL";
      			      }
      			      

      				List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
      			System.out.println("//////////////////"+stocklist.size());
            			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
            					stocklist);		
            			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

            			String schName=request.getParameter("location");
            			String schAddLine1=res.getString("AddressLine1");

            			Map mapdata=new HashMap();

            			mapdata.put("image",PropfilePath);
            			mapdata.put("schName", SchoolName);
            			mapdata.put("schadd", schAddLine1);
            			mapdata.put("locId", loctionname);
            			mapdata.put("itemId",itemtype);
            			mapdata.put("booktitle", bookId);
            			mapdata.put("authorId", author);
            			mapdata.put("pubId", pub);
            			
        			
        			File pdfxls = null;
        			FileInputStream input = null;
        			BufferedInputStream buf = null;
        			ServletOutputStream stream = null;

        			String sourceFileName = request.getRealPath("Reports/StockEntryReportXL.jrxml");
        			JasperDesign design = JRXmlLoader.load(sourceFileName);
        			JasperReport jasperreport = JasperCompileManager
        					.compileReport(design);

        			stream = response.getOutputStream();
        			JasperPrint print = JasperFillManager.fillReport(jasperreport,
        					mapdata, beanColDataSource);
        			JRXlsExporter exporter = new JRXlsExporter();
        			File outputFile = new File(
        					request.getRealPath("Reports/StockEntryExelReport.xls"));
        			FileOutputStream fos = new FileOutputStream(outputFile);
        			String[] sheetNames = { "Stock Entry Details" };
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

        			pdfxls = new File(request.getRealPath("Reports/StockEntryExelReport.xls"));
        			response.setContentType("application/octet-stream");
        			response.addHeader("Content-Disposition",
        					"attachment; filename=StockEntryExelReport.xls");
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
        				+ " Control in ReportsAction : StockEntryExelReport Ending");

        		return null;
        	}
  	public ActionForward getClassByLibraryLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String locationid = request.getParameter("locationid");

			List<LibrarySubscribVO> ClassList = new LibraryDelegate().getClassByLibraryLocation(locationid);

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}
  	
	public ActionForward getLibraryClassSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassSection Starting");

		String schoolLocation = null;
		schoolLocation = request.getParameter("locationId");
		if (schoolLocation == null || schoolLocation.equalsIgnoreCase("")) {
			schoolLocation = (String) request.getSession(false).getAttribute(
					"current_schoolLocation");
		}

		try {
			String classidVal = request.getParameter("classidVal");
			System.out.println("class id val is" + classidVal);
			List<LibrarySubscribVO> List = new LibraryDelegate().getLibraryClassSection(classidVal + "," + schoolLocation);
			
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.accumulate("sectionList", List);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getClassSection Ending");
		return null;
	}
	public ActionForward getStudentOverDueStatementByClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getOverDueStatement Starting");

		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String classname= request.getParameter("classname");
		String select=request.getParameter("select");
		String department=request.getParameter("department");
		
		try {
			if(locid.equalsIgnoreCase("all")||locid.equalsIgnoreCase("")){
				locid="%%";
			}
			if(accyear.equalsIgnoreCase("all")||accyear.equalsIgnoreCase("")){
				accyear="%%";
			}
			if(classname.equalsIgnoreCase("all")||classname.equalsIgnoreCase("") ||classname.equalsIgnoreCase("null")){
				classname="%%";
			}
			if(department.equalsIgnoreCase("all")||department.equalsIgnoreCase("") ||department.equalsIgnoreCase("null")){
				department="%%";
			}
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setLocationName(locid);
			pojo.setAccId(accyear);
			pojo.setClassId(classname);
			pojo.setSuscriberType(select);
			pojo.setDepartment(department);
			
			list= new  LibraryDelegate().getStudentOverDueStatementByClass(pojo);

			JSONObject obj1= new JSONObject();
			obj1.put("SearchList",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getOverDueStatement ending");

		return null;
	}
	
	public ActionForward getStudentOverDueStatementBySection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getOverDueStatement Starting");

		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String classname= request.getParameter("classname");
		String section= request.getParameter("section");
		String select=request.getParameter("select");
		String department=request.getParameter("department");
		String designation=request.getParameter("designation");
		
		try {
			if(locid.equalsIgnoreCase("all")||locid.equalsIgnoreCase("")){
				locid="%%";
			}
			if(accyear.equalsIgnoreCase("all")||accyear.equalsIgnoreCase("")){
				accyear="%%";
			}
			if(classname.equalsIgnoreCase("all")||classname.equalsIgnoreCase("") ||classname.equalsIgnoreCase("null")){
				classname="%%";
			}
			if(section.equalsIgnoreCase("all")||section.equalsIgnoreCase("")){
				section="%%";
			}
			if(department.equalsIgnoreCase("all")||department.equalsIgnoreCase("")){
				department="%%";
			}
			if(designation.equalsIgnoreCase("all")||designation.equalsIgnoreCase("")){
				designation="%%";
			}
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setLocationName(locid);
			pojo.setAccId(accyear);
			pojo.setClassId(classname);
			pojo.setSectionId(section);
			pojo.setSuscriberType(select);
			pojo.setDepartment(department);
			pojo.setDesignation(designation);
			
			list= new  LibraryDelegate().getStudentOverDueStatementBySection(pojo);

			JSONObject obj1= new JSONObject();
			obj1.put("SearchList",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getOverDueStatement ending");

		return null;
	}
	
	public ActionForward SearchOverDueStudentDetailsByAnyWhere(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getOverDueStatement Starting");

		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
		String searchby= request.getParameter("searchby");
		String searchvalue= request.getParameter("searchvalue").trim();
		String select= request.getParameter("select");
		
		String department= request.getParameter("department");
		String designation= request.getParameter("designation");
		String locationid= request.getParameter("locationid");
		String Acyearid= request.getParameter("Acyearid");
		String classname= request.getParameter("classname");
		String section= request.getParameter("section");
		
		try {
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			
			pojo.setSearchTextVal(searchvalue);
			pojo.setSearch(searchby);
			pojo.setSelect(select);
			
			pojo.setDepartment(department);
			pojo.setDesignation(designation);
			pojo.setLocationName(locationid);
			pojo.setAccId(Acyearid);
			pojo.setClassId(classname);
			pojo.setSectionId(section);
			
			if(searchby.equalsIgnoreCase("all")||searchby.equalsIgnoreCase("")){
				searchby="%%";
			}
			list= new  LibraryDelegate().SearchOverDueStudentDetailsByAnyWhere(pojo);

			JSONObject obj1= new JSONObject();
			obj1.put("SearchList",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getOverDueStatement ending");

		return null;
	}
	
	public ActionForward getStudentOverDueByOrderwise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getOverDueStatement Starting");

		ArrayList<LibraryStockEntryDetailsForm> list = new ArrayList<LibraryStockEntryDetailsForm>();
		
		String locid = request.getParameter("locationid");
		String accyear = request.getParameter("Acyearid");
		String classname= request.getParameter("classname");
		String section= request.getParameter("section");
		String select=request.getParameter("select");
		String orderby=request.getParameter("orderby");
		
		try {
			if(locid.equalsIgnoreCase("all")||locid.equalsIgnoreCase("")){
				locid="%%";
			}
			if(accyear.equalsIgnoreCase("all")||accyear.equalsIgnoreCase("")){
				accyear="%%";
			}
			if(classname.equalsIgnoreCase("all")||classname.equalsIgnoreCase("") ||classname.equalsIgnoreCase("null")){
				classname="%%";
			}
			if(section.equalsIgnoreCase("all")||section.equalsIgnoreCase("")){
				section="%%";
			}
			
			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
			pojo.setLocationName(locid);
			pojo.setAccId(accyear);
			pojo.setClassId(classname);
			pojo.setSectionId(section);
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);
			
			
			list= new  LibraryDelegate().getStudentOverDueByOrderwise(pojo);

			JSONObject obj1= new JSONObject();
			obj1.put("SearchList",list);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getOverDueStatement ending");

		return null;
	}
	
	public ActionForward getAllOverDueListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in LibraryAction: getStockEntryBookList Starting");
					try {
						String locid = request.getParameter("locationid");
						String accyear = request.getParameter("Acyearid");
						String select=request.getParameter("select");
						String orderby=request.getParameter("orderby");
						String section = request.getParameter("section");
						String classname = request.getParameter("classname");
						String designation=request.getParameter("desg");
						String department=request.getParameter("dept");
						String noofdays = request.getParameter("days");
						String dates = request.getParameter("dates");
						if(select.equalsIgnoreCase("Student") && classname.equalsIgnoreCase("all")){
							classname="%%";
						}
						if(select.equalsIgnoreCase("Student") && section.equalsIgnoreCase("all")){
							section="%%";
						}
						if(select.equalsIgnoreCase("staff") && designation.equalsIgnoreCase("all")){
							designation="%%";
						}
						if(select.equalsIgnoreCase("staff") && department.equalsIgnoreCase("all")){
							department="%%";
						}

						LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
						pojo.setLocationName(locid);
						pojo.setAccId(accyear);
						pojo.setSelect(select);
						pojo.setOrderBy(orderby);
						pojo.setSectionId(section);
						pojo.setClassId(classname);
						pojo.setDesignation(designation);
						pojo.setDepartment(department);


						List<LibrarySearchIssueDetailsVO > reservationList = new LibraryDelegate().getAllOverDueListDetails(pojo);
						request.getSession(false).setAttribute("dueList",reservationList);
						ArrayList<LibrarySearchIssueDetailsVO> list = new ArrayList<LibrarySearchIssueDetailsVO>();
						list.addAll(reservationList);
						 
						if(noofdays!=null && !noofdays.equalsIgnoreCase("")){
							 reservationList.clear();
							for(int i=0;i<list.size();i++){
								
								String todate = list.get(i).getTodate();
								String returndate = list.get(i).getReturneddate();
								
								if(list.get(i).getReturneddate().equalsIgnoreCase("-")){
									DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
									Date today = new Date();
									returndate = (dateFormat.format(today).toString());
								}
								
								
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
								Date dateStart = simpleDateFormat.parse(todate);
							    Date dateEnd = simpleDateFormat.parse(returndate);
								int diff = HelperClass.daysBetween(dateStart,dateEnd);
						        System.out.println("diff"+diff);
						        if(diff > Integer.parseInt(noofdays)){
						        	reservationList.add(list.get(i));
						        }
							}
						}
						
						if(dates!=null && !dates.equalsIgnoreCase("")){
							System.out.println("0--0-0---0-");
							ArrayList<LibrarySearchIssueDetailsVO> list1 = new ArrayList<LibrarySearchIssueDetailsVO>();
							list1.addAll(reservationList);
							reservationList.clear();
							for(int i=0;i<list1.size();i++){
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
								Date dateStart = simpleDateFormat.parse(list1.get(i).getTodate());
							    Date dateEnd = simpleDateFormat.parse(dates);
							    if(dateStart.compareTo(dateEnd) == 0){
							    	reservationList.add(list1.get(i));
							    }
							}
						}
						
						
						JSONObject jsonObject = new JSONObject();
						jsonObject.accumulate("reservationList",reservationList );
						response.getWriter().print(jsonObject);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in LibraryAction:getStockEntryBookList Ending");
					return null;}
 

       
      public ActionForward getReservationListReport(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getReservationListReport Starting");
			try {
				
				   String location= request.getParameter("Location");
			       String AccId=request.getParameter("AccYear");
			       String subId=request.getParameter("Subscriber");
			       String accNo=request.getParameter("AccNo");
			       String bookId=request.getParameter("booktitle");
			       String fromdat=request.getParameter("fromdate");
			       System.out.println("from date "+fromdat);
			       String todate=request.getParameter("todate");
			       System.out.println("todate date "+todate);
			       String date=request.getParameter("date"); 
				
			    	 
			       if(accNo.equalsIgnoreCase("all")){
			    	   accNo="%%";
			       }
			       if(bookId.equalsIgnoreCase("all")){
			    	   bookId="%%";
			       }
			      
			    	 
			    List<LibraryStockEntryVO > reservationList = new LibraryDelegate().getReservationListReport(location,AccId,subId,accNo,bookId,fromdat,todate,date);
						
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("reservationList",reservationList );
				response.getWriter().print(jsonObject);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction:getReservationListReport Ending");
			return null;
		}
      
      
      public ActionForward getReservationAccNo(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
						throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction: getReservationListReport Starting");
			try {
				String subtype = request.getParameter("subtype");
				String accyear = request.getParameter("accyear");
			
				if(subtype.equalsIgnoreCase("ALL")){
					subtype="%%";
				}
				List<LibraryStockEntryDetailsForm > ResAccnoList = new LibraryDelegate().getReservationAccNo(subtype,accyear);
				request.setAttribute("Accnolist", ResAccnoList);
				
				JSONObject json = new JSONObject();
				json.put("Accnolist",ResAccnoList);
				response.getWriter().print(json);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LibraryAction:getReservationListReport Ending");
			return null;
		}
    
      
      public ActionForward getbooktitleList(ActionMapping mapping, ActionForm form,
  				HttpServletRequest request, HttpServletResponse response)
  						throws Exception {

  			logger.setLevel(Level.DEBUG);
  			JLogger.log(0, JDate.getTimeString(new Date())
  					+ MessageConstants.START_POINT);
  			logger.info(JDate.getTimeString(new Date())
  					+ " Control in LibraryAction: getReservationListReport Starting");
  			try {
  				
  				
  				String subtype = request.getParameter("subtype");
  				String accyear = request.getParameter("accyear");
  				String accNo=request.getParameter("accNo");
  				System.out.println(subtype);
  				System.out.println(accNo);
  				if(subtype.equalsIgnoreCase("ALL")){
  					subtype="%%";
  				}

  				List<LibraryStockEntryDetailsForm > ResAccnoList = new LibraryDelegate().getbooktitleList(subtype,accyear,accNo);
  						request.setAttribute("Accnolist", ResAccnoList);
  				
  						
  					
  						JSONObject json = new JSONObject();
  						json.put("booklist",ResAccnoList);
  						response.getWriter().print(json);
  						
  						
  						
  			} catch (Exception e) {
  				logger.error(e.getMessage(), e);
  				e.printStackTrace();
  			}
  			JLogger.log(0, JDate.getTimeString(new Date())
  					+ MessageConstants.END_POINT);
  			logger.info(JDate.getTimeString(new Date())
  					+ " Control in LibraryAction:getReservationListReport Ending");
  			return null;
  		}
        
      
      public ActionForward ReservationReportPdf(ActionMapping mapping,
  			ActionForm form, HttpServletRequest request,
  			HttpServletResponse response) throws Exception {

  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ReportsAction : ReservationReportPdf Starting");
  		try {
  			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
  		  String userName = userDetailVO.getUserName();
  		
  		 String location= request.getParameter("LocId");
  		 System.out.println("////"+location);
	       String AccId=request.getParameter("AccId");
	       String subId=request.getParameter("Subscriber");
	       String accNo=request.getParameter("AccNo");
	       String bookId=request.getParameter("booktitle");
	       String fromdat=request.getParameter("fromdate");
	       System.out.println("from date "+fromdat);
	       String todate=request.getParameter("todate");
	       System.out.println("todate date "+todate);
	       String date=request.getParameter("date"); 
	       System.out.println("date "+date);
		   String locationname=request.getParameter("locationname");
		   String Accnm=request.getParameter("Acyearid");
		   String AccNo=request.getParameter("AccNo");
		  String  booktitle=request.getParameter("booktitle");
		    String  All=request.getParameter("All");
		    String dates=null;
	       if(accNo.equalsIgnoreCase("all")){
	    	   accNo="%%";
	       }
	       if(bookId.equalsIgnoreCase("all")){
	    	   bookId="%%";
	       }
	         
	       if(date.equalsIgnoreCase("allfordate")){
		    	  dates="ALL";
		       }else{
		    	  dates = fromdat+" - "+todate;
		       }
	    	 
	    List<LibraryStockEntryVO > reservationList = new LibraryDelegate().getReservationListReport(location,AccId,subId,accNo,bookId,fromdat,todate,date);
  			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reservationList);		
  			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

  			String schName=request.getParameter("location");
  			String schAddLine1=res.getString("AddressLine1");

  			Map mapdata=new HashMap();

  			mapdata.put("image",PropfilePath);
  			mapdata.put("schName", SchoolName);
  			mapdata.put("schadd", schAddLine1);
  			mapdata.put("locnm", locationname);
  			mapdata.put("AccId", Accnm);
  			mapdata.put("fromdate", fromdat);
  			mapdata.put("todate", todate);
  			mapdata.put("All", subId);
  			mapdata.put("date",dates);
  			
  			
  	
  			String sourceFileName=request.getRealPath("Reports/ReservationReportPdf.jrxml");

  			JasperDesign design = JRXmlLoader.load(sourceFileName);
  			JasperReport jasperreport = JasperCompileManager.compileReport(design);

  			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

  			response.setContentType("application/pdf");
  			response.setContentLength(bytes.length);
  			response.setHeader("Content-Disposition", "outline; filename=\""
  					+ "ReservationList" + ".pdf\"");

  			ServletOutputStream outstream = response.getOutputStream();
  			outstream.write(bytes,0,bytes.length);
  			outstream.flush();
  		} catch (Exception e) {
  			logger.error(e.getMessage(), e);
  			e.printStackTrace();
  		}
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ReportsAction : ReservationReportPdf Ending");

  		return null;
  	}

      
      
      
      public ActionForward  ReservationReportPrint(ActionMapping mapping,
  			ActionForm form, HttpServletRequest request,
  			HttpServletResponse response) throws Exception {

  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ReportsAction : ReservationReportPrint Starting");

  		
  		try {

  			
  			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
    		  String userName = userDetailVO.getUserName();
    		
    		 String location= request.getParameter("LocId");
    		 System.out.println("////"+location);
  	       String AccId=request.getParameter("AccId");
  	       String subId=request.getParameter("Subscriber");
  	       String accNo=request.getParameter("AccNo");
  	       String bookId=request.getParameter("booktitle");
  	       String fromdat=request.getParameter("fromdate");
  	       System.out.println("from date "+fromdat);
  	       String todate=request.getParameter("todate");
  	       System.out.println("todate date "+todate);
  	       String date=request.getParameter("date"); 
  	       System.out.println("date "+date);
  		   String locationname=request.getParameter("locationname");
  		   String Accnm=request.getParameter("Acyearid");
  		   String AccNo=request.getParameter("AccNo");
  		  String  booktitle=request.getParameter("booktitle");
  		    String  All=request.getParameter("All");
  		    String dates=null;
  	       if(accNo.equalsIgnoreCase("all")){
  	    	   accNo="%%";
  	       }
  	       if(bookId.equalsIgnoreCase("all")){
  	    	   bookId="%%";
  	       }
  	     if(date.equalsIgnoreCase("allfordate")){
	    	  dates="ALL";
	       }else{
	    	  dates = fromdat+" - "+todate;
	       }
  	       
  	       
  	    	 
  	    	 
  	    List<LibraryStockEntryVO > reservationList = new LibraryDelegate().getReservationListReport(location,AccId,subId,accNo,bookId,fromdat,todate,date);
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reservationList);		
    			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

    			String schName=request.getParameter("location");
    			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata=new HashMap();

    			mapdata.put("image",PropfilePath);
    			mapdata.put("schName", SchoolName);
    			mapdata.put("schadd", schAddLine1);
    			mapdata.put("locnm", locationname);
    			mapdata.put("AccId", Accnm);
    			mapdata.put("AccNo", AccNo);
    			mapdata.put("booktitle", booktitle);
    			mapdata.put("fromdate", fromdat);
      			mapdata.put("todate", todate);
    			mapdata.put("All", subId);
    			mapdata.put("date", dates);
    			
    			
    	
    			String sourceFileName=request.getRealPath("Reports/ReservationReportPdf.jrxml");
  			JasperDesign design = JRXmlLoader.load(sourceFileName);
  			JasperReport jasperreport = JasperCompileManager
  					.compileReport(design);

  			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
  			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
  	
  			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
  			//JasperViewer.viewReport(jasperPrint, false);
  			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
  			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
  			JasperViewer viewer = new JasperViewer(jasperPrint, false);
  			viewer.setVisible(true);
  			
  			PrinterJob job = PrinterJob.getPrinterJob();
  			   int selectedService = 0;
  			   selectedService = 0;
  			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
  			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
  			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
  			  /* MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
  			   printRequestAttributeSet.add(mediaSizeName);*/
  			   printRequestAttributeSet.add(new Copies(1));
  			   JRPrintServiceExporter exporter;
  			   exporter = new JRPrintServiceExporter();
  			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
  			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
  			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
  			   exporter.exportReport();
  			   job.print(printRequestAttributeSet);
  		} catch (Exception e) {
  			logger.error(e.getMessage(), e);
  			e.printStackTrace();
  		}
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ReportsAction : ReservationReportPrint Ending");


  		return null;
  	}   

      
   

   	
      public ActionForward getSubscriberDetailExcelReport(ActionMapping mapping,
    			ActionForm form, HttpServletRequest request,
    			HttpServletResponse response) {
    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Starting");
    		
    		
    		String filePath = null;

    		try {
    			
    			ArrayList<LibrarySubscribVO> data = (ArrayList<LibrarySubscribVO>)request.getSession(false).getAttribute("sublist");
    			System.out.println("data length>>>"+data.size());
    			String subtype = request.getSession(false).getAttribute("suscriberType").toString();
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
    			String id = request.getParameter("type");
    			String schName=request.getParameter("location");
       			String schAddLine1=res.getString("AddressLine1");
       			
    			Map mapdata = new HashMap();
       			mapdata.put("schnm", SchoolName);
       			mapdata.put("schadd", schAddLine1);
    			mapdata.put("accYear",request.getParameter("accyear"));
    			mapdata.put("totalstudents",data.size());
    			String sourceFileName  = null;
    			if(subtype.equalsIgnoreCase("studentwise")){
    				sourceFileName = request.getRealPath("Reports/SubscriberListStudentReportXLS.jrxml");
    			}else if(subtype.equalsIgnoreCase("staffwise")){
    				sourceFileName = request.getRealPath("Reports/SubscriberListStaffReportXLS.jrxml");
    			}else if(subtype.equalsIgnoreCase("other")){
    				sourceFileName = request.getRealPath("Reports/SubscriberListOthersReportXLS.jrxml");
    			}
    			
    			
    			if(id.equalsIgnoreCase("excel")){
    			
    			File pdfxls = null;
    			FileInputStream input = null;
    			BufferedInputStream buf = null;
    			ServletOutputStream stream = null;
    			JasperDesign design = JRXmlLoader.load(sourceFileName);
    			JasperReport jasperreport = JasperCompileManager
    					.compileReport(design);
    			

    			stream = response.getOutputStream();
    			JasperPrint print = JasperFillManager.fillReport(jasperreport,
    					mapdata, beanColDataSource);
    			JRXlsExporter exporter = new JRXlsExporter();
    			File outputFile = new File(
    					request.getRealPath("Reports/subscriberList.xls"));
    			FileOutputStream fos = new FileOutputStream(outputFile);
    			String[] sheetNames = { "Subscriber Details" };
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

    			pdfxls = new File(request.getRealPath("Reports/subscriberList.xls"));
    			response.setContentType("application/octet-stream");
    			response.addHeader("Content-Disposition",
    					"attachment; filename=SubscriberList.xls");
    			response.setContentLength((int) pdfxls.length());
    			input = new FileInputStream(pdfxls);
    			buf = new BufferedInputStream(input);
    			int readBytes = 0;
    			stream = response.getOutputStream();
    			while ((readBytes = buf.read()) != -1) {
    				stream.write(readBytes);
    			}
    			stream.close();
    			
    			}
    			
    			else if(id.equalsIgnoreCase("pdf")){
    				JasperDesign design = JRXmlLoader.load(sourceFileName);
        			JasperReport jasperreport = JasperCompileManager
        					.compileReport(design);
        			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,mapdata, beanColDataSource);

  				response.setContentType("application/pdf");

  				response.setContentLength(bytes.length);

  				response.setHeader("Content-Disposition", "outline; filename=\""
  						+ "SubscriberDetails" + ".pdf\"");

  				ServletOutputStream outstream = response.getOutputStream();

  				outstream.write(bytes, 0, bytes.length);

  				outstream.flush();
    			}
    			
    			else if(id.equalsIgnoreCase("print")){
    				
    				JasperDesign design = JRXmlLoader.load(sourceFileName);
        			JasperReport jasperreport = JasperCompileManager
        					.compileReport(design);
    				
    				JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
    				mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    		
    				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
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
    			}
    

    		} catch (Exception exception) {
    			logger.error(exception.getMessage(), exception);
    			exception.printStackTrace();
    		}

    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.END_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Ending");

    		return null;
    	}
  	           
      public ActionForward getStaffSubscriberDetailReport(ActionMapping mapping,
  			ActionForm form, HttpServletRequest request,
  			HttpServletResponse response) {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Starting");
  		
  		
  		String filePath = null;

  		try {
  			String id = request.getParameter("id");
  			String location = request.getParameter("location");
  			String accyear = request.getParameter("accyear");
  			String designation = request.getParameter("designation");
  			String department = request.getParameter("department");
  			
  			String designationname=request.getParameter("designationname");
  			String departmentname=request.getParameter("departmentname");
  			String accYear=request.getParameter("accYear");
  		
  			System.out.println(id);
  			System.out.println("location in EX"+location);
  			System.out.println("accyear"+accyear);
  			System.out.println("designation"+designation);
  			System.out.println("department"+department);
  		
  			if(location == null || location.equalsIgnoreCase("") || location.equalsIgnoreCase("all")) {
  				location = "%%";
  			}
  			
  			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
  				accyear = HelperClass.getCurrentYearID();
  			}
  			if(designation == null || designation.equalsIgnoreCase("") || designation.equalsIgnoreCase("all")) {
  				designation = "%%";
  			}
  			
  			if(department == null || department == "" || department.equalsIgnoreCase("all")) {
  				department = "%%";
  			}
  			
  			
  			LibrarySearchIssueDetailsVO obj = new LibrarySearchIssueDetailsVO();
  			
  			obj.setAccyrid(accyear);
  			obj.setDepartment(department);
  			obj.setDesigantion(designation);
  			obj.setLocationid(location);
  			obj.setDesignationname(designationname);
  			obj.setDepartmentname(departmentname);
  				
  			
  			ArrayList<LibrarySearchIssueDetailsVO> data = new LibraryDelegate().getStaffSubscriberDetailReport(obj);
  			System.out.println("data length>>>"+data.size());
  			
  			if(id.equalsIgnoreCase("excel")){
  			
  			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
  			
  			String schName=request.getParameter("location");
   			String schAddLine1=res.getString("AddressLine1");

			Map mapdata = new HashMap();
   			mapdata.put("schnm", SchoolName);
   			mapdata.put("schadd", schAddLine1);
			mapdata.put("accYear",accYear);
			mapdata.put("totalstaff", data.size());
  			
  			
  			
  			File pdfxls = null;
  			FileInputStream input = null;
  			BufferedInputStream buf = null;
  			ServletOutputStream stream = null;

  			String sourceFileName = request
  					.getRealPath("Reports/SubscriberListstaffReportXLS.jrxml");
  			JasperDesign design = JRXmlLoader.load(sourceFileName);
  			JasperReport jasperreport = JasperCompileManager
  					.compileReport(design);
  			

  			stream = response.getOutputStream();
  			JasperPrint print = JasperFillManager.fillReport(jasperreport,
  					mapdata, beanColDataSource);
  			JRXlsExporter exporter = new JRXlsExporter();
  			File outputFile = new File(
  					request.getRealPath("Reports/subscriberList.xls"));
  			FileOutputStream fos = new FileOutputStream(outputFile);
  			String[] sheetNames = { "Student Subscriber Details" };
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

  			pdfxls = new File(request.getRealPath("Reports/subscriberList.xls"));
  			response.setContentType("application/octet-stream");
  			response.addHeader("Content-Disposition",
  					"attachment; filename=subscriberList.xls");
  			response.setContentLength((int) pdfxls.length());
  			input = new FileInputStream(pdfxls);
  			buf = new BufferedInputStream(input);
  			int readBytes = 0;
  			stream = response.getOutputStream();
  			while ((readBytes = buf.read()) != -1) {
  				stream.write(readBytes);
  			}
  			stream.close();
  			
  			}
  			
  			else if(id.equalsIgnoreCase("pdf")){
  				String sourceFileName = request
						.getRealPath("Reports/SubscriberListLibStaffReportPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

				String PropfilePath = getServlet().getServletContext().getRealPath("")
						+ "\\images\\" + ImageName.trim();

				String schName=request.getParameter("location");
       			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata = new HashMap();
       			mapdata.put("schnm", SchoolName);
       			mapdata.put("schadd", schAddLine1);
    			mapdata.put("accYear",accYear);
    			mapdata.put("totalstaff", data.size());
				

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						mapdata, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentSubscriberDetailsPDF" + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();
  			}
  			
  			else if(id.equalsIgnoreCase("print")){
  				String sourceFileName = null;
  				
  				sourceFileName = request
  						.getRealPath("Reports/SubscriberListLibStaffReportPDF.jrxml");
  				
  				JasperDesign design = JRXmlLoader.load(sourceFileName);

  				JasperReport jasperreport = JasperCompileManager.compileReport(design);
  				
  				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

  				String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

  				String schName=request.getParameter("location");
       			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata = new HashMap();
       			mapdata.put("schnm", SchoolName);
       			mapdata.put("schadd", schAddLine1);
    			mapdata.put("accYear",accYear);
    			mapdata.put("totalstaff", data.size());
  				
  				JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
  				mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
  		
  				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
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
  			}
  

  		} catch (Exception exception) {
  			logger.error(exception.getMessage(), exception);
  			exception.printStackTrace();
  		}

  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Ending");

  		return null;
  	}
      
      public ActionForward getOtherSubscriberDetailReport(ActionMapping mapping,
    			ActionForm form, HttpServletRequest request,
    			HttpServletResponse response) {
    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Starting");
    		
    		
    		String filePath = null;

    		try {
    			String id = request.getParameter("id");
    			String location = request.getParameter("location");
    			String accyear = request.getParameter("accyear");
    			String otherName = request.getParameter("otherName");
    			String otherNameText = request.getParameter("otherNameText");
    		
    			String accYear=request.getParameter("accYear");
    		
    			System.out.println(id);
    			System.out.println("location in EX"+location);
    			System.out.println("accyear"+accyear);
    			System.out.println("otherName"+otherName);
    			System.out.println("otherNameText"+otherNameText);
    		
    			if(location == null || location.equalsIgnoreCase("") || location.equalsIgnoreCase("all")) {
    				location = "%%";
    			}
    			
    			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
    				accyear = HelperClass.getCurrentYearID();
    			}
    			/*if(otherNameText == null || otherNameText.equalsIgnoreCase("") || otherNameText.equalsIgnoreCase("all")) {
    				otherNameText = "%%";
    			}
    			*/
    			LibrarySearchIssueDetailsVO obj = new LibrarySearchIssueDetailsVO();
    			
    			obj.setAccyrid(accyear);
    			obj.setOthersName(otherName);
    			obj.setLocationid(location);
    			obj.setOthersNameText(otherNameText);
    				
    			
    			ArrayList<LibrarySearchIssueDetailsVO> data = new LibraryDelegate().getOtherSubscriberDetailReport(obj);
    			System.out.println("data length>>>"+data.size());
    			
    			if(id.equalsIgnoreCase("excel")){
    			
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
    			
    			   			
    			File pdfxls = null;
    			FileInputStream input = null;
    			BufferedInputStream buf = null;
    			ServletOutputStream stream = null;
    			
    			String schName=request.getParameter("location");
       			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata = new HashMap();
       			mapdata.put("schnm", SchoolName);
       			mapdata.put("schadd", schAddLine1);
    			mapdata.put("accYear",accYear);
    			mapdata.put("totalnumber",data.size());

    			String sourceFileName = request
    					.getRealPath("Reports/SubscriberListOthersReportXLS.jrxml");
    			JasperDesign design = JRXmlLoader.load(sourceFileName);
    			JasperReport jasperreport = JasperCompileManager
    					.compileReport(design);
    			

    			stream = response.getOutputStream();
    			JasperPrint print = JasperFillManager.fillReport(jasperreport,
    					mapdata, beanColDataSource);
    			JRXlsExporter exporter = new JRXlsExporter();
    			File outputFile = new File(
    					request.getRealPath("Reports/SubscriberListOthersReportXLS.xls"));
    			FileOutputStream fos = new FileOutputStream(outputFile);
    			String[] sheetNames = { "Student Subscriber Details" };
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

    			pdfxls = new File(request.getRealPath("Reports/SubscriberListOthersReportXLS.xls"));
    			response.setContentType("application/octet-stream");
    			response.addHeader("Content-Disposition",
    					"attachment; filename=subscriberList.xls");
    			response.setContentLength((int) pdfxls.length());
    			input = new FileInputStream(pdfxls);
    			buf = new BufferedInputStream(input);
    			int readBytes = 0;
    			stream = response.getOutputStream();
    			while ((readBytes = buf.read()) != -1) {
    				stream.write(readBytes);
    			}
    			stream.close();
    			
    			}
    			
    			else if(id.equalsIgnoreCase("pdf")){
    				String sourceFileName = request
  						.getRealPath("Reports/SubscriberListOthersReportPDF.jrxml");
  				JasperDesign design = JRXmlLoader.load(sourceFileName);

  				JasperReport jasperreport = JasperCompileManager.compileReport(design);

  				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

  				String PropfilePath = getServlet().getServletContext().getRealPath("")
  						+ "\\images\\" + ImageName.trim();
  				String schName=request.getParameter("location");
       			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata = new HashMap();
       			mapdata.put("schnm", SchoolName);
       			mapdata.put("schadd", schAddLine1);
    			mapdata.put("accYear",accYear);
  				mapdata.put("totalstaff", data.size());
  			
  				

  				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
  						mapdata, beanColDataSource);

  				response.setContentType("application/pdf");

  				response.setContentLength(bytes.length);

  				response.setHeader("Content-Disposition", "outline; filename=\""
  						+ "StudentSubscriberDetailsPDF" + ".pdf\"");

  				ServletOutputStream outstream = response.getOutputStream();

  				outstream.write(bytes, 0, bytes.length);

  				outstream.flush();
    			}
    			
    			else if(id.equalsIgnoreCase("print")){
    				String sourceFileName = null;
    				
    				sourceFileName = request
    						.getRealPath("Reports/SubscriberListOthersReportPDF.jrxml");
    				
    				JasperDesign design = JRXmlLoader.load(sourceFileName);

    				JasperReport jasperreport = JasperCompileManager.compileReport(design);
    				
    				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

    				String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

    				String schName=request.getParameter("location");
           			String schAddLine1=res.getString("AddressLine1");

        			Map mapdata = new HashMap();
           			mapdata.put("schnm", SchoolName);
           			mapdata.put("schadd", schAddLine1);
        			mapdata.put("accYear",accYear);

        			mapdata.put("totalstudents", data.size());
    				
    				JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
    				mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    		
    				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
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
    			}
    

    		} catch (Exception exception) {
    			logger.error(exception.getMessage(), exception);
    			exception.printStackTrace();
    		}

    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.END_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Ending");

    		return null;
    	}  
          
public ActionForward getNewArrivalListPDFreport(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in ReportsAction : getNewArrivalListPDFreport Starting");
    		
    		try {
    			String sourceFileName=null;
    		      String checkedVal=request.getParameter("checkedVal");
    		      String Fromdate=request.getParameter("Fromdate");
    		      String ToDate=request.getParameter("ToDate");
  		     
  		   
  		  
  		      List<LibraryStockEntryVO> arrivalList = new LibraryDelegate().getNewArrivalListReport(checkedVal,Fromdate,ToDate);
  		      System.out.println("list size>>>"+arrivalList.size());
  		      if(arrivalList.size()==0)
  		      {
  		    	  request.setAttribute("errorMessage", "No Records Found In These Dates..");
  		    	return mapping.findForward(LibraryMessageConstants.newarrivalList);
  		      }
  		      else
  		      {
  	
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(arrivalList);		
    			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

    			String schName=request.getParameter("location");
    			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata=new HashMap();
    			
    			mapdata.put("fromdate", Fromdate);
    			mapdata.put("todate", ToDate);
    			
    	if(checkedVal.equalsIgnoreCase("accNo"))
    	{
    		 sourceFileName=request.getRealPath("Reports/NewArrivalListPDF.jrxml");
    	}else if(checkedVal.equalsIgnoreCase("category"))
    	{
    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfcategory.jrxml");
    	}else if(checkedVal.equalsIgnoreCase("author"))
    	{
    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfauthor.jrxml");
    	}else if(checkedVal.equalsIgnoreCase("title"))
    	{
    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfTitle.jrxml");
    	}
    	
    			

    			JasperDesign design = JRXmlLoader.load(sourceFileName);
    			JasperReport jasperreport = JasperCompileManager.compileReport(design);

    			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

    			response.setContentType("application/pdf");
    			response.setContentLength(bytes.length);
    			response.setHeader("Content-Disposition", "outline; filename=\""
    					+ "NewArrivalListPDF" + ".pdf\"");

    			ServletOutputStream outstream = response.getOutputStream();
    			outstream.write(bytes,0,bytes.length);
    			outstream.flush();
    		} 
    		}catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			e.printStackTrace();
    		}
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.END_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in LibraryAction : getNewArrivalListPDFreport Ending");

    		return null;
    		
    	}    
      
      public ActionForward printNewArrivalList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in LibraryAction : printNewArrivalList Starting");
    		try{
    			String sourceFileName=null;
  		      String checkedVal=request.getParameter("checkedVal");
  		      String Fromdate=request.getParameter("Fromdate");
  		      String ToDate=request.getParameter("ToDate");
		     
		   
		 
		      List<LibraryStockEntryVO> arrivalList = new LibraryDelegate().getNewArrivalListReport(checkedVal,Fromdate,ToDate);
    			
    			String termName = null;
    			
    			if(checkedVal.equalsIgnoreCase("accNo"))
    	    	{
    	    		 sourceFileName=request.getRealPath("Reports/NewArrivalListPDF.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("category"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfcategory.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("author"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfauthor.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("title"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfTitle.jrxml");
    	    	}
    	    	
    		
    			
    			
    			
    			JasperDesign design = JRXmlLoader.load(sourceFileName);

    			JasperReport jasperreport = JasperCompileManager.compileReport(design);
    			
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(arrivalList);

    			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

    			

    			Map parameters = new HashMap();
    			parameters.put("fromdate", Fromdate);
    			parameters.put("todate", ToDate);
    		
    			
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
    		return mapping.findForward(LibraryMessageConstants.newarrivalList);
    	}
      
      public ActionForward getAllIssueReturnDetails(ActionMapping mapping, ActionForm form,
  			HttpServletRequest request, HttpServletResponse response)
  					throws Exception {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction : StockEntryDetails Starting");
  		try {
  			ArrayList<LibrarySubsciberDetailsPojo> list = new ArrayList<LibrarySubsciberDetailsPojo>();
  			
  			String select = request.getParameter("select");
  			String orderby = request.getParameter("orderby");
  			String statement = request.getParameter("statement");
  			String locationid = request.getParameter("locationid");
  			
  			if(locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase(""))
  			{
  				locationid="%%";
  			}
  			
  			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
  			
  			pojo.setLocationName(locationid);
  			pojo.setStatement(statement);
  			pojo.setSelect(select);
  			pojo.setOrderBy(orderby);
  			
  			list = new LibraryDelegate().getAllIssueReturnDetails(pojo);

  			JSONObject obj=new JSONObject();
  			obj.put("studentData",list);
  			response.getWriter().print(obj);
  			request.setAttribute("studentList", list);
  		} catch (Exception e) {
  			logger.error(e.getMessage(), e);
  			e.printStackTrace();
  		}
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction : StockEntryDetails Ending");
  		return null;
  	}
      
      public ActionForward getAllIssueReturnOrderwise(ActionMapping mapping, ActionForm form,
    			HttpServletRequest request, HttpServletResponse response)
    					throws Exception {
    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in LibraryAction : StockEntryDetails Starting");
    		try {
    			ArrayList<LibraryStockEntryDetailsForm> list = new ArrayList<LibraryStockEntryDetailsForm>();
    			
    			String select = request.getParameter("select");
    			String orderby = request.getParameter("orderby");
    			String statement = request.getParameter("statement");
    			String locationid = request.getParameter("locationid");
    			
    			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
    			
    			pojo.setLocationName(locationid);
    			pojo.setStatement(statement);
    			pojo.setSelect(select);
    			pojo.setOrderBy(orderby);
    			
    			//list = new LibraryDelegate().getAllIssueReturnOrderwise(pojo);

    			JSONObject obj=new JSONObject();
    			obj.put("studentData",list);
    			response.getWriter().print(obj);
    			request.setAttribute("studentList", list);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			e.printStackTrace();
    		}
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.END_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in LibraryAction : StockEntryDetails Ending");
    		return null;
    	}
      
      public ActionForward IndividualSearchInIssueReturnStatement(ActionMapping mapping, ActionForm form,
  			HttpServletRequest request, HttpServletResponse response)
  					throws Exception {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");


  		List<LibrarySubsciberDetailsPojo> searchStudentList = new ArrayList<LibrarySubsciberDetailsPojo>();
  		try {
  			
  			
  			String searchterm = request.getParameter("searchTerm");
  			String locid = request.getParameter("locid");
  			String select = request.getParameter("select");
			String orderby = request.getParameter("orderby");
			String statement = request.getParameter("statement");
  			
  			if(locid ==null || locid.equalsIgnoreCase("") || locid.equalsIgnoreCase("all")){
  				locid="%%";
  			}
  			System.out.println(searchterm);
  			
  			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
  			
  			pojo.setSearchTextVal(searchterm);
  			pojo.setLocationName(locid);
  			pojo.setStatement(statement);
			pojo.setSelect(select);
			pojo.setOrderBy(orderby);

  			searchStudentList = new LibraryDelegate().IndividualSearchInIssueReturnStatement(pojo);
  			System.out.println("size...........");
  			System.out.println(searchStudentList.size());
  			JSONObject jsonObject = new JSONObject();
  			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

  			response.getWriter().print(jsonObject);
  		}catch(Exception e){
  			e.printStackTrace();
  		}
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

  		return null;
  	}
      
      public ActionForward getBookIssueReturnDetailsByAccessionNo(ActionMapping mapping,
  			ActionForm form, HttpServletRequest request,
  			HttpServletResponse response) throws Exception {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info("Control in getBookIssueDetailsByAccessionNo : getBookIssueDetailsByAccessionNo Starting");
  		List<LibraryStockEntryVO> bookIssueList = new ArrayList<LibraryStockEntryVO>();
  		
  		
  		try {
  			String subscriberId = request.getParameter("subscriberId");
  			String statement = request.getParameter("statement");
  			String orderby = request.getParameter("orderby");
  			String select = request.getParameter("select"); 
  			String location = request.getParameter("location");
  			String accession_no = request.getParameter("accession_no");
  			
  			if(location.equalsIgnoreCase("all")||location.equalsIgnoreCase("")){
  				location="%%";
  			}
  			
  			LibraryStockEntryVO libVo = new LibraryStockEntryVO();
  			
  			libVo.setSubscriberId(subscriberId);
  			libVo.setStatement(statement);
  			libVo.setOrder_By(orderby);
  			libVo.setSelect(select);
  			libVo.setLocation(location);
  			libVo.setAccessionNo(accession_no);
  			
  			bookIssueList = LibraryDelegate.getBookIssueReturnDetailsByAccessionNo(libVo);
  			
              System.out.println("iside get action method...........bookissuelist");
  			System.out.println(bookIssueList);
  			JSONObject array = new JSONObject();
  			array.put("accessionList",bookIssueList);
  			response.getWriter().print(array);
  		} catch (Exception e) {
  			logger.error(e.getMessage(), e);
  			e.printStackTrace();
  		}

  		logger.info(JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info("Control in StudentRegistrationAction : studentSearchByParent Ending");
  		return null;
  	}
      
      public ActionForward getJournalNameList(ActionMapping mapping, ActionForm form,
  			HttpServletRequest request, HttpServletResponse response)
  					throws Exception {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction :getJournalNameList Starting");
  		String accyear = (String) request.getSession(false).getAttribute(
				"current_academicYear");
  		ArrayList<LibraryStockEntryVO> list = new ArrayList<LibraryStockEntryVO>();
  		
  		try {
  			
  			
  			list= new  LibraryDelegate().getJournalNameList(accyear);

  			JSONObject obj1= new JSONObject();
  			obj1.put("data",list);
  			response.getWriter().print(obj1);

  		}catch(Exception e){
  			e.printStackTrace();
  		}


  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction:getJournalNameList ending");

  		return null;
  	}
      
      public ActionForward getJournalListPDFreport(ActionMapping mapping,
    			ActionForm form, HttpServletRequest request,
    			HttpServletResponse response) throws Exception {

    	    		logger.setLevel(Level.DEBUG);
    	    		JLogger.log(0, JDate.getTimeString(new Date())
    	    				+ MessageConstants.START_POINT);
    	    		logger.info(JDate.getTimeString(new Date())
    	    				+ " Control in ReportsAction : getJournalListPDFreport Starting");
    	    		
    	    		try {
    	    			String accyear = (String) request.getSession(false).getAttribute(
    	    					"current_academicYear");
    	    			
    	    			String sourceFileName=null;
    	    		      String checkedVal=request.getParameter("checkedVal");
    	    		      String Fromdate=request.getParameter("Fromdate");
    	    		      String ToDate=request.getParameter("ToDate");
    	    		      String journalName=request.getParameter("journalName");
    	    		      
    	    		      if(journalName.equalsIgnoreCase("null")||journalName.equalsIgnoreCase("") )
    	    		      {
    	    		    	  journalName="%%";
    	    		      }
    	  		     
    	  		   System.out.println("Fromdate>>"+Fromdate);
    	  		 System.out.println("ToDate>>"+ToDate);
    	  		 System.out.println("journalName>>"+journalName);
    	  		  
    	  		      List<LibraryJournalSubscriptionVo> journalList = new LibraryDelegate().getJournalListReport(checkedVal,Fromdate,ToDate,accyear,journalName);
    	  		      System.out.println("list size>>>"+journalList.size());
    	  		      if(journalList.size()==0)
    	  		      {
    	  		    	  request.setAttribute("errorMessage", "No Records Found In These Dates..");
    	  		    	return mapping.findForward(LibraryMessageConstants.journalReports);
    	  		    	
    	  		      }
    	  		      else
    	  		      {
    	  	
    	    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(journalList);		
    	    			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

    	    			String schName=request.getParameter("location");
    	    			String schAddLine1=res.getString("AddressLine1");

    	    			Map mapdata=new HashMap();
    	    			
    	    			mapdata.put("fromdate", Fromdate);
    	    			mapdata.put("todate", ToDate);
    	    			//journalListPDF
    	    	if(checkedVal.equalsIgnoreCase("subscription"))
    	    	{
    	    		 sourceFileName=request.getRealPath("Reports/journalListPDF.jrxml");
    	    	}/*else if(checkedVal.equalsIgnoreCase("category"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfcategory.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("author"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfauthor.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("title"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfTitle.jrxml");
    	    	}*/
    	    	
    	    			

    	    			JasperDesign design = JRXmlLoader.load(sourceFileName);
    	    			JasperReport jasperreport = JasperCompileManager.compileReport(design);

    	    			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

    	    			response.setContentType("application/pdf");
    	    			response.setContentLength(bytes.length);
    	    			response.setHeader("Content-Disposition", "outline; filename=\""
    	    					+ "JournalListPDF" + ".pdf\"");

    	    			ServletOutputStream outstream = response.getOutputStream();
    	    			outstream.write(bytes,0,bytes.length);
    	    			outstream.flush();
    	    		} 
    	    		}catch (Exception e) {
    	    			logger.error(e.getMessage(), e);
    	    			e.printStackTrace();
    	    		}
    	    		JLogger.log(0, JDate.getTimeString(new Date())
    	    				+ MessageConstants.END_POINT);
    	    		logger.info(JDate.getTimeString(new Date())
    	    				+ " Control in LibraryAction : getJournalListPDFreport Ending");

    	    		return null;
    	    		
    	    	}
            
      public ActionForward printJournalNameList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in LibraryAction : printJournalNameList Starting");
    		try{
    			String accyear = (String) request.getSession(false).getAttribute(
  					"current_academicYear");
  			
  			String sourceFileName=null;
  		      String checkedVal=request.getParameter("checkedVal");
  		      String Fromdate=request.getParameter("Fromdate");
  		      String ToDate=request.getParameter("ToDate");
  		      String journalName=request.getParameter("journalName");
  		      
  		      if(journalName.equalsIgnoreCase("null")||journalName.equalsIgnoreCase("") )
  		      {
  		    	  journalName="%%";
  		      }
  		     
  		   System.out.println("Fromdate>>"+Fromdate);
  		 System.out.println("ToDate>>"+ToDate);
  		 System.out.println("journalName>>"+journalName);
  		  
  		      List<LibraryJournalSubscriptionVo> journalList = new LibraryDelegate().getJournalListReport(checkedVal,Fromdate,ToDate,accyear,journalName);
  		      System.out.println("list size>>>"+journalList.size());
  		      if(journalList.size()==0)
  		      {
  		    	return mapping.findForward(LibraryMessageConstants.journalReports);
  		      }
  		      else
  		      {/*else if(checkedVal.equalsIgnoreCase("category"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfcategory.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("author"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfauthor.jrxml");
    	    	}else if(checkedVal.equalsIgnoreCase("title"))
    	    	{
    	    		sourceFileName=request.getRealPath("Reports/NewArrivalListPdfTitle.jrxml");
    	    	}*/
    	    	
  		    	  if(checkedVal.equalsIgnoreCase("subscription"))
  	    	    	{
  	    	    		 sourceFileName=request.getRealPath("Reports/journalListPDF.jrxml");
  	    	    	}
    			
    			
    			
    			JasperDesign design = JRXmlLoader.load(sourceFileName);

    			JasperReport jasperreport = JasperCompileManager.compileReport(design);
    			
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(journalList);

    			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

    			

    			Map parameters = new HashMap();
    			parameters.put("fromdate", Fromdate);
    			parameters.put("todate", ToDate);
    		
    			
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
    			
    		}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return mapping.findForward(LibraryMessageConstants.journalReports);
    	}
      
      public ActionForward getAllBookDetailsDownloadandPrint(ActionMapping mapping, ActionForm form,
  			HttpServletRequest request, HttpServletResponse response)
  					throws Exception {
  		logger.setLevel(Level.DEBUG);
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.START_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction : StockEntryDetails Starting");
  		try {
  			ArrayList<LibrarySearchSubscriberVO> data = new ArrayList<LibrarySearchSubscriberVO>();
  			String id = request.getParameter("id");
  			String requested_by = request.getParameter("requested_by");
  			String orderby = request.getParameter("order_by");
  			String started_by = request.getParameter("started_by");
  			String searchValue = request.getParameter("searchValue");
  			String select = request.getParameter("entryid");
  			String accYear = HelperClass.getCurrentYearID();
  			
  			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
  			
  			pojo.setSelect(select);
  			pojo.setOrderBy(orderby);
  			pojo.setRequestedby(requested_by);
  			pojo.setStartedby(started_by);
  			pojo.setSearch(searchValue);
  			
  			data = new LibraryDelegate().getAllBookDetailsDownloadandPrint(pojo);

  			if(id.equalsIgnoreCase("excel")){
  	  			
  	  			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
  	  			
  	  			Map parameters = new HashMap();
				
				parameters.put("accYear",accYear);
				parameters.put("totalresult", data.size());
  	  			
  	  			
  	  			File pdfxls = null;
  	  			FileInputStream input = null;
  	  			BufferedInputStream buf = null;
  	  			ServletOutputStream stream = null;

  	  			String sourceFileName = request
  	  					.getRealPath("Reports/bookSearchXLS.jrxml");
  	  			JasperDesign design = JRXmlLoader.load(sourceFileName);
  	  			JasperReport jasperreport = JasperCompileManager
  	  					.compileReport(design);
  	  			

  	  			stream = response.getOutputStream();
  	  			JasperPrint print = JasperFillManager.fillReport(jasperreport,
  	  				parameters, beanColDataSource);
  	  			JRXlsExporter exporter = new JRXlsExporter();
  	  			File outputFile = new File(
  	  					request.getRealPath("Reports/subscriberList.xls"));
  	  			FileOutputStream fos = new FileOutputStream(outputFile);
  	  			String[] sheetNames = { "Student Subscriber Details" };
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

  	  			pdfxls = new File(request.getRealPath("Reports/subscriberList.xls"));
  	  			response.setContentType("application/octet-stream");
  	  			response.addHeader("Content-Disposition",
  	  					"attachment; filename=subscriberList.xls");
  	  			response.setContentLength((int) pdfxls.length());
  	  			input = new FileInputStream(pdfxls);
  	  			buf = new BufferedInputStream(input);
  	  			int readBytes = 0;
  	  			stream = response.getOutputStream();
  	  			while ((readBytes = buf.read()) != -1) {
  	  				stream.write(readBytes);
  	  			}
  	  			stream.close();
  	  			
  	  			}
  	  			
  	  			else if(id.equalsIgnoreCase("pdf")){
  	  				String sourceFileName = request
  							.getRealPath("Reports/bookSearchXLS.jrxml");
  					JasperDesign design = JRXmlLoader.load(sourceFileName);

  					JasperReport jasperreport = JasperCompileManager.compileReport(design);

  					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

  					String PropfilePath = getServlet().getServletContext().getRealPath("")
  							+ "\\images\\" + ImageName.trim();

  					Map parameters = new HashMap();
  					
  					parameters.put("accYear",accYear);
  					parameters.put("totalstudents", data.size());
  				
  					

  					byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
  							parameters, beanColDataSource);

  					response.setContentType("application/pdf");

  					response.setContentLength(bytes.length);

  					response.setHeader("Content-Disposition", "outline; filename=\""
  							+ "StudentSubscriberDetailsPDF" + ".pdf\"");

  					ServletOutputStream outstream = response.getOutputStream();

  					outstream.write(bytes, 0, bytes.length);

  					outstream.flush();
  	  			}
  	  			
  	  			else if(id.equalsIgnoreCase("print")){
  	  				String sourceFileName = null;
  	  				
  	  				sourceFileName = request
  	  						.getRealPath("Reports/SubscriberListStudentReportPDF.jrxml");
  	  				
  	  				JasperDesign design = JRXmlLoader.load(sourceFileName);

  	  				JasperReport jasperreport = JasperCompileManager.compileReport(design);
  	  				
  	  				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

  	  				String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

  	  				Map parameters = new HashMap();
  	  				parameters.put("accYear",accYear);
  					parameters.put("totalstudents", data.size());
  	  				
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
  	  			}
  		} catch (Exception e) {
  			logger.error(e.getMessage(), e);
  			e.printStackTrace();
  		}
  		JLogger.log(0, JDate.getTimeString(new Date())
  				+ MessageConstants.END_POINT);
  		logger.info(JDate.getTimeString(new Date())
  				+ " Control in LibraryAction : StockEntryDetails Ending");
  		return null;
  	}
  	public ActionForward getIssueReturnAccessionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo Starting");


		List<LibraryStockEntryVO> searchAccessionNo = new ArrayList<LibraryStockEntryVO>();
		try {
			LibraryStockEntryVO registrationVo = new LibraryStockEntryVO();
			
			String accNo = request.getParameter("accessionId");
			String subid = request.getParameter("subid");
			String statement = request.getParameter("statement");
			String orderby = request.getParameter("orderby");
			String select = request.getParameter("select");
			
			registrationVo.setAccessionNo(accNo);
			registrationVo.setSubscriberId(subid);
			registrationVo.setStatement(statement);
			registrationVo.setOrder_By(orderby);
			registrationVo.setSelect(select);
			
			searchAccessionNo = new LibraryDelegate().getIssueReturnAccessionNo(registrationVo);
			
			System.out.println("size in action cls;...........");
			System.out.println(searchAccessionNo.size());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ClassList", searchAccessionNo);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNo ending");

		return null;
	}     
 
  	
  	
  	 public ActionForward IssueReissuePdf(ActionMapping mapping,
   			ActionForm form, HttpServletRequest request,
   			HttpServletResponse response) throws Exception {

   		logger.setLevel(Level.DEBUG);
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.START_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ReportsAction : ReservationReportPdf Starting");
   		try {
      ArrayList<LibrarySubsciberDetailsPojo> list = new ArrayList<LibrarySubsciberDetailsPojo>();
  			String select = request.getParameter("select");
  			String orderby = request.getParameter("orderby");
  			String statement = request.getParameter("statement");
  			String locationid = request.getParameter("locationid");
  			String locationName=request.getParameter("locationname");
  			if(locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase(""))
  			{
  				locationid="%%";
  			}
  			
  			
  			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
  			
  			pojo.setLocationName(locationid);
  			pojo.setStatement(statement);
  			pojo.setSelect(select);
  			pojo.setOrderBy(orderby);
  			
  			list = new LibraryDelegate().getAllIssueReturnDetails(pojo);
   			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);		
   			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

   			String schName=request.getParameter("location");
   			String schAddLine1=res.getString("AddressLine1");

   			Map mapdata=new HashMap();

   			mapdata.put("image",PropfilePath);
   			mapdata.put("schnm", SchoolName);
   			mapdata.put("schadd", schAddLine1);
   			mapdata.put("locid", locationName);
   			mapdata.put("statement",statement);
   			
   	
   			String sourceFileName=request.getRealPath("Reports/IssueReturnPdf.jrxml");

   			JasperDesign design = JRXmlLoader.load(sourceFileName);
   			JasperReport jasperreport = JasperCompileManager.compileReport(design);

   			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

   			response.setContentType("application/pdf");
   			response.setContentLength(bytes.length);
   			response.setHeader("Content-Disposition", "outline; filename=\""
   					+ "Issue Statement" + ".pdf\"");

   			ServletOutputStream outstream = response.getOutputStream();
   			outstream.write(bytes,0,bytes.length);
   			outstream.flush();
   		} catch (Exception e) {
   			logger.error(e.getMessage(), e);
   			e.printStackTrace();
   		}
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.END_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ReportsAction : ReservationReportPdf Ending");

   		return null;
   	}

       
  	 public ActionForward IssueReturnPrint(ActionMapping mapping,
   			ActionForm form, HttpServletRequest request,
   			HttpServletResponse response) throws Exception {

   		logger.setLevel(Level.DEBUG);
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.START_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ReportsAction : ReservationReportPrint Starting");

   		
   		try {

      ArrayList<LibrarySubsciberDetailsPojo> list = new ArrayList<LibrarySubsciberDetailsPojo>();
  			
  			String select = request.getParameter("select");
  			String orderby = request.getParameter("orderby");
  			String statement = request.getParameter("statement");
  			String locationid = request.getParameter("locationid");
  			String locationName=request.getParameter("locationname");
  			if(locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase(""))
  			{
  				locationid="%%";
  			}
  			
  			
  			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
  			
  			pojo.setLocationName(locationid);
  			pojo.setStatement(statement);
  			pojo.setSelect(select);
  			pojo.setOrderBy(orderby);
  			
  			list = new LibraryDelegate().getAllIssueReturnDetails(pojo);
   			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);		
   			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

   			String schName=request.getParameter("location");
   			String schAddLine1=res.getString("AddressLine1");

   			Map mapdata=new HashMap();

   			mapdata.put("image",PropfilePath);
   			mapdata.put("schnm", SchoolName);
   			mapdata.put("schadd", schAddLine1);
   			mapdata.put("locid", locationName);
   			mapdata.put("statement",statement);
   			
   	
   			String sourceFileName=request.getRealPath("Reports/IssueReturnPdf.jrxml");
   			JasperDesign design = JRXmlLoader.load(sourceFileName);
   			JasperReport jasperreport = JasperCompileManager
   					.compileReport(design);

   			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
   			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
   	
   			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
   			//JasperViewer.viewReport(jasperPrint, false);
   			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
   			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
   			JasperViewer viewer = new JasperViewer(jasperPrint, false);
   			viewer.setVisible(true);
   			
   			PrinterJob job = PrinterJob.getPrinterJob();
   			   int selectedService = 0;
   			   selectedService = 0;
   			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
   			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
   			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
   			  /* MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
   			   printRequestAttributeSet.add(mediaSizeName);*/
   			   printRequestAttributeSet.add(new Copies(1));
   			   JRPrintServiceExporter exporter;
   			   exporter = new JRPrintServiceExporter();
   			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
   			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
   			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
   			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
   			   exporter.exportReport();
   			   job.print(printRequestAttributeSet);
   		} catch (Exception e) {
   			logger.error(e.getMessage(), e);
   			e.printStackTrace();
   		}
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.END_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ReportsAction : ReservationReportPrint Ending");


   		return null;
   	}   

       
  	
  	
  	 public ActionForward IssueReissueExcel(ActionMapping mapping,
 			ActionForm form, HttpServletRequest request,
 			HttpServletResponse response) {
 		logger.setLevel(Level.DEBUG);
 		JLogger.log(0, JDate.getTimeString(new Date())
 				+ MessageConstants.START_POINT);
 		logger.info(JDate.getTimeString(new Date())
 				+ " Control in ReportsAction : StockEntryExelReport Starting");

 		
 		String filePath = null;

 		try {

 			 ArrayList<LibrarySubsciberDetailsPojo> list = new ArrayList<LibrarySubsciberDetailsPojo>();
   			
   			String select = request.getParameter("select");
   			String orderby = request.getParameter("orderby");
   			String statement = request.getParameter("statement");
   			String locationid = request.getParameter("locationid");
   			String locationName=request.getParameter("locationname");
   			if(locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase(""))
   			{
   				locationid="%%";
   			}
   			
   			
   			LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
   			
   			pojo.setLocationName(locationid);
   			pojo.setStatement(statement);
   			pojo.setSelect(select);
   			pojo.setOrderBy(orderby);
   			
   			list = new LibraryDelegate().getAllIssueReturnDetails(pojo);
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);		
    			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

    			String schName=request.getParameter("location");
    			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata=new HashMap();

    			mapdata.put("image",PropfilePath);
    			mapdata.put("schnm", SchoolName);
    			mapdata.put("schadd", schAddLine1);
    			mapdata.put("locid", locationName);
    			mapdata.put("statement",statement);
    			
    	
    
     			
 			
 			File pdfxls = null;
 			FileInputStream input = null;
 			BufferedInputStream buf = null;
 			ServletOutputStream stream = null;

 			String sourceFileName = request.getRealPath("Reports/IssueReturnXL.jrxml");
 			JasperDesign design = JRXmlLoader.load(sourceFileName);
 			JasperReport jasperreport = JasperCompileManager
 					.compileReport(design);

 			stream = response.getOutputStream();
 			JasperPrint print = JasperFillManager.fillReport(jasperreport,
 					mapdata, beanColDataSource);
 			JRXlsExporter exporter = new JRXlsExporter();
 			File outputFile = new File(
 					request.getRealPath("Reports/IssueReturnXL.xls"));
 			FileOutputStream fos = new FileOutputStream(outputFile);
 			String[] sheetNames = { "Stock Entry Details" };
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

 			pdfxls = new File(request.getRealPath("Reports/IssueReturnXL.xls"));
 			response.setContentType("application/octet-stream");
 			response.addHeader("Content-Disposition",
 					"attachment; filename=IssueReissueExelReport.xls");
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
 				+ " Control in ReportsAction : StockEntryExelReport Ending");

 		return null;
 	}
  	
  	
  	
  	/* public ActionForward BarcodeGenrationPrint(ActionMapping mapping,
    			ActionForm form, HttpServletRequest request,
    			HttpServletResponse response) throws Exception {

    		logger.setLevel(Level.DEBUG);
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.START_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in ReportsAction : ReservationReportPrint Starting");

    		
    		try {

      
   			
   		    	List<LibraryStockEntryVO> stocklist = new LibraryDelegate().getstockEntryList();
    			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stocklist);		
    			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

    			String schName=request.getParameter("location");
    			String schAddLine1=res.getString("AddressLine1");

    			Map mapdata=new HashMap();

    			mapdata.put("image",PropfilePath);
    			mapdata.put("schnm", SchoolName);
    			mapdata.put("schadd", schAddLine1);
    			
    			
    	
    			String sourceFileName=request.getRealPath("Reports/IssueReturnPdf.jrxml");
    			JasperDesign design = JRXmlLoader.load(sourceFileName);
    			JasperReport jasperreport = JasperCompileManager
    					.compileReport(design);

    			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
    			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    	
    			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
    			//JasperViewer.viewReport(jasperPrint, false);
    			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
    			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
    			JasperViewer viewer = new JasperViewer(jasperPrint, false);
    			viewer.setVisible(true);
    			
    			PrinterJob job = PrinterJob.getPrinterJob();
    			   int selectedService = 0;
    			   selectedService = 0;
    			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
    			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
    			   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
    			   printRequestAttributeSet.add(mediaSizeName);
    			   printRequestAttributeSet.add(new Copies(1));
    			   JRPrintServiceExporter exporter;
    			   exporter = new JRPrintServiceExporter();
    			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
    			   exporter.exportReport();
    			   job.print(printRequestAttributeSet);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			e.printStackTrace();
    		}
    		JLogger.log(0, JDate.getTimeString(new Date())
    				+ MessageConstants.END_POINT);
    		logger.info(JDate.getTimeString(new Date())
    				+ " Control in ReportsAction : ReservationReportPrint Ending");


    		return null;
    	}   
*/
  	 
  	public ActionForward ReservationReportExcel(ActionMapping mapping,
 			ActionForm form, HttpServletRequest request,
 			HttpServletResponse response) {
 		logger.setLevel(Level.DEBUG);
 		JLogger.log(0, JDate.getTimeString(new Date())
 				+ MessageConstants.START_POINT);
 		logger.info(JDate.getTimeString(new Date())
 				+ " Control in ReportsAction : StockEntryExelReport Starting");

 		
 		String filePath = null;

 		try {

 			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
 	  		  String userName = userDetailVO.getUserName();
 	  		
 	  		 String location= request.getParameter("LocId");
 	  		 System.out.println("////"+location);
 		       String AccId=request.getParameter("AccId");
 		       String subId=request.getParameter("Subscriber");
 		       String accNo=request.getParameter("AccNo");
 		       String bookId=request.getParameter("booktitle");
 		       String fromdat=request.getParameter("fromdate");
 		       System.out.println("from date "+fromdat);
 		       String todate=request.getParameter("todate");
 		       System.out.println("todate date "+todate);
 		       String date=request.getParameter("date"); 
 		       System.out.println("date "+date);
 			   String locationname=request.getParameter("locationname");
 			   String Accnm=request.getParameter("Acyearid");
 			   String AccNo=request.getParameter("AccNo");
 			  String  booktitle=request.getParameter("booktitle");
 			    String  All=request.getParameter("All");
 			    String dates = null;
 		       if(accNo.equalsIgnoreCase("all")){
 		    	   accNo="%%";
 		       }
 		       if(bookId.equalsIgnoreCase("all")){
 		    	   bookId="%%";
 		       }
 		       if(date.equalsIgnoreCase("allfordate")){
 		    	  dates="ALL";
 		       }else{
 		    	  dates = fromdat+" - "+todate;
 		       }
 		    	 
 		    List<LibraryStockEntryVO > reservationList = new LibraryDelegate().getReservationListReport(location,AccId,subId,accNo,bookId,fromdat,todate,date);
 	  			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reservationList);		
 	  			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

 	  			String schName=request.getParameter("location");
 	  			String schAddLine1=res.getString("AddressLine1");

 	  			Map mapdata=new HashMap();

 	  			mapdata.put("image",PropfilePath);
 	  			mapdata.put("schName", SchoolName);
 	  			mapdata.put("schadd", schAddLine1);
 	  			mapdata.put("locnm", locationname);
 	  			mapdata.put("AccId", Accnm);
 	  			mapdata.put("fromdate", fromdat);
 	  			mapdata.put("todate", todate);
 	  			mapdata.put("All", subId);
 	  			mapdata.put("date", dates);
    
     			
 			
 			File pdfxls = null;
 			FileInputStream input = null;
 			BufferedInputStream buf = null;
 			ServletOutputStream stream = null;

 			String sourceFileName=request.getRealPath("Reports/ReservationReportXL.jrxml");
 			JasperDesign design = JRXmlLoader.load(sourceFileName);
 			JasperReport jasperreport = JasperCompileManager
 					.compileReport(design);

 			stream = response.getOutputStream();
 			JasperPrint print = JasperFillManager.fillReport(jasperreport,
 					mapdata, beanColDataSource);
 			JRXlsExporter exporter = new JRXlsExporter();
 			File outputFile = new File(
 					request.getRealPath("Reports/ReservationReportXL.xls"));
 			FileOutputStream fos = new FileOutputStream(outputFile);
 			String[] sheetNames = { "Stock Entry Details" };
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

 			pdfxls = new File(request.getRealPath("Reports/ReservationReportXL.xls"));
 			response.setContentType("application/octet-stream");
 			response.addHeader("Content-Disposition",
 					"attachment; filename=ReservationReportXL.xls");
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
 				+ " Control in ReportsAction : StockEntryExelReport Ending");

 		return null;
 	}
  	
  	public ActionForward overdueReportPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : overdueReportPDF Starting");

		try {
				
				String sourceFileName = request.getRealPath("Reports/OverDueReportPdf.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);
				List<LibrarySearchIssueDetailsVO > duelist = (List<LibrarySearchIssueDetailsVO>)request.getSession(false).getAttribute("dueList");
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(duelist);		
				
				//String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

				String schName=request.getParameter("location");
				String schAddLine1=res.getString("AddressLine1");

				Map mapdata=new HashMap();
				mapdata.put("schName",SchoolName);
				mapdata.put("schAdd",schAddLine1);

				byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""+ "OverDueStatement.pdf\"");

				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes,0,bytes.length);
				outstream.flush();
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : overdueReportPDF Ending");

		return null;
	}          

	public ActionForward printoverdueReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : printoverdueReport Starting");

		try {

			String sourceFileName = request.getRealPath("Reports/OverDueReportPdf.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			List<LibrarySearchIssueDetailsVO > duelist = (List<LibrarySearchIssueDetailsVO>)request.getSession(false).getAttribute("dueList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(duelist);		
			
			String schAddLine1=res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schName",SchoolName);
			parameters.put("schAdd",schAddLine1);

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
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : printoverdueReport Ending");

	return null;
	}

	public ActionForward overdueReportExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : overdueReportPDF Starting");

		try {
				
				String sourceFileName = request.getRealPath("Reports/OverDueReportPdf.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);
				List<LibrarySearchIssueDetailsVO > duelist = (List<LibrarySearchIssueDetailsVO>)request.getSession(false).getAttribute("dueList");
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(duelist);		
				
				//String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

				String schAddLine1=res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("schName",SchoolName);
				parameters.put("schAdd",schAddLine1);
				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						parameters, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(
						request.getRealPath("Reports/OverDueReportPdf.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "OverDue Statement" };
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

				pdfxls = new File(request.getRealPath("Reports/OverDueReportPdf.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=OverDueStatement.xls");
				response.setContentLength((int) pdfxls.length());
				input = new FileInputStream(pdfxls);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
				stream.close();
	} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : printoverdueReport Ending");
	return null;
	}
	
	public ActionForward IssueReturnBySubScriberType(ActionMapping mapping, ActionForm form,
	  		HttpServletRequest request, HttpServletResponse response)
	  				throws Exception {

	  	logger.setLevel(Level.DEBUG);
	  	JLogger.log(0, JDate.getTimeString(new Date())
	  			+ MessageConstants.START_POINT);
	  	logger.info(JDate.getTimeString(new Date())
	  			+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
	  	try {
	                 
	  		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
	  		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
	  		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
	  				LeftMenusHighlightMessageConstant.MODULE_LIBRARY_SEARCHSUBSCRIBER);
	  		
	  		
	  		LibrarySubscribVO vo=new LibrarySubscribVO();
	  		ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
	  		
	  		String academic_year = request.getParameter("accyear");
	  		String location = request.getParameter("location");
	  		String subId = request.getParameter("subId");
	  		String subscriberType=request.getParameter("subscriberType");

	  		if(location.equalsIgnoreCase("all")){
	  			location="%%";
	  		}
	  		
	  		vo = new LibraryDelegate().IssueReturnBySubScriberType(location,subId,academic_year,subscriberType);
	  		list=new LibraryDelegate().IssueReturnTable(location,subId,academic_year,subscriberType);
	  		request.setAttribute("studentList", vo);
	  		request.setAttribute("issuedetails",list);
	  		
	  		request.setAttribute("listsize",list.size());
	  		request.setAttribute("subId", subId);
	  		request.setAttribute("subscriberType", subscriberType);
	  		
	  	    }
	  	catch (Exception e) {
	  		logger.error(e.getMessage(),e);
	  		e.printStackTrace();
	  	}
	  	JLogger.log(0, JDate.getTimeString(new Date())
	  			+ MessageConstants.END_POINT);
	  	logger.info(JDate.getTimeString(new Date())
	  			+ " Control in AddDepartmentDetailsAction : editDepartment Ending");
	  	return mapping.findForward(LibraryMessageConstants.IssueReturnBySubScriberType);
	  }

	
	public ActionForward getTransferStaffdetailsByDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear_ID = request.getParameter("accyear");
			String Loc_ID = request.getParameter("location");
			String department=request.getParameter("department");
			String designation=request.getParameter("designation");
			String libloc = request.getParameter("libloc");
			
			if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase(""))
			{
				department="%%";
			}
			if(designation.equalsIgnoreCase("all") || designation.equalsIgnoreCase(""))
			{
				designation="%%";
			}
			if(libloc.equalsIgnoreCase("all") || libloc.equalsIgnoreCase(""))
			{
				libloc="%%";
			}

			String searchTerm = request.getParameter("searchname");
			
          LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
   			
   			pojo.setLocationName(Loc_ID);
   			pojo.setAccId(accyear_ID);
   			pojo.setDepartment(department);
   			pojo.setDesignation(designation);
   			pojo.setLibLoc(libloc);

			list = new LibraryDelegate().getTransferStaffdetailsByDepartment(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	public ActionForward getTransferStaffdetailsByDesignation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Starting");
		try {
			ArrayList<LibrarySearchSubscriberVO> list = new ArrayList<LibrarySearchSubscriberVO>();
			String accyear_ID = request.getParameter("accyear");
			String Loc_ID = request.getParameter("location");
			String department=request.getParameter("department");
			String designation=request.getParameter("designation"); 
			String libloc = request.getParameter("libloc");
			
			if(department.equalsIgnoreCase("all") || department.equalsIgnoreCase(""))
			{
				department="%%";
			}
			if(designation.equalsIgnoreCase("all") || designation.equalsIgnoreCase(""))
			{
				designation="%%";
			}

			String searchTerm = request.getParameter("searchname");
     LibrarySubsciberDetailsPojo pojo = new LibrarySubsciberDetailsPojo();
   			pojo.setLocationName(Loc_ID);
   			pojo.setAccId(accyear_ID);
   			pojo.setDepartment(department);
   			pojo.setDesignation(designation);
   			pojo.setLibLoc(libloc);
			
			list = new LibraryDelegate().getTransferStaffdetailsByDesignation(pojo);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction : StockEntryDetails Ending");

		return null;
	}
	
	
	public ActionForward getItemList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorydivisionlist Starting");

	
		try {
			
			List<LibraryStockEntryVO> itemlist = new LibraryDelegate().getitemlistList();
			request.setAttribute("itemlist", itemlist);
			
		

			JSONObject obj1= new JSONObject();
			obj1.put("itemlist",itemlist);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getlibcategorydivisionlist ending");

		return null;
	}
	
	public ActionForward getbookList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorydivisionlist Starting");

	
		try {
		
			List<LibraryStockEntryVO> booklist = new LibraryDelegate().booklist();
			request.setAttribute("booklist", booklist);

			JSONObject obj1= new JSONObject();
			obj1.put("booklist",booklist);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getlibcategorydivisionlist ending");

		return null;
	}
	
	
	public ActionForward getAuthorList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorydivisionlist Starting");

	
		try {
			List<LibraryStockEntryVO> authorlist = new LibraryDelegate().authorlist();
			request.setAttribute("authorlist", authorlist);
			
			

			JSONObject obj1= new JSONObject();
			obj1.put("authorlist",authorlist);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getlibcategorydivisionlist ending");

		return null;
	}
	
	
	public ActionForward getPublist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getlibcategorydivisionlist Starting");
		try {
		
			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
			request.setAttribute("publicationlist", publist);
			

			JSONObject obj1= new JSONObject();
			obj1.put("publist",publist);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getlibcategorydivisionlist ending");

		return null;
	}
	
	public ActionForward getAccessionNoByIssueStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNoByIssueStatus Starting");


		List<LibraryStockEntryVO> searchAccessionNo = new ArrayList<LibraryStockEntryVO>();
		try {
            LibraryStockEntryVO registrationVo = new LibraryStockEntryVO();
			
			String accNo = request.getParameter("accessionId");
			registrationVo.setAccessionNo(accNo);
			searchAccessionNo = new LibraryDelegate().getAccessionNoByIssueStatus(registrationVo);
			
			System.out.println("size in action cls;...........");
			System.out.println(searchAccessionNo.size());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchAccessionNo);

			response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getAccessionNo : getAccessionNoByIssueStatus ending");
		return null;
	}

			
	public ActionForward getLibraryLocationBySchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction :getLibraryLocationBySchool Starting");
		try {
		
			String id = request.getParameter("id");
			List<LibraryStockEntryVO> libraryLoc = new LibraryDAOIMPL().getLibraryLocationBySchool(id);
			request.setAttribute("libraryLoc", libraryLoc);
	
			JSONObject obj1= new JSONObject();
			obj1.put("data",libraryLoc);
			response.getWriter().print(obj1);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryAction: getLibraryLocationBySchool ending");

		return null;
	}
	
	
	
	
	
	
	
	
	
	
}


